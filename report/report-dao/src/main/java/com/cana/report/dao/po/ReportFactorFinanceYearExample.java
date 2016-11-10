package com.cana.report.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportFactorFinanceYearExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public ReportFactorFinanceYearExample() {
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

        public Criteria andOwnerIdIsNull() {
            addCriterion("owner_id is null");
            return (Criteria) this;
        }

        public Criteria andOwnerIdIsNotNull() {
            addCriterion("owner_id is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerIdEqualTo(String value) {
            addCriterion("owner_id =", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdNotEqualTo(String value) {
            addCriterion("owner_id <>", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdGreaterThan(String value) {
            addCriterion("owner_id >", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdGreaterThanOrEqualTo(String value) {
            addCriterion("owner_id >=", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdLessThan(String value) {
            addCriterion("owner_id <", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdLessThanOrEqualTo(String value) {
            addCriterion("owner_id <=", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdLike(String value) {
            addCriterion("owner_id like", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdNotLike(String value) {
            addCriterion("owner_id not like", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdIn(List<String> values) {
            addCriterion("owner_id in", values, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdNotIn(List<String> values) {
            addCriterion("owner_id not in", values, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdBetween(String value1, String value2) {
            addCriterion("owner_id between", value1, value2, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdNotBetween(String value1, String value2) {
            addCriterion("owner_id not between", value1, value2, "ownerId");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNull() {
            addCriterion("report_date is null");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNotNull() {
            addCriterion("report_date is not null");
            return (Criteria) this;
        }

        public Criteria andReportDateEqualTo(String value) {
            addCriterion("report_date =", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotEqualTo(String value) {
            addCriterion("report_date <>", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThan(String value) {
            addCriterion("report_date >", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThanOrEqualTo(String value) {
            addCriterion("report_date >=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThan(String value) {
            addCriterion("report_date <", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThanOrEqualTo(String value) {
            addCriterion("report_date <=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLike(String value) {
            addCriterion("report_date like", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotLike(String value) {
            addCriterion("report_date not like", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateIn(List<String> values) {
            addCriterion("report_date in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotIn(List<String> values) {
            addCriterion("report_date not in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateBetween(String value1, String value2) {
            addCriterion("report_date between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotBetween(String value1, String value2) {
            addCriterion("report_date not between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNull() {
            addCriterion("user_type is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("user_type is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(String value) {
            addCriterion("user_type =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(String value) {
            addCriterion("user_type <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(String value) {
            addCriterion("user_type >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(String value) {
            addCriterion("user_type >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(String value) {
            addCriterion("user_type <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(String value) {
            addCriterion("user_type <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLike(String value) {
            addCriterion("user_type like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotLike(String value) {
            addCriterion("user_type not like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<String> values) {
            addCriterion("user_type in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<String> values) {
            addCriterion("user_type not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(String value1, String value2) {
            addCriterion("user_type between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(String value1, String value2) {
            addCriterion("user_type not between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdIsNull() {
            addCriterion("business_product_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdIsNotNull() {
            addCriterion("business_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdEqualTo(String value) {
            addCriterion("business_product_id =", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdNotEqualTo(String value) {
            addCriterion("business_product_id <>", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdGreaterThan(String value) {
            addCriterion("business_product_id >", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("business_product_id >=", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdLessThan(String value) {
            addCriterion("business_product_id <", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdLessThanOrEqualTo(String value) {
            addCriterion("business_product_id <=", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdLike(String value) {
            addCriterion("business_product_id like", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdNotLike(String value) {
            addCriterion("business_product_id not like", value, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdIn(List<String> values) {
            addCriterion("business_product_id in", values, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdNotIn(List<String> values) {
            addCriterion("business_product_id not in", values, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdBetween(String value1, String value2) {
            addCriterion("business_product_id between", value1, value2, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andBusinessProductIdNotBetween(String value1, String value2) {
            addCriterion("business_product_id not between", value1, value2, "businessProductId");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceIsNull() {
            addCriterion("finance_balance is null");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceIsNotNull() {
            addCriterion("finance_balance is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceEqualTo(Long value) {
            addCriterion("finance_balance =", value, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceNotEqualTo(Long value) {
            addCriterion("finance_balance <>", value, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceGreaterThan(Long value) {
            addCriterion("finance_balance >", value, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("finance_balance >=", value, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceLessThan(Long value) {
            addCriterion("finance_balance <", value, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceLessThanOrEqualTo(Long value) {
            addCriterion("finance_balance <=", value, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceIn(List<Long> values) {
            addCriterion("finance_balance in", values, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceNotIn(List<Long> values) {
            addCriterion("finance_balance not in", values, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceBetween(Long value1, Long value2) {
            addCriterion("finance_balance between", value1, value2, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceBalanceNotBetween(Long value1, Long value2) {
            addCriterion("finance_balance not between", value1, value2, "financeBalance");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountIsNull() {
            addCriterion("finance_amount is null");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountIsNotNull() {
            addCriterion("finance_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountEqualTo(Long value) {
            addCriterion("finance_amount =", value, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountNotEqualTo(Long value) {
            addCriterion("finance_amount <>", value, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountGreaterThan(Long value) {
            addCriterion("finance_amount >", value, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("finance_amount >=", value, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountLessThan(Long value) {
            addCriterion("finance_amount <", value, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountLessThanOrEqualTo(Long value) {
            addCriterion("finance_amount <=", value, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountIn(List<Long> values) {
            addCriterion("finance_amount in", values, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountNotIn(List<Long> values) {
            addCriterion("finance_amount not in", values, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountBetween(Long value1, Long value2) {
            addCriterion("finance_amount between", value1, value2, "financeAmount");
            return (Criteria) this;
        }

        public Criteria andFinanceAmountNotBetween(Long value1, Long value2) {
            addCriterion("finance_amount not between", value1, value2, "financeAmount");
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

        public Criteria andAccountExtensionChargeIsNull() {
            addCriterion("account_extension_charge is null");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeIsNotNull() {
            addCriterion("account_extension_charge is not null");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeEqualTo(Long value) {
            addCriterion("account_extension_charge =", value, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeNotEqualTo(Long value) {
            addCriterion("account_extension_charge <>", value, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeGreaterThan(Long value) {
            addCriterion("account_extension_charge >", value, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("account_extension_charge >=", value, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeLessThan(Long value) {
            addCriterion("account_extension_charge <", value, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeLessThanOrEqualTo(Long value) {
            addCriterion("account_extension_charge <=", value, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeIn(List<Long> values) {
            addCriterion("account_extension_charge in", values, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeNotIn(List<Long> values) {
            addCriterion("account_extension_charge not in", values, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeBetween(Long value1, Long value2) {
            addCriterion("account_extension_charge between", value1, value2, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExtensionChargeNotBetween(Long value1, Long value2) {
            addCriterion("account_extension_charge not between", value1, value2, "accountExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountExpenseIsNull() {
            addCriterion("account_expense is null");
            return (Criteria) this;
        }

        public Criteria andAccountExpenseIsNotNull() {
            addCriterion("account_expense is not null");
            return (Criteria) this;
        }

        public Criteria andAccountExpenseEqualTo(Long value) {
            addCriterion("account_expense =", value, "accountExpense");
            return (Criteria) this;
        }

        public Criteria andAccountExpenseNotEqualTo(Long value) {
            addCriterion("account_expense <>", value, "accountExpense");
            return (Criteria) this;
        }

        public Criteria andAccountExpenseGreaterThan(Long value) {
            addCriterion("account_expense >", value, "accountExpense");
            return (Criteria) this;
        }

        public Criteria andAccountExpenseGreaterThanOrEqualTo(Long value) {
            addCriterion("account_expense >=", value, "accountExpense");
            return (Criteria) this;
        }

        public Criteria andAccountExpenseLessThan(Long value) {
            addCriterion("account_expense <", value, "accountExpense");
            return (Criteria) this;
        }

        public Criteria andAccountExpenseLessThanOrEqualTo(Long value) {
            addCriterion("account_expense <=", value, "accountExpense");
            return (Criteria) this;
        }

        public Criteria andAccountExpenseIn(List<Long> values) {
            addCriterion("account_expense in", values, "accountExpense");
            return (Criteria) this;
        }

        public Criteria andAccountExpenseNotIn(List<Long> values) {
            addCriterion("account_expense not in", values, "accountExpense");
            return (Criteria) this;
        }

        public Criteria andAccountExpenseBetween(Long value1, Long value2) {
            addCriterion("account_expense between", value1, value2, "accountExpense");
            return (Criteria) this;
        }

        public Criteria andAccountExpenseNotBetween(Long value1, Long value2) {
            addCriterion("account_expense not between", value1, value2, "accountExpense");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalIsNull() {
            addCriterion("account_overdue_principal is null");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalIsNotNull() {
            addCriterion("account_overdue_principal is not null");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalEqualTo(Long value) {
            addCriterion("account_overdue_principal =", value, "accountOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalNotEqualTo(Long value) {
            addCriterion("account_overdue_principal <>", value, "accountOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalGreaterThan(Long value) {
            addCriterion("account_overdue_principal >", value, "accountOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalGreaterThanOrEqualTo(Long value) {
            addCriterion("account_overdue_principal >=", value, "accountOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalLessThan(Long value) {
            addCriterion("account_overdue_principal <", value, "accountOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalLessThanOrEqualTo(Long value) {
            addCriterion("account_overdue_principal <=", value, "accountOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalIn(List<Long> values) {
            addCriterion("account_overdue_principal in", values, "accountOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalNotIn(List<Long> values) {
            addCriterion("account_overdue_principal not in", values, "accountOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalBetween(Long value1, Long value2) {
            addCriterion("account_overdue_principal between", value1, value2, "accountOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalNotBetween(Long value1, Long value2) {
            addCriterion("account_overdue_principal not between", value1, value2, "accountOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestIsNull() {
            addCriterion("account_overdue_interest is null");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestIsNotNull() {
            addCriterion("account_overdue_interest is not null");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestEqualTo(Long value) {
            addCriterion("account_overdue_interest =", value, "accountOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestNotEqualTo(Long value) {
            addCriterion("account_overdue_interest <>", value, "accountOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestGreaterThan(Long value) {
            addCriterion("account_overdue_interest >", value, "accountOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestGreaterThanOrEqualTo(Long value) {
            addCriterion("account_overdue_interest >=", value, "accountOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestLessThan(Long value) {
            addCriterion("account_overdue_interest <", value, "accountOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestLessThanOrEqualTo(Long value) {
            addCriterion("account_overdue_interest <=", value, "accountOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestIn(List<Long> values) {
            addCriterion("account_overdue_interest in", values, "accountOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestNotIn(List<Long> values) {
            addCriterion("account_overdue_interest not in", values, "accountOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestBetween(Long value1, Long value2) {
            addCriterion("account_overdue_interest between", value1, value2, "accountOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestNotBetween(Long value1, Long value2) {
            addCriterion("account_overdue_interest not between", value1, value2, "accountOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargeIsNull() {
            addCriterion("account_overdue_service_charge is null");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargeIsNotNull() {
            addCriterion("account_overdue_service_charge is not null");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargeEqualTo(Long value) {
            addCriterion("account_overdue_service_charge =", value, "accountOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargeNotEqualTo(Long value) {
            addCriterion("account_overdue_service_charge <>", value, "accountOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargeGreaterThan(Long value) {
            addCriterion("account_overdue_service_charge >", value, "accountOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("account_overdue_service_charge >=", value, "accountOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargeLessThan(Long value) {
            addCriterion("account_overdue_service_charge <", value, "accountOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargeLessThanOrEqualTo(Long value) {
            addCriterion("account_overdue_service_charge <=", value, "accountOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargeIn(List<Long> values) {
            addCriterion("account_overdue_service_charge in", values, "accountOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargeNotIn(List<Long> values) {
            addCriterion("account_overdue_service_charge not in", values, "accountOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargeBetween(Long value1, Long value2) {
            addCriterion("account_overdue_service_charge between", value1, value2, "accountOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargeNotBetween(Long value1, Long value2) {
            addCriterion("account_overdue_service_charge not between", value1, value2, "accountOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueIsNull() {
            addCriterion("account_overdue is null");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueIsNotNull() {
            addCriterion("account_overdue is not null");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueEqualTo(Long value) {
            addCriterion("account_overdue =", value, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueNotEqualTo(Long value) {
            addCriterion("account_overdue <>", value, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueGreaterThan(Long value) {
            addCriterion("account_overdue >", value, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueGreaterThanOrEqualTo(Long value) {
            addCriterion("account_overdue >=", value, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueLessThan(Long value) {
            addCriterion("account_overdue <", value, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueLessThanOrEqualTo(Long value) {
            addCriterion("account_overdue <=", value, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueIn(List<Long> values) {
            addCriterion("account_overdue in", values, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueNotIn(List<Long> values) {
            addCriterion("account_overdue not in", values, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueBetween(Long value1, Long value2) {
            addCriterion("account_overdue between", value1, value2, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueNotBetween(Long value1, Long value2) {
            addCriterion("account_overdue not between", value1, value2, "accountOverdue");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueExtensionChargeIsNull() {
            addCriterion("account_overdue_extension_charge is null");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueExtensionChargeIsNotNull() {
            addCriterion("account_overdue_extension_charge is not null");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueExtensionChargeEqualTo(Long value) {
            addCriterion("account_overdue_extension_charge =", value, "accountOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueExtensionChargeNotEqualTo(Long value) {
            addCriterion("account_overdue_extension_charge <>", value, "accountOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueExtensionChargeGreaterThan(Long value) {
            addCriterion("account_overdue_extension_charge >", value, "accountOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueExtensionChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("account_overdue_extension_charge >=", value, "accountOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueExtensionChargeLessThan(Long value) {
            addCriterion("account_overdue_extension_charge <", value, "accountOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueExtensionChargeLessThanOrEqualTo(Long value) {
            addCriterion("account_overdue_extension_charge <=", value, "accountOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueExtensionChargeIn(List<Long> values) {
            addCriterion("account_overdue_extension_charge in", values, "accountOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueExtensionChargeNotIn(List<Long> values) {
            addCriterion("account_overdue_extension_charge not in", values, "accountOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueExtensionChargeBetween(Long value1, Long value2) {
            addCriterion("account_overdue_extension_charge between", value1, value2, "accountOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueExtensionChargeNotBetween(Long value1, Long value2) {
            addCriterion("account_overdue_extension_charge not between", value1, value2, "accountOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalPenaltyIsNull() {
            addCriterion("account_overdue_principal_penalty is null");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalPenaltyIsNotNull() {
            addCriterion("account_overdue_principal_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalPenaltyEqualTo(Long value) {
            addCriterion("account_overdue_principal_penalty =", value, "accountOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalPenaltyNotEqualTo(Long value) {
            addCriterion("account_overdue_principal_penalty <>", value, "accountOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalPenaltyGreaterThan(Long value) {
            addCriterion("account_overdue_principal_penalty >", value, "accountOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalPenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("account_overdue_principal_penalty >=", value, "accountOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalPenaltyLessThan(Long value) {
            addCriterion("account_overdue_principal_penalty <", value, "accountOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalPenaltyLessThanOrEqualTo(Long value) {
            addCriterion("account_overdue_principal_penalty <=", value, "accountOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalPenaltyIn(List<Long> values) {
            addCriterion("account_overdue_principal_penalty in", values, "accountOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalPenaltyNotIn(List<Long> values) {
            addCriterion("account_overdue_principal_penalty not in", values, "accountOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalPenaltyBetween(Long value1, Long value2) {
            addCriterion("account_overdue_principal_penalty between", value1, value2, "accountOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverduePrincipalPenaltyNotBetween(Long value1, Long value2) {
            addCriterion("account_overdue_principal_penalty not between", value1, value2, "accountOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestPenaltyIsNull() {
            addCriterion("account_overdue_interest_penalty is null");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestPenaltyIsNotNull() {
            addCriterion("account_overdue_interest_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestPenaltyEqualTo(Long value) {
            addCriterion("account_overdue_interest_penalty =", value, "accountOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestPenaltyNotEqualTo(Long value) {
            addCriterion("account_overdue_interest_penalty <>", value, "accountOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestPenaltyGreaterThan(Long value) {
            addCriterion("account_overdue_interest_penalty >", value, "accountOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestPenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("account_overdue_interest_penalty >=", value, "accountOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestPenaltyLessThan(Long value) {
            addCriterion("account_overdue_interest_penalty <", value, "accountOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestPenaltyLessThanOrEqualTo(Long value) {
            addCriterion("account_overdue_interest_penalty <=", value, "accountOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestPenaltyIn(List<Long> values) {
            addCriterion("account_overdue_interest_penalty in", values, "accountOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestPenaltyNotIn(List<Long> values) {
            addCriterion("account_overdue_interest_penalty not in", values, "accountOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestPenaltyBetween(Long value1, Long value2) {
            addCriterion("account_overdue_interest_penalty between", value1, value2, "accountOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueInterestPenaltyNotBetween(Long value1, Long value2) {
            addCriterion("account_overdue_interest_penalty not between", value1, value2, "accountOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargePenaltyIsNull() {
            addCriterion("account_overdue_service_charge_penalty is null");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargePenaltyIsNotNull() {
            addCriterion("account_overdue_service_charge_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargePenaltyEqualTo(Long value) {
            addCriterion("account_overdue_service_charge_penalty =", value, "accountOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargePenaltyNotEqualTo(Long value) {
            addCriterion("account_overdue_service_charge_penalty <>", value, "accountOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargePenaltyGreaterThan(Long value) {
            addCriterion("account_overdue_service_charge_penalty >", value, "accountOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargePenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("account_overdue_service_charge_penalty >=", value, "accountOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargePenaltyLessThan(Long value) {
            addCriterion("account_overdue_service_charge_penalty <", value, "accountOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargePenaltyLessThanOrEqualTo(Long value) {
            addCriterion("account_overdue_service_charge_penalty <=", value, "accountOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargePenaltyIn(List<Long> values) {
            addCriterion("account_overdue_service_charge_penalty in", values, "accountOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargePenaltyNotIn(List<Long> values) {
            addCriterion("account_overdue_service_charge_penalty not in", values, "accountOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargePenaltyBetween(Long value1, Long value2) {
            addCriterion("account_overdue_service_charge_penalty between", value1, value2, "accountOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOverdueServiceChargePenaltyNotBetween(Long value1, Long value2) {
            addCriterion("account_overdue_service_charge_penalty not between", value1, value2, "accountOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOtherPenaltyIsNull() {
            addCriterion("account_other_penalty is null");
            return (Criteria) this;
        }

        public Criteria andAccountOtherPenaltyIsNotNull() {
            addCriterion("account_other_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andAccountOtherPenaltyEqualTo(Long value) {
            addCriterion("account_other_penalty =", value, "accountOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOtherPenaltyNotEqualTo(Long value) {
            addCriterion("account_other_penalty <>", value, "accountOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOtherPenaltyGreaterThan(Long value) {
            addCriterion("account_other_penalty >", value, "accountOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOtherPenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("account_other_penalty >=", value, "accountOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOtherPenaltyLessThan(Long value) {
            addCriterion("account_other_penalty <", value, "accountOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOtherPenaltyLessThanOrEqualTo(Long value) {
            addCriterion("account_other_penalty <=", value, "accountOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOtherPenaltyIn(List<Long> values) {
            addCriterion("account_other_penalty in", values, "accountOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOtherPenaltyNotIn(List<Long> values) {
            addCriterion("account_other_penalty not in", values, "accountOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOtherPenaltyBetween(Long value1, Long value2) {
            addCriterion("account_other_penalty between", value1, value2, "accountOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountOtherPenaltyNotBetween(Long value1, Long value2) {
            addCriterion("account_other_penalty not between", value1, value2, "accountOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andAccountChargeIsNull() {
            addCriterion("account_charge is null");
            return (Criteria) this;
        }

        public Criteria andAccountChargeIsNotNull() {
            addCriterion("account_charge is not null");
            return (Criteria) this;
        }

        public Criteria andAccountChargeEqualTo(Long value) {
            addCriterion("account_charge =", value, "accountCharge");
            return (Criteria) this;
        }

        public Criteria andAccountChargeNotEqualTo(Long value) {
            addCriterion("account_charge <>", value, "accountCharge");
            return (Criteria) this;
        }

        public Criteria andAccountChargeGreaterThan(Long value) {
            addCriterion("account_charge >", value, "accountCharge");
            return (Criteria) this;
        }

        public Criteria andAccountChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("account_charge >=", value, "accountCharge");
            return (Criteria) this;
        }

        public Criteria andAccountChargeLessThan(Long value) {
            addCriterion("account_charge <", value, "accountCharge");
            return (Criteria) this;
        }

        public Criteria andAccountChargeLessThanOrEqualTo(Long value) {
            addCriterion("account_charge <=", value, "accountCharge");
            return (Criteria) this;
        }

        public Criteria andAccountChargeIn(List<Long> values) {
            addCriterion("account_charge in", values, "accountCharge");
            return (Criteria) this;
        }

        public Criteria andAccountChargeNotIn(List<Long> values) {
            addCriterion("account_charge not in", values, "accountCharge");
            return (Criteria) this;
        }

        public Criteria andAccountChargeBetween(Long value1, Long value2) {
            addCriterion("account_charge between", value1, value2, "accountCharge");
            return (Criteria) this;
        }

        public Criteria andAccountChargeNotBetween(Long value1, Long value2) {
            addCriterion("account_charge not between", value1, value2, "accountCharge");
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

        public Criteria andPaidServiceChargeIsNull() {
            addCriterion("paid_service_charge is null");
            return (Criteria) this;
        }

        public Criteria andPaidServiceChargeIsNotNull() {
            addCriterion("paid_service_charge is not null");
            return (Criteria) this;
        }

        public Criteria andPaidServiceChargeEqualTo(Long value) {
            addCriterion("paid_service_charge =", value, "paidServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidServiceChargeNotEqualTo(Long value) {
            addCriterion("paid_service_charge <>", value, "paidServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidServiceChargeGreaterThan(Long value) {
            addCriterion("paid_service_charge >", value, "paidServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidServiceChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_service_charge >=", value, "paidServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidServiceChargeLessThan(Long value) {
            addCriterion("paid_service_charge <", value, "paidServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidServiceChargeLessThanOrEqualTo(Long value) {
            addCriterion("paid_service_charge <=", value, "paidServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidServiceChargeIn(List<Long> values) {
            addCriterion("paid_service_charge in", values, "paidServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidServiceChargeNotIn(List<Long> values) {
            addCriterion("paid_service_charge not in", values, "paidServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidServiceChargeBetween(Long value1, Long value2) {
            addCriterion("paid_service_charge between", value1, value2, "paidServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidServiceChargeNotBetween(Long value1, Long value2) {
            addCriterion("paid_service_charge not between", value1, value2, "paidServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeIsNull() {
            addCriterion("paid_extension_charge is null");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeIsNotNull() {
            addCriterion("paid_extension_charge is not null");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeEqualTo(Long value) {
            addCriterion("paid_extension_charge =", value, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeNotEqualTo(Long value) {
            addCriterion("paid_extension_charge <>", value, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeGreaterThan(Long value) {
            addCriterion("paid_extension_charge >", value, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_extension_charge >=", value, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeLessThan(Long value) {
            addCriterion("paid_extension_charge <", value, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeLessThanOrEqualTo(Long value) {
            addCriterion("paid_extension_charge <=", value, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeIn(List<Long> values) {
            addCriterion("paid_extension_charge in", values, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeNotIn(List<Long> values) {
            addCriterion("paid_extension_charge not in", values, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeBetween(Long value1, Long value2) {
            addCriterion("paid_extension_charge between", value1, value2, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExtensionChargeNotBetween(Long value1, Long value2) {
            addCriterion("paid_extension_charge not between", value1, value2, "paidExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeIsNull() {
            addCriterion("paid_early_repayment_charge is null");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeIsNotNull() {
            addCriterion("paid_early_repayment_charge is not null");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeEqualTo(Long value) {
            addCriterion("paid_early_repayment_charge =", value, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeNotEqualTo(Long value) {
            addCriterion("paid_early_repayment_charge <>", value, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeGreaterThan(Long value) {
            addCriterion("paid_early_repayment_charge >", value, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_early_repayment_charge >=", value, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeLessThan(Long value) {
            addCriterion("paid_early_repayment_charge <", value, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeLessThanOrEqualTo(Long value) {
            addCriterion("paid_early_repayment_charge <=", value, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeIn(List<Long> values) {
            addCriterion("paid_early_repayment_charge in", values, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeNotIn(List<Long> values) {
            addCriterion("paid_early_repayment_charge not in", values, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeBetween(Long value1, Long value2) {
            addCriterion("paid_early_repayment_charge between", value1, value2, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidEarlyRepaymentChargeNotBetween(Long value1, Long value2) {
            addCriterion("paid_early_repayment_charge not between", value1, value2, "paidEarlyRepaymentCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyIsNull() {
            addCriterion("paid_overdue_principal_penalty is null");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyIsNotNull() {
            addCriterion("paid_overdue_principal_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyEqualTo(Long value) {
            addCriterion("paid_overdue_principal_penalty =", value, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyNotEqualTo(Long value) {
            addCriterion("paid_overdue_principal_penalty <>", value, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyGreaterThan(Long value) {
            addCriterion("paid_overdue_principal_penalty >", value, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_principal_penalty >=", value, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyLessThan(Long value) {
            addCriterion("paid_overdue_principal_penalty <", value, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyLessThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_principal_penalty <=", value, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyIn(List<Long> values) {
            addCriterion("paid_overdue_principal_penalty in", values, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyNotIn(List<Long> values) {
            addCriterion("paid_overdue_principal_penalty not in", values, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_principal_penalty between", value1, value2, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalPenaltyNotBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_principal_penalty not between", value1, value2, "paidOverduePrincipalPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyIsNull() {
            addCriterion("paid_overdue_interest_penalty is null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyIsNotNull() {
            addCriterion("paid_overdue_interest_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyEqualTo(Long value) {
            addCriterion("paid_overdue_interest_penalty =", value, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyNotEqualTo(Long value) {
            addCriterion("paid_overdue_interest_penalty <>", value, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyGreaterThan(Long value) {
            addCriterion("paid_overdue_interest_penalty >", value, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_interest_penalty >=", value, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyLessThan(Long value) {
            addCriterion("paid_overdue_interest_penalty <", value, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyLessThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_interest_penalty <=", value, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyIn(List<Long> values) {
            addCriterion("paid_overdue_interest_penalty in", values, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyNotIn(List<Long> values) {
            addCriterion("paid_overdue_interest_penalty not in", values, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_interest_penalty between", value1, value2, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestPenaltyNotBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_interest_penalty not between", value1, value2, "paidOverdueInterestPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyIsNull() {
            addCriterion("paid_overdue_service_charge_penalty is null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyIsNotNull() {
            addCriterion("paid_overdue_service_charge_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyEqualTo(Long value) {
            addCriterion("paid_overdue_service_charge_penalty =", value, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyNotEqualTo(Long value) {
            addCriterion("paid_overdue_service_charge_penalty <>", value, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyGreaterThan(Long value) {
            addCriterion("paid_overdue_service_charge_penalty >", value, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_service_charge_penalty >=", value, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyLessThan(Long value) {
            addCriterion("paid_overdue_service_charge_penalty <", value, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyLessThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_service_charge_penalty <=", value, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyIn(List<Long> values) {
            addCriterion("paid_overdue_service_charge_penalty in", values, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyNotIn(List<Long> values) {
            addCriterion("paid_overdue_service_charge_penalty not in", values, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_service_charge_penalty between", value1, value2, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargePenaltyNotBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_service_charge_penalty not between", value1, value2, "paidOverdueServiceChargePenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyIsNull() {
            addCriterion("paid_other_penalty is null");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyIsNotNull() {
            addCriterion("paid_other_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyEqualTo(Long value) {
            addCriterion("paid_other_penalty =", value, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyNotEqualTo(Long value) {
            addCriterion("paid_other_penalty <>", value, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyGreaterThan(Long value) {
            addCriterion("paid_other_penalty >", value, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_other_penalty >=", value, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyLessThan(Long value) {
            addCriterion("paid_other_penalty <", value, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyLessThanOrEqualTo(Long value) {
            addCriterion("paid_other_penalty <=", value, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyIn(List<Long> values) {
            addCriterion("paid_other_penalty in", values, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyNotIn(List<Long> values) {
            addCriterion("paid_other_penalty not in", values, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyBetween(Long value1, Long value2) {
            addCriterion("paid_other_penalty between", value1, value2, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidOtherPenaltyNotBetween(Long value1, Long value2) {
            addCriterion("paid_other_penalty not between", value1, value2, "paidOtherPenalty");
            return (Criteria) this;
        }

        public Criteria andPaidChargeIsNull() {
            addCriterion("paid_charge is null");
            return (Criteria) this;
        }

        public Criteria andPaidChargeIsNotNull() {
            addCriterion("paid_charge is not null");
            return (Criteria) this;
        }

        public Criteria andPaidChargeEqualTo(Long value) {
            addCriterion("paid_charge =", value, "paidCharge");
            return (Criteria) this;
        }

        public Criteria andPaidChargeNotEqualTo(Long value) {
            addCriterion("paid_charge <>", value, "paidCharge");
            return (Criteria) this;
        }

        public Criteria andPaidChargeGreaterThan(Long value) {
            addCriterion("paid_charge >", value, "paidCharge");
            return (Criteria) this;
        }

        public Criteria andPaidChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_charge >=", value, "paidCharge");
            return (Criteria) this;
        }

        public Criteria andPaidChargeLessThan(Long value) {
            addCriterion("paid_charge <", value, "paidCharge");
            return (Criteria) this;
        }

        public Criteria andPaidChargeLessThanOrEqualTo(Long value) {
            addCriterion("paid_charge <=", value, "paidCharge");
            return (Criteria) this;
        }

        public Criteria andPaidChargeIn(List<Long> values) {
            addCriterion("paid_charge in", values, "paidCharge");
            return (Criteria) this;
        }

        public Criteria andPaidChargeNotIn(List<Long> values) {
            addCriterion("paid_charge not in", values, "paidCharge");
            return (Criteria) this;
        }

        public Criteria andPaidChargeBetween(Long value1, Long value2) {
            addCriterion("paid_charge between", value1, value2, "paidCharge");
            return (Criteria) this;
        }

        public Criteria andPaidChargeNotBetween(Long value1, Long value2) {
            addCriterion("paid_charge not between", value1, value2, "paidCharge");
            return (Criteria) this;
        }

        public Criteria andPaidExpenseIsNull() {
            addCriterion("paid_expense is null");
            return (Criteria) this;
        }

        public Criteria andPaidExpenseIsNotNull() {
            addCriterion("paid_expense is not null");
            return (Criteria) this;
        }

        public Criteria andPaidExpenseEqualTo(Long value) {
            addCriterion("paid_expense =", value, "paidExpense");
            return (Criteria) this;
        }

        public Criteria andPaidExpenseNotEqualTo(Long value) {
            addCriterion("paid_expense <>", value, "paidExpense");
            return (Criteria) this;
        }

        public Criteria andPaidExpenseGreaterThan(Long value) {
            addCriterion("paid_expense >", value, "paidExpense");
            return (Criteria) this;
        }

        public Criteria andPaidExpenseGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_expense >=", value, "paidExpense");
            return (Criteria) this;
        }

        public Criteria andPaidExpenseLessThan(Long value) {
            addCriterion("paid_expense <", value, "paidExpense");
            return (Criteria) this;
        }

        public Criteria andPaidExpenseLessThanOrEqualTo(Long value) {
            addCriterion("paid_expense <=", value, "paidExpense");
            return (Criteria) this;
        }

        public Criteria andPaidExpenseIn(List<Long> values) {
            addCriterion("paid_expense in", values, "paidExpense");
            return (Criteria) this;
        }

        public Criteria andPaidExpenseNotIn(List<Long> values) {
            addCriterion("paid_expense not in", values, "paidExpense");
            return (Criteria) this;
        }

        public Criteria andPaidExpenseBetween(Long value1, Long value2) {
            addCriterion("paid_expense between", value1, value2, "paidExpense");
            return (Criteria) this;
        }

        public Criteria andPaidExpenseNotBetween(Long value1, Long value2) {
            addCriterion("paid_expense not between", value1, value2, "paidExpense");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalIsNull() {
            addCriterion("paid_overdue_principal is null");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalIsNotNull() {
            addCriterion("paid_overdue_principal is not null");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalEqualTo(Long value) {
            addCriterion("paid_overdue_principal =", value, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalNotEqualTo(Long value) {
            addCriterion("paid_overdue_principal <>", value, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalGreaterThan(Long value) {
            addCriterion("paid_overdue_principal >", value, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_principal >=", value, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalLessThan(Long value) {
            addCriterion("paid_overdue_principal <", value, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalLessThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_principal <=", value, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalIn(List<Long> values) {
            addCriterion("paid_overdue_principal in", values, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalNotIn(List<Long> values) {
            addCriterion("paid_overdue_principal not in", values, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_principal between", value1, value2, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverduePrincipalNotBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_principal not between", value1, value2, "paidOverduePrincipal");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestIsNull() {
            addCriterion("paid_overdue_interest is null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestIsNotNull() {
            addCriterion("paid_overdue_interest is not null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestEqualTo(Long value) {
            addCriterion("paid_overdue_interest =", value, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestNotEqualTo(Long value) {
            addCriterion("paid_overdue_interest <>", value, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestGreaterThan(Long value) {
            addCriterion("paid_overdue_interest >", value, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_interest >=", value, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestLessThan(Long value) {
            addCriterion("paid_overdue_interest <", value, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestLessThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_interest <=", value, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestIn(List<Long> values) {
            addCriterion("paid_overdue_interest in", values, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestNotIn(List<Long> values) {
            addCriterion("paid_overdue_interest not in", values, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_interest between", value1, value2, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueInterestNotBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_interest not between", value1, value2, "paidOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeIsNull() {
            addCriterion("paid_overdue_service_charge is null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeIsNotNull() {
            addCriterion("paid_overdue_service_charge is not null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeEqualTo(Long value) {
            addCriterion("paid_overdue_service_charge =", value, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeNotEqualTo(Long value) {
            addCriterion("paid_overdue_service_charge <>", value, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeGreaterThan(Long value) {
            addCriterion("paid_overdue_service_charge >", value, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_service_charge >=", value, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeLessThan(Long value) {
            addCriterion("paid_overdue_service_charge <", value, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeLessThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_service_charge <=", value, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeIn(List<Long> values) {
            addCriterion("paid_overdue_service_charge in", values, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeNotIn(List<Long> values) {
            addCriterion("paid_overdue_service_charge not in", values, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_service_charge between", value1, value2, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueServiceChargeNotBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_service_charge not between", value1, value2, "paidOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueExtensionChargeIsNull() {
            addCriterion("paid_overdue_extension_charge is null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueExtensionChargeIsNotNull() {
            addCriterion("paid_overdue_extension_charge is not null");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueExtensionChargeEqualTo(Long value) {
            addCriterion("paid_overdue_extension_charge =", value, "paidOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueExtensionChargeNotEqualTo(Long value) {
            addCriterion("paid_overdue_extension_charge <>", value, "paidOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueExtensionChargeGreaterThan(Long value) {
            addCriterion("paid_overdue_extension_charge >", value, "paidOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueExtensionChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_extension_charge >=", value, "paidOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueExtensionChargeLessThan(Long value) {
            addCriterion("paid_overdue_extension_charge <", value, "paidOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueExtensionChargeLessThanOrEqualTo(Long value) {
            addCriterion("paid_overdue_extension_charge <=", value, "paidOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueExtensionChargeIn(List<Long> values) {
            addCriterion("paid_overdue_extension_charge in", values, "paidOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueExtensionChargeNotIn(List<Long> values) {
            addCriterion("paid_overdue_extension_charge not in", values, "paidOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueExtensionChargeBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_extension_charge between", value1, value2, "paidOverdueExtensionCharge");
            return (Criteria) this;
        }

        public Criteria andPaidOverdueExtensionChargeNotBetween(Long value1, Long value2) {
            addCriterion("paid_overdue_extension_charge not between", value1, value2, "paidOverdueExtensionCharge");
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

        public Criteria andTotalOverdueIsNull() {
            addCriterion("total_overdue is null");
            return (Criteria) this;
        }

        public Criteria andTotalOverdueIsNotNull() {
            addCriterion("total_overdue is not null");
            return (Criteria) this;
        }

        public Criteria andTotalOverdueEqualTo(Long value) {
            addCriterion("total_overdue =", value, "totalOverdue");
            return (Criteria) this;
        }

        public Criteria andTotalOverdueNotEqualTo(Long value) {
            addCriterion("total_overdue <>", value, "totalOverdue");
            return (Criteria) this;
        }

        public Criteria andTotalOverdueGreaterThan(Long value) {
            addCriterion("total_overdue >", value, "totalOverdue");
            return (Criteria) this;
        }

        public Criteria andTotalOverdueGreaterThanOrEqualTo(Long value) {
            addCriterion("total_overdue >=", value, "totalOverdue");
            return (Criteria) this;
        }

        public Criteria andTotalOverdueLessThan(Long value) {
            addCriterion("total_overdue <", value, "totalOverdue");
            return (Criteria) this;
        }

        public Criteria andTotalOverdueLessThanOrEqualTo(Long value) {
            addCriterion("total_overdue <=", value, "totalOverdue");
            return (Criteria) this;
        }

        public Criteria andTotalOverdueIn(List<Long> values) {
            addCriterion("total_overdue in", values, "totalOverdue");
            return (Criteria) this;
        }

        public Criteria andTotalOverdueNotIn(List<Long> values) {
            addCriterion("total_overdue not in", values, "totalOverdue");
            return (Criteria) this;
        }

        public Criteria andTotalOverdueBetween(Long value1, Long value2) {
            addCriterion("total_overdue between", value1, value2, "totalOverdue");
            return (Criteria) this;
        }

        public Criteria andTotalOverdueNotBetween(Long value1, Long value2) {
            addCriterion("total_overdue not between", value1, value2, "totalOverdue");
            return (Criteria) this;
        }

        public Criteria andTotalExtensionIsNull() {
            addCriterion("total_extension is null");
            return (Criteria) this;
        }

        public Criteria andTotalExtensionIsNotNull() {
            addCriterion("total_extension is not null");
            return (Criteria) this;
        }

        public Criteria andTotalExtensionEqualTo(Long value) {
            addCriterion("total_extension =", value, "totalExtension");
            return (Criteria) this;
        }

        public Criteria andTotalExtensionNotEqualTo(Long value) {
            addCriterion("total_extension <>", value, "totalExtension");
            return (Criteria) this;
        }

        public Criteria andTotalExtensionGreaterThan(Long value) {
            addCriterion("total_extension >", value, "totalExtension");
            return (Criteria) this;
        }

        public Criteria andTotalExtensionGreaterThanOrEqualTo(Long value) {
            addCriterion("total_extension >=", value, "totalExtension");
            return (Criteria) this;
        }

        public Criteria andTotalExtensionLessThan(Long value) {
            addCriterion("total_extension <", value, "totalExtension");
            return (Criteria) this;
        }

        public Criteria andTotalExtensionLessThanOrEqualTo(Long value) {
            addCriterion("total_extension <=", value, "totalExtension");
            return (Criteria) this;
        }

        public Criteria andTotalExtensionIn(List<Long> values) {
            addCriterion("total_extension in", values, "totalExtension");
            return (Criteria) this;
        }

        public Criteria andTotalExtensionNotIn(List<Long> values) {
            addCriterion("total_extension not in", values, "totalExtension");
            return (Criteria) this;
        }

        public Criteria andTotalExtensionBetween(Long value1, Long value2) {
            addCriterion("total_extension between", value1, value2, "totalExtension");
            return (Criteria) this;
        }

        public Criteria andTotalExtensionNotBetween(Long value1, Long value2) {
            addCriterion("total_extension not between", value1, value2, "totalExtension");
            return (Criteria) this;
        }

        public Criteria andAdjustPrincipalIsNull() {
            addCriterion("adjust_principal is null");
            return (Criteria) this;
        }

        public Criteria andAdjustPrincipalIsNotNull() {
            addCriterion("adjust_principal is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustPrincipalEqualTo(Long value) {
            addCriterion("adjust_principal =", value, "adjustPrincipal");
            return (Criteria) this;
        }

        public Criteria andAdjustPrincipalNotEqualTo(Long value) {
            addCriterion("adjust_principal <>", value, "adjustPrincipal");
            return (Criteria) this;
        }

        public Criteria andAdjustPrincipalGreaterThan(Long value) {
            addCriterion("adjust_principal >", value, "adjustPrincipal");
            return (Criteria) this;
        }

        public Criteria andAdjustPrincipalGreaterThanOrEqualTo(Long value) {
            addCriterion("adjust_principal >=", value, "adjustPrincipal");
            return (Criteria) this;
        }

        public Criteria andAdjustPrincipalLessThan(Long value) {
            addCriterion("adjust_principal <", value, "adjustPrincipal");
            return (Criteria) this;
        }

        public Criteria andAdjustPrincipalLessThanOrEqualTo(Long value) {
            addCriterion("adjust_principal <=", value, "adjustPrincipal");
            return (Criteria) this;
        }

        public Criteria andAdjustPrincipalIn(List<Long> values) {
            addCriterion("adjust_principal in", values, "adjustPrincipal");
            return (Criteria) this;
        }

        public Criteria andAdjustPrincipalNotIn(List<Long> values) {
            addCriterion("adjust_principal not in", values, "adjustPrincipal");
            return (Criteria) this;
        }

        public Criteria andAdjustPrincipalBetween(Long value1, Long value2) {
            addCriterion("adjust_principal between", value1, value2, "adjustPrincipal");
            return (Criteria) this;
        }

        public Criteria andAdjustPrincipalNotBetween(Long value1, Long value2) {
            addCriterion("adjust_principal not between", value1, value2, "adjustPrincipal");
            return (Criteria) this;
        }

        public Criteria andAdjustInterestIsNull() {
            addCriterion("adjust_interest is null");
            return (Criteria) this;
        }

        public Criteria andAdjustInterestIsNotNull() {
            addCriterion("adjust_interest is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustInterestEqualTo(Long value) {
            addCriterion("adjust_interest =", value, "adjustInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustInterestNotEqualTo(Long value) {
            addCriterion("adjust_interest <>", value, "adjustInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustInterestGreaterThan(Long value) {
            addCriterion("adjust_interest >", value, "adjustInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustInterestGreaterThanOrEqualTo(Long value) {
            addCriterion("adjust_interest >=", value, "adjustInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustInterestLessThan(Long value) {
            addCriterion("adjust_interest <", value, "adjustInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustInterestLessThanOrEqualTo(Long value) {
            addCriterion("adjust_interest <=", value, "adjustInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustInterestIn(List<Long> values) {
            addCriterion("adjust_interest in", values, "adjustInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustInterestNotIn(List<Long> values) {
            addCriterion("adjust_interest not in", values, "adjustInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustInterestBetween(Long value1, Long value2) {
            addCriterion("adjust_interest between", value1, value2, "adjustInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustInterestNotBetween(Long value1, Long value2) {
            addCriterion("adjust_interest not between", value1, value2, "adjustInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustServiceChargeIsNull() {
            addCriterion("adjust_service_charge is null");
            return (Criteria) this;
        }

        public Criteria andAdjustServiceChargeIsNotNull() {
            addCriterion("adjust_service_charge is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustServiceChargeEqualTo(Long value) {
            addCriterion("adjust_service_charge =", value, "adjustServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustServiceChargeNotEqualTo(Long value) {
            addCriterion("adjust_service_charge <>", value, "adjustServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustServiceChargeGreaterThan(Long value) {
            addCriterion("adjust_service_charge >", value, "adjustServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustServiceChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("adjust_service_charge >=", value, "adjustServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustServiceChargeLessThan(Long value) {
            addCriterion("adjust_service_charge <", value, "adjustServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustServiceChargeLessThanOrEqualTo(Long value) {
            addCriterion("adjust_service_charge <=", value, "adjustServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustServiceChargeIn(List<Long> values) {
            addCriterion("adjust_service_charge in", values, "adjustServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustServiceChargeNotIn(List<Long> values) {
            addCriterion("adjust_service_charge not in", values, "adjustServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustServiceChargeBetween(Long value1, Long value2) {
            addCriterion("adjust_service_charge between", value1, value2, "adjustServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustServiceChargeNotBetween(Long value1, Long value2) {
            addCriterion("adjust_service_charge not between", value1, value2, "adjustServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustExtensionIsNull() {
            addCriterion("adjust_extension is null");
            return (Criteria) this;
        }

        public Criteria andAdjustExtensionIsNotNull() {
            addCriterion("adjust_extension is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustExtensionEqualTo(Long value) {
            addCriterion("adjust_extension =", value, "adjustExtension");
            return (Criteria) this;
        }

        public Criteria andAdjustExtensionNotEqualTo(Long value) {
            addCriterion("adjust_extension <>", value, "adjustExtension");
            return (Criteria) this;
        }

        public Criteria andAdjustExtensionGreaterThan(Long value) {
            addCriterion("adjust_extension >", value, "adjustExtension");
            return (Criteria) this;
        }

        public Criteria andAdjustExtensionGreaterThanOrEqualTo(Long value) {
            addCriterion("adjust_extension >=", value, "adjustExtension");
            return (Criteria) this;
        }

        public Criteria andAdjustExtensionLessThan(Long value) {
            addCriterion("adjust_extension <", value, "adjustExtension");
            return (Criteria) this;
        }

        public Criteria andAdjustExtensionLessThanOrEqualTo(Long value) {
            addCriterion("adjust_extension <=", value, "adjustExtension");
            return (Criteria) this;
        }

        public Criteria andAdjustExtensionIn(List<Long> values) {
            addCriterion("adjust_extension in", values, "adjustExtension");
            return (Criteria) this;
        }

        public Criteria andAdjustExtensionNotIn(List<Long> values) {
            addCriterion("adjust_extension not in", values, "adjustExtension");
            return (Criteria) this;
        }

        public Criteria andAdjustExtensionBetween(Long value1, Long value2) {
            addCriterion("adjust_extension between", value1, value2, "adjustExtension");
            return (Criteria) this;
        }

        public Criteria andAdjustExtensionNotBetween(Long value1, Long value2) {
            addCriterion("adjust_extension not between", value1, value2, "adjustExtension");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueInterestIsNull() {
            addCriterion("adjust_overdue_interest is null");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueInterestIsNotNull() {
            addCriterion("adjust_overdue_interest is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueInterestEqualTo(Long value) {
            addCriterion("adjust_overdue_interest =", value, "adjustOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueInterestNotEqualTo(Long value) {
            addCriterion("adjust_overdue_interest <>", value, "adjustOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueInterestGreaterThan(Long value) {
            addCriterion("adjust_overdue_interest >", value, "adjustOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueInterestGreaterThanOrEqualTo(Long value) {
            addCriterion("adjust_overdue_interest >=", value, "adjustOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueInterestLessThan(Long value) {
            addCriterion("adjust_overdue_interest <", value, "adjustOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueInterestLessThanOrEqualTo(Long value) {
            addCriterion("adjust_overdue_interest <=", value, "adjustOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueInterestIn(List<Long> values) {
            addCriterion("adjust_overdue_interest in", values, "adjustOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueInterestNotIn(List<Long> values) {
            addCriterion("adjust_overdue_interest not in", values, "adjustOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueInterestBetween(Long value1, Long value2) {
            addCriterion("adjust_overdue_interest between", value1, value2, "adjustOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueInterestNotBetween(Long value1, Long value2) {
            addCriterion("adjust_overdue_interest not between", value1, value2, "adjustOverdueInterest");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueServiceChargeIsNull() {
            addCriterion("adjust_overdue_service_charge is null");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueServiceChargeIsNotNull() {
            addCriterion("adjust_overdue_service_charge is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueServiceChargeEqualTo(Long value) {
            addCriterion("adjust_overdue_service_charge =", value, "adjustOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueServiceChargeNotEqualTo(Long value) {
            addCriterion("adjust_overdue_service_charge <>", value, "adjustOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueServiceChargeGreaterThan(Long value) {
            addCriterion("adjust_overdue_service_charge >", value, "adjustOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueServiceChargeGreaterThanOrEqualTo(Long value) {
            addCriterion("adjust_overdue_service_charge >=", value, "adjustOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueServiceChargeLessThan(Long value) {
            addCriterion("adjust_overdue_service_charge <", value, "adjustOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueServiceChargeLessThanOrEqualTo(Long value) {
            addCriterion("adjust_overdue_service_charge <=", value, "adjustOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueServiceChargeIn(List<Long> values) {
            addCriterion("adjust_overdue_service_charge in", values, "adjustOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueServiceChargeNotIn(List<Long> values) {
            addCriterion("adjust_overdue_service_charge not in", values, "adjustOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueServiceChargeBetween(Long value1, Long value2) {
            addCriterion("adjust_overdue_service_charge between", value1, value2, "adjustOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustOverdueServiceChargeNotBetween(Long value1, Long value2) {
            addCriterion("adjust_overdue_service_charge not between", value1, value2, "adjustOverdueServiceCharge");
            return (Criteria) this;
        }

        public Criteria andAdjustPenaltyIsNull() {
            addCriterion("adjust_penalty is null");
            return (Criteria) this;
        }

        public Criteria andAdjustPenaltyIsNotNull() {
            addCriterion("adjust_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustPenaltyEqualTo(Long value) {
            addCriterion("adjust_penalty =", value, "adjustPenalty");
            return (Criteria) this;
        }

        public Criteria andAdjustPenaltyNotEqualTo(Long value) {
            addCriterion("adjust_penalty <>", value, "adjustPenalty");
            return (Criteria) this;
        }

        public Criteria andAdjustPenaltyGreaterThan(Long value) {
            addCriterion("adjust_penalty >", value, "adjustPenalty");
            return (Criteria) this;
        }

        public Criteria andAdjustPenaltyGreaterThanOrEqualTo(Long value) {
            addCriterion("adjust_penalty >=", value, "adjustPenalty");
            return (Criteria) this;
        }

        public Criteria andAdjustPenaltyLessThan(Long value) {
            addCriterion("adjust_penalty <", value, "adjustPenalty");
            return (Criteria) this;
        }

        public Criteria andAdjustPenaltyLessThanOrEqualTo(Long value) {
            addCriterion("adjust_penalty <=", value, "adjustPenalty");
            return (Criteria) this;
        }

        public Criteria andAdjustPenaltyIn(List<Long> values) {
            addCriterion("adjust_penalty in", values, "adjustPenalty");
            return (Criteria) this;
        }

        public Criteria andAdjustPenaltyNotIn(List<Long> values) {
            addCriterion("adjust_penalty not in", values, "adjustPenalty");
            return (Criteria) this;
        }

        public Criteria andAdjustPenaltyBetween(Long value1, Long value2) {
            addCriterion("adjust_penalty between", value1, value2, "adjustPenalty");
            return (Criteria) this;
        }

        public Criteria andAdjustPenaltyNotBetween(Long value1, Long value2) {
            addCriterion("adjust_penalty not between", value1, value2, "adjustPenalty");
            return (Criteria) this;
        }

        public Criteria andAdjustExpenseIsNull() {
            addCriterion("adjust_expense is null");
            return (Criteria) this;
        }

        public Criteria andAdjustExpenseIsNotNull() {
            addCriterion("adjust_expense is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustExpenseEqualTo(Long value) {
            addCriterion("adjust_expense =", value, "adjustExpense");
            return (Criteria) this;
        }

        public Criteria andAdjustExpenseNotEqualTo(Long value) {
            addCriterion("adjust_expense <>", value, "adjustExpense");
            return (Criteria) this;
        }

        public Criteria andAdjustExpenseGreaterThan(Long value) {
            addCriterion("adjust_expense >", value, "adjustExpense");
            return (Criteria) this;
        }

        public Criteria andAdjustExpenseGreaterThanOrEqualTo(Long value) {
            addCriterion("adjust_expense >=", value, "adjustExpense");
            return (Criteria) this;
        }

        public Criteria andAdjustExpenseLessThan(Long value) {
            addCriterion("adjust_expense <", value, "adjustExpense");
            return (Criteria) this;
        }

        public Criteria andAdjustExpenseLessThanOrEqualTo(Long value) {
            addCriterion("adjust_expense <=", value, "adjustExpense");
            return (Criteria) this;
        }

        public Criteria andAdjustExpenseIn(List<Long> values) {
            addCriterion("adjust_expense in", values, "adjustExpense");
            return (Criteria) this;
        }

        public Criteria andAdjustExpenseNotIn(List<Long> values) {
            addCriterion("adjust_expense not in", values, "adjustExpense");
            return (Criteria) this;
        }

        public Criteria andAdjustExpenseBetween(Long value1, Long value2) {
            addCriterion("adjust_expense between", value1, value2, "adjustExpense");
            return (Criteria) this;
        }

        public Criteria andAdjustExpenseNotBetween(Long value1, Long value2) {
            addCriterion("adjust_expense not between", value1, value2, "adjustExpense");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountIsNull() {
            addCriterion("adjust_amount is null");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountIsNotNull() {
            addCriterion("adjust_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountEqualTo(Long value) {
            addCriterion("adjust_amount =", value, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountNotEqualTo(Long value) {
            addCriterion("adjust_amount <>", value, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountGreaterThan(Long value) {
            addCriterion("adjust_amount >", value, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("adjust_amount >=", value, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountLessThan(Long value) {
            addCriterion("adjust_amount <", value, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountLessThanOrEqualTo(Long value) {
            addCriterion("adjust_amount <=", value, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountIn(List<Long> values) {
            addCriterion("adjust_amount in", values, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountNotIn(List<Long> values) {
            addCriterion("adjust_amount not in", values, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountBetween(Long value1, Long value2) {
            addCriterion("adjust_amount between", value1, value2, "adjustAmount");
            return (Criteria) this;
        }

        public Criteria andAdjustAmountNotBetween(Long value1, Long value2) {
            addCriterion("adjust_amount not between", value1, value2, "adjustAmount");
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