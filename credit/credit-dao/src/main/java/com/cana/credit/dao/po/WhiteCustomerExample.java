package com.cana.credit.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WhiteCustomerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public WhiteCustomerExample() {
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

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
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

        public Criteria andCooperationPeriodEqualTo(Integer value) {
            addCriterion("cooperation_period =", value, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodNotEqualTo(Integer value) {
            addCriterion("cooperation_period <>", value, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodGreaterThan(Integer value) {
            addCriterion("cooperation_period >", value, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("cooperation_period >=", value, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodLessThan(Integer value) {
            addCriterion("cooperation_period <", value, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("cooperation_period <=", value, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodIn(List<Integer> values) {
            addCriterion("cooperation_period in", values, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodNotIn(List<Integer> values) {
            addCriterion("cooperation_period not in", values, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodBetween(Integer value1, Integer value2) {
            addCriterion("cooperation_period between", value1, value2, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("cooperation_period not between", value1, value2, "cooperationPeriod");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderGrowthRateIsNull() {
            addCriterion("purchase_order_growth_rate is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderGrowthRateIsNotNull() {
            addCriterion("purchase_order_growth_rate is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderGrowthRateEqualTo(BigDecimal value) {
            addCriterion("purchase_order_growth_rate =", value, "purchaseOrderGrowthRate");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderGrowthRateNotEqualTo(BigDecimal value) {
            addCriterion("purchase_order_growth_rate <>", value, "purchaseOrderGrowthRate");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderGrowthRateGreaterThan(BigDecimal value) {
            addCriterion("purchase_order_growth_rate >", value, "purchaseOrderGrowthRate");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderGrowthRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("purchase_order_growth_rate >=", value, "purchaseOrderGrowthRate");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderGrowthRateLessThan(BigDecimal value) {
            addCriterion("purchase_order_growth_rate <", value, "purchaseOrderGrowthRate");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderGrowthRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("purchase_order_growth_rate <=", value, "purchaseOrderGrowthRate");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderGrowthRateIn(List<BigDecimal> values) {
            addCriterion("purchase_order_growth_rate in", values, "purchaseOrderGrowthRate");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderGrowthRateNotIn(List<BigDecimal> values) {
            addCriterion("purchase_order_growth_rate not in", values, "purchaseOrderGrowthRate");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderGrowthRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("purchase_order_growth_rate between", value1, value2, "purchaseOrderGrowthRate");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderGrowthRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("purchase_order_growth_rate not between", value1, value2, "purchaseOrderGrowthRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateIsNull() {
            addCriterion("overdue_rate is null");
            return (Criteria) this;
        }

        public Criteria andOverdueRateIsNotNull() {
            addCriterion("overdue_rate is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueRateEqualTo(BigDecimal value) {
            addCriterion("overdue_rate =", value, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateNotEqualTo(BigDecimal value) {
            addCriterion("overdue_rate <>", value, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateGreaterThan(BigDecimal value) {
            addCriterion("overdue_rate >", value, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("overdue_rate >=", value, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateLessThan(BigDecimal value) {
            addCriterion("overdue_rate <", value, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("overdue_rate <=", value, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateIn(List<BigDecimal> values) {
            addCriterion("overdue_rate in", values, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateNotIn(List<BigDecimal> values) {
            addCriterion("overdue_rate not in", values, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("overdue_rate between", value1, value2, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("overdue_rate not between", value1, value2, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesIsNull() {
            addCriterion("overdue_times is null");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesIsNotNull() {
            addCriterion("overdue_times is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesEqualTo(Integer value) {
            addCriterion("overdue_times =", value, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesNotEqualTo(Integer value) {
            addCriterion("overdue_times <>", value, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesGreaterThan(Integer value) {
            addCriterion("overdue_times >", value, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("overdue_times >=", value, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesLessThan(Integer value) {
            addCriterion("overdue_times <", value, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesLessThanOrEqualTo(Integer value) {
            addCriterion("overdue_times <=", value, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesIn(List<Integer> values) {
            addCriterion("overdue_times in", values, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesNotIn(List<Integer> values) {
            addCriterion("overdue_times not in", values, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesBetween(Integer value1, Integer value2) {
            addCriterion("overdue_times between", value1, value2, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("overdue_times not between", value1, value2, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andRuleBatchNoIsNull() {
            addCriterion("rule_batch_no is null");
            return (Criteria) this;
        }

        public Criteria andRuleBatchNoIsNotNull() {
            addCriterion("rule_batch_no is not null");
            return (Criteria) this;
        }

        public Criteria andRuleBatchNoEqualTo(Integer value) {
            addCriterion("rule_batch_no =", value, "ruleBatchNo");
            return (Criteria) this;
        }

        public Criteria andRuleBatchNoNotEqualTo(Integer value) {
            addCriterion("rule_batch_no <>", value, "ruleBatchNo");
            return (Criteria) this;
        }

        public Criteria andRuleBatchNoGreaterThan(Integer value) {
            addCriterion("rule_batch_no >", value, "ruleBatchNo");
            return (Criteria) this;
        }

        public Criteria andRuleBatchNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("rule_batch_no >=", value, "ruleBatchNo");
            return (Criteria) this;
        }

        public Criteria andRuleBatchNoLessThan(Integer value) {
            addCriterion("rule_batch_no <", value, "ruleBatchNo");
            return (Criteria) this;
        }

        public Criteria andRuleBatchNoLessThanOrEqualTo(Integer value) {
            addCriterion("rule_batch_no <=", value, "ruleBatchNo");
            return (Criteria) this;
        }

        public Criteria andRuleBatchNoIn(List<Integer> values) {
            addCriterion("rule_batch_no in", values, "ruleBatchNo");
            return (Criteria) this;
        }

        public Criteria andRuleBatchNoNotIn(List<Integer> values) {
            addCriterion("rule_batch_no not in", values, "ruleBatchNo");
            return (Criteria) this;
        }

        public Criteria andRuleBatchNoBetween(Integer value1, Integer value2) {
            addCriterion("rule_batch_no between", value1, value2, "ruleBatchNo");
            return (Criteria) this;
        }

        public Criteria andRuleBatchNoNotBetween(Integer value1, Integer value2) {
            addCriterion("rule_batch_no not between", value1, value2, "ruleBatchNo");
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