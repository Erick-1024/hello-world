package com.cana.early.warning.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarlywarningEventExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public EarlywarningEventExample() {
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

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(String value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(String value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(String value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(String value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLike(String value) {
            addCriterion("product_id like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotLike(String value) {
            addCriterion("product_id not like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<String> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<String> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdIsNull() {
            addCriterion("finance_id is null");
            return (Criteria) this;
        }

        public Criteria andFinanceIdIsNotNull() {
            addCriterion("finance_id is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceIdEqualTo(String value) {
            addCriterion("finance_id =", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdNotEqualTo(String value) {
            addCriterion("finance_id <>", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdGreaterThan(String value) {
            addCriterion("finance_id >", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdGreaterThanOrEqualTo(String value) {
            addCriterion("finance_id >=", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdLessThan(String value) {
            addCriterion("finance_id <", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdLessThanOrEqualTo(String value) {
            addCriterion("finance_id <=", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdLike(String value) {
            addCriterion("finance_id like", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdNotLike(String value) {
            addCriterion("finance_id not like", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdIn(List<String> values) {
            addCriterion("finance_id in", values, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdNotIn(List<String> values) {
            addCriterion("finance_id not in", values, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdBetween(String value1, String value2) {
            addCriterion("finance_id between", value1, value2, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdNotBetween(String value1, String value2) {
            addCriterion("finance_id not between", value1, value2, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyIsNull() {
            addCriterion("finance_company is null");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyIsNotNull() {
            addCriterion("finance_company is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyEqualTo(String value) {
            addCriterion("finance_company =", value, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyNotEqualTo(String value) {
            addCriterion("finance_company <>", value, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyGreaterThan(String value) {
            addCriterion("finance_company >", value, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("finance_company >=", value, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyLessThan(String value) {
            addCriterion("finance_company <", value, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyLessThanOrEqualTo(String value) {
            addCriterion("finance_company <=", value, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyLike(String value) {
            addCriterion("finance_company like", value, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyNotLike(String value) {
            addCriterion("finance_company not like", value, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyIn(List<String> values) {
            addCriterion("finance_company in", values, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyNotIn(List<String> values) {
            addCriterion("finance_company not in", values, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyBetween(String value1, String value2) {
            addCriterion("finance_company between", value1, value2, "financeCompany");
            return (Criteria) this;
        }

        public Criteria andFinanceCompanyNotBetween(String value1, String value2) {
            addCriterion("finance_company not between", value1, value2, "financeCompany");
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

        public Criteria andSubTypeIsNull() {
            addCriterion("sub_type is null");
            return (Criteria) this;
        }

        public Criteria andSubTypeIsNotNull() {
            addCriterion("sub_type is not null");
            return (Criteria) this;
        }

        public Criteria andSubTypeEqualTo(String value) {
            addCriterion("sub_type =", value, "subType");
            return (Criteria) this;
        }

        public Criteria andSubTypeNotEqualTo(String value) {
            addCriterion("sub_type <>", value, "subType");
            return (Criteria) this;
        }

        public Criteria andSubTypeGreaterThan(String value) {
            addCriterion("sub_type >", value, "subType");
            return (Criteria) this;
        }

        public Criteria andSubTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sub_type >=", value, "subType");
            return (Criteria) this;
        }

        public Criteria andSubTypeLessThan(String value) {
            addCriterion("sub_type <", value, "subType");
            return (Criteria) this;
        }

        public Criteria andSubTypeLessThanOrEqualTo(String value) {
            addCriterion("sub_type <=", value, "subType");
            return (Criteria) this;
        }

        public Criteria andSubTypeLike(String value) {
            addCriterion("sub_type like", value, "subType");
            return (Criteria) this;
        }

        public Criteria andSubTypeNotLike(String value) {
            addCriterion("sub_type not like", value, "subType");
            return (Criteria) this;
        }

        public Criteria andSubTypeIn(List<String> values) {
            addCriterion("sub_type in", values, "subType");
            return (Criteria) this;
        }

        public Criteria andSubTypeNotIn(List<String> values) {
            addCriterion("sub_type not in", values, "subType");
            return (Criteria) this;
        }

        public Criteria andSubTypeBetween(String value1, String value2) {
            addCriterion("sub_type between", value1, value2, "subType");
            return (Criteria) this;
        }

        public Criteria andSubTypeNotBetween(String value1, String value2) {
            addCriterion("sub_type not between", value1, value2, "subType");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(String value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(String value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(String value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(String value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(String value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(String value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLike(String value) {
            addCriterion("level like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotLike(String value) {
            addCriterion("level not like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<String> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<String> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(String value1, String value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(String value1, String value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andEntryUserIdIsNull() {
            addCriterion("entry_user_id is null");
            return (Criteria) this;
        }

        public Criteria andEntryUserIdIsNotNull() {
            addCriterion("entry_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andEntryUserIdEqualTo(String value) {
            addCriterion("entry_user_id =", value, "entryUserId");
            return (Criteria) this;
        }

        public Criteria andEntryUserIdNotEqualTo(String value) {
            addCriterion("entry_user_id <>", value, "entryUserId");
            return (Criteria) this;
        }

        public Criteria andEntryUserIdGreaterThan(String value) {
            addCriterion("entry_user_id >", value, "entryUserId");
            return (Criteria) this;
        }

        public Criteria andEntryUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("entry_user_id >=", value, "entryUserId");
            return (Criteria) this;
        }

        public Criteria andEntryUserIdLessThan(String value) {
            addCriterion("entry_user_id <", value, "entryUserId");
            return (Criteria) this;
        }

        public Criteria andEntryUserIdLessThanOrEqualTo(String value) {
            addCriterion("entry_user_id <=", value, "entryUserId");
            return (Criteria) this;
        }

        public Criteria andEntryUserIdLike(String value) {
            addCriterion("entry_user_id like", value, "entryUserId");
            return (Criteria) this;
        }

        public Criteria andEntryUserIdNotLike(String value) {
            addCriterion("entry_user_id not like", value, "entryUserId");
            return (Criteria) this;
        }

        public Criteria andEntryUserIdIn(List<String> values) {
            addCriterion("entry_user_id in", values, "entryUserId");
            return (Criteria) this;
        }

        public Criteria andEntryUserIdNotIn(List<String> values) {
            addCriterion("entry_user_id not in", values, "entryUserId");
            return (Criteria) this;
        }

        public Criteria andEntryUserIdBetween(String value1, String value2) {
            addCriterion("entry_user_id between", value1, value2, "entryUserId");
            return (Criteria) this;
        }

        public Criteria andEntryUserIdNotBetween(String value1, String value2) {
            addCriterion("entry_user_id not between", value1, value2, "entryUserId");
            return (Criteria) this;
        }

        public Criteria andEntryRealNameIsNull() {
            addCriterion("entry_real_name is null");
            return (Criteria) this;
        }

        public Criteria andEntryRealNameIsNotNull() {
            addCriterion("entry_real_name is not null");
            return (Criteria) this;
        }

        public Criteria andEntryRealNameEqualTo(String value) {
            addCriterion("entry_real_name =", value, "entryRealName");
            return (Criteria) this;
        }

        public Criteria andEntryRealNameNotEqualTo(String value) {
            addCriterion("entry_real_name <>", value, "entryRealName");
            return (Criteria) this;
        }

        public Criteria andEntryRealNameGreaterThan(String value) {
            addCriterion("entry_real_name >", value, "entryRealName");
            return (Criteria) this;
        }

        public Criteria andEntryRealNameGreaterThanOrEqualTo(String value) {
            addCriterion("entry_real_name >=", value, "entryRealName");
            return (Criteria) this;
        }

        public Criteria andEntryRealNameLessThan(String value) {
            addCriterion("entry_real_name <", value, "entryRealName");
            return (Criteria) this;
        }

        public Criteria andEntryRealNameLessThanOrEqualTo(String value) {
            addCriterion("entry_real_name <=", value, "entryRealName");
            return (Criteria) this;
        }

        public Criteria andEntryRealNameLike(String value) {
            addCriterion("entry_real_name like", value, "entryRealName");
            return (Criteria) this;
        }

        public Criteria andEntryRealNameNotLike(String value) {
            addCriterion("entry_real_name not like", value, "entryRealName");
            return (Criteria) this;
        }

        public Criteria andEntryRealNameIn(List<String> values) {
            addCriterion("entry_real_name in", values, "entryRealName");
            return (Criteria) this;
        }

        public Criteria andEntryRealNameNotIn(List<String> values) {
            addCriterion("entry_real_name not in", values, "entryRealName");
            return (Criteria) this;
        }

        public Criteria andEntryRealNameBetween(String value1, String value2) {
            addCriterion("entry_real_name between", value1, value2, "entryRealName");
            return (Criteria) this;
        }

        public Criteria andEntryRealNameNotBetween(String value1, String value2) {
            addCriterion("entry_real_name not between", value1, value2, "entryRealName");
            return (Criteria) this;
        }

        public Criteria andEntryReviewTimeIsNull() {
            addCriterion("entry_review_time is null");
            return (Criteria) this;
        }

        public Criteria andEntryReviewTimeIsNotNull() {
            addCriterion("entry_review_time is not null");
            return (Criteria) this;
        }

        public Criteria andEntryReviewTimeEqualTo(Date value) {
            addCriterion("entry_review_time =", value, "entryReviewTime");
            return (Criteria) this;
        }

        public Criteria andEntryReviewTimeNotEqualTo(Date value) {
            addCriterion("entry_review_time <>", value, "entryReviewTime");
            return (Criteria) this;
        }

        public Criteria andEntryReviewTimeGreaterThan(Date value) {
            addCriterion("entry_review_time >", value, "entryReviewTime");
            return (Criteria) this;
        }

        public Criteria andEntryReviewTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("entry_review_time >=", value, "entryReviewTime");
            return (Criteria) this;
        }

        public Criteria andEntryReviewTimeLessThan(Date value) {
            addCriterion("entry_review_time <", value, "entryReviewTime");
            return (Criteria) this;
        }

        public Criteria andEntryReviewTimeLessThanOrEqualTo(Date value) {
            addCriterion("entry_review_time <=", value, "entryReviewTime");
            return (Criteria) this;
        }

        public Criteria andEntryReviewTimeIn(List<Date> values) {
            addCriterion("entry_review_time in", values, "entryReviewTime");
            return (Criteria) this;
        }

        public Criteria andEntryReviewTimeNotIn(List<Date> values) {
            addCriterion("entry_review_time not in", values, "entryReviewTime");
            return (Criteria) this;
        }

        public Criteria andEntryReviewTimeBetween(Date value1, Date value2) {
            addCriterion("entry_review_time between", value1, value2, "entryReviewTime");
            return (Criteria) this;
        }

        public Criteria andEntryReviewTimeNotBetween(Date value1, Date value2) {
            addCriterion("entry_review_time not between", value1, value2, "entryReviewTime");
            return (Criteria) this;
        }

        public Criteria andCancelUserIdIsNull() {
            addCriterion("cancel_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCancelUserIdIsNotNull() {
            addCriterion("cancel_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCancelUserIdEqualTo(String value) {
            addCriterion("cancel_user_id =", value, "cancelUserId");
            return (Criteria) this;
        }

        public Criteria andCancelUserIdNotEqualTo(String value) {
            addCriterion("cancel_user_id <>", value, "cancelUserId");
            return (Criteria) this;
        }

        public Criteria andCancelUserIdGreaterThan(String value) {
            addCriterion("cancel_user_id >", value, "cancelUserId");
            return (Criteria) this;
        }

        public Criteria andCancelUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("cancel_user_id >=", value, "cancelUserId");
            return (Criteria) this;
        }

        public Criteria andCancelUserIdLessThan(String value) {
            addCriterion("cancel_user_id <", value, "cancelUserId");
            return (Criteria) this;
        }

        public Criteria andCancelUserIdLessThanOrEqualTo(String value) {
            addCriterion("cancel_user_id <=", value, "cancelUserId");
            return (Criteria) this;
        }

        public Criteria andCancelUserIdLike(String value) {
            addCriterion("cancel_user_id like", value, "cancelUserId");
            return (Criteria) this;
        }

        public Criteria andCancelUserIdNotLike(String value) {
            addCriterion("cancel_user_id not like", value, "cancelUserId");
            return (Criteria) this;
        }

        public Criteria andCancelUserIdIn(List<String> values) {
            addCriterion("cancel_user_id in", values, "cancelUserId");
            return (Criteria) this;
        }

        public Criteria andCancelUserIdNotIn(List<String> values) {
            addCriterion("cancel_user_id not in", values, "cancelUserId");
            return (Criteria) this;
        }

        public Criteria andCancelUserIdBetween(String value1, String value2) {
            addCriterion("cancel_user_id between", value1, value2, "cancelUserId");
            return (Criteria) this;
        }

        public Criteria andCancelUserIdNotBetween(String value1, String value2) {
            addCriterion("cancel_user_id not between", value1, value2, "cancelUserId");
            return (Criteria) this;
        }

        public Criteria andCancelRealNameIsNull() {
            addCriterion("cancel_real_name is null");
            return (Criteria) this;
        }

        public Criteria andCancelRealNameIsNotNull() {
            addCriterion("cancel_real_name is not null");
            return (Criteria) this;
        }

        public Criteria andCancelRealNameEqualTo(String value) {
            addCriterion("cancel_real_name =", value, "cancelRealName");
            return (Criteria) this;
        }

        public Criteria andCancelRealNameNotEqualTo(String value) {
            addCriterion("cancel_real_name <>", value, "cancelRealName");
            return (Criteria) this;
        }

        public Criteria andCancelRealNameGreaterThan(String value) {
            addCriterion("cancel_real_name >", value, "cancelRealName");
            return (Criteria) this;
        }

        public Criteria andCancelRealNameGreaterThanOrEqualTo(String value) {
            addCriterion("cancel_real_name >=", value, "cancelRealName");
            return (Criteria) this;
        }

        public Criteria andCancelRealNameLessThan(String value) {
            addCriterion("cancel_real_name <", value, "cancelRealName");
            return (Criteria) this;
        }

        public Criteria andCancelRealNameLessThanOrEqualTo(String value) {
            addCriterion("cancel_real_name <=", value, "cancelRealName");
            return (Criteria) this;
        }

        public Criteria andCancelRealNameLike(String value) {
            addCriterion("cancel_real_name like", value, "cancelRealName");
            return (Criteria) this;
        }

        public Criteria andCancelRealNameNotLike(String value) {
            addCriterion("cancel_real_name not like", value, "cancelRealName");
            return (Criteria) this;
        }

        public Criteria andCancelRealNameIn(List<String> values) {
            addCriterion("cancel_real_name in", values, "cancelRealName");
            return (Criteria) this;
        }

        public Criteria andCancelRealNameNotIn(List<String> values) {
            addCriterion("cancel_real_name not in", values, "cancelRealName");
            return (Criteria) this;
        }

        public Criteria andCancelRealNameBetween(String value1, String value2) {
            addCriterion("cancel_real_name between", value1, value2, "cancelRealName");
            return (Criteria) this;
        }

        public Criteria andCancelRealNameNotBetween(String value1, String value2) {
            addCriterion("cancel_real_name not between", value1, value2, "cancelRealName");
            return (Criteria) this;
        }

        public Criteria andCancelReviewTimeIsNull() {
            addCriterion("cancel_review_time is null");
            return (Criteria) this;
        }

        public Criteria andCancelReviewTimeIsNotNull() {
            addCriterion("cancel_review_time is not null");
            return (Criteria) this;
        }

        public Criteria andCancelReviewTimeEqualTo(Date value) {
            addCriterion("cancel_review_time =", value, "cancelReviewTime");
            return (Criteria) this;
        }

        public Criteria andCancelReviewTimeNotEqualTo(Date value) {
            addCriterion("cancel_review_time <>", value, "cancelReviewTime");
            return (Criteria) this;
        }

        public Criteria andCancelReviewTimeGreaterThan(Date value) {
            addCriterion("cancel_review_time >", value, "cancelReviewTime");
            return (Criteria) this;
        }

        public Criteria andCancelReviewTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("cancel_review_time >=", value, "cancelReviewTime");
            return (Criteria) this;
        }

        public Criteria andCancelReviewTimeLessThan(Date value) {
            addCriterion("cancel_review_time <", value, "cancelReviewTime");
            return (Criteria) this;
        }

        public Criteria andCancelReviewTimeLessThanOrEqualTo(Date value) {
            addCriterion("cancel_review_time <=", value, "cancelReviewTime");
            return (Criteria) this;
        }

        public Criteria andCancelReviewTimeIn(List<Date> values) {
            addCriterion("cancel_review_time in", values, "cancelReviewTime");
            return (Criteria) this;
        }

        public Criteria andCancelReviewTimeNotIn(List<Date> values) {
            addCriterion("cancel_review_time not in", values, "cancelReviewTime");
            return (Criteria) this;
        }

        public Criteria andCancelReviewTimeBetween(Date value1, Date value2) {
            addCriterion("cancel_review_time between", value1, value2, "cancelReviewTime");
            return (Criteria) this;
        }

        public Criteria andCancelReviewTimeNotBetween(Date value1, Date value2) {
            addCriterion("cancel_review_time not between", value1, value2, "cancelReviewTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeIsNull() {
            addCriterion("occur_time is null");
            return (Criteria) this;
        }

        public Criteria andOccurTimeIsNotNull() {
            addCriterion("occur_time is not null");
            return (Criteria) this;
        }

        public Criteria andOccurTimeEqualTo(Date value) {
            addCriterion("occur_time =", value, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeNotEqualTo(Date value) {
            addCriterion("occur_time <>", value, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeGreaterThan(Date value) {
            addCriterion("occur_time >", value, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("occur_time >=", value, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeLessThan(Date value) {
            addCriterion("occur_time <", value, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeLessThanOrEqualTo(Date value) {
            addCriterion("occur_time <=", value, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeIn(List<Date> values) {
            addCriterion("occur_time in", values, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeNotIn(List<Date> values) {
            addCriterion("occur_time not in", values, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeBetween(Date value1, Date value2) {
            addCriterion("occur_time between", value1, value2, "occurTime");
            return (Criteria) this;
        }

        public Criteria andOccurTimeNotBetween(Date value1, Date value2) {
            addCriterion("occur_time not between", value1, value2, "occurTime");
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

        public Criteria andRepresentIsNull() {
            addCriterion("represent is null");
            return (Criteria) this;
        }

        public Criteria andRepresentIsNotNull() {
            addCriterion("represent is not null");
            return (Criteria) this;
        }

        public Criteria andRepresentEqualTo(String value) {
            addCriterion("represent =", value, "represent");
            return (Criteria) this;
        }

        public Criteria andRepresentNotEqualTo(String value) {
            addCriterion("represent <>", value, "represent");
            return (Criteria) this;
        }

        public Criteria andRepresentGreaterThan(String value) {
            addCriterion("represent >", value, "represent");
            return (Criteria) this;
        }

        public Criteria andRepresentGreaterThanOrEqualTo(String value) {
            addCriterion("represent >=", value, "represent");
            return (Criteria) this;
        }

        public Criteria andRepresentLessThan(String value) {
            addCriterion("represent <", value, "represent");
            return (Criteria) this;
        }

        public Criteria andRepresentLessThanOrEqualTo(String value) {
            addCriterion("represent <=", value, "represent");
            return (Criteria) this;
        }

        public Criteria andRepresentLike(String value) {
            addCriterion("represent like", value, "represent");
            return (Criteria) this;
        }

        public Criteria andRepresentNotLike(String value) {
            addCriterion("represent not like", value, "represent");
            return (Criteria) this;
        }

        public Criteria andRepresentIn(List<String> values) {
            addCriterion("represent in", values, "represent");
            return (Criteria) this;
        }

        public Criteria andRepresentNotIn(List<String> values) {
            addCriterion("represent not in", values, "represent");
            return (Criteria) this;
        }

        public Criteria andRepresentBetween(String value1, String value2) {
            addCriterion("represent between", value1, value2, "represent");
            return (Criteria) this;
        }

        public Criteria andRepresentNotBetween(String value1, String value2) {
            addCriterion("represent not between", value1, value2, "represent");
            return (Criteria) this;
        }

        public Criteria andExtraDataIsNull() {
            addCriterion("extra_data is null");
            return (Criteria) this;
        }

        public Criteria andExtraDataIsNotNull() {
            addCriterion("extra_data is not null");
            return (Criteria) this;
        }

        public Criteria andExtraDataEqualTo(String value) {
            addCriterion("extra_data =", value, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataNotEqualTo(String value) {
            addCriterion("extra_data <>", value, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataGreaterThan(String value) {
            addCriterion("extra_data >", value, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataGreaterThanOrEqualTo(String value) {
            addCriterion("extra_data >=", value, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataLessThan(String value) {
            addCriterion("extra_data <", value, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataLessThanOrEqualTo(String value) {
            addCriterion("extra_data <=", value, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataLike(String value) {
            addCriterion("extra_data like", value, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataNotLike(String value) {
            addCriterion("extra_data not like", value, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataIn(List<String> values) {
            addCriterion("extra_data in", values, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataNotIn(List<String> values) {
            addCriterion("extra_data not in", values, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataBetween(String value1, String value2) {
            addCriterion("extra_data between", value1, value2, "extraData");
            return (Criteria) this;
        }

        public Criteria andExtraDataNotBetween(String value1, String value2) {
            addCriterion("extra_data not between", value1, value2, "extraData");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
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