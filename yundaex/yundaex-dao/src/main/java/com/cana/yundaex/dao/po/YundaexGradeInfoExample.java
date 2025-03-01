package com.cana.yundaex.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class YundaexGradeInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public YundaexGradeInfoExample() {
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

        public Criteria andMinPointsIsNull() {
            addCriterion("min_points is null");
            return (Criteria) this;
        }

        public Criteria andMinPointsIsNotNull() {
            addCriterion("min_points is not null");
            return (Criteria) this;
        }

        public Criteria andMinPointsEqualTo(BigDecimal value) {
            addCriterion("min_points =", value, "minPoints");
            return (Criteria) this;
        }

        public Criteria andMinPointsNotEqualTo(BigDecimal value) {
            addCriterion("min_points <>", value, "minPoints");
            return (Criteria) this;
        }

        public Criteria andMinPointsGreaterThan(BigDecimal value) {
            addCriterion("min_points >", value, "minPoints");
            return (Criteria) this;
        }

        public Criteria andMinPointsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min_points >=", value, "minPoints");
            return (Criteria) this;
        }

        public Criteria andMinPointsLessThan(BigDecimal value) {
            addCriterion("min_points <", value, "minPoints");
            return (Criteria) this;
        }

        public Criteria andMinPointsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min_points <=", value, "minPoints");
            return (Criteria) this;
        }

        public Criteria andMinPointsIn(List<BigDecimal> values) {
            addCriterion("min_points in", values, "minPoints");
            return (Criteria) this;
        }

        public Criteria andMinPointsNotIn(List<BigDecimal> values) {
            addCriterion("min_points not in", values, "minPoints");
            return (Criteria) this;
        }

        public Criteria andMinPointsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_points between", value1, value2, "minPoints");
            return (Criteria) this;
        }

        public Criteria andMinPointsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_points not between", value1, value2, "minPoints");
            return (Criteria) this;
        }

        public Criteria andMaxPointsIsNull() {
            addCriterion("max_points is null");
            return (Criteria) this;
        }

        public Criteria andMaxPointsIsNotNull() {
            addCriterion("max_points is not null");
            return (Criteria) this;
        }

        public Criteria andMaxPointsEqualTo(BigDecimal value) {
            addCriterion("max_points =", value, "maxPoints");
            return (Criteria) this;
        }

        public Criteria andMaxPointsNotEqualTo(BigDecimal value) {
            addCriterion("max_points <>", value, "maxPoints");
            return (Criteria) this;
        }

        public Criteria andMaxPointsGreaterThan(BigDecimal value) {
            addCriterion("max_points >", value, "maxPoints");
            return (Criteria) this;
        }

        public Criteria andMaxPointsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("max_points >=", value, "maxPoints");
            return (Criteria) this;
        }

        public Criteria andMaxPointsLessThan(BigDecimal value) {
            addCriterion("max_points <", value, "maxPoints");
            return (Criteria) this;
        }

        public Criteria andMaxPointsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("max_points <=", value, "maxPoints");
            return (Criteria) this;
        }

        public Criteria andMaxPointsIn(List<BigDecimal> values) {
            addCriterion("max_points in", values, "maxPoints");
            return (Criteria) this;
        }

        public Criteria andMaxPointsNotIn(List<BigDecimal> values) {
            addCriterion("max_points not in", values, "maxPoints");
            return (Criteria) this;
        }

        public Criteria andMaxPointsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max_points between", value1, value2, "maxPoints");
            return (Criteria) this;
        }

        public Criteria andMaxPointsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max_points not between", value1, value2, "maxPoints");
            return (Criteria) this;
        }

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(String value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(String value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(String value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(String value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(String value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(String value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLike(String value) {
            addCriterion("grade like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotLike(String value) {
            addCriterion("grade not like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<String> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<String> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(String value1, String value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(String value1, String value2) {
            addCriterion("grade not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andBetaIsNull() {
            addCriterion("beta is null");
            return (Criteria) this;
        }

        public Criteria andBetaIsNotNull() {
            addCriterion("beta is not null");
            return (Criteria) this;
        }

        public Criteria andBetaEqualTo(BigDecimal value) {
            addCriterion("beta =", value, "beta");
            return (Criteria) this;
        }

        public Criteria andBetaNotEqualTo(BigDecimal value) {
            addCriterion("beta <>", value, "beta");
            return (Criteria) this;
        }

        public Criteria andBetaGreaterThan(BigDecimal value) {
            addCriterion("beta >", value, "beta");
            return (Criteria) this;
        }

        public Criteria andBetaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("beta >=", value, "beta");
            return (Criteria) this;
        }

        public Criteria andBetaLessThan(BigDecimal value) {
            addCriterion("beta <", value, "beta");
            return (Criteria) this;
        }

        public Criteria andBetaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("beta <=", value, "beta");
            return (Criteria) this;
        }

        public Criteria andBetaIn(List<BigDecimal> values) {
            addCriterion("beta in", values, "beta");
            return (Criteria) this;
        }

        public Criteria andBetaNotIn(List<BigDecimal> values) {
            addCriterion("beta not in", values, "beta");
            return (Criteria) this;
        }

        public Criteria andBetaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("beta between", value1, value2, "beta");
            return (Criteria) this;
        }

        public Criteria andBetaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("beta not between", value1, value2, "beta");
            return (Criteria) this;
        }

        public Criteria andRatioIsNull() {
            addCriterion("ratio is null");
            return (Criteria) this;
        }

        public Criteria andRatioIsNotNull() {
            addCriterion("ratio is not null");
            return (Criteria) this;
        }

        public Criteria andRatioEqualTo(BigDecimal value) {
            addCriterion("ratio =", value, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioNotEqualTo(BigDecimal value) {
            addCriterion("ratio <>", value, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioGreaterThan(BigDecimal value) {
            addCriterion("ratio >", value, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ratio >=", value, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioLessThan(BigDecimal value) {
            addCriterion("ratio <", value, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ratio <=", value, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioIn(List<BigDecimal> values) {
            addCriterion("ratio in", values, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioNotIn(List<BigDecimal> values) {
            addCriterion("ratio not in", values, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ratio between", value1, value2, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ratio not between", value1, value2, "ratio");
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