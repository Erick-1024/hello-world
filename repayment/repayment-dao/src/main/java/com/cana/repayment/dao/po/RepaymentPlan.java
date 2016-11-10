/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.repayment.dao.po;

import java.io.Serializable;
import java.util.Date;

public class RepaymentPlan implements Serializable {
    /**
     *主键（唯一判断，保理商+还款编号）
     */
    private String id;

    /**
     *业务模型（保理商+融资客户监管等）
     */
    private String businessMode;

    /**
     *信息录入方式
     */
    private String inputMethod;

    /**
     *期数（int 还是 String？）
     */
    private Integer repaymentPeriod;

    /**
     *融资客户Id
     */
    private String financeId;

    /**
     *融资客户公司名称
     */
    private String financeCompany;

    /**
     *核心企业id
     */
    private String coreCompanyId;

    /**
     *核心企业名称
     */
    private String coreCompanyName;

    /**
     *融资金额
     */
    private Long financeAmount;

    /**
     *融资余额
     */
    private Long financeBalance;

    /**
     *起息日
     */
    private String valueDate;

    /**
     *到期日
     */
    private String dueDate;

    /**
     *结息日
     */
    private String settleInterestDate;

    /**
     *还款日
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
     *应还服务费
     */
    private Long accountServiceCharge;

    /**
     *结清状态（已结清、未结清）
     */
    private String settleStatus;

    /**
     *放款信息Id
     */
    private String loanInfoId;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date upateTime;

    /**
     *放款编号
     */
    private String loanNo;

    /**
     *放款日
     */
    private String loanDate;

    /**
     *应还展期费用
     */
    private Long accountExtensionCharge;

    /**
     *已还正常本金
     */
    private Long paidNormalPrincipal;

    /**
     *已还逾期本金
     */
    private Long paidOverduePrincipal;

    /**
     *已还正常利息
     */
    private Long paidNormalInterest;

    /**
     *已还逾期利息
     */
    private Long paidOverdueInterest;

    /**
     *已还正常服务费
     */
    private Long paidNormalServiceCharge;

    /**
     *已还逾期服务费
     */
    private Long paidOverdueServiceCharge;

    /**
     *已还提前还款手续费
     */
    private Long paidEarlyRepaymentCharge;

    /**
     *已还展期费用
     */
    private Long paidExtensionCharge;

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
     *逾期本金罚息
     */
    private Long overduePrincipalPenalty;

    /**
     *逾期利息罚息
     */
    private Long overdueInterestPenalty;

    /**
     *逾期服务费罚息
     */
    private Long overdueServiceChargePenalty;

    /**
     *其他罚息
     */
    private Long otherPenalty;

    /**
     *融资客户Id
     */
    private String factorId;

    /**
     *本期展期天数，即MAX（节假日因素产生的展期天数，放款的展期天数）
     */
    private Integer extensionDays;

    private static final long serialVersionUID = 1L;

    /**
     *主键（唯一判断，保理商+还款编号）
     */
    public String getId() {
        return id;
    }

    /**
     *主键（唯一判断，保理商+还款编号）
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *业务模型（保理商+融资客户监管等）
     */
    public String getBusinessMode() {
        return businessMode;
    }

    /**
     *业务模型（保理商+融资客户监管等）
     */
    public void setBusinessMode(String businessMode) {
        this.businessMode = businessMode == null ? null : businessMode.trim();
    }

    /**
     *信息录入方式
     */
    public String getInputMethod() {
        return inputMethod;
    }

    /**
     *信息录入方式
     */
    public void setInputMethod(String inputMethod) {
        this.inputMethod = inputMethod == null ? null : inputMethod.trim();
    }

    /**
     *期数（int 还是 String？）
     */
    public Integer getRepaymentPeriod() {
        return repaymentPeriod;
    }

    /**
     *期数（int 还是 String？）
     */
    public void setRepaymentPeriod(Integer repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
    }

    /**
     *融资客户Id
     */
    public String getFinanceId() {
        return financeId;
    }

    /**
     *融资客户Id
     */
    public void setFinanceId(String financeId) {
        this.financeId = financeId == null ? null : financeId.trim();
    }

    /**
     *融资客户公司名称
     */
    public String getFinanceCompany() {
        return financeCompany;
    }

    /**
     *融资客户公司名称
     */
    public void setFinanceCompany(String financeCompany) {
        this.financeCompany = financeCompany == null ? null : financeCompany.trim();
    }

