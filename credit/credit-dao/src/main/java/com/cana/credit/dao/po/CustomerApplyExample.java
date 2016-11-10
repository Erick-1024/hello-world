package com.cana.credit.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public CustomerApplyExample() {
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

        public Criteria andTzCustomerIdIsNull() {
            addCriterion("tz_customer_id is null");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdIsNotNull() {
            addCriterion("tz_customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdEqualTo(String value) {
            addCriterion("tz_customer_id =", value, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdNotEqualTo(String value) {
            addCriterion("tz_customer_id <>", value, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdGreaterThan(String value) {
            addCriterion("tz_customer_id >", value, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdGreaterThanOrEqualTo(String value) {
            addCriterion("tz_customer_id >=", value, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdLessThan(String value) {
            addCriterion("tz_customer_id <", value, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdLessThanOrEqualTo(String value) {
            addCriterion("tz_customer_id <=", value, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdLike(String value) {
            addCriterion("tz_customer_id like", value, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdNotLike(String value) {
            addCriterion("tz_customer_id not like", value, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdIn(List<String> values) {
            addCriterion("tz_customer_id in", values, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdNotIn(List<String> values) {
            addCriterion("tz_customer_id not in", values, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdBetween(String value1, String value2) {
            addCriterion("tz_customer_id between", value1, value2, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerIdNotBetween(String value1, String value2) {
            addCriterion("tz_customer_id not between", value1, value2, "tzCustomerId");
            return (Criteria) this;
        }

        public Criteria andTzCustomerNameIsNull() {
            addCriterion("tz_customer_name is null");
            return (Criteria) this;
        }

        public Criteria andTzCustomerNameIsNotNull() {
            addCriterion("tz_customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andTzCustomerNameEqualTo(String value) {
            addCriterion("tz_customer_name =", value, "tzCustomerName");
            return (Criteria) this;
        }

        public Criteria andTzCustomerNameNotEqualTo(String value) {
            addCriterion("tz_customer_name <>", value, "tzCustomerName");
            return (Criteria) this;
        }

        public Criteria andTzCustomerNameGreaterThan(String value) {
            addCriterion("tz_customer_name >", value, "tzCustomerName");
            return (Criteria) this;
        }

        public Criteria andTzCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("tz_customer_name >=", value, "tzCustomerName");
            return (Criteria) this;
        }

        public Criteria andTzCustomerNameLessThan(String value) {
            addCriterion("tz_customer_name <", value, "tzCustomerName");
            return (Criteria) this;
        }

        public Criteria andTzCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("tz_customer_name <=", value, "tzCustomerName");
            return (Criteria) this;
        }

        public Criteria andTzCustomerNameLike(String value) {
            addCriterion("tz_customer_name like", value, "tzCustomerName");
            return (Criteria) this;
        }

        public Criteria andTzCustomerNameNotLike(String value) {
            addCriterion("tz_customer_name not like", value, "tzCustomerName");
            return (Criteria) this;
        }

        public Criteria andTzCustomerNameIn(List<String> values) {
            addCriterion("tz_customer_name in", values, "tzCustomerName");
            return (Criteria) this;
        }

        public Criteria andTzCustomerNameNotIn(List<String> values) {
            addCriterion("tz_customer_name not in", values, "tzCustomerName");
            return (Criteria) this;
        }

        public Criteria andTzCustomerNameBetween(String value1, String value2) {
            addCriterion("tz_customer_name between", value1, value2, "tzCustomerName");
            return (Criteria) this;
        }

        public Criteria andTzCustomerNameNotBetween(String value1, String value2) {
            addCriterion("tz_customer_name not between", value1, value2, "tzCustomerName");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNull() {
            addCriterion("apply_date is null");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNotNull() {
            addCriterion("apply_date is not null");
            return (Criteria) this;
        }

        public Criteria andApplyDateEqualTo(Date value) {
            addCriterion("apply_date =", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotEqualTo(Date value) {
            addCriterion("apply_date <>", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThan(Date value) {
            addCriterion("apply_date >", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThanOrEqualTo(Date value) {
            addCriterion("apply_date >=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThan(Date value) {
            addCriterion("apply_date <", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThanOrEqualTo(Date value) {
            addCriterion("apply_date <=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateIn(List<Date> values) {
            addCriterion("apply_date in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotIn(List<Date> values) {
            addCriterion("apply_date not in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateBetween(Date value1, Date value2) {
            addCriterion("apply_date between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotBetween(Date value1, Date value2) {
            addCriterion("apply_date not between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIsNull() {
            addCriterion("real_control_person is null");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIsNotNull() {
            addCriterion("real_control_person is not null");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonEqualTo(String value) {
            addCriterion("real_control_person =", value, "realControlPerson");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonNotEqualTo(String value) {
            addCriterion("real_control_person <>", value, "realControlPerson");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonGreaterThan(String value) {
            addCriterion("real_control_person >", value, "realControlPerson");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonGreaterThanOrEqualTo(String value) {
            addCriterion("real_control_person >=", value, "realControlPerson");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonLessThan(String value) {
            addCriterion("real_control_person <", value, "realControlPerson");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonLessThanOrEqualTo(String value) {
            addCriterion("real_control_person <=", value, "realControlPerson");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonLike(String value) {
            addCriterion("real_control_person like", value, "realControlPerson");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonNotLike(String value) {
            addCriterion("real_control_person not like", value, "realControlPerson");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIn(List<String> values) {
            addCriterion("real_control_person in", values, "realControlPerson");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonNotIn(List<String> values) {
            addCriterion("real_control_person not in", values, "realControlPerson");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonBetween(String value1, String value2) {
            addCriterion("real_control_person between", value1, value2, "realControlPerson");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonNotBetween(String value1, String value2) {
            addCriterion("real_control_person not between", value1, value2, "realControlPerson");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitIsNull() {
            addCriterion("apply_credit_limit is null");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitIsNotNull() {
            addCriterion("apply_credit_limit is not null");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitEqualTo(Long value) {
            addCriterion("apply_credit_limit =", value, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitNotEqualTo(Long value) {
            addCriterion("apply_credit_limit <>", value, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitGreaterThan(Long value) {
            addCriterion("apply_credit_limit >", value, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("apply_credit_limit >=", value, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitLessThan(Long value) {
            addCriterion("apply_credit_limit <", value, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitLessThanOrEqualTo(Long value) {
            addCriterion("apply_credit_limit <=", value, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitIn(List<Long> values) {
            addCriterion("apply_credit_limit in", values, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitNotIn(List<Long> values) {
            addCriterion("apply_credit_limit not in", values, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitBetween(Long value1, Long value2) {
            addCriterion("apply_credit_limit between", value1, value2, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitNotBetween(Long value1, Long value2) {
            addCriterion("apply_credit_limit not between", value1, value2, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIsNull() {
            addCriterion("apply_type is null");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIsNotNull() {
            addCriterion("apply_type is not null");
            return (Criteria) this;
        }

        public Criteria andApplyTypeEqualTo(String value) {
            addCriterion("apply_type =", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotEqualTo(String value) {
            addCriterion("apply_type <>", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeGreaterThan(String value) {
            addCriterion("apply_type >", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("apply_type >=", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeLessThan(String value) {
            addCriterion("apply_type <", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeLessThanOrEqualTo(String value) {
            addCriterion("apply_type <=", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeLike(String value) {
            addCriterion("apply_type like", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotLike(String value) {
            addCriterion("apply_type not like", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIn(List<String> values) {
            addCriterion("apply_type in", values, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotIn(List<String> values) {
            addCriterion("apply_type not in", values, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeBetween(String value1, String value2) {
            addCriterion("apply_type between", value1, value2, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotBetween(String value1, String value2) {
            addCriterion("apply_type not between", value1, value2, "applyType");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoIsNull() {
            addCriterion("organization_no is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoIsNotNull() {
            addCriterion("organization_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoEqualTo(String value) {
            addCriterion("organization_no =", value, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoNotEqualTo(String value) {
            addCriterion("organization_no <>", value, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoGreaterThan(String value) {
            addCriterion("organization_no >", value, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoGreaterThanOrEqualTo(String value) {
            addCriterion("organization_no >=", value, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoLessThan(String value) {
            addCriterion("organization_no <", value, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoLessThanOrEqualTo(String value) {
            addCriterion("organization_no <=", value, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoLike(String value) {
            addCriterion("organization_no like", value, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoNotLike(String value) {
            addCriterion("organization_no not like", value, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoIn(List<String> values) {
            addCriterion("organization_no in", values, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoNotIn(List<String> values) {
            addCriterion("organization_no not in", values, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoBetween(String value1, String value2) {
            addCriterion("organization_no between", value1, value2, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoNotBetween(String value1, String value2) {
            addCriterion("organization_no not between", value1, value2, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdIsNull() {
            addCriterion("organization_media_id is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdIsNotNull() {
            addCriterion("organization_media_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdEqualTo(String value) {
            addCriterion("organization_media_id =", value, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdNotEqualTo(String value) {
            addCriterion("organization_media_id <>", value, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdGreaterThan(String value) {
            addCriterion("organization_media_id >", value, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdGreaterThanOrEqualTo(String value) {
            addCriterion("organization_media_id >=", value, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdLessThan(String value) {
            addCriterion("organization_media_id <", value, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdLessThanOrEqualTo(String value) {
            addCriterion("organization_media_id <=", value, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdLike(String value) {
            addCriterion("organization_media_id like", value, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdNotLike(String value) {
            addCriterion("organization_media_id not like", value, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdIn(List<String> values) {
            addCriterion("organization_media_id in", values, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdNotIn(List<String> values) {
            addCriterion("organization_media_id not in", values, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdBetween(String value1, String value2) {
            addCriterion("organization_media_id between", value1, value2, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdNotBetween(String value1, String value2) {
            addCriterion("organization_media_id not between", value1, value2, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoIsNull() {
            addCriterion("business_licence_no is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoIsNotNull() {
            addCriterion("business_licence_no is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoEqualTo(String value) {
            addCriterion("business_licence_no =", value, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoNotEqualTo(String value) {
            addCriterion("business_licence_no <>", value, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoGreaterThan(String value) {
            addCriterion("business_licence_no >", value, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoGreaterThanOrEqualTo(String value) {
            addCriterion("business_licence_no >=", value, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoLessThan(String value) {
            addCriterion("business_licence_no <", value, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoLessThanOrEqualTo(String value) {
            addCriterion("business_licence_no <=", value, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoLike(String value) {
            addCriterion("business_licence_no like", value, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoNotLike(String value) {
            addCriterion("business_licence_no not like", value, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoIn(List<String> values) {
            addCriterion("business_licence_no in", values, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoNotIn(List<String> values) {
            addCriterion("business_licence_no not in", values, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoBetween(String value1, String value2) {
            addCriterion("business_licence_no between", value1, value2, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoNotBetween(String value1, String value2) {
            addCriterion("business_licence_no not between", value1, value2, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdIsNull() {
            addCriterion("business_licence_media_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdIsNotNull() {
            addCriterion("business_licence_media_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdEqualTo(String value) {
            addCriterion("business_licence_media_id =", value, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdNotEqualTo(String value) {
            addCriterion("business_licence_media_id <>", value, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdGreaterThan(String value) {
            addCriterion("business_licence_media_id >", value, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdGreaterThanOrEqualTo(String value) {
            addCriterion("business_licence_media_id >=", value, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdLessThan(String value) {
            addCriterion("business_licence_media_id <", value, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdLessThanOrEqualTo(String value) {
            addCriterion("business_licence_media_id <=", value, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdLike(String value) {
            addCriterion("business_licence_media_id like", value, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdNotLike(String value) {
            addCriterion("business_licence_media_id not like", value, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdIn(List<String> values) {
            addCriterion("business_licence_media_id in", values, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdNotIn(List<String> values) {
            addCriterion("business_licence_media_id not in", values, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdBetween(String value1, String value2) {
            addCriterion("business_licence_media_id between", value1, value2, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdNotBetween(String value1, String value2) {
            addCriterion("business_licence_media_id not between", value1, value2, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoIsNull() {
            addCriterion("tax_registration_certificate_no is null");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoIsNotNull() {
            addCriterion("tax_registration_certificate_no is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoEqualTo(String value) {
            addCriterion("tax_registration_certificate_no =", value, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoNotEqualTo(String value) {
            addCriterion("tax_registration_certificate_no <>", value, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoGreaterThan(String value) {
            addCriterion("tax_registration_certificate_no >", value, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoGreaterThanOrEqualTo(String value) {
            addCriterion("tax_registration_certificate_no >=", value, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoLessThan(String value) {
            addCriterion("tax_registration_certificate_no <", value, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoLessThanOrEqualTo(String value) {
            addCriterion("tax_registration_certificate_no <=", value, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoLike(String value) {
            addCriterion("tax_registration_certificate_no like", value, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoNotLike(String value) {
            addCriterion("tax_registration_certificate_no not like", value, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoIn(List<String> values) {
            addCriterion("tax_registration_certificate_no in", values, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoNotIn(List<String> values) {
            addCriterion("tax_registration_certificate_no not in", values, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoBetween(String value1, String value2) {
            addCriterion("tax_registration_certificate_no between", value1, value2, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoNotBetween(String value1, String value2) {
            addCriterion("tax_registration_certificate_no not between", value1, value2, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdIsNull() {
            addCriterion("tax_registration_certificate_media_id is null");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdIsNotNull() {
            addCriterion("tax_registration_certificate_media_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdEqualTo(String value) {
            addCriterion("tax_registration_certificate_media_id =", value, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdNotEqualTo(String value) {
            addCriterion("tax_registration_certificate_media_id <>", value, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdGreaterThan(String value) {
            addCriterion("tax_registration_certificate_media_id >", value, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdGreaterThanOrEqualTo(String value) {
            addCriterion("tax_registration_certificate_media_id >=", value, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdLessThan(String value) {
            addCriterion("tax_registration_certificate_media_id <", value, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdLessThanOrEqualTo(String value) {
            addCriterion("tax_registration_certificate_media_id <=", value, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdLike(String value) {
            addCriterion("tax_registration_certificate_media_id like", value, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdNotLike(String value) {
            addCriterion("tax_registration_certificate_media_id not like", value, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdIn(List<String> values) {
            addCriterion("tax_registration_certificate_media_id in", values, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdNotIn(List<String> values) {
            addCriterion("tax_registration_certificate_media_id not in", values, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdBetween(String value1, String value2) {
            addCriterion("tax_registration_certificate_media_id between", value1, value2, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdNotBetween(String value1, String value2) {
            addCriterion("tax_registration_certificate_media_id not between", value1, value2, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdIsNull() {
            addCriterion("real_control_person_id is null");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdIsNotNull() {
            addCriterion("real_control_person_id is not null");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdEqualTo(String value) {
            addCriterion("real_control_person_id =", value, "realControlPersonId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdNotEqualTo(String value) {
            addCriterion("real_control_person_id <>", value, "realControlPersonId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdGreaterThan(String value) {
            addCriterion("real_control_person_id >", value, "realControlPersonId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdGreaterThanOrEqualTo(String value) {
            addCriterion("real_control_person_id >=", value, "realControlPersonId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdLessThan(String value) {
            addCriterion("real_control_person_id <", value, "realControlPersonId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdLessThanOrEqualTo(String value) {
            addCriterion("real_control_person_id <=", value, "realControlPersonId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdLike(String value) {
            addCriterion("real_control_person_id like", value, "realControlPersonId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdNotLike(String value) {
            addCriterion("real_control_person_id not like", value, "realControlPersonId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdIn(List<String> values) {
            addCriterion("real_control_person_id in", values, "realControlPersonId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdNotIn(List<String> values) {
            addCriterion("real_control_person_id not in", values, "realControlPersonId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdBetween(String value1, String value2) {
            addCriterion("real_control_person_id between", value1, value2, "realControlPersonId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdNotBetween(String value1, String value2) {
            addCriterion("real_control_person_id not between", value1, value2, "realControlPersonId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdHandheldFrontMediaIdIsNull() {
            addCriterion("real_control_person_id_handheld_front_media_id is null");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdHandheldFrontMediaIdIsNotNull() {
            addCriterion("real_control_person_id_handheld_front_media_id is not null");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdHandheldFrontMediaIdEqualTo(String value) {
            addCriterion("real_control_person_id_handheld_front_media_id =", value, "realControlPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdHandheldFrontMediaIdNotEqualTo(String value) {
            addCriterion("real_control_person_id_handheld_front_media_id <>", value, "realControlPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdHandheldFrontMediaIdGreaterThan(String value) {
            addCriterion("real_control_person_id_handheld_front_media_id >", value, "realControlPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdHandheldFrontMediaIdGreaterThanOrEqualTo(String value) {
            addCriterion("real_control_person_id_handheld_front_media_id >=", value, "realControlPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdHandheldFrontMediaIdLessThan(String value) {
            addCriterion("real_control_person_id_handheld_front_media_id <", value, "realControlPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdHandheldFrontMediaIdLessThanOrEqualTo(String value) {
            addCriterion("real_control_person_id_handheld_front_media_id <=", value, "realControlPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdHandheldFrontMediaIdLike(String value) {
            addCriterion("real_control_person_id_handheld_front_media_id like", value, "realControlPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdHandheldFrontMediaIdNotLike(String value) {
            addCriterion("real_control_person_id_handheld_front_media_id not like", value, "realControlPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdHandheldFrontMediaIdIn(List<String> values) {
            addCriterion("real_control_person_id_handheld_front_media_id in", values, "realControlPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdHandheldFrontMediaIdNotIn(List<String> values) {
            addCriterion("real_control_person_id_handheld_front_media_id not in", values, "realControlPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdHandheldFrontMediaIdBetween(String value1, String value2) {
            addCriterion("real_control_person_id_handheld_front_media_id between", value1, value2, "realControlPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andRealControlPersonIdHandheldFrontMediaIdNotBetween(String value1, String value2) {
            addCriterion("real_control_person_id_handheld_front_media_id not between", value1, value2, "realControlPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdIsNull() {
            addCriterion("legal_person_id is null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdIsNotNull() {
            addCriterion("legal_person_id is not null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdEqualTo(String value) {
            addCriterion("legal_person_id =", value, "legalPersonId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdNotEqualTo(String value) {
            addCriterion("legal_person_id <>", value, "legalPersonId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdGreaterThan(String value) {
            addCriterion("legal_person_id >", value, "legalPersonId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdGreaterThanOrEqualTo(String value) {
            addCriterion("legal_person_id >=", value, "legalPersonId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdLessThan(String value) {
            addCriterion("legal_person_id <", value, "legalPersonId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdLessThanOrEqualTo(String value) {
            addCriterion("legal_person_id <=", value, "legalPersonId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdLike(String value) {
            addCriterion("legal_person_id like", value, "legalPersonId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdNotLike(String value) {
            addCriterion("legal_person_id not like", value, "legalPersonId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdIn(List<String> values) {
            addCriterion("legal_person_id in", values, "legalPersonId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdNotIn(List<String> values) {
            addCriterion("legal_person_id not in", values, "legalPersonId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdBetween(String value1, String value2) {
            addCriterion("legal_person_id between", value1, value2, "legalPersonId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdNotBetween(String value1, String value2) {
            addCriterion("legal_person_id not between", value1, value2, "legalPersonId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdHandheldFrontMediaIdIsNull() {
            addCriterion("legal_person_id_handheld_front_media_id is null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdHandheldFrontMediaIdIsNotNull() {
            addCriterion("legal_person_id_handheld_front_media_id is not null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdHandheldFrontMediaIdEqualTo(String value) {
            addCriterion("legal_person_id_handheld_front_media_id =", value, "legalPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdHandheldFrontMediaIdNotEqualTo(String value) {
            addCriterion("legal_person_id_handheld_front_media_id <>", value, "legalPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdHandheldFrontMediaIdGreaterThan(String value) {
            addCriterion("legal_person_id_handheld_front_media_id >", value, "legalPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdHandheldFrontMediaIdGreaterThanOrEqualTo(String value) {
            addCriterion("legal_person_id_handheld_front_media_id >=", value, "legalPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdHandheldFrontMediaIdLessThan(String value) {
            addCriterion("legal_person_id_handheld_front_media_id <", value, "legalPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdHandheldFrontMediaIdLessThanOrEqualTo(String value) {
            addCriterion("legal_person_id_handheld_front_media_id <=", value, "legalPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdHandheldFrontMediaIdLike(String value) {
            addCriterion("legal_person_id_handheld_front_media_id like", value, "legalPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdHandheldFrontMediaIdNotLike(String value) {
            addCriterion("legal_person_id_handheld_front_media_id not like", value, "legalPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdHandheldFrontMediaIdIn(List<String> values) {
            addCriterion("legal_person_id_handheld_front_media_id in", values, "legalPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdHandheldFrontMediaIdNotIn(List<String> values) {
            addCriterion("legal_person_id_handheld_front_media_id not in", values, "legalPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdHandheldFrontMediaIdBetween(String value1, String value2) {
            addCriterion("legal_person_id_handheld_front_media_id between", value1, value2, "legalPersonIdHandheldFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdHandheldFrontMediaIdNotBetween(String value1, String value2) {
            addCriterion("legal_person_id_handheld_front_media_id not between", value1, value2, "legalPersonIdHandheldFrontMediaId");
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

        public Criteria andPhoneNumberIsNull() {
            addCriterion("phone_number is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNotNull() {
            addCriterion("phone_number is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberEqualTo(String value) {
            addCriterion("phone_number =", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotEqualTo(String value) {
            addCriterion("phone_number <>", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThan(String value) {
            addCriterion("phone_number >", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThanOrEqualTo(String value) {
            addCriterion("phone_number >=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThan(String value) {
            addCriterion("phone_number <", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThanOrEqualTo(String value) {
            addCriterion("phone_number <=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLike(String value) {
            addCriterion("phone_number like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotLike(String value) {
            addCriterion("phone_number not like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIn(List<String> values) {
            addCriterion("phone_number in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotIn(List<String> values) {
            addCriterion("phone_number not in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberBetween(String value1, String value2) {
            addCriterion("phone_number between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotBetween(String value1, String value2) {
            addCriterion("phone_number not between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andDownstreamCustomerTypeIsNull() {
            addCriterion("downstream_customer_type is null");
            return (Criteria) this;
        }

        public Criteria andDownstreamCustomerTypeIsNotNull() {
            addCriterion("downstream_customer_type is not null");
            return (Criteria) this;
        }

        public Criteria andDownstreamCustomerTypeEqualTo(String value) {
            addCriterion("downstream_customer_type =", value, "downstreamCustomerType");
            return (Criteria) this;
        }

        public Criteria andDownstreamCustomerTypeNotEqualTo(String value) {
            addCriterion("downstream_customer_type <>", value, "downstreamCustomerType");
            return (Criteria) this;
        }

        public Criteria andDownstreamCustomerTypeGreaterThan(String value) {
            addCriterion("downstream_customer_type >", value, "downstreamCustomerType");
            return (Criteria) this;
        }

        public Criteria andDownstreamCustomerTypeGreaterThanOrEqualTo(String value) {
            addCriterion("downstream_customer_type >=", value, "downstreamCustomerType");
            return (Criteria) this;
        }

        public Criteria andDownstreamCustomerTypeLessThan(String value) {
            addCriterion("downstream_customer_type <", value, "downstreamCustomerType");
            return (Criteria) this;
        }

        public Criteria andDownstreamCustomerTypeLessThanOrEqualTo(String value) {
            addCriterion("downstream_customer_type <=", value, "downstreamCustomerType");
            return (Criteria) this;
        }

        public Criteria andDownstreamCustomerTypeLike(String value) {
            addCriterion("downstream_customer_type like", value, "downstreamCustomerType");
            return (Criteria) this;
        }

        public Criteria andDownstreamCustomerTypeNotLike(String value) {
            addCriterion("downstream_customer_type not like", value, "downstreamCustomerType");
            return (Criteria) this;
        }

        public Criteria andDownstreamCustomerTypeIn(List<String> values) {
            addCriterion("downstream_customer_type in", values, "downstreamCustomerType");
            return (Criteria) this;
        }

        public Criteria andDownstreamCustomerTypeNotIn(List<String> values) {
            addCriterion("downstream_customer_type not in", values, "downstreamCustomerType");
            return (Criteria) this;
        }

        public Criteria andDownstreamCustomerTypeBetween(String value1, String value2) {
            addCriterion("downstream_customer_type between", value1, value2, "downstreamCustomerType");
            return (Criteria) this;
        }

        public Criteria andDownstreamCustomerTypeNotBetween(String value1, String value2) {
            addCriterion("downstream_customer_type not between", value1, value2, "downstreamCustomerType");
            return (Criteria) this;
        }

        public Criteria andDownstreamRepaymentAccountPeriodIsNull() {
            addCriterion("downstream_repayment_account_period is null");
            return (Criteria) this;
        }

        public Criteria andDownstreamRepaymentAccountPeriodIsNotNull() {
            addCriterion("downstream_repayment_account_period is not null");
            return (Criteria) this;
        }

        public Criteria andDownstreamRepaymentAccountPeriodEqualTo(String value) {
            addCriterion("downstream_repayment_account_period =", value, "downstreamRepaymentAccountPeriod");
            return (Criteria) this;
        }

        public Criteria andDownstreamRepaymentAccountPeriodNotEqualTo(String value) {
            addCriterion("downstream_repayment_account_period <>", value, "downstreamRepaymentAccountPeriod");
            return (Criteria) this;
        }

        public Criteria andDownstreamRepaymentAccountPeriodGreaterThan(String value) {
            addCriterion("downstream_repayment_account_period >", value, "downstreamRepaymentAccountPeriod");
            return (Criteria) this;
        }

        public Criteria andDownstreamRepaymentAccountPeriodGreaterThanOrEqualTo(String value) {
            addCriterion("downstream_repayment_account_period >=", value, "downstreamRepaymentAccountPeriod");
            return (Criteria) this;
        }

        public Criteria andDownstreamRepaymentAccountPeriodLessThan(String value) {
            addCriterion("downstream_repayment_account_period <", value, "downstreamRepaymentAccountPeriod");
            return (Criteria) this;
        }

        public Criteria andDownstreamRepaymentAccountPeriodLessThanOrEqualTo(String value) {
            addCriterion("downstream_repayment_account_period <=", value, "downstreamRepaymentAccountPeriod");
            return (Criteria) this;
        }

        public Criteria andDownstreamRepaymentAccountPeriodLike(String value) {
            addCriterion("downstream_repayment_account_period like", value, "downstreamRepaymentAccountPeriod");
            return (Criteria) this;
        }

        public Criteria andDownstreamRepaymentAccountPeriodNotLike(String value) {
            addCriterion("downstream_repayment_account_period not like", value, "downstreamRepaymentAccountPeriod");
            return (Criteria) this;
        }

        public Criteria andDownstreamRepaymentAccountPeriodIn(List<String> values) {
            addCriterion("downstream_repayment_account_period in", values, "downstreamRepaymentAccountPeriod");
            return (Criteria) this;
        }

        public Criteria andDownstreamRepaymentAccountPeriodNotIn(List<String> values) {
            addCriterion("downstream_repayment_account_period not in", values, "downstreamRepaymentAccountPeriod");
            return (Criteria) this;
        }

        public Criteria andDownstreamRepaymentAccountPeriodBetween(String value1, String value2) {
            addCriterion("downstream_repayment_account_period between", value1, value2, "downstreamRepaymentAccountPeriod");
            return (Criteria) this;
        }

        public Criteria andDownstreamRepaymentAccountPeriodNotBetween(String value1, String value2) {
            addCriterion("downstream_repayment_account_period not between", value1, value2, "downstreamRepaymentAccountPeriod");
            return (Criteria) this;
        }

        public Criteria andApplicantTypeIsNull() {
            addCriterion("applicant_type is null");
            return (Criteria) this;
        }

        public Criteria andApplicantTypeIsNotNull() {
            addCriterion("applicant_type is not null");
            return (Criteria) this;
        }

        public Criteria andApplicantTypeEqualTo(String value) {
            addCriterion("applicant_type =", value, "applicantType");
            return (Criteria) this;
        }

        public Criteria andApplicantTypeNotEqualTo(String value) {
            addCriterion("applicant_type <>", value, "applicantType");
            return (Criteria) this;
        }

        public Criteria andApplicantTypeGreaterThan(String value) {
            addCriterion("applicant_type >", value, "applicantType");
            return (Criteria) this;
        }

        public Criteria andApplicantTypeGreaterThanOrEqualTo(String value) {
            addCriterion("applicant_type >=", value, "applicantType");
            return (Criteria) this;
        }

        public Criteria andApplicantTypeLessThan(String value) {
            addCriterion("applicant_type <", value, "applicantType");
            return (Criteria) this;
        }

        public Criteria andApplicantTypeLessThanOrEqualTo(String value) {
            addCriterion("applicant_type <=", value, "applicantType");
            return (Criteria) this;
        }

        public Criteria andApplicantTypeLike(String value) {
            addCriterion("applicant_type like", value, "applicantType");
            return (Criteria) this;
        }

        public Criteria andApplicantTypeNotLike(String value) {
            addCriterion("applicant_type not like", value, "applicantType");
            return (Criteria) this;
        }

        public Criteria andApplicantTypeIn(List<String> values) {
            addCriterion("applicant_type in", values, "applicantType");
            return (Criteria) this;
        }

        public Criteria andApplicantTypeNotIn(List<String> values) {
            addCriterion("applicant_type not in", values, "applicantType");
            return (Criteria) this;
        }

        public Criteria andApplicantTypeBetween(String value1, String value2) {
            addCriterion("applicant_type between", value1, value2, "applicantType");
            return (Criteria) this;
        }

        public Criteria andApplicantTypeNotBetween(String value1, String value2) {
            addCriterion("applicant_type not between", value1, value2, "applicantType");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionMoneyIsNull() {
            addCriterion("enterprise_execution_money is null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionMoneyIsNotNull() {
            addCriterion("enterprise_execution_money is not null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionMoneyEqualTo(Long value) {
            addCriterion("enterprise_execution_money =", value, "enterpriseExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionMoneyNotEqualTo(Long value) {
            addCriterion("enterprise_execution_money <>", value, "enterpriseExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionMoneyGreaterThan(Long value) {
            addCriterion("enterprise_execution_money >", value, "enterpriseExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("enterprise_execution_money >=", value, "enterpriseExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionMoneyLessThan(Long value) {
            addCriterion("enterprise_execution_money <", value, "enterpriseExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionMoneyLessThanOrEqualTo(Long value) {
            addCriterion("enterprise_execution_money <=", value, "enterpriseExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionMoneyIn(List<Long> values) {
            addCriterion("enterprise_execution_money in", values, "enterpriseExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionMoneyNotIn(List<Long> values) {
            addCriterion("enterprise_execution_money not in", values, "enterpriseExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionMoneyBetween(Long value1, Long value2) {
            addCriterion("enterprise_execution_money between", value1, value2, "enterpriseExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionMoneyNotBetween(Long value1, Long value2) {
            addCriterion("enterprise_execution_money not between", value1, value2, "enterpriseExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionTimesIsNull() {
            addCriterion("enterprise_execution_times is null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionTimesIsNotNull() {
            addCriterion("enterprise_execution_times is not null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionTimesEqualTo(Integer value) {
            addCriterion("enterprise_execution_times =", value, "enterpriseExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionTimesNotEqualTo(Integer value) {
            addCriterion("enterprise_execution_times <>", value, "enterpriseExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionTimesGreaterThan(Integer value) {
            addCriterion("enterprise_execution_times >", value, "enterpriseExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("enterprise_execution_times >=", value, "enterpriseExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionTimesLessThan(Integer value) {
            addCriterion("enterprise_execution_times <", value, "enterpriseExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionTimesLessThanOrEqualTo(Integer value) {
            addCriterion("enterprise_execution_times <=", value, "enterpriseExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionTimesIn(List<Integer> values) {
            addCriterion("enterprise_execution_times in", values, "enterpriseExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionTimesNotIn(List<Integer> values) {
            addCriterion("enterprise_execution_times not in", values, "enterpriseExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionTimesBetween(Integer value1, Integer value2) {
            addCriterion("enterprise_execution_times between", value1, value2, "enterpriseExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andEnterpriseExecutionTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("enterprise_execution_times not between", value1, value2, "enterpriseExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionMoneyIsNull() {
            addCriterion("individual_execution_money is null");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionMoneyIsNotNull() {
            addCriterion("individual_execution_money is not null");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionMoneyEqualTo(Long value) {
            addCriterion("individual_execution_money =", value, "individualExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionMoneyNotEqualTo(Long value) {
            addCriterion("individual_execution_money <>", value, "individualExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionMoneyGreaterThan(Long value) {
            addCriterion("individual_execution_money >", value, "individualExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("individual_execution_money >=", value, "individualExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionMoneyLessThan(Long value) {
            addCriterion("individual_execution_money <", value, "individualExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionMoneyLessThanOrEqualTo(Long value) {
            addCriterion("individual_execution_money <=", value, "individualExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionMoneyIn(List<Long> values) {
            addCriterion("individual_execution_money in", values, "individualExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionMoneyNotIn(List<Long> values) {
            addCriterion("individual_execution_money not in", values, "individualExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionMoneyBetween(Long value1, Long value2) {
            addCriterion("individual_execution_money between", value1, value2, "individualExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionMoneyNotBetween(Long value1, Long value2) {
            addCriterion("individual_execution_money not between", value1, value2, "individualExecutionMoney");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionTimesIsNull() {
            addCriterion("individual_execution_times is null");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionTimesIsNotNull() {
            addCriterion("individual_execution_times is not null");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionTimesEqualTo(Integer value) {
            addCriterion("individual_execution_times =", value, "individualExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionTimesNotEqualTo(Integer value) {
            addCriterion("individual_execution_times <>", value, "individualExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionTimesGreaterThan(Integer value) {
            addCriterion("individual_execution_times >", value, "individualExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("individual_execution_times >=", value, "individualExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionTimesLessThan(Integer value) {
            addCriterion("individual_execution_times <", value, "individualExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionTimesLessThanOrEqualTo(Integer value) {
            addCriterion("individual_execution_times <=", value, "individualExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionTimesIn(List<Integer> values) {
            addCriterion("individual_execution_times in", values, "individualExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionTimesNotIn(List<Integer> values) {
            addCriterion("individual_execution_times not in", values, "individualExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionTimesBetween(Integer value1, Integer value2) {
            addCriterion("individual_execution_times between", value1, value2, "individualExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andIndividualExecutionTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("individual_execution_times not between", value1, value2, "individualExecutionTimes");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkIsNull() {
            addCriterion("negative_network is null");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkIsNotNull() {
            addCriterion("negative_network is not null");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkEqualTo(String value) {
            addCriterion("negative_network =", value, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkNotEqualTo(String value) {
            addCriterion("negative_network <>", value, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkGreaterThan(String value) {
            addCriterion("negative_network >", value, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkGreaterThanOrEqualTo(String value) {
            addCriterion("negative_network >=", value, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkLessThan(String value) {
            addCriterion("negative_network <", value, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkLessThanOrEqualTo(String value) {
            addCriterion("negative_network <=", value, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkLike(String value) {
            addCriterion("negative_network like", value, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkNotLike(String value) {
            addCriterion("negative_network not like", value, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkIn(List<String> values) {
            addCriterion("negative_network in", values, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkNotIn(List<String> values) {
            addCriterion("negative_network not in", values, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkBetween(String value1, String value2) {
            addCriterion("negative_network between", value1, value2, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkNotBetween(String value1, String value2) {
            addCriterion("negative_network not between", value1, value2, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckIsNull() {
            addCriterion("consistency_check is null");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckIsNotNull() {
            addCriterion("consistency_check is not null");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckEqualTo(Integer value) {
            addCriterion("consistency_check =", value, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckNotEqualTo(Integer value) {
            addCriterion("consistency_check <>", value, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckGreaterThan(Integer value) {
            addCriterion("consistency_check >", value, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckGreaterThanOrEqualTo(Integer value) {
            addCriterion("consistency_check >=", value, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckLessThan(Integer value) {
            addCriterion("consistency_check <", value, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckLessThanOrEqualTo(Integer value) {
            addCriterion("consistency_check <=", value, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckIn(List<Integer> values) {
            addCriterion("consistency_check in", values, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckNotIn(List<Integer> values) {
            addCriterion("consistency_check not in", values, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckBetween(Integer value1, Integer value2) {
            addCriterion("consistency_check between", value1, value2, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckNotBetween(Integer value1, Integer value2) {
            addCriterion("consistency_check not between", value1, value2, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksIsNull() {
            addCriterion("automatic_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksIsNotNull() {
            addCriterion("automatic_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksEqualTo(String value) {
            addCriterion("automatic_audit_remarks =", value, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksNotEqualTo(String value) {
            addCriterion("automatic_audit_remarks <>", value, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksGreaterThan(String value) {
            addCriterion("automatic_audit_remarks >", value, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("automatic_audit_remarks >=", value, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksLessThan(String value) {
            addCriterion("automatic_audit_remarks <", value, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("automatic_audit_remarks <=", value, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksLike(String value) {
            addCriterion("automatic_audit_remarks like", value, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksNotLike(String value) {
            addCriterion("automatic_audit_remarks not like", value, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksIn(List<String> values) {
            addCriterion("automatic_audit_remarks in", values, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksNotIn(List<String> values) {
            addCriterion("automatic_audit_remarks not in", values, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksBetween(String value1, String value2) {
            addCriterion("automatic_audit_remarks between", value1, value2, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("automatic_audit_remarks not between", value1, value2, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateIsNull() {
            addCriterion("access_automatic_state is null");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateIsNotNull() {
            addCriterion("access_automatic_state is not null");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateEqualTo(String value) {
            addCriterion("access_automatic_state =", value, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateNotEqualTo(String value) {
            addCriterion("access_automatic_state <>", value, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateGreaterThan(String value) {
            addCriterion("access_automatic_state >", value, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateGreaterThanOrEqualTo(String value) {
            addCriterion("access_automatic_state >=", value, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateLessThan(String value) {
            addCriterion("access_automatic_state <", value, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateLessThanOrEqualTo(String value) {
            addCriterion("access_automatic_state <=", value, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateLike(String value) {
            addCriterion("access_automatic_state like", value, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateNotLike(String value) {
            addCriterion("access_automatic_state not like", value, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateIn(List<String> values) {
            addCriterion("access_automatic_state in", values, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateNotIn(List<String> values) {
            addCriterion("access_automatic_state not in", values, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateBetween(String value1, String value2) {
            addCriterion("access_automatic_state between", value1, value2, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateNotBetween(String value1, String value2) {
            addCriterion("access_automatic_state not between", value1, value2, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoIsNull() {
            addCriterion("automatic_audit_rule_batch_no is null");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoIsNotNull() {
            addCriterion("automatic_audit_rule_batch_no is not null");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoEqualTo(Integer value) {
            addCriterion("automatic_audit_rule_batch_no =", value, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoNotEqualTo(Integer value) {
            addCriterion("automatic_audit_rule_batch_no <>", value, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoGreaterThan(Integer value) {
            addCriterion("automatic_audit_rule_batch_no >", value, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("automatic_audit_rule_batch_no >=", value, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoLessThan(Integer value) {
            addCriterion("automatic_audit_rule_batch_no <", value, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoLessThanOrEqualTo(Integer value) {
            addCriterion("automatic_audit_rule_batch_no <=", value, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoIn(List<Integer> values) {
            addCriterion("automatic_audit_rule_batch_no in", values, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoNotIn(List<Integer> values) {
            addCriterion("automatic_audit_rule_batch_no not in", values, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoBetween(Integer value1, Integer value2) {
            addCriterion("automatic_audit_rule_batch_no between", value1, value2, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoNotBetween(Integer value1, Integer value2) {
            addCriterion("automatic_audit_rule_batch_no not between", value1, value2, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditDataIsNull() {
            addCriterion("automatic_audit_data is null");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditDataIsNotNull() {
            addCriterion("automatic_audit_data is not null");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditDataEqualTo(String value) {
            addCriterion("automatic_audit_data =", value, "automaticAuditData");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditDataNotEqualTo(String value) {
            addCriterion("automatic_audit_data <>", value, "automaticAuditData");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditDataGreaterThan(String value) {
            addCriterion("automatic_audit_data >", value, "automaticAuditData");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditDataGreaterThanOrEqualTo(String value) {
            addCriterion("automatic_audit_data >=", value, "automaticAuditData");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditDataLessThan(String value) {
            addCriterion("automatic_audit_data <", value, "automaticAuditData");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditDataLessThanOrEqualTo(String value) {
            addCriterion("automatic_audit_data <=", value, "automaticAuditData");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditDataLike(String value) {
            addCriterion("automatic_audit_data like", value, "automaticAuditData");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditDataNotLike(String value) {
            addCriterion("automatic_audit_data not like", value, "automaticAuditData");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditDataIn(List<String> values) {
            addCriterion("automatic_audit_data in", values, "automaticAuditData");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditDataNotIn(List<String> values) {
            addCriterion("automatic_audit_data not in", values, "automaticAuditData");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditDataBetween(String value1, String value2) {
            addCriterion("automatic_audit_data between", value1, value2, "automaticAuditData");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditDataNotBetween(String value1, String value2) {
            addCriterion("automatic_audit_data not between", value1, value2, "automaticAuditData");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksIsNull() {
            addCriterion("manual_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksIsNotNull() {
            addCriterion("manual_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksEqualTo(String value) {
            addCriterion("manual_audit_remarks =", value, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksNotEqualTo(String value) {
            addCriterion("manual_audit_remarks <>", value, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksGreaterThan(String value) {
            addCriterion("manual_audit_remarks >", value, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("manual_audit_remarks >=", value, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksLessThan(String value) {
            addCriterion("manual_audit_remarks <", value, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("manual_audit_remarks <=", value, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksLike(String value) {
            addCriterion("manual_audit_remarks like", value, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksNotLike(String value) {
            addCriterion("manual_audit_remarks not like", value, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksIn(List<String> values) {
            addCriterion("manual_audit_remarks in", values, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksNotIn(List<String> values) {
            addCriterion("manual_audit_remarks not in", values, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksBetween(String value1, String value2) {
            addCriterion("manual_audit_remarks between", value1, value2, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("manual_audit_remarks not between", value1, value2, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateIsNull() {
            addCriterion("access_manual_state is null");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateIsNotNull() {
            addCriterion("access_manual_state is not null");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateEqualTo(String value) {
            addCriterion("access_manual_state =", value, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateNotEqualTo(String value) {
            addCriterion("access_manual_state <>", value, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateGreaterThan(String value) {
            addCriterion("access_manual_state >", value, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateGreaterThanOrEqualTo(String value) {
            addCriterion("access_manual_state >=", value, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateLessThan(String value) {
            addCriterion("access_manual_state <", value, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateLessThanOrEqualTo(String value) {
            addCriterion("access_manual_state <=", value, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateLike(String value) {
            addCriterion("access_manual_state like", value, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateNotLike(String value) {
            addCriterion("access_manual_state not like", value, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateIn(List<String> values) {
            addCriterion("access_manual_state in", values, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateNotIn(List<String> values) {
            addCriterion("access_manual_state not in", values, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateBetween(String value1, String value2) {
            addCriterion("access_manual_state between", value1, value2, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateNotBetween(String value1, String value2) {
            addCriterion("access_manual_state not between", value1, value2, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoIsNull() {
            addCriterion("manual_audit_rule_batch_no is null");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoIsNotNull() {
            addCriterion("manual_audit_rule_batch_no is not null");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoEqualTo(Integer value) {
            addCriterion("manual_audit_rule_batch_no =", value, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoNotEqualTo(Integer value) {
            addCriterion("manual_audit_rule_batch_no <>", value, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoGreaterThan(Integer value) {
            addCriterion("manual_audit_rule_batch_no >", value, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("manual_audit_rule_batch_no >=", value, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoLessThan(Integer value) {
            addCriterion("manual_audit_rule_batch_no <", value, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoLessThanOrEqualTo(Integer value) {
            addCriterion("manual_audit_rule_batch_no <=", value, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoIn(List<Integer> values) {
            addCriterion("manual_audit_rule_batch_no in", values, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoNotIn(List<Integer> values) {
            addCriterion("manual_audit_rule_batch_no not in", values, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoBetween(Integer value1, Integer value2) {
            addCriterion("manual_audit_rule_batch_no between", value1, value2, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoNotBetween(Integer value1, Integer value2) {
            addCriterion("manual_audit_rule_batch_no not between", value1, value2, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateIsNull() {
            addCriterion("credit_limit_generate_state is null");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateIsNotNull() {
            addCriterion("credit_limit_generate_state is not null");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateEqualTo(String value) {
            addCriterion("credit_limit_generate_state =", value, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateNotEqualTo(String value) {
            addCriterion("credit_limit_generate_state <>", value, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateGreaterThan(String value) {
            addCriterion("credit_limit_generate_state >", value, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateGreaterThanOrEqualTo(String value) {
            addCriterion("credit_limit_generate_state >=", value, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateLessThan(String value) {
            addCriterion("credit_limit_generate_state <", value, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateLessThanOrEqualTo(String value) {
            addCriterion("credit_limit_generate_state <=", value, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateLike(String value) {
            addCriterion("credit_limit_generate_state like", value, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateNotLike(String value) {
            addCriterion("credit_limit_generate_state not like", value, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateIn(List<String> values) {
            addCriterion("credit_limit_generate_state in", values, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateNotIn(List<String> values) {
            addCriterion("credit_limit_generate_state not in", values, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateBetween(String value1, String value2) {
            addCriterion("credit_limit_generate_state between", value1, value2, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateNotBetween(String value1, String value2) {
            addCriterion("credit_limit_generate_state not between", value1, value2, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andAuditorIdIsNull() {
            addCriterion("auditor_id is null");
            return (Criteria) this;
        }

        public Criteria andAuditorIdIsNotNull() {
            addCriterion("auditor_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuditorIdEqualTo(String value) {
            addCriterion("auditor_id =", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotEqualTo(String value) {
            addCriterion("auditor_id <>", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdGreaterThan(String value) {
            addCriterion("auditor_id >", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdGreaterThanOrEqualTo(String value) {
            addCriterion("auditor_id >=", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdLessThan(String value) {
            addCriterion("auditor_id <", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdLessThanOrEqualTo(String value) {
            addCriterion("auditor_id <=", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdLike(String value) {
            addCriterion("auditor_id like", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotLike(String value) {
            addCriterion("auditor_id not like", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdIn(List<String> values) {
            addCriterion("auditor_id in", values, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotIn(List<String> values) {
            addCriterion("auditor_id not in", values, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdBetween(String value1, String value2) {
            addCriterion("auditor_id between", value1, value2, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotBetween(String value1, String value2) {
            addCriterion("auditor_id not between", value1, value2, "auditorId");
            return (Criteria) this;
        }

        public Criteria andInWhitelistIsNull() {
            addCriterion("in_whitelist is null");
            return (Criteria) this;
        }

        public Criteria andInWhitelistIsNotNull() {
            addCriterion("in_whitelist is not null");
            return (Criteria) this;
        }

        public Criteria andInWhitelistEqualTo(Boolean value) {
            addCriterion("in_whitelist =", value, "inWhitelist");
            return (Criteria) this;
        }

        public Criteria andInWhitelistNotEqualTo(Boolean value) {
            addCriterion("in_whitelist <>", value, "inWhitelist");
            return (Criteria) this;
        }

        public Criteria andInWhitelistGreaterThan(Boolean value) {
            addCriterion("in_whitelist >", value, "inWhitelist");
            return (Criteria) this;
        }

        public Criteria andInWhitelistGreaterThanOrEqualTo(Boolean value) {
            addCriterion("in_whitelist >=", value, "inWhitelist");
            return (Criteria) this;
        }

        public Criteria andInWhitelistLessThan(Boolean value) {
            addCriterion("in_whitelist <", value, "inWhitelist");
            return (Criteria) this;
        }

        public Criteria andInWhitelistLessThanOrEqualTo(Boolean value) {
            addCriterion("in_whitelist <=", value, "inWhitelist");
            return (Criteria) this;
        }

        public Criteria andInWhitelistIn(List<Boolean> values) {
            addCriterion("in_whitelist in", values, "inWhitelist");
            return (Criteria) this;
        }

        public Criteria andInWhitelistNotIn(List<Boolean> values) {
            addCriterion("in_whitelist not in", values, "inWhitelist");
            return (Criteria) this;
        }

        public Criteria andInWhitelistBetween(Boolean value1, Boolean value2) {
            addCriterion("in_whitelist between", value1, value2, "inWhitelist");
            return (Criteria) this;
        }

        public Criteria andInWhitelistNotBetween(Boolean value1, Boolean value2) {
            addCriterion("in_whitelist not between", value1, value2, "inWhitelist");
            return (Criteria) this;
        }

        public Criteria andApproverIdIsNull() {
            addCriterion("approver_id is null");
            return (Criteria) this;
        }

        public Criteria andApproverIdIsNotNull() {
            addCriterion("approver_id is not null");
            return (Criteria) this;
        }

        public Criteria andApproverIdEqualTo(String value) {
            addCriterion("approver_id =", value, "approverId");
            return (Criteria) this;
        }

        public Criteria andApproverIdNotEqualTo(String value) {
            addCriterion("approver_id <>", value, "approverId");
            return (Criteria) this;
        }

        public Criteria andApproverIdGreaterThan(String value) {
            addCriterion("approver_id >", value, "approverId");
            return (Criteria) this;
        }

        public Criteria andApproverIdGreaterThanOrEqualTo(String value) {
            addCriterion("approver_id >=", value, "approverId");
            return (Criteria) this;
        }

        public Criteria andApproverIdLessThan(String value) {
            addCriterion("approver_id <", value, "approverId");
            return (Criteria) this;
        }

        public Criteria andApproverIdLessThanOrEqualTo(String value) {
            addCriterion("approver_id <=", value, "approverId");
            return (Criteria) this;
        }

        public Criteria andApproverIdLike(String value) {
            addCriterion("approver_id like", value, "approverId");
            return (Criteria) this;
        }

        public Criteria andApproverIdNotLike(String value) {
            addCriterion("approver_id not like", value, "approverId");
            return (Criteria) this;
        }

        public Criteria andApproverIdIn(List<String> values) {
            addCriterion("approver_id in", values, "approverId");
            return (Criteria) this;
        }

        public Criteria andApproverIdNotIn(List<String> values) {
            addCriterion("approver_id not in", values, "approverId");
            return (Criteria) this;
        }

        public Criteria andApproverIdBetween(String value1, String value2) {
            addCriterion("approver_id between", value1, value2, "approverId");
            return (Criteria) this;
        }

        public Criteria andApproverIdNotBetween(String value1, String value2) {
            addCriterion("approver_id not between", value1, value2, "approverId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIsNull() {
            addCriterion("legal_person is null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIsNotNull() {
            addCriterion("legal_person is not null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonEqualTo(String value) {
            addCriterion("legal_person =", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNotEqualTo(String value) {
            addCriterion("legal_person <>", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonGreaterThan(String value) {
            addCriterion("legal_person >", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonGreaterThanOrEqualTo(String value) {
            addCriterion("legal_person >=", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonLessThan(String value) {
            addCriterion("legal_person <", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonLessThanOrEqualTo(String value) {
            addCriterion("legal_person <=", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonLike(String value) {
            addCriterion("legal_person like", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNotLike(String value) {
            addCriterion("legal_person not like", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIn(List<String> values) {
            addCriterion("legal_person in", values, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNotIn(List<String> values) {
            addCriterion("legal_person not in", values, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonBetween(String value1, String value2) {
            addCriterion("legal_person between", value1, value2, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNotBetween(String value1, String value2) {
            addCriterion("legal_person not between", value1, value2, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andAuditNotifyUrlIsNull() {
            addCriterion("audit_notify_url is null");
            return (Criteria) this;
        }

        public Criteria andAuditNotifyUrlIsNotNull() {
            addCriterion("audit_notify_url is not null");
            return (Criteria) this;
        }

        public Criteria andAuditNotifyUrlEqualTo(String value) {
            addCriterion("audit_notify_url =", value, "auditNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andAuditNotifyUrlNotEqualTo(String value) {
            addCriterion("audit_notify_url <>", value, "auditNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andAuditNotifyUrlGreaterThan(String value) {
            addCriterion("audit_notify_url >", value, "auditNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andAuditNotifyUrlGreaterThanOrEqualTo(String value) {
            addCriterion("audit_notify_url >=", value, "auditNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andAuditNotifyUrlLessThan(String value) {
            addCriterion("audit_notify_url <", value, "auditNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andAuditNotifyUrlLessThanOrEqualTo(String value) {
            addCriterion("audit_notify_url <=", value, "auditNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andAuditNotifyUrlLike(String value) {
            addCriterion("audit_notify_url like", value, "auditNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andAuditNotifyUrlNotLike(String value) {
            addCriterion("audit_notify_url not like", value, "auditNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andAuditNotifyUrlIn(List<String> values) {
            addCriterion("audit_notify_url in", values, "auditNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andAuditNotifyUrlNotIn(List<String> values) {
            addCriterion("audit_notify_url not in", values, "auditNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andAuditNotifyUrlBetween(String value1, String value2) {
            addCriterion("audit_notify_url between", value1, value2, "auditNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andAuditNotifyUrlNotBetween(String value1, String value2) {
            addCriterion("audit_notify_url not between", value1, value2, "auditNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andLimitNotifyUrlIsNull() {
            addCriterion("limit_notify_url is null");
            return (Criteria) this;
        }

        public Criteria andLimitNotifyUrlIsNotNull() {
            addCriterion("limit_notify_url is not null");
            return (Criteria) this;
        }

        public Criteria andLimitNotifyUrlEqualTo(String value) {
            addCriterion("limit_notify_url =", value, "limitNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andLimitNotifyUrlNotEqualTo(String value) {
            addCriterion("limit_notify_url <>", value, "limitNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andLimitNotifyUrlGreaterThan(String value) {
            addCriterion("limit_notify_url >", value, "limitNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andLimitNotifyUrlGreaterThanOrEqualTo(String value) {
            addCriterion("limit_notify_url >=", value, "limitNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andLimitNotifyUrlLessThan(String value) {
            addCriterion("limit_notify_url <", value, "limitNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andLimitNotifyUrlLessThanOrEqualTo(String value) {
            addCriterion("limit_notify_url <=", value, "limitNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andLimitNotifyUrlLike(String value) {
            addCriterion("limit_notify_url like", value, "limitNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andLimitNotifyUrlNotLike(String value) {
            addCriterion("limit_notify_url not like", value, "limitNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andLimitNotifyUrlIn(List<String> values) {
            addCriterion("limit_notify_url in", values, "limitNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andLimitNotifyUrlNotIn(List<String> values) {
            addCriterion("limit_notify_url not in", values, "limitNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andLimitNotifyUrlBetween(String value1, String value2) {
            addCriterion("limit_notify_url between", value1, value2, "limitNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andLimitNotifyUrlNotBetween(String value1, String value2) {
            addCriterion("limit_notify_url not between", value1, value2, "limitNotifyUrl");
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