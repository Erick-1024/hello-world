package com.cana.member.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public UserExample() {
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

        public Criteria andMasterIdIsNull() {
            addCriterion("master_id is null");
            return (Criteria) this;
        }

        public Criteria andMasterIdIsNotNull() {
            addCriterion("master_id is not null");
            return (Criteria) this;
        }

        public Criteria andMasterIdEqualTo(String value) {
            addCriterion("master_id =", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotEqualTo(String value) {
            addCriterion("master_id <>", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdGreaterThan(String value) {
            addCriterion("master_id >", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdGreaterThanOrEqualTo(String value) {
            addCriterion("master_id >=", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdLessThan(String value) {
            addCriterion("master_id <", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdLessThanOrEqualTo(String value) {
            addCriterion("master_id <=", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdLike(String value) {
            addCriterion("master_id like", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotLike(String value) {
            addCriterion("master_id not like", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdIn(List<String> values) {
            addCriterion("master_id in", values, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotIn(List<String> values) {
            addCriterion("master_id not in", values, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdBetween(String value1, String value2) {
            addCriterion("master_id between", value1, value2, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotBetween(String value1, String value2) {
            addCriterion("master_id not between", value1, value2, "masterId");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
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

        public Criteria andOrganizationCodeCertificateMediaIdIsNull() {
            addCriterion("organization_code_certificate_media_id is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeCertificateMediaIdIsNotNull() {
            addCriterion("organization_code_certificate_media_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeCertificateMediaIdEqualTo(String value) {
            addCriterion("organization_code_certificate_media_id =", value, "organizationCodeCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeCertificateMediaIdNotEqualTo(String value) {
            addCriterion("organization_code_certificate_media_id <>", value, "organizationCodeCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeCertificateMediaIdGreaterThan(String value) {
            addCriterion("organization_code_certificate_media_id >", value, "organizationCodeCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeCertificateMediaIdGreaterThanOrEqualTo(String value) {
            addCriterion("organization_code_certificate_media_id >=", value, "organizationCodeCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeCertificateMediaIdLessThan(String value) {
            addCriterion("organization_code_certificate_media_id <", value, "organizationCodeCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeCertificateMediaIdLessThanOrEqualTo(String value) {
            addCriterion("organization_code_certificate_media_id <=", value, "organizationCodeCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeCertificateMediaIdLike(String value) {
            addCriterion("organization_code_certificate_media_id like", value, "organizationCodeCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeCertificateMediaIdNotLike(String value) {
            addCriterion("organization_code_certificate_media_id not like", value, "organizationCodeCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeCertificateMediaIdIn(List<String> values) {
            addCriterion("organization_code_certificate_media_id in", values, "organizationCodeCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeCertificateMediaIdNotIn(List<String> values) {
            addCriterion("organization_code_certificate_media_id not in", values, "organizationCodeCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeCertificateMediaIdBetween(String value1, String value2) {
            addCriterion("organization_code_certificate_media_id between", value1, value2, "organizationCodeCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeCertificateMediaIdNotBetween(String value1, String value2) {
            addCriterion("organization_code_certificate_media_id not between", value1, value2, "organizationCodeCertificateMediaId");
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

        public Criteria andContactTelIsNull() {
            addCriterion("contact_Tel is null");
            return (Criteria) this;
        }

        public Criteria andContactTelIsNotNull() {
            addCriterion("contact_Tel is not null");
            return (Criteria) this;
        }

        public Criteria andContactTelEqualTo(String value) {
            addCriterion("contact_Tel =", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotEqualTo(String value) {
            addCriterion("contact_Tel <>", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelGreaterThan(String value) {
            addCriterion("contact_Tel >", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelGreaterThanOrEqualTo(String value) {
            addCriterion("contact_Tel >=", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelLessThan(String value) {
            addCriterion("contact_Tel <", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelLessThanOrEqualTo(String value) {
            addCriterion("contact_Tel <=", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelLike(String value) {
            addCriterion("contact_Tel like", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotLike(String value) {
            addCriterion("contact_Tel not like", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelIn(List<String> values) {
            addCriterion("contact_Tel in", values, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotIn(List<String> values) {
            addCriterion("contact_Tel not in", values, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelBetween(String value1, String value2) {
            addCriterion("contact_Tel between", value1, value2, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotBetween(String value1, String value2) {
            addCriterion("contact_Tel not between", value1, value2, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactMailIsNull() {
            addCriterion("contact_mail is null");
            return (Criteria) this;
        }

        public Criteria andContactMailIsNotNull() {
            addCriterion("contact_mail is not null");
            return (Criteria) this;
        }

        public Criteria andContactMailEqualTo(String value) {
            addCriterion("contact_mail =", value, "contactMail");
            return (Criteria) this;
        }

        public Criteria andContactMailNotEqualTo(String value) {
            addCriterion("contact_mail <>", value, "contactMail");
            return (Criteria) this;
        }

        public Criteria andContactMailGreaterThan(String value) {
            addCriterion("contact_mail >", value, "contactMail");
            return (Criteria) this;
        }

        public Criteria andContactMailGreaterThanOrEqualTo(String value) {
            addCriterion("contact_mail >=", value, "contactMail");
            return (Criteria) this;
        }

        public Criteria andContactMailLessThan(String value) {
            addCriterion("contact_mail <", value, "contactMail");
            return (Criteria) this;
        }

        public Criteria andContactMailLessThanOrEqualTo(String value) {
            addCriterion("contact_mail <=", value, "contactMail");
            return (Criteria) this;
        }

        public Criteria andContactMailLike(String value) {
            addCriterion("contact_mail like", value, "contactMail");
            return (Criteria) this;
        }

        public Criteria andContactMailNotLike(String value) {
            addCriterion("contact_mail not like", value, "contactMail");
            return (Criteria) this;
        }

        public Criteria andContactMailIn(List<String> values) {
            addCriterion("contact_mail in", values, "contactMail");
            return (Criteria) this;
        }

        public Criteria andContactMailNotIn(List<String> values) {
            addCriterion("contact_mail not in", values, "contactMail");
            return (Criteria) this;
        }

        public Criteria andContactMailBetween(String value1, String value2) {
            addCriterion("contact_mail between", value1, value2, "contactMail");
            return (Criteria) this;
        }

        public Criteria andContactMailNotBetween(String value1, String value2) {
            addCriterion("contact_mail not between", value1, value2, "contactMail");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNull() {
            addCriterion("user_type is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("user_type is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(String value) {
            addCriterion("user_type =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(String value) {
            addCriterion("user_type <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(String value) {
            addCriterion("user_type >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(String value) {
            addCriterion("user_type >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(String value) {
            addCriterion("user_type <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(String value) {
            addCriterion("user_type <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLike(String value) {
            addCriterion("user_type like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotLike(String value) {
            addCriterion("user_type not like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<String> values) {
            addCriterion("user_type in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<String> values) {
            addCriterion("user_type not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(String value1, String value2) {
            addCriterion("user_type between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(String value1, String value2) {
            addCriterion("user_type not between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserStatusIsNull() {
            addCriterion("user_status is null");
            return (Criteria) this;
        }

        public Criteria andUserStatusIsNotNull() {
            addCriterion("user_status is not null");
            return (Criteria) this;
        }

        public Criteria andUserStatusEqualTo(String value) {
            addCriterion("user_status =", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotEqualTo(String value) {
            addCriterion("user_status <>", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusGreaterThan(String value) {
            addCriterion("user_status >", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusGreaterThanOrEqualTo(String value) {
            addCriterion("user_status >=", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLessThan(String value) {
            addCriterion("user_status <", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLessThanOrEqualTo(String value) {
            addCriterion("user_status <=", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLike(String value) {
            addCriterion("user_status like", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotLike(String value) {
            addCriterion("user_status not like", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusIn(List<String> values) {
            addCriterion("user_status in", values, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotIn(List<String> values) {
            addCriterion("user_status not in", values, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusBetween(String value1, String value2) {
            addCriterion("user_status between", value1, value2, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotBetween(String value1, String value2) {
            addCriterion("user_status not between", value1, value2, "userStatus");
            return (Criteria) this;
        }

        public Criteria andJobTitleIsNull() {
            addCriterion("job_title is null");
            return (Criteria) this;
        }

        public Criteria andJobTitleIsNotNull() {
            addCriterion("job_title is not null");
            return (Criteria) this;
        }

        public Criteria andJobTitleEqualTo(String value) {
            addCriterion("job_title =", value, "jobTitle");
            return (Criteria) this;
        }

        public Criteria andJobTitleNotEqualTo(String value) {
            addCriterion("job_title <>", value, "jobTitle");
            return (Criteria) this;
        }

        public Criteria andJobTitleGreaterThan(String value) {
            addCriterion("job_title >", value, "jobTitle");
            return (Criteria) this;
        }

        public Criteria andJobTitleGreaterThanOrEqualTo(String value) {
            addCriterion("job_title >=", value, "jobTitle");
            return (Criteria) this;
        }

        public Criteria andJobTitleLessThan(String value) {
            addCriterion("job_title <", value, "jobTitle");
            return (Criteria) this;
        }

        public Criteria andJobTitleLessThanOrEqualTo(String value) {
            addCriterion("job_title <=", value, "jobTitle");
            return (Criteria) this;
        }

        public Criteria andJobTitleLike(String value) {
            addCriterion("job_title like", value, "jobTitle");
            return (Criteria) this;
        }

        public Criteria andJobTitleNotLike(String value) {
            addCriterion("job_title not like", value, "jobTitle");
            return (Criteria) this;
        }

        public Criteria andJobTitleIn(List<String> values) {
            addCriterion("job_title in", values, "jobTitle");
            return (Criteria) this;
        }

        public Criteria andJobTitleNotIn(List<String> values) {
            addCriterion("job_title not in", values, "jobTitle");
            return (Criteria) this;
        }

        public Criteria andJobTitleBetween(String value1, String value2) {
            addCriterion("job_title between", value1, value2, "jobTitle");
            return (Criteria) this;
        }

        public Criteria andJobTitleNotBetween(String value1, String value2) {
            addCriterion("job_title not between", value1, value2, "jobTitle");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNull() {
            addCriterion("role_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(String value) {
            addCriterion("role_id =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(String value) {
            addCriterion("role_id <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(String value) {
            addCriterion("role_id >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(String value) {
            addCriterion("role_id >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(String value) {
            addCriterion("role_id <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(String value) {
            addCriterion("role_id <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLike(String value) {
            addCriterion("role_id like", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotLike(String value) {
            addCriterion("role_id not like", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<String> values) {
            addCriterion("role_id in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<String> values) {
            addCriterion("role_id not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(String value1, String value2) {
            addCriterion("role_id between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(String value1, String value2) {
            addCriterion("role_id not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andTokenIsNull() {
            addCriterion("token is null");
            return (Criteria) this;
        }

        public Criteria andTokenIsNotNull() {
            addCriterion("token is not null");
            return (Criteria) this;
        }

        public Criteria andTokenEqualTo(String value) {
            addCriterion("token =", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotEqualTo(String value) {
            addCriterion("token <>", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThan(String value) {
            addCriterion("token >", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThanOrEqualTo(String value) {
            addCriterion("token >=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThan(String value) {
            addCriterion("token <", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThanOrEqualTo(String value) {
            addCriterion("token <=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLike(String value) {
            addCriterion("token like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotLike(String value) {
            addCriterion("token not like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenIn(List<String> values) {
            addCriterion("token in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotIn(List<String> values) {
            addCriterion("token not in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenBetween(String value1, String value2) {
            addCriterion("token between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotBetween(String value1, String value2) {
            addCriterion("token not between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andSignedinIsNull() {
            addCriterion("signedIn is null");
            return (Criteria) this;
        }

        public Criteria andSignedinIsNotNull() {
            addCriterion("signedIn is not null");
            return (Criteria) this;
        }

        public Criteria andSignedinEqualTo(Boolean value) {
            addCriterion("signedIn =", value, "signedin");
            return (Criteria) this;
        }

        public Criteria andSignedinNotEqualTo(Boolean value) {
            addCriterion("signedIn <>", value, "signedin");
            return (Criteria) this;
        }

        public Criteria andSignedinGreaterThan(Boolean value) {
            addCriterion("signedIn >", value, "signedin");
            return (Criteria) this;
        }

        public Criteria andSignedinGreaterThanOrEqualTo(Boolean value) {
            addCriterion("signedIn >=", value, "signedin");
            return (Criteria) this;
        }

        public Criteria andSignedinLessThan(Boolean value) {
            addCriterion("signedIn <", value, "signedin");
            return (Criteria) this;
        }

        public Criteria andSignedinLessThanOrEqualTo(Boolean value) {
            addCriterion("signedIn <=", value, "signedin");
            return (Criteria) this;
        }

        public Criteria andSignedinIn(List<Boolean> values) {
            addCriterion("signedIn in", values, "signedin");
            return (Criteria) this;
        }

        public Criteria andSignedinNotIn(List<Boolean> values) {
            addCriterion("signedIn not in", values, "signedin");
            return (Criteria) this;
        }

        public Criteria andSignedinBetween(Boolean value1, Boolean value2) {
            addCriterion("signedIn between", value1, value2, "signedin");
            return (Criteria) this;
        }

        public Criteria andSignedinNotBetween(Boolean value1, Boolean value2) {
            addCriterion("signedIn not between", value1, value2, "signedin");
            return (Criteria) this;
        }

        public Criteria andSigninTimeIsNull() {
            addCriterion("signIn_time is null");
            return (Criteria) this;
        }

        public Criteria andSigninTimeIsNotNull() {
            addCriterion("signIn_time is not null");
            return (Criteria) this;
        }

        public Criteria andSigninTimeEqualTo(Date value) {
            addCriterion("signIn_time =", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeNotEqualTo(Date value) {
            addCriterion("signIn_time <>", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeGreaterThan(Date value) {
            addCriterion("signIn_time >", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("signIn_time >=", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeLessThan(Date value) {
            addCriterion("signIn_time <", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeLessThanOrEqualTo(Date value) {
            addCriterion("signIn_time <=", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeIn(List<Date> values) {
            addCriterion("signIn_time in", values, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeNotIn(List<Date> values) {
            addCriterion("signIn_time not in", values, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeBetween(Date value1, Date value2) {
            addCriterion("signIn_time between", value1, value2, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeNotBetween(Date value1, Date value2) {
            addCriterion("signIn_time not between", value1, value2, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninIpIsNull() {
            addCriterion("signIn_ip is null");
            return (Criteria) this;
        }

        public Criteria andSigninIpIsNotNull() {
            addCriterion("signIn_ip is not null");
            return (Criteria) this;
        }

        public Criteria andSigninIpEqualTo(String value) {
            addCriterion("signIn_ip =", value, "signinIp");
            return (Criteria) this;
        }

        public Criteria andSigninIpNotEqualTo(String value) {
            addCriterion("signIn_ip <>", value, "signinIp");
            return (Criteria) this;
        }

        public Criteria andSigninIpGreaterThan(String value) {
            addCriterion("signIn_ip >", value, "signinIp");
            return (Criteria) this;
        }

        public Criteria andSigninIpGreaterThanOrEqualTo(String value) {
            addCriterion("signIn_ip >=", value, "signinIp");
            return (Criteria) this;
        }

        public Criteria andSigninIpLessThan(String value) {
            addCriterion("signIn_ip <", value, "signinIp");
            return (Criteria) this;
        }

        public Criteria andSigninIpLessThanOrEqualTo(String value) {
            addCriterion("signIn_ip <=", value, "signinIp");
            return (Criteria) this;
        }

        public Criteria andSigninIpLike(String value) {
            addCriterion("signIn_ip like", value, "signinIp");
            return (Criteria) this;
        }

        public Criteria andSigninIpNotLike(String value) {
            addCriterion("signIn_ip not like", value, "signinIp");
            return (Criteria) this;
        }

        public Criteria andSigninIpIn(List<String> values) {
            addCriterion("signIn_ip in", values, "signinIp");
            return (Criteria) this;
        }

        public Criteria andSigninIpNotIn(List<String> values) {
            addCriterion("signIn_ip not in", values, "signinIp");
            return (Criteria) this;
        }

        public Criteria andSigninIpBetween(String value1, String value2) {
            addCriterion("signIn_ip between", value1, value2, "signinIp");
            return (Criteria) this;
        }

        public Criteria andSigninIpNotBetween(String value1, String value2) {
            addCriterion("signIn_ip not between", value1, value2, "signinIp");
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

        public Criteria andAuditTimeIsNull() {
            addCriterion("audit_time is null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNotNull() {
            addCriterion("audit_time is not null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeEqualTo(Date value) {
            addCriterion("audit_time =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(Date value) {
            addCriterion("audit_time <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(Date value) {
            addCriterion("audit_time >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("audit_time >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(Date value) {
            addCriterion("audit_time <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(Date value) {
            addCriterion("audit_time <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<Date> values) {
            addCriterion("audit_time in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<Date> values) {
            addCriterion("audit_time not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(Date value1, Date value2) {
            addCriterion("audit_time between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(Date value1, Date value2) {
            addCriterion("audit_time not between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeIsNull() {
            addCriterion("security_code is null");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeIsNotNull() {
            addCriterion("security_code is not null");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeEqualTo(String value) {
            addCriterion("security_code =", value, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeNotEqualTo(String value) {
            addCriterion("security_code <>", value, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeGreaterThan(String value) {
            addCriterion("security_code >", value, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("security_code >=", value, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeLessThan(String value) {
            addCriterion("security_code <", value, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeLessThanOrEqualTo(String value) {
            addCriterion("security_code <=", value, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeLike(String value) {
            addCriterion("security_code like", value, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeNotLike(String value) {
            addCriterion("security_code not like", value, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeIn(List<String> values) {
            addCriterion("security_code in", values, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeNotIn(List<String> values) {
            addCriterion("security_code not in", values, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeBetween(String value1, String value2) {
            addCriterion("security_code between", value1, value2, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeNotBetween(String value1, String value2) {
            addCriterion("security_code not between", value1, value2, "securityCode");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeExpirationTimeIsNull() {
            addCriterion("security_code_expiration_time is null");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeExpirationTimeIsNotNull() {
            addCriterion("security_code_expiration_time is not null");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeExpirationTimeEqualTo(Date value) {
            addCriterion("security_code_expiration_time =", value, "securityCodeExpirationTime");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeExpirationTimeNotEqualTo(Date value) {
            addCriterion("security_code_expiration_time <>", value, "securityCodeExpirationTime");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeExpirationTimeGreaterThan(Date value) {
            addCriterion("security_code_expiration_time >", value, "securityCodeExpirationTime");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeExpirationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("security_code_expiration_time >=", value, "securityCodeExpirationTime");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeExpirationTimeLessThan(Date value) {
            addCriterion("security_code_expiration_time <", value, "securityCodeExpirationTime");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeExpirationTimeLessThanOrEqualTo(Date value) {
            addCriterion("security_code_expiration_time <=", value, "securityCodeExpirationTime");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeExpirationTimeIn(List<Date> values) {
            addCriterion("security_code_expiration_time in", values, "securityCodeExpirationTime");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeExpirationTimeNotIn(List<Date> values) {
            addCriterion("security_code_expiration_time not in", values, "securityCodeExpirationTime");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeExpirationTimeBetween(Date value1, Date value2) {
            addCriterion("security_code_expiration_time between", value1, value2, "securityCodeExpirationTime");
            return (Criteria) this;
        }

        public Criteria andSecurityCodeExpirationTimeNotBetween(Date value1, Date value2) {
            addCriterion("security_code_expiration_time not between", value1, value2, "securityCodeExpirationTime");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyIsNull() {
            addCriterion("agent_company is null");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyIsNotNull() {
            addCriterion("agent_company is not null");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyEqualTo(String value) {
            addCriterion("agent_company =", value, "agentCompany");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyNotEqualTo(String value) {
            addCriterion("agent_company <>", value, "agentCompany");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyGreaterThan(String value) {
            addCriterion("agent_company >", value, "agentCompany");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("agent_company >=", value, "agentCompany");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyLessThan(String value) {
            addCriterion("agent_company <", value, "agentCompany");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyLessThanOrEqualTo(String value) {
            addCriterion("agent_company <=", value, "agentCompany");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyLike(String value) {
            addCriterion("agent_company like", value, "agentCompany");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyNotLike(String value) {
            addCriterion("agent_company not like", value, "agentCompany");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyIn(List<String> values) {
            addCriterion("agent_company in", values, "agentCompany");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyNotIn(List<String> values) {
            addCriterion("agent_company not in", values, "agentCompany");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyBetween(String value1, String value2) {
            addCriterion("agent_company between", value1, value2, "agentCompany");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyNotBetween(String value1, String value2) {
            addCriterion("agent_company not between", value1, value2, "agentCompany");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardFrontMediaIdIsNull() {
            addCriterion("contact_identity_card_front_media_id is null");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardFrontMediaIdIsNotNull() {
            addCriterion("contact_identity_card_front_media_id is not null");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardFrontMediaIdEqualTo(String value) {
            addCriterion("contact_identity_card_front_media_id =", value, "contactIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardFrontMediaIdNotEqualTo(String value) {
            addCriterion("contact_identity_card_front_media_id <>", value, "contactIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardFrontMediaIdGreaterThan(String value) {
            addCriterion("contact_identity_card_front_media_id >", value, "contactIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardFrontMediaIdGreaterThanOrEqualTo(String value) {
            addCriterion("contact_identity_card_front_media_id >=", value, "contactIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardFrontMediaIdLessThan(String value) {
            addCriterion("contact_identity_card_front_media_id <", value, "contactIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardFrontMediaIdLessThanOrEqualTo(String value) {
            addCriterion("contact_identity_card_front_media_id <=", value, "contactIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardFrontMediaIdLike(String value) {
            addCriterion("contact_identity_card_front_media_id like", value, "contactIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardFrontMediaIdNotLike(String value) {
            addCriterion("contact_identity_card_front_media_id not like", value, "contactIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardFrontMediaIdIn(List<String> values) {
            addCriterion("contact_identity_card_front_media_id in", values, "contactIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardFrontMediaIdNotIn(List<String> values) {
            addCriterion("contact_identity_card_front_media_id not in", values, "contactIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardFrontMediaIdBetween(String value1, String value2) {
            addCriterion("contact_identity_card_front_media_id between", value1, value2, "contactIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardFrontMediaIdNotBetween(String value1, String value2) {
            addCriterion("contact_identity_card_front_media_id not between", value1, value2, "contactIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardBackMediaIdIsNull() {
            addCriterion("contact_identity_card_back_media_id is null");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardBackMediaIdIsNotNull() {
            addCriterion("contact_identity_card_back_media_id is not null");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardBackMediaIdEqualTo(String value) {
            addCriterion("contact_identity_card_back_media_id =", value, "contactIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardBackMediaIdNotEqualTo(String value) {
            addCriterion("contact_identity_card_back_media_id <>", value, "contactIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardBackMediaIdGreaterThan(String value) {
            addCriterion("contact_identity_card_back_media_id >", value, "contactIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardBackMediaIdGreaterThanOrEqualTo(String value) {
            addCriterion("contact_identity_card_back_media_id >=", value, "contactIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardBackMediaIdLessThan(String value) {
            addCriterion("contact_identity_card_back_media_id <", value, "contactIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardBackMediaIdLessThanOrEqualTo(String value) {
            addCriterion("contact_identity_card_back_media_id <=", value, "contactIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardBackMediaIdLike(String value) {
            addCriterion("contact_identity_card_back_media_id like", value, "contactIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardBackMediaIdNotLike(String value) {
            addCriterion("contact_identity_card_back_media_id not like", value, "contactIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardBackMediaIdIn(List<String> values) {
            addCriterion("contact_identity_card_back_media_id in", values, "contactIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardBackMediaIdNotIn(List<String> values) {
            addCriterion("contact_identity_card_back_media_id not in", values, "contactIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardBackMediaIdBetween(String value1, String value2) {
            addCriterion("contact_identity_card_back_media_id between", value1, value2, "contactIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andContactIdentityCardBackMediaIdNotBetween(String value1, String value2) {
            addCriterion("contact_identity_card_back_media_id not between", value1, value2, "contactIdentityCardBackMediaId");
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

        public Criteria andLegalPersonIdentityCardFrontMediaIdIsNull() {
            addCriterion("legal_person_identity_card_front_media_id is null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardFrontMediaIdIsNotNull() {
            addCriterion("legal_person_identity_card_front_media_id is not null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardFrontMediaIdEqualTo(String value) {
            addCriterion("legal_person_identity_card_front_media_id =", value, "legalPersonIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardFrontMediaIdNotEqualTo(String value) {
            addCriterion("legal_person_identity_card_front_media_id <>", value, "legalPersonIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardFrontMediaIdGreaterThan(String value) {
            addCriterion("legal_person_identity_card_front_media_id >", value, "legalPersonIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardFrontMediaIdGreaterThanOrEqualTo(String value) {
            addCriterion("legal_person_identity_card_front_media_id >=", value, "legalPersonIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardFrontMediaIdLessThan(String value) {
            addCriterion("legal_person_identity_card_front_media_id <", value, "legalPersonIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardFrontMediaIdLessThanOrEqualTo(String value) {
            addCriterion("legal_person_identity_card_front_media_id <=", value, "legalPersonIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardFrontMediaIdLike(String value) {
            addCriterion("legal_person_identity_card_front_media_id like", value, "legalPersonIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardFrontMediaIdNotLike(String value) {
            addCriterion("legal_person_identity_card_front_media_id not like", value, "legalPersonIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardFrontMediaIdIn(List<String> values) {
            addCriterion("legal_person_identity_card_front_media_id in", values, "legalPersonIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardFrontMediaIdNotIn(List<String> values) {
            addCriterion("legal_person_identity_card_front_media_id not in", values, "legalPersonIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardFrontMediaIdBetween(String value1, String value2) {
            addCriterion("legal_person_identity_card_front_media_id between", value1, value2, "legalPersonIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardFrontMediaIdNotBetween(String value1, String value2) {
            addCriterion("legal_person_identity_card_front_media_id not between", value1, value2, "legalPersonIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardBackMediaIdIsNull() {
            addCriterion("legal_person_identity_card_back_media_id is null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardBackMediaIdIsNotNull() {
            addCriterion("legal_person_identity_card_back_media_id is not null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardBackMediaIdEqualTo(String value) {
            addCriterion("legal_person_identity_card_back_media_id =", value, "legalPersonIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardBackMediaIdNotEqualTo(String value) {
            addCriterion("legal_person_identity_card_back_media_id <>", value, "legalPersonIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardBackMediaIdGreaterThan(String value) {
            addCriterion("legal_person_identity_card_back_media_id >", value, "legalPersonIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardBackMediaIdGreaterThanOrEqualTo(String value) {
            addCriterion("legal_person_identity_card_back_media_id >=", value, "legalPersonIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardBackMediaIdLessThan(String value) {
            addCriterion("legal_person_identity_card_back_media_id <", value, "legalPersonIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardBackMediaIdLessThanOrEqualTo(String value) {
            addCriterion("legal_person_identity_card_back_media_id <=", value, "legalPersonIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardBackMediaIdLike(String value) {
            addCriterion("legal_person_identity_card_back_media_id like", value, "legalPersonIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardBackMediaIdNotLike(String value) {
            addCriterion("legal_person_identity_card_back_media_id not like", value, "legalPersonIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardBackMediaIdIn(List<String> values) {
            addCriterion("legal_person_identity_card_back_media_id in", values, "legalPersonIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardBackMediaIdNotIn(List<String> values) {
            addCriterion("legal_person_identity_card_back_media_id not in", values, "legalPersonIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardBackMediaIdBetween(String value1, String value2) {
            addCriterion("legal_person_identity_card_back_media_id between", value1, value2, "legalPersonIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdentityCardBackMediaIdNotBetween(String value1, String value2) {
            addCriterion("legal_person_identity_card_back_media_id not between", value1, value2, "legalPersonIdentityCardBackMediaId");
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

        public Criteria andJobNoIsNull() {
            addCriterion("job_no is null");
            return (Criteria) this;
        }

        public Criteria andJobNoIsNotNull() {
            addCriterion("job_no is not null");
            return (Criteria) this;
        }

        public Criteria andJobNoEqualTo(String value) {
            addCriterion("job_no =", value, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoNotEqualTo(String value) {
            addCriterion("job_no <>", value, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoGreaterThan(String value) {
            addCriterion("job_no >", value, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoGreaterThanOrEqualTo(String value) {
            addCriterion("job_no >=", value, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoLessThan(String value) {
            addCriterion("job_no <", value, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoLessThanOrEqualTo(String value) {
            addCriterion("job_no <=", value, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoLike(String value) {
            addCriterion("job_no like", value, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoNotLike(String value) {
            addCriterion("job_no not like", value, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoIn(List<String> values) {
            addCriterion("job_no in", values, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoNotIn(List<String> values) {
            addCriterion("job_no not in", values, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoBetween(String value1, String value2) {
            addCriterion("job_no between", value1, value2, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoNotBetween(String value1, String value2) {
            addCriterion("job_no not between", value1, value2, "jobNo");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNull() {
            addCriterion("real_name is null");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNotNull() {
            addCriterion("real_name is not null");
            return (Criteria) this;
        }

        public Criteria andRealNameEqualTo(String value) {
            addCriterion("real_name =", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotEqualTo(String value) {
            addCriterion("real_name <>", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThan(String value) {
            addCriterion("real_name >", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThanOrEqualTo(String value) {
            addCriterion("real_name >=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThan(String value) {
            addCriterion("real_name <", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThanOrEqualTo(String value) {
            addCriterion("real_name <=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLike(String value) {
            addCriterion("real_name like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotLike(String value) {
            addCriterion("real_name not like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameIn(List<String> values) {
            addCriterion("real_name in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotIn(List<String> values) {
            addCriterion("real_name not in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameBetween(String value1, String value2) {
            addCriterion("real_name between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotBetween(String value1, String value2) {
            addCriterion("real_name not between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andPayPasswordIsNull() {
            addCriterion("pay_password is null");
            return (Criteria) this;
        }

        public Criteria andPayPasswordIsNotNull() {
            addCriterion("pay_password is not null");
            return (Criteria) this;
        }

        public Criteria andPayPasswordEqualTo(String value) {
            addCriterion("pay_password =", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotEqualTo(String value) {
            addCriterion("pay_password <>", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordGreaterThan(String value) {
            addCriterion("pay_password >", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("pay_password >=", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLessThan(String value) {
            addCriterion("pay_password <", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLessThanOrEqualTo(String value) {
            addCriterion("pay_password <=", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLike(String value) {
            addCriterion("pay_password like", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotLike(String value) {
            addCriterion("pay_password not like", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordIn(List<String> values) {
            addCriterion("pay_password in", values, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotIn(List<String> values) {
            addCriterion("pay_password not in", values, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordBetween(String value1, String value2) {
            addCriterion("pay_password between", value1, value2, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotBetween(String value1, String value2) {
            addCriterion("pay_password not between", value1, value2, "payPassword");
            return (Criteria) this;
        }

        public Criteria andGuideStatusIsNull() {
            addCriterion("guide_status is null");
            return (Criteria) this;
        }

        public Criteria andGuideStatusIsNotNull() {
            addCriterion("guide_status is not null");
            return (Criteria) this;
        }

        public Criteria andGuideStatusEqualTo(String value) {
            addCriterion("guide_status =", value, "guideStatus");
            return (Criteria) this;
        }

        public Criteria andGuideStatusNotEqualTo(String value) {
            addCriterion("guide_status <>", value, "guideStatus");
            return (Criteria) this;
        }

        public Criteria andGuideStatusGreaterThan(String value) {
            addCriterion("guide_status >", value, "guideStatus");
            return (Criteria) this;
        }

        public Criteria andGuideStatusGreaterThanOrEqualTo(String value) {
            addCriterion("guide_status >=", value, "guideStatus");
            return (Criteria) this;
        }

        public Criteria andGuideStatusLessThan(String value) {
            addCriterion("guide_status <", value, "guideStatus");
            return (Criteria) this;
        }

        public Criteria andGuideStatusLessThanOrEqualTo(String value) {
            addCriterion("guide_status <=", value, "guideStatus");
            return (Criteria) this;
        }

        public Criteria andGuideStatusLike(String value) {
            addCriterion("guide_status like", value, "guideStatus");
            return (Criteria) this;
        }

        public Criteria andGuideStatusNotLike(String value) {
            addCriterion("guide_status not like", value, "guideStatus");
            return (Criteria) this;
        }

        public Criteria andGuideStatusIn(List<String> values) {
            addCriterion("guide_status in", values, "guideStatus");
            return (Criteria) this;
        }

        public Criteria andGuideStatusNotIn(List<String> values) {
            addCriterion("guide_status not in", values, "guideStatus");
            return (Criteria) this;
        }

        public Criteria andGuideStatusBetween(String value1, String value2) {
            addCriterion("guide_status between", value1, value2, "guideStatus");
            return (Criteria) this;
        }

        public Criteria andGuideStatusNotBetween(String value1, String value2) {
            addCriterion("guide_status not between", value1, value2, "guideStatus");
            return (Criteria) this;
        }

        public Criteria andEmployeeTelIsNull() {
            addCriterion("employee_tel is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeTelIsNotNull() {
            addCriterion("employee_tel is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeTelEqualTo(String value) {
            addCriterion("employee_tel =", value, "employeeTel");
            return (Criteria) this;
        }

        public Criteria andEmployeeTelNotEqualTo(String value) {
            addCriterion("employee_tel <>", value, "employeeTel");
            return (Criteria) this;
        }

        public Criteria andEmployeeTelGreaterThan(String value) {
            addCriterion("employee_tel >", value, "employeeTel");
            return (Criteria) this;
        }

        public Criteria andEmployeeTelGreaterThanOrEqualTo(String value) {
            addCriterion("employee_tel >=", value, "employeeTel");
            return (Criteria) this;
        }

        public Criteria andEmployeeTelLessThan(String value) {
            addCriterion("employee_tel <", value, "employeeTel");
            return (Criteria) this;
        }

        public Criteria andEmployeeTelLessThanOrEqualTo(String value) {
            addCriterion("employee_tel <=", value, "employeeTel");
            return (Criteria) this;
        }

        public Criteria andEmployeeTelLike(String value) {
            addCriterion("employee_tel like", value, "employeeTel");
            return (Criteria) this;
        }

        public Criteria andEmployeeTelNotLike(String value) {
            addCriterion("employee_tel not like", value, "employeeTel");
            return (Criteria) this;
        }

        public Criteria andEmployeeTelIn(List<String> values) {
            addCriterion("employee_tel in", values, "employeeTel");
            return (Criteria) this;
        }

        public Criteria andEmployeeTelNotIn(List<String> values) {
            addCriterion("employee_tel not in", values, "employeeTel");
            return (Criteria) this;
        }

        public Criteria andEmployeeTelBetween(String value1, String value2) {
            addCriterion("employee_tel between", value1, value2, "employeeTel");
            return (Criteria) this;
        }

        public Criteria andEmployeeTelNotBetween(String value1, String value2) {
            addCriterion("employee_tel not between", value1, value2, "employeeTel");
            return (Criteria) this;
        }

        public Criteria andEmployeeMailIsNull() {
            addCriterion("employee_mail is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeMailIsNotNull() {
            addCriterion("employee_mail is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeMailEqualTo(String value) {
            addCriterion("employee_mail =", value, "employeeMail");
            return (Criteria) this;
        }

        public Criteria andEmployeeMailNotEqualTo(String value) {
            addCriterion("employee_mail <>", value, "employeeMail");
            return (Criteria) this;
        }

        public Criteria andEmployeeMailGreaterThan(String value) {
            addCriterion("employee_mail >", value, "employeeMail");
            return (Criteria) this;
        }

        public Criteria andEmployeeMailGreaterThanOrEqualTo(String value) {
            addCriterion("employee_mail >=", value, "employeeMail");
            return (Criteria) this;
        }

        public Criteria andEmployeeMailLessThan(String value) {
            addCriterion("employee_mail <", value, "employeeMail");
            return (Criteria) this;
        }

        public Criteria andEmployeeMailLessThanOrEqualTo(String value) {
            addCriterion("employee_mail <=", value, "employeeMail");
            return (Criteria) this;
        }

        public Criteria andEmployeeMailLike(String value) {
            addCriterion("employee_mail like", value, "employeeMail");
            return (Criteria) this;
        }

        public Criteria andEmployeeMailNotLike(String value) {
            addCriterion("employee_mail not like", value, "employeeMail");
            return (Criteria) this;
        }

        public Criteria andEmployeeMailIn(List<String> values) {
            addCriterion("employee_mail in", values, "employeeMail");
            return (Criteria) this;
        }

        public Criteria andEmployeeMailNotIn(List<String> values) {
            addCriterion("employee_mail not in", values, "employeeMail");
            return (Criteria) this;
        }

        public Criteria andEmployeeMailBetween(String value1, String value2) {
            addCriterion("employee_mail between", value1, value2, "employeeMail");
            return (Criteria) this;
        }

        public Criteria andEmployeeMailNotBetween(String value1, String value2) {
            addCriterion("employee_mail not between", value1, value2, "employeeMail");
            return (Criteria) this;
        }

        public Criteria andEmployeeJobTitleIsNull() {
            addCriterion("employee_job_title is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeJobTitleIsNotNull() {
            addCriterion("employee_job_title is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeJobTitleEqualTo(String value) {
            addCriterion("employee_job_title =", value, "employeeJobTitle");
            return (Criteria) this;
        }

        public Criteria andEmployeeJobTitleNotEqualTo(String value) {
            addCriterion("employee_job_title <>", value, "employeeJobTitle");
            return (Criteria) this;
        }

        public Criteria andEmployeeJobTitleGreaterThan(String value) {
            addCriterion("employee_job_title >", value, "employeeJobTitle");
            return (Criteria) this;
        }

        public Criteria andEmployeeJobTitleGreaterThanOrEqualTo(String value) {
            addCriterion("employee_job_title >=", value, "employeeJobTitle");
            return (Criteria) this;
        }

        public Criteria andEmployeeJobTitleLessThan(String value) {
            addCriterion("employee_job_title <", value, "employeeJobTitle");
            return (Criteria) this;
        }

        public Criteria andEmployeeJobTitleLessThanOrEqualTo(String value) {
            addCriterion("employee_job_title <=", value, "employeeJobTitle");
            return (Criteria) this;
        }

        public Criteria andEmployeeJobTitleLike(String value) {
            addCriterion("employee_job_title like", value, "employeeJobTitle");
            return (Criteria) this;
        }

        public Criteria andEmployeeJobTitleNotLike(String value) {
            addCriterion("employee_job_title not like", value, "employeeJobTitle");
            return (Criteria) this;
        }

        public Criteria andEmployeeJobTitleIn(List<String> values) {
            addCriterion("employee_job_title in", values, "employeeJobTitle");
            return (Criteria) this;
        }

        public Criteria andEmployeeJobTitleNotIn(List<String> values) {
            addCriterion("employee_job_title not in", values, "employeeJobTitle");
            return (Criteria) this;
        }

        public Criteria andEmployeeJobTitleBetween(String value1, String value2) {
            addCriterion("employee_job_title between", value1, value2, "employeeJobTitle");
            return (Criteria) this;
        }

        public Criteria andEmployeeJobTitleNotBetween(String value1, String value2) {
            addCriterion("employee_job_title not between", value1, value2, "employeeJobTitle");
            return (Criteria) this;
        }

        public Criteria andCertSubjectDnIsNull() {
            addCriterion("cert_subject_dn is null");
            return (Criteria) this;
        }

        public Criteria andCertSubjectDnIsNotNull() {
            addCriterion("cert_subject_dn is not null");
            return (Criteria) this;
        }

        public Criteria andCertSubjectDnEqualTo(String value) {
            addCriterion("cert_subject_dn =", value, "certSubjectDn");
            return (Criteria) this;
        }

        public Criteria andCertSubjectDnNotEqualTo(String value) {
            addCriterion("cert_subject_dn <>", value, "certSubjectDn");
            return (Criteria) this;
        }

        public Criteria andCertSubjectDnGreaterThan(String value) {
            addCriterion("cert_subject_dn >", value, "certSubjectDn");
            return (Criteria) this;
        }

        public Criteria andCertSubjectDnGreaterThanOrEqualTo(String value) {
            addCriterion("cert_subject_dn >=", value, "certSubjectDn");
            return (Criteria) this;
        }

        public Criteria andCertSubjectDnLessThan(String value) {
            addCriterion("cert_subject_dn <", value, "certSubjectDn");
            return (Criteria) this;
        }

        public Criteria andCertSubjectDnLessThanOrEqualTo(String value) {
            addCriterion("cert_subject_dn <=", value, "certSubjectDn");
            return (Criteria) this;
        }

        public Criteria andCertSubjectDnLike(String value) {
            addCriterion("cert_subject_dn like", value, "certSubjectDn");
            return (Criteria) this;
        }

        public Criteria andCertSubjectDnNotLike(String value) {
            addCriterion("cert_subject_dn not like", value, "certSubjectDn");
            return (Criteria) this;
        }

        public Criteria andCertSubjectDnIn(List<String> values) {
            addCriterion("cert_subject_dn in", values, "certSubjectDn");
            return (Criteria) this;
        }

        public Criteria andCertSubjectDnNotIn(List<String> values) {
            addCriterion("cert_subject_dn not in", values, "certSubjectDn");
            return (Criteria) this;
        }

        public Criteria andCertSubjectDnBetween(String value1, String value2) {
            addCriterion("cert_subject_dn between", value1, value2, "certSubjectDn");
            return (Criteria) this;
        }

        public Criteria andCertSubjectDnNotBetween(String value1, String value2) {
            addCriterion("cert_subject_dn not between", value1, value2, "certSubjectDn");
            return (Criteria) this;
        }

        public Criteria andIdentityCardNoIsNull() {
            addCriterion("identity_card_no is null");
            return (Criteria) this;
        }

        public Criteria andIdentityCardNoIsNotNull() {
            addCriterion("identity_card_no is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityCardNoEqualTo(String value) {
            addCriterion("identity_card_no =", value, "identityCardNo");
            return (Criteria) this;
        }

        public Criteria andIdentityCardNoNotEqualTo(String value) {
            addCriterion("identity_card_no <>", value, "identityCardNo");
            return (Criteria) this;
        }

        public Criteria andIdentityCardNoGreaterThan(String value) {
            addCriterion("identity_card_no >", value, "identityCardNo");
            return (Criteria) this;
        }

        public Criteria andIdentityCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("identity_card_no >=", value, "identityCardNo");
            return (Criteria) this;
        }

        public Criteria andIdentityCardNoLessThan(String value) {
            addCriterion("identity_card_no <", value, "identityCardNo");
            return (Criteria) this;
        }

        public Criteria andIdentityCardNoLessThanOrEqualTo(String value) {
            addCriterion("identity_card_no <=", value, "identityCardNo");
            return (Criteria) this;
        }

        public Criteria andIdentityCardNoLike(String value) {
            addCriterion("identity_card_no like", value, "identityCardNo");
            return (Criteria) this;
        }

        public Criteria andIdentityCardNoNotLike(String value) {
            addCriterion("identity_card_no not like", value, "identityCardNo");
            return (Criteria) this;
        }

        public Criteria andIdentityCardNoIn(List<String> values) {
            addCriterion("identity_card_no in", values, "identityCardNo");
            return (Criteria) this;
        }

        public Criteria andIdentityCardNoNotIn(List<String> values) {
            addCriterion("identity_card_no not in", values, "identityCardNo");
            return (Criteria) this;
        }

        public Criteria andIdentityCardNoBetween(String value1, String value2) {
            addCriterion("identity_card_no between", value1, value2, "identityCardNo");
            return (Criteria) this;
        }

        public Criteria andIdentityCardNoNotBetween(String value1, String value2) {
            addCriterion("identity_card_no not between", value1, value2, "identityCardNo");
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