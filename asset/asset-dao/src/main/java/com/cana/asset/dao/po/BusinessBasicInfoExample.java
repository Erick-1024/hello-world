package com.cana.asset.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessBasicInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public BusinessBasicInfoExample() {
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

        public Criteria andFactorNameIsNull() {
            addCriterion("factor_name is null");
            return (Criteria) this;
        }

        public Criteria andFactorNameIsNotNull() {
            addCriterion("factor_name is not null");
            return (Criteria) this;
        }

        public Criteria andFactorNameEqualTo(String value) {
            addCriterion("factor_name =", value, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameNotEqualTo(String value) {
            addCriterion("factor_name <>", value, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameGreaterThan(String value) {
            addCriterion("factor_name >", value, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameGreaterThanOrEqualTo(String value) {
            addCriterion("factor_name >=", value, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameLessThan(String value) {
            addCriterion("factor_name <", value, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameLessThanOrEqualTo(String value) {
            addCriterion("factor_name <=", value, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameLike(String value) {
            addCriterion("factor_name like", value, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameNotLike(String value) {
            addCriterion("factor_name not like", value, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameIn(List<String> values) {
            addCriterion("factor_name in", values, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameNotIn(List<String> values) {
            addCriterion("factor_name not in", values, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameBetween(String value1, String value2) {
            addCriterion("factor_name between", value1, value2, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameNotBetween(String value1, String value2) {
            addCriterion("factor_name not between", value1, value2, "factorName");
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

        public Criteria andContractStartDateIsNull() {
            addCriterion("contract_start_date is null");
            return (Criteria) this;
        }

        public Criteria andContractStartDateIsNotNull() {
            addCriterion("contract_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andContractStartDateEqualTo(String value) {
            addCriterion("contract_start_date =", value, "contractStartDate");
            return (Criteria) this;
        }

        public Criteria andContractStartDateNotEqualTo(String value) {
            addCriterion("contract_start_date <>", value, "contractStartDate");
            return (Criteria) this;
        }

        public Criteria andContractStartDateGreaterThan(String value) {
            addCriterion("contract_start_date >", value, "contractStartDate");
            return (Criteria) this;
        }

        public Criteria andContractStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("contract_start_date >=", value, "contractStartDate");
            return (Criteria) this;
        }

        public Criteria andContractStartDateLessThan(String value) {
            addCriterion("contract_start_date <", value, "contractStartDate");
            return (Criteria) this;
        }

        public Criteria andContractStartDateLessThanOrEqualTo(String value) {
            addCriterion("contract_start_date <=", value, "contractStartDate");
            return (Criteria) this;
        }

        public Criteria andContractStartDateLike(String value) {
            addCriterion("contract_start_date like", value, "contractStartDate");
            return (Criteria) this;
        }

        public Criteria andContractStartDateNotLike(String value) {
            addCriterion("contract_start_date not like", value, "contractStartDate");
            return (Criteria) this;
        }

        public Criteria andContractStartDateIn(List<String> values) {
            addCriterion("contract_start_date in", values, "contractStartDate");
            return (Criteria) this;
        }

        public Criteria andContractStartDateNotIn(List<String> values) {
            addCriterion("contract_start_date not in", values, "contractStartDate");
            return (Criteria) this;
        }

        public Criteria andContractStartDateBetween(String value1, String value2) {
            addCriterion("contract_start_date between", value1, value2, "contractStartDate");
            return (Criteria) this;
        }

        public Criteria andContractStartDateNotBetween(String value1, String value2) {
            addCriterion("contract_start_date not between", value1, value2, "contractStartDate");
            return (Criteria) this;
        }

        public Criteria andContractEndDateIsNull() {
            addCriterion("contract_end_date is null");
            return (Criteria) this;
        }

        public Criteria andContractEndDateIsNotNull() {
            addCriterion("contract_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andContractEndDateEqualTo(String value) {
            addCriterion("contract_end_date =", value, "contractEndDate");
            return (Criteria) this;
        }

        public Criteria andContractEndDateNotEqualTo(String value) {
            addCriterion("contract_end_date <>", value, "contractEndDate");
            return (Criteria) this;
        }

        public Criteria andContractEndDateGreaterThan(String value) {
            addCriterion("contract_end_date >", value, "contractEndDate");
            return (Criteria) this;
        }

        public Criteria andContractEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("contract_end_date >=", value, "contractEndDate");
            return (Criteria) this;
        }

        public Criteria andContractEndDateLessThan(String value) {
            addCriterion("contract_end_date <", value, "contractEndDate");
            return (Criteria) this;
        }

        public Criteria andContractEndDateLessThanOrEqualTo(String value) {
            addCriterion("contract_end_date <=", value, "contractEndDate");
            return (Criteria) this;
        }

        public Criteria andContractEndDateLike(String value) {
            addCriterion("contract_end_date like", value, "contractEndDate");
            return (Criteria) this;
        }

        public Criteria andContractEndDateNotLike(String value) {
            addCriterion("contract_end_date not like", value, "contractEndDate");
            return (Criteria) this;
        }

        public Criteria andContractEndDateIn(List<String> values) {
            addCriterion("contract_end_date in", values, "contractEndDate");
            return (Criteria) this;
        }

        public Criteria andContractEndDateNotIn(List<String> values) {
            addCriterion("contract_end_date not in", values, "contractEndDate");
            return (Criteria) this;
        }

        public Criteria andContractEndDateBetween(String value1, String value2) {
            addCriterion("contract_end_date between", value1, value2, "contractEndDate");
            return (Criteria) this;
        }

        public Criteria andContractEndDateNotBetween(String value1, String value2) {
            addCriterion("contract_end_date not between", value1, value2, "contractEndDate");
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

        public Criteria andInterestRateEqualTo(String value) {
            addCriterion("interest_rate =", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateNotEqualTo(String value) {
            addCriterion("interest_rate <>", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateGreaterThan(String value) {
            addCriterion("interest_rate >", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateGreaterThanOrEqualTo(String value) {
            addCriterion("interest_rate >=", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateLessThan(String value) {
            addCriterion("interest_rate <", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateLessThanOrEqualTo(String value) {
            addCriterion("interest_rate <=", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateLike(String value) {
            addCriterion("interest_rate like", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateNotLike(String value) {
            addCriterion("interest_rate not like", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateIn(List<String> values) {
            addCriterion("interest_rate in", values, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateNotIn(List<String> values) {
            addCriterion("interest_rate not in", values, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateBetween(String value1, String value2) {
            addCriterion("interest_rate between", value1, value2, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateNotBetween(String value1, String value2) {
            addCriterion("interest_rate not between", value1, value2, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestTypeIsNull() {
            addCriterion("interest_type is null");
            return (Criteria) this;
        }

        public Criteria andInterestTypeIsNotNull() {
            addCriterion("interest_type is not null");
            return (Criteria) this;
        }

        public Criteria andInterestTypeEqualTo(String value) {
            addCriterion("interest_type =", value, "interestType");
            return (Criteria) this;
        }

        public Criteria andInterestTypeNotEqualTo(String value) {
            addCriterion("interest_type <>", value, "interestType");
            return (Criteria) this;
        }

        public Criteria andInterestTypeGreaterThan(String value) {
            addCriterion("interest_type >", value, "interestType");
            return (Criteria) this;
        }

        public Criteria andInterestTypeGreaterThanOrEqualTo(String value) {
            addCriterion("interest_type >=", value, "interestType");
            return (Criteria) this;
        }

        public Criteria andInterestTypeLessThan(String value) {
            addCriterion("interest_type <", value, "interestType");
            return (Criteria) this;
        }

        public Criteria andInterestTypeLessThanOrEqualTo(String value) {
            addCriterion("interest_type <=", value, "interestType");
            return (Criteria) this;
        }

        public Criteria andInterestTypeLike(String value) {
            addCriterion("interest_type like", value, "interestType");
            return (Criteria) this;
        }

        public Criteria andInterestTypeNotLike(String value) {
            addCriterion("interest_type not like", value, "interestType");
            return (Criteria) this;
        }

        public Criteria andInterestTypeIn(List<String> values) {
            addCriterion("interest_type in", values, "interestType");
            return (Criteria) this;
        }

        public Criteria andInterestTypeNotIn(List<String> values) {
            addCriterion("interest_type not in", values, "interestType");
            return (Criteria) this;
        }

        public Criteria andInterestTypeBetween(String value1, String value2) {
            addCriterion("interest_type between", value1, value2, "interestType");
            return (Criteria) this;
        }

        public Criteria andInterestTypeNotBetween(String value1, String value2) {
            addCriterion("interest_type not between", value1, value2, "interestType");
            return (Criteria) this;
        }

        public Criteria andFeeRateIsNull() {
            addCriterion("fee_rate is null");
            return (Criteria) this;
        }

        public Criteria andFeeRateIsNotNull() {
            addCriterion("fee_rate is not null");
            return (Criteria) this;
        }

        public Criteria andFeeRateEqualTo(String value) {
            addCriterion("fee_rate =", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateNotEqualTo(String value) {
            addCriterion("fee_rate <>", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateGreaterThan(String value) {
            addCriterion("fee_rate >", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateGreaterThanOrEqualTo(String value) {
            addCriterion("fee_rate >=", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateLessThan(String value) {
            addCriterion("fee_rate <", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateLessThanOrEqualTo(String value) {
            addCriterion("fee_rate <=", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateLike(String value) {
            addCriterion("fee_rate like", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateNotLike(String value) {
            addCriterion("fee_rate not like", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateIn(List<String> values) {
            addCriterion("fee_rate in", values, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateNotIn(List<String> values) {
            addCriterion("fee_rate not in", values, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateBetween(String value1, String value2) {
            addCriterion("fee_rate between", value1, value2, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateNotBetween(String value1, String value2) {
            addCriterion("fee_rate not between", value1, value2, "feeRate");
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

        public Criteria andPenaltyRateEqualTo(String value) {
            addCriterion("penalty_rate =", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateNotEqualTo(String value) {
            addCriterion("penalty_rate <>", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateGreaterThan(String value) {
            addCriterion("penalty_rate >", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateGreaterThanOrEqualTo(String value) {
            addCriterion("penalty_rate >=", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateLessThan(String value) {
            addCriterion("penalty_rate <", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateLessThanOrEqualTo(String value) {
            addCriterion("penalty_rate <=", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateLike(String value) {
            addCriterion("penalty_rate like", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateNotLike(String value) {
            addCriterion("penalty_rate not like", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateIn(List<String> values) {
            addCriterion("penalty_rate in", values, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateNotIn(List<String> values) {
            addCriterion("penalty_rate not in", values, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateBetween(String value1, String value2) {
            addCriterion("penalty_rate between", value1, value2, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateNotBetween(String value1, String value2) {
            addCriterion("penalty_rate not between", value1, value2, "penaltyRate");
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

        public Criteria andExtensionDaysEqualTo(String value) {
            addCriterion("extension_days =", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysNotEqualTo(String value) {
            addCriterion("extension_days <>", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysGreaterThan(String value) {
            addCriterion("extension_days >", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysGreaterThanOrEqualTo(String value) {
            addCriterion("extension_days >=", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysLessThan(String value) {
            addCriterion("extension_days <", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysLessThanOrEqualTo(String value) {
            addCriterion("extension_days <=", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysLike(String value) {
            addCriterion("extension_days like", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysNotLike(String value) {
            addCriterion("extension_days not like", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysIn(List<String> values) {
            addCriterion("extension_days in", values, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysNotIn(List<String> values) {
            addCriterion("extension_days not in", values, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysBetween(String value1, String value2) {
            addCriterion("extension_days between", value1, value2, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysNotBetween(String value1, String value2) {
            addCriterion("extension_days not between", value1, value2, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andPaymentPeriodIsNull() {
            addCriterion("payment_period is null");
            return (Criteria) this;
        }

        public Criteria andPaymentPeriodIsNotNull() {
            addCriterion("payment_period is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentPeriodEqualTo(String value) {
            addCriterion("payment_period =", value, "paymentPeriod");
            return (Criteria) this;
        }

        public Criteria andPaymentPeriodNotEqualTo(String value) {
            addCriterion("payment_period <>", value, "paymentPeriod");
            return (Criteria) this;
        }

        public Criteria andPaymentPeriodGreaterThan(String value) {
            addCriterion("payment_period >", value, "paymentPeriod");
            return (Criteria) this;
        }

        public Criteria andPaymentPeriodGreaterThanOrEqualTo(String value) {
            addCriterion("payment_period >=", value, "paymentPeriod");
            return (Criteria) this;
        }

        public Criteria andPaymentPeriodLessThan(String value) {
            addCriterion("payment_period <", value, "paymentPeriod");
            return (Criteria) this;
        }

        public Criteria andPaymentPeriodLessThanOrEqualTo(String value) {
            addCriterion("payment_period <=", value, "paymentPeriod");
            return (Criteria) this;
        }

        public Criteria andPaymentPeriodLike(String value) {
            addCriterion("payment_period like", value, "paymentPeriod");
            return (Criteria) this;
        }

        public Criteria andPaymentPeriodNotLike(String value) {
            addCriterion("payment_period not like", value, "paymentPeriod");
            return (Criteria) this;
        }

        public Criteria andPaymentPeriodIn(List<String> values) {
            addCriterion("payment_period in", values, "paymentPeriod");
            return (Criteria) this;
        }

        public Criteria andPaymentPeriodNotIn(List<String> values) {
            addCriterion("payment_period not in", values, "paymentPeriod");
            return (Criteria) this;
        }

        public Criteria andPaymentPeriodBetween(String value1, String value2) {
            addCriterion("payment_period between", value1, value2, "paymentPeriod");
            return (Criteria) this;
        }

        public Criteria andPaymentPeriodNotBetween(String value1, String value2) {
            addCriterion("payment_period not between", value1, value2, "paymentPeriod");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeIsNull() {
            addCriterion("receipt_type is null");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeIsNotNull() {
            addCriterion("receipt_type is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeEqualTo(String value) {
            addCriterion("receipt_type =", value, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeNotEqualTo(String value) {
            addCriterion("receipt_type <>", value, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeGreaterThan(String value) {
            addCriterion("receipt_type >", value, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeGreaterThanOrEqualTo(String value) {
            addCriterion("receipt_type >=", value, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeLessThan(String value) {
            addCriterion("receipt_type <", value, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeLessThanOrEqualTo(String value) {
            addCriterion("receipt_type <=", value, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeLike(String value) {
            addCriterion("receipt_type like", value, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeNotLike(String value) {
            addCriterion("receipt_type not like", value, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeIn(List<String> values) {
            addCriterion("receipt_type in", values, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeNotIn(List<String> values) {
            addCriterion("receipt_type not in", values, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeBetween(String value1, String value2) {
            addCriterion("receipt_type between", value1, value2, "receiptType");
            return (Criteria) this;
        }

        public Criteria andReceiptTypeNotBetween(String value1, String value2) {
            addCriterion("receipt_type not between", value1, value2, "receiptType");
            return (Criteria) this;
        }

        public Criteria andRepaymentSourceIsNull() {
            addCriterion("repayment_source is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentSourceIsNotNull() {
            addCriterion("repayment_source is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentSourceEqualTo(String value) {
            addCriterion("repayment_source =", value, "repaymentSource");
            return (Criteria) this;
        }

        public Criteria andRepaymentSourceNotEqualTo(String value) {
            addCriterion("repayment_source <>", value, "repaymentSource");
            return (Criteria) this;
        }

        public Criteria andRepaymentSourceGreaterThan(String value) {
            addCriterion("repayment_source >", value, "repaymentSource");
            return (Criteria) this;
        }

        public Criteria andRepaymentSourceGreaterThanOrEqualTo(String value) {
            addCriterion("repayment_source >=", value, "repaymentSource");
            return (Criteria) this;
        }

        public Criteria andRepaymentSourceLessThan(String value) {
            addCriterion("repayment_source <", value, "repaymentSource");
            return (Criteria) this;
        }

        public Criteria andRepaymentSourceLessThanOrEqualTo(String value) {
            addCriterion("repayment_source <=", value, "repaymentSource");
            return (Criteria) this;
        }

        public Criteria andRepaymentSourceLike(String value) {
            addCriterion("repayment_source like", value, "repaymentSource");
            return (Criteria) this;
        }

        public Criteria andRepaymentSourceNotLike(String value) {
            addCriterion("repayment_source not like", value, "repaymentSource");
            return (Criteria) this;
        }

        public Criteria andRepaymentSourceIn(List<String> values) {
            addCriterion("repayment_source in", values, "repaymentSource");
            return (Criteria) this;
        }

        public Criteria andRepaymentSourceNotIn(List<String> values) {
            addCriterion("repayment_source not in", values, "repaymentSource");
            return (Criteria) this;
        }

        public Criteria andRepaymentSourceBetween(String value1, String value2) {
            addCriterion("repayment_source between", value1, value2, "repaymentSource");
            return (Criteria) this;
        }

        public Criteria andRepaymentSourceNotBetween(String value1, String value2) {
            addCriterion("repayment_source not between", value1, value2, "repaymentSource");
            return (Criteria) this;
        }

        public Criteria andRepaymentArrangementIsNull() {
            addCriterion("repayment_arrangement is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentArrangementIsNotNull() {
            addCriterion("repayment_arrangement is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentArrangementEqualTo(String value) {
            addCriterion("repayment_arrangement =", value, "repaymentArrangement");
            return (Criteria) this;
        }

        public Criteria andRepaymentArrangementNotEqualTo(String value) {
            addCriterion("repayment_arrangement <>", value, "repaymentArrangement");
            return (Criteria) this;
        }

        public Criteria andRepaymentArrangementGreaterThan(String value) {
            addCriterion("repayment_arrangement >", value, "repaymentArrangement");
            return (Criteria) this;
        }

        public Criteria andRepaymentArrangementGreaterThanOrEqualTo(String value) {
            addCriterion("repayment_arrangement >=", value, "repaymentArrangement");
            return (Criteria) this;
        }

        public Criteria andRepaymentArrangementLessThan(String value) {
            addCriterion("repayment_arrangement <", value, "repaymentArrangement");
            return (Criteria) this;
        }

        public Criteria andRepaymentArrangementLessThanOrEqualTo(String value) {
            addCriterion("repayment_arrangement <=", value, "repaymentArrangement");
            return (Criteria) this;
        }

        public Criteria andRepaymentArrangementLike(String value) {
            addCriterion("repayment_arrangement like", value, "repaymentArrangement");
            return (Criteria) this;
        }

        public Criteria andRepaymentArrangementNotLike(String value) {
            addCriterion("repayment_arrangement not like", value, "repaymentArrangement");
            return (Criteria) this;
        }

        public Criteria andRepaymentArrangementIn(List<String> values) {
            addCriterion("repayment_arrangement in", values, "repaymentArrangement");
            return (Criteria) this;
        }

        public Criteria andRepaymentArrangementNotIn(List<String> values) {
            addCriterion("repayment_arrangement not in", values, "repaymentArrangement");
            return (Criteria) this;
        }

        public Criteria andRepaymentArrangementBetween(String value1, String value2) {
            addCriterion("repayment_arrangement between", value1, value2, "repaymentArrangement");
            return (Criteria) this;
        }

        public Criteria andRepaymentArrangementNotBetween(String value1, String value2) {
            addCriterion("repayment_arrangement not between", value1, value2, "repaymentArrangement");
            return (Criteria) this;
        }

        public Criteria andFundUsageIsNull() {
            addCriterion("fund_usage is null");
            return (Criteria) this;
        }

        public Criteria andFundUsageIsNotNull() {
            addCriterion("fund_usage is not null");
            return (Criteria) this;
        }

        public Criteria andFundUsageEqualTo(String value) {
            addCriterion("fund_usage =", value, "fundUsage");
            return (Criteria) this;
        }

        public Criteria andFundUsageNotEqualTo(String value) {
            addCriterion("fund_usage <>", value, "fundUsage");
            return (Criteria) this;
        }

        public Criteria andFundUsageGreaterThan(String value) {
            addCriterion("fund_usage >", value, "fundUsage");
            return (Criteria) this;
        }

        public Criteria andFundUsageGreaterThanOrEqualTo(String value) {
            addCriterion("fund_usage >=", value, "fundUsage");
            return (Criteria) this;
        }

        public Criteria andFundUsageLessThan(String value) {
            addCriterion("fund_usage <", value, "fundUsage");
            return (Criteria) this;
        }

        public Criteria andFundUsageLessThanOrEqualTo(String value) {
            addCriterion("fund_usage <=", value, "fundUsage");
            return (Criteria) this;
        }

        public Criteria andFundUsageLike(String value) {
            addCriterion("fund_usage like", value, "fundUsage");
            return (Criteria) this;
        }

        public Criteria andFundUsageNotLike(String value) {
            addCriterion("fund_usage not like", value, "fundUsage");
            return (Criteria) this;
        }

        public Criteria andFundUsageIn(List<String> values) {
            addCriterion("fund_usage in", values, "fundUsage");
            return (Criteria) this;
        }

        public Criteria andFundUsageNotIn(List<String> values) {
            addCriterion("fund_usage not in", values, "fundUsage");
            return (Criteria) this;
        }

        public Criteria andFundUsageBetween(String value1, String value2) {
            addCriterion("fund_usage between", value1, value2, "fundUsage");
            return (Criteria) this;
        }

        public Criteria andFundUsageNotBetween(String value1, String value2) {
            addCriterion("fund_usage not between", value1, value2, "fundUsage");
            return (Criteria) this;
        }

        public Criteria andMonitoringSolutionIsNull() {
            addCriterion("monitoring_solution is null");
            return (Criteria) this;
        }

        public Criteria andMonitoringSolutionIsNotNull() {
            addCriterion("monitoring_solution is not null");
            return (Criteria) this;
        }

        public Criteria andMonitoringSolutionEqualTo(String value) {
            addCriterion("monitoring_solution =", value, "monitoringSolution");
            return (Criteria) this;
        }

        public Criteria andMonitoringSolutionNotEqualTo(String value) {
            addCriterion("monitoring_solution <>", value, "monitoringSolution");
            return (Criteria) this;
        }

        public Criteria andMonitoringSolutionGreaterThan(String value) {
            addCriterion("monitoring_solution >", value, "monitoringSolution");
            return (Criteria) this;
        }

        public Criteria andMonitoringSolutionGreaterThanOrEqualTo(String value) {
            addCriterion("monitoring_solution >=", value, "monitoringSolution");
            return (Criteria) this;
        }

        public Criteria andMonitoringSolutionLessThan(String value) {
            addCriterion("monitoring_solution <", value, "monitoringSolution");
            return (Criteria) this;
        }

        public Criteria andMonitoringSolutionLessThanOrEqualTo(String value) {
            addCriterion("monitoring_solution <=", value, "monitoringSolution");
            return (Criteria) this;
        }

        public Criteria andMonitoringSolutionLike(String value) {
            addCriterion("monitoring_solution like", value, "monitoringSolution");
            return (Criteria) this;
        }

        public Criteria andMonitoringSolutionNotLike(String value) {
            addCriterion("monitoring_solution not like", value, "monitoringSolution");
            return (Criteria) this;
        }

        public Criteria andMonitoringSolutionIn(List<String> values) {
            addCriterion("monitoring_solution in", values, "monitoringSolution");
            return (Criteria) this;
        }

        public Criteria andMonitoringSolutionNotIn(List<String> values) {
            addCriterion("monitoring_solution not in", values, "monitoringSolution");
            return (Criteria) this;
        }

        public Criteria andMonitoringSolutionBetween(String value1, String value2) {
            addCriterion("monitoring_solution between", value1, value2, "monitoringSolution");
            return (Criteria) this;
        }

        public Criteria andMonitoringSolutionNotBetween(String value1, String value2) {
            addCriterion("monitoring_solution not between", value1, value2, "monitoringSolution");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustMeasuresIsNull() {
            addCriterion("increase_trust_measures is null");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustMeasuresIsNotNull() {
            addCriterion("increase_trust_measures is not null");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustMeasuresEqualTo(String value) {
            addCriterion("increase_trust_measures =", value, "increaseTrustMeasures");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustMeasuresNotEqualTo(String value) {
            addCriterion("increase_trust_measures <>", value, "increaseTrustMeasures");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustMeasuresGreaterThan(String value) {
            addCriterion("increase_trust_measures >", value, "increaseTrustMeasures");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustMeasuresGreaterThanOrEqualTo(String value) {
            addCriterion("increase_trust_measures >=", value, "increaseTrustMeasures");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustMeasuresLessThan(String value) {
            addCriterion("increase_trust_measures <", value, "increaseTrustMeasures");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustMeasuresLessThanOrEqualTo(String value) {
            addCriterion("increase_trust_measures <=", value, "increaseTrustMeasures");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustMeasuresLike(String value) {
            addCriterion("increase_trust_measures like", value, "increaseTrustMeasures");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustMeasuresNotLike(String value) {
            addCriterion("increase_trust_measures not like", value, "increaseTrustMeasures");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustMeasuresIn(List<String> values) {
            addCriterion("increase_trust_measures in", values, "increaseTrustMeasures");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustMeasuresNotIn(List<String> values) {
            addCriterion("increase_trust_measures not in", values, "increaseTrustMeasures");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustMeasuresBetween(String value1, String value2) {
            addCriterion("increase_trust_measures between", value1, value2, "increaseTrustMeasures");
            return (Criteria) this;
        }

        public Criteria andIncreaseTrustMeasuresNotBetween(String value1, String value2) {
            addCriterion("increase_trust_measures not between", value1, value2, "increaseTrustMeasures");
            return (Criteria) this;
        }

        public Criteria andBindingProvisionsIsNull() {
            addCriterion("binding_provisions is null");
            return (Criteria) this;
        }

        public Criteria andBindingProvisionsIsNotNull() {
            addCriterion("binding_provisions is not null");
            return (Criteria) this;
        }

        public Criteria andBindingProvisionsEqualTo(String value) {
            addCriterion("binding_provisions =", value, "bindingProvisions");
            return (Criteria) this;
        }

        public Criteria andBindingProvisionsNotEqualTo(String value) {
            addCriterion("binding_provisions <>", value, "bindingProvisions");
            return (Criteria) this;
        }

        public Criteria andBindingProvisionsGreaterThan(String value) {
            addCriterion("binding_provisions >", value, "bindingProvisions");
            return (Criteria) this;
        }

        public Criteria andBindingProvisionsGreaterThanOrEqualTo(String value) {
            addCriterion("binding_provisions >=", value, "bindingProvisions");
            return (Criteria) this;
        }

        public Criteria andBindingProvisionsLessThan(String value) {
            addCriterion("binding_provisions <", value, "bindingProvisions");
            return (Criteria) this;
        }

        public Criteria andBindingProvisionsLessThanOrEqualTo(String value) {
            addCriterion("binding_provisions <=", value, "bindingProvisions");
            return (Criteria) this;
        }

        public Criteria andBindingProvisionsLike(String value) {
            addCriterion("binding_provisions like", value, "bindingProvisions");
            return (Criteria) this;
        }

        public Criteria andBindingProvisionsNotLike(String value) {
            addCriterion("binding_provisions not like", value, "bindingProvisions");
            return (Criteria) this;
        }

        public Criteria andBindingProvisionsIn(List<String> values) {
            addCriterion("binding_provisions in", values, "bindingProvisions");
            return (Criteria) this;
        }

        public Criteria andBindingProvisionsNotIn(List<String> values) {
            addCriterion("binding_provisions not in", values, "bindingProvisions");
            return (Criteria) this;
        }

        public Criteria andBindingProvisionsBetween(String value1, String value2) {
            addCriterion("binding_provisions between", value1, value2, "bindingProvisions");
            return (Criteria) this;
        }

        public Criteria andBindingProvisionsNotBetween(String value1, String value2) {
            addCriterion("binding_provisions not between", value1, value2, "bindingProvisions");
            return (Criteria) this;
        }

        public Criteria andLoanStateIsNull() {
            addCriterion("loan_state is null");
            return (Criteria) this;
        }

        public Criteria andLoanStateIsNotNull() {
            addCriterion("loan_state is not null");
            return (Criteria) this;
        }

        public Criteria andLoanStateEqualTo(String value) {
            addCriterion("loan_state =", value, "loanState");
            return (Criteria) this;
        }

        public Criteria andLoanStateNotEqualTo(String value) {
            addCriterion("loan_state <>", value, "loanState");
            return (Criteria) this;
        }

        public Criteria andLoanStateGreaterThan(String value) {
            addCriterion("loan_state >", value, "loanState");
            return (Criteria) this;
        }

        public Criteria andLoanStateGreaterThanOrEqualTo(String value) {
            addCriterion("loan_state >=", value, "loanState");
            return (Criteria) this;
        }

        public Criteria andLoanStateLessThan(String value) {
            addCriterion("loan_state <", value, "loanState");
            return (Criteria) this;
        }

        public Criteria andLoanStateLessThanOrEqualTo(String value) {
            addCriterion("loan_state <=", value, "loanState");
            return (Criteria) this;
        }

        public Criteria andLoanStateLike(String value) {
            addCriterion("loan_state like", value, "loanState");
            return (Criteria) this;
        }

        public Criteria andLoanStateNotLike(String value) {
            addCriterion("loan_state not like", value, "loanState");
            return (Criteria) this;
        }

        public Criteria andLoanStateIn(List<String> values) {
            addCriterion("loan_state in", values, "loanState");
            return (Criteria) this;
        }

        public Criteria andLoanStateNotIn(List<String> values) {
            addCriterion("loan_state not in", values, "loanState");
            return (Criteria) this;
        }

        public Criteria andLoanStateBetween(String value1, String value2) {
            addCriterion("loan_state between", value1, value2, "loanState");
            return (Criteria) this;
        }

        public Criteria andLoanStateNotBetween(String value1, String value2) {
            addCriterion("loan_state not between", value1, value2, "loanState");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountNameIsNull() {
            addCriterion("factoring_account_name is null");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountNameIsNotNull() {
            addCriterion("factoring_account_name is not null");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountNameEqualTo(String value) {
            addCriterion("factoring_account_name =", value, "factoringAccountName");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountNameNotEqualTo(String value) {
            addCriterion("factoring_account_name <>", value, "factoringAccountName");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountNameGreaterThan(String value) {
            addCriterion("factoring_account_name >", value, "factoringAccountName");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("factoring_account_name >=", value, "factoringAccountName");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountNameLessThan(String value) {
            addCriterion("factoring_account_name <", value, "factoringAccountName");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountNameLessThanOrEqualTo(String value) {
            addCriterion("factoring_account_name <=", value, "factoringAccountName");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountNameLike(String value) {
            addCriterion("factoring_account_name like", value, "factoringAccountName");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountNameNotLike(String value) {
            addCriterion("factoring_account_name not like", value, "factoringAccountName");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountNameIn(List<String> values) {
            addCriterion("factoring_account_name in", values, "factoringAccountName");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountNameNotIn(List<String> values) {
            addCriterion("factoring_account_name not in", values, "factoringAccountName");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountNameBetween(String value1, String value2) {
            addCriterion("factoring_account_name between", value1, value2, "factoringAccountName");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountNameNotBetween(String value1, String value2) {
            addCriterion("factoring_account_name not between", value1, value2, "factoringAccountName");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountBankAddressIsNull() {
            addCriterion("factoring_account_bank_address is null");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountBankAddressIsNotNull() {
            addCriterion("factoring_account_bank_address is not null");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountBankAddressEqualTo(String value) {
            addCriterion("factoring_account_bank_address =", value, "factoringAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountBankAddressNotEqualTo(String value) {
            addCriterion("factoring_account_bank_address <>", value, "factoringAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountBankAddressGreaterThan(String value) {
            addCriterion("factoring_account_bank_address >", value, "factoringAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountBankAddressGreaterThanOrEqualTo(String value) {
            addCriterion("factoring_account_bank_address >=", value, "factoringAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountBankAddressLessThan(String value) {
            addCriterion("factoring_account_bank_address <", value, "factoringAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountBankAddressLessThanOrEqualTo(String value) {
            addCriterion("factoring_account_bank_address <=", value, "factoringAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountBankAddressLike(String value) {
            addCriterion("factoring_account_bank_address like", value, "factoringAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountBankAddressNotLike(String value) {
            addCriterion("factoring_account_bank_address not like", value, "factoringAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountBankAddressIn(List<String> values) {
            addCriterion("factoring_account_bank_address in", values, "factoringAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountBankAddressNotIn(List<String> values) {
            addCriterion("factoring_account_bank_address not in", values, "factoringAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountBankAddressBetween(String value1, String value2) {
            addCriterion("factoring_account_bank_address between", value1, value2, "factoringAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountBankAddressNotBetween(String value1, String value2) {
            addCriterion("factoring_account_bank_address not between", value1, value2, "factoringAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountIsNull() {
            addCriterion("factoring_account is null");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountIsNotNull() {
            addCriterion("factoring_account is not null");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountEqualTo(String value) {
            addCriterion("factoring_account =", value, "factoringAccount");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountNotEqualTo(String value) {
            addCriterion("factoring_account <>", value, "factoringAccount");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountGreaterThan(String value) {
            addCriterion("factoring_account >", value, "factoringAccount");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountGreaterThanOrEqualTo(String value) {
            addCriterion("factoring_account >=", value, "factoringAccount");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountLessThan(String value) {
            addCriterion("factoring_account <", value, "factoringAccount");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountLessThanOrEqualTo(String value) {
            addCriterion("factoring_account <=", value, "factoringAccount");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountLike(String value) {
            addCriterion("factoring_account like", value, "factoringAccount");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountNotLike(String value) {
            addCriterion("factoring_account not like", value, "factoringAccount");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountIn(List<String> values) {
            addCriterion("factoring_account in", values, "factoringAccount");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountNotIn(List<String> values) {
            addCriterion("factoring_account not in", values, "factoringAccount");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountBetween(String value1, String value2) {
            addCriterion("factoring_account between", value1, value2, "factoringAccount");
            return (Criteria) this;
        }

        public Criteria andFactoringAccountNotBetween(String value1, String value2) {
            addCriterion("factoring_account not between", value1, value2, "factoringAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNameIsNull() {
            addCriterion("settlement_account_name is null");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNameIsNotNull() {
            addCriterion("settlement_account_name is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNameEqualTo(String value) {
            addCriterion("settlement_account_name =", value, "settlementAccountName");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNameNotEqualTo(String value) {
            addCriterion("settlement_account_name <>", value, "settlementAccountName");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNameGreaterThan(String value) {
            addCriterion("settlement_account_name >", value, "settlementAccountName");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("settlement_account_name >=", value, "settlementAccountName");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNameLessThan(String value) {
            addCriterion("settlement_account_name <", value, "settlementAccountName");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNameLessThanOrEqualTo(String value) {
            addCriterion("settlement_account_name <=", value, "settlementAccountName");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNameLike(String value) {
            addCriterion("settlement_account_name like", value, "settlementAccountName");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNameNotLike(String value) {
            addCriterion("settlement_account_name not like", value, "settlementAccountName");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNameIn(List<String> values) {
            addCriterion("settlement_account_name in", values, "settlementAccountName");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNameNotIn(List<String> values) {
            addCriterion("settlement_account_name not in", values, "settlementAccountName");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNameBetween(String value1, String value2) {
            addCriterion("settlement_account_name between", value1, value2, "settlementAccountName");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNameNotBetween(String value1, String value2) {
            addCriterion("settlement_account_name not between", value1, value2, "settlementAccountName");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountBankAddressIsNull() {
            addCriterion("settlement_account_bank_address is null");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountBankAddressIsNotNull() {
            addCriterion("settlement_account_bank_address is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountBankAddressEqualTo(String value) {
            addCriterion("settlement_account_bank_address =", value, "settlementAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountBankAddressNotEqualTo(String value) {
            addCriterion("settlement_account_bank_address <>", value, "settlementAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountBankAddressGreaterThan(String value) {
            addCriterion("settlement_account_bank_address >", value, "settlementAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountBankAddressGreaterThanOrEqualTo(String value) {
            addCriterion("settlement_account_bank_address >=", value, "settlementAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountBankAddressLessThan(String value) {
            addCriterion("settlement_account_bank_address <", value, "settlementAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountBankAddressLessThanOrEqualTo(String value) {
            addCriterion("settlement_account_bank_address <=", value, "settlementAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountBankAddressLike(String value) {
            addCriterion("settlement_account_bank_address like", value, "settlementAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountBankAddressNotLike(String value) {
            addCriterion("settlement_account_bank_address not like", value, "settlementAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountBankAddressIn(List<String> values) {
            addCriterion("settlement_account_bank_address in", values, "settlementAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountBankAddressNotIn(List<String> values) {
            addCriterion("settlement_account_bank_address not in", values, "settlementAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountBankAddressBetween(String value1, String value2) {
            addCriterion("settlement_account_bank_address between", value1, value2, "settlementAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountBankAddressNotBetween(String value1, String value2) {
            addCriterion("settlement_account_bank_address not between", value1, value2, "settlementAccountBankAddress");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountIsNull() {
            addCriterion("settlement_account is null");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountIsNotNull() {
            addCriterion("settlement_account is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountEqualTo(String value) {
            addCriterion("settlement_account =", value, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNotEqualTo(String value) {
            addCriterion("settlement_account <>", value, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountGreaterThan(String value) {
            addCriterion("settlement_account >", value, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountGreaterThanOrEqualTo(String value) {
            addCriterion("settlement_account >=", value, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountLessThan(String value) {
            addCriterion("settlement_account <", value, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountLessThanOrEqualTo(String value) {
            addCriterion("settlement_account <=", value, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountLike(String value) {
            addCriterion("settlement_account like", value, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNotLike(String value) {
            addCriterion("settlement_account not like", value, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountIn(List<String> values) {
            addCriterion("settlement_account in", values, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNotIn(List<String> values) {
            addCriterion("settlement_account not in", values, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountBetween(String value1, String value2) {
            addCriterion("settlement_account between", value1, value2, "settlementAccount");
            return (Criteria) this;
        }

        public Criteria andSettlementAccountNotBetween(String value1, String value2) {
            addCriterion("settlement_account not between", value1, value2, "settlementAccount");
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