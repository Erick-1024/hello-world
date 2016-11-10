package com.cana.asset.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssetMarketDataProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public AssetMarketDataProjectExample() {
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

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andValueDateIsNull() {
            addCriterion("value_date is null");
            return (Criteria) this;
        }

        public Criteria andValueDateIsNotNull() {
            addCriterion("value_date is not null");
            return (Criteria) this;
        }

        public Criteria andValueDateEqualTo(String value) {
            addCriterion("value_date =", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateNotEqualTo(String value) {
            addCriterion("value_date <>", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateGreaterThan(String value) {
            addCriterion("value_date >", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateGreaterThanOrEqualTo(String value) {
            addCriterion("value_date >=", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateLessThan(String value) {
            addCriterion("value_date <", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateLessThanOrEqualTo(String value) {
            addCriterion("value_date <=", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateLike(String value) {
            addCriterion("value_date like", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateNotLike(String value) {
            addCriterion("value_date not like", value, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateIn(List<String> values) {
            addCriterion("value_date in", values, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateNotIn(List<String> values) {
            addCriterion("value_date not in", values, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateBetween(String value1, String value2) {
            addCriterion("value_date between", value1, value2, "valueDate");
            return (Criteria) this;
        }

        public Criteria andValueDateNotBetween(String value1, String value2) {
            addCriterion("value_date not between", value1, value2, "valueDate");
            return (Criteria) this;
        }

        public Criteria andOriginatorIsNull() {
            addCriterion("originator is null");
            return (Criteria) this;
        }

        public Criteria andOriginatorIsNotNull() {
            addCriterion("originator is not null");
            return (Criteria) this;
        }

        public Criteria andOriginatorEqualTo(String value) {
            addCriterion("originator =", value, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorNotEqualTo(String value) {
            addCriterion("originator <>", value, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorGreaterThan(String value) {
            addCriterion("originator >", value, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorGreaterThanOrEqualTo(String value) {
            addCriterion("originator >=", value, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorLessThan(String value) {
            addCriterion("originator <", value, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorLessThanOrEqualTo(String value) {
            addCriterion("originator <=", value, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorLike(String value) {
            addCriterion("originator like", value, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorNotLike(String value) {
            addCriterion("originator not like", value, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorIn(List<String> values) {
            addCriterion("originator in", values, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorNotIn(List<String> values) {
            addCriterion("originator not in", values, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorBetween(String value1, String value2) {
            addCriterion("originator between", value1, value2, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorNotBetween(String value1, String value2) {
            addCriterion("originator not between", value1, value2, "originator");
            return (Criteria) this;
        }

        public Criteria andIssueTotalAmountIsNull() {
            addCriterion("issue_total_amount is null");
            return (Criteria) this;
        }

        public Criteria andIssueTotalAmountIsNotNull() {
            addCriterion("issue_total_amount is not null");
            return (Criteria) this;
        }

        public Criteria andIssueTotalAmountEqualTo(BigDecimal value) {
            addCriterion("issue_total_amount =", value, "issueTotalAmount");
            return (Criteria) this;
        }

        public Criteria andIssueTotalAmountNotEqualTo(BigDecimal value) {
            addCriterion("issue_total_amount <>", value, "issueTotalAmount");
            return (Criteria) this;
        }

        public Criteria andIssueTotalAmountGreaterThan(BigDecimal value) {
            addCriterion("issue_total_amount >", value, "issueTotalAmount");
            return (Criteria) this;
        }

        public Criteria andIssueTotalAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("issue_total_amount >=", value, "issueTotalAmount");
            return (Criteria) this;
        }

        public Criteria andIssueTotalAmountLessThan(BigDecimal value) {
            addCriterion("issue_total_amount <", value, "issueTotalAmount");
            return (Criteria) this;
        }

        public Criteria andIssueTotalAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("issue_total_amount <=", value, "issueTotalAmount");
            return (Criteria) this;
        }

        public Criteria andIssueTotalAmountIn(List<BigDecimal> values) {
            addCriterion("issue_total_amount in", values, "issueTotalAmount");
            return (Criteria) this;
        }

        public Criteria andIssueTotalAmountNotIn(List<BigDecimal> values) {
            addCriterion("issue_total_amount not in", values, "issueTotalAmount");
            return (Criteria) this;
        }

        public Criteria andIssueTotalAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("issue_total_amount between", value1, value2, "issueTotalAmount");
            return (Criteria) this;
        }

        public Criteria andIssueTotalAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("issue_total_amount not between", value1, value2, "issueTotalAmount");
            return (Criteria) this;
        }

        public Criteria andSupervisionAgencyIsNull() {
            addCriterion("supervision_agency is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionAgencyIsNotNull() {
            addCriterion("supervision_agency is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionAgencyEqualTo(String value) {
            addCriterion("supervision_agency =", value, "supervisionAgency");
            return (Criteria) this;
        }

        public Criteria andSupervisionAgencyNotEqualTo(String value) {
            addCriterion("supervision_agency <>", value, "supervisionAgency");
            return (Criteria) this;
        }

        public Criteria andSupervisionAgencyGreaterThan(String value) {
            addCriterion("supervision_agency >", value, "supervisionAgency");
            return (Criteria) this;
        }

        public Criteria andSupervisionAgencyGreaterThanOrEqualTo(String value) {
            addCriterion("supervision_agency >=", value, "supervisionAgency");
            return (Criteria) this;
        }

        public Criteria andSupervisionAgencyLessThan(String value) {
            addCriterion("supervision_agency <", value, "supervisionAgency");
            return (Criteria) this;
        }

        public Criteria andSupervisionAgencyLessThanOrEqualTo(String value) {
            addCriterion("supervision_agency <=", value, "supervisionAgency");
            return (Criteria) this;
        }

        public Criteria andSupervisionAgencyLike(String value) {
            addCriterion("supervision_agency like", value, "supervisionAgency");
            return (Criteria) this;
        }

        public Criteria andSupervisionAgencyNotLike(String value) {
            addCriterion("supervision_agency not like", value, "supervisionAgency");
            return (Criteria) this;
        }

        public Criteria andSupervisionAgencyIn(List<String> values) {
            addCriterion("supervision_agency in", values, "supervisionAgency");
            return (Criteria) this;
        }

        public Criteria andSupervisionAgencyNotIn(List<String> values) {
            addCriterion("supervision_agency not in", values, "supervisionAgency");
            return (Criteria) this;
        }

        public Criteria andSupervisionAgencyBetween(String value1, String value2) {
            addCriterion("supervision_agency between", value1, value2, "supervisionAgency");
            return (Criteria) this;
        }

        public Criteria andSupervisionAgencyNotBetween(String value1, String value2) {
            addCriterion("supervision_agency not between", value1, value2, "supervisionAgency");
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

        public Criteria andIssuerIsNull() {
            addCriterion("issuer is null");
            return (Criteria) this;
        }

        public Criteria andIssuerIsNotNull() {
            addCriterion("issuer is not null");
            return (Criteria) this;
        }

        public Criteria andIssuerEqualTo(String value) {
            addCriterion("issuer =", value, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerNotEqualTo(String value) {
            addCriterion("issuer <>", value, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerGreaterThan(String value) {
            addCriterion("issuer >", value, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerGreaterThanOrEqualTo(String value) {
            addCriterion("issuer >=", value, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerLessThan(String value) {
            addCriterion("issuer <", value, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerLessThanOrEqualTo(String value) {
            addCriterion("issuer <=", value, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerLike(String value) {
            addCriterion("issuer like", value, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerNotLike(String value) {
            addCriterion("issuer not like", value, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerIn(List<String> values) {
            addCriterion("issuer in", values, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerNotIn(List<String> values) {
            addCriterion("issuer not in", values, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerBetween(String value1, String value2) {
            addCriterion("issuer between", value1, value2, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssuerNotBetween(String value1, String value2) {
            addCriterion("issuer not between", value1, value2, "issuer");
            return (Criteria) this;
        }

        public Criteria andIssueMonthIsNull() {
            addCriterion("issue_month is null");
            return (Criteria) this;
        }

        public Criteria andIssueMonthIsNotNull() {
            addCriterion("issue_month is not null");
            return (Criteria) this;
        }

        public Criteria andIssueMonthEqualTo(Integer value) {
            addCriterion("issue_month =", value, "issueMonth");
            return (Criteria) this;
        }

        public Criteria andIssueMonthNotEqualTo(Integer value) {
            addCriterion("issue_month <>", value, "issueMonth");
            return (Criteria) this;
        }

        public Criteria andIssueMonthGreaterThan(Integer value) {
            addCriterion("issue_month >", value, "issueMonth");
            return (Criteria) this;
        }

        public Criteria andIssueMonthGreaterThanOrEqualTo(Integer value) {
            addCriterion("issue_month >=", value, "issueMonth");
            return (Criteria) this;
        }

        public Criteria andIssueMonthLessThan(Integer value) {
            addCriterion("issue_month <", value, "issueMonth");
            return (Criteria) this;
        }

        public Criteria andIssueMonthLessThanOrEqualTo(Integer value) {
            addCriterion("issue_month <=", value, "issueMonth");
            return (Criteria) this;
        }

        public Criteria andIssueMonthIn(List<Integer> values) {
            addCriterion("issue_month in", values, "issueMonth");
            return (Criteria) this;
        }

        public Criteria andIssueMonthNotIn(List<Integer> values) {
            addCriterion("issue_month not in", values, "issueMonth");
            return (Criteria) this;
        }

        public Criteria andIssueMonthBetween(Integer value1, Integer value2) {
            addCriterion("issue_month between", value1, value2, "issueMonth");
            return (Criteria) this;
        }

        public Criteria andIssueMonthNotBetween(Integer value1, Integer value2) {
            addCriterion("issue_month not between", value1, value2, "issueMonth");
            return (Criteria) this;
        }

        public Criteria andAaaAverageInterestRateIsNull() {
            addCriterion("AAA_average_interest_rate is null");
            return (Criteria) this;
        }

        public Criteria andAaaAverageInterestRateIsNotNull() {
            addCriterion("AAA_average_interest_rate is not null");
            return (Criteria) this;
        }

        public Criteria andAaaAverageInterestRateEqualTo(BigDecimal value) {
            addCriterion("AAA_average_interest_rate =", value, "aaaAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andAaaAverageInterestRateNotEqualTo(BigDecimal value) {
            addCriterion("AAA_average_interest_rate <>", value, "aaaAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andAaaAverageInterestRateGreaterThan(BigDecimal value) {
            addCriterion("AAA_average_interest_rate >", value, "aaaAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andAaaAverageInterestRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AAA_average_interest_rate >=", value, "aaaAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andAaaAverageInterestRateLessThan(BigDecimal value) {
            addCriterion("AAA_average_interest_rate <", value, "aaaAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andAaaAverageInterestRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("AAA_average_interest_rate <=", value, "aaaAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andAaaAverageInterestRateIn(List<BigDecimal> values) {
            addCriterion("AAA_average_interest_rate in", values, "aaaAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andAaaAverageInterestRateNotIn(List<BigDecimal> values) {
            addCriterion("AAA_average_interest_rate not in", values, "aaaAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andAaaAverageInterestRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AAA_average_interest_rate between", value1, value2, "aaaAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andAaaAverageInterestRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AAA_average_interest_rate not between", value1, value2, "aaaAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andPriorityAverageInterestRateIsNull() {
            addCriterion("priority_average_interest_rate is null");
            return (Criteria) this;
        }

        public Criteria andPriorityAverageInterestRateIsNotNull() {
            addCriterion("priority_average_interest_rate is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityAverageInterestRateEqualTo(BigDecimal value) {
            addCriterion("priority_average_interest_rate =", value, "priorityAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andPriorityAverageInterestRateNotEqualTo(BigDecimal value) {
            addCriterion("priority_average_interest_rate <>", value, "priorityAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andPriorityAverageInterestRateGreaterThan(BigDecimal value) {
            addCriterion("priority_average_interest_rate >", value, "priorityAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andPriorityAverageInterestRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("priority_average_interest_rate >=", value, "priorityAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andPriorityAverageInterestRateLessThan(BigDecimal value) {
            addCriterion("priority_average_interest_rate <", value, "priorityAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andPriorityAverageInterestRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("priority_average_interest_rate <=", value, "priorityAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andPriorityAverageInterestRateIn(List<BigDecimal> values) {
            addCriterion("priority_average_interest_rate in", values, "priorityAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andPriorityAverageInterestRateNotIn(List<BigDecimal> values) {
            addCriterion("priority_average_interest_rate not in", values, "priorityAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andPriorityAverageInterestRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("priority_average_interest_rate between", value1, value2, "priorityAverageInterestRate");
            return (Criteria) this;
        }

        public Criteria andPriorityAverageInterestRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("priority_average_interest_rate not between", value1, value2, "priorityAverageInterestRate");
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