package org.opensrp.connector.openmrs.service;

import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;

import com.mysql.jdbc.StringUtils;
import org.opensrp.common.util.HttpResponse;
import org.opensrp.common.util.HttpUtil;
import org.springframework.stereotype.Service;

/**
 * Created by amosl on 5/25/17.
 */

@Service
public class OpenmrsRelationshipService extends OpenmrsService {

    private static final String RELATIONSHIP_URL = "ws/rest/v1/relationship";
    private static final String RELATIONSHIP_TYPE_URL = "ws/rest/v1/relationshiptype";
    private static final String DEFAULT_RELATIONSHIP_TYPE = "Parent/Child";

    public OpenmrsRelationshipService() {
    }

    public OpenmrsRelationshipService(String openmrsUrl, String user, String password) {
        super(openmrsUrl, user, password);
    }

    public JSONObject getRelationshipTypes() throws JSONException{
        HttpResponse op = HttpUtil.get(HttpUtil.removeEndingSlash(OPENMRS_BASE_URL) + "/" + RELATIONSHIP_TYPE_URL,
                "", OPENMRS_USER, OPENMRS_PWD);

        if (!StringUtils.isEmptyOrWhitespaceOnly(op.body())) {
            JSONObject response = new JSONObject(op.body());
            JSONArray responseArray = response.getJSONArray("results");
            JSONArray relationshipTypes = new JSONArray();

            for (int i = 0; i < responseArray.length(); i++) {
                String key = responseArray.getJSONObject(i).getString("uuid");
                String name = responseArray.getJSONObject(i).getString("display");
                JSONObject r = new JSONObject();
                r.put("key", key);
                r.put("name", name);
                relationshipTypes.put(r.toString());
            }

            return new JSONObject().put("relationshipTypes", relationshipTypes);
        }
        return null;
    }

    public JSONObject createRelationship(String clientUuid, String isARelationship, String relativeUuid) throws JSONException{
        JSONObject o = convertRelationshipToOpenmrsJson(clientUuid, isARelationship, relativeUuid);
        return new JSONObject(HttpUtil.post(getURL()+"/"+RELATIONSHIP_URL, "", o.toString(), OPENMRS_USER, OPENMRS_PWD).body());
    }

    public JSONObject convertRelationshipToOpenmrsJson(String personA, String isARelationship, String personB) throws JSONException {
        JSONObject a = new JSONObject();
        a.put("personA", personA);
        a.put("relationshipType", isARelationship);
        a.put("personB", personB);
        return a;
    }

    public String getDefaultRelationshipTypeId(){
        String defaultRelationshipTypeId = "";
        try {
            JSONArray relationshipTypes = getRelationshipTypes().getJSONArray("relationshipTypes");
            for(int i=0; i<relationshipTypes.length(); i++){
                JSONObject object = relationshipTypes.getJSONObject(i);
                if(object.has("name") && object.getString("name").equals(DEFAULT_RELATIONSHIP_TYPE)){
                    defaultRelationshipTypeId = object.getString("key");
                }
            }

            if(defaultRelationshipTypeId.equals("")){
                defaultRelationshipTypeId = relationshipTypes.getJSONObject(0).getString("key");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return defaultRelationshipTypeId;
    }
}