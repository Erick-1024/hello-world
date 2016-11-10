/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.util.Date;

public class Credit implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     *授信类型(综合,单笔)
     */
    private String creditMode;

    /**
     *币种
     */
    private String currency;

    /**
     *申请金额
     */
    private Long totalLimit;

    /**
     *已用金额
     */
    private Long usedLimit;

    /**
     *客户id
     */
    private String customerId;

    /**
     *
     */
    private String factorId;

    /**
     *生效日
     */
    private String effectiveDate;

    /**
     *保理商id
     */
    private String dueDate;

    /**
     *授信状态
     */
    private String status;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date upateTime;

    /**
     *业务合同号
     */
    private String businessContractNo;

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
     *
     */
    public String getFactorId() {
        return factorId;
    }

    /**
     *
     */
    public void setFactorId(String factorId) {
        this.factorId = factorId == null ? null : factorId.trim();
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
     *保理商id
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     *保理商id
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate == null ? null : dueDate.trim();
    }

    /**
     *授信状态
     */
    public String getStatus() {
        return status;
    }

    /**
     *授信状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
     *更新时间
     */
    public Date getUpateTime() {
        return upateTime;
    }

    /**
     *更新时间
     */
    public void setUpateTime(Date upateTime) {
        this.upateTime = upateTime;
    }

    /**
     *业务合同号
     */
    public String getBusinessContractNo() {
        return businessContractNo;
    }

    /**
     *业务合同号
     */
    public void setBusinessContractNo(String businessContractNo) {
        this.businessContractNo = businessContractNo == null ? null : businessContractNo.trim();
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
        Credit other = (Credit) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreditMode() == null ? other.getCreditMode() == null : this.getCreditMode().equals(other.getCreditMode()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getTotalLimit() == null ? other.getTotalLimit() == null : this.getTotalLimit().equals(other.getTotalLimit()))
            && (this.getUsedLimit() == null ? other.getUsedLimit() == null : this.getUsedLimit().equals(other.getUsedLimit()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getFactorId() == null ? other.getFactorId() == null : this.getFactorId().equals(other.getFactorId()))
            && (this.getEffectiveDate() == null ? other.getEffectiveDate() == null : this.getEffectiveDate().equals(other.getEffectiveDate()))
            && (this.getDueDate() == null ? other.getDueDate() == null : this.getDueDate().equals(other.getDueDate()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpateTime() == null ? other.getUpateTime() == null : this.getUpateTime().equals(other.getUpateTime()))
            && (this.getBusinessContractNo() == null ? other.getBusinessContractNo() == null : this.getBusinessContractNo().equals(other.getBusinessContractNo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreditMode() == null) ? 0 : getCreditMode().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getTotalLimit() == null) ? 0 : getTotalLimit().hashCode());
        result = prime * result + ((getUsedLimit() == null) ? 0 : getUsedLimit().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getFactorId() == null) ? 0 : getFactorId().hashCode());
        result = prime * result + ((getEffectiveDate() == null) ? 0 : getEffectiveDate().hashCode());
        result = prime * result + ((getDueDate() == null) ? 0 : getDueDate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpateTime() == null) ? 0 : getUpateTime().hashCode());
        result = prime * result + ((getBusinessContractNo() == null) ? 0 : getBusinessContractNo().hashCode());
        return result;
    }
}