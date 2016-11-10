package com.cana.repayment.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepaymentRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public RepaymentRuleExample() {
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

        public Criteria andFactorIdIsNull() {
            addCriterion("factor_id is null");
            return (Criteria) this;
        }

        public Criteria andFactorIdIsNotNull() {
            addCriterion("factor_id is not null");
            return (Criteria) this;
        }

        public Criteria andFactorIdEqualTo(String value) {
            addCriterion("factor_id =", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdNotEqualTo(String value) {
            addCriterion("factor_id <>", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdGreaterThan(String value) {
            addCriterion("factor_id >", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdGreaterThanOrEqualTo(String value) {
            addCriterion("factor_id >=", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdLessThan(String value) {
            addCriterion("factor_id <", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdLessThanOrEqualTo(String value) {
            addCriterion("factor_id <=", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdLike(String value) {
            addCriterion("factor_id like", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdNotLike(String value) {
            addCriterion("factor_id not like", value, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdIn(List<String> values) {
            addCriterion("factor_id in", values, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdNotIn(List<String> values) {
            addCriterion("factor_id not in", values, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdBetween(String value1, String value2) {
            addCriterion("factor_id between", value1, value2, "factorId");
            return (Criteria) this;
        }

        public Criteria andFactorIdNotBetween(String value1, String value2) {
            addCriterion("factor_id not between", value1, value2, "factorId");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerIdsIsNull() {
            addCriterion("fiance_customer_ids is null");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerIdsIsNotNull() {
            addCriterion("fiance_customer_ids is not null");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerIdsEqualTo(String value) {
            addCriterion("fiance_customer_ids =", value, "fianceCustomerIds");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerIdsNotEqualTo(String value) {
            addCriterion("fiance_customer_ids <>", value, "fianceCustomerIds");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerIdsGreaterThan(String value) {
            addCriterion("fiance_customer_ids >", value, "fianceCustomerIds");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerIdsGreaterThanOrEqualTo(String value) {
            addCriterion("fiance_customer_ids >=", value, "fianceCustomerIds");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerIdsLessThan(String value) {
            addCriterion("fiance_customer_ids <", value, "fianceCustomerIds");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerIdsLessThanOrEqualTo(String value) {
            addCriterion("fiance_customer_ids <=", value, "fianceCustomerIds");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerIdsLike(String value) {
            addCriterion("fiance_customer_ids like", value, "fianceCustomerIds");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerIdsNotLike(String value) {
            addCriterion("fiance_customer_ids not like", value, "fianceCustomerIds");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerIdsIn(List<String> values) {
            addCriterion("fiance_customer_ids in", values, "fianceCustomerIds");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerIdsNotIn(List<String> values) {
            addCriterion("fiance_customer_ids not in", values, "fianceCustomerIds");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerIdsBetween(String value1, String value2) {
            addCriterion("fiance_customer_ids between", value1, value2, "fianceCustomerIds");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerIdsNotBetween(String value1, String value2) {
            addCriterion("fiance_customer_ids not between", value1, value2, "fianceCustomerIds");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerCompanysIsNull() {
            addCriterion("fiance_customer_companys is null");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerCompanysIsNotNull() {
            addCriterion("fiance_customer_companys is not null");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerCompanysEqualTo(String value) {
            addCriterion("fiance_customer_companys =", value, "fianceCustomerCompanys");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerCompanysNotEqualTo(String value) {
            addCriterion("fiance_customer_companys <>", value, "fianceCustomerCompanys");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerCompanysGreaterThan(String value) {
            addCriterion("fiance_customer_companys >", value, "fianceCustomerCompanys");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerCompanysGreaterThanOrEqualTo(String value) {
            addCriterion("fiance_customer_companys >=", value, "fianceCustomerCompanys");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerCompanysLessThan(String value) {
            addCriterion("fiance_customer_companys <", value, "fianceCustomerCompanys");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerCompanysLessThanOrEqualTo(String value) {
            addCriterion("fiance_customer_companys <=", value, "fianceCustomerCompanys");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerCompanysLike(String value) {
            addCriterion("fiance_customer_companys like", value, "fianceCustomerCompanys");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerCompanysNotLike(String value) {
            addCriterion("fiance_customer_companys not like", value, "fianceCustomerCompanys");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerCompanysIn(List<String> values) {
            addCriterion("fiance_customer_companys in", values, "fianceCustomerCompanys");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerCompanysNotIn(List<String> values) {
            addCriterion("fiance_customer_companys not in", values, "fianceCustomerCompanys");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerCompanysBetween(String value1, String value2) {
            addCriterion("fiance_customer_companys between", value1, value2, "fianceCustomerCompanys");
            return (Criteria) this;
        }

        public Criteria andFianceCustomerCompanysNotBetween(String value1, String value2) {
            addCriterion("fiance_customer_companys not between", value1, value2, "fianceCustomerCompanys");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoIsNull() {
            addCriterion("factor_transfer_in_account_no is null");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoIsNotNull() {
            addCriterion("factor_transfer_in_account_no is not null");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoEqualTo(String value) {
            addCriterion("factor_transfer_in_account_no =", value, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoNotEqualTo(String value) {
            addCriterion("factor_transfer_in_account_no <>", value, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoGreaterThan(String value) {
            addCriterion("factor_transfer_in_account_no >", value, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("factor_transfer_in_account_no >=", value, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoLessThan(String value) {
            addCriterion("factor_transfer_in_account_no <", value, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoLessThanOrEqualTo(String value) {
            addCriterion("factor_transfer_in_account_no <=", value, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoLike(String value) {
            addCriterion("factor_transfer_in_account_no like", value, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoNotLike(String value) {
            addCriterion("factor_transfer_in_account_no not like", value, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoIn(List<String> values) {
            addCriterion("factor_transfer_in_account_no in", values, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoNotIn(List<String> values) {
            addCriterion("factor_transfer_in_account_no not in", values, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoBetween(String value1, String value2) {
            addCriterion("factor_transfer_in_account_no between", value1, value2, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andFactorTransferInAccountNoNotBetween(String value1, String value2) {
            addCriterion("factor_transfer_in_account_no not between", value1, value2, "factorTransferInAccountNo");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeIsNull() {
            addCriterion("deduction_time is null");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeIsNotNull() {
            addCriterion("deduction_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeEqualTo(String value) {
            addCriterion("deduction_time =", value, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeNotEqualTo(String value) {
            addCriterion("deduction_time <>", value, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeGreaterThan(String value) {
            addCriterion("deduction_time >", value, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeGreaterThanOrEqualTo(String value) {
            addCriterion("deduction_time >=", value, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeLessThan(String value) {
            addCriterion("deduction_time <", value, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeLessThanOrEqualTo(String value) {
            addCriterion("deduction_time <=", value, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeLike(String value) {
            addCriterion("deduction_time like", value, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeNotLike(String value) {
            addCriterion("deduction_time not like", value, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeIn(List<String> values) {
            addCriterion("deduction_time in", values, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeNotIn(List<String> values) {
            addCriterion("deduction_time not in", values, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeBetween(String value1, String value2) {
            addCriterion("deduction_time between", value1, value2, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionTimeNotBetween(String value1, String value2) {
            addCriterion("deduction_time not between", value1, value2, "deductionTime");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleIsNull() {
            addCriterion("deduction_rule is null");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleIsNotNull() {
            addCriterion("deduction_rule is not null");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleEqualTo(String value) {
            addCriterion("deduction_rule =", value, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleNotEqualTo(String value) {
            addCriterion("deduction_rule <>", value, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleGreaterThan(String value) {
            addCriterion("deduction_rule >", value, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleGreaterThanOrEqualTo(String value) {
            addCriterion("deduction_rule >=", value, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleLessThan(String value) {
            addCriterion("deduction_rule <", value, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleLessThanOrEqualTo(String value) {
            addCriterion("deduction_rule <=", value, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleLike(String value) {
            addCriterion("deduction_rule like", value, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleNotLike(String value) {
            addCriterion("deduction_rule not like", value, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleIn(List<String> values) {
            addCriterion("deduction_rule in", values, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleNotIn(List<String> values) {
            addCriterion("deduction_rule not in", values, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleBetween(String value1, String value2) {
            addCriterion("deduction_rule between", value1, value2, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andDeductionRuleNotBetween(String value1, String value2) {
            addCriterion("deduction_rule not between", value1, value2, "deductionRule");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysIsNull() {
            addCriterion("extension_days is null");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysIsNotNull() {
            addCriterion("extension_days is not null");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysEqualTo(Integer value) {
            addCriterion("extension_days =", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysNotEqualTo(Integer value) {
            addCriterion("extension_days <>", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysGreaterThan(Integer value) {
            addCriterion("extension_days >", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("extension_days >=", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysLessThan(Integer value) {
            addCriterion("extension_days <", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysLessThanOrEqualTo(Integer value) {
            addCriterion("extension_days <=", value, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysIn(List<Integer> values) {
            addCriterion("extension_days in", values, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysNotIn(List<Integer> values) {
            addCriterion("extension_days not in", values, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysBetween(Integer value1, Integer value2) {
            addCriterion("extension_days between", value1, value2, "extensionDays");
            return (Criteria) this;
        }

        public Criteria andExtensionDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("extension_days not between", value1, value2, "extensionDays");
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

        public Criteria andPenaltyRateIsNull() {
            addCriterion("penalty_rate is null");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateIsNotNull() {
            addCriterion("penalty_rate is not null");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateEqualTo(BigDecimal value) {
            addCriterion("penalty_rate =", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateNotEqualTo(BigDecimal value) {
            addCriterion("penalty_rate <>", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateGreaterThan(BigDecimal value) {
            addCriterion("penalty_rate >", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("penalty_rate >=", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateLessThan(BigDecimal value) {
            addCriterion("penalty_rate <", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("penalty_rate <=", value, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateIn(List<BigDecimal> values) {
            addCriterion("penalty_rate in", values, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateNotIn(List<BigDecimal> values) {
            addCriterion("penalty_rate not in", values, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("penalty_rate between", value1, value2, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andPenaltyRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("penalty_rate not between", value1, value2, "penaltyRate");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioIsNull() {
            addCriterion("early_repayment_charge_ratio is null");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioIsNotNull() {
            addCriterion("early_repayment_charge_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioEqualTo(BigDecimal value) {
            addCriterion("early_repayment_charge_ratio =", value, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioNotEqualTo(BigDecimal value) {
            addCriterion("early_repayment_charge_ratio <>", value, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioGreaterThan(BigDecimal value) {
            addCriterion("early_repayment_charge_ratio >", value, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("early_repayment_charge_ratio >=", value, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioLessThan(BigDecimal value) {
            addCriterion("early_repayment_charge_ratio <", value, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("early_repayment_charge_ratio <=", value, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioIn(List<BigDecimal> values) {
            addCriterion("early_repayment_charge_ratio in", values, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioNotIn(List<BigDecimal> values) {
            addCriterion("early_repayment_charge_ratio not in", values, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("early_repayment_charge_ratio between", value1, value2, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("early_repayment_charge_ratio not between", value1, value2, "earlyRepaymentChargeRatio");
            return (Criteria) this;
        }

        public Criteria andAccountAccumulationTimeIsNull() {
            addCriterion("account_accumulation_time is null");
            return (Criteria) this;
        }

        public Criteria andAccountAccumulationTimeIsNotNull() {
            addCriterion("account_accumulation_time is not null");
            return (Criteria) this;
        }

        public Criteria andAccountAccumulationTimeEqualTo(String value) {
            addCriterion("account_accumulation_time =", value, "accountAccumulationTime");
            return (Criteria) this;
        }

        public Criteria andAccountAccumulationTimeNotEqualTo(String value) {
            addCriterion("account_accumulation_time <>", value, "accountAccumulationTime");
            return (Criteria) this;
        }

        public Criteria andAccountAccumulationTimeGreaterThan(String value) {
            addCriterion("account_accumulation_time >", value, "accountAccumulationTime");
            return (Criteria) this;
        }

        public Criteria andAccountAccumulationTimeGreaterThanOrEqualTo(String value) {
            addCriterion("account_accumulation_time >=", value, "accountAccumulationTime");
            return (Criteria) this;
        }

        public Criteria andAccountAccumulationTimeLessThan(String value) {
            addCriterion("account_accumulation_time <", value, "accountAccumulationTime");
            return (Criteria) this;
        }

        public Criteria andAccountAccumulationTimeLessThanOrEqualTo(String value) {
            addCriterion("account_accumulation_time <=", value, "accountAccumulationTime");
            return (Criteria) this;
        }

        public Criteria andAccountAccumulationTimeLike(String value) {
            addCriterion("account_accumulation_time like", value, "accountAccumulationTime");
            return (Criteria) this;
        }

        public Criteria andAccountAccumulationTimeNotLike(String value) {
            addCriterion("account_accumulation_time not like", value, "accountAccumulationTime");
            return (Criteria) this;
        }

        public Criteria andAccountAccumulationTimeIn(List<String> values) {
            addCriterion("account_accumulation_time in", values, "accountAccumulationTime");
            return (Criteria) this;
        }

        public Criteria andAccountAccumulationTimeNotIn(List<String> values) {
            addCriterion("account_accumulation_time not in", values, "accountAccumulationTime");
            return (Criteria) this;
        }

        public Criteria andAccountAccumulationTimeBetween(String value1, String value2) {
            addCriterion("account_accumulation_time between", value1, value2, "accountAccumulationTime");
            return (Criteria) this;
        }

        public Criteria andAccountAccumulationTimeNotBetween(String value1, String value2) {
            addCriterion("account_accumulation_time not between", value1, value2, "accountAccumulationTime");
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