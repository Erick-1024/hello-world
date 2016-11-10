package com.travelzen.framework.retry.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RetryTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public RetryTaskExample() {
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

        public Criteria andTaskTypeIsNull() {
            addCriterion("task_type is null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNotNull() {
            addCriterion("task_type is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeEqualTo(String value) {
            addCriterion("task_type =", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotEqualTo(String value) {
            addCriterion("task_type <>", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThan(String value) {
            addCriterion("task_type >", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThanOrEqualTo(String value) {
            addCriterion("task_type >=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThan(String value) {
            addCriterion("task_type <", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThanOrEqualTo(String value) {
            addCriterion("task_type <=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLike(String value) {
            addCriterion("task_type like", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotLike(String value) {
            addCriterion("task_type not like", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIn(List<String> values) {
            addCriterion("task_type in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotIn(List<String> values) {
            addCriterion("task_type not in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeBetween(String value1, String value2) {
            addCriterion("task_type between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotBetween(String value1, String value2) {
            addCriterion("task_type not between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(String value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(String value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(String value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(String value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(String value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(String value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLike(String value) {
            addCriterion("task_id like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotLike(String value) {
            addCriterion("task_id not like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<String> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<String> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(String value1, String value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(String value1, String value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andRetryPolicyIsNull() {
            addCriterion("retry_policy is null");
            return (Criteria) this;
        }

        public Criteria andRetryPolicyIsNotNull() {
            addCriterion("retry_policy is not null");
            return (Criteria) this;
        }

        public Criteria andRetryPolicyEqualTo(String value) {
            addCriterion("retry_policy =", value, "retryPolicy");
            return (Criteria) this;
        }

        public Criteria andRetryPolicyNotEqualTo(String value) {
            addCriterion("retry_policy <>", value, "retryPolicy");
            return (Criteria) this;
        }

        public Criteria andRetryPolicyGreaterThan(String value) {
            addCriterion("retry_policy >", value, "retryPolicy");
            return (Criteria) this;
        }

        public Criteria andRetryPolicyGreaterThanOrEqualTo(String value) {
            addCriterion("retry_policy >=", value, "retryPolicy");
            return (Criteria) this;
        }

        public Criteria andRetryPolicyLessThan(String value) {
            addCriterion("retry_policy <", value, "retryPolicy");
            return (Criteria) this;
        }

        public Criteria andRetryPolicyLessThanOrEqualTo(String value) {
            addCriterion("retry_policy <=", value, "retryPolicy");
            return (Criteria) this;
        }

        public Criteria andRetryPolicyLike(String value) {
            addCriterion("retry_policy like", value, "retryPolicy");
            return (Criteria) this;
        }

        public Criteria andRetryPolicyNotLike(String value) {
            addCriterion("retry_policy not like", value, "retryPolicy");
            return (Criteria) this;
        }

        public Criteria andRetryPolicyIn(List<String> values) {
            addCriterion("retry_policy in", values, "retryPolicy");
            return (Criteria) this;
        }

        public Criteria andRetryPolicyNotIn(List<String> values) {
            addCriterion("retry_policy not in", values, "retryPolicy");
            return (Criteria) this;
        }

        public Criteria andRetryPolicyBetween(String value1, String value2) {
            addCriterion("retry_policy between", value1, value2, "retryPolicy");
            return (Criteria) this;
        }

        public Criteria andRetryPolicyNotBetween(String value1, String value2) {
            addCriterion("retry_policy not between", value1, value2, "retryPolicy");
            return (Criteria) this;
        }

        public Criteria andBackoffPolicyIsNull() {
            addCriterion("backoff_policy is null");
            return (Criteria) this;
        }

        public Criteria andBackoffPolicyIsNotNull() {
            addCriterion("backoff_policy is not null");
            return (Criteria) this;
        }

        public Criteria andBackoffPolicyEqualTo(String value) {
            addCriterion("backoff_policy =", value, "backoffPolicy");
            return (Criteria) this;
        }

        public Criteria andBackoffPolicyNotEqualTo(String value) {
            addCriterion("backoff_policy <>", value, "backoffPolicy");
            return (Criteria) this;
        }

        public Criteria andBackoffPolicyGreaterThan(String value) {
            addCriterion("backoff_policy >", value, "backoffPolicy");
            return (Criteria) this;
        }

        public Criteria andBackoffPolicyGreaterThanOrEqualTo(String value) {
            addCriterion("backoff_policy >=", value, "backoffPolicy");
            return (Criteria) this;
        }

        public Criteria andBackoffPolicyLessThan(String value) {
            addCriterion("backoff_policy <", value, "backoffPolicy");
            return (Criteria) this;
        }

        public Criteria andBackoffPolicyLessThanOrEqualTo(String value) {
            addCriterion("backoff_policy <=", value, "backoffPolicy");
            return (Criteria) this;
        }

        public Criteria andBackoffPolicyLike(String value) {
            addCriterion("backoff_policy like", value, "backoffPolicy");
            return (Criteria) this;
        }

        public Criteria andBackoffPolicyNotLike(String value) {
            addCriterion("backoff_policy not like", value, "backoffPolicy");
            return (Criteria) this;
        }

        public Criteria andBackoffPolicyIn(List<String> values) {
            addCriterion("backoff_policy in", values, "backoffPolicy");
            return (Criteria) this;
        }

        public Criteria andBackoffPolicyNotIn(List<String> values) {
            addCriterion("backoff_policy not in", values, "backoffPolicy");
            return (Criteria) this;
        }

        public Criteria andBackoffPolicyBetween(String value1, String value2) {
            addCriterion("backoff_policy between", value1, value2, "backoffPolicy");
            return (Criteria) this;
        }

        public Criteria andBackoffPolicyNotBetween(String value1, String value2) {
            addCriterion("backoff_policy not between", value1, value2, "backoffPolicy");
            return (Criteria) this;
        }

        public Criteria andMaxAttemptsIsNull() {
            addCriterion("max_attempts is null");
            return (Criteria) this;
        }

        public Criteria andMaxAttemptsIsNotNull() {
            addCriterion("max_attempts is not null");
            return (Criteria) this;
        }

        public Criteria andMaxAttemptsEqualTo(Long value) {
            addCriterion("max_attempts =", value, "maxAttempts");
            return (Criteria) this;
        }

        public Criteria andMaxAttemptsNotEqualTo(Long value) {
            addCriterion("max_attempts <>", value, "maxAttempts");
            return (Criteria) this;
        }

        public Criteria andMaxAttemptsGreaterThan(Long value) {
            addCriterion("max_attempts >", value, "maxAttempts");
            return (Criteria) this;
        }

        public Criteria andMaxAttemptsGreaterThanOrEqualTo(Long value) {
            addCriterion("max_attempts >=", value, "maxAttempts");
            return (Criteria) this;
        }

        public Criteria andMaxAttemptsLessThan(Long value) {
            addCriterion("max_attempts <", value, "maxAttempts");
            return (Criteria) this;
        }

        public Criteria andMaxAttemptsLessThanOrEqualTo(Long value) {
            addCriterion("max_attempts <=", value, "maxAttempts");
            return (Criteria) this;
        }

        public Criteria andMaxAttemptsIn(List<Long> values) {
            addCriterion("max_attempts in", values, "maxAttempts");
            return (Criteria) this;
        }

        public Criteria andMaxAttemptsNotIn(List<Long> values) {
            addCriterion("max_attempts not in", values, "maxAttempts");
            return (Criteria) this;
        }

        public Criteria andMaxAttemptsBetween(Long value1, Long value2) {
            addCriterion("max_attempts between", value1, value2, "maxAttempts");
            return (Criteria) this;
        }

        public Criteria andMaxAttemptsNotBetween(Long value1, Long value2) {
            addCriterion("max_attempts not between", value1, value2, "maxAttempts");
            return (Criteria) this;
        }

        public Criteria andCurrentAttemptsIsNull() {
            addCriterion("current_attempts is null");
            return (Criteria) this;
        }

        public Criteria andCurrentAttemptsIsNotNull() {
            addCriterion("current_attempts is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentAttemptsEqualTo(Long value) {
            addCriterion("current_attempts =", value, "currentAttempts");
            return (Criteria) this;
        }

        public Criteria andCurrentAttemptsNotEqualTo(Long value) {
            addCriterion("current_attempts <>", value, "currentAttempts");
            return (Criteria) this;
        }

        public Criteria andCurrentAttemptsGreaterThan(Long value) {
            addCriterion("current_attempts >", value, "currentAttempts");
            return (Criteria) this;
        }

        public Criteria andCurrentAttemptsGreaterThanOrEqualTo(Long value) {
            addCriterion("current_attempts >=", value, "currentAttempts");
            return (Criteria) this;
        }

        public Criteria andCurrentAttemptsLessThan(Long value) {
            addCriterion("current_attempts <", value, "currentAttempts");
            return (Criteria) this;
        }

        public Criteria andCurrentAttemptsLessThanOrEqualTo(Long value) {
            addCriterion("current_attempts <=", value, "currentAttempts");
            return (Criteria) this;
        }

        public Criteria andCurrentAttemptsIn(List<Long> values) {
            addCriterion("current_attempts in", values, "currentAttempts");
            return (Criteria) this;
        }

        public Criteria andCurrentAttemptsNotIn(List<Long> values) {
            addCriterion("current_attempts not in", values, "currentAttempts");
            return (Criteria) this;
        }

        public Criteria andCurrentAttemptsBetween(Long value1, Long value2) {
            addCriterion("current_attempts between", value1, value2, "currentAttempts");
            return (Criteria) this;
        }

        public Criteria andCurrentAttemptsNotBetween(Long value1, Long value2) {
            addCriterion("current_attempts not between", value1, value2, "currentAttempts");
            return (Criteria) this;
        }

        public Criteria andTaskDeadlineIsNull() {
            addCriterion("task_deadline is null");
            return (Criteria) this;
        }

        public Criteria andTaskDeadlineIsNotNull() {
            addCriterion("task_deadline is not null");
            return (Criteria) this;
        }

        public Criteria andTaskDeadlineEqualTo(Date value) {
            addCriterion("task_deadline =", value, "taskDeadline");
            return (Criteria) this;
        }

        public Criteria andTaskDeadlineNotEqualTo(Date value) {
            addCriterion("task_deadline <>", value, "taskDeadline");
            return (Criteria) this;
        }

        public Criteria andTaskDeadlineGreaterThan(Date value) {
            addCriterion("task_deadline >", value, "taskDeadline");
            return (Criteria) this;
        }

        public Criteria andTaskDeadlineGreaterThanOrEqualTo(Date value) {
            addCriterion("task_deadline >=", value, "taskDeadline");
            return (Criteria) this;
        }

        public Criteria andTaskDeadlineLessThan(Date value) {
            addCriterion("task_deadline <", value, "taskDeadline");
            return (Criteria) this;
        }

        public Criteria andTaskDeadlineLessThanOrEqualTo(Date value) {
            addCriterion("task_deadline <=", value, "taskDeadline");
            return (Criteria) this;
        }

        public Criteria andTaskDeadlineIn(List<Date> values) {
            addCriterion("task_deadline in", values, "taskDeadline");
            return (Criteria) this;
        }

        public Criteria andTaskDeadlineNotIn(List<Date> values) {
            addCriterion("task_deadline not in", values, "taskDeadline");
            return (Criteria) this;
        }

        public Criteria andTaskDeadlineBetween(Date value1, Date value2) {
            addCriterion("task_deadline between", value1, value2, "taskDeadline");
            return (Criteria) this;
        }

        public Criteria andTaskDeadlineNotBetween(Date value1, Date value2) {
            addCriterion("task_deadline not between", value1, value2, "taskDeadline");
            return (Criteria) this;
        }

        public Criteria andFixedBackoffPeriodIsNull() {
            addCriterion("fixed_backoff_period is null");
            return (Criteria) this;
        }

        public Criteria andFixedBackoffPeriodIsNotNull() {
            addCriterion("fixed_backoff_period is not null");
            return (Criteria) this;
        }

        public Criteria andFixedBackoffPeriodEqualTo(Long value) {
            addCriterion("fixed_backoff_period =", value, "fixedBackoffPeriod");
            return (Criteria) this;
        }

        public Criteria andFixedBackoffPeriodNotEqualTo(Long value) {
            addCriterion("fixed_backoff_period <>", value, "fixedBackoffPeriod");
            return (Criteria) this;
        }

        public Criteria andFixedBackoffPeriodGreaterThan(Long value) {
            addCriterion("fixed_backoff_period >", value, "fixedBackoffPeriod");
            return (Criteria) this;
        }

        public Criteria andFixedBackoffPeriodGreaterThanOrEqualTo(Long value) {
            addCriterion("fixed_backoff_period >=", value, "fixedBackoffPeriod");
            return (Criteria) this;
        }

        public Criteria andFixedBackoffPeriodLessThan(Long value) {
            addCriterion("fixed_backoff_period <", value, "fixedBackoffPeriod");
            return (Criteria) this;
        }

        public Criteria andFixedBackoffPeriodLessThanOrEqualTo(Long value) {
            addCriterion("fixed_backoff_period <=", value, "fixedBackoffPeriod");
            return (Criteria) this;
        }

        public Criteria andFixedBackoffPeriodIn(List<Long> values) {
            addCriterion("fixed_backoff_period in", values, "fixedBackoffPeriod");
            return (Criteria) this;
        }

        public Criteria andFixedBackoffPeriodNotIn(List<Long> values) {
            addCriterion("fixed_backoff_period not in", values, "fixedBackoffPeriod");
            return (Criteria) this;
        }

        public Criteria andFixedBackoffPeriodBetween(Long value1, Long value2) {
            addCriterion("fixed_backoff_period between", value1, value2, "fixedBackoffPeriod");
            return (Criteria) this;
        }

        public Criteria andFixedBackoffPeriodNotBetween(Long value1, Long value2) {
            addCriterion("fixed_backoff_period not between", value1, value2, "fixedBackoffPeriod");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andMessageIsNull() {
            addCriterion("message is null");
            return (Criteria) this;
        }

        public Criteria andMessageIsNotNull() {
            addCriterion("message is not null");
            return (Criteria) this;
        }

        public Criteria andMessageEqualTo(String value) {
            addCriterion("message =", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotEqualTo(String value) {
            addCriterion("message <>", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThan(String value) {
            addCriterion("message >", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThanOrEqualTo(String value) {
            addCriterion("message >=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThan(String value) {
            addCriterion("message <", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThanOrEqualTo(String value) {
            addCriterion("message <=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLike(String value) {
            addCriterion("message like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotLike(String value) {
            addCriterion("message not like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageIn(List<String> values) {
            addCriterion("message in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotIn(List<String> values) {
            addCriterion("message not in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageBetween(String value1, String value2) {
            addCriterion("message between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotBetween(String value1, String value2) {
            addCriterion("message not between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andDataIsNull() {
            addCriterion("data is null");
            return (Criteria) this;
        }

        public Criteria andDataIsNotNull() {
            addCriterion("data is not null");
            return (Criteria) this;
        }

        public Criteria andDataEqualTo(String value) {
            addCriterion("data =", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotEqualTo(String value) {
            addCriterion("data <>", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataGreaterThan(String value) {
            addCriterion("data >", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataGreaterThanOrEqualTo(String value) {
            addCriterion("data >=", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLessThan(String value) {
            addCriterion("data <", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLessThanOrEqualTo(String value) {
            addCriterion("data <=", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLike(String value) {
            addCriterion("data like", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotLike(String value) {
            addCriterion("data not like", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataIn(List<String> values) {
            addCriterion("data in", values, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotIn(List<String> values) {
            addCriterion("data not in", values, "data");
            return (Criteria) this;
        }

        public Criteria andDataBetween(String value1, String value2) {
            addCriterion("data between", value1, value2, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotBetween(String value1, String value2) {
            addCriterion("data not between", value1, value2, "data");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andBusinessTransactionVersionIsNull() {
            addCriterion("business_transaction_version is null");
            return (Criteria) this;
        }

        public Criteria andBusinessTransactionVersionIsNotNull() {
            addCriterion("business_transaction_version is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessTransactionVersionEqualTo(String value) {
            addCriterion("business_transaction_version =", value, "businessTransactionVersion");
            return (Criteria) this;
        }

        public Criteria andBusinessTransactionVersionNotEqualTo(String value) {
            addCriterion("business_transaction_version <>", value, "businessTransactionVersion");
            return (Criteria) this;
        }

        public Criteria andBusinessTransactionVersionGreaterThan(String value) {
            addCriterion("business_transaction_version >", value, "businessTransactionVersion");
            return (Criteria) this;
        }

        public Criteria andBusinessTransactionVersionGreaterThanOrEqualTo(String value) {
            addCriterion("business_transaction_version >=", value, "businessTransactionVersion");
            return (Criteria) this;
        }

        public Criteria andBusinessTransactionVersionLessThan(String value) {
            addCriterion("business_transaction_version <", value, "businessTransactionVersion");
            return (Criteria) this;
        }

        public Criteria andBusinessTransactionVersionLessThanOrEqualTo(String value) {
            addCriterion("business_transaction_version <=", value, "businessTransactionVersion");
            return (Criteria) this;
        }

        public Criteria andBusinessTransactionVersionLike(String value) {
            addCriterion("business_transaction_version like", value, "businessTransactionVersion");
            return (Criteria) this;
        }

        public Criteria andBusinessTransactionVersionNotLike(String value) {
            addCriterion("business_transaction_version not like", value, "businessTransactionVersion");
            return (Criteria) this;
        }

        public Criteria andBusinessTransactionVersionIn(List<String> values) {
            addCriterion("business_transaction_version in", values, "businessTransactionVersion");
            return (Criteria) this;
        }

        public Criteria andBusinessTransactionVersionNotIn(List<String> values) {
            addCriterion("business_transaction_version not in", values, "businessTransactionVersion");
            return (Criteria) this;
        }

        public Criteria andBusinessTransactionVersionBetween(String value1, String value2) {
            addCriterion("business_transaction_version between", value1, value2, "businessTransactionVersion");
            return (Criteria) this;
        }

        public Criteria andBusinessTransactionVersionNotBetween(String value1, String value2) {
            addCriterion("business_transaction_version not between", value1, value2, "businessTransactionVersion");
            return (Criteria) this;
        }

        public Criteria andProcessTimeoutIsNull() {
            addCriterion("process_timeout is null");
            return (Criteria) this;
        }

        public Criteria andProcessTimeoutIsNotNull() {
            addCriterion("process_timeout is not null");
            return (Criteria) this;
        }

        public Criteria andProcessTimeoutEqualTo(Long value) {
            addCriterion("process_timeout =", value, "processTimeout");
            return (Criteria) this;
        }

        public Criteria andProcessTimeoutNotEqualTo(Long value) {
            addCriterion("process_timeout <>", value, "processTimeout");
            return (Criteria) this;
        }

        public Criteria andProcessTimeoutGreaterThan(Long value) {
            addCriterion("process_timeout >", value, "processTimeout");
            return (Criteria) this;
        }

        public Criteria andProcessTimeoutGreaterThanOrEqualTo(Long value) {
            addCriterion("process_timeout >=", value, "processTimeout");
            return (Criteria) this;
        }

        public Criteria andProcessTimeoutLessThan(Long value) {
            addCriterion("process_timeout <", value, "processTimeout");
            return (Criteria) this;
        }

        public Criteria andProcessTimeoutLessThanOrEqualTo(Long value) {
            addCriterion("process_timeout <=", value, "processTimeout");
            return (Criteria) this;
        }

        public Criteria andProcessTimeoutIn(List<Long> values) {
            addCriterion("process_timeout in", values, "processTimeout");
            return (Criteria) this;
        }

        public Criteria andProcessTimeoutNotIn(List<Long> values) {
            addCriterion("process_timeout not in", values, "processTimeout");
            return (Criteria) this;
        }

        public Criteria andProcessTimeoutBetween(Long value1, Long value2) {
            addCriterion("process_timeout between", value1, value2, "processTimeout");
            return (Criteria) this;
        }

        public Criteria andProcessTimeoutNotBetween(Long value1, Long value2) {
            addCriterion("process_timeout not between", value1, value2, "processTimeout");
            return (Criteria) this;
        }

        public Criteria andFinishedIsNull() {
            addCriterion("finished is null");
            return (Criteria) this;
        }

        public Criteria andFinishedIsNotNull() {
            addCriterion("finished is not null");
            return (Criteria) this;
        }

        public Criteria andFinishedEqualTo(Boolean value) {
            addCriterion("finished =", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedNotEqualTo(Boolean value) {
            addCriterion("finished <>", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThan(Boolean value) {
            addCriterion("finished >", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("finished >=", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedLessThan(Boolean value) {
            addCriterion("finished <", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanOrEqualTo(Boolean value) {
            addCriterion("finished <=", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedIn(List<Boolean> values) {
            addCriterion("finished in", values, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedNotIn(List<Boolean> values) {
            addCriterion("finished not in", values, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedBetween(Boolean value1, Boolean value2) {
            addCriterion("finished between", value1, value2, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("finished not between", value1, value2, "finished");
            return (Criteria) this;
        }

        public Criteria andUseIsolatedThreadPoolIsNull() {
            addCriterion("use_isolated_thread_pool is null");
            return (Criteria) this;
        }

        public Criteria andUseIsolatedThreadPoolIsNotNull() {
            addCriterion("use_isolated_thread_pool is not null");
            return (Criteria) this;
        }

        public Criteria andUseIsolatedThreadPoolEqualTo(Boolean value) {
            addCriterion("use_isolated_thread_pool =", value, "useIsolatedThreadPool");
            return (Criteria) this;
        }

        public Criteria andUseIsolatedThreadPoolNotEqualTo(Boolean value) {
            addCriterion("use_isolated_thread_pool <>", value, "useIsolatedThreadPool");
            return (Criteria) this;
        }

        public Criteria andUseIsolatedThreadPoolGreaterThan(Boolean value) {
            addCriterion("use_isolated_thread_pool >", value, "useIsolatedThreadPool");
            return (Criteria) this;
        }

        public Criteria andUseIsolatedThreadPoolGreaterThanOrEqualTo(Boolean value) {
            addCriterion("use_isolated_thread_pool >=", value, "useIsolatedThreadPool");
            return (Criteria) this;
        }

        public Criteria andUseIsolatedThreadPoolLessThan(Boolean value) {
            addCriterion("use_isolated_thread_pool <", value, "useIsolatedThreadPool");
            return (Criteria) this;
        }

        public Criteria andUseIsolatedThreadPoolLessThanOrEqualTo(Boolean value) {
            addCriterion("use_isolated_thread_pool <=", value, "useIsolatedThreadPool");
            return (Criteria) this;
        }

        public Criteria andUseIsolatedThreadPoolIn(List<Boolean> values) {
            addCriterion("use_isolated_thread_pool in", values, "useIsolatedThreadPool");
            return (Criteria) this;
        }

        public Criteria andUseIsolatedThreadPoolNotIn(List<Boolean> values) {
            addCriterion("use_isolated_thread_pool not in", values, "useIsolatedThreadPool");
            return (Criteria) this;
        }

        public Criteria andUseIsolatedThreadPoolBetween(Boolean value1, Boolean value2) {
            addCriterion("use_isolated_thread_pool between", value1, value2, "useIsolatedThreadPool");
            return (Criteria) this;
        }

        public Criteria andUseIsolatedThreadPoolNotBetween(Boolean value1, Boolean value2) {
            addCriterion("use_isolated_thread_pool not between", value1, value2, "useIsolatedThreadPool");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeIsNull() {
            addCriterion("thread_pool_size is null");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeIsNotNull() {
            addCriterion("thread_pool_size is not null");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeEqualTo(Long value) {
            addCriterion("thread_pool_size =", value, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeNotEqualTo(Long value) {
            addCriterion("thread_pool_size <>", value, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeGreaterThan(Long value) {
            addCriterion("thread_pool_size >", value, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("thread_pool_size >=", value, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeLessThan(Long value) {
            addCriterion("thread_pool_size <", value, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeLessThanOrEqualTo(Long value) {
            addCriterion("thread_pool_size <=", value, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeIn(List<Long> values) {
            addCriterion("thread_pool_size in", values, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeNotIn(List<Long> values) {
            addCriterion("thread_pool_size not in", values, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeBetween(Long value1, Long value2) {
            addCriterion("thread_pool_size between", value1, value2, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeNotBetween(Long value1, Long value2) {
            addCriterion("thread_pool_size not between", value1, value2, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
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