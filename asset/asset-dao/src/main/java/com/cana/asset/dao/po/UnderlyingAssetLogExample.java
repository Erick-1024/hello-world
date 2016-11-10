package com.cana.asset.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UnderlyingAssetLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public UnderlyingAssetLogExample() {
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

        public Criteria andCustomerEconomicCategoryIsNull() {
            addCriterion("customer_economic_category is null");
            return (Criteria) this;
        }

        public Criteria andCustomerEconomicCategoryIsNotNull() {
            addCriterion("customer_economic_category is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerEconomicCategoryEqualTo(String value) {
            addCriterion("customer_economic_category =", value, "customerEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCustomerEconomicCategoryNotEqualTo(String value) {
            addCriterion("customer_economic_category <>", value, "customerEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCustomerEconomicCategoryGreaterThan(String value) {
            addCriterion("customer_economic_category >", value, "customerEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCustomerEconomicCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("customer_economic_category >=", value, "customerEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCustomerEconomicCategoryLessThan(String value) {
            addCriterion("customer_economic_category <", value, "customerEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCustomerEconomicCategoryLessThanOrEqualTo(String value) {
            addCriterion("customer_economic_category <=", value, "customerEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCustomerEconomicCategoryLike(String value) {
            addCriterion("customer_economic_category like", value, "customerEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCustomerEconomicCategoryNotLike(String value) {
            addCriterion("customer_economic_category not like", value, "customerEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCustomerEconomicCategoryIn(List<String> values) {
            addCriterion("customer_economic_category in", values, "customerEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCustomerEconomicCategoryNotIn(List<String> values) {
            addCriterion("customer_economic_category not in", values, "customerEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCustomerEconomicCategoryBetween(String value1, String value2) {
            addCriterion("customer_economic_category between", value1, value2, "customerEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCustomerEconomicCategoryNotBetween(String value1, String value2) {
            addCriterion("customer_economic_category not between", value1, value2, "customerEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIsNull() {
            addCriterion("counterparty is null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIsNotNull() {
            addCriterion("counterparty is not null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyEqualTo(String value) {
            addCriterion("counterparty =", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyNotEqualTo(String value) {
            addCriterion("counterparty <>", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyGreaterThan(String value) {
            addCriterion("counterparty >", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyGreaterThanOrEqualTo(String value) {
            addCriterion("counterparty >=", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLessThan(String value) {
            addCriterion("counterparty <", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLessThanOrEqualTo(String value) {
            addCriterion("counterparty <=", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLike(String value) {
            addCriterion("counterparty like", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyNotLike(String value) {
            addCriterion("counterparty not like", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIn(List<String> values) {
            addCriterion("counterparty in", values, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyNotIn(List<String> values) {
            addCriterion("counterparty not in", values, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyBetween(String value1, String value2) {
            addCriterion("counterparty between", value1, value2, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyNotBetween(String value1, String value2) {
            addCriterion("counterparty not between", value1, value2, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyEconomicCategoryIsNull() {
            addCriterion("counterparty_economic_category is null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyEconomicCategoryIsNotNull() {
            addCriterion("counterparty_economic_category is not null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyEconomicCategoryEqualTo(String value) {
            addCriterion("counterparty_economic_category =", value, "counterpartyEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCounterpartyEconomicCategoryNotEqualTo(String value) {
            addCriterion("counterparty_economic_category <>", value, "counterpartyEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCounterpartyEconomicCategoryGreaterThan(String value) {
            addCriterion("counterparty_economic_category >", value, "counterpartyEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCounterpartyEconomicCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("counterparty_economic_category >=", value, "counterpartyEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCounterpartyEconomicCategoryLessThan(String value) {
            addCriterion("counterparty_economic_category <", value, "counterpartyEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCounterpartyEconomicCategoryLessThanOrEqualTo(String value) {
            addCriterion("counterparty_economic_category <=", value, "counterpartyEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCounterpartyEconomicCategoryLike(String value) {
            addCriterion("counterparty_economic_category like", value, "counterpartyEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCounterpartyEconomicCategoryNotLike(String value) {
            addCriterion("counterparty_economic_category not like", value, "counterpartyEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCounterpartyEconomicCategoryIn(List<String> values) {
            addCriterion("counterparty_economic_category in", values, "counterpartyEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCounterpartyEconomicCategoryNotIn(List<String> values) {
            addCriterion("counterparty_economic_category not in", values, "counterpartyEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCounterpartyEconomicCategoryBetween(String value1, String value2) {
            addCriterion("counterparty_economic_category between", value1, value2, "counterpartyEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCounterpartyEconomicCategoryNotBetween(String value1, String value2) {
            addCriterion("counterparty_economic_category not between", value1, value2, "counterpartyEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCustomerCityIsNull() {
            addCriterion("customer_city is null");
            return (Criteria) this;
        }

        public Criteria andCustomerCityIsNotNull() {
            addCriterion("customer_city is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerCityEqualTo(String value) {
            addCriterion("customer_city =", value, "customerCity");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNotEqualTo(String value) {
            addCriterion("customer_city <>", value, "customerCity");
            return (Criteria) this;
        }

        public Criteria andCustomerCityGreaterThan(String value) {
            addCriterion("customer_city >", value, "customerCity");
            return (Criteria) this;
        }

        public Criteria andCustomerCityGreaterThanOrEqualTo(String value) {
            addCriterion("customer_city >=", value, "customerCity");
            return (Criteria) this;
        }

        public Criteria andCustomerCityLessThan(String value) {
            addCriterion("customer_city <", value, "customerCity");
            return (Criteria) this;
        }

        public Criteria andCustomerCityLessThanOrEqualTo(String value) {
            addCriterion("customer_city <=", value, "customerCity");
            return (Criteria) this;
        }

        public Criteria andCustomerCityLike(String value) {
            addCriterion("customer_city like", value, "customerCity");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNotLike(String value) {
            addCriterion("customer_city not like", value, "customerCity");
            return (Criteria) this;
        }

        public Criteria andCustomerCityIn(List<String> values) {
            addCriterion("customer_city in", values, "customerCity");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNotIn(List<String> values) {
            addCriterion("customer_city not in", values, "customerCity");
            return (Criteria) this;
        }

        public Criteria andCustomerCityBetween(String value1, String value2) {
            addCriterion("customer_city between", value1, value2, "customerCity");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNotBetween(String value1, String value2) {
            addCriterion("customer_city not between", value1, value2, "customerCity");
            return (Criteria) this;
        }

        public Criteria andCustomerIndustryIsNull() {
            addCriterion("customer_industry is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIndustryIsNotNull() {
            addCriterion("customer_industry is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIndustryEqualTo(String value) {
            addCriterion("customer_industry =", value, "customerIndustry");
            return (Criteria) this;
        }

        public Criteria andCustomerIndustryNotEqualTo(String value) {
            addCriterion("customer_industry <>", value, "customerIndustry");
            return (Criteria) this;
        }

        public Criteria andCustomerIndustryGreaterThan(String value) {
            addCriterion("customer_industry >", value, "customerIndustry");
            return (Criteria) this;
        }

        public Criteria andCustomerIndustryGreaterThanOrEqualTo(String value) {
            addCriterion("customer_industry >=", value, "customerIndustry");
            return (Criteria) this;
        }

        public Criteria andCustomerIndustryLessThan(String value) {
            addCriterion("customer_industry <", value, "customerIndustry");
            return (Criteria) this;
        }

        public Criteria andCustomerIndustryLessThanOrEqualTo(String value) {
            addCriterion("customer_industry <=", value, "customerIndustry");
            return (Criteria) this;
        }

        public Criteria andCustomerIndustryLike(String value) {
            addCriterion("customer_industry like", value, "customerIndustry");
            return (Criteria) this;
        }

        public Criteria andCustomerIndustryNotLike(String value) {
            addCriterion("customer_industry not like", value, "customerIndustry");
            return (Criteria) this;
        }

        public Criteria andCustomerIndustryIn(List<String> values) {
            addCriterion("customer_industry in", values, "customerIndustry");
            return (Criteria) this;
        }

        public Criteria andCustomerIndustryNotIn(List<String> values) {
            addCriterion("customer_industry not in", values, "customerIndustry");
            return (Criteria) this;
        }

        public Criteria andCustomerIndustryBetween(String value1, String value2) {
            addCriterion("customer_industry between", value1, value2, "customerIndustry");
            return (Criteria) this;
        }

        public Criteria andCustomerIndustryNotBetween(String value1, String value2) {
            addCriterion("customer_industry not between", value1, value2, "customerIndustry");
            return (Criteria) this;
        }

        public Criteria andCounterpartyCityIsNull() {
            addCriterion("counterparty_city is null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyCityIsNotNull() {
            addCriterion("counterparty_city is not null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyCityEqualTo(String value) {
            addCriterion("counterparty_city =", value, "counterpartyCity");
            return (Criteria) this;
        }

        public Criteria andCounterpartyCityNotEqualTo(String value) {
            addCriterion("counterparty_city <>", value, "counterpartyCity");
            return (Criteria) this;
        }

        public Criteria andCounterpartyCityGreaterThan(String value) {
            addCriterion("counterparty_city >", value, "counterpartyCity");
            return (Criteria) this;
        }

        public Criteria andCounterpartyCityGreaterThanOrEqualTo(String value) {
            addCriterion("counterparty_city >=", value, "counterpartyCity");
            return (Criteria) this;
        }

        public Criteria andCounterpartyCityLessThan(String value) {
            addCriterion("counterparty_city <", value, "counterpartyCity");
            return (Criteria) this;
        }

        public Criteria andCounterpartyCityLessThanOrEqualTo(String value) {
            addCriterion("counterparty_city <=", value, "counterpartyCity");
            return (Criteria) this;
        }

        public Criteria andCounterpartyCityLike(String value) {
            addCriterion("counterparty_city like", value, "counterpartyCity");
            return (Criteria) this;
        }

        public Criteria andCounterpartyCityNotLike(String value) {
            addCriterion("counterparty_city not like", value, "counterpartyCity");
            return (Criteria) this;
        }

        public Criteria andCounterpartyCityIn(List<String> values) {
            addCriterion("counterparty_city in", values, "counterpartyCity");
            return (Criteria) this;
        }

        public Criteria andCounterpartyCityNotIn(List<String> values) {
            addCriterion("counterparty_city not in", values, "counterpartyCity");
            return (Criteria) this;
        }

        public Criteria andCounterpartyCityBetween(String value1, String value2) {
            addCriterion("counterparty_city between", value1, value2, "counterpartyCity");
            return (Criteria) this;
        }

        public Criteria andCounterpartyCityNotBetween(String value1, String value2) {
            addCriterion("counterparty_city not between", value1, value2, "counterpartyCity");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIndustryIsNull() {
            addCriterion("counterparty_industry is null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIndustryIsNotNull() {
            addCriterion("counterparty_industry is not null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIndustryEqualTo(String value) {
            addCriterion("counterparty_industry =", value, "counterpartyIndustry");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIndustryNotEqualTo(String value) {
            addCriterion("counterparty_industry <>", value, "counterpartyIndustry");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIndustryGreaterThan(String value) {
            addCriterion("counterparty_industry >", value, "counterpartyIndustry");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIndustryGreaterThanOrEqualTo(String value) {
            addCriterion("counterparty_industry >=", value, "counterpartyIndustry");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIndustryLessThan(String value) {
            addCriterion("counterparty_industry <", value, "counterpartyIndustry");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIndustryLessThanOrEqualTo(String value) {
            addCriterion("counterparty_industry <=", value, "counterpartyIndustry");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIndustryLike(String value) {
            addCriterion("counterparty_industry like", value, "counterpartyIndustry");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIndustryNotLike(String value) {
            addCriterion("counterparty_industry not like", value, "counterpartyIndustry");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIndustryIn(List<String> values) {
            addCriterion("counterparty_industry in", values, "counterpartyIndustry");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIndustryNotIn(List<String> values) {
            addCriterion("counterparty_industry not in", values, "counterpartyIndustry");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIndustryBetween(String value1, String value2) {
            addCriterion("counterparty_industry between", value1, value2, "counterpartyIndustry");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIndustryNotBetween(String value1, String value2) {
            addCriterion("counterparty_industry not between", value1, value2, "counterpartyIndustry");
            return (Criteria) this;
        }

        public Criteria andCreditLimitIsNull() {
            addCriterion("credit_limit is null");
            return (Criteria) this;
        }

        public Criteria andCreditLimitIsNotNull() {
            addCriterion("credit_limit is not null");
            return (Criteria) this;
        }

        public Criteria andCreditLimitEqualTo(Long value) {
            addCriterion("credit_limit =", value, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitNotEqualTo(Long value) {
            addCriterion("credit_limit <>", value, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGreaterThan(Long value) {
            addCriterion("credit_limit >", value, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("credit_limit >=", value, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitLessThan(Long value) {
            addCriterion("credit_limit <", value, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitLessThanOrEqualTo(Long value) {
            addCriterion("credit_limit <=", value, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitIn(List<Long> values) {
            addCriterion("credit_limit in", values, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitNotIn(List<Long> values) {
            addCriterion("credit_limit not in", values, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitBetween(Long value1, Long value2) {
            addCriterion("credit_limit between", value1, value2, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitNotBetween(Long value1, Long value2) {
            addCriterion("credit_limit not between", value1, value2, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditBalanceIsNull() {
            addCriterion("credit_balance is null");
            return (Criteria) this;
        }

        public Criteria andCreditBalanceIsNotNull() {
            addCriterion("credit_balance is not null");
            return (Criteria) this;
        }

        public Criteria andCreditBalanceEqualTo(Long value) {
            addCriterion("credit_balance =", value, "creditBalance");
            return (Criteria) this;
        }

        public Criteria andCreditBalanceNotEqualTo(Long value) {
            addCriterion("credit_balance <>", value, "creditBalance");
            return (Criteria) this;
        }

        public Criteria andCreditBalanceGreaterThan(Long value) {
            addCriterion("credit_balance >", value, "creditBalance");
            return (Criteria) this;
        }

        public Criteria andCreditBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("credit_balance >=", value, "creditBalance");
            return (Criteria) this;
        }

        public Criteria andCreditBalanceLessThan(Long value) {
            addCriterion("credit_balance <", value, "creditBalance");
            return (Criteria) this;
        }

        public Criteria andCreditBalanceLessThanOrEqualTo(Long value) {
            addCriterion("credit_balance <=", value, "creditBalance");
            return (Criteria) this;
        }

        public Criteria andCreditBalanceIn(List<Long> values) {
            addCriterion("credit_balance in", values, "creditBalance");
            return (Criteria) this;
        }

        public Criteria andCreditBalanceNotIn(List<Long> values) {
            addCriterion("credit_balance not in", values, "creditBalance");
            return (Criteria) this;
        }

        public Criteria andCreditBalanceBetween(Long value1, Long value2) {
            addCriterion("credit_balance between", value1, value2, "creditBalance");
            return (Criteria) this;
        }

        public Criteria andCreditBalanceNotBetween(Long value1, Long value2) {
            addCriterion("credit_balance not between", value1, value2, "creditBalance");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLimitIsNull() {
            addCriterion("counterparty_limit is null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLimitIsNotNull() {
            addCriterion("counterparty_limit is not null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLimitEqualTo(Long value) {
            addCriterion("counterparty_limit =", value, "counterpartyLimit");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLimitNotEqualTo(Long value) {
            addCriterion("counterparty_limit <>", value, "counterpartyLimit");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLimitGreaterThan(Long value) {
            addCriterion("counterparty_limit >", value, "counterpartyLimit");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("counterparty_limit >=", value, "counterpartyLimit");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLimitLessThan(Long value) {
            addCriterion("counterparty_limit <", value, "counterpartyLimit");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLimitLessThanOrEqualTo(Long value) {
            addCriterion("counterparty_limit <=", value, "counterpartyLimit");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLimitIn(List<Long> values) {
            addCriterion("counterparty_limit in", values, "counterpartyLimit");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLimitNotIn(List<Long> values) {
            addCriterion("counterparty_limit not in", values, "counterpartyLimit");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLimitBetween(Long value1, Long value2) {
            addCriterion("counterparty_limit between", value1, value2, "counterpartyLimit");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLimitNotBetween(Long value1, Long value2) {
            addCriterion("counterparty_limit not between", value1, value2, "counterpartyLimit");
            return (Criteria) this;
        }

        public Criteria andCounterpartyBalanceIsNull() {
            addCriterion("counterparty_balance is null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyBalanceIsNotNull() {
            addCriterion("counterparty_balance is not null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyBalanceEqualTo(Long value) {
            addCriterion("counterparty_balance =", value, "counterpartyBalance");
            return (Criteria) this;
        }

        public Criteria andCounterpartyBalanceNotEqualTo(Long value) {
            addCriterion("counterparty_balance <>", value, "counterpartyBalance");
            return (Criteria) this;
        }

        public Criteria andCounterpartyBalanceGreaterThan(Long value) {
            addCriterion("counterparty_balance >", value, "counterpartyBalance");
            return (Criteria) this;
        }

        public Criteria andCounterpartyBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("counterparty_balance >=", value, "counterpartyBalance");
            return (Criteria) this;
        }

        public Criteria andCounterpartyBalanceLessThan(Long value) {
            addCriterion("counterparty_balance <", value, "counterpartyBalance");
            return (Criteria) this;
        }

        public Criteria andCounterpartyBalanceLessThanOrEqualTo(Long value) {
            addCriterion("counterparty_balance <=", value, "counterpartyBalance");
            return (Criteria) this;
        }

        public Criteria andCounterpartyBalanceIn(List<Long> values) {
            addCriterion("counterparty_balance in", values, "counterpartyBalance");
            return (Criteria) this;
        }

        public Criteria andCounterpartyBalanceNotIn(List<Long> values) {
            addCriterion("counterparty_balance not in", values, "counterpartyBalance");
            return (Criteria) this;
        }

        public Criteria andCounterpartyBalanceBetween(Long value1, Long value2) {
            addCriterion("counterparty_balance between", value1, value2, "counterpartyBalance");
            return (Criteria) this;
        }

        public Criteria andCounterpartyBalanceNotBetween(Long value1, Long value2) {
            addCriterion("counterparty_balance not between", value1, value2, "counterpartyBalance");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountIsNull() {
            addCriterion("invoice_amount is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountIsNotNull() {
            addCriterion("invoice_amount is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountEqualTo(Long value) {
            addCriterion("invoice_amount =", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountNotEqualTo(Long value) {
            addCriterion("invoice_amount <>", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountGreaterThan(Long value) {
            addCriterion("invoice_amount >", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("invoice_amount >=", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountLessThan(Long value) {
            addCriterion("invoice_amount <", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountLessThanOrEqualTo(Long value) {
            addCriterion("invoice_amount <=", value, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountIn(List<Long> values) {
            addCriterion("invoice_amount in", values, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountNotIn(List<Long> values) {
            addCriterion("invoice_amount not in", values, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountBetween(Long value1, Long value2) {
            addCriterion("invoice_amount between", value1, value2, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceAmountNotBetween(Long value1, Long value2) {
            addCriterion("invoice_amount not between", value1, value2, "invoiceAmount");
            return (Criteria) this;
        }

        public Criteria andInvoiceBalanceIsNull() {
            addCriterion("invoice_balance is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceBalanceIsNotNull() {
            addCriterion("invoice_balance is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceBalanceEqualTo(Long value) {
            addCriterion("invoice_balance =", value, "invoiceBalance");
            return (Criteria) this;
        }

        public Criteria andInvoiceBalanceNotEqualTo(Long value) {
            addCriterion("invoice_balance <>", value, "invoiceBalance");
            return (Criteria) this;
        }

        public Criteria andInvoiceBalanceGreaterThan(Long value) {
            addCriterion("invoice_balance >", value, "invoiceBalance");
            return (Criteria) this;
        }

        public Criteria andInvoiceBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("invoice_balance >=", value, "invoiceBalance");
            return (Criteria) this;
        }

        public Criteria andInvoiceBalanceLessThan(Long value) {
            addCriterion("invoice_balance <", value, "invoiceBalance");
            return (Criteria) this;
        }

        public Criteria andInvoiceBalanceLessThanOrEqualTo(Long value) {
            addCriterion("invoice_balance <=", value, "invoiceBalance");
            return (Criteria) this;
        }

        public Criteria andInvoiceBalanceIn(List<Long> values) {
            addCriterion("invoice_balance in", values, "invoiceBalance");
            return (Criteria) this;
        }

        public Criteria andInvoiceBalanceNotIn(List<Long> values) {
            addCriterion("invoice_balance not in", values, "invoiceBalance");
            return (Criteria) this;
        }

        public Criteria andInvoiceBalanceBetween(Long value1, Long value2) {
            addCriterion("invoice_balance between", value1, value2, "invoiceBalance");
            return (Criteria) this;
        }

        public Criteria andInvoiceBalanceNotBetween(Long value1, Long value2) {
            addCriterion("invoice_balance not between", value1, value2, "invoiceBalance");
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

        public Criteria andSpecialProgramIdIsNull() {
            addCriterion("special_program_id is null");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdIsNotNull() {
            addCriterion("special_program_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdEqualTo(String value) {
            addCriterion("special_program_id =", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdNotEqualTo(String value) {
            addCriterion("special_program_id <>", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdGreaterThan(String value) {
            addCriterion("special_program_id >", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdGreaterThanOrEqualTo(String value) {
            addCriterion("special_program_id >=", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdLessThan(String value) {
            addCriterion("special_program_id <", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdLessThanOrEqualTo(String value) {
            addCriterion("special_program_id <=", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdLike(String value) {
            addCriterion("special_program_id like", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdNotLike(String value) {
            addCriterion("special_program_id not like", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdIn(List<String> values) {
            addCriterion("special_program_id in", values, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdNotIn(List<String> values) {
            addCriterion("special_program_id not in", values, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdBetween(String value1, String value2) {
            addCriterion("special_program_id between", value1, value2, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdNotBetween(String value1, String value2) {
            addCriterion("special_program_id not between", value1, value2, "specialProgramId");
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

        public Criteria andOperateCompanyNameIsNull() {
            addCriterion("operate_company_name is null");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameIsNotNull() {
            addCriterion("operate_company_name is not null");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameEqualTo(String value) {
            addCriterion("operate_company_name =", value, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameNotEqualTo(String value) {
            addCriterion("operate_company_name <>", value, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameGreaterThan(String value) {
            addCriterion("operate_company_name >", value, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("operate_company_name >=", value, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameLessThan(String value) {
            addCriterion("operate_company_name <", value, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("operate_company_name <=", value, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameLike(String value) {
            addCriterion("operate_company_name like", value, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameNotLike(String value) {
            addCriterion("operate_company_name not like", value, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameIn(List<String> values) {
            addCriterion("operate_company_name in", values, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameNotIn(List<String> values) {
            addCriterion("operate_company_name not in", values, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameBetween(String value1, String value2) {
            addCriterion("operate_company_name between", value1, value2, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyNameNotBetween(String value1, String value2) {
            addCriterion("operate_company_name not between", value1, value2, "operateCompanyName");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameIsNull() {
            addCriterion("operate_username is null");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameIsNotNull() {
            addCriterion("operate_username is not null");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameEqualTo(String value) {
            addCriterion("operate_username =", value, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameNotEqualTo(String value) {
            addCriterion("operate_username <>", value, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameGreaterThan(String value) {
            addCriterion("operate_username >", value, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("operate_username >=", value, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameLessThan(String value) {
            addCriterion("operate_username <", value, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameLessThanOrEqualTo(String value) {
            addCriterion("operate_username <=", value, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameLike(String value) {
            addCriterion("operate_username like", value, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameNotLike(String value) {
            addCriterion("operate_username not like", value, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameIn(List<String> values) {
            addCriterion("operate_username in", values, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameNotIn(List<String> values) {
            addCriterion("operate_username not in", values, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameBetween(String value1, String value2) {
            addCriterion("operate_username between", value1, value2, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateUsernameNotBetween(String value1, String value2) {
            addCriterion("operate_username not between", value1, value2, "operateUsername");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIsNull() {
            addCriterion("operate_type is null");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIsNotNull() {
            addCriterion("operate_type is not null");
            return (Criteria) this;
        }

        public Criteria andOperateTypeEqualTo(String value) {
            addCriterion("operate_type =", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotEqualTo(String value) {
            addCriterion("operate_type <>", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeGreaterThan(String value) {
            addCriterion("operate_type >", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("operate_type >=", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLessThan(String value) {
            addCriterion("operate_type <", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLessThanOrEqualTo(String value) {
            addCriterion("operate_type <=", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLike(String value) {
            addCriterion("operate_type like", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotLike(String value) {
            addCriterion("operate_type not like", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIn(List<String> values) {
            addCriterion("operate_type in", values, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotIn(List<String> values) {
            addCriterion("operate_type not in", values, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeBetween(String value1, String value2) {
            addCriterion("operate_type between", value1, value2, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotBetween(String value1, String value2) {
            addCriterion("operate_type not between", value1, value2, "operateType");
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