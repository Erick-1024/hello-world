package com.cana.repayment.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepaymentPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public RepaymentPlanExample() {
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

        public Criteria andBusinessModeIsNull() {
            addCriterion("business_mode is null");
            return (Criteria) this;
        }

        public Criteria andBusinessModeIsNotNull() {
            addCriterion("business_mode is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessModeEqualTo(String value) {
            addCriterion("business_mode =", value, "businessMode");
            return (Criteria) this;
        }

        public Criteria andBusinessModeNotEqualTo(String value) {
            addCriterion("business_mode <>", value, "businessMode");
            return (Criteria) this;
        }

        public Criteria andBusinessModeGreaterThan(String value) {
            addCriterion("business_mode >", value, "businessMode");
            return (Criteria) this;
        }

        public Criteria andBusinessModeGreaterThanOrEqualTo(String value) {
            addCriterion("business_mode >=", value, "businessMode");
            return (Criteria) this;
        }

        public Criteria andBusinessModeLessThan(String value) {
            addCriterion("business_mode <", value, "businessMode");
            return (Criteria) this;
        }

        public Criteria andBusinessModeLessThanOrEqualTo(String value) {
            addCriterion("business_mode <=", value, "businessMode");
            return (Criteria) this;
        }

        public Criteria andBusinessModeLike(String value) {
            addCriterion("business_mode like", value, "businessMode");
            return (Criteria) this;
        }

        public Criteria andBusinessModeNotLike(String value) {
            addCriterion("business_mode not like", value, "businessMode");
            return (Criteria) this;
        }

        public Criteria andBusinessModeIn(List<String> values) {
            addCriterion("business_mode in", values, "businessMode");
            return (Criteria) this;
        }

        public Criteria andBusinessModeNotIn(List<String> values) {
            addCriterion("business_mode not in", values, "businessMode");
            return (Criteria) this;
        }

        public Criteria andBusinessModeBetween(String value1, String value2) {
            addCriterion("business_mode between", value1, value2, "businessMode");
            return (Criteria) this;
        }

        public Criteria andBusinessModeNotBetween(String value1, String value2) {
            addCriterion("business_mode not between", value1, value2, "businessMode");
            return (Criteria) this;
        }

        public Criteria andInputMethodIsNull() {
            addCriterion("input_method is null");
            return (Criteria) this;
        }

        public Criteria andInputMethodIsNotNull() {
            addCriterion("input_method is not null");
            return (Criteria) this;
        }

        public Criteria andInputMethodEqualTo(String value) {
            addCriterion("input_method =", value, "inputMethod");
            return (Criteria) this;
        }

        public Criteria andInputMethodNotEqualTo(String value) {
            addCriterion("input_method <>", value, "inputMethod");
            return (Criteria) this;
        }

        public Criteria andInputMethodGreaterThan(String value) {
            addCriterion("input_method >", value, "inputMethod");
            return (Criteria) this;
        }

        public Criteria andInputMethodGreaterThanOrEqualTo(String value) {
            addCriterion("input_method >=", value, "inputMethod");
            return (Criteria) this;
        }

        public Criteria andInputMethodLessThan(String value) {
            addCriterion("input_method <", value, "inputMethod");
            return (Criteria) this;
        }

        public Criteria andInputMethodLessThanOrEqualTo(String value) {
            addCriterion("input_method <=", value, "inputMethod");
            return (Criteria) this;
        }

        public Criteria andInputMethodLike(String value) {
            addCriterion("input_method like", value, "inputMethod");
            return (Criteria) this;
        }

        public Criteria andInputMethodNotLike(String value) {
            addCriterion("input_method not like", value, "inputMethod");
            return (Criteria) this;
        }

        public Criteria andInputMethodIn(List<String> values) {
            addCriterion("input_method in", values, "inputMethod");
            return (Criteria) this;
        }

        public Criteria andInputMethodNotIn(List<String> values) {
            addCriterion("input_method not in", values, "inputMethod");
            return (Criteria) this;
        }

        public Criteria andInputMethodBetween(String value1, String value2) {
            addCriterion("input_method between", value1, value2, "inputMethod");
            return (Criteria) this;
        }

        public Criteria andInputMethodNotBetween(String value1, String value2) {
            addCriterion("input_method not between", value1, value2, "inputMethod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodIsNull() {
            addCriterion("repayment_period is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodIsNotNull() {
            addCriterion("repayment_period is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodEqualTo(Integer value) {
            addCriterion("repayment_period =", value, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodNotEqualTo(Integer value) {
            addCriterion("repayment_period <>", value, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodGreaterThan(Integer value) {
            addCriterion("repayment_period >", value, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("repayment_period >=", value, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodLessThan(Integer value) {
            addCriterion("repayment_period <", value, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("repayment_period <=", value, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodIn(List<Integer> values) {
            addCriterion("repayment_period in", values, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodNotIn(List<Integer> values) {
            addCriterion("repayment_period not in", values, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodBetween(Integer value1, Integer value2) {
            addCriterion("repayment_period between", value1, value2, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andRepaymentPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("repayment_period not between", value1, value2, "repaymentPeriod");
            return (Criteria) this;
        }

        public Criteria andFinanceIdIsNull() {
            addCriterion("finance_id is null");
            return (Criteria) this;
        }

        public Criteria andFinanceIdIsNotNull() {
            addCriterion("finance_id is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceIdEqualTo(String value) {
            addCriterion("finance_id =", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdNotEqualTo(String value) {
            addCriterion("finance_id <>", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdGreaterThan(String value) {
            addCriterion("finance_id >", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdGreaterThanOrEqualTo(String value) {
            addCriterion("finance_id >=", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdLessThan(String value) {
            addCriterion("finance_id <", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdLessThanOrEqualTo(String value) {
            addCriterion("finance_id <=", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdLike(String value) {
            addCriterion("finance_id like", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdNotLike(String value) {
            addCriterion("finance_id not like", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdIn(List<String> values) {
            addCriterion("finance_id in", values, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdNotIn(List<String> values) {
            addCriterion("finance_id not in", values, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdBetween(String value1, String value2) {
            addCriterion("finance_id between", value1, value2, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdNotBetween(String value1, String value2) {
            addCriterion("finance_id not between", value1, value2, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyIsNull() {
            addCriterion("finance_company is null");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyIsNotNull() {
            addCriterion("finance_company is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyEqualTo(String value) {
            addCriterion("finance_company =", value, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyNotEqualTo(String value) {
            addCriterion("finance_company <>", value, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyGreaterThan(String value) {
            addCriterion("finance_company >", value, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("finance_company >=", value, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyLessThan(String value) {
            addCriterion("finance_company <", value, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyLessThanOrEqualTo(String value) {
            addCriterion("finance_company <=", value, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyLike(String value) {
            addCriterion("finance_company like", value, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyNotLike(String value) {
            addCriterion("finance_company not like", value, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyIn(List<String> values) {
            addCriterion("finance_company in", values, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyNotIn(List<String> values) {
            addCriterion("finance_company not in", values, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyBetween(String value1, String value2) {
            addCriterion("finance_company between", value1, value2, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyNotBetween(String value1, String value2) {
            addCriterion("finance_company not between", value1, value2, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyIdIsNull() {
            addCriterion("core_company_id is null");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyIdIsNotNull() {
            addCriterion("core_company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyIdEqualTo(String value) {
            addCriterion("core_company_id =", value, "coreCompanyId");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyIdNotEqualTo(String value) {
            addCriterion("core_company_id <>", value, "coreCompanyId");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyIdGreaterThan(String value) {
            addCriterion("core_company_id >", value, "coreCompanyId");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("core_company_id >=", value, "coreCompanyId");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyIdLessThan(String value) {
            addCriterion("core_company_id <", value, "coreCompanyId");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("core_company_id <=", value, "coreCompanyId");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyIdLike(String value) {
            addCriterion("core_company_id like", value, "coreCompanyId");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyIdNotLike(String value) {
            addCriterion("core_company_id not like", value, "coreCompanyId");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyIdIn(List<String> values) {
            addCriterion("core_company_id in", values, "coreCompanyId");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyIdNotIn(List<String> values) {
            addCriterion("core_company_id not in", values, "coreCompanyId");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyIdBetween(String value1, String value2) {
            addCriterion("core_company_id between", value1, value2, "coreCompanyId");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyIdNotBetween(String value1, String value2) {
            addCriterion("core_company_id not between", value1, value2, "coreCompanyId");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyNameIsNull() {
            addCriterion("core_company_name is null");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyNameIsNotNull() {
            addCriterion("core_company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyNameEqualTo(String value) {
            addCriterion("core_company_name =", value, "coreCompanyName");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyNameNotEqualTo(String value) {
            addCriterion("core_company_name <>", value, "coreCompanyName");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyNameGreaterThan(String value) {
            addCriterion("core_company_name >", value, "coreCompanyName");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("core_company_name >=", value, "coreCompanyName");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyNameLessThan(String value) {
            addCriterion("core_company_name <", value, "coreCompanyName");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("core_company_name <=", value, "coreCompanyName");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyNameLike(String value) {
            addCriterion("core_company_name like", value, "coreCompanyName");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyNameNotLike(String value) {
            addCriterion("core_company_name not like", value, "coreCompanyName");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyNameIn(List<String> values) {
            addCriterion("core_company_name in", values, "coreCompanyName");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyNameNotIn(List<String> values) {
            addCriterion("core_company_name not in", values, "coreCompanyName");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyNameBetween(String value1, String value2) {
            addCriterion("core_company_name between", value1, value2, "coreCompanyName");
            return (Criteria) this;
        }

        public Criteria andCoreCompanyNameNotBetween(String value1, String value2) {
            addCriterion("core_company_name not between", value1, value2, "coreCompanyName");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountIsNull() {
            addCriterion("finance_amount is null");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountIsNotNull() {
            addCriterion("finance_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountEqualTo(Long value) {
            addCriterion("finance_amount =", value, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountNotEqualTo(Long value) {
            addCriterion("finance_amount <>", value, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountGreaterThan(Long value) {
            addCriterion("finance_amount >", value, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("finance_amount >=", value, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountLessThan(Long value) {
            addCriterion("finance_amount <", value, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountLessThanOrEqualTo(Long value) {
            addCriterion("finance_amount <=", value, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountIn(List<Long> values) {
            addCriterion("finance_amount in", values, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountNotIn(List<Long> values) {
            addCriterion("finance_amount not in", values, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountBetween(Long value1, Long value2) {
            addCriterion("finance_amount between", value1, value2, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountNotBetween(Long value1, Long value2) {
            addCriterion("finance_amount not between", value1, value2, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceIsNull() {
            addCriterion("finance_balance is null");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceIsNotNull() {
            addCriterion("finance_balance is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceEqualTo(Long value) {
            addCriterion("finance_balance =", value, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceNotEqualTo(Long value) {
            addCriterion("finance_balance <>", value, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceGreaterThan(Long value) {
            addCriterion("finance_balance >", value, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("finance_balance >=", value, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceLessThan(Long value) {
            addCriterion("finance_balance <", value, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceLessThanOrEqualTo(Long value) {
            addCriterion("finance_balance <=", value, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceIn(List<Long> values) {
            addCriterion("finance_balance in", values, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceNotIn(List<Long> values) {
            addCriterion("finance_balance not in", values, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceBetween(Long value1, Long value2) {
            addCriterion("finance_balance between", value1, value2, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceNotBetween(Long value1, Long value2) {
            addCriterion("finance_balance not between", value1, value2, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andValueDateIsNull() {
            addCriterion("value_date is null");
            return (Criteria) this;
        }

        public Criteria andValueDateIsNotNull() {
            addCriterion("value_date is not null");
            return (Criteria) this;
        }

        public Criteria andValueDateEqualTo(String value) {
            addCriterion("value_date =", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateNotEqualTo(String value) {
            addCriterion("value_date <>", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateGreaterThan(String value) {
            addCriterion("value_date >", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateGreaterThanOrEqualTo(String value) {
            addCriterion("value_date >=", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateLessThan(String value) {
            addCriterion("value_date <", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateLessThanOrEqualTo(String value) {
            addCriterion("value_date <=", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateLike(String value) {
            addCriterion("value_date like", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateNotLike(String value) {
            addCriterion("value_date not like", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateIn(List<String> values) {
            addCriterion("value_date in", values, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateNotIn(List<String> values) {
            addCriterion("value_date not in", values, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateBetween(String value1, String value2) {
            addCriterion("value_date between", value1, value2, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateNotBetween(String value1, String value2) {
            addCriterion("value_date not between", value1, value2, "valueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateIsNull() {
            addCriterion("due_date is null");
            return (Criteria) this;
        }

        public Criteria andDueDateIsNotNull() {
            addCriterion("due_date is not null");
            return (Criteria) this;
        }

        public Criteria andDueDateEqualTo(String value) {
            addCriterion("due_date =", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotEqualTo(String value) {
            addCriterion("due_date <>", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateGreaterThan(String value) {
            addCriterion("due_date >", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateGreaterThanOrEqualTo(String value) {
            addCriterion("due_date >=", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateLessThan(String value) {
            addCriterion("due_date <", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateLessThanOrEqualTo(String value) {
            addCriterion("due_date <=", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateLike(String value) {
            addCriterion("due_date like", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotLike(String value) {
            addCriterion("due_date not like", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateIn(List<String> values) {
            addCriterion("due_date in", values, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotIn(List<String> values) {
            addCriterion("due_date not in", values, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateBetween(String value1, String value2) {
            addCriterion("due_date between", value1, value2, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotBetween(String value1, String value2) {
            addCriterion("due_date not between", value1, value2, "dueDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateIsNull() {
            addCriterion("settle_interest_date is null");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateIsNotNull() {
            addCriterion("settle_interest_date is not null");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateEqualTo(String value) {
            addCriterion("settle_interest_date =", value, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateNotEqualTo(String value) {
            addCriterion("settle_interest_date <>", value, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateGreaterThan(String value) {
            addCriterion("settle_interest_date >", value, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateGreaterThanOrEqualTo(String value) {
            addCriterion("settle_interest_date >=", value, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateLessThan(String value) {
            addCriterion("settle_interest_date <", value, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateLessThanOrEqualTo(String value) {
            addCriterion("settle_interest_date <=", value, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateLike(String value) {
            addCriterion("settle_interest_date like", value, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateNotLike(String value) {
            addCriterion("settle_interest_date not like", value, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateIn(List<String> values) {
            addCriterion("settle_interest_date in", values, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateNotIn(List<String> values) {
            addCriterion("settle_interest_date not in", values, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateBetween(String value1, String value2) {
            addCriterion("settle_interest_date between", value1, value2, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andSettleInterestDateNotBetween(String value1, String value2) {
            addCriterion("settle_interest_date not between", value1, value2, "settleInterestDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateIsNull() {
            addCriterion("repayment_date is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateIsNotNull() {
            addCriterion("repayment_date is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateEqualTo(String value) {
            addCriterion("repayment_date =", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateNotEqualTo(String value) {
            addCriterion("repayment_date <>", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateGreaterThan(String value) {
            addCriterion("repayment_date >", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateGreaterThanOrEqualTo(String value) {
            addCriterion("repayment_date >=", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateLessThan(String value) {
            addCriterion("repayment_date <", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateLessThanOrEqualTo(String value) {
            addCriterion("repayment_date <=", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateLike(String value) {
            addCriterion("repayment_date like", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateNotLike(String value) {
            addCriterion("repayment_date not like", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateIn(List<String> values) {
            addCriterion("repayment_date in", values, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateNotIn(List<String> values) {
            addCriterion("repayment_date not in", values, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateBetween(String value1, String value2) {
            addCriterion("repayment_date between", value1, value2, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateNotBetween(String value1, String value2) {
            addCriterion("repayment_date not between", value1, value2, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalIsNull() {
            addCriterion("account_principal is null");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalIsNotNull() {
            addCriterion("account_principal is not null");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalEqualTo(Long value) {
            addCriterion("account_principal =", value, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalNotEqualTo(Long value) {
            addCriterion("account_principal <>", value, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalGreaterThan(Long value) {
            addCriterion("account_principal >", value, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalGreaterThanOrEqualTo(Long value) {
            addCriterion("account_principal >=", value, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalLessThan(Long value) {
            addCriterion("account_principal <", value, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalLessThanOrEqualTo(Long value) {
            addCriterion("account_principal <=", value, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalIn(List<Long> values) {
            addCriterion("account_principal in", values, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalNotIn(List<Long> values) {
            addCriterion("account_principal not in", values, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalBetween(Long value1, Long value2) {
            addCriterion("account_principal between", value1, value2, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalNotBetween(Long value1, Long value2) {
            addCriterion("account_principal not between", value1, value2, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountInterestIsNull() {
            addCriterion("account_interest is null");
            return (Criteria) this;
        }

        public Criteria andAccountInterestIsNotNull() {
            addCriterion("account_interest is not null");
            return (Criteria) this;
        }

        public Criteria andAccountInterestEqualTo(Long value) {
            addCriterion("account_interest =", value, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestNotEqualTo(Long value) {
            addCriterion("account_interest <>", value, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestGreaterThan(Long value) {
            addCriterion("account_interest >", value, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestGreaterThanOrEqualTo(Long value) {
            addCriterion("account_interest >=", value, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestLessThan(Long value) {
            addCriterion("account_interest <", value, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestLessThanOrEqualTo(Long value) {
            addCriterion("account_interest <=", value, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestIn(List<Long> values) {
            addCriterion("account_interest in", values, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestNotIn(List<Long> values) {
            addCriterion("account_interest not in", values, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestBetween(Long value1, Long value2) {
            addCriterion("account_interest between", value1, value2, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestNotBetween(Long value1, Long value2) {
            addCriterion("account_interest not between", value1, value2, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeIsNull() {
            addCriterion("account_service_charge is null");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeIsNotNull() {
            addCriterion("account_service_charge is not null");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeEqualTo(Long value) {
            addCriterion("account_service_charge =", value, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeNotEqualTo(Long value) {
            addCriterion("account_service_charge <>", value, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeGreaterThan(Long value) {
            addCriterion("account_service_charge >", value, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("account_service_charge >=", value, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeLessThan(Long value) {
            addCriterion("account_service_charge <", value, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeLessThanOrEqualTo(Long value) {
            addCriterion("account_service_charge <=", value, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeIn(List<Long> values) {
            addCriterion("account_service_charge in", values, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeNotIn(List<Long> values) {
            addCriterion("account_service_charge not in", values, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeBetween(Long value1, Long value2) {
            addCriterion("account_service_charge between", value1, value2, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeNotBetween(Long value1, Long value2) {
            addCriterion("account_service_charge not between", value1, value2, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIsNull() {
            addCriterion("settle_status is null");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIsNotNull() {
            addCriterion("settle_status is not null");
            return (Criteria) this;
        }

        public Criteria andSettleStatusEqualTo(String value) {
            addCriterion("settle_status =", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotEqualTo(String value) {
            addCriterion("settle_status <>", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusGreaterThan(String value) {
            addCriterion("settle_status >", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusGreaterThanOrEqualTo(String value) {
            addCriterion("settle_status >=", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLessThan(String value) {
            addCriterion("settle_status <", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLessThanOrEqualTo(String value) {
            addCriterion("settle_status <=", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLike(String value) {
            addCriterion("settle_status like", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotLike(String value) {
            addCriterion("settle_status not like", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIn(List<String> values) {
            addCriterion("settle_status in", values, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotIn(List<String> values) {
            addCriterion("settle_status not in", values, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBetween(String value1, String value2) {
            addCriterion("settle_status between", value1, value2, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotBetween(String value1, String value2) {
            addCriterion("settle_status not between", value1, value2, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdIsNull() {
            addCriterion("loan_info_id is null");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdIsNotNull() {
            addCriterion("loan_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdEqualTo(String value) {
            addCriterion("loan_info_id =", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdNotEqualTo(String value) {
            addCriterion("loan_info_id <>", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdGreaterThan(String value) {
            addCriterion("loan_info_id >", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdGreaterThanOrEqualTo(String value) {
            addCriterion("loan_info_id >=", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdLessThan(String value) {
            addCriterion("loan_info_id <", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdLessThanOrEqualTo(String value) {
            addCriterion("loan_info_id <=", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdLike(String value) {
            addCriterion("loan_info_id like", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdNotLike(String value) {
            addCriterion("loan_info_id not like", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdIn(List<String> values) {
            addCriterion("loan_info_id in", values, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdNotIn(List<String> values) {
            addCriterion("loan_info_id not in", values, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdBetween(String value1, String value2) {
            addCriterion("loan_info_id between", value1, value2, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdNotBetween(String value1, String value2) {
            addCriterion("loan_info_id not between", value1, value2, "loanInfoId");
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

        public Criteria andUpateTimeIsNull() {
            addCriterion("upate_time is null");
            return (Criteria) this;
        }

        public Criteria andUpateTimeIsNotNull() {
            addCriterion("upate_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpateTimeEqualTo(Date value) {
            addCriterion("upate_time =", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeNotEqualTo(Date value) {
            addCriterion("upate_time <>", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeGreaterThan(Date value) {
            addCriterion("upate_time >", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("upate_time >=", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeLessThan(Date value) {
            addCriterion("upate_time <", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeLessThanOrEqualTo(Date value) {
            addCriterion("upate_time <=", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeIn(List<Date> values) {
            addCriterion("upate_time in", values, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeNotIn(List<Date> values) {
            addCriterion("upate_time not in", values, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeBetween(Date value1, Date value2) {
            addCriterion("upate_time between", value1, value2, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeNotBetween(Date value1, Date value2) {
            addCriterion("upate_time not between", value1, value2, "upateTime");
            return (Criteria) this;
        }

        public Criteria andLoanNoIsNull() {
            addCriterion("loan_no is null");
            return (Criteria) this;
        }

        public Criteria andLoanNoIsNotNull() {
            addCriterion("loan_no is not null");
            return (Criteria) this;
        }

        public Criteria andLoanNoEqualTo(String value) {
            addCriterion("loan_no =", value, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoNotEqualTo(String value) {
            addCriterion("loan_no <>", value, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoGreaterThan(String value) {
            addCriterion("loan_no >", value, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoGreaterThanOrEqualTo(String value) {
            addCriterion("loan_no >=", value, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoLessThan(String value) {
            addCriterion("loan_no <", value, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoLessThanOrEqualTo(String value) {
            addCriterion("loan_no <=", value, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoLike(String value) {
            addCriterion("loan_no like", value, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoNotLike(String value) {
            addCriterion("loan_no not like", value, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoIn(List<String> values) {
            addCriterion("loan_no in", values, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoNotIn(List<String> values) {
            addCriterion("loan_no not in", values, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoBetween(String value1, String value2) {
            addCriterion("loan_no between", value1, value2, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanNoNotBetween(String value1, String value2) {
            addCriterion("loan_no not between", value1, value2, "loanNo");
            return (Criteria) this;
        }

        public Criteria andLoanDateIsNull() {
            addCriterion("loan_date is null");
            return (Criteria) this;
        }

        public Criteria andLoanDateIsNotNull() {
            addCriterion("loan_date is not null");
            return (Criteria) this;
        }

        public Criteria andLoanDateEqualTo(String value) {
            addCriterion("loan_date =", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateNotEqualTo(String value) {
            addCriterion("loan_date <>", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateGreaterThan(String value) {
            addCriterion("loan_date >", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateGreaterThanOrEqualTo(String value) {
            addCriterion("loan_date >=", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateLessThan(String value) {
            addCriterion("loan_date <", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateLessThanOrEqualTo(String value) {
            addCriterion("loan_date <=", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateLike(String value) {
            addCriterion("loan_date like", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateNotLike(String value) {
            addCriterion("loan_date not like", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateIn(List<String> values) {
            addCriterion("loan_date in", values, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateNotIn(List<String> values) {
            addCriterion("loan_date not in", values, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateBetween(String value1, String value2) {
            addCriterion("loan_date between", value1, value2, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateNotBetween(String value1, String value2) {
            addCriterion("loan_date not between", value1, value2, "loanDate");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeIsNull() {
            addCriterion("account_extension_charge is null");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeIsNotNull() {
            addCriterion("account_extension_charge is not null");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeEqualTo(Long value) {
            addCriterion("account_extension_charge =", value, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeNotEqualTo(Long value) {
            addCriterion("account_extension_charge <>", value, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeGreaterThan(Long value) {
            addCriterion("account_extension_charge >", value, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("account_extension_charge >=", value, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeLessThan(Long value) {
            addCriterion("account_extension_charge <", value, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeLessThanOrEqualTo(Long value) {
            addCriterion("account_extension_charge <=", value, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeIn(List<Long> values) {
            addCriterion("account_extension_charge in", values, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeNotIn(List<Long> values) {
            addCriterion("account_extension_charge not in", values, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeBetween(Long value1, Long value2) {
            addCriterion("account_extension_charge between", value1, value2, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeNotBetween(Long value1, Long value2) {
            addCriterion("account_extension_charge not between", value1, value2, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidNormalPrincipalIsNull() {
            addCriterion("paid_normal_principal is null");
            return (Criteria) this;
        }

        public Criteria andPaidNormalPrincipalIsNotNull() {
            addCriterion("paid_normal_principal is not null");
            return (Criteria) this;
        }

        public Criteria andPaidNormalPrincipalEqualTo(Long value) {
            addCriterion("paid_normal_principal =", value, "paidNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidNormalPrincipalNotEqualTo(Long value) {
            addCriterion("paid_normal_principal <>", value, "paidNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidNormalPrincipalGreaterThan(Long value) {
            addCriterion("paid_normal_principal >", value, "paidNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidNormalPrincipalGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_normal_principal >=", value, "paidNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidNormalPrincipalLessThan(Long value) {
            addCriterion("paid_normal_principal <", value, "paidNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidNormalPrincipalLessThanOrEqualTo(Long value) {
            addCriterion("paid_normal_principal <=", value, "paidNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidNormalPrincipalIn(List<Long> values) {
            addCriterion("paid_normal_principal in", values, "paidNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidNormalPrincipalNotIn(List<Long> values) {
            addCriterion("paid_normal_principal not in", values, "paidNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidNormalPrincipalBetween(Long value1, Long value2) {
            addCriterion("paid_normal_principal between", value1, value2, "paidNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidNormalPrincipalNotBetween(Long value1, Long value2) {
            addCriterion("paid_normal_principal not between", value1, value2, "paidNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalIsNull() {
            addCriterion("paid_overdue_principal is null");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalIsNotNull() {
            addCriterion("paid_overdue_principal is not null");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalEqualTo(Long value) {
            addCriterion("paid_overdue_principal =", value, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalNotEqualTo(Long value) {
            addCriterion("paid_overdue_principal <>", value, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalGreaterThan(Long value) {
            addCriterion("paid_overdue_principal >", value, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_principal >=", value, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalLessThan(Long value) {
            addCriterion("paid_overdue_principal <", value, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalLessThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_principal <=", value, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalIn(List<Long> values) {
            addCriterion("paid_overdue_principal in", values, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalNotIn(List<Long> values) {
            addCriterion("paid_overdue_principal not in", values, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_principal between", value1, value2, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalNotBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_principal not between", value1, value2, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidNormalInterestIsNull() {
            addCriterion("paid_normal_interest is null");
            return (Criteria) this;
        }

        public Criteria andPaidNormalInterestIsNotNull() {
            addCriterion("paid_normal_interest is not null");
            return (Criteria) this;
        }

        public Criteria andPaidNormalInterestEqualTo(Long value) {
            addCriterion("paid_normal_interest =", value, "paidNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPaidNormalInterestNotEqualTo(Long value) {
            addCriterion("paid_normal_interest <>", value, "paidNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPaidNormalInterestGreaterThan(Long value) {
            addCriterion("paid_normal_interest >", value, "paidNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPaidNormalInterestGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_normal_interest >=", value, "paidNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPaidNormalInterestLessThan(Long value) {
            addCriterion("paid_normal_interest <", value, "paidNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPaidNormalInterestLessThanOrEqualTo(Long value) {
            addCriterion("paid_normal_interest <=", value, "paidNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPaidNormalInterestIn(List<Long> values) {
            addCriterion("paid_normal_interest in", values, "paidNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPaidNormalInterestNotIn(List<Long> values) {
            addCriterion("paid_normal_interest not in", values, "paidNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPaidNormalInterestBetween(Long value1, Long value2) {
            addCriterion("paid_normal_interest between", value1, value2, "paidNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPaidNormalInterestNotBetween(Long value1, Long value2) {
            addCriterion("paid_normal_interest not between", value1, value2, "paidNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestIsNull() {
            addCriterion("paid_overdue_interest is null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestIsNotNull() {
            addCriterion("paid_overdue_interest is not null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestEqualTo(Long value) {
            addCriterion("paid_overdue_interest =", value, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestNotEqualTo(Long value) {
            addCriterion("paid_overdue_interest <>", value, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestGreaterThan(Long value) {
            addCriterion("paid_overdue_interest >", value, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_interest >=", value, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestLessThan(Long value) {
            addCriterion("paid_overdue_interest <", value, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestLessThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_interest <=", value, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestIn(List<Long> values) {
            addCriterion("paid_overdue_interest in", values, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestNotIn(List<Long> values) {
            addCriterion("paid_overdue_interest not in", values, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_interest between", value1, value2, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestNotBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_interest not between", value1, value2, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidNormalServiceChargeIsNull() {
            addCriterion("paid_normal_service_charge is null");
            return (Criteria) this;
        }

        public Criteria andPaidNormalServiceChargeIsNotNull() {
            addCriterion("paid_normal_service_charge is not null");
            return (Criteria) this;
        }

        public Criteria andPaidNormalServiceChargeEqualTo(Long value) {
            addCriterion("paid_normal_service_charge =", value, "paidNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidNormalServiceChargeNotEqualTo(Long value) {
            addCriterion("paid_normal_service_charge <>", value, "paidNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidNormalServiceChargeGreaterThan(Long value) {
            addCriterion("paid_normal_service_charge >", value, "paidNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidNormalServiceChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_normal_service_charge >=", value, "paidNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidNormalServiceChargeLessThan(Long value) {
            addCriterion("paid_normal_service_charge <", value, "paidNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidNormalServiceChargeLessThanOrEqualTo(Long value) {
            addCriterion("paid_normal_service_charge <=", value, "paidNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidNormalServiceChargeIn(List<Long> values) {
            addCriterion("paid_normal_service_charge in", values, "paidNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidNormalServiceChargeNotIn(List<Long> values) {
            addCriterion("paid_normal_service_charge not in", values, "paidNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidNormalServiceChargeBetween(Long value1, Long value2) {
            addCriterion("paid_normal_service_charge between", value1, value2, "paidNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidNormalServiceChargeNotBetween(Long value1, Long value2) {
            addCriterion("paid_normal_service_charge not between", value1, value2, "paidNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeIsNull() {
            addCriterion("paid_overdue_service_charge is null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeIsNotNull() {
            addCriterion("paid_overdue_service_charge is not null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeEqualTo(Long value) {
            addCriterion("paid_overdue_service_charge =", value, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeNotEqualTo(Long value) {
            addCriterion("paid_overdue_service_charge <>", value, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeGreaterThan(Long value) {
            addCriterion("paid_overdue_service_charge >", value, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_service_charge >=", value, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeLessThan(Long value) {
            addCriterion("paid_overdue_service_charge <", value, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeLessThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_service_charge <=", value, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeIn(List<Long> values) {
            addCriterion("paid_overdue_service_charge in", values, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeNotIn(List<Long> values) {
            addCriterion("paid_overdue_service_charge not in", values, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_service_charge between", value1, value2, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeNotBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_service_charge not between", value1, value2, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeIsNull() {
            addCriterion("paid_early_repayment_charge is null");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeIsNotNull() {
            addCriterion("paid_early_repayment_charge is not null");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeEqualTo(Long value) {
            addCriterion("paid_early_repayment_charge =", value, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeNotEqualTo(Long value) {
            addCriterion("paid_early_repayment_charge <>", value, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeGreaterThan(Long value) {
            addCriterion("paid_early_repayment_charge >", value, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_early_repayment_charge >=", value, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeLessThan(Long value) {
            addCriterion("paid_early_repayment_charge <", value, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeLessThanOrEqualTo(Long value) {
            addCriterion("paid_early_repayment_charge <=", value, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeIn(List<Long> values) {
            addCriterion("paid_early_repayment_charge in", values, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeNotIn(List<Long> values) {
            addCriterion("paid_early_repayment_charge not in", values, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeBetween(Long value1, Long value2) {
            addCriterion("paid_early_repayment_charge between", value1, value2, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeNotBetween(Long value1, Long value2) {
            addCriterion("paid_early_repayment_charge not between", value1, value2, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeIsNull() {
            addCriterion("paid_extension_charge is null");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeIsNotNull() {
            addCriterion("paid_extension_charge is not null");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeEqualTo(Long value) {
            addCriterion("paid_extension_charge =", value, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeNotEqualTo(Long value) {
            addCriterion("paid_extension_charge <>", value, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeGreaterThan(Long value) {
            addCriterion("paid_extension_charge >", value, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_extension_charge >=", value, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeLessThan(Long value) {
            addCriterion("paid_extension_charge <", value, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeLessThanOrEqualTo(Long value) {
            addCriterion("paid_extension_charge <=", value, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeIn(List<Long> values) {
            addCriterion("paid_extension_charge in", values, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeNotIn(List<Long> values) {
            addCriterion("paid_extension_charge not in", values, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeBetween(Long value1, Long value2) {
            addCriterion("paid_extension_charge between", value1, value2, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeNotBetween(Long value1, Long value2) {
            addCriterion("paid_extension_charge not between", value1, value2, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyIsNull() {
            addCriterion("paid_overdue_principal_penalty is null");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyIsNotNull() {
            addCriterion("paid_overdue_principal_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyEqualTo(Long value) {
            addCriterion("paid_overdue_principal_penalty =", value, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyNotEqualTo(Long value) {
            addCriterion("paid_overdue_principal_penalty <>", value, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyGreaterThan(Long value) {
            addCriterion("paid_overdue_principal_penalty >", value, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_principal_penalty >=", value, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyLessThan(Long value) {
            addCriterion("paid_overdue_principal_penalty <", value, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyLessThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_principal_penalty <=", value, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyIn(List<Long> values) {
            addCriterion("paid_overdue_principal_penalty in", values, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyNotIn(List<Long> values) {
            addCriterion("paid_overdue_principal_penalty not in", values, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_principal_penalty between", value1, value2, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyNotBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_principal_penalty not between", value1, value2, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyIsNull() {
            addCriterion("paid_overdue_interest_penalty is null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyIsNotNull() {
            addCriterion("paid_overdue_interest_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyEqualTo(Long value) {
            addCriterion("paid_overdue_interest_penalty =", value, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyNotEqualTo(Long value) {
            addCriterion("paid_overdue_interest_penalty <>", value, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyGreaterThan(Long value) {
            addCriterion("paid_overdue_interest_penalty >", value, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_interest_penalty >=", value, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyLessThan(Long value) {
            addCriterion("paid_overdue_interest_penalty <", value, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyLessThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_interest_penalty <=", value, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyIn(List<Long> values) {
            addCriterion("paid_overdue_interest_penalty in", values, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyNotIn(List<Long> values) {
            addCriterion("paid_overdue_interest_penalty not in", values, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_interest_penalty between", value1, value2, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyNotBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_interest_penalty not between", value1, value2, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyIsNull() {
            addCriterion("paid_overdue_service_charge_penalty is null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyIsNotNull() {
            addCriterion("paid_overdue_service_charge_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyEqualTo(Long value) {
            addCriterion("paid_overdue_service_charge_penalty =", value, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyNotEqualTo(Long value) {
            addCriterion("paid_overdue_service_charge_penalty <>", value, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyGreaterThan(Long value) {
            addCriterion("paid_overdue_service_charge_penalty >", value, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_service_charge_penalty >=", value, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyLessThan(Long value) {
            addCriterion("paid_overdue_service_charge_penalty <", value, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyLessThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_service_charge_penalty <=", value, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyIn(List<Long> values) {
            addCriterion("paid_overdue_service_charge_penalty in", values, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyNotIn(List<Long> values) {
            addCriterion("paid_overdue_service_charge_penalty not in", values, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_service_charge_penalty between", value1, value2, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyNotBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_service_charge_penalty not between", value1, value2, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyIsNull() {
            addCriterion("paid_other_penalty is null");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyIsNotNull() {
            addCriterion("paid_other_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyEqualTo(Long value) {
            addCriterion("paid_other_penalty =", value, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyNotEqualTo(Long value) {
            addCriterion("paid_other_penalty <>", value, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyGreaterThan(Long value) {
            addCriterion("paid_other_penalty >", value, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_other_penalty >=", value, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyLessThan(Long value) {
            addCriterion("paid_other_penalty <", value, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyLessThanOrEqualTo(Long value) {
            addCriterion("paid_other_penalty <=", value, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyIn(List<Long> values) {
            addCriterion("paid_other_penalty in", values, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyNotIn(List<Long> values) {
            addCriterion("paid_other_penalty not in", values, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyBetween(Long value1, Long value2) {
            addCriterion("paid_other_penalty between", value1, value2, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyNotBetween(Long value1, Long value2) {
            addCriterion("paid_other_penalty not between", value1, value2, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalIsNull() {
            addCriterion("overdue_principal is null");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalIsNotNull() {
            addCriterion("overdue_principal is not null");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalEqualTo(Long value) {
            addCriterion("overdue_principal =", value, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalNotEqualTo(Long value) {
            addCriterion("overdue_principal <>", value, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalGreaterThan(Long value) {
            addCriterion("overdue_principal >", value, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalGreaterThanOrEqualTo(Long value) {
            addCriterion("overdue_principal >=", value, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalLessThan(Long value) {
            addCriterion("overdue_principal <", value, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalLessThanOrEqualTo(Long value) {
            addCriterion("overdue_principal <=", value, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalIn(List<Long> values) {
            addCriterion("overdue_principal in", values, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalNotIn(List<Long> values) {
            addCriterion("overdue_principal not in", values, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalBetween(Long value1, Long value2) {
            addCriterion("overdue_principal between", value1, value2, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalNotBetween(Long value1, Long value2) {
            addCriterion("overdue_principal not between", value1, value2, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestIsNull() {
            addCriterion("overdue_interest is null");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestIsNotNull() {
            addCriterion("overdue_interest is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestEqualTo(Long value) {
            addCriterion("overdue_interest =", value, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestNotEqualTo(Long value) {
            addCriterion("overdue_interest <>", value, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestGreaterThan(Long value) {
            addCriterion("overdue_interest >", value, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestGreaterThanOrEqualTo(Long value) {
            addCriterion("overdue_interest >=", value, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestLessThan(Long value) {
            addCriterion("overdue_interest <", value, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestLessThanOrEqualTo(Long value) {
            addCriterion("overdue_interest <=", value, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestIn(List<Long> values) {
            addCriterion("overdue_interest in", values, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestNotIn(List<Long> values) {
            addCriterion("overdue_interest not in", values, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestBetween(Long value1, Long value2) {
            addCriterion("overdue_interest between", value1, value2, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestNotBetween(Long value1, Long value2) {
            addCriterion("overdue_interest not between", value1, value2, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeIsNull() {
            addCriterion("overdue_service_charge is null");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeIsNotNull() {
            addCriterion("overdue_service_charge is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeEqualTo(Long value) {
            addCriterion("overdue_service_charge =", value, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeNotEqualTo(Long value) {
            addCriterion("overdue_service_charge <>", value, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeGreaterThan(Long value) {
            addCriterion("overdue_service_charge >", value, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("overdue_service_charge >=", value, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeLessThan(Long value) {
            addCriterion("overdue_service_charge <", value, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeLessThanOrEqualTo(Long value) {
            addCriterion("overdue_service_charge <=", value, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeIn(List<Long> values) {
            addCriterion("overdue_service_charge in", values, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeNotIn(List<Long> values) {
            addCriterion("overdue_service_charge not in", values, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeBetween(Long value1, Long value2) {
            addCriterion("overdue_service_charge between", value1, value2, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeNotBetween(Long value1, Long value2) {
            addCriterion("overdue_service_charge not between", value1, value2, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalPenaltyIsNull() {
            addCriterion("overdue_principal_penalty is null");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalPenaltyIsNotNull() {
            addCriterion("overdue_principal_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalPenaltyEqualTo(Long value) {
            addCriterion("overdue_principal_penalty =", value, "overduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalPenaltyNotEqualTo(Long value) {
            addCriterion("overdue_principal_penalty <>", value, "overduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalPenaltyGreaterThan(Long value) {
            addCriterion("overdue_principal_penalty >", value, "overduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalPenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("overdue_principal_penalty >=", value, "overduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalPenaltyLessThan(Long value) {
            addCriterion("overdue_principal_penalty <", value, "overduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalPenaltyLessThanOrEqualTo(Long value) {
            addCriterion("overdue_principal_penalty <=", value, "overduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalPenaltyIn(List<Long> values) {
            addCriterion("overdue_principal_penalty in", values, "overduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalPenaltyNotIn(List<Long> values) {
            addCriterion("overdue_principal_penalty not in", values, "overduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalPenaltyBetween(Long value1, Long value2) {
            addCriterion("overdue_principal_penalty between", value1, value2, "overduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalPenaltyNotBetween(Long value1, Long value2) {
            addCriterion("overdue_principal_penalty not between", value1, value2, "overduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestPenaltyIsNull() {
            addCriterion("overdue_interest_penalty is null");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestPenaltyIsNotNull() {
            addCriterion("overdue_interest_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestPenaltyEqualTo(Long value) {
            addCriterion("overdue_interest_penalty =", value, "overdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestPenaltyNotEqualTo(Long value) {
            addCriterion("overdue_interest_penalty <>", value, "overdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestPenaltyGreaterThan(Long value) {
            addCriterion("overdue_interest_penalty >", value, "overdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestPenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("overdue_interest_penalty >=", value, "overdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestPenaltyLessThan(Long value) {
            addCriterion("overdue_interest_penalty <", value, "overdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestPenaltyLessThanOrEqualTo(Long value) {
            addCriterion("overdue_interest_penalty <=", value, "overdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestPenaltyIn(List<Long> values) {
            addCriterion("overdue_interest_penalty in", values, "overdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestPenaltyNotIn(List<Long> values) {
            addCriterion("overdue_interest_penalty not in", values, "overdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestPenaltyBetween(Long value1, Long value2) {
            addCriterion("overdue_interest_penalty between", value1, value2, "overdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestPenaltyNotBetween(Long value1, Long value2) {
            addCriterion("overdue_interest_penalty not between", value1, value2, "overdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargePenaltyIsNull() {
            addCriterion("overdue_service_charge_penalty is null");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargePenaltyIsNotNull() {
            addCriterion("overdue_service_charge_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargePenaltyEqualTo(Long value) {
            addCriterion("overdue_service_charge_penalty =", value, "overdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargePenaltyNotEqualTo(Long value) {
            addCriterion("overdue_service_charge_penalty <>", value, "overdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargePenaltyGreaterThan(Long value) {
            addCriterion("overdue_service_charge_penalty >", value, "overdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargePenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("overdue_service_charge_penalty >=", value, "overdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargePenaltyLessThan(Long value) {
            addCriterion("overdue_service_charge_penalty <", value, "overdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargePenaltyLessThanOrEqualTo(Long value) {
            addCriterion("overdue_service_charge_penalty <=", value, "overdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargePenaltyIn(List<Long> values) {
            addCriterion("overdue_service_charge_penalty in", values, "overdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargePenaltyNotIn(List<Long> values) {
            addCriterion("overdue_service_charge_penalty not in", values, "overdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargePenaltyBetween(Long value1, Long value2) {
            addCriterion("overdue_service_charge_penalty between", value1, value2, "overdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargePenaltyNotBetween(Long value1, Long value2) {
            addCriterion("overdue_service_charge_penalty not between", value1, value2, "overdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andOtherPenaltyIsNull() {
            addCriterion("other_penalty is null");
            return (Criteria) this;
        }

        public Criteria andOtherPenaltyIsNotNull() {
            addCriterion("other_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andOtherPenaltyEqualTo(Long value) {
            addCriterion("other_penalty =", value, "otherPenalty");
            return (Criteria) this;
        }

        public Criteria andOtherPenaltyNotEqualTo(Long value) {
            addCriterion("other_penalty <>", value, "otherPenalty");
            return (Criteria) this;
        }

        public Criteria andOtherPenaltyGreaterThan(Long value) {
            addCriterion("other_penalty >", value, "otherPenalty");
            return (Criteria) this;
        }

        public Criteria andOtherPenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("other_penalty >=", value, "otherPenalty");
            return (Criteria) this;
        }

        public Criteria andOtherPenaltyLessThan(Long value) {
            addCriterion("other_penalty <", value, "otherPenalty");
            return (Criteria) this;
        }

        public Criteria andOtherPenaltyLessThanOrEqualTo(Long value) {
            addCriterion("other_penalty <=", value, "otherPenalty");
            return (Criteria) this;
        }

        public Criteria andOtherPenaltyIn(List<Long> values) {
            addCriterion("other_penalty in", values, "otherPenalty");
            return (Criteria) this;
        }

        public Criteria andOtherPenaltyNotIn(List<Long> values) {
            addCriterion("other_penalty not in", values, "otherPenalty");
            return (Criteria) this;
        }

        public Criteria andOtherPenaltyBetween(Long value1, Long value2) {
            addCriterion("other_penalty between", value1, value2, "otherPenalty");
            return (Criteria) this;
        }

        public Criteria andOtherPenaltyNotBetween(Long value1, Long value2) {
            addCriterion("other_penalty not between", value1, value2, "otherPenalty");
            return (Criteria) this;
        }

        public Criteria andFactorIdIsNull() {
            addCriterion("factor_id is null");
            return (Criteria) this;
        }

        public Criteria andFactorIdIsNotNull() {
            addCriterion("factor_id is not null");
            return (Criteria) this;
        }

        public Criteria andFactorIdEqualTo(String value) {
            addCriterion("factor_id =", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdNotEqualTo(String value) {
            addCriterion("factor_id <>", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdGreaterThan(String value) {
            addCriterion("factor_id >", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdGreaterThanOrEqualTo(String value) {
            addCriterion("factor_id >=", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdLessThan(String value) {
            addCriterion("factor_id <", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdLessThanOrEqualTo(String value) {
            addCriterion("factor_id <=", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdLike(String value) {
            addCriterion("factor_id like", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdNotLike(String value) {
            addCriterion("factor_id not like", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdIn(List<String> values) {
            addCriterion("factor_id in", values, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdNotIn(List<String> values) {
            addCriterion("factor_id not in", values, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdBetween(String value1, String value2) {
            addCriterion("factor_id between", value1, value2, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdNotBetween(String value1, String value2) {
            addCriterion("factor_id not between", value1, value2, "factorId");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysIsNull() {
            addCriterion("extension_days is null");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysIsNotNull() {
            addCriterion("extension_days is not null");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysEqualTo(Integer value) {
            addCriterion("extension_days =", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysNotEqualTo(Integer value) {
            addCriterion("extension_days <>", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysGreaterThan(Integer value) {
            addCriterion("extension_days >", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("extension_days >=", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysLessThan(Integer value) {
            addCriterion("extension_days <", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysLessThanOrEqualTo(Integer value) {
            addCriterion("extension_days <=", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysIn(List<Integer> values) {
            addCriterion("extension_days in", values, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysNotIn(List<Integer> values) {
            addCriterion("extension_days not in", values, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysBetween(Integer value1, Integer value2) {
            addCriterion("extension_days between", value1, value2, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("extension_days not between", value1, value2, "extensionDays");
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