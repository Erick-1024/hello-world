package com.cana.credit.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccessRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public AccessRuleExample() {
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

        public Criteria andBatchNoIsNull() {
            addCriterion("batch_no is null");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNotNull() {
            addCriterion("batch_no is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNoEqualTo(Integer value) {
            addCriterion("batch_no =", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotEqualTo(Integer value) {
            addCriterion("batch_no <>", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThan(Integer value) {
            addCriterion("batch_no >", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("batch_no >=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThan(Integer value) {
            addCriterion("batch_no <", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThanOrEqualTo(Integer value) {
            addCriterion("batch_no <=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoIn(List<Integer> values) {
            addCriterion("batch_no in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotIn(List<Integer> values) {
            addCriterion("batch_no not in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoBetween(Integer value1, Integer value2) {
            addCriterion("batch_no between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotBetween(Integer value1, Integer value2) {
            addCriterion("batch_no not between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andFitObjectIsNull() {
            addCriterion("fit_object is null");
            return (Criteria) this;
        }

        public Criteria andFitObjectIsNotNull() {
            addCriterion("fit_object is not null");
            return (Criteria) this;
        }

        public Criteria andFitObjectEqualTo(String value) {
            addCriterion("fit_object =", value, "fitObject");
            return (Criteria) this;
        }

        public Criteria andFitObjectNotEqualTo(String value) {
            addCriterion("fit_object <>", value, "fitObject");
            return (Criteria) this;
        }

        public Criteria andFitObjectGreaterThan(String value) {
            addCriterion("fit_object >", value, "fitObject");
            return (Criteria) this;
        }

        public Criteria andFitObjectGreaterThanOrEqualTo(String value) {
            addCriterion("fit_object >=", value, "fitObject");
            return (Criteria) this;
        }

        public Criteria andFitObjectLessThan(String value) {
            addCriterion("fit_object <", value, "fitObject");
            return (Criteria) this;
        }

        public Criteria andFitObjectLessThanOrEqualTo(String value) {
            addCriterion("fit_object <=", value, "fitObject");
            return (Criteria) this;
        }

        public Criteria andFitObjectLike(String value) {
            addCriterion("fit_object like", value, "fitObject");
            return (Criteria) this;
        }

        public Criteria andFitObjectNotLike(String value) {
            addCriterion("fit_object not like", value, "fitObject");
            return (Criteria) this;
        }

        public Criteria andFitObjectIn(List<String> values) {
            addCriterion("fit_object in", values, "fitObject");
            return (Criteria) this;
        }

        public Criteria andFitObjectNotIn(List<String> values) {
            addCriterion("fit_object not in", values, "fitObject");
            return (Criteria) this;
        }

        public Criteria andFitObjectBetween(String value1, String value2) {
            addCriterion("fit_object between", value1, value2, "fitObject");
            return (Criteria) this;
        }

        public Criteria andFitObjectNotBetween(String value1, String value2) {
            addCriterion("fit_object not between", value1, value2, "fitObject");
            return (Criteria) this;
        }

        public Criteria andIsCheckWhiteCustomerIsNull() {
            addCriterion("is_check_white_customer is null");
            return (Criteria) this;
        }

        public Criteria andIsCheckWhiteCustomerIsNotNull() {
            addCriterion("is_check_white_customer is not null");
            return (Criteria) this;
        }

        public Criteria andIsCheckWhiteCustomerEqualTo(Boolean value) {
            addCriterion("is_check_white_customer =", value, "isCheckWhiteCustomer");
            return (Criteria) this;
        }

        public Criteria andIsCheckWhiteCustomerNotEqualTo(Boolean value) {
            addCriterion("is_check_white_customer <>", value, "isCheckWhiteCustomer");
            return (Criteria) this;
        }

        public Criteria andIsCheckWhiteCustomerGreaterThan(Boolean value) {
            addCriterion("is_check_white_customer >", value, "isCheckWhiteCustomer");
            return (Criteria) this;
        }

        public Criteria andIsCheckWhiteCustomerGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_check_white_customer >=", value, "isCheckWhiteCustomer");
            return (Criteria) this;
        }

        public Criteria andIsCheckWhiteCustomerLessThan(Boolean value) {
            addCriterion("is_check_white_customer <", value, "isCheckWhiteCustomer");
            return (Criteria) this;
        }

        public Criteria andIsCheckWhiteCustomerLessThanOrEqualTo(Boolean value) {
            addCriterion("is_check_white_customer <=", value, "isCheckWhiteCustomer");
            return (Criteria) this;
        }

        public Criteria andIsCheckWhiteCustomerIn(List<Boolean> values) {
            addCriterion("is_check_white_customer in", values, "isCheckWhiteCustomer");
            return (Criteria) this;
        }

        public Criteria andIsCheckWhiteCustomerNotIn(List<Boolean> values) {
            addCriterion("is_check_white_customer not in", values, "isCheckWhiteCustomer");
            return (Criteria) this;
        }

        public Criteria andIsCheckWhiteCustomerBetween(Boolean value1, Boolean value2) {
            addCriterion("is_check_white_customer between", value1, value2, "isCheckWhiteCustomer");
            return (Criteria) this;
        }

        public Criteria andIsCheckWhiteCustomerNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_check_white_customer not between", value1, value2, "isCheckWhiteCustomer");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMinIsNull() {
            addCriterion("cooperation_period_min is null");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMinIsNotNull() {
            addCriterion("cooperation_period_min is not null");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMinEqualTo(Integer value) {
            addCriterion("cooperation_period_min =", value, "cooperationPeriodMin");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMinNotEqualTo(Integer value) {
            addCriterion("cooperation_period_min <>", value, "cooperationPeriodMin");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMinGreaterThan(Integer value) {
            addCriterion("cooperation_period_min >", value, "cooperationPeriodMin");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMinGreaterThanOrEqualTo(Integer value) {
            addCriterion("cooperation_period_min >=", value, "cooperationPeriodMin");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMinLessThan(Integer value) {
            addCriterion("cooperation_period_min <", value, "cooperationPeriodMin");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMinLessThanOrEqualTo(Integer value) {
            addCriterion("cooperation_period_min <=", value, "cooperationPeriodMin");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMinIn(List<Integer> values) {
            addCriterion("cooperation_period_min in", values, "cooperationPeriodMin");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMinNotIn(List<Integer> values) {
            addCriterion("cooperation_period_min not in", values, "cooperationPeriodMin");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMinBetween(Integer value1, Integer value2) {
            addCriterion("cooperation_period_min between", value1, value2, "cooperationPeriodMin");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMinNotBetween(Integer value1, Integer value2) {
            addCriterion("cooperation_period_min not between", value1, value2, "cooperationPeriodMin");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMaxIsNull() {
            addCriterion("cooperation_period_max is null");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMaxIsNotNull() {
            addCriterion("cooperation_period_max is not null");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMaxEqualTo(Integer value) {
            addCriterion("cooperation_period_max =", value, "cooperationPeriodMax");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMaxNotEqualTo(Integer value) {
            addCriterion("cooperation_period_max <>", value, "cooperationPeriodMax");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMaxGreaterThan(Integer value) {
            addCriterion("cooperation_period_max >", value, "cooperationPeriodMax");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("cooperation_period_max >=", value, "cooperationPeriodMax");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMaxLessThan(Integer value) {
            addCriterion("cooperation_period_max <", value, "cooperationPeriodMax");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMaxLessThanOrEqualTo(Integer value) {
            addCriterion("cooperation_period_max <=", value, "cooperationPeriodMax");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMaxIn(List<Integer> values) {
            addCriterion("cooperation_period_max in", values, "cooperationPeriodMax");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMaxNotIn(List<Integer> values) {
            addCriterion("cooperation_period_max not in", values, "cooperationPeriodMax");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMaxBetween(Integer value1, Integer value2) {
            addCriterion("cooperation_period_max between", value1, value2, "cooperationPeriodMax");
            return (Criteria) this;
        }

        public Criteria andCooperationPeriodMaxNotBetween(Integer value1, Integer value2) {
            addCriterion("cooperation_period_max not between", value1, value2, "cooperationPeriodMax");
            return (Criteria) this;
        }

        public Criteria andOverdueRateTzIsNull() {
            addCriterion("overdue_rate_tz is null");
            return (Criteria) this;
        }

        public Criteria andOverdueRateTzIsNotNull() {
            addCriterion("overdue_rate_tz is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueRateTzEqualTo(BigDecimal value) {
            addCriterion("overdue_rate_tz =", value, "overdueRateTz");
            return (Criteria) this;
        }

        public Criteria andOverdueRateTzNotEqualTo(BigDecimal value) {
            addCriterion("overdue_rate_tz <>", value, "overdueRateTz");
            return (Criteria) this;
        }

        public Criteria andOverdueRateTzGreaterThan(BigDecimal value) {
            addCriterion("overdue_rate_tz >", value, "overdueRateTz");
            return (Criteria) this;
        }

        public Criteria andOverdueRateTzGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("overdue_rate_tz >=", value, "overdueRateTz");
            return (Criteria) this;
        }

        public Criteria andOverdueRateTzLessThan(BigDecimal value) {
            addCriterion("overdue_rate_tz <", value, "overdueRateTz");
            return (Criteria) this;
        }

        public Criteria andOverdueRateTzLessThanOrEqualTo(BigDecimal value) {
            addCriterion("overdue_rate_tz <=", value, "overdueRateTz");
            return (Criteria) this;
        }

        public Criteria andOverdueRateTzIn(List<BigDecimal> values) {
            addCriterion("overdue_rate_tz in", values, "overdueRateTz");
            return (Criteria) this;
        }

        public Criteria andOverdueRateTzNotIn(List<BigDecimal> values) {
            addCriterion("overdue_rate_tz not in", values, "overdueRateTz");
            return (Criteria) this;
        }

        public Criteria andOverdueRateTzBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("overdue_rate_tz between", value1, value2, "overdueRateTz");
            return (Criteria) this;
        }

        public Criteria andOverdueRateTzNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("overdue_rate_tz not between", value1, value2, "overdueRateTz");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesTzIsNull() {
            addCriterion("overdue_times_tz is null");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesTzIsNotNull() {
            addCriterion("overdue_times_tz is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesTzEqualTo(Integer value) {
            addCriterion("overdue_times_tz =", value, "overdueTimesTz");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesTzNotEqualTo(Integer value) {
            addCriterion("overdue_times_tz <>", value, "overdueTimesTz");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesTzGreaterThan(Integer value) {
            addCriterion("overdue_times_tz >", value, "overdueTimesTz");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesTzGreaterThanOrEqualTo(Integer value) {
            addCriterion("overdue_times_tz >=", value, "overdueTimesTz");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesTzLessThan(Integer value) {
            addCriterion("overdue_times_tz <", value, "overdueTimesTz");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesTzLessThanOrEqualTo(Integer value) {
            addCriterion("overdue_times_tz <=", value, "overdueTimesTz");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesTzIn(List<Integer> values) {
            addCriterion("overdue_times_tz in", values, "overdueTimesTz");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesTzNotIn(List<Integer> values) {
            addCriterion("overdue_times_tz not in", values, "overdueTimesTz");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesTzBetween(Integer value1, Integer value2) {
            addCriterion("overdue_times_tz between", value1, value2, "overdueTimesTz");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesTzNotBetween(Integer value1, Integer value2) {
            addCriterion("overdue_times_tz not between", value1, value2, "overdueTimesTz");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysTzIsNull() {
            addCriterion("overdue_days_tz is null");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysTzIsNotNull() {
            addCriterion("overdue_days_tz is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysTzEqualTo(Integer value) {
            addCriterion("overdue_days_tz =", value, "overdueDaysTz");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysTzNotEqualTo(Integer value) {
            addCriterion("overdue_days_tz <>", value, "overdueDaysTz");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysTzGreaterThan(Integer value) {
            addCriterion("overdue_days_tz >", value, "overdueDaysTz");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysTzGreaterThanOrEqualTo(Integer value) {
            addCriterion("overdue_days_tz >=", value, "overdueDaysTz");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysTzLessThan(Integer value) {
            addCriterion("overdue_days_tz <", value, "overdueDaysTz");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysTzLessThanOrEqualTo(Integer value) {
            addCriterion("overdue_days_tz <=", value, "overdueDaysTz");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysTzIn(List<Integer> values) {
            addCriterion("overdue_days_tz in", values, "overdueDaysTz");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysTzNotIn(List<Integer> values) {
            addCriterion("overdue_days_tz not in", values, "overdueDaysTz");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysTzBetween(Integer value1, Integer value2) {
            addCriterion("overdue_days_tz between", value1, value2, "overdueDaysTz");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysTzNotBetween(Integer value1, Integer value2) {
            addCriterion("overdue_days_tz not between", value1, value2, "overdueDaysTz");
            return (Criteria) this;
        }

        public Criteria andOverdueRateCanaIsNull() {
            addCriterion("overdue_rate_cana is null");
            return (Criteria) this;
        }

        public Criteria andOverdueRateCanaIsNotNull() {
            addCriterion("overdue_rate_cana is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueRateCanaEqualTo(BigDecimal value) {
            addCriterion("overdue_rate_cana =", value, "overdueRateCana");
            return (Criteria) this;
        }

        public Criteria andOverdueRateCanaNotEqualTo(BigDecimal value) {
            addCriterion("overdue_rate_cana <>", value, "overdueRateCana");
            return (Criteria) this;
        }

        public Criteria andOverdueRateCanaGreaterThan(BigDecimal value) {
            addCriterion("overdue_rate_cana >", value, "overdueRateCana");
            return (Criteria) this;
        }

        public Criteria andOverdueRateCanaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("overdue_rate_cana >=", value, "overdueRateCana");
            return (Criteria) this;
        }

        public Criteria andOverdueRateCanaLessThan(BigDecimal value) {
            addCriterion("overdue_rate_cana <", value, "overdueRateCana");
            return (Criteria) this;
        }

        public Criteria andOverdueRateCanaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("overdue_rate_cana <=", value, "overdueRateCana");
            return (Criteria) this;
        }

        public Criteria andOverdueRateCanaIn(List<BigDecimal> values) {
            addCriterion("overdue_rate_cana in", values, "overdueRateCana");
            return (Criteria) this;
        }

        public Criteria andOverdueRateCanaNotIn(List<BigDecimal> values) {
            addCriterion("overdue_rate_cana not in", values, "overdueRateCana");
            return (Criteria) this;
        }

        public Criteria andOverdueRateCanaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("overdue_rate_cana between", value1, value2, "overdueRateCana");
            return (Criteria) this;
        }

        public Criteria andOverdueRateCanaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("overdue_rate_cana not between", value1, value2, "overdueRateCana");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesCanaIsNull() {
            addCriterion("overdue_times_cana is null");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesCanaIsNotNull() {
            addCriterion("overdue_times_cana is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesCanaEqualTo(Integer value) {
            addCriterion("overdue_times_cana =", value, "overdueTimesCana");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesCanaNotEqualTo(Integer value) {
            addCriterion("overdue_times_cana <>", value, "overdueTimesCana");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesCanaGreaterThan(Integer value) {
            addCriterion("overdue_times_cana >", value, "overdueTimesCana");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesCanaGreaterThanOrEqualTo(Integer value) {
            addCriterion("overdue_times_cana >=", value, "overdueTimesCana");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesCanaLessThan(Integer value) {
            addCriterion("overdue_times_cana <", value, "overdueTimesCana");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesCanaLessThanOrEqualTo(Integer value) {
            addCriterion("overdue_times_cana <=", value, "overdueTimesCana");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesCanaIn(List<Integer> values) {
            addCriterion("overdue_times_cana in", values, "overdueTimesCana");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesCanaNotIn(List<Integer> values) {
            addCriterion("overdue_times_cana not in", values, "overdueTimesCana");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesCanaBetween(Integer value1, Integer value2) {
            addCriterion("overdue_times_cana between", value1, value2, "overdueTimesCana");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesCanaNotBetween(Integer value1, Integer value2) {
            addCriterion("overdue_times_cana not between", value1, value2, "overdueTimesCana");
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

        public Criteria andCourtExecuteCompanyAmountIsNull() {
            addCriterion("court_execute_company_amount is null");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyAmountIsNotNull() {
            addCriterion("court_execute_company_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyAmountEqualTo(Long value) {
            addCriterion("court_execute_company_amount =", value, "courtExecuteCompanyAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyAmountNotEqualTo(Long value) {
            addCriterion("court_execute_company_amount <>", value, "courtExecuteCompanyAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyAmountGreaterThan(Long value) {
            addCriterion("court_execute_company_amount >", value, "courtExecuteCompanyAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("court_execute_company_amount >=", value, "courtExecuteCompanyAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyAmountLessThan(Long value) {
            addCriterion("court_execute_company_amount <", value, "courtExecuteCompanyAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyAmountLessThanOrEqualTo(Long value) {
            addCriterion("court_execute_company_amount <=", value, "courtExecuteCompanyAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyAmountIn(List<Long> values) {
            addCriterion("court_execute_company_amount in", values, "courtExecuteCompanyAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyAmountNotIn(List<Long> values) {
            addCriterion("court_execute_company_amount not in", values, "courtExecuteCompanyAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyAmountBetween(Long value1, Long value2) {
            addCriterion("court_execute_company_amount between", value1, value2, "courtExecuteCompanyAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyAmountNotBetween(Long value1, Long value2) {
            addCriterion("court_execute_company_amount not between", value1, value2, "courtExecuteCompanyAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyTimesIsNull() {
            addCriterion("court_execute_company_times is null");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyTimesIsNotNull() {
            addCriterion("court_execute_company_times is not null");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyTimesEqualTo(Integer value) {
            addCriterion("court_execute_company_times =", value, "courtExecuteCompanyTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyTimesNotEqualTo(Integer value) {
            addCriterion("court_execute_company_times <>", value, "courtExecuteCompanyTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyTimesGreaterThan(Integer value) {
            addCriterion("court_execute_company_times >", value, "courtExecuteCompanyTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("court_execute_company_times >=", value, "courtExecuteCompanyTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyTimesLessThan(Integer value) {
            addCriterion("court_execute_company_times <", value, "courtExecuteCompanyTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyTimesLessThanOrEqualTo(Integer value) {
            addCriterion("court_execute_company_times <=", value, "courtExecuteCompanyTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyTimesIn(List<Integer> values) {
            addCriterion("court_execute_company_times in", values, "courtExecuteCompanyTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyTimesNotIn(List<Integer> values) {
            addCriterion("court_execute_company_times not in", values, "courtExecuteCompanyTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyTimesBetween(Integer value1, Integer value2) {
            addCriterion("court_execute_company_times between", value1, value2, "courtExecuteCompanyTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteCompanyTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("court_execute_company_times not between", value1, value2, "courtExecuteCompanyTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualAmountIsNull() {
            addCriterion("court_execute_individual_amount is null");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualAmountIsNotNull() {
            addCriterion("court_execute_individual_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualAmountEqualTo(Long value) {
            addCriterion("court_execute_individual_amount =", value, "courtExecuteIndividualAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualAmountNotEqualTo(Long value) {
            addCriterion("court_execute_individual_amount <>", value, "courtExecuteIndividualAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualAmountGreaterThan(Long value) {
            addCriterion("court_execute_individual_amount >", value, "courtExecuteIndividualAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("court_execute_individual_amount >=", value, "courtExecuteIndividualAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualAmountLessThan(Long value) {
            addCriterion("court_execute_individual_amount <", value, "courtExecuteIndividualAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualAmountLessThanOrEqualTo(Long value) {
            addCriterion("court_execute_individual_amount <=", value, "courtExecuteIndividualAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualAmountIn(List<Long> values) {
            addCriterion("court_execute_individual_amount in", values, "courtExecuteIndividualAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualAmountNotIn(List<Long> values) {
            addCriterion("court_execute_individual_amount not in", values, "courtExecuteIndividualAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualAmountBetween(Long value1, Long value2) {
            addCriterion("court_execute_individual_amount between", value1, value2, "courtExecuteIndividualAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualAmountNotBetween(Long value1, Long value2) {
            addCriterion("court_execute_individual_amount not between", value1, value2, "courtExecuteIndividualAmount");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualTimesIsNull() {
            addCriterion("court_execute_individual_times is null");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualTimesIsNotNull() {
            addCriterion("court_execute_individual_times is not null");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualTimesEqualTo(Integer value) {
            addCriterion("court_execute_individual_times =", value, "courtExecuteIndividualTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualTimesNotEqualTo(Integer value) {
            addCriterion("court_execute_individual_times <>", value, "courtExecuteIndividualTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualTimesGreaterThan(Integer value) {
            addCriterion("court_execute_individual_times >", value, "courtExecuteIndividualTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("court_execute_individual_times >=", value, "courtExecuteIndividualTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualTimesLessThan(Integer value) {
            addCriterion("court_execute_individual_times <", value, "courtExecuteIndividualTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualTimesLessThanOrEqualTo(Integer value) {
            addCriterion("court_execute_individual_times <=", value, "courtExecuteIndividualTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualTimesIn(List<Integer> values) {
            addCriterion("court_execute_individual_times in", values, "courtExecuteIndividualTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualTimesNotIn(List<Integer> values) {
            addCriterion("court_execute_individual_times not in", values, "courtExecuteIndividualTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualTimesBetween(Integer value1, Integer value2) {
            addCriterion("court_execute_individual_times between", value1, value2, "courtExecuteIndividualTimes");
            return (Criteria) this;
        }

        public Criteria andCourtExecuteIndividualTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("court_execute_individual_times not between", value1, value2, "courtExecuteIndividualTimes");
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