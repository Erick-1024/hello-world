/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UnderlyingAssetLog implements Serializable {
    /**
     *日志主键
     */
    private String id;

    /**
     *放款编号
     */
    private String loanInfoId;

    /**
     *业务合同号
     */
    private String businessContractNo;

    /**
     *借款人姓名，即放款中的融资客户公司名称
     */
    private String customerName;

    /**
     *借款人经济类型类型，@see EconomicCategoryEnum
     */
    private String customerEconomicCategory;

    /**
     *交易对手名称
     */
    private String counterparty;

    /**
     *交易对手经济类型，@see EconomicCategoryEnum
     */
    private String counterpartyEconomicCategory;

    /**
     *借款人所在地区，@see CustomerCityTypeEnum
     */
    private String customerCity;

    /**
     *借款人所属行业，@see IndustryTypeEnum
     */
    private String customerIndustry;

    /**
     *交易对手所在地区，@see CustomerCityTypeEnum
     */
    private String counterpartyCity;

    /**
     *交易对手所属行业，@see IndustryTypeEnum
     */
    private String counterpartyIndustry;

    /**
     *授信额度
     */
    private Long creditLimit;

    /**
     *可用额度
     */
    private Long creditBalance;

    /**
     *交易对手非承保额度，保理放款不存在此值
     */
    private Long counterpartyLimit;

    /**
     *交易对手非承保余额，保理放款不存在此值
     */
    private Long counterpartyBalance;

    /**
     *应收账款金额
     */
    private Long invoiceAmount;

    /**
     *应收账款余额
     */
    private Long invoiceBalance;

    /**
     *融资金额
     */
    private Long financeAmount;

    /**
     *融资余额
     */
    private Long financeBalance;

    /**
     *放款日（起息日）
     */
    private String loanDate;

    /**
     *到期日
     */
    private String dueDate;

    /**
     *还款方式
     */
    private String repaymentMethod;

    /**
     *利率单位
     */
    private String interestRateUnit;

    /**
     *利率
     */
    private BigDecimal interestRate;

    /**
     *放款期限
     */
    private String loanPeriod;

    /**
     *基础资产关联的专项计划编号
     */
    private String specialProgramId;
    
    /**
     *基础资产关联的专项计划名称
     */
    private String specialProgramName;

    /**
     *创建基础资产的客户ID
     */
    private String factorId;

    /**
     *操作人的企业名称
     */
    private String operateCompanyName;

    /**
     *操作人的用户名
     */
    private String operateUsername;

    /**
     *操作类型，@see UnderlyingAssetOperateTypeEnum
     */
    private String operateType;

    /**
     *操作时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     *日志主键
     */
    public String getId() {
        return id;
    }

    /**
     *日志主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *放款编号
     */
    public String getLoanInfoId() {
        return loanInfoId;
    }

    /**
     *放款编号
     */
    public void setLoanInfoId(String loanInfoId) {
        this.loanInfoId = loanInfoId == null ? null : loanInfoId.trim();
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
     *借款人姓名，即放款中的融资客户公司名称
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *借款人姓名，即放款中的融资客户公司名称
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    /**
     *借款人经济类型类型，@see EconomicCategoryEnum
     */
    public String getCustomerEconomicCategory() {
        return customerEconomicCategory;
    }

    /**
     *借款人经济类型类型，@see EconomicCategoryEnum
     */
    public void setCustomerEconomicCategory(String customerEconomicCategory) {
        this.customerEconomicCategory = customerEconomicCategory == null ? null : customerEconomicCategory.trim();
    }

    /**
     *交易对手名称
     */
    public String getCounterparty() {
        return counterparty;
    }

    /**
     *交易对手名称
     */
    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty == null ? null : counterparty.trim();
    }

    /**
     *交易对手经济类型，@see EconomicCategoryEnum
     */
    public String getCounterpartyEconomicCategory() {
        return counterpartyEconomicCategory;
    }

    /**
     *交易对手经济类型，@see EconomicCategoryEnum
     */
    public void setCounterpartyEconomicCategory(String counterpartyEconomicCategory) {
        this.counterpartyEconomicCategory = counterpartyEconomicCategory == null ? null : counterpartyEconomicCategory.trim();
    }

    /**
     *借款人所在地区，@see CustomerCityTypeEnum
     */
    public String getCustomerCity() {
        return customerCity;
    }

    /**
     *借款人所在地区，@see CustomerCityTypeEnum
     */
    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity == null ? null : customerCity.trim();
    }

    /**
     *借款人所属行业，@see IndustryTypeEnum
     */
    public String getCustomerIndustry() {
        return customerIndustry;
    }

    /**
     *借款人所属行业，@see IndustryTypeEnum
     */
    public void setCustomerIndustry(String customerIndustry) {
        this.customerIndustry = customerIndustry == null ? null : customerIndustry.trim();
    }

    /**
     *交易对手所在地区，@see CustomerCityTypeEnum
     */
    public String getCounterpartyCity() {
        return counterpartyCity;
    }

    /**
     *交易对手所在地区，@see CustomerCityTypeEnum
     */
    public void setCounterpartyCity(String counterpartyCity) {
        this.counterpartyCity = counterpartyCity == null ? null : counterpartyCity.trim();
    }

    /**
     *交易对手所属行业，@see IndustryTypeEnum
     */
    public String getCounterpartyIndustry() {
        return counterpartyIndustry;
    }

    /**
     *交易对手所属行业，@see IndustryTypeEnum
     */
    public void setCounterpartyIndustry(String counterpartyIndustry) {
        this.counterpartyIndustry = counterpartyIndustry == null ? null : counterpartyIndustry.trim();
    }

    /**
     *授信额度
     */
    public Long getCreditLimit() {
        return creditLimit;
    }

    /**
     *授信额度
     */
    public void setCreditLimit(Long creditLimit) {
        this.creditLimit = creditLimit;
    }

    /**
     *可用额度
     */
    public Long getCreditBalance() {
        return creditBalance;
    }

    /**
     *可用额度
     */
    public void setCreditBalance(Long creditBalance) {
        this.creditBalance = creditBalance;
    }

    /**
     *交易对手非承保额度，保理放款不存在此值
     */
    public Long getCounterpartyLimit() {
        return counterpartyLimit;
    }

    /**
     *交易对手非承保额度，保理放款不存在此值
     */
    public void setCounterpartyLimit(Long counterpartyLimit) {
        this.counterpartyLimit = counterpartyLimit;
    }

    /**
     *交易对手非承保余额，保理放款不存在此值
     */
    public Long getCounterpartyBalance() {
        return counterpartyBalance;
    }

    /**
     *交易对手非承保余额，保理放款不存在此值
     */
    public void setCounterpartyBalance(Long counterpartyBalance) {
        this.counterpartyBalance = counterpartyBalance;
    }

    /**
     *应收账款金额
     */
    public Long getInvoiceAmount() {
        return invoiceAmount;
    }

    /**
     *应收账款金额
     */
    public void setInvoiceAmount(Long invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    /**
     *应收账款余额
     */
    public Long getInvoiceBalance() {
        return invoiceBalance;
    }

    /**
     *应收账款余额
     */
    public void setInvoiceBalance(Long invoiceBalance) {
        this.invoiceBalance = invoiceBalance;
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
     *放款日（起息日）
     */
    public String getLoanDate() {
        return loanDate;
    }

    /**
     *放款日（起息日）
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
     *还款方式
     */
    public String getRepaymentMethod() {
        return repaymentMethod;
    }

    /**
     *还款方式
     */
    public void setRepaymentMethod(String repaymentMethod) {
        this.repaymentMethod = repaymentMethod == null ? null : repaymentMethod.trim();
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
     *放款期限
     */
    public String getLoanPeriod() {
        return loanPeriod;
    }

    /**
     *放款期限
     */
    public void setLoanPeriod(String loanPeriod) {
        this.loanPeriod = loanPeriod == null ? null : loanPeriod.trim();
    }

    /**
     *基础资产关联的专项计划编号
     */
    public String getSpecialProgramId() {
        return specialProgramId;
    }

    /**
     *基础资产关联的专项计划编号
     */
    public void setSpecialProgramId(String specialProgramId) {
        this.specialProgramId = specialProgramId == null ? null : specialProgramId.trim();
    }

    /**
     *创建基础资产的客户ID
     */
    public String getFactorId() {
        return factorId;
    }

    /**
     *创建基础资产的客户ID
     */
    public void setFactorId(String factorId) {
        this.factorId = factorId == null ? null : factorId.trim();
    }

    /**
     *操作人的企业名称
     */
    public String getOperateCompanyName() {
        return operateCompanyName;
    }

    /**
     *操作人的企业名称
     */
    public void setOperateCompanyName(String operateCompanyName) {
        this.operateCompanyName = operateCompanyName == null ? null : operateCompanyName.trim();
    }

    /**
     *操作人的用户名
     */
    public String getOperateUsername() {
        return operateUsername;
    }

    /**
     *操作人的用户名
     */
    public void setOperateUsername(String operateUsername) {
        this.operateUsername = operateUsername == null ? null : operateUsername.trim();
    }

    /**
     *操作类型，@see UnderlyingAssetOperateTypeEnum
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     *操作类型，@see UnderlyingAssetOperateTypeEnum
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    /**
     *操作时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *操作时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        UnderlyingAssetLog other = (UnderlyingAssetLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLoanInfoId() == null ? other.getLoanInfoId() == null : this.getLoanInfoId().equals(other.getLoanInfoId()))
            && (this.getBusinessContractNo() == null ? other.getBusinessContractNo() == null : this.getBusinessContractNo().equals(other.getBusinessContractNo()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getCustomerEconomicCategory() == null ? other.getCustomerEconomicCategory() == null : this.getCustomerEconomicCategory().equals(other.getCustomerEconomicCategory()))
            && (this.getCounterparty() == null ? other.getCounterparty() == null : this.getCounterparty().equals(other.getCounterparty()))
            && (this.getCounterpartyEconomicCategory() == null ? other.getCounterpartyEconomicCategory() == null : this.getCounterpartyEconomicCategory().equals(other.getCounterpartyEconomicCategory()))
            && (this.getCustomerCity() == null ? other.getCustomerCity() == null : this.getCustomerCity().equals(other.getCustomerCity()))
            && (this.getCustomerIndustry() == null ? other.getCustomerIndustry() == null : this.getCustomerIndustry().equals(other.getCustomerIndustry()))
            && (this.getCounterpartyCity() == null ? other.getCounterpartyCity() == null : this.getCounterpartyCity().equals(other.getCounterpartyCity()))
            && (this.getCounterpartyIndustry() == null ? other.getCounterpartyIndustry() == null : this.getCounterpartyIndustry().equals(other.getCounterpartyIndustry()))
            && (this.getCreditLimit() == null ? other.getCreditLimit() == null : this.getCreditLimit().equals(other.getCreditLimit()))
            && (this.getCreditBalance() == null ? other.getCreditBalance() == null : this.getCreditBalance().equals(other.getCreditBalance()))
            && (this.getCounterpartyLimit() == null ? other.getCounterpartyLimit() == null : this.getCounterpartyLimit().equals(other.getCounterpartyLimit()))
            && (this.getCounterpartyBalance() == null ? other.getCounterpartyBalance() == null : this.getCounterpartyBalance().equals(other.getCounterpartyBalance()))
            && (this.getInvoiceAmount() == null ? other.getInvoiceAmount() == null : this.getInvoiceAmount().equals(other.getInvoiceAmount()))
            && (this.getInvoiceBalance() == null ? other.getInvoiceBalance() == null : this.getInvoiceBalance().equals(other.getInvoiceBalance()))
            && (this.getFinanceAmount() == null ? other.getFinanceAmount() == null : this.getFinanceAmount().equals(other.getFinanceAmount()))
            && (this.getFinanceBalance() == null ? other.getFinanceBalance() == null : this.getFinanceBalance().equals(other.getFinanceBalance()))
            && (this.getLoanDate() == null ? other.getLoanDate() == null : this.getLoanDate().equals(other.getLoanDate()))
            && (this.getDueDate() == null ? other.getDueDate() == null : this.getDueDate().equals(other.getDueDate()))
            && (this.getRepaymentMethod() == null ? other.getRepaymentMethod() == null : this.getRepaymentMethod().equals(other.getRepaymentMethod()))
            && (this.getInterestRateUnit() == null ? other.getInterestRateUnit() == null : this.getInterestRateUnit().equals(other.getInterestRateUnit()))
            && (this.getInterestRate() == null ? other.getInterestRate() == null : this.getInterestRate().equals(other.getInterestRate()))
            && (this.getLoanPeriod() == null ? other.getLoanPeriod() == null : this.getLoanPeriod().equals(other.getLoanPeriod()))
            && (this.getSpecialProgramId() == null ? other.getSpecialProgramId() == null : this.getSpecialProgramId().equals(other.getSpecialProgramId()))
            && (this.getFactorId() == null ? other.getFactorId() == null : this.getFactorId().equals(other.getFactorId()))
            && (this.getOperateCompanyName() == null ? other.getOperateCompanyName() == null : this.getOperateCompanyName().equals(other.getOperateCompanyName()))
            && (this.getOperateUsername() == null ? other.getOperateUsername() == null : this.getOperateUsername().equals(other.getOperateUsername()))
            && (this.getOperateType() == null ? other.getOperateType() == null : this.getOperateType().equals(other.getOperateType()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLoanInfoId() == null) ? 0 : getLoanInfoId().hashCode());
        result = prime * result + ((getBusinessContractNo() == null) ? 0 : getBusinessContractNo().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getCustomerEconomicCategory() == null) ? 0 : getCustomerEconomicCategory().hashCode());
        result = prime * result + ((getCounterparty() == null) ? 0 : getCounterparty().hashCode());
        result = prime * result + ((getCounterpartyEconomicCategory() == null) ? 0 : getCounterpartyEconomicCategory().hashCode());
        result = prime * result + ((getCustomerCity() == null) ? 0 : getCustomerCity().hashCode());
        result = prime * result + ((getCustomerIndustry() == null) ? 0 : getCustomerIndustry().hashCode());
        result = prime * result + ((getCounterpartyCity() == null) ? 0 : getCounterpartyCity().hashCode());
        result = prime * result + ((getCounterpartyIndustry() == null) ? 0 : getCounterpartyIndustry().hashCode());
        result = prime * result + ((getCreditLimit() == null) ? 0 : getCreditLimit().hashCode());
        result = prime * result + ((getCreditBalance() == null) ? 0 : getCreditBalance().hashCode());
        result = prime * result + ((getCounterpartyLimit() == null) ? 0 : getCounterpartyLimit().hashCode());
        result = prime * result + ((getCounterpartyBalance() == null) ? 0 : getCounterpartyBalance().hashCode());
        result = prime * result + ((getInvoiceAmount() == null) ? 0 : getInvoiceAmount().hashCode());
        result = prime * result + ((getInvoiceBalance() == null) ? 0 : getInvoiceBalance().hashCode());
        result = prime * result + ((getFinanceAmount() == null) ? 0 : getFinanceAmount().hashCode());
        result = prime * result + ((getFinanceBalance() == null) ? 0 : getFinanceBalance().hashCode());
        result = prime * result + ((getLoanDate() == null) ? 0 : getLoanDate().hashCode());
        result = prime * result + ((getDueDate() == null) ? 0 : getDueDate().hashCode());
        result = prime * result + ((getRepaymentMethod() == null) ? 0 : getRepaymentMethod().hashCode());
        result = prime * result + ((getInterestRateUnit() == null) ? 0 : getInterestRateUnit().hashCode());
        result = prime * result + ((getInterestRate() == null) ? 0 : getInterestRate().hashCode());
        result = prime * result + ((getLoanPeriod() == null) ? 0 : getLoanPeriod().hashCode());
        result = prime * result + ((getSpecialProgramId() == null) ? 0 : getSpecialProgramId().hashCode());
        result = prime * result + ((getFactorId() == null) ? 0 : getFactorId().hashCode());
        result = prime * result + ((getOperateCompanyName() == null) ? 0 : getOperateCompanyName().hashCode());
        result = prime * result + ((getOperateUsername() == null) ? 0 : getOperateUsername().hashCode());
        result = prime * result + ((getOperateType() == null) ? 0 : getOperateType().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

	public String getSpecialProgramName() {
		return specialProgramName;
	}

	public void setSpecialProgramName(String specialProgramName) {
		this.specialProgramName = specialProgramName;
	}
}