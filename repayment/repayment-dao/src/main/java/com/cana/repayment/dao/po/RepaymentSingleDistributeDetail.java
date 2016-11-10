/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.repayment.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RepaymentSingleDistributeDetail implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *关联者Id（还款计划、费用）
     */
    private String relatedId;

    /**
     *关联者类型
     */
    private String relatedType;

    /**
     *还正常本金
     */
    private Long payNormalPrincipal;

    /**
     *还正常利息
     */
    private Long payNormalInterest;

    /**
     *还正常服务费
     */
    private Long payNormalServiceCharge;

    /**
     *提前还款手续费
     */
    private Long earlyRepaymentCharge;

    /**
     *提前还款手续费率
     */
    private BigDecimal earlyRepaymentChargeRatio;

    /**
     *还展期费用
     */
    private Long payExtensionCharge;

    /**
     *还逾期本金
     */
    private Long payOverduePrincipal;

    /**
     *还逾期利息
     */
    private Long payOverdueInterest;

    /**
     *还逾期服务费
     */
    private Long payOverdueServiceCharge;

    /**
     *还逾期本金罚息
     */
    private Long payOverduePrincipalPenalty;

    /**
     *还逾期利息罚息
     */
    private Long payOverdueInterestPenalty;

    /**
     *还逾期服务费罚息
     */
    private Long payOverdueServiceChargePenalty;

    /**
     *还其他罚息
     */
    private Long payOtherPenalty;

    /**
     *还固定费用
     */
    private Long payExpense;

    /**
     *还款汇总表Id
     */
    private String repaymentSingleCollectId;

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
     *关联者Id（还款计划、费用）
     */
    public String getRelatedId() {
        return relatedId;
    }

    /**
     *关联者Id（还款计划、费用）
     */
    public void setRelatedId(String relatedId) {
        this.relatedId = relatedId == null ? null : relatedId.trim();
    }

    /**
     *关联者类型
     */
    public String getRelatedType() {
        return relatedType;
    }

    /**
     *关联者类型
     */
    public void setRelatedType(String relatedType) {
        this.relatedType = relatedType == null ? null : relatedType.trim();
    }

    /**
     *还正常本金
     */
    public Long getPayNormalPrincipal() {
        return payNormalPrincipal;
    }

    /**
     *还正常本金
     */
    public void setPayNormalPrincipal(Long payNormalPrincipal) {
        this.payNormalPrincipal = payNormalPrincipal;
    }

    /**
     *还正常利息
     */
    public Long getPayNormalInterest() {
        return payNormalInterest;
    }

    /**
     *还正常利息
     */
    public void setPayNormalInterest(Long payNormalInterest) {
        this.payNormalInterest = payNormalInterest;
    }

    /**
     *还正常服务费
     */
    public Long getPayNormalServiceCharge() {
        return payNormalServiceCharge;
    }

    /**
     *还正常服务费
     */
    public void setPayNormalServiceCharge(Long payNormalServiceCharge) {
        this.payNormalServiceCharge = payNormalServiceCharge;
    }

    /**
     *提前还款手续费
     */
    public Long getEarlyRepaymentCharge() {
        return earlyRepaymentCharge;
    }

    /**
     *提前还款手续费
     */
    public void setEarlyRepaymentCharge(Long earlyRepaymentCharge) {
        this.earlyRepaymentCharge = earlyRepaymentCharge;
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
     *还展期费用
     */
    public Long getPayExtensionCharge() {
        return payExtensionCharge;
    }

    /**
     *还展期费用
     */
    public void setPayExtensionCharge(Long payExtensionCharge) {
        this.payExtensionCharge = payExtensionCharge;
    }

    /**
     *还逾期本金
     */
    public Long getPayOverduePrincipal() {
        return payOverduePrincipal;
    }

    /**
     *还逾期本金
     */
    public void setPayOverduePrincipal(Long payOverduePrincipal) {
        this.payOverduePrincipal = payOverduePrincipal;
    }

    /**
     *还逾期利息
     */
    public Long getPayOverdueInterest() {
        return payOverdueInterest;
    }

    /**
     *还逾期利息
     */
    public void setPayOverdueInterest(Long payOverdueInterest) {
        this.payOverdueInterest = payOverdueInterest;
    }

    /**
     *还逾期服务费
     */
    public Long getPayOverdueServiceCharge() {
        return payOverdueServiceCharge;
    }

    /**
     *还逾期服务费
     */
    public void setPayOverdueServiceCharge(Long payOverdueServiceCharge) {
        this.payOverdueServiceCharge = payOverdueServiceCharge;
    }

    /**
     *还逾期本金罚息
     */
    public Long getPayOverduePrincipalPenalty() {
        return payOverduePrincipalPenalty;
    }

    /**
     *还逾期本金罚息
     */
    public void setPayOverduePrincipalPenalty(Long payOverduePrincipalPenalty) {
        this.payOverduePrincipalPenalty = payOverduePrincipalPenalty;
    }

    /**
     *还逾期利息罚息
     */
    public Long getPayOverdueInterestPenalty() {
        return payOverdueInterestPenalty;
    }

    /**
     *还逾期利息罚息
     */
    public void setPayOverdueInterestPenalty(Long payOverdueInterestPenalty) {
        this.payOverdueInterestPenalty = payOverdueInterestPenalty;
    }

    /**
     *还逾期服务费罚息
     */
    public Long getPayOverdueServiceChargePenalty() {
        return payOverdueServiceChargePenalty;
    }

    /**
     *还逾期服务费罚息
     */
    public void setPayOverdueServiceChargePenalty(Long payOverdueServiceChargePenalty) {
        this.payOverdueServiceChargePenalty = payOverdueServiceChargePenalty;
    }

    /**
     *还其他罚息
     */
    public Long getPayOtherPenalty() {
        return payOtherPenalty;
    }

    /**
     *还其他罚息
     */
    public void setPayOtherPenalty(Long payOtherPenalty) {
        this.payOtherPenalty = payOtherPenalty;
    }

    /**
     *还固定费用
     */
    public Long getPayExpense() {
        return payExpense;
    }

    /**
     *还固定费用
     */
    public void setPayExpense(Long payExpense) {
        this.payExpense = payExpense;
    }

    /**
     *还款汇总表Id
     */
    public String getRepaymentSingleCollectId() {
        return repaymentSingleCollectId;
    }

    /**
     *还款汇总表Id
     */
    public void setRepaymentSingleCollectId(String repaymentSingleCollectId) {
        this.repaymentSingleCollectId = repaymentSingleCollectId == null ? null : repaymentSingleCollectId.trim();
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
        RepaymentSingleDistributeDetail other = (RepaymentSingleDistributeDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRelatedId() == null ? other.getRelatedId() == null : this.getRelatedId().equals(other.getRelatedId()))
            && (this.getRelatedType() == null ? other.getRelatedType() == null : this.getRelatedType().equals(other.getRelatedType()))
            && (this.getPayNormalPrincipal() == null ? other.getPayNormalPrincipal() == null : this.getPayNormalPrincipal().equals(other.getPayNormalPrincipal()))
            && (this.getPayNormalInterest() == null ? other.getPayNormalInterest() == null : this.getPayNormalInterest().equals(other.getPayNormalInterest()))
            && (this.getPayNormalServiceCharge() == null ? other.getPayNormalServiceCharge() == null : this.getPayNormalServiceCharge().equals(other.getPayNormalServiceCharge()))
            && (this.getEarlyRepaymentCharge() == null ? other.getEarlyRepaymentCharge() == null : this.getEarlyRepaymentCharge().equals(other.getEarlyRepaymentCharge()))
            && (this.getEarlyRepaymentChargeRatio() == null ? other.getEarlyRepaymentChargeRatio() == null : this.getEarlyRepaymentChargeRatio().equals(other.getEarlyRepaymentChargeRatio()))
            && (this.getPayExtensionCharge() == null ? other.getPayExtensionCharge() == null : this.getPayExtensionCharge().equals(other.getPayExtensionCharge()))
            && (this.getPayOverduePrincipal() == null ? other.getPayOverduePrincipal() == null : this.getPayOverduePrincipal().equals(other.getPayOverduePrincipal()))
            && (this.getPayOverdueInterest() == null ? other.getPayOverdueInterest() == null : this.getPayOverdueInterest().equals(other.getPayOverdueInterest()))
            && (this.getPayOverdueServiceCharge() == null ? other.getPayOverdueServiceCharge() == null : this.getPayOverdueServiceCharge().equals(other.getPayOverdueServiceCharge()))
            && (this.getPayOverduePrincipalPenalty() == null ? other.getPayOverduePrincipalPenalty() == null : this.getPayOverduePrincipalPenalty().equals(other.getPayOverduePrincipalPenalty()))
            && (this.getPayOverdueInterestPenalty() == null ? other.getPayOverdueInterestPenalty() == null : this.getPayOverdueInterestPenalty().equals(other.getPayOverdueInterestPenalty()))
            && (this.getPayOverdueServiceChargePenalty() == null ? other.getPayOverdueServiceChargePenalty() == null : this.getPayOverdueServiceChargePenalty().equals(other.getPayOverdueServiceChargePenalty()))
            && (this.getPayOtherPenalty() == null ? other.getPayOtherPenalty() == null : this.getPayOtherPenalty().equals(other.getPayOtherPenalty()))
            && (this.getPayExpense() == null ? other.getPayExpense() == null : this.getPayExpense().equals(other.getPayExpense()))
            && (this.getRepaymentSingleCollectId() == null ? other.getRepaymentSingleCollectId() == null : this.getRepaymentSingleCollectId().equals(other.getRepaymentSingleCollectId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRelatedId() == null) ? 0 : getRelatedId().hashCode());
        result = prime * result + ((getRelatedType() == null) ? 0 : getRelatedType().hashCode());
        result = prime * result + ((getPayNormalPrincipal() == null) ? 0 : getPayNormalPrincipal().hashCode());
        result = prime * result + ((getPayNormalInterest() == null) ? 0 : getPayNormalInterest().hashCode());
        result = prime * result + ((getPayNormalServiceCharge() == null) ? 0 : getPayNormalServiceCharge().hashCode());
        result = prime * result + ((getEarlyRepaymentCharge() == null) ? 0 : getEarlyRepaymentCharge().hashCode());
        result = prime * result + ((getEarlyRepaymentChargeRatio() == null) ? 0 : getEarlyRepaymentChargeRatio().hashCode());
        result = prime * result + ((getPayExtensionCharge() == null) ? 0 : getPayExtensionCharge().hashCode());
        result = prime * result + ((getPayOverduePrincipal() == null) ? 0 : getPayOverduePrincipal().hashCode());
        result = prime * result + ((getPayOverdueInterest() == null) ? 0 : getPayOverdueInterest().hashCode());
        result = prime * result + ((getPayOverdueServiceCharge() == null) ? 0 : getPayOverdueServiceCharge().hashCode());
        result = prime * result + ((getPayOverduePrincipalPenalty() == null) ? 0 : getPayOverduePrincipalPenalty().hashCode());
        result = prime * result + ((getPayOverdueInterestPenalty() == null) ? 0 : getPayOverdueInterestPenalty().hashCode());
        result = prime * result + ((getPayOverdueServiceChargePenalty() == null) ? 0 : getPayOverdueServiceChargePenalty().hashCode());
        result = prime * result + ((getPayOtherPenalty() == null) ? 0 : getPayOtherPenalty().hashCode());
        result = prime * result + ((getPayExpense() == null) ? 0 : getPayExpense().hashCode());
        result = prime * result + ((getRepaymentSingleCollectId() == null) ? 0 : getRepaymentSingleCollectId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}