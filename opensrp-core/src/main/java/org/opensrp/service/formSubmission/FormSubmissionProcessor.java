package org.opensrp.service.formSubmission;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.joda.time.LocalDate;
import org.json.JSONException;
import org.opensrp.common.AllConstants;
import org.opensrp.domain.Client;
import org.opensrp.domain.Event;
import org.opensrp.form.domain.FormField;
import org.opensrp.form.domain.FormSubmission;
import org.opensrp.form.domain.SubFormData;
import org.opensrp.scheduler.HealthSchedulerService;
import org.opensrp.scheduler.Schedule;
import org.opensrp.scheduler.Schedule.ActionType;
import org.opensrp.service.ClientService;
import org.opensrp.service.EventService;
import org.opensrp.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

@Service
public class FormSubmissionProcessor {
	
	private static Logger logger = LoggerFactory.getLogger(FormSubmissionProcessor.class.toString());
	
	private ZiggyService ziggyService;
	
	private FormSubmissionRouter formSubmissionRouter;
	
	private FormEntityConverter formEntityConverter;
	
	private ClientService clientService;
	
	private EventService eventService;
	
	private HealthSchedulerService scheduleService;
	private static final String BRIS_URL = "http://192.168.22.55/rest/index.php";
	
	@Autowired
	public FormSubmissionProcessor(ZiggyService ziggyService, FormSubmissionRouter formSubmissionRouter,
	    FormEntityConverter formEntityConverter, HealthSchedulerService scheduleService, ClientService clientService,
	    EventService eventService) throws IOException {
		this.ziggyService = ziggyService;
		this.formSubmissionRouter = formSubmissionRouter;
		this.formEntityConverter = formEntityConverter;
		this.scheduleService = scheduleService;
		this.clientService = clientService;
		this.eventService = eventService;
	}
	
	public void processFormSubmission(FormSubmission submission) throws Exception {
		// ugly hack TODO
		if (submission.bindType().equalsIgnoreCase("stock"))
			return;
		
		// parse and into client and event model
		//logger.info("Creating model entities");
		makeModelEntities(submission);
		//logger.info("Handling xls configured schedules");
		handleSchedules(submission);
		if (!ziggyService.isZiggyCompliant(submission.bindType())) {
			logger.info("Ziggy active");
			passToZiggy(submission);
			//and skip form submission routing as ziggy does it automatically
		} else {//if not ziggy entity call custom route handler explicitly
			logger.info("Routing to custom handler");
			//formSubmissionRouter.route(submission);
		}
	}
	
	void handleSchedules(FormSubmission submission) throws JSONException, IOException {
		List<Schedule> schl = scheduleService.findAutomatedSchedules(submission.formName());
		for (Schedule sch : schl) {
			Map<String, String> entsch = getEntitiesQualifyingForSchedule(submission, sch);
			System.out.println("creating schedule for : " + entsch);
			for (String enid : entsch.keySet()) {
				if (sch.action().equals(ActionType.enroll)) {
					scheduleService.enrollIntoSchedule(enid, sch.schedule(), sch.milestone(), entsch.get(enid),
					    submission.instanceId());
				} else if (sch.action().equals(ActionType.fulfill)) {
					scheduleService.fullfillMilestoneAndCloseAlert(enid, submission.anmId(), sch.schedule(),
					    LocalDate.parse(entsch.get(enid)), submission.instanceId());
				} else if (sch.action().equals(ActionType.unenroll)) {
					scheduleService.unEnrollFromSchedule(enid, submission.anmId(), sch.schedule(), submission.instanceId());
				} else if (sch.action().equals(ActionType.unenroll) && sch.schedule().equalsIgnoreCase("*")) {
					scheduleService.unEnrollFromAllSchedules(enid, submission.instanceId());
				}
			}
		}
	}
	
	Map<String, String> getEntitiesQualifyingForSchedule(FormSubmission submission, Schedule schedule) throws JSONException {
		Map<String, String> entityIds = new HashMap<String, String>();
		if (schedule.applicableForEntity(submission.bindType())) {
			String res = evaluateScheduleFor(schedule, submission.instance().form().getFieldsAsMap());
			if (!StringUtils.isEmptyOrWhitespaceOnly(res)) {
				entityIds.put(submission.entityId(), res);
			}
		}
		
		if (submission.subForms() != null)
			for (SubFormData sbf : submission.subForms()) {
				if (schedule.applicableForEntity(sbf.bindType())) {
					for (Map<String, String> inst : sbf.instances()) {
						String res = evaluateScheduleFor(schedule, inst);
						if (!StringUtils.isEmptyOrWhitespaceOnly(res)) {
							entityIds.put(inst.get("id"), res);
						}
					}
				}
			}
		return entityIds;
	}
	
