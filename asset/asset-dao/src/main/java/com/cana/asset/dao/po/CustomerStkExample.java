package com.cana.asset.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerStkExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public CustomerStkExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(String value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(String value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(String value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(String value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(String value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(String value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLike(String value) {
            addCriterion("customer_id like", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotLike(String value) {
            addCriterion("customer_id not like", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<String> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<String> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(String value1, String value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(String value1, String value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andStkTypeIsNull() {
            addCriterion("stk_type is null");
            return (Criteria) this;
        }

        public Criteria andStkTypeIsNotNull() {
            addCriterion("stk_type is not null");
            return (Criteria) this;
        }

        public Criteria andStkTypeEqualTo(String value) {
            addCriterion("stk_type =", value, "stkType");
            return (Criteria) this;
        }

        public Criteria andStkTypeNotEqualTo(String value) {
            addCriterion("stk_type <>", value, "stkType");
            return (Criteria) this;
        }

        public Criteria andStkTypeGreaterThan(String value) {
            addCriterion("stk_type >", value, "stkType");
            return (Criteria) this;
        }

        public Criteria andStkTypeGreaterThanOrEqualTo(String value) {
            addCriterion("stk_type >=", value, "stkType");
            return (Criteria) this;
        }

        public Criteria andStkTypeLessThan(String value) {
            addCriterion("stk_type <", value, "stkType");
            return (Criteria) this;
        }

        public Criteria andStkTypeLessThanOrEqualTo(String value) {
            addCriterion("stk_type <=", value, "stkType");
            return (Criteria) this;
        }

        public Criteria andStkTypeLike(String value) {
            addCriterion("stk_type like", value, "stkType");
            return (Criteria) this;
        }

        public Criteria andStkTypeNotLike(String value) {
            addCriterion("stk_type not like", value, "stkType");
            return (Criteria) this;
        }

        public Criteria andStkTypeIn(List<String> values) {
            addCriterion("stk_type in", values, "stkType");
            return (Criteria) this;
        }

        public Criteria andStkTypeNotIn(List<String> values) {
            addCriterion("stk_type not in", values, "stkType");
            return (Criteria) this;
        }

        public Criteria andStkTypeBetween(String value1, String value2) {
            addCriterion("stk_type between", value1, value2, "stkType");
            return (Criteria) this;
        }

        public Criteria andStkTypeNotBetween(String value1, String value2) {
            addCriterion("stk_type not between", value1, value2, "stkType");
            return (Criteria) this;
        }

        public Criteria andStkNameIsNull() {
            addCriterion("stk_name is null");
            return (Criteria) this;
        }

        public Criteria andStkNameIsNotNull() {
            addCriterion("stk_name is not null");
            return (Criteria) this;
        }

        public Criteria andStkNameEqualTo(String value) {
            addCriterion("stk_name =", value, "stkName");
            return (Criteria) this;
        }

        public Criteria andStkNameNotEqualTo(String value) {
            addCriterion("stk_name <>", value, "stkName");
            return (Criteria) this;
        }

        public Criteria andStkNameGreaterThan(String value) {
            addCriterion("stk_name >", value, "stkName");
            return (Criteria) this;
        }

        public Criteria andStkNameGreaterThanOrEqualTo(String value) {
            addCriterion("stk_name >=", value, "stkName");
            return (Criteria) this;
        }

        public Criteria andStkNameLessThan(String value) {
            addCriterion("stk_name <", value, "stkName");
            return (Criteria) this;
        }

        public Criteria andStkNameLessThanOrEqualTo(String value) {
            addCriterion("stk_name <=", value, "stkName");
            return (Criteria) this;
        }

        public Criteria andStkNameLike(String value) {
            addCriterion("stk_name like", value, "stkName");
            return (Criteria) this;
        }

        public Criteria andStkNameNotLike(String value) {
            addCriterion("stk_name not like", value, "stkName");
            return (Criteria) this;
        }

        public Criteria andStkNameIn(List<String> values) {
            addCriterion("stk_name in", values, "stkName");
            return (Criteria) this;
        }

        public Criteria andStkNameNotIn(List<String> values) {
            addCriterion("stk_name not in", values, "stkName");
            return (Criteria) this;
        }

        public Criteria andStkNameBetween(String value1, String value2) {
            addCriterion("stk_name between", value1, value2, "stkName");
            return (Criteria) this;
        }

        public Criteria andStkNameNotBetween(String value1, String value2) {
            addCriterion("stk_name not between", value1, value2, "stkName");
            return (Criteria) this;
        }

        public Criteria andStkPayamtIsNull() {
            addCriterion("stk_payamt is null");
            return (Criteria) this;
        }

        public Criteria andStkPayamtIsNotNull() {
            addCriterion("stk_payamt is not null");
            return (Criteria) this;
        }

        public Criteria andStkPayamtEqualTo(BigDecimal value) {
            addCriterion("stk_payamt =", value, "stkPayamt");
            return (Criteria) this;
        }

        public Criteria andStkPayamtNotEqualTo(BigDecimal value) {
            addCriterion("stk_payamt <>", value, "stkPayamt");
            return (Criteria) this;
        }

        public Criteria andStkPayamtGreaterThan(BigDecimal value) {
            addCriterion("stk_payamt >", value, "stkPayamt");
            return (Criteria) this;
        }

        public Criteria andStkPayamtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("stk_payamt >=", value, "stkPayamt");
            return (Criteria) this;
        }

        public Criteria andStkPayamtLessThan(BigDecimal value) {
            addCriterion("stk_payamt <", value, "stkPayamt");
            return (Criteria) this;
        }

        public Criteria andStkPayamtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("stk_payamt <=", value, "stkPayamt");
            return (Criteria) this;
        }

        public Criteria andStkPayamtIn(List<BigDecimal> values) {
            addCriterion("stk_payamt in", values, "stkPayamt");
            return (Criteria) this;
        }

        public Criteria andStkPayamtNotIn(List<BigDecimal> values) {
            addCriterion("stk_payamt not in", values, "stkPayamt");
            return (Criteria) this;
        }

        public Criteria andStkPayamtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("stk_payamt between", value1, value2, "stkPayamt");
            return (Criteria) this;
        }

        public Criteria andStkPayamtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("stk_payamt not between", value1, value2, "stkPayamt");
            return (Criteria) this;
        }

        public Criteria andStkPcntIsNull() {
            addCriterion("stk_pcnt is null");
            return (Criteria) this;
        }

        public Criteria andStkPcntIsNotNull() {
            addCriterion("stk_pcnt is not null");
            return (Criteria) this;
        }

        public Criteria andStkPcntEqualTo(BigDecimal value) {
            addCriterion("stk_pcnt =", value, "stkPcnt");
            return (Criteria) this;
        }

        public Criteria andStkPcntNotEqualTo(BigDecimal value) {
            addCriterion("stk_pcnt <>", value, "stkPcnt");
            return (Criteria) this;
        }

        public Criteria andStkPcntGreaterThan(BigDecimal value) {
            addCriterion("stk_pcnt >", value, "stkPcnt");
            return (Criteria) this;
        }

        public Criteria andStkPcntGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("stk_pcnt >=", value, "stkPcnt");
            return (Criteria) this;
        }

        public Criteria andStkPcntLessThan(BigDecimal value) {
            addCriterion("stk_pcnt <", value, "stkPcnt");
            return (Criteria) this;
        }

        public Criteria andStkPcntLessThanOrEqualTo(BigDecimal value) {
            addCriterion("stk_pcnt <=", value, "stkPcnt");
            return (Criteria) this;
        }

        public Criteria andStkPcntIn(List<BigDecimal> values) {
            addCriterion("stk_pcnt in", values, "stkPcnt");
            return (Criteria) this;
        }

        public Criteria andStkPcntNotIn(List<BigDecimal> values) {
            addCriterion("stk_pcnt not in", values, "stkPcnt");
            return (Criteria) this;
        }

        public Criteria andStkPcntBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("stk_pcnt between", value1, value2, "stkPcnt");
            return (Criteria) this;
        }

        public Criteria andStkPcntNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("stk_pcnt not between", value1, value2, "stkPcnt");
            return (Criteria) this;
        }

        public Criteria andStkPayamtTypeIsNull() {
            addCriterion("stk_payamt_type is null");
            return (Criteria) this;
        }

        public Criteria andStkPayamtTypeIsNotNull() {
            addCriterion("stk_payamt_type is not null");
            return (Criteria) this;
        }

        public Criteria andStkPayamtTypeEqualTo(String value) {
            addCriterion("stk_payamt_type =", value, "stkPayamtType");
            return (Criteria) this;
        }

        public Criteria andStkPayamtTypeNotEqualTo(String value) {
            addCriterion("stk_payamt_type <>", value, "stkPayamtType");
            return (Criteria) this;
        }

        public Criteria andStkPayamtTypeGreaterThan(String value) {
            addCriterion("stk_payamt_type >", value, "stkPayamtType");
            return (Criteria) this;
        }

        public Criteria andStkPayamtTypeGreaterThanOrEqualTo(String value) {
            addCriterion("stk_payamt_type >=", value, "stkPayamtType");
            return (Criteria) this;
        }

        public Criteria andStkPayamtTypeLessThan(String value) {
            addCriterion("stk_payamt_type <", value, "stkPayamtType");
            return (Criteria) this;
        }

        public Criteria andStkPayamtTypeLessThanOrEqualTo(String value) {
            addCriterion("stk_payamt_type <=", value, "stkPayamtType");
            return (Criteria) this;
        }

        public Criteria andStkPayamtTypeLike(String value) {
            addCriterion("stk_payamt_type like", value, "stkPayamtType");
            return (Criteria) this;
        }

        public Criteria andStkPayamtTypeNotLike(String value) {
            addCriterion("stk_payamt_type not like", value, "stkPayamtType");
            return (Criteria) this;
        }

        public Criteria andStkPayamtTypeIn(List<String> values) {
            addCriterion("stk_payamt_type in", values, "stkPayamtType");
            return (Criteria) this;
        }

        public Criteria andStkPayamtTypeNotIn(List<String> values) {
            addCriterion("stk_payamt_type not in", values, "stkPayamtType");
            return (Criteria) this;
        }

        public Criteria andStkPayamtTypeBetween(String value1, String value2) {
            addCriterion("stk_payamt_type between", value1, value2, "stkPayamtType");
            return (Criteria) this;
        }

        public Criteria andStkPayamtTypeNotBetween(String value1, String value2) {
            addCriterion("stk_payamt_type not between", value1, value2, "stkPayamtType");
            return (Criteria) this;
        }

        public Criteria andStkStatusIsNull() {
            addCriterion("stk_status is null");
            return (Criteria) this;
        }

        public Criteria andStkStatusIsNotNull() {
            addCriterion("stk_status is not null");
            return (Criteria) this;
        }

        public Criteria andStkStatusEqualTo(String value) {
            addCriterion("stk_status =", value, "stkStatus");
            return (Criteria) this;
        }

        public Criteria andStkStatusNotEqualTo(String value) {
            addCriterion("stk_status <>", value, "stkStatus");
            return (Criteria) this;
        }

        public Criteria andStkStatusGreaterThan(String value) {
            addCriterion("stk_status >", value, "stkStatus");
            return (Criteria) this;
        }

        public Criteria andStkStatusGreaterThanOrEqualTo(String value) {
            addCriterion("stk_status >=", value, "stkStatus");
            return (Criteria) this;
        }

        public Criteria andStkStatusLessThan(String value) {
            addCriterion("stk_status <", value, "stkStatus");
            return (Criteria) this;
        }

        public Criteria andStkStatusLessThanOrEqualTo(String value) {
            addCriterion("stk_status <=", value, "stkStatus");
            return (Criteria) this;
        }

        public Criteria andStkStatusLike(String value) {
            addCriterion("stk_status like", value, "stkStatus");
            return (Criteria) this;
        }

        public Criteria andStkStatusNotLike(String value) {
            addCriterion("stk_status not like", value, "stkStatus");
            return (Criteria) this;
        }

        public Criteria andStkStatusIn(List<String> values) {
            addCriterion("stk_status in", values, "stkStatus");
            return (Criteria) this;
        }

        public Criteria andStkStatusNotIn(List<String> values) {
            addCriterion("stk_status not in", values, "stkStatus");
            return (Criteria) this;
        }

        public Criteria andStkStatusBetween(String value1, String value2) {
            addCriterion("stk_status between", value1, value2, "stkStatus");
            return (Criteria) this;
        }

        public Criteria andStkStatusNotBetween(String value1, String value2) {
            addCriterion("stk_status not between", value1, value2, "stkStatus");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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