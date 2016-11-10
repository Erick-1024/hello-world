package com.cana.credit.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditTransferExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public CreditTransferExample() {
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

        public Criteria andBusinessSeqIsNull() {
            addCriterion("business_seq is null");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqIsNotNull() {
            addCriterion("business_seq is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqEqualTo(String value) {
            addCriterion("business_seq =", value, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqNotEqualTo(String value) {
            addCriterion("business_seq <>", value, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqGreaterThan(String value) {
            addCriterion("business_seq >", value, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqGreaterThanOrEqualTo(String value) {
            addCriterion("business_seq >=", value, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqLessThan(String value) {
            addCriterion("business_seq <", value, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqLessThanOrEqualTo(String value) {
            addCriterion("business_seq <=", value, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqLike(String value) {
            addCriterion("business_seq like", value, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqNotLike(String value) {
            addCriterion("business_seq not like", value, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqIn(List<String> values) {
            addCriterion("business_seq in", values, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqNotIn(List<String> values) {
            addCriterion("business_seq not in", values, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqBetween(String value1, String value2) {
            addCriterion("business_seq between", value1, value2, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqNotBetween(String value1, String value2) {
            addCriterion("business_seq not between", value1, value2, "businessSeq");
            return (Criteria) this;
        }

        public Criteria andCreditTradeIdIsNull() {
            addCriterion("credit_trade_id is null");
            return (Criteria) this;
        }

        public Criteria andCreditTradeIdIsNotNull() {
            addCriterion("credit_trade_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreditTradeIdEqualTo(String value) {
            addCriterion("credit_trade_id =", value, "creditTradeId");
            return (Criteria) this;
        }

        public Criteria andCreditTradeIdNotEqualTo(String value) {
            addCriterion("credit_trade_id <>", value, "creditTradeId");
            return (Criteria) this;
        }

        public Criteria andCreditTradeIdGreaterThan(String value) {
            addCriterion("credit_trade_id >", value, "creditTradeId");
            return (Criteria) this;
        }

        public Criteria andCreditTradeIdGreaterThanOrEqualTo(String value) {
            addCriterion("credit_trade_id >=", value, "creditTradeId");
            return (Criteria) this;
        }

        public Criteria andCreditTradeIdLessThan(String value) {
            addCriterion("credit_trade_id <", value, "creditTradeId");
            return (Criteria) this;
        }

        public Criteria andCreditTradeIdLessThanOrEqualTo(String value) {
            addCriterion("credit_trade_id <=", value, "creditTradeId");
            return (Criteria) this;
        }

        public Criteria andCreditTradeIdLike(String value) {
            addCriterion("credit_trade_id like", value, "creditTradeId");
            return (Criteria) this;
        }

        public Criteria andCreditTradeIdNotLike(String value) {
            addCriterion("credit_trade_id not like", value, "creditTradeId");
            return (Criteria) this;
        }

        public Criteria andCreditTradeIdIn(List<String> values) {
            addCriterion("credit_trade_id in", values, "creditTradeId");
            return (Criteria) this;
        }

        public Criteria andCreditTradeIdNotIn(List<String> values) {
            addCriterion("credit_trade_id not in", values, "creditTradeId");
            return (Criteria) this;
        }

        public Criteria andCreditTradeIdBetween(String value1, String value2) {
            addCriterion("credit_trade_id between", value1, value2, "creditTradeId");
            return (Criteria) this;
        }

        public Criteria andCreditTradeIdNotBetween(String value1, String value2) {
            addCriterion("credit_trade_id not between", value1, value2, "creditTradeId");
            return (Criteria) this;
        }

        public Criteria andTransferStatusIsNull() {
            addCriterion("transfer_status is null");
            return (Criteria) this;
        }

        public Criteria andTransferStatusIsNotNull() {
            addCriterion("transfer_status is not null");
            return (Criteria) this;
        }

        public Criteria andTransferStatusEqualTo(String value) {
            addCriterion("transfer_status =", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotEqualTo(String value) {
            addCriterion("transfer_status <>", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusGreaterThan(String value) {
            addCriterion("transfer_status >", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusGreaterThanOrEqualTo(String value) {
            addCriterion("transfer_status >=", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusLessThan(String value) {
            addCriterion("transfer_status <", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusLessThanOrEqualTo(String value) {
            addCriterion("transfer_status <=", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusLike(String value) {
            addCriterion("transfer_status like", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotLike(String value) {
            addCriterion("transfer_status not like", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusIn(List<String> values) {
            addCriterion("transfer_status in", values, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotIn(List<String> values) {
            addCriterion("transfer_status not in", values, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusBetween(String value1, String value2) {
            addCriterion("transfer_status between", value1, value2, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotBetween(String value1, String value2) {
            addCriterion("transfer_status not between", value1, value2, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andFeeIsNull() {
            addCriterion("fee is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("fee is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(Long value) {
            addCriterion("fee =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(Long value) {
            addCriterion("fee <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(Long value) {
            addCriterion("fee >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("fee >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(Long value) {
            addCriterion("fee <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(Long value) {
            addCriterion("fee <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<Long> values) {
            addCriterion("fee in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<Long> values) {
            addCriterion("fee not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(Long value1, Long value2) {
            addCriterion("fee between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(Long value1, Long value2) {
            addCriterion("fee not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andTransferTypeIsNull() {
            addCriterion("transfer_type is null");
            return (Criteria) this;
        }

        public Criteria andTransferTypeIsNotNull() {
            addCriterion("transfer_type is not null");
            return (Criteria) this;
        }

        public Criteria andTransferTypeEqualTo(String value) {
            addCriterion("transfer_type =", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeNotEqualTo(String value) {
            addCriterion("transfer_type <>", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeGreaterThan(String value) {
            addCriterion("transfer_type >", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeGreaterThanOrEqualTo(String value) {
            addCriterion("transfer_type >=", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeLessThan(String value) {
            addCriterion("transfer_type <", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeLessThanOrEqualTo(String value) {
            addCriterion("transfer_type <=", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeLike(String value) {
            addCriterion("transfer_type like", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeNotLike(String value) {
            addCriterion("transfer_type not like", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeIn(List<String> values) {
            addCriterion("transfer_type in", values, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeNotIn(List<String> values) {
            addCriterion("transfer_type not in", values, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeBetween(String value1, String value2) {
            addCriterion("transfer_type between", value1, value2, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeNotBetween(String value1, String value2) {
            addCriterion("transfer_type not between", value1, value2, "transferType");
            return (Criteria) this;
        }

        public Criteria andFromAccountNoIsNull() {
            addCriterion("from_account_no is null");
            return (Criteria) this;
        }

        public Criteria andFromAccountNoIsNotNull() {
            addCriterion("from_account_no is not null");
            return (Criteria) this;
        }

        public Criteria andFromAccountNoEqualTo(String value) {
            addCriterion("from_account_no =", value, "fromAccountNo");
            return (Criteria) this;
        }

        public Criteria andFromAccountNoNotEqualTo(String value) {
            addCriterion("from_account_no <>", value, "fromAccountNo");
            return (Criteria) this;
        }

        public Criteria andFromAccountNoGreaterThan(String value) {
            addCriterion("from_account_no >", value, "fromAccountNo");
            return (Criteria) this;
        }

        public Criteria andFromAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("from_account_no >=", value, "fromAccountNo");
            return (Criteria) this;
        }

        public Criteria andFromAccountNoLessThan(String value) {
            addCriterion("from_account_no <", value, "fromAccountNo");
            return (Criteria) this;
        }

        public Criteria andFromAccountNoLessThanOrEqualTo(String value) {
            addCriterion("from_account_no <=", value, "fromAccountNo");
            return (Criteria) this;
        }

        public Criteria andFromAccountNoLike(String value) {
            addCriterion("from_account_no like", value, "fromAccountNo");
            return (Criteria) this;
        }

        public Criteria andFromAccountNoNotLike(String value) {
            addCriterion("from_account_no not like", value, "fromAccountNo");
            return (Criteria) this;
        }

        public Criteria andFromAccountNoIn(List<String> values) {
            addCriterion("from_account_no in", values, "fromAccountNo");
            return (Criteria) this;
        }

        public Criteria andFromAccountNoNotIn(List<String> values) {
            addCriterion("from_account_no not in", values, "fromAccountNo");
            return (Criteria) this;
        }

        public Criteria andFromAccountNoBetween(String value1, String value2) {
            addCriterion("from_account_no between", value1, value2, "fromAccountNo");
            return (Criteria) this;
        }

        public Criteria andFromAccountNoNotBetween(String value1, String value2) {
            addCriterion("from_account_no not between", value1, value2, "fromAccountNo");
            return (Criteria) this;
        }

        public Criteria andFromAccountNameIsNull() {
            addCriterion("from_account_name is null");
            return (Criteria) this;
        }

        public Criteria andFromAccountNameIsNotNull() {
            addCriterion("from_account_name is not null");
            return (Criteria) this;
        }

        public Criteria andFromAccountNameEqualTo(String value) {
            addCriterion("from_account_name =", value, "fromAccountName");
            return (Criteria) this;
        }

        public Criteria andFromAccountNameNotEqualTo(String value) {
            addCriterion("from_account_name <>", value, "fromAccountName");
            return (Criteria) this;
        }

        public Criteria andFromAccountNameGreaterThan(String value) {
            addCriterion("from_account_name >", value, "fromAccountName");
            return (Criteria) this;
        }

        public Criteria andFromAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("from_account_name >=", value, "fromAccountName");
            return (Criteria) this;
        }

        public Criteria andFromAccountNameLessThan(String value) {
            addCriterion("from_account_name <", value, "fromAccountName");
            return (Criteria) this;
        }

        public Criteria andFromAccountNameLessThanOrEqualTo(String value) {
            addCriterion("from_account_name <=", value, "fromAccountName");
            return (Criteria) this;
        }

        public Criteria andFromAccountNameLike(String value) {
            addCriterion("from_account_name like", value, "fromAccountName");
            return (Criteria) this;
        }

        public Criteria andFromAccountNameNotLike(String value) {
            addCriterion("from_account_name not like", value, "fromAccountName");
            return (Criteria) this;
        }

        public Criteria andFromAccountNameIn(List<String> values) {
            addCriterion("from_account_name in", values, "fromAccountName");
            return (Criteria) this;
        }

        public Criteria andFromAccountNameNotIn(List<String> values) {
            addCriterion("from_account_name not in", values, "fromAccountName");
            return (Criteria) this;
        }

        public Criteria andFromAccountNameBetween(String value1, String value2) {
            addCriterion("from_account_name between", value1, value2, "fromAccountName");
            return (Criteria) this;
        }

        public Criteria andFromAccountNameNotBetween(String value1, String value2) {
            addCriterion("from_account_name not between", value1, value2, "fromAccountName");
            return (Criteria) this;
        }

        public Criteria andToAccountNoIsNull() {
            addCriterion("to_account_no is null");
            return (Criteria) this;
        }

        public Criteria andToAccountNoIsNotNull() {
            addCriterion("to_account_no is not null");
            return (Criteria) this;
        }

        public Criteria andToAccountNoEqualTo(String value) {
            addCriterion("to_account_no =", value, "toAccountNo");
            return (Criteria) this;
        }

        public Criteria andToAccountNoNotEqualTo(String value) {
            addCriterion("to_account_no <>", value, "toAccountNo");
            return (Criteria) this;
        }

        public Criteria andToAccountNoGreaterThan(String value) {
            addCriterion("to_account_no >", value, "toAccountNo");
            return (Criteria) this;
        }

        public Criteria andToAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("to_account_no >=", value, "toAccountNo");
            return (Criteria) this;
        }

        public Criteria andToAccountNoLessThan(String value) {
            addCriterion("to_account_no <", value, "toAccountNo");
            return (Criteria) this;
        }

        public Criteria andToAccountNoLessThanOrEqualTo(String value) {
            addCriterion("to_account_no <=", value, "toAccountNo");
            return (Criteria) this;
        }

        public Criteria andToAccountNoLike(String value) {
            addCriterion("to_account_no like", value, "toAccountNo");
            return (Criteria) this;
        }

        public Criteria andToAccountNoNotLike(String value) {
            addCriterion("to_account_no not like", value, "toAccountNo");
            return (Criteria) this;
        }

        public Criteria andToAccountNoIn(List<String> values) {
            addCriterion("to_account_no in", values, "toAccountNo");
            return (Criteria) this;
        }

        public Criteria andToAccountNoNotIn(List<String> values) {
            addCriterion("to_account_no not in", values, "toAccountNo");
            return (Criteria) this;
        }

        public Criteria andToAccountNoBetween(String value1, String value2) {
            addCriterion("to_account_no between", value1, value2, "toAccountNo");
            return (Criteria) this;
        }

        public Criteria andToAccountNoNotBetween(String value1, String value2) {
            addCriterion("to_account_no not between", value1, value2, "toAccountNo");
            return (Criteria) this;
        }

        public Criteria andToAccountNameIsNull() {
            addCriterion("to_account_name is null");
            return (Criteria) this;
        }

        public Criteria andToAccountNameIsNotNull() {
            addCriterion("to_account_name is not null");
            return (Criteria) this;
        }

        public Criteria andToAccountNameEqualTo(String value) {
            addCriterion("to_account_name =", value, "toAccountName");
            return (Criteria) this;
        }

        public Criteria andToAccountNameNotEqualTo(String value) {
            addCriterion("to_account_name <>", value, "toAccountName");
            return (Criteria) this;
        }

        public Criteria andToAccountNameGreaterThan(String value) {
            addCriterion("to_account_name >", value, "toAccountName");
            return (Criteria) this;
        }

        public Criteria andToAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("to_account_name >=", value, "toAccountName");
            return (Criteria) this;
        }

        public Criteria andToAccountNameLessThan(String value) {
            addCriterion("to_account_name <", value, "toAccountName");
            return (Criteria) this;
        }

        public Criteria andToAccountNameLessThanOrEqualTo(String value) {
            addCriterion("to_account_name <=", value, "toAccountName");
            return (Criteria) this;
        }

        public Criteria andToAccountNameLike(String value) {
            addCriterion("to_account_name like", value, "toAccountName");
            return (Criteria) this;
        }

        public Criteria andToAccountNameNotLike(String value) {
            addCriterion("to_account_name not like", value, "toAccountName");
            return (Criteria) this;
        }

        public Criteria andToAccountNameIn(List<String> values) {
            addCriterion("to_account_name in", values, "toAccountName");
            return (Criteria) this;
        }

        public Criteria andToAccountNameNotIn(List<String> values) {
            addCriterion("to_account_name not in", values, "toAccountName");
            return (Criteria) this;
        }

        public Criteria andToAccountNameBetween(String value1, String value2) {
            addCriterion("to_account_name between", value1, value2, "toAccountName");
            return (Criteria) this;
        }

        public Criteria andToAccountNameNotBetween(String value1, String value2) {
            addCriterion("to_account_name not between", value1, value2, "toAccountName");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNull() {
            addCriterion("operator_id is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNotNull() {
            addCriterion("operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdEqualTo(String value) {
            addCriterion("operator_id =", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotEqualTo(String value) {
            addCriterion("operator_id <>", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThan(String value) {
            addCriterion("operator_id >", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("operator_id >=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThan(String value) {
            addCriterion("operator_id <", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThanOrEqualTo(String value) {
            addCriterion("operator_id <=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLike(String value) {
            addCriterion("operator_id like", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotLike(String value) {
            addCriterion("operator_id not like", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIn(List<String> values) {
            addCriterion("operator_id in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotIn(List<String> values) {
            addCriterion("operator_id not in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdBetween(String value1, String value2) {
            addCriterion("operator_id between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotBetween(String value1, String value2) {
            addCriterion("operator_id not between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andTransferStartTimeIsNull() {
            addCriterion("transfer_start_time is null");
            return (Criteria) this;
        }

        public Criteria andTransferStartTimeIsNotNull() {
            addCriterion("transfer_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andTransferStartTimeEqualTo(Date value) {
            addCriterion("transfer_start_time =", value, "transferStartTime");
            return (Criteria) this;
        }

        public Criteria andTransferStartTimeNotEqualTo(Date value) {
            addCriterion("transfer_start_time <>", value, "transferStartTime");
            return (Criteria) this;
        }

        public Criteria andTransferStartTimeGreaterThan(Date value) {
            addCriterion("transfer_start_time >", value, "transferStartTime");
            return (Criteria) this;
        }

        public Criteria andTransferStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("transfer_start_time >=", value, "transferStartTime");
            return (Criteria) this;
        }

        public Criteria andTransferStartTimeLessThan(Date value) {
            addCriterion("transfer_start_time <", value, "transferStartTime");
            return (Criteria) this;
        }

        public Criteria andTransferStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("transfer_start_time <=", value, "transferStartTime");
            return (Criteria) this;
        }

        public Criteria andTransferStartTimeIn(List<Date> values) {
            addCriterion("transfer_start_time in", values, "transferStartTime");
            return (Criteria) this;
        }

        public Criteria andTransferStartTimeNotIn(List<Date> values) {
            addCriterion("transfer_start_time not in", values, "transferStartTime");
            return (Criteria) this;
        }

        public Criteria andTransferStartTimeBetween(Date value1, Date value2) {
            addCriterion("transfer_start_time between", value1, value2, "transferStartTime");
            return (Criteria) this;
        }

        public Criteria andTransferStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("transfer_start_time not between", value1, value2, "transferStartTime");
            return (Criteria) this;
        }

        public Criteria andTransferEndTimeIsNull() {
            addCriterion("transfer_end_time is null");
            return (Criteria) this;
        }

        public Criteria andTransferEndTimeIsNotNull() {
            addCriterion("transfer_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andTransferEndTimeEqualTo(Date value) {
            addCriterion("transfer_end_time =", value, "transferEndTime");
            return (Criteria) this;
        }

        public Criteria andTransferEndTimeNotEqualTo(Date value) {
            addCriterion("transfer_end_time <>", value, "transferEndTime");
            return (Criteria) this;
        }

        public Criteria andTransferEndTimeGreaterThan(Date value) {
            addCriterion("transfer_end_time >", value, "transferEndTime");
            return (Criteria) this;
        }

        public Criteria andTransferEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("transfer_end_time >=", value, "transferEndTime");
            return (Criteria) this;
        }

        public Criteria andTransferEndTimeLessThan(Date value) {
            addCriterion("transfer_end_time <", value, "transferEndTime");
            return (Criteria) this;
        }

        public Criteria andTransferEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("transfer_end_time <=", value, "transferEndTime");
            return (Criteria) this;
        }

        public Criteria andTransferEndTimeIn(List<Date> values) {
            addCriterion("transfer_end_time in", values, "transferEndTime");
            return (Criteria) this;
        }

        public Criteria andTransferEndTimeNotIn(List<Date> values) {
            addCriterion("transfer_end_time not in", values, "transferEndTime");
            return (Criteria) this;
        }

        public Criteria andTransferEndTimeBetween(Date value1, Date value2) {
            addCriterion("transfer_end_time between", value1, value2, "transferEndTime");
            return (Criteria) this;
        }

        public Criteria andTransferEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("transfer_end_time not between", value1, value2, "transferEndTime");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqHistoryIsNull() {
            addCriterion("business_seq_history is null");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqHistoryIsNotNull() {
            addCriterion("business_seq_history is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqHistoryEqualTo(String value) {
            addCriterion("business_seq_history =", value, "businessSeqHistory");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqHistoryNotEqualTo(String value) {
            addCriterion("business_seq_history <>", value, "businessSeqHistory");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqHistoryGreaterThan(String value) {
            addCriterion("business_seq_history >", value, "businessSeqHistory");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqHistoryGreaterThanOrEqualTo(String value) {
            addCriterion("business_seq_history >=", value, "businessSeqHistory");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqHistoryLessThan(String value) {
            addCriterion("business_seq_history <", value, "businessSeqHistory");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqHistoryLessThanOrEqualTo(String value) {
            addCriterion("business_seq_history <=", value, "businessSeqHistory");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqHistoryLike(String value) {
            addCriterion("business_seq_history like", value, "businessSeqHistory");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqHistoryNotLike(String value) {
            addCriterion("business_seq_history not like", value, "businessSeqHistory");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqHistoryIn(List<String> values) {
            addCriterion("business_seq_history in", values, "businessSeqHistory");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqHistoryNotIn(List<String> values) {
            addCriterion("business_seq_history not in", values, "businessSeqHistory");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqHistoryBetween(String value1, String value2) {
            addCriterion("business_seq_history between", value1, value2, "businessSeqHistory");
            return (Criteria) this;
        }

        public Criteria andBusinessSeqHistoryNotBetween(String value1, String value2) {
            addCriterion("business_seq_history not between", value1, value2, "businessSeqHistory");
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