	String evaluateScheduleFor(Schedule schedule, Map<String, String> flvl) {
		//find first field in submission that qualifies triggerdate field and has a value
		for (String tf : schedule.triggerDateFields()) {
			String flv = flvl.get(tf);
			// if field has value and schedule flag field is empty or has value 1 or true
			if (!StringUtils.isEmptyOrWhitespaceOnly(flv) && schedule.passesValidations(flvl)) {
				return flv;
			}
		}
		return null;
	}
	
	private void makeModelEntities(FormSubmission submission) throws JSONException, ParseException, IOException {
		Client c = formEntityConverter.getClientFromFormSubmission(submission);
		Event e = formEntityConverter.getEventFromFormSubmission(submission);
		Map<String, Map<String, Object>> dep = formEntityConverter.getDependentClientsFromFormSubmission(submission);
		
		if (clientService.findClient(c) != null) {
			clientService.mergeClient(c);
		} else
			clientService.addClient(c);
		
		updateClientWithBrisEventId(submission, c);
		eventService.addEvent(e);
		// TODO relationships b/w entities
		
		for (Map<String, Object> cm : dep.values()) {
			Client cin = (Client) cm.get("client");
			Event evin = (Event) cm.get("event");
			clientService.addClient(cin);
			eventService.addEvent(evin);
		}
	}
	
	private void passToZiggy(FormSubmission submission) {
		String params = Utils.getZiggyParams(submission);
		ziggyService.saveForm(params, new Gson().toJson(submission.instance()));
	}
	
	public void updateClientWithBrisEventId(FormSubmission fs, Client c) throws ParseException, JSONException, IOException {
		List<FormField> formfileds = fs.instance().form().fields();
		//System.err.println(formfileds.get(29).value());
		String NewBornsUid = formfileds.get(0).value();
		String facilityId = formfileds.get(8).value();
		String DOB = formfileds.get(34).value();
		String FathersNID = formfileds.get(26).value();
		String FathersName = formfileds.get(27).value();
		String FathersDOB = formfileds.get(29).value();
		String MothersNID = formfileds.get(30).value();
		String MothersName = formfileds.get(31).value();
		String MothersDOB = formfileds.get(33).value();
		String urlParameters = "facilityId=" + facilityId + "&NewBornsUid=" + NewBornsUid + "&DOB=" + DOB + "&FathersNID="
		        + FathersNID + "&FathersName=" + FathersName + "&FathersDOB=" + FathersDOB + "&MothersNID=" + MothersNID
		        + "&MothersName=" + MothersName + "&MothersDOB=" + MothersDOB;
		
		/*JSONArray p = new JSONObject(HttpUtil.get(BRIS_URL, urlParameters,
		    "", "").body()).getJSONArray("results");*/
		String url = BRIS_URL;
		String charset = "UTF-8";
		String payload = "";
		String username = "sohel";
		String password = "Sohel@123";
		if (url.endsWith("/")) {
			url = url.substring(0, url.lastIndexOf("/"));
		}
		url = (url + (StringUtils.isEmptyOrWhitespaceOnly(payload) ? "" : ("?" + payload))).replaceAll(" ", "%20");
		URL urlo = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) urlo.openConnection();
		conn.setRequestProperty("Accept-Charset", charset);//Trojanhorse30
		boolean useBasicAuth = false;
		
		if (useBasicAuth) {
			String encoded = new String(Base64.encodeBase64((username + ":" + password).getBytes()));
			conn.setRequestProperty("Authorization", "Basic " + encoded);
		}
		conn.setRequestMethod(HttpMethod.GET.name());
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		StringBuilder sb = new StringBuilder();
		String output;
		while ((output = br.readLine()) != null) {
			sb.append(output);
		}
		System.err.println("Output:  "+sb);
		System.out.println("Param:" + urlParameters);
		Client originalClient = clientService.findClient(c);
		originalClient.addIdentifier(AllConstants.BRISEVENTID, sb.toString());
		try {
			clientService.updateClient(originalClient);
		}
		catch (Exception e) {
			System.out.println(e.getClass() + " : " + e.getMessage());
		}
		
	}
}
