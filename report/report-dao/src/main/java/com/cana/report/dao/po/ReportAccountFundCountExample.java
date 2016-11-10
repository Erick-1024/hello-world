package com.cana.report.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportAccountFundCountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public ReportAccountFundCountExample() {
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

        public Criteria andDepositCountIsNull() {
            addCriterion("deposit_count is null");
            return (Criteria) this;
        }

        public Criteria andDepositCountIsNotNull() {
            addCriterion("deposit_count is not null");
            return (Criteria) this;
        }

        public Criteria andDepositCountEqualTo(Long value) {
            addCriterion("deposit_count =", value, "depositCount");
            return (Criteria) this;
        }

        public Criteria andDepositCountNotEqualTo(Long value) {
            addCriterion("deposit_count <>", value, "depositCount");
            return (Criteria) this;
        }

        public Criteria andDepositCountGreaterThan(Long value) {
            addCriterion("deposit_count >", value, "depositCount");
            return (Criteria) this;
        }

        public Criteria andDepositCountGreaterThanOrEqualTo(Long value) {
            addCriterion("deposit_count >=", value, "depositCount");
            return (Criteria) this;
        }

        public Criteria andDepositCountLessThan(Long value) {
            addCriterion("deposit_count <", value, "depositCount");
            return (Criteria) this;
        }

        public Criteria andDepositCountLessThanOrEqualTo(Long value) {
            addCriterion("deposit_count <=", value, "depositCount");
            return (Criteria) this;
        }

        public Criteria andDepositCountIn(List<Long> values) {
            addCriterion("deposit_count in", values, "depositCount");
            return (Criteria) this;
        }

        public Criteria andDepositCountNotIn(List<Long> values) {
            addCriterion("deposit_count not in", values, "depositCount");
            return (Criteria) this;
        }

        public Criteria andDepositCountBetween(Long value1, Long value2) {
            addCriterion("deposit_count between", value1, value2, "depositCount");
            return (Criteria) this;
        }

        public Criteria andDepositCountNotBetween(Long value1, Long value2) {
            addCriterion("deposit_count not between", value1, value2, "depositCount");
            return (Criteria) this;
        }

        public Criteria andTransferInCountIsNull() {
            addCriterion("transfer_in_count is null");
            return (Criteria) this;
        }

        public Criteria andTransferInCountIsNotNull() {
            addCriterion("transfer_in_count is not null");
            return (Criteria) this;
        }

        public Criteria andTransferInCountEqualTo(Long value) {
            addCriterion("transfer_in_count =", value, "transferInCount");
            return (Criteria) this;
        }

        public Criteria andTransferInCountNotEqualTo(Long value) {
            addCriterion("transfer_in_count <>", value, "transferInCount");
            return (Criteria) this;
        }

        public Criteria andTransferInCountGreaterThan(Long value) {
            addCriterion("transfer_in_count >", value, "transferInCount");
            return (Criteria) this;
        }

        public Criteria andTransferInCountGreaterThanOrEqualTo(Long value) {
            addCriterion("transfer_in_count >=", value, "transferInCount");
            return (Criteria) this;
        }

        public Criteria andTransferInCountLessThan(Long value) {
            addCriterion("transfer_in_count <", value, "transferInCount");
            return (Criteria) this;
        }

        public Criteria andTransferInCountLessThanOrEqualTo(Long value) {
            addCriterion("transfer_in_count <=", value, "transferInCount");
            return (Criteria) this;
        }

        public Criteria andTransferInCountIn(List<Long> values) {
            addCriterion("transfer_in_count in", values, "transferInCount");
            return (Criteria) this;
        }

        public Criteria andTransferInCountNotIn(List<Long> values) {
            addCriterion("transfer_in_count not in", values, "transferInCount");
            return (Criteria) this;
        }

        public Criteria andTransferInCountBetween(Long value1, Long value2) {
            addCriterion("transfer_in_count between", value1, value2, "transferInCount");
            return (Criteria) this;
        }

        public Criteria andTransferInCountNotBetween(Long value1, Long value2) {
            addCriterion("transfer_in_count not between", value1, value2, "transferInCount");
            return (Criteria) this;
        }

        public Criteria andTransferOutCountIsNull() {
            addCriterion("transfer_out_count is null");
            return (Criteria) this;
        }

        public Criteria andTransferOutCountIsNotNull() {
            addCriterion("transfer_out_count is not null");
            return (Criteria) this;
        }

        public Criteria andTransferOutCountEqualTo(Long value) {
            addCriterion("transfer_out_count =", value, "transferOutCount");
            return (Criteria) this;
        }

        public Criteria andTransferOutCountNotEqualTo(Long value) {
            addCriterion("transfer_out_count <>", value, "transferOutCount");
            return (Criteria) this;
        }

        public Criteria andTransferOutCountGreaterThan(Long value) {
            addCriterion("transfer_out_count >", value, "transferOutCount");
            return (Criteria) this;
        }

        public Criteria andTransferOutCountGreaterThanOrEqualTo(Long value) {
            addCriterion("transfer_out_count >=", value, "transferOutCount");
            return (Criteria) this;
        }

        public Criteria andTransferOutCountLessThan(Long value) {
            addCriterion("transfer_out_count <", value, "transferOutCount");
            return (Criteria) this;
        }

        public Criteria andTransferOutCountLessThanOrEqualTo(Long value) {
            addCriterion("transfer_out_count <=", value, "transferOutCount");
            return (Criteria) this;
        }

        public Criteria andTransferOutCountIn(List<Long> values) {
            addCriterion("transfer_out_count in", values, "transferOutCount");
            return (Criteria) this;
        }

        public Criteria andTransferOutCountNotIn(List<Long> values) {
            addCriterion("transfer_out_count not in", values, "transferOutCount");
            return (Criteria) this;
        }

        public Criteria andTransferOutCountBetween(Long value1, Long value2) {
            addCriterion("transfer_out_count between", value1, value2, "transferOutCount");
            return (Criteria) this;
        }

        public Criteria andTransferOutCountNotBetween(Long value1, Long value2) {
            addCriterion("transfer_out_count not between", value1, value2, "transferOutCount");
            return (Criteria) this;
        }

        public Criteria andWithdrawCountIsNull() {
            addCriterion("withdraw_count is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawCountIsNotNull() {
            addCriterion("withdraw_count is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawCountEqualTo(Long value) {
            addCriterion("withdraw_count =", value, "withdrawCount");
            return (Criteria) this;
        }

        public Criteria andWithdrawCountNotEqualTo(Long value) {
            addCriterion("withdraw_count <>", value, "withdrawCount");
            return (Criteria) this;
        }

        public Criteria andWithdrawCountGreaterThan(Long value) {
            addCriterion("withdraw_count >", value, "withdrawCount");
            return (Criteria) this;
        }

        public Criteria andWithdrawCountGreaterThanOrEqualTo(Long value) {
            addCriterion("withdraw_count >=", value, "withdrawCount");
            return (Criteria) this;
        }

        public Criteria andWithdrawCountLessThan(Long value) {
            addCriterion("withdraw_count <", value, "withdrawCount");
            return (Criteria) this;
        }

        public Criteria andWithdrawCountLessThanOrEqualTo(Long value) {
            addCriterion("withdraw_count <=", value, "withdrawCount");
            return (Criteria) this;
        }

        public Criteria andWithdrawCountIn(List<Long> values) {
            addCriterion("withdraw_count in", values, "withdrawCount");
            return (Criteria) this;
        }

        public Criteria andWithdrawCountNotIn(List<Long> values) {
            addCriterion("withdraw_count not in", values, "withdrawCount");
            return (Criteria) this;
        }

        public Criteria andWithdrawCountBetween(Long value1, Long value2) {
            addCriterion("withdraw_count between", value1, value2, "withdrawCount");
            return (Criteria) this;
        }

        public Criteria andWithdrawCountNotBetween(Long value1, Long value2) {
            addCriterion("withdraw_count not between", value1, value2, "withdrawCount");
            return (Criteria) this;
        }

        public Criteria andFreezeCountIsNull() {
            addCriterion("freeze_count is null");
            return (Criteria) this;
        }

        public Criteria andFreezeCountIsNotNull() {
            addCriterion("freeze_count is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeCountEqualTo(Long value) {
            addCriterion("freeze_count =", value, "freezeCount");
            return (Criteria) this;
        }

        public Criteria andFreezeCountNotEqualTo(Long value) {
            addCriterion("freeze_count <>", value, "freezeCount");
            return (Criteria) this;
        }

        public Criteria andFreezeCountGreaterThan(Long value) {
            addCriterion("freeze_count >", value, "freezeCount");
            return (Criteria) this;
        }

        public Criteria andFreezeCountGreaterThanOrEqualTo(Long value) {
            addCriterion("freeze_count >=", value, "freezeCount");
            return (Criteria) this;
        }

        public Criteria andFreezeCountLessThan(Long value) {
            addCriterion("freeze_count <", value, "freezeCount");
            return (Criteria) this;
        }

        public Criteria andFreezeCountLessThanOrEqualTo(Long value) {
            addCriterion("freeze_count <=", value, "freezeCount");
            return (Criteria) this;
        }

        public Criteria andFreezeCountIn(List<Long> values) {
            addCriterion("freeze_count in", values, "freezeCount");
            return (Criteria) this;
        }

        public Criteria andFreezeCountNotIn(List<Long> values) {
            addCriterion("freeze_count not in", values, "freezeCount");
            return (Criteria) this;
        }

        public Criteria andFreezeCountBetween(Long value1, Long value2) {
            addCriterion("freeze_count between", value1, value2, "freezeCount");
            return (Criteria) this;
        }

        public Criteria andFreezeCountNotBetween(Long value1, Long value2) {
            addCriterion("freeze_count not between", value1, value2, "freezeCount");
            return (Criteria) this;
        }

        public Criteria andUnfreezeCountIsNull() {
            addCriterion("unfreeze_count is null");
            return (Criteria) this;
        }

        public Criteria andUnfreezeCountIsNotNull() {
            addCriterion("unfreeze_count is not null");
            return (Criteria) this;
        }

        public Criteria andUnfreezeCountEqualTo(Long value) {
            addCriterion("unfreeze_count =", value, "unfreezeCount");
            return (Criteria) this;
        }

        public Criteria andUnfreezeCountNotEqualTo(Long value) {
            addCriterion("unfreeze_count <>", value, "unfreezeCount");
            return (Criteria) this;
        }

        public Criteria andUnfreezeCountGreaterThan(Long value) {
            addCriterion("unfreeze_count >", value, "unfreezeCount");
            return (Criteria) this;
        }

        public Criteria andUnfreezeCountGreaterThanOrEqualTo(Long value) {
            addCriterion("unfreeze_count >=", value, "unfreezeCount");
            return (Criteria) this;
        }

        public Criteria andUnfreezeCountLessThan(Long value) {
            addCriterion("unfreeze_count <", value, "unfreezeCount");
            return (Criteria) this;
        }

        public Criteria andUnfreezeCountLessThanOrEqualTo(Long value) {
            addCriterion("unfreeze_count <=", value, "unfreezeCount");
            return (Criteria) this;
        }

        public Criteria andUnfreezeCountIn(List<Long> values) {
            addCriterion("unfreeze_count in", values, "unfreezeCount");
            return (Criteria) this;
        }

        public Criteria andUnfreezeCountNotIn(List<Long> values) {
            addCriterion("unfreeze_count not in", values, "unfreezeCount");
            return (Criteria) this;
        }

        public Criteria andUnfreezeCountBetween(Long value1, Long value2) {
            addCriterion("unfreeze_count between", value1, value2, "unfreezeCount");
            return (Criteria) this;
        }

        public Criteria andUnfreezeCountNotBetween(Long value1, Long value2) {
            addCriterion("unfreeze_count not between", value1, value2, "unfreezeCount");
            return (Criteria) this;
        }

        public Criteria andRefundCountIsNull() {
            addCriterion("refund_count is null");
            return (Criteria) this;
        }

        public Criteria andRefundCountIsNotNull() {
            addCriterion("refund_count is not null");
            return (Criteria) this;
        }

        public Criteria andRefundCountEqualTo(Long value) {
            addCriterion("refund_count =", value, "refundCount");
            return (Criteria) this;
        }

        public Criteria andRefundCountNotEqualTo(Long value) {
            addCriterion("refund_count <>", value, "refundCount");
            return (Criteria) this;
        }

        public Criteria andRefundCountGreaterThan(Long value) {
            addCriterion("refund_count >", value, "refundCount");
            return (Criteria) this;
        }

        public Criteria andRefundCountGreaterThanOrEqualTo(Long value) {
            addCriterion("refund_count >=", value, "refundCount");
            return (Criteria) this;
        }

        public Criteria andRefundCountLessThan(Long value) {
            addCriterion("refund_count <", value, "refundCount");
            return (Criteria) this;
        }

        public Criteria andRefundCountLessThanOrEqualTo(Long value) {
            addCriterion("refund_count <=", value, "refundCount");
            return (Criteria) this;
        }

        public Criteria andRefundCountIn(List<Long> values) {
            addCriterion("refund_count in", values, "refundCount");
            return (Criteria) this;
        }

        public Criteria andRefundCountNotIn(List<Long> values) {
            addCriterion("refund_count not in", values, "refundCount");
            return (Criteria) this;
        }

        public Criteria andRefundCountBetween(Long value1, Long value2) {
            addCriterion("refund_count between", value1, value2, "refundCount");
            return (Criteria) this;
        }

        public Criteria andRefundCountNotBetween(Long value1, Long value2) {
            addCriterion("refund_count not between", value1, value2, "refundCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositCountIsNull() {
            addCriterion("supervision_deposit_count is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositCountIsNotNull() {
            addCriterion("supervision_deposit_count is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositCountEqualTo(Long value) {
            addCriterion("supervision_deposit_count =", value, "supervisionDepositCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositCountNotEqualTo(Long value) {
            addCriterion("supervision_deposit_count <>", value, "supervisionDepositCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositCountGreaterThan(Long value) {
            addCriterion("supervision_deposit_count >", value, "supervisionDepositCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositCountGreaterThanOrEqualTo(Long value) {
            addCriterion("supervision_deposit_count >=", value, "supervisionDepositCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositCountLessThan(Long value) {
            addCriterion("supervision_deposit_count <", value, "supervisionDepositCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositCountLessThanOrEqualTo(Long value) {
            addCriterion("supervision_deposit_count <=", value, "supervisionDepositCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositCountIn(List<Long> values) {
            addCriterion("supervision_deposit_count in", values, "supervisionDepositCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositCountNotIn(List<Long> values) {
            addCriterion("supervision_deposit_count not in", values, "supervisionDepositCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositCountBetween(Long value1, Long value2) {
            addCriterion("supervision_deposit_count between", value1, value2, "supervisionDepositCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionDepositCountNotBetween(Long value1, Long value2) {
            addCriterion("supervision_deposit_count not between", value1, value2, "supervisionDepositCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferInCountIsNull() {
            addCriterion("supervision_transfer_in_count is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferInCountIsNotNull() {
            addCriterion("supervision_transfer_in_count is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferInCountEqualTo(Long value) {
            addCriterion("supervision_transfer_in_count =", value, "supervisionTransferInCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferInCountNotEqualTo(Long value) {
            addCriterion("supervision_transfer_in_count <>", value, "supervisionTransferInCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferInCountGreaterThan(Long value) {
            addCriterion("supervision_transfer_in_count >", value, "supervisionTransferInCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferInCountGreaterThanOrEqualTo(Long value) {
            addCriterion("supervision_transfer_in_count >=", value, "supervisionTransferInCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferInCountLessThan(Long value) {
            addCriterion("supervision_transfer_in_count <", value, "supervisionTransferInCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferInCountLessThanOrEqualTo(Long value) {
            addCriterion("supervision_transfer_in_count <=", value, "supervisionTransferInCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferInCountIn(List<Long> values) {
            addCriterion("supervision_transfer_in_count in", values, "supervisionTransferInCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferInCountNotIn(List<Long> values) {
            addCriterion("supervision_transfer_in_count not in", values, "supervisionTransferInCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferInCountBetween(Long value1, Long value2) {
            addCriterion("supervision_transfer_in_count between", value1, value2, "supervisionTransferInCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferInCountNotBetween(Long value1, Long value2) {
            addCriterion("supervision_transfer_in_count not between", value1, value2, "supervisionTransferInCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferOutCountIsNull() {
            addCriterion("supervision_transfer_out_count is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferOutCountIsNotNull() {
            addCriterion("supervision_transfer_out_count is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferOutCountEqualTo(Long value) {
            addCriterion("supervision_transfer_out_count =", value, "supervisionTransferOutCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferOutCountNotEqualTo(Long value) {
            addCriterion("supervision_transfer_out_count <>", value, "supervisionTransferOutCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferOutCountGreaterThan(Long value) {
            addCriterion("supervision_transfer_out_count >", value, "supervisionTransferOutCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferOutCountGreaterThanOrEqualTo(Long value) {
            addCriterion("supervision_transfer_out_count >=", value, "supervisionTransferOutCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferOutCountLessThan(Long value) {
            addCriterion("supervision_transfer_out_count <", value, "supervisionTransferOutCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferOutCountLessThanOrEqualTo(Long value) {
            addCriterion("supervision_transfer_out_count <=", value, "supervisionTransferOutCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferOutCountIn(List<Long> values) {
            addCriterion("supervision_transfer_out_count in", values, "supervisionTransferOutCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferOutCountNotIn(List<Long> values) {
            addCriterion("supervision_transfer_out_count not in", values, "supervisionTransferOutCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferOutCountBetween(Long value1, Long value2) {
            addCriterion("supervision_transfer_out_count between", value1, value2, "supervisionTransferOutCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionTransferOutCountNotBetween(Long value1, Long value2) {
            addCriterion("supervision_transfer_out_count not between", value1, value2, "supervisionTransferOutCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawCountIsNull() {
            addCriterion("supervision_withdraw_count is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawCountIsNotNull() {
            addCriterion("supervision_withdraw_count is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawCountEqualTo(Long value) {
            addCriterion("supervision_withdraw_count =", value, "supervisionWithdrawCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawCountNotEqualTo(Long value) {
            addCriterion("supervision_withdraw_count <>", value, "supervisionWithdrawCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawCountGreaterThan(Long value) {
            addCriterion("supervision_withdraw_count >", value, "supervisionWithdrawCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawCountGreaterThanOrEqualTo(Long value) {
            addCriterion("supervision_withdraw_count >=", value, "supervisionWithdrawCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawCountLessThan(Long value) {
            addCriterion("supervision_withdraw_count <", value, "supervisionWithdrawCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawCountLessThanOrEqualTo(Long value) {
            addCriterion("supervision_withdraw_count <=", value, "supervisionWithdrawCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawCountIn(List<Long> values) {
            addCriterion("supervision_withdraw_count in", values, "supervisionWithdrawCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawCountNotIn(List<Long> values) {
            addCriterion("supervision_withdraw_count not in", values, "supervisionWithdrawCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawCountBetween(Long value1, Long value2) {
            addCriterion("supervision_withdraw_count between", value1, value2, "supervisionWithdrawCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionWithdrawCountNotBetween(Long value1, Long value2) {
            addCriterion("supervision_withdraw_count not between", value1, value2, "supervisionWithdrawCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeCountIsNull() {
            addCriterion("supervision_freeze_count is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeCountIsNotNull() {
            addCriterion("supervision_freeze_count is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeCountEqualTo(Long value) {
            addCriterion("supervision_freeze_count =", value, "supervisionFreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeCountNotEqualTo(Long value) {
            addCriterion("supervision_freeze_count <>", value, "supervisionFreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeCountGreaterThan(Long value) {
            addCriterion("supervision_freeze_count >", value, "supervisionFreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeCountGreaterThanOrEqualTo(Long value) {
            addCriterion("supervision_freeze_count >=", value, "supervisionFreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeCountLessThan(Long value) {
            addCriterion("supervision_freeze_count <", value, "supervisionFreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeCountLessThanOrEqualTo(Long value) {
            addCriterion("supervision_freeze_count <=", value, "supervisionFreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeCountIn(List<Long> values) {
            addCriterion("supervision_freeze_count in", values, "supervisionFreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeCountNotIn(List<Long> values) {
            addCriterion("supervision_freeze_count not in", values, "supervisionFreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeCountBetween(Long value1, Long value2) {
            addCriterion("supervision_freeze_count between", value1, value2, "supervisionFreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionFreezeCountNotBetween(Long value1, Long value2) {
            addCriterion("supervision_freeze_count not between", value1, value2, "supervisionFreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeCountIsNull() {
            addCriterion("supervision_unfreeze_count is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeCountIsNotNull() {
            addCriterion("supervision_unfreeze_count is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeCountEqualTo(Long value) {
            addCriterion("supervision_unfreeze_count =", value, "supervisionUnfreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeCountNotEqualTo(Long value) {
            addCriterion("supervision_unfreeze_count <>", value, "supervisionUnfreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeCountGreaterThan(Long value) {
            addCriterion("supervision_unfreeze_count >", value, "supervisionUnfreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeCountGreaterThanOrEqualTo(Long value) {
            addCriterion("supervision_unfreeze_count >=", value, "supervisionUnfreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeCountLessThan(Long value) {
            addCriterion("supervision_unfreeze_count <", value, "supervisionUnfreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeCountLessThanOrEqualTo(Long value) {
            addCriterion("supervision_unfreeze_count <=", value, "supervisionUnfreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeCountIn(List<Long> values) {
            addCriterion("supervision_unfreeze_count in", values, "supervisionUnfreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeCountNotIn(List<Long> values) {
            addCriterion("supervision_unfreeze_count not in", values, "supervisionUnfreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeCountBetween(Long value1, Long value2) {
            addCriterion("supervision_unfreeze_count between", value1, value2, "supervisionUnfreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionUnfreezeCountNotBetween(Long value1, Long value2) {
            addCriterion("supervision_unfreeze_count not between", value1, value2, "supervisionUnfreezeCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundCountIsNull() {
            addCriterion("supervision_refund_count is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundCountIsNotNull() {
            addCriterion("supervision_refund_count is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundCountEqualTo(Long value) {
            addCriterion("supervision_refund_count =", value, "supervisionRefundCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundCountNotEqualTo(Long value) {
            addCriterion("supervision_refund_count <>", value, "supervisionRefundCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundCountGreaterThan(Long value) {
            addCriterion("supervision_refund_count >", value, "supervisionRefundCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundCountGreaterThanOrEqualTo(Long value) {
            addCriterion("supervision_refund_count >=", value, "supervisionRefundCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundCountLessThan(Long value) {
            addCriterion("supervision_refund_count <", value, "supervisionRefundCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundCountLessThanOrEqualTo(Long value) {
            addCriterion("supervision_refund_count <=", value, "supervisionRefundCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundCountIn(List<Long> values) {
            addCriterion("supervision_refund_count in", values, "supervisionRefundCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundCountNotIn(List<Long> values) {
            addCriterion("supervision_refund_count not in", values, "supervisionRefundCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundCountBetween(Long value1, Long value2) {
            addCriterion("supervision_refund_count between", value1, value2, "supervisionRefundCount");
            return (Criteria) this;
        }

        public Criteria andSupervisionRefundCountNotBetween(Long value1, Long value2) {
            addCriterion("supervision_refund_count not between", value1, value2, "supervisionRefundCount");
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