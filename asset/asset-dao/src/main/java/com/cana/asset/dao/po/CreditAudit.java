/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.util.Date;

public class CreditAudit implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *操作类型
     */
    private String type;

    /**
     *业务合同号
     */
    private String creditId;

    /**
     *授信类型(综合,单笔)
     */
    private String creditMode;

    /**
     *币种
     */
    private String currency;

    /**
     *变化前申请金额
     */
    private Long preTotalLimit;

    /**
     *申请金额
     */
    private Long totalLimit;

    /**
     *变化前可用金额
     */
    private Long preAvailableLimit;

    /**
     *可用金额
     */
    private Long availableLimit;

    /**
     *变化前已用金额
     */
    private Long preUsedLimit;

    /**
     *已用金额
     */
    private Long usedLimit;

    /**
     *客户id
     */
    private String customerId;

    /**
     *变化前生效日期
     */
    private String preEffectiveDate;

    /**
     *生效日
     */
    private String effectiveDate;

    /**
     *变化前到期日
     */
    private String preDueDate;

    /**
     *到期日
     */
    private String dueDate;

    /**
     *变化前授信状态
     */
    private String preCreditStatus;

    /**
     *授信状态
     */
    private String creditStatus;

    /**
     *操作人id
     */
    private String operatorId;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *保理商id
     */
    private String factorId;

    /**
     *业务合同号
     */
    private String bussinessContractNo;

    private static final long serialVersionUID = 1L;

    /**
     *主键
     */
    public String getId() {
        return id;
    }

    /**
     *主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *操作类型
     */
    public String getType() {
        return type;
    }

    /**
     *操作类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     *业务合同号
     */
    public String getCreditId() {
        return creditId;
    }

    /**
     *业务合同号
     */
    public void setCreditId(String creditId) {
        this.creditId = creditId == null ? null : creditId.trim();
    }

    /**
     *授信类型(综合,单笔)
     */
    public String getCreditMode() {
        return creditMode;
    }

    /**
     *授信类型(综合,单笔)
     */
    public void setCreditMode(String creditMode) {
        this.creditMode = creditMode == null ? null : creditMode.trim();
    }

    /**
     *币种
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *币种
     */
    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    /**
     *变化前申请金额
     */
    public Long getPreTotalLimit() {
        return preTotalLimit;
    }

    /**
     *变化前申请金额
     */
    public void setPreTotalLimit(Long preTotalLimit) {
        this.preTotalLimit = preTotalLimit;
    }

    /**
     *申请金额
     */
    public Long getTotalLimit() {
        return totalLimit;
    }

    /**
     *申请金额
     */
    public void setTotalLimit(Long totalLimit) {
        this.totalLimit = totalLimit;
    }

    /**
     *变化前可用金额
     */
    public Long getPreAvailableLimit() {
        return preAvailableLimit;
    }

    /**
     *变化前可用金额
     */
    public void setPreAvailableLimit(Long preAvailableLimit) {
        this.preAvailableLimit = preAvailableLimit;
    }

    /**
     *可用金额
     */
    public Long getAvailableLimit() {
        return availableLimit;
    }

    /**
     *可用金额
     */
    public void setAvailableLimit(Long availableLimit) {
        this.availableLimit = availableLimit;
    }

    /**
     *变化前已用金额
     */
    public Long getPreUsedLimit() {
        return preUsedLimit;
    }

    /**
     *变化前已用金额
     */
    public void setPreUsedLimit(Long preUsedLimit) {
        this.preUsedLimit = preUsedLimit;
    }

    /**
     *已用金额
     */
    public Long getUsedLimit() {
        return usedLimit;
    }

    /**
     *已用金额
     */
    public void setUsedLimit(Long usedLimit) {
        this.usedLimit = usedLimit;
    }

    /**
     *客户id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *客户id
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     *变化前生效日期
     */
    public String getPreEffectiveDate() {
        return preEffectiveDate;
    }

    /**
     *变化前生效日期
     */
    public void setPreEffectiveDate(String preEffectiveDate) {
        this.preEffectiveDate = preEffectiveDate == null ? null : preEffectiveDate.trim();
    }

    /**
     *生效日
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     *生效日
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate == null ? null : effectiveDate.trim();
    }

    /**
     *变化前到期日
     */
    public String getPreDueDate() {
        return preDueDate;
    }

    /**
     *变化前到期日
     */
    public void setPreDueDate(String preDueDate) {
        this.preDueDate = preDueDate == null ? null : preDueDate.trim();
    }

    /**
     *到期日
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     *到期日
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate == null ? null : dueDate.trim();
    }

    /**
     *变化前授信状态
     */
    public String getPreCreditStatus() {
        return preCreditStatus;
    }

    /**
     *变化前授信状态
     */
    public void setPreCreditStatus(String preCreditStatus) {
        this.preCreditStatus = preCreditStatus == null ? null : preCreditStatus.trim();
    }

    /**
     *授信状态
     */
    public String getCreditStatus() {
        return creditStatus;
    }

    /**
     *授信状态
     */
    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus == null ? null : creditStatus.trim();
    }

    /**
     *操作人id
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     *操作人id
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    /**
     *创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *保理商id
     */
    public String getFactorId() {
        return factorId;
    }

    /**
     *保理商id
     */
    public void setFactorId(String factorId) {
        this.factorId = factorId == null ? null : factorId.trim();
    }

    /**
     *业务合同号
     */
    public String getBussinessContractNo() {
        return bussinessContractNo;
    }

    /**
     *业务合同号
     */
    public void setBussinessContractNo(String bussinessContractNo) {
        this.bussinessContractNo = bussinessContractNo == null ? null : bussinessContractNo.trim();
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
        CreditAudit other = (CreditAudit) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCreditId() == null ? other.getCreditId() == null : this.getCreditId().equals(other.getCreditId()))
            && (this.getCreditMode() == null ? other.getCreditMode() == null : this.getCreditMode().equals(other.getCreditMode()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getPreTotalLimit() == null ? other.getPreTotalLimit() == null : this.getPreTotalLimit().equals(other.getPreTotalLimit()))
            && (this.getTotalLimit() == null ? other.getTotalLimit() == null : this.getTotalLimit().equals(other.getTotalLimit()))
            && (this.getPreAvailableLimit() == null ? other.getPreAvailableLimit() == null : this.getPreAvailableLimit().equals(other.getPreAvailableLimit()))
            && (this.getAvailableLimit() == null ? other.getAvailableLimit() == null : this.getAvailableLimit().equals(other.getAvailableLimit()))
            && (this.getPreUsedLimit() == null ? other.getPreUsedLimit() == null : this.getPreUsedLimit().equals(other.getPreUsedLimit()))
            && (this.getUsedLimit() == null ? other.getUsedLimit() == null : this.getUsedLimit().equals(other.getUsedLimit()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getPreEffectiveDate() == null ? other.getPreEffectiveDate() == null : this.getPreEffectiveDate().equals(other.getPreEffectiveDate()))
            && (this.getEffectiveDate() == null ? other.getEffectiveDate() == null : this.getEffectiveDate().equals(other.getEffectiveDate()))
            && (this.getPreDueDate() == null ? other.getPreDueDate() == null : this.getPreDueDate().equals(other.getPreDueDate()))
            && (this.getDueDate() == null ? other.getDueDate() == null : this.getDueDate().equals(other.getDueDate()))
            && (this.getPreCreditStatus() == null ? other.getPreCreditStatus() == null : this.getPreCreditStatus().equals(other.getPreCreditStatus()))
            && (this.getCreditStatus() == null ? other.getCreditStatus() == null : this.getCreditStatus().equals(other.getCreditStatus()))
            && (this.getOperatorId() == null ? other.getOperatorId() == null : this.getOperatorId().equals(other.getOperatorId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getFactorId() == null ? other.getFactorId() == null : this.getFactorId().equals(other.getFactorId()))
            && (this.getBussinessContractNo() == null ? other.getBussinessContractNo() == null : this.getBussinessContractNo().equals(other.getBussinessContractNo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCreditId() == null) ? 0 : getCreditId().hashCode());
        result = prime * result + ((getCreditMode() == null) ? 0 : getCreditMode().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getPreTotalLimit() == null) ? 0 : getPreTotalLimit().hashCode());
        result = prime * result + ((getTotalLimit() == null) ? 0 : getTotalLimit().hashCode());
        result = prime * result + ((getPreAvailableLimit() == null) ? 0 : getPreAvailableLimit().hashCode());
        result = prime * result + ((getAvailableLimit() == null) ? 0 : getAvailableLimit().hashCode());
        result = prime * result + ((getPreUsedLimit() == null) ? 0 : getPreUsedLimit().hashCode());
        result = prime * result + ((getUsedLimit() == null) ? 0 : getUsedLimit().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getPreEffectiveDate() == null) ? 0 : getPreEffectiveDate().hashCode());
        result = prime * result + ((getEffectiveDate() == null) ? 0 : getEffectiveDate().hashCode());
        result = prime * result + ((getPreDueDate() == null) ? 0 : getPreDueDate().hashCode());
        result = prime * result + ((getDueDate() == null) ? 0 : getDueDate().hashCode());
        result = prime * result + ((getPreCreditStatus() == null) ? 0 : getPreCreditStatus().hashCode());
        result = prime * result + ((getCreditStatus() == null) ? 0 : getCreditStatus().hashCode());
        result = prime * result + ((getOperatorId() == null) ? 0 : getOperatorId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getFactorId() == null) ? 0 : getFactorId().hashCode());
        result = prime * result + ((getBussinessContractNo() == null) ? 0 : getBussinessContractNo().hashCode());
        return result;
    }
}