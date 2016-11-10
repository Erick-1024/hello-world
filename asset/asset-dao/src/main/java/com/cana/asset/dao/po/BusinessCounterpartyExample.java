package com.cana.asset.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessCounterpartyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public BusinessCounterpartyExample() {
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

        public Criteria andCounterpartyIdIsNull() {
            addCriterion("counterparty_id is null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdIsNotNull() {
            addCriterion("counterparty_id is not null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdEqualTo(String value) {
            addCriterion("counterparty_id =", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdNotEqualTo(String value) {
            addCriterion("counterparty_id <>", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdGreaterThan(String value) {
            addCriterion("counterparty_id >", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdGreaterThanOrEqualTo(String value) {
            addCriterion("counterparty_id >=", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdLessThan(String value) {
            addCriterion("counterparty_id <", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdLessThanOrEqualTo(String value) {
            addCriterion("counterparty_id <=", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdLike(String value) {
            addCriterion("counterparty_id like", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdNotLike(String value) {
            addCriterion("counterparty_id not like", value, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdIn(List<String> values) {
            addCriterion("counterparty_id in", values, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdNotIn(List<String> values) {
            addCriterion("counterparty_id not in", values, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdBetween(String value1, String value2) {
            addCriterion("counterparty_id between", value1, value2, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIdNotBetween(String value1, String value2) {
            addCriterion("counterparty_id not between", value1, value2, "counterpartyId");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIsNull() {
            addCriterion("counterparty is null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIsNotNull() {
            addCriterion("counterparty is not null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyEqualTo(String value) {
            addCriterion("counterparty =", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyNotEqualTo(String value) {
            addCriterion("counterparty <>", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyGreaterThan(String value) {
            addCriterion("counterparty >", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyGreaterThanOrEqualTo(String value) {
            addCriterion("counterparty >=", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLessThan(String value) {
            addCriterion("counterparty <", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLessThanOrEqualTo(String value) {
            addCriterion("counterparty <=", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyLike(String value) {
            addCriterion("counterparty like", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyNotLike(String value) {
            addCriterion("counterparty not like", value, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyIn(List<String> values) {
            addCriterion("counterparty in", values, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyNotIn(List<String> values) {
            addCriterion("counterparty not in", values, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyBetween(String value1, String value2) {
            addCriterion("counterparty between", value1, value2, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyNotBetween(String value1, String value2) {
            addCriterion("counterparty not between", value1, value2, "counterparty");
            return (Criteria) this;
        }

        public Criteria andCounterpartyTypeIsNull() {
            addCriterion("counterparty_type is null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyTypeIsNotNull() {
            addCriterion("counterparty_type is not null");
            return (Criteria) this;
        }

        public Criteria andCounterpartyTypeEqualTo(String value) {
            addCriterion("counterparty_type =", value, "counterpartyType");
            return (Criteria) this;
        }

        public Criteria andCounterpartyTypeNotEqualTo(String value) {
            addCriterion("counterparty_type <>", value, "counterpartyType");
            return (Criteria) this;
        }

        public Criteria andCounterpartyTypeGreaterThan(String value) {
            addCriterion("counterparty_type >", value, "counterpartyType");
            return (Criteria) this;
        }

        public Criteria andCounterpartyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("counterparty_type >=", value, "counterpartyType");
            return (Criteria) this;
        }

        public Criteria andCounterpartyTypeLessThan(String value) {
            addCriterion("counterparty_type <", value, "counterpartyType");
            return (Criteria) this;
        }

        public Criteria andCounterpartyTypeLessThanOrEqualTo(String value) {
            addCriterion("counterparty_type <=", value, "counterpartyType");
            return (Criteria) this;
        }

        public Criteria andCounterpartyTypeLike(String value) {
            addCriterion("counterparty_type like", value, "counterpartyType");
            return (Criteria) this;
        }

        public Criteria andCounterpartyTypeNotLike(String value) {
            addCriterion("counterparty_type not like", value, "counterpartyType");
            return (Criteria) this;
        }

        public Criteria andCounterpartyTypeIn(List<String> values) {
            addCriterion("counterparty_type in", values, "counterpartyType");
            return (Criteria) this;
        }

        public Criteria andCounterpartyTypeNotIn(List<String> values) {
            addCriterion("counterparty_type not in", values, "counterpartyType");
            return (Criteria) this;
        }

        public Criteria andCounterpartyTypeBetween(String value1, String value2) {
            addCriterion("counterparty_type between", value1, value2, "counterpartyType");
            return (Criteria) this;
        }

        public Criteria andCounterpartyTypeNotBetween(String value1, String value2) {
            addCriterion("counterparty_type not between", value1, value2, "counterpartyType");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioIsNull() {
            addCriterion("financing_ratio is null");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioIsNotNull() {
            addCriterion("financing_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioEqualTo(BigDecimal value) {
            addCriterion("financing_ratio =", value, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioNotEqualTo(BigDecimal value) {
            addCriterion("financing_ratio <>", value, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioGreaterThan(BigDecimal value) {
            addCriterion("financing_ratio >", value, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("financing_ratio >=", value, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioLessThan(BigDecimal value) {
            addCriterion("financing_ratio <", value, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("financing_ratio <=", value, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioIn(List<BigDecimal> values) {
            addCriterion("financing_ratio in", values, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioNotIn(List<BigDecimal> values) {
            addCriterion("financing_ratio not in", values, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("financing_ratio between", value1, value2, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFinancingRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("financing_ratio not between", value1, value2, "financingRatio");
            return (Criteria) this;
        }

        public Criteria andFactoringTypeIsNull() {
            addCriterion("factoring_type is null");
            return (Criteria) this;
        }

        public Criteria andFactoringTypeIsNotNull() {
            addCriterion("factoring_type is not null");
            return (Criteria) this;
        }

        public Criteria andFactoringTypeEqualTo(String value) {
            addCriterion("factoring_type =", value, "factoringType");
            return (Criteria) this;
        }

        public Criteria andFactoringTypeNotEqualTo(String value) {
            addCriterion("factoring_type <>", value, "factoringType");
            return (Criteria) this;
        }

        public Criteria andFactoringTypeGreaterThan(String value) {
            addCriterion("factoring_type >", value, "factoringType");
            return (Criteria) this;
        }

        public Criteria andFactoringTypeGreaterThanOrEqualTo(String value) {
            addCriterion("factoring_type >=", value, "factoringType");
            return (Criteria) this;
        }

        public Criteria andFactoringTypeLessThan(String value) {
            addCriterion("factoring_type <", value, "factoringType");
            return (Criteria) this;
        }

        public Criteria andFactoringTypeLessThanOrEqualTo(String value) {
            addCriterion("factoring_type <=", value, "factoringType");
            return (Criteria) this;
        }

        public Criteria andFactoringTypeLike(String value) {
            addCriterion("factoring_type like", value, "factoringType");
            return (Criteria) this;
        }

        public Criteria andFactoringTypeNotLike(String value) {
            addCriterion("factoring_type not like", value, "factoringType");
            return (Criteria) this;
        }

        public Criteria andFactoringTypeIn(List<String> values) {
            addCriterion("factoring_type in", values, "factoringType");
            return (Criteria) this;
        }

        public Criteria andFactoringTypeNotIn(List<String> values) {
            addCriterion("factoring_type not in", values, "factoringType");
            return (Criteria) this;
        }

        public Criteria andFactoringTypeBetween(String value1, String value2) {
            addCriterion("factoring_type between", value1, value2, "factoringType");
            return (Criteria) this;
        }

        public Criteria andFactoringTypeNotBetween(String value1, String value2) {
            addCriterion("factoring_type not between", value1, value2, "factoringType");
            return (Criteria) this;
        }

        public Criteria andQuerySubLimitFlagIsNull() {
            addCriterion("query_sub_limit_flag is null");
            return (Criteria) this;
        }

        public Criteria andQuerySubLimitFlagIsNotNull() {
            addCriterion("query_sub_limit_flag is not null");
            return (Criteria) this;
        }

        public Criteria andQuerySubLimitFlagEqualTo(Boolean value) {
            addCriterion("query_sub_limit_flag =", value, "querySubLimitFlag");
            return (Criteria) this;
        }

        public Criteria andQuerySubLimitFlagNotEqualTo(Boolean value) {
            addCriterion("query_sub_limit_flag <>", value, "querySubLimitFlag");
            return (Criteria) this;
        }

        public Criteria andQuerySubLimitFlagGreaterThan(Boolean value) {
            addCriterion("query_sub_limit_flag >", value, "querySubLimitFlag");
            return (Criteria) this;
        }

        public Criteria andQuerySubLimitFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("query_sub_limit_flag >=", value, "querySubLimitFlag");
            return (Criteria) this;
        }

        public Criteria andQuerySubLimitFlagLessThan(Boolean value) {
            addCriterion("query_sub_limit_flag <", value, "querySubLimitFlag");
            return (Criteria) this;
        }

        public Criteria andQuerySubLimitFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("query_sub_limit_flag <=", value, "querySubLimitFlag");
            return (Criteria) this;
        }

        public Criteria andQuerySubLimitFlagIn(List<Boolean> values) {
            addCriterion("query_sub_limit_flag in", values, "querySubLimitFlag");
            return (Criteria) this;
        }

        public Criteria andQuerySubLimitFlagNotIn(List<Boolean> values) {
            addCriterion("query_sub_limit_flag not in", values, "querySubLimitFlag");
            return (Criteria) this;
        }

        public Criteria andQuerySubLimitFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("query_sub_limit_flag between", value1, value2, "querySubLimitFlag");
            return (Criteria) this;
        }

        public Criteria andQuerySubLimitFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("query_sub_limit_flag not between", value1, value2, "querySubLimitFlag");
            return (Criteria) this;
        }

        public Criteria andSubLimitIsNull() {
            addCriterion("sub_limit is null");
            return (Criteria) this;
        }

        public Criteria andSubLimitIsNotNull() {
            addCriterion("sub_limit is not null");
            return (Criteria) this;
        }

        public Criteria andSubLimitEqualTo(Long value) {
            addCriterion("sub_limit =", value, "subLimit");
            return (Criteria) this;
        }

        public Criteria andSubLimitNotEqualTo(Long value) {
            addCriterion("sub_limit <>", value, "subLimit");
            return (Criteria) this;
        }

        public Criteria andSubLimitGreaterThan(Long value) {
            addCriterion("sub_limit >", value, "subLimit");
            return (Criteria) this;
        }

        public Criteria andSubLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("sub_limit >=", value, "subLimit");
            return (Criteria) this;
        }

        public Criteria andSubLimitLessThan(Long value) {
            addCriterion("sub_limit <", value, "subLimit");
            return (Criteria) this;
        }

        public Criteria andSubLimitLessThanOrEqualTo(Long value) {
            addCriterion("sub_limit <=", value, "subLimit");
            return (Criteria) this;
        }

        public Criteria andSubLimitIn(List<Long> values) {
            addCriterion("sub_limit in", values, "subLimit");
            return (Criteria) this;
        }

        public Criteria andSubLimitNotIn(List<Long> values) {
            addCriterion("sub_limit not in", values, "subLimit");
            return (Criteria) this;
        }

        public Criteria andSubLimitBetween(Long value1, Long value2) {
            addCriterion("sub_limit between", value1, value2, "subLimit");
            return (Criteria) this;
        }

        public Criteria andSubLimitNotBetween(Long value1, Long value2) {
            addCriterion("sub_limit not between", value1, value2, "subLimit");
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