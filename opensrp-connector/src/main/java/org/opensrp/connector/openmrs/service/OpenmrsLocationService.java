package org.opensrp.connector.openmrs.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opensrp.api.domain.Location;
import org.opensrp.api.util.LocationTree;
import org.opensrp.api.util.TreeNode;
import org.opensrp.common.util.HttpResponse;
import org.opensrp.common.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


@Service
public class OpenmrsLocationService extends OpenmrsService {
	
	private static Logger logger = LoggerFactory.getLogger(OpenmrsLocationService.class);
	
	private static final String LOCATION_URL = "ws/rest/v1/location";

	private static final String COUNTY = "County";
	private static final String SUB_COUNTY = "Sub County";
	private static final String WARD = "Ward";
	
	public OpenmrsLocationService() {
	}
	
	public OpenmrsLocationService(String openmrsUrl, String user, String password) {
		super(openmrsUrl, user, password);
	}
	
	private String getURL(String url) {
		Request request = new Request.Builder().url(url)
		        .addHeader("Authorization", Credentials.basic(OPENMRS_USER, OPENMRS_PWD)).build();
		OkHttpClient client = new OkHttpClient();
		Call call = client.newCall(request);
		Response response;
		try {
			response = call.execute();
			String responseBody=response.body().string();
			if (!StringUtils.isEmptyOrWhitespaceOnly(responseBody)) {
				return responseBody;
			}
		}
		catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
		
	}
	
	public Location getLocation(String locationIdOrName) throws JSONException {
		String response = getURL(HttpUtil.removeEndingSlash(OPENMRS_BASE_URL) + "/" + LOCATION_URL + "/"
		        + (locationIdOrName.replaceAll(" ", "%20")) + "?v=full");
		if (!StringUtils.isEmptyOrWhitespaceOnly(response) && (new JSONObject(response).has("uuid"))) {
			return makeLocation(response);
		}
		
		return null;
	}
	
	public Location getParent(JSONObject locobj) throws JSONException {
		JSONObject parentL = (locobj.has("parentLocation") && !locobj.isNull("parentLocation"))
		        ? locobj.getJSONObject("parentLocation")
		        : null;
		
		if (parentL != null) {
			return new Location(parentL.getString("uuid"), parentL.getString("display"), null, getParent(parentL));
		}
		return null;
	}
	
	private Location makeLocation(String locationJson) throws JSONException {
		logger.info("makeLocation: " + locationJson);
		JSONObject obj = new JSONObject(locationJson);
		Location p = getParent(obj);
		Location l = new Location(obj.getString("uuid"), obj.getString("name"), null, null, p, null, null);
		JSONArray t = obj.getJSONArray("tags");
		
		for (int i = 0; i < t.length(); i++) {
			l.addTag(t.getJSONObject(i).getString("display"));
		}
		
		JSONArray a = obj.getJSONArray("attributes");
		
		for (int i = 0; i < a.length(); i++) {
			boolean voided = a.getJSONObject(i).optBoolean("voided");
			if (!voided) {
				String ad = a.getJSONObject(i).getString("display");
				l.addAttribute(ad.substring(0, ad.indexOf(":")), ad.substring(ad.indexOf(":") + 2));
			}
		}
		
		logger.info("location: " + ReflectionToStringBuilder.toString(l));
		return l;
	}
	
	private Location makeLocation(JSONObject location) throws JSONException {
		return makeLocation(location.toString());
	}
	
	public LocationTree getLocationTree() throws JSONException {
		LocationTree ltr = new LocationTree();
		String response = getURL(HttpUtil.removeEndingSlash(OPENMRS_BASE_URL) + "/" + LOCATION_URL+ "?v=full");
		
		JSONArray res = new JSONObject(response).getJSONArray("results");
		if (res.length() == 0) {
			return ltr;
		}
		
		for (int i = 0; i < res.length(); i++) {
			ltr.addLocation(makeLocation(res.getJSONObject(i)));
		}
		return ltr;
	}
	
	public LocationTree getLocationTreeOf(String locationIdOrName) throws JSONException {
		LocationTree ltr = new LocationTree();
		
		fillTreeWithHierarchy(ltr, locationIdOrName);
		fillTreeWithUpperHierarchy(ltr, locationIdOrName);
		
		return ltr;
	}
	
	public LocationTree getLocationTreeWithUpperHierachyOf(String locationIdOrName) throws JSONException {
		LocationTree ltr = new LocationTree();
		fillTreeWithUpperHierarchy(ltr, locationIdOrName);
		return ltr;
	}
	
	public LocationTree getLocationTreeOf(String[] locationIdsOrNames) throws JSONException {
		LocationTree ltr = new LocationTree();
		
		for (String loc : locationIdsOrNames) {
			String locTreeId = fillTreeWithHierarchy(ltr, loc);
			Location lp = ltr.findLocation(locTreeId).getParentLocation();
			LoggerFactory.getLogger(this.getClass())
			        .info("getLocationTreeOf node: " + ReflectionToStringBuilder.toString(lp));
			if (lp != null) {
				fillTreeWithUpperHierarchy(ltr, lp.getLocationId());
			}
		}
		
		LoggerFactory.getLogger(this.getClass()).info("getLocationTreeOf tree: " + ReflectionToStringBuilder.toString(ltr));
		return ltr;
	}
	
