package com.cana.asset.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoanInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public LoanInfoExample() {
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

        public Criteria andCounterpartyIdIsNull() {
            addCriterion("counterparty_id is null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdIsNotNull() {
            addCriterion("counterparty_id is not null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdEqualTo(String value) {
            addCriterion("counterparty_id =", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdNotEqualTo(String value) {
            addCriterion("counterparty_id <>", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdGreaterThan(String value) {
            addCriterion("counterparty_id >", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdGreaterThanOrEqualTo(String value) {
            addCriterion("counterparty_id >=", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdLessThan(String value) {
            addCriterion("counterparty_id <", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdLessThanOrEqualTo(String value) {
            addCriterion("counterparty_id <=", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdLike(String value) {
            addCriterion("counterparty_id like", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdNotLike(String value) {
            addCriterion("counterparty_id not like", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdIn(List<String> values) {
            addCriterion("counterparty_id in", values, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdNotIn(List<String> values) {
            addCriterion("counterparty_id not in", values, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdBetween(String value1, String value2) {
            addCriterion("counterparty_id between", value1, value2, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdNotBetween(String value1, String value2) {
            addCriterion("counterparty_id not between", value1, value2, "counterpartyId");
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

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
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

        public Criteria andLoanPeriodIsNull() {
            addCriterion("loan_period is null");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodIsNotNull() {
            addCriterion("loan_period is not null");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodEqualTo(Integer value) {
            addCriterion("loan_period =", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodNotEqualTo(Integer value) {
            addCriterion("loan_period <>", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodGreaterThan(Integer value) {
            addCriterion("loan_period >", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("loan_period >=", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLessThan(Integer value) {
            addCriterion("loan_period <", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("loan_period <=", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodIn(List<Integer> values) {
            addCriterion("loan_period in", values, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodNotIn(List<Integer> values) {
            addCriterion("loan_period not in", values, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodBetween(Integer value1, Integer value2) {
            addCriterion("loan_period between", value1, value2, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("loan_period not between", value1, value2, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andDayCountConventionIsNull() {
            addCriterion("day_count_convention is null");
            return (Criteria) this;
        }

        public Criteria andDayCountConventionIsNotNull() {
            addCriterion("day_count_convention is not null");
            return (Criteria) this;
        }

        public Criteria andDayCountConventionEqualTo(Integer value) {
            addCriterion("day_count_convention =", value, "dayCountConvention");
            return (Criteria) this;
        }

        public Criteria andDayCountConventionNotEqualTo(Integer value) {
            addCriterion("day_count_convention <>", value, "dayCountConvention");
            return (Criteria) this;
        }

        public Criteria andDayCountConventionGreaterThan(Integer value) {
            addCriterion("day_count_convention >", value, "dayCountConvention");
            return (Criteria) this;
        }

        public Criteria andDayCountConventionGreaterThanOrEqualTo(Integer value) {
            addCriterion("day_count_convention >=", value, "dayCountConvention");
            return (Criteria) this;
        }

        public Criteria andDayCountConventionLessThan(Integer value) {
            addCriterion("day_count_convention <", value, "dayCountConvention");
            return (Criteria) this;
        }

        public Criteria andDayCountConventionLessThanOrEqualTo(Integer value) {
            addCriterion("day_count_convention <=", value, "dayCountConvention");
            return (Criteria) this;
        }

        public Criteria andDayCountConventionIn(List<Integer> values) {
            addCriterion("day_count_convention in", values, "dayCountConvention");
            return (Criteria) this;
        }

        public Criteria andDayCountConventionNotIn(List<Integer> values) {
            addCriterion("day_count_convention not in", values, "dayCountConvention");
            return (Criteria) this;
        }

        public Criteria andDayCountConventionBetween(Integer value1, Integer value2) {
            addCriterion("day_count_convention between", value1, value2, "dayCountConvention");
            return (Criteria) this;
        }

        public Criteria andDayCountConventionNotBetween(Integer value1, Integer value2) {
            addCriterion("day_count_convention not between", value1, value2, "dayCountConvention");
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

        public Criteria andForwardDaysIsNull() {
            addCriterion("forward_days is null");
            return (Criteria) this;
        }

        public Criteria andForwardDaysIsNotNull() {
            addCriterion("forward_days is not null");
            return (Criteria) this;
        }

        public Criteria andForwardDaysEqualTo(Integer value) {
            addCriterion("forward_days =", value, "forwardDays");
            return (Criteria) this;
        }

        public Criteria andForwardDaysNotEqualTo(Integer value) {
            addCriterion("forward_days <>", value, "forwardDays");
            return (Criteria) this;
        }

        public Criteria andForwardDaysGreaterThan(Integer value) {
            addCriterion("forward_days >", value, "forwardDays");
            return (Criteria) this;
        }

        public Criteria andForwardDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("forward_days >=", value, "forwardDays");
            return (Criteria) this;
        }

        public Criteria andForwardDaysLessThan(Integer value) {
            addCriterion("forward_days <", value, "forwardDays");
            return (Criteria) this;
        }

        public Criteria andForwardDaysLessThanOrEqualTo(Integer value) {
            addCriterion("forward_days <=", value, "forwardDays");
            return (Criteria) this;
        }

        public Criteria andForwardDaysIn(List<Integer> values) {
            addCriterion("forward_days in", values, "forwardDays");
            return (Criteria) this;
        }

        public Criteria andForwardDaysNotIn(List<Integer> values) {
            addCriterion("forward_days not in", values, "forwardDays");
            return (Criteria) this;
        }

        public Criteria andForwardDaysBetween(Integer value1, Integer value2) {
            addCriterion("forward_days between", value1, value2, "forwardDays");
            return (Criteria) this;
        }

        public Criteria andForwardDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("forward_days not between", value1, value2, "forwardDays");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysIsNull() {
            addCriterion("overdue_days is null");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysIsNotNull() {
            addCriterion("overdue_days is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysEqualTo(Integer value) {
            addCriterion("overdue_days =", value, "overdueDays");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysNotEqualTo(Integer value) {
            addCriterion("overdue_days <>", value, "overdueDays");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysGreaterThan(Integer value) {
            addCriterion("overdue_days >", value, "overdueDays");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("overdue_days >=", value, "overdueDays");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysLessThan(Integer value) {
            addCriterion("overdue_days <", value, "overdueDays");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysLessThanOrEqualTo(Integer value) {
            addCriterion("overdue_days <=", value, "overdueDays");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysIn(List<Integer> values) {
            addCriterion("overdue_days in", values, "overdueDays");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysNotIn(List<Integer> values) {
            addCriterion("overdue_days not in", values, "overdueDays");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysBetween(Integer value1, Integer value2) {
            addCriterion("overdue_days between", value1, value2, "overdueDays");
            return (Criteria) this;
        }

        public Criteria andOverdueDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("overdue_days not between", value1, value2, "overdueDays");
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