    /**
     *核心企业id
     */
    public String getCoreCompanyId() {
        return coreCompanyId;
    }

    /**
     *核心企业id
     */
    public void setCoreCompanyId(String coreCompanyId) {
        this.coreCompanyId = coreCompanyId == null ? null : coreCompanyId.trim();
    }

    /**
     *核心企业名称
     */
    public String getCoreCompanyName() {
        return coreCompanyName;
    }

    /**
     *核心企业名称
     */
    public void setCoreCompanyName(String coreCompanyName) {
        this.coreCompanyName = coreCompanyName == null ? null : coreCompanyName.trim();
    }

    /**
     *融资金额
     */
    public Long getFinanceAmount() {
        return financeAmount;
    }

    /**
     *融资金额
     */
    public void setFinanceAmount(Long financeAmount) {
        this.financeAmount = financeAmount;
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
     *还款日
     */
    public String getRepaymentDate() {
        return repaymentDate;
    }

    /**
     *还款日
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
     *应还服务费
     */
    public Long getAccountServiceCharge() {
        return accountServiceCharge;
    }

    /**
     *应还服务费
     */
    public void setAccountServiceCharge(Long accountServiceCharge) {
        this.accountServiceCharge = accountServiceCharge;
    }

    /**
     *结清状态（已结清、未结清）
     */
    public String getSettleStatus() {
        return settleStatus;
    }

    /**
     *结清状态（已结清、未结清）
     */
    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus == null ? null : settleStatus.trim();
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
     *放款编号
     */
    public String getLoanNo() {
        return loanNo;
    }

    /**
     *放款编号
     */
    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo == null ? null : loanNo.trim();
    }

    /**
     *放款日
     */
    public String getLoanDate() {
        return loanDate;
    }

