/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.util.Date;

public class BusinessBasicInfo implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *保理商Id
     */
    private String factorId;

    /**
     *保理商名称
     */
    private String factorName;

    /**
     *客户Id
     */
    private String customerId;

    /**
     *客户名称
     */
    private String customerName;

    /**
     *业务合同号
     */
    private String businessContractNo;

    /**
     *币种
     */
    private String currency;

    /**
     *项目名称
     */
    private String projectName;

    /**
     *业务产品
     */
    private String businessProduct;

    /**
     *业务模式
     */
    private String businessMode;

    /**
     *合同起始日期
     */
    private String contractStartDate;

    /**
     *合同到期日期
     */
    private String contractEndDate;

    /**
     *利率
     */
    private String interestRate;

    /**
     *计息方式
     */
    private String interestType;

    /**
     *手续费率
     */
    private String feeRate;

    /**
     *罚息利率
     */
    private String penaltyRate;

    /**
     *宽限期
     */
    private String extensionDays;

    /**
     *付款期限
     */
    private String paymentPeriod;

    /**
     *单证类型
     */
    private String receiptType;

    /**
     *还款来源
     */
    private String repaymentSource;

    /**
     *还款安排
     */
    private String repaymentArrangement;

    /**
     *资金用途
     */
    private String fundUsage;

    /**
     *监控方案
     */
    private String monitoringSolution;

    /**
     *增信措施
     */
    private String increaseTrustMeasures;

    /**
     *约束性条款
     */
    private String bindingProvisions;

    /**
     *放款状态
     */
    private String loanState;

    /**
     *保理专户户名
     */
    private String factoringAccountName;

    /**
     *保理专用账户开户行
     */
    private String factoringAccountBankAddress;

    /**
     *保理专用账户
     */
    private String factoringAccount;

    /**
     *结算账户户名
     */
    private String settlementAccountName;

    /**
     *结算账户开户行
     */
    private String settlementAccountBankAddress;

    /**
     *结算账户账号
     */
    private String settlementAccount;

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
     *保理商Id
     */
    public String getFactorId() {
        return factorId;
    }

    /**
     *保理商Id
     */
    public void setFactorId(String factorId) {
        this.factorId = factorId == null ? null : factorId.trim();
    }

    /**
     *保理商名称
     */
    public String getFactorName() {
        return factorName;
    }

    /**
     *保理商名称
     */
    public void setFactorName(String factorName) {
        this.factorName = factorName == null ? null : factorName.trim();
    }

    /**
     *客户Id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *客户Id
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     *客户名称
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *客户名称
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
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
     *业务产品
     */
    public String getBusinessProduct() {
        return businessProduct;
    }

    /**
     *业务产品
     */
    public void setBusinessProduct(String businessProduct) {
        this.businessProduct = businessProduct == null ? null : businessProduct.trim();
    }

    /**
     *业务模式
     */
    public String getBusinessMode() {
        return businessMode;
    }

    /**
     *业务模式
     */
    public void setBusinessMode(String businessMode) {
        this.businessMode = businessMode == null ? null : businessMode.trim();
    }

    /**
     *合同起始日期
     */
    public String getContractStartDate() {
        return contractStartDate;
    }

    /**
     *合同起始日期
     */
    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate == null ? null : contractStartDate.trim();
    }

    /**
     *合同到期日期
     */
    public String getContractEndDate() {
        return contractEndDate;
    }

    /**
     *合同到期日期
     */
    public void setContractEndDate(String contractEndDate) {
        this.contractEndDate = contractEndDate == null ? null : contractEndDate.trim();
    }

    /**
     *利率
     */
    public String getInterestRate() {
        return interestRate;
    }

    /**
     *利率
     */
    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate == null ? null : interestRate.trim();
    }

    /**
     *计息方式
     */
    public String getInterestType() {
        return interestType;
    }

    /**
     *计息方式
     */
    public void setInterestType(String interestType) {
        this.interestType = interestType == null ? null : interestType.trim();
    }

    /**
     *手续费率
     */
    public String getFeeRate() {
        return feeRate;
    }

    /**
     *手续费率
     */
    public void setFeeRate(String feeRate) {
        this.feeRate = feeRate == null ? null : feeRate.trim();
    }

    /**
     *罚息利率
     */
    public String getPenaltyRate() {
        return penaltyRate;
    }

    /**
     *罚息利率
     */
    public void setPenaltyRate(String penaltyRate) {
        this.penaltyRate = penaltyRate == null ? null : penaltyRate.trim();
    }

    /**
     *宽限期
     */
    public String getExtensionDays() {
        return extensionDays;
    }

    /**
     *宽限期
     */
    public void setExtensionDays(String extensionDays) {
        this.extensionDays = extensionDays == null ? null : extensionDays.trim();
    }

    /**
     *付款期限
     */
    public String getPaymentPeriod() {
        return paymentPeriod;
    }

    /**
     *付款期限
     */
    public void setPaymentPeriod(String paymentPeriod) {
        this.paymentPeriod = paymentPeriod == null ? null : paymentPeriod.trim();
    }

    /**
     *单证类型
     */
    public String getReceiptType() {
        return receiptType;
    }

    /**
     *单证类型
     */
    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType == null ? null : receiptType.trim();
    }

    /**
     *还款来源
     */
    public String getRepaymentSource() {
        return repaymentSource;
    }

    /**
     *还款来源
     */
    public void setRepaymentSource(String repaymentSource) {
        this.repaymentSource = repaymentSource == null ? null : repaymentSource.trim();
    }

    /**
     *还款安排
     */
    public String getRepaymentArrangement() {
        return repaymentArrangement;
    }

    /**
     *还款安排
     */
    public void setRepaymentArrangement(String repaymentArrangement) {
        this.repaymentArrangement = repaymentArrangement == null ? null : repaymentArrangement.trim();
    }

    /**
     *资金用途
     */
    public String getFundUsage() {
        return fundUsage;
    }

    /**
     *资金用途
     */
    public void setFundUsage(String fundUsage) {
        this.fundUsage = fundUsage == null ? null : fundUsage.trim();
    }

    /**
     *监控方案
     */
    public String getMonitoringSolution() {
        return monitoringSolution;
    }

    /**
     *监控方案
     */
    public void setMonitoringSolution(String monitoringSolution) {
        this.monitoringSolution = monitoringSolution == null ? null : monitoringSolution.trim();
    }

    /**
     *增信措施
     */
    public String getIncreaseTrustMeasures() {
        return increaseTrustMeasures;
    }

    /**
     *增信措施
     */
    public void setIncreaseTrustMeasures(String increaseTrustMeasures) {
        this.increaseTrustMeasures = increaseTrustMeasures == null ? null : increaseTrustMeasures.trim();
    }

    /**
     *约束性条款
     */
    public String getBindingProvisions() {
        return bindingProvisions;
    }

    /**
     *约束性条款
     */
    public void setBindingProvisions(String bindingProvisions) {
        this.bindingProvisions = bindingProvisions == null ? null : bindingProvisions.trim();
    }

    /**
     *放款状态
     */
    public String getLoanState() {
        return loanState;
    }

    /**
     *放款状态
     */
    public void setLoanState(String loanState) {
        this.loanState = loanState == null ? null : loanState.trim();
    }

    /**
     *保理专户户名
     */
    public String getFactoringAccountName() {
        return factoringAccountName;
    }

    /**
     *保理专户户名
     */
    public void setFactoringAccountName(String factoringAccountName) {
        this.factoringAccountName = factoringAccountName == null ? null : factoringAccountName.trim();
    }

    /**
     *保理专用账户开户行
     */
    public String getFactoringAccountBankAddress() {
        return factoringAccountBankAddress;
    }

    /**
     *保理专用账户开户行
     */
    public void setFactoringAccountBankAddress(String factoringAccountBankAddress) {
        this.factoringAccountBankAddress = factoringAccountBankAddress == null ? null : factoringAccountBankAddress.trim();
    }

    /**
     *保理专用账户
     */
    public String getFactoringAccount() {
        return factoringAccount;
    }

    /**
     *保理专用账户
     */
    public void setFactoringAccount(String factoringAccount) {
        this.factoringAccount = factoringAccount == null ? null : factoringAccount.trim();
    }

    /**
     *结算账户户名
     */
    public String getSettlementAccountName() {
        return settlementAccountName;
    }

    /**
     *结算账户户名
     */
    public void setSettlementAccountName(String settlementAccountName) {
        this.settlementAccountName = settlementAccountName == null ? null : settlementAccountName.trim();
    }

    /**
     *结算账户开户行
     */
    public String getSettlementAccountBankAddress() {
        return settlementAccountBankAddress;
    }

    /**
     *结算账户开户行
     */
    public void setSettlementAccountBankAddress(String settlementAccountBankAddress) {
        this.settlementAccountBankAddress = settlementAccountBankAddress == null ? null : settlementAccountBankAddress.trim();
    }

    /**
     *结算账户账号
     */
    public String getSettlementAccount() {
        return settlementAccount;
    }

    /**
     *结算账户账号
     */
    public void setSettlementAccount(String settlementAccount) {
        this.settlementAccount = settlementAccount == null ? null : settlementAccount.trim();
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
        BusinessBasicInfo other = (BusinessBasicInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFactorId() == null ? other.getFactorId() == null : this.getFactorId().equals(other.getFactorId()))
            && (this.getFactorName() == null ? other.getFactorName() == null : this.getFactorName().equals(other.getFactorName()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getBusinessContractNo() == null ? other.getBusinessContractNo() == null : this.getBusinessContractNo().equals(other.getBusinessContractNo()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getProjectName() == null ? other.getProjectName() == null : this.getProjectName().equals(other.getProjectName()))
            && (this.getBusinessProduct() == null ? other.getBusinessProduct() == null : this.getBusinessProduct().equals(other.getBusinessProduct()))
            && (this.getBusinessMode() == null ? other.getBusinessMode() == null : this.getBusinessMode().equals(other.getBusinessMode()))
            && (this.getContractStartDate() == null ? other.getContractStartDate() == null : this.getContractStartDate().equals(other.getContractStartDate()))
            && (this.getContractEndDate() == null ? other.getContractEndDate() == null : this.getContractEndDate().equals(other.getContractEndDate()))
            && (this.getInterestRate() == null ? other.getInterestRate() == null : this.getInterestRate().equals(other.getInterestRate()))
            && (this.getInterestType() == null ? other.getInterestType() == null : this.getInterestType().equals(other.getInterestType()))
            && (this.getFeeRate() == null ? other.getFeeRate() == null : this.getFeeRate().equals(other.getFeeRate()))
            && (this.getPenaltyRate() == null ? other.getPenaltyRate() == null : this.getPenaltyRate().equals(other.getPenaltyRate()))
            && (this.getExtensionDays() == null ? other.getExtensionDays() == null : this.getExtensionDays().equals(other.getExtensionDays()))
            && (this.getPaymentPeriod() == null ? other.getPaymentPeriod() == null : this.getPaymentPeriod().equals(other.getPaymentPeriod()))
            && (this.getReceiptType() == null ? other.getReceiptType() == null : this.getReceiptType().equals(other.getReceiptType()))
            && (this.getRepaymentSource() == null ? other.getRepaymentSource() == null : this.getRepaymentSource().equals(other.getRepaymentSource()))
            && (this.getRepaymentArrangement() == null ? other.getRepaymentArrangement() == null : this.getRepaymentArrangement().equals(other.getRepaymentArrangement()))
            && (this.getFundUsage() == null ? other.getFundUsage() == null : this.getFundUsage().equals(other.getFundUsage()))
            && (this.getMonitoringSolution() == null ? other.getMonitoringSolution() == null : this.getMonitoringSolution().equals(other.getMonitoringSolution()))
            && (this.getIncreaseTrustMeasures() == null ? other.getIncreaseTrustMeasures() == null : this.getIncreaseTrustMeasures().equals(other.getIncreaseTrustMeasures()))
            && (this.getBindingProvisions() == null ? other.getBindingProvisions() == null : this.getBindingProvisions().equals(other.getBindingProvisions()))
            && (this.getLoanState() == null ? other.getLoanState() == null : this.getLoanState().equals(other.getLoanState()))
            && (this.getFactoringAccountName() == null ? other.getFactoringAccountName() == null : this.getFactoringAccountName().equals(other.getFactoringAccountName()))
            && (this.getFactoringAccountBankAddress() == null ? other.getFactoringAccountBankAddress() == null : this.getFactoringAccountBankAddress().equals(other.getFactoringAccountBankAddress()))
            && (this.getFactoringAccount() == null ? other.getFactoringAccount() == null : this.getFactoringAccount().equals(other.getFactoringAccount()))
            && (this.getSettlementAccountName() == null ? other.getSettlementAccountName() == null : this.getSettlementAccountName().equals(other.getSettlementAccountName()))
            && (this.getSettlementAccountBankAddress() == null ? other.getSettlementAccountBankAddress() == null : this.getSettlementAccountBankAddress().equals(other.getSettlementAccountBankAddress()))
            && (this.getSettlementAccount() == null ? other.getSettlementAccount() == null : this.getSettlementAccount().equals(other.getSettlementAccount()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFactorId() == null) ? 0 : getFactorId().hashCode());
        result = prime * result + ((getFactorName() == null) ? 0 : getFactorName().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getBusinessContractNo() == null) ? 0 : getBusinessContractNo().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getProjectName() == null) ? 0 : getProjectName().hashCode());
        result = prime * result + ((getBusinessProduct() == null) ? 0 : getBusinessProduct().hashCode());
        result = prime * result + ((getBusinessMode() == null) ? 0 : getBusinessMode().hashCode());
        result = prime * result + ((getContractStartDate() == null) ? 0 : getContractStartDate().hashCode());
        result = prime * result + ((getContractEndDate() == null) ? 0 : getContractEndDate().hashCode());
        result = prime * result + ((getInterestRate() == null) ? 0 : getInterestRate().hashCode());
        result = prime * result + ((getInterestType() == null) ? 0 : getInterestType().hashCode());
        result = prime * result + ((getFeeRate() == null) ? 0 : getFeeRate().hashCode());
        result = prime * result + ((getPenaltyRate() == null) ? 0 : getPenaltyRate().hashCode());
        result = prime * result + ((getExtensionDays() == null) ? 0 : getExtensionDays().hashCode());
        result = prime * result + ((getPaymentPeriod() == null) ? 0 : getPaymentPeriod().hashCode());
        result = prime * result + ((getReceiptType() == null) ? 0 : getReceiptType().hashCode());
        result = prime * result + ((getRepaymentSource() == null) ? 0 : getRepaymentSource().hashCode());
        result = prime * result + ((getRepaymentArrangement() == null) ? 0 : getRepaymentArrangement().hashCode());
        result = prime * result + ((getFundUsage() == null) ? 0 : getFundUsage().hashCode());
        result = prime * result + ((getMonitoringSolution() == null) ? 0 : getMonitoringSolution().hashCode());
        result = prime * result + ((getIncreaseTrustMeasures() == null) ? 0 : getIncreaseTrustMeasures().hashCode());
        result = prime * result + ((getBindingProvisions() == null) ? 0 : getBindingProvisions().hashCode());
        result = prime * result + ((getLoanState() == null) ? 0 : getLoanState().hashCode());
        result = prime * result + ((getFactoringAccountName() == null) ? 0 : getFactoringAccountName().hashCode());
        result = prime * result + ((getFactoringAccountBankAddress() == null) ? 0 : getFactoringAccountBankAddress().hashCode());
        result = prime * result + ((getFactoringAccount() == null) ? 0 : getFactoringAccount().hashCode());
        result = prime * result + ((getSettlementAccountName() == null) ? 0 : getSettlementAccountName().hashCode());
        result = prime * result + ((getSettlementAccountBankAddress() == null) ? 0 : getSettlementAccountBankAddress().hashCode());
        result = prime * result + ((getSettlementAccount() == null) ? 0 : getSettlementAccount().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}