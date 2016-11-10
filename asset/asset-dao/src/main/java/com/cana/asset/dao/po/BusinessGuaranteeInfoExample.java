package com.cana.asset.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessGuaranteeInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public BusinessGuaranteeInfoExample() {
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

        public Criteria andBusinessInfoIdIsNull() {
            addCriterion("business_info_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoIdIsNotNull() {
            addCriterion("business_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoIdEqualTo(String value) {
            addCriterion("business_info_id =", value, "businessInfoId");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoIdNotEqualTo(String value) {
            addCriterion("business_info_id <>", value, "businessInfoId");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoIdGreaterThan(String value) {
            addCriterion("business_info_id >", value, "businessInfoId");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoIdGreaterThanOrEqualTo(String value) {
            addCriterion("business_info_id >=", value, "businessInfoId");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoIdLessThan(String value) {
            addCriterion("business_info_id <", value, "businessInfoId");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoIdLessThanOrEqualTo(String value) {
            addCriterion("business_info_id <=", value, "businessInfoId");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoIdLike(String value) {
            addCriterion("business_info_id like", value, "businessInfoId");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoIdNotLike(String value) {
            addCriterion("business_info_id not like", value, "businessInfoId");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoIdIn(List<String> values) {
            addCriterion("business_info_id in", values, "businessInfoId");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoIdNotIn(List<String> values) {
            addCriterion("business_info_id not in", values, "businessInfoId");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoIdBetween(String value1, String value2) {
            addCriterion("business_info_id between", value1, value2, "businessInfoId");
            return (Criteria) this;
        }

        public Criteria andBusinessInfoIdNotBetween(String value1, String value2) {
            addCriterion("business_info_id not between", value1, value2, "businessInfoId");
            return (Criteria) this;
        }

        public Criteria andGuaranteedContractNoIsNull() {
            addCriterion("guaranteed_contract_no is null");
            return (Criteria) this;
        }

        public Criteria andGuaranteedContractNoIsNotNull() {
            addCriterion("guaranteed_contract_no is not null");
            return (Criteria) this;
        }

        public Criteria andGuaranteedContractNoEqualTo(String value) {
            addCriterion("guaranteed_contract_no =", value, "guaranteedContractNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteedContractNoNotEqualTo(String value) {
            addCriterion("guaranteed_contract_no <>", value, "guaranteedContractNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteedContractNoGreaterThan(String value) {
            addCriterion("guaranteed_contract_no >", value, "guaranteedContractNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteedContractNoGreaterThanOrEqualTo(String value) {
            addCriterion("guaranteed_contract_no >=", value, "guaranteedContractNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteedContractNoLessThan(String value) {
            addCriterion("guaranteed_contract_no <", value, "guaranteedContractNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteedContractNoLessThanOrEqualTo(String value) {
            addCriterion("guaranteed_contract_no <=", value, "guaranteedContractNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteedContractNoLike(String value) {
            addCriterion("guaranteed_contract_no like", value, "guaranteedContractNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteedContractNoNotLike(String value) {
            addCriterion("guaranteed_contract_no not like", value, "guaranteedContractNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteedContractNoIn(List<String> values) {
            addCriterion("guaranteed_contract_no in", values, "guaranteedContractNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteedContractNoNotIn(List<String> values) {
            addCriterion("guaranteed_contract_no not in", values, "guaranteedContractNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteedContractNoBetween(String value1, String value2) {
            addCriterion("guaranteed_contract_no between", value1, value2, "guaranteedContractNo");
            return (Criteria) this;
        }

        public Criteria andGuaranteedContractNoNotBetween(String value1, String value2) {
            addCriterion("guaranteed_contract_no not between", value1, value2, "guaranteedContractNo");
            return (Criteria) this;
        }

        public Criteria andGuarantorInfoIsNull() {
            addCriterion("guarantor_info is null");
            return (Criteria) this;
        }

        public Criteria andGuarantorInfoIsNotNull() {
            addCriterion("guarantor_info is not null");
            return (Criteria) this;
        }

        public Criteria andGuarantorInfoEqualTo(String value) {
            addCriterion("guarantor_info =", value, "guarantorInfo");
            return (Criteria) this;
        }

        public Criteria andGuarantorInfoNotEqualTo(String value) {
            addCriterion("guarantor_info <>", value, "guarantorInfo");
            return (Criteria) this;
        }

        public Criteria andGuarantorInfoGreaterThan(String value) {
            addCriterion("guarantor_info >", value, "guarantorInfo");
            return (Criteria) this;
        }

        public Criteria andGuarantorInfoGreaterThanOrEqualTo(String value) {
            addCriterion("guarantor_info >=", value, "guarantorInfo");
            return (Criteria) this;
        }

        public Criteria andGuarantorInfoLessThan(String value) {
            addCriterion("guarantor_info <", value, "guarantorInfo");
            return (Criteria) this;
        }

        public Criteria andGuarantorInfoLessThanOrEqualTo(String value) {
            addCriterion("guarantor_info <=", value, "guarantorInfo");
            return (Criteria) this;
        }

        public Criteria andGuarantorInfoLike(String value) {
            addCriterion("guarantor_info like", value, "guarantorInfo");
            return (Criteria) this;
        }

        public Criteria andGuarantorInfoNotLike(String value) {
            addCriterion("guarantor_info not like", value, "guarantorInfo");
            return (Criteria) this;
        }

        public Criteria andGuarantorInfoIn(List<String> values) {
            addCriterion("guarantor_info in", values, "guarantorInfo");
            return (Criteria) this;
        }

        public Criteria andGuarantorInfoNotIn(List<String> values) {
            addCriterion("guarantor_info not in", values, "guarantorInfo");
            return (Criteria) this;
        }

        public Criteria andGuarantorInfoBetween(String value1, String value2) {
            addCriterion("guarantor_info between", value1, value2, "guarantorInfo");
            return (Criteria) this;
        }

        public Criteria andGuarantorInfoNotBetween(String value1, String value2) {
            addCriterion("guarantor_info not between", value1, value2, "guarantorInfo");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeIsNull() {
            addCriterion("guarantee_type is null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeIsNotNull() {
            addCriterion("guarantee_type is not null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeEqualTo(String value) {
            addCriterion("guarantee_type =", value, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeNotEqualTo(String value) {
            addCriterion("guarantee_type <>", value, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeGreaterThan(String value) {
            addCriterion("guarantee_type >", value, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("guarantee_type >=", value, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeLessThan(String value) {
            addCriterion("guarantee_type <", value, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeLessThanOrEqualTo(String value) {
            addCriterion("guarantee_type <=", value, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeLike(String value) {
            addCriterion("guarantee_type like", value, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeNotLike(String value) {
            addCriterion("guarantee_type not like", value, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeIn(List<String> values) {
            addCriterion("guarantee_type in", values, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeNotIn(List<String> values) {
            addCriterion("guarantee_type not in", values, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeBetween(String value1, String value2) {
            addCriterion("guarantee_type between", value1, value2, "guaranteeType");
            return (Criteria) this;
        }

        public Criteria andGuaranteeTypeNotBetween(String value1, String value2) {
            addCriterion("guarantee_type not between", value1, value2, "guaranteeType");
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