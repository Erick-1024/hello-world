package com.cana.yundaex.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YundaexPersonalInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public YundaexPersonalInfoExample() {
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

        public Criteria andCellphoneIsNull() {
            addCriterion("cellphone is null");
            return (Criteria) this;
        }

        public Criteria andCellphoneIsNotNull() {
            addCriterion("cellphone is not null");
            return (Criteria) this;
        }

        public Criteria andCellphoneEqualTo(String value) {
            addCriterion("cellphone =", value, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneNotEqualTo(String value) {
            addCriterion("cellphone <>", value, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneGreaterThan(String value) {
            addCriterion("cellphone >", value, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneGreaterThanOrEqualTo(String value) {
            addCriterion("cellphone >=", value, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneLessThan(String value) {
            addCriterion("cellphone <", value, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneLessThanOrEqualTo(String value) {
            addCriterion("cellphone <=", value, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneLike(String value) {
            addCriterion("cellphone like", value, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneNotLike(String value) {
            addCriterion("cellphone not like", value, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneIn(List<String> values) {
            addCriterion("cellphone in", values, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneNotIn(List<String> values) {
            addCriterion("cellphone not in", values, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneBetween(String value1, String value2) {
            addCriterion("cellphone between", value1, value2, "cellphone");
            return (Criteria) this;
        }

        public Criteria andCellphoneNotBetween(String value1, String value2) {
            addCriterion("cellphone not between", value1, value2, "cellphone");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardNoIsNull() {
            addCriterion("resident_identity_card_no is null");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardNoIsNotNull() {
            addCriterion("resident_identity_card_no is not null");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardNoEqualTo(String value) {
            addCriterion("resident_identity_card_no =", value, "residentIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardNoNotEqualTo(String value) {
            addCriterion("resident_identity_card_no <>", value, "residentIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardNoGreaterThan(String value) {
            addCriterion("resident_identity_card_no >", value, "residentIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("resident_identity_card_no >=", value, "residentIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardNoLessThan(String value) {
            addCriterion("resident_identity_card_no <", value, "residentIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardNoLessThanOrEqualTo(String value) {
            addCriterion("resident_identity_card_no <=", value, "residentIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardNoLike(String value) {
            addCriterion("resident_identity_card_no like", value, "residentIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardNoNotLike(String value) {
            addCriterion("resident_identity_card_no not like", value, "residentIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardNoIn(List<String> values) {
            addCriterion("resident_identity_card_no in", values, "residentIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardNoNotIn(List<String> values) {
            addCriterion("resident_identity_card_no not in", values, "residentIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardNoBetween(String value1, String value2) {
            addCriterion("resident_identity_card_no between", value1, value2, "residentIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardNoNotBetween(String value1, String value2) {
            addCriterion("resident_identity_card_no not between", value1, value2, "residentIdentityCardNo");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardFrontMediaIdIsNull() {
            addCriterion("resident_identity_card_front_media_id is null");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardFrontMediaIdIsNotNull() {
            addCriterion("resident_identity_card_front_media_id is not null");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardFrontMediaIdEqualTo(String value) {
            addCriterion("resident_identity_card_front_media_id =", value, "residentIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardFrontMediaIdNotEqualTo(String value) {
            addCriterion("resident_identity_card_front_media_id <>", value, "residentIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardFrontMediaIdGreaterThan(String value) {
            addCriterion("resident_identity_card_front_media_id >", value, "residentIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardFrontMediaIdGreaterThanOrEqualTo(String value) {
            addCriterion("resident_identity_card_front_media_id >=", value, "residentIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardFrontMediaIdLessThan(String value) {
            addCriterion("resident_identity_card_front_media_id <", value, "residentIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardFrontMediaIdLessThanOrEqualTo(String value) {
            addCriterion("resident_identity_card_front_media_id <=", value, "residentIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardFrontMediaIdLike(String value) {
            addCriterion("resident_identity_card_front_media_id like", value, "residentIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardFrontMediaIdNotLike(String value) {
            addCriterion("resident_identity_card_front_media_id not like", value, "residentIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardFrontMediaIdIn(List<String> values) {
            addCriterion("resident_identity_card_front_media_id in", values, "residentIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardFrontMediaIdNotIn(List<String> values) {
            addCriterion("resident_identity_card_front_media_id not in", values, "residentIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardFrontMediaIdBetween(String value1, String value2) {
            addCriterion("resident_identity_card_front_media_id between", value1, value2, "residentIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardFrontMediaIdNotBetween(String value1, String value2) {
            addCriterion("resident_identity_card_front_media_id not between", value1, value2, "residentIdentityCardFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardBackMediaIdIsNull() {
            addCriterion("resident_identity_card_back_media_id is null");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardBackMediaIdIsNotNull() {
            addCriterion("resident_identity_card_back_media_id is not null");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardBackMediaIdEqualTo(String value) {
            addCriterion("resident_identity_card_back_media_id =", value, "residentIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardBackMediaIdNotEqualTo(String value) {
            addCriterion("resident_identity_card_back_media_id <>", value, "residentIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardBackMediaIdGreaterThan(String value) {
            addCriterion("resident_identity_card_back_media_id >", value, "residentIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardBackMediaIdGreaterThanOrEqualTo(String value) {
            addCriterion("resident_identity_card_back_media_id >=", value, "residentIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardBackMediaIdLessThan(String value) {
            addCriterion("resident_identity_card_back_media_id <", value, "residentIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardBackMediaIdLessThanOrEqualTo(String value) {
            addCriterion("resident_identity_card_back_media_id <=", value, "residentIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardBackMediaIdLike(String value) {
            addCriterion("resident_identity_card_back_media_id like", value, "residentIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardBackMediaIdNotLike(String value) {
            addCriterion("resident_identity_card_back_media_id not like", value, "residentIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardBackMediaIdIn(List<String> values) {
            addCriterion("resident_identity_card_back_media_id in", values, "residentIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardBackMediaIdNotIn(List<String> values) {
            addCriterion("resident_identity_card_back_media_id not in", values, "residentIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardBackMediaIdBetween(String value1, String value2) {
            addCriterion("resident_identity_card_back_media_id between", value1, value2, "residentIdentityCardBackMediaId");
            return (Criteria) this;
        }

        public Criteria andResidentIdentityCardBackMediaIdNotBetween(String value1, String value2) {
            addCriterion("resident_identity_card_back_media_id not between", value1, value2, "residentIdentityCardBackMediaId");
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

        public Criteria andAuditStateIsNull() {
            addCriterion("audit_state is null");
            return (Criteria) this;
        }

        public Criteria andAuditStateIsNotNull() {
            addCriterion("audit_state is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStateEqualTo(String value) {
            addCriterion("audit_state =", value, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateNotEqualTo(String value) {
            addCriterion("audit_state <>", value, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateGreaterThan(String value) {
            addCriterion("audit_state >", value, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateGreaterThanOrEqualTo(String value) {
            addCriterion("audit_state >=", value, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateLessThan(String value) {
            addCriterion("audit_state <", value, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateLessThanOrEqualTo(String value) {
            addCriterion("audit_state <=", value, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateLike(String value) {
            addCriterion("audit_state like", value, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateNotLike(String value) {
            addCriterion("audit_state not like", value, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateIn(List<String> values) {
            addCriterion("audit_state in", values, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateNotIn(List<String> values) {
            addCriterion("audit_state not in", values, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateBetween(String value1, String value2) {
            addCriterion("audit_state between", value1, value2, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditStateNotBetween(String value1, String value2) {
            addCriterion("audit_state not between", value1, value2, "auditState");
            return (Criteria) this;
        }

        public Criteria andAuditApplyTimeIsNull() {
            addCriterion("audit_apply_time is null");
            return (Criteria) this;
        }

        public Criteria andAuditApplyTimeIsNotNull() {
            addCriterion("audit_apply_time is not null");
            return (Criteria) this;
        }

        public Criteria andAuditApplyTimeEqualTo(Date value) {
            addCriterion("audit_apply_time =", value, "auditApplyTime");
            return (Criteria) this;
        }

        public Criteria andAuditApplyTimeNotEqualTo(Date value) {
            addCriterion("audit_apply_time <>", value, "auditApplyTime");
            return (Criteria) this;
        }

        public Criteria andAuditApplyTimeGreaterThan(Date value) {
            addCriterion("audit_apply_time >", value, "auditApplyTime");
            return (Criteria) this;
        }

        public Criteria andAuditApplyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("audit_apply_time >=", value, "auditApplyTime");
            return (Criteria) this;
        }

        public Criteria andAuditApplyTimeLessThan(Date value) {
            addCriterion("audit_apply_time <", value, "auditApplyTime");
            return (Criteria) this;
        }

        public Criteria andAuditApplyTimeLessThanOrEqualTo(Date value) {
            addCriterion("audit_apply_time <=", value, "auditApplyTime");
            return (Criteria) this;
        }

        public Criteria andAuditApplyTimeIn(List<Date> values) {
            addCriterion("audit_apply_time in", values, "auditApplyTime");
            return (Criteria) this;
        }

        public Criteria andAuditApplyTimeNotIn(List<Date> values) {
            addCriterion("audit_apply_time not in", values, "auditApplyTime");
            return (Criteria) this;
        }

        public Criteria andAuditApplyTimeBetween(Date value1, Date value2) {
            addCriterion("audit_apply_time between", value1, value2, "auditApplyTime");
            return (Criteria) this;
        }

        public Criteria andAuditApplyTimeNotBetween(Date value1, Date value2) {
            addCriterion("audit_apply_time not between", value1, value2, "auditApplyTime");
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

        public Criteria andRelatedCustomerIdIsNull() {
            addCriterion("related_customer_id is null");
            return (Criteria) this;
        }

        public Criteria andRelatedCustomerIdIsNotNull() {
            addCriterion("related_customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelatedCustomerIdEqualTo(String value) {
            addCriterion("related_customer_id =", value, "relatedCustomerId");
            return (Criteria) this;
        }

        public Criteria andRelatedCustomerIdNotEqualTo(String value) {
            addCriterion("related_customer_id <>", value, "relatedCustomerId");
            return (Criteria) this;
        }

        public Criteria andRelatedCustomerIdGreaterThan(String value) {
            addCriterion("related_customer_id >", value, "relatedCustomerId");
            return (Criteria) this;
        }

        public Criteria andRelatedCustomerIdGreaterThanOrEqualTo(String value) {
            addCriterion("related_customer_id >=", value, "relatedCustomerId");
            return (Criteria) this;
        }

        public Criteria andRelatedCustomerIdLessThan(String value) {
            addCriterion("related_customer_id <", value, "relatedCustomerId");
            return (Criteria) this;
        }

        public Criteria andRelatedCustomerIdLessThanOrEqualTo(String value) {
            addCriterion("related_customer_id <=", value, "relatedCustomerId");
            return (Criteria) this;
        }

        public Criteria andRelatedCustomerIdLike(String value) {
            addCriterion("related_customer_id like", value, "relatedCustomerId");
            return (Criteria) this;
        }

        public Criteria andRelatedCustomerIdNotLike(String value) {
            addCriterion("related_customer_id not like", value, "relatedCustomerId");
            return (Criteria) this;
        }

        public Criteria andRelatedCustomerIdIn(List<String> values) {
            addCriterion("related_customer_id in", values, "relatedCustomerId");
            return (Criteria) this;
        }

        public Criteria andRelatedCustomerIdNotIn(List<String> values) {
            addCriterion("related_customer_id not in", values, "relatedCustomerId");
            return (Criteria) this;
        }

        public Criteria andRelatedCustomerIdBetween(String value1, String value2) {
            addCriterion("related_customer_id between", value1, value2, "relatedCustomerId");
            return (Criteria) this;
        }

        public Criteria andRelatedCustomerIdNotBetween(String value1, String value2) {
            addCriterion("related_customer_id not between", value1, value2, "relatedCustomerId");
            return (Criteria) this;
        }

        public Criteria andStationNameIsNull() {
            addCriterion("station_name is null");
            return (Criteria) this;
        }

        public Criteria andStationNameIsNotNull() {
            addCriterion("station_name is not null");
            return (Criteria) this;
        }

        public Criteria andStationNameEqualTo(String value) {
            addCriterion("station_name =", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotEqualTo(String value) {
            addCriterion("station_name <>", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameGreaterThan(String value) {
            addCriterion("station_name >", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameGreaterThanOrEqualTo(String value) {
            addCriterion("station_name >=", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameLessThan(String value) {
            addCriterion("station_name <", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameLessThanOrEqualTo(String value) {
            addCriterion("station_name <=", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameLike(String value) {
            addCriterion("station_name like", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotLike(String value) {
            addCriterion("station_name not like", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameIn(List<String> values) {
            addCriterion("station_name in", values, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotIn(List<String> values) {
            addCriterion("station_name not in", values, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameBetween(String value1, String value2) {
            addCriterion("station_name between", value1, value2, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotBetween(String value1, String value2) {
            addCriterion("station_name not between", value1, value2, "stationName");
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

        public Criteria andAuditorNameIsNull() {
            addCriterion("auditor_name is null");
            return (Criteria) this;
        }

        public Criteria andAuditorNameIsNotNull() {
            addCriterion("auditor_name is not null");
            return (Criteria) this;
        }

        public Criteria andAuditorNameEqualTo(String value) {
            addCriterion("auditor_name =", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameNotEqualTo(String value) {
            addCriterion("auditor_name <>", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameGreaterThan(String value) {
            addCriterion("auditor_name >", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameGreaterThanOrEqualTo(String value) {
            addCriterion("auditor_name >=", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameLessThan(String value) {
            addCriterion("auditor_name <", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameLessThanOrEqualTo(String value) {
            addCriterion("auditor_name <=", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameLike(String value) {
            addCriterion("auditor_name like", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameNotLike(String value) {
            addCriterion("auditor_name not like", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameIn(List<String> values) {
            addCriterion("auditor_name in", values, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameNotIn(List<String> values) {
            addCriterion("auditor_name not in", values, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameBetween(String value1, String value2) {
            addCriterion("auditor_name between", value1, value2, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameNotBetween(String value1, String value2) {
            addCriterion("auditor_name not between", value1, value2, "auditorName");
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