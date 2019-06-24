package org.opensrp.domain.postgres;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ClientMetadataExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table core.client_metadata
	 * @mbg.generated  Fri Apr 06 15:32:26 EAT 2018
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table core.client_metadata
	 * @mbg.generated  Fri Apr 06 15:32:26 EAT 2018
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table core.client_metadata
	 * @mbg.generated  Fri Apr 06 15:32:26 EAT 2018
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client_metadata
	 * @mbg.generated  Fri Apr 06 15:32:26 EAT 2018
	 */
	public ClientMetadataExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client_metadata
	 * @mbg.generated  Fri Apr 06 15:32:26 EAT 2018
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client_metadata
	 * @mbg.generated  Fri Apr 06 15:32:26 EAT 2018
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client_metadata
	 * @mbg.generated  Fri Apr 06 15:32:26 EAT 2018
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client_metadata
	 * @mbg.generated  Fri Apr 06 15:32:26 EAT 2018
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client_metadata
	 * @mbg.generated  Fri Apr 06 15:32:26 EAT 2018
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client_metadata
	 * @mbg.generated  Fri Apr 06 15:32:26 EAT 2018
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client_metadata
	 * @mbg.generated  Fri Apr 06 15:32:26 EAT 2018
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client_metadata
	 * @mbg.generated  Fri Apr 06 15:32:26 EAT 2018
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client_metadata
	 * @mbg.generated  Fri Apr 06 15:32:26 EAT 2018
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table core.client_metadata
	 * @mbg.generated  Fri Apr 06 15:32:26 EAT 2018
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table core.client_metadata
	 * @mbg.generated  Fri Apr 06 15:32:26 EAT 2018
	 */
	protected abstract static class GeneratedCriteria {
		
		protected List<Criterion> criteria;
		
		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}
		
		public boolean isValid() {
			return criteria.size() > 0;
		}
		
		public List<Criterion> getAllCriteria() {
			return criteria;
		}
		
		public List<Criterion> getCriteria() {
			return criteria;
		}
		
		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}
		
		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}
		
		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}
		
		protected void addCriterionForJDBCDate(String condition, Date value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value.getTime()), property);
		}
		
		protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property + " cannot be null or empty");
			}
			List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
			Iterator<Date> iter = values.iterator();
			while (iter.hasNext()) {
				dateList.add(new java.sql.Date(iter.next().getTime()));
			}
			addCriterion(condition, dateList, property);
		}
		
		protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
		}
		
		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}
		
		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}
		
		public Criteria andIdEqualTo(Long value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdNotEqualTo(Long value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdGreaterThan(Long value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdGreaterThanOrEqualTo(Long value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdLessThan(Long value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdLessThanOrEqualTo(Long value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdIn(List<Long> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdNotIn(List<Long> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdBetween(Long value1, Long value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}
		
		public Criteria andIdNotBetween(Long value1, Long value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}
		
		public Criteria andClientIdIsNull() {
			addCriterion("client_id is null");
			return (Criteria) this;
		}
		
		public Criteria andClientIdIsNotNull() {
			addCriterion("client_id is not null");
			return (Criteria) this;
		}
		
		public Criteria andClientIdEqualTo(Long value) {
			addCriterion("client_id =", value, "clientId");
			return (Criteria) this;
		}
		
		public Criteria andClientIdNotEqualTo(Long value) {
			addCriterion("client_id <>", value, "clientId");
			return (Criteria) this;
		}
		
		public Criteria andClientIdGreaterThan(Long value) {
			addCriterion("client_id >", value, "clientId");
			return (Criteria) this;
		}
		
		public Criteria andClientIdGreaterThanOrEqualTo(Long value) {
			addCriterion("client_id >=", value, "clientId");
			return (Criteria) this;
		}
		
		public Criteria andClientIdLessThan(Long value) {
			addCriterion("client_id <", value, "clientId");
			return (Criteria) this;
		}
		
		public Criteria andClientIdLessThanOrEqualTo(Long value) {
			addCriterion("client_id <=", value, "clientId");
			return (Criteria) this;
		}
		
		public Criteria andClientIdIn(List<Long> values) {
			addCriterion("client_id in", values, "clientId");
			return (Criteria) this;
		}
		
		public Criteria andClientIdNotIn(List<Long> values) {
			addCriterion("client_id not in", values, "clientId");
			return (Criteria) this;
		}
		
		public Criteria andClientIdBetween(Long value1, Long value2) {
			addCriterion("client_id between", value1, value2, "clientId");
			return (Criteria) this;
		}
		
		public Criteria andClientIdNotBetween(Long value1, Long value2) {
			addCriterion("client_id not between", value1, value2, "clientId");
			return (Criteria) this;
		}
		
		public Criteria andDocumentIdIsNull() {
			addCriterion("document_id is null");
			return (Criteria) this;
		}
		
		public Criteria andDocumentIdIsNotNull() {
			addCriterion("document_id is not null");
			return (Criteria) this;
		}
		
		public Criteria andDocumentIdEqualTo(String value) {
			addCriterion("document_id =", value, "documentId");
			return (Criteria) this;
		}
		
		public Criteria andDocumentIdNotEqualTo(String value) {
			addCriterion("document_id <>", value, "documentId");
			return (Criteria) this;
		}
		
		public Criteria andDocumentIdGreaterThan(String value) {
			addCriterion("document_id >", value, "documentId");
			return (Criteria) this;
		}
		
		public Criteria andDocumentIdGreaterThanOrEqualTo(String value) {
			addCriterion("document_id >=", value, "documentId");
			return (Criteria) this;
		}
		
		public Criteria andDocumentIdLessThan(String value) {
			addCriterion("document_id <", value, "documentId");
			return (Criteria) this;
		}
		
		public Criteria andDocumentIdLessThanOrEqualTo(String value) {
			addCriterion("document_id <=", value, "documentId");
			return (Criteria) this;
		}
		
		public Criteria andDocumentIdLike(String value) {
			addCriterion("document_id like", value, "documentId");
			return (Criteria) this;
		}
		
		public Criteria andDocumentIdNotLike(String value) {
			addCriterion("document_id not like", value, "documentId");
			return (Criteria) this;
		}
		
		public Criteria andDocumentIdIn(List<String> values) {
			addCriterion("document_id in", values, "documentId");
			return (Criteria) this;
		}
		
		public Criteria andDocumentIdNotIn(List<String> values) {
			addCriterion("document_id not in", values, "documentId");
			return (Criteria) this;
		}
		
		public Criteria andDocumentIdBetween(String value1, String value2) {
			addCriterion("document_id between", value1, value2, "documentId");
			return (Criteria) this;
		}
		
		public Criteria andDocumentIdNotBetween(String value1, String value2) {
			addCriterion("document_id not between", value1, value2, "documentId");
			return (Criteria) this;
		}
		
		public Criteria andBaseEntityIdIsNull() {
			addCriterion("base_entity_id is null");
			return (Criteria) this;
		}
		
		public Criteria andBaseEntityIdIsNotNull() {
			addCriterion("base_entity_id is not null");
			return (Criteria) this;
		}
		
		public Criteria andBaseEntityIdEqualTo(String value) {
			addCriterion("base_entity_id =", value, "baseEntityId");
			return (Criteria) this;
		}
		
		public Criteria andBaseEntityIdNotEqualTo(String value) {
			addCriterion("base_entity_id <>", value, "baseEntityId");
			return (Criteria) this;
		}
		
		public Criteria andBaseEntityIdGreaterThan(String value) {
			addCriterion("base_entity_id >", value, "baseEntityId");
			return (Criteria) this;
		}
		
		public Criteria andBaseEntityIdGreaterThanOrEqualTo(String value) {
			addCriterion("base_entity_id >=", value, "baseEntityId");
			return (Criteria) this;
		}
		
		public Criteria andBaseEntityIdLessThan(String value) {
			addCriterion("base_entity_id <", value, "baseEntityId");
			return (Criteria) this;
		}
		
		public Criteria andBaseEntityIdLessThanOrEqualTo(String value) {
			addCriterion("base_entity_id <=", value, "baseEntityId");
			return (Criteria) this;
		}
		
		public Criteria andBaseEntityIdLike(String value) {
			addCriterion("base_entity_id like", value, "baseEntityId");
			return (Criteria) this;
		}
		
		public Criteria andBaseEntityIdNotLike(String value) {
			addCriterion("base_entity_id not like", value, "baseEntityId");
			return (Criteria) this;
		}
		
		public Criteria andBaseEntityIdIn(List<String> values) {
			addCriterion("base_entity_id in", values, "baseEntityId");
			return (Criteria) this;
		}
		
		public Criteria andBaseEntityIdNotIn(List<String> values) {
			addCriterion("base_entity_id not in", values, "baseEntityId");
			return (Criteria) this;
		}
		
		public Criteria andBaseEntityIdBetween(String value1, String value2) {
			addCriterion("base_entity_id between", value1, value2, "baseEntityId");
			return (Criteria) this;
		}
		
		public Criteria andBaseEntityIdNotBetween(String value1, String value2) {
			addCriterion("base_entity_id not between", value1, value2, "baseEntityId");
			return (Criteria) this;
		}
		
		public Criteria andRelationalIdIsNull() {
			addCriterion("relational_id is null");
			return (Criteria) this;
		}
		
		public Criteria andRelationalIdIsNotNull() {
			addCriterion("relational_id is not null");
			return (Criteria) this;
		}
		
		public Criteria andRelationalIdEqualTo(String value) {
			addCriterion("relational_id =", value, "relationalId");
			return (Criteria) this;
		}
		
		public Criteria andRelationalIdNotEqualTo(String value) {
			addCriterion("relational_id <>", value, "relationalId");
			return (Criteria) this;
		}
		
		public Criteria andRelationalIdGreaterThan(String value) {
			addCriterion("relational_id >", value, "relationalId");
			return (Criteria) this;
		}
		
		public Criteria andRelationalIdGreaterThanOrEqualTo(String value) {
			addCriterion("relational_id >=", value, "relationalId");
			return (Criteria) this;
		}
		
		public Criteria andRelationalIdLessThan(String value) {
			addCriterion("relational_id <", value, "relationalId");
			return (Criteria) this;
		}
		
		public Criteria andRelationalIdLessThanOrEqualTo(String value) {
			addCriterion("relational_id <=", value, "relationalId");
			return (Criteria) this;
		}
		
		public Criteria andRelationalIdLike(String value) {
			addCriterion("relational_id like", value, "relationalId");
			return (Criteria) this;
		}
		
		public Criteria andRelationalIdNotLike(String value) {
			addCriterion("relational_id not like", value, "relationalId");
			return (Criteria) this;
		}
		
		public Criteria andRelationalIdIn(List<String> values) {
			addCriterion("relational_id in", values, "relationalId");
			return (Criteria) this;
		}
		
		public Criteria andRelationalIdNotIn(List<String> values) {
			addCriterion("relational_id not in", values, "relationalId");
			return (Criteria) this;
		}
		
		public Criteria andRelationalIdBetween(String value1, String value2) {
			addCriterion("relational_id between", value1, value2, "relationalId");
			return (Criteria) this;
		}
		
		public Criteria andRelationalIdNotBetween(String value1, String value2) {
			addCriterion("relational_id not between", value1, value2, "relationalId");
			return (Criteria) this;
		}
		
		public Criteria andServerVersionIsNull() {
			addCriterion("server_version is null");
			return (Criteria) this;
		}
		
		public Criteria andServerVersionIsNotNull() {
			addCriterion("server_version is not null");
			return (Criteria) this;
		}
		
		public Criteria andServerVersionEqualTo(Long value) {
			addCriterion("server_version =", value, "serverVersion");
			return (Criteria) this;
		}
		
		public Criteria andServerVersionNotEqualTo(Long value) {
			addCriterion("server_version <>", value, "serverVersion");
			return (Criteria) this;
		}
		
		public Criteria andServerVersionGreaterThan(Long value) {
			addCriterion("server_version >", value, "serverVersion");
			return (Criteria) this;
		}
		
		public Criteria andServerVersionGreaterThanOrEqualTo(Long value) {
			addCriterion("server_version >=", value, "serverVersion");
			return (Criteria) this;
		}
		
		public Criteria andServerVersionLessThan(Long value) {
			addCriterion("server_version <", value, "serverVersion");
			return (Criteria) this;
		}
		
		public Criteria andServerVersionLessThanOrEqualTo(Long value) {
			addCriterion("server_version <=", value, "serverVersion");
			return (Criteria) this;
		}
		
		public Criteria andServerVersionIn(List<Long> values) {
			addCriterion("server_version in", values, "serverVersion");
			return (Criteria) this;
		}
		
		public Criteria andServerVersionNotIn(List<Long> values) {
			addCriterion("server_version not in", values, "serverVersion");
			return (Criteria) this;
		}
		
		public Criteria andServerVersionBetween(Long value1, Long value2) {
			addCriterion("server_version between", value1, value2, "serverVersion");
			return (Criteria) this;
		}
		
		public Criteria andServerVersionNotBetween(Long value1, Long value2) {
			addCriterion("server_version not between", value1, value2, "serverVersion");
			return (Criteria) this;
		}
		
		public Criteria andOpenmrsUuidIsNull() {
			addCriterion("openmrs_uuid is null");
			return (Criteria) this;
		}
		
		public Criteria andOpenmrsUuidIsNotNull() {
			addCriterion("openmrs_uuid is not null");
			return (Criteria) this;
		}
		
		public Criteria andOpenmrsUuidEqualTo(String value) {
			addCriterion("openmrs_uuid =", value, "openmrsUuid");
			return (Criteria) this;
		}
		
		public Criteria andOpenmrsUuidNotEqualTo(String value) {
			addCriterion("openmrs_uuid <>", value, "openmrsUuid");
			return (Criteria) this;
		}
		
		public Criteria andOpenmrsUuidGreaterThan(String value) {
			addCriterion("openmrs_uuid >", value, "openmrsUuid");
			return (Criteria) this;
		}
		
		public Criteria andOpenmrsUuidGreaterThanOrEqualTo(String value) {
			addCriterion("openmrs_uuid >=", value, "openmrsUuid");
			return (Criteria) this;
		}
		
		public Criteria andOpenmrsUuidLessThan(String value) {
			addCriterion("openmrs_uuid <", value, "openmrsUuid");
			return (Criteria) this;
		}
		
		public Criteria andOpenmrsUuidLessThanOrEqualTo(String value) {
			addCriterion("openmrs_uuid <=", value, "openmrsUuid");
			return (Criteria) this;
		}
		
		public Criteria andOpenmrsUuidLike(String value) {
			addCriterion("openmrs_uuid like", value, "openmrsUuid");
			return (Criteria) this;
		}
		
		public Criteria andOpenmrsUuidNotLike(String value) {
			addCriterion("openmrs_uuid not like", value, "openmrsUuid");
			return (Criteria) this;
		}
		
		public Criteria andOpenmrsUuidIn(List<String> values) {
			addCriterion("openmrs_uuid in", values, "openmrsUuid");
			return (Criteria) this;
		}
		
		public Criteria andOpenmrsUuidNotIn(List<String> values) {
			addCriterion("openmrs_uuid not in", values, "openmrsUuid");
			return (Criteria) this;
		}
		
		public Criteria andOpenmrsUuidBetween(String value1, String value2) {
			addCriterion("openmrs_uuid between", value1, value2, "openmrsUuid");
			return (Criteria) this;
		}
		
		public Criteria andOpenmrsUuidNotBetween(String value1, String value2) {
			addCriterion("openmrs_uuid not between", value1, value2, "openmrsUuid");
			return (Criteria) this;
		}
		
		public Criteria andUniqueIdIsNull() {
			addCriterion("unique_id is null");
			return (Criteria) this;
		}
		
		public Criteria andUniqueIdIsNotNull() {
			addCriterion("unique_id is not null");
			return (Criteria) this;
		}
		
		public Criteria andUniqueIdEqualTo(String value) {
			addCriterion("unique_id =", value, "uniqueId");
			return (Criteria) this;
		}
		
		public Criteria andUniqueIdNotEqualTo(String value) {
			addCriterion("unique_id <>", value, "uniqueId");
			return (Criteria) this;
		}
		
		public Criteria andUniqueIdGreaterThan(String value) {
			addCriterion("unique_id >", value, "uniqueId");
			return (Criteria) this;
		}
		
		public Criteria andUniqueIdGreaterThanOrEqualTo(String value) {
			addCriterion("unique_id >=", value, "uniqueId");
			return (Criteria) this;
		}
		
		public Criteria andUniqueIdLessThan(String value) {
			addCriterion("unique_id <", value, "uniqueId");
			return (Criteria) this;
		}
		
		public Criteria andUniqueIdLessThanOrEqualTo(String value) {
			addCriterion("unique_id <=", value, "uniqueId");
			return (Criteria) this;
		}
		
		public Criteria andUniqueIdLike(String value) {
			addCriterion("unique_id like", value, "uniqueId");
			return (Criteria) this;
		}
		
		public Criteria andUniqueIdNotLike(String value) {
			addCriterion("unique_id not like", value, "uniqueId");
			return (Criteria) this;
		}
		
		public Criteria andUniqueIdIn(List<String> values) {
			addCriterion("unique_id in", values, "uniqueId");
			return (Criteria) this;
		}
		
		public Criteria andUniqueIdNotIn(List<String> values) {
			addCriterion("unique_id not in", values, "uniqueId");
			return (Criteria) this;
		}
		
		public Criteria andUniqueIdBetween(String value1, String value2) {
			addCriterion("unique_id between", value1, value2, "uniqueId");
			return (Criteria) this;
		}
		
		public Criteria andUniqueIdNotBetween(String value1, String value2) {
			addCriterion("unique_id not between", value1, value2, "uniqueId");
			return (Criteria) this;
		}
		
		public Criteria andFirstNameIsNull() {
			addCriterion("first_name is null");
			return (Criteria) this;
		}
		
		public Criteria andFirstNameIsNotNull() {
			addCriterion("first_name is not null");
			return (Criteria) this;
		}
		
		public Criteria andFirstNameEqualTo(String value) {
			addCriterion("first_name =", value, "firstName");
			return (Criteria) this;
		}
		
		public Criteria andFirstNameNotEqualTo(String value) {
			addCriterion("first_name <>", value, "firstName");
			return (Criteria) this;
		}
		
		public Criteria andFirstNameGreaterThan(String value) {
			addCriterion("first_name >", value, "firstName");
			return (Criteria) this;
		}
		
		public Criteria andFirstNameGreaterThanOrEqualTo(String value) {
			addCriterion("first_name >=", value, "firstName");
			return (Criteria) this;
		}
		
		public Criteria andFirstNameLessThan(String value) {
			addCriterion("first_name <", value, "firstName");
			return (Criteria) this;
		}
		
		public Criteria andFirstNameLessThanOrEqualTo(String value) {
			addCriterion("first_name <=", value, "firstName");
			return (Criteria) this;
		}
		
		public Criteria andFirstNameLike(String value) {
			addCriterion("first_name like", value, "firstName");
			return (Criteria) this;
		}
		
		public Criteria andFirstNameNotLike(String value) {
			addCriterion("first_name not like", value, "firstName");
			return (Criteria) this;
		}
		
		public Criteria andFirstNameIn(List<String> values) {
			addCriterion("first_name in", values, "firstName");
			return (Criteria) this;
		}
		
		public Criteria andFirstNameNotIn(List<String> values) {
			addCriterion("first_name not in", values, "firstName");
			return (Criteria) this;
		}
		
		public Criteria andFirstNameBetween(String value1, String value2) {
			addCriterion("first_name between", value1, value2, "firstName");
			return (Criteria) this;
		}
		
		public Criteria andFirstNameNotBetween(String value1, String value2) {
			addCriterion("first_name not between", value1, value2, "firstName");
			return (Criteria) this;
		}
		
		public Criteria andMiddleNameIsNull() {
			addCriterion("middle_name is null");
			return (Criteria) this;
		}
		
		public Criteria andMiddleNameIsNotNull() {
			addCriterion("middle_name is not null");
			return (Criteria) this;
		}
		
		public Criteria andMiddleNameEqualTo(String value) {
			addCriterion("middle_name =", value, "middleName");
			return (Criteria) this;
		}
		
		public Criteria andMiddleNameNotEqualTo(String value) {
			addCriterion("middle_name <>", value, "middleName");
			return (Criteria) this;
		}
		
		public Criteria andMiddleNameGreaterThan(String value) {
			addCriterion("middle_name >", value, "middleName");
			return (Criteria) this;
		}
		
		public Criteria andMiddleNameGreaterThanOrEqualTo(String value) {
			addCriterion("middle_name >=", value, "middleName");
			return (Criteria) this;
		}
		
		public Criteria andMiddleNameLessThan(String value) {
			addCriterion("middle_name <", value, "middleName");
			return (Criteria) this;
		}
		
		public Criteria andMiddleNameLessThanOrEqualTo(String value) {
			addCriterion("middle_name <=", value, "middleName");
			return (Criteria) this;
		}
		
		public Criteria andMiddleNameLike(String value) {
			addCriterion("middle_name like", value, "middleName");
			return (Criteria) this;
		}
		
		public Criteria andMiddleNameNotLike(String value) {
			addCriterion("middle_name not like", value, "middleName");
			return (Criteria) this;
		}
		
		public Criteria andMiddleNameIn(List<String> values) {
			addCriterion("middle_name in", values, "middleName");
			return (Criteria) this;
		}
		
		public Criteria andMiddleNameNotIn(List<String> values) {
			addCriterion("middle_name not in", values, "middleName");
			return (Criteria) this;
		}
		
		public Criteria andMiddleNameBetween(String value1, String value2) {
			addCriterion("middle_name between", value1, value2, "middleName");
			return (Criteria) this;
		}
		
		public Criteria andMiddleNameNotBetween(String value1, String value2) {
			addCriterion("middle_name not between", value1, value2, "middleName");
			return (Criteria) this;
		}
		
		public Criteria andLastNameIsNull() {
			addCriterion("last_name is null");
			return (Criteria) this;
		}
		
		public Criteria andLastNameIsNotNull() {
			addCriterion("last_name is not null");
			return (Criteria) this;
		}
		
		public Criteria andLastNameEqualTo(String value) {
			addCriterion("last_name =", value, "lastName");
			return (Criteria) this;
		}
		
		public Criteria andLastNameNotEqualTo(String value) {
			addCriterion("last_name <>", value, "lastName");
			return (Criteria) this;
		}
		
		public Criteria andLastNameGreaterThan(String value) {
			addCriterion("last_name >", value, "lastName");
			return (Criteria) this;
		}
		
		public Criteria andLastNameGreaterThanOrEqualTo(String value) {
			addCriterion("last_name >=", value, "lastName");
			return (Criteria) this;
		}
		
		public Criteria andLastNameLessThan(String value) {
			addCriterion("last_name <", value, "lastName");
			return (Criteria) this;
		}
		
		public Criteria andLastNameLessThanOrEqualTo(String value) {
			addCriterion("last_name <=", value, "lastName");
			return (Criteria) this;
		}
		
		public Criteria andLastNameLike(String value) {
			addCriterion("last_name like", value, "lastName");
			return (Criteria) this;
		}
		
		public Criteria andLastNameNotLike(String value) {
			addCriterion("last_name not like", value, "lastName");
			return (Criteria) this;
		}
		
		public Criteria andLastNameIn(List<String> values) {
			addCriterion("last_name in", values, "lastName");
			return (Criteria) this;
		}
		
		public Criteria andLastNameNotIn(List<String> values) {
			addCriterion("last_name not in", values, "lastName");
			return (Criteria) this;
		}
		
		public Criteria andLastNameBetween(String value1, String value2) {
			addCriterion("last_name between", value1, value2, "lastName");
			return (Criteria) this;
		}
		
		public Criteria andLastNameNotBetween(String value1, String value2) {
			addCriterion("last_name not between", value1, value2, "lastName");
			return (Criteria) this;
		}
		
		public Criteria andBirthDateIsNull() {
			addCriterion("birth_date is null");
			return (Criteria) this;
		}
		
		public Criteria andBirthDateIsNotNull() {
			addCriterion("birth_date is not null");
			return (Criteria) this;
		}
		
		public Criteria andBirthDateEqualTo(Date value) {
			addCriterionForJDBCDate("birth_date =", value, "birthDate");
			return (Criteria) this;
		}
		
		public Criteria andBirthDateNotEqualTo(Date value) {
			addCriterionForJDBCDate("birth_date <>", value, "birthDate");
			return (Criteria) this;
		}
		
		public Criteria andBirthDateGreaterThan(Date value) {
			addCriterionForJDBCDate("birth_date >", value, "birthDate");
			return (Criteria) this;
		}
		
		public Criteria andBirthDateGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("birth_date >=", value, "birthDate");
			return (Criteria) this;
		}
		
		public Criteria andBirthDateLessThan(Date value) {
			addCriterionForJDBCDate("birth_date <", value, "birthDate");
			return (Criteria) this;
		}
		
		public Criteria andBirthDateLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("birth_date <=", value, "birthDate");
			return (Criteria) this;
		}
		
		public Criteria andBirthDateIn(List<Date> values) {
			addCriterionForJDBCDate("birth_date in", values, "birthDate");
			return (Criteria) this;
		}
		
		public Criteria andBirthDateNotIn(List<Date> values) {
			addCriterionForJDBCDate("birth_date not in", values, "birthDate");
			return (Criteria) this;
		}
		
		public Criteria andBirthDateBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("birth_date between", value1, value2, "birthDate");
			return (Criteria) this;
		}
		
		public Criteria andBirthDateNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("birth_date not between", value1, value2, "birthDate");
			return (Criteria) this;
		}
		
		public Criteria andDateDeletedIsNull() {
			addCriterion("date_deleted is null");
			return (Criteria) this;
		}
		
		public Criteria andDateDeletedIsNotNull() {
			addCriterion("date_deleted is not null");
			return (Criteria) this;
		}
		
		public Criteria andDateDeletedEqualTo(Date value) {
			addCriterion("date_deleted =", value, "dateDeleted");
			return (Criteria) this;
		}
		
		public Criteria andDateDeletedNotEqualTo(Date value) {
			addCriterion("date_deleted <>", value, "dateDeleted");
			return (Criteria) this;
		}
		
		public Criteria andDateDeletedGreaterThan(Date value) {
			addCriterion("date_deleted >", value, "dateDeleted");
			return (Criteria) this;
		}
		
		public Criteria andDateDeletedGreaterThanOrEqualTo(Date value) {
			addCriterion("date_deleted >=", value, "dateDeleted");
			return (Criteria) this;
		}
		
		public Criteria andDateDeletedLessThan(Date value) {
			addCriterion("date_deleted <", value, "dateDeleted");
			return (Criteria) this;
		}
		
		public Criteria andDateDeletedLessThanOrEqualTo(Date value) {
			addCriterion("date_deleted <=", value, "dateDeleted");
			return (Criteria) this;
		}
		
		public Criteria andDateDeletedIn(List<Date> values) {
			addCriterion("date_deleted in", values, "dateDeleted");
			return (Criteria) this;
		}
		
		public Criteria andDateDeletedNotIn(List<Date> values) {
			addCriterion("date_deleted not in", values, "dateDeleted");
			return (Criteria) this;
		}
		
		public Criteria andDateDeletedBetween(Date value1, Date value2) {
			addCriterion("date_deleted between", value1, value2, "dateDeleted");
			return (Criteria) this;
		}
		
		public Criteria andDateDeletedNotBetween(Date value1, Date value2) {
			addCriterion("date_deleted not between", value1, value2, "dateDeleted");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table core.client_metadata
	 * @mbg.generated  Fri Apr 06 15:32:26 EAT 2018
	 */
	public static class Criterion {
		
		private String condition;
		
		private Object value;
		
		private Object secondValue;
		
		private boolean noValue;
		
		private boolean singleValue;
		
		private boolean betweenValue;
		
		private boolean listValue;
		
		private String typeHandler;
		
		public String getCondition() {
			return condition;
		}
		
		public Object getValue() {
			return value;
		}
		
		public Object getSecondValue() {
			return secondValue;
		}
		
		public boolean isNoValue() {
			return noValue;
		}
		
		public boolean isSingleValue() {
			return singleValue;
		}
		
		public boolean isBetweenValue() {
			return betweenValue;
		}
		
		public boolean isListValue() {
			return listValue;
		}
		
		public String getTypeHandler() {
			return typeHandler;
		}
		
		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}
		
		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}
		
		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}
		
		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}
		
		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table core.client_metadata
     *
     * @mbg.generated do_not_delete_during_merge Wed Mar 07 18:26:22 EAT 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}