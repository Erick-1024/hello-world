package com.cana.credit.limit.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditLimitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public CreditLimitExample() {
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

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(String value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(String value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(String value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(String value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(String value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(String value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLike(String value) {
            addCriterion("member_id like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotLike(String value) {
            addCriterion("member_id not like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<String> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<String> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(String value1, String value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(String value1, String value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(String value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(String value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(String value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(String value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(String value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLike(String value) {
            addCriterion("project_id like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotLike(String value) {
            addCriterion("project_id not like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<String> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<String> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(String value1, String value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(String value1, String value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
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

        public Criteria andCreditModeIsNull() {
            addCriterion("credit_mode is null");
            return (Criteria) this;
        }

        public Criteria andCreditModeIsNotNull() {
            addCriterion("credit_mode is not null");
            return (Criteria) this;
        }

        public Criteria andCreditModeEqualTo(String value) {
            addCriterion("credit_mode =", value, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeNotEqualTo(String value) {
            addCriterion("credit_mode <>", value, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeGreaterThan(String value) {
            addCriterion("credit_mode >", value, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeGreaterThanOrEqualTo(String value) {
            addCriterion("credit_mode >=", value, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeLessThan(String value) {
            addCriterion("credit_mode <", value, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeLessThanOrEqualTo(String value) {
            addCriterion("credit_mode <=", value, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeLike(String value) {
            addCriterion("credit_mode like", value, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeNotLike(String value) {
            addCriterion("credit_mode not like", value, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeIn(List<String> values) {
            addCriterion("credit_mode in", values, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeNotIn(List<String> values) {
            addCriterion("credit_mode not in", values, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeBetween(String value1, String value2) {
            addCriterion("credit_mode between", value1, value2, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeNotBetween(String value1, String value2) {
            addCriterion("credit_mode not between", value1, value2, "creditMode");
            return (Criteria) this;
        }

        public Criteria andTotalLimitIsNull() {
            addCriterion("total_limit is null");
            return (Criteria) this;
        }

        public Criteria andTotalLimitIsNotNull() {
            addCriterion("total_limit is not null");
            return (Criteria) this;
        }

        public Criteria andTotalLimitEqualTo(Long value) {
            addCriterion("total_limit =", value, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitNotEqualTo(Long value) {
            addCriterion("total_limit <>", value, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitGreaterThan(Long value) {
            addCriterion("total_limit >", value, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("total_limit >=", value, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitLessThan(Long value) {
            addCriterion("total_limit <", value, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitLessThanOrEqualTo(Long value) {
            addCriterion("total_limit <=", value, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitIn(List<Long> values) {
            addCriterion("total_limit in", values, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitNotIn(List<Long> values) {
            addCriterion("total_limit not in", values, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitBetween(Long value1, Long value2) {
            addCriterion("total_limit between", value1, value2, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitNotBetween(Long value1, Long value2) {
            addCriterion("total_limit not between", value1, value2, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitIsNull() {
            addCriterion("used_limit is null");
            return (Criteria) this;
        }

        public Criteria andUsedLimitIsNotNull() {
            addCriterion("used_limit is not null");
            return (Criteria) this;
        }

        public Criteria andUsedLimitEqualTo(Long value) {
            addCriterion("used_limit =", value, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitNotEqualTo(Long value) {
            addCriterion("used_limit <>", value, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitGreaterThan(Long value) {
            addCriterion("used_limit >", value, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("used_limit >=", value, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitLessThan(Long value) {
            addCriterion("used_limit <", value, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitLessThanOrEqualTo(Long value) {
            addCriterion("used_limit <=", value, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitIn(List<Long> values) {
            addCriterion("used_limit in", values, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitNotIn(List<Long> values) {
            addCriterion("used_limit not in", values, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitBetween(Long value1, Long value2) {
            addCriterion("used_limit between", value1, value2, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitNotBetween(Long value1, Long value2) {
            addCriterion("used_limit not between", value1, value2, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateIsNull() {
            addCriterion("effective_date is null");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateIsNotNull() {
            addCriterion("effective_date is not null");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateEqualTo(Date value) {
            addCriterion("effective_date =", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateNotEqualTo(Date value) {
            addCriterion("effective_date <>", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateGreaterThan(Date value) {
            addCriterion("effective_date >", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateGreaterThanOrEqualTo(Date value) {
            addCriterion("effective_date >=", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateLessThan(Date value) {
            addCriterion("effective_date <", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateLessThanOrEqualTo(Date value) {
            addCriterion("effective_date <=", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateIn(List<Date> values) {
            addCriterion("effective_date in", values, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateNotIn(List<Date> values) {
            addCriterion("effective_date not in", values, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateBetween(Date value1, Date value2) {
            addCriterion("effective_date between", value1, value2, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateNotBetween(Date value1, Date value2) {
            addCriterion("effective_date not between", value1, value2, "effectiveDate");
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