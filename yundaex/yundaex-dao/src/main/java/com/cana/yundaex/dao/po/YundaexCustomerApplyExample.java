package com.cana.yundaex.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YundaexCustomerApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public YundaexCustomerApplyExample() {
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

        public Criteria andApplyDateIsNull() {
            addCriterion("apply_date is null");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNotNull() {
            addCriterion("apply_date is not null");
            return (Criteria) this;
        }

        public Criteria andApplyDateEqualTo(Date value) {
            addCriterion("apply_date =", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotEqualTo(Date value) {
            addCriterion("apply_date <>", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThan(Date value) {
            addCriterion("apply_date >", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThanOrEqualTo(Date value) {
            addCriterion("apply_date >=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThan(Date value) {
            addCriterion("apply_date <", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThanOrEqualTo(Date value) {
            addCriterion("apply_date <=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateIn(List<Date> values) {
            addCriterion("apply_date in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotIn(List<Date> values) {
            addCriterion("apply_date not in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateBetween(Date value1, Date value2) {
            addCriterion("apply_date between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotBetween(Date value1, Date value2) {
            addCriterion("apply_date not between", value1, value2, "applyDate");
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

        public Criteria andCustNameIsNull() {
            addCriterion("cust_name is null");
            return (Criteria) this;
        }

        public Criteria andCustNameIsNotNull() {
            addCriterion("cust_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustNameEqualTo(String value) {
            addCriterion("cust_name =", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotEqualTo(String value) {
            addCriterion("cust_name <>", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameGreaterThan(String value) {
            addCriterion("cust_name >", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameGreaterThanOrEqualTo(String value) {
            addCriterion("cust_name >=", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameLessThan(String value) {
            addCriterion("cust_name <", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameLessThanOrEqualTo(String value) {
            addCriterion("cust_name <=", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameLike(String value) {
            addCriterion("cust_name like", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotLike(String value) {
            addCriterion("cust_name not like", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameIn(List<String> values) {
            addCriterion("cust_name in", values, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotIn(List<String> values) {
            addCriterion("cust_name not in", values, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameBetween(String value1, String value2) {
            addCriterion("cust_name between", value1, value2, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotBetween(String value1, String value2) {
            addCriterion("cust_name not between", value1, value2, "custName");
            return (Criteria) this;
        }

        public Criteria andCustIdnoIsNull() {
            addCriterion("cust_idno is null");
            return (Criteria) this;
        }

        public Criteria andCustIdnoIsNotNull() {
            addCriterion("cust_idno is not null");
            return (Criteria) this;
        }

        public Criteria andCustIdnoEqualTo(String value) {
            addCriterion("cust_idno =", value, "custIdno");
            return (Criteria) this;
        }

        public Criteria andCustIdnoNotEqualTo(String value) {
            addCriterion("cust_idno <>", value, "custIdno");
            return (Criteria) this;
        }

        public Criteria andCustIdnoGreaterThan(String value) {
            addCriterion("cust_idno >", value, "custIdno");
            return (Criteria) this;
        }

        public Criteria andCustIdnoGreaterThanOrEqualTo(String value) {
            addCriterion("cust_idno >=", value, "custIdno");
            return (Criteria) this;
        }

        public Criteria andCustIdnoLessThan(String value) {
            addCriterion("cust_idno <", value, "custIdno");
            return (Criteria) this;
        }

        public Criteria andCustIdnoLessThanOrEqualTo(String value) {
            addCriterion("cust_idno <=", value, "custIdno");
            return (Criteria) this;
        }

        public Criteria andCustIdnoLike(String value) {
            addCriterion("cust_idno like", value, "custIdno");
            return (Criteria) this;
        }

        public Criteria andCustIdnoNotLike(String value) {
            addCriterion("cust_idno not like", value, "custIdno");
            return (Criteria) this;
        }

        public Criteria andCustIdnoIn(List<String> values) {
            addCriterion("cust_idno in", values, "custIdno");
            return (Criteria) this;
        }

        public Criteria andCustIdnoNotIn(List<String> values) {
            addCriterion("cust_idno not in", values, "custIdno");
            return (Criteria) this;
        }

        public Criteria andCustIdnoBetween(String value1, String value2) {
            addCriterion("cust_idno between", value1, value2, "custIdno");
            return (Criteria) this;
        }

        public Criteria andCustIdnoNotBetween(String value1, String value2) {
            addCriterion("cust_idno not between", value1, value2, "custIdno");
            return (Criteria) this;
        }

        public Criteria andCustPhoneIsNull() {
            addCriterion("cust_phone is null");
            return (Criteria) this;
        }

        public Criteria andCustPhoneIsNotNull() {
            addCriterion("cust_phone is not null");
            return (Criteria) this;
        }

        public Criteria andCustPhoneEqualTo(String value) {
            addCriterion("cust_phone =", value, "custPhone");
            return (Criteria) this;
        }

        public Criteria andCustPhoneNotEqualTo(String value) {
            addCriterion("cust_phone <>", value, "custPhone");
            return (Criteria) this;
        }

        public Criteria andCustPhoneGreaterThan(String value) {
            addCriterion("cust_phone >", value, "custPhone");
            return (Criteria) this;
        }

        public Criteria andCustPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("cust_phone >=", value, "custPhone");
            return (Criteria) this;
        }

        public Criteria andCustPhoneLessThan(String value) {
            addCriterion("cust_phone <", value, "custPhone");
            return (Criteria) this;
        }

        public Criteria andCustPhoneLessThanOrEqualTo(String value) {
            addCriterion("cust_phone <=", value, "custPhone");
            return (Criteria) this;
        }

        public Criteria andCustPhoneLike(String value) {
            addCriterion("cust_phone like", value, "custPhone");
            return (Criteria) this;
        }

        public Criteria andCustPhoneNotLike(String value) {
            addCriterion("cust_phone not like", value, "custPhone");
            return (Criteria) this;
        }

        public Criteria andCustPhoneIn(List<String> values) {
            addCriterion("cust_phone in", values, "custPhone");
            return (Criteria) this;
        }

        public Criteria andCustPhoneNotIn(List<String> values) {
            addCriterion("cust_phone not in", values, "custPhone");
            return (Criteria) this;
        }

        public Criteria andCustPhoneBetween(String value1, String value2) {
            addCriterion("cust_phone between", value1, value2, "custPhone");
            return (Criteria) this;
        }

        public Criteria andCustPhoneNotBetween(String value1, String value2) {
            addCriterion("cust_phone not between", value1, value2, "custPhone");
            return (Criteria) this;
        }

        public Criteria andCustEmailIsNull() {
            addCriterion("cust_email is null");
            return (Criteria) this;
        }

        public Criteria andCustEmailIsNotNull() {
            addCriterion("cust_email is not null");
            return (Criteria) this;
        }

        public Criteria andCustEmailEqualTo(String value) {
            addCriterion("cust_email =", value, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailNotEqualTo(String value) {
            addCriterion("cust_email <>", value, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailGreaterThan(String value) {
            addCriterion("cust_email >", value, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailGreaterThanOrEqualTo(String value) {
            addCriterion("cust_email >=", value, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailLessThan(String value) {
            addCriterion("cust_email <", value, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailLessThanOrEqualTo(String value) {
            addCriterion("cust_email <=", value, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailLike(String value) {
            addCriterion("cust_email like", value, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailNotLike(String value) {
            addCriterion("cust_email not like", value, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailIn(List<String> values) {
            addCriterion("cust_email in", values, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailNotIn(List<String> values) {
            addCriterion("cust_email not in", values, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailBetween(String value1, String value2) {
            addCriterion("cust_email between", value1, value2, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailNotBetween(String value1, String value2) {
            addCriterion("cust_email not between", value1, value2, "custEmail");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
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

        public Criteria andRegioncodeIsNull() {
            addCriterion("regioncode is null");
            return (Criteria) this;
        }

        public Criteria andRegioncodeIsNotNull() {
            addCriterion("regioncode is not null");
            return (Criteria) this;
        }

        public Criteria andRegioncodeEqualTo(String value) {
            addCriterion("regioncode =", value, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeNotEqualTo(String value) {
            addCriterion("regioncode <>", value, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeGreaterThan(String value) {
            addCriterion("regioncode >", value, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeGreaterThanOrEqualTo(String value) {
            addCriterion("regioncode >=", value, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeLessThan(String value) {
            addCriterion("regioncode <", value, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeLessThanOrEqualTo(String value) {
            addCriterion("regioncode <=", value, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeLike(String value) {
            addCriterion("regioncode like", value, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeNotLike(String value) {
            addCriterion("regioncode not like", value, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeIn(List<String> values) {
            addCriterion("regioncode in", values, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeNotIn(List<String> values) {
            addCriterion("regioncode not in", values, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeBetween(String value1, String value2) {
            addCriterion("regioncode between", value1, value2, "regioncode");
            return (Criteria) this;
        }

        public Criteria andRegioncodeNotBetween(String value1, String value2) {
            addCriterion("regioncode not between", value1, value2, "regioncode");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitIsNull() {
            addCriterion("apply_credit_limit is null");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitIsNotNull() {
            addCriterion("apply_credit_limit is not null");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitEqualTo(Long value) {
            addCriterion("apply_credit_limit =", value, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitNotEqualTo(Long value) {
            addCriterion("apply_credit_limit <>", value, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitGreaterThan(Long value) {
            addCriterion("apply_credit_limit >", value, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("apply_credit_limit >=", value, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitLessThan(Long value) {
            addCriterion("apply_credit_limit <", value, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitLessThanOrEqualTo(Long value) {
            addCriterion("apply_credit_limit <=", value, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitIn(List<Long> values) {
            addCriterion("apply_credit_limit in", values, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitNotIn(List<Long> values) {
            addCriterion("apply_credit_limit not in", values, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitBetween(Long value1, Long value2) {
            addCriterion("apply_credit_limit between", value1, value2, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andApplyCreditLimitNotBetween(Long value1, Long value2) {
            addCriterion("apply_credit_limit not between", value1, value2, "applyCreditLimit");
            return (Criteria) this;
        }

        public Criteria andFundsUseIsNull() {
            addCriterion("funds_use is null");
            return (Criteria) this;
        }

        public Criteria andFundsUseIsNotNull() {
            addCriterion("funds_use is not null");
            return (Criteria) this;
        }

        public Criteria andFundsUseEqualTo(String value) {
            addCriterion("funds_use =", value, "fundsUse");
            return (Criteria) this;
        }

        public Criteria andFundsUseNotEqualTo(String value) {
            addCriterion("funds_use <>", value, "fundsUse");
            return (Criteria) this;
        }

        public Criteria andFundsUseGreaterThan(String value) {
            addCriterion("funds_use >", value, "fundsUse");
            return (Criteria) this;
        }

        public Criteria andFundsUseGreaterThanOrEqualTo(String value) {
            addCriterion("funds_use >=", value, "fundsUse");
            return (Criteria) this;
        }

        public Criteria andFundsUseLessThan(String value) {
            addCriterion("funds_use <", value, "fundsUse");
            return (Criteria) this;
        }

        public Criteria andFundsUseLessThanOrEqualTo(String value) {
            addCriterion("funds_use <=", value, "fundsUse");
            return (Criteria) this;
        }

        public Criteria andFundsUseLike(String value) {
            addCriterion("funds_use like", value, "fundsUse");
            return (Criteria) this;
        }

        public Criteria andFundsUseNotLike(String value) {
            addCriterion("funds_use not like", value, "fundsUse");
            return (Criteria) this;
        }

        public Criteria andFundsUseIn(List<String> values) {
            addCriterion("funds_use in", values, "fundsUse");
            return (Criteria) this;
        }

        public Criteria andFundsUseNotIn(List<String> values) {
            addCriterion("funds_use not in", values, "fundsUse");
            return (Criteria) this;
        }

        public Criteria andFundsUseBetween(String value1, String value2) {
            addCriterion("funds_use between", value1, value2, "fundsUse");
            return (Criteria) this;
        }

        public Criteria andFundsUseNotBetween(String value1, String value2) {
            addCriterion("funds_use not between", value1, value2, "fundsUse");
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

        public Criteria andLoanLimitEqualTo(String value) {
            addCriterion("loan_limit =", value, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitNotEqualTo(String value) {
            addCriterion("loan_limit <>", value, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitGreaterThan(String value) {
            addCriterion("loan_limit >", value, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitGreaterThanOrEqualTo(String value) {
            addCriterion("loan_limit >=", value, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitLessThan(String value) {
            addCriterion("loan_limit <", value, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitLessThanOrEqualTo(String value) {
            addCriterion("loan_limit <=", value, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitLike(String value) {
            addCriterion("loan_limit like", value, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitNotLike(String value) {
            addCriterion("loan_limit not like", value, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitIn(List<String> values) {
            addCriterion("loan_limit in", values, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitNotIn(List<String> values) {
            addCriterion("loan_limit not in", values, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitBetween(String value1, String value2) {
            addCriterion("loan_limit between", value1, value2, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanLimitNotBetween(String value1, String value2) {
            addCriterion("loan_limit not between", value1, value2, "loanLimit");
            return (Criteria) this;
        }

        public Criteria andAddCreditIsNull() {
            addCriterion("add_credit is null");
            return (Criteria) this;
        }

        public Criteria andAddCreditIsNotNull() {
            addCriterion("add_credit is not null");
            return (Criteria) this;
        }

        public Criteria andAddCreditEqualTo(String value) {
            addCriterion("add_credit =", value, "addCredit");
            return (Criteria) this;
        }

        public Criteria andAddCreditNotEqualTo(String value) {
            addCriterion("add_credit <>", value, "addCredit");
            return (Criteria) this;
        }

        public Criteria andAddCreditGreaterThan(String value) {
            addCriterion("add_credit >", value, "addCredit");
            return (Criteria) this;
        }

        public Criteria andAddCreditGreaterThanOrEqualTo(String value) {
            addCriterion("add_credit >=", value, "addCredit");
            return (Criteria) this;
        }

        public Criteria andAddCreditLessThan(String value) {
            addCriterion("add_credit <", value, "addCredit");
            return (Criteria) this;
        }

        public Criteria andAddCreditLessThanOrEqualTo(String value) {
            addCriterion("add_credit <=", value, "addCredit");
            return (Criteria) this;
        }

        public Criteria andAddCreditLike(String value) {
            addCriterion("add_credit like", value, "addCredit");
            return (Criteria) this;
        }

        public Criteria andAddCreditNotLike(String value) {
            addCriterion("add_credit not like", value, "addCredit");
            return (Criteria) this;
        }

        public Criteria andAddCreditIn(List<String> values) {
            addCriterion("add_credit in", values, "addCredit");
            return (Criteria) this;
        }

        public Criteria andAddCreditNotIn(List<String> values) {
            addCriterion("add_credit not in", values, "addCredit");
            return (Criteria) this;
        }

        public Criteria andAddCreditBetween(String value1, String value2) {
            addCriterion("add_credit between", value1, value2, "addCredit");
            return (Criteria) this;
        }

        public Criteria andAddCreditNotBetween(String value1, String value2) {
            addCriterion("add_credit not between", value1, value2, "addCredit");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeIsNull() {
            addCriterion("repayment_type is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeIsNotNull() {
            addCriterion("repayment_type is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeEqualTo(String value) {
            addCriterion("repayment_type =", value, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeNotEqualTo(String value) {
            addCriterion("repayment_type <>", value, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeGreaterThan(String value) {
            addCriterion("repayment_type >", value, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeGreaterThanOrEqualTo(String value) {
            addCriterion("repayment_type >=", value, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeLessThan(String value) {
            addCriterion("repayment_type <", value, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeLessThanOrEqualTo(String value) {
            addCriterion("repayment_type <=", value, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeLike(String value) {
            addCriterion("repayment_type like", value, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeNotLike(String value) {
            addCriterion("repayment_type not like", value, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeIn(List<String> values) {
            addCriterion("repayment_type in", values, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeNotIn(List<String> values) {
            addCriterion("repayment_type not in", values, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeBetween(String value1, String value2) {
            addCriterion("repayment_type between", value1, value2, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeNotBetween(String value1, String value2) {
            addCriterion("repayment_type not between", value1, value2, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNull() {
            addCriterion("bank_account is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNotNull() {
            addCriterion("bank_account is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountEqualTo(String value) {
            addCriterion("bank_account =", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotEqualTo(String value) {
            addCriterion("bank_account <>", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThan(String value) {
            addCriterion("bank_account >", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThanOrEqualTo(String value) {
            addCriterion("bank_account >=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThan(String value) {
            addCriterion("bank_account <", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThanOrEqualTo(String value) {
            addCriterion("bank_account <=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLike(String value) {
            addCriterion("bank_account like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotLike(String value) {
            addCriterion("bank_account not like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountIn(List<String> values) {
            addCriterion("bank_account in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotIn(List<String> values) {
            addCriterion("bank_account not in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountBetween(String value1, String value2) {
            addCriterion("bank_account between", value1, value2, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotBetween(String value1, String value2) {
            addCriterion("bank_account not between", value1, value2, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIsNull() {
            addCriterion("bank_account_name is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIsNotNull() {
            addCriterion("bank_account_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameEqualTo(String value) {
            addCriterion("bank_account_name =", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotEqualTo(String value) {
            addCriterion("bank_account_name <>", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameGreaterThan(String value) {
            addCriterion("bank_account_name >", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_account_name >=", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLessThan(String value) {
            addCriterion("bank_account_name <", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLessThanOrEqualTo(String value) {
            addCriterion("bank_account_name <=", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLike(String value) {
            addCriterion("bank_account_name like", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotLike(String value) {
            addCriterion("bank_account_name not like", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIn(List<String> values) {
            addCriterion("bank_account_name in", values, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotIn(List<String> values) {
            addCriterion("bank_account_name not in", values, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameBetween(String value1, String value2) {
            addCriterion("bank_account_name between", value1, value2, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotBetween(String value1, String value2) {
            addCriterion("bank_account_name not between", value1, value2, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountAddressIsNull() {
            addCriterion("bank_account_address is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountAddressIsNotNull() {
            addCriterion("bank_account_address is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountAddressEqualTo(String value) {
            addCriterion("bank_account_address =", value, "bankAccountAddress");
            return (Criteria) this;
        }

        public Criteria andBankAccountAddressNotEqualTo(String value) {
            addCriterion("bank_account_address <>", value, "bankAccountAddress");
            return (Criteria) this;
        }

        public Criteria andBankAccountAddressGreaterThan(String value) {
            addCriterion("bank_account_address >", value, "bankAccountAddress");
            return (Criteria) this;
        }

        public Criteria andBankAccountAddressGreaterThanOrEqualTo(String value) {
            addCriterion("bank_account_address >=", value, "bankAccountAddress");
            return (Criteria) this;
        }

        public Criteria andBankAccountAddressLessThan(String value) {
            addCriterion("bank_account_address <", value, "bankAccountAddress");
            return (Criteria) this;
        }

        public Criteria andBankAccountAddressLessThanOrEqualTo(String value) {
            addCriterion("bank_account_address <=", value, "bankAccountAddress");
            return (Criteria) this;
        }

        public Criteria andBankAccountAddressLike(String value) {
            addCriterion("bank_account_address like", value, "bankAccountAddress");
            return (Criteria) this;
        }

        public Criteria andBankAccountAddressNotLike(String value) {
            addCriterion("bank_account_address not like", value, "bankAccountAddress");
            return (Criteria) this;
        }

        public Criteria andBankAccountAddressIn(List<String> values) {
            addCriterion("bank_account_address in", values, "bankAccountAddress");
            return (Criteria) this;
        }

        public Criteria andBankAccountAddressNotIn(List<String> values) {
            addCriterion("bank_account_address not in", values, "bankAccountAddress");
            return (Criteria) this;
        }

        public Criteria andBankAccountAddressBetween(String value1, String value2) {
            addCriterion("bank_account_address between", value1, value2, "bankAccountAddress");
            return (Criteria) this;
        }

        public Criteria andBankAccountAddressNotBetween(String value1, String value2) {
            addCriterion("bank_account_address not between", value1, value2, "bankAccountAddress");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoIsNull() {
            addCriterion("organization_no is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoIsNotNull() {
            addCriterion("organization_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoEqualTo(String value) {
            addCriterion("organization_no =", value, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoNotEqualTo(String value) {
            addCriterion("organization_no <>", value, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoGreaterThan(String value) {
            addCriterion("organization_no >", value, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoGreaterThanOrEqualTo(String value) {
            addCriterion("organization_no >=", value, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoLessThan(String value) {
            addCriterion("organization_no <", value, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoLessThanOrEqualTo(String value) {
            addCriterion("organization_no <=", value, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoLike(String value) {
            addCriterion("organization_no like", value, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoNotLike(String value) {
            addCriterion("organization_no not like", value, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoIn(List<String> values) {
            addCriterion("organization_no in", values, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoNotIn(List<String> values) {
            addCriterion("organization_no not in", values, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoBetween(String value1, String value2) {
            addCriterion("organization_no between", value1, value2, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationNoNotBetween(String value1, String value2) {
            addCriterion("organization_no not between", value1, value2, "organizationNo");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdIsNull() {
            addCriterion("organization_media_id is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdIsNotNull() {
            addCriterion("organization_media_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdEqualTo(String value) {
            addCriterion("organization_media_id =", value, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdNotEqualTo(String value) {
            addCriterion("organization_media_id <>", value, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdGreaterThan(String value) {
            addCriterion("organization_media_id >", value, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdGreaterThanOrEqualTo(String value) {
            addCriterion("organization_media_id >=", value, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdLessThan(String value) {
            addCriterion("organization_media_id <", value, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdLessThanOrEqualTo(String value) {
            addCriterion("organization_media_id <=", value, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdLike(String value) {
            addCriterion("organization_media_id like", value, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdNotLike(String value) {
            addCriterion("organization_media_id not like", value, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdIn(List<String> values) {
            addCriterion("organization_media_id in", values, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdNotIn(List<String> values) {
            addCriterion("organization_media_id not in", values, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdBetween(String value1, String value2) {
            addCriterion("organization_media_id between", value1, value2, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andOrganizationMediaIdNotBetween(String value1, String value2) {
            addCriterion("organization_media_id not between", value1, value2, "organizationMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoIsNull() {
            addCriterion("business_licence_no is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoIsNotNull() {
            addCriterion("business_licence_no is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoEqualTo(String value) {
            addCriterion("business_licence_no =", value, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoNotEqualTo(String value) {
            addCriterion("business_licence_no <>", value, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoGreaterThan(String value) {
            addCriterion("business_licence_no >", value, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoGreaterThanOrEqualTo(String value) {
            addCriterion("business_licence_no >=", value, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoLessThan(String value) {
            addCriterion("business_licence_no <", value, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoLessThanOrEqualTo(String value) {
            addCriterion("business_licence_no <=", value, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoLike(String value) {
            addCriterion("business_licence_no like", value, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoNotLike(String value) {
            addCriterion("business_licence_no not like", value, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoIn(List<String> values) {
            addCriterion("business_licence_no in", values, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoNotIn(List<String> values) {
            addCriterion("business_licence_no not in", values, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoBetween(String value1, String value2) {
            addCriterion("business_licence_no between", value1, value2, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceNoNotBetween(String value1, String value2) {
            addCriterion("business_licence_no not between", value1, value2, "businessLicenceNo");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdIsNull() {
            addCriterion("business_licence_media_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdIsNotNull() {
            addCriterion("business_licence_media_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdEqualTo(String value) {
            addCriterion("business_licence_media_id =", value, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdNotEqualTo(String value) {
            addCriterion("business_licence_media_id <>", value, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdGreaterThan(String value) {
            addCriterion("business_licence_media_id >", value, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdGreaterThanOrEqualTo(String value) {
            addCriterion("business_licence_media_id >=", value, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdLessThan(String value) {
            addCriterion("business_licence_media_id <", value, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdLessThanOrEqualTo(String value) {
            addCriterion("business_licence_media_id <=", value, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdLike(String value) {
            addCriterion("business_licence_media_id like", value, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdNotLike(String value) {
            addCriterion("business_licence_media_id not like", value, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdIn(List<String> values) {
            addCriterion("business_licence_media_id in", values, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdNotIn(List<String> values) {
            addCriterion("business_licence_media_id not in", values, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdBetween(String value1, String value2) {
            addCriterion("business_licence_media_id between", value1, value2, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceMediaIdNotBetween(String value1, String value2) {
            addCriterion("business_licence_media_id not between", value1, value2, "businessLicenceMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoIsNull() {
            addCriterion("tax_registration_certificate_no is null");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoIsNotNull() {
            addCriterion("tax_registration_certificate_no is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoEqualTo(String value) {
            addCriterion("tax_registration_certificate_no =", value, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoNotEqualTo(String value) {
            addCriterion("tax_registration_certificate_no <>", value, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoGreaterThan(String value) {
            addCriterion("tax_registration_certificate_no >", value, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoGreaterThanOrEqualTo(String value) {
            addCriterion("tax_registration_certificate_no >=", value, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoLessThan(String value) {
            addCriterion("tax_registration_certificate_no <", value, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoLessThanOrEqualTo(String value) {
            addCriterion("tax_registration_certificate_no <=", value, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoLike(String value) {
            addCriterion("tax_registration_certificate_no like", value, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoNotLike(String value) {
            addCriterion("tax_registration_certificate_no not like", value, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoIn(List<String> values) {
            addCriterion("tax_registration_certificate_no in", values, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoNotIn(List<String> values) {
            addCriterion("tax_registration_certificate_no not in", values, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoBetween(String value1, String value2) {
            addCriterion("tax_registration_certificate_no between", value1, value2, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateNoNotBetween(String value1, String value2) {
            addCriterion("tax_registration_certificate_no not between", value1, value2, "taxRegistrationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdIsNull() {
            addCriterion("tax_registration_certificate_media_id is null");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdIsNotNull() {
            addCriterion("tax_registration_certificate_media_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdEqualTo(String value) {
            addCriterion("tax_registration_certificate_media_id =", value, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdNotEqualTo(String value) {
            addCriterion("tax_registration_certificate_media_id <>", value, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdGreaterThan(String value) {
            addCriterion("tax_registration_certificate_media_id >", value, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdGreaterThanOrEqualTo(String value) {
            addCriterion("tax_registration_certificate_media_id >=", value, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdLessThan(String value) {
            addCriterion("tax_registration_certificate_media_id <", value, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdLessThanOrEqualTo(String value) {
            addCriterion("tax_registration_certificate_media_id <=", value, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdLike(String value) {
            addCriterion("tax_registration_certificate_media_id like", value, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdNotLike(String value) {
            addCriterion("tax_registration_certificate_media_id not like", value, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdIn(List<String> values) {
            addCriterion("tax_registration_certificate_media_id in", values, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdNotIn(List<String> values) {
            addCriterion("tax_registration_certificate_media_id not in", values, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdBetween(String value1, String value2) {
            addCriterion("tax_registration_certificate_media_id between", value1, value2, "taxRegistrationCertificateMediaId");
            return (Criteria) this;
        }

        public Criteria andTaxRegistrationCertificateMediaIdNotBetween(String value1, String value2) {
            addCriterion("tax_registration_certificate_media_id not between", value1, value2, "taxRegistrationCertificateMediaId");
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

        public Criteria andAccessManualStateIsNull() {
            addCriterion("access_manual_state is null");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateIsNotNull() {
            addCriterion("access_manual_state is not null");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateEqualTo(String value) {
            addCriterion("access_manual_state =", value, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateNotEqualTo(String value) {
            addCriterion("access_manual_state <>", value, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateGreaterThan(String value) {
            addCriterion("access_manual_state >", value, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateGreaterThanOrEqualTo(String value) {
            addCriterion("access_manual_state >=", value, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateLessThan(String value) {
            addCriterion("access_manual_state <", value, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateLessThanOrEqualTo(String value) {
            addCriterion("access_manual_state <=", value, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateLike(String value) {
            addCriterion("access_manual_state like", value, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateNotLike(String value) {
            addCriterion("access_manual_state not like", value, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateIn(List<String> values) {
            addCriterion("access_manual_state in", values, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateNotIn(List<String> values) {
            addCriterion("access_manual_state not in", values, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateBetween(String value1, String value2) {
            addCriterion("access_manual_state between", value1, value2, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andAccessManualStateNotBetween(String value1, String value2) {
            addCriterion("access_manual_state not between", value1, value2, "accessManualState");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksIsNull() {
            addCriterion("manual_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksIsNotNull() {
            addCriterion("manual_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksEqualTo(String value) {
            addCriterion("manual_audit_remarks =", value, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksNotEqualTo(String value) {
            addCriterion("manual_audit_remarks <>", value, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksGreaterThan(String value) {
            addCriterion("manual_audit_remarks >", value, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("manual_audit_remarks >=", value, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksLessThan(String value) {
            addCriterion("manual_audit_remarks <", value, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("manual_audit_remarks <=", value, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksLike(String value) {
            addCriterion("manual_audit_remarks like", value, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksNotLike(String value) {
            addCriterion("manual_audit_remarks not like", value, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksIn(List<String> values) {
            addCriterion("manual_audit_remarks in", values, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksNotIn(List<String> values) {
            addCriterion("manual_audit_remarks not in", values, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksBetween(String value1, String value2) {
            addCriterion("manual_audit_remarks between", value1, value2, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andManualAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("manual_audit_remarks not between", value1, value2, "manualAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateIsNull() {
            addCriterion("access_automatic_state is null");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateIsNotNull() {
            addCriterion("access_automatic_state is not null");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateEqualTo(String value) {
            addCriterion("access_automatic_state =", value, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateNotEqualTo(String value) {
            addCriterion("access_automatic_state <>", value, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateGreaterThan(String value) {
            addCriterion("access_automatic_state >", value, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateGreaterThanOrEqualTo(String value) {
            addCriterion("access_automatic_state >=", value, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateLessThan(String value) {
            addCriterion("access_automatic_state <", value, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateLessThanOrEqualTo(String value) {
            addCriterion("access_automatic_state <=", value, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateLike(String value) {
            addCriterion("access_automatic_state like", value, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateNotLike(String value) {
            addCriterion("access_automatic_state not like", value, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateIn(List<String> values) {
            addCriterion("access_automatic_state in", values, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateNotIn(List<String> values) {
            addCriterion("access_automatic_state not in", values, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateBetween(String value1, String value2) {
            addCriterion("access_automatic_state between", value1, value2, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAccessAutomaticStateNotBetween(String value1, String value2) {
            addCriterion("access_automatic_state not between", value1, value2, "accessAutomaticState");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksIsNull() {
            addCriterion("automatic_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksIsNotNull() {
            addCriterion("automatic_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksEqualTo(String value) {
            addCriterion("automatic_audit_remarks =", value, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksNotEqualTo(String value) {
            addCriterion("automatic_audit_remarks <>", value, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksGreaterThan(String value) {
            addCriterion("automatic_audit_remarks >", value, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("automatic_audit_remarks >=", value, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksLessThan(String value) {
            addCriterion("automatic_audit_remarks <", value, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("automatic_audit_remarks <=", value, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksLike(String value) {
            addCriterion("automatic_audit_remarks like", value, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksNotLike(String value) {
            addCriterion("automatic_audit_remarks not like", value, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksIn(List<String> values) {
            addCriterion("automatic_audit_remarks in", values, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksNotIn(List<String> values) {
            addCriterion("automatic_audit_remarks not in", values, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksBetween(String value1, String value2) {
            addCriterion("automatic_audit_remarks between", value1, value2, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("automatic_audit_remarks not between", value1, value2, "automaticAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditorIdIsNull() {
            addCriterion("auditor_id is null");
            return (Criteria) this;
        }

        public Criteria andAuditorIdIsNotNull() {
            addCriterion("auditor_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuditorIdEqualTo(String value) {
            addCriterion("auditor_id =", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotEqualTo(String value) {
            addCriterion("auditor_id <>", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdGreaterThan(String value) {
            addCriterion("auditor_id >", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdGreaterThanOrEqualTo(String value) {
            addCriterion("auditor_id >=", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdLessThan(String value) {
            addCriterion("auditor_id <", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdLessThanOrEqualTo(String value) {
            addCriterion("auditor_id <=", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdLike(String value) {
            addCriterion("auditor_id like", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotLike(String value) {
            addCriterion("auditor_id not like", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdIn(List<String> values) {
            addCriterion("auditor_id in", values, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotIn(List<String> values) {
            addCriterion("auditor_id not in", values, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdBetween(String value1, String value2) {
            addCriterion("auditor_id between", value1, value2, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotBetween(String value1, String value2) {
            addCriterion("auditor_id not between", value1, value2, "auditorId");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckIsNull() {
            addCriterion("consistency_check is null");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckIsNotNull() {
            addCriterion("consistency_check is not null");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckEqualTo(Integer value) {
            addCriterion("consistency_check =", value, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckNotEqualTo(Integer value) {
            addCriterion("consistency_check <>", value, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckGreaterThan(Integer value) {
            addCriterion("consistency_check >", value, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckGreaterThanOrEqualTo(Integer value) {
            addCriterion("consistency_check >=", value, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckLessThan(Integer value) {
            addCriterion("consistency_check <", value, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckLessThanOrEqualTo(Integer value) {
            addCriterion("consistency_check <=", value, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckIn(List<Integer> values) {
            addCriterion("consistency_check in", values, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckNotIn(List<Integer> values) {
            addCriterion("consistency_check not in", values, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckBetween(Integer value1, Integer value2) {
            addCriterion("consistency_check between", value1, value2, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andConsistencyCheckNotBetween(Integer value1, Integer value2) {
            addCriterion("consistency_check not between", value1, value2, "consistencyCheck");
            return (Criteria) this;
        }

        public Criteria andWhetherStationInfoIsNull() {
            addCriterion("whether_station_info is null");
            return (Criteria) this;
        }

        public Criteria andWhetherStationInfoIsNotNull() {
            addCriterion("whether_station_info is not null");
            return (Criteria) this;
        }

        public Criteria andWhetherStationInfoEqualTo(String value) {
            addCriterion("whether_station_info =", value, "whetherStationInfo");
            return (Criteria) this;
        }

        public Criteria andWhetherStationInfoNotEqualTo(String value) {
            addCriterion("whether_station_info <>", value, "whetherStationInfo");
            return (Criteria) this;
        }

        public Criteria andWhetherStationInfoGreaterThan(String value) {
            addCriterion("whether_station_info >", value, "whetherStationInfo");
            return (Criteria) this;
        }

        public Criteria andWhetherStationInfoGreaterThanOrEqualTo(String value) {
            addCriterion("whether_station_info >=", value, "whetherStationInfo");
            return (Criteria) this;
        }

        public Criteria andWhetherStationInfoLessThan(String value) {
            addCriterion("whether_station_info <", value, "whetherStationInfo");
            return (Criteria) this;
        }

        public Criteria andWhetherStationInfoLessThanOrEqualTo(String value) {
            addCriterion("whether_station_info <=", value, "whetherStationInfo");
            return (Criteria) this;
        }

        public Criteria andWhetherStationInfoLike(String value) {
            addCriterion("whether_station_info like", value, "whetherStationInfo");
            return (Criteria) this;
        }

        public Criteria andWhetherStationInfoNotLike(String value) {
            addCriterion("whether_station_info not like", value, "whetherStationInfo");
            return (Criteria) this;
        }

        public Criteria andWhetherStationInfoIn(List<String> values) {
            addCriterion("whether_station_info in", values, "whetherStationInfo");
            return (Criteria) this;
        }

        public Criteria andWhetherStationInfoNotIn(List<String> values) {
            addCriterion("whether_station_info not in", values, "whetherStationInfo");
            return (Criteria) this;
        }

        public Criteria andWhetherStationInfoBetween(String value1, String value2) {
            addCriterion("whether_station_info between", value1, value2, "whetherStationInfo");
            return (Criteria) this;
        }

        public Criteria andWhetherStationInfoNotBetween(String value1, String value2) {
            addCriterion("whether_station_info not between", value1, value2, "whetherStationInfo");
            return (Criteria) this;
        }

        public Criteria andReasonWIsNull() {
            addCriterion("reason_W is null");
            return (Criteria) this;
        }

        public Criteria andReasonWIsNotNull() {
            addCriterion("reason_W is not null");
            return (Criteria) this;
        }

        public Criteria andReasonWEqualTo(String value) {
            addCriterion("reason_W =", value, "reasonW");
            return (Criteria) this;
        }

        public Criteria andReasonWNotEqualTo(String value) {
            addCriterion("reason_W <>", value, "reasonW");
            return (Criteria) this;
        }

        public Criteria andReasonWGreaterThan(String value) {
            addCriterion("reason_W >", value, "reasonW");
            return (Criteria) this;
        }

        public Criteria andReasonWGreaterThanOrEqualTo(String value) {
            addCriterion("reason_W >=", value, "reasonW");
            return (Criteria) this;
        }

        public Criteria andReasonWLessThan(String value) {
            addCriterion("reason_W <", value, "reasonW");
            return (Criteria) this;
        }

        public Criteria andReasonWLessThanOrEqualTo(String value) {
            addCriterion("reason_W <=", value, "reasonW");
            return (Criteria) this;
        }

        public Criteria andReasonWLike(String value) {
            addCriterion("reason_W like", value, "reasonW");
            return (Criteria) this;
        }

        public Criteria andReasonWNotLike(String value) {
            addCriterion("reason_W not like", value, "reasonW");
            return (Criteria) this;
        }

        public Criteria andReasonWIn(List<String> values) {
            addCriterion("reason_W in", values, "reasonW");
            return (Criteria) this;
        }

        public Criteria andReasonWNotIn(List<String> values) {
            addCriterion("reason_W not in", values, "reasonW");
            return (Criteria) this;
        }

        public Criteria andReasonWBetween(String value1, String value2) {
            addCriterion("reason_W between", value1, value2, "reasonW");
            return (Criteria) this;
        }

        public Criteria andReasonWNotBetween(String value1, String value2) {
            addCriterion("reason_W not between", value1, value2, "reasonW");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoIsNull() {
            addCriterion("automatic_audit_rule_batch_no is null");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoIsNotNull() {
            addCriterion("automatic_audit_rule_batch_no is not null");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoEqualTo(Integer value) {
            addCriterion("automatic_audit_rule_batch_no =", value, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoNotEqualTo(Integer value) {
            addCriterion("automatic_audit_rule_batch_no <>", value, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoGreaterThan(Integer value) {
            addCriterion("automatic_audit_rule_batch_no >", value, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("automatic_audit_rule_batch_no >=", value, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoLessThan(Integer value) {
            addCriterion("automatic_audit_rule_batch_no <", value, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoLessThanOrEqualTo(Integer value) {
            addCriterion("automatic_audit_rule_batch_no <=", value, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoIn(List<Integer> values) {
            addCriterion("automatic_audit_rule_batch_no in", values, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoNotIn(List<Integer> values) {
            addCriterion("automatic_audit_rule_batch_no not in", values, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoBetween(Integer value1, Integer value2) {
            addCriterion("automatic_audit_rule_batch_no between", value1, value2, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andAutomaticAuditRuleBatchNoNotBetween(Integer value1, Integer value2) {
            addCriterion("automatic_audit_rule_batch_no not between", value1, value2, "automaticAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoIsNull() {
            addCriterion("manual_audit_rule_batch_no is null");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoIsNotNull() {
            addCriterion("manual_audit_rule_batch_no is not null");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoEqualTo(Integer value) {
            addCriterion("manual_audit_rule_batch_no =", value, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoNotEqualTo(Integer value) {
            addCriterion("manual_audit_rule_batch_no <>", value, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoGreaterThan(Integer value) {
            addCriterion("manual_audit_rule_batch_no >", value, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("manual_audit_rule_batch_no >=", value, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoLessThan(Integer value) {
            addCriterion("manual_audit_rule_batch_no <", value, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoLessThanOrEqualTo(Integer value) {
            addCriterion("manual_audit_rule_batch_no <=", value, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoIn(List<Integer> values) {
            addCriterion("manual_audit_rule_batch_no in", values, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoNotIn(List<Integer> values) {
            addCriterion("manual_audit_rule_batch_no not in", values, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoBetween(Integer value1, Integer value2) {
            addCriterion("manual_audit_rule_batch_no between", value1, value2, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andManualAuditRuleBatchNoNotBetween(Integer value1, Integer value2) {
            addCriterion("manual_audit_rule_batch_no not between", value1, value2, "manualAuditRuleBatchNo");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateIsNull() {
            addCriterion("credit_limit_generate_state is null");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateIsNotNull() {
            addCriterion("credit_limit_generate_state is not null");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateEqualTo(String value) {
            addCriterion("credit_limit_generate_state =", value, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateNotEqualTo(String value) {
            addCriterion("credit_limit_generate_state <>", value, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateGreaterThan(String value) {
            addCriterion("credit_limit_generate_state >", value, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateGreaterThanOrEqualTo(String value) {
            addCriterion("credit_limit_generate_state >=", value, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateLessThan(String value) {
            addCriterion("credit_limit_generate_state <", value, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateLessThanOrEqualTo(String value) {
            addCriterion("credit_limit_generate_state <=", value, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateLike(String value) {
            addCriterion("credit_limit_generate_state like", value, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateNotLike(String value) {
            addCriterion("credit_limit_generate_state not like", value, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateIn(List<String> values) {
            addCriterion("credit_limit_generate_state in", values, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateNotIn(List<String> values) {
            addCriterion("credit_limit_generate_state not in", values, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateBetween(String value1, String value2) {
            addCriterion("credit_limit_generate_state between", value1, value2, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGenerateStateNotBetween(String value1, String value2) {
            addCriterion("credit_limit_generate_state not between", value1, value2, "creditLimitGenerateState");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkIsNull() {
            addCriterion("negative_network is null");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkIsNotNull() {
            addCriterion("negative_network is not null");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkEqualTo(String value) {
            addCriterion("negative_network =", value, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkNotEqualTo(String value) {
            addCriterion("negative_network <>", value, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkGreaterThan(String value) {
            addCriterion("negative_network >", value, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkGreaterThanOrEqualTo(String value) {
            addCriterion("negative_network >=", value, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkLessThan(String value) {
            addCriterion("negative_network <", value, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkLessThanOrEqualTo(String value) {
            addCriterion("negative_network <=", value, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkLike(String value) {
            addCriterion("negative_network like", value, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkNotLike(String value) {
            addCriterion("negative_network not like", value, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkIn(List<String> values) {
            addCriterion("negative_network in", values, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkNotIn(List<String> values) {
            addCriterion("negative_network not in", values, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkBetween(String value1, String value2) {
            addCriterion("negative_network between", value1, value2, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andNegativeNetworkNotBetween(String value1, String value2) {
            addCriterion("negative_network not between", value1, value2, "negativeNetwork");
            return (Criteria) this;
        }

        public Criteria andRanchiseContractDeadlineIsNull() {
            addCriterion("ranchise_contract_deadline is null");
            return (Criteria) this;
        }

        public Criteria andRanchiseContractDeadlineIsNotNull() {
            addCriterion("ranchise_contract_deadline is not null");
            return (Criteria) this;
        }

        public Criteria andRanchiseContractDeadlineEqualTo(Date value) {
            addCriterion("ranchise_contract_deadline =", value, "ranchiseContractDeadline");
            return (Criteria) this;
        }

        public Criteria andRanchiseContractDeadlineNotEqualTo(Date value) {
            addCriterion("ranchise_contract_deadline <>", value, "ranchiseContractDeadline");
            return (Criteria) this;
        }

        public Criteria andRanchiseContractDeadlineGreaterThan(Date value) {
            addCriterion("ranchise_contract_deadline >", value, "ranchiseContractDeadline");
            return (Criteria) this;
        }

        public Criteria andRanchiseContractDeadlineGreaterThanOrEqualTo(Date value) {
            addCriterion("ranchise_contract_deadline >=", value, "ranchiseContractDeadline");
            return (Criteria) this;
        }

        public Criteria andRanchiseContractDeadlineLessThan(Date value) {
            addCriterion("ranchise_contract_deadline <", value, "ranchiseContractDeadline");
            return (Criteria) this;
        }

        public Criteria andRanchiseContractDeadlineLessThanOrEqualTo(Date value) {
            addCriterion("ranchise_contract_deadline <=", value, "ranchiseContractDeadline");
            return (Criteria) this;
        }

        public Criteria andRanchiseContractDeadlineIn(List<Date> values) {
            addCriterion("ranchise_contract_deadline in", values, "ranchiseContractDeadline");
            return (Criteria) this;
        }

        public Criteria andRanchiseContractDeadlineNotIn(List<Date> values) {
            addCriterion("ranchise_contract_deadline not in", values, "ranchiseContractDeadline");
            return (Criteria) this;
        }

        public Criteria andRanchiseContractDeadlineBetween(Date value1, Date value2) {
            addCriterion("ranchise_contract_deadline between", value1, value2, "ranchiseContractDeadline");
            return (Criteria) this;
        }

        public Criteria andRanchiseContractDeadlineNotBetween(Date value1, Date value2) {
            addCriterion("ranchise_contract_deadline not between", value1, value2, "ranchiseContractDeadline");
            return (Criteria) this;
        }

        public Criteria andExecuteIndividualInfoIsNull() {
            addCriterion("execute_individual_info is null");
            return (Criteria) this;
        }

        public Criteria andExecuteIndividualInfoIsNotNull() {
            addCriterion("execute_individual_info is not null");
            return (Criteria) this;
        }

        public Criteria andExecuteIndividualInfoEqualTo(String value) {
            addCriterion("execute_individual_info =", value, "executeIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andExecuteIndividualInfoNotEqualTo(String value) {
            addCriterion("execute_individual_info <>", value, "executeIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andExecuteIndividualInfoGreaterThan(String value) {
            addCriterion("execute_individual_info >", value, "executeIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andExecuteIndividualInfoGreaterThanOrEqualTo(String value) {
            addCriterion("execute_individual_info >=", value, "executeIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andExecuteIndividualInfoLessThan(String value) {
            addCriterion("execute_individual_info <", value, "executeIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andExecuteIndividualInfoLessThanOrEqualTo(String value) {
            addCriterion("execute_individual_info <=", value, "executeIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andExecuteIndividualInfoLike(String value) {
            addCriterion("execute_individual_info like", value, "executeIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andExecuteIndividualInfoNotLike(String value) {
            addCriterion("execute_individual_info not like", value, "executeIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andExecuteIndividualInfoIn(List<String> values) {
            addCriterion("execute_individual_info in", values, "executeIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andExecuteIndividualInfoNotIn(List<String> values) {
            addCriterion("execute_individual_info not in", values, "executeIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andExecuteIndividualInfoBetween(String value1, String value2) {
            addCriterion("execute_individual_info between", value1, value2, "executeIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andExecuteIndividualInfoNotBetween(String value1, String value2) {
            addCriterion("execute_individual_info not between", value1, value2, "executeIndividualInfo");
            return (Criteria) this;
        }

        public Criteria andQualifiedInspectionRecordIsNull() {
            addCriterion("qualified_inspection_record is null");
            return (Criteria) this;
        }

        public Criteria andQualifiedInspectionRecordIsNotNull() {
            addCriterion("qualified_inspection_record is not null");
            return (Criteria) this;
        }

        public Criteria andQualifiedInspectionRecordEqualTo(String value) {
            addCriterion("qualified_inspection_record =", value, "qualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andQualifiedInspectionRecordNotEqualTo(String value) {
            addCriterion("qualified_inspection_record <>", value, "qualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andQualifiedInspectionRecordGreaterThan(String value) {
            addCriterion("qualified_inspection_record >", value, "qualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andQualifiedInspectionRecordGreaterThanOrEqualTo(String value) {
            addCriterion("qualified_inspection_record >=", value, "qualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andQualifiedInspectionRecordLessThan(String value) {
            addCriterion("qualified_inspection_record <", value, "qualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andQualifiedInspectionRecordLessThanOrEqualTo(String value) {
            addCriterion("qualified_inspection_record <=", value, "qualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andQualifiedInspectionRecordLike(String value) {
            addCriterion("qualified_inspection_record like", value, "qualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andQualifiedInspectionRecordNotLike(String value) {
            addCriterion("qualified_inspection_record not like", value, "qualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andQualifiedInspectionRecordIn(List<String> values) {
            addCriterion("qualified_inspection_record in", values, "qualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andQualifiedInspectionRecordNotIn(List<String> values) {
            addCriterion("qualified_inspection_record not in", values, "qualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andQualifiedInspectionRecordBetween(String value1, String value2) {
            addCriterion("qualified_inspection_record between", value1, value2, "qualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andQualifiedInspectionRecordNotBetween(String value1, String value2) {
            addCriterion("qualified_inspection_record not between", value1, value2, "qualifiedInspectionRecord");
            return (Criteria) this;
        }

        public Criteria andPayAccountIsNull() {
            addCriterion("pay_account is null");
            return (Criteria) this;
        }

        public Criteria andPayAccountIsNotNull() {
            addCriterion("pay_account is not null");
            return (Criteria) this;
        }

        public Criteria andPayAccountEqualTo(String value) {
            addCriterion("pay_account =", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountNotEqualTo(String value) {
            addCriterion("pay_account <>", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountGreaterThan(String value) {
            addCriterion("pay_account >", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountGreaterThanOrEqualTo(String value) {
            addCriterion("pay_account >=", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountLessThan(String value) {
            addCriterion("pay_account <", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountLessThanOrEqualTo(String value) {
            addCriterion("pay_account <=", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountLike(String value) {
            addCriterion("pay_account like", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountNotLike(String value) {
            addCriterion("pay_account not like", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountIn(List<String> values) {
            addCriterion("pay_account in", values, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountNotIn(List<String> values) {
            addCriterion("pay_account not in", values, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountBetween(String value1, String value2) {
            addCriterion("pay_account between", value1, value2, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountNotBetween(String value1, String value2) {
            addCriterion("pay_account not between", value1, value2, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameIsNull() {
            addCriterion("pay_account_name is null");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameIsNotNull() {
            addCriterion("pay_account_name is not null");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameEqualTo(String value) {
            addCriterion("pay_account_name =", value, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameNotEqualTo(String value) {
            addCriterion("pay_account_name <>", value, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameGreaterThan(String value) {
            addCriterion("pay_account_name >", value, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("pay_account_name >=", value, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameLessThan(String value) {
            addCriterion("pay_account_name <", value, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameLessThanOrEqualTo(String value) {
            addCriterion("pay_account_name <=", value, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameLike(String value) {
            addCriterion("pay_account_name like", value, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameNotLike(String value) {
            addCriterion("pay_account_name not like", value, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameIn(List<String> values) {
            addCriterion("pay_account_name in", values, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameNotIn(List<String> values) {
            addCriterion("pay_account_name not in", values, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameBetween(String value1, String value2) {
            addCriterion("pay_account_name between", value1, value2, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountNameNotBetween(String value1, String value2) {
            addCriterion("pay_account_name not between", value1, value2, "payAccountName");
            return (Criteria) this;
        }

        public Criteria andPayAccountAddressIsNull() {
            addCriterion("pay_account_address is null");
            return (Criteria) this;
        }

        public Criteria andPayAccountAddressIsNotNull() {
            addCriterion("pay_account_address is not null");
            return (Criteria) this;
        }

        public Criteria andPayAccountAddressEqualTo(String value) {
            addCriterion("pay_account_address =", value, "payAccountAddress");
            return (Criteria) this;
        }

        public Criteria andPayAccountAddressNotEqualTo(String value) {
            addCriterion("pay_account_address <>", value, "payAccountAddress");
            return (Criteria) this;
        }

        public Criteria andPayAccountAddressGreaterThan(String value) {
            addCriterion("pay_account_address >", value, "payAccountAddress");
            return (Criteria) this;
        }

        public Criteria andPayAccountAddressGreaterThanOrEqualTo(String value) {
            addCriterion("pay_account_address >=", value, "payAccountAddress");
            return (Criteria) this;
        }

        public Criteria andPayAccountAddressLessThan(String value) {
            addCriterion("pay_account_address <", value, "payAccountAddress");
            return (Criteria) this;
        }

        public Criteria andPayAccountAddressLessThanOrEqualTo(String value) {
            addCriterion("pay_account_address <=", value, "payAccountAddress");
            return (Criteria) this;
        }

        public Criteria andPayAccountAddressLike(String value) {
            addCriterion("pay_account_address like", value, "payAccountAddress");
            return (Criteria) this;
        }

        public Criteria andPayAccountAddressNotLike(String value) {
            addCriterion("pay_account_address not like", value, "payAccountAddress");
            return (Criteria) this;
        }

        public Criteria andPayAccountAddressIn(List<String> values) {
            addCriterion("pay_account_address in", values, "payAccountAddress");
            return (Criteria) this;
        }

        public Criteria andPayAccountAddressNotIn(List<String> values) {
            addCriterion("pay_account_address not in", values, "payAccountAddress");
            return (Criteria) this;
        }

        public Criteria andPayAccountAddressBetween(String value1, String value2) {
            addCriterion("pay_account_address between", value1, value2, "payAccountAddress");
            return (Criteria) this;
        }

        public Criteria andPayAccountAddressNotBetween(String value1, String value2) {
            addCriterion("pay_account_address not between", value1, value2, "payAccountAddress");
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

        public Criteria andControllerIsNull() {
            addCriterion("controller is null");
            return (Criteria) this;
        }

        public Criteria andControllerIsNotNull() {
            addCriterion("controller is not null");
            return (Criteria) this;
        }

        public Criteria andControllerEqualTo(String value) {
            addCriterion("controller =", value, "controller");
            return (Criteria) this;
        }

        public Criteria andControllerNotEqualTo(String value) {
            addCriterion("controller <>", value, "controller");
            return (Criteria) this;
        }

        public Criteria andControllerGreaterThan(String value) {
            addCriterion("controller >", value, "controller");
            return (Criteria) this;
        }

        public Criteria andControllerGreaterThanOrEqualTo(String value) {
            addCriterion("controller >=", value, "controller");
            return (Criteria) this;
        }

        public Criteria andControllerLessThan(String value) {
            addCriterion("controller <", value, "controller");
            return (Criteria) this;
        }

        public Criteria andControllerLessThanOrEqualTo(String value) {
            addCriterion("controller <=", value, "controller");
            return (Criteria) this;
        }

        public Criteria andControllerLike(String value) {
            addCriterion("controller like", value, "controller");
            return (Criteria) this;
        }

        public Criteria andControllerNotLike(String value) {
            addCriterion("controller not like", value, "controller");
            return (Criteria) this;
        }

        public Criteria andControllerIn(List<String> values) {
            addCriterion("controller in", values, "controller");
            return (Criteria) this;
        }

        public Criteria andControllerNotIn(List<String> values) {
            addCriterion("controller not in", values, "controller");
            return (Criteria) this;
        }

        public Criteria andControllerBetween(String value1, String value2) {
            addCriterion("controller between", value1, value2, "controller");
            return (Criteria) this;
        }

        public Criteria andControllerNotBetween(String value1, String value2) {
            addCriterion("controller not between", value1, value2, "controller");
            return (Criteria) this;
        }

        public Criteria andControllerIdnoIsNull() {
            addCriterion("controller_idno is null");
            return (Criteria) this;
        }

        public Criteria andControllerIdnoIsNotNull() {
            addCriterion("controller_idno is not null");
            return (Criteria) this;
        }

        public Criteria andControllerIdnoEqualTo(String value) {
            addCriterion("controller_idno =", value, "controllerIdno");
            return (Criteria) this;
        }

        public Criteria andControllerIdnoNotEqualTo(String value) {
            addCriterion("controller_idno <>", value, "controllerIdno");
            return (Criteria) this;
        }

        public Criteria andControllerIdnoGreaterThan(String value) {
            addCriterion("controller_idno >", value, "controllerIdno");
            return (Criteria) this;
        }

        public Criteria andControllerIdnoGreaterThanOrEqualTo(String value) {
            addCriterion("controller_idno >=", value, "controllerIdno");
            return (Criteria) this;
        }

        public Criteria andControllerIdnoLessThan(String value) {
            addCriterion("controller_idno <", value, "controllerIdno");
            return (Criteria) this;
        }

        public Criteria andControllerIdnoLessThanOrEqualTo(String value) {
            addCriterion("controller_idno <=", value, "controllerIdno");
            return (Criteria) this;
        }

        public Criteria andControllerIdnoLike(String value) {
            addCriterion("controller_idno like", value, "controllerIdno");
            return (Criteria) this;
        }

        public Criteria andControllerIdnoNotLike(String value) {
            addCriterion("controller_idno not like", value, "controllerIdno");
            return (Criteria) this;
        }

        public Criteria andControllerIdnoIn(List<String> values) {
            addCriterion("controller_idno in", values, "controllerIdno");
            return (Criteria) this;
        }

        public Criteria andControllerIdnoNotIn(List<String> values) {
            addCriterion("controller_idno not in", values, "controllerIdno");
            return (Criteria) this;
        }

        public Criteria andControllerIdnoBetween(String value1, String value2) {
            addCriterion("controller_idno between", value1, value2, "controllerIdno");
            return (Criteria) this;
        }

        public Criteria andControllerIdnoNotBetween(String value1, String value2) {
            addCriterion("controller_idno not between", value1, value2, "controllerIdno");
            return (Criteria) this;
        }

        public Criteria andControllerEmailIsNull() {
            addCriterion("controller_email is null");
            return (Criteria) this;
        }

        public Criteria andControllerEmailIsNotNull() {
            addCriterion("controller_email is not null");
            return (Criteria) this;
        }

        public Criteria andControllerEmailEqualTo(String value) {
            addCriterion("controller_email =", value, "controllerEmail");
            return (Criteria) this;
        }

        public Criteria andControllerEmailNotEqualTo(String value) {
            addCriterion("controller_email <>", value, "controllerEmail");
            return (Criteria) this;
        }

        public Criteria andControllerEmailGreaterThan(String value) {
            addCriterion("controller_email >", value, "controllerEmail");
            return (Criteria) this;
        }

        public Criteria andControllerEmailGreaterThanOrEqualTo(String value) {
            addCriterion("controller_email >=", value, "controllerEmail");
            return (Criteria) this;
        }

        public Criteria andControllerEmailLessThan(String value) {
            addCriterion("controller_email <", value, "controllerEmail");
            return (Criteria) this;
        }

        public Criteria andControllerEmailLessThanOrEqualTo(String value) {
            addCriterion("controller_email <=", value, "controllerEmail");
            return (Criteria) this;
        }

        public Criteria andControllerEmailLike(String value) {
            addCriterion("controller_email like", value, "controllerEmail");
            return (Criteria) this;
        }

        public Criteria andControllerEmailNotLike(String value) {
            addCriterion("controller_email not like", value, "controllerEmail");
            return (Criteria) this;
        }

        public Criteria andControllerEmailIn(List<String> values) {
            addCriterion("controller_email in", values, "controllerEmail");
            return (Criteria) this;
        }

        public Criteria andControllerEmailNotIn(List<String> values) {
            addCriterion("controller_email not in", values, "controllerEmail");
            return (Criteria) this;
        }

        public Criteria andControllerEmailBetween(String value1, String value2) {
            addCriterion("controller_email between", value1, value2, "controllerEmail");
            return (Criteria) this;
        }

        public Criteria andControllerEmailNotBetween(String value1, String value2) {
            addCriterion("controller_email not between", value1, value2, "controllerEmail");
            return (Criteria) this;
        }

        public Criteria andControllerPhoneIsNull() {
            addCriterion("controller_phone is null");
            return (Criteria) this;
        }

        public Criteria andControllerPhoneIsNotNull() {
            addCriterion("controller_phone is not null");
            return (Criteria) this;
        }

        public Criteria andControllerPhoneEqualTo(String value) {
            addCriterion("controller_phone =", value, "controllerPhone");
            return (Criteria) this;
        }

        public Criteria andControllerPhoneNotEqualTo(String value) {
            addCriterion("controller_phone <>", value, "controllerPhone");
            return (Criteria) this;
        }

        public Criteria andControllerPhoneGreaterThan(String value) {
            addCriterion("controller_phone >", value, "controllerPhone");
            return (Criteria) this;
        }

        public Criteria andControllerPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("controller_phone >=", value, "controllerPhone");
            return (Criteria) this;
        }

        public Criteria andControllerPhoneLessThan(String value) {
            addCriterion("controller_phone <", value, "controllerPhone");
            return (Criteria) this;
        }

        public Criteria andControllerPhoneLessThanOrEqualTo(String value) {
            addCriterion("controller_phone <=", value, "controllerPhone");
            return (Criteria) this;
        }

        public Criteria andControllerPhoneLike(String value) {
            addCriterion("controller_phone like", value, "controllerPhone");
            return (Criteria) this;
        }

        public Criteria andControllerPhoneNotLike(String value) {
            addCriterion("controller_phone not like", value, "controllerPhone");
            return (Criteria) this;
        }

        public Criteria andControllerPhoneIn(List<String> values) {
            addCriterion("controller_phone in", values, "controllerPhone");
            return (Criteria) this;
        }

        public Criteria andControllerPhoneNotIn(List<String> values) {
            addCriterion("controller_phone not in", values, "controllerPhone");
            return (Criteria) this;
        }

        public Criteria andControllerPhoneBetween(String value1, String value2) {
            addCriterion("controller_phone between", value1, value2, "controllerPhone");
            return (Criteria) this;
        }

        public Criteria andControllerPhoneNotBetween(String value1, String value2) {
            addCriterion("controller_phone not between", value1, value2, "controllerPhone");
            return (Criteria) this;
        }

        public Criteria andControllerOriginIsNull() {
            addCriterion("controller_origin is null");
            return (Criteria) this;
        }

        public Criteria andControllerOriginIsNotNull() {
            addCriterion("controller_origin is not null");
            return (Criteria) this;
        }

        public Criteria andControllerOriginEqualTo(String value) {
            addCriterion("controller_origin =", value, "controllerOrigin");
            return (Criteria) this;
        }

        public Criteria andControllerOriginNotEqualTo(String value) {
            addCriterion("controller_origin <>", value, "controllerOrigin");
            return (Criteria) this;
        }

        public Criteria andControllerOriginGreaterThan(String value) {
            addCriterion("controller_origin >", value, "controllerOrigin");
            return (Criteria) this;
        }

        public Criteria andControllerOriginGreaterThanOrEqualTo(String value) {
            addCriterion("controller_origin >=", value, "controllerOrigin");
            return (Criteria) this;
        }

        public Criteria andControllerOriginLessThan(String value) {
            addCriterion("controller_origin <", value, "controllerOrigin");
            return (Criteria) this;
        }

        public Criteria andControllerOriginLessThanOrEqualTo(String value) {
            addCriterion("controller_origin <=", value, "controllerOrigin");
            return (Criteria) this;
        }

        public Criteria andControllerOriginLike(String value) {
            addCriterion("controller_origin like", value, "controllerOrigin");
            return (Criteria) this;
        }

        public Criteria andControllerOriginNotLike(String value) {
            addCriterion("controller_origin not like", value, "controllerOrigin");
            return (Criteria) this;
        }

        public Criteria andControllerOriginIn(List<String> values) {
            addCriterion("controller_origin in", values, "controllerOrigin");
            return (Criteria) this;
        }

        public Criteria andControllerOriginNotIn(List<String> values) {
            addCriterion("controller_origin not in", values, "controllerOrigin");
            return (Criteria) this;
        }

        public Criteria andControllerOriginBetween(String value1, String value2) {
            addCriterion("controller_origin between", value1, value2, "controllerOrigin");
            return (Criteria) this;
        }

        public Criteria andControllerOriginNotBetween(String value1, String value2) {
            addCriterion("controller_origin not between", value1, value2, "controllerOrigin");
            return (Criteria) this;
        }

        public Criteria andControllerIsLegalIsNull() {
            addCriterion("controller_is_legal is null");
            return (Criteria) this;
        }

        public Criteria andControllerIsLegalIsNotNull() {
            addCriterion("controller_is_legal is not null");
            return (Criteria) this;
        }

        public Criteria andControllerIsLegalEqualTo(String value) {
            addCriterion("controller_is_legal =", value, "controllerIsLegal");
            return (Criteria) this;
        }

        public Criteria andControllerIsLegalNotEqualTo(String value) {
            addCriterion("controller_is_legal <>", value, "controllerIsLegal");
            return (Criteria) this;
        }

        public Criteria andControllerIsLegalGreaterThan(String value) {
            addCriterion("controller_is_legal >", value, "controllerIsLegal");
            return (Criteria) this;
        }

        public Criteria andControllerIsLegalGreaterThanOrEqualTo(String value) {
            addCriterion("controller_is_legal >=", value, "controllerIsLegal");
            return (Criteria) this;
        }

        public Criteria andControllerIsLegalLessThan(String value) {
            addCriterion("controller_is_legal <", value, "controllerIsLegal");
            return (Criteria) this;
        }

        public Criteria andControllerIsLegalLessThanOrEqualTo(String value) {
            addCriterion("controller_is_legal <=", value, "controllerIsLegal");
            return (Criteria) this;
        }

        public Criteria andControllerIsLegalLike(String value) {
            addCriterion("controller_is_legal like", value, "controllerIsLegal");
            return (Criteria) this;
        }

        public Criteria andControllerIsLegalNotLike(String value) {
            addCriterion("controller_is_legal not like", value, "controllerIsLegal");
            return (Criteria) this;
        }

        public Criteria andControllerIsLegalIn(List<String> values) {
            addCriterion("controller_is_legal in", values, "controllerIsLegal");
            return (Criteria) this;
        }

        public Criteria andControllerIsLegalNotIn(List<String> values) {
            addCriterion("controller_is_legal not in", values, "controllerIsLegal");
            return (Criteria) this;
        }

        public Criteria andControllerIsLegalBetween(String value1, String value2) {
            addCriterion("controller_is_legal between", value1, value2, "controllerIsLegal");
            return (Criteria) this;
        }

        public Criteria andControllerIsLegalNotBetween(String value1, String value2) {
            addCriterion("controller_is_legal not between", value1, value2, "controllerIsLegal");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIsNull() {
            addCriterion("legal_person is null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIsNotNull() {
            addCriterion("legal_person is not null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonEqualTo(String value) {
            addCriterion("legal_person =", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNotEqualTo(String value) {
            addCriterion("legal_person <>", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonGreaterThan(String value) {
            addCriterion("legal_person >", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonGreaterThanOrEqualTo(String value) {
            addCriterion("legal_person >=", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonLessThan(String value) {
            addCriterion("legal_person <", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonLessThanOrEqualTo(String value) {
            addCriterion("legal_person <=", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonLike(String value) {
            addCriterion("legal_person like", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNotLike(String value) {
            addCriterion("legal_person not like", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIn(List<String> values) {
            addCriterion("legal_person in", values, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNotIn(List<String> values) {
            addCriterion("legal_person not in", values, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonBetween(String value1, String value2) {
            addCriterion("legal_person between", value1, value2, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNotBetween(String value1, String value2) {
            addCriterion("legal_person not between", value1, value2, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalEmailIsNull() {
            addCriterion("legal_email is null");
            return (Criteria) this;
        }

        public Criteria andLegalEmailIsNotNull() {
            addCriterion("legal_email is not null");
            return (Criteria) this;
        }

        public Criteria andLegalEmailEqualTo(String value) {
            addCriterion("legal_email =", value, "legalEmail");
            return (Criteria) this;
        }

        public Criteria andLegalEmailNotEqualTo(String value) {
            addCriterion("legal_email <>", value, "legalEmail");
            return (Criteria) this;
        }

        public Criteria andLegalEmailGreaterThan(String value) {
            addCriterion("legal_email >", value, "legalEmail");
            return (Criteria) this;
        }

        public Criteria andLegalEmailGreaterThanOrEqualTo(String value) {
            addCriterion("legal_email >=", value, "legalEmail");
            return (Criteria) this;
        }

        public Criteria andLegalEmailLessThan(String value) {
            addCriterion("legal_email <", value, "legalEmail");
            return (Criteria) this;
        }

        public Criteria andLegalEmailLessThanOrEqualTo(String value) {
            addCriterion("legal_email <=", value, "legalEmail");
            return (Criteria) this;
        }

        public Criteria andLegalEmailLike(String value) {
            addCriterion("legal_email like", value, "legalEmail");
            return (Criteria) this;
        }

        public Criteria andLegalEmailNotLike(String value) {
            addCriterion("legal_email not like", value, "legalEmail");
            return (Criteria) this;
        }

        public Criteria andLegalEmailIn(List<String> values) {
            addCriterion("legal_email in", values, "legalEmail");
            return (Criteria) this;
        }

        public Criteria andLegalEmailNotIn(List<String> values) {
            addCriterion("legal_email not in", values, "legalEmail");
            return (Criteria) this;
        }

        public Criteria andLegalEmailBetween(String value1, String value2) {
            addCriterion("legal_email between", value1, value2, "legalEmail");
            return (Criteria) this;
        }

        public Criteria andLegalEmailNotBetween(String value1, String value2) {
            addCriterion("legal_email not between", value1, value2, "legalEmail");
            return (Criteria) this;
        }

        public Criteria andLegalPhoneIsNull() {
            addCriterion("legal_phone is null");
            return (Criteria) this;
        }

        public Criteria andLegalPhoneIsNotNull() {
            addCriterion("legal_phone is not null");
            return (Criteria) this;
        }

        public Criteria andLegalPhoneEqualTo(String value) {
            addCriterion("legal_phone =", value, "legalPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPhoneNotEqualTo(String value) {
            addCriterion("legal_phone <>", value, "legalPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPhoneGreaterThan(String value) {
            addCriterion("legal_phone >", value, "legalPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("legal_phone >=", value, "legalPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPhoneLessThan(String value) {
            addCriterion("legal_phone <", value, "legalPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPhoneLessThanOrEqualTo(String value) {
            addCriterion("legal_phone <=", value, "legalPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPhoneLike(String value) {
            addCriterion("legal_phone like", value, "legalPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPhoneNotLike(String value) {
            addCriterion("legal_phone not like", value, "legalPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPhoneIn(List<String> values) {
            addCriterion("legal_phone in", values, "legalPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPhoneNotIn(List<String> values) {
            addCriterion("legal_phone not in", values, "legalPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPhoneBetween(String value1, String value2) {
            addCriterion("legal_phone between", value1, value2, "legalPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPhoneNotBetween(String value1, String value2) {
            addCriterion("legal_phone not between", value1, value2, "legalPhone");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerIsNull() {
            addCriterion("account_owner is null");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerIsNotNull() {
            addCriterion("account_owner is not null");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerEqualTo(String value) {
            addCriterion("account_owner =", value, "accountOwner");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerNotEqualTo(String value) {
            addCriterion("account_owner <>", value, "accountOwner");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerGreaterThan(String value) {
            addCriterion("account_owner >", value, "accountOwner");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerGreaterThanOrEqualTo(String value) {
            addCriterion("account_owner >=", value, "accountOwner");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerLessThan(String value) {
            addCriterion("account_owner <", value, "accountOwner");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerLessThanOrEqualTo(String value) {
            addCriterion("account_owner <=", value, "accountOwner");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerLike(String value) {
            addCriterion("account_owner like", value, "accountOwner");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerNotLike(String value) {
            addCriterion("account_owner not like", value, "accountOwner");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerIn(List<String> values) {
            addCriterion("account_owner in", values, "accountOwner");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerNotIn(List<String> values) {
            addCriterion("account_owner not in", values, "accountOwner");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerBetween(String value1, String value2) {
            addCriterion("account_owner between", value1, value2, "accountOwner");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerNotBetween(String value1, String value2) {
            addCriterion("account_owner not between", value1, value2, "accountOwner");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerNameIsNull() {
            addCriterion("account_owner_name is null");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerNameIsNotNull() {
            addCriterion("account_owner_name is not null");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerNameEqualTo(String value) {
            addCriterion("account_owner_name =", value, "accountOwnerName");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerNameNotEqualTo(String value) {
            addCriterion("account_owner_name <>", value, "accountOwnerName");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerNameGreaterThan(String value) {
            addCriterion("account_owner_name >", value, "accountOwnerName");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerNameGreaterThanOrEqualTo(String value) {
            addCriterion("account_owner_name >=", value, "accountOwnerName");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerNameLessThan(String value) {
            addCriterion("account_owner_name <", value, "accountOwnerName");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerNameLessThanOrEqualTo(String value) {
            addCriterion("account_owner_name <=", value, "accountOwnerName");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerNameLike(String value) {
            addCriterion("account_owner_name like", value, "accountOwnerName");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerNameNotLike(String value) {
            addCriterion("account_owner_name not like", value, "accountOwnerName");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerNameIn(List<String> values) {
            addCriterion("account_owner_name in", values, "accountOwnerName");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerNameNotIn(List<String> values) {
            addCriterion("account_owner_name not in", values, "accountOwnerName");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerNameBetween(String value1, String value2) {
            addCriterion("account_owner_name between", value1, value2, "accountOwnerName");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerNameNotBetween(String value1, String value2) {
            addCriterion("account_owner_name not between", value1, value2, "accountOwnerName");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerEmailIsNull() {
            addCriterion("account_owner_email is null");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerEmailIsNotNull() {
            addCriterion("account_owner_email is not null");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerEmailEqualTo(String value) {
            addCriterion("account_owner_email =", value, "accountOwnerEmail");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerEmailNotEqualTo(String value) {
            addCriterion("account_owner_email <>", value, "accountOwnerEmail");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerEmailGreaterThan(String value) {
            addCriterion("account_owner_email >", value, "accountOwnerEmail");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerEmailGreaterThanOrEqualTo(String value) {
            addCriterion("account_owner_email >=", value, "accountOwnerEmail");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerEmailLessThan(String value) {
            addCriterion("account_owner_email <", value, "accountOwnerEmail");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerEmailLessThanOrEqualTo(String value) {
            addCriterion("account_owner_email <=", value, "accountOwnerEmail");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerEmailLike(String value) {
            addCriterion("account_owner_email like", value, "accountOwnerEmail");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerEmailNotLike(String value) {
            addCriterion("account_owner_email not like", value, "accountOwnerEmail");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerEmailIn(List<String> values) {
            addCriterion("account_owner_email in", values, "accountOwnerEmail");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerEmailNotIn(List<String> values) {
            addCriterion("account_owner_email not in", values, "accountOwnerEmail");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerEmailBetween(String value1, String value2) {
            addCriterion("account_owner_email between", value1, value2, "accountOwnerEmail");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerEmailNotBetween(String value1, String value2) {
            addCriterion("account_owner_email not between", value1, value2, "accountOwnerEmail");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerPhoneIsNull() {
            addCriterion("account_owner_phone is null");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerPhoneIsNotNull() {
            addCriterion("account_owner_phone is not null");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerPhoneEqualTo(String value) {
            addCriterion("account_owner_phone =", value, "accountOwnerPhone");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerPhoneNotEqualTo(String value) {
            addCriterion("account_owner_phone <>", value, "accountOwnerPhone");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerPhoneGreaterThan(String value) {
            addCriterion("account_owner_phone >", value, "accountOwnerPhone");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("account_owner_phone >=", value, "accountOwnerPhone");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerPhoneLessThan(String value) {
            addCriterion("account_owner_phone <", value, "accountOwnerPhone");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerPhoneLessThanOrEqualTo(String value) {
            addCriterion("account_owner_phone <=", value, "accountOwnerPhone");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerPhoneLike(String value) {
            addCriterion("account_owner_phone like", value, "accountOwnerPhone");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerPhoneNotLike(String value) {
            addCriterion("account_owner_phone not like", value, "accountOwnerPhone");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerPhoneIn(List<String> values) {
            addCriterion("account_owner_phone in", values, "accountOwnerPhone");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerPhoneNotIn(List<String> values) {
            addCriterion("account_owner_phone not in", values, "accountOwnerPhone");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerPhoneBetween(String value1, String value2) {
            addCriterion("account_owner_phone between", value1, value2, "accountOwnerPhone");
            return (Criteria) this;
        }

        public Criteria andAccountOwnerPhoneNotBetween(String value1, String value2) {
            addCriterion("account_owner_phone not between", value1, value2, "accountOwnerPhone");
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

        public Criteria andShortLoanLimitIsNull() {
            addCriterion("short_loan_limit is null");
            return (Criteria) this;
        }

        public Criteria andShortLoanLimitIsNotNull() {
            addCriterion("short_loan_limit is not null");
            return (Criteria) this;
        }

        public Criteria andShortLoanLimitEqualTo(Integer value) {
            addCriterion("short_loan_limit =", value, "shortLoanLimit");
            return (Criteria) this;
        }

        public Criteria andShortLoanLimitNotEqualTo(Integer value) {
            addCriterion("short_loan_limit <>", value, "shortLoanLimit");
            return (Criteria) this;
        }

        public Criteria andShortLoanLimitGreaterThan(Integer value) {
            addCriterion("short_loan_limit >", value, "shortLoanLimit");
            return (Criteria) this;
        }

        public Criteria andShortLoanLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("short_loan_limit >=", value, "shortLoanLimit");
            return (Criteria) this;
        }

        public Criteria andShortLoanLimitLessThan(Integer value) {
            addCriterion("short_loan_limit <", value, "shortLoanLimit");
            return (Criteria) this;
        }

        public Criteria andShortLoanLimitLessThanOrEqualTo(Integer value) {
            addCriterion("short_loan_limit <=", value, "shortLoanLimit");
            return (Criteria) this;
        }

        public Criteria andShortLoanLimitIn(List<Integer> values) {
            addCriterion("short_loan_limit in", values, "shortLoanLimit");
            return (Criteria) this;
        }

        public Criteria andShortLoanLimitNotIn(List<Integer> values) {
            addCriterion("short_loan_limit not in", values, "shortLoanLimit");
            return (Criteria) this;
        }

        public Criteria andShortLoanLimitBetween(Integer value1, Integer value2) {
            addCriterion("short_loan_limit between", value1, value2, "shortLoanLimit");
            return (Criteria) this;
        }

        public Criteria andShortLoanLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("short_loan_limit not between", value1, value2, "shortLoanLimit");
            return (Criteria) this;
        }

        public Criteria andLoanFromIsNull() {
            addCriterion("loan_from is null");
            return (Criteria) this;
        }

        public Criteria andLoanFromIsNotNull() {
            addCriterion("loan_from is not null");
            return (Criteria) this;
        }

        public Criteria andLoanFromEqualTo(String value) {
            addCriterion("loan_from =", value, "loanFrom");
            return (Criteria) this;
        }

        public Criteria andLoanFromNotEqualTo(String value) {
            addCriterion("loan_from <>", value, "loanFrom");
            return (Criteria) this;
        }

        public Criteria andLoanFromGreaterThan(String value) {
            addCriterion("loan_from >", value, "loanFrom");
            return (Criteria) this;
        }

        public Criteria andLoanFromGreaterThanOrEqualTo(String value) {
            addCriterion("loan_from >=", value, "loanFrom");
            return (Criteria) this;
        }

        public Criteria andLoanFromLessThan(String value) {
            addCriterion("loan_from <", value, "loanFrom");
            return (Criteria) this;
        }

        public Criteria andLoanFromLessThanOrEqualTo(String value) {
            addCriterion("loan_from <=", value, "loanFrom");
            return (Criteria) this;
        }

        public Criteria andLoanFromLike(String value) {
            addCriterion("loan_from like", value, "loanFrom");
            return (Criteria) this;
        }

        public Criteria andLoanFromNotLike(String value) {
            addCriterion("loan_from not like", value, "loanFrom");
            return (Criteria) this;
        }

        public Criteria andLoanFromIn(List<String> values) {
            addCriterion("loan_from in", values, "loanFrom");
            return (Criteria) this;
        }

        public Criteria andLoanFromNotIn(List<String> values) {
            addCriterion("loan_from not in", values, "loanFrom");
            return (Criteria) this;
        }

        public Criteria andLoanFromBetween(String value1, String value2) {
            addCriterion("loan_from between", value1, value2, "loanFrom");
            return (Criteria) this;
        }

        public Criteria andLoanFromNotBetween(String value1, String value2) {
            addCriterion("loan_from not between", value1, value2, "loanFrom");
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

        public Criteria andAgentQualificationIsNull() {
            addCriterion("agent_qualification is null");
            return (Criteria) this;
        }

        public Criteria andAgentQualificationIsNotNull() {
            addCriterion("agent_qualification is not null");
            return (Criteria) this;
        }

        public Criteria andAgentQualificationEqualTo(String value) {
            addCriterion("agent_qualification =", value, "agentQualification");
            return (Criteria) this;
        }

        public Criteria andAgentQualificationNotEqualTo(String value) {
            addCriterion("agent_qualification <>", value, "agentQualification");
            return (Criteria) this;
        }

        public Criteria andAgentQualificationGreaterThan(String value) {
            addCriterion("agent_qualification >", value, "agentQualification");
            return (Criteria) this;
        }

        public Criteria andAgentQualificationGreaterThanOrEqualTo(String value) {
            addCriterion("agent_qualification >=", value, "agentQualification");
            return (Criteria) this;
        }

        public Criteria andAgentQualificationLessThan(String value) {
            addCriterion("agent_qualification <", value, "agentQualification");
            return (Criteria) this;
        }

        public Criteria andAgentQualificationLessThanOrEqualTo(String value) {
            addCriterion("agent_qualification <=", value, "agentQualification");
            return (Criteria) this;
        }

        public Criteria andAgentQualificationLike(String value) {
            addCriterion("agent_qualification like", value, "agentQualification");
            return (Criteria) this;
        }

        public Criteria andAgentQualificationNotLike(String value) {
            addCriterion("agent_qualification not like", value, "agentQualification");
            return (Criteria) this;
        }

        public Criteria andAgentQualificationIn(List<String> values) {
            addCriterion("agent_qualification in", values, "agentQualification");
            return (Criteria) this;
        }

        public Criteria andAgentQualificationNotIn(List<String> values) {
            addCriterion("agent_qualification not in", values, "agentQualification");
            return (Criteria) this;
        }

        public Criteria andAgentQualificationBetween(String value1, String value2) {
            addCriterion("agent_qualification between", value1, value2, "agentQualification");
            return (Criteria) this;
        }

        public Criteria andAgentQualificationNotBetween(String value1, String value2) {
            addCriterion("agent_qualification not between", value1, value2, "agentQualification");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoIsNull() {
            addCriterion("legal_idno is null");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoIsNotNull() {
            addCriterion("legal_idno is not null");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoEqualTo(String value) {
            addCriterion("legal_idno =", value, "legalIdno");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoNotEqualTo(String value) {
            addCriterion("legal_idno <>", value, "legalIdno");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoGreaterThan(String value) {
            addCriterion("legal_idno >", value, "legalIdno");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoGreaterThanOrEqualTo(String value) {
            addCriterion("legal_idno >=", value, "legalIdno");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoLessThan(String value) {
            addCriterion("legal_idno <", value, "legalIdno");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoLessThanOrEqualTo(String value) {
            addCriterion("legal_idno <=", value, "legalIdno");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoLike(String value) {
            addCriterion("legal_idno like", value, "legalIdno");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoNotLike(String value) {
            addCriterion("legal_idno not like", value, "legalIdno");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoIn(List<String> values) {
            addCriterion("legal_idno in", values, "legalIdno");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoNotIn(List<String> values) {
            addCriterion("legal_idno not in", values, "legalIdno");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoBetween(String value1, String value2) {
            addCriterion("legal_idno between", value1, value2, "legalIdno");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoNotBetween(String value1, String value2) {
            addCriterion("legal_idno not between", value1, value2, "legalIdno");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoFrontMediaIdIsNull() {
            addCriterion("legal_idno_front_media_id is null");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoFrontMediaIdIsNotNull() {
            addCriterion("legal_idno_front_media_id is not null");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoFrontMediaIdEqualTo(String value) {
            addCriterion("legal_idno_front_media_id =", value, "legalIdnoFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoFrontMediaIdNotEqualTo(String value) {
            addCriterion("legal_idno_front_media_id <>", value, "legalIdnoFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoFrontMediaIdGreaterThan(String value) {
            addCriterion("legal_idno_front_media_id >", value, "legalIdnoFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoFrontMediaIdGreaterThanOrEqualTo(String value) {
            addCriterion("legal_idno_front_media_id >=", value, "legalIdnoFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoFrontMediaIdLessThan(String value) {
            addCriterion("legal_idno_front_media_id <", value, "legalIdnoFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoFrontMediaIdLessThanOrEqualTo(String value) {
            addCriterion("legal_idno_front_media_id <=", value, "legalIdnoFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoFrontMediaIdLike(String value) {
            addCriterion("legal_idno_front_media_id like", value, "legalIdnoFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoFrontMediaIdNotLike(String value) {
            addCriterion("legal_idno_front_media_id not like", value, "legalIdnoFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoFrontMediaIdIn(List<String> values) {
            addCriterion("legal_idno_front_media_id in", values, "legalIdnoFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoFrontMediaIdNotIn(List<String> values) {
            addCriterion("legal_idno_front_media_id not in", values, "legalIdnoFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoFrontMediaIdBetween(String value1, String value2) {
            addCriterion("legal_idno_front_media_id between", value1, value2, "legalIdnoFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoFrontMediaIdNotBetween(String value1, String value2) {
            addCriterion("legal_idno_front_media_id not between", value1, value2, "legalIdnoFrontMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoBackMediaIdIsNull() {
            addCriterion("legal_idno_back_media_id is null");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoBackMediaIdIsNotNull() {
            addCriterion("legal_idno_back_media_id is not null");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoBackMediaIdEqualTo(String value) {
            addCriterion("legal_idno_back_media_id =", value, "legalIdnoBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoBackMediaIdNotEqualTo(String value) {
            addCriterion("legal_idno_back_media_id <>", value, "legalIdnoBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoBackMediaIdGreaterThan(String value) {
            addCriterion("legal_idno_back_media_id >", value, "legalIdnoBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoBackMediaIdGreaterThanOrEqualTo(String value) {
            addCriterion("legal_idno_back_media_id >=", value, "legalIdnoBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoBackMediaIdLessThan(String value) {
            addCriterion("legal_idno_back_media_id <", value, "legalIdnoBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoBackMediaIdLessThanOrEqualTo(String value) {
            addCriterion("legal_idno_back_media_id <=", value, "legalIdnoBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoBackMediaIdLike(String value) {
            addCriterion("legal_idno_back_media_id like", value, "legalIdnoBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoBackMediaIdNotLike(String value) {
            addCriterion("legal_idno_back_media_id not like", value, "legalIdnoBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoBackMediaIdIn(List<String> values) {
            addCriterion("legal_idno_back_media_id in", values, "legalIdnoBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoBackMediaIdNotIn(List<String> values) {
            addCriterion("legal_idno_back_media_id not in", values, "legalIdnoBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoBackMediaIdBetween(String value1, String value2) {
            addCriterion("legal_idno_back_media_id between", value1, value2, "legalIdnoBackMediaId");
            return (Criteria) this;
        }

        public Criteria andLegalIdnoBackMediaIdNotBetween(String value1, String value2) {
            addCriterion("legal_idno_back_media_id not between", value1, value2, "legalIdnoBackMediaId");
            return (Criteria) this;
        }

        public Criteria andAdditionInformationMediaIdIsNull() {
            addCriterion("addition_information_media_id is null");
            return (Criteria) this;
        }

        public Criteria andAdditionInformationMediaIdIsNotNull() {
            addCriterion("addition_information_media_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdditionInformationMediaIdEqualTo(String value) {
            addCriterion("addition_information_media_id =", value, "additionInformationMediaId");
            return (Criteria) this;
        }

        public Criteria andAdditionInformationMediaIdNotEqualTo(String value) {
            addCriterion("addition_information_media_id <>", value, "additionInformationMediaId");
            return (Criteria) this;
        }

        public Criteria andAdditionInformationMediaIdGreaterThan(String value) {
            addCriterion("addition_information_media_id >", value, "additionInformationMediaId");
            return (Criteria) this;
        }

        public Criteria andAdditionInformationMediaIdGreaterThanOrEqualTo(String value) {
            addCriterion("addition_information_media_id >=", value, "additionInformationMediaId");
            return (Criteria) this;
        }

        public Criteria andAdditionInformationMediaIdLessThan(String value) {
            addCriterion("addition_information_media_id <", value, "additionInformationMediaId");
            return (Criteria) this;
        }

        public Criteria andAdditionInformationMediaIdLessThanOrEqualTo(String value) {
            addCriterion("addition_information_media_id <=", value, "additionInformationMediaId");
            return (Criteria) this;
        }

        public Criteria andAdditionInformationMediaIdLike(String value) {
            addCriterion("addition_information_media_id like", value, "additionInformationMediaId");
            return (Criteria) this;
        }

        public Criteria andAdditionInformationMediaIdNotLike(String value) {
            addCriterion("addition_information_media_id not like", value, "additionInformationMediaId");
            return (Criteria) this;
        }

        public Criteria andAdditionInformationMediaIdIn(List<String> values) {
            addCriterion("addition_information_media_id in", values, "additionInformationMediaId");
            return (Criteria) this;
        }

        public Criteria andAdditionInformationMediaIdNotIn(List<String> values) {
            addCriterion("addition_information_media_id not in", values, "additionInformationMediaId");
            return (Criteria) this;
        }

        public Criteria andAdditionInformationMediaIdBetween(String value1, String value2) {
            addCriterion("addition_information_media_id between", value1, value2, "additionInformationMediaId");
            return (Criteria) this;
        }

        public Criteria andAdditionInformationMediaIdNotBetween(String value1, String value2) {
            addCriterion("addition_information_media_id not between", value1, value2, "additionInformationMediaId");
            return (Criteria) this;
        }

        public Criteria andWhetherTbOrderIsNull() {
            addCriterion("whether_tb_order is null");
            return (Criteria) this;
        }

        public Criteria andWhetherTbOrderIsNotNull() {
            addCriterion("whether_tb_order is not null");
            return (Criteria) this;
        }

        public Criteria andWhetherTbOrderEqualTo(String value) {
            addCriterion("whether_tb_order =", value, "whetherTbOrder");
            return (Criteria) this;
        }

        public Criteria andWhetherTbOrderNotEqualTo(String value) {
            addCriterion("whether_tb_order <>", value, "whetherTbOrder");
            return (Criteria) this;
        }

        public Criteria andWhetherTbOrderGreaterThan(String value) {
            addCriterion("whether_tb_order >", value, "whetherTbOrder");
            return (Criteria) this;
        }

        public Criteria andWhetherTbOrderGreaterThanOrEqualTo(String value) {
            addCriterion("whether_tb_order >=", value, "whetherTbOrder");
            return (Criteria) this;
        }

        public Criteria andWhetherTbOrderLessThan(String value) {
            addCriterion("whether_tb_order <", value, "whetherTbOrder");
            return (Criteria) this;
        }

        public Criteria andWhetherTbOrderLessThanOrEqualTo(String value) {
            addCriterion("whether_tb_order <=", value, "whetherTbOrder");
            return (Criteria) this;
        }

        public Criteria andWhetherTbOrderLike(String value) {
            addCriterion("whether_tb_order like", value, "whetherTbOrder");
            return (Criteria) this;
        }

        public Criteria andWhetherTbOrderNotLike(String value) {
            addCriterion("whether_tb_order not like", value, "whetherTbOrder");
            return (Criteria) this;
        }

        public Criteria andWhetherTbOrderIn(List<String> values) {
            addCriterion("whether_tb_order in", values, "whetherTbOrder");
            return (Criteria) this;
        }

        public Criteria andWhetherTbOrderNotIn(List<String> values) {
            addCriterion("whether_tb_order not in", values, "whetherTbOrder");
            return (Criteria) this;
        }

        public Criteria andWhetherTbOrderBetween(String value1, String value2) {
            addCriterion("whether_tb_order between", value1, value2, "whetherTbOrder");
            return (Criteria) this;
        }

        public Criteria andWhetherTbOrderNotBetween(String value1, String value2) {
            addCriterion("whether_tb_order not between", value1, value2, "whetherTbOrder");
            return (Criteria) this;
        }

        public Criteria andTbOrderRatioIsNull() {
            addCriterion("tb_order_ratio is null");
            return (Criteria) this;
        }

        public Criteria andTbOrderRatioIsNotNull() {
            addCriterion("tb_order_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andTbOrderRatioEqualTo(BigDecimal value) {
            addCriterion("tb_order_ratio =", value, "tbOrderRatio");
            return (Criteria) this;
        }

        public Criteria andTbOrderRatioNotEqualTo(BigDecimal value) {
            addCriterion("tb_order_ratio <>", value, "tbOrderRatio");
            return (Criteria) this;
        }

        public Criteria andTbOrderRatioGreaterThan(BigDecimal value) {
            addCriterion("tb_order_ratio >", value, "tbOrderRatio");
            return (Criteria) this;
        }

        public Criteria andTbOrderRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tb_order_ratio >=", value, "tbOrderRatio");
            return (Criteria) this;
        }

        public Criteria andTbOrderRatioLessThan(BigDecimal value) {
            addCriterion("tb_order_ratio <", value, "tbOrderRatio");
            return (Criteria) this;
        }

        public Criteria andTbOrderRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tb_order_ratio <=", value, "tbOrderRatio");
            return (Criteria) this;
        }

        public Criteria andTbOrderRatioIn(List<BigDecimal> values) {
            addCriterion("tb_order_ratio in", values, "tbOrderRatio");
            return (Criteria) this;
        }

        public Criteria andTbOrderRatioNotIn(List<BigDecimal> values) {
            addCriterion("tb_order_ratio not in", values, "tbOrderRatio");
            return (Criteria) this;
        }

        public Criteria andTbOrderRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tb_order_ratio between", value1, value2, "tbOrderRatio");
            return (Criteria) this;
        }

        public Criteria andTbOrderRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tb_order_ratio not between", value1, value2, "tbOrderRatio");
            return (Criteria) this;
        }

        public Criteria andYundaexExplainIsNull() {
            addCriterion("yundaex_explain is null");
            return (Criteria) this;
        }

        public Criteria andYundaexExplainIsNotNull() {
            addCriterion("yundaex_explain is not null");
            return (Criteria) this;
        }

        public Criteria andYundaexExplainEqualTo(String value) {
            addCriterion("yundaex_explain =", value, "yundaexExplain");
            return (Criteria) this;
        }

        public Criteria andYundaexExplainNotEqualTo(String value) {
            addCriterion("yundaex_explain <>", value, "yundaexExplain");
            return (Criteria) this;
        }

        public Criteria andYundaexExplainGreaterThan(String value) {
            addCriterion("yundaex_explain >", value, "yundaexExplain");
            return (Criteria) this;
        }

        public Criteria andYundaexExplainGreaterThanOrEqualTo(String value) {
            addCriterion("yundaex_explain >=", value, "yundaexExplain");
            return (Criteria) this;
        }

        public Criteria andYundaexExplainLessThan(String value) {
            addCriterion("yundaex_explain <", value, "yundaexExplain");
            return (Criteria) this;
        }

        public Criteria andYundaexExplainLessThanOrEqualTo(String value) {
            addCriterion("yundaex_explain <=", value, "yundaexExplain");
            return (Criteria) this;
        }

        public Criteria andYundaexExplainLike(String value) {
            addCriterion("yundaex_explain like", value, "yundaexExplain");
            return (Criteria) this;
        }

        public Criteria andYundaexExplainNotLike(String value) {
            addCriterion("yundaex_explain not like", value, "yundaexExplain");
            return (Criteria) this;
        }

        public Criteria andYundaexExplainIn(List<String> values) {
            addCriterion("yundaex_explain in", values, "yundaexExplain");
            return (Criteria) this;
        }

        public Criteria andYundaexExplainNotIn(List<String> values) {
            addCriterion("yundaex_explain not in", values, "yundaexExplain");
            return (Criteria) this;
        }

        public Criteria andYundaexExplainBetween(String value1, String value2) {
            addCriterion("yundaex_explain between", value1, value2, "yundaexExplain");
            return (Criteria) this;
        }

        public Criteria andYundaexExplainNotBetween(String value1, String value2) {
            addCriterion("yundaex_explain not between", value1, value2, "yundaexExplain");
            return (Criteria) this;
        }

        public Criteria andOtherExplainIsNull() {
            addCriterion("other_explain is null");
            return (Criteria) this;
        }

        public Criteria andOtherExplainIsNotNull() {
            addCriterion("other_explain is not null");
            return (Criteria) this;
        }

        public Criteria andOtherExplainEqualTo(String value) {
            addCriterion("other_explain =", value, "otherExplain");
            return (Criteria) this;
        }

        public Criteria andOtherExplainNotEqualTo(String value) {
            addCriterion("other_explain <>", value, "otherExplain");
            return (Criteria) this;
        }

        public Criteria andOtherExplainGreaterThan(String value) {
            addCriterion("other_explain >", value, "otherExplain");
            return (Criteria) this;
        }

        public Criteria andOtherExplainGreaterThanOrEqualTo(String value) {
            addCriterion("other_explain >=", value, "otherExplain");
            return (Criteria) this;
        }

        public Criteria andOtherExplainLessThan(String value) {
            addCriterion("other_explain <", value, "otherExplain");
            return (Criteria) this;
        }

        public Criteria andOtherExplainLessThanOrEqualTo(String value) {
            addCriterion("other_explain <=", value, "otherExplain");
            return (Criteria) this;
        }

        public Criteria andOtherExplainLike(String value) {
            addCriterion("other_explain like", value, "otherExplain");
            return (Criteria) this;
        }

        public Criteria andOtherExplainNotLike(String value) {
            addCriterion("other_explain not like", value, "otherExplain");
            return (Criteria) this;
        }

        public Criteria andOtherExplainIn(List<String> values) {
            addCriterion("other_explain in", values, "otherExplain");
            return (Criteria) this;
        }

        public Criteria andOtherExplainNotIn(List<String> values) {
            addCriterion("other_explain not in", values, "otherExplain");
            return (Criteria) this;
        }

        public Criteria andOtherExplainBetween(String value1, String value2) {
            addCriterion("other_explain between", value1, value2, "otherExplain");
            return (Criteria) this;
        }

        public Criteria andOtherExplainNotBetween(String value1, String value2) {
            addCriterion("other_explain not between", value1, value2, "otherExplain");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankNameIsNull() {
            addCriterion("pay_account_bank_name is null");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankNameIsNotNull() {
            addCriterion("pay_account_bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankNameEqualTo(String value) {
            addCriterion("pay_account_bank_name =", value, "payAccountBankName");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankNameNotEqualTo(String value) {
            addCriterion("pay_account_bank_name <>", value, "payAccountBankName");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankNameGreaterThan(String value) {
            addCriterion("pay_account_bank_name >", value, "payAccountBankName");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("pay_account_bank_name >=", value, "payAccountBankName");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankNameLessThan(String value) {
            addCriterion("pay_account_bank_name <", value, "payAccountBankName");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankNameLessThanOrEqualTo(String value) {
            addCriterion("pay_account_bank_name <=", value, "payAccountBankName");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankNameLike(String value) {
            addCriterion("pay_account_bank_name like", value, "payAccountBankName");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankNameNotLike(String value) {
            addCriterion("pay_account_bank_name not like", value, "payAccountBankName");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankNameIn(List<String> values) {
            addCriterion("pay_account_bank_name in", values, "payAccountBankName");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankNameNotIn(List<String> values) {
            addCriterion("pay_account_bank_name not in", values, "payAccountBankName");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankNameBetween(String value1, String value2) {
            addCriterion("pay_account_bank_name between", value1, value2, "payAccountBankName");
            return (Criteria) this;
        }

        public Criteria andPayAccountBankNameNotBetween(String value1, String value2) {
            addCriterion("pay_account_bank_name not between", value1, value2, "payAccountBankName");
            return (Criteria) this;
        }

        public Criteria andBankProvinceIsNull() {
            addCriterion("bank_province is null");
            return (Criteria) this;
        }

        public Criteria andBankProvinceIsNotNull() {
            addCriterion("bank_province is not null");
            return (Criteria) this;
        }

        public Criteria andBankProvinceEqualTo(String value) {
            addCriterion("bank_province =", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceNotEqualTo(String value) {
            addCriterion("bank_province <>", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceGreaterThan(String value) {
            addCriterion("bank_province >", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("bank_province >=", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceLessThan(String value) {
            addCriterion("bank_province <", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceLessThanOrEqualTo(String value) {
            addCriterion("bank_province <=", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceLike(String value) {
            addCriterion("bank_province like", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceNotLike(String value) {
            addCriterion("bank_province not like", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceIn(List<String> values) {
            addCriterion("bank_province in", values, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceNotIn(List<String> values) {
            addCriterion("bank_province not in", values, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceBetween(String value1, String value2) {
            addCriterion("bank_province between", value1, value2, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceNotBetween(String value1, String value2) {
            addCriterion("bank_province not between", value1, value2, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankCityIsNull() {
            addCriterion("bank_city is null");
            return (Criteria) this;
        }

        public Criteria andBankCityIsNotNull() {
            addCriterion("bank_city is not null");
            return (Criteria) this;
        }

        public Criteria andBankCityEqualTo(String value) {
            addCriterion("bank_city =", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityNotEqualTo(String value) {
            addCriterion("bank_city <>", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityGreaterThan(String value) {
            addCriterion("bank_city >", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityGreaterThanOrEqualTo(String value) {
            addCriterion("bank_city >=", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityLessThan(String value) {
            addCriterion("bank_city <", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityLessThanOrEqualTo(String value) {
            addCriterion("bank_city <=", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityLike(String value) {
            addCriterion("bank_city like", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityNotLike(String value) {
            addCriterion("bank_city not like", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityIn(List<String> values) {
            addCriterion("bank_city in", values, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityNotIn(List<String> values) {
            addCriterion("bank_city not in", values, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityBetween(String value1, String value2) {
            addCriterion("bank_city between", value1, value2, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityNotBetween(String value1, String value2) {
            addCriterion("bank_city not between", value1, value2, "bankCity");
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

        public Criteria andStationAddressIsNull() {
            addCriterion("station_address is null");
            return (Criteria) this;
        }

        public Criteria andStationAddressIsNotNull() {
            addCriterion("station_address is not null");
            return (Criteria) this;
        }

        public Criteria andStationAddressEqualTo(String value) {
            addCriterion("station_address =", value, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressNotEqualTo(String value) {
            addCriterion("station_address <>", value, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressGreaterThan(String value) {
            addCriterion("station_address >", value, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressGreaterThanOrEqualTo(String value) {
            addCriterion("station_address >=", value, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressLessThan(String value) {
            addCriterion("station_address <", value, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressLessThanOrEqualTo(String value) {
            addCriterion("station_address <=", value, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressLike(String value) {
            addCriterion("station_address like", value, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressNotLike(String value) {
            addCriterion("station_address not like", value, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressIn(List<String> values) {
            addCriterion("station_address in", values, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressNotIn(List<String> values) {
            addCriterion("station_address not in", values, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressBetween(String value1, String value2) {
            addCriterion("station_address between", value1, value2, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andStationAddressNotBetween(String value1, String value2) {
            addCriterion("station_address not between", value1, value2, "stationAddress");
            return (Criteria) this;
        }

        public Criteria andBailRatioIsNull() {
            addCriterion("bail_ratio is null");
            return (Criteria) this;
        }

        public Criteria andBailRatioIsNotNull() {
            addCriterion("bail_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andBailRatioEqualTo(BigDecimal value) {
            addCriterion("bail_ratio =", value, "bailRatio");
            return (Criteria) this;
        }

        public Criteria andBailRatioNotEqualTo(BigDecimal value) {
            addCriterion("bail_ratio <>", value, "bailRatio");
            return (Criteria) this;
        }

        public Criteria andBailRatioGreaterThan(BigDecimal value) {
            addCriterion("bail_ratio >", value, "bailRatio");
            return (Criteria) this;
        }

        public Criteria andBailRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bail_ratio >=", value, "bailRatio");
            return (Criteria) this;
        }

        public Criteria andBailRatioLessThan(BigDecimal value) {
            addCriterion("bail_ratio <", value, "bailRatio");
            return (Criteria) this;
        }

        public Criteria andBailRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bail_ratio <=", value, "bailRatio");
            return (Criteria) this;
        }

        public Criteria andBailRatioIn(List<BigDecimal> values) {
            addCriterion("bail_ratio in", values, "bailRatio");
            return (Criteria) this;
        }

        public Criteria andBailRatioNotIn(List<BigDecimal> values) {
            addCriterion("bail_ratio not in", values, "bailRatio");
            return (Criteria) this;
        }

        public Criteria andBailRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bail_ratio between", value1, value2, "bailRatio");
            return (Criteria) this;
        }

        public Criteria andBailRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bail_ratio not between", value1, value2, "bailRatio");
            return (Criteria) this;
        }

        public Criteria andGradeStateIsNull() {
            addCriterion("grade_state is null");
            return (Criteria) this;
        }

        public Criteria andGradeStateIsNotNull() {
            addCriterion("grade_state is not null");
            return (Criteria) this;
        }

        public Criteria andGradeStateEqualTo(String value) {
            addCriterion("grade_state =", value, "gradeState");
            return (Criteria) this;
        }

        public Criteria andGradeStateNotEqualTo(String value) {
            addCriterion("grade_state <>", value, "gradeState");
            return (Criteria) this;
        }

        public Criteria andGradeStateGreaterThan(String value) {
            addCriterion("grade_state >", value, "gradeState");
            return (Criteria) this;
        }

        public Criteria andGradeStateGreaterThanOrEqualTo(String value) {
            addCriterion("grade_state >=", value, "gradeState");
            return (Criteria) this;
        }

        public Criteria andGradeStateLessThan(String value) {
            addCriterion("grade_state <", value, "gradeState");
            return (Criteria) this;
        }

        public Criteria andGradeStateLessThanOrEqualTo(String value) {
            addCriterion("grade_state <=", value, "gradeState");
            return (Criteria) this;
        }

        public Criteria andGradeStateLike(String value) {
            addCriterion("grade_state like", value, "gradeState");
            return (Criteria) this;
        }

        public Criteria andGradeStateNotLike(String value) {
            addCriterion("grade_state not like", value, "gradeState");
            return (Criteria) this;
        }

        public Criteria andGradeStateIn(List<String> values) {
            addCriterion("grade_state in", values, "gradeState");
            return (Criteria) this;
        }

        public Criteria andGradeStateNotIn(List<String> values) {
            addCriterion("grade_state not in", values, "gradeState");
            return (Criteria) this;
        }

        public Criteria andGradeStateBetween(String value1, String value2) {
            addCriterion("grade_state between", value1, value2, "gradeState");
            return (Criteria) this;
        }

        public Criteria andGradeStateNotBetween(String value1, String value2) {
            addCriterion("grade_state not between", value1, value2, "gradeState");
            return (Criteria) this;
        }

        public Criteria andGradeStateRemarksIsNull() {
            addCriterion("grade_state_remarks is null");
            return (Criteria) this;
        }

        public Criteria andGradeStateRemarksIsNotNull() {
            addCriterion("grade_state_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andGradeStateRemarksEqualTo(String value) {
            addCriterion("grade_state_remarks =", value, "gradeStateRemarks");
            return (Criteria) this;
        }

        public Criteria andGradeStateRemarksNotEqualTo(String value) {
            addCriterion("grade_state_remarks <>", value, "gradeStateRemarks");
            return (Criteria) this;
        }

        public Criteria andGradeStateRemarksGreaterThan(String value) {
            addCriterion("grade_state_remarks >", value, "gradeStateRemarks");
            return (Criteria) this;
        }

        public Criteria andGradeStateRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("grade_state_remarks >=", value, "gradeStateRemarks");
            return (Criteria) this;
        }

        public Criteria andGradeStateRemarksLessThan(String value) {
            addCriterion("grade_state_remarks <", value, "gradeStateRemarks");
            return (Criteria) this;
        }

        public Criteria andGradeStateRemarksLessThanOrEqualTo(String value) {
            addCriterion("grade_state_remarks <=", value, "gradeStateRemarks");
            return (Criteria) this;
        }

        public Criteria andGradeStateRemarksLike(String value) {
            addCriterion("grade_state_remarks like", value, "gradeStateRemarks");
            return (Criteria) this;
        }

        public Criteria andGradeStateRemarksNotLike(String value) {
            addCriterion("grade_state_remarks not like", value, "gradeStateRemarks");
            return (Criteria) this;
        }

        public Criteria andGradeStateRemarksIn(List<String> values) {
            addCriterion("grade_state_remarks in", values, "gradeStateRemarks");
            return (Criteria) this;
        }

        public Criteria andGradeStateRemarksNotIn(List<String> values) {
            addCriterion("grade_state_remarks not in", values, "gradeStateRemarks");
            return (Criteria) this;
        }

        public Criteria andGradeStateRemarksBetween(String value1, String value2) {
            addCriterion("grade_state_remarks between", value1, value2, "gradeStateRemarks");
            return (Criteria) this;
        }

        public Criteria andGradeStateRemarksNotBetween(String value1, String value2) {
            addCriterion("grade_state_remarks not between", value1, value2, "gradeStateRemarks");
            return (Criteria) this;
        }

        public Criteria andLimitInfoRemarksIsNull() {
            addCriterion("limit_info_remarks is null");
            return (Criteria) this;
        }

        public Criteria andLimitInfoRemarksIsNotNull() {
            addCriterion("limit_info_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andLimitInfoRemarksEqualTo(String value) {
            addCriterion("limit_info_remarks =", value, "limitInfoRemarks");
            return (Criteria) this;
        }

        public Criteria andLimitInfoRemarksNotEqualTo(String value) {
            addCriterion("limit_info_remarks <>", value, "limitInfoRemarks");
            return (Criteria) this;
        }

        public Criteria andLimitInfoRemarksGreaterThan(String value) {
            addCriterion("limit_info_remarks >", value, "limitInfoRemarks");
            return (Criteria) this;
        }

        public Criteria andLimitInfoRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("limit_info_remarks >=", value, "limitInfoRemarks");
            return (Criteria) this;
        }

        public Criteria andLimitInfoRemarksLessThan(String value) {
            addCriterion("limit_info_remarks <", value, "limitInfoRemarks");
            return (Criteria) this;
        }

        public Criteria andLimitInfoRemarksLessThanOrEqualTo(String value) {
            addCriterion("limit_info_remarks <=", value, "limitInfoRemarks");
            return (Criteria) this;
        }

        public Criteria andLimitInfoRemarksLike(String value) {
            addCriterion("limit_info_remarks like", value, "limitInfoRemarks");
            return (Criteria) this;
        }

        public Criteria andLimitInfoRemarksNotLike(String value) {
            addCriterion("limit_info_remarks not like", value, "limitInfoRemarks");
            return (Criteria) this;
        }

        public Criteria andLimitInfoRemarksIn(List<String> values) {
            addCriterion("limit_info_remarks in", values, "limitInfoRemarks");
            return (Criteria) this;
        }

        public Criteria andLimitInfoRemarksNotIn(List<String> values) {
            addCriterion("limit_info_remarks not in", values, "limitInfoRemarks");
            return (Criteria) this;
        }

        public Criteria andLimitInfoRemarksBetween(String value1, String value2) {
            addCriterion("limit_info_remarks between", value1, value2, "limitInfoRemarks");
            return (Criteria) this;
        }

        public Criteria andLimitInfoRemarksNotBetween(String value1, String value2) {
            addCriterion("limit_info_remarks not between", value1, value2, "limitInfoRemarks");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIsNull() {
            addCriterion("apply_type is null");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIsNotNull() {
            addCriterion("apply_type is not null");
            return (Criteria) this;
        }

        public Criteria andApplyTypeEqualTo(String value) {
            addCriterion("apply_type =", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotEqualTo(String value) {
            addCriterion("apply_type <>", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeGreaterThan(String value) {
            addCriterion("apply_type >", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("apply_type >=", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeLessThan(String value) {
            addCriterion("apply_type <", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeLessThanOrEqualTo(String value) {
            addCriterion("apply_type <=", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeLike(String value) {
            addCriterion("apply_type like", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotLike(String value) {
            addCriterion("apply_type not like", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIn(List<String> values) {
            addCriterion("apply_type in", values, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotIn(List<String> values) {
            addCriterion("apply_type not in", values, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeBetween(String value1, String value2) {
            addCriterion("apply_type between", value1, value2, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotBetween(String value1, String value2) {
            addCriterion("apply_type not between", value1, value2, "applyType");
            return (Criteria) this;
        }

        public Criteria andNotifyFlagIsNull() {
            addCriterion("notify_flag is null");
            return (Criteria) this;
        }

        public Criteria andNotifyFlagIsNotNull() {
            addCriterion("notify_flag is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyFlagEqualTo(Boolean value) {
            addCriterion("notify_flag =", value, "notifyFlag");
            return (Criteria) this;
        }

        public Criteria andNotifyFlagNotEqualTo(Boolean value) {
            addCriterion("notify_flag <>", value, "notifyFlag");
            return (Criteria) this;
        }

        public Criteria andNotifyFlagGreaterThan(Boolean value) {
            addCriterion("notify_flag >", value, "notifyFlag");
            return (Criteria) this;
        }

        public Criteria andNotifyFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("notify_flag >=", value, "notifyFlag");
            return (Criteria) this;
        }

        public Criteria andNotifyFlagLessThan(Boolean value) {
            addCriterion("notify_flag <", value, "notifyFlag");
            return (Criteria) this;
        }

        public Criteria andNotifyFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("notify_flag <=", value, "notifyFlag");
            return (Criteria) this;
        }

        public Criteria andNotifyFlagIn(List<Boolean> values) {
            addCriterion("notify_flag in", values, "notifyFlag");
            return (Criteria) this;
        }

        public Criteria andNotifyFlagNotIn(List<Boolean> values) {
            addCriterion("notify_flag not in", values, "notifyFlag");
            return (Criteria) this;
        }

        public Criteria andNotifyFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("notify_flag between", value1, value2, "notifyFlag");
            return (Criteria) this;
        }

        public Criteria andNotifyFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("notify_flag not between", value1, value2, "notifyFlag");
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

        public Criteria andAuditTimeEqualTo(String value) {
            addCriterion("audit_time =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(String value) {
            addCriterion("audit_time <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(String value) {
            addCriterion("audit_time >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("audit_time >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(String value) {
            addCriterion("audit_time <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("audit_time <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLike(String value) {
            addCriterion("audit_time like", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotLike(String value) {
            addCriterion("audit_time not like", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<String> values) {
            addCriterion("audit_time in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<String> values) {
            addCriterion("audit_time not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(String value1, String value2) {
            addCriterion("audit_time between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(String value1, String value2) {
            addCriterion("audit_time not between", value1, value2, "auditTime");
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