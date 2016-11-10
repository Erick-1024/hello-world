/**
* Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.flight.finance.common.dto;

import java.io.Serializable;

public class RepaymentDTO implements Serializable {
    /**
    *主键。记录ID。唯一。
    */
    private Integer recordId;

    /**
    *采购商id，唯一
    */
    private String customerId;

    /**
    *采购商的简称，真旅要保证采购商简称是唯一的，不能出现多个采购商的简称相同
    */
    private String customerName;

    /**
    *账期开始时间（yyyy-MM-dd）
    */
    private String paymentStartDate;

    /**
    *账期结束时间（yyyy-MM-dd）
    */
    private String paymentEndDate;

    /**
    *计划还款日期（格式：yyyy-MM-dd）
    */
    private String repaymentDate;

    /**
    *最晚还款日期。格式： yyyy-MM-dd。延时还款的情况下，最晚还款日和计划还款日不等
    */
    private String lateRepaymentDate;

    /**
    *实际还款日期。格式： yyyy-MM-dd。如果逾期未还款，此字段可以为空
    */
    private String actualRepaymentDate;

    /**
    *应还金额，精确到分
    */
    private Long accountAmount;

    /**
    *实还金额。精确到分
    */
    private Long payedAmount;

    private static final long serialVersionUID = 1L;

    /**
    *主键。记录ID。唯一。
    */
    public Integer getRecordId() {
        return recordId;
    }

    /**
    *主键。记录ID。唯一。
    */
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    /**
    *采购商id，唯一
    */
    public String getCustomerId() {
        return customerId;
    }

    /**
    *采购商id，唯一
    */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
    *采购商的简称，真旅要保证采购商简称是唯一的，不能出现多个采购商的简称相同
    */
    public String getCustomerName() {
        return customerName;
    }

    /**
    *采购商的简称，真旅要保证采购商简称是唯一的，不能出现多个采购商的简称相同
    */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    /**
    *账期开始时间（yyyy-MM-dd）
    */
    public String getPaymentStartDate() {
        return paymentStartDate;
    }

    /**
    *账期开始时间（yyyy-MM-dd）
    */
    public void setPaymentStartDate(String paymentStartDate) {
        this.paymentStartDate = paymentStartDate == null ? null : paymentStartDate.trim();
    }

    /**
    *账期结束时间（yyyy-MM-dd）
    */
    public String getPaymentEndDate() {
        return paymentEndDate;
    }

    /**
    *账期结束时间（yyyy-MM-dd）
    */
    public void setPaymentEndDate(String paymentEndDate) {
        this.paymentEndDate = paymentEndDate == null ? null : paymentEndDate.trim();
    }

    /**
    *计划还款日期（格式：yyyy-MM-dd）
    */
    public String getRepaymentDate() {
        return repaymentDate;
    }

    /**
    *计划还款日期（格式：yyyy-MM-dd）
    */
    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate == null ? null : repaymentDate.trim();
    }

    /**
    *最晚还款日期。格式： yyyy-MM-dd。延时还款的情况下，最晚还款日和计划还款日不等
    */
    public String getLateRepaymentDate() {
        return lateRepaymentDate;
    }

    /**
    *最晚还款日期。格式： yyyy-MM-dd。延时还款的情况下，最晚还款日和计划还款日不等
    */
    public void setLateRepaymentDate(String lateRepaymentDate) {
        this.lateRepaymentDate = lateRepaymentDate == null ? null : lateRepaymentDate.trim();
    }

    /**
    *实际还款日期。格式： yyyy-MM-dd。如果逾期未还款，此字段可以为空
    */
    public String getActualRepaymentDate() {
        return actualRepaymentDate;
    }

    /**
    *实际还款日期。格式： yyyy-MM-dd。如果逾期未还款，此字段可以为空
    */
    public void setActualRepaymentDate(String actualRepaymentDate) {
        this.actualRepaymentDate = actualRepaymentDate == null ? null : actualRepaymentDate.trim();
    }

    /**
    *应还金额，精确到分
    */
    public Long getAccountAmount() {
        return accountAmount;
    }

    /**
    *应还金额，精确到分
    */
    public void setAccountAmount(Long accountAmount) {
        this.accountAmount = accountAmount;
    }

    /**
    *实还金额。精确到分
    */
    public Long getPayedAmount() {
        return payedAmount;
    }

    /**
    *实还金额。精确到分
    */
    public void setPayedAmount(Long payedAmount) {
        this.payedAmount = payedAmount;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RepaymentDTO other = (RepaymentDTO) that;
        return (this.getRecordId() == null ? other.getRecordId() == null : this.getRecordId().equals(other.getRecordId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getPaymentStartDate() == null ? other.getPaymentStartDate() == null : this.getPaymentStartDate().equals(other.getPaymentStartDate()))
            && (this.getPaymentEndDate() == null ? other.getPaymentEndDate() == null : this.getPaymentEndDate().equals(other.getPaymentEndDate()))
            && (this.getRepaymentDate() == null ? other.getRepaymentDate() == null : this.getRepaymentDate().equals(other.getRepaymentDate()))
            && (this.getLateRepaymentDate() == null ? other.getLateRepaymentDate() == null : this.getLateRepaymentDate().equals(other.getLateRepaymentDate()))
            && (this.getActualRepaymentDate() == null ? other.getActualRepaymentDate() == null : this.getActualRepaymentDate().equals(other.getActualRepaymentDate()))
            && (this.getAccountAmount() == null ? other.getAccountAmount() == null : this.getAccountAmount().equals(other.getAccountAmount()))
            && (this.getPayedAmount() == null ? other.getPayedAmount() == null : this.getPayedAmount().equals(other.getPayedAmount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRecordId() == null) ? 0 : getRecordId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getPaymentStartDate() == null) ? 0 : getPaymentStartDate().hashCode());
        result = prime * result + ((getPaymentEndDate() == null) ? 0 : getPaymentEndDate().hashCode());
        result = prime * result + ((getRepaymentDate() == null) ? 0 : getRepaymentDate().hashCode());
        result = prime * result + ((getLateRepaymentDate() == null) ? 0 : getLateRepaymentDate().hashCode());
        result = prime * result + ((getActualRepaymentDate() == null) ? 0 : getActualRepaymentDate().hashCode());
        result = prime * result + ((getAccountAmount() == null) ? 0 : getAccountAmount().hashCode());
        result = prime * result + ((getPayedAmount() == null) ? 0 : getPayedAmount().hashCode());
        return result;
    }
}