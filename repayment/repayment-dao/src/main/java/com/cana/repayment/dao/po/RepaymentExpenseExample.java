package com.cana.repayment.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepaymentExpenseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public RepaymentExpenseExample() {
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

        public Criteria andExpenseSubjectIsNull() {
            addCriterion("expense_subject is null");
            return (Criteria) this;
        }

        public Criteria andExpenseSubjectIsNotNull() {
            addCriterion("expense_subject is not null");
            return (Criteria) this;
        }

        public Criteria andExpenseSubjectEqualTo(String value) {
            addCriterion("expense_subject =", value, "expenseSubject");
            return (Criteria) this;
        }

        public Criteria andExpenseSubjectNotEqualTo(String value) {
            addCriterion("expense_subject <>", value, "expenseSubject");
            return (Criteria) this;
        }

        public Criteria andExpenseSubjectGreaterThan(String value) {
            addCriterion("expense_subject >", value, "expenseSubject");
            return (Criteria) this;
        }

        public Criteria andExpenseSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("expense_subject >=", value, "expenseSubject");
            return (Criteria) this;
        }

        public Criteria andExpenseSubjectLessThan(String value) {
            addCriterion("expense_subject <", value, "expenseSubject");
            return (Criteria) this;
        }

        public Criteria andExpenseSubjectLessThanOrEqualTo(String value) {
            addCriterion("expense_subject <=", value, "expenseSubject");
            return (Criteria) this;
        }

        public Criteria andExpenseSubjectLike(String value) {
            addCriterion("expense_subject like", value, "expenseSubject");
            return (Criteria) this;
        }

        public Criteria andExpenseSubjectNotLike(String value) {
            addCriterion("expense_subject not like", value, "expenseSubject");
            return (Criteria) this;
        }

        public Criteria andExpenseSubjectIn(List<String> values) {
            addCriterion("expense_subject in", values, "expenseSubject");
            return (Criteria) this;
        }

        public Criteria andExpenseSubjectNotIn(List<String> values) {
            addCriterion("expense_subject not in", values, "expenseSubject");
            return (Criteria) this;
        }

        public Criteria andExpenseSubjectBetween(String value1, String value2) {
            addCriterion("expense_subject between", value1, value2, "expenseSubject");
            return (Criteria) this;
        }

        public Criteria andExpenseSubjectNotBetween(String value1, String value2) {
            addCriterion("expense_subject not between", value1, value2, "expenseSubject");
            return (Criteria) this;
        }

        public Criteria andChargeMethodIsNull() {
            addCriterion("charge_method is null");
            return (Criteria) this;
        }

        public Criteria andChargeMethodIsNotNull() {
            addCriterion("charge_method is not null");
            return (Criteria) this;
        }

        public Criteria andChargeMethodEqualTo(String value) {
            addCriterion("charge_method =", value, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodNotEqualTo(String value) {
            addCriterion("charge_method <>", value, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodGreaterThan(String value) {
            addCriterion("charge_method >", value, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodGreaterThanOrEqualTo(String value) {
            addCriterion("charge_method >=", value, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodLessThan(String value) {
            addCriterion("charge_method <", value, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodLessThanOrEqualTo(String value) {
            addCriterion("charge_method <=", value, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodLike(String value) {
            addCriterion("charge_method like", value, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodNotLike(String value) {
            addCriterion("charge_method not like", value, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodIn(List<String> values) {
            addCriterion("charge_method in", values, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodNotIn(List<String> values) {
            addCriterion("charge_method not in", values, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodBetween(String value1, String value2) {
            addCriterion("charge_method between", value1, value2, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodNotBetween(String value1, String value2) {
            addCriterion("charge_method not between", value1, value2, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeStandardIsNull() {
            addCriterion("charge_standard is null");
            return (Criteria) this;
        }

        public Criteria andChargeStandardIsNotNull() {
            addCriterion("charge_standard is not null");
            return (Criteria) this;
        }

        public Criteria andChargeStandardEqualTo(Long value) {
            addCriterion("charge_standard =", value, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardNotEqualTo(Long value) {
            addCriterion("charge_standard <>", value, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardGreaterThan(Long value) {
            addCriterion("charge_standard >", value, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardGreaterThanOrEqualTo(Long value) {
            addCriterion("charge_standard >=", value, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardLessThan(Long value) {
            addCriterion("charge_standard <", value, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardLessThanOrEqualTo(Long value) {
            addCriterion("charge_standard <=", value, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardIn(List<Long> values) {
            addCriterion("charge_standard in", values, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardNotIn(List<Long> values) {
            addCriterion("charge_standard not in", values, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardBetween(Long value1, Long value2) {
            addCriterion("charge_standard between", value1, value2, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardNotBetween(Long value1, Long value2) {
            addCriterion("charge_standard not between", value1, value2, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeRatioIsNull() {
            addCriterion("charge_ratio is null");
            return (Criteria) this;
        }

        public Criteria andChargeRatioIsNotNull() {
            addCriterion("charge_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andChargeRatioEqualTo(BigDecimal value) {
            addCriterion("charge_ratio =", value, "chargeRatio");
            return (Criteria) this;
        }

        public Criteria andChargeRatioNotEqualTo(BigDecimal value) {
            addCriterion("charge_ratio <>", value, "chargeRatio");
            return (Criteria) this;
        }

        public Criteria andChargeRatioGreaterThan(BigDecimal value) {
            addCriterion("charge_ratio >", value, "chargeRatio");
            return (Criteria) this;
        }

        public Criteria andChargeRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("charge_ratio >=", value, "chargeRatio");
            return (Criteria) this;
        }

        public Criteria andChargeRatioLessThan(BigDecimal value) {
            addCriterion("charge_ratio <", value, "chargeRatio");
            return (Criteria) this;
        }

        public Criteria andChargeRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("charge_ratio <=", value, "chargeRatio");
            return (Criteria) this;
        }

        public Criteria andChargeRatioIn(List<BigDecimal> values) {
            addCriterion("charge_ratio in", values, "chargeRatio");
            return (Criteria) this;
        }

        public Criteria andChargeRatioNotIn(List<BigDecimal> values) {
            addCriterion("charge_ratio not in", values, "chargeRatio");
            return (Criteria) this;
        }

        public Criteria andChargeRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("charge_ratio between", value1, value2, "chargeRatio");
            return (Criteria) this;
        }

        public Criteria andChargeRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("charge_ratio not between", value1, value2, "chargeRatio");
            return (Criteria) this;
        }

        public Criteria andChargeAmountIsNull() {
            addCriterion("charge_amount is null");
            return (Criteria) this;
        }

        public Criteria andChargeAmountIsNotNull() {
            addCriterion("charge_amount is not null");
            return (Criteria) this;
        }

        public Criteria andChargeAmountEqualTo(Long value) {
            addCriterion("charge_amount =", value, "chargeAmount");
            return (Criteria) this;
        }

        public Criteria andChargeAmountNotEqualTo(Long value) {
            addCriterion("charge_amount <>", value, "chargeAmount");
            return (Criteria) this;
        }

        public Criteria andChargeAmountGreaterThan(Long value) {
            addCriterion("charge_amount >", value, "chargeAmount");
            return (Criteria) this;
        }

        public Criteria andChargeAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("charge_amount >=", value, "chargeAmount");
            return (Criteria) this;
        }

        public Criteria andChargeAmountLessThan(Long value) {
            addCriterion("charge_amount <", value, "chargeAmount");
            return (Criteria) this;
        }

        public Criteria andChargeAmountLessThanOrEqualTo(Long value) {
            addCriterion("charge_amount <=", value, "chargeAmount");
            return (Criteria) this;
        }

        public Criteria andChargeAmountIn(List<Long> values) {
            addCriterion("charge_amount in", values, "chargeAmount");
            return (Criteria) this;
        }

        public Criteria andChargeAmountNotIn(List<Long> values) {
            addCriterion("charge_amount not in", values, "chargeAmount");
            return (Criteria) this;
        }

        public Criteria andChargeAmountBetween(Long value1, Long value2) {
            addCriterion("charge_amount between", value1, value2, "chargeAmount");
            return (Criteria) this;
        }

        public Criteria andChargeAmountNotBetween(Long value1, Long value2) {
            addCriterion("charge_amount not between", value1, value2, "chargeAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountIsNull() {
            addCriterion("repayment_amount is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountIsNotNull() {
            addCriterion("repayment_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountEqualTo(Long value) {
            addCriterion("repayment_amount =", value, "repaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountNotEqualTo(Long value) {
            addCriterion("repayment_amount <>", value, "repaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountGreaterThan(Long value) {
            addCriterion("repayment_amount >", value, "repaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("repayment_amount >=", value, "repaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountLessThan(Long value) {
            addCriterion("repayment_amount <", value, "repaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountLessThanOrEqualTo(Long value) {
            addCriterion("repayment_amount <=", value, "repaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountIn(List<Long> values) {
            addCriterion("repayment_amount in", values, "repaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountNotIn(List<Long> values) {
            addCriterion("repayment_amount not in", values, "repaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountBetween(Long value1, Long value2) {
            addCriterion("repayment_amount between", value1, value2, "repaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountNotBetween(Long value1, Long value2) {
            addCriterion("repayment_amount not between", value1, value2, "repaymentAmount");
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

        public Criteria andPaidAmountIsNull() {
            addCriterion("paid_amount is null");
            return (Criteria) this;
        }

        public Criteria andPaidAmountIsNotNull() {
            addCriterion("paid_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPaidAmountEqualTo(Long value) {
            addCriterion("paid_amount =", value, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountNotEqualTo(Long value) {
            addCriterion("paid_amount <>", value, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountGreaterThan(Long value) {
            addCriterion("paid_amount >", value, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_amount >=", value, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountLessThan(Long value) {
            addCriterion("paid_amount <", value, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountLessThanOrEqualTo(Long value) {
            addCriterion("paid_amount <=", value, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountIn(List<Long> values) {
            addCriterion("paid_amount in", values, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountNotIn(List<Long> values) {
            addCriterion("paid_amount not in", values, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountBetween(Long value1, Long value2) {
            addCriterion("paid_amount between", value1, value2, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountNotBetween(Long value1, Long value2) {
            addCriterion("paid_amount not between", value1, value2, "paidAmount");
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