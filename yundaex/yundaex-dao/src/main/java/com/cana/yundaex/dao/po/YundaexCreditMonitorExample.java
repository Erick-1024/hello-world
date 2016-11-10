package com.cana.yundaex.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YundaexCreditMonitorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public YundaexCreditMonitorExample() {
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

        public Criteria andStationNoIsNull() {
            addCriterion("station_no is null");
            return (Criteria) this;
        }

        public Criteria andStationNoIsNotNull() {
            addCriterion("station_no is not null");
            return (Criteria) this;
        }

        public Criteria andStationNoEqualTo(String value) {
            addCriterion("station_no =", value, "stationNo");
            return (Criteria) this;
        }

        public Criteria andStationNoNotEqualTo(String value) {
            addCriterion("station_no <>", value, "stationNo");
            return (Criteria) this;
        }

        public Criteria andStationNoGreaterThan(String value) {
            addCriterion("station_no >", value, "stationNo");
            return (Criteria) this;
        }

        public Criteria andStationNoGreaterThanOrEqualTo(String value) {
            addCriterion("station_no >=", value, "stationNo");
            return (Criteria) this;
        }

        public Criteria andStationNoLessThan(String value) {
            addCriterion("station_no <", value, "stationNo");
            return (Criteria) this;
        }

        public Criteria andStationNoLessThanOrEqualTo(String value) {
            addCriterion("station_no <=", value, "stationNo");
            return (Criteria) this;
        }

        public Criteria andStationNoLike(String value) {
            addCriterion("station_no like", value, "stationNo");
            return (Criteria) this;
        }

        public Criteria andStationNoNotLike(String value) {
            addCriterion("station_no not like", value, "stationNo");
            return (Criteria) this;
        }

        public Criteria andStationNoIn(List<String> values) {
            addCriterion("station_no in", values, "stationNo");
            return (Criteria) this;
        }

        public Criteria andStationNoNotIn(List<String> values) {
            addCriterion("station_no not in", values, "stationNo");
            return (Criteria) this;
        }

        public Criteria andStationNoBetween(String value1, String value2) {
            addCriterion("station_no between", value1, value2, "stationNo");
            return (Criteria) this;
        }

        public Criteria andStationNoNotBetween(String value1, String value2) {
            addCriterion("station_no not between", value1, value2, "stationNo");
            return (Criteria) this;
        }

        public Criteria andStationMgrIsNull() {
            addCriterion("station_mgr is null");
            return (Criteria) this;
        }

        public Criteria andStationMgrIsNotNull() {
            addCriterion("station_mgr is not null");
            return (Criteria) this;
        }

        public Criteria andStationMgrEqualTo(String value) {
            addCriterion("station_mgr =", value, "stationMgr");
            return (Criteria) this;
        }

        public Criteria andStationMgrNotEqualTo(String value) {
            addCriterion("station_mgr <>", value, "stationMgr");
            return (Criteria) this;
        }

        public Criteria andStationMgrGreaterThan(String value) {
            addCriterion("station_mgr >", value, "stationMgr");
            return (Criteria) this;
        }

        public Criteria andStationMgrGreaterThanOrEqualTo(String value) {
            addCriterion("station_mgr >=", value, "stationMgr");
            return (Criteria) this;
        }

        public Criteria andStationMgrLessThan(String value) {
            addCriterion("station_mgr <", value, "stationMgr");
            return (Criteria) this;
        }

        public Criteria andStationMgrLessThanOrEqualTo(String value) {
            addCriterion("station_mgr <=", value, "stationMgr");
            return (Criteria) this;
        }

        public Criteria andStationMgrLike(String value) {
            addCriterion("station_mgr like", value, "stationMgr");
            return (Criteria) this;
        }

        public Criteria andStationMgrNotLike(String value) {
            addCriterion("station_mgr not like", value, "stationMgr");
            return (Criteria) this;
        }

        public Criteria andStationMgrIn(List<String> values) {
            addCriterion("station_mgr in", values, "stationMgr");
            return (Criteria) this;
        }

        public Criteria andStationMgrNotIn(List<String> values) {
            addCriterion("station_mgr not in", values, "stationMgr");
            return (Criteria) this;
        }

        public Criteria andStationMgrBetween(String value1, String value2) {
            addCriterion("station_mgr between", value1, value2, "stationMgr");
            return (Criteria) this;
        }

        public Criteria andStationMgrNotBetween(String value1, String value2) {
            addCriterion("station_mgr not between", value1, value2, "stationMgr");
            return (Criteria) this;
        }

        public Criteria andStationNameIsNull() {
            addCriterion("station_name is null");
            return (Criteria) this;
        }

        public Criteria andStationNameIsNotNull() {
            addCriterion("station_name is not null");
            return (Criteria) this;
        }

        public Criteria andStationNameEqualTo(String value) {
            addCriterion("station_name =", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotEqualTo(String value) {
            addCriterion("station_name <>", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameGreaterThan(String value) {
            addCriterion("station_name >", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameGreaterThanOrEqualTo(String value) {
            addCriterion("station_name >=", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameLessThan(String value) {
            addCriterion("station_name <", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameLessThanOrEqualTo(String value) {
            addCriterion("station_name <=", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameLike(String value) {
            addCriterion("station_name like", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotLike(String value) {
            addCriterion("station_name not like", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameIn(List<String> values) {
            addCriterion("station_name in", values, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotIn(List<String> values) {
            addCriterion("station_name not in", values, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameBetween(String value1, String value2) {
            addCriterion("station_name between", value1, value2, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotBetween(String value1, String value2) {
            addCriterion("station_name not between", value1, value2, "stationName");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(String value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(String value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(String value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(String value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(String value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(String value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLike(String value) {
            addCriterion("member_id like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotLike(String value) {
            addCriterion("member_id not like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<String> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<String> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(String value1, String value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(String value1, String value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andRecandsendGrowthRateIsNull() {
            addCriterion("recandsend_growth_rate is null");
            return (Criteria) this;
        }

        public Criteria andRecandsendGrowthRateIsNotNull() {
            addCriterion("recandsend_growth_rate is not null");
            return (Criteria) this;
        }

        public Criteria andRecandsendGrowthRateEqualTo(BigDecimal value) {
            addCriterion("recandsend_growth_rate =", value, "recandsendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRecandsendGrowthRateNotEqualTo(BigDecimal value) {
            addCriterion("recandsend_growth_rate <>", value, "recandsendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRecandsendGrowthRateGreaterThan(BigDecimal value) {
            addCriterion("recandsend_growth_rate >", value, "recandsendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRecandsendGrowthRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("recandsend_growth_rate >=", value, "recandsendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRecandsendGrowthRateLessThan(BigDecimal value) {
            addCriterion("recandsend_growth_rate <", value, "recandsendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRecandsendGrowthRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("recandsend_growth_rate <=", value, "recandsendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRecandsendGrowthRateIn(List<BigDecimal> values) {
            addCriterion("recandsend_growth_rate in", values, "recandsendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRecandsendGrowthRateNotIn(List<BigDecimal> values) {
            addCriterion("recandsend_growth_rate not in", values, "recandsendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRecandsendGrowthRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("recandsend_growth_rate between", value1, value2, "recandsendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRecandsendGrowthRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("recandsend_growth_rate not between", value1, value2, "recandsendGrowthRate");
            return (Criteria) this;
        }

        public Criteria andDayRequirementsIsNull() {
            addCriterion("day_requirements is null");
            return (Criteria) this;
        }

        public Criteria andDayRequirementsIsNotNull() {
            addCriterion("day_requirements is not null");
            return (Criteria) this;
        }

        public Criteria andDayRequirementsEqualTo(Long value) {
            addCriterion("day_requirements =", value, "dayRequirements");
            return (Criteria) this;
        }

        public Criteria andDayRequirementsNotEqualTo(Long value) {
            addCriterion("day_requirements <>", value, "dayRequirements");
            return (Criteria) this;
        }

        public Criteria andDayRequirementsGreaterThan(Long value) {
            addCriterion("day_requirements >", value, "dayRequirements");
            return (Criteria) this;
        }

        public Criteria andDayRequirementsGreaterThanOrEqualTo(Long value) {
            addCriterion("day_requirements >=", value, "dayRequirements");
            return (Criteria) this;
        }

        public Criteria andDayRequirementsLessThan(Long value) {
            addCriterion("day_requirements <", value, "dayRequirements");
            return (Criteria) this;
        }

        public Criteria andDayRequirementsLessThanOrEqualTo(Long value) {
            addCriterion("day_requirements <=", value, "dayRequirements");
            return (Criteria) this;
        }

        public Criteria andDayRequirementsIn(List<Long> values) {
            addCriterion("day_requirements in", values, "dayRequirements");
            return (Criteria) this;
        }

        public Criteria andDayRequirementsNotIn(List<Long> values) {
            addCriterion("day_requirements not in", values, "dayRequirements");
            return (Criteria) this;
        }

        public Criteria andDayRequirementsBetween(Long value1, Long value2) {
            addCriterion("day_requirements between", value1, value2, "dayRequirements");
            return (Criteria) this;
        }

        public Criteria andDayRequirementsNotBetween(Long value1, Long value2) {
            addCriterion("day_requirements not between", value1, value2, "dayRequirements");
            return (Criteria) this;
        }

        public Criteria andYundaexGradeIsNull() {
            addCriterion("yundaex_grade is null");
            return (Criteria) this;
        }

        public Criteria andYundaexGradeIsNotNull() {
            addCriterion("yundaex_grade is not null");
            return (Criteria) this;
        }

        public Criteria andYundaexGradeEqualTo(BigDecimal value) {
            addCriterion("yundaex_grade =", value, "yundaexGrade");
            return (Criteria) this;
        }

        public Criteria andYundaexGradeNotEqualTo(BigDecimal value) {
            addCriterion("yundaex_grade <>", value, "yundaexGrade");
            return (Criteria) this;
        }

        public Criteria andYundaexGradeGreaterThan(BigDecimal value) {
            addCriterion("yundaex_grade >", value, "yundaexGrade");
            return (Criteria) this;
        }

        public Criteria andYundaexGradeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("yundaex_grade >=", value, "yundaexGrade");
            return (Criteria) this;
        }

        public Criteria andYundaexGradeLessThan(BigDecimal value) {
            addCriterion("yundaex_grade <", value, "yundaexGrade");
            return (Criteria) this;
        }

        public Criteria andYundaexGradeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("yundaex_grade <=", value, "yundaexGrade");
            return (Criteria) this;
        }

        public Criteria andYundaexGradeIn(List<BigDecimal> values) {
            addCriterion("yundaex_grade in", values, "yundaexGrade");
            return (Criteria) this;
        }

        public Criteria andYundaexGradeNotIn(List<BigDecimal> values) {
            addCriterion("yundaex_grade not in", values, "yundaexGrade");
            return (Criteria) this;
        }

        public Criteria andYundaexGradeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("yundaex_grade between", value1, value2, "yundaexGrade");
            return (Criteria) this;
        }

        public Criteria andYundaexGradeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("yundaex_grade not between", value1, value2, "yundaexGrade");
            return (Criteria) this;
        }

        public Criteria andLastYundaexGradeIsNull() {
            addCriterion("last_yundaex_grade is null");
            return (Criteria) this;
        }

        public Criteria andLastYundaexGradeIsNotNull() {
            addCriterion("last_yundaex_grade is not null");
            return (Criteria) this;
        }

        public Criteria andLastYundaexGradeEqualTo(BigDecimal value) {
            addCriterion("last_yundaex_grade =", value, "lastYundaexGrade");
            return (Criteria) this;
        }

        public Criteria andLastYundaexGradeNotEqualTo(BigDecimal value) {
            addCriterion("last_yundaex_grade <>", value, "lastYundaexGrade");
            return (Criteria) this;
        }

        public Criteria andLastYundaexGradeGreaterThan(BigDecimal value) {
            addCriterion("last_yundaex_grade >", value, "lastYundaexGrade");
            return (Criteria) this;
        }

        public Criteria andLastYundaexGradeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("last_yundaex_grade >=", value, "lastYundaexGrade");
            return (Criteria) this;
        }

        public Criteria andLastYundaexGradeLessThan(BigDecimal value) {
            addCriterion("last_yundaex_grade <", value, "lastYundaexGrade");
            return (Criteria) this;
        }

        public Criteria andLastYundaexGradeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("last_yundaex_grade <=", value, "lastYundaexGrade");
            return (Criteria) this;
        }

        public Criteria andLastYundaexGradeIn(List<BigDecimal> values) {
            addCriterion("last_yundaex_grade in", values, "lastYundaexGrade");
            return (Criteria) this;
        }

        public Criteria andLastYundaexGradeNotIn(List<BigDecimal> values) {
            addCriterion("last_yundaex_grade not in", values, "lastYundaexGrade");
            return (Criteria) this;
        }

        public Criteria andLastYundaexGradeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("last_yundaex_grade between", value1, value2, "lastYundaexGrade");
            return (Criteria) this;
        }

        public Criteria andLastYundaexGradeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("last_yundaex_grade not between", value1, value2, "lastYundaexGrade");
            return (Criteria) this;
        }

        public Criteria andBailBalanceIsNull() {
            addCriterion("bail_balance is null");
            return (Criteria) this;
        }

        public Criteria andBailBalanceIsNotNull() {
            addCriterion("bail_balance is not null");
            return (Criteria) this;
        }

        public Criteria andBailBalanceEqualTo(Long value) {
            addCriterion("bail_balance =", value, "bailBalance");
            return (Criteria) this;
        }

        public Criteria andBailBalanceNotEqualTo(Long value) {
            addCriterion("bail_balance <>", value, "bailBalance");
            return (Criteria) this;
        }

        public Criteria andBailBalanceGreaterThan(Long value) {
            addCriterion("bail_balance >", value, "bailBalance");
            return (Criteria) this;
        }

        public Criteria andBailBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("bail_balance >=", value, "bailBalance");
            return (Criteria) this;
        }

        public Criteria andBailBalanceLessThan(Long value) {
            addCriterion("bail_balance <", value, "bailBalance");
            return (Criteria) this;
        }

        public Criteria andBailBalanceLessThanOrEqualTo(Long value) {
            addCriterion("bail_balance <=", value, "bailBalance");
            return (Criteria) this;
        }

        public Criteria andBailBalanceIn(List<Long> values) {
            addCriterion("bail_balance in", values, "bailBalance");
            return (Criteria) this;
        }

        public Criteria andBailBalanceNotIn(List<Long> values) {
            addCriterion("bail_balance not in", values, "bailBalance");
            return (Criteria) this;
        }

        public Criteria andBailBalanceBetween(Long value1, Long value2) {
            addCriterion("bail_balance between", value1, value2, "bailBalance");
            return (Criteria) this;
        }

        public Criteria andBailBalanceNotBetween(Long value1, Long value2) {
            addCriterion("bail_balance not between", value1, value2, "bailBalance");
            return (Criteria) this;
        }

        public Criteria andNetCashflowIsNull() {
            addCriterion("net_cashflow is null");
            return (Criteria) this;
        }

        public Criteria andNetCashflowIsNotNull() {
            addCriterion("net_cashflow is not null");
            return (Criteria) this;
        }

        public Criteria andNetCashflowEqualTo(Long value) {
            addCriterion("net_cashflow =", value, "netCashflow");
            return (Criteria) this;
        }

        public Criteria andNetCashflowNotEqualTo(Long value) {
            addCriterion("net_cashflow <>", value, "netCashflow");
            return (Criteria) this;
        }

        public Criteria andNetCashflowGreaterThan(Long value) {
            addCriterion("net_cashflow >", value, "netCashflow");
            return (Criteria) this;
        }

        public Criteria andNetCashflowGreaterThanOrEqualTo(Long value) {
            addCriterion("net_cashflow >=", value, "netCashflow");
            return (Criteria) this;
        }

        public Criteria andNetCashflowLessThan(Long value) {
            addCriterion("net_cashflow <", value, "netCashflow");
            return (Criteria) this;
        }

        public Criteria andNetCashflowLessThanOrEqualTo(Long value) {
            addCriterion("net_cashflow <=", value, "netCashflow");
            return (Criteria) this;
        }

        public Criteria andNetCashflowIn(List<Long> values) {
            addCriterion("net_cashflow in", values, "netCashflow");
            return (Criteria) this;
        }

        public Criteria andNetCashflowNotIn(List<Long> values) {
            addCriterion("net_cashflow not in", values, "netCashflow");
            return (Criteria) this;
        }

        public Criteria andNetCashflowBetween(Long value1, Long value2) {
            addCriterion("net_cashflow between", value1, value2, "netCashflow");
            return (Criteria) this;
        }

        public Criteria andNetCashflowNotBetween(Long value1, Long value2) {
            addCriterion("net_cashflow not between", value1, value2, "netCashflow");
            return (Criteria) this;
        }

        public Criteria andCreditLimitIsNull() {
            addCriterion("credit_limit is null");
            return (Criteria) this;
        }

        public Criteria andCreditLimitIsNotNull() {
            addCriterion("credit_limit is not null");
            return (Criteria) this;
        }

        public Criteria andCreditLimitEqualTo(Long value) {
            addCriterion("credit_limit =", value, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitNotEqualTo(Long value) {
            addCriterion("credit_limit <>", value, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGreaterThan(Long value) {
            addCriterion("credit_limit >", value, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("credit_limit >=", value, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitLessThan(Long value) {
            addCriterion("credit_limit <", value, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitLessThanOrEqualTo(Long value) {
            addCriterion("credit_limit <=", value, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitIn(List<Long> values) {
            addCriterion("credit_limit in", values, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitNotIn(List<Long> values) {
            addCriterion("credit_limit not in", values, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitBetween(Long value1, Long value2) {
            addCriterion("credit_limit between", value1, value2, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitNotBetween(Long value1, Long value2) {
            addCriterion("credit_limit not between", value1, value2, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andLastCreditLimitIsNull() {
            addCriterion("last_credit_limit is null");
            return (Criteria) this;
        }

        public Criteria andLastCreditLimitIsNotNull() {
            addCriterion("last_credit_limit is not null");
            return (Criteria) this;
        }

        public Criteria andLastCreditLimitEqualTo(Long value) {
            addCriterion("last_credit_limit =", value, "lastCreditLimit");
            return (Criteria) this;
        }

        public Criteria andLastCreditLimitNotEqualTo(Long value) {
            addCriterion("last_credit_limit <>", value, "lastCreditLimit");
            return (Criteria) this;
        }

        public Criteria andLastCreditLimitGreaterThan(Long value) {
            addCriterion("last_credit_limit >", value, "lastCreditLimit");
            return (Criteria) this;
        }

        public Criteria andLastCreditLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("last_credit_limit >=", value, "lastCreditLimit");
            return (Criteria) this;
        }

        public Criteria andLastCreditLimitLessThan(Long value) {
            addCriterion("last_credit_limit <", value, "lastCreditLimit");
            return (Criteria) this;
        }

        public Criteria andLastCreditLimitLessThanOrEqualTo(Long value) {
            addCriterion("last_credit_limit <=", value, "lastCreditLimit");
            return (Criteria) this;
        }

        public Criteria andLastCreditLimitIn(List<Long> values) {
            addCriterion("last_credit_limit in", values, "lastCreditLimit");
            return (Criteria) this;
        }

        public Criteria andLastCreditLimitNotIn(List<Long> values) {
            addCriterion("last_credit_limit not in", values, "lastCreditLimit");
            return (Criteria) this;
        }

        public Criteria andLastCreditLimitBetween(Long value1, Long value2) {
            addCriterion("last_credit_limit between", value1, value2, "lastCreditLimit");
            return (Criteria) this;
        }

        public Criteria andLastCreditLimitNotBetween(Long value1, Long value2) {
            addCriterion("last_credit_limit not between", value1, value2, "lastCreditLimit");
            return (Criteria) this;
        }

        public Criteria andOverduesIsNull() {
            addCriterion("overdues is null");
            return (Criteria) this;
        }

        public Criteria andOverduesIsNotNull() {
            addCriterion("overdues is not null");
            return (Criteria) this;
        }

        public Criteria andOverduesEqualTo(Integer value) {
            addCriterion("overdues =", value, "overdues");
            return (Criteria) this;
        }

        public Criteria andOverduesNotEqualTo(Integer value) {
            addCriterion("overdues <>", value, "overdues");
            return (Criteria) this;
        }

        public Criteria andOverduesGreaterThan(Integer value) {
            addCriterion("overdues >", value, "overdues");
            return (Criteria) this;
        }

        public Criteria andOverduesGreaterThanOrEqualTo(Integer value) {
            addCriterion("overdues >=", value, "overdues");
            return (Criteria) this;
        }

        public Criteria andOverduesLessThan(Integer value) {
            addCriterion("overdues <", value, "overdues");
            return (Criteria) this;
        }

        public Criteria andOverduesLessThanOrEqualTo(Integer value) {
            addCriterion("overdues <=", value, "overdues");
            return (Criteria) this;
        }

        public Criteria andOverduesIn(List<Integer> values) {
            addCriterion("overdues in", values, "overdues");
            return (Criteria) this;
        }

        public Criteria andOverduesNotIn(List<Integer> values) {
            addCriterion("overdues not in", values, "overdues");
            return (Criteria) this;
        }

        public Criteria andOverduesBetween(Integer value1, Integer value2) {
            addCriterion("overdues between", value1, value2, "overdues");
            return (Criteria) this;
        }

        public Criteria andOverduesNotBetween(Integer value1, Integer value2) {
            addCriterion("overdues not between", value1, value2, "overdues");
            return (Criteria) this;
        }

        public Criteria andInterestRateIsNull() {
            addCriterion("interest_rate is null");
            return (Criteria) this;
        }

        public Criteria andInterestRateIsNotNull() {
            addCriterion("interest_rate is not null");
            return (Criteria) this;
        }

        public Criteria andInterestRateEqualTo(String value) {
            addCriterion("interest_rate =", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateNotEqualTo(String value) {
            addCriterion("interest_rate <>", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateGreaterThan(String value) {
            addCriterion("interest_rate >", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateGreaterThanOrEqualTo(String value) {
            addCriterion("interest_rate >=", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateLessThan(String value) {
            addCriterion("interest_rate <", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateLessThanOrEqualTo(String value) {
            addCriterion("interest_rate <=", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateLike(String value) {
            addCriterion("interest_rate like", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateNotLike(String value) {
            addCriterion("interest_rate not like", value, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateIn(List<String> values) {
            addCriterion("interest_rate in", values, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateNotIn(List<String> values) {
            addCriterion("interest_rate not in", values, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateBetween(String value1, String value2) {
            addCriterion("interest_rate between", value1, value2, "interestRate");
            return (Criteria) this;
        }

        public Criteria andInterestRateNotBetween(String value1, String value2) {
            addCriterion("interest_rate not between", value1, value2, "interestRate");
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

        public Criteria andAuditStatusIsNull() {
            addCriterion("audit_status is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNotNull() {
            addCriterion("audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusEqualTo(String value) {
            addCriterion("audit_status =", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotEqualTo(String value) {
            addCriterion("audit_status <>", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThan(String value) {
            addCriterion("audit_status >", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("audit_status >=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThan(String value) {
            addCriterion("audit_status <", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("audit_status <=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLike(String value) {
            addCriterion("audit_status like", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotLike(String value) {
            addCriterion("audit_status not like", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIn(List<String> values) {
            addCriterion("audit_status in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotIn(List<String> values) {
            addCriterion("audit_status not in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusBetween(String value1, String value2) {
            addCriterion("audit_status between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotBetween(String value1, String value2) {
            addCriterion("audit_status not between", value1, value2, "auditStatus");
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