    /**
     *放款日
     */
    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate == null ? null : loanDate.trim();
    }

    /**
     *应还展期费用
     */
    public Long getAccountExtensionCharge() {
        return accountExtensionCharge;
    }

    /**
     *应还展期费用
     */
    public void setAccountExtensionCharge(Long accountExtensionCharge) {
        this.accountExtensionCharge = accountExtensionCharge;
    }

    /**
     *已还正常本金
     */
    public Long getPaidNormalPrincipal() {
        return paidNormalPrincipal;
    }

    /**
     *已还正常本金
     */
    public void setPaidNormalPrincipal(Long paidNormalPrincipal) {
        this.paidNormalPrincipal = paidNormalPrincipal;
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
     *已还正常利息
     */
    public Long getPaidNormalInterest() {
        return paidNormalInterest;
    }

    /**
     *已还正常利息
     */
    public void setPaidNormalInterest(Long paidNormalInterest) {
        this.paidNormalInterest = paidNormalInterest;
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
     *已还正常服务费
     */
    public Long getPaidNormalServiceCharge() {
        return paidNormalServiceCharge;
    }

    /**
     *已还正常服务费
     */
    public void setPaidNormalServiceCharge(Long paidNormalServiceCharge) {
        this.paidNormalServiceCharge = paidNormalServiceCharge;
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
     *已还提前还款手续费
     */
    public Long getPaidEarlyRepaymentCharge() {
        return paidEarlyRepaymentCharge;
    }

    /**
     *已还提前还款手续费
     */
    public void setPaidEarlyRepaymentCharge(Long paidEarlyRepaymentCharge) {
        this.paidEarlyRepaymentCharge = paidEarlyRepaymentCharge;
    }

    /**
     *已还展期费用
     */
    public Long getPaidExtensionCharge() {
        return paidExtensionCharge;
    }

    /**
     *已还展期费用
     */
    public void setPaidExtensionCharge(Long paidExtensionCharge) {
        this.paidExtensionCharge = paidExtensionCharge;
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
     *逾期本金罚息
     */
    public Long getOverduePrincipalPenalty() {
        return overduePrincipalPenalty;
    }

    /**
     *逾期本金罚息
     */
    public void setOverduePrincipalPenalty(Long overduePrincipalPenalty) {
        this.overduePrincipalPenalty = overduePrincipalPenalty;
    }

    /**
     *逾期利息罚息
     */
    public Long getOverdueInterestPenalty() {
        return overdueInterestPenalty;
    }

    /**
     *逾期利息罚息
     */
    public void setOverdueInterestPenalty(Long overdueInterestPenalty) {
        this.overdueInterestPenalty = overdueInterestPenalty;
    }

    /**
     *逾期服务费罚息
     */
    public Long getOverdueServiceChargePenalty() {
        return overdueServiceChargePenalty;
    }

    /**
     *逾期服务费罚息
     */
    public void setOverdueServiceChargePenalty(Long overdueServiceChargePenalty) {
        this.overdueServiceChargePenalty = overdueServiceChargePenalty;
    }

    /**
     *其他罚息
     */
    public Long getOtherPenalty() {
        return otherPenalty;
    }

    /**
     *其他罚息
     */
    public void setOtherPenalty(Long otherPenalty) {
        this.otherPenalty = otherPenalty;
    }

    /**
     *融资客户Id
     */
    public String getFactorId() {
        return factorId;
    }

    /**
     *融资客户Id
     */
    public void setFactorId(String factorId) {
        this.factorId = factorId == null ? null : factorId.trim();
    }

    /**
     *本期展期天数，即MAX（节假日因素产生的展期天数，放款的展期天数）
     */
    public Integer getExtensionDays() {
        return extensionDays;
    }

    /**
     *本期展期天数，即MAX（节假日因素产生的展期天数，放款的展期天数）
     */
    public void setExtensionDays(Integer extensionDays) {
        this.extensionDays = extensionDays;
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
        RepaymentPlan other = (RepaymentPlan) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBusinessMode() == null ? other.getBusinessMode() == null : this.getBusinessMode().equals(other.getBusinessMode()))
            && (this.getInputMethod() == null ? other.getInputMethod() == null : this.getInputMethod().equals(other.getInputMethod()))
            && (this.getRepaymentPeriod() == null ? other.getRepaymentPeriod() == null : this.getRepaymentPeriod().equals(other.getRepaymentPeriod()))
            && (this.getFinanceId() == null ? other.getFinanceId() == null : this.getFinanceId().equals(other.getFinanceId()))
            && (this.getFinanceCompany() == null ? other.getFinanceCompany() == null : this.getFinanceCompany().equals(other.getFinanceCompany()))
            && (this.getCoreCompanyId() == null ? other.getCoreCompanyId() == null : this.getCoreCompanyId().equals(other.getCoreCompanyId()))
            && (this.getCoreCompanyName() == null ? other.getCoreCompanyName() == null : this.getCoreCompanyName().equals(other.getCoreCompanyName()))
            && (this.getFinanceAmount() == null ? other.getFinanceAmount() == null : this.getFinanceAmount().equals(other.getFinanceAmount()))
            && (this.getFinanceBalance() == null ? other.getFinanceBalance() == null : this.getFinanceBalance().equals(other.getFinanceBalance()))
            && (this.getValueDate() == null ? other.getValueDate() == null : this.getValueDate().equals(other.getValueDate()))
            && (this.getDueDate() == null ? other.getDueDate() == null : this.getDueDate().equals(other.getDueDate()))
            && (this.getSettleInterestDate() == null ? other.getSettleInterestDate() == null : this.getSettleInterestDate().equals(other.getSettleInterestDate()))
            && (this.getRepaymentDate() == null ? other.getRepaymentDate() == null : this.getRepaymentDate().equals(other.getRepaymentDate()))
            && (this.getAccountPrincipal() == null ? other.getAccountPrincipal() == null : this.getAccountPrincipal().equals(other.getAccountPrincipal()))
            && (this.getAccountInterest() == null ? other.getAccountInterest() == null : this.getAccountInterest().equals(other.getAccountInterest()))
            && (this.getAccountServiceCharge() == null ? other.getAccountServiceCharge() == null : this.getAccountServiceCharge().equals(other.getAccountServiceCharge()))
            && (this.getSettleStatus() == null ? other.getSettleStatus() == null : this.getSettleStatus().equals(other.getSettleStatus()))
            && (this.getLoanInfoId() == null ? other.getLoanInfoId() == null : this.getLoanInfoId().equals(other.getLoanInfoId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpateTime() == null ? other.getUpateTime() == null : this.getUpateTime().equals(other.getUpateTime()))
            && (this.getLoanNo() == null ? other.getLoanNo() == null : this.getLoanNo().equals(other.getLoanNo()))
            && (this.getLoanDate() == null ? other.getLoanDate() == null : this.getLoanDate().equals(other.getLoanDate()))
            && (this.getAccountExtensionCharge() == null ? other.getAccountExtensionCharge() == null : this.getAccountExtensionCharge().equals(other.getAccountExtensionCharge()))
            && (this.getPaidNormalPrincipal() == null ? other.getPaidNormalPrincipal() == null : this.getPaidNormalPrincipal().equals(other.getPaidNormalPrincipal()))
            && (this.getPaidOverduePrincipal() == null ? other.getPaidOverduePrincipal() == null : this.getPaidOverduePrincipal().equals(other.getPaidOverduePrincipal()))
            && (this.getPaidNormalInterest() == null ? other.getPaidNormalInterest() == null : this.getPaidNormalInterest().equals(other.getPaidNormalInterest()))
            && (this.getPaidOverdueInterest() == null ? other.getPaidOverdueInterest() == null : this.getPaidOverdueInterest().equals(other.getPaidOverdueInterest()))
            && (this.getPaidNormalServiceCharge() == null ? other.getPaidNormalServiceCharge() == null : this.getPaidNormalServiceCharge().equals(other.getPaidNormalServiceCharge()))
            && (this.getPaidOverdueServiceCharge() == null ? other.getPaidOverdueServiceCharge() == null : this.getPaidOverdueServiceCharge().equals(other.getPaidOverdueServiceCharge()))
            && (this.getPaidEarlyRepaymentCharge() == null ? other.getPaidEarlyRepaymentCharge() == null : this.getPaidEarlyRepaymentCharge().equals(other.getPaidEarlyRepaymentCharge()))
            && (this.getPaidExtensionCharge() == null ? other.getPaidExtensionCharge() == null : this.getPaidExtensionCharge().equals(other.getPaidExtensionCharge()))
            && (this.getPaidOverduePrincipalPenalty() == null ? other.getPaidOverduePrincipalPenalty() == null : this.getPaidOverduePrincipalPenalty().equals(other.getPaidOverduePrincipalPenalty()))
            && (this.getPaidOverdueInterestPenalty() == null ? other.getPaidOverdueInterestPenalty() == null : this.getPaidOverdueInterestPenalty().equals(other.getPaidOverdueInterestPenalty()))
            && (this.getPaidOverdueServiceChargePenalty() == null ? other.getPaidOverdueServiceChargePenalty() == null : this.getPaidOverdueServiceChargePenalty().equals(other.getPaidOverdueServiceChargePenalty()))
            && (this.getPaidOtherPenalty() == null ? other.getPaidOtherPenalty() == null : this.getPaidOtherPenalty().equals(other.getPaidOtherPenalty()))
            && (this.getOverduePrincipal() == null ? other.getOverduePrincipal() == null : this.getOverduePrincipal().equals(other.getOverduePrincipal()))
            && (this.getOverdueInterest() == null ? other.getOverdueInterest() == null : this.getOverdueInterest().equals(other.getOverdueInterest()))
            && (this.getOverdueServiceCharge() == null ? other.getOverdueServiceCharge() == null : this.getOverdueServiceCharge().equals(other.getOverdueServiceCharge()))
            && (this.getOverduePrincipalPenalty() == null ? other.getOverduePrincipalPenalty() == null : this.getOverduePrincipalPenalty().equals(other.getOverduePrincipalPenalty()))
            && (this.getOverdueInterestPenalty() == null ? other.getOverdueInterestPenalty() == null : this.getOverdueInterestPenalty().equals(other.getOverdueInterestPenalty()))
            && (this.getOverdueServiceChargePenalty() == null ? other.getOverdueServiceChargePenalty() == null : this.getOverdueServiceChargePenalty().equals(other.getOverdueServiceChargePenalty()))
            && (this.getOtherPenalty() == null ? other.getOtherPenalty() == null : this.getOtherPenalty().equals(other.getOtherPenalty()))
            && (this.getFactorId() == null ? other.getFactorId() == null : this.getFactorId().equals(other.getFactorId()))
            && (this.getExtensionDays() == null ? other.getExtensionDays() == null : this.getExtensionDays().equals(other.getExtensionDays()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBusinessMode() == null) ? 0 : getBusinessMode().hashCode());
        result = prime * result + ((getInputMethod() == null) ? 0 : getInputMethod().hashCode());
        result = prime * result + ((getRepaymentPeriod() == null) ? 0 : getRepaymentPeriod().hashCode());
        result = prime * result + ((getFinanceId() == null) ? 0 : getFinanceId().hashCode());
        result = prime * result + ((getFinanceCompany() == null) ? 0 : getFinanceCompany().hashCode());
        result = prime * result + ((getCoreCompanyId() == null) ? 0 : getCoreCompanyId().hashCode());
        result = prime * result + ((getCoreCompanyName() == null) ? 0 : getCoreCompanyName().hashCode());
        result = prime * result + ((getFinanceAmount() == null) ? 0 : getFinanceAmount().hashCode());
        result = prime * result + ((getFinanceBalance() == null) ? 0 : getFinanceBalance().hashCode());
        result = prime * result + ((getValueDate() == null) ? 0 : getValueDate().hashCode());
        result = prime * result + ((getDueDate() == null) ? 0 : getDueDate().hashCode());
        result = prime * result + ((getSettleInterestDate() == null) ? 0 : getSettleInterestDate().hashCode());
        result = prime * result + ((getRepaymentDate() == null) ? 0 : getRepaymentDate().hashCode());
        result = prime * result + ((getAccountPrincipal() == null) ? 0 : getAccountPrincipal().hashCode());
        result = prime * result + ((getAccountInterest() == null) ? 0 : getAccountInterest().hashCode());
        result = prime * result + ((getAccountServiceCharge() == null) ? 0 : getAccountServiceCharge().hashCode());
        result = prime * result + ((getSettleStatus() == null) ? 0 : getSettleStatus().hashCode());
        result = prime * result + ((getLoanInfoId() == null) ? 0 : getLoanInfoId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpateTime() == null) ? 0 : getUpateTime().hashCode());
        result = prime * result + ((getLoanNo() == null) ? 0 : getLoanNo().hashCode());
        result = prime * result + ((getLoanDate() == null) ? 0 : getLoanDate().hashCode());
        result = prime * result + ((getAccountExtensionCharge() == null) ? 0 : getAccountExtensionCharge().hashCode());
        result = prime * result + ((getPaidNormalPrincipal() == null) ? 0 : getPaidNormalPrincipal().hashCode());
        result = prime * result + ((getPaidOverduePrincipal() == null) ? 0 : getPaidOverduePrincipal().hashCode());
        result = prime * result + ((getPaidNormalInterest() == null) ? 0 : getPaidNormalInterest().hashCode());
        result = prime * result + ((getPaidOverdueInterest() == null) ? 0 : getPaidOverdueInterest().hashCode());
        result = prime * result + ((getPaidNormalServiceCharge() == null) ? 0 : getPaidNormalServiceCharge().hashCode());
        result = prime * result + ((getPaidOverdueServiceCharge() == null) ? 0 : getPaidOverdueServiceCharge().hashCode());
        result = prime * result + ((getPaidEarlyRepaymentCharge() == null) ? 0 : getPaidEarlyRepaymentCharge().hashCode());
        result = prime * result + ((getPaidExtensionCharge() == null) ? 0 : getPaidExtensionCharge().hashCode());
        result = prime * result + ((getPaidOverduePrincipalPenalty() == null) ? 0 : getPaidOverduePrincipalPenalty().hashCode());
        result = prime * result + ((getPaidOverdueInterestPenalty() == null) ? 0 : getPaidOverdueInterestPenalty().hashCode());
        result = prime * result + ((getPaidOverdueServiceChargePenalty() == null) ? 0 : getPaidOverdueServiceChargePenalty().hashCode());
        result = prime * result + ((getPaidOtherPenalty() == null) ? 0 : getPaidOtherPenalty().hashCode());
        result = prime * result + ((getOverduePrincipal() == null) ? 0 : getOverduePrincipal().hashCode());
        result = prime * result + ((getOverdueInterest() == null) ? 0 : getOverdueInterest().hashCode());
        result = prime * result + ((getOverdueServiceCharge() == null) ? 0 : getOverdueServiceCharge().hashCode());
        result = prime * result + ((getOverduePrincipalPenalty() == null) ? 0 : getOverduePrincipalPenalty().hashCode());
        result = prime * result + ((getOverdueInterestPenalty() == null) ? 0 : getOverdueInterestPenalty().hashCode());
        result = prime * result + ((getOverdueServiceChargePenalty() == null) ? 0 : getOverdueServiceChargePenalty().hashCode());
        result = prime * result + ((getOtherPenalty() == null) ? 0 : getOtherPenalty().hashCode());
        result = prime * result + ((getFactorId() == null) ? 0 : getFactorId().hashCode());
        result = prime * result + ((getExtensionDays() == null) ? 0 : getExtensionDays().hashCode());
        return result;
    }
}