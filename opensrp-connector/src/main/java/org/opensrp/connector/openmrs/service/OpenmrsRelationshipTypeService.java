package org.opensrp.connector.openmrs.service;

import org.json.JSONObject;
import org.json.JSONException;

import com.mysql.jdbc.StringUtils;
import org.opensrp.common.util.HttpResponse;
import org.opensrp.common.util.HttpUtil;
import org.springframework.stereotype.Service;

/**
 * Created by amosl on 5/25/17.
 */

@Service
public class OpenmrsRelationshipTypeService extends OpenmrsService {
    private static final String RELATIONSHIP_TYPE_URL = "ws/rest/v1/relationshiptype";

    public OpenmrsRelationshipTypeService() {
    }

    public OpenmrsRelationshipTypeService(String openmrsUrl, String user, String password) {
        super(openmrsUrl, user, password);
    }

    public JSONObject getRelationshipTypes() throws JSONException{
        HttpResponse op = HttpUtil.get(HttpUtil.removeEndingSlash(OPENMRS_BASE_URL) + "/" + RELATIONSHIP_TYPE_URL,
                "", OPENMRS_USER, OPENMRS_PWD);

        if (!StringUtils.isEmptyOrWhitespaceOnly(op.body())) {
            return new JSONObject(op.body());
        }
        return null;
    }
}
