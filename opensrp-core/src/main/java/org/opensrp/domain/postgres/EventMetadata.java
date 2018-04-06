package org.opensrp.domain.postgres;

import java.util.Date;

public class EventMetadata {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.event_metadata.id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.event_metadata.event_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	private Long eventId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.event_metadata.document_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	private String documentId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.event_metadata.base_entity_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	private String baseEntityId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.event_metadata.form_submission_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	private String formSubmissionId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.event_metadata.server_version
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	private Long serverVersion;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.event_metadata.openmrs_uuid
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	private String openmrsUuid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.event_metadata.event_type
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	private String eventType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.event_metadata.event_date
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	private Date eventDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.event_metadata.entity_type
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	private String entityType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.event_metadata.provider_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	private String providerId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.event_metadata.location_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	private String locationId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.event_metadata.team
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	private String team;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.event_metadata.team_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	private String teamId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.event_metadata.date_created
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	private Date dateCreated;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.event_metadata.date_edited
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	private Date dateEdited;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column core.event_metadata.date_deleted
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	private Date dateDeleted;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.event_metadata.id
	 * @return  the value of core.event_metadata.id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.event_metadata.id
	 * @param id  the value for core.event_metadata.id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.event_metadata.event_id
	 * @return  the value of core.event_metadata.event_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public Long getEventId() {
		return eventId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.event_metadata.event_id
	 * @param eventId  the value for core.event_metadata.event_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.event_metadata.document_id
	 * @return  the value of core.event_metadata.document_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public String getDocumentId() {
		return documentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.event_metadata.document_id
	 * @param documentId  the value for core.event_metadata.document_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.event_metadata.base_entity_id
	 * @return  the value of core.event_metadata.base_entity_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public String getBaseEntityId() {
		return baseEntityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.event_metadata.base_entity_id
	 * @param baseEntityId  the value for core.event_metadata.base_entity_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public void setBaseEntityId(String baseEntityId) {
		this.baseEntityId = baseEntityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.event_metadata.form_submission_id
	 * @return  the value of core.event_metadata.form_submission_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public String getFormSubmissionId() {
		return formSubmissionId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.event_metadata.form_submission_id
	 * @param formSubmissionId  the value for core.event_metadata.form_submission_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public void setFormSubmissionId(String formSubmissionId) {
		this.formSubmissionId = formSubmissionId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.event_metadata.server_version
	 * @return  the value of core.event_metadata.server_version
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public Long getServerVersion() {
		return serverVersion;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.event_metadata.server_version
	 * @param serverVersion  the value for core.event_metadata.server_version
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public void setServerVersion(Long serverVersion) {
		this.serverVersion = serverVersion;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.event_metadata.openmrs_uuid
	 * @return  the value of core.event_metadata.openmrs_uuid
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public String getOpenmrsUuid() {
		return openmrsUuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.event_metadata.openmrs_uuid
	 * @param openmrsUuid  the value for core.event_metadata.openmrs_uuid
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public void setOpenmrsUuid(String openmrsUuid) {
		this.openmrsUuid = openmrsUuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.event_metadata.event_type
	 * @return  the value of core.event_metadata.event_type
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.event_metadata.event_type
	 * @param eventType  the value for core.event_metadata.event_type
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.event_metadata.event_date
	 * @return  the value of core.event_metadata.event_date
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.event_metadata.event_date
	 * @param eventDate  the value for core.event_metadata.event_date
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.event_metadata.entity_type
	 * @return  the value of core.event_metadata.entity_type
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public String getEntityType() {
		return entityType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.event_metadata.entity_type
	 * @param entityType  the value for core.event_metadata.entity_type
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.event_metadata.provider_id
	 * @return  the value of core.event_metadata.provider_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public String getProviderId() {
		return providerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.event_metadata.provider_id
	 * @param providerId  the value for core.event_metadata.provider_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.event_metadata.location_id
	 * @return  the value of core.event_metadata.location_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.event_metadata.location_id
	 * @param locationId  the value for core.event_metadata.location_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.event_metadata.team
	 * @return  the value of core.event_metadata.team
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.event_metadata.team
	 * @param team  the value for core.event_metadata.team
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public void setTeam(String team) {
		this.team = team;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.event_metadata.team_id
	 * @return  the value of core.event_metadata.team_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public String getTeamId() {
		return teamId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.event_metadata.team_id
	 * @param teamId  the value for core.event_metadata.team_id
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.event_metadata.date_created
	 * @return  the value of core.event_metadata.date_created
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.event_metadata.date_created
	 * @param dateCreated  the value for core.event_metadata.date_created
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.event_metadata.date_edited
	 * @return  the value of core.event_metadata.date_edited
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public Date getDateEdited() {
		return dateEdited;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.event_metadata.date_edited
	 * @param dateEdited  the value for core.event_metadata.date_edited
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public void setDateEdited(Date dateEdited) {
		this.dateEdited = dateEdited;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column core.event_metadata.date_deleted
	 * @return  the value of core.event_metadata.date_deleted
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public Date getDateDeleted() {
		return dateDeleted;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column core.event_metadata.date_deleted
	 * @param dateDeleted  the value for core.event_metadata.date_deleted
	 * @mbg.generated  Fri Apr 06 12:20:41 EAT 2018
	 */
	public void setDateDeleted(Date dateDeleted) {
		this.dateDeleted = dateDeleted;
	}
}