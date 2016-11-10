package com.cana.asset.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssetMarketDataReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public AssetMarketDataReportExample() {
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

        public Criteria andUnderlyingAssetTypeIsNull() {
            addCriterion("underlying_asset_type is null");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeIsNotNull() {
            addCriterion("underlying_asset_type is not null");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeEqualTo(String value) {
            addCriterion("underlying_asset_type =", value, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeNotEqualTo(String value) {
            addCriterion("underlying_asset_type <>", value, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeGreaterThan(String value) {
            addCriterion("underlying_asset_type >", value, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeGreaterThanOrEqualTo(String value) {
            addCriterion("underlying_asset_type >=", value, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeLessThan(String value) {
            addCriterion("underlying_asset_type <", value, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeLessThanOrEqualTo(String value) {
            addCriterion("underlying_asset_type <=", value, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeLike(String value) {
            addCriterion("underlying_asset_type like", value, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeNotLike(String value) {
            addCriterion("underlying_asset_type not like", value, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeIn(List<String> values) {
            addCriterion("underlying_asset_type in", values, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeNotIn(List<String> values) {
            addCriterion("underlying_asset_type not in", values, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeBetween(String value1, String value2) {
            addCriterion("underlying_asset_type between", value1, value2, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingAssetTypeNotBetween(String value1, String value2) {
            addCriterion("underlying_asset_type not between", value1, value2, "underlyingAssetType");
            return (Criteria) this;
        }

        public Criteria andIssueAmountIsNull() {
            addCriterion("issue_amount is null");
            return (Criteria) this;
        }

        public Criteria andIssueAmountIsNotNull() {
            addCriterion("issue_amount is not null");
            return (Criteria) this;
        }

        public Criteria andIssueAmountEqualTo(BigDecimal value) {
            addCriterion("issue_amount =", value, "issueAmount");
            return (Criteria) this;
        }

        public Criteria andIssueAmountNotEqualTo(BigDecimal value) {
            addCriterion("issue_amount <>", value, "issueAmount");
            return (Criteria) this;
        }

        public Criteria andIssueAmountGreaterThan(BigDecimal value) {
            addCriterion("issue_amount >", value, "issueAmount");
            return (Criteria) this;
        }

        public Criteria andIssueAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("issue_amount >=", value, "issueAmount");
            return (Criteria) this;
        }

        public Criteria andIssueAmountLessThan(BigDecimal value) {
            addCriterion("issue_amount <", value, "issueAmount");
            return (Criteria) this;
        }

        public Criteria andIssueAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("issue_amount <=", value, "issueAmount");
            return (Criteria) this;
        }

        public Criteria andIssueAmountIn(List<BigDecimal> values) {
            addCriterion("issue_amount in", values, "issueAmount");
            return (Criteria) this;
        }

        public Criteria andIssueAmountNotIn(List<BigDecimal> values) {
            addCriterion("issue_amount not in", values, "issueAmount");
            return (Criteria) this;
        }

        public Criteria andIssueAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("issue_amount between", value1, value2, "issueAmount");
            return (Criteria) this;
        }

        public Criteria andIssueAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("issue_amount not between", value1, value2, "issueAmount");
            return (Criteria) this;
        }

        public Criteria andIssueNumIsNull() {
            addCriterion("issue_num is null");
            return (Criteria) this;
        }

        public Criteria andIssueNumIsNotNull() {
            addCriterion("issue_num is not null");
            return (Criteria) this;
        }

        public Criteria andIssueNumEqualTo(Integer value) {
            addCriterion("issue_num =", value, "issueNum");
            return (Criteria) this;
        }

        public Criteria andIssueNumNotEqualTo(Integer value) {
            addCriterion("issue_num <>", value, "issueNum");
            return (Criteria) this;
        }

        public Criteria andIssueNumGreaterThan(Integer value) {
            addCriterion("issue_num >", value, "issueNum");
            return (Criteria) this;
        }

        public Criteria andIssueNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("issue_num >=", value, "issueNum");
            return (Criteria) this;
        }

        public Criteria andIssueNumLessThan(Integer value) {
            addCriterion("issue_num <", value, "issueNum");
            return (Criteria) this;
        }

        public Criteria andIssueNumLessThanOrEqualTo(Integer value) {
            addCriterion("issue_num <=", value, "issueNum");
            return (Criteria) this;
        }

        public Criteria andIssueNumIn(List<Integer> values) {
            addCriterion("issue_num in", values, "issueNum");
            return (Criteria) this;
        }

        public Criteria andIssueNumNotIn(List<Integer> values) {
            addCriterion("issue_num not in", values, "issueNum");
            return (Criteria) this;
        }

        public Criteria andIssueNumBetween(Integer value1, Integer value2) {
            addCriterion("issue_num between", value1, value2, "issueNum");
            return (Criteria) this;
        }

        public Criteria andIssueNumNotBetween(Integer value1, Integer value2) {
            addCriterion("issue_num not between", value1, value2, "issueNum");
            return (Criteria) this;
        }

        public Criteria andMonthIsNull() {
            addCriterion("month is null");
            return (Criteria) this;
        }

        public Criteria andMonthIsNotNull() {
            addCriterion("month is not null");
            return (Criteria) this;
        }

        public Criteria andMonthEqualTo(String value) {
            addCriterion("month =", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotEqualTo(String value) {
            addCriterion("month <>", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThan(String value) {
            addCriterion("month >", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThanOrEqualTo(String value) {
            addCriterion("month >=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThan(String value) {
            addCriterion("month <", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThanOrEqualTo(String value) {
            addCriterion("month <=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLike(String value) {
            addCriterion("month like", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotLike(String value) {
            addCriterion("month not like", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthIn(List<String> values) {
            addCriterion("month in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotIn(List<String> values) {
            addCriterion("month not in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthBetween(String value1, String value2) {
            addCriterion("month between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotBetween(String value1, String value2) {
            addCriterion("month not between", value1, value2, "month");
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

        public Criteria andUpateTimeIsNull() {
            addCriterion("upate_time is null");
            return (Criteria) this;
        }

        public Criteria andUpateTimeIsNotNull() {
            addCriterion("upate_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpateTimeEqualTo(Date value) {
            addCriterion("upate_time =", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeNotEqualTo(Date value) {
            addCriterion("upate_time <>", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeGreaterThan(Date value) {
            addCriterion("upate_time >", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("upate_time >=", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeLessThan(Date value) {
            addCriterion("upate_time <", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeLessThanOrEqualTo(Date value) {
            addCriterion("upate_time <=", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeIn(List<Date> values) {
            addCriterion("upate_time in", values, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeNotIn(List<Date> values) {
            addCriterion("upate_time not in", values, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeBetween(Date value1, Date value2) {
            addCriterion("upate_time between", value1, value2, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeNotBetween(Date value1, Date value2) {
            addCriterion("upate_time not between", value1, value2, "upateTime");
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