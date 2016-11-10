package com.cana.flight.finance.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepaymentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public RepaymentExample() {
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

        public Criteria andRecordIdIsNull() {
            addCriterion("record_id is null");
            return (Criteria) this;
        }

        public Criteria andRecordIdIsNotNull() {
            addCriterion("record_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecordIdEqualTo(Integer value) {
            addCriterion("record_id =", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotEqualTo(Integer value) {
            addCriterion("record_id <>", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThan(Integer value) {
            addCriterion("record_id >", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("record_id >=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThan(Integer value) {
            addCriterion("record_id <", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThanOrEqualTo(Integer value) {
            addCriterion("record_id <=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdIn(List<Integer> values) {
            addCriterion("record_id in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotIn(List<Integer> values) {
            addCriterion("record_id not in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdBetween(Integer value1, Integer value2) {
            addCriterion("record_id between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotBetween(Integer value1, Integer value2) {
            addCriterion("record_id not between", value1, value2, "recordId");
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

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andPaymentStartDateIsNull() {
            addCriterion("payment_start_date is null");
            return (Criteria) this;
        }

        public Criteria andPaymentStartDateIsNotNull() {
            addCriterion("payment_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentStartDateEqualTo(String value) {
            addCriterion("payment_start_date =", value, "paymentStartDate");
            return (Criteria) this;
        }

        public Criteria andPaymentStartDateNotEqualTo(String value) {
            addCriterion("payment_start_date <>", value, "paymentStartDate");
            return (Criteria) this;
        }

        public Criteria andPaymentStartDateGreaterThan(String value) {
            addCriterion("payment_start_date >", value, "paymentStartDate");
            return (Criteria) this;
        }

        public Criteria andPaymentStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("payment_start_date >=", value, "paymentStartDate");
            return (Criteria) this;
        }

        public Criteria andPaymentStartDateLessThan(String value) {
            addCriterion("payment_start_date <", value, "paymentStartDate");
            return (Criteria) this;
        }

        public Criteria andPaymentStartDateLessThanOrEqualTo(String value) {
            addCriterion("payment_start_date <=", value, "paymentStartDate");
            return (Criteria) this;
        }

        public Criteria andPaymentStartDateLike(String value) {
            addCriterion("payment_start_date like", value, "paymentStartDate");
            return (Criteria) this;
        }

        public Criteria andPaymentStartDateNotLike(String value) {
            addCriterion("payment_start_date not like", value, "paymentStartDate");
            return (Criteria) this;
        }

        public Criteria andPaymentStartDateIn(List<String> values) {
            addCriterion("payment_start_date in", values, "paymentStartDate");
            return (Criteria) this;
        }

        public Criteria andPaymentStartDateNotIn(List<String> values) {
            addCriterion("payment_start_date not in", values, "paymentStartDate");
            return (Criteria) this;
        }

        public Criteria andPaymentStartDateBetween(String value1, String value2) {
            addCriterion("payment_start_date between", value1, value2, "paymentStartDate");
            return (Criteria) this;
        }

        public Criteria andPaymentStartDateNotBetween(String value1, String value2) {
            addCriterion("payment_start_date not between", value1, value2, "paymentStartDate");
            return (Criteria) this;
        }

        public Criteria andPaymentEndDateIsNull() {
            addCriterion("payment_end_date is null");
            return (Criteria) this;
        }

        public Criteria andPaymentEndDateIsNotNull() {
            addCriterion("payment_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentEndDateEqualTo(String value) {
            addCriterion("payment_end_date =", value, "paymentEndDate");
            return (Criteria) this;
        }

        public Criteria andPaymentEndDateNotEqualTo(String value) {
            addCriterion("payment_end_date <>", value, "paymentEndDate");
            return (Criteria) this;
        }

        public Criteria andPaymentEndDateGreaterThan(String value) {
            addCriterion("payment_end_date >", value, "paymentEndDate");
            return (Criteria) this;
        }

        public Criteria andPaymentEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("payment_end_date >=", value, "paymentEndDate");
            return (Criteria) this;
        }

        public Criteria andPaymentEndDateLessThan(String value) {
            addCriterion("payment_end_date <", value, "paymentEndDate");
            return (Criteria) this;
        }

        public Criteria andPaymentEndDateLessThanOrEqualTo(String value) {
            addCriterion("payment_end_date <=", value, "paymentEndDate");
            return (Criteria) this;
        }

        public Criteria andPaymentEndDateLike(String value) {
            addCriterion("payment_end_date like", value, "paymentEndDate");
            return (Criteria) this;
        }

        public Criteria andPaymentEndDateNotLike(String value) {
            addCriterion("payment_end_date not like", value, "paymentEndDate");
            return (Criteria) this;
        }

        public Criteria andPaymentEndDateIn(List<String> values) {
            addCriterion("payment_end_date in", values, "paymentEndDate");
            return (Criteria) this;
        }

        public Criteria andPaymentEndDateNotIn(List<String> values) {
            addCriterion("payment_end_date not in", values, "paymentEndDate");
            return (Criteria) this;
        }

        public Criteria andPaymentEndDateBetween(String value1, String value2) {
            addCriterion("payment_end_date between", value1, value2, "paymentEndDate");
            return (Criteria) this;
        }

        public Criteria andPaymentEndDateNotBetween(String value1, String value2) {
            addCriterion("payment_end_date not between", value1, value2, "paymentEndDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateIsNull() {
            addCriterion("repayment_date is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateIsNotNull() {
            addCriterion("repayment_date is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateEqualTo(String value) {
            addCriterion("repayment_date =", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateNotEqualTo(String value) {
            addCriterion("repayment_date <>", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateGreaterThan(String value) {
            addCriterion("repayment_date >", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateGreaterThanOrEqualTo(String value) {
            addCriterion("repayment_date >=", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateLessThan(String value) {
            addCriterion("repayment_date <", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateLessThanOrEqualTo(String value) {
            addCriterion("repayment_date <=", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateLike(String value) {
            addCriterion("repayment_date like", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateNotLike(String value) {
            addCriterion("repayment_date not like", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateIn(List<String> values) {
            addCriterion("repayment_date in", values, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateNotIn(List<String> values) {
            addCriterion("repayment_date not in", values, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateBetween(String value1, String value2) {
            addCriterion("repayment_date between", value1, value2, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateNotBetween(String value1, String value2) {
            addCriterion("repayment_date not between", value1, value2, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andLateRepaymentDateIsNull() {
            addCriterion("late_repayment_date is null");
            return (Criteria) this;
        }

        public Criteria andLateRepaymentDateIsNotNull() {
            addCriterion("late_repayment_date is not null");
            return (Criteria) this;
        }

        public Criteria andLateRepaymentDateEqualTo(String value) {
            addCriterion("late_repayment_date =", value, "lateRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andLateRepaymentDateNotEqualTo(String value) {
            addCriterion("late_repayment_date <>", value, "lateRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andLateRepaymentDateGreaterThan(String value) {
            addCriterion("late_repayment_date >", value, "lateRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andLateRepaymentDateGreaterThanOrEqualTo(String value) {
            addCriterion("late_repayment_date >=", value, "lateRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andLateRepaymentDateLessThan(String value) {
            addCriterion("late_repayment_date <", value, "lateRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andLateRepaymentDateLessThanOrEqualTo(String value) {
            addCriterion("late_repayment_date <=", value, "lateRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andLateRepaymentDateLike(String value) {
            addCriterion("late_repayment_date like", value, "lateRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andLateRepaymentDateNotLike(String value) {
            addCriterion("late_repayment_date not like", value, "lateRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andLateRepaymentDateIn(List<String> values) {
            addCriterion("late_repayment_date in", values, "lateRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andLateRepaymentDateNotIn(List<String> values) {
            addCriterion("late_repayment_date not in", values, "lateRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andLateRepaymentDateBetween(String value1, String value2) {
            addCriterion("late_repayment_date between", value1, value2, "lateRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andLateRepaymentDateNotBetween(String value1, String value2) {
            addCriterion("late_repayment_date not between", value1, value2, "lateRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andActualRepaymentDateIsNull() {
            addCriterion("actual_repayment_date is null");
            return (Criteria) this;
        }

        public Criteria andActualRepaymentDateIsNotNull() {
            addCriterion("actual_repayment_date is not null");
            return (Criteria) this;
        }

        public Criteria andActualRepaymentDateEqualTo(String value) {
            addCriterion("actual_repayment_date =", value, "actualRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andActualRepaymentDateNotEqualTo(String value) {
            addCriterion("actual_repayment_date <>", value, "actualRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andActualRepaymentDateGreaterThan(String value) {
            addCriterion("actual_repayment_date >", value, "actualRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andActualRepaymentDateGreaterThanOrEqualTo(String value) {
            addCriterion("actual_repayment_date >=", value, "actualRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andActualRepaymentDateLessThan(String value) {
            addCriterion("actual_repayment_date <", value, "actualRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andActualRepaymentDateLessThanOrEqualTo(String value) {
            addCriterion("actual_repayment_date <=", value, "actualRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andActualRepaymentDateLike(String value) {
            addCriterion("actual_repayment_date like", value, "actualRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andActualRepaymentDateNotLike(String value) {
            addCriterion("actual_repayment_date not like", value, "actualRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andActualRepaymentDateIn(List<String> values) {
            addCriterion("actual_repayment_date in", values, "actualRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andActualRepaymentDateNotIn(List<String> values) {
            addCriterion("actual_repayment_date not in", values, "actualRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andActualRepaymentDateBetween(String value1, String value2) {
            addCriterion("actual_repayment_date between", value1, value2, "actualRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andActualRepaymentDateNotBetween(String value1, String value2) {
            addCriterion("actual_repayment_date not between", value1, value2, "actualRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andAccountAmountIsNull() {
            addCriterion("account_amount is null");
            return (Criteria) this;
        }

        public Criteria andAccountAmountIsNotNull() {
            addCriterion("account_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAccountAmountEqualTo(Long value) {
            addCriterion("account_amount =", value, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNotEqualTo(Long value) {
            addCriterion("account_amount <>", value, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountGreaterThan(Long value) {
            addCriterion("account_amount >", value, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("account_amount >=", value, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountLessThan(Long value) {
            addCriterion("account_amount <", value, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountLessThanOrEqualTo(Long value) {
            addCriterion("account_amount <=", value, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountIn(List<Long> values) {
            addCriterion("account_amount in", values, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNotIn(List<Long> values) {
            addCriterion("account_amount not in", values, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountBetween(Long value1, Long value2) {
            addCriterion("account_amount between", value1, value2, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andAccountAmountNotBetween(Long value1, Long value2) {
            addCriterion("account_amount not between", value1, value2, "accountAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountIsNull() {
            addCriterion("payed_amount is null");
            return (Criteria) this;
        }

        public Criteria andPayedAmountIsNotNull() {
            addCriterion("payed_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPayedAmountEqualTo(Long value) {
            addCriterion("payed_amount =", value, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountNotEqualTo(Long value) {
            addCriterion("payed_amount <>", value, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountGreaterThan(Long value) {
            addCriterion("payed_amount >", value, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("payed_amount >=", value, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountLessThan(Long value) {
            addCriterion("payed_amount <", value, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountLessThanOrEqualTo(Long value) {
            addCriterion("payed_amount <=", value, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountIn(List<Long> values) {
            addCriterion("payed_amount in", values, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountNotIn(List<Long> values) {
            addCriterion("payed_amount not in", values, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountBetween(Long value1, Long value2) {
            addCriterion("payed_amount between", value1, value2, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountNotBetween(Long value1, Long value2) {
            addCriterion("payed_amount not between", value1, value2, "payedAmount");
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