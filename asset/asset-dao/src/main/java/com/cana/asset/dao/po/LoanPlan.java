/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.util.Date;

public class LoanPlan implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *放款信息Id
     */
    private String loanInfoId;

    /**
     *期数
     */
    private Integer repaymentPeriod;

    /**
     *融资余额
     */
    private Long financeBalance;

    /**
     *起息日
     */
    private String valueDate;

    /**
     *结息日
     */
    private String settleInterestDate;

    /**
     *固定还款日
     */
    private String repaymentDate;

    /**
     *应还本金
     */
    private Long accountPrincipal;

    /**
     *应还利息
     */
    private Long accountInterest;

    /**
     *应还逾期费
     */
    private Long accountOverdue;

    /**
     *结清状态（已结清、未结清）@see SettleStatus
     */
    private String settleStatus;

    /**
     *最后入账日期
     */
    private String lastPaidDate;

    /**
     *已还本金
     */
    private Long paidPrincipal;

    /**
     *已还利息
     */
    private Long paidInterest;

    /**
     *已还逾期
     */
    private Long paidOverdue;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date upateTime;

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
     *期数
     */
    public Integer getRepaymentPeriod() {
        return repaymentPeriod;
    }

    /**
     *期数
     */
    public void setRepaymentPeriod(Integer repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
    }

    /**
     *融资余额
     */
    public Long getFinanceBalance() {
        return financeBalance;
    }

    /**
     *融资余额
     */
    public void setFinanceBalance(Long financeBalance) {
        this.financeBalance = financeBalance;
    }

    /**
     *起息日
     */
    public String getValueDate() {
        return valueDate;
    }

    /**
     *起息日
     */
    public void setValueDate(String valueDate) {
        this.valueDate = valueDate == null ? null : valueDate.trim();
    }

    /**
     *结息日
     */
    public String getSettleInterestDate() {
        return settleInterestDate;
    }

    /**
     *结息日
     */
    public void setSettleInterestDate(String settleInterestDate) {
        this.settleInterestDate = settleInterestDate == null ? null : settleInterestDate.trim();
    }

    /**
     *固定还款日
     */
    public String getRepaymentDate() {
        return repaymentDate;
    }

    /**
     *固定还款日
     */
    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate == null ? null : repaymentDate.trim();
    }

    /**
     *应还本金
     */
    public Long getAccountPrincipal() {
        return accountPrincipal;
    }

    /**
     *应还本金
     */
    public void setAccountPrincipal(Long accountPrincipal) {
        this.accountPrincipal = accountPrincipal;
    }

    /**
     *应还利息
     */
    public Long getAccountInterest() {
        return accountInterest;
    }

    /**
     *应还利息
     */
    public void setAccountInterest(Long accountInterest) {
        this.accountInterest = accountInterest;
    }

    /**
     *应还逾期费
     */
    public Long getAccountOverdue() {
        return accountOverdue;
    }

    /**
     *应还逾期费
     */
    public void setAccountOverdue(Long accountOverdue) {
        this.accountOverdue = accountOverdue;
    }

    /**
     *结清状态（已结清、未结清）@see SettleStatus
     */
    public String getSettleStatus() {
        return settleStatus;
    }

    /**
     *结清状态（已结清、未结清）@see SettleStatus
     */
    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus == null ? null : settleStatus.trim();
    }

    /**
     *最后入账日期
     */
    public String getLastPaidDate() {
        return lastPaidDate;
    }

    /**
     *最后入账日期
     */
    public void setLastPaidDate(String lastPaidDate) {
        this.lastPaidDate = lastPaidDate == null ? null : lastPaidDate.trim();
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
     *已还逾期
     */
    public Long getPaidOverdue() {
        return paidOverdue;
    }

    /**
     *已还逾期
     */
    public void setPaidOverdue(Long paidOverdue) {
        this.paidOverdue = paidOverdue;
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
        LoanPlan other = (LoanPlan) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLoanInfoId() == null ? other.getLoanInfoId() == null : this.getLoanInfoId().equals(other.getLoanInfoId()))
            && (this.getRepaymentPeriod() == null ? other.getRepaymentPeriod() == null : this.getRepaymentPeriod().equals(other.getRepaymentPeriod()))
            && (this.getFinanceBalance() == null ? other.getFinanceBalance() == null : this.getFinanceBalance().equals(other.getFinanceBalance()))
            && (this.getValueDate() == null ? other.getValueDate() == null : this.getValueDate().equals(other.getValueDate()))
            && (this.getSettleInterestDate() == null ? other.getSettleInterestDate() == null : this.getSettleInterestDate().equals(other.getSettleInterestDate()))
            && (this.getRepaymentDate() == null ? other.getRepaymentDate() == null : this.getRepaymentDate().equals(other.getRepaymentDate()))
            && (this.getAccountPrincipal() == null ? other.getAccountPrincipal() == null : this.getAccountPrincipal().equals(other.getAccountPrincipal()))
            && (this.getAccountInterest() == null ? other.getAccountInterest() == null : this.getAccountInterest().equals(other.getAccountInterest()))
            && (this.getAccountOverdue() == null ? other.getAccountOverdue() == null : this.getAccountOverdue().equals(other.getAccountOverdue()))
            && (this.getSettleStatus() == null ? other.getSettleStatus() == null : this.getSettleStatus().equals(other.getSettleStatus()))
            && (this.getLastPaidDate() == null ? other.getLastPaidDate() == null : this.getLastPaidDate().equals(other.getLastPaidDate()))
            && (this.getPaidPrincipal() == null ? other.getPaidPrincipal() == null : this.getPaidPrincipal().equals(other.getPaidPrincipal()))
            && (this.getPaidInterest() == null ? other.getPaidInterest() == null : this.getPaidInterest().equals(other.getPaidInterest()))
            && (this.getPaidOverdue() == null ? other.getPaidOverdue() == null : this.getPaidOverdue().equals(other.getPaidOverdue()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpateTime() == null ? other.getUpateTime() == null : this.getUpateTime().equals(other.getUpateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLoanInfoId() == null) ? 0 : getLoanInfoId().hashCode());
        result = prime * result + ((getRepaymentPeriod() == null) ? 0 : getRepaymentPeriod().hashCode());
        result = prime * result + ((getFinanceBalance() == null) ? 0 : getFinanceBalance().hashCode());
        result = prime * result + ((getValueDate() == null) ? 0 : getValueDate().hashCode());
        result = prime * result + ((getSettleInterestDate() == null) ? 0 : getSettleInterestDate().hashCode());
        result = prime * result + ((getRepaymentDate() == null) ? 0 : getRepaymentDate().hashCode());
        result = prime * result + ((getAccountPrincipal() == null) ? 0 : getAccountPrincipal().hashCode());
        result = prime * result + ((getAccountInterest() == null) ? 0 : getAccountInterest().hashCode());
        result = prime * result + ((getAccountOverdue() == null) ? 0 : getAccountOverdue().hashCode());
        result = prime * result + ((getSettleStatus() == null) ? 0 : getSettleStatus().hashCode());
        result = prime * result + ((getLastPaidDate() == null) ? 0 : getLastPaidDate().hashCode());
        result = prime * result + ((getPaidPrincipal() == null) ? 0 : getPaidPrincipal().hashCode());
        result = prime * result + ((getPaidInterest() == null) ? 0 : getPaidInterest().hashCode());
        result = prime * result + ((getPaidOverdue() == null) ? 0 : getPaidOverdue().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpateTime() == null) ? 0 : getUpateTime().hashCode());
        return result;
    }
}