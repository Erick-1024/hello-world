package com.cana.flight.finance.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightTicketExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public FlightTicketExample() {
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

        public Criteria andRecordIdEqualTo(String value) {
            addCriterion("record_id =", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotEqualTo(String value) {
            addCriterion("record_id <>", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThan(String value) {
            addCriterion("record_id >", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThanOrEqualTo(String value) {
            addCriterion("record_id >=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThan(String value) {
            addCriterion("record_id <", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThanOrEqualTo(String value) {
            addCriterion("record_id <=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLike(String value) {
            addCriterion("record_id like", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotLike(String value) {
            addCriterion("record_id not like", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdIn(List<String> values) {
            addCriterion("record_id in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotIn(List<String> values) {
            addCriterion("record_id not in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdBetween(String value1, String value2) {
            addCriterion("record_id between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotBetween(String value1, String value2) {
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

        public Criteria andIsDomesticIsNull() {
            addCriterion("is_domestic is null");
            return (Criteria) this;
        }

        public Criteria andIsDomesticIsNotNull() {
            addCriterion("is_domestic is not null");
            return (Criteria) this;
        }

        public Criteria andIsDomesticEqualTo(Boolean value) {
            addCriterion("is_domestic =", value, "isDomestic");
            return (Criteria) this;
        }

        public Criteria andIsDomesticNotEqualTo(Boolean value) {
            addCriterion("is_domestic <>", value, "isDomestic");
            return (Criteria) this;
        }

        public Criteria andIsDomesticGreaterThan(Boolean value) {
            addCriterion("is_domestic >", value, "isDomestic");
            return (Criteria) this;
        }

        public Criteria andIsDomesticGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_domestic >=", value, "isDomestic");
            return (Criteria) this;
        }

        public Criteria andIsDomesticLessThan(Boolean value) {
            addCriterion("is_domestic <", value, "isDomestic");
            return (Criteria) this;
        }

        public Criteria andIsDomesticLessThanOrEqualTo(Boolean value) {
            addCriterion("is_domestic <=", value, "isDomestic");
            return (Criteria) this;
        }

        public Criteria andIsDomesticIn(List<Boolean> values) {
            addCriterion("is_domestic in", values, "isDomestic");
            return (Criteria) this;
        }

        public Criteria andIsDomesticNotIn(List<Boolean> values) {
            addCriterion("is_domestic not in", values, "isDomestic");
            return (Criteria) this;
        }

        public Criteria andIsDomesticBetween(Boolean value1, Boolean value2) {
            addCriterion("is_domestic between", value1, value2, "isDomestic");
            return (Criteria) this;
        }

        public Criteria andIsDomesticNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_domestic not between", value1, value2, "isDomestic");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNull() {
            addCriterion("order_type is null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNotNull() {
            addCriterion("order_type is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeEqualTo(String value) {
            addCriterion("order_type =", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotEqualTo(String value) {
            addCriterion("order_type <>", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThan(String value) {
            addCriterion("order_type >", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThanOrEqualTo(String value) {
            addCriterion("order_type >=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThan(String value) {
            addCriterion("order_type <", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThanOrEqualTo(String value) {
            addCriterion("order_type <=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLike(String value) {
            addCriterion("order_type like", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotLike(String value) {
            addCriterion("order_type not like", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIn(List<String> values) {
            addCriterion("order_type in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotIn(List<String> values) {
            addCriterion("order_type not in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeBetween(String value1, String value2) {
            addCriterion("order_type between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotBetween(String value1, String value2) {
            addCriterion("order_type not between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andIsAdjustIsNull() {
            addCriterion("is_adjust is null");
            return (Criteria) this;
        }

        public Criteria andIsAdjustIsNotNull() {
            addCriterion("is_adjust is not null");
            return (Criteria) this;
        }

        public Criteria andIsAdjustEqualTo(Boolean value) {
            addCriterion("is_adjust =", value, "isAdjust");
            return (Criteria) this;
        }

        public Criteria andIsAdjustNotEqualTo(Boolean value) {
            addCriterion("is_adjust <>", value, "isAdjust");
            return (Criteria) this;
        }

        public Criteria andIsAdjustGreaterThan(Boolean value) {
            addCriterion("is_adjust >", value, "isAdjust");
            return (Criteria) this;
        }

        public Criteria andIsAdjustGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_adjust >=", value, "isAdjust");
            return (Criteria) this;
        }

        public Criteria andIsAdjustLessThan(Boolean value) {
            addCriterion("is_adjust <", value, "isAdjust");
            return (Criteria) this;
        }

        public Criteria andIsAdjustLessThanOrEqualTo(Boolean value) {
            addCriterion("is_adjust <=", value, "isAdjust");
            return (Criteria) this;
        }

        public Criteria andIsAdjustIn(List<Boolean> values) {
            addCriterion("is_adjust in", values, "isAdjust");
            return (Criteria) this;
        }

        public Criteria andIsAdjustNotIn(List<Boolean> values) {
            addCriterion("is_adjust not in", values, "isAdjust");
            return (Criteria) this;
        }

        public Criteria andIsAdjustBetween(Boolean value1, Boolean value2) {
            addCriterion("is_adjust between", value1, value2, "isAdjust");
            return (Criteria) this;
        }

        public Criteria andIsAdjustNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_adjust not between", value1, value2, "isAdjust");
            return (Criteria) this;
        }

        public Criteria andCompleteIssueTimeIsNull() {
            addCriterion("complete_Issue_time is null");
            return (Criteria) this;
        }

        public Criteria andCompleteIssueTimeIsNotNull() {
            addCriterion("complete_Issue_time is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteIssueTimeEqualTo(String value) {
            addCriterion("complete_Issue_time =", value, "completeIssueTime");
            return (Criteria) this;
        }

        public Criteria andCompleteIssueTimeNotEqualTo(String value) {
            addCriterion("complete_Issue_time <>", value, "completeIssueTime");
            return (Criteria) this;
        }

        public Criteria andCompleteIssueTimeGreaterThan(String value) {
            addCriterion("complete_Issue_time >", value, "completeIssueTime");
            return (Criteria) this;
        }

        public Criteria andCompleteIssueTimeGreaterThanOrEqualTo(String value) {
            addCriterion("complete_Issue_time >=", value, "completeIssueTime");
            return (Criteria) this;
        }

        public Criteria andCompleteIssueTimeLessThan(String value) {
            addCriterion("complete_Issue_time <", value, "completeIssueTime");
            return (Criteria) this;
        }

        public Criteria andCompleteIssueTimeLessThanOrEqualTo(String value) {
            addCriterion("complete_Issue_time <=", value, "completeIssueTime");
            return (Criteria) this;
        }

        public Criteria andCompleteIssueTimeLike(String value) {
            addCriterion("complete_Issue_time like", value, "completeIssueTime");
            return (Criteria) this;
        }

        public Criteria andCompleteIssueTimeNotLike(String value) {
            addCriterion("complete_Issue_time not like", value, "completeIssueTime");
            return (Criteria) this;
        }

        public Criteria andCompleteIssueTimeIn(List<String> values) {
            addCriterion("complete_Issue_time in", values, "completeIssueTime");
            return (Criteria) this;
        }

        public Criteria andCompleteIssueTimeNotIn(List<String> values) {
            addCriterion("complete_Issue_time not in", values, "completeIssueTime");
            return (Criteria) this;
        }

        public Criteria andCompleteIssueTimeBetween(String value1, String value2) {
            addCriterion("complete_Issue_time between", value1, value2, "completeIssueTime");
            return (Criteria) this;
        }

        public Criteria andCompleteIssueTimeNotBetween(String value1, String value2) {
            addCriterion("complete_Issue_time not between", value1, value2, "completeIssueTime");
            return (Criteria) this;
        }

        public Criteria andTicketNoIsNull() {
            addCriterion("ticket_no is null");
            return (Criteria) this;
        }

        public Criteria andTicketNoIsNotNull() {
            addCriterion("ticket_no is not null");
            return (Criteria) this;
        }

        public Criteria andTicketNoEqualTo(String value) {
            addCriterion("ticket_no =", value, "ticketNo");
            return (Criteria) this;
        }

        public Criteria andTicketNoNotEqualTo(String value) {
            addCriterion("ticket_no <>", value, "ticketNo");
            return (Criteria) this;
        }

        public Criteria andTicketNoGreaterThan(String value) {
            addCriterion("ticket_no >", value, "ticketNo");
            return (Criteria) this;
        }

        public Criteria andTicketNoGreaterThanOrEqualTo(String value) {
            addCriterion("ticket_no >=", value, "ticketNo");
            return (Criteria) this;
        }

        public Criteria andTicketNoLessThan(String value) {
            addCriterion("ticket_no <", value, "ticketNo");
            return (Criteria) this;
        }

        public Criteria andTicketNoLessThanOrEqualTo(String value) {
            addCriterion("ticket_no <=", value, "ticketNo");
            return (Criteria) this;
        }

        public Criteria andTicketNoLike(String value) {
            addCriterion("ticket_no like", value, "ticketNo");
            return (Criteria) this;
        }

        public Criteria andTicketNoNotLike(String value) {
            addCriterion("ticket_no not like", value, "ticketNo");
            return (Criteria) this;
        }

        public Criteria andTicketNoIn(List<String> values) {
            addCriterion("ticket_no in", values, "ticketNo");
            return (Criteria) this;
        }

        public Criteria andTicketNoNotIn(List<String> values) {
            addCriterion("ticket_no not in", values, "ticketNo");
            return (Criteria) this;
        }

        public Criteria andTicketNoBetween(String value1, String value2) {
            addCriterion("ticket_no between", value1, value2, "ticketNo");
            return (Criteria) this;
        }

        public Criteria andTicketNoNotBetween(String value1, String value2) {
            addCriterion("ticket_no not between", value1, value2, "ticketNo");
            return (Criteria) this;
        }

        public Criteria andConjunctionTicketSeqNoIsNull() {
            addCriterion("conjunction_ticket_seq_no is null");
            return (Criteria) this;
        }

        public Criteria andConjunctionTicketSeqNoIsNotNull() {
            addCriterion("conjunction_ticket_seq_no is not null");
            return (Criteria) this;
        }

        public Criteria andConjunctionTicketSeqNoEqualTo(String value) {
            addCriterion("conjunction_ticket_seq_no =", value, "conjunctionTicketSeqNo");
            return (Criteria) this;
        }

        public Criteria andConjunctionTicketSeqNoNotEqualTo(String value) {
            addCriterion("conjunction_ticket_seq_no <>", value, "conjunctionTicketSeqNo");
            return (Criteria) this;
        }

        public Criteria andConjunctionTicketSeqNoGreaterThan(String value) {
            addCriterion("conjunction_ticket_seq_no >", value, "conjunctionTicketSeqNo");
            return (Criteria) this;
        }

        public Criteria andConjunctionTicketSeqNoGreaterThanOrEqualTo(String value) {
            addCriterion("conjunction_ticket_seq_no >=", value, "conjunctionTicketSeqNo");
            return (Criteria) this;
        }

        public Criteria andConjunctionTicketSeqNoLessThan(String value) {
            addCriterion("conjunction_ticket_seq_no <", value, "conjunctionTicketSeqNo");
            return (Criteria) this;
        }

        public Criteria andConjunctionTicketSeqNoLessThanOrEqualTo(String value) {
            addCriterion("conjunction_ticket_seq_no <=", value, "conjunctionTicketSeqNo");
            return (Criteria) this;
        }

        public Criteria andConjunctionTicketSeqNoLike(String value) {
            addCriterion("conjunction_ticket_seq_no like", value, "conjunctionTicketSeqNo");
            return (Criteria) this;
        }

        public Criteria andConjunctionTicketSeqNoNotLike(String value) {
            addCriterion("conjunction_ticket_seq_no not like", value, "conjunctionTicketSeqNo");
            return (Criteria) this;
        }

        public Criteria andConjunctionTicketSeqNoIn(List<String> values) {
            addCriterion("conjunction_ticket_seq_no in", values, "conjunctionTicketSeqNo");
            return (Criteria) this;
        }

        public Criteria andConjunctionTicketSeqNoNotIn(List<String> values) {
            addCriterion("conjunction_ticket_seq_no not in", values, "conjunctionTicketSeqNo");
            return (Criteria) this;
        }

        public Criteria andConjunctionTicketSeqNoBetween(String value1, String value2) {
            addCriterion("conjunction_ticket_seq_no between", value1, value2, "conjunctionTicketSeqNo");
            return (Criteria) this;
        }

        public Criteria andConjunctionTicketSeqNoNotBetween(String value1, String value2) {
            addCriterion("conjunction_ticket_seq_no not between", value1, value2, "conjunctionTicketSeqNo");
            return (Criteria) this;
        }

        public Criteria andSupplierNameIsNull() {
            addCriterion("supplier_name is null");
            return (Criteria) this;
        }

        public Criteria andSupplierNameIsNotNull() {
            addCriterion("supplier_name is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierNameEqualTo(String value) {
            addCriterion("supplier_name =", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotEqualTo(String value) {
            addCriterion("supplier_name <>", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameGreaterThan(String value) {
            addCriterion("supplier_name >", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_name >=", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameLessThan(String value) {
            addCriterion("supplier_name <", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameLessThanOrEqualTo(String value) {
            addCriterion("supplier_name <=", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameLike(String value) {
            addCriterion("supplier_name like", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotLike(String value) {
            addCriterion("supplier_name not like", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameIn(List<String> values) {
            addCriterion("supplier_name in", values, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotIn(List<String> values) {
            addCriterion("supplier_name not in", values, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameBetween(String value1, String value2) {
            addCriterion("supplier_name between", value1, value2, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotBetween(String value1, String value2) {
            addCriterion("supplier_name not between", value1, value2, "supplierName");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(String value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(String value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(String value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(String value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(String value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLike(String value) {
            addCriterion("pay_type like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotLike(String value) {
            addCriterion("pay_type not like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<String> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<String> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(String value1, String value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(String value1, String value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNull() {
            addCriterion("total_amount is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNotNull() {
            addCriterion("total_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountEqualTo(Long value) {
            addCriterion("total_amount =", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotEqualTo(Long value) {
            addCriterion("total_amount <>", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThan(Long value) {
            addCriterion("total_amount >", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("total_amount >=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThan(Long value) {
            addCriterion("total_amount <", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThanOrEqualTo(Long value) {
            addCriterion("total_amount <=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIn(List<Long> values) {
            addCriterion("total_amount in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotIn(List<Long> values) {
            addCriterion("total_amount not in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountBetween(Long value1, Long value2) {
            addCriterion("total_amount between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotBetween(Long value1, Long value2) {
            addCriterion("total_amount not between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTicketPriceIsNull() {
            addCriterion("ticket_price is null");
            return (Criteria) this;
        }

        public Criteria andTicketPriceIsNotNull() {
            addCriterion("ticket_price is not null");
            return (Criteria) this;
        }

        public Criteria andTicketPriceEqualTo(Long value) {
            addCriterion("ticket_price =", value, "ticketPrice");
            return (Criteria) this;
        }

        public Criteria andTicketPriceNotEqualTo(Long value) {
            addCriterion("ticket_price <>", value, "ticketPrice");
            return (Criteria) this;
        }

        public Criteria andTicketPriceGreaterThan(Long value) {
            addCriterion("ticket_price >", value, "ticketPrice");
            return (Criteria) this;
        }

        public Criteria andTicketPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("ticket_price >=", value, "ticketPrice");
            return (Criteria) this;
        }

        public Criteria andTicketPriceLessThan(Long value) {
            addCriterion("ticket_price <", value, "ticketPrice");
            return (Criteria) this;
        }

        public Criteria andTicketPriceLessThanOrEqualTo(Long value) {
            addCriterion("ticket_price <=", value, "ticketPrice");
            return (Criteria) this;
        }

        public Criteria andTicketPriceIn(List<Long> values) {
            addCriterion("ticket_price in", values, "ticketPrice");
            return (Criteria) this;
        }

        public Criteria andTicketPriceNotIn(List<Long> values) {
            addCriterion("ticket_price not in", values, "ticketPrice");
            return (Criteria) this;
        }

        public Criteria andTicketPriceBetween(Long value1, Long value2) {
            addCriterion("ticket_price between", value1, value2, "ticketPrice");
            return (Criteria) this;
        }

        public Criteria andTicketPriceNotBetween(Long value1, Long value2) {
            addCriterion("ticket_price not between", value1, value2, "ticketPrice");
            return (Criteria) this;
        }

        public Criteria andTotalTaxIsNull() {
            addCriterion("total_tax is null");
            return (Criteria) this;
        }

        public Criteria andTotalTaxIsNotNull() {
            addCriterion("total_tax is not null");
            return (Criteria) this;
        }

        public Criteria andTotalTaxEqualTo(Long value) {
            addCriterion("total_tax =", value, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxNotEqualTo(Long value) {
            addCriterion("total_tax <>", value, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxGreaterThan(Long value) {
            addCriterion("total_tax >", value, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxGreaterThanOrEqualTo(Long value) {
            addCriterion("total_tax >=", value, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxLessThan(Long value) {
            addCriterion("total_tax <", value, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxLessThanOrEqualTo(Long value) {
            addCriterion("total_tax <=", value, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxIn(List<Long> values) {
            addCriterion("total_tax in", values, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxNotIn(List<Long> values) {
            addCriterion("total_tax not in", values, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxBetween(Long value1, Long value2) {
            addCriterion("total_tax between", value1, value2, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxNotBetween(Long value1, Long value2) {
            addCriterion("total_tax not between", value1, value2, "totalTax");
            return (Criteria) this;
        }

        public Criteria andFuelTaxIsNull() {
            addCriterion("fuel_tax is null");
            return (Criteria) this;
        }

        public Criteria andFuelTaxIsNotNull() {
            addCriterion("fuel_tax is not null");
            return (Criteria) this;
        }

        public Criteria andFuelTaxEqualTo(Long value) {
            addCriterion("fuel_tax =", value, "fuelTax");
            return (Criteria) this;
        }

        public Criteria andFuelTaxNotEqualTo(Long value) {
            addCriterion("fuel_tax <>", value, "fuelTax");
            return (Criteria) this;
        }

        public Criteria andFuelTaxGreaterThan(Long value) {
            addCriterion("fuel_tax >", value, "fuelTax");
            return (Criteria) this;
        }

        public Criteria andFuelTaxGreaterThanOrEqualTo(Long value) {
            addCriterion("fuel_tax >=", value, "fuelTax");
            return (Criteria) this;
        }

        public Criteria andFuelTaxLessThan(Long value) {
            addCriterion("fuel_tax <", value, "fuelTax");
            return (Criteria) this;
        }

        public Criteria andFuelTaxLessThanOrEqualTo(Long value) {
            addCriterion("fuel_tax <=", value, "fuelTax");
            return (Criteria) this;
        }

        public Criteria andFuelTaxIn(List<Long> values) {
            addCriterion("fuel_tax in", values, "fuelTax");
            return (Criteria) this;
        }

        public Criteria andFuelTaxNotIn(List<Long> values) {
            addCriterion("fuel_tax not in", values, "fuelTax");
            return (Criteria) this;
        }

        public Criteria andFuelTaxBetween(Long value1, Long value2) {
            addCriterion("fuel_tax between", value1, value2, "fuelTax");
            return (Criteria) this;
        }

        public Criteria andFuelTaxNotBetween(Long value1, Long value2) {
            addCriterion("fuel_tax not between", value1, value2, "fuelTax");
            return (Criteria) this;
        }

        public Criteria andConstructionFeeIsNull() {
            addCriterion("construction_fee is null");
            return (Criteria) this;
        }

        public Criteria andConstructionFeeIsNotNull() {
            addCriterion("construction_fee is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionFeeEqualTo(Long value) {
            addCriterion("construction_fee =", value, "constructionFee");
            return (Criteria) this;
        }

        public Criteria andConstructionFeeNotEqualTo(Long value) {
            addCriterion("construction_fee <>", value, "constructionFee");
            return (Criteria) this;
        }

        public Criteria andConstructionFeeGreaterThan(Long value) {
            addCriterion("construction_fee >", value, "constructionFee");
            return (Criteria) this;
        }

        public Criteria andConstructionFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("construction_fee >=", value, "constructionFee");
            return (Criteria) this;
        }

        public Criteria andConstructionFeeLessThan(Long value) {
            addCriterion("construction_fee <", value, "constructionFee");
            return (Criteria) this;
        }

        public Criteria andConstructionFeeLessThanOrEqualTo(Long value) {
            addCriterion("construction_fee <=", value, "constructionFee");
            return (Criteria) this;
        }

        public Criteria andConstructionFeeIn(List<Long> values) {
            addCriterion("construction_fee in", values, "constructionFee");
            return (Criteria) this;
        }

        public Criteria andConstructionFeeNotIn(List<Long> values) {
            addCriterion("construction_fee not in", values, "constructionFee");
            return (Criteria) this;
        }

        public Criteria andConstructionFeeBetween(Long value1, Long value2) {
            addCriterion("construction_fee between", value1, value2, "constructionFee");
            return (Criteria) this;
        }

        public Criteria andConstructionFeeNotBetween(Long value1, Long value2) {
            addCriterion("construction_fee not between", value1, value2, "constructionFee");
            return (Criteria) this;
        }

        public Criteria andCrsPnrIsNull() {
            addCriterion("crs_pnr is null");
            return (Criteria) this;
        }

        public Criteria andCrsPnrIsNotNull() {
            addCriterion("crs_pnr is not null");
            return (Criteria) this;
        }

        public Criteria andCrsPnrEqualTo(String value) {
            addCriterion("crs_pnr =", value, "crsPnr");
            return (Criteria) this;
        }

        public Criteria andCrsPnrNotEqualTo(String value) {
            addCriterion("crs_pnr <>", value, "crsPnr");
            return (Criteria) this;
        }

        public Criteria andCrsPnrGreaterThan(String value) {
            addCriterion("crs_pnr >", value, "crsPnr");
            return (Criteria) this;
        }

        public Criteria andCrsPnrGreaterThanOrEqualTo(String value) {
            addCriterion("crs_pnr >=", value, "crsPnr");
            return (Criteria) this;
        }

        public Criteria andCrsPnrLessThan(String value) {
            addCriterion("crs_pnr <", value, "crsPnr");
            return (Criteria) this;
        }

        public Criteria andCrsPnrLessThanOrEqualTo(String value) {
            addCriterion("crs_pnr <=", value, "crsPnr");
            return (Criteria) this;
        }

        public Criteria andCrsPnrLike(String value) {
            addCriterion("crs_pnr like", value, "crsPnr");
            return (Criteria) this;
        }

        public Criteria andCrsPnrNotLike(String value) {
            addCriterion("crs_pnr not like", value, "crsPnr");
            return (Criteria) this;
        }

        public Criteria andCrsPnrIn(List<String> values) {
            addCriterion("crs_pnr in", values, "crsPnr");
            return (Criteria) this;
        }

        public Criteria andCrsPnrNotIn(List<String> values) {
            addCriterion("crs_pnr not in", values, "crsPnr");
            return (Criteria) this;
        }

        public Criteria andCrsPnrBetween(String value1, String value2) {
            addCriterion("crs_pnr between", value1, value2, "crsPnr");
            return (Criteria) this;
        }

        public Criteria andCrsPnrNotBetween(String value1, String value2) {
            addCriterion("crs_pnr not between", value1, value2, "crsPnr");
            return (Criteria) this;
        }

        public Criteria andTicketOfficeNoIsNull() {
            addCriterion("ticket_office_no is null");
            return (Criteria) this;
        }

        public Criteria andTicketOfficeNoIsNotNull() {
            addCriterion("ticket_office_no is not null");
            return (Criteria) this;
        }

        public Criteria andTicketOfficeNoEqualTo(String value) {
            addCriterion("ticket_office_no =", value, "ticketOfficeNo");
            return (Criteria) this;
        }

        public Criteria andTicketOfficeNoNotEqualTo(String value) {
            addCriterion("ticket_office_no <>", value, "ticketOfficeNo");
            return (Criteria) this;
        }

        public Criteria andTicketOfficeNoGreaterThan(String value) {
            addCriterion("ticket_office_no >", value, "ticketOfficeNo");
            return (Criteria) this;
        }

        public Criteria andTicketOfficeNoGreaterThanOrEqualTo(String value) {
            addCriterion("ticket_office_no >=", value, "ticketOfficeNo");
            return (Criteria) this;
        }

        public Criteria andTicketOfficeNoLessThan(String value) {
            addCriterion("ticket_office_no <", value, "ticketOfficeNo");
            return (Criteria) this;
        }

        public Criteria andTicketOfficeNoLessThanOrEqualTo(String value) {
            addCriterion("ticket_office_no <=", value, "ticketOfficeNo");
            return (Criteria) this;
        }

        public Criteria andTicketOfficeNoLike(String value) {
            addCriterion("ticket_office_no like", value, "ticketOfficeNo");
            return (Criteria) this;
        }

        public Criteria andTicketOfficeNoNotLike(String value) {
            addCriterion("ticket_office_no not like", value, "ticketOfficeNo");
            return (Criteria) this;
        }

        public Criteria andTicketOfficeNoIn(List<String> values) {
            addCriterion("ticket_office_no in", values, "ticketOfficeNo");
            return (Criteria) this;
        }

        public Criteria andTicketOfficeNoNotIn(List<String> values) {
            addCriterion("ticket_office_no not in", values, "ticketOfficeNo");
            return (Criteria) this;
        }

        public Criteria andTicketOfficeNoBetween(String value1, String value2) {
            addCriterion("ticket_office_no between", value1, value2, "ticketOfficeNo");
            return (Criteria) this;
        }

        public Criteria andTicketOfficeNoNotBetween(String value1, String value2) {
            addCriterion("ticket_office_no not between", value1, value2, "ticketOfficeNo");
            return (Criteria) this;
        }

        public Criteria andAirlineIsNull() {
            addCriterion("airline is null");
            return (Criteria) this;
        }

        public Criteria andAirlineIsNotNull() {
            addCriterion("airline is not null");
            return (Criteria) this;
        }

        public Criteria andAirlineEqualTo(String value) {
            addCriterion("airline =", value, "airline");
            return (Criteria) this;
        }

        public Criteria andAirlineNotEqualTo(String value) {
            addCriterion("airline <>", value, "airline");
            return (Criteria) this;
        }

        public Criteria andAirlineGreaterThan(String value) {
            addCriterion("airline >", value, "airline");
            return (Criteria) this;
        }

        public Criteria andAirlineGreaterThanOrEqualTo(String value) {
            addCriterion("airline >=", value, "airline");
            return (Criteria) this;
        }

        public Criteria andAirlineLessThan(String value) {
            addCriterion("airline <", value, "airline");
            return (Criteria) this;
        }

        public Criteria andAirlineLessThanOrEqualTo(String value) {
            addCriterion("airline <=", value, "airline");
            return (Criteria) this;
        }

        public Criteria andAirlineLike(String value) {
            addCriterion("airline like", value, "airline");
            return (Criteria) this;
        }

        public Criteria andAirlineNotLike(String value) {
            addCriterion("airline not like", value, "airline");
            return (Criteria) this;
        }

        public Criteria andAirlineIn(List<String> values) {
            addCriterion("airline in", values, "airline");
            return (Criteria) this;
        }

        public Criteria andAirlineNotIn(List<String> values) {
            addCriterion("airline not in", values, "airline");
            return (Criteria) this;
        }

        public Criteria andAirlineBetween(String value1, String value2) {
            addCriterion("airline between", value1, value2, "airline");
            return (Criteria) this;
        }

        public Criteria andAirlineNotBetween(String value1, String value2) {
            addCriterion("airline not between", value1, value2, "airline");
            return (Criteria) this;
        }

        public Criteria andItineraryIsNull() {
            addCriterion("itinerary is null");
            return (Criteria) this;
        }

        public Criteria andItineraryIsNotNull() {
            addCriterion("itinerary is not null");
            return (Criteria) this;
        }

        public Criteria andItineraryEqualTo(String value) {
            addCriterion("itinerary =", value, "itinerary");
            return (Criteria) this;
        }

        public Criteria andItineraryNotEqualTo(String value) {
            addCriterion("itinerary <>", value, "itinerary");
            return (Criteria) this;
        }

        public Criteria andItineraryGreaterThan(String value) {
            addCriterion("itinerary >", value, "itinerary");
            return (Criteria) this;
        }

        public Criteria andItineraryGreaterThanOrEqualTo(String value) {
            addCriterion("itinerary >=", value, "itinerary");
            return (Criteria) this;
        }

        public Criteria andItineraryLessThan(String value) {
            addCriterion("itinerary <", value, "itinerary");
            return (Criteria) this;
        }

        public Criteria andItineraryLessThanOrEqualTo(String value) {
            addCriterion("itinerary <=", value, "itinerary");
            return (Criteria) this;
        }

        public Criteria andItineraryLike(String value) {
            addCriterion("itinerary like", value, "itinerary");
            return (Criteria) this;
        }

        public Criteria andItineraryNotLike(String value) {
            addCriterion("itinerary not like", value, "itinerary");
            return (Criteria) this;
        }

        public Criteria andItineraryIn(List<String> values) {
            addCriterion("itinerary in", values, "itinerary");
            return (Criteria) this;
        }

        public Criteria andItineraryNotIn(List<String> values) {
            addCriterion("itinerary not in", values, "itinerary");
            return (Criteria) this;
        }

        public Criteria andItineraryBetween(String value1, String value2) {
            addCriterion("itinerary between", value1, value2, "itinerary");
            return (Criteria) this;
        }

        public Criteria andItineraryNotBetween(String value1, String value2) {
            addCriterion("itinerary not between", value1, value2, "itinerary");
            return (Criteria) this;
        }

        public Criteria andDepartureDateTimeIsNull() {
            addCriterion("departure_date_time is null");
            return (Criteria) this;
        }

        public Criteria andDepartureDateTimeIsNotNull() {
            addCriterion("departure_date_time is not null");
            return (Criteria) this;
        }

        public Criteria andDepartureDateTimeEqualTo(String value) {
            addCriterion("departure_date_time =", value, "departureDateTime");
            return (Criteria) this;
        }

        public Criteria andDepartureDateTimeNotEqualTo(String value) {
            addCriterion("departure_date_time <>", value, "departureDateTime");
            return (Criteria) this;
        }

        public Criteria andDepartureDateTimeGreaterThan(String value) {
            addCriterion("departure_date_time >", value, "departureDateTime");
            return (Criteria) this;
        }

        public Criteria andDepartureDateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("departure_date_time >=", value, "departureDateTime");
            return (Criteria) this;
        }

        public Criteria andDepartureDateTimeLessThan(String value) {
            addCriterion("departure_date_time <", value, "departureDateTime");
            return (Criteria) this;
        }

        public Criteria andDepartureDateTimeLessThanOrEqualTo(String value) {
            addCriterion("departure_date_time <=", value, "departureDateTime");
            return (Criteria) this;
        }

        public Criteria andDepartureDateTimeLike(String value) {
            addCriterion("departure_date_time like", value, "departureDateTime");
            return (Criteria) this;
        }

        public Criteria andDepartureDateTimeNotLike(String value) {
            addCriterion("departure_date_time not like", value, "departureDateTime");
            return (Criteria) this;
        }

        public Criteria andDepartureDateTimeIn(List<String> values) {
            addCriterion("departure_date_time in", values, "departureDateTime");
            return (Criteria) this;
        }

        public Criteria andDepartureDateTimeNotIn(List<String> values) {
            addCriterion("departure_date_time not in", values, "departureDateTime");
            return (Criteria) this;
        }

        public Criteria andDepartureDateTimeBetween(String value1, String value2) {
            addCriterion("departure_date_time between", value1, value2, "departureDateTime");
            return (Criteria) this;
        }

        public Criteria andDepartureDateTimeNotBetween(String value1, String value2) {
            addCriterion("departure_date_time not between", value1, value2, "departureDateTime");
            return (Criteria) this;
        }

        public Criteria andCabinCodeIsNull() {
            addCriterion("cabin_code is null");
            return (Criteria) this;
        }

        public Criteria andCabinCodeIsNotNull() {
            addCriterion("cabin_code is not null");
            return (Criteria) this;
        }

        public Criteria andCabinCodeEqualTo(String value) {
            addCriterion("cabin_code =", value, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeNotEqualTo(String value) {
            addCriterion("cabin_code <>", value, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeGreaterThan(String value) {
            addCriterion("cabin_code >", value, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeGreaterThanOrEqualTo(String value) {
            addCriterion("cabin_code >=", value, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeLessThan(String value) {
            addCriterion("cabin_code <", value, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeLessThanOrEqualTo(String value) {
            addCriterion("cabin_code <=", value, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeLike(String value) {
            addCriterion("cabin_code like", value, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeNotLike(String value) {
            addCriterion("cabin_code not like", value, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeIn(List<String> values) {
            addCriterion("cabin_code in", values, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeNotIn(List<String> values) {
            addCriterion("cabin_code not in", values, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeBetween(String value1, String value2) {
            addCriterion("cabin_code between", value1, value2, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andCabinCodeNotBetween(String value1, String value2) {
            addCriterion("cabin_code not between", value1, value2, "cabinCode");
            return (Criteria) this;
        }

        public Criteria andPassengerNameIsNull() {
            addCriterion("passenger_name is null");
            return (Criteria) this;
        }

        public Criteria andPassengerNameIsNotNull() {
            addCriterion("passenger_name is not null");
            return (Criteria) this;
        }

        public Criteria andPassengerNameEqualTo(String value) {
            addCriterion("passenger_name =", value, "passengerName");
            return (Criteria) this;
        }

        public Criteria andPassengerNameNotEqualTo(String value) {
            addCriterion("passenger_name <>", value, "passengerName");
            return (Criteria) this;
        }

        public Criteria andPassengerNameGreaterThan(String value) {
            addCriterion("passenger_name >", value, "passengerName");
            return (Criteria) this;
        }

        public Criteria andPassengerNameGreaterThanOrEqualTo(String value) {
            addCriterion("passenger_name >=", value, "passengerName");
            return (Criteria) this;
        }

        public Criteria andPassengerNameLessThan(String value) {
            addCriterion("passenger_name <", value, "passengerName");
            return (Criteria) this;
        }

        public Criteria andPassengerNameLessThanOrEqualTo(String value) {
            addCriterion("passenger_name <=", value, "passengerName");
            return (Criteria) this;
        }

        public Criteria andPassengerNameLike(String value) {
            addCriterion("passenger_name like", value, "passengerName");
            return (Criteria) this;
        }

        public Criteria andPassengerNameNotLike(String value) {
            addCriterion("passenger_name not like", value, "passengerName");
            return (Criteria) this;
        }

        public Criteria andPassengerNameIn(List<String> values) {
            addCriterion("passenger_name in", values, "passengerName");
            return (Criteria) this;
        }

        public Criteria andPassengerNameNotIn(List<String> values) {
            addCriterion("passenger_name not in", values, "passengerName");
            return (Criteria) this;
        }

        public Criteria andPassengerNameBetween(String value1, String value2) {
            addCriterion("passenger_name between", value1, value2, "passengerName");
            return (Criteria) this;
        }

        public Criteria andPassengerNameNotBetween(String value1, String value2) {
            addCriterion("passenger_name not between", value1, value2, "passengerName");
            return (Criteria) this;
        }

        public Criteria andPassengerTypeIsNull() {
            addCriterion("passenger_type is null");
            return (Criteria) this;
        }

        public Criteria andPassengerTypeIsNotNull() {
            addCriterion("passenger_type is not null");
            return (Criteria) this;
        }

        public Criteria andPassengerTypeEqualTo(String value) {
            addCriterion("passenger_type =", value, "passengerType");
            return (Criteria) this;
        }

        public Criteria andPassengerTypeNotEqualTo(String value) {
            addCriterion("passenger_type <>", value, "passengerType");
            return (Criteria) this;
        }

        public Criteria andPassengerTypeGreaterThan(String value) {
            addCriterion("passenger_type >", value, "passengerType");
            return (Criteria) this;
        }

        public Criteria andPassengerTypeGreaterThanOrEqualTo(String value) {
            addCriterion("passenger_type >=", value, "passengerType");
            return (Criteria) this;
        }

        public Criteria andPassengerTypeLessThan(String value) {
            addCriterion("passenger_type <", value, "passengerType");
            return (Criteria) this;
        }

        public Criteria andPassengerTypeLessThanOrEqualTo(String value) {
            addCriterion("passenger_type <=", value, "passengerType");
            return (Criteria) this;
        }

        public Criteria andPassengerTypeLike(String value) {
            addCriterion("passenger_type like", value, "passengerType");
            return (Criteria) this;
        }

        public Criteria andPassengerTypeNotLike(String value) {
            addCriterion("passenger_type not like", value, "passengerType");
            return (Criteria) this;
        }

        public Criteria andPassengerTypeIn(List<String> values) {
            addCriterion("passenger_type in", values, "passengerType");
            return (Criteria) this;
        }

        public Criteria andPassengerTypeNotIn(List<String> values) {
            addCriterion("passenger_type not in", values, "passengerType");
            return (Criteria) this;
        }

        public Criteria andPassengerTypeBetween(String value1, String value2) {
            addCriterion("passenger_type between", value1, value2, "passengerType");
            return (Criteria) this;
        }

        public Criteria andPassengerTypeNotBetween(String value1, String value2) {
            addCriterion("passenger_type not between", value1, value2, "passengerType");
            return (Criteria) this;
        }

        public Criteria andDoucmentTypeIsNull() {
            addCriterion("doucment_type is null");
            return (Criteria) this;
        }

        public Criteria andDoucmentTypeIsNotNull() {
            addCriterion("doucment_type is not null");
            return (Criteria) this;
        }

        public Criteria andDoucmentTypeEqualTo(String value) {
            addCriterion("doucment_type =", value, "doucmentType");
            return (Criteria) this;
        }

        public Criteria andDoucmentTypeNotEqualTo(String value) {
            addCriterion("doucment_type <>", value, "doucmentType");
            return (Criteria) this;
        }

        public Criteria andDoucmentTypeGreaterThan(String value) {
            addCriterion("doucment_type >", value, "doucmentType");
            return (Criteria) this;
        }

        public Criteria andDoucmentTypeGreaterThanOrEqualTo(String value) {
            addCriterion("doucment_type >=", value, "doucmentType");
            return (Criteria) this;
        }

        public Criteria andDoucmentTypeLessThan(String value) {
            addCriterion("doucment_type <", value, "doucmentType");
            return (Criteria) this;
        }

        public Criteria andDoucmentTypeLessThanOrEqualTo(String value) {
            addCriterion("doucment_type <=", value, "doucmentType");
            return (Criteria) this;
        }

        public Criteria andDoucmentTypeLike(String value) {
            addCriterion("doucment_type like", value, "doucmentType");
            return (Criteria) this;
        }

        public Criteria andDoucmentTypeNotLike(String value) {
            addCriterion("doucment_type not like", value, "doucmentType");
            return (Criteria) this;
        }

        public Criteria andDoucmentTypeIn(List<String> values) {
            addCriterion("doucment_type in", values, "doucmentType");
            return (Criteria) this;
        }

        public Criteria andDoucmentTypeNotIn(List<String> values) {
            addCriterion("doucment_type not in", values, "doucmentType");
            return (Criteria) this;
        }

        public Criteria andDoucmentTypeBetween(String value1, String value2) {
            addCriterion("doucment_type between", value1, value2, "doucmentType");
            return (Criteria) this;
        }

        public Criteria andDoucmentTypeNotBetween(String value1, String value2) {
            addCriterion("doucment_type not between", value1, value2, "doucmentType");
            return (Criteria) this;
        }

        public Criteria andDocumentNoIsNull() {
            addCriterion("document_no is null");
            return (Criteria) this;
        }

        public Criteria andDocumentNoIsNotNull() {
            addCriterion("document_no is not null");
            return (Criteria) this;
        }

        public Criteria andDocumentNoEqualTo(String value) {
            addCriterion("document_no =", value, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoNotEqualTo(String value) {
            addCriterion("document_no <>", value, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoGreaterThan(String value) {
            addCriterion("document_no >", value, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoGreaterThanOrEqualTo(String value) {
            addCriterion("document_no >=", value, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoLessThan(String value) {
            addCriterion("document_no <", value, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoLessThanOrEqualTo(String value) {
            addCriterion("document_no <=", value, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoLike(String value) {
            addCriterion("document_no like", value, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoNotLike(String value) {
            addCriterion("document_no not like", value, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoIn(List<String> values) {
            addCriterion("document_no in", values, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoNotIn(List<String> values) {
            addCriterion("document_no not in", values, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoBetween(String value1, String value2) {
            addCriterion("document_no between", value1, value2, "documentNo");
            return (Criteria) this;
        }

        public Criteria andDocumentNoNotBetween(String value1, String value2) {
            addCriterion("document_no not between", value1, value2, "documentNo");
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

        public Criteria andOrigRecordIdIsNull() {
            addCriterion("orig_record_id is null");
            return (Criteria) this;
        }

        public Criteria andOrigRecordIdIsNotNull() {
            addCriterion("orig_record_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrigRecordIdEqualTo(String value) {
            addCriterion("orig_record_id =", value, "origRecordId");
            return (Criteria) this;
        }

        public Criteria andOrigRecordIdNotEqualTo(String value) {
            addCriterion("orig_record_id <>", value, "origRecordId");
            return (Criteria) this;
        }

        public Criteria andOrigRecordIdGreaterThan(String value) {
            addCriterion("orig_record_id >", value, "origRecordId");
            return (Criteria) this;
        }

        public Criteria andOrigRecordIdGreaterThanOrEqualTo(String value) {
            addCriterion("orig_record_id >=", value, "origRecordId");
            return (Criteria) this;
        }

        public Criteria andOrigRecordIdLessThan(String value) {
            addCriterion("orig_record_id <", value, "origRecordId");
            return (Criteria) this;
        }

        public Criteria andOrigRecordIdLessThanOrEqualTo(String value) {
            addCriterion("orig_record_id <=", value, "origRecordId");
            return (Criteria) this;
        }

        public Criteria andOrigRecordIdLike(String value) {
            addCriterion("orig_record_id like", value, "origRecordId");
            return (Criteria) this;
        }

        public Criteria andOrigRecordIdNotLike(String value) {
            addCriterion("orig_record_id not like", value, "origRecordId");
            return (Criteria) this;
        }

        public Criteria andOrigRecordIdIn(List<String> values) {
            addCriterion("orig_record_id in", values, "origRecordId");
            return (Criteria) this;
        }

        public Criteria andOrigRecordIdNotIn(List<String> values) {
            addCriterion("orig_record_id not in", values, "origRecordId");
            return (Criteria) this;
        }

        public Criteria andOrigRecordIdBetween(String value1, String value2) {
            addCriterion("orig_record_id between", value1, value2, "origRecordId");
            return (Criteria) this;
        }

        public Criteria andOrigRecordIdNotBetween(String value1, String value2) {
            addCriterion("orig_record_id not between", value1, value2, "origRecordId");
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

        public Criteria andIsValuableIsNull() {
            addCriterion("is_valuable is null");
            return (Criteria) this;
        }

        public Criteria andIsValuableIsNotNull() {
            addCriterion("is_valuable is not null");
            return (Criteria) this;
        }

        public Criteria andIsValuableEqualTo(Boolean value) {
            addCriterion("is_valuable =", value, "isValuable");
            return (Criteria) this;
        }

        public Criteria andIsValuableNotEqualTo(Boolean value) {
            addCriterion("is_valuable <>", value, "isValuable");
            return (Criteria) this;
        }

        public Criteria andIsValuableGreaterThan(Boolean value) {
            addCriterion("is_valuable >", value, "isValuable");
            return (Criteria) this;
        }

        public Criteria andIsValuableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_valuable >=", value, "isValuable");
            return (Criteria) this;
        }

        public Criteria andIsValuableLessThan(Boolean value) {
            addCriterion("is_valuable <", value, "isValuable");
            return (Criteria) this;
        }

        public Criteria andIsValuableLessThanOrEqualTo(Boolean value) {
            addCriterion("is_valuable <=", value, "isValuable");
            return (Criteria) this;
        }

        public Criteria andIsValuableIn(List<Boolean> values) {
            addCriterion("is_valuable in", values, "isValuable");
            return (Criteria) this;
        }

        public Criteria andIsValuableNotIn(List<Boolean> values) {
            addCriterion("is_valuable not in", values, "isValuable");
            return (Criteria) this;
        }

        public Criteria andIsValuableBetween(Boolean value1, Boolean value2) {
            addCriterion("is_valuable between", value1, value2, "isValuable");
            return (Criteria) this;
        }

        public Criteria andIsValuableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_valuable not between", value1, value2, "isValuable");
            return (Criteria) this;
        }

        public Criteria andNoValueCauseIsNull() {
            addCriterion("no_value_cause is null");
            return (Criteria) this;
        }

        public Criteria andNoValueCauseIsNotNull() {
            addCriterion("no_value_cause is not null");
            return (Criteria) this;
        }

        public Criteria andNoValueCauseEqualTo(String value) {
            addCriterion("no_value_cause =", value, "noValueCause");
            return (Criteria) this;
        }

        public Criteria andNoValueCauseNotEqualTo(String value) {
            addCriterion("no_value_cause <>", value, "noValueCause");
            return (Criteria) this;
        }

        public Criteria andNoValueCauseGreaterThan(String value) {
            addCriterion("no_value_cause >", value, "noValueCause");
            return (Criteria) this;
        }

        public Criteria andNoValueCauseGreaterThanOrEqualTo(String value) {
            addCriterion("no_value_cause >=", value, "noValueCause");
            return (Criteria) this;
        }

        public Criteria andNoValueCauseLessThan(String value) {
            addCriterion("no_value_cause <", value, "noValueCause");
            return (Criteria) this;
        }

        public Criteria andNoValueCauseLessThanOrEqualTo(String value) {
            addCriterion("no_value_cause <=", value, "noValueCause");
            return (Criteria) this;
        }

        public Criteria andNoValueCauseLike(String value) {
            addCriterion("no_value_cause like", value, "noValueCause");
            return (Criteria) this;
        }

        public Criteria andNoValueCauseNotLike(String value) {
            addCriterion("no_value_cause not like", value, "noValueCause");
            return (Criteria) this;
        }

        public Criteria andNoValueCauseIn(List<String> values) {
            addCriterion("no_value_cause in", values, "noValueCause");
            return (Criteria) this;
        }

        public Criteria andNoValueCauseNotIn(List<String> values) {
            addCriterion("no_value_cause not in", values, "noValueCause");
            return (Criteria) this;
        }

        public Criteria andNoValueCauseBetween(String value1, String value2) {
            addCriterion("no_value_cause between", value1, value2, "noValueCause");
            return (Criteria) this;
        }

        public Criteria andNoValueCauseNotBetween(String value1, String value2) {
            addCriterion("no_value_cause not between", value1, value2, "noValueCause");
            return (Criteria) this;
        }

        public Criteria andNoTakeOffNumberIsNull() {
            addCriterion("no_take_off_number is null");
            return (Criteria) this;
        }

        public Criteria andNoTakeOffNumberIsNotNull() {
            addCriterion("no_take_off_number is not null");
            return (Criteria) this;
        }

        public Criteria andNoTakeOffNumberEqualTo(Integer value) {
            addCriterion("no_take_off_number =", value, "noTakeOffNumber");
            return (Criteria) this;
        }

        public Criteria andNoTakeOffNumberNotEqualTo(Integer value) {
            addCriterion("no_take_off_number <>", value, "noTakeOffNumber");
            return (Criteria) this;
        }

        public Criteria andNoTakeOffNumberGreaterThan(Integer value) {
            addCriterion("no_take_off_number >", value, "noTakeOffNumber");
            return (Criteria) this;
        }

        public Criteria andNoTakeOffNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("no_take_off_number >=", value, "noTakeOffNumber");
            return (Criteria) this;
        }

        public Criteria andNoTakeOffNumberLessThan(Integer value) {
            addCriterion("no_take_off_number <", value, "noTakeOffNumber");
            return (Criteria) this;
        }

        public Criteria andNoTakeOffNumberLessThanOrEqualTo(Integer value) {
            addCriterion("no_take_off_number <=", value, "noTakeOffNumber");
            return (Criteria) this;
        }

        public Criteria andNoTakeOffNumberIn(List<Integer> values) {
            addCriterion("no_take_off_number in", values, "noTakeOffNumber");
            return (Criteria) this;
        }

        public Criteria andNoTakeOffNumberNotIn(List<Integer> values) {
            addCriterion("no_take_off_number not in", values, "noTakeOffNumber");
            return (Criteria) this;
        }

        public Criteria andNoTakeOffNumberBetween(Integer value1, Integer value2) {
            addCriterion("no_take_off_number between", value1, value2, "noTakeOffNumber");
            return (Criteria) this;
        }

        public Criteria andNoTakeOffNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("no_take_off_number not between", value1, value2, "noTakeOffNumber");
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