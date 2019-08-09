package org.opensrp.connector.openmrs.schedule;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.motechproject.scheduler.domain.MotechEvent;
import org.motechproject.server.event.annotations.MotechListener;
import org.opensrp.common.util.DateUtil;
import org.opensrp.connector.dhis2.Dhis2TrackCaptureConnector;
import org.opensrp.connector.openmrs.constants.OpenmrsConstants;
import org.opensrp.connector.openmrs.constants.OpenmrsConstants.SchedulerConfig;
import org.opensrp.connector.openmrs.service.EncounterService;
import org.opensrp.connector.openmrs.service.OpenmrsRelationshipService;
import org.opensrp.connector.openmrs.service.OpenmrsSchedulerService;
import org.opensrp.connector.openmrs.service.PatientService;
import org.opensrp.domain.AppStateToken;
import org.opensrp.domain.Client;
import org.opensrp.domain.Event;
import org.opensrp.scheduler.service.ActionService;
import org.opensrp.scheduler.service.ScheduleService;
import org.opensrp.service.ClientService;
import org.opensrp.service.ConfigService;
import org.opensrp.service.ErrorTraceService;
import org.opensrp.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class OpenmrsSyncerListener {

	private static final ReentrantLock lock = new ReentrantLock();

	private static Logger logger = LoggerFactory.getLogger(OpenmrsSyncerListener.class.toString());

	private OpenmrsSchedulerService openmrsSchedulerService;

	private ScheduleService opensrpScheduleService;

	private ActionService actionService;

	private final ConfigService config;

	private final ErrorTraceService errorTraceService;

	private final PatientService patientService;

	private final EncounterService encounterService;

	private final EventService eventService;

	private final ClientService clientService;

	private OpenmrsRelationshipService openmrsRelationshipService;

	@Autowired
	public OpenmrsSyncerListener(OpenmrsSchedulerService openmrsSchedulerService, ScheduleService opensrpScheduleService, ActionService actionService, ConfigService config,
								 ErrorTraceService errorTraceService, PatientService patientService, EncounterService encounterService,
								 ClientService clientService, EventService eventService,  OpenmrsRelationshipService openmrsRelationshipService) {
		this.openmrsSchedulerService = openmrsSchedulerService;
		this.opensrpScheduleService = opensrpScheduleService;
		this.actionService = actionService;
		this.config = config;
		this.errorTraceService = errorTraceService;
		this.patientService = patientService;
		this.encounterService = encounterService;
		this.eventService = eventService;
		this.clientService = clientService;
		this.openmrsRelationshipService = openmrsRelationshipService;

		this.config.registerAppStateToken(SchedulerConfig.openmrs_syncer_sync_schedule_tracker_by_last_update_enrollment, 0,
				"ScheduleTracker token to keep track of enrollment synced with OpenMRS", true);

		this.config.registerAppStateToken(SchedulerConfig.openmrs_syncer_sync_client_by_date_updated, 0,
				"OpenMRS data pusher token to keep track of new / updated clients synced with OpenMRS", true);

		this.config.registerAppStateToken(SchedulerConfig.openmrs_syncer_sync_client_by_date_voided, 0,
				"OpenMRS data pusher token to keep track of voided clients synced with OpenMRS", true);

		this.config.registerAppStateToken(SchedulerConfig.openmrs_syncer_sync_event_by_date_updated, 0,
				"OpenMRS data pusher token to keep track of new / updated events synced with OpenMRS", true);

		this.config.registerAppStateToken(SchedulerConfig.openmrs_syncer_sync_event_by_date_voided, 0,
				"OpenMRS data pusher token to keep track of voided events synced with OpenMRS", true);
	}

	public void pushToOpenMRS() {

		if (!lock.tryLock()) {
			logger.warn("Not fetching forms from Message Queue. It is already in progress.");
			return;
		}
		try {

			logger("RUNNING OPENMRS DATA PUSH Service at " + DateTime.now(), "");

			logger.info("RUNNING FOR EVENTS");

			AppStateToken lastsync = config
					.getAppStateTokenByName(SchedulerConfig.openmrs_syncer_sync_client_by_date_updated);
			Long start = lastsync == null || lastsync.getValue() == null ? 0 : lastsync.longValue();

			List<Client> cl = clientService.findByServerVersion(start);
			logger.info("Clients list size " + cl.size());

			List<Client> clientsWithRelationships = new ArrayList<>();
			for (Client c : cl) {
//				try {
//					//sentTrackCaptureDataToDHIS2(c);
//				} catch (Exception e) {
//					logger.error("DHIS2 Message:" + e.getMessage());
//				}
				try {
					// FIXME This is to deal with existing records and should be
					// removed later
					if (c.getIdentifiers().containsKey("M_ZEIR_ID")) {
						if (c.getBirthdate() == null) {
							c.setBirthdate(new DateTime("01-01-1960"));
						}
						c.setGender("Female");
					}
					String uuid = c.getIdentifier(PatientService.OPENMRS_UUID_IDENTIFIER_TYPE);
					logger.info("the UUID " + uuid);

					if (uuid == null) {
						logger.info("uuid == null");
						JSONObject p = patientService.getPatientByIdentifierPerson(c.getBaseEntityId());
						for (Entry<String, String> id : c.getIdentifiers().entrySet()) {
							p  = patientService.getPatientByIdentifierPerson(id.getValue());
							if (p != null) {
								logger.info("p != null: " );
								break;
							}
						}
						if (p != null) {
							uuid = p.getString("uuid");
						}
					}
					if (uuid != null) {
						logger.info("Updating patient " + uuid);

						patientService.updatePatient(c, uuid);

						if(StringUtils.isBlank(c.getIdentifier(PatientService.OPENMRS_UUID_IDENTIFIER_TYPE))){
							c.addIdentifier(PatientService.OPENMRS_UUID_IDENTIFIER_TYPE, uuid);
							clientService.addorUpdate(c, false);
						}

						config.updateAppStateToken(SchedulerConfig.openmrs_syncer_sync_client_by_date_updated, c.getServerVersion());

					} else {
						logger.info("uuid is null....creating a new patient/person");
						//TODO Find a better more flexible way of identifying the difference between a patient and related person
						JSONObject pJson;
						if(c.getRelationships() == null){
							pJson = patientService.createPerson(c);
						} else {
							pJson = patientService.createPatient(c);
						}

						if (pJson != null && pJson.has("uuid")) {
							c.addIdentifier(PatientService.OPENMRS_UUID_IDENTIFIER_TYPE, pJson.getString("uuid"));
							clientService.addorUpdate(c, false);

							config.updateAppStateToken(SchedulerConfig.openmrs_syncer_sync_client_by_date_updated,
									c.getServerVersion());

						}
					}

					if(c.getRelationships() != null && c.getRelationships().size() > 0){
						clientsWithRelationships.add(c);
					}
				} catch (Exception ex1) {
					ex1.printStackTrace();
					errorTraceService.log("OPENMRS FAILED CLIENT PUSH", Client.class.getName(), c.getBaseEntityId(),
							ExceptionUtils.getStackTrace(ex1), "");
				}
			}

			logger.info("RUNNING FOR RELATIONSHIPS");
			if(clientsWithRelationships.size() > 0){
				for(Client client : clientsWithRelationships){
					Map<String, Map<String, String>> relationshipsMap = client.getRelationships();

					for(String key : relationshipsMap.keySet()){
						Map<String, String> relationship = relationshipsMap.get(key);

						String relativeEntityId = clientService.getByBaseEntityId(relationship.get("relativeEntityId")).getIdentifier(PatientService.OPENMRS_UUID_IDENTIFIER_TYPE);
						String relationshipType = relationship.get("relationshipType");
						String clientEntityId = client.getIdentifier(PatientService.OPENMRS_UUID_IDENTIFIER_TYPE);

						if(StringUtils.isNotBlank(relativeEntityId) && StringUtils.isNotBlank(relationshipType)
								&& StringUtils.isNotBlank(clientEntityId)) {
							logger.info("Relationship required params present, proceeding to make a rest call...");
							openmrsRelationshipService.createRelationship(relativeEntityId, relationshipType, clientEntityId);
						} else {
							logger.info("Relationship required params absent, not making a rest call...[" +
									"relativeEntityId ?: " + relativeEntityId +", relationshipType ?: " + relationshipType
									+", clientEntityId ?: " + clientEntityId);
						}
					}
				}
			}
			pushClient(start);

			logger.info("RUNNING FOR EVENTS");

			lastsync = config.getAppStateTokenByName(SchedulerConfig.openmrs_syncer_sync_event_by_date_updated);
			start = lastsync == null || lastsync.getValue() == null ? 0 : lastsync.longValue();

			pushEvent(start);

			logger("PUSH TO OPENMRS FINISHED AT ", "");

		}
		catch (Exception ex) {
			logger.error("", ex);
		}
		finally {
			lock.unlock();
		}
	}


	public DateTime logger(String message, String subject) {
		logger.info(message + subject + " at " + DateTime.now());
		return DateTime.now();

	}

	public JSONObject pushClient(long start) {
		try {
			List<Client> cl = clientService.findByServerVersion(start);
			logger.info("Clients list size " + cl.size());
			JSONArray patientsJsonArray = new JSONArray();// only for test code purpose
			JSONArray relationshipsArray = new JSONArray();// only for test code purpose
			JSONObject returnJsonObject = new JSONObject();// only for test code purpose
			if (cl != null && !cl.isEmpty()) {
				patientService.processClients(cl, patientsJsonArray,
				    SchedulerConfig.openmrs_syncer_sync_client_by_date_updated, "OPENMRS FAILED CLIENT PUSH");
				logger.info("RUNNING FOR RELATIONSHIPS");
				patientService.createRelationShip(cl, "OPENMRS FAILED CLIENT RELATIONSHIP PUSH");
			}
			returnJsonObject.put("patient", patientsJsonArray); // only for test code purpose
			returnJsonObject.put("relation", relationshipsArray);// only for test code purpose
			return returnJsonObject;
		}
		catch (Exception e) {
			logger.error("", e);
			return null;
		}

	}

	public JSONObject pushEvent(long start) {
		List<Event> el = eventService.findByServerVersion(start);
		logger.info("Event list size " + el.size() + " [start]" + start);
		JSONObject encounter = null;
		if (el != null && !el.isEmpty())
			for (Event e : el) {
				try {
					String uuid = e.getIdentifier(EncounterService.OPENMRS_UUID_IDENTIFIER_TYPE);
					if (uuid != null) {
						encounter = encounterService.updateEncounter(e);
						config.updateAppStateToken(SchedulerConfig.openmrs_syncer_sync_event_by_date_updated,
								e.getServerVersion());
					} else {
						JSONObject eventJson = encounterService.createEncounter(e);
						encounter = eventJson;// only for test code purpose
						if (eventJson != null && eventJson.has("uuid")) {
							e.addIdentifier(EncounterService.OPENMRS_UUID_IDENTIFIER_TYPE, eventJson.getString("uuid"));
							eventService.updateEvent(e);
							config.updateAppStateToken(SchedulerConfig.openmrs_syncer_sync_event_by_date_updated,
									e.getServerVersion());
						}
					}
				}
				catch (Exception ex2) {
					logger.error("", ex2);
					errorTraceService.log("OPENMRS FAILED EVENT PUSH", Event.class.getName(), e.getId(),
							ExceptionUtils.getStackTrace(ex2), "");
				}
			}
		return encounter;

	}

}
