package com.cana.account.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountTradeRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public AccountTradeRecordExample() {
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

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(String value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(String value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(String value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(String value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLike(String value) {
            addCriterion("company_id like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotLike(String value) {
            addCriterion("company_id not like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<String> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<String> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(String value1, String value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(String value1, String value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
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

        public Criteria andAccountTypeIsNull() {
            addCriterion("account_type is null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIsNotNull() {
            addCriterion("account_type is not null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeEqualTo(String value) {
            addCriterion("account_type =", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotEqualTo(String value) {
            addCriterion("account_type <>", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThan(String value) {
            addCriterion("account_type >", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThanOrEqualTo(String value) {
            addCriterion("account_type >=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThan(String value) {
            addCriterion("account_type <", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThanOrEqualTo(String value) {
            addCriterion("account_type <=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLike(String value) {
            addCriterion("account_type like", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotLike(String value) {
            addCriterion("account_type not like", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIn(List<String> values) {
            addCriterion("account_type in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotIn(List<String> values) {
            addCriterion("account_type not in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeBetween(String value1, String value2) {
            addCriterion("account_type between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotBetween(String value1, String value2) {
            addCriterion("account_type not between", value1, value2, "accountType");
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

        public Criteria andOperateTypeIsNull() {
            addCriterion("operate_type is null");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIsNotNull() {
            addCriterion("operate_type is not null");
            return (Criteria) this;
        }

        public Criteria andOperateTypeEqualTo(String value) {
            addCriterion("operate_type =", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotEqualTo(String value) {
            addCriterion("operate_type <>", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeGreaterThan(String value) {
            addCriterion("operate_type >", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("operate_type >=", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLessThan(String value) {
            addCriterion("operate_type <", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLessThanOrEqualTo(String value) {
            addCriterion("operate_type <=", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLike(String value) {
            addCriterion("operate_type like", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotLike(String value) {
            addCriterion("operate_type not like", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIn(List<String> values) {
            addCriterion("operate_type in", values, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotIn(List<String> values) {
            addCriterion("operate_type not in", values, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeBetween(String value1, String value2) {
            addCriterion("operate_type between", value1, value2, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotBetween(String value1, String value2) {
            addCriterion("operate_type not between", value1, value2, "operateType");
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

        public Criteria andAccountSupervisionIdIsNull() {
            addCriterion("account_supervision_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdIsNotNull() {
            addCriterion("account_supervision_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdEqualTo(String value) {
            addCriterion("account_supervision_id =", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdNotEqualTo(String value) {
            addCriterion("account_supervision_id <>", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdGreaterThan(String value) {
            addCriterion("account_supervision_id >", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdGreaterThanOrEqualTo(String value) {
            addCriterion("account_supervision_id >=", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdLessThan(String value) {
            addCriterion("account_supervision_id <", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdLessThanOrEqualTo(String value) {
            addCriterion("account_supervision_id <=", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdLike(String value) {
            addCriterion("account_supervision_id like", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdNotLike(String value) {
            addCriterion("account_supervision_id not like", value, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdIn(List<String> values) {
            addCriterion("account_supervision_id in", values, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdNotIn(List<String> values) {
            addCriterion("account_supervision_id not in", values, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdBetween(String value1, String value2) {
            addCriterion("account_supervision_id between", value1, value2, "accountSupervisionId");
            return (Criteria) this;
        }

        public Criteria andAccountSupervisionIdNotBetween(String value1, String value2) {
            addCriterion("account_supervision_id not between", value1, value2, "accountSupervisionId");
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

        public Criteria andOperateUserIdIsNull() {
            addCriterion("operate_user_id is null");
            return (Criteria) this;
        }

        public Criteria andOperateUserIdIsNotNull() {
            addCriterion("operate_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperateUserIdEqualTo(String value) {
            addCriterion("operate_user_id =", value, "operateUserId");
            return (Criteria) this;
        }

        public Criteria andOperateUserIdNotEqualTo(String value) {
            addCriterion("operate_user_id <>", value, "operateUserId");
            return (Criteria) this;
        }

        public Criteria andOperateUserIdGreaterThan(String value) {
            addCriterion("operate_user_id >", value, "operateUserId");
            return (Criteria) this;
        }

        public Criteria andOperateUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("operate_user_id >=", value, "operateUserId");
            return (Criteria) this;
        }

        public Criteria andOperateUserIdLessThan(String value) {
            addCriterion("operate_user_id <", value, "operateUserId");
            return (Criteria) this;
        }

        public Criteria andOperateUserIdLessThanOrEqualTo(String value) {
            addCriterion("operate_user_id <=", value, "operateUserId");
            return (Criteria) this;
        }

        public Criteria andOperateUserIdLike(String value) {
            addCriterion("operate_user_id like", value, "operateUserId");
            return (Criteria) this;
        }

        public Criteria andOperateUserIdNotLike(String value) {
            addCriterion("operate_user_id not like", value, "operateUserId");
            return (Criteria) this;
        }

        public Criteria andOperateUserIdIn(List<String> values) {
            addCriterion("operate_user_id in", values, "operateUserId");
            return (Criteria) this;
        }

        public Criteria andOperateUserIdNotIn(List<String> values) {
            addCriterion("operate_user_id not in", values, "operateUserId");
            return (Criteria) this;
        }

        public Criteria andOperateUserIdBetween(String value1, String value2) {
            addCriterion("operate_user_id between", value1, value2, "operateUserId");
            return (Criteria) this;
        }

        public Criteria andOperateUserIdNotBetween(String value1, String value2) {
            addCriterion("operate_user_id not between", value1, value2, "operateUserId");
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

        public Criteria andUserTypeIsNull() {
            addCriterion("user_type is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("user_type is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(String value) {
            addCriterion("user_type =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(String value) {
            addCriterion("user_type <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(String value) {
            addCriterion("user_type >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(String value) {
            addCriterion("user_type >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(String value) {
            addCriterion("user_type <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(String value) {
            addCriterion("user_type <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLike(String value) {
            addCriterion("user_type like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotLike(String value) {
            addCriterion("user_type not like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<String> values) {
            addCriterion("user_type in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<String> values) {
            addCriterion("user_type not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(String value1, String value2) {
            addCriterion("user_type between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(String value1, String value2) {
            addCriterion("user_type not between", value1, value2, "userType");
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