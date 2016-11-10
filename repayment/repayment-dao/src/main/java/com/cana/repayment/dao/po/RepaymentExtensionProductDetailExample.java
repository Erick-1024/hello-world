package com.cana.repayment.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepaymentExtensionProductDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public RepaymentExtensionProductDetailExample() {
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

        public Criteria andRepaymentPlanIdIsNull() {
            addCriterion("repayment_plan_id is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentPlanIdIsNotNull() {
            addCriterion("repayment_plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentPlanIdEqualTo(String value) {
            addCriterion("repayment_plan_id =", value, "repaymentPlanId");
            return (Criteria) this;
        }

        public Criteria andRepaymentPlanIdNotEqualTo(String value) {
            addCriterion("repayment_plan_id <>", value, "repaymentPlanId");
            return (Criteria) this;
        }

        public Criteria andRepaymentPlanIdGreaterThan(String value) {
            addCriterion("repayment_plan_id >", value, "repaymentPlanId");
            return (Criteria) this;
        }

        public Criteria andRepaymentPlanIdGreaterThanOrEqualTo(String value) {
            addCriterion("repayment_plan_id >=", value, "repaymentPlanId");
            return (Criteria) this;
        }

        public Criteria andRepaymentPlanIdLessThan(String value) {
            addCriterion("repayment_plan_id <", value, "repaymentPlanId");
            return (Criteria) this;
        }

        public Criteria andRepaymentPlanIdLessThanOrEqualTo(String value) {
            addCriterion("repayment_plan_id <=", value, "repaymentPlanId");
            return (Criteria) this;
        }

        public Criteria andRepaymentPlanIdLike(String value) {
            addCriterion("repayment_plan_id like", value, "repaymentPlanId");
            return (Criteria) this;
        }

        public Criteria andRepaymentPlanIdNotLike(String value) {
            addCriterion("repayment_plan_id not like", value, "repaymentPlanId");
            return (Criteria) this;
        }

        public Criteria andRepaymentPlanIdIn(List<String> values) {
            addCriterion("repayment_plan_id in", values, "repaymentPlanId");
            return (Criteria) this;
        }

        public Criteria andRepaymentPlanIdNotIn(List<String> values) {
            addCriterion("repayment_plan_id not in", values, "repaymentPlanId");
            return (Criteria) this;
        }

        public Criteria andRepaymentPlanIdBetween(String value1, String value2) {
            addCriterion("repayment_plan_id between", value1, value2, "repaymentPlanId");
            return (Criteria) this;
        }

        public Criteria andRepaymentPlanIdNotBetween(String value1, String value2) {
            addCriterion("repayment_plan_id not between", value1, value2, "repaymentPlanId");
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

        public Criteria andExtensionChargeIsNull() {
            addCriterion("extension_charge is null");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeIsNotNull() {
            addCriterion("extension_charge is not null");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeEqualTo(Long value) {
            addCriterion("extension_charge =", value, "extensionCharge");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeNotEqualTo(Long value) {
            addCriterion("extension_charge <>", value, "extensionCharge");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeGreaterThan(Long value) {
            addCriterion("extension_charge >", value, "extensionCharge");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("extension_charge >=", value, "extensionCharge");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeLessThan(Long value) {
            addCriterion("extension_charge <", value, "extensionCharge");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeLessThanOrEqualTo(Long value) {
            addCriterion("extension_charge <=", value, "extensionCharge");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeIn(List<Long> values) {
            addCriterion("extension_charge in", values, "extensionCharge");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeNotIn(List<Long> values) {
            addCriterion("extension_charge not in", values, "extensionCharge");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeBetween(Long value1, Long value2) {
            addCriterion("extension_charge between", value1, value2, "extensionCharge");
            return (Criteria) this;
        }

        public Criteria andExtensionChargeNotBetween(Long value1, Long value2) {
            addCriterion("extension_charge not between", value1, value2, "extensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalIsNull() {
            addCriterion("account_principal is null");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalIsNotNull() {
            addCriterion("account_principal is not null");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalEqualTo(Long value) {
            addCriterion("account_principal =", value, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalNotEqualTo(Long value) {
            addCriterion("account_principal <>", value, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalGreaterThan(Long value) {
            addCriterion("account_principal >", value, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalGreaterThanOrEqualTo(Long value) {
            addCriterion("account_principal >=", value, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalLessThan(Long value) {
            addCriterion("account_principal <", value, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalLessThanOrEqualTo(Long value) {
            addCriterion("account_principal <=", value, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalIn(List<Long> values) {
            addCriterion("account_principal in", values, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalNotIn(List<Long> values) {
            addCriterion("account_principal not in", values, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalBetween(Long value1, Long value2) {
            addCriterion("account_principal between", value1, value2, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountPrincipalNotBetween(Long value1, Long value2) {
            addCriterion("account_principal not between", value1, value2, "accountPrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountInterestIsNull() {
            addCriterion("account_interest is null");
            return (Criteria) this;
        }

        public Criteria andAccountInterestIsNotNull() {
            addCriterion("account_interest is not null");
            return (Criteria) this;
        }

        public Criteria andAccountInterestEqualTo(Long value) {
            addCriterion("account_interest =", value, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestNotEqualTo(Long value) {
            addCriterion("account_interest <>", value, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestGreaterThan(Long value) {
            addCriterion("account_interest >", value, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestGreaterThanOrEqualTo(Long value) {
            addCriterion("account_interest >=", value, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestLessThan(Long value) {
            addCriterion("account_interest <", value, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestLessThanOrEqualTo(Long value) {
            addCriterion("account_interest <=", value, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestIn(List<Long> values) {
            addCriterion("account_interest in", values, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestNotIn(List<Long> values) {
            addCriterion("account_interest not in", values, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestBetween(Long value1, Long value2) {
            addCriterion("account_interest between", value1, value2, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountInterestNotBetween(Long value1, Long value2) {
            addCriterion("account_interest not between", value1, value2, "accountInterest");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeIsNull() {
            addCriterion("account_service_charge is null");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeIsNotNull() {
            addCriterion("account_service_charge is not null");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeEqualTo(Long value) {
            addCriterion("account_service_charge =", value, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeNotEqualTo(Long value) {
            addCriterion("account_service_charge <>", value, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeGreaterThan(Long value) {
            addCriterion("account_service_charge >", value, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("account_service_charge >=", value, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeLessThan(Long value) {
            addCriterion("account_service_charge <", value, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeLessThanOrEqualTo(Long value) {
            addCriterion("account_service_charge <=", value, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeIn(List<Long> values) {
            addCriterion("account_service_charge in", values, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeNotIn(List<Long> values) {
            addCriterion("account_service_charge not in", values, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeBetween(Long value1, Long value2) {
            addCriterion("account_service_charge between", value1, value2, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountServiceChargeNotBetween(Long value1, Long value2) {
            addCriterion("account_service_charge not between", value1, value2, "accountServiceCharge");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioIsNull() {
            addCriterion("extension_ratio is null");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioIsNotNull() {
            addCriterion("extension_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioEqualTo(BigDecimal value) {
            addCriterion("extension_ratio =", value, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioNotEqualTo(BigDecimal value) {
            addCriterion("extension_ratio <>", value, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioGreaterThan(BigDecimal value) {
            addCriterion("extension_ratio >", value, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("extension_ratio >=", value, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioLessThan(BigDecimal value) {
            addCriterion("extension_ratio <", value, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("extension_ratio <=", value, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioIn(List<BigDecimal> values) {
            addCriterion("extension_ratio in", values, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioNotIn(List<BigDecimal> values) {
            addCriterion("extension_ratio not in", values, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("extension_ratio between", value1, value2, "extensionRatio");
            return (Criteria) this;
        }

        public Criteria andExtensionRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("extension_ratio not between", value1, value2, "extensionRatio");
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

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(String value) {
            addCriterion("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(String value) {
            addCriterion("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(String value) {
            addCriterion("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(String value) {
            addCriterion("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(String value) {
            addCriterion("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(String value) {
            addCriterion("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLike(String value) {
            addCriterion("date like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotLike(String value) {
            addCriterion("date not like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<String> values) {
            addCriterion("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<String> values) {
            addCriterion("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(String value1, String value2) {
            addCriterion("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(String value1, String value2) {
            addCriterion("date not between", value1, value2, "date");
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