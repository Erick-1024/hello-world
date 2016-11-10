package com.cana.account.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public AccountApplyExample() {
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

        public Criteria andApplyStatusIsNull() {
            addCriterion("apply_status is null");
            return (Criteria) this;
        }

        public Criteria andApplyStatusIsNotNull() {
            addCriterion("apply_status is not null");
            return (Criteria) this;
        }

        public Criteria andApplyStatusEqualTo(String value) {
            addCriterion("apply_status =", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusNotEqualTo(String value) {
            addCriterion("apply_status <>", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusGreaterThan(String value) {
            addCriterion("apply_status >", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusGreaterThanOrEqualTo(String value) {
            addCriterion("apply_status >=", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusLessThan(String value) {
            addCriterion("apply_status <", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusLessThanOrEqualTo(String value) {
            addCriterion("apply_status <=", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusLike(String value) {
            addCriterion("apply_status like", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusNotLike(String value) {
            addCriterion("apply_status not like", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusIn(List<String> values) {
            addCriterion("apply_status in", values, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusNotIn(List<String> values) {
            addCriterion("apply_status not in", values, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusBetween(String value1, String value2) {
            addCriterion("apply_status between", value1, value2, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusNotBetween(String value1, String value2) {
            addCriterion("apply_status not between", value1, value2, "applyStatus");
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

        public Criteria andSupervisorTypeIsNull() {
            addCriterion("supervisor_type is null");
            return (Criteria) this;
        }

        public Criteria andSupervisorTypeIsNotNull() {
            addCriterion("supervisor_type is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisorTypeEqualTo(String value) {
            addCriterion("supervisor_type =", value, "supervisorType");
            return (Criteria) this;
        }

        public Criteria andSupervisorTypeNotEqualTo(String value) {
            addCriterion("supervisor_type <>", value, "supervisorType");
            return (Criteria) this;
        }

        public Criteria andSupervisorTypeGreaterThan(String value) {
            addCriterion("supervisor_type >", value, "supervisorType");
            return (Criteria) this;
        }

        public Criteria andSupervisorTypeGreaterThanOrEqualTo(String value) {
            addCriterion("supervisor_type >=", value, "supervisorType");
            return (Criteria) this;
        }

        public Criteria andSupervisorTypeLessThan(String value) {
            addCriterion("supervisor_type <", value, "supervisorType");
            return (Criteria) this;
        }

        public Criteria andSupervisorTypeLessThanOrEqualTo(String value) {
            addCriterion("supervisor_type <=", value, "supervisorType");
            return (Criteria) this;
        }

        public Criteria andSupervisorTypeLike(String value) {
            addCriterion("supervisor_type like", value, "supervisorType");
            return (Criteria) this;
        }

        public Criteria andSupervisorTypeNotLike(String value) {
            addCriterion("supervisor_type not like", value, "supervisorType");
            return (Criteria) this;
        }

        public Criteria andSupervisorTypeIn(List<String> values) {
            addCriterion("supervisor_type in", values, "supervisorType");
            return (Criteria) this;
        }

        public Criteria andSupervisorTypeNotIn(List<String> values) {
            addCriterion("supervisor_type not in", values, "supervisorType");
            return (Criteria) this;
        }

        public Criteria andSupervisorTypeBetween(String value1, String value2) {
            addCriterion("supervisor_type between", value1, value2, "supervisorType");
            return (Criteria) this;
        }

        public Criteria andSupervisorTypeNotBetween(String value1, String value2) {
            addCriterion("supervisor_type not between", value1, value2, "supervisorType");
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

        public Criteria andAgentCompanyIdIsNull() {
            addCriterion("agent_company_id is null");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyIdIsNotNull() {
            addCriterion("agent_company_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyIdEqualTo(String value) {
            addCriterion("agent_company_id =", value, "agentCompanyId");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyIdNotEqualTo(String value) {
            addCriterion("agent_company_id <>", value, "agentCompanyId");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyIdGreaterThan(String value) {
            addCriterion("agent_company_id >", value, "agentCompanyId");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("agent_company_id >=", value, "agentCompanyId");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyIdLessThan(String value) {
            addCriterion("agent_company_id <", value, "agentCompanyId");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("agent_company_id <=", value, "agentCompanyId");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyIdLike(String value) {
            addCriterion("agent_company_id like", value, "agentCompanyId");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyIdNotLike(String value) {
            addCriterion("agent_company_id not like", value, "agentCompanyId");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyIdIn(List<String> values) {
            addCriterion("agent_company_id in", values, "agentCompanyId");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyIdNotIn(List<String> values) {
            addCriterion("agent_company_id not in", values, "agentCompanyId");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyIdBetween(String value1, String value2) {
            addCriterion("agent_company_id between", value1, value2, "agentCompanyId");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyIdNotBetween(String value1, String value2) {
            addCriterion("agent_company_id not between", value1, value2, "agentCompanyId");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyNameIsNull() {
            addCriterion("agent_company_name is null");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyNameIsNotNull() {
            addCriterion("agent_company_name is not null");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyNameEqualTo(String value) {
            addCriterion("agent_company_name =", value, "agentCompanyName");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyNameNotEqualTo(String value) {
            addCriterion("agent_company_name <>", value, "agentCompanyName");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyNameGreaterThan(String value) {
            addCriterion("agent_company_name >", value, "agentCompanyName");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("agent_company_name >=", value, "agentCompanyName");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyNameLessThan(String value) {
            addCriterion("agent_company_name <", value, "agentCompanyName");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("agent_company_name <=", value, "agentCompanyName");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyNameLike(String value) {
            addCriterion("agent_company_name like", value, "agentCompanyName");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyNameNotLike(String value) {
            addCriterion("agent_company_name not like", value, "agentCompanyName");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyNameIn(List<String> values) {
            addCriterion("agent_company_name in", values, "agentCompanyName");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyNameNotIn(List<String> values) {
            addCriterion("agent_company_name not in", values, "agentCompanyName");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyNameBetween(String value1, String value2) {
            addCriterion("agent_company_name between", value1, value2, "agentCompanyName");
            return (Criteria) this;
        }

        public Criteria andAgentCompanyNameNotBetween(String value1, String value2) {
            addCriterion("agent_company_name not between", value1, value2, "agentCompanyName");
            return (Criteria) this;
        }

        public Criteria andAccountNumberIsNull() {
            addCriterion("account_number is null");
            return (Criteria) this;
        }

        public Criteria andAccountNumberIsNotNull() {
            addCriterion("account_number is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNumberEqualTo(Integer value) {
            addCriterion("account_number =", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberNotEqualTo(Integer value) {
            addCriterion("account_number <>", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberGreaterThan(Integer value) {
            addCriterion("account_number >", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("account_number >=", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberLessThan(Integer value) {
            addCriterion("account_number <", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberLessThanOrEqualTo(Integer value) {
            addCriterion("account_number <=", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberIn(List<Integer> values) {
            addCriterion("account_number in", values, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberNotIn(List<Integer> values) {
            addCriterion("account_number not in", values, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberBetween(Integer value1, Integer value2) {
            addCriterion("account_number between", value1, value2, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("account_number not between", value1, value2, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andBuyerNamesIsNull() {
            addCriterion("buyer_names is null");
            return (Criteria) this;
        }

        public Criteria andBuyerNamesIsNotNull() {
            addCriterion("buyer_names is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerNamesEqualTo(String value) {
            addCriterion("buyer_names =", value, "buyerNames");
            return (Criteria) this;
        }

        public Criteria andBuyerNamesNotEqualTo(String value) {
            addCriterion("buyer_names <>", value, "buyerNames");
            return (Criteria) this;
        }

        public Criteria andBuyerNamesGreaterThan(String value) {
            addCriterion("buyer_names >", value, "buyerNames");
            return (Criteria) this;
        }

        public Criteria andBuyerNamesGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_names >=", value, "buyerNames");
            return (Criteria) this;
        }

        public Criteria andBuyerNamesLessThan(String value) {
            addCriterion("buyer_names <", value, "buyerNames");
            return (Criteria) this;
        }

        public Criteria andBuyerNamesLessThanOrEqualTo(String value) {
            addCriterion("buyer_names <=", value, "buyerNames");
            return (Criteria) this;
        }

        public Criteria andBuyerNamesLike(String value) {
            addCriterion("buyer_names like", value, "buyerNames");
            return (Criteria) this;
        }

        public Criteria andBuyerNamesNotLike(String value) {
            addCriterion("buyer_names not like", value, "buyerNames");
            return (Criteria) this;
        }

        public Criteria andBuyerNamesIn(List<String> values) {
            addCriterion("buyer_names in", values, "buyerNames");
            return (Criteria) this;
        }

        public Criteria andBuyerNamesNotIn(List<String> values) {
            addCriterion("buyer_names not in", values, "buyerNames");
            return (Criteria) this;
        }

        public Criteria andBuyerNamesBetween(String value1, String value2) {
            addCriterion("buyer_names between", value1, value2, "buyerNames");
            return (Criteria) this;
        }

        public Criteria andBuyerNamesNotBetween(String value1, String value2) {
            addCriterion("buyer_names not between", value1, value2, "buyerNames");
            return (Criteria) this;
        }

        public Criteria andSupervisionAccountIdIsNull() {
            addCriterion("supervision_account_id is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionAccountIdIsNotNull() {
            addCriterion("supervision_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionAccountIdEqualTo(String value) {
            addCriterion("supervision_account_id =", value, "supervisionAccountId");
            return (Criteria) this;
        }

        public Criteria andSupervisionAccountIdNotEqualTo(String value) {
            addCriterion("supervision_account_id <>", value, "supervisionAccountId");
            return (Criteria) this;
        }

        public Criteria andSupervisionAccountIdGreaterThan(String value) {
            addCriterion("supervision_account_id >", value, "supervisionAccountId");
            return (Criteria) this;
        }

        public Criteria andSupervisionAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("supervision_account_id >=", value, "supervisionAccountId");
            return (Criteria) this;
        }

        public Criteria andSupervisionAccountIdLessThan(String value) {
            addCriterion("supervision_account_id <", value, "supervisionAccountId");
            return (Criteria) this;
        }

        public Criteria andSupervisionAccountIdLessThanOrEqualTo(String value) {
            addCriterion("supervision_account_id <=", value, "supervisionAccountId");
            return (Criteria) this;
        }

        public Criteria andSupervisionAccountIdLike(String value) {
            addCriterion("supervision_account_id like", value, "supervisionAccountId");
            return (Criteria) this;
        }

        public Criteria andSupervisionAccountIdNotLike(String value) {
            addCriterion("supervision_account_id not like", value, "supervisionAccountId");
            return (Criteria) this;
        }

        public Criteria andSupervisionAccountIdIn(List<String> values) {
            addCriterion("supervision_account_id in", values, "supervisionAccountId");
            return (Criteria) this;
        }

        public Criteria andSupervisionAccountIdNotIn(List<String> values) {
            addCriterion("supervision_account_id not in", values, "supervisionAccountId");
            return (Criteria) this;
        }

        public Criteria andSupervisionAccountIdBetween(String value1, String value2) {
            addCriterion("supervision_account_id between", value1, value2, "supervisionAccountId");
            return (Criteria) this;
        }

        public Criteria andSupervisionAccountIdNotBetween(String value1, String value2) {
            addCriterion("supervision_account_id not between", value1, value2, "supervisionAccountId");
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

        public Criteria andContactJobTitleIsNull() {
            addCriterion("contact_job_title is null");
            return (Criteria) this;
        }

        public Criteria andContactJobTitleIsNotNull() {
            addCriterion("contact_job_title is not null");
            return (Criteria) this;
        }

        public Criteria andContactJobTitleEqualTo(String value) {
            addCriterion("contact_job_title =", value, "contactJobTitle");
            return (Criteria) this;
        }

        public Criteria andContactJobTitleNotEqualTo(String value) {
            addCriterion("contact_job_title <>", value, "contactJobTitle");
            return (Criteria) this;
        }

        public Criteria andContactJobTitleGreaterThan(String value) {
            addCriterion("contact_job_title >", value, "contactJobTitle");
            return (Criteria) this;
        }

        public Criteria andContactJobTitleGreaterThanOrEqualTo(String value) {
            addCriterion("contact_job_title >=", value, "contactJobTitle");
            return (Criteria) this;
        }

        public Criteria andContactJobTitleLessThan(String value) {
            addCriterion("contact_job_title <", value, "contactJobTitle");
            return (Criteria) this;
        }

        public Criteria andContactJobTitleLessThanOrEqualTo(String value) {
            addCriterion("contact_job_title <=", value, "contactJobTitle");
            return (Criteria) this;
        }

        public Criteria andContactJobTitleLike(String value) {
            addCriterion("contact_job_title like", value, "contactJobTitle");
            return (Criteria) this;
        }

        public Criteria andContactJobTitleNotLike(String value) {
            addCriterion("contact_job_title not like", value, "contactJobTitle");
            return (Criteria) this;
        }

        public Criteria andContactJobTitleIn(List<String> values) {
            addCriterion("contact_job_title in", values, "contactJobTitle");
            return (Criteria) this;
        }

        public Criteria andContactJobTitleNotIn(List<String> values) {
            addCriterion("contact_job_title not in", values, "contactJobTitle");
            return (Criteria) this;
        }

        public Criteria andContactJobTitleBetween(String value1, String value2) {
            addCriterion("contact_job_title between", value1, value2, "contactJobTitle");
            return (Criteria) this;
        }

        public Criteria andContactJobTitleNotBetween(String value1, String value2) {
            addCriterion("contact_job_title not between", value1, value2, "contactJobTitle");
            return (Criteria) this;
        }

        public Criteria andContactTelIsNull() {
            addCriterion("contact_tel is null");
            return (Criteria) this;
        }

        public Criteria andContactTelIsNotNull() {
            addCriterion("contact_tel is not null");
            return (Criteria) this;
        }

        public Criteria andContactTelEqualTo(String value) {
            addCriterion("contact_tel =", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotEqualTo(String value) {
            addCriterion("contact_tel <>", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelGreaterThan(String value) {
            addCriterion("contact_tel >", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelGreaterThanOrEqualTo(String value) {
            addCriterion("contact_tel >=", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelLessThan(String value) {
            addCriterion("contact_tel <", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelLessThanOrEqualTo(String value) {
            addCriterion("contact_tel <=", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelLike(String value) {
            addCriterion("contact_tel like", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotLike(String value) {
            addCriterion("contact_tel not like", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelIn(List<String> values) {
            addCriterion("contact_tel in", values, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotIn(List<String> values) {
            addCriterion("contact_tel not in", values, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelBetween(String value1, String value2) {
            addCriterion("contact_tel between", value1, value2, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotBetween(String value1, String value2) {
            addCriterion("contact_tel not between", value1, value2, "contactTel");
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

        public Criteria andAuthorizationLetterIdIsNull() {
            addCriterion("authorization_letter_id is null");
            return (Criteria) this;
        }

        public Criteria andAuthorizationLetterIdIsNotNull() {
            addCriterion("authorization_letter_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorizationLetterIdEqualTo(String value) {
            addCriterion("authorization_letter_id =", value, "authorizationLetterId");
            return (Criteria) this;
        }

        public Criteria andAuthorizationLetterIdNotEqualTo(String value) {
            addCriterion("authorization_letter_id <>", value, "authorizationLetterId");
            return (Criteria) this;
        }

        public Criteria andAuthorizationLetterIdGreaterThan(String value) {
            addCriterion("authorization_letter_id >", value, "authorizationLetterId");
            return (Criteria) this;
        }

        public Criteria andAuthorizationLetterIdGreaterThanOrEqualTo(String value) {
            addCriterion("authorization_letter_id >=", value, "authorizationLetterId");
            return (Criteria) this;
        }

        public Criteria andAuthorizationLetterIdLessThan(String value) {
            addCriterion("authorization_letter_id <", value, "authorizationLetterId");
            return (Criteria) this;
        }

        public Criteria andAuthorizationLetterIdLessThanOrEqualTo(String value) {
            addCriterion("authorization_letter_id <=", value, "authorizationLetterId");
            return (Criteria) this;
        }

        public Criteria andAuthorizationLetterIdLike(String value) {
            addCriterion("authorization_letter_id like", value, "authorizationLetterId");
            return (Criteria) this;
        }

        public Criteria andAuthorizationLetterIdNotLike(String value) {
            addCriterion("authorization_letter_id not like", value, "authorizationLetterId");
            return (Criteria) this;
        }

        public Criteria andAuthorizationLetterIdIn(List<String> values) {
            addCriterion("authorization_letter_id in", values, "authorizationLetterId");
            return (Criteria) this;
        }

        public Criteria andAuthorizationLetterIdNotIn(List<String> values) {
            addCriterion("authorization_letter_id not in", values, "authorizationLetterId");
            return (Criteria) this;
        }

        public Criteria andAuthorizationLetterIdBetween(String value1, String value2) {
            addCriterion("authorization_letter_id between", value1, value2, "authorizationLetterId");
            return (Criteria) this;
        }

        public Criteria andAuthorizationLetterIdNotBetween(String value1, String value2) {
            addCriterion("authorization_letter_id not between", value1, value2, "authorizationLetterId");
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