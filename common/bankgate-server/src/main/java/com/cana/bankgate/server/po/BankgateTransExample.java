package com.cana.bankgate.server.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankgateTransExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public BankgateTransExample() {
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

        public Criteria andOriginIdIsNull() {
            addCriterion("origin_id is null");
            return (Criteria) this;
        }

        public Criteria andOriginIdIsNotNull() {
            addCriterion("origin_id is not null");
            return (Criteria) this;
        }

        public Criteria andOriginIdEqualTo(String value) {
            addCriterion("origin_id =", value, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdNotEqualTo(String value) {
            addCriterion("origin_id <>", value, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdGreaterThan(String value) {
            addCriterion("origin_id >", value, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdGreaterThanOrEqualTo(String value) {
            addCriterion("origin_id >=", value, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdLessThan(String value) {
            addCriterion("origin_id <", value, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdLessThanOrEqualTo(String value) {
            addCriterion("origin_id <=", value, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdLike(String value) {
            addCriterion("origin_id like", value, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdNotLike(String value) {
            addCriterion("origin_id not like", value, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdIn(List<String> values) {
            addCriterion("origin_id in", values, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdNotIn(List<String> values) {
            addCriterion("origin_id not in", values, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdBetween(String value1, String value2) {
            addCriterion("origin_id between", value1, value2, "originId");
            return (Criteria) this;
        }

        public Criteria andOriginIdNotBetween(String value1, String value2) {
            addCriterion("origin_id not between", value1, value2, "originId");
            return (Criteria) this;
        }

        public Criteria andTransDateIsNull() {
            addCriterion("trans_date is null");
            return (Criteria) this;
        }

        public Criteria andTransDateIsNotNull() {
            addCriterion("trans_date is not null");
            return (Criteria) this;
        }

        public Criteria andTransDateEqualTo(String value) {
            addCriterion("trans_date =", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateNotEqualTo(String value) {
            addCriterion("trans_date <>", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateGreaterThan(String value) {
            addCriterion("trans_date >", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateGreaterThanOrEqualTo(String value) {
            addCriterion("trans_date >=", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateLessThan(String value) {
            addCriterion("trans_date <", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateLessThanOrEqualTo(String value) {
            addCriterion("trans_date <=", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateLike(String value) {
            addCriterion("trans_date like", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateNotLike(String value) {
            addCriterion("trans_date not like", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateIn(List<String> values) {
            addCriterion("trans_date in", values, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateNotIn(List<String> values) {
            addCriterion("trans_date not in", values, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateBetween(String value1, String value2) {
            addCriterion("trans_date between", value1, value2, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateNotBetween(String value1, String value2) {
            addCriterion("trans_date not between", value1, value2, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransTypeIsNull() {
            addCriterion("trans_type is null");
            return (Criteria) this;
        }

        public Criteria andTransTypeIsNotNull() {
            addCriterion("trans_type is not null");
            return (Criteria) this;
        }

        public Criteria andTransTypeEqualTo(String value) {
            addCriterion("trans_type =", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotEqualTo(String value) {
            addCriterion("trans_type <>", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeGreaterThan(String value) {
            addCriterion("trans_type >", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeGreaterThanOrEqualTo(String value) {
            addCriterion("trans_type >=", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeLessThan(String value) {
            addCriterion("trans_type <", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeLessThanOrEqualTo(String value) {
            addCriterion("trans_type <=", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeLike(String value) {
            addCriterion("trans_type like", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotLike(String value) {
            addCriterion("trans_type not like", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeIn(List<String> values) {
            addCriterion("trans_type in", values, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotIn(List<String> values) {
            addCriterion("trans_type not in", values, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeBetween(String value1, String value2) {
            addCriterion("trans_type between", value1, value2, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotBetween(String value1, String value2) {
            addCriterion("trans_type not between", value1, value2, "transType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIsNull() {
            addCriterion("business_type is null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIsNotNull() {
            addCriterion("business_type is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeEqualTo(String value) {
            addCriterion("business_type =", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotEqualTo(String value) {
            addCriterion("business_type <>", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeGreaterThan(String value) {
            addCriterion("business_type >", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeGreaterThanOrEqualTo(String value) {
            addCriterion("business_type >=", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLessThan(String value) {
            addCriterion("business_type <", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLessThanOrEqualTo(String value) {
            addCriterion("business_type <=", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLike(String value) {
            addCriterion("business_type like", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotLike(String value) {
            addCriterion("business_type not like", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIn(List<String> values) {
            addCriterion("business_type in", values, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotIn(List<String> values) {
            addCriterion("business_type not in", values, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeBetween(String value1, String value2) {
            addCriterion("business_type between", value1, value2, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotBetween(String value1, String value2) {
            addCriterion("business_type not between", value1, value2, "businessType");
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

        public Criteria andGateSeqIsNull() {
            addCriterion("gate_seq is null");
            return (Criteria) this;
        }

        public Criteria andGateSeqIsNotNull() {
            addCriterion("gate_seq is not null");
            return (Criteria) this;
        }

        public Criteria andGateSeqEqualTo(String value) {
            addCriterion("gate_seq =", value, "gateSeq");
            return (Criteria) this;
        }

        public Criteria andGateSeqNotEqualTo(String value) {
            addCriterion("gate_seq <>", value, "gateSeq");
            return (Criteria) this;
        }

        public Criteria andGateSeqGreaterThan(String value) {
            addCriterion("gate_seq >", value, "gateSeq");
            return (Criteria) this;
        }

        public Criteria andGateSeqGreaterThanOrEqualTo(String value) {
            addCriterion("gate_seq >=", value, "gateSeq");
            return (Criteria) this;
        }

        public Criteria andGateSeqLessThan(String value) {
            addCriterion("gate_seq <", value, "gateSeq");
            return (Criteria) this;
        }

        public Criteria andGateSeqLessThanOrEqualTo(String value) {
            addCriterion("gate_seq <=", value, "gateSeq");
            return (Criteria) this;
        }

        public Criteria andGateSeqLike(String value) {
            addCriterion("gate_seq like", value, "gateSeq");
            return (Criteria) this;
        }

        public Criteria andGateSeqNotLike(String value) {
            addCriterion("gate_seq not like", value, "gateSeq");
            return (Criteria) this;
        }

        public Criteria andGateSeqIn(List<String> values) {
            addCriterion("gate_seq in", values, "gateSeq");
            return (Criteria) this;
        }

        public Criteria andGateSeqNotIn(List<String> values) {
            addCriterion("gate_seq not in", values, "gateSeq");
            return (Criteria) this;
        }

        public Criteria andGateSeqBetween(String value1, String value2) {
            addCriterion("gate_seq between", value1, value2, "gateSeq");
            return (Criteria) this;
        }

        public Criteria andGateSeqNotBetween(String value1, String value2) {
            addCriterion("gate_seq not between", value1, value2, "gateSeq");
            return (Criteria) this;
        }

        public Criteria andBankSeqIsNull() {
            addCriterion("bank_seq is null");
            return (Criteria) this;
        }

        public Criteria andBankSeqIsNotNull() {
            addCriterion("bank_seq is not null");
            return (Criteria) this;
        }

        public Criteria andBankSeqEqualTo(String value) {
            addCriterion("bank_seq =", value, "bankSeq");
            return (Criteria) this;
        }

        public Criteria andBankSeqNotEqualTo(String value) {
            addCriterion("bank_seq <>", value, "bankSeq");
            return (Criteria) this;
        }

        public Criteria andBankSeqGreaterThan(String value) {
            addCriterion("bank_seq >", value, "bankSeq");
            return (Criteria) this;
        }

        public Criteria andBankSeqGreaterThanOrEqualTo(String value) {
            addCriterion("bank_seq >=", value, "bankSeq");
            return (Criteria) this;
        }

        public Criteria andBankSeqLessThan(String value) {
            addCriterion("bank_seq <", value, "bankSeq");
            return (Criteria) this;
        }

        public Criteria andBankSeqLessThanOrEqualTo(String value) {
            addCriterion("bank_seq <=", value, "bankSeq");
            return (Criteria) this;
        }

        public Criteria andBankSeqLike(String value) {
            addCriterion("bank_seq like", value, "bankSeq");
            return (Criteria) this;
        }

        public Criteria andBankSeqNotLike(String value) {
            addCriterion("bank_seq not like", value, "bankSeq");
            return (Criteria) this;
        }

        public Criteria andBankSeqIn(List<String> values) {
            addCriterion("bank_seq in", values, "bankSeq");
            return (Criteria) this;
        }

        public Criteria andBankSeqNotIn(List<String> values) {
            addCriterion("bank_seq not in", values, "bankSeq");
            return (Criteria) this;
        }

        public Criteria andBankSeqBetween(String value1, String value2) {
            addCriterion("bank_seq between", value1, value2, "bankSeq");
            return (Criteria) this;
        }

        public Criteria andBankSeqNotBetween(String value1, String value2) {
            addCriterion("bank_seq not between", value1, value2, "bankSeq");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Long value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Long value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Long value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Long value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Long value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Long> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Long> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Long value1, Long value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Long value1, Long value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateIsNull() {
            addCriterion("bank_check_date is null");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateIsNotNull() {
            addCriterion("bank_check_date is not null");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateEqualTo(String value) {
            addCriterion("bank_check_date =", value, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateNotEqualTo(String value) {
            addCriterion("bank_check_date <>", value, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateGreaterThan(String value) {
            addCriterion("bank_check_date >", value, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateGreaterThanOrEqualTo(String value) {
            addCriterion("bank_check_date >=", value, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateLessThan(String value) {
            addCriterion("bank_check_date <", value, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateLessThanOrEqualTo(String value) {
            addCriterion("bank_check_date <=", value, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateLike(String value) {
            addCriterion("bank_check_date like", value, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateNotLike(String value) {
            addCriterion("bank_check_date not like", value, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateIn(List<String> values) {
            addCriterion("bank_check_date in", values, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateNotIn(List<String> values) {
            addCriterion("bank_check_date not in", values, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateBetween(String value1, String value2) {
            addCriterion("bank_check_date between", value1, value2, "bankCheckDate");
            return (Criteria) this;
        }

        public Criteria andBankCheckDateNotBetween(String value1, String value2) {
            addCriterion("bank_check_date not between", value1, value2, "bankCheckDate");
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

        public Criteria andBankUserNameIsNull() {
            addCriterion("bank_user_name is null");
            return (Criteria) this;
        }

        public Criteria andBankUserNameIsNotNull() {
            addCriterion("bank_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankUserNameEqualTo(String value) {
            addCriterion("bank_user_name =", value, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameNotEqualTo(String value) {
            addCriterion("bank_user_name <>", value, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameGreaterThan(String value) {
            addCriterion("bank_user_name >", value, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_user_name >=", value, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameLessThan(String value) {
            addCriterion("bank_user_name <", value, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameLessThanOrEqualTo(String value) {
            addCriterion("bank_user_name <=", value, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameLike(String value) {
            addCriterion("bank_user_name like", value, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameNotLike(String value) {
            addCriterion("bank_user_name not like", value, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameIn(List<String> values) {
            addCriterion("bank_user_name in", values, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameNotIn(List<String> values) {
            addCriterion("bank_user_name not in", values, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameBetween(String value1, String value2) {
            addCriterion("bank_user_name between", value1, value2, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameNotBetween(String value1, String value2) {
            addCriterion("bank_user_name not between", value1, value2, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andMainAccountNoIsNull() {
            addCriterion("main_account_no is null");
            return (Criteria) this;
        }

        public Criteria andMainAccountNoIsNotNull() {
            addCriterion("main_account_no is not null");
            return (Criteria) this;
        }

        public Criteria andMainAccountNoEqualTo(String value) {
            addCriterion("main_account_no =", value, "mainAccountNo");
            return (Criteria) this;
        }

        public Criteria andMainAccountNoNotEqualTo(String value) {
            addCriterion("main_account_no <>", value, "mainAccountNo");
            return (Criteria) this;
        }

        public Criteria andMainAccountNoGreaterThan(String value) {
            addCriterion("main_account_no >", value, "mainAccountNo");
            return (Criteria) this;
        }

        public Criteria andMainAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("main_account_no >=", value, "mainAccountNo");
            return (Criteria) this;
        }

        public Criteria andMainAccountNoLessThan(String value) {
            addCriterion("main_account_no <", value, "mainAccountNo");
            return (Criteria) this;
        }

        public Criteria andMainAccountNoLessThanOrEqualTo(String value) {
            addCriterion("main_account_no <=", value, "mainAccountNo");
            return (Criteria) this;
        }

        public Criteria andMainAccountNoLike(String value) {
            addCriterion("main_account_no like", value, "mainAccountNo");
            return (Criteria) this;
        }

        public Criteria andMainAccountNoNotLike(String value) {
            addCriterion("main_account_no not like", value, "mainAccountNo");
            return (Criteria) this;
        }

        public Criteria andMainAccountNoIn(List<String> values) {
            addCriterion("main_account_no in", values, "mainAccountNo");
            return (Criteria) this;
        }

        public Criteria andMainAccountNoNotIn(List<String> values) {
            addCriterion("main_account_no not in", values, "mainAccountNo");
            return (Criteria) this;
        }

        public Criteria andMainAccountNoBetween(String value1, String value2) {
            addCriterion("main_account_no between", value1, value2, "mainAccountNo");
            return (Criteria) this;
        }

        public Criteria andMainAccountNoNotBetween(String value1, String value2) {
            addCriterion("main_account_no not between", value1, value2, "mainAccountNo");
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

        public Criteria andReceiveAccountNoIsNull() {
            addCriterion("receive_account_no is null");
            return (Criteria) this;
        }

        public Criteria andReceiveAccountNoIsNotNull() {
            addCriterion("receive_account_no is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveAccountNoEqualTo(String value) {
            addCriterion("receive_account_no =", value, "receiveAccountNo");
            return (Criteria) this;
        }

        public Criteria andReceiveAccountNoNotEqualTo(String value) {
            addCriterion("receive_account_no <>", value, "receiveAccountNo");
            return (Criteria) this;
        }

        public Criteria andReceiveAccountNoGreaterThan(String value) {
            addCriterion("receive_account_no >", value, "receiveAccountNo");
            return (Criteria) this;
        }

        public Criteria andReceiveAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("receive_account_no >=", value, "receiveAccountNo");
            return (Criteria) this;
        }

        public Criteria andReceiveAccountNoLessThan(String value) {
            addCriterion("receive_account_no <", value, "receiveAccountNo");
            return (Criteria) this;
        }

        public Criteria andReceiveAccountNoLessThanOrEqualTo(String value) {
            addCriterion("receive_account_no <=", value, "receiveAccountNo");
            return (Criteria) this;
        }

        public Criteria andReceiveAccountNoLike(String value) {
            addCriterion("receive_account_no like", value, "receiveAccountNo");
            return (Criteria) this;
        }

        public Criteria andReceiveAccountNoNotLike(String value) {
            addCriterion("receive_account_no not like", value, "receiveAccountNo");
            return (Criteria) this;
        }

        public Criteria andReceiveAccountNoIn(List<String> values) {
            addCriterion("receive_account_no in", values, "receiveAccountNo");
            return (Criteria) this;
        }

        public Criteria andReceiveAccountNoNotIn(List<String> values) {
            addCriterion("receive_account_no not in", values, "receiveAccountNo");
            return (Criteria) this;
        }

        public Criteria andReceiveAccountNoBetween(String value1, String value2) {
            addCriterion("receive_account_no between", value1, value2, "receiveAccountNo");
            return (Criteria) this;
        }

        public Criteria andReceiveAccountNoNotBetween(String value1, String value2) {
            addCriterion("receive_account_no not between", value1, value2, "receiveAccountNo");
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