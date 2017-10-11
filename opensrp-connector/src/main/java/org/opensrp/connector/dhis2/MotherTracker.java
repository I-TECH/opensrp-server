/**
 * 
 */
package org.opensrp.connector.dhis2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opensrp.domain.Client;
import org.opensrp.domain.Event;
import org.opensrp.domain.Obs;
import org.opensrp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author proshanto
 */
@Service
public class MotherTracker extends DHIS2Service implements DHIS2Tracker {
	
	@Autowired
	private DHIS2TrackerService dhis2TrackerService;
	
	@Autowired
	private EventService eventService;
	
	public MotherTracker() {
		
	}
	
	public MotherTracker(String dhis2Url, String user, String password) {
		super(dhis2Url, user, password);
	}
	
	@Override
	public JSONArray getTrackCaptureData(Client client) throws JSONException {
		JSONObject clientData = new JSONObject();
		
		JSONArray generateTrackCaptureData = new JSONArray();
		Map<String, Object> attributes = new HashMap<>();
		attributes = client.getAttributes();
		JSONObject attributesAsJson = new JSONObject(attributes);
		JSONObject clientAsJson = new JSONObject(client);
		generateTrackCaptureData.put(dhis2TrackerService.getTrackCaptureData(clientAsJson, DHIS2Settings.MOTHERIDMAPPING
		        .get(DHIS2Settings.FIRSTNAME).toString(), DHIS2Settings.FIRSTNAME));
		// LastName
		generateTrackCaptureData.put(dhis2TrackerService.getTrackCaptureData(clientAsJson, DHIS2Settings.MOTHERIDMAPPING
		        .get(DHIS2Settings.LASTNAME).toString(), DHIS2Settings.LASTNAME));
		//birthdate		
		JSONObject birthDate = new JSONObject();
		birthDate.put(DHIS2Settings.ATTRIBUTEKEY, DHIS2Settings.MOTHERIDMAPPING.get("birthdate").toString());
		birthDate.put(DHIS2Settings.VALUEKEY, client.getBirthdate());
		generateTrackCaptureData.put(birthDate);
		// registration date
		JSONObject registrationDate = new JSONObject();
		registrationDate.put(DHIS2Settings.ATTRIBUTEKEY, DHIS2Settings.MOTHERIDMAPPING.get("registration_Date").toString());
		registrationDate.put(DHIS2Settings.VALUEKEY, client.getDateCreated());
		generateTrackCaptureData.put(registrationDate);
		
		// Phone number
		generateTrackCaptureData.put(dhis2TrackerService.getTrackCaptureData(attributesAsJson, DHIS2Settings.MOTHERIDMAPPING
		        .get("phone_Number").toString(), "phoneNumber"));
		
		// ID(NID,BRID)
		generateTrackCaptureData.put(dhis2TrackerService.getTrackCaptureData(attributesAsJson, DHIS2Settings.MOTHERIDMAPPING
		        .get("NID_BRID").toString(), "nationalId"));
		
		// ID(NID,BRID)
		generateTrackCaptureData.put(dhis2TrackerService.getTrackCaptureData(attributesAsJson, DHIS2Settings.MOTHERIDMAPPING
		        .get("houband_Name").toString(), "spouseName"));
		
		/***** get information form Event ******/
		
		List<Event> event = eventService.findByBaseEntityAndType(client.getBaseEntityId(), "New Woman Member Registration");
		
		List<Obs> observations = event.get(0).getObs();
		/**** Member_Registration_No ***/
		generateTrackCaptureData.put(dhis2TrackerService.getTrackCaptureDataFromEventByValues(observations,
		    DHIS2Settings.MOTHERIDMAPPING.get("Member_Registration_No").toString(), "reg_No"));
		/**** epi_card_number ***/
		generateTrackCaptureData.put(dhis2TrackerService.getTrackCaptureDataFromEventByValues(observations,
		    DHIS2Settings.MOTHERIDMAPPING.get("EPI_Card_Number").toString(), "epi_card_number"));
		/**** Maritial_Status ***/
		generateTrackCaptureData.put(dhis2TrackerService.getTrackCaptureDataFromEventByValues(observations,
		    DHIS2Settings.MOTHERIDMAPPING.get("Maritial_Status").toString(), "maritial_status"));
		/** Couple_No */
		generateTrackCaptureData.put(dhis2TrackerService.getTrackCaptureDataFromEventByValues(observations,
		    DHIS2Settings.MOTHERIDMAPPING.get("Couple_No").toString(), "couple_no"));
		
		/** pregnant */
		generateTrackCaptureData.put(dhis2TrackerService.getTrackCaptureDataFromEventByValues(observations,
		    DHIS2Settings.MOTHERIDMAPPING.get("Pregnant").toString(), "pregnant"));
		
		/** FP_User */
		generateTrackCaptureData.put(dhis2TrackerService.getTrackCaptureDataFromEventByValues(observations,
		    DHIS2Settings.MOTHERIDMAPPING.get("FP_User").toString(), "fp_user"));
		
		/** FP_Methods */
		generateTrackCaptureData.put(dhis2TrackerService.getTrackCaptureDataFromEventByHumanReadableValues(observations,
		    DHIS2Settings.MOTHERIDMAPPING.get("FP_Methods").toString(), "fp_methods"));
		
		/** EDD_LMP */
		generateTrackCaptureData.put(dhis2TrackerService.getTrackCaptureDataFromEventByHumanReadableValues(observations,
		    DHIS2Settings.MOTHERIDMAPPING.get("EDD_LMP").toString(), "edd_lmp"));
		
		/** EDD */
		generateTrackCaptureData.put(dhis2TrackerService.getTrackCaptureDataFromEventByValues(observations,
		    DHIS2Settings.MOTHERIDMAPPING.get("EDD").toString(), "edd"));
		
		/****************/
		clientData.put(DHIS2Settings.ATTRIBUTSEKEY, generateTrackCaptureData);
		
		return generateTrackCaptureData;
	}
	
