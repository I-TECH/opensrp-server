package org.opensrp.domain.postgres;

import java.util.ArrayList;
import java.util.List;

public class StockExample {
	
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table
	 * core.stock
	 * 
	 * @mbg.generated Fri Mar 23 15:56:38 EAT 2018
	 */
	protected String orderByClause;
	
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table
	 * core.stock
	 * 
	 * @mbg.generated Fri Mar 23 15:56:38 EAT 2018
	 */
	protected boolean distinct;
	
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table
	 * core.stock
	 * 
	 * @mbg.generated Fri Mar 23 15:56:38 EAT 2018
	 */
	protected List<Criteria> oredCriteria;
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table
	 * core.stock
	 * 
	 * @mbg.generated Fri Mar 23 15:56:38 EAT 2018
	 */
	public StockExample() {
		oredCriteria = new ArrayList<Criteria>();
	}
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table
	 * core.stock
	 * 
	 * @mbg.generated Fri Mar 23 15:56:38 EAT 2018
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table
	 * core.stock
	 * 
	 * @mbg.generated Fri Mar 23 15:56:38 EAT 2018
	 */
	public String getOrderByClause() {
		return orderByClause;
	}
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table
	 * core.stock
	 * 
	 * @mbg.generated Fri Mar 23 15:56:38 EAT 2018
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table
	 * core.stock
	 * 
	 * @mbg.generated Fri Mar 23 15:56:38 EAT 2018
	 */
	public boolean isDistinct() {
		return distinct;
	}
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table
	 * core.stock
	 * 
	 * @mbg.generated Fri Mar 23 15:56:38 EAT 2018
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table
	 * core.stock
	 * 
	 * @mbg.generated Fri Mar 23 15:56:38 EAT 2018
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table
	 * core.stock
	 * 
	 * @mbg.generated Fri Mar 23 15:56:38 EAT 2018
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table
	 * core.stock
	 * 
	 * @mbg.generated Fri Mar 23 15:56:38 EAT 2018
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table
	 * core.stock
	 * 
	 * @mbg.generated Fri Mar 23 15:56:38 EAT 2018
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table
	 * core.stock
	 * 
	 * @mbg.generated Fri Mar 23 15:56:38 EAT 2018
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}
	
	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table
	 * core.stock
	 * 
	 * @mbg.generated Fri Mar 23 15:56:38 EAT 2018
	 */
	protected abstract static class GeneratedCriteria {
		
		protected List<Criterion> jsonCriteria;
		
		protected List<Criterion> allCriteria;
		
		protected List<Criterion> criteria;
		
		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
			jsonCriteria = new ArrayList<Criterion>();
		}
		
		public List<Criterion> getJsonCriteria() {
			return jsonCriteria;
		}
		
		protected void addJsonCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			jsonCriteria.add(new Criterion(condition, value, "org.opensrp.repository.postgres.handler.StockTypeHandler"));
			allCriteria = null;
		}
		
		protected void addJsonCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			jsonCriteria.add(
			    new Criterion(condition, value1, value2, "org.opensrp.repository.postgres.handler.StockTypeHandler"));
			allCriteria = null;
		}
		
		public boolean isValid() {
			return criteria.size() > 0 || jsonCriteria.size() > 0;
		}
		
		public List<Criterion> getAllCriteria() {
			if (allCriteria == null) {
				allCriteria = new ArrayList<Criterion>();
				allCriteria.addAll(criteria);
				allCriteria.addAll(jsonCriteria);
			}
			return allCriteria;
		}
		
		public List<Criterion> getCriteria() {
			return criteria;
		}
		
		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
			allCriteria = null;
		}
		
		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
			allCriteria = null;
		}
		
		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
			allCriteria = null;
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
		
		public Criteria andJsonIsNull() {
			addCriterion("json is null");
			return (Criteria) this;
		}
		
		public Criteria andJsonIsNotNull() {
			addCriterion("json is not null");
			return (Criteria) this;
		}
		
		public Criteria andJsonEqualTo(Object value) {
			addJsonCriterion("json =", value, "json");
			return (Criteria) this;
		}
		
		public Criteria andJsonNotEqualTo(Object value) {
			addJsonCriterion("json <>", value, "json");
			return (Criteria) this;
		}
		
		public Criteria andJsonGreaterThan(Object value) {
			addJsonCriterion("json >", value, "json");
			return (Criteria) this;
		}
		
		public Criteria andJsonGreaterThanOrEqualTo(Object value) {
			addJsonCriterion("json >=", value, "json");
			return (Criteria) this;
		}
		
		public Criteria andJsonLessThan(Object value) {
			addJsonCriterion("json <", value, "json");
			return (Criteria) this;
		}
		
		public Criteria andJsonLessThanOrEqualTo(Object value) {
			addJsonCriterion("json <=", value, "json");
			return (Criteria) this;
		}
		
		public Criteria andJsonIn(List<Object> values) {
			addJsonCriterion("json in", values, "json");
			return (Criteria) this;
		}
		
		public Criteria andJsonNotIn(List<Object> values) {
			addJsonCriterion("json not in", values, "json");
			return (Criteria) this;
		}
		
		public Criteria andJsonBetween(Object value1, Object value2) {
			addJsonCriterion("json between", value1, value2, "json");
			return (Criteria) this;
		}
		
		public Criteria andJsonNotBetween(Object value1, Object value2) {
			addJsonCriterion("json not between", value1, value2, "json");
			return (Criteria) this;
		}
	}
	
	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table
	 * core.stock
	 * 
	 * @mbg.generated Fri Mar 23 15:56:38 EAT 2018
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table
	 * core.stock
	 *
	 * @mbg.generated do_not_delete_during_merge Wed Mar 07 18:26:22 EAT 2018
	 */
	public static class Criteria extends GeneratedCriteria {
		
		protected Criteria() {
			super();
		}
	}
}
