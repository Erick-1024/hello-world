package com.cana.flight.finance.dao.po;

import java.util.ArrayList;
import java.util.List;

public class TzCustomerInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public TzCustomerInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(int limitStart) {
        this.limitStart=limitStart;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(int limitEnd) {
        this.limitEnd=limitEnd;
    }

    public int getLimitEnd() {
        return limitEnd;
    }

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

        public Criteria andTzShortIdIsNull() {
            addCriterion("tz_short_id is null");
            return (Criteria) this;
        }

        public Criteria andTzShortIdIsNotNull() {
            addCriterion("tz_short_id is not null");
            return (Criteria) this;
        }

        public Criteria andTzShortIdEqualTo(Integer value) {
            addCriterion("tz_short_id =", value, "tzShortId");
            return (Criteria) this;
        }

        public Criteria andTzShortIdNotEqualTo(Integer value) {
            addCriterion("tz_short_id <>", value, "tzShortId");
            return (Criteria) this;
        }

        public Criteria andTzShortIdGreaterThan(Integer value) {
            addCriterion("tz_short_id >", value, "tzShortId");
            return (Criteria) this;
        }

        public Criteria andTzShortIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("tz_short_id >=", value, "tzShortId");
            return (Criteria) this;
        }

        public Criteria andTzShortIdLessThan(Integer value) {
            addCriterion("tz_short_id <", value, "tzShortId");
            return (Criteria) this;
        }

        public Criteria andTzShortIdLessThanOrEqualTo(Integer value) {
            addCriterion("tz_short_id <=", value, "tzShortId");
            return (Criteria) this;
        }

        public Criteria andTzShortIdIn(List<Integer> values) {
            addCriterion("tz_short_id in", values, "tzShortId");
            return (Criteria) this;
        }

        public Criteria andTzShortIdNotIn(List<Integer> values) {
            addCriterion("tz_short_id not in", values, "tzShortId");
            return (Criteria) this;
        }

        public Criteria andTzShortIdBetween(Integer value1, Integer value2) {
            addCriterion("tz_short_id between", value1, value2, "tzShortId");
            return (Criteria) this;
        }

        public Criteria andTzShortIdNotBetween(Integer value1, Integer value2) {
            addCriterion("tz_short_id not between", value1, value2, "tzShortId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdIsNull() {
            addCriterion("tz_customer_id is null");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdIsNotNull() {
            addCriterion("tz_customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdEqualTo(String value) {
            addCriterion("tz_customer_id =", value, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdNotEqualTo(String value) {
            addCriterion("tz_customer_id <>", value, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdGreaterThan(String value) {
            addCriterion("tz_customer_id >", value, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdGreaterThanOrEqualTo(String value) {
            addCriterion("tz_customer_id >=", value, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdLessThan(String value) {
            addCriterion("tz_customer_id <", value, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdLessThanOrEqualTo(String value) {
            addCriterion("tz_customer_id <=", value, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdLike(String value) {
            addCriterion("tz_customer_id like", value, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdNotLike(String value) {
            addCriterion("tz_customer_id not like", value, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdIn(List<String> values) {
            addCriterion("tz_customer_id in", values, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdNotIn(List<String> values) {
            addCriterion("tz_customer_id not in", values, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdBetween(String value1, String value2) {
            addCriterion("tz_customer_id between", value1, value2, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdNotBetween(String value1, String value2) {
            addCriterion("tz_customer_id not between", value1, value2, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andCustomerNamesIsNull() {
            addCriterion("customer_names is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNamesIsNotNull() {
            addCriterion("customer_names is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNamesEqualTo(String value) {
            addCriterion("customer_names =", value, "customerNames");
            return (Criteria) this;
        }

        public Criteria andCustomerNamesNotEqualTo(String value) {
            addCriterion("customer_names <>", value, "customerNames");
            return (Criteria) this;
        }

        public Criteria andCustomerNamesGreaterThan(String value) {
            addCriterion("customer_names >", value, "customerNames");
            return (Criteria) this;
        }

        public Criteria andCustomerNamesGreaterThanOrEqualTo(String value) {
            addCriterion("customer_names >=", value, "customerNames");
            return (Criteria) this;
        }

        public Criteria andCustomerNamesLessThan(String value) {
            addCriterion("customer_names <", value, "customerNames");
            return (Criteria) this;
        }

        public Criteria andCustomerNamesLessThanOrEqualTo(String value) {
            addCriterion("customer_names <=", value, "customerNames");
            return (Criteria) this;
        }

        public Criteria andCustomerNamesLike(String value) {
            addCriterion("customer_names like", value, "customerNames");
            return (Criteria) this;
        }

        public Criteria andCustomerNamesNotLike(String value) {
            addCriterion("customer_names not like", value, "customerNames");
            return (Criteria) this;
        }

        public Criteria andCustomerNamesIn(List<String> values) {
            addCriterion("customer_names in", values, "customerNames");
            return (Criteria) this;
        }

        public Criteria andCustomerNamesNotIn(List<String> values) {
            addCriterion("customer_names not in", values, "customerNames");
            return (Criteria) this;
        }

        public Criteria andCustomerNamesBetween(String value1, String value2) {
            addCriterion("customer_names between", value1, value2, "customerNames");
            return (Criteria) this;
        }

        public Criteria andCustomerNamesNotBetween(String value1, String value2) {
            addCriterion("customer_names not between", value1, value2, "customerNames");
            return (Criteria) this;
        }

        public Criteria andFirstBusinessTimeIsNull() {
            addCriterion("first_business_time is null");
            return (Criteria) this;
        }

        public Criteria andFirstBusinessTimeIsNotNull() {
            addCriterion("first_business_time is not null");
            return (Criteria) this;
        }

        public Criteria andFirstBusinessTimeEqualTo(String value) {
            addCriterion("first_business_time =", value, "firstBusinessTime");
            return (Criteria) this;
        }

        public Criteria andFirstBusinessTimeNotEqualTo(String value) {
            addCriterion("first_business_time <>", value, "firstBusinessTime");
            return (Criteria) this;
        }

        public Criteria andFirstBusinessTimeGreaterThan(String value) {
            addCriterion("first_business_time >", value, "firstBusinessTime");
            return (Criteria) this;
        }

        public Criteria andFirstBusinessTimeGreaterThanOrEqualTo(String value) {
            addCriterion("first_business_time >=", value, "firstBusinessTime");
            return (Criteria) this;
        }

        public Criteria andFirstBusinessTimeLessThan(String value) {
            addCriterion("first_business_time <", value, "firstBusinessTime");
            return (Criteria) this;
        }

        public Criteria andFirstBusinessTimeLessThanOrEqualTo(String value) {
            addCriterion("first_business_time <=", value, "firstBusinessTime");
            return (Criteria) this;
        }

        public Criteria andFirstBusinessTimeLike(String value) {
            addCriterion("first_business_time like", value, "firstBusinessTime");
            return (Criteria) this;
        }

        public Criteria andFirstBusinessTimeNotLike(String value) {
            addCriterion("first_business_time not like", value, "firstBusinessTime");
            return (Criteria) this;
        }

        public Criteria andFirstBusinessTimeIn(List<String> values) {
            addCriterion("first_business_time in", values, "firstBusinessTime");
            return (Criteria) this;
        }

        public Criteria andFirstBusinessTimeNotIn(List<String> values) {
            addCriterion("first_business_time not in", values, "firstBusinessTime");
            return (Criteria) this;
        }

        public Criteria andFirstBusinessTimeBetween(String value1, String value2) {
            addCriterion("first_business_time between", value1, value2, "firstBusinessTime");
            return (Criteria) this;
        }

        public Criteria andFirstBusinessTimeNotBetween(String value1, String value2) {
            addCriterion("first_business_time not between", value1, value2, "firstBusinessTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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
}