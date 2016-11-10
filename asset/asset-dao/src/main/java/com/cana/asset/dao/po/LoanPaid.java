/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.util.Date;

public class LoanPaid implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *放款信息Id
     */
    private String loanInfoId;

    /**
     *还款的还款计划Id
     */
    private String loanPlanId;

    /**
     *还款总金额
     */
    private Long paidAmount;

    /**
     *已还本金
     */
    private Long paidPrincipal;

    /**
     *已还利息
     */
    private Long paidInterest;

    /**
     *已还逾期费
     */
    private Long paidOverdue;

    /**
     *入账日期
     */
    private String paidDate;

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
     *放款信息Id
     */
    public String getLoanInfoId() {
        return loanInfoId;
    }

    /**
     *放款信息Id
     */
    public void setLoanInfoId(String loanInfoId) {
        this.loanInfoId = loanInfoId == null ? null : loanInfoId.trim();
    }

    /**
     *还款的还款计划Id
     */
    public String getLoanPlanId() {
        return loanPlanId;
    }

    /**
     *还款的还款计划Id
     */
    public void setLoanPlanId(String loanPlanId) {
        this.loanPlanId = loanPlanId == null ? null : loanPlanId.trim();
    }

    /**
     *还款总金额
     */
    public Long getPaidAmount() {
        return paidAmount;
    }

    /**
     *还款总金额
     */
    public void setPaidAmount(Long paidAmount) {
        this.paidAmount = paidAmount;
    }

    /**
     *已还本金
     */
    public Long getPaidPrincipal() {
        return paidPrincipal;
    }

    /**
     *已还本金
     */
    public void setPaidPrincipal(Long paidPrincipal) {
        this.paidPrincipal = paidPrincipal;
    }

    /**
     *已还利息
     */
    public Long getPaidInterest() {
        return paidInterest;
    }

    /**
     *已还利息
     */
    public void setPaidInterest(Long paidInterest) {
        this.paidInterest = paidInterest;
    }

    /**
     *已还逾期费
     */
    public Long getPaidOverdue() {
        return paidOverdue;
    }

    /**
     *已还逾期费
     */
    public void setPaidOverdue(Long paidOverdue) {
        this.paidOverdue = paidOverdue;
    }

    /**
     *入账日期
     */
    public String getPaidDate() {
        return paidDate;
    }

    /**
     *入账日期
     */
    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate == null ? null : paidDate.trim();
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
        LoanPaid other = (LoanPaid) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLoanInfoId() == null ? other.getLoanInfoId() == null : this.getLoanInfoId().equals(other.getLoanInfoId()))
            && (this.getLoanPlanId() == null ? other.getLoanPlanId() == null : this.getLoanPlanId().equals(other.getLoanPlanId()))
            && (this.getPaidAmount() == null ? other.getPaidAmount() == null : this.getPaidAmount().equals(other.getPaidAmount()))
            && (this.getPaidPrincipal() == null ? other.getPaidPrincipal() == null : this.getPaidPrincipal().equals(other.getPaidPrincipal()))
            && (this.getPaidInterest() == null ? other.getPaidInterest() == null : this.getPaidInterest().equals(other.getPaidInterest()))
            && (this.getPaidOverdue() == null ? other.getPaidOverdue() == null : this.getPaidOverdue().equals(other.getPaidOverdue()))
            && (this.getPaidDate() == null ? other.getPaidDate() == null : this.getPaidDate().equals(other.getPaidDate()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLoanInfoId() == null) ? 0 : getLoanInfoId().hashCode());
        result = prime * result + ((getLoanPlanId() == null) ? 0 : getLoanPlanId().hashCode());
        result = prime * result + ((getPaidAmount() == null) ? 0 : getPaidAmount().hashCode());
        result = prime * result + ((getPaidPrincipal() == null) ? 0 : getPaidPrincipal().hashCode());
        result = prime * result + ((getPaidInterest() == null) ? 0 : getPaidInterest().hashCode());
        result = prime * result + ((getPaidOverdue() == null) ? 0 : getPaidOverdue().hashCode());
        result = prime * result + ((getPaidDate() == null) ? 0 : getPaidDate().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}