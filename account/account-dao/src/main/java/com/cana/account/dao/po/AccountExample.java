package com.cana.account.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public AccountExample() {
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

        public Criteria andAccumulationIdIsNull() {
            addCriterion("accumulation_id is null");
            return (Criteria) this;
        }

        public Criteria andAccumulationIdIsNotNull() {
            addCriterion("accumulation_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccumulationIdEqualTo(String value) {
            addCriterion("accumulation_id =", value, "accumulationId");
            return (Criteria) this;
        }

        public Criteria andAccumulationIdNotEqualTo(String value) {
            addCriterion("accumulation_id <>", value, "accumulationId");
            return (Criteria) this;
        }

        public Criteria andAccumulationIdGreaterThan(String value) {
            addCriterion("accumulation_id >", value, "accumulationId");
            return (Criteria) this;
        }

        public Criteria andAccumulationIdGreaterThanOrEqualTo(String value) {
            addCriterion("accumulation_id >=", value, "accumulationId");
            return (Criteria) this;
        }

        public Criteria andAccumulationIdLessThan(String value) {
            addCriterion("accumulation_id <", value, "accumulationId");
            return (Criteria) this;
        }

        public Criteria andAccumulationIdLessThanOrEqualTo(String value) {
            addCriterion("accumulation_id <=", value, "accumulationId");
            return (Criteria) this;
        }

        public Criteria andAccumulationIdLike(String value) {
            addCriterion("accumulation_id like", value, "accumulationId");
            return (Criteria) this;
        }

        public Criteria andAccumulationIdNotLike(String value) {
            addCriterion("accumulation_id not like", value, "accumulationId");
            return (Criteria) this;
        }

        public Criteria andAccumulationIdIn(List<String> values) {
            addCriterion("accumulation_id in", values, "accumulationId");
            return (Criteria) this;
        }

        public Criteria andAccumulationIdNotIn(List<String> values) {
            addCriterion("accumulation_id not in", values, "accumulationId");
            return (Criteria) this;
        }

        public Criteria andAccumulationIdBetween(String value1, String value2) {
            addCriterion("accumulation_id between", value1, value2, "accumulationId");
            return (Criteria) this;
        }

        public Criteria andAccumulationIdNotBetween(String value1, String value2) {
            addCriterion("accumulation_id not between", value1, value2, "accumulationId");
            return (Criteria) this;
        }

        public Criteria andAccountApplyIdIsNull() {
            addCriterion("account_apply_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountApplyIdIsNotNull() {
            addCriterion("account_apply_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountApplyIdEqualTo(String value) {
            addCriterion("account_apply_id =", value, "accountApplyId");
            return (Criteria) this;
        }

        public Criteria andAccountApplyIdNotEqualTo(String value) {
            addCriterion("account_apply_id <>", value, "accountApplyId");
            return (Criteria) this;
        }

        public Criteria andAccountApplyIdGreaterThan(String value) {
            addCriterion("account_apply_id >", value, "accountApplyId");
            return (Criteria) this;
        }

        public Criteria andAccountApplyIdGreaterThanOrEqualTo(String value) {
            addCriterion("account_apply_id >=", value, "accountApplyId");
            return (Criteria) this;
        }

        public Criteria andAccountApplyIdLessThan(String value) {
            addCriterion("account_apply_id <", value, "accountApplyId");
            return (Criteria) this;
        }

        public Criteria andAccountApplyIdLessThanOrEqualTo(String value) {
            addCriterion("account_apply_id <=", value, "accountApplyId");
            return (Criteria) this;
        }

        public Criteria andAccountApplyIdLike(String value) {
            addCriterion("account_apply_id like", value, "accountApplyId");
            return (Criteria) this;
        }

        public Criteria andAccountApplyIdNotLike(String value) {
            addCriterion("account_apply_id not like", value, "accountApplyId");
            return (Criteria) this;
        }

        public Criteria andAccountApplyIdIn(List<String> values) {
            addCriterion("account_apply_id in", values, "accountApplyId");
            return (Criteria) this;
        }

        public Criteria andAccountApplyIdNotIn(List<String> values) {
            addCriterion("account_apply_id not in", values, "accountApplyId");
            return (Criteria) this;
        }

        public Criteria andAccountApplyIdBetween(String value1, String value2) {
            addCriterion("account_apply_id between", value1, value2, "accountApplyId");
            return (Criteria) this;
        }

        public Criteria andAccountApplyIdNotBetween(String value1, String value2) {
            addCriterion("account_apply_id not between", value1, value2, "accountApplyId");
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

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(String value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(String value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(String value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(String value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLike(String value) {
            addCriterion("company_id like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotLike(String value) {
            addCriterion("company_id not like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<String> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<String> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(String value1, String value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(String value1, String value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
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

        public Criteria andAccountSupervisionIdIsNull() {
            addCriterion("account_supervision_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdIsNotNull() {
            addCriterion("account_supervision_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdEqualTo(String value) {
            addCriterion("account_supervision_id =", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdNotEqualTo(String value) {
            addCriterion("account_supervision_id <>", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdGreaterThan(String value) {
            addCriterion("account_supervision_id >", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdGreaterThanOrEqualTo(String value) {
            addCriterion("account_supervision_id >=", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdLessThan(String value) {
            addCriterion("account_supervision_id <", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdLessThanOrEqualTo(String value) {
            addCriterion("account_supervision_id <=", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdLike(String value) {
            addCriterion("account_supervision_id like", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdNotLike(String value) {
            addCriterion("account_supervision_id not like", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdIn(List<String> values) {
            addCriterion("account_supervision_id in", values, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdNotIn(List<String> values) {
            addCriterion("account_supervision_id not in", values, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdBetween(String value1, String value2) {
            addCriterion("account_supervision_id between", value1, value2, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdNotBetween(String value1, String value2) {
            addCriterion("account_supervision_id not between", value1, value2, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdIsNull() {
            addCriterion("supervisor_id is null");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdIsNotNull() {
            addCriterion("supervisor_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdEqualTo(String value) {
            addCriterion("supervisor_id =", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdNotEqualTo(String value) {
            addCriterion("supervisor_id <>", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdGreaterThan(String value) {
            addCriterion("supervisor_id >", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdGreaterThanOrEqualTo(String value) {
            addCriterion("supervisor_id >=", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdLessThan(String value) {
            addCriterion("supervisor_id <", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdLessThanOrEqualTo(String value) {
            addCriterion("supervisor_id <=", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdLike(String value) {
            addCriterion("supervisor_id like", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdNotLike(String value) {
            addCriterion("supervisor_id not like", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdIn(List<String> values) {
            addCriterion("supervisor_id in", values, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdNotIn(List<String> values) {
            addCriterion("supervisor_id not in", values, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdBetween(String value1, String value2) {
            addCriterion("supervisor_id between", value1, value2, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdNotBetween(String value1, String value2) {
            addCriterion("supervisor_id not between", value1, value2, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameIsNull() {
            addCriterion("supervisor_name is null");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameIsNotNull() {
            addCriterion("supervisor_name is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameEqualTo(String value) {
            addCriterion("supervisor_name =", value, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameNotEqualTo(String value) {
            addCriterion("supervisor_name <>", value, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameGreaterThan(String value) {
            addCriterion("supervisor_name >", value, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameGreaterThanOrEqualTo(String value) {
            addCriterion("supervisor_name >=", value, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameLessThan(String value) {
            addCriterion("supervisor_name <", value, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameLessThanOrEqualTo(String value) {
            addCriterion("supervisor_name <=", value, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameLike(String value) {
            addCriterion("supervisor_name like", value, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameNotLike(String value) {
            addCriterion("supervisor_name not like", value, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameIn(List<String> values) {
            addCriterion("supervisor_name in", values, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameNotIn(List<String> values) {
            addCriterion("supervisor_name not in", values, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameBetween(String value1, String value2) {
            addCriterion("supervisor_name between", value1, value2, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andSupervisorNameNotBetween(String value1, String value2) {
            addCriterion("supervisor_name not between", value1, value2, "supervisorName");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIsNull() {
            addCriterion("account_type is null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIsNotNull() {
            addCriterion("account_type is not null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeEqualTo(String value) {
            addCriterion("account_type =", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotEqualTo(String value) {
            addCriterion("account_type <>", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThan(String value) {
            addCriterion("account_type >", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThanOrEqualTo(String value) {
            addCriterion("account_type >=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThan(String value) {
            addCriterion("account_type <", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThanOrEqualTo(String value) {
            addCriterion("account_type <=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLike(String value) {
            addCriterion("account_type like", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotLike(String value) {
            addCriterion("account_type not like", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIn(List<String> values) {
            addCriterion("account_type in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotIn(List<String> values) {
            addCriterion("account_type not in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeBetween(String value1, String value2) {
            addCriterion("account_type between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotBetween(String value1, String value2) {
            addCriterion("account_type not between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNull() {
            addCriterion("account_no is null");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNotNull() {
            addCriterion("account_no is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNoEqualTo(String value) {
            addCriterion("account_no =", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotEqualTo(String value) {
            addCriterion("account_no <>", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThan(String value) {
            addCriterion("account_no >", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("account_no >=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThan(String value) {
            addCriterion("account_no <", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThanOrEqualTo(String value) {
            addCriterion("account_no <=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLike(String value) {
            addCriterion("account_no like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotLike(String value) {
            addCriterion("account_no not like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoIn(List<String> values) {
            addCriterion("account_no in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotIn(List<String> values) {
            addCriterion("account_no not in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoBetween(String value1, String value2) {
            addCriterion("account_no between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotBetween(String value1, String value2) {
            addCriterion("account_no not between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountStatusIsNull() {
            addCriterion("account_status is null");
            return (Criteria) this;
        }

        public Criteria andAccountStatusIsNotNull() {
            addCriterion("account_status is not null");
            return (Criteria) this;
        }

        public Criteria andAccountStatusEqualTo(String value) {
            addCriterion("account_status =", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusNotEqualTo(String value) {
            addCriterion("account_status <>", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusGreaterThan(String value) {
            addCriterion("account_status >", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusGreaterThanOrEqualTo(String value) {
            addCriterion("account_status >=", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusLessThan(String value) {
            addCriterion("account_status <", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusLessThanOrEqualTo(String value) {
            addCriterion("account_status <=", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusLike(String value) {
            addCriterion("account_status like", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusNotLike(String value) {
            addCriterion("account_status not like", value, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusIn(List<String> values) {
            addCriterion("account_status in", values, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusNotIn(List<String> values) {
            addCriterion("account_status not in", values, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusBetween(String value1, String value2) {
            addCriterion("account_status between", value1, value2, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andAccountStatusNotBetween(String value1, String value2) {
            addCriterion("account_status not between", value1, value2, "accountStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusIsNull() {
            addCriterion("supervision_status is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusIsNotNull() {
            addCriterion("supervision_status is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusEqualTo(String value) {
            addCriterion("supervision_status =", value, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusNotEqualTo(String value) {
            addCriterion("supervision_status <>", value, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusGreaterThan(String value) {
            addCriterion("supervision_status >", value, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusGreaterThanOrEqualTo(String value) {
            addCriterion("supervision_status >=", value, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusLessThan(String value) {
            addCriterion("supervision_status <", value, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusLessThanOrEqualTo(String value) {
            addCriterion("supervision_status <=", value, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusLike(String value) {
            addCriterion("supervision_status like", value, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusNotLike(String value) {
            addCriterion("supervision_status not like", value, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusIn(List<String> values) {
            addCriterion("supervision_status in", values, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusNotIn(List<String> values) {
            addCriterion("supervision_status not in", values, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusBetween(String value1, String value2) {
            addCriterion("supervision_status between", value1, value2, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusNotBetween(String value1, String value2) {
            addCriterion("supervision_status not between", value1, value2, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andAccumulationStatusIsNull() {
            addCriterion("accumulation_status is null");
            return (Criteria) this;
        }

        public Criteria andAccumulationStatusIsNotNull() {
            addCriterion("accumulation_status is not null");
            return (Criteria) this;
        }

        public Criteria andAccumulationStatusEqualTo(String value) {
            addCriterion("accumulation_status =", value, "accumulationStatus");
            return (Criteria) this;
        }

        public Criteria andAccumulationStatusNotEqualTo(String value) {
            addCriterion("accumulation_status <>", value, "accumulationStatus");
            return (Criteria) this;
        }

        public Criteria andAccumulationStatusGreaterThan(String value) {
            addCriterion("accumulation_status >", value, "accumulationStatus");
            return (Criteria) this;
        }

        public Criteria andAccumulationStatusGreaterThanOrEqualTo(String value) {
            addCriterion("accumulation_status >=", value, "accumulationStatus");
            return (Criteria) this;
        }

        public Criteria andAccumulationStatusLessThan(String value) {
            addCriterion("accumulation_status <", value, "accumulationStatus");
            return (Criteria) this;
        }

        public Criteria andAccumulationStatusLessThanOrEqualTo(String value) {
            addCriterion("accumulation_status <=", value, "accumulationStatus");
            return (Criteria) this;
        }

        public Criteria andAccumulationStatusLike(String value) {
            addCriterion("accumulation_status like", value, "accumulationStatus");
            return (Criteria) this;
        }

        public Criteria andAccumulationStatusNotLike(String value) {
            addCriterion("accumulation_status not like", value, "accumulationStatus");
            return (Criteria) this;
        }

        public Criteria andAccumulationStatusIn(List<String> values) {
            addCriterion("accumulation_status in", values, "accumulationStatus");
            return (Criteria) this;
        }

        public Criteria andAccumulationStatusNotIn(List<String> values) {
            addCriterion("accumulation_status not in", values, "accumulationStatus");
            return (Criteria) this;
        }

        public Criteria andAccumulationStatusBetween(String value1, String value2) {
            addCriterion("accumulation_status between", value1, value2, "accumulationStatus");
            return (Criteria) this;
        }

        public Criteria andAccumulationStatusNotBetween(String value1, String value2) {
            addCriterion("accumulation_status not between", value1, value2, "accumulationStatus");
            return (Criteria) this;
        }

        public Criteria andBuyerNameIsNull() {
            addCriterion("buyer_name is null");
            return (Criteria) this;
        }

        public Criteria andBuyerNameIsNotNull() {
            addCriterion("buyer_name is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerNameEqualTo(String value) {
            addCriterion("buyer_name =", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameNotEqualTo(String value) {
            addCriterion("buyer_name <>", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameGreaterThan(String value) {
            addCriterion("buyer_name >", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_name >=", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameLessThan(String value) {
            addCriterion("buyer_name <", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameLessThanOrEqualTo(String value) {
            addCriterion("buyer_name <=", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameLike(String value) {
            addCriterion("buyer_name like", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameNotLike(String value) {
            addCriterion("buyer_name not like", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameIn(List<String> values) {
            addCriterion("buyer_name in", values, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameNotIn(List<String> values) {
            addCriterion("buyer_name not in", values, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameBetween(String value1, String value2) {
            addCriterion("buyer_name between", value1, value2, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameNotBetween(String value1, String value2) {
            addCriterion("buyer_name not between", value1, value2, "buyerName");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentIsNull() {
            addCriterion("is_default_repayment is null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentIsNotNull() {
            addCriterion("is_default_repayment is not null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentEqualTo(Boolean value) {
            addCriterion("is_default_repayment =", value, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentNotEqualTo(Boolean value) {
            addCriterion("is_default_repayment <>", value, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentGreaterThan(Boolean value) {
            addCriterion("is_default_repayment >", value, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_default_repayment >=", value, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentLessThan(Boolean value) {
            addCriterion("is_default_repayment <", value, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentLessThanOrEqualTo(Boolean value) {
            addCriterion("is_default_repayment <=", value, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentIn(List<Boolean> values) {
            addCriterion("is_default_repayment in", values, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentNotIn(List<Boolean> values) {
            addCriterion("is_default_repayment not in", values, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentBetween(Boolean value1, Boolean value2) {
            addCriterion("is_default_repayment between", value1, value2, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_default_repayment not between", value1, value2, "isDefaultRepayment");
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

        public Criteria andOperateCompanyIdIsNull() {
            addCriterion("operate_company_id is null");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyIdIsNotNull() {
            addCriterion("operate_company_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyIdEqualTo(String value) {
            addCriterion("operate_company_id =", value, "operateCompanyId");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyIdNotEqualTo(String value) {
            addCriterion("operate_company_id <>", value, "operateCompanyId");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyIdGreaterThan(String value) {
            addCriterion("operate_company_id >", value, "operateCompanyId");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("operate_company_id >=", value, "operateCompanyId");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyIdLessThan(String value) {
            addCriterion("operate_company_id <", value, "operateCompanyId");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("operate_company_id <=", value, "operateCompanyId");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyIdLike(String value) {
            addCriterion("operate_company_id like", value, "operateCompanyId");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyIdNotLike(String value) {
            addCriterion("operate_company_id not like", value, "operateCompanyId");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyIdIn(List<String> values) {
            addCriterion("operate_company_id in", values, "operateCompanyId");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyIdNotIn(List<String> values) {
            addCriterion("operate_company_id not in", values, "operateCompanyId");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyIdBetween(String value1, String value2) {
            addCriterion("operate_company_id between", value1, value2, "operateCompanyId");
            return (Criteria) this;
        }

        public Criteria andOperateCompanyIdNotBetween(String value1, String value2) {
            addCriterion("operate_company_id not between", value1, value2, "operateCompanyId");
            return (Criteria) this;
        }

        public Criteria andIsTransferInAccountIsNull() {
            addCriterion("is_transfer_in_account is null");
            return (Criteria) this;
        }

        public Criteria andIsTransferInAccountIsNotNull() {
            addCriterion("is_transfer_in_account is not null");
            return (Criteria) this;
        }

        public Criteria andIsTransferInAccountEqualTo(Boolean value) {
            addCriterion("is_transfer_in_account =", value, "isTransferInAccount");
            return (Criteria) this;
        }

        public Criteria andIsTransferInAccountNotEqualTo(Boolean value) {
            addCriterion("is_transfer_in_account <>", value, "isTransferInAccount");
            return (Criteria) this;
        }

        public Criteria andIsTransferInAccountGreaterThan(Boolean value) {
            addCriterion("is_transfer_in_account >", value, "isTransferInAccount");
            return (Criteria) this;
        }

        public Criteria andIsTransferInAccountGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_transfer_in_account >=", value, "isTransferInAccount");
            return (Criteria) this;
        }

        public Criteria andIsTransferInAccountLessThan(Boolean value) {
            addCriterion("is_transfer_in_account <", value, "isTransferInAccount");
            return (Criteria) this;
        }

        public Criteria andIsTransferInAccountLessThanOrEqualTo(Boolean value) {
            addCriterion("is_transfer_in_account <=", value, "isTransferInAccount");
            return (Criteria) this;
        }

        public Criteria andIsTransferInAccountIn(List<Boolean> values) {
            addCriterion("is_transfer_in_account in", values, "isTransferInAccount");
            return (Criteria) this;
        }

        public Criteria andIsTransferInAccountNotIn(List<Boolean> values) {
            addCriterion("is_transfer_in_account not in", values, "isTransferInAccount");
            return (Criteria) this;
        }

        public Criteria andIsTransferInAccountBetween(Boolean value1, Boolean value2) {
            addCriterion("is_transfer_in_account between", value1, value2, "isTransferInAccount");
            return (Criteria) this;
        }

        public Criteria andIsTransferInAccountNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_transfer_in_account not between", value1, value2, "isTransferInAccount");
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