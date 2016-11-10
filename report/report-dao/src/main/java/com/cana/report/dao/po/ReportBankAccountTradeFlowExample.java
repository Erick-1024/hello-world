package com.cana.report.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportBankAccountTradeFlowExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public ReportBankAccountTradeFlowExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andSupervisionStatusIsNull() {
            addCriterion("supervision_status is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusIsNotNull() {
            addCriterion("supervision_status is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusEqualTo(String value) {
            addCriterion("supervision_status =", value, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusNotEqualTo(String value) {
            addCriterion("supervision_status <>", value, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusGreaterThan(String value) {
            addCriterion("supervision_status >", value, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusGreaterThanOrEqualTo(String value) {
            addCriterion("supervision_status >=", value, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusLessThan(String value) {
            addCriterion("supervision_status <", value, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusLessThanOrEqualTo(String value) {
            addCriterion("supervision_status <=", value, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusLike(String value) {
            addCriterion("supervision_status like", value, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusNotLike(String value) {
            addCriterion("supervision_status not like", value, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusIn(List<String> values) {
            addCriterion("supervision_status in", values, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusNotIn(List<String> values) {
            addCriterion("supervision_status not in", values, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusBetween(String value1, String value2) {
            addCriterion("supervision_status between", value1, value2, "supervisionStatus");
            return (Criteria) this;
        }

        public Criteria andSupervisionStatusNotBetween(String value1, String value2) {
            addCriterion("supervision_status not between", value1, value2, "supervisionStatus");
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

        public Criteria andTradeDateIsNull() {
            addCriterion("trade_date is null");
            return (Criteria) this;
        }

        public Criteria andTradeDateIsNotNull() {
            addCriterion("trade_date is not null");
            return (Criteria) this;
        }

        public Criteria andTradeDateEqualTo(String value) {
            addCriterion("trade_date =", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateNotEqualTo(String value) {
            addCriterion("trade_date <>", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateGreaterThan(String value) {
            addCriterion("trade_date >", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateGreaterThanOrEqualTo(String value) {
            addCriterion("trade_date >=", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateLessThan(String value) {
            addCriterion("trade_date <", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateLessThanOrEqualTo(String value) {
            addCriterion("trade_date <=", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateLike(String value) {
            addCriterion("trade_date like", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateNotLike(String value) {
            addCriterion("trade_date not like", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateIn(List<String> values) {
            addCriterion("trade_date in", values, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateNotIn(List<String> values) {
            addCriterion("trade_date not in", values, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateBetween(String value1, String value2) {
            addCriterion("trade_date between", value1, value2, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateNotBetween(String value1, String value2) {
            addCriterion("trade_date not between", value1, value2, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIsNull() {
            addCriterion("trade_time is null");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIsNotNull() {
            addCriterion("trade_time is not null");
            return (Criteria) this;
        }

        public Criteria andTradeTimeEqualTo(String value) {
            addCriterion("trade_time =", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotEqualTo(String value) {
            addCriterion("trade_time <>", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeGreaterThan(String value) {
            addCriterion("trade_time >", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeGreaterThanOrEqualTo(String value) {
            addCriterion("trade_time >=", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeLessThan(String value) {
            addCriterion("trade_time <", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeLessThanOrEqualTo(String value) {
            addCriterion("trade_time <=", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeLike(String value) {
            addCriterion("trade_time like", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotLike(String value) {
            addCriterion("trade_time not like", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIn(List<String> values) {
            addCriterion("trade_time in", values, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotIn(List<String> values) {
            addCriterion("trade_time not in", values, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeBetween(String value1, String value2) {
            addCriterion("trade_time between", value1, value2, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotBetween(String value1, String value2) {
            addCriterion("trade_time not between", value1, value2, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeStateIsNull() {
            addCriterion("trade_state is null");
            return (Criteria) this;
        }

        public Criteria andTradeStateIsNotNull() {
            addCriterion("trade_state is not null");
            return (Criteria) this;
        }

        public Criteria andTradeStateEqualTo(String value) {
            addCriterion("trade_state =", value, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateNotEqualTo(String value) {
            addCriterion("trade_state <>", value, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateGreaterThan(String value) {
            addCriterion("trade_state >", value, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateGreaterThanOrEqualTo(String value) {
            addCriterion("trade_state >=", value, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateLessThan(String value) {
            addCriterion("trade_state <", value, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateLessThanOrEqualTo(String value) {
            addCriterion("trade_state <=", value, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateLike(String value) {
            addCriterion("trade_state like", value, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateNotLike(String value) {
            addCriterion("trade_state not like", value, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateIn(List<String> values) {
            addCriterion("trade_state in", values, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateNotIn(List<String> values) {
            addCriterion("trade_state not in", values, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateBetween(String value1, String value2) {
            addCriterion("trade_state between", value1, value2, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeStateNotBetween(String value1, String value2) {
            addCriterion("trade_state not between", value1, value2, "tradeState");
            return (Criteria) this;
        }

        public Criteria andTradeAmountIsNull() {
            addCriterion("trade_amount is null");
            return (Criteria) this;
        }

        public Criteria andTradeAmountIsNotNull() {
            addCriterion("trade_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTradeAmountEqualTo(Long value) {
            addCriterion("trade_amount =", value, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountNotEqualTo(Long value) {
            addCriterion("trade_amount <>", value, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountGreaterThan(Long value) {
            addCriterion("trade_amount >", value, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("trade_amount >=", value, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountLessThan(Long value) {
            addCriterion("trade_amount <", value, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountLessThanOrEqualTo(Long value) {
            addCriterion("trade_amount <=", value, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountIn(List<Long> values) {
            addCriterion("trade_amount in", values, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountNotIn(List<Long> values) {
            addCriterion("trade_amount not in", values, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountBetween(Long value1, Long value2) {
            addCriterion("trade_amount between", value1, value2, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountNotBetween(Long value1, Long value2) {
            addCriterion("trade_amount not between", value1, value2, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeFeeIsNull() {
            addCriterion("trade_fee is null");
            return (Criteria) this;
        }

        public Criteria andTradeFeeIsNotNull() {
            addCriterion("trade_fee is not null");
            return (Criteria) this;
        }

        public Criteria andTradeFeeEqualTo(Long value) {
            addCriterion("trade_fee =", value, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andTradeFeeNotEqualTo(Long value) {
            addCriterion("trade_fee <>", value, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andTradeFeeGreaterThan(Long value) {
            addCriterion("trade_fee >", value, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andTradeFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("trade_fee >=", value, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andTradeFeeLessThan(Long value) {
            addCriterion("trade_fee <", value, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andTradeFeeLessThanOrEqualTo(Long value) {
            addCriterion("trade_fee <=", value, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andTradeFeeIn(List<Long> values) {
            addCriterion("trade_fee in", values, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andTradeFeeNotIn(List<Long> values) {
            addCriterion("trade_fee not in", values, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andTradeFeeBetween(Long value1, Long value2) {
            addCriterion("trade_fee between", value1, value2, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andTradeFeeNotBetween(Long value1, Long value2) {
            addCriterion("trade_fee not between", value1, value2, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNoIsNull() {
            addCriterion("opposite_account_no is null");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNoIsNotNull() {
            addCriterion("opposite_account_no is not null");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNoEqualTo(String value) {
            addCriterion("opposite_account_no =", value, "oppositeAccountNo");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNoNotEqualTo(String value) {
            addCriterion("opposite_account_no <>", value, "oppositeAccountNo");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNoGreaterThan(String value) {
            addCriterion("opposite_account_no >", value, "oppositeAccountNo");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("opposite_account_no >=", value, "oppositeAccountNo");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNoLessThan(String value) {
            addCriterion("opposite_account_no <", value, "oppositeAccountNo");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNoLessThanOrEqualTo(String value) {
            addCriterion("opposite_account_no <=", value, "oppositeAccountNo");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNoLike(String value) {
            addCriterion("opposite_account_no like", value, "oppositeAccountNo");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNoNotLike(String value) {
            addCriterion("opposite_account_no not like", value, "oppositeAccountNo");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNoIn(List<String> values) {
            addCriterion("opposite_account_no in", values, "oppositeAccountNo");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNoNotIn(List<String> values) {
            addCriterion("opposite_account_no not in", values, "oppositeAccountNo");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNoBetween(String value1, String value2) {
            addCriterion("opposite_account_no between", value1, value2, "oppositeAccountNo");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNoNotBetween(String value1, String value2) {
            addCriterion("opposite_account_no not between", value1, value2, "oppositeAccountNo");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNameIsNull() {
            addCriterion("opposite_account_name is null");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNameIsNotNull() {
            addCriterion("opposite_account_name is not null");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNameEqualTo(String value) {
            addCriterion("opposite_account_name =", value, "oppositeAccountName");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNameNotEqualTo(String value) {
            addCriterion("opposite_account_name <>", value, "oppositeAccountName");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNameGreaterThan(String value) {
            addCriterion("opposite_account_name >", value, "oppositeAccountName");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("opposite_account_name >=", value, "oppositeAccountName");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNameLessThan(String value) {
            addCriterion("opposite_account_name <", value, "oppositeAccountName");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNameLessThanOrEqualTo(String value) {
            addCriterion("opposite_account_name <=", value, "oppositeAccountName");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNameLike(String value) {
            addCriterion("opposite_account_name like", value, "oppositeAccountName");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNameNotLike(String value) {
            addCriterion("opposite_account_name not like", value, "oppositeAccountName");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNameIn(List<String> values) {
            addCriterion("opposite_account_name in", values, "oppositeAccountName");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNameNotIn(List<String> values) {
            addCriterion("opposite_account_name not in", values, "oppositeAccountName");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNameBetween(String value1, String value2) {
            addCriterion("opposite_account_name between", value1, value2, "oppositeAccountName");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountNameNotBetween(String value1, String value2) {
            addCriterion("opposite_account_name not between", value1, value2, "oppositeAccountName");
            return (Criteria) this;
        }

        public Criteria andCashTransferfFlagIsNull() {
            addCriterion("cash_transferF_flag is null");
            return (Criteria) this;
        }

        public Criteria andCashTransferfFlagIsNotNull() {
            addCriterion("cash_transferF_flag is not null");
            return (Criteria) this;
        }

        public Criteria andCashTransferfFlagEqualTo(String value) {
            addCriterion("cash_transferF_flag =", value, "cashTransferfFlag");
            return (Criteria) this;
        }

        public Criteria andCashTransferfFlagNotEqualTo(String value) {
            addCriterion("cash_transferF_flag <>", value, "cashTransferfFlag");
            return (Criteria) this;
        }

        public Criteria andCashTransferfFlagGreaterThan(String value) {
            addCriterion("cash_transferF_flag >", value, "cashTransferfFlag");
            return (Criteria) this;
        }

        public Criteria andCashTransferfFlagGreaterThanOrEqualTo(String value) {
            addCriterion("cash_transferF_flag >=", value, "cashTransferfFlag");
            return (Criteria) this;
        }

        public Criteria andCashTransferfFlagLessThan(String value) {
            addCriterion("cash_transferF_flag <", value, "cashTransferfFlag");
            return (Criteria) this;
        }

        public Criteria andCashTransferfFlagLessThanOrEqualTo(String value) {
            addCriterion("cash_transferF_flag <=", value, "cashTransferfFlag");
            return (Criteria) this;
        }

        public Criteria andCashTransferfFlagLike(String value) {
            addCriterion("cash_transferF_flag like", value, "cashTransferfFlag");
            return (Criteria) this;
        }

        public Criteria andCashTransferfFlagNotLike(String value) {
            addCriterion("cash_transferF_flag not like", value, "cashTransferfFlag");
            return (Criteria) this;
        }

        public Criteria andCashTransferfFlagIn(List<String> values) {
            addCriterion("cash_transferF_flag in", values, "cashTransferfFlag");
            return (Criteria) this;
        }

        public Criteria andCashTransferfFlagNotIn(List<String> values) {
            addCriterion("cash_transferF_flag not in", values, "cashTransferfFlag");
            return (Criteria) this;
        }

        public Criteria andCashTransferfFlagBetween(String value1, String value2) {
            addCriterion("cash_transferF_flag between", value1, value2, "cashTransferfFlag");
            return (Criteria) this;
        }

        public Criteria andCashTransferfFlagNotBetween(String value1, String value2) {
            addCriterion("cash_transferF_flag not between", value1, value2, "cashTransferfFlag");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceIsNull() {
            addCriterion("account_balance is null");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceIsNotNull() {
            addCriterion("account_balance is not null");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceEqualTo(Long value) {
            addCriterion("account_balance =", value, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceNotEqualTo(Long value) {
            addCriterion("account_balance <>", value, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceGreaterThan(Long value) {
            addCriterion("account_balance >", value, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("account_balance >=", value, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceLessThan(Long value) {
            addCriterion("account_balance <", value, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceLessThanOrEqualTo(Long value) {
            addCriterion("account_balance <=", value, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceIn(List<Long> values) {
            addCriterion("account_balance in", values, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceNotIn(List<Long> values) {
            addCriterion("account_balance not in", values, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceBetween(Long value1, Long value2) {
            addCriterion("account_balance between", value1, value2, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceNotBetween(Long value1, Long value2) {
            addCriterion("account_balance not between", value1, value2, "accountBalance");
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