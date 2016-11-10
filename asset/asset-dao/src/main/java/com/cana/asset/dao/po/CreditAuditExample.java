package com.cana.asset.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditAuditExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public CreditAuditExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCreditIdIsNull() {
            addCriterion("credit_id is null");
            return (Criteria) this;
        }

        public Criteria andCreditIdIsNotNull() {
            addCriterion("credit_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreditIdEqualTo(String value) {
            addCriterion("credit_id =", value, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdNotEqualTo(String value) {
            addCriterion("credit_id <>", value, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdGreaterThan(String value) {
            addCriterion("credit_id >", value, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdGreaterThanOrEqualTo(String value) {
            addCriterion("credit_id >=", value, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdLessThan(String value) {
            addCriterion("credit_id <", value, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdLessThanOrEqualTo(String value) {
            addCriterion("credit_id <=", value, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdLike(String value) {
            addCriterion("credit_id like", value, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdNotLike(String value) {
            addCriterion("credit_id not like", value, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdIn(List<String> values) {
            addCriterion("credit_id in", values, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdNotIn(List<String> values) {
            addCriterion("credit_id not in", values, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdBetween(String value1, String value2) {
            addCriterion("credit_id between", value1, value2, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditIdNotBetween(String value1, String value2) {
            addCriterion("credit_id not between", value1, value2, "creditId");
            return (Criteria) this;
        }

        public Criteria andCreditModeIsNull() {
            addCriterion("credit_mode is null");
            return (Criteria) this;
        }

        public Criteria andCreditModeIsNotNull() {
            addCriterion("credit_mode is not null");
            return (Criteria) this;
        }

        public Criteria andCreditModeEqualTo(String value) {
            addCriterion("credit_mode =", value, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeNotEqualTo(String value) {
            addCriterion("credit_mode <>", value, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeGreaterThan(String value) {
            addCriterion("credit_mode >", value, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeGreaterThanOrEqualTo(String value) {
            addCriterion("credit_mode >=", value, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeLessThan(String value) {
            addCriterion("credit_mode <", value, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeLessThanOrEqualTo(String value) {
            addCriterion("credit_mode <=", value, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeLike(String value) {
            addCriterion("credit_mode like", value, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeNotLike(String value) {
            addCriterion("credit_mode not like", value, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeIn(List<String> values) {
            addCriterion("credit_mode in", values, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeNotIn(List<String> values) {
            addCriterion("credit_mode not in", values, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeBetween(String value1, String value2) {
            addCriterion("credit_mode between", value1, value2, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCreditModeNotBetween(String value1, String value2) {
            addCriterion("credit_mode not between", value1, value2, "creditMode");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andPreTotalLimitIsNull() {
            addCriterion("pre_total_limit is null");
            return (Criteria) this;
        }

        public Criteria andPreTotalLimitIsNotNull() {
            addCriterion("pre_total_limit is not null");
            return (Criteria) this;
        }

        public Criteria andPreTotalLimitEqualTo(Long value) {
            addCriterion("pre_total_limit =", value, "preTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPreTotalLimitNotEqualTo(Long value) {
            addCriterion("pre_total_limit <>", value, "preTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPreTotalLimitGreaterThan(Long value) {
            addCriterion("pre_total_limit >", value, "preTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPreTotalLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("pre_total_limit >=", value, "preTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPreTotalLimitLessThan(Long value) {
            addCriterion("pre_total_limit <", value, "preTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPreTotalLimitLessThanOrEqualTo(Long value) {
            addCriterion("pre_total_limit <=", value, "preTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPreTotalLimitIn(List<Long> values) {
            addCriterion("pre_total_limit in", values, "preTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPreTotalLimitNotIn(List<Long> values) {
            addCriterion("pre_total_limit not in", values, "preTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPreTotalLimitBetween(Long value1, Long value2) {
            addCriterion("pre_total_limit between", value1, value2, "preTotalLimit");
            return (Criteria) this;
        }

        public Criteria andPreTotalLimitNotBetween(Long value1, Long value2) {
            addCriterion("pre_total_limit not between", value1, value2, "preTotalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitIsNull() {
            addCriterion("total_limit is null");
            return (Criteria) this;
        }

        public Criteria andTotalLimitIsNotNull() {
            addCriterion("total_limit is not null");
            return (Criteria) this;
        }

        public Criteria andTotalLimitEqualTo(Long value) {
            addCriterion("total_limit =", value, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitNotEqualTo(Long value) {
            addCriterion("total_limit <>", value, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitGreaterThan(Long value) {
            addCriterion("total_limit >", value, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("total_limit >=", value, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitLessThan(Long value) {
            addCriterion("total_limit <", value, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitLessThanOrEqualTo(Long value) {
            addCriterion("total_limit <=", value, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitIn(List<Long> values) {
            addCriterion("total_limit in", values, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitNotIn(List<Long> values) {
            addCriterion("total_limit not in", values, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitBetween(Long value1, Long value2) {
            addCriterion("total_limit between", value1, value2, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andTotalLimitNotBetween(Long value1, Long value2) {
            addCriterion("total_limit not between", value1, value2, "totalLimit");
            return (Criteria) this;
        }

        public Criteria andPreAvailableLimitIsNull() {
            addCriterion("pre_available_limit is null");
            return (Criteria) this;
        }

        public Criteria andPreAvailableLimitIsNotNull() {
            addCriterion("pre_available_limit is not null");
            return (Criteria) this;
        }

        public Criteria andPreAvailableLimitEqualTo(Long value) {
            addCriterion("pre_available_limit =", value, "preAvailableLimit");
            return (Criteria) this;
        }

        public Criteria andPreAvailableLimitNotEqualTo(Long value) {
            addCriterion("pre_available_limit <>", value, "preAvailableLimit");
            return (Criteria) this;
        }

        public Criteria andPreAvailableLimitGreaterThan(Long value) {
            addCriterion("pre_available_limit >", value, "preAvailableLimit");
            return (Criteria) this;
        }

        public Criteria andPreAvailableLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("pre_available_limit >=", value, "preAvailableLimit");
            return (Criteria) this;
        }

        public Criteria andPreAvailableLimitLessThan(Long value) {
            addCriterion("pre_available_limit <", value, "preAvailableLimit");
            return (Criteria) this;
        }

        public Criteria andPreAvailableLimitLessThanOrEqualTo(Long value) {
            addCriterion("pre_available_limit <=", value, "preAvailableLimit");
            return (Criteria) this;
        }

        public Criteria andPreAvailableLimitIn(List<Long> values) {
            addCriterion("pre_available_limit in", values, "preAvailableLimit");
            return (Criteria) this;
        }

        public Criteria andPreAvailableLimitNotIn(List<Long> values) {
            addCriterion("pre_available_limit not in", values, "preAvailableLimit");
            return (Criteria) this;
        }

        public Criteria andPreAvailableLimitBetween(Long value1, Long value2) {
            addCriterion("pre_available_limit between", value1, value2, "preAvailableLimit");
            return (Criteria) this;
        }

        public Criteria andPreAvailableLimitNotBetween(Long value1, Long value2) {
            addCriterion("pre_available_limit not between", value1, value2, "preAvailableLimit");
            return (Criteria) this;
        }

        public Criteria andAvailableLimitIsNull() {
            addCriterion("available_limit is null");
            return (Criteria) this;
        }

        public Criteria andAvailableLimitIsNotNull() {
            addCriterion("available_limit is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableLimitEqualTo(Long value) {
            addCriterion("available_limit =", value, "availableLimit");
            return (Criteria) this;
        }

        public Criteria andAvailableLimitNotEqualTo(Long value) {
            addCriterion("available_limit <>", value, "availableLimit");
            return (Criteria) this;
        }

        public Criteria andAvailableLimitGreaterThan(Long value) {
            addCriterion("available_limit >", value, "availableLimit");
            return (Criteria) this;
        }

        public Criteria andAvailableLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("available_limit >=", value, "availableLimit");
            return (Criteria) this;
        }

        public Criteria andAvailableLimitLessThan(Long value) {
            addCriterion("available_limit <", value, "availableLimit");
            return (Criteria) this;
        }

        public Criteria andAvailableLimitLessThanOrEqualTo(Long value) {
            addCriterion("available_limit <=", value, "availableLimit");
            return (Criteria) this;
        }

        public Criteria andAvailableLimitIn(List<Long> values) {
            addCriterion("available_limit in", values, "availableLimit");
            return (Criteria) this;
        }

        public Criteria andAvailableLimitNotIn(List<Long> values) {
            addCriterion("available_limit not in", values, "availableLimit");
            return (Criteria) this;
        }

        public Criteria andAvailableLimitBetween(Long value1, Long value2) {
            addCriterion("available_limit between", value1, value2, "availableLimit");
            return (Criteria) this;
        }

        public Criteria andAvailableLimitNotBetween(Long value1, Long value2) {
            addCriterion("available_limit not between", value1, value2, "availableLimit");
            return (Criteria) this;
        }

        public Criteria andPreUsedLimitIsNull() {
            addCriterion("pre_used_limit is null");
            return (Criteria) this;
        }

        public Criteria andPreUsedLimitIsNotNull() {
            addCriterion("pre_used_limit is not null");
            return (Criteria) this;
        }

        public Criteria andPreUsedLimitEqualTo(Long value) {
            addCriterion("pre_used_limit =", value, "preUsedLimit");
            return (Criteria) this;
        }

        public Criteria andPreUsedLimitNotEqualTo(Long value) {
            addCriterion("pre_used_limit <>", value, "preUsedLimit");
            return (Criteria) this;
        }

        public Criteria andPreUsedLimitGreaterThan(Long value) {
            addCriterion("pre_used_limit >", value, "preUsedLimit");
            return (Criteria) this;
        }

        public Criteria andPreUsedLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("pre_used_limit >=", value, "preUsedLimit");
            return (Criteria) this;
        }

        public Criteria andPreUsedLimitLessThan(Long value) {
            addCriterion("pre_used_limit <", value, "preUsedLimit");
            return (Criteria) this;
        }

        public Criteria andPreUsedLimitLessThanOrEqualTo(Long value) {
            addCriterion("pre_used_limit <=", value, "preUsedLimit");
            return (Criteria) this;
        }

        public Criteria andPreUsedLimitIn(List<Long> values) {
            addCriterion("pre_used_limit in", values, "preUsedLimit");
            return (Criteria) this;
        }

        public Criteria andPreUsedLimitNotIn(List<Long> values) {
            addCriterion("pre_used_limit not in", values, "preUsedLimit");
            return (Criteria) this;
        }

        public Criteria andPreUsedLimitBetween(Long value1, Long value2) {
            addCriterion("pre_used_limit between", value1, value2, "preUsedLimit");
            return (Criteria) this;
        }

        public Criteria andPreUsedLimitNotBetween(Long value1, Long value2) {
            addCriterion("pre_used_limit not between", value1, value2, "preUsedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitIsNull() {
            addCriterion("used_limit is null");
            return (Criteria) this;
        }

        public Criteria andUsedLimitIsNotNull() {
            addCriterion("used_limit is not null");
            return (Criteria) this;
        }

        public Criteria andUsedLimitEqualTo(Long value) {
            addCriterion("used_limit =", value, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitNotEqualTo(Long value) {
            addCriterion("used_limit <>", value, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitGreaterThan(Long value) {
            addCriterion("used_limit >", value, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("used_limit >=", value, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitLessThan(Long value) {
            addCriterion("used_limit <", value, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitLessThanOrEqualTo(Long value) {
            addCriterion("used_limit <=", value, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitIn(List<Long> values) {
            addCriterion("used_limit in", values, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitNotIn(List<Long> values) {
            addCriterion("used_limit not in", values, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitBetween(Long value1, Long value2) {
            addCriterion("used_limit between", value1, value2, "usedLimit");
            return (Criteria) this;
        }

        public Criteria andUsedLimitNotBetween(Long value1, Long value2) {
            addCriterion("used_limit not between", value1, value2, "usedLimit");
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

        public Criteria andPreEffectiveDateIsNull() {
            addCriterion("pre_effective_date is null");
            return (Criteria) this;
        }

        public Criteria andPreEffectiveDateIsNotNull() {
            addCriterion("pre_effective_date is not null");
            return (Criteria) this;
        }

        public Criteria andPreEffectiveDateEqualTo(String value) {
            addCriterion("pre_effective_date =", value, "preEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPreEffectiveDateNotEqualTo(String value) {
            addCriterion("pre_effective_date <>", value, "preEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPreEffectiveDateGreaterThan(String value) {
            addCriterion("pre_effective_date >", value, "preEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPreEffectiveDateGreaterThanOrEqualTo(String value) {
            addCriterion("pre_effective_date >=", value, "preEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPreEffectiveDateLessThan(String value) {
            addCriterion("pre_effective_date <", value, "preEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPreEffectiveDateLessThanOrEqualTo(String value) {
            addCriterion("pre_effective_date <=", value, "preEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPreEffectiveDateLike(String value) {
            addCriterion("pre_effective_date like", value, "preEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPreEffectiveDateNotLike(String value) {
            addCriterion("pre_effective_date not like", value, "preEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPreEffectiveDateIn(List<String> values) {
            addCriterion("pre_effective_date in", values, "preEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPreEffectiveDateNotIn(List<String> values) {
            addCriterion("pre_effective_date not in", values, "preEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPreEffectiveDateBetween(String value1, String value2) {
            addCriterion("pre_effective_date between", value1, value2, "preEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andPreEffectiveDateNotBetween(String value1, String value2) {
            addCriterion("pre_effective_date not between", value1, value2, "preEffectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateIsNull() {
            addCriterion("effective_date is null");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateIsNotNull() {
            addCriterion("effective_date is not null");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateEqualTo(String value) {
            addCriterion("effective_date =", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateNotEqualTo(String value) {
            addCriterion("effective_date <>", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateGreaterThan(String value) {
            addCriterion("effective_date >", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateGreaterThanOrEqualTo(String value) {
            addCriterion("effective_date >=", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateLessThan(String value) {
            addCriterion("effective_date <", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateLessThanOrEqualTo(String value) {
            addCriterion("effective_date <=", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateLike(String value) {
            addCriterion("effective_date like", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateNotLike(String value) {
            addCriterion("effective_date not like", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateIn(List<String> values) {
            addCriterion("effective_date in", values, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateNotIn(List<String> values) {
            addCriterion("effective_date not in", values, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateBetween(String value1, String value2) {
            addCriterion("effective_date between", value1, value2, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateNotBetween(String value1, String value2) {
            addCriterion("effective_date not between", value1, value2, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andPreDueDateIsNull() {
            addCriterion("pre_due_date is null");
            return (Criteria) this;
        }

        public Criteria andPreDueDateIsNotNull() {
            addCriterion("pre_due_date is not null");
            return (Criteria) this;
        }

        public Criteria andPreDueDateEqualTo(String value) {
            addCriterion("pre_due_date =", value, "preDueDate");
            return (Criteria) this;
        }

        public Criteria andPreDueDateNotEqualTo(String value) {
            addCriterion("pre_due_date <>", value, "preDueDate");
            return (Criteria) this;
        }

        public Criteria andPreDueDateGreaterThan(String value) {
            addCriterion("pre_due_date >", value, "preDueDate");
            return (Criteria) this;
        }

        public Criteria andPreDueDateGreaterThanOrEqualTo(String value) {
            addCriterion("pre_due_date >=", value, "preDueDate");
            return (Criteria) this;
        }

        public Criteria andPreDueDateLessThan(String value) {
            addCriterion("pre_due_date <", value, "preDueDate");
            return (Criteria) this;
        }

        public Criteria andPreDueDateLessThanOrEqualTo(String value) {
            addCriterion("pre_due_date <=", value, "preDueDate");
            return (Criteria) this;
        }

        public Criteria andPreDueDateLike(String value) {
            addCriterion("pre_due_date like", value, "preDueDate");
            return (Criteria) this;
        }

        public Criteria andPreDueDateNotLike(String value) {
            addCriterion("pre_due_date not like", value, "preDueDate");
            return (Criteria) this;
        }

        public Criteria andPreDueDateIn(List<String> values) {
            addCriterion("pre_due_date in", values, "preDueDate");
            return (Criteria) this;
        }

        public Criteria andPreDueDateNotIn(List<String> values) {
            addCriterion("pre_due_date not in", values, "preDueDate");
            return (Criteria) this;
        }

        public Criteria andPreDueDateBetween(String value1, String value2) {
            addCriterion("pre_due_date between", value1, value2, "preDueDate");
            return (Criteria) this;
        }

        public Criteria andPreDueDateNotBetween(String value1, String value2) {
            addCriterion("pre_due_date not between", value1, value2, "preDueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateIsNull() {
            addCriterion("due_date is null");
            return (Criteria) this;
        }

        public Criteria andDueDateIsNotNull() {
            addCriterion("due_date is not null");
            return (Criteria) this;
        }

        public Criteria andDueDateEqualTo(String value) {
            addCriterion("due_date =", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotEqualTo(String value) {
            addCriterion("due_date <>", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateGreaterThan(String value) {
            addCriterion("due_date >", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateGreaterThanOrEqualTo(String value) {
            addCriterion("due_date >=", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateLessThan(String value) {
            addCriterion("due_date <", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateLessThanOrEqualTo(String value) {
            addCriterion("due_date <=", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateLike(String value) {
            addCriterion("due_date like", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotLike(String value) {
            addCriterion("due_date not like", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateIn(List<String> values) {
            addCriterion("due_date in", values, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotIn(List<String> values) {
            addCriterion("due_date not in", values, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateBetween(String value1, String value2) {
            addCriterion("due_date between", value1, value2, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotBetween(String value1, String value2) {
            addCriterion("due_date not between", value1, value2, "dueDate");
            return (Criteria) this;
        }

        public Criteria andPreCreditStatusIsNull() {
            addCriterion("pre_credit_status is null");
            return (Criteria) this;
        }

        public Criteria andPreCreditStatusIsNotNull() {
            addCriterion("pre_credit_status is not null");
            return (Criteria) this;
        }

        public Criteria andPreCreditStatusEqualTo(String value) {
            addCriterion("pre_credit_status =", value, "preCreditStatus");
            return (Criteria) this;
        }

        public Criteria andPreCreditStatusNotEqualTo(String value) {
            addCriterion("pre_credit_status <>", value, "preCreditStatus");
            return (Criteria) this;
        }

        public Criteria andPreCreditStatusGreaterThan(String value) {
            addCriterion("pre_credit_status >", value, "preCreditStatus");
            return (Criteria) this;
        }

        public Criteria andPreCreditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("pre_credit_status >=", value, "preCreditStatus");
            return (Criteria) this;
        }

        public Criteria andPreCreditStatusLessThan(String value) {
            addCriterion("pre_credit_status <", value, "preCreditStatus");
            return (Criteria) this;
        }

        public Criteria andPreCreditStatusLessThanOrEqualTo(String value) {
            addCriterion("pre_credit_status <=", value, "preCreditStatus");
            return (Criteria) this;
        }

        public Criteria andPreCreditStatusLike(String value) {
            addCriterion("pre_credit_status like", value, "preCreditStatus");
            return (Criteria) this;
        }

        public Criteria andPreCreditStatusNotLike(String value) {
            addCriterion("pre_credit_status not like", value, "preCreditStatus");
            return (Criteria) this;
        }

        public Criteria andPreCreditStatusIn(List<String> values) {
            addCriterion("pre_credit_status in", values, "preCreditStatus");
            return (Criteria) this;
        }

        public Criteria andPreCreditStatusNotIn(List<String> values) {
            addCriterion("pre_credit_status not in", values, "preCreditStatus");
            return (Criteria) this;
        }

        public Criteria andPreCreditStatusBetween(String value1, String value2) {
            addCriterion("pre_credit_status between", value1, value2, "preCreditStatus");
            return (Criteria) this;
        }

        public Criteria andPreCreditStatusNotBetween(String value1, String value2) {
            addCriterion("pre_credit_status not between", value1, value2, "preCreditStatus");
            return (Criteria) this;
        }

        public Criteria andCreditStatusIsNull() {
            addCriterion("credit_status is null");
            return (Criteria) this;
        }

        public Criteria andCreditStatusIsNotNull() {
            addCriterion("credit_status is not null");
            return (Criteria) this;
        }

        public Criteria andCreditStatusEqualTo(String value) {
            addCriterion("credit_status =", value, "creditStatus");
            return (Criteria) this;
        }

        public Criteria andCreditStatusNotEqualTo(String value) {
            addCriterion("credit_status <>", value, "creditStatus");
            return (Criteria) this;
        }

        public Criteria andCreditStatusGreaterThan(String value) {
            addCriterion("credit_status >", value, "creditStatus");
            return (Criteria) this;
        }

        public Criteria andCreditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("credit_status >=", value, "creditStatus");
            return (Criteria) this;
        }

        public Criteria andCreditStatusLessThan(String value) {
            addCriterion("credit_status <", value, "creditStatus");
            return (Criteria) this;
        }

        public Criteria andCreditStatusLessThanOrEqualTo(String value) {
            addCriterion("credit_status <=", value, "creditStatus");
            return (Criteria) this;
        }

        public Criteria andCreditStatusLike(String value) {
            addCriterion("credit_status like", value, "creditStatus");
            return (Criteria) this;
        }

        public Criteria andCreditStatusNotLike(String value) {
            addCriterion("credit_status not like", value, "creditStatus");
            return (Criteria) this;
        }

        public Criteria andCreditStatusIn(List<String> values) {
            addCriterion("credit_status in", values, "creditStatus");
            return (Criteria) this;
        }

        public Criteria andCreditStatusNotIn(List<String> values) {
            addCriterion("credit_status not in", values, "creditStatus");
            return (Criteria) this;
        }

        public Criteria andCreditStatusBetween(String value1, String value2) {
            addCriterion("credit_status between", value1, value2, "creditStatus");
            return (Criteria) this;
        }

        public Criteria andCreditStatusNotBetween(String value1, String value2) {
            addCriterion("credit_status not between", value1, value2, "creditStatus");
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

        public Criteria andFactorIdIsNull() {
            addCriterion("factor_id is null");
            return (Criteria) this;
        }

        public Criteria andFactorIdIsNotNull() {
            addCriterion("factor_id is not null");
            return (Criteria) this;
        }

        public Criteria andFactorIdEqualTo(String value) {
            addCriterion("factor_id =", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdNotEqualTo(String value) {
            addCriterion("factor_id <>", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdGreaterThan(String value) {
            addCriterion("factor_id >", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdGreaterThanOrEqualTo(String value) {
            addCriterion("factor_id >=", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdLessThan(String value) {
            addCriterion("factor_id <", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdLessThanOrEqualTo(String value) {
            addCriterion("factor_id <=", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdLike(String value) {
            addCriterion("factor_id like", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdNotLike(String value) {
            addCriterion("factor_id not like", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdIn(List<String> values) {
            addCriterion("factor_id in", values, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdNotIn(List<String> values) {
            addCriterion("factor_id not in", values, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdBetween(String value1, String value2) {
            addCriterion("factor_id between", value1, value2, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdNotBetween(String value1, String value2) {
            addCriterion("factor_id not between", value1, value2, "factorId");
            return (Criteria) this;
        }

        public Criteria andBussinessContractNoIsNull() {
            addCriterion("bussiness_contract_no is null");
            return (Criteria) this;
        }

        public Criteria andBussinessContractNoIsNotNull() {
            addCriterion("bussiness_contract_no is not null");
            return (Criteria) this;
        }

        public Criteria andBussinessContractNoEqualTo(String value) {
            addCriterion("bussiness_contract_no =", value, "bussinessContractNo");
            return (Criteria) this;
        }

        public Criteria andBussinessContractNoNotEqualTo(String value) {
            addCriterion("bussiness_contract_no <>", value, "bussinessContractNo");
            return (Criteria) this;
        }

        public Criteria andBussinessContractNoGreaterThan(String value) {
            addCriterion("bussiness_contract_no >", value, "bussinessContractNo");
            return (Criteria) this;
        }

        public Criteria andBussinessContractNoGreaterThanOrEqualTo(String value) {
            addCriterion("bussiness_contract_no >=", value, "bussinessContractNo");
            return (Criteria) this;
        }

        public Criteria andBussinessContractNoLessThan(String value) {
            addCriterion("bussiness_contract_no <", value, "bussinessContractNo");
            return (Criteria) this;
        }

        public Criteria andBussinessContractNoLessThanOrEqualTo(String value) {
            addCriterion("bussiness_contract_no <=", value, "bussinessContractNo");
            return (Criteria) this;
        }

        public Criteria andBussinessContractNoLike(String value) {
            addCriterion("bussiness_contract_no like", value, "bussinessContractNo");
            return (Criteria) this;
        }

        public Criteria andBussinessContractNoNotLike(String value) {
            addCriterion("bussiness_contract_no not like", value, "bussinessContractNo");
            return (Criteria) this;
        }

        public Criteria andBussinessContractNoIn(List<String> values) {
            addCriterion("bussiness_contract_no in", values, "bussinessContractNo");
            return (Criteria) this;
        }

        public Criteria andBussinessContractNoNotIn(List<String> values) {
            addCriterion("bussiness_contract_no not in", values, "bussinessContractNo");
            return (Criteria) this;
        }

        public Criteria andBussinessContractNoBetween(String value1, String value2) {
            addCriterion("bussiness_contract_no between", value1, value2, "bussinessContractNo");
            return (Criteria) this;
        }

        public Criteria andBussinessContractNoNotBetween(String value1, String value2) {
            addCriterion("bussiness_contract_no not between", value1, value2, "bussinessContractNo");
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