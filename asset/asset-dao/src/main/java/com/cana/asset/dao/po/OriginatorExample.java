package com.cana.asset.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OriginatorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public OriginatorExample() {
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

        public Criteria andSpecialProgramIdIsNull() {
            addCriterion("special_program_id is null");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdIsNotNull() {
            addCriterion("special_program_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdEqualTo(String value) {
            addCriterion("special_program_id =", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdNotEqualTo(String value) {
            addCriterion("special_program_id <>", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdGreaterThan(String value) {
            addCriterion("special_program_id >", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdGreaterThanOrEqualTo(String value) {
            addCriterion("special_program_id >=", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdLessThan(String value) {
            addCriterion("special_program_id <", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdLessThanOrEqualTo(String value) {
            addCriterion("special_program_id <=", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdLike(String value) {
            addCriterion("special_program_id like", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdNotLike(String value) {
            addCriterion("special_program_id not like", value, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdIn(List<String> values) {
            addCriterion("special_program_id in", values, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdNotIn(List<String> values) {
            addCriterion("special_program_id not in", values, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdBetween(String value1, String value2) {
            addCriterion("special_program_id between", value1, value2, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andSpecialProgramIdNotBetween(String value1, String value2) {
            addCriterion("special_program_id not between", value1, value2, "specialProgramId");
            return (Criteria) this;
        }

        public Criteria andOriginatorNameIsNull() {
            addCriterion("originator_name is null");
            return (Criteria) this;
        }

        public Criteria andOriginatorNameIsNotNull() {
            addCriterion("originator_name is not null");
            return (Criteria) this;
        }

        public Criteria andOriginatorNameEqualTo(String value) {
            addCriterion("originator_name =", value, "originatorName");
            return (Criteria) this;
        }

        public Criteria andOriginatorNameNotEqualTo(String value) {
            addCriterion("originator_name <>", value, "originatorName");
            return (Criteria) this;
        }

        public Criteria andOriginatorNameGreaterThan(String value) {
            addCriterion("originator_name >", value, "originatorName");
            return (Criteria) this;
        }

        public Criteria andOriginatorNameGreaterThanOrEqualTo(String value) {
            addCriterion("originator_name >=", value, "originatorName");
            return (Criteria) this;
        }

        public Criteria andOriginatorNameLessThan(String value) {
            addCriterion("originator_name <", value, "originatorName");
            return (Criteria) this;
        }

        public Criteria andOriginatorNameLessThanOrEqualTo(String value) {
            addCriterion("originator_name <=", value, "originatorName");
            return (Criteria) this;
        }

        public Criteria andOriginatorNameLike(String value) {
            addCriterion("originator_name like", value, "originatorName");
            return (Criteria) this;
        }

        public Criteria andOriginatorNameNotLike(String value) {
            addCriterion("originator_name not like", value, "originatorName");
            return (Criteria) this;
        }

        public Criteria andOriginatorNameIn(List<String> values) {
            addCriterion("originator_name in", values, "originatorName");
            return (Criteria) this;
        }

        public Criteria andOriginatorNameNotIn(List<String> values) {
            addCriterion("originator_name not in", values, "originatorName");
            return (Criteria) this;
        }

        public Criteria andOriginatorNameBetween(String value1, String value2) {
            addCriterion("originator_name between", value1, value2, "originatorName");
            return (Criteria) this;
        }

        public Criteria andOriginatorNameNotBetween(String value1, String value2) {
            addCriterion("originator_name not between", value1, value2, "originatorName");
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