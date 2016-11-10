/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.report.dao.po;

import java.io.Serializable;
import java.util.Date;

public class ReportFactorFinanceDay implements Serializable {
    /**
     *报表id
     */
    private String id;

    /**
     *报表所有者id
     */
    private String ownerId;

    /**
     *报表日期（格式：yyyy-MM-dd）
     */
    private String reportDate;

    /**
     *用户类型
     */
    private String userType;

    /**
     *业务产品Id
     */
    private String businessProductId;

    /**
     *融资余额（每一个放款信息变化后的融资余额）
     */
    private Long financeBalance;

    /**
     *放款金额（新增放款信息的放款金额）
     */
    private Long financeAmount;

    /**
     *应还正常本金
     */
    private Long accountPrincipal;

    /**
     *应还正常固定费用
     */
    private Long accountExpense;

    /**
     *应还逾期本金
     */
    private Long accountOverduePrincipal;

    /**
     *应还逾期利息
     */
    private Long accountOverdueInterest;

    /**
     *应还逾期服务费
     */
    private Long accountOverdueServiceCharge;

    /**
     *应还逾期展期费
     */
    private Long accountOverdueExtensionCharge;

    /**
     *应还逾期金额（逾期的本金）
     */
    private Long accountOverdue;

    /**
     *应还正常利息
     */
    private Long accountInterest;

    /**
     *应还正常服务费
     */
    private Long accountServiceCharge;

    /**
     *应还正常展期费
     */
    private Long accountExtensionCharge;

    /**
     *应还逾期本金罚息
     */
    private Long accountOverduePrincipalPenalty;

    /**
     *应还逾期利息罚息
     */
    private Long accountOverdueInterestPenalty;

    /**
     *应还逾期服务费罚息
     */
    private Long accountOverdueServiceChargePenalty;

    /**
     *应还其他罚息
     */
    private Long accountOtherPenalty;

    /**
     *应还费用（包括利息，服务费，展期费，罚息等）
     */
    private Long accountCharge;

    /**
     *已还正常本金
     */
    private Long paidPrincipal;

    /**
     *已还正常利息
     */
    private Long paidInterest;

    /**
     *已还正常服务费
     */
    private Long paidServiceCharge;

    /**
     *已还正常展期费
     */
    private Long paidExtensionCharge;

    /**
     *提前还款手续费
     */
    private Long paidEarlyRepaymentCharge;

    /**
     *已还逾期本金罚息
     */
    private Long paidOverduePrincipalPenalty;

    /**
     *已还逾期利息罚息
     */
    private Long paidOverdueInterestPenalty;

    /**
     *已还逾期服务费罚息
     */
    private Long paidOverdueServiceChargePenalty;

    /**
     *已还其他罚息
     */
    private Long paidOtherPenalty;

    /**
     *已还费用（包括利息，服务费，展期费，罚息等）
     */
    private Long paidCharge;

    /**
     *已还正常固定费用
     */
    private Long paidExpense;

    /**
     *已还逾期本金
     */
    private Long paidOverduePrincipal;

    /**
     *已还逾期利息
     */
    private Long paidOverdueInterest;

    /**
     *已还逾期服务费
     */
    private Long paidOverdueServiceCharge;

    /**
     *已还逾期展期费
     */
    private Long paidOverdueExtensionCharge;

    /**
     *已还逾期金额
     */
    private Long paidOverdue;

    /**
     *调账本金
     */
    private Long adjustPrincipal;

    /**
     *调账利息
     */
    private Long adjustInterest;

    /**
     *调账服务费
     */
    private Long adjustServiceCharge;

    /**
     *调账展期
     */
    private Long adjustExtension;

    /**
     *调账逾期利息
     */
    private Long adjustOverdueInterest;

    /**
     *调账逾期服务费
     */
    private Long adjustOverdueServiceCharge;

    /**
     *调账罚息
     */
    private Long adjustPenalty;

    /**
     *调账固定费用
     */
    private Long adjustExpense;

