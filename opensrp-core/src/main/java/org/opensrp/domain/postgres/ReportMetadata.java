package org.opensrp.domain.postgres;

import java.util.Date;

public class ReportMetadata {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.report_metadata.id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.report_metadata.report_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	private Long reportId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.report_metadata.document_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	private String documentId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.report_metadata.form_submission_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	private String formSubmissionId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.report_metadata.base_entity_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	private String baseEntityId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.report_metadata.server_version
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	private Long serverVersion;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.report_metadata.report_type
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	private String reportType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.report_metadata.report_date
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	private Date reportDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.report_metadata.provider_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	private String providerId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.report_metadata.location_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	private String locationId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.report_metadata.team
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	private String team;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.report_metadata.team_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	private String teamId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.report_metadata.date_edited
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	private Date dateEdited;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.report_metadata.id
	 * @return  the value of core.report_metadata.id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.report_metadata.id
	 * @param id  the value for core.report_metadata.id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.report_metadata.report_id
	 * @return  the value of core.report_metadata.report_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public Long getReportId() {
		return reportId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.report_metadata.report_id
	 * @param reportId  the value for core.report_metadata.report_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.report_metadata.document_id
	 * @return  the value of core.report_metadata.document_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public String getDocumentId() {
		return documentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.report_metadata.document_id
	 * @param documentId  the value for core.report_metadata.document_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.report_metadata.form_submission_id
	 * @return  the value of core.report_metadata.form_submission_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public String getFormSubmissionId() {
		return formSubmissionId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.report_metadata.form_submission_id
	 * @param formSubmissionId  the value for core.report_metadata.form_submission_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public void setFormSubmissionId(String formSubmissionId) {
		this.formSubmissionId = formSubmissionId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.report_metadata.base_entity_id
	 * @return  the value of core.report_metadata.base_entity_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public String getBaseEntityId() {
		return baseEntityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.report_metadata.base_entity_id
	 * @param baseEntityId  the value for core.report_metadata.base_entity_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public void setBaseEntityId(String baseEntityId) {
		this.baseEntityId = baseEntityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.report_metadata.server_version
	 * @return  the value of core.report_metadata.server_version
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public Long getServerVersion() {
		return serverVersion;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.report_metadata.server_version
	 * @param serverVersion  the value for core.report_metadata.server_version
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public void setServerVersion(Long serverVersion) {
		this.serverVersion = serverVersion;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.report_metadata.report_type
	 * @return  the value of core.report_metadata.report_type
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.report_metadata.report_type
	 * @param reportType  the value for core.report_metadata.report_type
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.report_metadata.report_date
	 * @return  the value of core.report_metadata.report_date
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public Date getReportDate() {
		return reportDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.report_metadata.report_date
	 * @param reportDate  the value for core.report_metadata.report_date
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.report_metadata.provider_id
	 * @return  the value of core.report_metadata.provider_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public String getProviderId() {
		return providerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.report_metadata.provider_id
	 * @param providerId  the value for core.report_metadata.provider_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.report_metadata.location_id
	 * @return  the value of core.report_metadata.location_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.report_metadata.location_id
	 * @param locationId  the value for core.report_metadata.location_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.report_metadata.team
	 * @return  the value of core.report_metadata.team
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.report_metadata.team
	 * @param team  the value for core.report_metadata.team
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public void setTeam(String team) {
		this.team = team;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.report_metadata.team_id
	 * @return  the value of core.report_metadata.team_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public String getTeamId() {
		return teamId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.report_metadata.team_id
	 * @param teamId  the value for core.report_metadata.team_id
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.report_metadata.date_edited
	 * @return  the value of core.report_metadata.date_edited
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public Date getDateEdited() {
		return dateEdited;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.report_metadata.date_edited
	 * @param dateEdited  the value for core.report_metadata.date_edited
	 * @mbg.generated  Fri Mar 23 14:23:39 EAT 2018
	 */
	public void setDateEdited(Date dateEdited) {
		this.dateEdited = dateEdited;
	}
}