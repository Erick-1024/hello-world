package com.cana.asset.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UnderlyingAssetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public UnderlyingAssetExample() {
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

        public Criteria andAssetPoolStatusIsNull() {
            addCriterion("asset_pool_status is null");
            return (Criteria) this;
        }

        public Criteria andAssetPoolStatusIsNotNull() {
            addCriterion("asset_pool_status is not null");
            return (Criteria) this;
        }

        public Criteria andAssetPoolStatusEqualTo(String value) {
            addCriterion("asset_pool_status =", value, "assetPoolStatus");
            return (Criteria) this;
        }

        public Criteria andAssetPoolStatusNotEqualTo(String value) {
            addCriterion("asset_pool_status <>", value, "assetPoolStatus");
            return (Criteria) this;
        }

        public Criteria andAssetPoolStatusGreaterThan(String value) {
            addCriterion("asset_pool_status >", value, "assetPoolStatus");
            return (Criteria) this;
        }

        public Criteria andAssetPoolStatusGreaterThanOrEqualTo(String value) {
            addCriterion("asset_pool_status >=", value, "assetPoolStatus");
            return (Criteria) this;
        }

        public Criteria andAssetPoolStatusLessThan(String value) {
            addCriterion("asset_pool_status <", value, "assetPoolStatus");
            return (Criteria) this;
        }

        public Criteria andAssetPoolStatusLessThanOrEqualTo(String value) {
            addCriterion("asset_pool_status <=", value, "assetPoolStatus");
            return (Criteria) this;
        }

        public Criteria andAssetPoolStatusLike(String value) {
            addCriterion("asset_pool_status like", value, "assetPoolStatus");
            return (Criteria) this;
        }

        public Criteria andAssetPoolStatusNotLike(String value) {
            addCriterion("asset_pool_status not like", value, "assetPoolStatus");
            return (Criteria) this;
        }

        public Criteria andAssetPoolStatusIn(List<String> values) {
            addCriterion("asset_pool_status in", values, "assetPoolStatus");
            return (Criteria) this;
        }

        public Criteria andAssetPoolStatusNotIn(List<String> values) {
            addCriterion("asset_pool_status not in", values, "assetPoolStatus");
            return (Criteria) this;
        }

        public Criteria andAssetPoolStatusBetween(String value1, String value2) {
            addCriterion("asset_pool_status between", value1, value2, "assetPoolStatus");
            return (Criteria) this;
        }

        public Criteria andAssetPoolStatusNotBetween(String value1, String value2) {
            addCriterion("asset_pool_status not between", value1, value2, "assetPoolStatus");
            return (Criteria) this;
        }

        public Criteria andAssetSourceIsNull() {
            addCriterion("asset_source is null");
            return (Criteria) this;
        }

        public Criteria andAssetSourceIsNotNull() {
            addCriterion("asset_source is not null");
            return (Criteria) this;
        }

        public Criteria andAssetSourceEqualTo(String value) {
            addCriterion("asset_source =", value, "assetSource");
            return (Criteria) this;
        }

        public Criteria andAssetSourceNotEqualTo(String value) {
            addCriterion("asset_source <>", value, "assetSource");
            return (Criteria) this;
        }

        public Criteria andAssetSourceGreaterThan(String value) {
            addCriterion("asset_source >", value, "assetSource");
            return (Criteria) this;
        }

        public Criteria andAssetSourceGreaterThanOrEqualTo(String value) {
            addCriterion("asset_source >=", value, "assetSource");
            return (Criteria) this;
        }

        public Criteria andAssetSourceLessThan(String value) {
            addCriterion("asset_source <", value, "assetSource");
            return (Criteria) this;
        }

        public Criteria andAssetSourceLessThanOrEqualTo(String value) {
            addCriterion("asset_source <=", value, "assetSource");
            return (Criteria) this;
        }

        public Criteria andAssetSourceLike(String value) {
            addCriterion("asset_source like", value, "assetSource");
            return (Criteria) this;
        }

        public Criteria andAssetSourceNotLike(String value) {
            addCriterion("asset_source not like", value, "assetSource");
            return (Criteria) this;
        }

        public Criteria andAssetSourceIn(List<String> values) {
            addCriterion("asset_source in", values, "assetSource");
            return (Criteria) this;
        }

        public Criteria andAssetSourceNotIn(List<String> values) {
            addCriterion("asset_source not in", values, "assetSource");
            return (Criteria) this;
        }

        public Criteria andAssetSourceBetween(String value1, String value2) {
            addCriterion("asset_source between", value1, value2, "assetSource");
            return (Criteria) this;
        }

        public Criteria andAssetSourceNotBetween(String value1, String value2) {
            addCriterion("asset_source not between", value1, value2, "assetSource");
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

        public Criteria andCustomerRatingIsNull() {
            addCriterion("customer_rating is null");
            return (Criteria) this;
        }

        public Criteria andCustomerRatingIsNotNull() {
            addCriterion("customer_rating is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerRatingEqualTo(String value) {
            addCriterion("customer_rating =", value, "customerRating");
            return (Criteria) this;
        }

        public Criteria andCustomerRatingNotEqualTo(String value) {
            addCriterion("customer_rating <>", value, "customerRating");
            return (Criteria) this;
        }

        public Criteria andCustomerRatingGreaterThan(String value) {
            addCriterion("customer_rating >", value, "customerRating");
            return (Criteria) this;
        }

        public Criteria andCustomerRatingGreaterThanOrEqualTo(String value) {
            addCriterion("customer_rating >=", value, "customerRating");
            return (Criteria) this;
        }

        public Criteria andCustomerRatingLessThan(String value) {
            addCriterion("customer_rating <", value, "customerRating");
            return (Criteria) this;
        }

        public Criteria andCustomerRatingLessThanOrEqualTo(String value) {
            addCriterion("customer_rating <=", value, "customerRating");
            return (Criteria) this;
        }

        public Criteria andCustomerRatingLike(String value) {
            addCriterion("customer_rating like", value, "customerRating");
            return (Criteria) this;
        }

        public Criteria andCustomerRatingNotLike(String value) {
            addCriterion("customer_rating not like", value, "customerRating");
            return (Criteria) this;
        }

        public Criteria andCustomerRatingIn(List<String> values) {
            addCriterion("customer_rating in", values, "customerRating");
            return (Criteria) this;
        }

        public Criteria andCustomerRatingNotIn(List<String> values) {
            addCriterion("customer_rating not in", values, "customerRating");
            return (Criteria) this;
        }

        public Criteria andCustomerRatingBetween(String value1, String value2) {
            addCriterion("customer_rating between", value1, value2, "customerRating");
            return (Criteria) this;
        }

        public Criteria andCustomerRatingNotBetween(String value1, String value2) {
            addCriterion("customer_rating not between", value1, value2, "customerRating");
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

        public Criteria andCounterpartyRatingIsNull() {
            addCriterion("counterparty_rating is null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyRatingIsNotNull() {
            addCriterion("counterparty_rating is not null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyRatingEqualTo(String value) {
            addCriterion("counterparty_rating =", value, "counterpartyRating");
            return (Criteria) this;
        }

        public Criteria andCounterpartyRatingNotEqualTo(String value) {
            addCriterion("counterparty_rating <>", value, "counterpartyRating");
            return (Criteria) this;
        }

        public Criteria andCounterpartyRatingGreaterThan(String value) {
            addCriterion("counterparty_rating >", value, "counterpartyRating");
            return (Criteria) this;
        }

        public Criteria andCounterpartyRatingGreaterThanOrEqualTo(String value) {
            addCriterion("counterparty_rating >=", value, "counterpartyRating");
            return (Criteria) this;
        }

        public Criteria andCounterpartyRatingLessThan(String value) {
            addCriterion("counterparty_rating <", value, "counterpartyRating");
            return (Criteria) this;
        }

        public Criteria andCounterpartyRatingLessThanOrEqualTo(String value) {
            addCriterion("counterparty_rating <=", value, "counterpartyRating");
            return (Criteria) this;
        }

        public Criteria andCounterpartyRatingLike(String value) {
            addCriterion("counterparty_rating like", value, "counterpartyRating");
            return (Criteria) this;
        }

        public Criteria andCounterpartyRatingNotLike(String value) {
            addCriterion("counterparty_rating not like", value, "counterpartyRating");
            return (Criteria) this;
        }

        public Criteria andCounterpartyRatingIn(List<String> values) {
            addCriterion("counterparty_rating in", values, "counterpartyRating");
            return (Criteria) this;
        }

        public Criteria andCounterpartyRatingNotIn(List<String> values) {
            addCriterion("counterparty_rating not in", values, "counterpartyRating");
            return (Criteria) this;
        }

        public Criteria andCounterpartyRatingBetween(String value1, String value2) {
            addCriterion("counterparty_rating between", value1, value2, "counterpartyRating");
            return (Criteria) this;
        }

        public Criteria andCounterpartyRatingNotBetween(String value1, String value2) {
            addCriterion("counterparty_rating not between", value1, value2, "counterpartyRating");
            return (Criteria) this;
        }

        public Criteria andGuaranteeInfoIsNull() {
            addCriterion("guarantee_info is null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeInfoIsNotNull() {
            addCriterion("guarantee_info is not null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeInfoEqualTo(String value) {
            addCriterion("guarantee_info =", value, "guaranteeInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeInfoNotEqualTo(String value) {
            addCriterion("guarantee_info <>", value, "guaranteeInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeInfoGreaterThan(String value) {
            addCriterion("guarantee_info >", value, "guaranteeInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeInfoGreaterThanOrEqualTo(String value) {
            addCriterion("guarantee_info >=", value, "guaranteeInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeInfoLessThan(String value) {
            addCriterion("guarantee_info <", value, "guaranteeInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeInfoLessThanOrEqualTo(String value) {
            addCriterion("guarantee_info <=", value, "guaranteeInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeInfoLike(String value) {
            addCriterion("guarantee_info like", value, "guaranteeInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeInfoNotLike(String value) {
            addCriterion("guarantee_info not like", value, "guaranteeInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeInfoIn(List<String> values) {
            addCriterion("guarantee_info in", values, "guaranteeInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeInfoNotIn(List<String> values) {
            addCriterion("guarantee_info not in", values, "guaranteeInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeInfoBetween(String value1, String value2) {
            addCriterion("guarantee_info between", value1, value2, "guaranteeInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeInfoNotBetween(String value1, String value2) {
            addCriterion("guarantee_info not between", value1, value2, "guaranteeInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeIsNull() {
            addCriterion("guarantee_type is null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeIsNotNull() {
            addCriterion("guarantee_type is not null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeEqualTo(String value) {
            addCriterion("guarantee_type =", value, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeNotEqualTo(String value) {
            addCriterion("guarantee_type <>", value, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeGreaterThan(String value) {
            addCriterion("guarantee_type >", value, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("guarantee_type >=", value, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeLessThan(String value) {
            addCriterion("guarantee_type <", value, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeLessThanOrEqualTo(String value) {
            addCriterion("guarantee_type <=", value, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeLike(String value) {
            addCriterion("guarantee_type like", value, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeNotLike(String value) {
            addCriterion("guarantee_type not like", value, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeIn(List<String> values) {
            addCriterion("guarantee_type in", values, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeNotIn(List<String> values) {
            addCriterion("guarantee_type not in", values, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeBetween(String value1, String value2) {
            addCriterion("guarantee_type between", value1, value2, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeNotBetween(String value1, String value2) {
            addCriterion("guarantee_type not between", value1, value2, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyInfoIsNull() {
            addCriterion("guarantee_company_info is null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyInfoIsNotNull() {
            addCriterion("guarantee_company_info is not null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyInfoEqualTo(String value) {
            addCriterion("guarantee_company_info =", value, "guaranteeCompanyInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyInfoNotEqualTo(String value) {
            addCriterion("guarantee_company_info <>", value, "guaranteeCompanyInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyInfoGreaterThan(String value) {
            addCriterion("guarantee_company_info >", value, "guaranteeCompanyInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyInfoGreaterThanOrEqualTo(String value) {
            addCriterion("guarantee_company_info >=", value, "guaranteeCompanyInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyInfoLessThan(String value) {
            addCriterion("guarantee_company_info <", value, "guaranteeCompanyInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyInfoLessThanOrEqualTo(String value) {
            addCriterion("guarantee_company_info <=", value, "guaranteeCompanyInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyInfoLike(String value) {
            addCriterion("guarantee_company_info like", value, "guaranteeCompanyInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyInfoNotLike(String value) {
            addCriterion("guarantee_company_info not like", value, "guaranteeCompanyInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyInfoIn(List<String> values) {
            addCriterion("guarantee_company_info in", values, "guaranteeCompanyInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyInfoNotIn(List<String> values) {
            addCriterion("guarantee_company_info not in", values, "guaranteeCompanyInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyInfoBetween(String value1, String value2) {
            addCriterion("guarantee_company_info between", value1, value2, "guaranteeCompanyInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyInfoNotBetween(String value1, String value2) {
            addCriterion("guarantee_company_info not between", value1, value2, "guaranteeCompanyInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyTypeIsNull() {
            addCriterion("guarantee_company_type is null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyTypeIsNotNull() {
            addCriterion("guarantee_company_type is not null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyTypeEqualTo(String value) {
            addCriterion("guarantee_company_type =", value, "guaranteeCompanyType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyTypeNotEqualTo(String value) {
            addCriterion("guarantee_company_type <>", value, "guaranteeCompanyType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyTypeGreaterThan(String value) {
            addCriterion("guarantee_company_type >", value, "guaranteeCompanyType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("guarantee_company_type >=", value, "guaranteeCompanyType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyTypeLessThan(String value) {
            addCriterion("guarantee_company_type <", value, "guaranteeCompanyType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyTypeLessThanOrEqualTo(String value) {
            addCriterion("guarantee_company_type <=", value, "guaranteeCompanyType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyTypeLike(String value) {
            addCriterion("guarantee_company_type like", value, "guaranteeCompanyType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyTypeNotLike(String value) {
            addCriterion("guarantee_company_type not like", value, "guaranteeCompanyType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyTypeIn(List<String> values) {
            addCriterion("guarantee_company_type in", values, "guaranteeCompanyType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyTypeNotIn(List<String> values) {
            addCriterion("guarantee_company_type not in", values, "guaranteeCompanyType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyTypeBetween(String value1, String value2) {
            addCriterion("guarantee_company_type between", value1, value2, "guaranteeCompanyType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeCompanyTypeNotBetween(String value1, String value2) {
            addCriterion("guarantee_company_type not between", value1, value2, "guaranteeCompanyType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeGoodsNoIsNull() {
            addCriterion("guarantee_goods_no is null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeGoodsNoIsNotNull() {
            addCriterion("guarantee_goods_no is not null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeGoodsNoEqualTo(String value) {
            addCriterion("guarantee_goods_no =", value, "guaranteeGoodsNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeGoodsNoNotEqualTo(String value) {
            addCriterion("guarantee_goods_no <>", value, "guaranteeGoodsNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeGoodsNoGreaterThan(String value) {
            addCriterion("guarantee_goods_no >", value, "guaranteeGoodsNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeGoodsNoGreaterThanOrEqualTo(String value) {
            addCriterion("guarantee_goods_no >=", value, "guaranteeGoodsNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeGoodsNoLessThan(String value) {
            addCriterion("guarantee_goods_no <", value, "guaranteeGoodsNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeGoodsNoLessThanOrEqualTo(String value) {
            addCriterion("guarantee_goods_no <=", value, "guaranteeGoodsNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeGoodsNoLike(String value) {
            addCriterion("guarantee_goods_no like", value, "guaranteeGoodsNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeGoodsNoNotLike(String value) {
            addCriterion("guarantee_goods_no not like", value, "guaranteeGoodsNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeGoodsNoIn(List<String> values) {
            addCriterion("guarantee_goods_no in", values, "guaranteeGoodsNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeGoodsNoNotIn(List<String> values) {
            addCriterion("guarantee_goods_no not in", values, "guaranteeGoodsNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeGoodsNoBetween(String value1, String value2) {
            addCriterion("guarantee_goods_no between", value1, value2, "guaranteeGoodsNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeGoodsNoNotBetween(String value1, String value2) {
            addCriterion("guarantee_goods_no not between", value1, value2, "guaranteeGoodsNo");
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

        public Criteria andFiveLevelCategoryIsNull() {
            addCriterion("five_level_category is null");
            return (Criteria) this;
        }

        public Criteria andFiveLevelCategoryIsNotNull() {
            addCriterion("five_level_category is not null");
            return (Criteria) this;
        }

        public Criteria andFiveLevelCategoryEqualTo(String value) {
            addCriterion("five_level_category =", value, "fiveLevelCategory");
            return (Criteria) this;
        }

        public Criteria andFiveLevelCategoryNotEqualTo(String value) {
            addCriterion("five_level_category <>", value, "fiveLevelCategory");
            return (Criteria) this;
        }

        public Criteria andFiveLevelCategoryGreaterThan(String value) {
            addCriterion("five_level_category >", value, "fiveLevelCategory");
            return (Criteria) this;
        }

        public Criteria andFiveLevelCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("five_level_category >=", value, "fiveLevelCategory");
            return (Criteria) this;
        }

        public Criteria andFiveLevelCategoryLessThan(String value) {
            addCriterion("five_level_category <", value, "fiveLevelCategory");
            return (Criteria) this;
        }

        public Criteria andFiveLevelCategoryLessThanOrEqualTo(String value) {
            addCriterion("five_level_category <=", value, "fiveLevelCategory");
            return (Criteria) this;
        }

        public Criteria andFiveLevelCategoryLike(String value) {
            addCriterion("five_level_category like", value, "fiveLevelCategory");
            return (Criteria) this;
        }

        public Criteria andFiveLevelCategoryNotLike(String value) {
            addCriterion("five_level_category not like", value, "fiveLevelCategory");
            return (Criteria) this;
        }

        public Criteria andFiveLevelCategoryIn(List<String> values) {
            addCriterion("five_level_category in", values, "fiveLevelCategory");
            return (Criteria) this;
        }

        public Criteria andFiveLevelCategoryNotIn(List<String> values) {
            addCriterion("five_level_category not in", values, "fiveLevelCategory");
            return (Criteria) this;
        }

        public Criteria andFiveLevelCategoryBetween(String value1, String value2) {
            addCriterion("five_level_category between", value1, value2, "fiveLevelCategory");
            return (Criteria) this;
        }

        public Criteria andFiveLevelCategoryNotBetween(String value1, String value2) {
            addCriterion("five_level_category not between", value1, value2, "fiveLevelCategory");
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