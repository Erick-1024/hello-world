/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.repayment.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RepaymentPenaltyProductDetail implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *汇总id
     */
    private String summaryId;

    /**
     *还款计划Id
     */
    private String repaymentPlanId;

    /**
     *
     */
    private String loanInfoId;

    /**
     *新增本金罚息
     */
    private Long plusPrincipalPenalty;

    /**
     *新增利息罚息
     */
    private Long plusInterestPenalty;

    /**
     *新增服务费罚息
     */
    private Long plusServiceChargePenalty;

    /**
     *逾期本金
     */
    private Long overduePrincipal;

    /**
     *逾期利息
     */
    private Long overdueInterest;

    /**
     *逾期服务费
     */
    private Long overdueServiceCharge;

    /**
     *罚息率
     */
    private BigDecimal penaltyRate;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    /**
     *
     */
    private String date;

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
     *汇总id
     */
    public String getSummaryId() {
        return summaryId;
    }

    /**
     *汇总id
     */
    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId == null ? null : summaryId.trim();
    }

    /**
     *还款计划Id
     */
    public String getRepaymentPlanId() {
        return repaymentPlanId;
    }

    /**
     *还款计划Id
     */
    public void setRepaymentPlanId(String repaymentPlanId) {
        this.repaymentPlanId = repaymentPlanId == null ? null : repaymentPlanId.trim();
    }

    /**
     *
     */
    public String getLoanInfoId() {
        return loanInfoId;
    }

    /**
     *
     */
    public void setLoanInfoId(String loanInfoId) {
        this.loanInfoId = loanInfoId == null ? null : loanInfoId.trim();
    }

    /**
     *新增本金罚息
     */
    public Long getPlusPrincipalPenalty() {
        return plusPrincipalPenalty;
    }

    /**
     *新增本金罚息
     */
    public void setPlusPrincipalPenalty(Long plusPrincipalPenalty) {
        this.plusPrincipalPenalty = plusPrincipalPenalty;
    }

    /**
     *新增利息罚息
     */
    public Long getPlusInterestPenalty() {
        return plusInterestPenalty;
    }

    /**
     *新增利息罚息
     */
    public void setPlusInterestPenalty(Long plusInterestPenalty) {
        this.plusInterestPenalty = plusInterestPenalty;
    }

    /**
     *新增服务费罚息
     */
    public Long getPlusServiceChargePenalty() {
        return plusServiceChargePenalty;
    }

    /**
     *新增服务费罚息
     */
    public void setPlusServiceChargePenalty(Long plusServiceChargePenalty) {
        this.plusServiceChargePenalty = plusServiceChargePenalty;
    }

    /**
     *逾期本金
     */
    public Long getOverduePrincipal() {
        return overduePrincipal;
    }

    /**
     *逾期本金
     */
    public void setOverduePrincipal(Long overduePrincipal) {
        this.overduePrincipal = overduePrincipal;
    }

    /**
     *逾期利息
     */
    public Long getOverdueInterest() {
        return overdueInterest;
    }

    /**
     *逾期利息
     */
    public void setOverdueInterest(Long overdueInterest) {
        this.overdueInterest = overdueInterest;
    }

    /**
     *逾期服务费
     */
    public Long getOverdueServiceCharge() {
        return overdueServiceCharge;
    }

    /**
     *逾期服务费
     */
    public void setOverdueServiceCharge(Long overdueServiceCharge) {
        this.overdueServiceCharge = overdueServiceCharge;
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

    /**
     *
     */
    public String getDate() {
        return date;
    }

    /**
     *
     */
    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
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
        RepaymentPenaltyProductDetail other = (RepaymentPenaltyProductDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSummaryId() == null ? other.getSummaryId() == null : this.getSummaryId().equals(other.getSummaryId()))
            && (this.getRepaymentPlanId() == null ? other.getRepaymentPlanId() == null : this.getRepaymentPlanId().equals(other.getRepaymentPlanId()))
            && (this.getLoanInfoId() == null ? other.getLoanInfoId() == null : this.getLoanInfoId().equals(other.getLoanInfoId()))
            && (this.getPlusPrincipalPenalty() == null ? other.getPlusPrincipalPenalty() == null : this.getPlusPrincipalPenalty().equals(other.getPlusPrincipalPenalty()))
            && (this.getPlusInterestPenalty() == null ? other.getPlusInterestPenalty() == null : this.getPlusInterestPenalty().equals(other.getPlusInterestPenalty()))
            && (this.getPlusServiceChargePenalty() == null ? other.getPlusServiceChargePenalty() == null : this.getPlusServiceChargePenalty().equals(other.getPlusServiceChargePenalty()))
            && (this.getOverduePrincipal() == null ? other.getOverduePrincipal() == null : this.getOverduePrincipal().equals(other.getOverduePrincipal()))
            && (this.getOverdueInterest() == null ? other.getOverdueInterest() == null : this.getOverdueInterest().equals(other.getOverdueInterest()))
            && (this.getOverdueServiceCharge() == null ? other.getOverdueServiceCharge() == null : this.getOverdueServiceCharge().equals(other.getOverdueServiceCharge()))
            && (this.getPenaltyRate() == null ? other.getPenaltyRate() == null : this.getPenaltyRate().equals(other.getPenaltyRate()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSummaryId() == null) ? 0 : getSummaryId().hashCode());
        result = prime * result + ((getRepaymentPlanId() == null) ? 0 : getRepaymentPlanId().hashCode());
        result = prime * result + ((getLoanInfoId() == null) ? 0 : getLoanInfoId().hashCode());
        result = prime * result + ((getPlusPrincipalPenalty() == null) ? 0 : getPlusPrincipalPenalty().hashCode());
        result = prime * result + ((getPlusInterestPenalty() == null) ? 0 : getPlusInterestPenalty().hashCode());
        result = prime * result + ((getPlusServiceChargePenalty() == null) ? 0 : getPlusServiceChargePenalty().hashCode());
        result = prime * result + ((getOverduePrincipal() == null) ? 0 : getOverduePrincipal().hashCode());
        result = prime * result + ((getOverdueInterest() == null) ? 0 : getOverdueInterest().hashCode());
        result = prime * result + ((getOverdueServiceCharge() == null) ? 0 : getOverdueServiceCharge().hashCode());
        result = prime * result + ((getPenaltyRate() == null) ? 0 : getPenaltyRate().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        return result;
    }
}