package com.cana.yundaex.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YundaexLoanInfoRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public YundaexLoanInfoRecordExample() {
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

        public Criteria andBusinessContractNoIsNull() {
            addCriterion("business_contract_no is null");
            return (Criteria) this;
        }

        public Criteria andBusinessContractNoIsNotNull() {
            addCriterion("business_contract_no is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessContractNoEqualTo(String value) {
            addCriterion("business_contract_no =", value, "businessContractNo");
            return (Criteria) this;
        }

        public Criteria andBusinessContractNoNotEqualTo(String value) {
            addCriterion("business_contract_no <>", value, "businessContractNo");
            return (Criteria) this;
        }

        public Criteria andBusinessContractNoGreaterThan(String value) {
            addCriterion("business_contract_no >", value, "businessContractNo");
            return (Criteria) this;
        }

        public Criteria andBusinessContractNoGreaterThanOrEqualTo(String value) {
            addCriterion("business_contract_no >=", value, "businessContractNo");
            return (Criteria) this;
        }

        public Criteria andBusinessContractNoLessThan(String value) {
            addCriterion("business_contract_no <", value, "businessContractNo");
            return (Criteria) this;
        }

        public Criteria andBusinessContractNoLessThanOrEqualTo(String value) {
            addCriterion("business_contract_no <=", value, "businessContractNo");
            return (Criteria) this;
        }

        public Criteria andBusinessContractNoLike(String value) {
            addCriterion("business_contract_no like", value, "businessContractNo");
            return (Criteria) this;
        }

        public Criteria andBusinessContractNoNotLike(String value) {
            addCriterion("business_contract_no not like", value, "businessContractNo");
            return (Criteria) this;
        }

        public Criteria andBusinessContractNoIn(List<String> values) {
            addCriterion("business_contract_no in", values, "businessContractNo");
            return (Criteria) this;
        }

        public Criteria andBusinessContractNoNotIn(List<String> values) {
            addCriterion("business_contract_no not in", values, "businessContractNo");
            return (Criteria) this;
        }

        public Criteria andBusinessContractNoBetween(String value1, String value2) {
            addCriterion("business_contract_no between", value1, value2, "businessContractNo");
            return (Criteria) this;
        }

        public Criteria andBusinessContractNoNotBetween(String value1, String value2) {
            addCriterion("business_contract_no not between", value1, value2, "businessContractNo");
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

        public Criteria andFactorCompanyIsNull() {
            addCriterion("factor_company is null");
            return (Criteria) this;
        }

        public Criteria andFactorCompanyIsNotNull() {
            addCriterion("factor_company is not null");
            return (Criteria) this;
        }

        public Criteria andFactorCompanyEqualTo(String value) {
            addCriterion("factor_company =", value, "factorCompany");
            return (Criteria) this;
        }

        public Criteria andFactorCompanyNotEqualTo(String value) {
            addCriterion("factor_company <>", value, "factorCompany");
            return (Criteria) this;
        }

        public Criteria andFactorCompanyGreaterThan(String value) {
            addCriterion("factor_company >", value, "factorCompany");
            return (Criteria) this;
        }

        public Criteria andFactorCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("factor_company >=", value, "factorCompany");
            return (Criteria) this;
        }

        public Criteria andFactorCompanyLessThan(String value) {
            addCriterion("factor_company <", value, "factorCompany");
            return (Criteria) this;
        }

        public Criteria andFactorCompanyLessThanOrEqualTo(String value) {
            addCriterion("factor_company <=", value, "factorCompany");
            return (Criteria) this;
        }

        public Criteria andFactorCompanyLike(String value) {
            addCriterion("factor_company like", value, "factorCompany");
            return (Criteria) this;
        }

        public Criteria andFactorCompanyNotLike(String value) {
            addCriterion("factor_company not like", value, "factorCompany");
            return (Criteria) this;
        }

        public Criteria andFactorCompanyIn(List<String> values) {
            addCriterion("factor_company in", values, "factorCompany");
            return (Criteria) this;
        }

        public Criteria andFactorCompanyNotIn(List<String> values) {
            addCriterion("factor_company not in", values, "factorCompany");
            return (Criteria) this;
        }

        public Criteria andFactorCompanyBetween(String value1, String value2) {
            addCriterion("factor_company between", value1, value2, "factorCompany");
            return (Criteria) this;
        }

        public Criteria andFactorCompanyNotBetween(String value1, String value2) {
            addCriterion("factor_company not between", value1, value2, "factorCompany");
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

        public Criteria andVoucherNoIsNull() {
            addCriterion("voucher_no is null");
            return (Criteria) this;
        }

        public Criteria andVoucherNoIsNotNull() {
            addCriterion("voucher_no is not null");
            return (Criteria) this;
        }

        public Criteria andVoucherNoEqualTo(String value) {
            addCriterion("voucher_no =", value, "voucherNo");
            return (Criteria) this;
        }

        public Criteria andVoucherNoNotEqualTo(String value) {
            addCriterion("voucher_no <>", value, "voucherNo");
            return (Criteria) this;
        }

        public Criteria andVoucherNoGreaterThan(String value) {
            addCriterion("voucher_no >", value, "voucherNo");
            return (Criteria) this;
        }

        public Criteria andVoucherNoGreaterThanOrEqualTo(String value) {
            addCriterion("voucher_no >=", value, "voucherNo");
            return (Criteria) this;
        }

        public Criteria andVoucherNoLessThan(String value) {
            addCriterion("voucher_no <", value, "voucherNo");
            return (Criteria) this;
        }

        public Criteria andVoucherNoLessThanOrEqualTo(String value) {
            addCriterion("voucher_no <=", value, "voucherNo");
            return (Criteria) this;
        }

        public Criteria andVoucherNoLike(String value) {
            addCriterion("voucher_no like", value, "voucherNo");
            return (Criteria) this;
        }

        public Criteria andVoucherNoNotLike(String value) {
            addCriterion("voucher_no not like", value, "voucherNo");
            return (Criteria) this;
        }

        public Criteria andVoucherNoIn(List<String> values) {
            addCriterion("voucher_no in", values, "voucherNo");
            return (Criteria) this;
        }

        public Criteria andVoucherNoNotIn(List<String> values) {
            addCriterion("voucher_no not in", values, "voucherNo");
            return (Criteria) this;
        }

        public Criteria andVoucherNoBetween(String value1, String value2) {
            addCriterion("voucher_no between", value1, value2, "voucherNo");
            return (Criteria) this;
        }

        public Criteria andVoucherNoNotBetween(String value1, String value2) {
            addCriterion("voucher_no not between", value1, value2, "voucherNo");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIsNull() {
            addCriterion("business_product is null");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIsNotNull() {
            addCriterion("business_product is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessProductEqualTo(String value) {
            addCriterion("business_product =", value, "businessProduct");
            return (Criteria) this;
        }

        public Criteria andBusinessProductNotEqualTo(String value) {
            addCriterion("business_product <>", value, "businessProduct");
            return (Criteria) this;
        }

        public Criteria andBusinessProductGreaterThan(String value) {
            addCriterion("business_product >", value, "businessProduct");
            return (Criteria) this;
        }

        public Criteria andBusinessProductGreaterThanOrEqualTo(String value) {
            addCriterion("business_product >=", value, "businessProduct");
            return (Criteria) this;
        }

        public Criteria andBusinessProductLessThan(String value) {
            addCriterion("business_product <", value, "businessProduct");
            return (Criteria) this;
        }

        public Criteria andBusinessProductLessThanOrEqualTo(String value) {
            addCriterion("business_product <=", value, "businessProduct");
            return (Criteria) this;
        }

        public Criteria andBusinessProductLike(String value) {
            addCriterion("business_product like", value, "businessProduct");
            return (Criteria) this;
        }

        public Criteria andBusinessProductNotLike(String value) {
            addCriterion("business_product not like", value, "businessProduct");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIn(List<String> values) {
            addCriterion("business_product in", values, "businessProduct");
            return (Criteria) this;
        }

        public Criteria andBusinessProductNotIn(List<String> values) {
            addCriterion("business_product not in", values, "businessProduct");
            return (Criteria) this;
        }

        public Criteria andBusinessProductBetween(String value1, String value2) {
            addCriterion("business_product between", value1, value2, "businessProduct");
            return (Criteria) this;
        }

        public Criteria andBusinessProductNotBetween(String value1, String value2) {
            addCriterion("business_product not between", value1, value2, "businessProduct");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdIsNull() {
            addCriterion("business_product_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdIsNotNull() {
            addCriterion("business_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdEqualTo(String value) {
            addCriterion("business_product_id =", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdNotEqualTo(String value) {
            addCriterion("business_product_id <>", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdGreaterThan(String value) {
            addCriterion("business_product_id >", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("business_product_id >=", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdLessThan(String value) {
            addCriterion("business_product_id <", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdLessThanOrEqualTo(String value) {
            addCriterion("business_product_id <=", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdLike(String value) {
            addCriterion("business_product_id like", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdNotLike(String value) {
            addCriterion("business_product_id not like", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdIn(List<String> values) {
            addCriterion("business_product_id in", values, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdNotIn(List<String> values) {
            addCriterion("business_product_id not in", values, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdBetween(String value1, String value2) {
            addCriterion("business_product_id between", value1, value2, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdNotBetween(String value1, String value2) {
            addCriterion("business_product_id not between", value1, value2, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andReceivablesAmountIsNull() {
            addCriterion("receivables_amount is null");
            return (Criteria) this;
        }

        public Criteria andReceivablesAmountIsNotNull() {
            addCriterion("receivables_amount is not null");
            return (Criteria) this;
        }

        public Criteria andReceivablesAmountEqualTo(Long value) {
            addCriterion("receivables_amount =", value, "receivablesAmount");
            return (Criteria) this;
        }

        public Criteria andReceivablesAmountNotEqualTo(Long value) {
            addCriterion("receivables_amount <>", value, "receivablesAmount");
            return (Criteria) this;
        }

        public Criteria andReceivablesAmountGreaterThan(Long value) {
            addCriterion("receivables_amount >", value, "receivablesAmount");
            return (Criteria) this;
        }

        public Criteria andReceivablesAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("receivables_amount >=", value, "receivablesAmount");
            return (Criteria) this;
        }

        public Criteria andReceivablesAmountLessThan(Long value) {
            addCriterion("receivables_amount <", value, "receivablesAmount");
            return (Criteria) this;
        }

        public Criteria andReceivablesAmountLessThanOrEqualTo(Long value) {
            addCriterion("receivables_amount <=", value, "receivablesAmount");
            return (Criteria) this;
        }

        public Criteria andReceivablesAmountIn(List<Long> values) {
            addCriterion("receivables_amount in", values, "receivablesAmount");
            return (Criteria) this;
        }

        public Criteria andReceivablesAmountNotIn(List<Long> values) {
            addCriterion("receivables_amount not in", values, "receivablesAmount");
            return (Criteria) this;
        }

        public Criteria andReceivablesAmountBetween(Long value1, Long value2) {
            addCriterion("receivables_amount between", value1, value2, "receivablesAmount");
            return (Criteria) this;
        }

        public Criteria andReceivablesAmountNotBetween(Long value1, Long value2) {
            addCriterion("receivables_amount not between", value1, value2, "receivablesAmount");
            return (Criteria) this;
        }

        public Criteria andReceivablesBalanceIsNull() {
            addCriterion("receivables_balance is null");
            return (Criteria) this;
        }

        public Criteria andReceivablesBalanceIsNotNull() {
            addCriterion("receivables_balance is not null");
            return (Criteria) this;
        }

        public Criteria andReceivablesBalanceEqualTo(Long value) {
            addCriterion("receivables_balance =", value, "receivablesBalance");
            return (Criteria) this;
        }

        public Criteria andReceivablesBalanceNotEqualTo(Long value) {
            addCriterion("receivables_balance <>", value, "receivablesBalance");
            return (Criteria) this;
        }

        public Criteria andReceivablesBalanceGreaterThan(Long value) {
            addCriterion("receivables_balance >", value, "receivablesBalance");
            return (Criteria) this;
        }

        public Criteria andReceivablesBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("receivables_balance >=", value, "receivablesBalance");
            return (Criteria) this;
        }

        public Criteria andReceivablesBalanceLessThan(Long value) {
            addCriterion("receivables_balance <", value, "receivablesBalance");
            return (Criteria) this;
        }

        public Criteria andReceivablesBalanceLessThanOrEqualTo(Long value) {
            addCriterion("receivables_balance <=", value, "receivablesBalance");
            return (Criteria) this;
        }

        public Criteria andReceivablesBalanceIn(List<Long> values) {
            addCriterion("receivables_balance in", values, "receivablesBalance");
            return (Criteria) this;
        }

        public Criteria andReceivablesBalanceNotIn(List<Long> values) {
            addCriterion("receivables_balance not in", values, "receivablesBalance");
            return (Criteria) this;
        }

        public Criteria andReceivablesBalanceBetween(Long value1, Long value2) {
            addCriterion("receivables_balance between", value1, value2, "receivablesBalance");
            return (Criteria) this;
        }

        public Criteria andReceivablesBalanceNotBetween(Long value1, Long value2) {
            addCriterion("receivables_balance not between", value1, value2, "receivablesBalance");
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

        public Criteria andInterestRateIsNull() {
            addCriterion("interest_rate is null");
            return (Criteria) this;
        }

        public Criteria andInterestRateIsNotNull() {
            addCriterion("interest_rate is not null");
            return (Criteria) this;
        }

        public Criteria andInterestRateEqualTo(BigDecimal value) {
            addCriterion("interest_rate =", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateNotEqualTo(BigDecimal value) {
            addCriterion("interest_rate <>", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateGreaterThan(BigDecimal value) {
            addCriterion("interest_rate >", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("interest_rate >=", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateLessThan(BigDecimal value) {
            addCriterion("interest_rate <", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("interest_rate <=", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateIn(List<BigDecimal> values) {
            addCriterion("interest_rate in", values, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateNotIn(List<BigDecimal> values) {
            addCriterion("interest_rate not in", values, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("interest_rate between", value1, value2, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("interest_rate not between", value1, value2, "interestRate");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdIsNull() {
            addCriterion("account_supervision_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdIsNotNull() {
            addCriterion("account_supervision_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdEqualTo(String value) {
            addCriterion("account_supervision_id =", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdNotEqualTo(String value) {
            addCriterion("account_supervision_id <>", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdGreaterThan(String value) {
            addCriterion("account_supervision_id >", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdGreaterThanOrEqualTo(String value) {
            addCriterion("account_supervision_id >=", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdLessThan(String value) {
            addCriterion("account_supervision_id <", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdLessThanOrEqualTo(String value) {
            addCriterion("account_supervision_id <=", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdLike(String value) {
            addCriterion("account_supervision_id like", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdNotLike(String value) {
            addCriterion("account_supervision_id not like", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdIn(List<String> values) {
            addCriterion("account_supervision_id in", values, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdNotIn(List<String> values) {
            addCriterion("account_supervision_id not in", values, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdBetween(String value1, String value2) {
            addCriterion("account_supervision_id between", value1, value2, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdNotBetween(String value1, String value2) {
            addCriterion("account_supervision_id not between", value1, value2, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNull() {
            addCriterion("account_no is null");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNotNull() {
            addCriterion("account_no is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNoEqualTo(String value) {
            addCriterion("account_no =", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotEqualTo(String value) {
            addCriterion("account_no <>", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThan(String value) {
            addCriterion("account_no >", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("account_no >=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThan(String value) {
            addCriterion("account_no <", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThanOrEqualTo(String value) {
            addCriterion("account_no <=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLike(String value) {
            addCriterion("account_no like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotLike(String value) {
            addCriterion("account_no not like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoIn(List<String> values) {
            addCriterion("account_no in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotIn(List<String> values) {
            addCriterion("account_no not in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoBetween(String value1, String value2) {
            addCriterion("account_no between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotBetween(String value1, String value2) {
            addCriterion("account_no not between", value1, value2, "accountNo");
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

        public Criteria andRepaymentMethodIsNull() {
            addCriterion("repayment_method is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodIsNotNull() {
            addCriterion("repayment_method is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodEqualTo(String value) {
            addCriterion("repayment_method =", value, "repaymentMethod");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodNotEqualTo(String value) {
            addCriterion("repayment_method <>", value, "repaymentMethod");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodGreaterThan(String value) {
            addCriterion("repayment_method >", value, "repaymentMethod");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodGreaterThanOrEqualTo(String value) {
            addCriterion("repayment_method >=", value, "repaymentMethod");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodLessThan(String value) {
            addCriterion("repayment_method <", value, "repaymentMethod");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodLessThanOrEqualTo(String value) {
            addCriterion("repayment_method <=", value, "repaymentMethod");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodLike(String value) {
            addCriterion("repayment_method like", value, "repaymentMethod");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodNotLike(String value) {
            addCriterion("repayment_method not like", value, "repaymentMethod");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodIn(List<String> values) {
            addCriterion("repayment_method in", values, "repaymentMethod");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodNotIn(List<String> values) {
            addCriterion("repayment_method not in", values, "repaymentMethod");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodBetween(String value1, String value2) {
            addCriterion("repayment_method between", value1, value2, "repaymentMethod");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodNotBetween(String value1, String value2) {
            addCriterion("repayment_method not between", value1, value2, "repaymentMethod");
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

        public Criteria andLoanPeriodIsNull() {
            addCriterion("loan_period is null");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodIsNotNull() {
            addCriterion("loan_period is not null");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodEqualTo(String value) {
            addCriterion("loan_period =", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodNotEqualTo(String value) {
            addCriterion("loan_period <>", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodGreaterThan(String value) {
            addCriterion("loan_period >", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodGreaterThanOrEqualTo(String value) {
            addCriterion("loan_period >=", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLessThan(String value) {
            addCriterion("loan_period <", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLessThanOrEqualTo(String value) {
            addCriterion("loan_period <=", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLike(String value) {
            addCriterion("loan_period like", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodNotLike(String value) {
            addCriterion("loan_period not like", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodIn(List<String> values) {
            addCriterion("loan_period in", values, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodNotIn(List<String> values) {
            addCriterion("loan_period not in", values, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodBetween(String value1, String value2) {
            addCriterion("loan_period between", value1, value2, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodNotBetween(String value1, String value2) {
            addCriterion("loan_period not between", value1, value2, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUnitIsNull() {
            addCriterion("loan_period_unit is null");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUnitIsNotNull() {
            addCriterion("loan_period_unit is not null");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUnitEqualTo(String value) {
            addCriterion("loan_period_unit =", value, "loanPeriodUnit");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUnitNotEqualTo(String value) {
            addCriterion("loan_period_unit <>", value, "loanPeriodUnit");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUnitGreaterThan(String value) {
            addCriterion("loan_period_unit >", value, "loanPeriodUnit");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUnitGreaterThanOrEqualTo(String value) {
            addCriterion("loan_period_unit >=", value, "loanPeriodUnit");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUnitLessThan(String value) {
            addCriterion("loan_period_unit <", value, "loanPeriodUnit");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUnitLessThanOrEqualTo(String value) {
            addCriterion("loan_period_unit <=", value, "loanPeriodUnit");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUnitLike(String value) {
            addCriterion("loan_period_unit like", value, "loanPeriodUnit");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUnitNotLike(String value) {
            addCriterion("loan_period_unit not like", value, "loanPeriodUnit");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUnitIn(List<String> values) {
            addCriterion("loan_period_unit in", values, "loanPeriodUnit");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUnitNotIn(List<String> values) {
            addCriterion("loan_period_unit not in", values, "loanPeriodUnit");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUnitBetween(String value1, String value2) {
            addCriterion("loan_period_unit between", value1, value2, "loanPeriodUnit");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUnitNotBetween(String value1, String value2) {
            addCriterion("loan_period_unit not between", value1, value2, "loanPeriodUnit");
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

        public Criteria andInterestRateUnitIsNull() {
            addCriterion("interest_rate_unit is null");
            return (Criteria) this;
        }

        public Criteria andInterestRateUnitIsNotNull() {
            addCriterion("interest_rate_unit is not null");
            return (Criteria) this;
        }

        public Criteria andInterestRateUnitEqualTo(String value) {
            addCriterion("interest_rate_unit =", value, "interestRateUnit");
            return (Criteria) this;
        }

        public Criteria andInterestRateUnitNotEqualTo(String value) {
            addCriterion("interest_rate_unit <>", value, "interestRateUnit");
            return (Criteria) this;
        }

        public Criteria andInterestRateUnitGreaterThan(String value) {
            addCriterion("interest_rate_unit >", value, "interestRateUnit");
            return (Criteria) this;
        }

        public Criteria andInterestRateUnitGreaterThanOrEqualTo(String value) {
            addCriterion("interest_rate_unit >=", value, "interestRateUnit");
            return (Criteria) this;
        }

        public Criteria andInterestRateUnitLessThan(String value) {
            addCriterion("interest_rate_unit <", value, "interestRateUnit");
            return (Criteria) this;
        }

        public Criteria andInterestRateUnitLessThanOrEqualTo(String value) {
            addCriterion("interest_rate_unit <=", value, "interestRateUnit");
            return (Criteria) this;
        }

        public Criteria andInterestRateUnitLike(String value) {
            addCriterion("interest_rate_unit like", value, "interestRateUnit");
            return (Criteria) this;
        }

        public Criteria andInterestRateUnitNotLike(String value) {
            addCriterion("interest_rate_unit not like", value, "interestRateUnit");
            return (Criteria) this;
        }

        public Criteria andInterestRateUnitIn(List<String> values) {
            addCriterion("interest_rate_unit in", values, "interestRateUnit");
            return (Criteria) this;
        }

        public Criteria andInterestRateUnitNotIn(List<String> values) {
            addCriterion("interest_rate_unit not in", values, "interestRateUnit");
            return (Criteria) this;
        }

        public Criteria andInterestRateUnitBetween(String value1, String value2) {
            addCriterion("interest_rate_unit between", value1, value2, "interestRateUnit");
            return (Criteria) this;
        }

        public Criteria andInterestRateUnitNotBetween(String value1, String value2) {
            addCriterion("interest_rate_unit not between", value1, value2, "interestRateUnit");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqIsNull() {
            addCriterion("business_seq is null");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqIsNotNull() {
            addCriterion("business_seq is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqEqualTo(String value) {
            addCriterion("business_seq =", value, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqNotEqualTo(String value) {
            addCriterion("business_seq <>", value, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqGreaterThan(String value) {
            addCriterion("business_seq >", value, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqGreaterThanOrEqualTo(String value) {
            addCriterion("business_seq >=", value, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqLessThan(String value) {
            addCriterion("business_seq <", value, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqLessThanOrEqualTo(String value) {
            addCriterion("business_seq <=", value, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqLike(String value) {
            addCriterion("business_seq like", value, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqNotLike(String value) {
            addCriterion("business_seq not like", value, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqIn(List<String> values) {
            addCriterion("business_seq in", values, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqNotIn(List<String> values) {
            addCriterion("business_seq not in", values, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqBetween(String value1, String value2) {
            addCriterion("business_seq between", value1, value2, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqNotBetween(String value1, String value2) {
            addCriterion("business_seq not between", value1, value2, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoIsNull() {
            addCriterion("factor_transfer_in_account_no is null");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoIsNotNull() {
            addCriterion("factor_transfer_in_account_no is not null");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoEqualTo(String value) {
            addCriterion("factor_transfer_in_account_no =", value, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoNotEqualTo(String value) {
            addCriterion("factor_transfer_in_account_no <>", value, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoGreaterThan(String value) {
            addCriterion("factor_transfer_in_account_no >", value, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("factor_transfer_in_account_no >=", value, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoLessThan(String value) {
            addCriterion("factor_transfer_in_account_no <", value, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoLessThanOrEqualTo(String value) {
            addCriterion("factor_transfer_in_account_no <=", value, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoLike(String value) {
            addCriterion("factor_transfer_in_account_no like", value, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoNotLike(String value) {
            addCriterion("factor_transfer_in_account_no not like", value, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoIn(List<String> values) {
            addCriterion("factor_transfer_in_account_no in", values, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoNotIn(List<String> values) {
            addCriterion("factor_transfer_in_account_no not in", values, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoBetween(String value1, String value2) {
            addCriterion("factor_transfer_in_account_no between", value1, value2, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoNotBetween(String value1, String value2) {
            addCriterion("factor_transfer_in_account_no not between", value1, value2, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeIsNull() {
            addCriterion("deduction_time is null");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeIsNotNull() {
            addCriterion("deduction_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeEqualTo(String value) {
            addCriterion("deduction_time =", value, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeNotEqualTo(String value) {
            addCriterion("deduction_time <>", value, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeGreaterThan(String value) {
            addCriterion("deduction_time >", value, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeGreaterThanOrEqualTo(String value) {
            addCriterion("deduction_time >=", value, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeLessThan(String value) {
            addCriterion("deduction_time <", value, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeLessThanOrEqualTo(String value) {
            addCriterion("deduction_time <=", value, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeLike(String value) {
            addCriterion("deduction_time like", value, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeNotLike(String value) {
            addCriterion("deduction_time not like", value, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeIn(List<String> values) {
            addCriterion("deduction_time in", values, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeNotIn(List<String> values) {
            addCriterion("deduction_time not in", values, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeBetween(String value1, String value2) {
            addCriterion("deduction_time between", value1, value2, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeNotBetween(String value1, String value2) {
            addCriterion("deduction_time not between", value1, value2, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleIsNull() {
            addCriterion("deduction_rule is null");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleIsNotNull() {
            addCriterion("deduction_rule is not null");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleEqualTo(String value) {
            addCriterion("deduction_rule =", value, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleNotEqualTo(String value) {
            addCriterion("deduction_rule <>", value, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleGreaterThan(String value) {
            addCriterion("deduction_rule >", value, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleGreaterThanOrEqualTo(String value) {
            addCriterion("deduction_rule >=", value, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleLessThan(String value) {
            addCriterion("deduction_rule <", value, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleLessThanOrEqualTo(String value) {
            addCriterion("deduction_rule <=", value, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleLike(String value) {
            addCriterion("deduction_rule like", value, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleNotLike(String value) {
            addCriterion("deduction_rule not like", value, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleIn(List<String> values) {
            addCriterion("deduction_rule in", values, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleNotIn(List<String> values) {
            addCriterion("deduction_rule not in", values, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleBetween(String value1, String value2) {
            addCriterion("deduction_rule between", value1, value2, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleNotBetween(String value1, String value2) {
            addCriterion("deduction_rule not between", value1, value2, "deductionRule");
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

        public Criteria andExtensionRatioIsNull() {
            addCriterion("extension_ratio is null");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioIsNotNull() {
            addCriterion("extension_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioEqualTo(BigDecimal value) {
            addCriterion("extension_ratio =", value, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioNotEqualTo(BigDecimal value) {
            addCriterion("extension_ratio <>", value, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioGreaterThan(BigDecimal value) {
            addCriterion("extension_ratio >", value, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("extension_ratio >=", value, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioLessThan(BigDecimal value) {
            addCriterion("extension_ratio <", value, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("extension_ratio <=", value, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioIn(List<BigDecimal> values) {
            addCriterion("extension_ratio in", values, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioNotIn(List<BigDecimal> values) {
            addCriterion("extension_ratio not in", values, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("extension_ratio between", value1, value2, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("extension_ratio not between", value1, value2, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeMethodIsNull() {
            addCriterion("extension_charge_method is null");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeMethodIsNotNull() {
            addCriterion("extension_charge_method is not null");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeMethodEqualTo(String value) {
            addCriterion("extension_charge_method =", value, "extensionChargeMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeMethodNotEqualTo(String value) {
            addCriterion("extension_charge_method <>", value, "extensionChargeMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeMethodGreaterThan(String value) {
            addCriterion("extension_charge_method >", value, "extensionChargeMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeMethodGreaterThanOrEqualTo(String value) {
            addCriterion("extension_charge_method >=", value, "extensionChargeMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeMethodLessThan(String value) {
            addCriterion("extension_charge_method <", value, "extensionChargeMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeMethodLessThanOrEqualTo(String value) {
            addCriterion("extension_charge_method <=", value, "extensionChargeMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeMethodLike(String value) {
            addCriterion("extension_charge_method like", value, "extensionChargeMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeMethodNotLike(String value) {
            addCriterion("extension_charge_method not like", value, "extensionChargeMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeMethodIn(List<String> values) {
            addCriterion("extension_charge_method in", values, "extensionChargeMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeMethodNotIn(List<String> values) {
            addCriterion("extension_charge_method not in", values, "extensionChargeMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeMethodBetween(String value1, String value2) {
            addCriterion("extension_charge_method between", value1, value2, "extensionChargeMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeMethodNotBetween(String value1, String value2) {
            addCriterion("extension_charge_method not between", value1, value2, "extensionChargeMethod");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioIsNull() {
            addCriterion("early_repayment_charge_ratio is null");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioIsNotNull() {
            addCriterion("early_repayment_charge_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioEqualTo(BigDecimal value) {
            addCriterion("early_repayment_charge_ratio =", value, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioNotEqualTo(BigDecimal value) {
            addCriterion("early_repayment_charge_ratio <>", value, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioGreaterThan(BigDecimal value) {
            addCriterion("early_repayment_charge_ratio >", value, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("early_repayment_charge_ratio >=", value, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioLessThan(BigDecimal value) {
            addCriterion("early_repayment_charge_ratio <", value, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("early_repayment_charge_ratio <=", value, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioIn(List<BigDecimal> values) {
            addCriterion("early_repayment_charge_ratio in", values, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioNotIn(List<BigDecimal> values) {
            addCriterion("early_repayment_charge_ratio not in", values, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("early_repayment_charge_ratio between", value1, value2, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("early_repayment_charge_ratio not between", value1, value2, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateIsNull() {
            addCriterion("penalty_rate is null");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateIsNotNull() {
            addCriterion("penalty_rate is not null");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateEqualTo(BigDecimal value) {
            addCriterion("penalty_rate =", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateNotEqualTo(BigDecimal value) {
            addCriterion("penalty_rate <>", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateGreaterThan(BigDecimal value) {
            addCriterion("penalty_rate >", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("penalty_rate >=", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateLessThan(BigDecimal value) {
            addCriterion("penalty_rate <", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("penalty_rate <=", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateIn(List<BigDecimal> values) {
            addCriterion("penalty_rate in", values, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateNotIn(List<BigDecimal> values) {
            addCriterion("penalty_rate not in", values, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("penalty_rate between", value1, value2, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("penalty_rate not between", value1, value2, "penaltyRate");
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

        public Criteria andUseHolidayPolicyIsNull() {
            addCriterion("use_holiday_policy is null");
            return (Criteria) this;
        }

        public Criteria andUseHolidayPolicyIsNotNull() {
            addCriterion("use_holiday_policy is not null");
            return (Criteria) this;
        }

        public Criteria andUseHolidayPolicyEqualTo(Boolean value) {
            addCriterion("use_holiday_policy =", value, "useHolidayPolicy");
            return (Criteria) this;
        }

        public Criteria andUseHolidayPolicyNotEqualTo(Boolean value) {
            addCriterion("use_holiday_policy <>", value, "useHolidayPolicy");
            return (Criteria) this;
        }

        public Criteria andUseHolidayPolicyGreaterThan(Boolean value) {
            addCriterion("use_holiday_policy >", value, "useHolidayPolicy");
            return (Criteria) this;
        }

        public Criteria andUseHolidayPolicyGreaterThanOrEqualTo(Boolean value) {
            addCriterion("use_holiday_policy >=", value, "useHolidayPolicy");
            return (Criteria) this;
        }

        public Criteria andUseHolidayPolicyLessThan(Boolean value) {
            addCriterion("use_holiday_policy <", value, "useHolidayPolicy");
            return (Criteria) this;
        }

        public Criteria andUseHolidayPolicyLessThanOrEqualTo(Boolean value) {
            addCriterion("use_holiday_policy <=", value, "useHolidayPolicy");
            return (Criteria) this;
        }

        public Criteria andUseHolidayPolicyIn(List<Boolean> values) {
            addCriterion("use_holiday_policy in", values, "useHolidayPolicy");
            return (Criteria) this;
        }

        public Criteria andUseHolidayPolicyNotIn(List<Boolean> values) {
            addCriterion("use_holiday_policy not in", values, "useHolidayPolicy");
            return (Criteria) this;
        }

        public Criteria andUseHolidayPolicyBetween(Boolean value1, Boolean value2) {
            addCriterion("use_holiday_policy between", value1, value2, "useHolidayPolicy");
            return (Criteria) this;
        }

        public Criteria andUseHolidayPolicyNotBetween(Boolean value1, Boolean value2) {
            addCriterion("use_holiday_policy not between", value1, value2, "useHolidayPolicy");
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