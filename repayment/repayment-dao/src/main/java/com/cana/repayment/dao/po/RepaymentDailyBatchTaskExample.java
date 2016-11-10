package com.cana.repayment.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepaymentDailyBatchTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public RepaymentDailyBatchTaskExample() {
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

        public Criteria andLoanInfoIdIsNull() {
            addCriterion("loan_info_id is null");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdIsNotNull() {
            addCriterion("loan_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdEqualTo(String value) {
            addCriterion("loan_info_id =", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdNotEqualTo(String value) {
            addCriterion("loan_info_id <>", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdGreaterThan(String value) {
            addCriterion("loan_info_id >", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdGreaterThanOrEqualTo(String value) {
            addCriterion("loan_info_id >=", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdLessThan(String value) {
            addCriterion("loan_info_id <", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdLessThanOrEqualTo(String value) {
            addCriterion("loan_info_id <=", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdLike(String value) {
            addCriterion("loan_info_id like", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdNotLike(String value) {
            addCriterion("loan_info_id not like", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdIn(List<String> values) {
            addCriterion("loan_info_id in", values, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdNotIn(List<String> values) {
            addCriterion("loan_info_id not in", values, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdBetween(String value1, String value2) {
            addCriterion("loan_info_id between", value1, value2, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdNotBetween(String value1, String value2) {
            addCriterion("loan_info_id not between", value1, value2, "loanInfoId");
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

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(String value) {
            addCriterion("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(String value) {
            addCriterion("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(String value) {
            addCriterion("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(String value) {
            addCriterion("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(String value) {
            addCriterion("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(String value) {
            addCriterion("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLike(String value) {
            addCriterion("date like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotLike(String value) {
            addCriterion("date not like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<String> values) {
            addCriterion("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<String> values) {
            addCriterion("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(String value1, String value2) {
            addCriterion("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(String value1, String value2) {
            addCriterion("date not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andTaskNumIsNull() {
            addCriterion("task_num is null");
            return (Criteria) this;
        }

        public Criteria andTaskNumIsNotNull() {
            addCriterion("task_num is not null");
            return (Criteria) this;
        }

        public Criteria andTaskNumEqualTo(Integer value) {
            addCriterion("task_num =", value, "taskNum");
            return (Criteria) this;
        }

        public Criteria andTaskNumNotEqualTo(Integer value) {
            addCriterion("task_num <>", value, "taskNum");
            return (Criteria) this;
        }

        public Criteria andTaskNumGreaterThan(Integer value) {
            addCriterion("task_num >", value, "taskNum");
            return (Criteria) this;
        }

        public Criteria andTaskNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_num >=", value, "taskNum");
            return (Criteria) this;
        }

        public Criteria andTaskNumLessThan(Integer value) {
            addCriterion("task_num <", value, "taskNum");
            return (Criteria) this;
        }

        public Criteria andTaskNumLessThanOrEqualTo(Integer value) {
            addCriterion("task_num <=", value, "taskNum");
            return (Criteria) this;
        }

        public Criteria andTaskNumIn(List<Integer> values) {
            addCriterion("task_num in", values, "taskNum");
            return (Criteria) this;
        }

        public Criteria andTaskNumNotIn(List<Integer> values) {
            addCriterion("task_num not in", values, "taskNum");
            return (Criteria) this;
        }

        public Criteria andTaskNumBetween(Integer value1, Integer value2) {
            addCriterion("task_num between", value1, value2, "taskNum");
            return (Criteria) this;
        }

        public Criteria andTaskNumNotBetween(Integer value1, Integer value2) {
            addCriterion("task_num not between", value1, value2, "taskNum");
            return (Criteria) this;
        }

        public Criteria andSequenceIsNull() {
            addCriterion("sequence is null");
            return (Criteria) this;
        }

        public Criteria andSequenceIsNotNull() {
            addCriterion("sequence is not null");
            return (Criteria) this;
        }

        public Criteria andSequenceEqualTo(Integer value) {
            addCriterion("sequence =", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceNotEqualTo(Integer value) {
            addCriterion("sequence <>", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceGreaterThan(Integer value) {
            addCriterion("sequence >", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceGreaterThanOrEqualTo(Integer value) {
            addCriterion("sequence >=", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceLessThan(Integer value) {
            addCriterion("sequence <", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceLessThanOrEqualTo(Integer value) {
            addCriterion("sequence <=", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceIn(List<Integer> values) {
            addCriterion("sequence in", values, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceNotIn(List<Integer> values) {
            addCriterion("sequence not in", values, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceBetween(Integer value1, Integer value2) {
            addCriterion("sequence between", value1, value2, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceNotBetween(Integer value1, Integer value2) {
            addCriterion("sequence not between", value1, value2, "sequence");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemIdIsNull() {
            addCriterion("next_task_item_id is null");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemIdIsNotNull() {
            addCriterion("next_task_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemIdEqualTo(String value) {
            addCriterion("next_task_item_id =", value, "nextTaskItemId");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemIdNotEqualTo(String value) {
            addCriterion("next_task_item_id <>", value, "nextTaskItemId");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemIdGreaterThan(String value) {
            addCriterion("next_task_item_id >", value, "nextTaskItemId");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("next_task_item_id >=", value, "nextTaskItemId");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemIdLessThan(String value) {
            addCriterion("next_task_item_id <", value, "nextTaskItemId");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemIdLessThanOrEqualTo(String value) {
            addCriterion("next_task_item_id <=", value, "nextTaskItemId");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemIdLike(String value) {
            addCriterion("next_task_item_id like", value, "nextTaskItemId");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemIdNotLike(String value) {
            addCriterion("next_task_item_id not like", value, "nextTaskItemId");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemIdIn(List<String> values) {
            addCriterion("next_task_item_id in", values, "nextTaskItemId");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemIdNotIn(List<String> values) {
            addCriterion("next_task_item_id not in", values, "nextTaskItemId");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemIdBetween(String value1, String value2) {
            addCriterion("next_task_item_id between", value1, value2, "nextTaskItemId");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemIdNotBetween(String value1, String value2) {
            addCriterion("next_task_item_id not between", value1, value2, "nextTaskItemId");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemExecuteTimeIsNull() {
            addCriterion("next_task_item_execute_time is null");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemExecuteTimeIsNotNull() {
            addCriterion("next_task_item_execute_time is not null");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemExecuteTimeEqualTo(String value) {
            addCriterion("next_task_item_execute_time =", value, "nextTaskItemExecuteTime");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemExecuteTimeNotEqualTo(String value) {
            addCriterion("next_task_item_execute_time <>", value, "nextTaskItemExecuteTime");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemExecuteTimeGreaterThan(String value) {
            addCriterion("next_task_item_execute_time >", value, "nextTaskItemExecuteTime");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemExecuteTimeGreaterThanOrEqualTo(String value) {
            addCriterion("next_task_item_execute_time >=", value, "nextTaskItemExecuteTime");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemExecuteTimeLessThan(String value) {
            addCriterion("next_task_item_execute_time <", value, "nextTaskItemExecuteTime");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemExecuteTimeLessThanOrEqualTo(String value) {
            addCriterion("next_task_item_execute_time <=", value, "nextTaskItemExecuteTime");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemExecuteTimeLike(String value) {
            addCriterion("next_task_item_execute_time like", value, "nextTaskItemExecuteTime");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemExecuteTimeNotLike(String value) {
            addCriterion("next_task_item_execute_time not like", value, "nextTaskItemExecuteTime");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemExecuteTimeIn(List<String> values) {
            addCriterion("next_task_item_execute_time in", values, "nextTaskItemExecuteTime");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemExecuteTimeNotIn(List<String> values) {
            addCriterion("next_task_item_execute_time not in", values, "nextTaskItemExecuteTime");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemExecuteTimeBetween(String value1, String value2) {
            addCriterion("next_task_item_execute_time between", value1, value2, "nextTaskItemExecuteTime");
            return (Criteria) this;
        }

        public Criteria andNextTaskItemExecuteTimeNotBetween(String value1, String value2) {
            addCriterion("next_task_item_execute_time not between", value1, value2, "nextTaskItemExecuteTime");
            return (Criteria) this;
        }

        public Criteria andFailTaskItemIdIsNull() {
            addCriterion("fail_task_item_id is null");
            return (Criteria) this;
        }

        public Criteria andFailTaskItemIdIsNotNull() {
            addCriterion("fail_task_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andFailTaskItemIdEqualTo(String value) {
            addCriterion("fail_task_item_id =", value, "failTaskItemId");
            return (Criteria) this;
        }

        public Criteria andFailTaskItemIdNotEqualTo(String value) {
            addCriterion("fail_task_item_id <>", value, "failTaskItemId");
            return (Criteria) this;
        }

        public Criteria andFailTaskItemIdGreaterThan(String value) {
            addCriterion("fail_task_item_id >", value, "failTaskItemId");
            return (Criteria) this;
        }

        public Criteria andFailTaskItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("fail_task_item_id >=", value, "failTaskItemId");
            return (Criteria) this;
        }

        public Criteria andFailTaskItemIdLessThan(String value) {
            addCriterion("fail_task_item_id <", value, "failTaskItemId");
            return (Criteria) this;
        }

        public Criteria andFailTaskItemIdLessThanOrEqualTo(String value) {
            addCriterion("fail_task_item_id <=", value, "failTaskItemId");
            return (Criteria) this;
        }

        public Criteria andFailTaskItemIdLike(String value) {
            addCriterion("fail_task_item_id like", value, "failTaskItemId");
            return (Criteria) this;
        }

        public Criteria andFailTaskItemIdNotLike(String value) {
            addCriterion("fail_task_item_id not like", value, "failTaskItemId");
            return (Criteria) this;
        }

        public Criteria andFailTaskItemIdIn(List<String> values) {
            addCriterion("fail_task_item_id in", values, "failTaskItemId");
            return (Criteria) this;
        }

        public Criteria andFailTaskItemIdNotIn(List<String> values) {
            addCriterion("fail_task_item_id not in", values, "failTaskItemId");
            return (Criteria) this;
        }

        public Criteria andFailTaskItemIdBetween(String value1, String value2) {
            addCriterion("fail_task_item_id between", value1, value2, "failTaskItemId");
            return (Criteria) this;
        }

        public Criteria andFailTaskItemIdNotBetween(String value1, String value2) {
            addCriterion("fail_task_item_id not between", value1, value2, "failTaskItemId");
            return (Criteria) this;
        }

        public Criteria andFailMessageIsNull() {
            addCriterion("fail_message is null");
            return (Criteria) this;
        }

        public Criteria andFailMessageIsNotNull() {
            addCriterion("fail_message is not null");
            return (Criteria) this;
        }

        public Criteria andFailMessageEqualTo(String value) {
            addCriterion("fail_message =", value, "failMessage");
            return (Criteria) this;
        }

        public Criteria andFailMessageNotEqualTo(String value) {
            addCriterion("fail_message <>", value, "failMessage");
            return (Criteria) this;
        }

        public Criteria andFailMessageGreaterThan(String value) {
            addCriterion("fail_message >", value, "failMessage");
            return (Criteria) this;
        }

        public Criteria andFailMessageGreaterThanOrEqualTo(String value) {
            addCriterion("fail_message >=", value, "failMessage");
            return (Criteria) this;
        }

        public Criteria andFailMessageLessThan(String value) {
            addCriterion("fail_message <", value, "failMessage");
            return (Criteria) this;
        }

        public Criteria andFailMessageLessThanOrEqualTo(String value) {
            addCriterion("fail_message <=", value, "failMessage");
            return (Criteria) this;
        }

        public Criteria andFailMessageLike(String value) {
            addCriterion("fail_message like", value, "failMessage");
            return (Criteria) this;
        }

        public Criteria andFailMessageNotLike(String value) {
            addCriterion("fail_message not like", value, "failMessage");
            return (Criteria) this;
        }

        public Criteria andFailMessageIn(List<String> values) {
            addCriterion("fail_message in", values, "failMessage");
            return (Criteria) this;
        }

        public Criteria andFailMessageNotIn(List<String> values) {
            addCriterion("fail_message not in", values, "failMessage");
            return (Criteria) this;
        }

        public Criteria andFailMessageBetween(String value1, String value2) {
            addCriterion("fail_message between", value1, value2, "failMessage");
            return (Criteria) this;
        }

        public Criteria andFailMessageNotBetween(String value1, String value2) {
            addCriterion("fail_message not between", value1, value2, "failMessage");
            return (Criteria) this;
        }

        public Criteria andCanRetryIsNull() {
            addCriterion("can_retry is null");
            return (Criteria) this;
        }

        public Criteria andCanRetryIsNotNull() {
            addCriterion("can_retry is not null");
            return (Criteria) this;
        }

        public Criteria andCanRetryEqualTo(Boolean value) {
            addCriterion("can_retry =", value, "canRetry");
            return (Criteria) this;
        }

        public Criteria andCanRetryNotEqualTo(Boolean value) {
            addCriterion("can_retry <>", value, "canRetry");
            return (Criteria) this;
        }

        public Criteria andCanRetryGreaterThan(Boolean value) {
            addCriterion("can_retry >", value, "canRetry");
            return (Criteria) this;
        }

        public Criteria andCanRetryGreaterThanOrEqualTo(Boolean value) {
            addCriterion("can_retry >=", value, "canRetry");
            return (Criteria) this;
        }

        public Criteria andCanRetryLessThan(Boolean value) {
            addCriterion("can_retry <", value, "canRetry");
            return (Criteria) this;
        }

        public Criteria andCanRetryLessThanOrEqualTo(Boolean value) {
            addCriterion("can_retry <=", value, "canRetry");
            return (Criteria) this;
        }

        public Criteria andCanRetryIn(List<Boolean> values) {
            addCriterion("can_retry in", values, "canRetry");
            return (Criteria) this;
        }

        public Criteria andCanRetryNotIn(List<Boolean> values) {
            addCriterion("can_retry not in", values, "canRetry");
            return (Criteria) this;
        }

        public Criteria andCanRetryBetween(Boolean value1, Boolean value2) {
            addCriterion("can_retry between", value1, value2, "canRetry");
            return (Criteria) this;
        }

        public Criteria andCanRetryNotBetween(Boolean value1, Boolean value2) {
            addCriterion("can_retry not between", value1, value2, "canRetry");
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