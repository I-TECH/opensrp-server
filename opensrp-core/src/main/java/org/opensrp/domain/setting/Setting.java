package org.opensrp.domain.setting;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.json.JSONArray;

@JsonSubTypes({ @Type(value = SettingConfiguration.class, name = "Setting") })
public class Setting {
	
	@JsonProperty
	private String key;
	
	@JsonProperty
	private String value;

	@JsonProperty
	private JSONArray values;
	
	@JsonProperty
	private String label;
	
	@JsonProperty
	private String description;
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getDescription() {
		return description;
	}

	public JSONArray getValues() {
		return values;
	}

	public void setValues(JSONArray values) {
		this.values = values;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
