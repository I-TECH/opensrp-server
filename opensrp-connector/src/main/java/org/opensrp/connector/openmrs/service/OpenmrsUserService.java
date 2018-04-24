package org.opensrp.connector.openmrs.service;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opensrp.api.domain.User;
import org.opensrp.common.util.HttpResponse;
import org.opensrp.common.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OpenmrsUserService extends OpenmrsService {
	
	private static final String AUTHENTICATION_URL = "ws/rest/v1/session";
	
	private static final String USER_URL = "ws/rest/v1/user";
	
	private static final String PROVIDER_URL = "ws/rest/v1/provider";
	
	private static final String TEAM_MEMBER_URL = "ws/rest/v1/teammodule/member";
	
	private static Logger logger = LoggerFactory.getLogger(OpenmrsUserService.class.toString());
	
	public OpenmrsUserService() {
	}
	
	public OpenmrsUserService(String openmrsUrl, String user, String password) {
		super(openmrsUrl, user, password);
	}
	
	public Boolean authenticate(String username, String password) throws JSONException {
		HttpResponse op = HttpUtil.get(HttpUtil.removeEndingSlash(OPENMRS_BASE_URL) + "/" + AUTHENTICATION_URL, "", username,
		    password);
		if (!op.isSuccess() || StringUtils.isBlank(op.body())) {
			return null;
		}
		
		final String AUTHENTICATED = "authenticated";
		JSONObject jo = new JSONObject(op.body());
		if (jo.has(AUTHENTICATED)) {
			return jo.getBoolean(AUTHENTICATED);
		}
		return null;
	}
	
	public boolean deleteSession(String username, String password) throws JSONException {
		HttpResponse op = HttpUtil.delete(HttpUtil.removeEndingSlash(OPENMRS_BASE_URL) + "/" + AUTHENTICATION_URL, "",
		    username, password);
		return op.isSuccess();
	}
	
	public boolean deleteAdminSession() throws JSONException {
		HttpResponse op = HttpUtil.delete(HttpUtil.removeEndingSlash(OPENMRS_BASE_URL) + "/" + AUTHENTICATION_URL, "",
		    OPENMRS_USER, OPENMRS_PWD);
		return op.isSuccess();
	}
	
	/**
	 * Get openmrs user based using the openmrs credentials in opensrp.properties
	 * 
	 * @param username
	 * @return
	 * @throws JSONException
	 */
	public User getUser(String username) throws JSONException {
		HttpResponse op = HttpUtil.get(HttpUtil.removeEndingSlash(OPENMRS_BASE_URL) + "/" + USER_URL,
		    "v=full&username=" + username, OPENMRS_USER, OPENMRS_PWD);
		JSONObject res = new JSONObject(op.body());
		JSONArray jsonArray = res.has("results") ? res.getJSONArray("results") : null;
		deleteAdminSession();
		if (jsonArray == null || jsonArray.length() == 0) {
			return null;
		}
		JSONObject obj = jsonArray.getJSONObject(0);
		JSONObject p = obj.getJSONObject("person");
		String preferredName = p.getJSONObject("preferredName").getString("display");
		logger.info(jsonArray.toString());
		User u = new User(obj.getString("uuid"), obj.getString("username"), null, preferredName, null,
		        p.getString("display"), null, null);
		//Object ploc;
		JSONArray a = p.getJSONArray("attributes");
		
		for (int i = 0; i < a.length(); i++) {
			String ad = a.getJSONObject(i).getString("display");
			u.addAttribute(ad.substring(0, ad.indexOf("=") - 1), ad.substring(ad.indexOf("=") + 2));
		}
		
		JSONArray per = obj.getJSONArray("privileges");
		
		for (int i = 0; i < per.length(); i++) {
			u.addPermission(per.getJSONObject(i).getString("name"));
		}
		
		JSONArray rol = obj.getJSONArray("roles");
		
		for (int i = 0; i < rol.length(); i++) {
			u.addRole(rol.getJSONObject(i).getString("name"));
		}
		
		u.addAttribute("_PERSON_UUID", p.getString("uuid"));
		return u;
	}
	
	public String getPersonUUIDByUser(String username) throws JSONException {
		HttpResponse op = HttpUtil.get(HttpUtil.removeEndingSlash(OPENMRS_BASE_URL) + "/" + USER_URL,
		    PatientService.CUSTOM_PERSON_UUID_PARAM + "&username=" + username, OPENMRS_USER, OPENMRS_PWD);
		JSONObject res = new JSONObject(op.body());
		if (res.has(PatientService.RESULTS_KEY) && res.get(PatientService.RESULTS_KEY) instanceof JSONArray) {
			JSONArray p = res.getJSONArray(PatientService.RESULTS_KEY);
			if (p.length() > 0) {
				JSONObject resJson = p.getJSONObject(0);
				if (resJson.has(PatientService.PERSON_KEY)) {
					JSONObject personJoson = resJson.getJSONObject(PatientService.PERSON_KEY);
					if (personJoson.has(PatientService.UUID_KEY)) {
						return personJoson.getString(PatientService.UUID_KEY);
					}
				}
			}
		}
		
		return null;
	}
	
	public JSONObject getTeamMember(String uuid) throws JSONException {
		HttpResponse op = HttpUtil.get(HttpUtil.removeEndingSlash(OPENMRS_BASE_URL) + "/" + TEAM_MEMBER_URL + "/" + uuid,
		    "v=full", OPENMRS_USER, OPENMRS_PWD);
		return new JSONObject(op.body());
	}
	
	public JSONObject getProvider(String identifier) throws JSONException {
		HttpResponse op = HttpUtil.get(HttpUtil.removeEndingSlash(OPENMRS_BASE_URL) + "/" + PROVIDER_URL,
		    "v=full&q=" + identifier, OPENMRS_USER, OPENMRS_PWD);
		JSONArray res = new JSONObject(op.body()).getJSONArray("results");
		if (res.length() == 0) {
			return null;
		}
		JSONObject obj = res.getJSONObject(0);
		return obj;
	}
	
	public JSONObject createProvider(String existingUsername, String identifier) throws JSONException {
		JSONObject p = new JSONObject();
		p.put("person", getPersonUUIDByUser(existingUsername));
		p.put("identifier", identifier);
		return new JSONObject(
		        HttpUtil.post(getURL() + "/" + PROVIDER_URL, "", p.toString(), OPENMRS_USER, OPENMRS_PWD).body());
	}
}
