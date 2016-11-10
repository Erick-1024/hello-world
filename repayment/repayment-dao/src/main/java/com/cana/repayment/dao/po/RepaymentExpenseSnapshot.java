/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.repayment.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RepaymentExpenseSnapshot implements Serializable {
    /**
     *固定费用快照id
     */
    private String id;

    /**
     *放款信息快照id
     */
    private String loanInfoSnapshotId;

    /**
     *固定费用Id
     */
    private String repaymentExpenseId;

    /**
     *还款信息Id
     */
    private String loanInfoId;

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
     *费用明目
     */
    private String expenseSubject;

    /**
     *计费方式
     */
    private String chargeMethod;

    /**
     *计费基准值
     */
    private Long chargeStandard;

    /**
     *计费比率（计费方式选择比率情况使用）
     */
    private BigDecimal chargeRatio;

    /**
     *计费定额（当选择计费方式是定额情况使用）
     */
    private Long chargeAmount;

    /**
     *应还金额
     */
    private Long repaymentAmount;

    /**
     *还款日
     */
    private String repaymentDate;

    /**
     *结清状态（已结清、未结清）
     */
    private String settleStatus;

    /**
     *业务模式（保理商+融资商等）
     */
    private String businessMode;

    /**
     *录入方式（excal、手动）
     */
    private String inputMethod;

    /**
     *放款日
     */
    private String loanNo;

    /**
     *已还金额
     */
    private Long paidAmount;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     *保理商id
     */
    private String factorId;

    private static final long serialVersionUID = 1L;

    /**
     *固定费用快照id
     */
    public String getId() {
        return id;
    }

    /**
     *固定费用快照id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *放款信息快照id
     */
    public String getLoanInfoSnapshotId() {
        return loanInfoSnapshotId;
    }

    /**
     *放款信息快照id
     */
    public void setLoanInfoSnapshotId(String loanInfoSnapshotId) {
        this.loanInfoSnapshotId = loanInfoSnapshotId == null ? null : loanInfoSnapshotId.trim();
    }

    /**
     *固定费用Id
     */
    public String getRepaymentExpenseId() {
        return repaymentExpenseId;
    }

    /**
     *固定费用Id
     */
    public void setRepaymentExpenseId(String repaymentExpenseId) {
        this.repaymentExpenseId = repaymentExpenseId == null ? null : repaymentExpenseId.trim();
    }

    /**
     *还款信息Id
     */
    public String getLoanInfoId() {
        return loanInfoId;
    }

    /**
     *还款信息Id
     */
    public void setLoanInfoId(String loanInfoId) {
        this.loanInfoId = loanInfoId == null ? null : loanInfoId.trim();
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
     *费用明目
     */
    public String getExpenseSubject() {
        return expenseSubject;
    }

    /**
     *费用明目
     */
    public void setExpenseSubject(String expenseSubject) {
        this.expenseSubject = expenseSubject == null ? null : expenseSubject.trim();
    }

    /**
     *计费方式
     */
    public String getChargeMethod() {
        return chargeMethod;
    }

    /**
     *计费方式
     */
    public void setChargeMethod(String chargeMethod) {
        this.chargeMethod = chargeMethod == null ? null : chargeMethod.trim();
    }

    /**
     *计费基准值
     */
    public Long getChargeStandard() {
        return chargeStandard;
    }

    /**
     *计费基准值
     */
    public void setChargeStandard(Long chargeStandard) {
        this.chargeStandard = chargeStandard;
    }

    /**
     *计费比率（计费方式选择比率情况使用）
     */
    public BigDecimal getChargeRatio() {
        return chargeRatio;
    }

    /**
     *计费比率（计费方式选择比率情况使用）
     */
    public void setChargeRatio(BigDecimal chargeRatio) {
        this.chargeRatio = chargeRatio;
    }

    /**
     *计费定额（当选择计费方式是定额情况使用）
     */
    public Long getChargeAmount() {
        return chargeAmount;
    }

    /**
     *计费定额（当选择计费方式是定额情况使用）
     */
    public void setChargeAmount(Long chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    /**
     *应还金额
     */
    public Long getRepaymentAmount() {
        return repaymentAmount;
    }

    /**
     *应还金额
     */
    public void setRepaymentAmount(Long repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
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
     *业务模式（保理商+融资商等）
     */
    public String getBusinessMode() {
        return businessMode;
    }

    /**
     *业务模式（保理商+融资商等）
     */
    public void setBusinessMode(String businessMode) {
        this.businessMode = businessMode == null ? null : businessMode.trim();
    }

    /**
     *录入方式（excal、手动）
     */
    public String getInputMethod() {
        return inputMethod;
    }

    /**
     *录入方式（excal、手动）
     */
    public void setInputMethod(String inputMethod) {
        this.inputMethod = inputMethod == null ? null : inputMethod.trim();
    }

    /**
     *放款日
     */
    public String getLoanNo() {
        return loanNo;
    }

    /**
     *放款日
     */
    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo == null ? null : loanNo.trim();
    }

    /**
     *已还金额
     */
    public Long getPaidAmount() {
        return paidAmount;
    }

    /**
     *已还金额
     */
    public void setPaidAmount(Long paidAmount) {
        this.paidAmount = paidAmount;
    }

    /**
     *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        RepaymentExpenseSnapshot other = (RepaymentExpenseSnapshot) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLoanInfoSnapshotId() == null ? other.getLoanInfoSnapshotId() == null : this.getLoanInfoSnapshotId().equals(other.getLoanInfoSnapshotId()))
            && (this.getRepaymentExpenseId() == null ? other.getRepaymentExpenseId() == null : this.getRepaymentExpenseId().equals(other.getRepaymentExpenseId()))
            && (this.getLoanInfoId() == null ? other.getLoanInfoId() == null : this.getLoanInfoId().equals(other.getLoanInfoId()))
            && (this.getFinanceId() == null ? other.getFinanceId() == null : this.getFinanceId().equals(other.getFinanceId()))
            && (this.getFinanceCompany() == null ? other.getFinanceCompany() == null : this.getFinanceCompany().equals(other.getFinanceCompany()))
            && (this.getCoreCompanyId() == null ? other.getCoreCompanyId() == null : this.getCoreCompanyId().equals(other.getCoreCompanyId()))
            && (this.getCoreCompanyName() == null ? other.getCoreCompanyName() == null : this.getCoreCompanyName().equals(other.getCoreCompanyName()))
            && (this.getExpenseSubject() == null ? other.getExpenseSubject() == null : this.getExpenseSubject().equals(other.getExpenseSubject()))
            && (this.getChargeMethod() == null ? other.getChargeMethod() == null : this.getChargeMethod().equals(other.getChargeMethod()))
            && (this.getChargeStandard() == null ? other.getChargeStandard() == null : this.getChargeStandard().equals(other.getChargeStandard()))
            && (this.getChargeRatio() == null ? other.getChargeRatio() == null : this.getChargeRatio().equals(other.getChargeRatio()))
            && (this.getChargeAmount() == null ? other.getChargeAmount() == null : this.getChargeAmount().equals(other.getChargeAmount()))
            && (this.getRepaymentAmount() == null ? other.getRepaymentAmount() == null : this.getRepaymentAmount().equals(other.getRepaymentAmount()))
            && (this.getRepaymentDate() == null ? other.getRepaymentDate() == null : this.getRepaymentDate().equals(other.getRepaymentDate()))
            && (this.getSettleStatus() == null ? other.getSettleStatus() == null : this.getSettleStatus().equals(other.getSettleStatus()))
            && (this.getBusinessMode() == null ? other.getBusinessMode() == null : this.getBusinessMode().equals(other.getBusinessMode()))
            && (this.getInputMethod() == null ? other.getInputMethod() == null : this.getInputMethod().equals(other.getInputMethod()))
            && (this.getLoanNo() == null ? other.getLoanNo() == null : this.getLoanNo().equals(other.getLoanNo()))
            && (this.getPaidAmount() == null ? other.getPaidAmount() == null : this.getPaidAmount().equals(other.getPaidAmount()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getFactorId() == null ? other.getFactorId() == null : this.getFactorId().equals(other.getFactorId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLoanInfoSnapshotId() == null) ? 0 : getLoanInfoSnapshotId().hashCode());
        result = prime * result + ((getRepaymentExpenseId() == null) ? 0 : getRepaymentExpenseId().hashCode());
        result = prime * result + ((getLoanInfoId() == null) ? 0 : getLoanInfoId().hashCode());
        result = prime * result + ((getFinanceId() == null) ? 0 : getFinanceId().hashCode());
        result = prime * result + ((getFinanceCompany() == null) ? 0 : getFinanceCompany().hashCode());
        result = prime * result + ((getCoreCompanyId() == null) ? 0 : getCoreCompanyId().hashCode());
        result = prime * result + ((getCoreCompanyName() == null) ? 0 : getCoreCompanyName().hashCode());
        result = prime * result + ((getExpenseSubject() == null) ? 0 : getExpenseSubject().hashCode());
        result = prime * result + ((getChargeMethod() == null) ? 0 : getChargeMethod().hashCode());
        result = prime * result + ((getChargeStandard() == null) ? 0 : getChargeStandard().hashCode());
        result = prime * result + ((getChargeRatio() == null) ? 0 : getChargeRatio().hashCode());
        result = prime * result + ((getChargeAmount() == null) ? 0 : getChargeAmount().hashCode());
        result = prime * result + ((getRepaymentAmount() == null) ? 0 : getRepaymentAmount().hashCode());
        result = prime * result + ((getRepaymentDate() == null) ? 0 : getRepaymentDate().hashCode());
        result = prime * result + ((getSettleStatus() == null) ? 0 : getSettleStatus().hashCode());
        result = prime * result + ((getBusinessMode() == null) ? 0 : getBusinessMode().hashCode());
        result = prime * result + ((getInputMethod() == null) ? 0 : getInputMethod().hashCode());
        result = prime * result + ((getLoanNo() == null) ? 0 : getLoanNo().hashCode());
        result = prime * result + ((getPaidAmount() == null) ? 0 : getPaidAmount().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getFactorId() == null) ? 0 : getFactorId().hashCode());
        return result;
    }
}