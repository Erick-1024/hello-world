package com.cana.asset.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public ProjectExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andCoreOrganizationCodeIsNull() {
            addCriterion("core_organization_code is null");
            return (Criteria) this;
        }

        public Criteria andCoreOrganizationCodeIsNotNull() {
            addCriterion("core_organization_code is not null");
            return (Criteria) this;
        }

        public Criteria andCoreOrganizationCodeEqualTo(String value) {
            addCriterion("core_organization_code =", value, "coreOrganizationCode");
            return (Criteria) this;
        }

        public Criteria andCoreOrganizationCodeNotEqualTo(String value) {
            addCriterion("core_organization_code <>", value, "coreOrganizationCode");
            return (Criteria) this;
        }

        public Criteria andCoreOrganizationCodeGreaterThan(String value) {
            addCriterion("core_organization_code >", value, "coreOrganizationCode");
            return (Criteria) this;
        }

        public Criteria andCoreOrganizationCodeGreaterThanOrEqualTo(String value) {
            addCriterion("core_organization_code >=", value, "coreOrganizationCode");
            return (Criteria) this;
        }

        public Criteria andCoreOrganizationCodeLessThan(String value) {
            addCriterion("core_organization_code <", value, "coreOrganizationCode");
            return (Criteria) this;
        }

        public Criteria andCoreOrganizationCodeLessThanOrEqualTo(String value) {
            addCriterion("core_organization_code <=", value, "coreOrganizationCode");
            return (Criteria) this;
        }

        public Criteria andCoreOrganizationCodeLike(String value) {
            addCriterion("core_organization_code like", value, "coreOrganizationCode");
            return (Criteria) this;
        }

        public Criteria andCoreOrganizationCodeNotLike(String value) {
            addCriterion("core_organization_code not like", value, "coreOrganizationCode");
            return (Criteria) this;
        }

        public Criteria andCoreOrganizationCodeIn(List<String> values) {
            addCriterion("core_organization_code in", values, "coreOrganizationCode");
            return (Criteria) this;
        }

        public Criteria andCoreOrganizationCodeNotIn(List<String> values) {
            addCriterion("core_organization_code not in", values, "coreOrganizationCode");
            return (Criteria) this;
        }

        public Criteria andCoreOrganizationCodeBetween(String value1, String value2) {
            addCriterion("core_organization_code between", value1, value2, "coreOrganizationCode");
            return (Criteria) this;
        }

        public Criteria andCoreOrganizationCodeNotBetween(String value1, String value2) {
            addCriterion("core_organization_code not between", value1, value2, "coreOrganizationCode");
            return (Criteria) this;
        }

        public Criteria andCoreBusinessLicenceCodeIsNull() {
            addCriterion("core_business_licence_code is null");
            return (Criteria) this;
        }

        public Criteria andCoreBusinessLicenceCodeIsNotNull() {
            addCriterion("core_business_licence_code is not null");
            return (Criteria) this;
        }

        public Criteria andCoreBusinessLicenceCodeEqualTo(String value) {
            addCriterion("core_business_licence_code =", value, "coreBusinessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andCoreBusinessLicenceCodeNotEqualTo(String value) {
            addCriterion("core_business_licence_code <>", value, "coreBusinessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andCoreBusinessLicenceCodeGreaterThan(String value) {
            addCriterion("core_business_licence_code >", value, "coreBusinessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andCoreBusinessLicenceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("core_business_licence_code >=", value, "coreBusinessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andCoreBusinessLicenceCodeLessThan(String value) {
            addCriterion("core_business_licence_code <", value, "coreBusinessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andCoreBusinessLicenceCodeLessThanOrEqualTo(String value) {
            addCriterion("core_business_licence_code <=", value, "coreBusinessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andCoreBusinessLicenceCodeLike(String value) {
            addCriterion("core_business_licence_code like", value, "coreBusinessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andCoreBusinessLicenceCodeNotLike(String value) {
            addCriterion("core_business_licence_code not like", value, "coreBusinessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andCoreBusinessLicenceCodeIn(List<String> values) {
            addCriterion("core_business_licence_code in", values, "coreBusinessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andCoreBusinessLicenceCodeNotIn(List<String> values) {
            addCriterion("core_business_licence_code not in", values, "coreBusinessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andCoreBusinessLicenceCodeBetween(String value1, String value2) {
            addCriterion("core_business_licence_code between", value1, value2, "coreBusinessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andCoreBusinessLicenceCodeNotBetween(String value1, String value2) {
            addCriterion("core_business_licence_code not between", value1, value2, "coreBusinessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andCoreTaxRegistrationCertificateCodeIsNull() {
            addCriterion("core_tax_registration_certificate_code is null");
            return (Criteria) this;
        }

        public Criteria andCoreTaxRegistrationCertificateCodeIsNotNull() {
            addCriterion("core_tax_registration_certificate_code is not null");
            return (Criteria) this;
        }

        public Criteria andCoreTaxRegistrationCertificateCodeEqualTo(String value) {
            addCriterion("core_tax_registration_certificate_code =", value, "coreTaxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andCoreTaxRegistrationCertificateCodeNotEqualTo(String value) {
            addCriterion("core_tax_registration_certificate_code <>", value, "coreTaxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andCoreTaxRegistrationCertificateCodeGreaterThan(String value) {
            addCriterion("core_tax_registration_certificate_code >", value, "coreTaxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andCoreTaxRegistrationCertificateCodeGreaterThanOrEqualTo(String value) {
            addCriterion("core_tax_registration_certificate_code >=", value, "coreTaxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andCoreTaxRegistrationCertificateCodeLessThan(String value) {
            addCriterion("core_tax_registration_certificate_code <", value, "coreTaxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andCoreTaxRegistrationCertificateCodeLessThanOrEqualTo(String value) {
            addCriterion("core_tax_registration_certificate_code <=", value, "coreTaxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andCoreTaxRegistrationCertificateCodeLike(String value) {
            addCriterion("core_tax_registration_certificate_code like", value, "coreTaxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andCoreTaxRegistrationCertificateCodeNotLike(String value) {
            addCriterion("core_tax_registration_certificate_code not like", value, "coreTaxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andCoreTaxRegistrationCertificateCodeIn(List<String> values) {
            addCriterion("core_tax_registration_certificate_code in", values, "coreTaxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andCoreTaxRegistrationCertificateCodeNotIn(List<String> values) {
            addCriterion("core_tax_registration_certificate_code not in", values, "coreTaxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andCoreTaxRegistrationCertificateCodeBetween(String value1, String value2) {
            addCriterion("core_tax_registration_certificate_code between", value1, value2, "coreTaxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andCoreTaxRegistrationCertificateCodeNotBetween(String value1, String value2) {
            addCriterion("core_tax_registration_certificate_code not between", value1, value2, "coreTaxRegistrationCertificateCode");
            return (Criteria) this;
        }

        public Criteria andCoreIndustryIsNull() {
            addCriterion("core_industry is null");
            return (Criteria) this;
        }

        public Criteria andCoreIndustryIsNotNull() {
            addCriterion("core_industry is not null");
            return (Criteria) this;
        }

        public Criteria andCoreIndustryEqualTo(String value) {
            addCriterion("core_industry =", value, "coreIndustry");
            return (Criteria) this;
        }

        public Criteria andCoreIndustryNotEqualTo(String value) {
            addCriterion("core_industry <>", value, "coreIndustry");
            return (Criteria) this;
        }

        public Criteria andCoreIndustryGreaterThan(String value) {
            addCriterion("core_industry >", value, "coreIndustry");
            return (Criteria) this;
        }

        public Criteria andCoreIndustryGreaterThanOrEqualTo(String value) {
            addCriterion("core_industry >=", value, "coreIndustry");
            return (Criteria) this;
        }

        public Criteria andCoreIndustryLessThan(String value) {
            addCriterion("core_industry <", value, "coreIndustry");
            return (Criteria) this;
        }

        public Criteria andCoreIndustryLessThanOrEqualTo(String value) {
            addCriterion("core_industry <=", value, "coreIndustry");
            return (Criteria) this;
        }

        public Criteria andCoreIndustryLike(String value) {
            addCriterion("core_industry like", value, "coreIndustry");
            return (Criteria) this;
        }

        public Criteria andCoreIndustryNotLike(String value) {
            addCriterion("core_industry not like", value, "coreIndustry");
            return (Criteria) this;
        }

        public Criteria andCoreIndustryIn(List<String> values) {
            addCriterion("core_industry in", values, "coreIndustry");
            return (Criteria) this;
        }

        public Criteria andCoreIndustryNotIn(List<String> values) {
            addCriterion("core_industry not in", values, "coreIndustry");
            return (Criteria) this;
        }

        public Criteria andCoreIndustryBetween(String value1, String value2) {
            addCriterion("core_industry between", value1, value2, "coreIndustry");
            return (Criteria) this;
        }

        public Criteria andCoreIndustryNotBetween(String value1, String value2) {
            addCriterion("core_industry not between", value1, value2, "coreIndustry");
            return (Criteria) this;
        }

        public Criteria andCoreEconomicCategoryIsNull() {
            addCriterion("core_economic_category is null");
            return (Criteria) this;
        }

        public Criteria andCoreEconomicCategoryIsNotNull() {
            addCriterion("core_economic_category is not null");
            return (Criteria) this;
        }

        public Criteria andCoreEconomicCategoryEqualTo(String value) {
            addCriterion("core_economic_category =", value, "coreEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCoreEconomicCategoryNotEqualTo(String value) {
            addCriterion("core_economic_category <>", value, "coreEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCoreEconomicCategoryGreaterThan(String value) {
            addCriterion("core_economic_category >", value, "coreEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCoreEconomicCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("core_economic_category >=", value, "coreEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCoreEconomicCategoryLessThan(String value) {
            addCriterion("core_economic_category <", value, "coreEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCoreEconomicCategoryLessThanOrEqualTo(String value) {
            addCriterion("core_economic_category <=", value, "coreEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCoreEconomicCategoryLike(String value) {
            addCriterion("core_economic_category like", value, "coreEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCoreEconomicCategoryNotLike(String value) {
            addCriterion("core_economic_category not like", value, "coreEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCoreEconomicCategoryIn(List<String> values) {
            addCriterion("core_economic_category in", values, "coreEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCoreEconomicCategoryNotIn(List<String> values) {
            addCriterion("core_economic_category not in", values, "coreEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCoreEconomicCategoryBetween(String value1, String value2) {
            addCriterion("core_economic_category between", value1, value2, "coreEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCoreEconomicCategoryNotBetween(String value1, String value2) {
            addCriterion("core_economic_category not between", value1, value2, "coreEconomicCategory");
            return (Criteria) this;
        }

        public Criteria andCoreAccountNoIsNull() {
            addCriterion("core_account_no is null");
            return (Criteria) this;
        }

        public Criteria andCoreAccountNoIsNotNull() {
            addCriterion("core_account_no is not null");
            return (Criteria) this;
        }

        public Criteria andCoreAccountNoEqualTo(String value) {
            addCriterion("core_account_no =", value, "coreAccountNo");
            return (Criteria) this;
        }

        public Criteria andCoreAccountNoNotEqualTo(String value) {
            addCriterion("core_account_no <>", value, "coreAccountNo");
            return (Criteria) this;
        }

        public Criteria andCoreAccountNoGreaterThan(String value) {
            addCriterion("core_account_no >", value, "coreAccountNo");
            return (Criteria) this;
        }

        public Criteria andCoreAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("core_account_no >=", value, "coreAccountNo");
            return (Criteria) this;
        }

        public Criteria andCoreAccountNoLessThan(String value) {
            addCriterion("core_account_no <", value, "coreAccountNo");
            return (Criteria) this;
        }

        public Criteria andCoreAccountNoLessThanOrEqualTo(String value) {
            addCriterion("core_account_no <=", value, "coreAccountNo");
            return (Criteria) this;
        }

        public Criteria andCoreAccountNoLike(String value) {
            addCriterion("core_account_no like", value, "coreAccountNo");
            return (Criteria) this;
        }

        public Criteria andCoreAccountNoNotLike(String value) {
            addCriterion("core_account_no not like", value, "coreAccountNo");
            return (Criteria) this;
        }

        public Criteria andCoreAccountNoIn(List<String> values) {
            addCriterion("core_account_no in", values, "coreAccountNo");
            return (Criteria) this;
        }

        public Criteria andCoreAccountNoNotIn(List<String> values) {
            addCriterion("core_account_no not in", values, "coreAccountNo");
            return (Criteria) this;
        }

        public Criteria andCoreAccountNoBetween(String value1, String value2) {
            addCriterion("core_account_no between", value1, value2, "coreAccountNo");
            return (Criteria) this;
        }

        public Criteria andCoreAccountNoNotBetween(String value1, String value2) {
            addCriterion("core_account_no not between", value1, value2, "coreAccountNo");
            return (Criteria) this;
        }

        public Criteria andFinanceApplicantIsNull() {
            addCriterion("finance_applicant is null");
            return (Criteria) this;
        }

        public Criteria andFinanceApplicantIsNotNull() {
            addCriterion("finance_applicant is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceApplicantEqualTo(String value) {
            addCriterion("finance_applicant =", value, "financeApplicant");
            return (Criteria) this;
        }

        public Criteria andFinanceApplicantNotEqualTo(String value) {
            addCriterion("finance_applicant <>", value, "financeApplicant");
            return (Criteria) this;
        }

        public Criteria andFinanceApplicantGreaterThan(String value) {
            addCriterion("finance_applicant >", value, "financeApplicant");
            return (Criteria) this;
        }

        public Criteria andFinanceApplicantGreaterThanOrEqualTo(String value) {
            addCriterion("finance_applicant >=", value, "financeApplicant");
            return (Criteria) this;
        }

        public Criteria andFinanceApplicantLessThan(String value) {
            addCriterion("finance_applicant <", value, "financeApplicant");
            return (Criteria) this;
        }

        public Criteria andFinanceApplicantLessThanOrEqualTo(String value) {
            addCriterion("finance_applicant <=", value, "financeApplicant");
            return (Criteria) this;
        }

        public Criteria andFinanceApplicantLike(String value) {
            addCriterion("finance_applicant like", value, "financeApplicant");
            return (Criteria) this;
        }

        public Criteria andFinanceApplicantNotLike(String value) {
            addCriterion("finance_applicant not like", value, "financeApplicant");
            return (Criteria) this;
        }

        public Criteria andFinanceApplicantIn(List<String> values) {
            addCriterion("finance_applicant in", values, "financeApplicant");
            return (Criteria) this;
        }

        public Criteria andFinanceApplicantNotIn(List<String> values) {
            addCriterion("finance_applicant not in", values, "financeApplicant");
            return (Criteria) this;
        }

        public Criteria andFinanceApplicantBetween(String value1, String value2) {
            addCriterion("finance_applicant between", value1, value2, "financeApplicant");
            return (Criteria) this;
        }

        public Criteria andFinanceApplicantNotBetween(String value1, String value2) {
            addCriterion("finance_applicant not between", value1, value2, "financeApplicant");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitLowerIsNull() {
            addCriterion("single_loan_limit_lower is null");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitLowerIsNotNull() {
            addCriterion("single_loan_limit_lower is not null");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitLowerEqualTo(Long value) {
            addCriterion("single_loan_limit_lower =", value, "singleLoanLimitLower");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitLowerNotEqualTo(Long value) {
            addCriterion("single_loan_limit_lower <>", value, "singleLoanLimitLower");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitLowerGreaterThan(Long value) {
            addCriterion("single_loan_limit_lower >", value, "singleLoanLimitLower");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitLowerGreaterThanOrEqualTo(Long value) {
            addCriterion("single_loan_limit_lower >=", value, "singleLoanLimitLower");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitLowerLessThan(Long value) {
            addCriterion("single_loan_limit_lower <", value, "singleLoanLimitLower");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitLowerLessThanOrEqualTo(Long value) {
            addCriterion("single_loan_limit_lower <=", value, "singleLoanLimitLower");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitLowerIn(List<Long> values) {
            addCriterion("single_loan_limit_lower in", values, "singleLoanLimitLower");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitLowerNotIn(List<Long> values) {
            addCriterion("single_loan_limit_lower not in", values, "singleLoanLimitLower");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitLowerBetween(Long value1, Long value2) {
            addCriterion("single_loan_limit_lower between", value1, value2, "singleLoanLimitLower");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitLowerNotBetween(Long value1, Long value2) {
            addCriterion("single_loan_limit_lower not between", value1, value2, "singleLoanLimitLower");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitUpperIsNull() {
            addCriterion("single_loan_limit_upper is null");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitUpperIsNotNull() {
            addCriterion("single_loan_limit_upper is not null");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitUpperEqualTo(Long value) {
            addCriterion("single_loan_limit_upper =", value, "singleLoanLimitUpper");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitUpperNotEqualTo(Long value) {
            addCriterion("single_loan_limit_upper <>", value, "singleLoanLimitUpper");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitUpperGreaterThan(Long value) {
            addCriterion("single_loan_limit_upper >", value, "singleLoanLimitUpper");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitUpperGreaterThanOrEqualTo(Long value) {
            addCriterion("single_loan_limit_upper >=", value, "singleLoanLimitUpper");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitUpperLessThan(Long value) {
            addCriterion("single_loan_limit_upper <", value, "singleLoanLimitUpper");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitUpperLessThanOrEqualTo(Long value) {
            addCriterion("single_loan_limit_upper <=", value, "singleLoanLimitUpper");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitUpperIn(List<Long> values) {
            addCriterion("single_loan_limit_upper in", values, "singleLoanLimitUpper");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitUpperNotIn(List<Long> values) {
            addCriterion("single_loan_limit_upper not in", values, "singleLoanLimitUpper");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitUpperBetween(Long value1, Long value2) {
            addCriterion("single_loan_limit_upper between", value1, value2, "singleLoanLimitUpper");
            return (Criteria) this;
        }

        public Criteria andSingleLoanLimitUpperNotBetween(Long value1, Long value2) {
            addCriterion("single_loan_limit_upper not between", value1, value2, "singleLoanLimitUpper");
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

        public Criteria andInterestRateLowerIsNull() {
            addCriterion("interest_rate_lower is null");
            return (Criteria) this;
        }

        public Criteria andInterestRateLowerIsNotNull() {
            addCriterion("interest_rate_lower is not null");
            return (Criteria) this;
        }

        public Criteria andInterestRateLowerEqualTo(BigDecimal value) {
            addCriterion("interest_rate_lower =", value, "interestRateLower");
            return (Criteria) this;
        }

        public Criteria andInterestRateLowerNotEqualTo(BigDecimal value) {
            addCriterion("interest_rate_lower <>", value, "interestRateLower");
            return (Criteria) this;
        }

        public Criteria andInterestRateLowerGreaterThan(BigDecimal value) {
            addCriterion("interest_rate_lower >", value, "interestRateLower");
            return (Criteria) this;
        }

        public Criteria andInterestRateLowerGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("interest_rate_lower >=", value, "interestRateLower");
            return (Criteria) this;
        }

        public Criteria andInterestRateLowerLessThan(BigDecimal value) {
            addCriterion("interest_rate_lower <", value, "interestRateLower");
            return (Criteria) this;
        }

        public Criteria andInterestRateLowerLessThanOrEqualTo(BigDecimal value) {
            addCriterion("interest_rate_lower <=", value, "interestRateLower");
            return (Criteria) this;
        }

        public Criteria andInterestRateLowerIn(List<BigDecimal> values) {
            addCriterion("interest_rate_lower in", values, "interestRateLower");
            return (Criteria) this;
        }

        public Criteria andInterestRateLowerNotIn(List<BigDecimal> values) {
            addCriterion("interest_rate_lower not in", values, "interestRateLower");
            return (Criteria) this;
        }

        public Criteria andInterestRateLowerBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("interest_rate_lower between", value1, value2, "interestRateLower");
            return (Criteria) this;
        }

        public Criteria andInterestRateLowerNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("interest_rate_lower not between", value1, value2, "interestRateLower");
            return (Criteria) this;
        }

        public Criteria andInterestRateUpperIsNull() {
            addCriterion("interest_rate_upper is null");
            return (Criteria) this;
        }

        public Criteria andInterestRateUpperIsNotNull() {
            addCriterion("interest_rate_upper is not null");
            return (Criteria) this;
        }

        public Criteria andInterestRateUpperEqualTo(BigDecimal value) {
            addCriterion("interest_rate_upper =", value, "interestRateUpper");
            return (Criteria) this;
        }

        public Criteria andInterestRateUpperNotEqualTo(BigDecimal value) {
            addCriterion("interest_rate_upper <>", value, "interestRateUpper");
            return (Criteria) this;
        }

        public Criteria andInterestRateUpperGreaterThan(BigDecimal value) {
            addCriterion("interest_rate_upper >", value, "interestRateUpper");
            return (Criteria) this;
        }

        public Criteria andInterestRateUpperGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("interest_rate_upper >=", value, "interestRateUpper");
            return (Criteria) this;
        }

        public Criteria andInterestRateUpperLessThan(BigDecimal value) {
            addCriterion("interest_rate_upper <", value, "interestRateUpper");
            return (Criteria) this;
        }

        public Criteria andInterestRateUpperLessThanOrEqualTo(BigDecimal value) {
            addCriterion("interest_rate_upper <=", value, "interestRateUpper");
            return (Criteria) this;
        }

        public Criteria andInterestRateUpperIn(List<BigDecimal> values) {
            addCriterion("interest_rate_upper in", values, "interestRateUpper");
            return (Criteria) this;
        }

        public Criteria andInterestRateUpperNotIn(List<BigDecimal> values) {
            addCriterion("interest_rate_upper not in", values, "interestRateUpper");
            return (Criteria) this;
        }

        public Criteria andInterestRateUpperBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("interest_rate_upper between", value1, value2, "interestRateUpper");
            return (Criteria) this;
        }

        public Criteria andInterestRateUpperNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("interest_rate_upper not between", value1, value2, "interestRateUpper");
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

        public Criteria andLoanPeriodLowerIsNull() {
            addCriterion("loan_period_lower is null");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLowerIsNotNull() {
            addCriterion("loan_period_lower is not null");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLowerEqualTo(Integer value) {
            addCriterion("loan_period_lower =", value, "loanPeriodLower");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLowerNotEqualTo(Integer value) {
            addCriterion("loan_period_lower <>", value, "loanPeriodLower");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLowerGreaterThan(Integer value) {
            addCriterion("loan_period_lower >", value, "loanPeriodLower");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLowerGreaterThanOrEqualTo(Integer value) {
            addCriterion("loan_period_lower >=", value, "loanPeriodLower");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLowerLessThan(Integer value) {
            addCriterion("loan_period_lower <", value, "loanPeriodLower");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLowerLessThanOrEqualTo(Integer value) {
            addCriterion("loan_period_lower <=", value, "loanPeriodLower");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLowerIn(List<Integer> values) {
            addCriterion("loan_period_lower in", values, "loanPeriodLower");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLowerNotIn(List<Integer> values) {
            addCriterion("loan_period_lower not in", values, "loanPeriodLower");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLowerBetween(Integer value1, Integer value2) {
            addCriterion("loan_period_lower between", value1, value2, "loanPeriodLower");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLowerNotBetween(Integer value1, Integer value2) {
            addCriterion("loan_period_lower not between", value1, value2, "loanPeriodLower");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUpperIsNull() {
            addCriterion("loan_period_upper is null");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUpperIsNotNull() {
            addCriterion("loan_period_upper is not null");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUpperEqualTo(Integer value) {
            addCriterion("loan_period_upper =", value, "loanPeriodUpper");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUpperNotEqualTo(Integer value) {
            addCriterion("loan_period_upper <>", value, "loanPeriodUpper");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUpperGreaterThan(Integer value) {
            addCriterion("loan_period_upper >", value, "loanPeriodUpper");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUpperGreaterThanOrEqualTo(Integer value) {
            addCriterion("loan_period_upper >=", value, "loanPeriodUpper");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUpperLessThan(Integer value) {
            addCriterion("loan_period_upper <", value, "loanPeriodUpper");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUpperLessThanOrEqualTo(Integer value) {
            addCriterion("loan_period_upper <=", value, "loanPeriodUpper");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUpperIn(List<Integer> values) {
            addCriterion("loan_period_upper in", values, "loanPeriodUpper");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUpperNotIn(List<Integer> values) {
            addCriterion("loan_period_upper not in", values, "loanPeriodUpper");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUpperBetween(Integer value1, Integer value2) {
            addCriterion("loan_period_upper between", value1, value2, "loanPeriodUpper");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodUpperNotBetween(Integer value1, Integer value2) {
            addCriterion("loan_period_upper not between", value1, value2, "loanPeriodUpper");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodsIsNull() {
            addCriterion("repayment_methods is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodsIsNotNull() {
            addCriterion("repayment_methods is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodsEqualTo(String value) {
            addCriterion("repayment_methods =", value, "repaymentMethods");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodsNotEqualTo(String value) {
            addCriterion("repayment_methods <>", value, "repaymentMethods");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodsGreaterThan(String value) {
            addCriterion("repayment_methods >", value, "repaymentMethods");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodsGreaterThanOrEqualTo(String value) {
            addCriterion("repayment_methods >=", value, "repaymentMethods");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodsLessThan(String value) {
            addCriterion("repayment_methods <", value, "repaymentMethods");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodsLessThanOrEqualTo(String value) {
            addCriterion("repayment_methods <=", value, "repaymentMethods");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodsLike(String value) {
            addCriterion("repayment_methods like", value, "repaymentMethods");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodsNotLike(String value) {
            addCriterion("repayment_methods not like", value, "repaymentMethods");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodsIn(List<String> values) {
            addCriterion("repayment_methods in", values, "repaymentMethods");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodsNotIn(List<String> values) {
            addCriterion("repayment_methods not in", values, "repaymentMethods");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodsBetween(String value1, String value2) {
            addCriterion("repayment_methods between", value1, value2, "repaymentMethods");
            return (Criteria) this;
        }

        public Criteria andRepaymentMethodsNotBetween(String value1, String value2) {
            addCriterion("repayment_methods not between", value1, value2, "repaymentMethods");
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

        public Criteria andExtensionRatioMethodIsNull() {
            addCriterion("extension_ratio_method is null");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioMethodIsNotNull() {
            addCriterion("extension_ratio_method is not null");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioMethodEqualTo(String value) {
            addCriterion("extension_ratio_method =", value, "extensionRatioMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioMethodNotEqualTo(String value) {
            addCriterion("extension_ratio_method <>", value, "extensionRatioMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioMethodGreaterThan(String value) {
            addCriterion("extension_ratio_method >", value, "extensionRatioMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioMethodGreaterThanOrEqualTo(String value) {
            addCriterion("extension_ratio_method >=", value, "extensionRatioMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioMethodLessThan(String value) {
            addCriterion("extension_ratio_method <", value, "extensionRatioMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioMethodLessThanOrEqualTo(String value) {
            addCriterion("extension_ratio_method <=", value, "extensionRatioMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioMethodLike(String value) {
            addCriterion("extension_ratio_method like", value, "extensionRatioMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioMethodNotLike(String value) {
            addCriterion("extension_ratio_method not like", value, "extensionRatioMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioMethodIn(List<String> values) {
            addCriterion("extension_ratio_method in", values, "extensionRatioMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioMethodNotIn(List<String> values) {
            addCriterion("extension_ratio_method not in", values, "extensionRatioMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioMethodBetween(String value1, String value2) {
            addCriterion("extension_ratio_method between", value1, value2, "extensionRatioMethod");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioMethodNotBetween(String value1, String value2) {
            addCriterion("extension_ratio_method not between", value1, value2, "extensionRatioMethod");
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

        public Criteria andPenaltyRateMethodIsNull() {
            addCriterion("penalty_rate_method is null");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateMethodIsNotNull() {
            addCriterion("penalty_rate_method is not null");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateMethodEqualTo(String value) {
            addCriterion("penalty_rate_method =", value, "penaltyRateMethod");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateMethodNotEqualTo(String value) {
            addCriterion("penalty_rate_method <>", value, "penaltyRateMethod");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateMethodGreaterThan(String value) {
            addCriterion("penalty_rate_method >", value, "penaltyRateMethod");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateMethodGreaterThanOrEqualTo(String value) {
            addCriterion("penalty_rate_method >=", value, "penaltyRateMethod");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateMethodLessThan(String value) {
            addCriterion("penalty_rate_method <", value, "penaltyRateMethod");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateMethodLessThanOrEqualTo(String value) {
            addCriterion("penalty_rate_method <=", value, "penaltyRateMethod");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateMethodLike(String value) {
            addCriterion("penalty_rate_method like", value, "penaltyRateMethod");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateMethodNotLike(String value) {
            addCriterion("penalty_rate_method not like", value, "penaltyRateMethod");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateMethodIn(List<String> values) {
            addCriterion("penalty_rate_method in", values, "penaltyRateMethod");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateMethodNotIn(List<String> values) {
            addCriterion("penalty_rate_method not in", values, "penaltyRateMethod");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateMethodBetween(String value1, String value2) {
            addCriterion("penalty_rate_method between", value1, value2, "penaltyRateMethod");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateMethodNotBetween(String value1, String value2) {
            addCriterion("penalty_rate_method not between", value1, value2, "penaltyRateMethod");
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

        public Criteria andCreateUserIdIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(String value) {
            addCriterion("create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(String value) {
            addCriterion("create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(String value) {
            addCriterion("create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(String value) {
            addCriterion("create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(String value) {
            addCriterion("create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLike(String value) {
            addCriterion("create_user_id like", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotLike(String value) {
            addCriterion("create_user_id not like", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<String> values) {
            addCriterion("create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<String> values) {
            addCriterion("create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(String value1, String value2) {
            addCriterion("create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(String value1, String value2) {
            addCriterion("create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateCustomerIdIsNull() {
            addCriterion("create_customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateCustomerIdIsNotNull() {
            addCriterion("create_customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateCustomerIdEqualTo(String value) {
            addCriterion("create_customer_id =", value, "createCustomerId");
            return (Criteria) this;
        }

        public Criteria andCreateCustomerIdNotEqualTo(String value) {
            addCriterion("create_customer_id <>", value, "createCustomerId");
            return (Criteria) this;
        }

        public Criteria andCreateCustomerIdGreaterThan(String value) {
            addCriterion("create_customer_id >", value, "createCustomerId");
            return (Criteria) this;
        }

        public Criteria andCreateCustomerIdGreaterThanOrEqualTo(String value) {
            addCriterion("create_customer_id >=", value, "createCustomerId");
            return (Criteria) this;
        }

        public Criteria andCreateCustomerIdLessThan(String value) {
            addCriterion("create_customer_id <", value, "createCustomerId");
            return (Criteria) this;
        }

        public Criteria andCreateCustomerIdLessThanOrEqualTo(String value) {
            addCriterion("create_customer_id <=", value, "createCustomerId");
            return (Criteria) this;
        }

        public Criteria andCreateCustomerIdLike(String value) {
            addCriterion("create_customer_id like", value, "createCustomerId");
            return (Criteria) this;
        }

        public Criteria andCreateCustomerIdNotLike(String value) {
            addCriterion("create_customer_id not like", value, "createCustomerId");
            return (Criteria) this;
        }

        public Criteria andCreateCustomerIdIn(List<String> values) {
            addCriterion("create_customer_id in", values, "createCustomerId");
            return (Criteria) this;
        }

        public Criteria andCreateCustomerIdNotIn(List<String> values) {
            addCriterion("create_customer_id not in", values, "createCustomerId");
            return (Criteria) this;
        }

        public Criteria andCreateCustomerIdBetween(String value1, String value2) {
            addCriterion("create_customer_id between", value1, value2, "createCustomerId");
            return (Criteria) this;
        }

        public Criteria andCreateCustomerIdNotBetween(String value1, String value2) {
            addCriterion("create_customer_id not between", value1, value2, "createCustomerId");
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