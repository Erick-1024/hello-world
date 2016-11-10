package com.cana.asset.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoanPaidExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public LoanPaidExample() {
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

        public Criteria andLoanPlanIdIsNull() {
            addCriterion("loan_plan_id is null");
            return (Criteria) this;
        }

        public Criteria andLoanPlanIdIsNotNull() {
            addCriterion("loan_plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andLoanPlanIdEqualTo(String value) {
            addCriterion("loan_plan_id =", value, "loanPlanId");
            return (Criteria) this;
        }

        public Criteria andLoanPlanIdNotEqualTo(String value) {
            addCriterion("loan_plan_id <>", value, "loanPlanId");
            return (Criteria) this;
        }

        public Criteria andLoanPlanIdGreaterThan(String value) {
            addCriterion("loan_plan_id >", value, "loanPlanId");
            return (Criteria) this;
        }

        public Criteria andLoanPlanIdGreaterThanOrEqualTo(String value) {
            addCriterion("loan_plan_id >=", value, "loanPlanId");
            return (Criteria) this;
        }

        public Criteria andLoanPlanIdLessThan(String value) {
            addCriterion("loan_plan_id <", value, "loanPlanId");
            return (Criteria) this;
        }

        public Criteria andLoanPlanIdLessThanOrEqualTo(String value) {
            addCriterion("loan_plan_id <=", value, "loanPlanId");
            return (Criteria) this;
        }

        public Criteria andLoanPlanIdLike(String value) {
            addCriterion("loan_plan_id like", value, "loanPlanId");
            return (Criteria) this;
        }

        public Criteria andLoanPlanIdNotLike(String value) {
            addCriterion("loan_plan_id not like", value, "loanPlanId");
            return (Criteria) this;
        }

        public Criteria andLoanPlanIdIn(List<String> values) {
            addCriterion("loan_plan_id in", values, "loanPlanId");
            return (Criteria) this;
        }

        public Criteria andLoanPlanIdNotIn(List<String> values) {
            addCriterion("loan_plan_id not in", values, "loanPlanId");
            return (Criteria) this;
        }

        public Criteria andLoanPlanIdBetween(String value1, String value2) {
            addCriterion("loan_plan_id between", value1, value2, "loanPlanId");
            return (Criteria) this;
        }

        public Criteria andLoanPlanIdNotBetween(String value1, String value2) {
            addCriterion("loan_plan_id not between", value1, value2, "loanPlanId");
            return (Criteria) this;
        }

        public Criteria andPaidAmountIsNull() {
            addCriterion("paid_amount is null");
            return (Criteria) this;
        }

        public Criteria andPaidAmountIsNotNull() {
            addCriterion("paid_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPaidAmountEqualTo(Long value) {
            addCriterion("paid_amount =", value, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountNotEqualTo(Long value) {
            addCriterion("paid_amount <>", value, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountGreaterThan(Long value) {
            addCriterion("paid_amount >", value, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_amount >=", value, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountLessThan(Long value) {
            addCriterion("paid_amount <", value, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountLessThanOrEqualTo(Long value) {
            addCriterion("paid_amount <=", value, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountIn(List<Long> values) {
            addCriterion("paid_amount in", values, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountNotIn(List<Long> values) {
            addCriterion("paid_amount not in", values, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountBetween(Long value1, Long value2) {
            addCriterion("paid_amount between", value1, value2, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidAmountNotBetween(Long value1, Long value2) {
            addCriterion("paid_amount not between", value1, value2, "paidAmount");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalIsNull() {
            addCriterion("paid_principal is null");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalIsNotNull() {
            addCriterion("paid_principal is not null");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalEqualTo(Long value) {
            addCriterion("paid_principal =", value, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalNotEqualTo(Long value) {
            addCriterion("paid_principal <>", value, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalGreaterThan(Long value) {
            addCriterion("paid_principal >", value, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_principal >=", value, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalLessThan(Long value) {
            addCriterion("paid_principal <", value, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalLessThanOrEqualTo(Long value) {
            addCriterion("paid_principal <=", value, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalIn(List<Long> values) {
            addCriterion("paid_principal in", values, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalNotIn(List<Long> values) {
            addCriterion("paid_principal not in", values, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalBetween(Long value1, Long value2) {
            addCriterion("paid_principal between", value1, value2, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidPrincipalNotBetween(Long value1, Long value2) {
            addCriterion("paid_principal not between", value1, value2, "paidPrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidInterestIsNull() {
            addCriterion("paid_interest is null");
            return (Criteria) this;
        }

        public Criteria andPaidInterestIsNotNull() {
            addCriterion("paid_interest is not null");
            return (Criteria) this;
        }

        public Criteria andPaidInterestEqualTo(Long value) {
            addCriterion("paid_interest =", value, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidInterestNotEqualTo(Long value) {
            addCriterion("paid_interest <>", value, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidInterestGreaterThan(Long value) {
            addCriterion("paid_interest >", value, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidInterestGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_interest >=", value, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidInterestLessThan(Long value) {
            addCriterion("paid_interest <", value, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidInterestLessThanOrEqualTo(Long value) {
            addCriterion("paid_interest <=", value, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidInterestIn(List<Long> values) {
            addCriterion("paid_interest in", values, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidInterestNotIn(List<Long> values) {
            addCriterion("paid_interest not in", values, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidInterestBetween(Long value1, Long value2) {
            addCriterion("paid_interest between", value1, value2, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidInterestNotBetween(Long value1, Long value2) {
            addCriterion("paid_interest not between", value1, value2, "paidInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueIsNull() {
            addCriterion("paid_overdue is null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueIsNotNull() {
            addCriterion("paid_overdue is not null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueEqualTo(Long value) {
            addCriterion("paid_overdue =", value, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueNotEqualTo(Long value) {
            addCriterion("paid_overdue <>", value, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueGreaterThan(Long value) {
            addCriterion("paid_overdue >", value, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_overdue >=", value, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueLessThan(Long value) {
            addCriterion("paid_overdue <", value, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueLessThanOrEqualTo(Long value) {
            addCriterion("paid_overdue <=", value, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueIn(List<Long> values) {
            addCriterion("paid_overdue in", values, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueNotIn(List<Long> values) {
            addCriterion("paid_overdue not in", values, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueBetween(Long value1, Long value2) {
            addCriterion("paid_overdue between", value1, value2, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueNotBetween(Long value1, Long value2) {
            addCriterion("paid_overdue not between", value1, value2, "paidOverdue");
            return (Criteria) this;
        }

        public Criteria andPaidDateIsNull() {
            addCriterion("paid_date is null");
            return (Criteria) this;
        }

        public Criteria andPaidDateIsNotNull() {
            addCriterion("paid_date is not null");
            return (Criteria) this;
        }

        public Criteria andPaidDateEqualTo(String value) {
            addCriterion("paid_date =", value, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateNotEqualTo(String value) {
            addCriterion("paid_date <>", value, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateGreaterThan(String value) {
            addCriterion("paid_date >", value, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateGreaterThanOrEqualTo(String value) {
            addCriterion("paid_date >=", value, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateLessThan(String value) {
            addCriterion("paid_date <", value, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateLessThanOrEqualTo(String value) {
            addCriterion("paid_date <=", value, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateLike(String value) {
            addCriterion("paid_date like", value, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateNotLike(String value) {
            addCriterion("paid_date not like", value, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateIn(List<String> values) {
            addCriterion("paid_date in", values, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateNotIn(List<String> values) {
            addCriterion("paid_date not in", values, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateBetween(String value1, String value2) {
            addCriterion("paid_date between", value1, value2, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateNotBetween(String value1, String value2) {
            addCriterion("paid_date not between", value1, value2, "paidDate");
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