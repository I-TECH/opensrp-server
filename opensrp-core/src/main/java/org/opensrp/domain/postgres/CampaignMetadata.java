package org.opensrp.domain.postgres;

public class CampaignMetadata {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.campaign_metadata.id
	 * @mbg.generated  Fri Nov 09 12:47:47 EAT 2018
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.campaign_metadata.campaign_id
	 * @mbg.generated  Fri Nov 09 12:47:47 EAT 2018
	 */
	private Long campaignId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.campaign_metadata.identifier
	 * @mbg.generated  Fri Nov 09 12:47:47 EAT 2018
	 */
	private String identifier;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.campaign_metadata.server_version
	 * @mbg.generated  Fri Nov 09 12:47:47 EAT 2018
	 */
	private Long serverVersion;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.campaign_metadata.id
	 * @return  the value of core.campaign_metadata.id
	 * @mbg.generated  Fri Nov 09 12:47:47 EAT 2018
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.campaign_metadata.id
	 * @param id  the value for core.campaign_metadata.id
	 * @mbg.generated  Fri Nov 09 12:47:47 EAT 2018
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.campaign_metadata.campaign_id
	 * @return  the value of core.campaign_metadata.campaign_id
	 * @mbg.generated  Fri Nov 09 12:47:47 EAT 2018
	 */
	public Long getCampaignId() {
		return campaignId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.campaign_metadata.campaign_id
	 * @param campaignId  the value for core.campaign_metadata.campaign_id
	 * @mbg.generated  Fri Nov 09 12:47:47 EAT 2018
	 */
	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.campaign_metadata.identifier
	 * @return  the value of core.campaign_metadata.identifier
	 * @mbg.generated  Fri Nov 09 12:47:47 EAT 2018
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.campaign_metadata.identifier
	 * @param identifier  the value for core.campaign_metadata.identifier
	 * @mbg.generated  Fri Nov 09 12:47:47 EAT 2018
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.campaign_metadata.server_version
	 * @return  the value of core.campaign_metadata.server_version
	 * @mbg.generated  Fri Nov 09 12:47:47 EAT 2018
	 */
	public Long getServerVersion() {
		return serverVersion;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.campaign_metadata.server_version
	 * @param serverVersion  the value for core.campaign_metadata.server_version
	 * @mbg.generated  Fri Nov 09 12:47:47 EAT 2018
	 */
	public void setServerVersion(Long serverVersion) {
		this.serverVersion = serverVersion;
	}
}