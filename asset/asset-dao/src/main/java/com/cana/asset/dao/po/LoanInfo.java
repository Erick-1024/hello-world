/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LoanInfo implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *业务合同号
     */
    private String businessContractNo;

    /**
     *创建放款的客户ID
     */
    private String factorId;

    /**
     *交易对手ID
     */
    private String counterpartyId;

    /**
     *融资客户Id
     */
    private String customerId;

    /**
     *融资客户公司名称
     */
    private String customerName;

    /**
     *项目名称
     */
    private String projectName;

    /**
     *业务产品，国内公开 有追索保理
     */
    private String businessProduct;

    /**
     *融资金额
     */
    private Long financeAmount;

    /**
     *融资余额
     */
    private Long financeBalance;

    /**
     *币种 
     */
    private String currency;

    /**
     *还本付息方式
     */
    private String repaymentMethod;

    /**
     *放款期限单位
     */
    private String loanPeriodUnit;

    /**
     *放款期限
     */
    private Integer loanPeriod;

    /**
     *计息基准天数，360或者365
     */
    private Integer dayCountConvention;

    /**
     *利率单位
     */
    private String interestRateUnit;

    /**
     *利率
     */
    private BigDecimal interestRate;

    /**
     *放款日
     */
    private String loanDate;

    /**
     *到期日
     */
    private String dueDate;

    /**
     *逾期费率，日息
     */
    private BigDecimal penaltyRate;

    /**
     *还款账号
     */
    private String accountNo;

    /**
     *结清状态（已结清、未结清）@see SettleStatus
     */
    private String settleStatus;

    /**
     *提前还款天数，保理放款取提前的最大天数
     */
    private Integer forwardDays;

    /**
     *逾期天数，保理放款取逾期的最大天数
     */
    private Integer overdueDays;

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

    /**
     *创建放款的客户ID
     */
    public String getFactorId() {
        return factorId;
    }

    /**
     *创建放款的客户ID
     */
    public void setFactorId(String factorId) {
        this.factorId = factorId == null ? null : factorId.trim();
    }

    /**
     *交易对手ID
     */
    public String getCounterpartyId() {
        return counterpartyId;
    }

    /**
     *交易对手ID
     */
    public void setCounterpartyId(String counterpartyId) {
        this.counterpartyId = counterpartyId == null ? null : counterpartyId.trim();
    }

    /**
     *融资客户Id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *融资客户Id
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     *融资客户公司名称
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *融资客户公司名称
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    /**
     *项目名称
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     *项目名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    /**
     *业务产品，国内公开 有追索保理
     */
    public String getBusinessProduct() {
        return businessProduct;
    }

    /**
     *业务产品，国内公开 有追索保理
     */
    public void setBusinessProduct(String businessProduct) {
        this.businessProduct = businessProduct == null ? null : businessProduct.trim();
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
     *还本付息方式
     */
    public String getRepaymentMethod() {
        return repaymentMethod;
    }

    /**
     *还本付息方式
     */
    public void setRepaymentMethod(String repaymentMethod) {
        this.repaymentMethod = repaymentMethod == null ? null : repaymentMethod.trim();
    }

    /**
     *放款期限单位
     */
    public String getLoanPeriodUnit() {
        return loanPeriodUnit;
    }

    /**
     *放款期限单位
     */
    public void setLoanPeriodUnit(String loanPeriodUnit) {
        this.loanPeriodUnit = loanPeriodUnit == null ? null : loanPeriodUnit.trim();
    }

    /**
     *放款期限
     */
    public Integer getLoanPeriod() {
        return loanPeriod;
    }

    /**
     *放款期限
     */
    public void setLoanPeriod(Integer loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    /**
     *计息基准天数，360或者365
     */
    public Integer getDayCountConvention() {
        return dayCountConvention;
    }

    /**
     *计息基准天数，360或者365
     */
    public void setDayCountConvention(Integer dayCountConvention) {
        this.dayCountConvention = dayCountConvention;
    }

    /**
     *利率单位
     */
    public String getInterestRateUnit() {
        return interestRateUnit;
    }

    /**
     *利率单位
     */
    public void setInterestRateUnit(String interestRateUnit) {
        this.interestRateUnit = interestRateUnit == null ? null : interestRateUnit.trim();
    }

    /**
     *利率
     */
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    /**
     *利率
     */
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
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
     *逾期费率，日息
     */
    public BigDecimal getPenaltyRate() {
        return penaltyRate;
    }

    /**
     *逾期费率，日息
     */
    public void setPenaltyRate(BigDecimal penaltyRate) {
        this.penaltyRate = penaltyRate;
    }

    /**
     *还款账号
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     *还款账号
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
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
     *提前还款天数，保理放款取提前的最大天数
     */
    public Integer getForwardDays() {
        return forwardDays;
    }

    /**
     *提前还款天数，保理放款取提前的最大天数
     */
    public void setForwardDays(Integer forwardDays) {
        this.forwardDays = forwardDays;
    }

    /**
     *逾期天数，保理放款取逾期的最大天数
     */
    public Integer getOverdueDays() {
        return overdueDays;
    }

    /**
     *逾期天数，保理放款取逾期的最大天数
     */
    public void setOverdueDays(Integer overdueDays) {
        this.overdueDays = overdueDays;
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
        LoanInfo other = (LoanInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBusinessContractNo() == null ? other.getBusinessContractNo() == null : this.getBusinessContractNo().equals(other.getBusinessContractNo()))
            && (this.getFactorId() == null ? other.getFactorId() == null : this.getFactorId().equals(other.getFactorId()))
            && (this.getCounterpartyId() == null ? other.getCounterpartyId() == null : this.getCounterpartyId().equals(other.getCounterpartyId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getProjectName() == null ? other.getProjectName() == null : this.getProjectName().equals(other.getProjectName()))
            && (this.getBusinessProduct() == null ? other.getBusinessProduct() == null : this.getBusinessProduct().equals(other.getBusinessProduct()))
            && (this.getFinanceAmount() == null ? other.getFinanceAmount() == null : this.getFinanceAmount().equals(other.getFinanceAmount()))
            && (this.getFinanceBalance() == null ? other.getFinanceBalance() == null : this.getFinanceBalance().equals(other.getFinanceBalance()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getRepaymentMethod() == null ? other.getRepaymentMethod() == null : this.getRepaymentMethod().equals(other.getRepaymentMethod()))
            && (this.getLoanPeriodUnit() == null ? other.getLoanPeriodUnit() == null : this.getLoanPeriodUnit().equals(other.getLoanPeriodUnit()))
            && (this.getLoanPeriod() == null ? other.getLoanPeriod() == null : this.getLoanPeriod().equals(other.getLoanPeriod()))
            && (this.getDayCountConvention() == null ? other.getDayCountConvention() == null : this.getDayCountConvention().equals(other.getDayCountConvention()))
            && (this.getInterestRateUnit() == null ? other.getInterestRateUnit() == null : this.getInterestRateUnit().equals(other.getInterestRateUnit()))
            && (this.getInterestRate() == null ? other.getInterestRate() == null : this.getInterestRate().equals(other.getInterestRate()))
            && (this.getLoanDate() == null ? other.getLoanDate() == null : this.getLoanDate().equals(other.getLoanDate()))
            && (this.getDueDate() == null ? other.getDueDate() == null : this.getDueDate().equals(other.getDueDate()))
            && (this.getPenaltyRate() == null ? other.getPenaltyRate() == null : this.getPenaltyRate().equals(other.getPenaltyRate()))
            && (this.getAccountNo() == null ? other.getAccountNo() == null : this.getAccountNo().equals(other.getAccountNo()))
            && (this.getSettleStatus() == null ? other.getSettleStatus() == null : this.getSettleStatus().equals(other.getSettleStatus()))
            && (this.getForwardDays() == null ? other.getForwardDays() == null : this.getForwardDays().equals(other.getForwardDays()))
            && (this.getOverdueDays() == null ? other.getOverdueDays() == null : this.getOverdueDays().equals(other.getOverdueDays()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBusinessContractNo() == null) ? 0 : getBusinessContractNo().hashCode());
        result = prime * result + ((getFactorId() == null) ? 0 : getFactorId().hashCode());
        result = prime * result + ((getCounterpartyId() == null) ? 0 : getCounterpartyId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getProjectName() == null) ? 0 : getProjectName().hashCode());
        result = prime * result + ((getBusinessProduct() == null) ? 0 : getBusinessProduct().hashCode());
        result = prime * result + ((getFinanceAmount() == null) ? 0 : getFinanceAmount().hashCode());
        result = prime * result + ((getFinanceBalance() == null) ? 0 : getFinanceBalance().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getRepaymentMethod() == null) ? 0 : getRepaymentMethod().hashCode());
        result = prime * result + ((getLoanPeriodUnit() == null) ? 0 : getLoanPeriodUnit().hashCode());
        result = prime * result + ((getLoanPeriod() == null) ? 0 : getLoanPeriod().hashCode());
        result = prime * result + ((getDayCountConvention() == null) ? 0 : getDayCountConvention().hashCode());
        result = prime * result + ((getInterestRateUnit() == null) ? 0 : getInterestRateUnit().hashCode());
        result = prime * result + ((getInterestRate() == null) ? 0 : getInterestRate().hashCode());
        result = prime * result + ((getLoanDate() == null) ? 0 : getLoanDate().hashCode());
        result = prime * result + ((getDueDate() == null) ? 0 : getDueDate().hashCode());
        result = prime * result + ((getPenaltyRate() == null) ? 0 : getPenaltyRate().hashCode());
        result = prime * result + ((getAccountNo() == null) ? 0 : getAccountNo().hashCode());
        result = prime * result + ((getSettleStatus() == null) ? 0 : getSettleStatus().hashCode());
        result = prime * result + ((getForwardDays() == null) ? 0 : getForwardDays().hashCode());
        result = prime * result + ((getOverdueDays() == null) ? 0 : getOverdueDays().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}