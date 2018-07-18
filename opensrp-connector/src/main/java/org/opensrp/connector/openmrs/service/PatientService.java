package org.opensrp.connector.openmrs.service;

import com.mysql.jdbc.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opensrp.api.domain.Location;
import org.opensrp.api.util.LocationTree;
import org.opensrp.common.util.HttpResponse;
import org.opensrp.common.util.HttpUtil;
import org.opensrp.connector.MultipartUtility;
import org.opensrp.connector.openmrs.constants.OpenmrsConstants;
import org.opensrp.connector.openmrs.schedule.OpenmrsSyncerListener;
import org.opensrp.connector.openmrs.service.OpenmrsLocationService.AllowedLevels;
import org.opensrp.domain.*;
import org.opensrp.service.ClientService;
import org.opensrp.service.ConfigService;
import org.opensrp.service.ErrorTraceService;
import org.opensrp.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Service
public class PatientService extends OpenmrsService {

	// This ID should start with opensrp and end with uid. As matched by atomefeed module`s patient service
	public static final String OPENSRP_IDENTIFIER_TYPE = "OpenSRP Thrive UID";

	public static final String OPENSRP_IDENTIFIER_TYPE_MATCHER = "(?i)opensrp.*uid";

	public static final String OPENMRS_UUID_IDENTIFIER_TYPE = "OPENMRS_UUID";

	public static final String PERSON_ADDRESS_URL_PARAM = "/address";

	//TODO include everything for patient registration. i.e. person, person name, patient identifier
	// include get for patient on different params like name, identifier, location, uuid, attribute,etc
	//person methods should be separate
	private static final String PERSON_URL = "ws/rest/v1/person";

	private static final String OBS_URL = "ws/rest/v1/obs";

	private static final String PATIENT_URL = "ws/rest/v1/patient";

	private static final String PATIENT_IMAGE_URL = "ws/rest/v1/patientimage/uploadimage";

	private static final String PATIENT_IDENTIFIER_URL = "identifier";

	private static final String PERSON_ATTRIBUTE_URL = "attribute";

	private static final String PERSON_ATTRIBUTE_TYPE_URL = "ws/rest/v1/personattributetype";

	private static final String PATIENT_IDENTIFIER_TYPE_URL = "ws/rest/v1/patientidentifiertype";

	private static final String PATIENT_RELATIONSHIP_URL = "ws/rest/v1/relationship";

	private static final String CUSTOM_UUID_PARAM = "v=custom:(uuid)";

	private static final String CUSTOM_PERSON_PARAM = "v=custom:(person)";

	public static final String CUSTOM_PERSON_UUID_PARAM = "v=custom:(person:(uuid))";

	public static final String UUID_KEY = "uuid";

	public static final String RESULTS_KEY = "results";

	public static final String PERSON_KEY = "person";

	private static Logger logger = LoggerFactory.getLogger(OpenmrsSyncerListener.class.toString());

	private ClientService clientService;

	private EventService eventService;

	private OpenmrsLocationService openmrsLocationService;

	private ConfigService config;



	public PatientService() {
	}

	@Autowired
	public PatientService(ClientService clientService, OpenmrsLocationService openmrsLocationService,
	                      EventService eventService, ConfigService config) {
		this.clientService = clientService;
		this.openmrsLocationService = openmrsLocationService;
		this.eventService = eventService;
		this.config = config;

	}

	public PatientService(String openmrsUrl, String user, String password) {
		super(openmrsUrl, user, password);
	}

	public JSONObject getPatientByIdentifier(String identifier) throws JSONException {
		JSONObject j = new JSONObject(
				HttpUtil.get(getURL() + "/" + PATIENT_URL, "v=full&identifier=" + identifier, OPENMRS_USER, OPENMRS_PWD)
						.body());
		if (j != null && j.has("results") && j.get("results") instanceof JSONArray) {
			JSONArray p = j.getJSONArray("results");
			return p.length() > 0 ? p.getJSONObject(0) : null;
		}
		return null;
	}

	public JSONObject getPatientByUuid(String uuid, boolean noRepresentationTag) throws JSONException {
		return new JSONObject(
				HttpUtil.get(getURL() + "/" + PATIENT_URL + "/" + uuid, noRepresentationTag ? "" : "v=full", OPENMRS_USER,
						OPENMRS_PWD).body());
	}