	@Override
	public JSONObject sendTrackCaptureData(JSONArray attributes) throws JSONException {
		String orgUnit = "IDc0HEyjhvL";
		String program = "OprRhyWVIM6";
		JSONObject clientData = new JSONObject();
		/*JSONArray enrollments = new JSONArray();
		JSONObject enrollmentsObj = new JSONObject();
		enrollmentsObj.put(DHIS2Settings.ORGUNITKEY, orgUnit);
		enrollmentsObj.put(DHIS2Settings.PROGRAM, program);
		enrollmentsObj.put("enrollmentDate", DateUtil.getTodayAsString());
		enrollmentsObj.put("incidentDate", DateUtil.getTodayAsString());
		enrollments.put(enrollmentsObj);*/
		//clientData.put("enrollments", enrollments);
		clientData.put(DHIS2Settings.ATTRIBUTSEKEY, attributes);
		clientData.put("trackedEntity", "MCPQUTHX1Ze");
		clientData.put(DHIS2Settings.ORGUNITKEY, orgUnit);
		
		JSONObject responseTrackEntityInstance = new JSONObject(Dhis2HttpUtils.post(
		    DHIS2_BASE_URL.replaceAll(DHIS2Settings.REPLACE, "") + "trackedEntityInstances", "", clientData.toString(),
		    DHIS2_USER.replaceAll(DHIS2Settings.REPLACE, ""), DHIS2_PWD.replaceAll(DHIS2Settings.REPLACE, "")).body());
		JSONObject trackEntityReference = (JSONObject) responseTrackEntityInstance.get("response");
		
		JSONObject enroll = new JSONObject();
		enroll.put("trackedEntityInstance", trackEntityReference.get("reference"));
		enroll.put(DHIS2Settings.PROGRAM, program);
		enroll.put(DHIS2Settings.ORGUNITKEY, orgUnit);
		
		JSONObject response = new JSONObject(Dhis2HttpUtils.post(
		    DHIS2_BASE_URL.replaceAll(DHIS2Settings.REPLACE, "") + "enrollments", "", enroll.toString(),
		    DHIS2_USER.replaceAll(DHIS2Settings.REPLACE, ""), DHIS2_PWD.replaceAll(DHIS2Settings.REPLACE, "")).body());
		
		response.put("track", trackEntityReference.get("reference"));
		
		return response;
	}
}
