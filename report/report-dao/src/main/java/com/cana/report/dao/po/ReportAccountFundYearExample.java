package com.cana.report.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportAccountFundYearExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public ReportAccountFundYearExample() {
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

        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(String value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(String value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(String value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(String value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(String value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(String value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLike(String value) {
            addCriterion("customer_id like", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotLike(String value) {
            addCriterion("customer_id not like", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<String> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<String> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(String value1, String value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(String value1, String value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
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

        public Criteria andLastBalanceIsNull() {
            addCriterion("last_balance is null");
            return (Criteria) this;
        }

        public Criteria andLastBalanceIsNotNull() {
            addCriterion("last_balance is not null");
            return (Criteria) this;
        }

        public Criteria andLastBalanceEqualTo(Long value) {
            addCriterion("last_balance =", value, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastBalanceNotEqualTo(Long value) {
            addCriterion("last_balance <>", value, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastBalanceGreaterThan(Long value) {
            addCriterion("last_balance >", value, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("last_balance >=", value, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastBalanceLessThan(Long value) {
            addCriterion("last_balance <", value, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastBalanceLessThanOrEqualTo(Long value) {
            addCriterion("last_balance <=", value, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastBalanceIn(List<Long> values) {
            addCriterion("last_balance in", values, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastBalanceNotIn(List<Long> values) {
            addCriterion("last_balance not in", values, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastBalanceBetween(Long value1, Long value2) {
            addCriterion("last_balance between", value1, value2, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastBalanceNotBetween(Long value1, Long value2) {
            addCriterion("last_balance not between", value1, value2, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastOwnSupervisionBalanceIsNull() {
            addCriterion("last_own_supervision_balance is null");
            return (Criteria) this;
        }

        public Criteria andLastOwnSupervisionBalanceIsNotNull() {
            addCriterion("last_own_supervision_balance is not null");
            return (Criteria) this;
        }

        public Criteria andLastOwnSupervisionBalanceEqualTo(Long value) {
            addCriterion("last_own_supervision_balance =", value, "lastOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOwnSupervisionBalanceNotEqualTo(Long value) {
            addCriterion("last_own_supervision_balance <>", value, "lastOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOwnSupervisionBalanceGreaterThan(Long value) {
            addCriterion("last_own_supervision_balance >", value, "lastOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOwnSupervisionBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("last_own_supervision_balance >=", value, "lastOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOwnSupervisionBalanceLessThan(Long value) {
            addCriterion("last_own_supervision_balance <", value, "lastOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOwnSupervisionBalanceLessThanOrEqualTo(Long value) {
            addCriterion("last_own_supervision_balance <=", value, "lastOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOwnSupervisionBalanceIn(List<Long> values) {
            addCriterion("last_own_supervision_balance in", values, "lastOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOwnSupervisionBalanceNotIn(List<Long> values) {
            addCriterion("last_own_supervision_balance not in", values, "lastOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOwnSupervisionBalanceBetween(Long value1, Long value2) {
            addCriterion("last_own_supervision_balance between", value1, value2, "lastOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOwnSupervisionBalanceNotBetween(Long value1, Long value2) {
            addCriterion("last_own_supervision_balance not between", value1, value2, "lastOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOtherSupervisionBalanceIsNull() {
            addCriterion("last_other_supervision_balance is null");
            return (Criteria) this;
        }

        public Criteria andLastOtherSupervisionBalanceIsNotNull() {
            addCriterion("last_other_supervision_balance is not null");
            return (Criteria) this;
        }

        public Criteria andLastOtherSupervisionBalanceEqualTo(Long value) {
            addCriterion("last_other_supervision_balance =", value, "lastOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOtherSupervisionBalanceNotEqualTo(Long value) {
            addCriterion("last_other_supervision_balance <>", value, "lastOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOtherSupervisionBalanceGreaterThan(Long value) {
            addCriterion("last_other_supervision_balance >", value, "lastOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOtherSupervisionBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("last_other_supervision_balance >=", value, "lastOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOtherSupervisionBalanceLessThan(Long value) {
            addCriterion("last_other_supervision_balance <", value, "lastOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOtherSupervisionBalanceLessThanOrEqualTo(Long value) {
            addCriterion("last_other_supervision_balance <=", value, "lastOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOtherSupervisionBalanceIn(List<Long> values) {
            addCriterion("last_other_supervision_balance in", values, "lastOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOtherSupervisionBalanceNotIn(List<Long> values) {
            addCriterion("last_other_supervision_balance not in", values, "lastOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOtherSupervisionBalanceBetween(Long value1, Long value2) {
            addCriterion("last_other_supervision_balance between", value1, value2, "lastOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andLastOtherSupervisionBalanceNotBetween(Long value1, Long value2) {
            addCriterion("last_other_supervision_balance not between", value1, value2, "lastOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceIsNull() {
            addCriterion("current_balance is null");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceIsNotNull() {
            addCriterion("current_balance is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceEqualTo(Long value) {
            addCriterion("current_balance =", value, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceNotEqualTo(Long value) {
            addCriterion("current_balance <>", value, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceGreaterThan(Long value) {
            addCriterion("current_balance >", value, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("current_balance >=", value, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceLessThan(Long value) {
            addCriterion("current_balance <", value, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceLessThanOrEqualTo(Long value) {
            addCriterion("current_balance <=", value, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceIn(List<Long> values) {
            addCriterion("current_balance in", values, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceNotIn(List<Long> values) {
            addCriterion("current_balance not in", values, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceBetween(Long value1, Long value2) {
            addCriterion("current_balance between", value1, value2, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceNotBetween(Long value1, Long value2) {
            addCriterion("current_balance not between", value1, value2, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOwnSupervisionBalanceIsNull() {
            addCriterion("current_own_supervision_balance is null");
            return (Criteria) this;
        }

        public Criteria andCurrentOwnSupervisionBalanceIsNotNull() {
            addCriterion("current_own_supervision_balance is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentOwnSupervisionBalanceEqualTo(Long value) {
            addCriterion("current_own_supervision_balance =", value, "currentOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOwnSupervisionBalanceNotEqualTo(Long value) {
            addCriterion("current_own_supervision_balance <>", value, "currentOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOwnSupervisionBalanceGreaterThan(Long value) {
            addCriterion("current_own_supervision_balance >", value, "currentOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOwnSupervisionBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("current_own_supervision_balance >=", value, "currentOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOwnSupervisionBalanceLessThan(Long value) {
            addCriterion("current_own_supervision_balance <", value, "currentOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOwnSupervisionBalanceLessThanOrEqualTo(Long value) {
            addCriterion("current_own_supervision_balance <=", value, "currentOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOwnSupervisionBalanceIn(List<Long> values) {
            addCriterion("current_own_supervision_balance in", values, "currentOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOwnSupervisionBalanceNotIn(List<Long> values) {
            addCriterion("current_own_supervision_balance not in", values, "currentOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOwnSupervisionBalanceBetween(Long value1, Long value2) {
            addCriterion("current_own_supervision_balance between", value1, value2, "currentOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOwnSupervisionBalanceNotBetween(Long value1, Long value2) {
            addCriterion("current_own_supervision_balance not between", value1, value2, "currentOwnSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOtherSupervisionBalanceIsNull() {
            addCriterion("current_other_supervision_balance is null");
            return (Criteria) this;
        }

        public Criteria andCurrentOtherSupervisionBalanceIsNotNull() {
            addCriterion("current_other_supervision_balance is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentOtherSupervisionBalanceEqualTo(Long value) {
            addCriterion("current_other_supervision_balance =", value, "currentOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOtherSupervisionBalanceNotEqualTo(Long value) {
            addCriterion("current_other_supervision_balance <>", value, "currentOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOtherSupervisionBalanceGreaterThan(Long value) {
            addCriterion("current_other_supervision_balance >", value, "currentOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOtherSupervisionBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("current_other_supervision_balance >=", value, "currentOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOtherSupervisionBalanceLessThan(Long value) {
            addCriterion("current_other_supervision_balance <", value, "currentOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOtherSupervisionBalanceLessThanOrEqualTo(Long value) {
            addCriterion("current_other_supervision_balance <=", value, "currentOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOtherSupervisionBalanceIn(List<Long> values) {
            addCriterion("current_other_supervision_balance in", values, "currentOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOtherSupervisionBalanceNotIn(List<Long> values) {
            addCriterion("current_other_supervision_balance not in", values, "currentOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOtherSupervisionBalanceBetween(Long value1, Long value2) {
            addCriterion("current_other_supervision_balance between", value1, value2, "currentOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentOtherSupervisionBalanceNotBetween(Long value1, Long value2) {
            addCriterion("current_other_supervision_balance not between", value1, value2, "currentOtherSupervisionBalance");
            return (Criteria) this;
        }

        public Criteria andDepositFundIsNull() {
            addCriterion("deposit_fund is null");
            return (Criteria) this;
        }

        public Criteria andDepositFundIsNotNull() {
            addCriterion("deposit_fund is not null");
            return (Criteria) this;
        }

        public Criteria andDepositFundEqualTo(Long value) {
            addCriterion("deposit_fund =", value, "depositFund");
            return (Criteria) this;
        }

        public Criteria andDepositFundNotEqualTo(Long value) {
            addCriterion("deposit_fund <>", value, "depositFund");
            return (Criteria) this;
        }

        public Criteria andDepositFundGreaterThan(Long value) {
            addCriterion("deposit_fund >", value, "depositFund");
            return (Criteria) this;
        }

        public Criteria andDepositFundGreaterThanOrEqualTo(Long value) {
            addCriterion("deposit_fund >=", value, "depositFund");
            return (Criteria) this;
        }

        public Criteria andDepositFundLessThan(Long value) {
            addCriterion("deposit_fund <", value, "depositFund");
            return (Criteria) this;
        }

        public Criteria andDepositFundLessThanOrEqualTo(Long value) {
            addCriterion("deposit_fund <=", value, "depositFund");
            return (Criteria) this;
        }

        public Criteria andDepositFundIn(List<Long> values) {
            addCriterion("deposit_fund in", values, "depositFund");
            return (Criteria) this;
        }

        public Criteria andDepositFundNotIn(List<Long> values) {
            addCriterion("deposit_fund not in", values, "depositFund");
            return (Criteria) this;
        }

        public Criteria andDepositFundBetween(Long value1, Long value2) {
            addCriterion("deposit_fund between", value1, value2, "depositFund");
            return (Criteria) this;
        }

        public Criteria andDepositFundNotBetween(Long value1, Long value2) {
            addCriterion("deposit_fund not between", value1, value2, "depositFund");
            return (Criteria) this;
        }

        public Criteria andTransferFundIsNull() {
            addCriterion("transfer_fund is null");
            return (Criteria) this;
        }

        public Criteria andTransferFundIsNotNull() {
            addCriterion("transfer_fund is not null");
            return (Criteria) this;
        }

        public Criteria andTransferFundEqualTo(Long value) {
            addCriterion("transfer_fund =", value, "transferFund");
            return (Criteria) this;
        }

        public Criteria andTransferFundNotEqualTo(Long value) {
            addCriterion("transfer_fund <>", value, "transferFund");
            return (Criteria) this;
        }

        public Criteria andTransferFundGreaterThan(Long value) {
            addCriterion("transfer_fund >", value, "transferFund");
            return (Criteria) this;
        }

        public Criteria andTransferFundGreaterThanOrEqualTo(Long value) {
            addCriterion("transfer_fund >=", value, "transferFund");
            return (Criteria) this;
        }

        public Criteria andTransferFundLessThan(Long value) {
            addCriterion("transfer_fund <", value, "transferFund");
            return (Criteria) this;
        }

        public Criteria andTransferFundLessThanOrEqualTo(Long value) {
            addCriterion("transfer_fund <=", value, "transferFund");
            return (Criteria) this;
        }

        public Criteria andTransferFundIn(List<Long> values) {
            addCriterion("transfer_fund in", values, "transferFund");
            return (Criteria) this;
        }

        public Criteria andTransferFundNotIn(List<Long> values) {
            addCriterion("transfer_fund not in", values, "transferFund");
            return (Criteria) this;
        }

        public Criteria andTransferFundBetween(Long value1, Long value2) {
            addCriterion("transfer_fund between", value1, value2, "transferFund");
            return (Criteria) this;
        }

        public Criteria andTransferFundNotBetween(Long value1, Long value2) {
            addCriterion("transfer_fund not between", value1, value2, "transferFund");
            return (Criteria) this;
        }

        public Criteria andWithdrawFundIsNull() {
            addCriterion("withdraw_fund is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawFundIsNotNull() {
            addCriterion("withdraw_fund is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawFundEqualTo(Long value) {
            addCriterion("withdraw_fund =", value, "withdrawFund");
            return (Criteria) this;
        }

        public Criteria andWithdrawFundNotEqualTo(Long value) {
            addCriterion("withdraw_fund <>", value, "withdrawFund");
            return (Criteria) this;
        }

        public Criteria andWithdrawFundGreaterThan(Long value) {
            addCriterion("withdraw_fund >", value, "withdrawFund");
            return (Criteria) this;
        }

        public Criteria andWithdrawFundGreaterThanOrEqualTo(Long value) {
            addCriterion("withdraw_fund >=", value, "withdrawFund");
            return (Criteria) this;
        }

        public Criteria andWithdrawFundLessThan(Long value) {
            addCriterion("withdraw_fund <", value, "withdrawFund");
            return (Criteria) this;
        }

        public Criteria andWithdrawFundLessThanOrEqualTo(Long value) {
            addCriterion("withdraw_fund <=", value, "withdrawFund");
            return (Criteria) this;
        }

        public Criteria andWithdrawFundIn(List<Long> values) {
            addCriterion("withdraw_fund in", values, "withdrawFund");
            return (Criteria) this;
        }

        public Criteria andWithdrawFundNotIn(List<Long> values) {
            addCriterion("withdraw_fund not in", values, "withdrawFund");
            return (Criteria) this;
        }

        public Criteria andWithdrawFundBetween(Long value1, Long value2) {
            addCriterion("withdraw_fund between", value1, value2, "withdrawFund");
            return (Criteria) this;
        }

        public Criteria andWithdrawFundNotBetween(Long value1, Long value2) {
            addCriterion("withdraw_fund not between", value1, value2, "withdrawFund");
            return (Criteria) this;
        }

        public Criteria andWithdrawFeeIsNull() {
            addCriterion("withdraw_fee is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawFeeIsNotNull() {
            addCriterion("withdraw_fee is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawFeeEqualTo(Long value) {
            addCriterion("withdraw_fee =", value, "withdrawFee");
            return (Criteria) this;
        }

        public Criteria andWithdrawFeeNotEqualTo(Long value) {
            addCriterion("withdraw_fee <>", value, "withdrawFee");
            return (Criteria) this;
        }

        public Criteria andWithdrawFeeGreaterThan(Long value) {
            addCriterion("withdraw_fee >", value, "withdrawFee");
            return (Criteria) this;
        }

        public Criteria andWithdrawFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("withdraw_fee >=", value, "withdrawFee");
            return (Criteria) this;
        }

        public Criteria andWithdrawFeeLessThan(Long value) {
            addCriterion("withdraw_fee <", value, "withdrawFee");
            return (Criteria) this;
        }

        public Criteria andWithdrawFeeLessThanOrEqualTo(Long value) {
            addCriterion("withdraw_fee <=", value, "withdrawFee");
            return (Criteria) this;
        }

        public Criteria andWithdrawFeeIn(List<Long> values) {
            addCriterion("withdraw_fee in", values, "withdrawFee");
            return (Criteria) this;
        }

        public Criteria andWithdrawFeeNotIn(List<Long> values) {
            addCriterion("withdraw_fee not in", values, "withdrawFee");
            return (Criteria) this;
        }

        public Criteria andWithdrawFeeBetween(Long value1, Long value2) {
            addCriterion("withdraw_fee between", value1, value2, "withdrawFee");
            return (Criteria) this;
        }

        public Criteria andWithdrawFeeNotBetween(Long value1, Long value2) {
            addCriterion("withdraw_fee not between", value1, value2, "withdrawFee");
            return (Criteria) this;
        }

        public Criteria andFreezeFundIsNull() {
            addCriterion("freeze_fund is null");
            return (Criteria) this;
        }

        public Criteria andFreezeFundIsNotNull() {
            addCriterion("freeze_fund is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeFundEqualTo(Long value) {
            addCriterion("freeze_fund =", value, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andFreezeFundNotEqualTo(Long value) {
            addCriterion("freeze_fund <>", value, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andFreezeFundGreaterThan(Long value) {
            addCriterion("freeze_fund >", value, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andFreezeFundGreaterThanOrEqualTo(Long value) {
            addCriterion("freeze_fund >=", value, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andFreezeFundLessThan(Long value) {
            addCriterion("freeze_fund <", value, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andFreezeFundLessThanOrEqualTo(Long value) {
            addCriterion("freeze_fund <=", value, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andFreezeFundIn(List<Long> values) {
            addCriterion("freeze_fund in", values, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andFreezeFundNotIn(List<Long> values) {
            addCriterion("freeze_fund not in", values, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andFreezeFundBetween(Long value1, Long value2) {
            addCriterion("freeze_fund between", value1, value2, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andFreezeFundNotBetween(Long value1, Long value2) {
            addCriterion("freeze_fund not between", value1, value2, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andUnfreezeFundIsNull() {
            addCriterion("unfreeze_fund is null");
            return (Criteria) this;
        }

        public Criteria andUnfreezeFundIsNotNull() {
            addCriterion("unfreeze_fund is not null");
            return (Criteria) this;
        }

        public Criteria andUnfreezeFundEqualTo(Long value) {
            addCriterion("unfreeze_fund =", value, "unfreezeFund");
            return (Criteria) this;
        }

        public Criteria andUnfreezeFundNotEqualTo(Long value) {
            addCriterion("unfreeze_fund <>", value, "unfreezeFund");
            return (Criteria) this;
        }

        public Criteria andUnfreezeFundGreaterThan(Long value) {
            addCriterion("unfreeze_fund >", value, "unfreezeFund");
            return (Criteria) this;
        }

        public Criteria andUnfreezeFundGreaterThanOrEqualTo(Long value) {
            addCriterion("unfreeze_fund >=", value, "unfreezeFund");
            return (Criteria) this;
        }

        public Criteria andUnfreezeFundLessThan(Long value) {
            addCriterion("unfreeze_fund <", value, "unfreezeFund");
            return (Criteria) this;
        }

        public Criteria andUnfreezeFundLessThanOrEqualTo(Long value) {
            addCriterion("unfreeze_fund <=", value, "unfreezeFund");
            return (Criteria) this;
        }

        public Criteria andUnfreezeFundIn(List<Long> values) {
            addCriterion("unfreeze_fund in", values, "unfreezeFund");
            return (Criteria) this;
        }

        public Criteria andUnfreezeFundNotIn(List<Long> values) {
            addCriterion("unfreeze_fund not in", values, "unfreezeFund");
            return (Criteria) this;
        }

        public Criteria andUnfreezeFundBetween(Long value1, Long value2) {
            addCriterion("unfreeze_fund between", value1, value2, "unfreezeFund");
            return (Criteria) this;
        }

        public Criteria andUnfreezeFundNotBetween(Long value1, Long value2) {
            addCriterion("unfreeze_fund not between", value1, value2, "unfreezeFund");
            return (Criteria) this;
        }

        public Criteria andRefundFundIsNull() {
            addCriterion("refund_fund is null");
            return (Criteria) this;
        }

        public Criteria andRefundFundIsNotNull() {
            addCriterion("refund_fund is not null");
            return (Criteria) this;
        }

        public Criteria andRefundFundEqualTo(Long value) {
            addCriterion("refund_fund =", value, "refundFund");
            return (Criteria) this;
        }

        public Criteria andRefundFundNotEqualTo(Long value) {
            addCriterion("refund_fund <>", value, "refundFund");
            return (Criteria) this;
        }

        public Criteria andRefundFundGreaterThan(Long value) {
            addCriterion("refund_fund >", value, "refundFund");
            return (Criteria) this;
        }

        public Criteria andRefundFundGreaterThanOrEqualTo(Long value) {
            addCriterion("refund_fund >=", value, "refundFund");
            return (Criteria) this;
        }

        public Criteria andRefundFundLessThan(Long value) {
            addCriterion("refund_fund <", value, "refundFund");
            return (Criteria) this;
        }

        public Criteria andRefundFundLessThanOrEqualTo(Long value) {
            addCriterion("refund_fund <=", value, "refundFund");
            return (Criteria) this;
        }

        public Criteria andRefundFundIn(List<Long> values) {
            addCriterion("refund_fund in", values, "refundFund");
            return (Criteria) this;
        }

        public Criteria andRefundFundNotIn(List<Long> values) {
            addCriterion("refund_fund not in", values, "refundFund");
            return (Criteria) this;
        }

        public Criteria andRefundFundBetween(Long value1, Long value2) {
            addCriterion("refund_fund between", value1, value2, "refundFund");
            return (Criteria) this;
        }

        public Criteria andRefundFundNotBetween(Long value1, Long value2) {
            addCriterion("refund_fund not between", value1, value2, "refundFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositFundIsNull() {
            addCriterion("supervision_deposit_fund is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositFundIsNotNull() {
            addCriterion("supervision_deposit_fund is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositFundEqualTo(Long value) {
            addCriterion("supervision_deposit_fund =", value, "supervisionDepositFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositFundNotEqualTo(Long value) {
            addCriterion("supervision_deposit_fund <>", value, "supervisionDepositFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositFundGreaterThan(Long value) {
            addCriterion("supervision_deposit_fund >", value, "supervisionDepositFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositFundGreaterThanOrEqualTo(Long value) {
            addCriterion("supervision_deposit_fund >=", value, "supervisionDepositFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositFundLessThan(Long value) {
            addCriterion("supervision_deposit_fund <", value, "supervisionDepositFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositFundLessThanOrEqualTo(Long value) {
            addCriterion("supervision_deposit_fund <=", value, "supervisionDepositFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositFundIn(List<Long> values) {
            addCriterion("supervision_deposit_fund in", values, "supervisionDepositFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositFundNotIn(List<Long> values) {
            addCriterion("supervision_deposit_fund not in", values, "supervisionDepositFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositFundBetween(Long value1, Long value2) {
            addCriterion("supervision_deposit_fund between", value1, value2, "supervisionDepositFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositFundNotBetween(Long value1, Long value2) {
            addCriterion("supervision_deposit_fund not between", value1, value2, "supervisionDepositFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferFundIsNull() {
            addCriterion("supervision_transfer_fund is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferFundIsNotNull() {
            addCriterion("supervision_transfer_fund is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferFundEqualTo(Long value) {
            addCriterion("supervision_transfer_fund =", value, "supervisionTransferFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferFundNotEqualTo(Long value) {
            addCriterion("supervision_transfer_fund <>", value, "supervisionTransferFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferFundGreaterThan(Long value) {
            addCriterion("supervision_transfer_fund >", value, "supervisionTransferFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferFundGreaterThanOrEqualTo(Long value) {
            addCriterion("supervision_transfer_fund >=", value, "supervisionTransferFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferFundLessThan(Long value) {
            addCriterion("supervision_transfer_fund <", value, "supervisionTransferFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferFundLessThanOrEqualTo(Long value) {
            addCriterion("supervision_transfer_fund <=", value, "supervisionTransferFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferFundIn(List<Long> values) {
            addCriterion("supervision_transfer_fund in", values, "supervisionTransferFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferFundNotIn(List<Long> values) {
            addCriterion("supervision_transfer_fund not in", values, "supervisionTransferFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferFundBetween(Long value1, Long value2) {
            addCriterion("supervision_transfer_fund between", value1, value2, "supervisionTransferFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferFundNotBetween(Long value1, Long value2) {
            addCriterion("supervision_transfer_fund not between", value1, value2, "supervisionTransferFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFundIsNull() {
            addCriterion("supervision_withdraw_fund is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFundIsNotNull() {
            addCriterion("supervision_withdraw_fund is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFundEqualTo(Long value) {
            addCriterion("supervision_withdraw_fund =", value, "supervisionWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFundNotEqualTo(Long value) {
            addCriterion("supervision_withdraw_fund <>", value, "supervisionWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFundGreaterThan(Long value) {
            addCriterion("supervision_withdraw_fund >", value, "supervisionWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFundGreaterThanOrEqualTo(Long value) {
            addCriterion("supervision_withdraw_fund >=", value, "supervisionWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFundLessThan(Long value) {
            addCriterion("supervision_withdraw_fund <", value, "supervisionWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFundLessThanOrEqualTo(Long value) {
            addCriterion("supervision_withdraw_fund <=", value, "supervisionWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFundIn(List<Long> values) {
            addCriterion("supervision_withdraw_fund in", values, "supervisionWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFundNotIn(List<Long> values) {
            addCriterion("supervision_withdraw_fund not in", values, "supervisionWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFundBetween(Long value1, Long value2) {
            addCriterion("supervision_withdraw_fund between", value1, value2, "supervisionWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFundNotBetween(Long value1, Long value2) {
            addCriterion("supervision_withdraw_fund not between", value1, value2, "supervisionWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFeeIsNull() {
            addCriterion("supervision_withdraw_fee is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFeeIsNotNull() {
            addCriterion("supervision_withdraw_fee is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFeeEqualTo(Long value) {
            addCriterion("supervision_withdraw_fee =", value, "supervisionWithdrawFee");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFeeNotEqualTo(Long value) {
            addCriterion("supervision_withdraw_fee <>", value, "supervisionWithdrawFee");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFeeGreaterThan(Long value) {
            addCriterion("supervision_withdraw_fee >", value, "supervisionWithdrawFee");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("supervision_withdraw_fee >=", value, "supervisionWithdrawFee");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFeeLessThan(Long value) {
            addCriterion("supervision_withdraw_fee <", value, "supervisionWithdrawFee");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFeeLessThanOrEqualTo(Long value) {
            addCriterion("supervision_withdraw_fee <=", value, "supervisionWithdrawFee");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFeeIn(List<Long> values) {
            addCriterion("supervision_withdraw_fee in", values, "supervisionWithdrawFee");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFeeNotIn(List<Long> values) {
            addCriterion("supervision_withdraw_fee not in", values, "supervisionWithdrawFee");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFeeBetween(Long value1, Long value2) {
            addCriterion("supervision_withdraw_fee between", value1, value2, "supervisionWithdrawFee");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawFeeNotBetween(Long value1, Long value2) {
            addCriterion("supervision_withdraw_fee not between", value1, value2, "supervisionWithdrawFee");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeFundIsNull() {
            addCriterion("supervision_freeze_fund is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeFundIsNotNull() {
            addCriterion("supervision_freeze_fund is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeFundEqualTo(Long value) {
            addCriterion("supervision_freeze_fund =", value, "supervisionFreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeFundNotEqualTo(Long value) {
            addCriterion("supervision_freeze_fund <>", value, "supervisionFreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeFundGreaterThan(Long value) {
            addCriterion("supervision_freeze_fund >", value, "supervisionFreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeFundGreaterThanOrEqualTo(Long value) {
            addCriterion("supervision_freeze_fund >=", value, "supervisionFreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeFundLessThan(Long value) {
            addCriterion("supervision_freeze_fund <", value, "supervisionFreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeFundLessThanOrEqualTo(Long value) {
            addCriterion("supervision_freeze_fund <=", value, "supervisionFreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeFundIn(List<Long> values) {
            addCriterion("supervision_freeze_fund in", values, "supervisionFreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeFundNotIn(List<Long> values) {
            addCriterion("supervision_freeze_fund not in", values, "supervisionFreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeFundBetween(Long value1, Long value2) {
            addCriterion("supervision_freeze_fund between", value1, value2, "supervisionFreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeFundNotBetween(Long value1, Long value2) {
            addCriterion("supervision_freeze_fund not between", value1, value2, "supervisionFreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeFundIsNull() {
            addCriterion("supervision_unfreeze_fund is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeFundIsNotNull() {
            addCriterion("supervision_unfreeze_fund is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeFundEqualTo(Long value) {
            addCriterion("supervision_unfreeze_fund =", value, "supervisionUnfreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeFundNotEqualTo(Long value) {
            addCriterion("supervision_unfreeze_fund <>", value, "supervisionUnfreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeFundGreaterThan(Long value) {
            addCriterion("supervision_unfreeze_fund >", value, "supervisionUnfreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeFundGreaterThanOrEqualTo(Long value) {
            addCriterion("supervision_unfreeze_fund >=", value, "supervisionUnfreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeFundLessThan(Long value) {
            addCriterion("supervision_unfreeze_fund <", value, "supervisionUnfreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeFundLessThanOrEqualTo(Long value) {
            addCriterion("supervision_unfreeze_fund <=", value, "supervisionUnfreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeFundIn(List<Long> values) {
            addCriterion("supervision_unfreeze_fund in", values, "supervisionUnfreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeFundNotIn(List<Long> values) {
            addCriterion("supervision_unfreeze_fund not in", values, "supervisionUnfreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeFundBetween(Long value1, Long value2) {
            addCriterion("supervision_unfreeze_fund between", value1, value2, "supervisionUnfreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeFundNotBetween(Long value1, Long value2) {
            addCriterion("supervision_unfreeze_fund not between", value1, value2, "supervisionUnfreezeFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundFundIsNull() {
            addCriterion("supervision_refund_fund is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundFundIsNotNull() {
            addCriterion("supervision_refund_fund is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundFundEqualTo(Long value) {
            addCriterion("supervision_refund_fund =", value, "supervisionRefundFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundFundNotEqualTo(Long value) {
            addCriterion("supervision_refund_fund <>", value, "supervisionRefundFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundFundGreaterThan(Long value) {
            addCriterion("supervision_refund_fund >", value, "supervisionRefundFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundFundGreaterThanOrEqualTo(Long value) {
            addCriterion("supervision_refund_fund >=", value, "supervisionRefundFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundFundLessThan(Long value) {
            addCriterion("supervision_refund_fund <", value, "supervisionRefundFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundFundLessThanOrEqualTo(Long value) {
            addCriterion("supervision_refund_fund <=", value, "supervisionRefundFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundFundIn(List<Long> values) {
            addCriterion("supervision_refund_fund in", values, "supervisionRefundFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundFundNotIn(List<Long> values) {
            addCriterion("supervision_refund_fund not in", values, "supervisionRefundFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundFundBetween(Long value1, Long value2) {
            addCriterion("supervision_refund_fund between", value1, value2, "supervisionRefundFund");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundFundNotBetween(Long value1, Long value2) {
            addCriterion("supervision_refund_fund not between", value1, value2, "supervisionRefundFund");
            return (Criteria) this;
        }

        public Criteria andFundReportStateIsNull() {
            addCriterion("fund_report_state is null");
            return (Criteria) this;
        }

        public Criteria andFundReportStateIsNotNull() {
            addCriterion("fund_report_state is not null");
            return (Criteria) this;
        }

        public Criteria andFundReportStateEqualTo(Integer value) {
            addCriterion("fund_report_state =", value, "fundReportState");
            return (Criteria) this;
        }

        public Criteria andFundReportStateNotEqualTo(Integer value) {
            addCriterion("fund_report_state <>", value, "fundReportState");
            return (Criteria) this;
        }

        public Criteria andFundReportStateGreaterThan(Integer value) {
            addCriterion("fund_report_state >", value, "fundReportState");
            return (Criteria) this;
        }

        public Criteria andFundReportStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("fund_report_state >=", value, "fundReportState");
            return (Criteria) this;
        }

        public Criteria andFundReportStateLessThan(Integer value) {
            addCriterion("fund_report_state <", value, "fundReportState");
            return (Criteria) this;
        }

        public Criteria andFundReportStateLessThanOrEqualTo(Integer value) {
            addCriterion("fund_report_state <=", value, "fundReportState");
            return (Criteria) this;
        }

        public Criteria andFundReportStateIn(List<Integer> values) {
            addCriterion("fund_report_state in", values, "fundReportState");
            return (Criteria) this;
        }

        public Criteria andFundReportStateNotIn(List<Integer> values) {
            addCriterion("fund_report_state not in", values, "fundReportState");
            return (Criteria) this;
        }

        public Criteria andFundReportStateBetween(Integer value1, Integer value2) {
            addCriterion("fund_report_state between", value1, value2, "fundReportState");
            return (Criteria) this;
        }

        public Criteria andFundReportStateNotBetween(Integer value1, Integer value2) {
            addCriterion("fund_report_state not between", value1, value2, "fundReportState");
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