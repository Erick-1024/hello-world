package com.cana.yundaex.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YundaexTstationInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public YundaexTstationInfoExample() {
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

        public Criteria andStatmonthIsNull() {
            addCriterion("statmonth is null");
            return (Criteria) this;
        }

        public Criteria andStatmonthIsNotNull() {
            addCriterion("statmonth is not null");
            return (Criteria) this;
        }

        public Criteria andStatmonthEqualTo(String value) {
            addCriterion("statmonth =", value, "statmonth");
            return (Criteria) this;
        }

        public Criteria andStatmonthNotEqualTo(String value) {
            addCriterion("statmonth <>", value, "statmonth");
            return (Criteria) this;
        }

        public Criteria andStatmonthGreaterThan(String value) {
            addCriterion("statmonth >", value, "statmonth");
            return (Criteria) this;
        }

        public Criteria andStatmonthGreaterThanOrEqualTo(String value) {
            addCriterion("statmonth >=", value, "statmonth");
            return (Criteria) this;
        }

        public Criteria andStatmonthLessThan(String value) {
            addCriterion("statmonth <", value, "statmonth");
            return (Criteria) this;
        }

        public Criteria andStatmonthLessThanOrEqualTo(String value) {
            addCriterion("statmonth <=", value, "statmonth");
            return (Criteria) this;
        }

        public Criteria andStatmonthLike(String value) {
            addCriterion("statmonth like", value, "statmonth");
            return (Criteria) this;
        }

        public Criteria andStatmonthNotLike(String value) {
            addCriterion("statmonth not like", value, "statmonth");
            return (Criteria) this;
        }

        public Criteria andStatmonthIn(List<String> values) {
            addCriterion("statmonth in", values, "statmonth");
            return (Criteria) this;
        }

        public Criteria andStatmonthNotIn(List<String> values) {
            addCriterion("statmonth not in", values, "statmonth");
            return (Criteria) this;
        }

        public Criteria andStatmonthBetween(String value1, String value2) {
            addCriterion("statmonth between", value1, value2, "statmonth");
            return (Criteria) this;
        }

        public Criteria andStatmonthNotBetween(String value1, String value2) {
            addCriterion("statmonth not between", value1, value2, "statmonth");
            return (Criteria) this;
        }

        public Criteria andReceiveTotalIsNull() {
            addCriterion("receive_total is null");
            return (Criteria) this;
        }

        public Criteria andReceiveTotalIsNotNull() {
            addCriterion("receive_total is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveTotalEqualTo(Integer value) {
            addCriterion("receive_total =", value, "receiveTotal");
            return (Criteria) this;
        }

        public Criteria andReceiveTotalNotEqualTo(Integer value) {
            addCriterion("receive_total <>", value, "receiveTotal");
            return (Criteria) this;
        }

        public Criteria andReceiveTotalGreaterThan(Integer value) {
            addCriterion("receive_total >", value, "receiveTotal");
            return (Criteria) this;
        }

        public Criteria andReceiveTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("receive_total >=", value, "receiveTotal");
            return (Criteria) this;
        }

        public Criteria andReceiveTotalLessThan(Integer value) {
            addCriterion("receive_total <", value, "receiveTotal");
            return (Criteria) this;
        }

        public Criteria andReceiveTotalLessThanOrEqualTo(Integer value) {
            addCriterion("receive_total <=", value, "receiveTotal");
            return (Criteria) this;
        }

        public Criteria andReceiveTotalIn(List<Integer> values) {
            addCriterion("receive_total in", values, "receiveTotal");
            return (Criteria) this;
        }

        public Criteria andReceiveTotalNotIn(List<Integer> values) {
            addCriterion("receive_total not in", values, "receiveTotal");
            return (Criteria) this;
        }

        public Criteria andReceiveTotalBetween(Integer value1, Integer value2) {
            addCriterion("receive_total between", value1, value2, "receiveTotal");
            return (Criteria) this;
        }

        public Criteria andReceiveTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("receive_total not between", value1, value2, "receiveTotal");
            return (Criteria) this;
        }

        public Criteria andAvgReceiveNumIsNull() {
            addCriterion("avg_receive_num is null");
            return (Criteria) this;
        }

        public Criteria andAvgReceiveNumIsNotNull() {
            addCriterion("avg_receive_num is not null");
            return (Criteria) this;
        }

        public Criteria andAvgReceiveNumEqualTo(BigDecimal value) {
            addCriterion("avg_receive_num =", value, "avgReceiveNum");
            return (Criteria) this;
        }

        public Criteria andAvgReceiveNumNotEqualTo(BigDecimal value) {
            addCriterion("avg_receive_num <>", value, "avgReceiveNum");
            return (Criteria) this;
        }

        public Criteria andAvgReceiveNumGreaterThan(BigDecimal value) {
            addCriterion("avg_receive_num >", value, "avgReceiveNum");
            return (Criteria) this;
        }

        public Criteria andAvgReceiveNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("avg_receive_num >=", value, "avgReceiveNum");
            return (Criteria) this;
        }

        public Criteria andAvgReceiveNumLessThan(BigDecimal value) {
            addCriterion("avg_receive_num <", value, "avgReceiveNum");
            return (Criteria) this;
        }

        public Criteria andAvgReceiveNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("avg_receive_num <=", value, "avgReceiveNum");
            return (Criteria) this;
        }

        public Criteria andAvgReceiveNumIn(List<BigDecimal> values) {
            addCriterion("avg_receive_num in", values, "avgReceiveNum");
            return (Criteria) this;
        }

        public Criteria andAvgReceiveNumNotIn(List<BigDecimal> values) {
            addCriterion("avg_receive_num not in", values, "avgReceiveNum");
            return (Criteria) this;
        }

        public Criteria andAvgReceiveNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("avg_receive_num between", value1, value2, "avgReceiveNum");
            return (Criteria) this;
        }

        public Criteria andAvgReceiveNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("avg_receive_num not between", value1, value2, "avgReceiveNum");
            return (Criteria) this;
        }

        public Criteria andReceiveSumSignedIsNull() {
            addCriterion("receive_sum_signed is null");
            return (Criteria) this;
        }

        public Criteria andReceiveSumSignedIsNotNull() {
            addCriterion("receive_sum_signed is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveSumSignedEqualTo(Integer value) {
            addCriterion("receive_sum_signed =", value, "receiveSumSigned");
            return (Criteria) this;
        }

        public Criteria andReceiveSumSignedNotEqualTo(Integer value) {
            addCriterion("receive_sum_signed <>", value, "receiveSumSigned");
            return (Criteria) this;
        }

        public Criteria andReceiveSumSignedGreaterThan(Integer value) {
            addCriterion("receive_sum_signed >", value, "receiveSumSigned");
            return (Criteria) this;
        }

        public Criteria andReceiveSumSignedGreaterThanOrEqualTo(Integer value) {
            addCriterion("receive_sum_signed >=", value, "receiveSumSigned");
            return (Criteria) this;
        }

        public Criteria andReceiveSumSignedLessThan(Integer value) {
            addCriterion("receive_sum_signed <", value, "receiveSumSigned");
            return (Criteria) this;
        }

        public Criteria andReceiveSumSignedLessThanOrEqualTo(Integer value) {
            addCriterion("receive_sum_signed <=", value, "receiveSumSigned");
            return (Criteria) this;
        }

        public Criteria andReceiveSumSignedIn(List<Integer> values) {
            addCriterion("receive_sum_signed in", values, "receiveSumSigned");
            return (Criteria) this;
        }

        public Criteria andReceiveSumSignedNotIn(List<Integer> values) {
            addCriterion("receive_sum_signed not in", values, "receiveSumSigned");
            return (Criteria) this;
        }

        public Criteria andReceiveSumSignedBetween(Integer value1, Integer value2) {
            addCriterion("receive_sum_signed between", value1, value2, "receiveSumSigned");
            return (Criteria) this;
        }

        public Criteria andReceiveSumSignedNotBetween(Integer value1, Integer value2) {
            addCriterion("receive_sum_signed not between", value1, value2, "receiveSumSigned");
            return (Criteria) this;
        }

        public Criteria andReceiveSumUnsignIsNull() {
            addCriterion("receive_sum_unsign is null");
            return (Criteria) this;
        }

        public Criteria andReceiveSumUnsignIsNotNull() {
            addCriterion("receive_sum_unsign is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveSumUnsignEqualTo(Integer value) {
            addCriterion("receive_sum_unsign =", value, "receiveSumUnsign");
            return (Criteria) this;
        }

        public Criteria andReceiveSumUnsignNotEqualTo(Integer value) {
            addCriterion("receive_sum_unsign <>", value, "receiveSumUnsign");
            return (Criteria) this;
        }

        public Criteria andReceiveSumUnsignGreaterThan(Integer value) {
            addCriterion("receive_sum_unsign >", value, "receiveSumUnsign");
            return (Criteria) this;
        }

        public Criteria andReceiveSumUnsignGreaterThanOrEqualTo(Integer value) {
            addCriterion("receive_sum_unsign >=", value, "receiveSumUnsign");
            return (Criteria) this;
        }

        public Criteria andReceiveSumUnsignLessThan(Integer value) {
            addCriterion("receive_sum_unsign <", value, "receiveSumUnsign");
            return (Criteria) this;
        }

        public Criteria andReceiveSumUnsignLessThanOrEqualTo(Integer value) {
            addCriterion("receive_sum_unsign <=", value, "receiveSumUnsign");
            return (Criteria) this;
        }

        public Criteria andReceiveSumUnsignIn(List<Integer> values) {
            addCriterion("receive_sum_unsign in", values, "receiveSumUnsign");
            return (Criteria) this;
        }

        public Criteria andReceiveSumUnsignNotIn(List<Integer> values) {
            addCriterion("receive_sum_unsign not in", values, "receiveSumUnsign");
            return (Criteria) this;
        }

        public Criteria andReceiveSumUnsignBetween(Integer value1, Integer value2) {
            addCriterion("receive_sum_unsign between", value1, value2, "receiveSumUnsign");
            return (Criteria) this;
        }

        public Criteria andReceiveSumUnsignNotBetween(Integer value1, Integer value2) {
            addCriterion("receive_sum_unsign not between", value1, value2, "receiveSumUnsign");
            return (Criteria) this;
        }

        public Criteria andSendTotalIsNull() {
            addCriterion("send_total is null");
            return (Criteria) this;
        }

        public Criteria andSendTotalIsNotNull() {
            addCriterion("send_total is not null");
            return (Criteria) this;
        }

        public Criteria andSendTotalEqualTo(Integer value) {
            addCriterion("send_total =", value, "sendTotal");
            return (Criteria) this;
        }

        public Criteria andSendTotalNotEqualTo(Integer value) {
            addCriterion("send_total <>", value, "sendTotal");
            return (Criteria) this;
        }

        public Criteria andSendTotalGreaterThan(Integer value) {
            addCriterion("send_total >", value, "sendTotal");
            return (Criteria) this;
        }

        public Criteria andSendTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_total >=", value, "sendTotal");
            return (Criteria) this;
        }

        public Criteria andSendTotalLessThan(Integer value) {
            addCriterion("send_total <", value, "sendTotal");
            return (Criteria) this;
        }

        public Criteria andSendTotalLessThanOrEqualTo(Integer value) {
            addCriterion("send_total <=", value, "sendTotal");
            return (Criteria) this;
        }

        public Criteria andSendTotalIn(List<Integer> values) {
            addCriterion("send_total in", values, "sendTotal");
            return (Criteria) this;
        }

        public Criteria andSendTotalNotIn(List<Integer> values) {
            addCriterion("send_total not in", values, "sendTotal");
            return (Criteria) this;
        }

        public Criteria andSendTotalBetween(Integer value1, Integer value2) {
            addCriterion("send_total between", value1, value2, "sendTotal");
            return (Criteria) this;
        }

        public Criteria andSendTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("send_total not between", value1, value2, "sendTotal");
            return (Criteria) this;
        }

        public Criteria andAvgSendNumIsNull() {
            addCriterion("avg_send_num is null");
            return (Criteria) this;
        }

        public Criteria andAvgSendNumIsNotNull() {
            addCriterion("avg_send_num is not null");
            return (Criteria) this;
        }

        public Criteria andAvgSendNumEqualTo(BigDecimal value) {
            addCriterion("avg_send_num =", value, "avgSendNum");
            return (Criteria) this;
        }

        public Criteria andAvgSendNumNotEqualTo(BigDecimal value) {
            addCriterion("avg_send_num <>", value, "avgSendNum");
            return (Criteria) this;
        }

        public Criteria andAvgSendNumGreaterThan(BigDecimal value) {
            addCriterion("avg_send_num >", value, "avgSendNum");
            return (Criteria) this;
        }

        public Criteria andAvgSendNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("avg_send_num >=", value, "avgSendNum");
            return (Criteria) this;
        }

        public Criteria andAvgSendNumLessThan(BigDecimal value) {
            addCriterion("avg_send_num <", value, "avgSendNum");
            return (Criteria) this;
        }

        public Criteria andAvgSendNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("avg_send_num <=", value, "avgSendNum");
            return (Criteria) this;
        }

        public Criteria andAvgSendNumIn(List<BigDecimal> values) {
            addCriterion("avg_send_num in", values, "avgSendNum");
            return (Criteria) this;
        }

        public Criteria andAvgSendNumNotIn(List<BigDecimal> values) {
            addCriterion("avg_send_num not in", values, "avgSendNum");
            return (Criteria) this;
        }

        public Criteria andAvgSendNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("avg_send_num between", value1, value2, "avgSendNum");
            return (Criteria) this;
        }

        public Criteria andAvgSendNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("avg_send_num not between", value1, value2, "avgSendNum");
            return (Criteria) this;
        }

        public Criteria andSendSumSignedIsNull() {
            addCriterion("send_sum_signed is null");
            return (Criteria) this;
        }

        public Criteria andSendSumSignedIsNotNull() {
            addCriterion("send_sum_signed is not null");
            return (Criteria) this;
        }

        public Criteria andSendSumSignedEqualTo(Integer value) {
            addCriterion("send_sum_signed =", value, "sendSumSigned");
            return (Criteria) this;
        }

        public Criteria andSendSumSignedNotEqualTo(Integer value) {
            addCriterion("send_sum_signed <>", value, "sendSumSigned");
            return (Criteria) this;
        }

        public Criteria andSendSumSignedGreaterThan(Integer value) {
            addCriterion("send_sum_signed >", value, "sendSumSigned");
            return (Criteria) this;
        }

        public Criteria andSendSumSignedGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_sum_signed >=", value, "sendSumSigned");
            return (Criteria) this;
        }

        public Criteria andSendSumSignedLessThan(Integer value) {
            addCriterion("send_sum_signed <", value, "sendSumSigned");
            return (Criteria) this;
        }

        public Criteria andSendSumSignedLessThanOrEqualTo(Integer value) {
            addCriterion("send_sum_signed <=", value, "sendSumSigned");
            return (Criteria) this;
        }

        public Criteria andSendSumSignedIn(List<Integer> values) {
            addCriterion("send_sum_signed in", values, "sendSumSigned");
            return (Criteria) this;
        }

        public Criteria andSendSumSignedNotIn(List<Integer> values) {
            addCriterion("send_sum_signed not in", values, "sendSumSigned");
            return (Criteria) this;
        }

        public Criteria andSendSumSignedBetween(Integer value1, Integer value2) {
            addCriterion("send_sum_signed between", value1, value2, "sendSumSigned");
            return (Criteria) this;
        }

        public Criteria andSendSumSignedNotBetween(Integer value1, Integer value2) {
            addCriterion("send_sum_signed not between", value1, value2, "sendSumSigned");
            return (Criteria) this;
        }

        public Criteria andSendSumUnsignIsNull() {
            addCriterion("send_sum_unsign is null");
            return (Criteria) this;
        }

        public Criteria andSendSumUnsignIsNotNull() {
            addCriterion("send_sum_unsign is not null");
            return (Criteria) this;
        }

        public Criteria andSendSumUnsignEqualTo(Integer value) {
            addCriterion("send_sum_unsign =", value, "sendSumUnsign");
            return (Criteria) this;
        }

        public Criteria andSendSumUnsignNotEqualTo(Integer value) {
            addCriterion("send_sum_unsign <>", value, "sendSumUnsign");
            return (Criteria) this;
        }

        public Criteria andSendSumUnsignGreaterThan(Integer value) {
            addCriterion("send_sum_unsign >", value, "sendSumUnsign");
            return (Criteria) this;
        }

        public Criteria andSendSumUnsignGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_sum_unsign >=", value, "sendSumUnsign");
            return (Criteria) this;
        }

        public Criteria andSendSumUnsignLessThan(Integer value) {
            addCriterion("send_sum_unsign <", value, "sendSumUnsign");
            return (Criteria) this;
        }

        public Criteria andSendSumUnsignLessThanOrEqualTo(Integer value) {
            addCriterion("send_sum_unsign <=", value, "sendSumUnsign");
            return (Criteria) this;
        }

        public Criteria andSendSumUnsignIn(List<Integer> values) {
            addCriterion("send_sum_unsign in", values, "sendSumUnsign");
            return (Criteria) this;
        }

        public Criteria andSendSumUnsignNotIn(List<Integer> values) {
            addCriterion("send_sum_unsign not in", values, "sendSumUnsign");
            return (Criteria) this;
        }

        public Criteria andSendSumUnsignBetween(Integer value1, Integer value2) {
            addCriterion("send_sum_unsign between", value1, value2, "sendSumUnsign");
            return (Criteria) this;
        }

        public Criteria andSendSumUnsignNotBetween(Integer value1, Integer value2) {
            addCriterion("send_sum_unsign not between", value1, value2, "sendSumUnsign");
            return (Criteria) this;
        }

        public Criteria andRecSendDifIsNull() {
            addCriterion("rec_send_dif is null");
            return (Criteria) this;
        }

        public Criteria andRecSendDifIsNotNull() {
            addCriterion("rec_send_dif is not null");
            return (Criteria) this;
        }

        public Criteria andRecSendDifEqualTo(Integer value) {
            addCriterion("rec_send_dif =", value, "recSendDif");
            return (Criteria) this;
        }

        public Criteria andRecSendDifNotEqualTo(Integer value) {
            addCriterion("rec_send_dif <>", value, "recSendDif");
            return (Criteria) this;
        }

        public Criteria andRecSendDifGreaterThan(Integer value) {
            addCriterion("rec_send_dif >", value, "recSendDif");
            return (Criteria) this;
        }

        public Criteria andRecSendDifGreaterThanOrEqualTo(Integer value) {
            addCriterion("rec_send_dif >=", value, "recSendDif");
            return (Criteria) this;
        }

        public Criteria andRecSendDifLessThan(Integer value) {
            addCriterion("rec_send_dif <", value, "recSendDif");
            return (Criteria) this;
        }

        public Criteria andRecSendDifLessThanOrEqualTo(Integer value) {
            addCriterion("rec_send_dif <=", value, "recSendDif");
            return (Criteria) this;
        }

        public Criteria andRecSendDifIn(List<Integer> values) {
            addCriterion("rec_send_dif in", values, "recSendDif");
            return (Criteria) this;
        }

        public Criteria andRecSendDifNotIn(List<Integer> values) {
            addCriterion("rec_send_dif not in", values, "recSendDif");
            return (Criteria) this;
        }

        public Criteria andRecSendDifBetween(Integer value1, Integer value2) {
            addCriterion("rec_send_dif between", value1, value2, "recSendDif");
            return (Criteria) this;
        }

        public Criteria andRecSendDifNotBetween(Integer value1, Integer value2) {
            addCriterion("rec_send_dif not between", value1, value2, "recSendDif");
            return (Criteria) this;
        }

        public Criteria andRecSendRatioIsNull() {
            addCriterion("rec_send_ratio is null");
            return (Criteria) this;
        }

        public Criteria andRecSendRatioIsNotNull() {
            addCriterion("rec_send_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andRecSendRatioEqualTo(BigDecimal value) {
            addCriterion("rec_send_ratio =", value, "recSendRatio");
            return (Criteria) this;
        }

        public Criteria andRecSendRatioNotEqualTo(BigDecimal value) {
            addCriterion("rec_send_ratio <>", value, "recSendRatio");
            return (Criteria) this;
        }

        public Criteria andRecSendRatioGreaterThan(BigDecimal value) {
            addCriterion("rec_send_ratio >", value, "recSendRatio");
            return (Criteria) this;
        }

        public Criteria andRecSendRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rec_send_ratio >=", value, "recSendRatio");
            return (Criteria) this;
        }

        public Criteria andRecSendRatioLessThan(BigDecimal value) {
            addCriterion("rec_send_ratio <", value, "recSendRatio");
            return (Criteria) this;
        }

        public Criteria andRecSendRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rec_send_ratio <=", value, "recSendRatio");
            return (Criteria) this;
        }

        public Criteria andRecSendRatioIn(List<BigDecimal> values) {
            addCriterion("rec_send_ratio in", values, "recSendRatio");
            return (Criteria) this;
        }

        public Criteria andRecSendRatioNotIn(List<BigDecimal> values) {
            addCriterion("rec_send_ratio not in", values, "recSendRatio");
            return (Criteria) this;
        }

        public Criteria andRecSendRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rec_send_ratio between", value1, value2, "recSendRatio");
            return (Criteria) this;
        }

        public Criteria andRecSendRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rec_send_ratio not between", value1, value2, "recSendRatio");
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