	public JSONObject getPersonAddressByUuid(String uuid, boolean noRepresentationTag) throws JSONException {
		return new JSONObject(HttpUtil.get(getURL() + "/" + PERSON_URL + "/" + uuid + PERSON_ADDRESS_URL_PARAM,
				noRepresentationTag ? "" : "v=full", OPENMRS_USER, OPENMRS_PWD).body());
	}

	public JSONObject getIdentifierType(String identifierType) throws JSONException {
		// we have to use this ugly approach because identifier not found throws exception and
		// its hard to find whether it was network error or object not found or server error
		JSONObject resIdentifier = new JSONObject(
				HttpUtil.get(getURL() + "/" + PATIENT_IDENTIFIER_TYPE_URL, "v=full", OPENMRS_USER, OPENMRS_PWD).body());

		if (resIdentifier.has("results") && resIdentifier.get("results") instanceof JSONArray) {
			JSONArray res = resIdentifier.getJSONArray("results");
			for (int j = 0; j < res.length(); j++) {
				if (res.getJSONObject(j).getString("display").equalsIgnoreCase(identifierType)) {
					return res.getJSONObject(j);
				}
			}
		}
		return null;
	}

	public JSONObject createIdentifierType(String name, String description) throws JSONException {
		JSONObject o = convertIdentifierToOpenmrsJson(name, description);
		return new JSONObject(
				HttpUtil.post(getURL() + "/" + PATIENT_IDENTIFIER_TYPE_URL, "", o.toString(), OPENMRS_USER, OPENMRS_PWD)
						.body());
	}

