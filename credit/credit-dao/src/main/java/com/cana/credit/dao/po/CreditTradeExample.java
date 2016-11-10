package com.cana.credit.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditTradeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public CreditTradeExample() {
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

        public Criteria andOutTradeNoIsNull() {
            addCriterion("out_trade_no is null");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoIsNotNull() {
            addCriterion("out_trade_no is not null");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoEqualTo(String value) {
            addCriterion("out_trade_no =", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoNotEqualTo(String value) {
            addCriterion("out_trade_no <>", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoGreaterThan(String value) {
            addCriterion("out_trade_no >", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoGreaterThanOrEqualTo(String value) {
            addCriterion("out_trade_no >=", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoLessThan(String value) {
            addCriterion("out_trade_no <", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoLessThanOrEqualTo(String value) {
            addCriterion("out_trade_no <=", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoLike(String value) {
            addCriterion("out_trade_no like", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoNotLike(String value) {
            addCriterion("out_trade_no not like", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoIn(List<String> values) {
            addCriterion("out_trade_no in", values, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoNotIn(List<String> values) {
            addCriterion("out_trade_no not in", values, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoBetween(String value1, String value2) {
            addCriterion("out_trade_no between", value1, value2, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoNotBetween(String value1, String value2) {
            addCriterion("out_trade_no not between", value1, value2, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdIsNull() {
            addCriterion("out_customer_id is null");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdIsNotNull() {
            addCriterion("out_customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdEqualTo(String value) {
            addCriterion("out_customer_id =", value, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdNotEqualTo(String value) {
            addCriterion("out_customer_id <>", value, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdGreaterThan(String value) {
            addCriterion("out_customer_id >", value, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdGreaterThanOrEqualTo(String value) {
            addCriterion("out_customer_id >=", value, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdLessThan(String value) {
            addCriterion("out_customer_id <", value, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdLessThanOrEqualTo(String value) {
            addCriterion("out_customer_id <=", value, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdLike(String value) {
            addCriterion("out_customer_id like", value, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdNotLike(String value) {
            addCriterion("out_customer_id not like", value, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdIn(List<String> values) {
            addCriterion("out_customer_id in", values, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdNotIn(List<String> values) {
            addCriterion("out_customer_id not in", values, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdBetween(String value1, String value2) {
            addCriterion("out_customer_id between", value1, value2, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutCustomerIdNotBetween(String value1, String value2) {
            addCriterion("out_customer_id not between", value1, value2, "outCustomerId");
            return (Criteria) this;
        }

        public Criteria andOutTradeTimeIsNull() {
            addCriterion("out_trade_time is null");
            return (Criteria) this;
        }

        public Criteria andOutTradeTimeIsNotNull() {
            addCriterion("out_trade_time is not null");
            return (Criteria) this;
        }

        public Criteria andOutTradeTimeEqualTo(Date value) {
            addCriterion("out_trade_time =", value, "outTradeTime");
            return (Criteria) this;
        }

        public Criteria andOutTradeTimeNotEqualTo(Date value) {
            addCriterion("out_trade_time <>", value, "outTradeTime");
            return (Criteria) this;
        }

        public Criteria andOutTradeTimeGreaterThan(Date value) {
            addCriterion("out_trade_time >", value, "outTradeTime");
            return (Criteria) this;
        }

        public Criteria andOutTradeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("out_trade_time >=", value, "outTradeTime");
            return (Criteria) this;
        }

        public Criteria andOutTradeTimeLessThan(Date value) {
            addCriterion("out_trade_time <", value, "outTradeTime");
            return (Criteria) this;
        }

        public Criteria andOutTradeTimeLessThanOrEqualTo(Date value) {
            addCriterion("out_trade_time <=", value, "outTradeTime");
            return (Criteria) this;
        }

        public Criteria andOutTradeTimeIn(List<Date> values) {
            addCriterion("out_trade_time in", values, "outTradeTime");
            return (Criteria) this;
        }

        public Criteria andOutTradeTimeNotIn(List<Date> values) {
            addCriterion("out_trade_time not in", values, "outTradeTime");
            return (Criteria) this;
        }

        public Criteria andOutTradeTimeBetween(Date value1, Date value2) {
            addCriterion("out_trade_time between", value1, value2, "outTradeTime");
            return (Criteria) this;
        }

        public Criteria andOutTradeTimeNotBetween(Date value1, Date value2) {
            addCriterion("out_trade_time not between", value1, value2, "outTradeTime");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameIsNull() {
            addCriterion("out_customer_name is null");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameIsNotNull() {
            addCriterion("out_customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameEqualTo(String value) {
            addCriterion("out_customer_name =", value, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameNotEqualTo(String value) {
            addCriterion("out_customer_name <>", value, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameGreaterThan(String value) {
            addCriterion("out_customer_name >", value, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("out_customer_name >=", value, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameLessThan(String value) {
            addCriterion("out_customer_name <", value, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("out_customer_name <=", value, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameLike(String value) {
            addCriterion("out_customer_name like", value, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameNotLike(String value) {
            addCriterion("out_customer_name not like", value, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameIn(List<String> values) {
            addCriterion("out_customer_name in", values, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameNotIn(List<String> values) {
            addCriterion("out_customer_name not in", values, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameBetween(String value1, String value2) {
            addCriterion("out_customer_name between", value1, value2, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutCustomerNameNotBetween(String value1, String value2) {
            addCriterion("out_customer_name not between", value1, value2, "outCustomerName");
            return (Criteria) this;
        }

        public Criteria andOutOriginTradeNoIsNull() {
            addCriterion("out_origin_trade_no is null");
            return (Criteria) this;
        }

        public Criteria andOutOriginTradeNoIsNotNull() {
            addCriterion("out_origin_trade_no is not null");
            return (Criteria) this;
        }

        public Criteria andOutOriginTradeNoEqualTo(String value) {
            addCriterion("out_origin_trade_no =", value, "outOriginTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutOriginTradeNoNotEqualTo(String value) {
            addCriterion("out_origin_trade_no <>", value, "outOriginTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutOriginTradeNoGreaterThan(String value) {
            addCriterion("out_origin_trade_no >", value, "outOriginTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutOriginTradeNoGreaterThanOrEqualTo(String value) {
            addCriterion("out_origin_trade_no >=", value, "outOriginTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutOriginTradeNoLessThan(String value) {
            addCriterion("out_origin_trade_no <", value, "outOriginTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutOriginTradeNoLessThanOrEqualTo(String value) {
            addCriterion("out_origin_trade_no <=", value, "outOriginTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutOriginTradeNoLike(String value) {
            addCriterion("out_origin_trade_no like", value, "outOriginTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutOriginTradeNoNotLike(String value) {
            addCriterion("out_origin_trade_no not like", value, "outOriginTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutOriginTradeNoIn(List<String> values) {
            addCriterion("out_origin_trade_no in", values, "outOriginTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutOriginTradeNoNotIn(List<String> values) {
            addCriterion("out_origin_trade_no not in", values, "outOriginTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutOriginTradeNoBetween(String value1, String value2) {
            addCriterion("out_origin_trade_no between", value1, value2, "outOriginTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutOriginTradeNoNotBetween(String value1, String value2) {
            addCriterion("out_origin_trade_no not between", value1, value2, "outOriginTradeNo");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerIdIsNull() {
            addCriterion("finace_customer_id is null");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerIdIsNotNull() {
            addCriterion("finace_customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerIdEqualTo(String value) {
            addCriterion("finace_customer_id =", value, "finaceCustomerId");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerIdNotEqualTo(String value) {
            addCriterion("finace_customer_id <>", value, "finaceCustomerId");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerIdGreaterThan(String value) {
            addCriterion("finace_customer_id >", value, "finaceCustomerId");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerIdGreaterThanOrEqualTo(String value) {
            addCriterion("finace_customer_id >=", value, "finaceCustomerId");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerIdLessThan(String value) {
            addCriterion("finace_customer_id <", value, "finaceCustomerId");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerIdLessThanOrEqualTo(String value) {
            addCriterion("finace_customer_id <=", value, "finaceCustomerId");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerIdLike(String value) {
            addCriterion("finace_customer_id like", value, "finaceCustomerId");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerIdNotLike(String value) {
            addCriterion("finace_customer_id not like", value, "finaceCustomerId");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerIdIn(List<String> values) {
            addCriterion("finace_customer_id in", values, "finaceCustomerId");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerIdNotIn(List<String> values) {
            addCriterion("finace_customer_id not in", values, "finaceCustomerId");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerIdBetween(String value1, String value2) {
            addCriterion("finace_customer_id between", value1, value2, "finaceCustomerId");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerIdNotBetween(String value1, String value2) {
            addCriterion("finace_customer_id not between", value1, value2, "finaceCustomerId");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerNameIsNull() {
            addCriterion("finace_customer_name is null");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerNameIsNotNull() {
            addCriterion("finace_customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerNameEqualTo(String value) {
            addCriterion("finace_customer_name =", value, "finaceCustomerName");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerNameNotEqualTo(String value) {
            addCriterion("finace_customer_name <>", value, "finaceCustomerName");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerNameGreaterThan(String value) {
            addCriterion("finace_customer_name >", value, "finaceCustomerName");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("finace_customer_name >=", value, "finaceCustomerName");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerNameLessThan(String value) {
            addCriterion("finace_customer_name <", value, "finaceCustomerName");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("finace_customer_name <=", value, "finaceCustomerName");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerNameLike(String value) {
            addCriterion("finace_customer_name like", value, "finaceCustomerName");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerNameNotLike(String value) {
            addCriterion("finace_customer_name not like", value, "finaceCustomerName");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerNameIn(List<String> values) {
            addCriterion("finace_customer_name in", values, "finaceCustomerName");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerNameNotIn(List<String> values) {
            addCriterion("finace_customer_name not in", values, "finaceCustomerName");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerNameBetween(String value1, String value2) {
            addCriterion("finace_customer_name between", value1, value2, "finaceCustomerName");
            return (Criteria) this;
        }

        public Criteria andFinaceCustomerNameNotBetween(String value1, String value2) {
            addCriterion("finace_customer_name not between", value1, value2, "finaceCustomerName");
            return (Criteria) this;
        }

        public Criteria andInstitutionIsNull() {
            addCriterion("institution is null");
            return (Criteria) this;
        }

        public Criteria andInstitutionIsNotNull() {
            addCriterion("institution is not null");
            return (Criteria) this;
        }

        public Criteria andInstitutionEqualTo(String value) {
            addCriterion("institution =", value, "institution");
            return (Criteria) this;
        }

        public Criteria andInstitutionNotEqualTo(String value) {
            addCriterion("institution <>", value, "institution");
            return (Criteria) this;
        }

        public Criteria andInstitutionGreaterThan(String value) {
            addCriterion("institution >", value, "institution");
            return (Criteria) this;
        }

        public Criteria andInstitutionGreaterThanOrEqualTo(String value) {
            addCriterion("institution >=", value, "institution");
            return (Criteria) this;
        }

        public Criteria andInstitutionLessThan(String value) {
            addCriterion("institution <", value, "institution");
            return (Criteria) this;
        }

        public Criteria andInstitutionLessThanOrEqualTo(String value) {
            addCriterion("institution <=", value, "institution");
            return (Criteria) this;
        }

        public Criteria andInstitutionLike(String value) {
            addCriterion("institution like", value, "institution");
            return (Criteria) this;
        }

        public Criteria andInstitutionNotLike(String value) {
            addCriterion("institution not like", value, "institution");
            return (Criteria) this;
        }

        public Criteria andInstitutionIn(List<String> values) {
            addCriterion("institution in", values, "institution");
            return (Criteria) this;
        }

        public Criteria andInstitutionNotIn(List<String> values) {
            addCriterion("institution not in", values, "institution");
            return (Criteria) this;
        }

        public Criteria andInstitutionBetween(String value1, String value2) {
            addCriterion("institution between", value1, value2, "institution");
            return (Criteria) this;
        }

        public Criteria andInstitutionNotBetween(String value1, String value2) {
            addCriterion("institution not between", value1, value2, "institution");
            return (Criteria) this;
        }

        public Criteria andSignIsNull() {
            addCriterion("sign is null");
            return (Criteria) this;
        }

        public Criteria andSignIsNotNull() {
            addCriterion("sign is not null");
            return (Criteria) this;
        }

        public Criteria andSignEqualTo(String value) {
            addCriterion("sign =", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotEqualTo(String value) {
            addCriterion("sign <>", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignGreaterThan(String value) {
            addCriterion("sign >", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignGreaterThanOrEqualTo(String value) {
            addCriterion("sign >=", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignLessThan(String value) {
            addCriterion("sign <", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignLessThanOrEqualTo(String value) {
            addCriterion("sign <=", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignLike(String value) {
            addCriterion("sign like", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotLike(String value) {
            addCriterion("sign not like", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignIn(List<String> values) {
            addCriterion("sign in", values, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotIn(List<String> values) {
            addCriterion("sign not in", values, "sign");
            return (Criteria) this;
        }

        public Criteria andSignBetween(String value1, String value2) {
            addCriterion("sign between", value1, value2, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotBetween(String value1, String value2) {
            addCriterion("sign not between", value1, value2, "sign");
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

        public Criteria andOrderInfoIsNull() {
            addCriterion("order_info is null");
            return (Criteria) this;
        }

        public Criteria andOrderInfoIsNotNull() {
            addCriterion("order_info is not null");
            return (Criteria) this;
        }

        public Criteria andOrderInfoEqualTo(String value) {
            addCriterion("order_info =", value, "orderInfo");
            return (Criteria) this;
        }

        public Criteria andOrderInfoNotEqualTo(String value) {
            addCriterion("order_info <>", value, "orderInfo");
            return (Criteria) this;
        }

        public Criteria andOrderInfoGreaterThan(String value) {
            addCriterion("order_info >", value, "orderInfo");
            return (Criteria) this;
        }

        public Criteria andOrderInfoGreaterThanOrEqualTo(String value) {
            addCriterion("order_info >=", value, "orderInfo");
            return (Criteria) this;
        }

        public Criteria andOrderInfoLessThan(String value) {
            addCriterion("order_info <", value, "orderInfo");
            return (Criteria) this;
        }

        public Criteria andOrderInfoLessThanOrEqualTo(String value) {
            addCriterion("order_info <=", value, "orderInfo");
            return (Criteria) this;
        }

        public Criteria andOrderInfoLike(String value) {
            addCriterion("order_info like", value, "orderInfo");
            return (Criteria) this;
        }

        public Criteria andOrderInfoNotLike(String value) {
            addCriterion("order_info not like", value, "orderInfo");
            return (Criteria) this;
        }

        public Criteria andOrderInfoIn(List<String> values) {
            addCriterion("order_info in", values, "orderInfo");
            return (Criteria) this;
        }

        public Criteria andOrderInfoNotIn(List<String> values) {
            addCriterion("order_info not in", values, "orderInfo");
            return (Criteria) this;
        }

        public Criteria andOrderInfoBetween(String value1, String value2) {
            addCriterion("order_info between", value1, value2, "orderInfo");
            return (Criteria) this;
        }

        public Criteria andOrderInfoNotBetween(String value1, String value2) {
            addCriterion("order_info not between", value1, value2, "orderInfo");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlIsNull() {
            addCriterion("notify_url is null");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlIsNotNull() {
            addCriterion("notify_url is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlEqualTo(String value) {
            addCriterion("notify_url =", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlNotEqualTo(String value) {
            addCriterion("notify_url <>", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlGreaterThan(String value) {
            addCriterion("notify_url >", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlGreaterThanOrEqualTo(String value) {
            addCriterion("notify_url >=", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlLessThan(String value) {
            addCriterion("notify_url <", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlLessThanOrEqualTo(String value) {
            addCriterion("notify_url <=", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlLike(String value) {
            addCriterion("notify_url like", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlNotLike(String value) {
            addCriterion("notify_url not like", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlIn(List<String> values) {
            addCriterion("notify_url in", values, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlNotIn(List<String> values) {
            addCriterion("notify_url not in", values, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlBetween(String value1, String value2) {
            addCriterion("notify_url between", value1, value2, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlNotBetween(String value1, String value2) {
            addCriterion("notify_url not between", value1, value2, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIsNull() {
            addCriterion("trade_type is null");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIsNotNull() {
            addCriterion("trade_type is not null");
            return (Criteria) this;
        }

        public Criteria andTradeTypeEqualTo(String value) {
            addCriterion("trade_type =", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotEqualTo(String value) {
            addCriterion("trade_type <>", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeGreaterThan(String value) {
            addCriterion("trade_type >", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("trade_type >=", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLessThan(String value) {
            addCriterion("trade_type <", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLessThanOrEqualTo(String value) {
            addCriterion("trade_type <=", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLike(String value) {
            addCriterion("trade_type like", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotLike(String value) {
            addCriterion("trade_type not like", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIn(List<String> values) {
            addCriterion("trade_type in", values, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotIn(List<String> values) {
            addCriterion("trade_type not in", values, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeBetween(String value1, String value2) {
            addCriterion("trade_type between", value1, value2, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotBetween(String value1, String value2) {
            addCriterion("trade_type not between", value1, value2, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeStatusIsNull() {
            addCriterion("trade_status is null");
            return (Criteria) this;
        }

        public Criteria andTradeStatusIsNotNull() {
            addCriterion("trade_status is not null");
            return (Criteria) this;
        }

        public Criteria andTradeStatusEqualTo(String value) {
            addCriterion("trade_status =", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNotEqualTo(String value) {
            addCriterion("trade_status <>", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusGreaterThan(String value) {
            addCriterion("trade_status >", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusGreaterThanOrEqualTo(String value) {
            addCriterion("trade_status >=", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusLessThan(String value) {
            addCriterion("trade_status <", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusLessThanOrEqualTo(String value) {
            addCriterion("trade_status <=", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusLike(String value) {
            addCriterion("trade_status like", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNotLike(String value) {
            addCriterion("trade_status not like", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusIn(List<String> values) {
            addCriterion("trade_status in", values, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNotIn(List<String> values) {
            addCriterion("trade_status not in", values, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusBetween(String value1, String value2) {
            addCriterion("trade_status between", value1, value2, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNotBetween(String value1, String value2) {
            addCriterion("trade_status not between", value1, value2, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andSummaryIdIsNull() {
            addCriterion("summary_id is null");
            return (Criteria) this;
        }

        public Criteria andSummaryIdIsNotNull() {
            addCriterion("summary_id is not null");
            return (Criteria) this;
        }

        public Criteria andSummaryIdEqualTo(String value) {
            addCriterion("summary_id =", value, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdNotEqualTo(String value) {
            addCriterion("summary_id <>", value, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdGreaterThan(String value) {
            addCriterion("summary_id >", value, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdGreaterThanOrEqualTo(String value) {
            addCriterion("summary_id >=", value, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdLessThan(String value) {
            addCriterion("summary_id <", value, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdLessThanOrEqualTo(String value) {
            addCriterion("summary_id <=", value, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdLike(String value) {
            addCriterion("summary_id like", value, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdNotLike(String value) {
            addCriterion("summary_id not like", value, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdIn(List<String> values) {
            addCriterion("summary_id in", values, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdNotIn(List<String> values) {
            addCriterion("summary_id not in", values, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdBetween(String value1, String value2) {
            addCriterion("summary_id between", value1, value2, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdNotBetween(String value1, String value2) {
            addCriterion("summary_id not between", value1, value2, "summaryId");
            return (Criteria) this;
        }

        public Criteria andTradeStartTimeIsNull() {
            addCriterion("trade_start_time is null");
            return (Criteria) this;
        }

        public Criteria andTradeStartTimeIsNotNull() {
            addCriterion("trade_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andTradeStartTimeEqualTo(Date value) {
            addCriterion("trade_start_time =", value, "tradeStartTime");
            return (Criteria) this;
        }

        public Criteria andTradeStartTimeNotEqualTo(Date value) {
            addCriterion("trade_start_time <>", value, "tradeStartTime");
            return (Criteria) this;
        }

        public Criteria andTradeStartTimeGreaterThan(Date value) {
            addCriterion("trade_start_time >", value, "tradeStartTime");
            return (Criteria) this;
        }

        public Criteria andTradeStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("trade_start_time >=", value, "tradeStartTime");
            return (Criteria) this;
        }

        public Criteria andTradeStartTimeLessThan(Date value) {
            addCriterion("trade_start_time <", value, "tradeStartTime");
            return (Criteria) this;
        }

        public Criteria andTradeStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("trade_start_time <=", value, "tradeStartTime");
            return (Criteria) this;
        }

        public Criteria andTradeStartTimeIn(List<Date> values) {
            addCriterion("trade_start_time in", values, "tradeStartTime");
            return (Criteria) this;
        }

        public Criteria andTradeStartTimeNotIn(List<Date> values) {
            addCriterion("trade_start_time not in", values, "tradeStartTime");
            return (Criteria) this;
        }

        public Criteria andTradeStartTimeBetween(Date value1, Date value2) {
            addCriterion("trade_start_time between", value1, value2, "tradeStartTime");
            return (Criteria) this;
        }

        public Criteria andTradeStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("trade_start_time not between", value1, value2, "tradeStartTime");
            return (Criteria) this;
        }

        public Criteria andTradeEndTimeIsNull() {
            addCriterion("trade_end_time is null");
            return (Criteria) this;
        }

        public Criteria andTradeEndTimeIsNotNull() {
            addCriterion("trade_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andTradeEndTimeEqualTo(Date value) {
            addCriterion("trade_end_time =", value, "tradeEndTime");
            return (Criteria) this;
        }

        public Criteria andTradeEndTimeNotEqualTo(Date value) {
            addCriterion("trade_end_time <>", value, "tradeEndTime");
            return (Criteria) this;
        }

        public Criteria andTradeEndTimeGreaterThan(Date value) {
            addCriterion("trade_end_time >", value, "tradeEndTime");
            return (Criteria) this;
        }

        public Criteria andTradeEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("trade_end_time >=", value, "tradeEndTime");
            return (Criteria) this;
        }

        public Criteria andTradeEndTimeLessThan(Date value) {
            addCriterion("trade_end_time <", value, "tradeEndTime");
            return (Criteria) this;
        }

        public Criteria andTradeEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("trade_end_time <=", value, "tradeEndTime");
            return (Criteria) this;
        }

        public Criteria andTradeEndTimeIn(List<Date> values) {
            addCriterion("trade_end_time in", values, "tradeEndTime");
            return (Criteria) this;
        }

        public Criteria andTradeEndTimeNotIn(List<Date> values) {
            addCriterion("trade_end_time not in", values, "tradeEndTime");
            return (Criteria) this;
        }

        public Criteria andTradeEndTimeBetween(Date value1, Date value2) {
            addCriterion("trade_end_time between", value1, value2, "tradeEndTime");
            return (Criteria) this;
        }

        public Criteria andTradeEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("trade_end_time not between", value1, value2, "tradeEndTime");
            return (Criteria) this;
        }

        public Criteria andOriginTradeEndTimeIsNull() {
            addCriterion("origin_trade_end_time is null");
            return (Criteria) this;
        }

        public Criteria andOriginTradeEndTimeIsNotNull() {
            addCriterion("origin_trade_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andOriginTradeEndTimeEqualTo(Date value) {
            addCriterion("origin_trade_end_time =", value, "originTradeEndTime");
            return (Criteria) this;
        }

        public Criteria andOriginTradeEndTimeNotEqualTo(Date value) {
            addCriterion("origin_trade_end_time <>", value, "originTradeEndTime");
            return (Criteria) this;
        }

        public Criteria andOriginTradeEndTimeGreaterThan(Date value) {
            addCriterion("origin_trade_end_time >", value, "originTradeEndTime");
            return (Criteria) this;
        }

        public Criteria andOriginTradeEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("origin_trade_end_time >=", value, "originTradeEndTime");
            return (Criteria) this;
        }

        public Criteria andOriginTradeEndTimeLessThan(Date value) {
            addCriterion("origin_trade_end_time <", value, "originTradeEndTime");
            return (Criteria) this;
        }

        public Criteria andOriginTradeEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("origin_trade_end_time <=", value, "originTradeEndTime");
            return (Criteria) this;
        }

        public Criteria andOriginTradeEndTimeIn(List<Date> values) {
            addCriterion("origin_trade_end_time in", values, "originTradeEndTime");
            return (Criteria) this;
        }

        public Criteria andOriginTradeEndTimeNotIn(List<Date> values) {
            addCriterion("origin_trade_end_time not in", values, "originTradeEndTime");
            return (Criteria) this;
        }

        public Criteria andOriginTradeEndTimeBetween(Date value1, Date value2) {
            addCriterion("origin_trade_end_time between", value1, value2, "originTradeEndTime");
            return (Criteria) this;
        }

        public Criteria andOriginTradeEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("origin_trade_end_time not between", value1, value2, "originTradeEndTime");
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