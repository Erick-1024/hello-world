package com.cana.repayment.dao.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepaymentSingleDistributeDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public RepaymentSingleDistributeDetailExample() {
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

        public Criteria andPayNormalPrincipalIsNull() {
            addCriterion("pay_normal_principal is null");
            return (Criteria) this;
        }

        public Criteria andPayNormalPrincipalIsNotNull() {
            addCriterion("pay_normal_principal is not null");
            return (Criteria) this;
        }

        public Criteria andPayNormalPrincipalEqualTo(Long value) {
            addCriterion("pay_normal_principal =", value, "payNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPayNormalPrincipalNotEqualTo(Long value) {
            addCriterion("pay_normal_principal <>", value, "payNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPayNormalPrincipalGreaterThan(Long value) {
            addCriterion("pay_normal_principal >", value, "payNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPayNormalPrincipalGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_normal_principal >=", value, "payNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPayNormalPrincipalLessThan(Long value) {
            addCriterion("pay_normal_principal <", value, "payNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPayNormalPrincipalLessThanOrEqualTo(Long value) {
            addCriterion("pay_normal_principal <=", value, "payNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPayNormalPrincipalIn(List<Long> values) {
            addCriterion("pay_normal_principal in", values, "payNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPayNormalPrincipalNotIn(List<Long> values) {
            addCriterion("pay_normal_principal not in", values, "payNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPayNormalPrincipalBetween(Long value1, Long value2) {
            addCriterion("pay_normal_principal between", value1, value2, "payNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPayNormalPrincipalNotBetween(Long value1, Long value2) {
            addCriterion("pay_normal_principal not between", value1, value2, "payNormalPrincipal");
            return (Criteria) this;
        }

        public Criteria andPayNormalInterestIsNull() {
            addCriterion("pay_normal_interest is null");
            return (Criteria) this;
        }

        public Criteria andPayNormalInterestIsNotNull() {
            addCriterion("pay_normal_interest is not null");
            return (Criteria) this;
        }

        public Criteria andPayNormalInterestEqualTo(Long value) {
            addCriterion("pay_normal_interest =", value, "payNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPayNormalInterestNotEqualTo(Long value) {
            addCriterion("pay_normal_interest <>", value, "payNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPayNormalInterestGreaterThan(Long value) {
            addCriterion("pay_normal_interest >", value, "payNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPayNormalInterestGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_normal_interest >=", value, "payNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPayNormalInterestLessThan(Long value) {
            addCriterion("pay_normal_interest <", value, "payNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPayNormalInterestLessThanOrEqualTo(Long value) {
            addCriterion("pay_normal_interest <=", value, "payNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPayNormalInterestIn(List<Long> values) {
            addCriterion("pay_normal_interest in", values, "payNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPayNormalInterestNotIn(List<Long> values) {
            addCriterion("pay_normal_interest not in", values, "payNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPayNormalInterestBetween(Long value1, Long value2) {
            addCriterion("pay_normal_interest between", value1, value2, "payNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPayNormalInterestNotBetween(Long value1, Long value2) {
            addCriterion("pay_normal_interest not between", value1, value2, "payNormalInterest");
            return (Criteria) this;
        }

        public Criteria andPayNormalServiceChargeIsNull() {
            addCriterion("pay_normal_service_charge is null");
            return (Criteria) this;
        }

        public Criteria andPayNormalServiceChargeIsNotNull() {
            addCriterion("pay_normal_service_charge is not null");
            return (Criteria) this;
        }

        public Criteria andPayNormalServiceChargeEqualTo(Long value) {
            addCriterion("pay_normal_service_charge =", value, "payNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayNormalServiceChargeNotEqualTo(Long value) {
            addCriterion("pay_normal_service_charge <>", value, "payNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayNormalServiceChargeGreaterThan(Long value) {
            addCriterion("pay_normal_service_charge >", value, "payNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayNormalServiceChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_normal_service_charge >=", value, "payNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayNormalServiceChargeLessThan(Long value) {
            addCriterion("pay_normal_service_charge <", value, "payNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayNormalServiceChargeLessThanOrEqualTo(Long value) {
            addCriterion("pay_normal_service_charge <=", value, "payNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayNormalServiceChargeIn(List<Long> values) {
            addCriterion("pay_normal_service_charge in", values, "payNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayNormalServiceChargeNotIn(List<Long> values) {
            addCriterion("pay_normal_service_charge not in", values, "payNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayNormalServiceChargeBetween(Long value1, Long value2) {
            addCriterion("pay_normal_service_charge between", value1, value2, "payNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayNormalServiceChargeNotBetween(Long value1, Long value2) {
            addCriterion("pay_normal_service_charge not between", value1, value2, "payNormalServiceCharge");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeIsNull() {
            addCriterion("early_repayment_charge is null");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeIsNotNull() {
            addCriterion("early_repayment_charge is not null");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeEqualTo(Long value) {
            addCriterion("early_repayment_charge =", value, "earlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeNotEqualTo(Long value) {
            addCriterion("early_repayment_charge <>", value, "earlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeGreaterThan(Long value) {
            addCriterion("early_repayment_charge >", value, "earlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("early_repayment_charge >=", value, "earlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeLessThan(Long value) {
            addCriterion("early_repayment_charge <", value, "earlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeLessThanOrEqualTo(Long value) {
            addCriterion("early_repayment_charge <=", value, "earlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeIn(List<Long> values) {
            addCriterion("early_repayment_charge in", values, "earlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeNotIn(List<Long> values) {
            addCriterion("early_repayment_charge not in", values, "earlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeBetween(Long value1, Long value2) {
            addCriterion("early_repayment_charge between", value1, value2, "earlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andEarlyRepaymentChargeNotBetween(Long value1, Long value2) {
            addCriterion("early_repayment_charge not between", value1, value2, "earlyRepaymentCharge");
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

        public Criteria andPayExtensionChargeIsNull() {
            addCriterion("pay_extension_charge is null");
            return (Criteria) this;
        }

        public Criteria andPayExtensionChargeIsNotNull() {
            addCriterion("pay_extension_charge is not null");
            return (Criteria) this;
        }

        public Criteria andPayExtensionChargeEqualTo(Long value) {
            addCriterion("pay_extension_charge =", value, "payExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPayExtensionChargeNotEqualTo(Long value) {
            addCriterion("pay_extension_charge <>", value, "payExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPayExtensionChargeGreaterThan(Long value) {
            addCriterion("pay_extension_charge >", value, "payExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPayExtensionChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_extension_charge >=", value, "payExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPayExtensionChargeLessThan(Long value) {
            addCriterion("pay_extension_charge <", value, "payExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPayExtensionChargeLessThanOrEqualTo(Long value) {
            addCriterion("pay_extension_charge <=", value, "payExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPayExtensionChargeIn(List<Long> values) {
            addCriterion("pay_extension_charge in", values, "payExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPayExtensionChargeNotIn(List<Long> values) {
            addCriterion("pay_extension_charge not in", values, "payExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPayExtensionChargeBetween(Long value1, Long value2) {
            addCriterion("pay_extension_charge between", value1, value2, "payExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPayExtensionChargeNotBetween(Long value1, Long value2) {
            addCriterion("pay_extension_charge not between", value1, value2, "payExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalIsNull() {
            addCriterion("pay_overdue_principal is null");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalIsNotNull() {
            addCriterion("pay_overdue_principal is not null");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalEqualTo(Long value) {
            addCriterion("pay_overdue_principal =", value, "payOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalNotEqualTo(Long value) {
            addCriterion("pay_overdue_principal <>", value, "payOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalGreaterThan(Long value) {
            addCriterion("pay_overdue_principal >", value, "payOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_overdue_principal >=", value, "payOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalLessThan(Long value) {
            addCriterion("pay_overdue_principal <", value, "payOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalLessThanOrEqualTo(Long value) {
            addCriterion("pay_overdue_principal <=", value, "payOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalIn(List<Long> values) {
            addCriterion("pay_overdue_principal in", values, "payOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalNotIn(List<Long> values) {
            addCriterion("pay_overdue_principal not in", values, "payOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalBetween(Long value1, Long value2) {
            addCriterion("pay_overdue_principal between", value1, value2, "payOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalNotBetween(Long value1, Long value2) {
            addCriterion("pay_overdue_principal not between", value1, value2, "payOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestIsNull() {
            addCriterion("pay_overdue_interest is null");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestIsNotNull() {
            addCriterion("pay_overdue_interest is not null");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestEqualTo(Long value) {
            addCriterion("pay_overdue_interest =", value, "payOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestNotEqualTo(Long value) {
            addCriterion("pay_overdue_interest <>", value, "payOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestGreaterThan(Long value) {
            addCriterion("pay_overdue_interest >", value, "payOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_overdue_interest >=", value, "payOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestLessThan(Long value) {
            addCriterion("pay_overdue_interest <", value, "payOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestLessThanOrEqualTo(Long value) {
            addCriterion("pay_overdue_interest <=", value, "payOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestIn(List<Long> values) {
            addCriterion("pay_overdue_interest in", values, "payOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestNotIn(List<Long> values) {
            addCriterion("pay_overdue_interest not in", values, "payOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestBetween(Long value1, Long value2) {
            addCriterion("pay_overdue_interest between", value1, value2, "payOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestNotBetween(Long value1, Long value2) {
            addCriterion("pay_overdue_interest not between", value1, value2, "payOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargeIsNull() {
            addCriterion("pay_overdue_service_charge is null");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargeIsNotNull() {
            addCriterion("pay_overdue_service_charge is not null");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargeEqualTo(Long value) {
            addCriterion("pay_overdue_service_charge =", value, "payOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargeNotEqualTo(Long value) {
            addCriterion("pay_overdue_service_charge <>", value, "payOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargeGreaterThan(Long value) {
            addCriterion("pay_overdue_service_charge >", value, "payOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_overdue_service_charge >=", value, "payOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargeLessThan(Long value) {
            addCriterion("pay_overdue_service_charge <", value, "payOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargeLessThanOrEqualTo(Long value) {
            addCriterion("pay_overdue_service_charge <=", value, "payOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargeIn(List<Long> values) {
            addCriterion("pay_overdue_service_charge in", values, "payOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargeNotIn(List<Long> values) {
            addCriterion("pay_overdue_service_charge not in", values, "payOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargeBetween(Long value1, Long value2) {
            addCriterion("pay_overdue_service_charge between", value1, value2, "payOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargeNotBetween(Long value1, Long value2) {
            addCriterion("pay_overdue_service_charge not between", value1, value2, "payOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalPenaltyIsNull() {
            addCriterion("pay_overdue_principal_penalty is null");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalPenaltyIsNotNull() {
            addCriterion("pay_overdue_principal_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalPenaltyEqualTo(Long value) {
            addCriterion("pay_overdue_principal_penalty =", value, "payOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalPenaltyNotEqualTo(Long value) {
            addCriterion("pay_overdue_principal_penalty <>", value, "payOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalPenaltyGreaterThan(Long value) {
            addCriterion("pay_overdue_principal_penalty >", value, "payOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalPenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_overdue_principal_penalty >=", value, "payOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalPenaltyLessThan(Long value) {
            addCriterion("pay_overdue_principal_penalty <", value, "payOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalPenaltyLessThanOrEqualTo(Long value) {
            addCriterion("pay_overdue_principal_penalty <=", value, "payOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalPenaltyIn(List<Long> values) {
            addCriterion("pay_overdue_principal_penalty in", values, "payOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalPenaltyNotIn(List<Long> values) {
            addCriterion("pay_overdue_principal_penalty not in", values, "payOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalPenaltyBetween(Long value1, Long value2) {
            addCriterion("pay_overdue_principal_penalty between", value1, value2, "payOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverduePrincipalPenaltyNotBetween(Long value1, Long value2) {
            addCriterion("pay_overdue_principal_penalty not between", value1, value2, "payOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestPenaltyIsNull() {
            addCriterion("pay_overdue_interest_penalty is null");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestPenaltyIsNotNull() {
            addCriterion("pay_overdue_interest_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestPenaltyEqualTo(Long value) {
            addCriterion("pay_overdue_interest_penalty =", value, "payOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestPenaltyNotEqualTo(Long value) {
            addCriterion("pay_overdue_interest_penalty <>", value, "payOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestPenaltyGreaterThan(Long value) {
            addCriterion("pay_overdue_interest_penalty >", value, "payOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestPenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_overdue_interest_penalty >=", value, "payOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestPenaltyLessThan(Long value) {
            addCriterion("pay_overdue_interest_penalty <", value, "payOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestPenaltyLessThanOrEqualTo(Long value) {
            addCriterion("pay_overdue_interest_penalty <=", value, "payOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestPenaltyIn(List<Long> values) {
            addCriterion("pay_overdue_interest_penalty in", values, "payOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestPenaltyNotIn(List<Long> values) {
            addCriterion("pay_overdue_interest_penalty not in", values, "payOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestPenaltyBetween(Long value1, Long value2) {
            addCriterion("pay_overdue_interest_penalty between", value1, value2, "payOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueInterestPenaltyNotBetween(Long value1, Long value2) {
            addCriterion("pay_overdue_interest_penalty not between", value1, value2, "payOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargePenaltyIsNull() {
            addCriterion("pay_overdue_service_charge_penalty is null");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargePenaltyIsNotNull() {
            addCriterion("pay_overdue_service_charge_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargePenaltyEqualTo(Long value) {
            addCriterion("pay_overdue_service_charge_penalty =", value, "payOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargePenaltyNotEqualTo(Long value) {
            addCriterion("pay_overdue_service_charge_penalty <>", value, "payOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargePenaltyGreaterThan(Long value) {
            addCriterion("pay_overdue_service_charge_penalty >", value, "payOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargePenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_overdue_service_charge_penalty >=", value, "payOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargePenaltyLessThan(Long value) {
            addCriterion("pay_overdue_service_charge_penalty <", value, "payOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargePenaltyLessThanOrEqualTo(Long value) {
            addCriterion("pay_overdue_service_charge_penalty <=", value, "payOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargePenaltyIn(List<Long> values) {
            addCriterion("pay_overdue_service_charge_penalty in", values, "payOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargePenaltyNotIn(List<Long> values) {
            addCriterion("pay_overdue_service_charge_penalty not in", values, "payOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargePenaltyBetween(Long value1, Long value2) {
            addCriterion("pay_overdue_service_charge_penalty between", value1, value2, "payOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPayOverdueServiceChargePenaltyNotBetween(Long value1, Long value2) {
            addCriterion("pay_overdue_service_charge_penalty not between", value1, value2, "payOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPayOtherPenaltyIsNull() {
            addCriterion("pay_other_penalty is null");
            return (Criteria) this;
        }

        public Criteria andPayOtherPenaltyIsNotNull() {
            addCriterion("pay_other_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andPayOtherPenaltyEqualTo(Long value) {
            addCriterion("pay_other_penalty =", value, "payOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOtherPenaltyNotEqualTo(Long value) {
            addCriterion("pay_other_penalty <>", value, "payOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOtherPenaltyGreaterThan(Long value) {
            addCriterion("pay_other_penalty >", value, "payOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOtherPenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_other_penalty >=", value, "payOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOtherPenaltyLessThan(Long value) {
            addCriterion("pay_other_penalty <", value, "payOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOtherPenaltyLessThanOrEqualTo(Long value) {
            addCriterion("pay_other_penalty <=", value, "payOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOtherPenaltyIn(List<Long> values) {
            addCriterion("pay_other_penalty in", values, "payOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOtherPenaltyNotIn(List<Long> values) {
            addCriterion("pay_other_penalty not in", values, "payOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOtherPenaltyBetween(Long value1, Long value2) {
            addCriterion("pay_other_penalty between", value1, value2, "payOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPayOtherPenaltyNotBetween(Long value1, Long value2) {
            addCriterion("pay_other_penalty not between", value1, value2, "payOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPayExpenseIsNull() {
            addCriterion("pay_expense is null");
            return (Criteria) this;
        }

        public Criteria andPayExpenseIsNotNull() {
            addCriterion("pay_expense is not null");
            return (Criteria) this;
        }

        public Criteria andPayExpenseEqualTo(Long value) {
            addCriterion("pay_expense =", value, "payExpense");
            return (Criteria) this;
        }

        public Criteria andPayExpenseNotEqualTo(Long value) {
            addCriterion("pay_expense <>", value, "payExpense");
            return (Criteria) this;
        }

        public Criteria andPayExpenseGreaterThan(Long value) {
            addCriterion("pay_expense >", value, "payExpense");
            return (Criteria) this;
        }

        public Criteria andPayExpenseGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_expense >=", value, "payExpense");
            return (Criteria) this;
        }

        public Criteria andPayExpenseLessThan(Long value) {
            addCriterion("pay_expense <", value, "payExpense");
            return (Criteria) this;
        }

        public Criteria andPayExpenseLessThanOrEqualTo(Long value) {
            addCriterion("pay_expense <=", value, "payExpense");
            return (Criteria) this;
        }

        public Criteria andPayExpenseIn(List<Long> values) {
            addCriterion("pay_expense in", values, "payExpense");
            return (Criteria) this;
        }

        public Criteria andPayExpenseNotIn(List<Long> values) {
            addCriterion("pay_expense not in", values, "payExpense");
            return (Criteria) this;
        }

        public Criteria andPayExpenseBetween(Long value1, Long value2) {
            addCriterion("pay_expense between", value1, value2, "payExpense");
            return (Criteria) this;
        }

        public Criteria andPayExpenseNotBetween(Long value1, Long value2) {
            addCriterion("pay_expense not between", value1, value2, "payExpense");
            return (Criteria) this;
        }

        public Criteria andRepaymentSingleCollectIdIsNull() {
            addCriterion("repayment_single_collect_id is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentSingleCollectIdIsNotNull() {
            addCriterion("repayment_single_collect_id is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentSingleCollectIdEqualTo(String value) {
            addCriterion("repayment_single_collect_id =", value, "repaymentSingleCollectId");
            return (Criteria) this;
        }

        public Criteria andRepaymentSingleCollectIdNotEqualTo(String value) {
            addCriterion("repayment_single_collect_id <>", value, "repaymentSingleCollectId");
            return (Criteria) this;
        }

        public Criteria andRepaymentSingleCollectIdGreaterThan(String value) {
            addCriterion("repayment_single_collect_id >", value, "repaymentSingleCollectId");
            return (Criteria) this;
        }

        public Criteria andRepaymentSingleCollectIdGreaterThanOrEqualTo(String value) {
            addCriterion("repayment_single_collect_id >=", value, "repaymentSingleCollectId");
            return (Criteria) this;
        }

        public Criteria andRepaymentSingleCollectIdLessThan(String value) {
            addCriterion("repayment_single_collect_id <", value, "repaymentSingleCollectId");
            return (Criteria) this;
        }

        public Criteria andRepaymentSingleCollectIdLessThanOrEqualTo(String value) {
            addCriterion("repayment_single_collect_id <=", value, "repaymentSingleCollectId");
            return (Criteria) this;
        }

        public Criteria andRepaymentSingleCollectIdLike(String value) {
            addCriterion("repayment_single_collect_id like", value, "repaymentSingleCollectId");
            return (Criteria) this;
        }

        public Criteria andRepaymentSingleCollectIdNotLike(String value) {
            addCriterion("repayment_single_collect_id not like", value, "repaymentSingleCollectId");
            return (Criteria) this;
        }

        public Criteria andRepaymentSingleCollectIdIn(List<String> values) {
            addCriterion("repayment_single_collect_id in", values, "repaymentSingleCollectId");
            return (Criteria) this;
        }

        public Criteria andRepaymentSingleCollectIdNotIn(List<String> values) {
            addCriterion("repayment_single_collect_id not in", values, "repaymentSingleCollectId");
            return (Criteria) this;
        }

        public Criteria andRepaymentSingleCollectIdBetween(String value1, String value2) {
            addCriterion("repayment_single_collect_id between", value1, value2, "repaymentSingleCollectId");
            return (Criteria) this;
        }

        public Criteria andRepaymentSingleCollectIdNotBetween(String value1, String value2) {
            addCriterion("repayment_single_collect_id not between", value1, value2, "repaymentSingleCollectId");
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