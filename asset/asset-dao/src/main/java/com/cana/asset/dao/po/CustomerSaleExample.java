package com.cana.asset.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerSaleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public CustomerSaleExample() {
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

        public Criteria andSaleCustomerNameIsNull() {
            addCriterion("sale_customer_name is null");
            return (Criteria) this;
        }

        public Criteria andSaleCustomerNameIsNotNull() {
            addCriterion("sale_customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andSaleCustomerNameEqualTo(String value) {
            addCriterion("sale_customer_name =", value, "saleCustomerName");
            return (Criteria) this;
        }

        public Criteria andSaleCustomerNameNotEqualTo(String value) {
            addCriterion("sale_customer_name <>", value, "saleCustomerName");
            return (Criteria) this;
        }

        public Criteria andSaleCustomerNameGreaterThan(String value) {
            addCriterion("sale_customer_name >", value, "saleCustomerName");
            return (Criteria) this;
        }

        public Criteria andSaleCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("sale_customer_name >=", value, "saleCustomerName");
            return (Criteria) this;
        }

        public Criteria andSaleCustomerNameLessThan(String value) {
            addCriterion("sale_customer_name <", value, "saleCustomerName");
            return (Criteria) this;
        }

        public Criteria andSaleCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("sale_customer_name <=", value, "saleCustomerName");
            return (Criteria) this;
        }

        public Criteria andSaleCustomerNameLike(String value) {
            addCriterion("sale_customer_name like", value, "saleCustomerName");
            return (Criteria) this;
        }

        public Criteria andSaleCustomerNameNotLike(String value) {
            addCriterion("sale_customer_name not like", value, "saleCustomerName");
            return (Criteria) this;
        }

        public Criteria andSaleCustomerNameIn(List<String> values) {
            addCriterion("sale_customer_name in", values, "saleCustomerName");
            return (Criteria) this;
        }

        public Criteria andSaleCustomerNameNotIn(List<String> values) {
            addCriterion("sale_customer_name not in", values, "saleCustomerName");
            return (Criteria) this;
        }

        public Criteria andSaleCustomerNameBetween(String value1, String value2) {
            addCriterion("sale_customer_name between", value1, value2, "saleCustomerName");
            return (Criteria) this;
        }

        public Criteria andSaleCustomerNameNotBetween(String value1, String value2) {
            addCriterion("sale_customer_name not between", value1, value2, "saleCustomerName");
            return (Criteria) this;
        }

        public Criteria andSaleLastYearIsNull() {
            addCriterion("sale_last_year is null");
            return (Criteria) this;
        }

        public Criteria andSaleLastYearIsNotNull() {
            addCriterion("sale_last_year is not null");
            return (Criteria) this;
        }

        public Criteria andSaleLastYearEqualTo(BigDecimal value) {
            addCriterion("sale_last_year =", value, "saleLastYear");
            return (Criteria) this;
        }

        public Criteria andSaleLastYearNotEqualTo(BigDecimal value) {
            addCriterion("sale_last_year <>", value, "saleLastYear");
            return (Criteria) this;
        }

        public Criteria andSaleLastYearGreaterThan(BigDecimal value) {
            addCriterion("sale_last_year >", value, "saleLastYear");
            return (Criteria) this;
        }

        public Criteria andSaleLastYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sale_last_year >=", value, "saleLastYear");
            return (Criteria) this;
        }

        public Criteria andSaleLastYearLessThan(BigDecimal value) {
            addCriterion("sale_last_year <", value, "saleLastYear");
            return (Criteria) this;
        }

        public Criteria andSaleLastYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sale_last_year <=", value, "saleLastYear");
            return (Criteria) this;
        }

        public Criteria andSaleLastYearIn(List<BigDecimal> values) {
            addCriterion("sale_last_year in", values, "saleLastYear");
            return (Criteria) this;
        }

        public Criteria andSaleLastYearNotIn(List<BigDecimal> values) {
            addCriterion("sale_last_year not in", values, "saleLastYear");
            return (Criteria) this;
        }

        public Criteria andSaleLastYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sale_last_year between", value1, value2, "saleLastYear");
            return (Criteria) this;
        }

        public Criteria andSaleLastYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sale_last_year not between", value1, value2, "saleLastYear");
            return (Criteria) this;
        }

        public Criteria andPercentIsNull() {
            addCriterion("percent is null");
            return (Criteria) this;
        }

        public Criteria andPercentIsNotNull() {
            addCriterion("percent is not null");
            return (Criteria) this;
        }

        public Criteria andPercentEqualTo(BigDecimal value) {
            addCriterion("percent =", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotEqualTo(BigDecimal value) {
            addCriterion("percent <>", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentGreaterThan(BigDecimal value) {
            addCriterion("percent >", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("percent >=", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentLessThan(BigDecimal value) {
            addCriterion("percent <", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("percent <=", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentIn(List<BigDecimal> values) {
            addCriterion("percent in", values, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotIn(List<BigDecimal> values) {
            addCriterion("percent not in", values, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("percent between", value1, value2, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("percent not between", value1, value2, "percent");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodIsNull() {
            addCriterion("cooperation_period is null");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodIsNotNull() {
            addCriterion("cooperation_period is not null");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodEqualTo(BigDecimal value) {
            addCriterion("cooperation_period =", value, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodNotEqualTo(BigDecimal value) {
            addCriterion("cooperation_period <>", value, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodGreaterThan(BigDecimal value) {
            addCriterion("cooperation_period >", value, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cooperation_period >=", value, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodLessThan(BigDecimal value) {
            addCriterion("cooperation_period <", value, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cooperation_period <=", value, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodIn(List<BigDecimal> values) {
            addCriterion("cooperation_period in", values, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodNotIn(List<BigDecimal> values) {
            addCriterion("cooperation_period not in", values, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cooperation_period between", value1, value2, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cooperation_period not between", value1, value2, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountReceivableBalanceIsNull() {
            addCriterion("account_receivable_balance is null");
            return (Criteria) this;
        }

        public Criteria andAccountReceivableBalanceIsNotNull() {
            addCriterion("account_receivable_balance is not null");
            return (Criteria) this;
        }

        public Criteria andAccountReceivableBalanceEqualTo(BigDecimal value) {
            addCriterion("account_receivable_balance =", value, "accountReceivableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountReceivableBalanceNotEqualTo(BigDecimal value) {
            addCriterion("account_receivable_balance <>", value, "accountReceivableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountReceivableBalanceGreaterThan(BigDecimal value) {
            addCriterion("account_receivable_balance >", value, "accountReceivableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountReceivableBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("account_receivable_balance >=", value, "accountReceivableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountReceivableBalanceLessThan(BigDecimal value) {
            addCriterion("account_receivable_balance <", value, "accountReceivableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountReceivableBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("account_receivable_balance <=", value, "accountReceivableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountReceivableBalanceIn(List<BigDecimal> values) {
            addCriterion("account_receivable_balance in", values, "accountReceivableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountReceivableBalanceNotIn(List<BigDecimal> values) {
            addCriterion("account_receivable_balance not in", values, "accountReceivableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountReceivableBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_receivable_balance between", value1, value2, "accountReceivableBalance");
            return (Criteria) this;
        }

        public Criteria andAccountReceivableBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_receivable_balance not between", value1, value2, "accountReceivableBalance");
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