	private String getUuidFromJSONObject(JSONObject object) {
		try {
			if (object.has(RESULTS_KEY) && object.get(RESULTS_KEY) instanceof JSONArray) {
				JSONArray p = object.getJSONArray(RESULTS_KEY);
				if (p.length() > 0) {
					JSONObject resJson = p.getJSONObject(0);
					if (resJson.has(UUID_KEY)) {
						return resJson.getString(UUID_KEY);
					}
				}
			}
			return null;
		}
		catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	private JSONObject getRelationshipFromOpenMRS(String personUUID) {
		try {
			return new JSONObject(
					HttpUtil.get(getURL() + "/" + PATIENT_RELATIONSHIP_URL + "/" + personUUID, "", OPENMRS_USER, OPENMRS_PWD)
							.body());
		}
		catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean checkIfRelationShipExist(String personB, String personA, String relationshipType) {
		JSONArray res;
		JSONObject relationship;
		String relationShipUUID;
		String relationShipPersonA;
		String relationShipPersonB;
		try {
			res = new JSONObject(
					HttpUtil.get(getURL() + "/" + PATIENT_RELATIONSHIP_URL, "person=" + personA, OPENMRS_USER, OPENMRS_PWD)
							.body()).getJSONArray("results");
			for (int i = 0; i < res.length(); i++) {
				if (res.getJSONObject(i).getString("uuid") != null) {

					relationship = new JSONObject(HttpUtil.get(
							getURL() + "/" + PATIENT_RELATIONSHIP_URL + "/" + res.getJSONObject(i).getString("uuid"), "",
							OPENMRS_USER, OPENMRS_PWD).body());
					if (relationship.getString("relationshipType") != null && relationship.getString("personB") != null
							&& relationship.getString("personA") != null) {
						relationShipUUID = new JSONObject(relationship.getString("relationshipType")).getString("uuid");
						relationShipPersonB = new JSONObject(relationship.getString("personB")).getString("uuid");
						relationShipPersonA = new JSONObject(relationship.getString("personA")).getString("uuid");

						if (relationShipUUID.equals(relationshipType) && relationShipPersonB.equals(personB)
								&& relationShipPersonA.equals(personA)) {
							return true;
						}

					}
				}
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}

		return false;
	}

	public JSONObject convertIdentifierToOpenmrsJson(String name, String description) throws JSONException {
		JSONObject a = new JSONObject();
		a.put("name", name);
		a.put("description", description);
		return a;
	}


	public JSONObject getPersonAttributeType(String attributeName) throws JSONException {
		JSONObject resAttributeType = new JSONObject(
				HttpUtil.get(getURL() + "/" + PERSON_ATTRIBUTE_TYPE_URL, "v=full&q=" + attributeName, OPENMRS_USER,
						OPENMRS_PWD).body());
		if (resAttributeType.has("results") && resAttributeType.get("results") instanceof JSONArray) {

			JSONArray res = resAttributeType.getJSONArray("results");

			return res.length() > 0 ? res.getJSONObject(0) : null;
		}
		return null;
	}

	public JSONObject createPerson(Client be) throws JSONException {
		logger.info("createperson client is "+ be);
		JSONObject per = convertBaseEntityToOpenmrsJson(be, false);
		logger.info("PERSON TO CREATE " + per.toString());
		String response = HttpUtil.post(getURL() + "/" + PERSON_URL, "", per.toString(), OPENMRS_USER, OPENMRS_PWD).body();
		logger.info("PERSON TO CREATE RESPONSE ----" + response);
		JSONObject jsonResponse = new JSONObject(response);

		if(jsonResponse.has("error")){
			JSONObject responseError = new JSONObject(jsonResponse.getString("error"));
			if(responseError.has("message")&& responseError.getString("message").equals("User is not logged in")){
				be.setServerVersion(null);
				clientService.updateClient(be);
			}
		}
		return new JSONObject(response);
	}

	public JSONObject convertBaseEntityToOpenmrsJson(Client be, boolean update) throws JSONException {
		JSONObject per = new JSONObject();
		per.put("gender", be.getGender());
		per.put("birthdate", OPENMRS_DATE.format(be.getBirthdate().toDate()));
		per.put("birthdateEstimated", be.getBirthdateApprox());
		if (be.getDeathdate() != null) {
			per.put("deathDate", OPENMRS_DATE.format(be.getDeathdate().toDate()));
		}

		String fn = be.getFirstName() == null || StringUtils.isEmptyOrWhitespaceOnly(be.getFirstName()) ?
				"-" :
				be.getFirstName();
		if (!fn.equals("-")) {
			fn = fn.replaceAll("[^A-Za-z0-9\\s]+", "");
		}

		String mn = be.getMiddleName() == null ? "" : be.getMiddleName();

		if (!mn.equals("-")) {
			mn = mn.replaceAll("[^A-Za-z0-9\\s]+", "");
		}

		String ln = (be.getLastName() == null || be.getLastName().equals(".")) ? "-" : be.getLastName();
		if (!ln.equals("-")) {
			ln = ln.replaceAll("[^A-Za-z0-9\\s]+", "");
		}

		List<Event> registrationEvents = eventService.findByBaseEntityId(be.getBaseEntityId());
		for (Event event : registrationEvents) {
			if (event.getEventType().equals("Birth Registration")) {
				List<Obs> obs = event.getObs();
				for (Obs obs2 : obs) {
					if (obs2 != null && obs2.getFieldType().equals("formsubmissionField") && obs2.getFormSubmissionField().equals("Home_Facility") && obs2.getValue() != null) {
						String clientAddress4 = openmrsLocationService.getLocation(obs2.getValue().toString()).getName();
						if (be.getAttribute("Home_Facility") != null) {
							be.removeAttribute("Home_Facility");
						}
						be.addAttribute("Home_Facility", clientAddress4);
					}
				}
			}
			break;
		}
		per.put("attributes", convertAttributesToOpenmrsJson(be.getAttributes()));

		if (!update) {
			per.put("names", new JSONArray(
					"[{\"givenName\":\"" + fn + "\",\"middleName\":\"" + mn + "\", \"familyName\":\"" + ln + "\"}]"));
			per.put("addresses", convertAddressesToOpenmrsJson(be));
		}
		return per;
	}

	public JSONArray convertAttributesToOpenmrsJson(Map<String, Object> attributes) throws JSONException {
		if (CollectionUtils.isEmpty(attributes)) {
			return null;
		}
		JSONArray attrs = new JSONArray();
		for (Entry<String, Object> at : attributes.entrySet()) {
			JSONObject a = new JSONObject();
			a.put("attributeType", getPersonAttributeType(at.getKey()).getString("uuid"));
			a.put("value", at.getValue());
			attrs.put(a);
		}

		return attrs;
	}

	public JSONArray convertAddressesToOpenmrsJson(Client client) throws JSONException {
		List<Address> adl = client.getAddresses();
		if (CollectionUtils.isEmpty(adl)) {
			return null;
		}
		JSONArray jaar = new JSONArray();
		for (Address ad : adl) {
			JSONObject jao = new JSONObject();
			if (ad.getAddressFields() != null) {
				jao.put("address1", ad.getAddressFieldMatchingRegex("(?i)(ADDRESS1|HOUSE_NUMBER|HOUSE|HOUSE_NO|UNIT|UNIT_NUMBER|UNIT_NO)"));
				jao.put("address2", ad.getAddressFieldMatchingRegex("(?i)(ADDRESS2|STREET|STREET_NUMBER|STREET_NO|LANE)"));
				jao.put("address3", ad.getAddressFieldMatchingRegex("(?i)(ADDRESS3|SECTOR|AREA)"));
				jao.put("address4", ad.getAddressFieldMatchingRegex("(?i)(ADDRESS4|SUB_DISTRICT|MUNICIPALITY|TOWN|LOCALITY|REGION)"));

				List<Event> registrationEvents = eventService.findByBaseEntityId(client.getBaseEntityId());
				for (Event event : registrationEvents) {
					if (event.getEventType().equals("Birth Registration")
							|| event.getEventType().equals("Child Enrollment")) {

						for (Obs obs2 : event.getObs()) {
							if (obs2 != null && obs2.getFieldType().equals("formsubmissionField") && obs2
									.getFormSubmissionField().equals("Home_Facility") && obs2.getValue() != null) {
								jao.put("address5", fetchLocationByUUID(obs2.getValue().toString()));
								break;
							}
						}
					}
				}
			}
			jao.put("address6", ad.getAddressType());
			jao.put("cityVillage", ad.getCityVillage());
			jao.put("countyDistrict", ad.getCountyDistrict());
			jao.put("stateProvince", ad.getStateProvince());
			jao.put("country", ad.getCountry());
			jao.put("postalCode", ad.getPostalCode());
			jao.put("latitude", ad.getLatitude());
			jao.put("longitude", ad.getLongitude());
			if (ad.getStartDate() != null) {
				jao.put("startDate", OPENMRS_DATE.format(ad.getStartDate().toDate()));
			}
			if (ad.getEndDate() != null) {
				jao.put("endDate", OPENMRS_DATE.format(ad.getEndDate().toDate()));
			}

			jaar.put(jao);
		}

		return jaar;
	}

	public JSONObject createPatient(Client c) throws JSONException {
		JSONObject p = new JSONObject();
		p.put("person", createPerson(c).getString("uuid"));
		JSONArray ids = new JSONArray();
		if (c.getIdentifiers() != null) {
			for (Entry<String, String> id : c.getIdentifiers().entrySet()) {
				JSONObject jio = new JSONObject();
				JSONObject idobj = getIdentifierType(id.getKey());
				if (idobj == null) {
					idobj = createIdentifierType(id.getKey(), id.getKey() + " - FOR THRIVE OPENSRP");
				}
				jio.put("identifierType", idobj.getString("uuid"));
				jio.put("identifier", id.getValue());
				Object cloc = c.getAttribute("Location");
				jio.put("location", cloc == null ? "Unknown Location" : cloc);
				//jio.put("preferred", true);

				ids.put(jio);
			}
		}
		JSONObject jio = new JSONObject();
		JSONObject ido = getIdentifierType(OPENSRP_IDENTIFIER_TYPE);
		if (ido == null) {
			ido = createIdentifierType(OPENSRP_IDENTIFIER_TYPE, OPENSRP_IDENTIFIER_TYPE + " - FOR THRIVE OPENSRP");
		}
		jio.put("identifierType", ido.getString("uuid"));
		jio.put("identifier", c.getBaseEntityId());
		Object cloc = c.getAttribute("Location");
		jio.put("location", cloc == null ? "Unknown Location" : cloc);
		jio.put("preferred", true);

		ids.put(jio);

		p.put("identifiers", ids);
		String response = HttpUtil.post(getURL() + "/" + PATIENT_URL, "", p.toString(), OPENMRS_USER, OPENMRS_PWD).body();
		logger.info("the jsonobject"+ response);
		return new JSONObject(response);
	}

	public JSONObject updatePatientIdentifier(String patientUUID, String identifierUUID, String newIdentifier)
			throws JSONException {
		String url = "ws/rest/v1/patient/" + patientUUID + "/identifier/" + identifierUUID;
		JSONObject p = new JSONObject();
		p.put("identifier", newIdentifier);

		return new JSONObject(HttpUtil.post(getURL() + "/" + url, "", p.toString(), OPENMRS_USER, OPENMRS_PWD).body());

	}

	public JSONObject updatePatient(Client c, String uuid) throws JSONException {
		JSONObject p = new JSONObject();

		p.put("person", convertBaseEntityToOpenmrsJson(c, true));
		JSONArray ids = new JSONArray();
		if (c.getIdentifiers() != null) {
			updateIdentifiers(uuid, c);
		}
		c.setAddresses(null);
		JSONObject jio = new JSONObject();
		JSONObject ido = getIdentifierType(OPENSRP_IDENTIFIER_TYPE);
		if (ido == null) {
			ido = createIdentifierType(OPENSRP_IDENTIFIER_TYPE, OPENSRP_IDENTIFIER_TYPE + " - FOR THRIVE OPENSRP");
		}
		jio.put("identifierType", ido.getString("uuid"));
		jio.put("identifier", c.getBaseEntityId());
		Object cloc = c.getAttribute("Location");
		jio.put("location", cloc == null ? "Unknown Location" : cloc);
		jio.put("preferred", true);
		ids.put(jio);

		p.put("identifiers", ids);
		return new JSONObject(
				HttpUtil.post(getURL() + "/" + PATIENT_URL + "/" + uuid, "", p.toString(), OPENMRS_USER, OPENMRS_PWD)
						.body());
	}

	public JSONObject addThriveId(String baseEntityId, JSONObject patient) throws JSONException {
		JSONObject jio = new JSONObject();
		JSONObject ido = getIdentifierType(OPENSRP_IDENTIFIER_TYPE);
		if (ido == null) {
			ido = createIdentifierType(OPENSRP_IDENTIFIER_TYPE, OPENSRP_IDENTIFIER_TYPE + " - FOR THRIVE OPENSRP");
		}
		jio.put("identifierType", ido.getString("uuid"));
		jio.put("identifier", baseEntityId);
		jio.put("location", "Unknown Location");
		jio.put("preferred", true);

		return new JSONObject(
				HttpUtil.post(getURL() + "/" + PATIENT_URL + "/" + patient.getString("uuid") + "/" + PATIENT_IDENTIFIER_URL,
						"", jio.toString(), OPENMRS_USER, OPENMRS_PWD).body());
	}

	public Client convertToClient(JSONObject patient) throws JSONException {
		Client c = new Client(null);
		JSONArray ar = patient.getJSONArray("identifiers");
		for (int i = 0; i < ar.length(); i++) {
			JSONObject ji = ar.getJSONObject(i);
			if (ji.getJSONObject("identifierType").getString("display").equalsIgnoreCase(OPENSRP_IDENTIFIER_TYPE)) {
				c.setBaseEntityId(ji.getString("identifier"));
			} else {
				c.addIdentifier(ji.getJSONObject("identifierType").getString("display"), ji.getString("identifier"));
			}
		}

		c.addIdentifier(OPENMRS_UUID_IDENTIFIER_TYPE, patient.getString("uuid"));

		JSONObject pr = patient.getJSONObject("person");

		String mn = pr.getJSONObject("preferredName").has("middleName") ?
				pr.getJSONObject("preferredName").getString("middleName") :
				null;
		DateTime dd = pr.has("deathDate") && !pr.getString("deathDate").equalsIgnoreCase("null") ?
				new DateTime(pr.getString("deathDate")) :
				null;
		c.withFirstName(pr.getJSONObject("preferredName").getString("givenName")).withMiddleName(mn)
				.withLastName(pr.getJSONObject("preferredName").getString("familyName")).withGender(pr.getString("gender"))
				.withBirthdate(new DateTime(pr.getString("birthdate")), pr.getBoolean("birthdateEstimated"))
				.withDeathdate(dd, false);

		if (pr.has("attributes")) {
			for (int i = 0; i < pr.getJSONArray("attributes").length(); i++) {
				JSONObject at = pr.getJSONArray("attributes").getJSONObject(i);
				if (at.optJSONObject("value") == null) {
					c.addAttribute(at.getJSONObject("attributeType").getString("display"), at.getString("value"));
				} else {
					c.addAttribute(at.getJSONObject("attributeType").getString("display"),
							at.getJSONObject("value").getString("display"));
				}
			}
		}

		if (pr.has("addresses")) {
			for (int i = 0; i < pr.getJSONArray("addresses").length(); i++) {
				JSONObject ad = pr.getJSONArray("addresses").getJSONObject(i);
				DateTime startDate = ad.has("startDate") && !ad.getString("startDate").equalsIgnoreCase("null") ?
						new DateTime(ad.getString("startDate")) :
						null;
				DateTime endDate = ad.has("startDate") && !ad.getString("endDate").equalsIgnoreCase("null") ?
						new DateTime(ad.getString("endDate")) :
						null;
				Address a = new Address(ad.getString("address6"), startDate, endDate, null, ad.getString("latitude"),
						ad.getString("longitude"), ad.getString("postalCode"), ad.getString("stateProvince"),
						ad.getString("country"));
				//a.setGeopoint(geopoint);
				a.setSubTown(ad.getString("address2"));//TODO
				a.setTown(ad.getString("address3"));
				a.setSubDistrict(ad.getString("address4"));
				a.setCountyDistrict(ad.getString("countyDistrict"));
				a.setCityVillage(ad.getString("cityVillage"));

				c.addAddress(a);
			}

		}
		return c;
	}

	public void patientImageUpload(Multimedia multimedia) throws IOException {
		//String requestURL =  "http://46.101.51.199:8080/openmrs/ws/rest/v1/patientimage/uploadimage";

		try {
			File convFile = new File("/opt" + multimedia.getFilePath());
			MultipartUtility multipart = new MultipartUtility(getURL() + "/" + PATIENT_IMAGE_URL, OPENMRS_USER, OPENMRS_PWD);
			multipart.addFormField("patientidentifier", multimedia.getCaseId());
			multipart.addFormField("category", multimedia.getFileCategory());
			multipart.addFilePart("file", convFile);

			List<String> response = multipart.finish();

			System.out.println("SERVER REPLIED:");

			for (String line : response) {
				System.out.println(line);
			}
		}
		catch (IOException ex) {
			System.err.println(ex);
		}
	}

	public JSONObject updatePersonAsDeceased(Event deathEvent) throws JSONException {
		JSONObject patientObject = getPatientByIdentifier(deathEvent.getBaseEntityId());
		JSONObject requestBody = new JSONObject();

		String patientUUID = patientObject.getString("uuid");

		requestBody.put("dead", true);
		requestBody.put("deathDate", OPENMRS_DATE.format(deathEvent.getEventDate().toDate()));
		requestBody.put("causeOfDeath", PROBABLE_CAUSE_PARENT_CONCEPT);

		HttpResponse op = HttpUtil
				.post(HttpUtil.removeEndingSlash(OPENMRS_BASE_URL) + "/" + PERSON_URL + "/" + patientUUID, "",
						requestBody.toString(), OPENMRS_USER, OPENMRS_PWD);

		return new JSONObject(op.body());

	}

	public JSONObject updatePersonName(JSONObject patientObject, Client client) throws JSONException {

		JSONObject nameObject = patientObject.getJSONObject("preferredName");

		String fn = client.getFirstName() == null || client.getFirstName().isEmpty() ? "-" : client.getFirstName();
		if (!fn.equals("-")) {
			fn = fn.replaceAll("[^A-Za-z0-9\\s]+", "");
		}

		String ln = (client.getLastName() == null || client.getLastName().equals(".")) ? "-" : client.getLastName();
		if (!ln.equals("-")) {
			ln = ln.replaceAll("[^A-Za-z0-9\\s]+", "");
		}

		if (fn.equals(nameObject.getString("givenName")) && ln.equals(nameObject.getString("givenName"))) {
			return null;
		}

		JSONObject requestBody = new JSONObject();
		requestBody.put("givenName", fn);
		requestBody.put("familyName", ln);
		String url = "ws/rest/v1/person/" + patientObject.getString("uuid") + "/name/" + nameObject.getString("uuid");

		return new JSONObject(
				HttpUtil.post(getURL() + "/" + url, "", requestBody.toString(), OPENMRS_USER, OPENMRS_PWD).body());

	}

	public JSONObject updatePersonAddressAndName(Event addressUpdateEvent) throws JSONException {
		JSONObject j = getPatientByIdentifier(addressUpdateEvent.getBaseEntityId());
		if(j == null) {
			logger.info("updatePersonAddressAndName: patientObject is null, " + addressUpdateEvent.toString());
			return new JSONObject();
		}

		JSONObject patientObject = j.getJSONObject("person");
		Client client = clientService.getByBaseEntityId(addressUpdateEvent.getBaseEntityId());

		//update person names if any changes were made
		updatePersonName(patientObject, client);

		List<Address> clientAddresses = client.getAddresses();

		JSONObject addressObject = patientObject.getJSONObject("preferredAddress");
		String clientAddress;

		JSONObject requestBody = new JSONObject();

		Map<String, String> addressMap = clientAddresses.get(0).getAddressFields();

		for (Map.Entry<String, String> entry : addressMap.entrySet()) {
			clientAddress = entry.getValue();

			if (!clientAddress.equals(addressObject.getString(entry.getKey()))) {
				requestBody.put(entry.getKey(), clientAddress);
			}
		}

		// Home_Facility is a hidden field and is the facility where the client was registered

		String url = "ws/rest/v1/person/" + patientObject.getString("uuid") + "/address/" + addressObject.getString("uuid");
		return new JSONObject(
				HttpUtil.post(getURL() + "/" + url, "", requestBody.toString(), OPENMRS_USER, OPENMRS_PWD).body());

	}

	public JSONObject moveToCatchment(Event event) {
		String clientAddress4 = null;
		List<Obs> obs = event.getObs();
		for (Obs obs2 : obs) {
			if (obs2.getFieldCode() != null && obs2.getFieldCode().equals("To_LocationId") && obs2.getValue() != null) {
				{
					clientAddress4 = fetchLocationByUUID(obs2.getValue().toString());
					break;
				}
			}
		}
		if (clientAddress4 == null) {
			return null;
		}
		return postNewAddress(event, clientAddress4);
	}

	public JSONObject postNewAddress(Event event, String clientAddress4) {
		try {

			Client client = clientService.getByBaseEntityId(event.getBaseEntityId());
			JSONObject patientObject = getPatientByIdentifier(client.getBaseEntityId());
			JSONObject addressObject = patientObject.getJSONObject("preferredAddress");
			JSONObject updateAddress = convertAddressesToOpenmrsJson(client).getJSONObject(0);
			String url = PERSON_URL + "/" + patientObject.getString("uuid") + "/address/" + addressObject.getString("uuid");
			return new JSONObject(
					HttpUtil.post(getURL() + "/" + url, "", updateAddress.toString(), OPENMRS_USER, OPENMRS_PWD).body());
		}
		catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateIdentifiers(String personUUID, Client c) throws JSONException {
		JSONObject p = getPatientByUuid(personUUID, false);
		if (p.has("identifiers") && p.get("identifiers") instanceof JSONArray) {
			JSONArray identifiers = p.getJSONArray("identifiers");
			Map<String, String> idsMap = c.getIdentifiers();

			for (int j = 0; j < identifiers.length(); j++) {
				JSONObject idObject = identifiers.getJSONObject(j);
				String identifierType = idObject.has("display") ? idObject.getString("display") : null;
				String identifierUuid = idObject.has("uuid") ? idObject.getString("uuid") : null;

				if (identifierType == null || identifierUuid == null) {
					continue;
				}
				if (identifierType.contains("=")) {
					String[] fn = identifierType.split("\\=");
					identifierType = fn[0].trim();
					String identifierValue = fn[1].trim();
					if (idsMap.containsKey(identifierType) && !identifierValue.equals(idsMap.get(identifierType))) {
						updatePatientIdentifier(personUUID, identifierUuid, idsMap.get(identifierType));
					}
				}
			}
		}
	}

	private String convertToOpenmrsString(String s) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(s)) {
			return s;
		}

		s = s.replaceAll("\t", "");
		s = org.apache.commons.lang3.StringUtils.stripAccents(s);
		return s;

	}

	private Object convertToOpenmrsString(Object o) {
		if (o != null && o instanceof String) {
			return convertToOpenmrsString(o.toString());
		}
		return o;

	}

	private String fetchLocationByUUID(String locationUUID) {
		try {
			if (locationUUID == null || StringUtils.isEmptyOrWhitespaceOnly(locationUUID) || locationUUID
					.equalsIgnoreCase("Other")) {
				return locationUUID;
			}
			Location location = openmrsLocationService.getLocation(locationUUID);
			if (location == null) {
				return "Unknown Location Id: " + locationUUID;
			} else {
				return location.getName();
			}
		}
		catch (Exception e) {
			logger.error("", e);
			return "Unknown Location Id: " + locationUUID;
		}
	}


}
