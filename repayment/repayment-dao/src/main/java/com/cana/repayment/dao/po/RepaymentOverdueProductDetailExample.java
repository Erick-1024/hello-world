package com.cana.repayment.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepaymentOverdueProductDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public RepaymentOverdueProductDetailExample() {
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

        public Criteria andSummaryIdIsNull() {
            addCriterion("summary_id is null");
            return (Criteria) this;
        }

        public Criteria andSummaryIdIsNotNull() {
            addCriterion("summary_id is not null");
            return (Criteria) this;
        }

        public Criteria andSummaryIdEqualTo(String value) {
            addCriterion("summary_id =", value, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdNotEqualTo(String value) {
            addCriterion("summary_id <>", value, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdGreaterThan(String value) {
            addCriterion("summary_id >", value, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdGreaterThanOrEqualTo(String value) {
            addCriterion("summary_id >=", value, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdLessThan(String value) {
            addCriterion("summary_id <", value, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdLessThanOrEqualTo(String value) {
            addCriterion("summary_id <=", value, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdLike(String value) {
            addCriterion("summary_id like", value, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdNotLike(String value) {
            addCriterion("summary_id not like", value, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdIn(List<String> values) {
            addCriterion("summary_id in", values, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdNotIn(List<String> values) {
            addCriterion("summary_id not in", values, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdBetween(String value1, String value2) {
            addCriterion("summary_id between", value1, value2, "summaryId");
            return (Criteria) this;
        }

        public Criteria andSummaryIdNotBetween(String value1, String value2) {
            addCriterion("summary_id not between", value1, value2, "summaryId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdIsNull() {
            addCriterion("related_id is null");
            return (Criteria) this;
        }

        public Criteria andRelatedIdIsNotNull() {
            addCriterion("related_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelatedIdEqualTo(String value) {
            addCriterion("related_id =", value, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdNotEqualTo(String value) {
            addCriterion("related_id <>", value, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdGreaterThan(String value) {
            addCriterion("related_id >", value, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdGreaterThanOrEqualTo(String value) {
            addCriterion("related_id >=", value, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdLessThan(String value) {
            addCriterion("related_id <", value, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdLessThanOrEqualTo(String value) {
            addCriterion("related_id <=", value, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdLike(String value) {
            addCriterion("related_id like", value, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdNotLike(String value) {
            addCriterion("related_id not like", value, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdIn(List<String> values) {
            addCriterion("related_id in", values, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdNotIn(List<String> values) {
            addCriterion("related_id not in", values, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdBetween(String value1, String value2) {
            addCriterion("related_id between", value1, value2, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdNotBetween(String value1, String value2) {
            addCriterion("related_id not between", value1, value2, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedTypeIsNull() {
            addCriterion("related_type is null");
            return (Criteria) this;
        }

        public Criteria andRelatedTypeIsNotNull() {
            addCriterion("related_type is not null");
            return (Criteria) this;
        }

        public Criteria andRelatedTypeEqualTo(String value) {
            addCriterion("related_type =", value, "relatedType");
            return (Criteria) this;
        }

        public Criteria andRelatedTypeNotEqualTo(String value) {
            addCriterion("related_type <>", value, "relatedType");
            return (Criteria) this;
        }

        public Criteria andRelatedTypeGreaterThan(String value) {
            addCriterion("related_type >", value, "relatedType");
            return (Criteria) this;
        }

        public Criteria andRelatedTypeGreaterThanOrEqualTo(String value) {
            addCriterion("related_type >=", value, "relatedType");
            return (Criteria) this;
        }

        public Criteria andRelatedTypeLessThan(String value) {
            addCriterion("related_type <", value, "relatedType");
            return (Criteria) this;
        }

        public Criteria andRelatedTypeLessThanOrEqualTo(String value) {
            addCriterion("related_type <=", value, "relatedType");
            return (Criteria) this;
        }

        public Criteria andRelatedTypeLike(String value) {
            addCriterion("related_type like", value, "relatedType");
            return (Criteria) this;
        }

        public Criteria andRelatedTypeNotLike(String value) {
            addCriterion("related_type not like", value, "relatedType");
            return (Criteria) this;
        }

        public Criteria andRelatedTypeIn(List<String> values) {
            addCriterion("related_type in", values, "relatedType");
            return (Criteria) this;
        }

        public Criteria andRelatedTypeNotIn(List<String> values) {
            addCriterion("related_type not in", values, "relatedType");
            return (Criteria) this;
        }

        public Criteria andRelatedTypeBetween(String value1, String value2) {
            addCriterion("related_type between", value1, value2, "relatedType");
            return (Criteria) this;
        }

        public Criteria andRelatedTypeNotBetween(String value1, String value2) {
            addCriterion("related_type not between", value1, value2, "relatedType");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdIsNull() {
            addCriterion("loan_info_id is null");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdIsNotNull() {
            addCriterion("loan_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdEqualTo(String value) {
            addCriterion("loan_info_id =", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdNotEqualTo(String value) {
            addCriterion("loan_info_id <>", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdGreaterThan(String value) {
            addCriterion("loan_info_id >", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdGreaterThanOrEqualTo(String value) {
            addCriterion("loan_info_id >=", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdLessThan(String value) {
            addCriterion("loan_info_id <", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdLessThanOrEqualTo(String value) {
            addCriterion("loan_info_id <=", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdLike(String value) {
            addCriterion("loan_info_id like", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdNotLike(String value) {
            addCriterion("loan_info_id not like", value, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdIn(List<String> values) {
            addCriterion("loan_info_id in", values, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdNotIn(List<String> values) {
            addCriterion("loan_info_id not in", values, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdBetween(String value1, String value2) {
            addCriterion("loan_info_id between", value1, value2, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andLoanInfoIdNotBetween(String value1, String value2) {
            addCriterion("loan_info_id not between", value1, value2, "loanInfoId");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalIsNull() {
            addCriterion("overdue_principal is null");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalIsNotNull() {
            addCriterion("overdue_principal is not null");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalEqualTo(Long value) {
            addCriterion("overdue_principal =", value, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalNotEqualTo(Long value) {
            addCriterion("overdue_principal <>", value, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalGreaterThan(Long value) {
            addCriterion("overdue_principal >", value, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalGreaterThanOrEqualTo(Long value) {
            addCriterion("overdue_principal >=", value, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalLessThan(Long value) {
            addCriterion("overdue_principal <", value, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalLessThanOrEqualTo(Long value) {
            addCriterion("overdue_principal <=", value, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalIn(List<Long> values) {
            addCriterion("overdue_principal in", values, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalNotIn(List<Long> values) {
            addCriterion("overdue_principal not in", values, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalBetween(Long value1, Long value2) {
            addCriterion("overdue_principal between", value1, value2, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalNotBetween(Long value1, Long value2) {
            addCriterion("overdue_principal not between", value1, value2, "overduePrincipal");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestIsNull() {
            addCriterion("overdue_interest is null");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestIsNotNull() {
            addCriterion("overdue_interest is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestEqualTo(Long value) {
            addCriterion("overdue_interest =", value, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestNotEqualTo(Long value) {
            addCriterion("overdue_interest <>", value, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestGreaterThan(Long value) {
            addCriterion("overdue_interest >", value, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestGreaterThanOrEqualTo(Long value) {
            addCriterion("overdue_interest >=", value, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestLessThan(Long value) {
            addCriterion("overdue_interest <", value, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestLessThanOrEqualTo(Long value) {
            addCriterion("overdue_interest <=", value, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestIn(List<Long> values) {
            addCriterion("overdue_interest in", values, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestNotIn(List<Long> values) {
            addCriterion("overdue_interest not in", values, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestBetween(Long value1, Long value2) {
            addCriterion("overdue_interest between", value1, value2, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestNotBetween(Long value1, Long value2) {
            addCriterion("overdue_interest not between", value1, value2, "overdueInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeIsNull() {
            addCriterion("overdue_service_charge is null");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeIsNotNull() {
            addCriterion("overdue_service_charge is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeEqualTo(Long value) {
            addCriterion("overdue_service_charge =", value, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeNotEqualTo(Long value) {
            addCriterion("overdue_service_charge <>", value, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeGreaterThan(Long value) {
            addCriterion("overdue_service_charge >", value, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("overdue_service_charge >=", value, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeLessThan(Long value) {
            addCriterion("overdue_service_charge <", value, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeLessThanOrEqualTo(Long value) {
            addCriterion("overdue_service_charge <=", value, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeIn(List<Long> values) {
            addCriterion("overdue_service_charge in", values, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeNotIn(List<Long> values) {
            addCriterion("overdue_service_charge not in", values, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeBetween(Long value1, Long value2) {
            addCriterion("overdue_service_charge between", value1, value2, "overdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andOverdueServiceChargeNotBetween(Long value1, Long value2) {
            addCriterion("overdue_service_charge not between", value1, value2, "overdueServiceCharge");
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