    /**
     *调账费用
     */
    private Long adjustAmount;

    /**
     *报表创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     *报表id
     */
    public String getId() {
        return id;
    }

    /**
     *报表id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *报表所有者id
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     *报表所有者id
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
    }

    /**
     *报表日期（格式：yyyy-MM-dd）
     */
    public String getReportDate() {
        return reportDate;
    }

    /**
     *报表日期（格式：yyyy-MM-dd）
     */
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate == null ? null : reportDate.trim();
    }

    /**
     *用户类型
     */
    public String getUserType() {
        return userType;
    }

    /**
     *用户类型
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     *业务产品Id
     */
    public String getBusinessProductId() {
        return businessProductId;
    }

    /**
     *业务产品Id
     */
    public void setBusinessProductId(String businessProductId) {
        this.businessProductId = businessProductId == null ? null : businessProductId.trim();
    }

    /**
     *融资余额（每一个放款信息变化后的融资余额）
     */
    public Long getFinanceBalance() {
        return financeBalance;
    }

    /**
     *融资余额（每一个放款信息变化后的融资余额）
     */
    public void setFinanceBalance(Long financeBalance) {
        this.financeBalance = financeBalance;
    }

    /**
     *放款金额（新增放款信息的放款金额）
     */
    public Long getFinanceAmount() {
        return financeAmount;
    }

    /**
     *放款金额（新增放款信息的放款金额）
     */
    public void setFinanceAmount(Long financeAmount) {
        this.financeAmount = financeAmount;
    }

    /**
     *应还正常本金
     */
    public Long getAccountPrincipal() {
        return accountPrincipal;
    }

    /**
     *应还正常本金
     */
    public void setAccountPrincipal(Long accountPrincipal) {
        this.accountPrincipal = accountPrincipal;
    }

    /**
     *应还正常固定费用
     */
    public Long getAccountExpense() {
        return accountExpense;
    }

    /**
     *应还正常固定费用
     */
    public void setAccountExpense(Long accountExpense) {
        this.accountExpense = accountExpense;
    }

    /**
     *应还逾期本金
     */
    public Long getAccountOverduePrincipal() {
        return accountOverduePrincipal;
    }

    /**
     *应还逾期本金
     */
    public void setAccountOverduePrincipal(Long accountOverduePrincipal) {
        this.accountOverduePrincipal = accountOverduePrincipal;
    }

    /**
     *应还逾期利息
     */
    public Long getAccountOverdueInterest() {
        return accountOverdueInterest;
    }

    /**
     *应还逾期利息
     */
    public void setAccountOverdueInterest(Long accountOverdueInterest) {
        this.accountOverdueInterest = accountOverdueInterest;
    }

    /**
     *应还逾期服务费
     */
    public Long getAccountOverdueServiceCharge() {
        return accountOverdueServiceCharge;
    }

    /**
     *应还逾期服务费
     */
    public void setAccountOverdueServiceCharge(Long accountOverdueServiceCharge) {
        this.accountOverdueServiceCharge = accountOverdueServiceCharge;
    }

    /**
     *应还逾期展期费
     */
    public Long getAccountOverdueExtensionCharge() {
        return accountOverdueExtensionCharge;
    }

    /**
     *应还逾期展期费
     */
    public void setAccountOverdueExtensionCharge(Long accountOverdueExtensionCharge) {
        this.accountOverdueExtensionCharge = accountOverdueExtensionCharge;
    }

    /**
     *应还逾期金额（逾期的本金）
     */
    public Long getAccountOverdue() {
        return accountOverdue;
    }

    /**
     *应还逾期金额（逾期的本金）
     */
    public void setAccountOverdue(Long accountOverdue) {
        this.accountOverdue = accountOverdue;
    }

    /**
     *应还正常利息
     */
    public Long getAccountInterest() {
        return accountInterest;
    }

    /**
     *应还正常利息
     */
    public void setAccountInterest(Long accountInterest) {
        this.accountInterest = accountInterest;
    }

    /**
     *应还正常服务费
     */
    public Long getAccountServiceCharge() {
        return accountServiceCharge;
    }

    /**
     *应还正常服务费
     */
    public void setAccountServiceCharge(Long accountServiceCharge) {
        this.accountServiceCharge = accountServiceCharge;
    }

    /**
     *应还正常展期费
     */
    public Long getAccountExtensionCharge() {
        return accountExtensionCharge;
    }

    /**
     *应还正常展期费
     */
    public void setAccountExtensionCharge(Long accountExtensionCharge) {
        this.accountExtensionCharge = accountExtensionCharge;
    }

    /**
     *应还逾期本金罚息
     */
    public Long getAccountOverduePrincipalPenalty() {
        return accountOverduePrincipalPenalty;
    }

    /**
     *应还逾期本金罚息
     */
    public void setAccountOverduePrincipalPenalty(Long accountOverduePrincipalPenalty) {
        this.accountOverduePrincipalPenalty = accountOverduePrincipalPenalty;
    }

    /**
     *应还逾期利息罚息
     */
    public Long getAccountOverdueInterestPenalty() {
        return accountOverdueInterestPenalty;
    }

    /**
     *应还逾期利息罚息
     */
    public void setAccountOverdueInterestPenalty(Long accountOverdueInterestPenalty) {
        this.accountOverdueInterestPenalty = accountOverdueInterestPenalty;
    }

    /**
     *应还逾期服务费罚息
     */
    public Long getAccountOverdueServiceChargePenalty() {
        return accountOverdueServiceChargePenalty;
    }

    /**
     *应还逾期服务费罚息
     */
    public void setAccountOverdueServiceChargePenalty(Long accountOverdueServiceChargePenalty) {
        this.accountOverdueServiceChargePenalty = accountOverdueServiceChargePenalty;
    }

    /**
     *应还其他罚息
     */
    public Long getAccountOtherPenalty() {
        return accountOtherPenalty;
    }

    /**
     *应还其他罚息
     */
    public void setAccountOtherPenalty(Long accountOtherPenalty) {
        this.accountOtherPenalty = accountOtherPenalty;
    }

    /**
     *应还费用（包括利息，服务费，展期费，罚息等）
     */
    public Long getAccountCharge() {
        return accountCharge;
    }

    /**
     *应还费用（包括利息，服务费，展期费，罚息等）
     */
    public void setAccountCharge(Long accountCharge) {
        this.accountCharge = accountCharge;
    }

    /**
     *已还正常本金
     */
    public Long getPaidPrincipal() {
        return paidPrincipal;
    }

    /**
     *已还正常本金
     */
    public void setPaidPrincipal(Long paidPrincipal) {
        this.paidPrincipal = paidPrincipal;
    }

    /**
     *已还正常利息
     */
    public Long getPaidInterest() {
        return paidInterest;
    }

    /**
     *已还正常利息
     */
    public void setPaidInterest(Long paidInterest) {
        this.paidInterest = paidInterest;
    }

    /**
     *已还正常服务费
     */
    public Long getPaidServiceCharge() {
        return paidServiceCharge;
    }

    /**
     *已还正常服务费
     */
    public void setPaidServiceCharge(Long paidServiceCharge) {
        this.paidServiceCharge = paidServiceCharge;
    }

    /**
     *已还正常展期费
     */
    public Long getPaidExtensionCharge() {
        return paidExtensionCharge;
    }

    /**
     *已还正常展期费
     */
    public void setPaidExtensionCharge(Long paidExtensionCharge) {
        this.paidExtensionCharge = paidExtensionCharge;
    }

    /**
     *提前还款手续费
     */
    public Long getPaidEarlyRepaymentCharge() {
        return paidEarlyRepaymentCharge;
    }

    /**
     *提前还款手续费
     */
    public void setPaidEarlyRepaymentCharge(Long paidEarlyRepaymentCharge) {
        this.paidEarlyRepaymentCharge = paidEarlyRepaymentCharge;
    }

    /**
     *已还逾期本金罚息
     */
    public Long getPaidOverduePrincipalPenalty() {
        return paidOverduePrincipalPenalty;
    }

    /**
     *已还逾期本金罚息
     */
    public void setPaidOverduePrincipalPenalty(Long paidOverduePrincipalPenalty) {
        this.paidOverduePrincipalPenalty = paidOverduePrincipalPenalty;
    }

    /**
     *已还逾期利息罚息
     */
    public Long getPaidOverdueInterestPenalty() {
        return paidOverdueInterestPenalty;
    }

    /**
     *已还逾期利息罚息
     */
    public void setPaidOverdueInterestPenalty(Long paidOverdueInterestPenalty) {
        this.paidOverdueInterestPenalty = paidOverdueInterestPenalty;
    }

    /**
     *已还逾期服务费罚息
     */
    public Long getPaidOverdueServiceChargePenalty() {
        return paidOverdueServiceChargePenalty;
    }

    /**
     *已还逾期服务费罚息
     */
    public void setPaidOverdueServiceChargePenalty(Long paidOverdueServiceChargePenalty) {
        this.paidOverdueServiceChargePenalty = paidOverdueServiceChargePenalty;
    }

    /**
     *已还其他罚息
     */
    public Long getPaidOtherPenalty() {
        return paidOtherPenalty;
    }

    /**
     *已还其他罚息
     */
    public void setPaidOtherPenalty(Long paidOtherPenalty) {
        this.paidOtherPenalty = paidOtherPenalty;
    }

    /**
     *已还费用（包括利息，服务费，展期费，罚息等）
     */
    public Long getPaidCharge() {
        return paidCharge;
    }

    /**
     *已还费用（包括利息，服务费，展期费，罚息等）
     */
    public void setPaidCharge(Long paidCharge) {
        this.paidCharge = paidCharge;
    }

    /**
     *已还正常固定费用
     */
    public Long getPaidExpense() {
        return paidExpense;
    }

    /**
     *已还正常固定费用
     */
    public void setPaidExpense(Long paidExpense) {
        this.paidExpense = paidExpense;
    }

    /**
     *已还逾期本金
     */
    public Long getPaidOverduePrincipal() {
        return paidOverduePrincipal;
    }

    /**
     *已还逾期本金
     */
    public void setPaidOverduePrincipal(Long paidOverduePrincipal) {
        this.paidOverduePrincipal = paidOverduePrincipal;
    }

    /**
     *已还逾期利息
     */
    public Long getPaidOverdueInterest() {
        return paidOverdueInterest;
    }

    /**
     *已还逾期利息
     */
    public void setPaidOverdueInterest(Long paidOverdueInterest) {
        this.paidOverdueInterest = paidOverdueInterest;
    }

    /**
     *已还逾期服务费
     */
    public Long getPaidOverdueServiceCharge() {
        return paidOverdueServiceCharge;
    }

    /**
     *已还逾期服务费
     */
    public void setPaidOverdueServiceCharge(Long paidOverdueServiceCharge) {
        this.paidOverdueServiceCharge = paidOverdueServiceCharge;
    }

    /**
     *已还逾期展期费
     */
    public Long getPaidOverdueExtensionCharge() {
        return paidOverdueExtensionCharge;
    }

    /**
     *已还逾期展期费
     */
    public void setPaidOverdueExtensionCharge(Long paidOverdueExtensionCharge) {
        this.paidOverdueExtensionCharge = paidOverdueExtensionCharge;
    }

    /**
     *已还逾期金额
     */
    public Long getPaidOverdue() {
        return paidOverdue;
    }

    /**
     *已还逾期金额
     */
    public void setPaidOverdue(Long paidOverdue) {
        this.paidOverdue = paidOverdue;
    }

    /**
     *调账本金
     */
    public Long getAdjustPrincipal() {
        return adjustPrincipal;
    }

    /**
     *调账本金
     */
    public void setAdjustPrincipal(Long adjustPrincipal) {
        this.adjustPrincipal = adjustPrincipal;
    }

    /**
     *调账利息
     */
    public Long getAdjustInterest() {
        return adjustInterest;
    }

    /**
     *调账利息
     */
    public void setAdjustInterest(Long adjustInterest) {
        this.adjustInterest = adjustInterest;
    }

    /**
     *调账服务费
     */
    public Long getAdjustServiceCharge() {
        return adjustServiceCharge;
    }

    /**
     *调账服务费
     */
    public void setAdjustServiceCharge(Long adjustServiceCharge) {
        this.adjustServiceCharge = adjustServiceCharge;
    }

    /**
     *调账展期
     */
    public Long getAdjustExtension() {
        return adjustExtension;
    }

    /**
     *调账展期
     */
    public void setAdjustExtension(Long adjustExtension) {
        this.adjustExtension = adjustExtension;
    }

    /**
     *调账逾期利息
     */
    public Long getAdjustOverdueInterest() {
        return adjustOverdueInterest;
    }

    /**
     *调账逾期利息
     */
    public void setAdjustOverdueInterest(Long adjustOverdueInterest) {
        this.adjustOverdueInterest = adjustOverdueInterest;
    }

    /**
     *调账逾期服务费
     */
    public Long getAdjustOverdueServiceCharge() {
        return adjustOverdueServiceCharge;
    }

    /**
     *调账逾期服务费
     */
    public void setAdjustOverdueServiceCharge(Long adjustOverdueServiceCharge) {
        this.adjustOverdueServiceCharge = adjustOverdueServiceCharge;
    }

    /**
     *调账罚息
     */
    public Long getAdjustPenalty() {
        return adjustPenalty;
    }

    /**
     *调账罚息
     */
    public void setAdjustPenalty(Long adjustPenalty) {
        this.adjustPenalty = adjustPenalty;
    }

    /**
     *调账固定费用
     */
    public Long getAdjustExpense() {
        return adjustExpense;
    }

    /**
     *调账固定费用
     */
    public void setAdjustExpense(Long adjustExpense) {
        this.adjustExpense = adjustExpense;
    }

    /**
     *调账费用
     */
    public Long getAdjustAmount() {
        return adjustAmount;
    }

    /**
     *调账费用
     */
    public void setAdjustAmount(Long adjustAmount) {
        this.adjustAmount = adjustAmount;
    }

    /**
     *报表创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *报表创建时间
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
        ReportFactorFinanceDay other = (ReportFactorFinanceDay) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
            && (this.getReportDate() == null ? other.getReportDate() == null : this.getReportDate().equals(other.getReportDate()))
            && (this.getUserType() == null ? other.getUserType() == null : this.getUserType().equals(other.getUserType()))
            && (this.getBusinessProductId() == null ? other.getBusinessProductId() == null : this.getBusinessProductId().equals(other.getBusinessProductId()))
            && (this.getFinanceBalance() == null ? other.getFinanceBalance() == null : this.getFinanceBalance().equals(other.getFinanceBalance()))
            && (this.getFinanceAmount() == null ? other.getFinanceAmount() == null : this.getFinanceAmount().equals(other.getFinanceAmount()))
            && (this.getAccountPrincipal() == null ? other.getAccountPrincipal() == null : this.getAccountPrincipal().equals(other.getAccountPrincipal()))
            && (this.getAccountExpense() == null ? other.getAccountExpense() == null : this.getAccountExpense().equals(other.getAccountExpense()))
            && (this.getAccountOverduePrincipal() == null ? other.getAccountOverduePrincipal() == null : this.getAccountOverduePrincipal().equals(other.getAccountOverduePrincipal()))
            && (this.getAccountOverdueInterest() == null ? other.getAccountOverdueInterest() == null : this.getAccountOverdueInterest().equals(other.getAccountOverdueInterest()))
            && (this.getAccountOverdueServiceCharge() == null ? other.getAccountOverdueServiceCharge() == null : this.getAccountOverdueServiceCharge().equals(other.getAccountOverdueServiceCharge()))
            && (this.getAccountOverdueExtensionCharge() == null ? other.getAccountOverdueExtensionCharge() == null : this.getAccountOverdueExtensionCharge().equals(other.getAccountOverdueExtensionCharge()))
            && (this.getAccountOverdue() == null ? other.getAccountOverdue() == null : this.getAccountOverdue().equals(other.getAccountOverdue()))
            && (this.getAccountInterest() == null ? other.getAccountInterest() == null : this.getAccountInterest().equals(other.getAccountInterest()))
            && (this.getAccountServiceCharge() == null ? other.getAccountServiceCharge() == null : this.getAccountServiceCharge().equals(other.getAccountServiceCharge()))
            && (this.getAccountExtensionCharge() == null ? other.getAccountExtensionCharge() == null : this.getAccountExtensionCharge().equals(other.getAccountExtensionCharge()))
            && (this.getAccountOverduePrincipalPenalty() == null ? other.getAccountOverduePrincipalPenalty() == null : this.getAccountOverduePrincipalPenalty().equals(other.getAccountOverduePrincipalPenalty()))
            && (this.getAccountOverdueInterestPenalty() == null ? other.getAccountOverdueInterestPenalty() == null : this.getAccountOverdueInterestPenalty().equals(other.getAccountOverdueInterestPenalty()))
            && (this.getAccountOverdueServiceChargePenalty() == null ? other.getAccountOverdueServiceChargePenalty() == null : this.getAccountOverdueServiceChargePenalty().equals(other.getAccountOverdueServiceChargePenalty()))
            && (this.getAccountOtherPenalty() == null ? other.getAccountOtherPenalty() == null : this.getAccountOtherPenalty().equals(other.getAccountOtherPenalty()))
            && (this.getAccountCharge() == null ? other.getAccountCharge() == null : this.getAccountCharge().equals(other.getAccountCharge()))
            && (this.getPaidPrincipal() == null ? other.getPaidPrincipal() == null : this.getPaidPrincipal().equals(other.getPaidPrincipal()))
            && (this.getPaidInterest() == null ? other.getPaidInterest() == null : this.getPaidInterest().equals(other.getPaidInterest()))
            && (this.getPaidServiceCharge() == null ? other.getPaidServiceCharge() == null : this.getPaidServiceCharge().equals(other.getPaidServiceCharge()))
            && (this.getPaidExtensionCharge() == null ? other.getPaidExtensionCharge() == null : this.getPaidExtensionCharge().equals(other.getPaidExtensionCharge()))
            && (this.getPaidEarlyRepaymentCharge() == null ? other.getPaidEarlyRepaymentCharge() == null : this.getPaidEarlyRepaymentCharge().equals(other.getPaidEarlyRepaymentCharge()))
            && (this.getPaidOverduePrincipalPenalty() == null ? other.getPaidOverduePrincipalPenalty() == null : this.getPaidOverduePrincipalPenalty().equals(other.getPaidOverduePrincipalPenalty()))
            && (this.getPaidOverdueInterestPenalty() == null ? other.getPaidOverdueInterestPenalty() == null : this.getPaidOverdueInterestPenalty().equals(other.getPaidOverdueInterestPenalty()))
            && (this.getPaidOverdueServiceChargePenalty() == null ? other.getPaidOverdueServiceChargePenalty() == null : this.getPaidOverdueServiceChargePenalty().equals(other.getPaidOverdueServiceChargePenalty()))
            && (this.getPaidOtherPenalty() == null ? other.getPaidOtherPenalty() == null : this.getPaidOtherPenalty().equals(other.getPaidOtherPenalty()))
            && (this.getPaidCharge() == null ? other.getPaidCharge() == null : this.getPaidCharge().equals(other.getPaidCharge()))
            && (this.getPaidExpense() == null ? other.getPaidExpense() == null : this.getPaidExpense().equals(other.getPaidExpense()))
            && (this.getPaidOverduePrincipal() == null ? other.getPaidOverduePrincipal() == null : this.getPaidOverduePrincipal().equals(other.getPaidOverduePrincipal()))
            && (this.getPaidOverdueInterest() == null ? other.getPaidOverdueInterest() == null : this.getPaidOverdueInterest().equals(other.getPaidOverdueInterest()))
            && (this.getPaidOverdueServiceCharge() == null ? other.getPaidOverdueServiceCharge() == null : this.getPaidOverdueServiceCharge().equals(other.getPaidOverdueServiceCharge()))
            && (this.getPaidOverdueExtensionCharge() == null ? other.getPaidOverdueExtensionCharge() == null : this.getPaidOverdueExtensionCharge().equals(other.getPaidOverdueExtensionCharge()))
            && (this.getPaidOverdue() == null ? other.getPaidOverdue() == null : this.getPaidOverdue().equals(other.getPaidOverdue()))
            && (this.getAdjustPrincipal() == null ? other.getAdjustPrincipal() == null : this.getAdjustPrincipal().equals(other.getAdjustPrincipal()))
            && (this.getAdjustInterest() == null ? other.getAdjustInterest() == null : this.getAdjustInterest().equals(other.getAdjustInterest()))
            && (this.getAdjustServiceCharge() == null ? other.getAdjustServiceCharge() == null : this.getAdjustServiceCharge().equals(other.getAdjustServiceCharge()))
            && (this.getAdjustExtension() == null ? other.getAdjustExtension() == null : this.getAdjustExtension().equals(other.getAdjustExtension()))
            && (this.getAdjustOverdueInterest() == null ? other.getAdjustOverdueInterest() == null : this.getAdjustOverdueInterest().equals(other.getAdjustOverdueInterest()))
            && (this.getAdjustOverdueServiceCharge() == null ? other.getAdjustOverdueServiceCharge() == null : this.getAdjustOverdueServiceCharge().equals(other.getAdjustOverdueServiceCharge()))
            && (this.getAdjustPenalty() == null ? other.getAdjustPenalty() == null : this.getAdjustPenalty().equals(other.getAdjustPenalty()))
            && (this.getAdjustExpense() == null ? other.getAdjustExpense() == null : this.getAdjustExpense().equals(other.getAdjustExpense()))
            && (this.getAdjustAmount() == null ? other.getAdjustAmount() == null : this.getAdjustAmount().equals(other.getAdjustAmount()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getReportDate() == null) ? 0 : getReportDate().hashCode());
        result = prime * result + ((getUserType() == null) ? 0 : getUserType().hashCode());
        result = prime * result + ((getBusinessProductId() == null) ? 0 : getBusinessProductId().hashCode());
        result = prime * result + ((getFinanceBalance() == null) ? 0 : getFinanceBalance().hashCode());
        result = prime * result + ((getFinanceAmount() == null) ? 0 : getFinanceAmount().hashCode());
        result = prime * result + ((getAccountPrincipal() == null) ? 0 : getAccountPrincipal().hashCode());
        result = prime * result + ((getAccountExpense() == null) ? 0 : getAccountExpense().hashCode());
        result = prime * result + ((getAccountOverduePrincipal() == null) ? 0 : getAccountOverduePrincipal().hashCode());
        result = prime * result + ((getAccountOverdueInterest() == null) ? 0 : getAccountOverdueInterest().hashCode());
        result = prime * result + ((getAccountOverdueServiceCharge() == null) ? 0 : getAccountOverdueServiceCharge().hashCode());
        result = prime * result + ((getAccountOverdueExtensionCharge() == null) ? 0 : getAccountOverdueExtensionCharge().hashCode());
        result = prime * result + ((getAccountOverdue() == null) ? 0 : getAccountOverdue().hashCode());
        result = prime * result + ((getAccountInterest() == null) ? 0 : getAccountInterest().hashCode());
        result = prime * result + ((getAccountServiceCharge() == null) ? 0 : getAccountServiceCharge().hashCode());
        result = prime * result + ((getAccountExtensionCharge() == null) ? 0 : getAccountExtensionCharge().hashCode());
        result = prime * result + ((getAccountOverduePrincipalPenalty() == null) ? 0 : getAccountOverduePrincipalPenalty().hashCode());
        result = prime * result + ((getAccountOverdueInterestPenalty() == null) ? 0 : getAccountOverdueInterestPenalty().hashCode());
        result = prime * result + ((getAccountOverdueServiceChargePenalty() == null) ? 0 : getAccountOverdueServiceChargePenalty().hashCode());
        result = prime * result + ((getAccountOtherPenalty() == null) ? 0 : getAccountOtherPenalty().hashCode());
        result = prime * result + ((getAccountCharge() == null) ? 0 : getAccountCharge().hashCode());
        result = prime * result + ((getPaidPrincipal() == null) ? 0 : getPaidPrincipal().hashCode());
        result = prime * result + ((getPaidInterest() == null) ? 0 : getPaidInterest().hashCode());
        result = prime * result + ((getPaidServiceCharge() == null) ? 0 : getPaidServiceCharge().hashCode());
        result = prime * result + ((getPaidExtensionCharge() == null) ? 0 : getPaidExtensionCharge().hashCode());
        result = prime * result + ((getPaidEarlyRepaymentCharge() == null) ? 0 : getPaidEarlyRepaymentCharge().hashCode());
        result = prime * result + ((getPaidOverduePrincipalPenalty() == null) ? 0 : getPaidOverduePrincipalPenalty().hashCode());
        result = prime * result + ((getPaidOverdueInterestPenalty() == null) ? 0 : getPaidOverdueInterestPenalty().hashCode());
        result = prime * result + ((getPaidOverdueServiceChargePenalty() == null) ? 0 : getPaidOverdueServiceChargePenalty().hashCode());
        result = prime * result + ((getPaidOtherPenalty() == null) ? 0 : getPaidOtherPenalty().hashCode());
        result = prime * result + ((getPaidCharge() == null) ? 0 : getPaidCharge().hashCode());
        result = prime * result + ((getPaidExpense() == null) ? 0 : getPaidExpense().hashCode());
        result = prime * result + ((getPaidOverduePrincipal() == null) ? 0 : getPaidOverduePrincipal().hashCode());
        result = prime * result + ((getPaidOverdueInterest() == null) ? 0 : getPaidOverdueInterest().hashCode());
        result = prime * result + ((getPaidOverdueServiceCharge() == null) ? 0 : getPaidOverdueServiceCharge().hashCode());
        result = prime * result + ((getPaidOverdueExtensionCharge() == null) ? 0 : getPaidOverdueExtensionCharge().hashCode());
        result = prime * result + ((getPaidOverdue() == null) ? 0 : getPaidOverdue().hashCode());
        result = prime * result + ((getAdjustPrincipal() == null) ? 0 : getAdjustPrincipal().hashCode());
        result = prime * result + ((getAdjustInterest() == null) ? 0 : getAdjustInterest().hashCode());
        result = prime * result + ((getAdjustServiceCharge() == null) ? 0 : getAdjustServiceCharge().hashCode());
        result = prime * result + ((getAdjustExtension() == null) ? 0 : getAdjustExtension().hashCode());
        result = prime * result + ((getAdjustOverdueInterest() == null) ? 0 : getAdjustOverdueInterest().hashCode());
        result = prime * result + ((getAdjustOverdueServiceCharge() == null) ? 0 : getAdjustOverdueServiceCharge().hashCode());
        result = prime * result + ((getAdjustPenalty() == null) ? 0 : getAdjustPenalty().hashCode());
        result = prime * result + ((getAdjustExpense() == null) ? 0 : getAdjustExpense().hashCode());
        result = prime * result + ((getAdjustAmount() == null) ? 0 : getAdjustAmount().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}