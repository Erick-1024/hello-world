package com.cana.asset.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerMagExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public CustomerMagExample() {
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

        public Criteria andMagNameIsNull() {
            addCriterion("mag_name is null");
            return (Criteria) this;
        }

        public Criteria andMagNameIsNotNull() {
            addCriterion("mag_name is not null");
            return (Criteria) this;
        }

        public Criteria andMagNameEqualTo(String value) {
            addCriterion("mag_name =", value, "magName");
            return (Criteria) this;
        }

        public Criteria andMagNameNotEqualTo(String value) {
            addCriterion("mag_name <>", value, "magName");
            return (Criteria) this;
        }

        public Criteria andMagNameGreaterThan(String value) {
            addCriterion("mag_name >", value, "magName");
            return (Criteria) this;
        }

        public Criteria andMagNameGreaterThanOrEqualTo(String value) {
            addCriterion("mag_name >=", value, "magName");
            return (Criteria) this;
        }

        public Criteria andMagNameLessThan(String value) {
            addCriterion("mag_name <", value, "magName");
            return (Criteria) this;
        }

        public Criteria andMagNameLessThanOrEqualTo(String value) {
            addCriterion("mag_name <=", value, "magName");
            return (Criteria) this;
        }

        public Criteria andMagNameLike(String value) {
            addCriterion("mag_name like", value, "magName");
            return (Criteria) this;
        }

        public Criteria andMagNameNotLike(String value) {
            addCriterion("mag_name not like", value, "magName");
            return (Criteria) this;
        }

        public Criteria andMagNameIn(List<String> values) {
            addCriterion("mag_name in", values, "magName");
            return (Criteria) this;
        }

        public Criteria andMagNameNotIn(List<String> values) {
            addCriterion("mag_name not in", values, "magName");
            return (Criteria) this;
        }

        public Criteria andMagNameBetween(String value1, String value2) {
            addCriterion("mag_name between", value1, value2, "magName");
            return (Criteria) this;
        }

        public Criteria andMagNameNotBetween(String value1, String value2) {
            addCriterion("mag_name not between", value1, value2, "magName");
            return (Criteria) this;
        }

        public Criteria andMagSexIsNull() {
            addCriterion("mag_sex is null");
            return (Criteria) this;
        }

        public Criteria andMagSexIsNotNull() {
            addCriterion("mag_sex is not null");
            return (Criteria) this;
        }

        public Criteria andMagSexEqualTo(String value) {
            addCriterion("mag_sex =", value, "magSex");
            return (Criteria) this;
        }

        public Criteria andMagSexNotEqualTo(String value) {
            addCriterion("mag_sex <>", value, "magSex");
            return (Criteria) this;
        }

        public Criteria andMagSexGreaterThan(String value) {
            addCriterion("mag_sex >", value, "magSex");
            return (Criteria) this;
        }

        public Criteria andMagSexGreaterThanOrEqualTo(String value) {
            addCriterion("mag_sex >=", value, "magSex");
            return (Criteria) this;
        }

        public Criteria andMagSexLessThan(String value) {
            addCriterion("mag_sex <", value, "magSex");
            return (Criteria) this;
        }

        public Criteria andMagSexLessThanOrEqualTo(String value) {
            addCriterion("mag_sex <=", value, "magSex");
            return (Criteria) this;
        }

        public Criteria andMagSexLike(String value) {
            addCriterion("mag_sex like", value, "magSex");
            return (Criteria) this;
        }

        public Criteria andMagSexNotLike(String value) {
            addCriterion("mag_sex not like", value, "magSex");
            return (Criteria) this;
        }

        public Criteria andMagSexIn(List<String> values) {
            addCriterion("mag_sex in", values, "magSex");
            return (Criteria) this;
        }

        public Criteria andMagSexNotIn(List<String> values) {
            addCriterion("mag_sex not in", values, "magSex");
            return (Criteria) this;
        }

        public Criteria andMagSexBetween(String value1, String value2) {
            addCriterion("mag_sex between", value1, value2, "magSex");
            return (Criteria) this;
        }

        public Criteria andMagSexNotBetween(String value1, String value2) {
            addCriterion("mag_sex not between", value1, value2, "magSex");
            return (Criteria) this;
        }

        public Criteria andMagIdentityCardNoIsNull() {
            addCriterion("mag_identity_card_no is null");
            return (Criteria) this;
        }

        public Criteria andMagIdentityCardNoIsNotNull() {
            addCriterion("mag_identity_card_no is not null");
            return (Criteria) this;
        }

        public Criteria andMagIdentityCardNoEqualTo(String value) {
            addCriterion("mag_identity_card_no =", value, "magIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andMagIdentityCardNoNotEqualTo(String value) {
            addCriterion("mag_identity_card_no <>", value, "magIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andMagIdentityCardNoGreaterThan(String value) {
            addCriterion("mag_identity_card_no >", value, "magIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andMagIdentityCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("mag_identity_card_no >=", value, "magIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andMagIdentityCardNoLessThan(String value) {
            addCriterion("mag_identity_card_no <", value, "magIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andMagIdentityCardNoLessThanOrEqualTo(String value) {
            addCriterion("mag_identity_card_no <=", value, "magIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andMagIdentityCardNoLike(String value) {
            addCriterion("mag_identity_card_no like", value, "magIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andMagIdentityCardNoNotLike(String value) {
            addCriterion("mag_identity_card_no not like", value, "magIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andMagIdentityCardNoIn(List<String> values) {
            addCriterion("mag_identity_card_no in", values, "magIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andMagIdentityCardNoNotIn(List<String> values) {
            addCriterion("mag_identity_card_no not in", values, "magIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andMagIdentityCardNoBetween(String value1, String value2) {
            addCriterion("mag_identity_card_no between", value1, value2, "magIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andMagIdentityCardNoNotBetween(String value1, String value2) {
            addCriterion("mag_identity_card_no not between", value1, value2, "magIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andMagDutyTypeIsNull() {
            addCriterion("mag_duty_type is null");
            return (Criteria) this;
        }

        public Criteria andMagDutyTypeIsNotNull() {
            addCriterion("mag_duty_type is not null");
            return (Criteria) this;
        }

        public Criteria andMagDutyTypeEqualTo(String value) {
            addCriterion("mag_duty_type =", value, "magDutyType");
            return (Criteria) this;
        }

        public Criteria andMagDutyTypeNotEqualTo(String value) {
            addCriterion("mag_duty_type <>", value, "magDutyType");
            return (Criteria) this;
        }

        public Criteria andMagDutyTypeGreaterThan(String value) {
            addCriterion("mag_duty_type >", value, "magDutyType");
            return (Criteria) this;
        }

        public Criteria andMagDutyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("mag_duty_type >=", value, "magDutyType");
            return (Criteria) this;
        }

        public Criteria andMagDutyTypeLessThan(String value) {
            addCriterion("mag_duty_type <", value, "magDutyType");
            return (Criteria) this;
        }

        public Criteria andMagDutyTypeLessThanOrEqualTo(String value) {
            addCriterion("mag_duty_type <=", value, "magDutyType");
            return (Criteria) this;
        }

        public Criteria andMagDutyTypeLike(String value) {
            addCriterion("mag_duty_type like", value, "magDutyType");
            return (Criteria) this;
        }

        public Criteria andMagDutyTypeNotLike(String value) {
            addCriterion("mag_duty_type not like", value, "magDutyType");
            return (Criteria) this;
        }

        public Criteria andMagDutyTypeIn(List<String> values) {
            addCriterion("mag_duty_type in", values, "magDutyType");
            return (Criteria) this;
        }

        public Criteria andMagDutyTypeNotIn(List<String> values) {
            addCriterion("mag_duty_type not in", values, "magDutyType");
            return (Criteria) this;
        }

        public Criteria andMagDutyTypeBetween(String value1, String value2) {
            addCriterion("mag_duty_type between", value1, value2, "magDutyType");
            return (Criteria) this;
        }

        public Criteria andMagDutyTypeNotBetween(String value1, String value2) {
            addCriterion("mag_duty_type not between", value1, value2, "magDutyType");
            return (Criteria) this;
        }

        public Criteria andMagEducationIsNull() {
            addCriterion("mag_education is null");
            return (Criteria) this;
        }

        public Criteria andMagEducationIsNotNull() {
            addCriterion("mag_education is not null");
            return (Criteria) this;
        }

        public Criteria andMagEducationEqualTo(String value) {
            addCriterion("mag_education =", value, "magEducation");
            return (Criteria) this;
        }

        public Criteria andMagEducationNotEqualTo(String value) {
            addCriterion("mag_education <>", value, "magEducation");
            return (Criteria) this;
        }

        public Criteria andMagEducationGreaterThan(String value) {
            addCriterion("mag_education >", value, "magEducation");
            return (Criteria) this;
        }

        public Criteria andMagEducationGreaterThanOrEqualTo(String value) {
            addCriterion("mag_education >=", value, "magEducation");
            return (Criteria) this;
        }

        public Criteria andMagEducationLessThan(String value) {
            addCriterion("mag_education <", value, "magEducation");
            return (Criteria) this;
        }

        public Criteria andMagEducationLessThanOrEqualTo(String value) {
            addCriterion("mag_education <=", value, "magEducation");
            return (Criteria) this;
        }

        public Criteria andMagEducationLike(String value) {
            addCriterion("mag_education like", value, "magEducation");
            return (Criteria) this;
        }

        public Criteria andMagEducationNotLike(String value) {
            addCriterion("mag_education not like", value, "magEducation");
            return (Criteria) this;
        }

        public Criteria andMagEducationIn(List<String> values) {
            addCriterion("mag_education in", values, "magEducation");
            return (Criteria) this;
        }

        public Criteria andMagEducationNotIn(List<String> values) {
            addCriterion("mag_education not in", values, "magEducation");
            return (Criteria) this;
        }

        public Criteria andMagEducationBetween(String value1, String value2) {
            addCriterion("mag_education between", value1, value2, "magEducation");
            return (Criteria) this;
        }

        public Criteria andMagEducationNotBetween(String value1, String value2) {
            addCriterion("mag_education not between", value1, value2, "magEducation");
            return (Criteria) this;
        }

        public Criteria andMagProfessionIsNull() {
            addCriterion("mag_profession is null");
            return (Criteria) this;
        }

        public Criteria andMagProfessionIsNotNull() {
            addCriterion("mag_profession is not null");
            return (Criteria) this;
        }

        public Criteria andMagProfessionEqualTo(String value) {
            addCriterion("mag_profession =", value, "magProfession");
            return (Criteria) this;
        }

        public Criteria andMagProfessionNotEqualTo(String value) {
            addCriterion("mag_profession <>", value, "magProfession");
            return (Criteria) this;
        }

        public Criteria andMagProfessionGreaterThan(String value) {
            addCriterion("mag_profession >", value, "magProfession");
            return (Criteria) this;
        }

        public Criteria andMagProfessionGreaterThanOrEqualTo(String value) {
            addCriterion("mag_profession >=", value, "magProfession");
            return (Criteria) this;
        }

        public Criteria andMagProfessionLessThan(String value) {
            addCriterion("mag_profession <", value, "magProfession");
            return (Criteria) this;
        }

        public Criteria andMagProfessionLessThanOrEqualTo(String value) {
            addCriterion("mag_profession <=", value, "magProfession");
            return (Criteria) this;
        }

        public Criteria andMagProfessionLike(String value) {
            addCriterion("mag_profession like", value, "magProfession");
            return (Criteria) this;
        }

        public Criteria andMagProfessionNotLike(String value) {
            addCriterion("mag_profession not like", value, "magProfession");
            return (Criteria) this;
        }

        public Criteria andMagProfessionIn(List<String> values) {
            addCriterion("mag_profession in", values, "magProfession");
            return (Criteria) this;
        }

        public Criteria andMagProfessionNotIn(List<String> values) {
            addCriterion("mag_profession not in", values, "magProfession");
            return (Criteria) this;
        }

        public Criteria andMagProfessionBetween(String value1, String value2) {
            addCriterion("mag_profession between", value1, value2, "magProfession");
            return (Criteria) this;
        }

        public Criteria andMagProfessionNotBetween(String value1, String value2) {
            addCriterion("mag_profession not between", value1, value2, "magProfession");
            return (Criteria) this;
        }

        public Criteria andExperienceIsNull() {
            addCriterion("experience is null");
            return (Criteria) this;
        }

        public Criteria andExperienceIsNotNull() {
            addCriterion("experience is not null");
            return (Criteria) this;
        }

        public Criteria andExperienceEqualTo(String value) {
            addCriterion("experience =", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceNotEqualTo(String value) {
            addCriterion("experience <>", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceGreaterThan(String value) {
            addCriterion("experience >", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceGreaterThanOrEqualTo(String value) {
            addCriterion("experience >=", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceLessThan(String value) {
            addCriterion("experience <", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceLessThanOrEqualTo(String value) {
            addCriterion("experience <=", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceLike(String value) {
            addCriterion("experience like", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceNotLike(String value) {
            addCriterion("experience not like", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceIn(List<String> values) {
            addCriterion("experience in", values, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceNotIn(List<String> values) {
            addCriterion("experience not in", values, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceBetween(String value1, String value2) {
            addCriterion("experience between", value1, value2, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceNotBetween(String value1, String value2) {
            addCriterion("experience not between", value1, value2, "experience");
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