package com.cana.asset.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public CustomerExample() {
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

        public Criteria andCustomerTypeIsNull() {
            addCriterion("customer_type is null");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIsNotNull() {
            addCriterion("customer_type is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeEqualTo(String value) {
            addCriterion("customer_type =", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNotEqualTo(String value) {
            addCriterion("customer_type <>", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeGreaterThan(String value) {
            addCriterion("customer_type >", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeGreaterThanOrEqualTo(String value) {
            addCriterion("customer_type >=", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeLessThan(String value) {
            addCriterion("customer_type <", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeLessThanOrEqualTo(String value) {
            addCriterion("customer_type <=", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeLike(String value) {
            addCriterion("customer_type like", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNotLike(String value) {
            addCriterion("customer_type not like", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIn(List<String> values) {
            addCriterion("customer_type in", values, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNotIn(List<String> values) {
            addCriterion("customer_type not in", values, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeBetween(String value1, String value2) {
            addCriterion("customer_type between", value1, value2, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNotBetween(String value1, String value2) {
            addCriterion("customer_type not between", value1, value2, "customerType");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNull() {
            addCriterion("contact_name is null");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNotNull() {
            addCriterion("contact_name is not null");
            return (Criteria) this;
        }

        public Criteria andContactNameEqualTo(String value) {
            addCriterion("contact_name =", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotEqualTo(String value) {
            addCriterion("contact_name <>", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThan(String value) {
            addCriterion("contact_name >", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThanOrEqualTo(String value) {
            addCriterion("contact_name >=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThan(String value) {
            addCriterion("contact_name <", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThanOrEqualTo(String value) {
            addCriterion("contact_name <=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLike(String value) {
            addCriterion("contact_name like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotLike(String value) {
            addCriterion("contact_name not like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameIn(List<String> values) {
            addCriterion("contact_name in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotIn(List<String> values) {
            addCriterion("contact_name not in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameBetween(String value1, String value2) {
            addCriterion("contact_name between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotBetween(String value1, String value2) {
            addCriterion("contact_name not between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andMobileNoIsNull() {
            addCriterion("mobile_no is null");
            return (Criteria) this;
        }

        public Criteria andMobileNoIsNotNull() {
            addCriterion("mobile_no is not null");
            return (Criteria) this;
        }

        public Criteria andMobileNoEqualTo(String value) {
            addCriterion("mobile_no =", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoNotEqualTo(String value) {
            addCriterion("mobile_no <>", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoGreaterThan(String value) {
            addCriterion("mobile_no >", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoGreaterThanOrEqualTo(String value) {
            addCriterion("mobile_no >=", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoLessThan(String value) {
            addCriterion("mobile_no <", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoLessThanOrEqualTo(String value) {
            addCriterion("mobile_no <=", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoLike(String value) {
            addCriterion("mobile_no like", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoNotLike(String value) {
            addCriterion("mobile_no not like", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoIn(List<String> values) {
            addCriterion("mobile_no in", values, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoNotIn(List<String> values) {
            addCriterion("mobile_no not in", values, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoBetween(String value1, String value2) {
            addCriterion("mobile_no between", value1, value2, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoNotBetween(String value1, String value2) {
            addCriterion("mobile_no not between", value1, value2, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMailIsNull() {
            addCriterion("mail is null");
            return (Criteria) this;
        }

        public Criteria andMailIsNotNull() {
            addCriterion("mail is not null");
            return (Criteria) this;
        }

        public Criteria andMailEqualTo(String value) {
            addCriterion("mail =", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotEqualTo(String value) {
            addCriterion("mail <>", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailGreaterThan(String value) {
            addCriterion("mail >", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailGreaterThanOrEqualTo(String value) {
            addCriterion("mail >=", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailLessThan(String value) {
            addCriterion("mail <", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailLessThanOrEqualTo(String value) {
            addCriterion("mail <=", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailLike(String value) {
            addCriterion("mail like", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotLike(String value) {
            addCriterion("mail not like", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailIn(List<String> values) {
            addCriterion("mail in", values, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotIn(List<String> values) {
            addCriterion("mail not in", values, "mail");
            return (Criteria) this;
        }

        public Criteria andMailBetween(String value1, String value2) {
            addCriterion("mail between", value1, value2, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotBetween(String value1, String value2) {
            addCriterion("mail not between", value1, value2, "mail");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIsNull() {
            addCriterion("company_address is null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIsNotNull() {
            addCriterion("company_address is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressEqualTo(String value) {
            addCriterion("company_address =", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotEqualTo(String value) {
            addCriterion("company_address <>", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressGreaterThan(String value) {
            addCriterion("company_address >", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressGreaterThanOrEqualTo(String value) {
            addCriterion("company_address >=", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLessThan(String value) {
            addCriterion("company_address <", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLessThanOrEqualTo(String value) {
            addCriterion("company_address <=", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLike(String value) {
            addCriterion("company_address like", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotLike(String value) {
            addCriterion("company_address not like", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIn(List<String> values) {
            addCriterion("company_address in", values, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotIn(List<String> values) {
            addCriterion("company_address not in", values, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressBetween(String value1, String value2) {
            addCriterion("company_address between", value1, value2, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotBetween(String value1, String value2) {
            addCriterion("company_address not between", value1, value2, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andEconomicCategoryIsNull() {
            addCriterion("economic_category is null");
            return (Criteria) this;
        }

        public Criteria andEconomicCategoryIsNotNull() {
            addCriterion("economic_category is not null");
            return (Criteria) this;
        }

        public Criteria andEconomicCategoryEqualTo(String value) {
            addCriterion("economic_category =", value, "economicCategory");
            return (Criteria) this;
        }

        public Criteria andEconomicCategoryNotEqualTo(String value) {
            addCriterion("economic_category <>", value, "economicCategory");
            return (Criteria) this;
        }

        public Criteria andEconomicCategoryGreaterThan(String value) {
            addCriterion("economic_category >", value, "economicCategory");
            return (Criteria) this;
        }

        public Criteria andEconomicCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("economic_category >=", value, "economicCategory");
            return (Criteria) this;
        }

        public Criteria andEconomicCategoryLessThan(String value) {
            addCriterion("economic_category <", value, "economicCategory");
            return (Criteria) this;
        }

        public Criteria andEconomicCategoryLessThanOrEqualTo(String value) {
            addCriterion("economic_category <=", value, "economicCategory");
            return (Criteria) this;
        }

        public Criteria andEconomicCategoryLike(String value) {
            addCriterion("economic_category like", value, "economicCategory");
            return (Criteria) this;
        }

        public Criteria andEconomicCategoryNotLike(String value) {
            addCriterion("economic_category not like", value, "economicCategory");
            return (Criteria) this;
        }

        public Criteria andEconomicCategoryIn(List<String> values) {
            addCriterion("economic_category in", values, "economicCategory");
            return (Criteria) this;
        }

        public Criteria andEconomicCategoryNotIn(List<String> values) {
            addCriterion("economic_category not in", values, "economicCategory");
            return (Criteria) this;
        }

        public Criteria andEconomicCategoryBetween(String value1, String value2) {
            addCriterion("economic_category between", value1, value2, "economicCategory");
            return (Criteria) this;
        }

        public Criteria andEconomicCategoryNotBetween(String value1, String value2) {
            addCriterion("economic_category not between", value1, value2, "economicCategory");
            return (Criteria) this;
        }

        public Criteria andIndustryIsNull() {
            addCriterion("industry is null");
            return (Criteria) this;
        }

        public Criteria andIndustryIsNotNull() {
            addCriterion("industry is not null");
            return (Criteria) this;
        }

        public Criteria andIndustryEqualTo(String value) {
            addCriterion("industry =", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotEqualTo(String value) {
            addCriterion("industry <>", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryGreaterThan(String value) {
            addCriterion("industry >", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryGreaterThanOrEqualTo(String value) {
            addCriterion("industry >=", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLessThan(String value) {
            addCriterion("industry <", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLessThanOrEqualTo(String value) {
            addCriterion("industry <=", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLike(String value) {
            addCriterion("industry like", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotLike(String value) {
            addCriterion("industry not like", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryIn(List<String> values) {
            addCriterion("industry in", values, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotIn(List<String> values) {
            addCriterion("industry not in", values, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryBetween(String value1, String value2) {
            addCriterion("industry between", value1, value2, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotBetween(String value1, String value2) {
            addCriterion("industry not between", value1, value2, "industry");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeIsNull() {
            addCriterion("business_licence_code is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeIsNotNull() {
            addCriterion("business_licence_code is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeEqualTo(String value) {
            addCriterion("business_licence_code =", value, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeNotEqualTo(String value) {
            addCriterion("business_licence_code <>", value, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeGreaterThan(String value) {
            addCriterion("business_licence_code >", value, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("business_licence_code >=", value, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeLessThan(String value) {
            addCriterion("business_licence_code <", value, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeLessThanOrEqualTo(String value) {
            addCriterion("business_licence_code <=", value, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeLike(String value) {
            addCriterion("business_licence_code like", value, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeNotLike(String value) {
            addCriterion("business_licence_code not like", value, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeIn(List<String> values) {
            addCriterion("business_licence_code in", values, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeNotIn(List<String> values) {
            addCriterion("business_licence_code not in", values, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeBetween(String value1, String value2) {
            addCriterion("business_licence_code between", value1, value2, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeNotBetween(String value1, String value2) {
            addCriterion("business_licence_code not between", value1, value2, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeExpiryDateIsNull() {
            addCriterion("business_licence_code_expiry_date is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeExpiryDateIsNotNull() {
            addCriterion("business_licence_code_expiry_date is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeExpiryDateEqualTo(Date value) {
            addCriterion("business_licence_code_expiry_date =", value, "businessLicenceCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeExpiryDateNotEqualTo(Date value) {
            addCriterion("business_licence_code_expiry_date <>", value, "businessLicenceCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeExpiryDateGreaterThan(Date value) {
            addCriterion("business_licence_code_expiry_date >", value, "businessLicenceCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeExpiryDateGreaterThanOrEqualTo(Date value) {
            addCriterion("business_licence_code_expiry_date >=", value, "businessLicenceCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeExpiryDateLessThan(Date value) {
            addCriterion("business_licence_code_expiry_date <", value, "businessLicenceCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeExpiryDateLessThanOrEqualTo(Date value) {
            addCriterion("business_licence_code_expiry_date <=", value, "businessLicenceCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeExpiryDateIn(List<Date> values) {
            addCriterion("business_licence_code_expiry_date in", values, "businessLicenceCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeExpiryDateNotIn(List<Date> values) {
            addCriterion("business_licence_code_expiry_date not in", values, "businessLicenceCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeExpiryDateBetween(Date value1, Date value2) {
            addCriterion("business_licence_code_expiry_date between", value1, value2, "businessLicenceCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeExpiryDateNotBetween(Date value1, Date value2) {
            addCriterion("business_licence_code_expiry_date not between", value1, value2, "businessLicenceCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeIsNull() {
            addCriterion("tax_registration_certificate_code is null");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeIsNotNull() {
            addCriterion("tax_registration_certificate_code is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeEqualTo(String value) {
            addCriterion("tax_registration_certificate_code =", value, "taxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeNotEqualTo(String value) {
            addCriterion("tax_registration_certificate_code <>", value, "taxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeGreaterThan(String value) {
            addCriterion("tax_registration_certificate_code >", value, "taxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeGreaterThanOrEqualTo(String value) {
            addCriterion("tax_registration_certificate_code >=", value, "taxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeLessThan(String value) {
            addCriterion("tax_registration_certificate_code <", value, "taxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeLessThanOrEqualTo(String value) {
            addCriterion("tax_registration_certificate_code <=", value, "taxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeLike(String value) {
            addCriterion("tax_registration_certificate_code like", value, "taxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeNotLike(String value) {
            addCriterion("tax_registration_certificate_code not like", value, "taxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeIn(List<String> values) {
            addCriterion("tax_registration_certificate_code in", values, "taxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeNotIn(List<String> values) {
            addCriterion("tax_registration_certificate_code not in", values, "taxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeBetween(String value1, String value2) {
            addCriterion("tax_registration_certificate_code between", value1, value2, "taxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeNotBetween(String value1, String value2) {
            addCriterion("tax_registration_certificate_code not between", value1, value2, "taxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeExpiryDateIsNull() {
            addCriterion("tax_registration_certificate_code_expiry_date is null");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeExpiryDateIsNotNull() {
            addCriterion("tax_registration_certificate_code_expiry_date is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeExpiryDateEqualTo(Date value) {
            addCriterion("tax_registration_certificate_code_expiry_date =", value, "taxRegistrationCertificateCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeExpiryDateNotEqualTo(Date value) {
            addCriterion("tax_registration_certificate_code_expiry_date <>", value, "taxRegistrationCertificateCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeExpiryDateGreaterThan(Date value) {
            addCriterion("tax_registration_certificate_code_expiry_date >", value, "taxRegistrationCertificateCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeExpiryDateGreaterThanOrEqualTo(Date value) {
            addCriterion("tax_registration_certificate_code_expiry_date >=", value, "taxRegistrationCertificateCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeExpiryDateLessThan(Date value) {
            addCriterion("tax_registration_certificate_code_expiry_date <", value, "taxRegistrationCertificateCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeExpiryDateLessThanOrEqualTo(Date value) {
            addCriterion("tax_registration_certificate_code_expiry_date <=", value, "taxRegistrationCertificateCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeExpiryDateIn(List<Date> values) {
            addCriterion("tax_registration_certificate_code_expiry_date in", values, "taxRegistrationCertificateCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeExpiryDateNotIn(List<Date> values) {
            addCriterion("tax_registration_certificate_code_expiry_date not in", values, "taxRegistrationCertificateCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeExpiryDateBetween(Date value1, Date value2) {
            addCriterion("tax_registration_certificate_code_expiry_date between", value1, value2, "taxRegistrationCertificateCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateCodeExpiryDateNotBetween(Date value1, Date value2) {
            addCriterion("tax_registration_certificate_code_expiry_date not between", value1, value2, "taxRegistrationCertificateCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeIsNull() {
            addCriterion("organization_code is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeIsNotNull() {
            addCriterion("organization_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeEqualTo(String value) {
            addCriterion("organization_code =", value, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeNotEqualTo(String value) {
            addCriterion("organization_code <>", value, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeGreaterThan(String value) {
            addCriterion("organization_code >", value, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeGreaterThanOrEqualTo(String value) {
            addCriterion("organization_code >=", value, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeLessThan(String value) {
            addCriterion("organization_code <", value, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeLessThanOrEqualTo(String value) {
            addCriterion("organization_code <=", value, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeLike(String value) {
            addCriterion("organization_code like", value, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeNotLike(String value) {
            addCriterion("organization_code not like", value, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeIn(List<String> values) {
            addCriterion("organization_code in", values, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeNotIn(List<String> values) {
            addCriterion("organization_code not in", values, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeBetween(String value1, String value2) {
            addCriterion("organization_code between", value1, value2, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeNotBetween(String value1, String value2) {
            addCriterion("organization_code not between", value1, value2, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeExpiryDateIsNull() {
            addCriterion("organization_code_expiry_date is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeExpiryDateIsNotNull() {
            addCriterion("organization_code_expiry_date is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeExpiryDateEqualTo(Date value) {
            addCriterion("organization_code_expiry_date =", value, "organizationCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeExpiryDateNotEqualTo(Date value) {
            addCriterion("organization_code_expiry_date <>", value, "organizationCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeExpiryDateGreaterThan(Date value) {
            addCriterion("organization_code_expiry_date >", value, "organizationCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeExpiryDateGreaterThanOrEqualTo(Date value) {
            addCriterion("organization_code_expiry_date >=", value, "organizationCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeExpiryDateLessThan(Date value) {
            addCriterion("organization_code_expiry_date <", value, "organizationCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeExpiryDateLessThanOrEqualTo(Date value) {
            addCriterion("organization_code_expiry_date <=", value, "organizationCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeExpiryDateIn(List<Date> values) {
            addCriterion("organization_code_expiry_date in", values, "organizationCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeExpiryDateNotIn(List<Date> values) {
            addCriterion("organization_code_expiry_date not in", values, "organizationCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeExpiryDateBetween(Date value1, Date value2) {
            addCriterion("organization_code_expiry_date between", value1, value2, "organizationCodeExpiryDate");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeExpiryDateNotBetween(Date value1, Date value2) {
            addCriterion("organization_code_expiry_date not between", value1, value2, "organizationCodeExpiryDate");
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

        public Criteria andEnterpriseMaterialStateIsNull() {
            addCriterion("enterprise_material_state is null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseMaterialStateIsNotNull() {
            addCriterion("enterprise_material_state is not null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseMaterialStateEqualTo(String value) {
            addCriterion("enterprise_material_state =", value, "enterpriseMaterialState");
            return (Criteria) this;
        }

        public Criteria andEnterpriseMaterialStateNotEqualTo(String value) {
            addCriterion("enterprise_material_state <>", value, "enterpriseMaterialState");
            return (Criteria) this;
        }

        public Criteria andEnterpriseMaterialStateGreaterThan(String value) {
            addCriterion("enterprise_material_state >", value, "enterpriseMaterialState");
            return (Criteria) this;
        }

        public Criteria andEnterpriseMaterialStateGreaterThanOrEqualTo(String value) {
            addCriterion("enterprise_material_state >=", value, "enterpriseMaterialState");
            return (Criteria) this;
        }

        public Criteria andEnterpriseMaterialStateLessThan(String value) {
            addCriterion("enterprise_material_state <", value, "enterpriseMaterialState");
            return (Criteria) this;
        }

        public Criteria andEnterpriseMaterialStateLessThanOrEqualTo(String value) {
            addCriterion("enterprise_material_state <=", value, "enterpriseMaterialState");
            return (Criteria) this;
        }

        public Criteria andEnterpriseMaterialStateLike(String value) {
            addCriterion("enterprise_material_state like", value, "enterpriseMaterialState");
            return (Criteria) this;
        }

        public Criteria andEnterpriseMaterialStateNotLike(String value) {
            addCriterion("enterprise_material_state not like", value, "enterpriseMaterialState");
            return (Criteria) this;
        }

        public Criteria andEnterpriseMaterialStateIn(List<String> values) {
            addCriterion("enterprise_material_state in", values, "enterpriseMaterialState");
            return (Criteria) this;
        }

        public Criteria andEnterpriseMaterialStateNotIn(List<String> values) {
            addCriterion("enterprise_material_state not in", values, "enterpriseMaterialState");
            return (Criteria) this;
        }

        public Criteria andEnterpriseMaterialStateBetween(String value1, String value2) {
            addCriterion("enterprise_material_state between", value1, value2, "enterpriseMaterialState");
            return (Criteria) this;
        }

        public Criteria andEnterpriseMaterialStateNotBetween(String value1, String value2) {
            addCriterion("enterprise_material_state not between", value1, value2, "enterpriseMaterialState");
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

        public Criteria andLegalRepresentativeIsNull() {
            addCriterion("legal_representative is null");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeIsNotNull() {
            addCriterion("legal_representative is not null");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeEqualTo(String value) {
            addCriterion("legal_representative =", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotEqualTo(String value) {
            addCriterion("legal_representative <>", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeGreaterThan(String value) {
            addCriterion("legal_representative >", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeGreaterThanOrEqualTo(String value) {
            addCriterion("legal_representative >=", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeLessThan(String value) {
            addCriterion("legal_representative <", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeLessThanOrEqualTo(String value) {
            addCriterion("legal_representative <=", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeLike(String value) {
            addCriterion("legal_representative like", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotLike(String value) {
            addCriterion("legal_representative not like", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeIn(List<String> values) {
            addCriterion("legal_representative in", values, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotIn(List<String> values) {
            addCriterion("legal_representative not in", values, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeBetween(String value1, String value2) {
            addCriterion("legal_representative between", value1, value2, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotBetween(String value1, String value2) {
            addCriterion("legal_representative not between", value1, value2, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIsNull() {
            addCriterion("registered_capital is null");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIsNotNull() {
            addCriterion("registered_capital is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalEqualTo(BigDecimal value) {
            addCriterion("registered_capital =", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalNotEqualTo(BigDecimal value) {
            addCriterion("registered_capital <>", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalGreaterThan(BigDecimal value) {
            addCriterion("registered_capital >", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("registered_capital >=", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalLessThan(BigDecimal value) {
            addCriterion("registered_capital <", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("registered_capital <=", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIn(List<BigDecimal> values) {
            addCriterion("registered_capital in", values, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalNotIn(List<BigDecimal> values) {
            addCriterion("registered_capital not in", values, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("registered_capital between", value1, value2, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("registered_capital not between", value1, value2, "registeredCapital");
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