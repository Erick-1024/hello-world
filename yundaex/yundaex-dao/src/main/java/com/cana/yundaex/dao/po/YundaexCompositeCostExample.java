package com.cana.yundaex.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class YundaexCompositeCostExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public YundaexCompositeCostExample() {
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

        public Criteria andCityLevelIsNull() {
            addCriterion("city_level is null");
            return (Criteria) this;
        }

        public Criteria andCityLevelIsNotNull() {
            addCriterion("city_level is not null");
            return (Criteria) this;
        }

        public Criteria andCityLevelEqualTo(String value) {
            addCriterion("city_level =", value, "cityLevel");
            return (Criteria) this;
        }

        public Criteria andCityLevelNotEqualTo(String value) {
            addCriterion("city_level <>", value, "cityLevel");
            return (Criteria) this;
        }

        public Criteria andCityLevelGreaterThan(String value) {
            addCriterion("city_level >", value, "cityLevel");
            return (Criteria) this;
        }

        public Criteria andCityLevelGreaterThanOrEqualTo(String value) {
            addCriterion("city_level >=", value, "cityLevel");
            return (Criteria) this;
        }

        public Criteria andCityLevelLessThan(String value) {
            addCriterion("city_level <", value, "cityLevel");
            return (Criteria) this;
        }

        public Criteria andCityLevelLessThanOrEqualTo(String value) {
            addCriterion("city_level <=", value, "cityLevel");
            return (Criteria) this;
        }

        public Criteria andCityLevelLike(String value) {
            addCriterion("city_level like", value, "cityLevel");
            return (Criteria) this;
        }

        public Criteria andCityLevelNotLike(String value) {
            addCriterion("city_level not like", value, "cityLevel");
            return (Criteria) this;
        }

        public Criteria andCityLevelIn(List<String> values) {
            addCriterion("city_level in", values, "cityLevel");
            return (Criteria) this;
        }

        public Criteria andCityLevelNotIn(List<String> values) {
            addCriterion("city_level not in", values, "cityLevel");
            return (Criteria) this;
        }

        public Criteria andCityLevelBetween(String value1, String value2) {
            addCriterion("city_level between", value1, value2, "cityLevel");
            return (Criteria) this;
        }

        public Criteria andCityLevelNotBetween(String value1, String value2) {
            addCriterion("city_level not between", value1, value2, "cityLevel");
            return (Criteria) this;
        }

        public Criteria andStationCityIsNull() {
            addCriterion("station_city is null");
            return (Criteria) this;
        }

        public Criteria andStationCityIsNotNull() {
            addCriterion("station_city is not null");
            return (Criteria) this;
        }

        public Criteria andStationCityEqualTo(String value) {
            addCriterion("station_city =", value, "stationCity");
            return (Criteria) this;
        }

        public Criteria andStationCityNotEqualTo(String value) {
            addCriterion("station_city <>", value, "stationCity");
            return (Criteria) this;
        }

        public Criteria andStationCityGreaterThan(String value) {
            addCriterion("station_city >", value, "stationCity");
            return (Criteria) this;
        }

        public Criteria andStationCityGreaterThanOrEqualTo(String value) {
            addCriterion("station_city >=", value, "stationCity");
            return (Criteria) this;
        }

        public Criteria andStationCityLessThan(String value) {
            addCriterion("station_city <", value, "stationCity");
            return (Criteria) this;
        }

        public Criteria andStationCityLessThanOrEqualTo(String value) {
            addCriterion("station_city <=", value, "stationCity");
            return (Criteria) this;
        }

        public Criteria andStationCityLike(String value) {
            addCriterion("station_city like", value, "stationCity");
            return (Criteria) this;
        }

        public Criteria andStationCityNotLike(String value) {
            addCriterion("station_city not like", value, "stationCity");
            return (Criteria) this;
        }

        public Criteria andStationCityIn(List<String> values) {
            addCriterion("station_city in", values, "stationCity");
            return (Criteria) this;
        }

        public Criteria andStationCityNotIn(List<String> values) {
            addCriterion("station_city not in", values, "stationCity");
            return (Criteria) this;
        }

        public Criteria andStationCityBetween(String value1, String value2) {
            addCriterion("station_city between", value1, value2, "stationCity");
            return (Criteria) this;
        }

        public Criteria andStationCityNotBetween(String value1, String value2) {
            addCriterion("station_city not between", value1, value2, "stationCity");
            return (Criteria) this;
        }

        public Criteria andRentalCostIsNull() {
            addCriterion("rental_cost is null");
            return (Criteria) this;
        }

        public Criteria andRentalCostIsNotNull() {
            addCriterion("rental_cost is not null");
            return (Criteria) this;
        }

        public Criteria andRentalCostEqualTo(BigDecimal value) {
            addCriterion("rental_cost =", value, "rentalCost");
            return (Criteria) this;
        }

        public Criteria andRentalCostNotEqualTo(BigDecimal value) {
            addCriterion("rental_cost <>", value, "rentalCost");
            return (Criteria) this;
        }

        public Criteria andRentalCostGreaterThan(BigDecimal value) {
            addCriterion("rental_cost >", value, "rentalCost");
            return (Criteria) this;
        }

        public Criteria andRentalCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rental_cost >=", value, "rentalCost");
            return (Criteria) this;
        }

        public Criteria andRentalCostLessThan(BigDecimal value) {
            addCriterion("rental_cost <", value, "rentalCost");
            return (Criteria) this;
        }

        public Criteria andRentalCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rental_cost <=", value, "rentalCost");
            return (Criteria) this;
        }

        public Criteria andRentalCostIn(List<BigDecimal> values) {
            addCriterion("rental_cost in", values, "rentalCost");
            return (Criteria) this;
        }

        public Criteria andRentalCostNotIn(List<BigDecimal> values) {
            addCriterion("rental_cost not in", values, "rentalCost");
            return (Criteria) this;
        }

        public Criteria andRentalCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rental_cost between", value1, value2, "rentalCost");
            return (Criteria) this;
        }

        public Criteria andRentalCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rental_cost not between", value1, value2, "rentalCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostIsNull() {
            addCriterion("transport_cost is null");
            return (Criteria) this;
        }

        public Criteria andTransportCostIsNotNull() {
            addCriterion("transport_cost is not null");
            return (Criteria) this;
        }

        public Criteria andTransportCostEqualTo(BigDecimal value) {
            addCriterion("transport_cost =", value, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostNotEqualTo(BigDecimal value) {
            addCriterion("transport_cost <>", value, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostGreaterThan(BigDecimal value) {
            addCriterion("transport_cost >", value, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("transport_cost >=", value, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostLessThan(BigDecimal value) {
            addCriterion("transport_cost <", value, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("transport_cost <=", value, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostIn(List<BigDecimal> values) {
            addCriterion("transport_cost in", values, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostNotIn(List<BigDecimal> values) {
            addCriterion("transport_cost not in", values, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transport_cost between", value1, value2, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transport_cost not between", value1, value2, "transportCost");
            return (Criteria) this;
        }

        public Criteria andDefectCostIsNull() {
            addCriterion("defect_cost is null");
            return (Criteria) this;
        }

        public Criteria andDefectCostIsNotNull() {
            addCriterion("defect_cost is not null");
            return (Criteria) this;
        }

        public Criteria andDefectCostEqualTo(BigDecimal value) {
            addCriterion("defect_cost =", value, "defectCost");
            return (Criteria) this;
        }

        public Criteria andDefectCostNotEqualTo(BigDecimal value) {
            addCriterion("defect_cost <>", value, "defectCost");
            return (Criteria) this;
        }

        public Criteria andDefectCostGreaterThan(BigDecimal value) {
            addCriterion("defect_cost >", value, "defectCost");
            return (Criteria) this;
        }

        public Criteria andDefectCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("defect_cost >=", value, "defectCost");
            return (Criteria) this;
        }

        public Criteria andDefectCostLessThan(BigDecimal value) {
            addCriterion("defect_cost <", value, "defectCost");
            return (Criteria) this;
        }

        public Criteria andDefectCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("defect_cost <=", value, "defectCost");
            return (Criteria) this;
        }

        public Criteria andDefectCostIn(List<BigDecimal> values) {
            addCriterion("defect_cost in", values, "defectCost");
            return (Criteria) this;
        }

        public Criteria andDefectCostNotIn(List<BigDecimal> values) {
            addCriterion("defect_cost not in", values, "defectCost");
            return (Criteria) this;
        }

        public Criteria andDefectCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("defect_cost between", value1, value2, "defectCost");
            return (Criteria) this;
        }

        public Criteria andDefectCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("defect_cost not between", value1, value2, "defectCost");
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