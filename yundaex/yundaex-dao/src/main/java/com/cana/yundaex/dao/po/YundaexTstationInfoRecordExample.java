package com.cana.yundaex.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YundaexTstationInfoRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public YundaexTstationInfoRecordExample() {
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

        public Criteria andBatchDateIsNull() {
            addCriterion("batch_date is null");
            return (Criteria) this;
        }

        public Criteria andBatchDateIsNotNull() {
            addCriterion("batch_date is not null");
            return (Criteria) this;
        }

        public Criteria andBatchDateEqualTo(String value) {
            addCriterion("batch_date =", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateNotEqualTo(String value) {
            addCriterion("batch_date <>", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateGreaterThan(String value) {
            addCriterion("batch_date >", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateGreaterThanOrEqualTo(String value) {
            addCriterion("batch_date >=", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateLessThan(String value) {
            addCriterion("batch_date <", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateLessThanOrEqualTo(String value) {
            addCriterion("batch_date <=", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateLike(String value) {
            addCriterion("batch_date like", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateNotLike(String value) {
            addCriterion("batch_date not like", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateIn(List<String> values) {
            addCriterion("batch_date in", values, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateNotIn(List<String> values) {
            addCriterion("batch_date not in", values, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateBetween(String value1, String value2) {
            addCriterion("batch_date between", value1, value2, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateNotBetween(String value1, String value2) {
            addCriterion("batch_date not between", value1, value2, "batchDate");
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

        public Criteria andStationDataStatusIsNull() {
            addCriterion("station_data_status is null");
            return (Criteria) this;
        }

        public Criteria andStationDataStatusIsNotNull() {
            addCriterion("station_data_status is not null");
            return (Criteria) this;
        }

        public Criteria andStationDataStatusEqualTo(String value) {
            addCriterion("station_data_status =", value, "stationDataStatus");
            return (Criteria) this;
        }

        public Criteria andStationDataStatusNotEqualTo(String value) {
            addCriterion("station_data_status <>", value, "stationDataStatus");
            return (Criteria) this;
        }

        public Criteria andStationDataStatusGreaterThan(String value) {
            addCriterion("station_data_status >", value, "stationDataStatus");
            return (Criteria) this;
        }

        public Criteria andStationDataStatusGreaterThanOrEqualTo(String value) {
            addCriterion("station_data_status >=", value, "stationDataStatus");
            return (Criteria) this;
        }

        public Criteria andStationDataStatusLessThan(String value) {
            addCriterion("station_data_status <", value, "stationDataStatus");
            return (Criteria) this;
        }

        public Criteria andStationDataStatusLessThanOrEqualTo(String value) {
            addCriterion("station_data_status <=", value, "stationDataStatus");
            return (Criteria) this;
        }

        public Criteria andStationDataStatusLike(String value) {
            addCriterion("station_data_status like", value, "stationDataStatus");
            return (Criteria) this;
        }

        public Criteria andStationDataStatusNotLike(String value) {
            addCriterion("station_data_status not like", value, "stationDataStatus");
            return (Criteria) this;
        }

        public Criteria andStationDataStatusIn(List<String> values) {
            addCriterion("station_data_status in", values, "stationDataStatus");
            return (Criteria) this;
        }

        public Criteria andStationDataStatusNotIn(List<String> values) {
            addCriterion("station_data_status not in", values, "stationDataStatus");
            return (Criteria) this;
        }

        public Criteria andStationDataStatusBetween(String value1, String value2) {
            addCriterion("station_data_status between", value1, value2, "stationDataStatus");
            return (Criteria) this;
        }

        public Criteria andStationDataStatusNotBetween(String value1, String value2) {
            addCriterion("station_data_status not between", value1, value2, "stationDataStatus");
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