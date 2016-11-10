package com.cana.repayment.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepaymentLoanInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public RepaymentLoanInfoExample() {
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

        public Criteria andOutCustomerIdIsNull() {
            addCriterion("out_customer_id is null");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdIsNotNull() {
            addCriterion("out_customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdEqualTo(String value) {
            addCriterion("out_customer_id =", value, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdNotEqualTo(String value) {
            addCriterion("out_customer_id <>", value, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdGreaterThan(String value) {
            addCriterion("out_customer_id >", value, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdGreaterThanOrEqualTo(String value) {
            addCriterion("out_customer_id >=", value, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdLessThan(String value) {
            addCriterion("out_customer_id <", value, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdLessThanOrEqualTo(String value) {
            addCriterion("out_customer_id <=", value, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdLike(String value) {
            addCriterion("out_customer_id like", value, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdNotLike(String value) {
            addCriterion("out_customer_id not like", value, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdIn(List<String> values) {
            addCriterion("out_customer_id in", values, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdNotIn(List<String> values) {
            addCriterion("out_customer_id not in", values, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdBetween(String value1, String value2) {
            addCriterion("out_customer_id between", value1, value2, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdNotBetween(String value1, String value2) {
            addCriterion("out_customer_id not between", value1, value2, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameIsNull() {
            addCriterion("out_customer_name is null");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameIsNotNull() {
            addCriterion("out_customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameEqualTo(String value) {
            addCriterion("out_customer_name =", value, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameNotEqualTo(String value) {
            addCriterion("out_customer_name <>", value, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameGreaterThan(String value) {
            addCriterion("out_customer_name >", value, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("out_customer_name >=", value, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameLessThan(String value) {
            addCriterion("out_customer_name <", value, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("out_customer_name <=", value, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameLike(String value) {
            addCriterion("out_customer_name like", value, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameNotLike(String value) {
            addCriterion("out_customer_name not like", value, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameIn(List<String> values) {
            addCriterion("out_customer_name in", values, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameNotIn(List<String> values) {
            addCriterion("out_customer_name not in", values, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameBetween(String value1, String value2) {
            addCriterion("out_customer_name between", value1, value2, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameNotBetween(String value1, String value2) {
            addCriterion("out_customer_name not between", value1, value2, "outCustomerName");
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

        public Criteria andPaidTotalAmountIsNull() {
            addCriterion("paid_total_amount is null");
            return (Criteria) this;
        }

        public Criteria andPaidTotalAmountIsNotNull() {
            addCriterion("paid_total_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPaidTotalAmountEqualTo(Long value) {
            addCriterion("paid_total_amount =", value, "paidTotalAmount");
            return (Criteria) this;
        }

        public Criteria andPaidTotalAmountNotEqualTo(Long value) {
            addCriterion("paid_total_amount <>", value, "paidTotalAmount");
            return (Criteria) this;
        }

        public Criteria andPaidTotalAmountGreaterThan(Long value) {
            addCriterion("paid_total_amount >", value, "paidTotalAmount");
            return (Criteria) this;
        }

        public Criteria andPaidTotalAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_total_amount >=", value, "paidTotalAmount");
            return (Criteria) this;
        }

        public Criteria andPaidTotalAmountLessThan(Long value) {
            addCriterion("paid_total_amount <", value, "paidTotalAmount");
            return (Criteria) this;
        }

        public Criteria andPaidTotalAmountLessThanOrEqualTo(Long value) {
            addCriterion("paid_total_amount <=", value, "paidTotalAmount");
            return (Criteria) this;
        }

        public Criteria andPaidTotalAmountIn(List<Long> values) {
            addCriterion("paid_total_amount in", values, "paidTotalAmount");
            return (Criteria) this;
        }

        public Criteria andPaidTotalAmountNotIn(List<Long> values) {
            addCriterion("paid_total_amount not in", values, "paidTotalAmount");
            return (Criteria) this;
        }

        public Criteria andPaidTotalAmountBetween(Long value1, Long value2) {
            addCriterion("paid_total_amount between", value1, value2, "paidTotalAmount");
            return (Criteria) this;
        }

        public Criteria andPaidTotalAmountNotBetween(Long value1, Long value2) {
            addCriterion("paid_total_amount not between", value1, value2, "paidTotalAmount");
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

        public Criteria andChangeTypeIsNull() {
            addCriterion("change_type is null");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIsNotNull() {
            addCriterion("change_type is not null");
            return (Criteria) this;
        }

        public Criteria andChangeTypeEqualTo(String value) {
            addCriterion("change_type =", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotEqualTo(String value) {
            addCriterion("change_type <>", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeGreaterThan(String value) {
            addCriterion("change_type >", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("change_type >=", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeLessThan(String value) {
            addCriterion("change_type <", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeLessThanOrEqualTo(String value) {
            addCriterion("change_type <=", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeLike(String value) {
            addCriterion("change_type like", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotLike(String value) {
            addCriterion("change_type not like", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIn(List<String> values) {
            addCriterion("change_type in", values, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotIn(List<String> values) {
            addCriterion("change_type not in", values, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeBetween(String value1, String value2) {
            addCriterion("change_type between", value1, value2, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotBetween(String value1, String value2) {
            addCriterion("change_type not between", value1, value2, "changeType");
            return (Criteria) this;
        }

        public Criteria andCurrentVersionIsNull() {
            addCriterion("current_version is null");
            return (Criteria) this;
        }

        public Criteria andCurrentVersionIsNotNull() {
            addCriterion("current_version is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentVersionEqualTo(String value) {
            addCriterion("current_version =", value, "currentVersion");
            return (Criteria) this;
        }

        public Criteria andCurrentVersionNotEqualTo(String value) {
            addCriterion("current_version <>", value, "currentVersion");
            return (Criteria) this;
        }

        public Criteria andCurrentVersionGreaterThan(String value) {
            addCriterion("current_version >", value, "currentVersion");
            return (Criteria) this;
        }

        public Criteria andCurrentVersionGreaterThanOrEqualTo(String value) {
            addCriterion("current_version >=", value, "currentVersion");
            return (Criteria) this;
        }

        public Criteria andCurrentVersionLessThan(String value) {
            addCriterion("current_version <", value, "currentVersion");
            return (Criteria) this;
        }

        public Criteria andCurrentVersionLessThanOrEqualTo(String value) {
            addCriterion("current_version <=", value, "currentVersion");
            return (Criteria) this;
        }

        public Criteria andCurrentVersionLike(String value) {
            addCriterion("current_version like", value, "currentVersion");
            return (Criteria) this;
        }

        public Criteria andCurrentVersionNotLike(String value) {
            addCriterion("current_version not like", value, "currentVersion");
            return (Criteria) this;
        }

        public Criteria andCurrentVersionIn(List<String> values) {
            addCriterion("current_version in", values, "currentVersion");
            return (Criteria) this;
        }

        public Criteria andCurrentVersionNotIn(List<String> values) {
            addCriterion("current_version not in", values, "currentVersion");
            return (Criteria) this;
        }

        public Criteria andCurrentVersionBetween(String value1, String value2) {
            addCriterion("current_version between", value1, value2, "currentVersion");
            return (Criteria) this;
        }

        public Criteria andCurrentVersionNotBetween(String value1, String value2) {
            addCriterion("current_version not between", value1, value2, "currentVersion");
            return (Criteria) this;
        }

        public Criteria andLastVersionIsNull() {
            addCriterion("last_version is null");
            return (Criteria) this;
        }

        public Criteria andLastVersionIsNotNull() {
            addCriterion("last_version is not null");
            return (Criteria) this;
        }

        public Criteria andLastVersionEqualTo(String value) {
            addCriterion("last_version =", value, "lastVersion");
            return (Criteria) this;
        }

        public Criteria andLastVersionNotEqualTo(String value) {
            addCriterion("last_version <>", value, "lastVersion");
            return (Criteria) this;
        }

        public Criteria andLastVersionGreaterThan(String value) {
            addCriterion("last_version >", value, "lastVersion");
            return (Criteria) this;
        }

        public Criteria andLastVersionGreaterThanOrEqualTo(String value) {
            addCriterion("last_version >=", value, "lastVersion");
            return (Criteria) this;
        }

        public Criteria andLastVersionLessThan(String value) {
            addCriterion("last_version <", value, "lastVersion");
            return (Criteria) this;
        }

        public Criteria andLastVersionLessThanOrEqualTo(String value) {
            addCriterion("last_version <=", value, "lastVersion");
            return (Criteria) this;
        }

        public Criteria andLastVersionLike(String value) {
            addCriterion("last_version like", value, "lastVersion");
            return (Criteria) this;
        }

        public Criteria andLastVersionNotLike(String value) {
            addCriterion("last_version not like", value, "lastVersion");
            return (Criteria) this;
        }

        public Criteria andLastVersionIn(List<String> values) {
            addCriterion("last_version in", values, "lastVersion");
            return (Criteria) this;
        }

        public Criteria andLastVersionNotIn(List<String> values) {
            addCriterion("last_version not in", values, "lastVersion");
            return (Criteria) this;
        }

        public Criteria andLastVersionBetween(String value1, String value2) {
            addCriterion("last_version between", value1, value2, "lastVersion");
            return (Criteria) this;
        }

        public Criteria andLastVersionNotBetween(String value1, String value2) {
            addCriterion("last_version not between", value1, value2, "lastVersion");
            return (Criteria) this;
        }

        public Criteria andChangeIdIsNull() {
            addCriterion("change_id is null");
            return (Criteria) this;
        }

        public Criteria andChangeIdIsNotNull() {
            addCriterion("change_id is not null");
            return (Criteria) this;
        }

        public Criteria andChangeIdEqualTo(String value) {
            addCriterion("change_id =", value, "changeId");
            return (Criteria) this;
        }

        public Criteria andChangeIdNotEqualTo(String value) {
            addCriterion("change_id <>", value, "changeId");
            return (Criteria) this;
        }

        public Criteria andChangeIdGreaterThan(String value) {
            addCriterion("change_id >", value, "changeId");
            return (Criteria) this;
        }

        public Criteria andChangeIdGreaterThanOrEqualTo(String value) {
            addCriterion("change_id >=", value, "changeId");
            return (Criteria) this;
        }

        public Criteria andChangeIdLessThan(String value) {
            addCriterion("change_id <", value, "changeId");
            return (Criteria) this;
        }

        public Criteria andChangeIdLessThanOrEqualTo(String value) {
            addCriterion("change_id <=", value, "changeId");
            return (Criteria) this;
        }

        public Criteria andChangeIdLike(String value) {
            addCriterion("change_id like", value, "changeId");
            return (Criteria) this;
        }

        public Criteria andChangeIdNotLike(String value) {
            addCriterion("change_id not like", value, "changeId");
            return (Criteria) this;
        }

        public Criteria andChangeIdIn(List<String> values) {
            addCriterion("change_id in", values, "changeId");
            return (Criteria) this;
        }

        public Criteria andChangeIdNotIn(List<String> values) {
            addCriterion("change_id not in", values, "changeId");
            return (Criteria) this;
        }

        public Criteria andChangeIdBetween(String value1, String value2) {
            addCriterion("change_id between", value1, value2, "changeId");
            return (Criteria) this;
        }

        public Criteria andChangeIdNotBetween(String value1, String value2) {
            addCriterion("change_id not between", value1, value2, "changeId");
            return (Criteria) this;
        }

        public Criteria andExtraDataIsNull() {
            addCriterion("extra_data is null");
            return (Criteria) this;
        }

        public Criteria andExtraDataIsNotNull() {
            addCriterion("extra_data is not null");
            return (Criteria) this;
        }

        public Criteria andExtraDataEqualTo(String value) {
            addCriterion("extra_data =", value, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataNotEqualTo(String value) {
            addCriterion("extra_data <>", value, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataGreaterThan(String value) {
            addCriterion("extra_data >", value, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataGreaterThanOrEqualTo(String value) {
            addCriterion("extra_data >=", value, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataLessThan(String value) {
            addCriterion("extra_data <", value, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataLessThanOrEqualTo(String value) {
            addCriterion("extra_data <=", value, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataLike(String value) {
            addCriterion("extra_data like", value, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataNotLike(String value) {
            addCriterion("extra_data not like", value, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataIn(List<String> values) {
            addCriterion("extra_data in", values, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataNotIn(List<String> values) {
            addCriterion("extra_data not in", values, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataBetween(String value1, String value2) {
            addCriterion("extra_data between", value1, value2, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataNotBetween(String value1, String value2) {
            addCriterion("extra_data not between", value1, value2, "extraData");
            return (Criteria) this;
        }

        public Criteria andActiveRepaymentStatusIsNull() {
            addCriterion("active_repayment_status is null");
            return (Criteria) this;
        }

        public Criteria andActiveRepaymentStatusIsNotNull() {
            addCriterion("active_repayment_status is not null");
            return (Criteria) this;
        }

        public Criteria andActiveRepaymentStatusEqualTo(String value) {
            addCriterion("active_repayment_status =", value, "activeRepaymentStatus");
            return (Criteria) this;
        }

        public Criteria andActiveRepaymentStatusNotEqualTo(String value) {
            addCriterion("active_repayment_status <>", value, "activeRepaymentStatus");
            return (Criteria) this;
        }

        public Criteria andActiveRepaymentStatusGreaterThan(String value) {
            addCriterion("active_repayment_status >", value, "activeRepaymentStatus");
            return (Criteria) this;
        }

        public Criteria andActiveRepaymentStatusGreaterThanOrEqualTo(String value) {
            addCriterion("active_repayment_status >=", value, "activeRepaymentStatus");
            return (Criteria) this;
        }

        public Criteria andActiveRepaymentStatusLessThan(String value) {
            addCriterion("active_repayment_status <", value, "activeRepaymentStatus");
            return (Criteria) this;
        }

        public Criteria andActiveRepaymentStatusLessThanOrEqualTo(String value) {
            addCriterion("active_repayment_status <=", value, "activeRepaymentStatus");
            return (Criteria) this;
        }

        public Criteria andActiveRepaymentStatusLike(String value) {
            addCriterion("active_repayment_status like", value, "activeRepaymentStatus");
            return (Criteria) this;
        }

        public Criteria andActiveRepaymentStatusNotLike(String value) {
            addCriterion("active_repayment_status not like", value, "activeRepaymentStatus");
            return (Criteria) this;
        }

        public Criteria andActiveRepaymentStatusIn(List<String> values) {
            addCriterion("active_repayment_status in", values, "activeRepaymentStatus");
            return (Criteria) this;
        }

        public Criteria andActiveRepaymentStatusNotIn(List<String> values) {
            addCriterion("active_repayment_status not in", values, "activeRepaymentStatus");
            return (Criteria) this;
        }

        public Criteria andActiveRepaymentStatusBetween(String value1, String value2) {
            addCriterion("active_repayment_status between", value1, value2, "activeRepaymentStatus");
            return (Criteria) this;
        }

        public Criteria andActiveRepaymentStatusNotBetween(String value1, String value2) {
            addCriterion("active_repayment_status not between", value1, value2, "activeRepaymentStatus");
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