/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.repayment.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LoanInfoConfig implements Serializable {
    /**
     *id(直接放款信息id)
     */
    private String id;

    /**
     *保理商回款账户账号
     */
    private String factorTransferInAccountNo;

    /**
     *扣款时点
     */
    private String deductionTime;

    /**
     *扣款规则（足额扣款，部分扣款）
     */
    private String deductionRule;

    /**
     *展期天数
     */
    private Integer extensionDays;

    /**
     *展期利率
     */
    private BigDecimal extensionRatio;

    /**
     *罚息率
     */
    private BigDecimal penaltyRate;

    /**
     *提前还款手续费率
     */
    private BigDecimal earlyRepaymentChargeRatio;

    /**
     *账户归集时间
     */
    private String accountAccumulationTime;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     *id(直接放款信息id)
     */
    public String getId() {
        return id;
    }

    /**
     *id(直接放款信息id)
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *保理商回款账户账号
     */
    public String getFactorTransferInAccountNo() {
        return factorTransferInAccountNo;
    }

    /**
     *保理商回款账户账号
     */
    public void setFactorTransferInAccountNo(String factorTransferInAccountNo) {
        this.factorTransferInAccountNo = factorTransferInAccountNo == null ? null : factorTransferInAccountNo.trim();
    }

    /**
     *扣款时点
     */
    public String getDeductionTime() {
        return deductionTime;
    }

    /**
     *扣款时点
     */
    public void setDeductionTime(String deductionTime) {
        this.deductionTime = deductionTime == null ? null : deductionTime.trim();
    }

    /**
     *扣款规则（足额扣款，部分扣款）
     */
    public String getDeductionRule() {
        return deductionRule;
    }

    /**
     *扣款规则（足额扣款，部分扣款）
     */
    public void setDeductionRule(String deductionRule) {
        this.deductionRule = deductionRule == null ? null : deductionRule.trim();
    }

    /**
     *展期天数
     */
    public Integer getExtensionDays() {
        return extensionDays;
    }

    /**
     *展期天数
     */
    public void setExtensionDays(Integer extensionDays) {
        this.extensionDays = extensionDays;
    }

    /**
     *展期利率
     */
    public BigDecimal getExtensionRatio() {
        return extensionRatio;
    }

    /**
     *展期利率
     */
    public void setExtensionRatio(BigDecimal extensionRatio) {
        this.extensionRatio = extensionRatio;
    }

    /**
     *罚息率
     */
    public BigDecimal getPenaltyRate() {
        return penaltyRate;
    }

    /**
     *罚息率
     */
    public void setPenaltyRate(BigDecimal penaltyRate) {
        this.penaltyRate = penaltyRate;
    }

    /**
     *提前还款手续费率
     */
    public BigDecimal getEarlyRepaymentChargeRatio() {
        return earlyRepaymentChargeRatio;
    }

    /**
     *提前还款手续费率
     */
    public void setEarlyRepaymentChargeRatio(BigDecimal earlyRepaymentChargeRatio) {
        this.earlyRepaymentChargeRatio = earlyRepaymentChargeRatio;
    }

    /**
     *账户归集时间
     */
    public String getAccountAccumulationTime() {
        return accountAccumulationTime;
    }

    /**
     *账户归集时间
     */
    public void setAccountAccumulationTime(String accountAccumulationTime) {
        this.accountAccumulationTime = accountAccumulationTime == null ? null : accountAccumulationTime.trim();
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
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        LoanInfoConfig other = (LoanInfoConfig) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFactorTransferInAccountNo() == null ? other.getFactorTransferInAccountNo() == null : this.getFactorTransferInAccountNo().equals(other.getFactorTransferInAccountNo()))
            && (this.getDeductionTime() == null ? other.getDeductionTime() == null : this.getDeductionTime().equals(other.getDeductionTime()))
            && (this.getDeductionRule() == null ? other.getDeductionRule() == null : this.getDeductionRule().equals(other.getDeductionRule()))
            && (this.getExtensionDays() == null ? other.getExtensionDays() == null : this.getExtensionDays().equals(other.getExtensionDays()))
            && (this.getExtensionRatio() == null ? other.getExtensionRatio() == null : this.getExtensionRatio().equals(other.getExtensionRatio()))
            && (this.getPenaltyRate() == null ? other.getPenaltyRate() == null : this.getPenaltyRate().equals(other.getPenaltyRate()))
            && (this.getEarlyRepaymentChargeRatio() == null ? other.getEarlyRepaymentChargeRatio() == null : this.getEarlyRepaymentChargeRatio().equals(other.getEarlyRepaymentChargeRatio()))
            && (this.getAccountAccumulationTime() == null ? other.getAccountAccumulationTime() == null : this.getAccountAccumulationTime().equals(other.getAccountAccumulationTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFactorTransferInAccountNo() == null) ? 0 : getFactorTransferInAccountNo().hashCode());
        result = prime * result + ((getDeductionTime() == null) ? 0 : getDeductionTime().hashCode());
        result = prime * result + ((getDeductionRule() == null) ? 0 : getDeductionRule().hashCode());
        result = prime * result + ((getExtensionDays() == null) ? 0 : getExtensionDays().hashCode());
        result = prime * result + ((getExtensionRatio() == null) ? 0 : getExtensionRatio().hashCode());
        result = prime * result + ((getPenaltyRate() == null) ? 0 : getPenaltyRate().hashCode());
        result = prime * result + ((getEarlyRepaymentChargeRatio() == null) ? 0 : getEarlyRepaymentChargeRatio().hashCode());
        result = prime * result + ((getAccountAccumulationTime() == null) ? 0 : getAccountAccumulationTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}