	private String fillTreeWithHierarchy(LocationTree ltr, String locationIdOrName) throws JSONException {
		
		
		String response = getURL(HttpUtil.removeEndingSlash(OPENMRS_BASE_URL) + "/" + LOCATION_URL + "/"
		        + (locationIdOrName.replaceAll(" ", "%20"))+"?v=full");
		
		JSONObject lo = new JSONObject(response);
		Location l = makeLocation(response);
		ltr.addLocation(l);
		
		if (lo.has("childLocations")) {
			JSONArray lch = lo.getJSONArray("childLocations");
			
			for (int i = 0; i < lch.length(); i++) {
				
				JSONObject cj = lch.getJSONObject(i);
				fillTreeWithHierarchy(ltr, cj.getString("uuid"));
			}
		}
		return l.getLocationId();
	}
	
	private void fillTreeWithUpperHierarchy(LocationTree ltr, String locationId) throws JSONException {
		
		String response = getURL( HttpUtil.removeEndingSlash(OPENMRS_BASE_URL) + "/" + LOCATION_URL + "/" + (locationId.replaceAll(" ", "%20"))+
		    "?v=full");
	
		Location l = makeLocation(response);
		ltr.addLocation(l);
		
		if (l.getParentLocation() != null) {
			fillTreeWithUpperHierarchy(ltr, l.getParentLocation().getLocationId());
		}
	}


	public JSONObject getLocationTree(String[] locationIdsOrNames) throws JSONException {
		JSONArray locations = new JSONArray();

		for (String loc : locationIdsOrNames) {
			HttpResponse op = HttpUtil.get(HttpUtil.removeEndingSlash(this.OPENMRS_BASE_URL)+"/"+LOCATION_URL+"/"+(loc.replaceAll(" ", "%20")), "v=full", this.OPENMRS_USER, this.OPENMRS_PWD);
			JSONObject lo = new JSONObject(op.body());

			fillTreeWithLowerHierarchy(locations, lo);
		}

		return new JSONObject().put("userLocations", locations);
	}

	private String fillTreeWithLowerHierarchy(JSONArray locations, JSONObject lo) throws JSONException{

		Location l = null;
		if(lo.has("tags")){
			for (int n = 0; n < lo.getJSONArray("tags").length(); n++) {
				String tag = lo.getJSONArray("tags").getJSONObject(n).getString("display");
				if(tag.equals(COUNTY) || tag.equals(SUB_COUNTY) || tag.equals(WARD))
					l = makeLocation(lo);
			}
		}

		if(l != null)
			locations.put(new Gson().toJson(l));

		if(l != null && lo.has("childLocations")){
			JSONArray lch = lo.getJSONArray("childLocations");

			for (int i = 0; i < lch.length(); i++) {

				JSONObject cj = lch.getJSONObject(i);
				boolean proceed = false;

				if(cj.has("tags")){
					for (int n = 0; n < cj.getJSONArray("tags").length(); n++) {
						String tag = cj.getJSONArray("tags").getJSONObject(n).getString("display");
						if(tag.equals(COUNTY) || tag.equals(SUB_COUNTY) || tag.equals(WARD))
							proceed = true;
					}
				} else {
					if(l.getTags().contains(COUNTY) || l.getTags().contains(SUB_COUNTY)){
						proceed = true;
					}
				}

				if(proceed) {
					if (cj.has("name")) {
						fillTreeWithLowerHierarchy(locations, cj);
					} else {
						String uuid = cj.has("uuid") ? cj.getString("uuid") : "";
						if (org.apache.commons.lang3.StringUtils.isNotBlank(uuid)) {
							HttpResponse op = HttpUtil.get(HttpUtil.removeEndingSlash(this.OPENMRS_BASE_URL) + "/" + LOCATION_URL + "/" + (uuid.replaceAll(" ", "%20")), "v=full", this.OPENMRS_USER, this.OPENMRS_PWD);

							fillTreeWithLowerHierarchy(locations, new JSONObject(op.body()));
						}
					}
				}
			}
		}
		return l == null ? null : l.getLocationId();
	}
	
	public Map<String, String> getLocationsHierarchy(LocationTree locationTree) throws JSONException {
		Map<String, String> map = new HashMap<>();
		if (locationTree == null) {
			return map;
		}
		
		Map<String, TreeNode<String, Location>> locationsHierarchy = locationTree.getLocationsHierarchy();
		if (locationsHierarchy != null && !locationsHierarchy.isEmpty()) {
			for (TreeNode<String, Location> value : locationsHierarchy.values()) {
				extractLocations(map, value);
			}
		}
		return map;
	}
	
	private void extractLocations(Map<String, String> map, TreeNode<String, Location> value) throws JSONException {
		if (value == null || value.getNode() == null) {
			return;
		}
		
		final String[] allowedLevels = { AllowedLevels.COUNTRY.toString(), AllowedLevels.DISTRICT.toString(),
		        AllowedLevels.COUNTY.toString(), AllowedLevels.SUB_COUNTY.toString(),
		        AllowedLevels.HEALTH_FACILITY.toString() };
		
		Location node = value.getNode();
		String name = node.getName();
		Set<String> tags = node.getTags();
		{
			if (tags != null && !tags.isEmpty()) {
				for (String level : tags) {
					if (ArrayUtils.contains(allowedLevels, level)) {
						map.put(level, name);
					}
				}
			}
			
		}
		Map<String, TreeNode<String, Location>> children = value.getChildren();
		if (children != null && !children.isEmpty()) {
			for (TreeNode<String, Location> childValue : children.values()) {
				extractLocations(map, childValue);
			}
		}
	}
	
	public enum AllowedLevels {
		COUNTRY("Country"),
		PROVINCE("Province"),
		DISTRICT("District"),
		COUNTY("County"),
		SUB_COUNTY("Sub-county"),
		HEALTH_FACILITY("Health Facility");
		
		private final String display;
		
		private AllowedLevels(final String display) {
			this.display = display;
		}
		
		@Override
		public String toString() {
			return display;
		}
		
	};
}
