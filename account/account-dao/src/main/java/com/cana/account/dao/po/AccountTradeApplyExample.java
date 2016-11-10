package com.cana.account.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountTradeApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public AccountTradeApplyExample() {
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

        public Criteria andAccountIdIsNull() {
            addCriterion("account_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("account_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(String value) {
            addCriterion("account_id =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(String value) {
            addCriterion("account_id <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(String value) {
            addCriterion("account_id >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("account_id >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(String value) {
            addCriterion("account_id <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(String value) {
            addCriterion("account_id <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLike(String value) {
            addCriterion("account_id like", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotLike(String value) {
            addCriterion("account_id not like", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<String> values) {
            addCriterion("account_id in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<String> values) {
            addCriterion("account_id not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(String value1, String value2) {
            addCriterion("account_id between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(String value1, String value2) {
            addCriterion("account_id not between", value1, value2, "accountId");
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

        public Criteria andAccountNameIsNull() {
            addCriterion("account_name is null");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNotNull() {
            addCriterion("account_name is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNameEqualTo(String value) {
            addCriterion("account_name =", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotEqualTo(String value) {
            addCriterion("account_name <>", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThan(String value) {
            addCriterion("account_name >", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("account_name >=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThan(String value) {
            addCriterion("account_name <", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThanOrEqualTo(String value) {
            addCriterion("account_name <=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLike(String value) {
            addCriterion("account_name like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotLike(String value) {
            addCriterion("account_name not like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameIn(List<String> values) {
            addCriterion("account_name in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotIn(List<String> values) {
            addCriterion("account_name not in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameBetween(String value1, String value2) {
            addCriterion("account_name between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotBetween(String value1, String value2) {
            addCriterion("account_name not between", value1, value2, "accountName");
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

        public Criteria andOppositeAccountIdIsNull() {
            addCriterion("opposite_account_id is null");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountIdIsNotNull() {
            addCriterion("opposite_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountIdEqualTo(String value) {
            addCriterion("opposite_account_id =", value, "oppositeAccountId");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountIdNotEqualTo(String value) {
            addCriterion("opposite_account_id <>", value, "oppositeAccountId");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountIdGreaterThan(String value) {
            addCriterion("opposite_account_id >", value, "oppositeAccountId");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("opposite_account_id >=", value, "oppositeAccountId");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountIdLessThan(String value) {
            addCriterion("opposite_account_id <", value, "oppositeAccountId");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountIdLessThanOrEqualTo(String value) {
            addCriterion("opposite_account_id <=", value, "oppositeAccountId");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountIdLike(String value) {
            addCriterion("opposite_account_id like", value, "oppositeAccountId");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountIdNotLike(String value) {
            addCriterion("opposite_account_id not like", value, "oppositeAccountId");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountIdIn(List<String> values) {
            addCriterion("opposite_account_id in", values, "oppositeAccountId");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountIdNotIn(List<String> values) {
            addCriterion("opposite_account_id not in", values, "oppositeAccountId");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountIdBetween(String value1, String value2) {
            addCriterion("opposite_account_id between", value1, value2, "oppositeAccountId");
            return (Criteria) this;
        }

        public Criteria andOppositeAccountIdNotBetween(String value1, String value2) {
            addCriterion("opposite_account_id not between", value1, value2, "oppositeAccountId");
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

        public Criteria andWithdrawBankIsNull() {
            addCriterion("withdraw_bank is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankIsNotNull() {
            addCriterion("withdraw_bank is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankEqualTo(String value) {
            addCriterion("withdraw_bank =", value, "withdrawBank");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankNotEqualTo(String value) {
            addCriterion("withdraw_bank <>", value, "withdrawBank");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankGreaterThan(String value) {
            addCriterion("withdraw_bank >", value, "withdrawBank");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankGreaterThanOrEqualTo(String value) {
            addCriterion("withdraw_bank >=", value, "withdrawBank");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankLessThan(String value) {
            addCriterion("withdraw_bank <", value, "withdrawBank");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankLessThanOrEqualTo(String value) {
            addCriterion("withdraw_bank <=", value, "withdrawBank");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankLike(String value) {
            addCriterion("withdraw_bank like", value, "withdrawBank");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankNotLike(String value) {
            addCriterion("withdraw_bank not like", value, "withdrawBank");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankIn(List<String> values) {
            addCriterion("withdraw_bank in", values, "withdrawBank");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankNotIn(List<String> values) {
            addCriterion("withdraw_bank not in", values, "withdrawBank");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankBetween(String value1, String value2) {
            addCriterion("withdraw_bank between", value1, value2, "withdrawBank");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankNotBetween(String value1, String value2) {
            addCriterion("withdraw_bank not between", value1, value2, "withdrawBank");
            return (Criteria) this;
        }

        public Criteria andLianHangNoIsNull() {
            addCriterion("lian_hang_no is null");
            return (Criteria) this;
        }

        public Criteria andLianHangNoIsNotNull() {
            addCriterion("lian_hang_no is not null");
            return (Criteria) this;
        }

        public Criteria andLianHangNoEqualTo(String value) {
            addCriterion("lian_hang_no =", value, "lianHangNo");
            return (Criteria) this;
        }

        public Criteria andLianHangNoNotEqualTo(String value) {
            addCriterion("lian_hang_no <>", value, "lianHangNo");
            return (Criteria) this;
        }

        public Criteria andLianHangNoGreaterThan(String value) {
            addCriterion("lian_hang_no >", value, "lianHangNo");
            return (Criteria) this;
        }

        public Criteria andLianHangNoGreaterThanOrEqualTo(String value) {
            addCriterion("lian_hang_no >=", value, "lianHangNo");
            return (Criteria) this;
        }

        public Criteria andLianHangNoLessThan(String value) {
            addCriterion("lian_hang_no <", value, "lianHangNo");
            return (Criteria) this;
        }

        public Criteria andLianHangNoLessThanOrEqualTo(String value) {
            addCriterion("lian_hang_no <=", value, "lianHangNo");
            return (Criteria) this;
        }

        public Criteria andLianHangNoLike(String value) {
            addCriterion("lian_hang_no like", value, "lianHangNo");
            return (Criteria) this;
        }

        public Criteria andLianHangNoNotLike(String value) {
            addCriterion("lian_hang_no not like", value, "lianHangNo");
            return (Criteria) this;
        }

        public Criteria andLianHangNoIn(List<String> values) {
            addCriterion("lian_hang_no in", values, "lianHangNo");
            return (Criteria) this;
        }

        public Criteria andLianHangNoNotIn(List<String> values) {
            addCriterion("lian_hang_no not in", values, "lianHangNo");
            return (Criteria) this;
        }

        public Criteria andLianHangNoBetween(String value1, String value2) {
            addCriterion("lian_hang_no between", value1, value2, "lianHangNo");
            return (Criteria) this;
        }

        public Criteria andLianHangNoNotBetween(String value1, String value2) {
            addCriterion("lian_hang_no not between", value1, value2, "lianHangNo");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankAddressIsNull() {
            addCriterion("withdraw_bank_address is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankAddressIsNotNull() {
            addCriterion("withdraw_bank_address is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankAddressEqualTo(String value) {
            addCriterion("withdraw_bank_address =", value, "withdrawBankAddress");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankAddressNotEqualTo(String value) {
            addCriterion("withdraw_bank_address <>", value, "withdrawBankAddress");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankAddressGreaterThan(String value) {
            addCriterion("withdraw_bank_address >", value, "withdrawBankAddress");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankAddressGreaterThanOrEqualTo(String value) {
            addCriterion("withdraw_bank_address >=", value, "withdrawBankAddress");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankAddressLessThan(String value) {
            addCriterion("withdraw_bank_address <", value, "withdrawBankAddress");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankAddressLessThanOrEqualTo(String value) {
            addCriterion("withdraw_bank_address <=", value, "withdrawBankAddress");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankAddressLike(String value) {
            addCriterion("withdraw_bank_address like", value, "withdrawBankAddress");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankAddressNotLike(String value) {
            addCriterion("withdraw_bank_address not like", value, "withdrawBankAddress");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankAddressIn(List<String> values) {
            addCriterion("withdraw_bank_address in", values, "withdrawBankAddress");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankAddressNotIn(List<String> values) {
            addCriterion("withdraw_bank_address not in", values, "withdrawBankAddress");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankAddressBetween(String value1, String value2) {
            addCriterion("withdraw_bank_address between", value1, value2, "withdrawBankAddress");
            return (Criteria) this;
        }

        public Criteria andWithdrawBankAddressNotBetween(String value1, String value2) {
            addCriterion("withdraw_bank_address not between", value1, value2, "withdrawBankAddress");
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

        public Criteria andChargeIsNull() {
            addCriterion("charge is null");
            return (Criteria) this;
        }

        public Criteria andChargeIsNotNull() {
            addCriterion("charge is not null");
            return (Criteria) this;
        }

        public Criteria andChargeEqualTo(Long value) {
            addCriterion("charge =", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeNotEqualTo(Long value) {
            addCriterion("charge <>", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeGreaterThan(Long value) {
            addCriterion("charge >", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("charge >=", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeLessThan(Long value) {
            addCriterion("charge <", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeLessThanOrEqualTo(Long value) {
            addCriterion("charge <=", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeIn(List<Long> values) {
            addCriterion("charge in", values, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeNotIn(List<Long> values) {
            addCriterion("charge not in", values, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeBetween(Long value1, Long value2) {
            addCriterion("charge between", value1, value2, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeNotBetween(Long value1, Long value2) {
            addCriterion("charge not between", value1, value2, "charge");
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

        public Criteria andApplyCompanyIdIsNull() {
            addCriterion("apply_company_id is null");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyIdIsNotNull() {
            addCriterion("apply_company_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyIdEqualTo(String value) {
            addCriterion("apply_company_id =", value, "applyCompanyId");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyIdNotEqualTo(String value) {
            addCriterion("apply_company_id <>", value, "applyCompanyId");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyIdGreaterThan(String value) {
            addCriterion("apply_company_id >", value, "applyCompanyId");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("apply_company_id >=", value, "applyCompanyId");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyIdLessThan(String value) {
            addCriterion("apply_company_id <", value, "applyCompanyId");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("apply_company_id <=", value, "applyCompanyId");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyIdLike(String value) {
            addCriterion("apply_company_id like", value, "applyCompanyId");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyIdNotLike(String value) {
            addCriterion("apply_company_id not like", value, "applyCompanyId");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyIdIn(List<String> values) {
            addCriterion("apply_company_id in", values, "applyCompanyId");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyIdNotIn(List<String> values) {
            addCriterion("apply_company_id not in", values, "applyCompanyId");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyIdBetween(String value1, String value2) {
            addCriterion("apply_company_id between", value1, value2, "applyCompanyId");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyIdNotBetween(String value1, String value2) {
            addCriterion("apply_company_id not between", value1, value2, "applyCompanyId");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyNameIsNull() {
            addCriterion("apply_company_name is null");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyNameIsNotNull() {
            addCriterion("apply_company_name is not null");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyNameEqualTo(String value) {
            addCriterion("apply_company_name =", value, "applyCompanyName");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyNameNotEqualTo(String value) {
            addCriterion("apply_company_name <>", value, "applyCompanyName");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyNameGreaterThan(String value) {
            addCriterion("apply_company_name >", value, "applyCompanyName");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("apply_company_name >=", value, "applyCompanyName");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyNameLessThan(String value) {
            addCriterion("apply_company_name <", value, "applyCompanyName");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("apply_company_name <=", value, "applyCompanyName");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyNameLike(String value) {
            addCriterion("apply_company_name like", value, "applyCompanyName");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyNameNotLike(String value) {
            addCriterion("apply_company_name not like", value, "applyCompanyName");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyNameIn(List<String> values) {
            addCriterion("apply_company_name in", values, "applyCompanyName");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyNameNotIn(List<String> values) {
            addCriterion("apply_company_name not in", values, "applyCompanyName");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyNameBetween(String value1, String value2) {
            addCriterion("apply_company_name between", value1, value2, "applyCompanyName");
            return (Criteria) this;
        }

        public Criteria andApplyCompanyNameNotBetween(String value1, String value2) {
            addCriterion("apply_company_name not between", value1, value2, "applyCompanyName");
            return (Criteria) this;
        }

        public Criteria andAuditCompanyIdIsNull() {
            addCriterion("audit_company_id is null");
            return (Criteria) this;
        }

        public Criteria andAuditCompanyIdIsNotNull() {
            addCriterion("audit_company_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuditCompanyIdEqualTo(String value) {
            addCriterion("audit_company_id =", value, "auditCompanyId");
            return (Criteria) this;
        }

        public Criteria andAuditCompanyIdNotEqualTo(String value) {
            addCriterion("audit_company_id <>", value, "auditCompanyId");
            return (Criteria) this;
        }

        public Criteria andAuditCompanyIdGreaterThan(String value) {
            addCriterion("audit_company_id >", value, "auditCompanyId");
            return (Criteria) this;
        }

        public Criteria andAuditCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("audit_company_id >=", value, "auditCompanyId");
            return (Criteria) this;
        }

        public Criteria andAuditCompanyIdLessThan(String value) {
            addCriterion("audit_company_id <", value, "auditCompanyId");
            return (Criteria) this;
        }

        public Criteria andAuditCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("audit_company_id <=", value, "auditCompanyId");
            return (Criteria) this;
        }

        public Criteria andAuditCompanyIdLike(String value) {
            addCriterion("audit_company_id like", value, "auditCompanyId");
            return (Criteria) this;
        }

        public Criteria andAuditCompanyIdNotLike(String value) {
            addCriterion("audit_company_id not like", value, "auditCompanyId");
            return (Criteria) this;
        }

        public Criteria andAuditCompanyIdIn(List<String> values) {
            addCriterion("audit_company_id in", values, "auditCompanyId");
            return (Criteria) this;
        }

        public Criteria andAuditCompanyIdNotIn(List<String> values) {
            addCriterion("audit_company_id not in", values, "auditCompanyId");
            return (Criteria) this;
        }

        public Criteria andAuditCompanyIdBetween(String value1, String value2) {
            addCriterion("audit_company_id between", value1, value2, "auditCompanyId");
            return (Criteria) this;
        }

        public Criteria andAuditCompanyIdNotBetween(String value1, String value2) {
            addCriterion("audit_company_id not between", value1, value2, "auditCompanyId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdIsNull() {
            addCriterion("audit_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdIsNotNull() {
            addCriterion("audit_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdEqualTo(String value) {
            addCriterion("audit_user_id =", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotEqualTo(String value) {
            addCriterion("audit_user_id <>", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdGreaterThan(String value) {
            addCriterion("audit_user_id >", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("audit_user_id >=", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdLessThan(String value) {
            addCriterion("audit_user_id <", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdLessThanOrEqualTo(String value) {
            addCriterion("audit_user_id <=", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdLike(String value) {
            addCriterion("audit_user_id like", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotLike(String value) {
            addCriterion("audit_user_id not like", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdIn(List<String> values) {
            addCriterion("audit_user_id in", values, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotIn(List<String> values) {
            addCriterion("audit_user_id not in", values, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdBetween(String value1, String value2) {
            addCriterion("audit_user_id between", value1, value2, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotBetween(String value1, String value2) {
            addCriterion("audit_user_id not between", value1, value2, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditMessageIsNull() {
            addCriterion("audit_message is null");
            return (Criteria) this;
        }

        public Criteria andAuditMessageIsNotNull() {
            addCriterion("audit_message is not null");
            return (Criteria) this;
        }

        public Criteria andAuditMessageEqualTo(String value) {
            addCriterion("audit_message =", value, "auditMessage");
            return (Criteria) this;
        }

        public Criteria andAuditMessageNotEqualTo(String value) {
            addCriterion("audit_message <>", value, "auditMessage");
            return (Criteria) this;
        }

        public Criteria andAuditMessageGreaterThan(String value) {
            addCriterion("audit_message >", value, "auditMessage");
            return (Criteria) this;
        }

        public Criteria andAuditMessageGreaterThanOrEqualTo(String value) {
            addCriterion("audit_message >=", value, "auditMessage");
            return (Criteria) this;
        }

        public Criteria andAuditMessageLessThan(String value) {
            addCriterion("audit_message <", value, "auditMessage");
            return (Criteria) this;
        }

        public Criteria andAuditMessageLessThanOrEqualTo(String value) {
            addCriterion("audit_message <=", value, "auditMessage");
            return (Criteria) this;
        }

        public Criteria andAuditMessageLike(String value) {
            addCriterion("audit_message like", value, "auditMessage");
            return (Criteria) this;
        }

        public Criteria andAuditMessageNotLike(String value) {
            addCriterion("audit_message not like", value, "auditMessage");
            return (Criteria) this;
        }

        public Criteria andAuditMessageIn(List<String> values) {
            addCriterion("audit_message in", values, "auditMessage");
            return (Criteria) this;
        }

        public Criteria andAuditMessageNotIn(List<String> values) {
            addCriterion("audit_message not in", values, "auditMessage");
            return (Criteria) this;
        }

        public Criteria andAuditMessageBetween(String value1, String value2) {
            addCriterion("audit_message between", value1, value2, "auditMessage");
            return (Criteria) this;
        }

        public Criteria andAuditMessageNotBetween(String value1, String value2) {
            addCriterion("audit_message not between", value1, value2, "auditMessage");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNull() {
            addCriterion("audit_time is null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNotNull() {
            addCriterion("audit_time is not null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeEqualTo(Date value) {
            addCriterion("audit_time =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(Date value) {
            addCriterion("audit_time <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(Date value) {
            addCriterion("audit_time >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("audit_time >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(Date value) {
            addCriterion("audit_time <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(Date value) {
            addCriterion("audit_time <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<Date> values) {
            addCriterion("audit_time in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<Date> values) {
            addCriterion("audit_time not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(Date value1, Date value2) {
            addCriterion("audit_time between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(Date value1, Date value2) {
            addCriterion("audit_time not between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andIsRemoveAllSupervisionIsNull() {
            addCriterion("is_remove_all_supervision is null");
            return (Criteria) this;
        }

        public Criteria andIsRemoveAllSupervisionIsNotNull() {
            addCriterion("is_remove_all_supervision is not null");
            return (Criteria) this;
        }

        public Criteria andIsRemoveAllSupervisionEqualTo(Boolean value) {
            addCriterion("is_remove_all_supervision =", value, "isRemoveAllSupervision");
            return (Criteria) this;
        }

        public Criteria andIsRemoveAllSupervisionNotEqualTo(Boolean value) {
            addCriterion("is_remove_all_supervision <>", value, "isRemoveAllSupervision");
            return (Criteria) this;
        }

        public Criteria andIsRemoveAllSupervisionGreaterThan(Boolean value) {
            addCriterion("is_remove_all_supervision >", value, "isRemoveAllSupervision");
            return (Criteria) this;
        }

        public Criteria andIsRemoveAllSupervisionGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_remove_all_supervision >=", value, "isRemoveAllSupervision");
            return (Criteria) this;
        }

        public Criteria andIsRemoveAllSupervisionLessThan(Boolean value) {
            addCriterion("is_remove_all_supervision <", value, "isRemoveAllSupervision");
            return (Criteria) this;
        }

        public Criteria andIsRemoveAllSupervisionLessThanOrEqualTo(Boolean value) {
            addCriterion("is_remove_all_supervision <=", value, "isRemoveAllSupervision");
            return (Criteria) this;
        }

        public Criteria andIsRemoveAllSupervisionIn(List<Boolean> values) {
            addCriterion("is_remove_all_supervision in", values, "isRemoveAllSupervision");
            return (Criteria) this;
        }

        public Criteria andIsRemoveAllSupervisionNotIn(List<Boolean> values) {
            addCriterion("is_remove_all_supervision not in", values, "isRemoveAllSupervision");
            return (Criteria) this;
        }

        public Criteria andIsRemoveAllSupervisionBetween(Boolean value1, Boolean value2) {
            addCriterion("is_remove_all_supervision between", value1, value2, "isRemoveAllSupervision");
            return (Criteria) this;
        }

        public Criteria andIsRemoveAllSupervisionNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_remove_all_supervision not between", value1, value2, "isRemoveAllSupervision");
            return (Criteria) this;
        }

        public Criteria andSpecialAccountIdsIsNull() {
            addCriterion("special_account_ids is null");
            return (Criteria) this;
        }

        public Criteria andSpecialAccountIdsIsNotNull() {
            addCriterion("special_account_ids is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialAccountIdsEqualTo(String value) {
            addCriterion("special_account_ids =", value, "specialAccountIds");
            return (Criteria) this;
        }

        public Criteria andSpecialAccountIdsNotEqualTo(String value) {
            addCriterion("special_account_ids <>", value, "specialAccountIds");
            return (Criteria) this;
        }

        public Criteria andSpecialAccountIdsGreaterThan(String value) {
            addCriterion("special_account_ids >", value, "specialAccountIds");
            return (Criteria) this;
        }

        public Criteria andSpecialAccountIdsGreaterThanOrEqualTo(String value) {
            addCriterion("special_account_ids >=", value, "specialAccountIds");
            return (Criteria) this;
        }

        public Criteria andSpecialAccountIdsLessThan(String value) {
            addCriterion("special_account_ids <", value, "specialAccountIds");
            return (Criteria) this;
        }

        public Criteria andSpecialAccountIdsLessThanOrEqualTo(String value) {
            addCriterion("special_account_ids <=", value, "specialAccountIds");
            return (Criteria) this;
        }

        public Criteria andSpecialAccountIdsLike(String value) {
            addCriterion("special_account_ids like", value, "specialAccountIds");
            return (Criteria) this;
        }

        public Criteria andSpecialAccountIdsNotLike(String value) {
            addCriterion("special_account_ids not like", value, "specialAccountIds");
            return (Criteria) this;
        }

        public Criteria andSpecialAccountIdsIn(List<String> values) {
            addCriterion("special_account_ids in", values, "specialAccountIds");
            return (Criteria) this;
        }

        public Criteria andSpecialAccountIdsNotIn(List<String> values) {
            addCriterion("special_account_ids not in", values, "specialAccountIds");
            return (Criteria) this;
        }

        public Criteria andSpecialAccountIdsBetween(String value1, String value2) {
            addCriterion("special_account_ids between", value1, value2, "specialAccountIds");
            return (Criteria) this;
        }

        public Criteria andSpecialAccountIdsNotBetween(String value1, String value2) {
            addCriterion("special_account_ids not between", value1, value2, "specialAccountIds");
            return (Criteria) this;
        }

        public Criteria andRemoveSpecialAccountIdsIsNull() {
            addCriterion("remove_special_account_ids is null");
            return (Criteria) this;
        }

        public Criteria andRemoveSpecialAccountIdsIsNotNull() {
            addCriterion("remove_special_account_ids is not null");
            return (Criteria) this;
        }

        public Criteria andRemoveSpecialAccountIdsEqualTo(String value) {
            addCriterion("remove_special_account_ids =", value, "removeSpecialAccountIds");
            return (Criteria) this;
        }

        public Criteria andRemoveSpecialAccountIdsNotEqualTo(String value) {
            addCriterion("remove_special_account_ids <>", value, "removeSpecialAccountIds");
            return (Criteria) this;
        }

        public Criteria andRemoveSpecialAccountIdsGreaterThan(String value) {
            addCriterion("remove_special_account_ids >", value, "removeSpecialAccountIds");
            return (Criteria) this;
        }

        public Criteria andRemoveSpecialAccountIdsGreaterThanOrEqualTo(String value) {
            addCriterion("remove_special_account_ids >=", value, "removeSpecialAccountIds");
            return (Criteria) this;
        }

        public Criteria andRemoveSpecialAccountIdsLessThan(String value) {
            addCriterion("remove_special_account_ids <", value, "removeSpecialAccountIds");
            return (Criteria) this;
        }

        public Criteria andRemoveSpecialAccountIdsLessThanOrEqualTo(String value) {
            addCriterion("remove_special_account_ids <=", value, "removeSpecialAccountIds");
            return (Criteria) this;
        }

        public Criteria andRemoveSpecialAccountIdsLike(String value) {
            addCriterion("remove_special_account_ids like", value, "removeSpecialAccountIds");
            return (Criteria) this;
        }

        public Criteria andRemoveSpecialAccountIdsNotLike(String value) {
            addCriterion("remove_special_account_ids not like", value, "removeSpecialAccountIds");
            return (Criteria) this;
        }

        public Criteria andRemoveSpecialAccountIdsIn(List<String> values) {
            addCriterion("remove_special_account_ids in", values, "removeSpecialAccountIds");
            return (Criteria) this;
        }

        public Criteria andRemoveSpecialAccountIdsNotIn(List<String> values) {
            addCriterion("remove_special_account_ids not in", values, "removeSpecialAccountIds");
            return (Criteria) this;
        }

        public Criteria andRemoveSpecialAccountIdsBetween(String value1, String value2) {
            addCriterion("remove_special_account_ids between", value1, value2, "removeSpecialAccountIds");
            return (Criteria) this;
        }

        public Criteria andRemoveSpecialAccountIdsNotBetween(String value1, String value2) {
            addCriterion("remove_special_account_ids not between", value1, value2, "removeSpecialAccountIds");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentIsNull() {
            addCriterion("is_default_repayment is null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentIsNotNull() {
            addCriterion("is_default_repayment is not null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentEqualTo(Boolean value) {
            addCriterion("is_default_repayment =", value, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentNotEqualTo(Boolean value) {
            addCriterion("is_default_repayment <>", value, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentGreaterThan(Boolean value) {
            addCriterion("is_default_repayment >", value, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_default_repayment >=", value, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentLessThan(Boolean value) {
            addCriterion("is_default_repayment <", value, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentLessThanOrEqualTo(Boolean value) {
            addCriterion("is_default_repayment <=", value, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentIn(List<Boolean> values) {
            addCriterion("is_default_repayment in", values, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentNotIn(List<Boolean> values) {
            addCriterion("is_default_repayment not in", values, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentBetween(Boolean value1, Boolean value2) {
            addCriterion("is_default_repayment between", value1, value2, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andIsDefaultRepaymentNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_default_repayment not between", value1, value2, "isDefaultRepayment");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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