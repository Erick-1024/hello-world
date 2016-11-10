package com.cana.yundaex.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YundaexCustomerApplyMonitorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public YundaexCustomerApplyMonitorExample() {
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

        public Criteria andStationAmountIsNull() {
            addCriterion("station_amount is null");
            return (Criteria) this;
        }

        public Criteria andStationAmountIsNotNull() {
            addCriterion("station_amount is not null");
            return (Criteria) this;
        }

        public Criteria andStationAmountEqualTo(Integer value) {
            addCriterion("station_amount =", value, "stationAmount");
            return (Criteria) this;
        }

        public Criteria andStationAmountNotEqualTo(Integer value) {
            addCriterion("station_amount <>", value, "stationAmount");
            return (Criteria) this;
        }

        public Criteria andStationAmountGreaterThan(Integer value) {
            addCriterion("station_amount >", value, "stationAmount");
            return (Criteria) this;
        }

        public Criteria andStationAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("station_amount >=", value, "stationAmount");
            return (Criteria) this;
        }

        public Criteria andStationAmountLessThan(Integer value) {
            addCriterion("station_amount <", value, "stationAmount");
            return (Criteria) this;
        }

        public Criteria andStationAmountLessThanOrEqualTo(Integer value) {
            addCriterion("station_amount <=", value, "stationAmount");
            return (Criteria) this;
        }

        public Criteria andStationAmountIn(List<Integer> values) {
            addCriterion("station_amount in", values, "stationAmount");
            return (Criteria) this;
        }

        public Criteria andStationAmountNotIn(List<Integer> values) {
            addCriterion("station_amount not in", values, "stationAmount");
            return (Criteria) this;
        }

        public Criteria andStationAmountBetween(Integer value1, Integer value2) {
            addCriterion("station_amount between", value1, value2, "stationAmount");
            return (Criteria) this;
        }

        public Criteria andStationAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("station_amount not between", value1, value2, "stationAmount");
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

        public Criteria andBusiLimitIsNull() {
            addCriterion("busi_limit is null");
            return (Criteria) this;
        }

        public Criteria andBusiLimitIsNotNull() {
            addCriterion("busi_limit is not null");
            return (Criteria) this;
        }

        public Criteria andBusiLimitEqualTo(Long value) {
            addCriterion("busi_limit =", value, "busiLimit");
            return (Criteria) this;
        }

        public Criteria andBusiLimitNotEqualTo(Long value) {
            addCriterion("busi_limit <>", value, "busiLimit");
            return (Criteria) this;
        }

        public Criteria andBusiLimitGreaterThan(Long value) {
            addCriterion("busi_limit >", value, "busiLimit");
            return (Criteria) this;
        }

        public Criteria andBusiLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("busi_limit >=", value, "busiLimit");
            return (Criteria) this;
        }

        public Criteria andBusiLimitLessThan(Long value) {
            addCriterion("busi_limit <", value, "busiLimit");
            return (Criteria) this;
        }

        public Criteria andBusiLimitLessThanOrEqualTo(Long value) {
            addCriterion("busi_limit <=", value, "busiLimit");
            return (Criteria) this;
        }

        public Criteria andBusiLimitIn(List<Long> values) {
            addCriterion("busi_limit in", values, "busiLimit");
            return (Criteria) this;
        }

        public Criteria andBusiLimitNotIn(List<Long> values) {
            addCriterion("busi_limit not in", values, "busiLimit");
            return (Criteria) this;
        }

        public Criteria andBusiLimitBetween(Long value1, Long value2) {
            addCriterion("busi_limit between", value1, value2, "busiLimit");
            return (Criteria) this;
        }

        public Criteria andBusiLimitNotBetween(Long value1, Long value2) {
            addCriterion("busi_limit not between", value1, value2, "busiLimit");
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

        public Criteria andShortLoanIsNull() {
            addCriterion("short_loan is null");
            return (Criteria) this;
        }

        public Criteria andShortLoanIsNotNull() {
            addCriterion("short_loan is not null");
            return (Criteria) this;
        }

        public Criteria andShortLoanEqualTo(Long value) {
            addCriterion("short_loan =", value, "shortLoan");
            return (Criteria) this;
        }

        public Criteria andShortLoanNotEqualTo(Long value) {
            addCriterion("short_loan <>", value, "shortLoan");
            return (Criteria) this;
        }

        public Criteria andShortLoanGreaterThan(Long value) {
            addCriterion("short_loan >", value, "shortLoan");
            return (Criteria) this;
        }

        public Criteria andShortLoanGreaterThanOrEqualTo(Long value) {
            addCriterion("short_loan >=", value, "shortLoan");
            return (Criteria) this;
        }

        public Criteria andShortLoanLessThan(Long value) {
            addCriterion("short_loan <", value, "shortLoan");
            return (Criteria) this;
        }

        public Criteria andShortLoanLessThanOrEqualTo(Long value) {
            addCriterion("short_loan <=", value, "shortLoan");
            return (Criteria) this;
        }

        public Criteria andShortLoanIn(List<Long> values) {
            addCriterion("short_loan in", values, "shortLoan");
            return (Criteria) this;
        }

        public Criteria andShortLoanNotIn(List<Long> values) {
            addCriterion("short_loan not in", values, "shortLoan");
            return (Criteria) this;
        }

        public Criteria andShortLoanBetween(Long value1, Long value2) {
            addCriterion("short_loan between", value1, value2, "shortLoan");
            return (Criteria) this;
        }

        public Criteria andShortLoanNotBetween(Long value1, Long value2) {
            addCriterion("short_loan not between", value1, value2, "shortLoan");
            return (Criteria) this;
        }

        public Criteria andLoanTypeIsNull() {
            addCriterion("loan_type is null");
            return (Criteria) this;
        }

        public Criteria andLoanTypeIsNotNull() {
            addCriterion("loan_type is not null");
            return (Criteria) this;
        }

        public Criteria andLoanTypeEqualTo(String value) {
            addCriterion("loan_type =", value, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNotEqualTo(String value) {
            addCriterion("loan_type <>", value, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeGreaterThan(String value) {
            addCriterion("loan_type >", value, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeGreaterThanOrEqualTo(String value) {
            addCriterion("loan_type >=", value, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeLessThan(String value) {
            addCriterion("loan_type <", value, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeLessThanOrEqualTo(String value) {
            addCriterion("loan_type <=", value, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeLike(String value) {
            addCriterion("loan_type like", value, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNotLike(String value) {
            addCriterion("loan_type not like", value, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeIn(List<String> values) {
            addCriterion("loan_type in", values, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNotIn(List<String> values) {
            addCriterion("loan_type not in", values, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeBetween(String value1, String value2) {
            addCriterion("loan_type between", value1, value2, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNotBetween(String value1, String value2) {
            addCriterion("loan_type not between", value1, value2, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanLimitIsNull() {
            addCriterion("loan_limit is null");
            return (Criteria) this;
        }

        public Criteria andLoanLimitIsNotNull() {
            addCriterion("loan_limit is not null");
            return (Criteria) this;
        }

        public Criteria andLoanLimitEqualTo(Integer value) {
            addCriterion("loan_limit =", value, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitNotEqualTo(Integer value) {
            addCriterion("loan_limit <>", value, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitGreaterThan(Integer value) {
            addCriterion("loan_limit >", value, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("loan_limit >=", value, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitLessThan(Integer value) {
            addCriterion("loan_limit <", value, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitLessThanOrEqualTo(Integer value) {
            addCriterion("loan_limit <=", value, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitIn(List<Integer> values) {
            addCriterion("loan_limit in", values, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitNotIn(List<Integer> values) {
            addCriterion("loan_limit not in", values, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitBetween(Integer value1, Integer value2) {
            addCriterion("loan_limit between", value1, value2, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("loan_limit not between", value1, value2, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLimitUnitIsNull() {
            addCriterion("limit_unit is null");
            return (Criteria) this;
        }

        public Criteria andLimitUnitIsNotNull() {
            addCriterion("limit_unit is not null");
            return (Criteria) this;
        }

        public Criteria andLimitUnitEqualTo(String value) {
            addCriterion("limit_unit =", value, "limitUnit");
            return (Criteria) this;
        }

        public Criteria andLimitUnitNotEqualTo(String value) {
            addCriterion("limit_unit <>", value, "limitUnit");
            return (Criteria) this;
        }

        public Criteria andLimitUnitGreaterThan(String value) {
            addCriterion("limit_unit >", value, "limitUnit");
            return (Criteria) this;
        }

        public Criteria andLimitUnitGreaterThanOrEqualTo(String value) {
            addCriterion("limit_unit >=", value, "limitUnit");
            return (Criteria) this;
        }

        public Criteria andLimitUnitLessThan(String value) {
            addCriterion("limit_unit <", value, "limitUnit");
            return (Criteria) this;
        }

        public Criteria andLimitUnitLessThanOrEqualTo(String value) {
            addCriterion("limit_unit <=", value, "limitUnit");
            return (Criteria) this;
        }

        public Criteria andLimitUnitLike(String value) {
            addCriterion("limit_unit like", value, "limitUnit");
            return (Criteria) this;
        }

        public Criteria andLimitUnitNotLike(String value) {
            addCriterion("limit_unit not like", value, "limitUnit");
            return (Criteria) this;
        }

        public Criteria andLimitUnitIn(List<String> values) {
            addCriterion("limit_unit in", values, "limitUnit");
            return (Criteria) this;
        }

        public Criteria andLimitUnitNotIn(List<String> values) {
            addCriterion("limit_unit not in", values, "limitUnit");
            return (Criteria) this;
        }

        public Criteria andLimitUnitBetween(String value1, String value2) {
            addCriterion("limit_unit between", value1, value2, "limitUnit");
            return (Criteria) this;
        }

        public Criteria andLimitUnitNotBetween(String value1, String value2) {
            addCriterion("limit_unit not between", value1, value2, "limitUnit");
            return (Criteria) this;
        }

        public Criteria andYundaexJudgeIsNull() {
            addCriterion("yundaex_judge is null");
            return (Criteria) this;
        }

        public Criteria andYundaexJudgeIsNotNull() {
            addCriterion("yundaex_judge is not null");
            return (Criteria) this;
        }

        public Criteria andYundaexJudgeEqualTo(String value) {
            addCriterion("yundaex_judge =", value, "yundaexJudge");
            return (Criteria) this;
        }

        public Criteria andYundaexJudgeNotEqualTo(String value) {
            addCriterion("yundaex_judge <>", value, "yundaexJudge");
            return (Criteria) this;
        }

        public Criteria andYundaexJudgeGreaterThan(String value) {
            addCriterion("yundaex_judge >", value, "yundaexJudge");
            return (Criteria) this;
        }

        public Criteria andYundaexJudgeGreaterThanOrEqualTo(String value) {
            addCriterion("yundaex_judge >=", value, "yundaexJudge");
            return (Criteria) this;
        }

        public Criteria andYundaexJudgeLessThan(String value) {
            addCriterion("yundaex_judge <", value, "yundaexJudge");
            return (Criteria) this;
        }

        public Criteria andYundaexJudgeLessThanOrEqualTo(String value) {
            addCriterion("yundaex_judge <=", value, "yundaexJudge");
            return (Criteria) this;
        }

        public Criteria andYundaexJudgeLike(String value) {
            addCriterion("yundaex_judge like", value, "yundaexJudge");
            return (Criteria) this;
        }

        public Criteria andYundaexJudgeNotLike(String value) {
            addCriterion("yundaex_judge not like", value, "yundaexJudge");
            return (Criteria) this;
        }

        public Criteria andYundaexJudgeIn(List<String> values) {
            addCriterion("yundaex_judge in", values, "yundaexJudge");
            return (Criteria) this;
        }

        public Criteria andYundaexJudgeNotIn(List<String> values) {
            addCriterion("yundaex_judge not in", values, "yundaexJudge");
            return (Criteria) this;
        }

        public Criteria andYundaexJudgeBetween(String value1, String value2) {
            addCriterion("yundaex_judge between", value1, value2, "yundaexJudge");
            return (Criteria) this;
        }

        public Criteria andYundaexJudgeNotBetween(String value1, String value2) {
            addCriterion("yundaex_judge not between", value1, value2, "yundaexJudge");
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