/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UnderlyingAsset implements Serializable {
    /**
     *放款编号
     */
    private String id;

    /**
     *业务合同号
     */
    private String businessContractNo;

    /**
     *基础资产关联的专项计划编号
     */
    private String specialProgramId;

    /**
     *资产池状态，未入池，备选池，入池
     */
    private String assetPoolStatus;

    /**
     *基础资产来源，保理类型来源，非保理类型来源
     */
    private String assetSource;

    /**
     *创建基础资产的客户ID
     */
    private String factorId;

    /**
     *借款人ID，保理资产才有值
     */
    private String customerId;

    /**
     *借款人名称，即放款中的融资客户公司名称
     */
    private String customerName;

    /**
     *借款人经济类型类型，客户输入的文本
     */
    private String customerEconomicCategory;

    /**
     *借款人所在地区，客户输入的文本
     */
    private String customerCity;

    /**
     *借款人所属行业，客户输入的文本
     */
    private String customerIndustry;

    /**
     *借款人评级，客户输入的文本
     */
    private String customerRating;

    /**
     *交易对手ID，只有保理资产才有值
     */
    private String counterpartyId;

    /**
     *交易对手名称
     */
    private String counterparty;

    /**
     *交易对手经济类型，客户输入的文本
     */
    private String counterpartyEconomicCategory;

    /**
     *交易对手所在地区，客户输入的文本
     */
    private String counterpartyCity;

    /**
     *交易对手所属行业，客户输入的文本
     */
    private String counterpartyIndustry;

    /**
     *交易对手评级，客户输入的文本
     */
    private String counterpartyRating;

    /**
     *担保人信息，客户输入的文本
     */
    private String guaranteeInfo;

    /**
     *担保人类型，客户输入的文本
     */
    private String guaranteeType;

    /**
     *担保企业信息，客户输入的文本
     */
    private String guaranteeCompanyInfo;

    /**
     *担保企业类型，客户输入的文本
     */
    private String guaranteeCompanyType;

    /**
     *担保品编号，客户输入的文本
     */
    private String guaranteeGoodsNo;

    /**
     *授信额度，如果是保理放款，则实时从额度模块读取
     */
    private Long creditLimit;

    /**
     *授信额度可用余额，如果是保理放款，则实时从额度模块读取
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
     *应收账款金额，保理放款则实时计算
     */
    private Long invoiceAmount;

    /**
     *应收账款余额，保理放款时，为未到期的应收账款金额
     */
    private Long invoiceBalance;

    /**
     *融资金额，保理放款实时取
     */
    private Long financeAmount;

    /**
     *融资余额，保理放款实时取
     */
    private Long financeBalance;

    /**
     *放款日，保理放款实时冗余到此
     */
    private String loanDate;

    /**
     *到期日，保理放款实时冗余到此
     */
    private String dueDate;

    /**
     *还本付息方式，保理放款实时取，非保理资产为业务手动录入的文本
     */
    private String repaymentMethod;

    /**
     *利率单位，非保理资产可为空值
     */
    private String interestRateUnit;

    /**
     *利率
     */
    private BigDecimal interestRate;

    /**
     *放款期限，保理放款实时取
     */
    private String loanPeriod;

    /**
     *五级分类
     */
    private String fiveLevelCategory;

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
     *展期天数，保理放款目前不存在，取0
     */
    private Integer extensionDays;

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
     *放款编号
     */
    public String getId() {
        return id;
    }

    /**
     *放款编号
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
     *资产池状态，未入池，备选池，入池
     */
    public String getAssetPoolStatus() {
        return assetPoolStatus;
    }

    /**
     *资产池状态，未入池，备选池，入池
     */
    public void setAssetPoolStatus(String assetPoolStatus) {
        this.assetPoolStatus = assetPoolStatus == null ? null : assetPoolStatus.trim();
    }

    /**
     *基础资产来源，保理类型来源，非保理类型来源
     */
    public String getAssetSource() {
        return assetSource;
    }

    /**
     *基础资产来源，保理类型来源，非保理类型来源
     */
    public void setAssetSource(String assetSource) {
        this.assetSource = assetSource == null ? null : assetSource.trim();
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
     *借款人ID，保理资产才有值
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *借款人ID，保理资产才有值
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     *借款人名称，即放款中的融资客户公司名称
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *借款人名称，即放款中的融资客户公司名称
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    /**
     *借款人经济类型类型，客户输入的文本
     */
    public String getCustomerEconomicCategory() {
        return customerEconomicCategory;
    }

    /**
     *借款人经济类型类型，客户输入的文本
     */
    public void setCustomerEconomicCategory(String customerEconomicCategory) {
        this.customerEconomicCategory = customerEconomicCategory == null ? null : customerEconomicCategory.trim();
    }

    /**
     *借款人所在地区，客户输入的文本
     */
    public String getCustomerCity() {
        return customerCity;
    }

    /**
     *借款人所在地区，客户输入的文本
     */
    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity == null ? null : customerCity.trim();
    }

    /**
     *借款人所属行业，客户输入的文本
     */
    public String getCustomerIndustry() {
        return customerIndustry;
    }

    /**
     *借款人所属行业，客户输入的文本
     */
    public void setCustomerIndustry(String customerIndustry) {
        this.customerIndustry = customerIndustry == null ? null : customerIndustry.trim();
    }

    /**
     *借款人评级，客户输入的文本
     */
    public String getCustomerRating() {
        return customerRating;
    }

    /**
     *借款人评级，客户输入的文本
     */
    public void setCustomerRating(String customerRating) {
        this.customerRating = customerRating == null ? null : customerRating.trim();
    }

    /**
     *交易对手ID，只有保理资产才有值
     */
    public String getCounterpartyId() {
        return counterpartyId;
    }

    /**
     *交易对手ID，只有保理资产才有值
     */
    public void setCounterpartyId(String counterpartyId) {
        this.counterpartyId = counterpartyId == null ? null : counterpartyId.trim();
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
     *交易对手经济类型，客户输入的文本
     */
    public String getCounterpartyEconomicCategory() {
        return counterpartyEconomicCategory;
    }

    /**
     *交易对手经济类型，客户输入的文本
     */
    public void setCounterpartyEconomicCategory(String counterpartyEconomicCategory) {
        this.counterpartyEconomicCategory = counterpartyEconomicCategory == null ? null : counterpartyEconomicCategory.trim();
    }

    /**
     *交易对手所在地区，客户输入的文本
     */
    public String getCounterpartyCity() {
        return counterpartyCity;
    }

    /**
     *交易对手所在地区，客户输入的文本
     */
    public void setCounterpartyCity(String counterpartyCity) {
        this.counterpartyCity = counterpartyCity == null ? null : counterpartyCity.trim();
    }

    /**
     *交易对手所属行业，客户输入的文本
     */
    public String getCounterpartyIndustry() {
        return counterpartyIndustry;
    }

    /**
     *交易对手所属行业，客户输入的文本
     */
    public void setCounterpartyIndustry(String counterpartyIndustry) {
        this.counterpartyIndustry = counterpartyIndustry == null ? null : counterpartyIndustry.trim();
    }

    /**
     *交易对手评级，客户输入的文本
     */
    public String getCounterpartyRating() {
        return counterpartyRating;
    }

    /**
     *交易对手评级，客户输入的文本
     */
    public void setCounterpartyRating(String counterpartyRating) {
        this.counterpartyRating = counterpartyRating == null ? null : counterpartyRating.trim();
    }

    /**
     *担保人信息，客户输入的文本
     */
    public String getGuaranteeInfo() {
        return guaranteeInfo;
    }

    /**
     *担保人信息，客户输入的文本
     */
    public void setGuaranteeInfo(String guaranteeInfo) {
        this.guaranteeInfo = guaranteeInfo == null ? null : guaranteeInfo.trim();
    }

    /**
     *担保人类型，客户输入的文本
     */
    public String getGuaranteeType() {
        return guaranteeType;
    }

    /**
     *担保人类型，客户输入的文本
     */
    public void setGuaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType == null ? null : guaranteeType.trim();
    }

    /**
     *担保企业信息，客户输入的文本
     */
    public String getGuaranteeCompanyInfo() {
        return guaranteeCompanyInfo;
    }

    /**
     *担保企业信息，客户输入的文本
     */
    public void setGuaranteeCompanyInfo(String guaranteeCompanyInfo) {
        this.guaranteeCompanyInfo = guaranteeCompanyInfo == null ? null : guaranteeCompanyInfo.trim();
    }

    /**
     *担保企业类型，客户输入的文本
     */
    public String getGuaranteeCompanyType() {
        return guaranteeCompanyType;
    }

    /**
     *担保企业类型，客户输入的文本
     */
    public void setGuaranteeCompanyType(String guaranteeCompanyType) {
        this.guaranteeCompanyType = guaranteeCompanyType == null ? null : guaranteeCompanyType.trim();
    }

    /**
     *担保品编号，客户输入的文本
     */
    public String getGuaranteeGoodsNo() {
        return guaranteeGoodsNo;
    }

    /**
     *担保品编号，客户输入的文本
     */
    public void setGuaranteeGoodsNo(String guaranteeGoodsNo) {
        this.guaranteeGoodsNo = guaranteeGoodsNo == null ? null : guaranteeGoodsNo.trim();
    }

    /**
     *授信额度，如果是保理放款，则实时从额度模块读取
     */
    public Long getCreditLimit() {
        return creditLimit;
    }

    /**
     *授信额度，如果是保理放款，则实时从额度模块读取
     */
    public void setCreditLimit(Long creditLimit) {
        this.creditLimit = creditLimit;
    }

    /**
     *授信额度可用余额，如果是保理放款，则实时从额度模块读取
     */
    public Long getCreditBalance() {
        return creditBalance;
    }

    /**
     *授信额度可用余额，如果是保理放款，则实时从额度模块读取
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
     *应收账款金额，保理放款则实时计算
     */
    public Long getInvoiceAmount() {
        return invoiceAmount;
    }

    /**
     *应收账款金额，保理放款则实时计算
     */
    public void setInvoiceAmount(Long invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    /**
     *应收账款余额，保理放款时，为未到期的应收账款金额
     */
    public Long getInvoiceBalance() {
        return invoiceBalance;
    }

    /**
     *应收账款余额，保理放款时，为未到期的应收账款金额
     */
    public void setInvoiceBalance(Long invoiceBalance) {
        this.invoiceBalance = invoiceBalance;
    }

    /**
     *融资金额，保理放款实时取
     */
    public Long getFinanceAmount() {
        return financeAmount;
    }

    /**
     *融资金额，保理放款实时取
     */
    public void setFinanceAmount(Long financeAmount) {
        this.financeAmount = financeAmount;
    }

    /**
     *融资余额，保理放款实时取
     */
    public Long getFinanceBalance() {
        return financeBalance;
    }

    /**
     *融资余额，保理放款实时取
     */
    public void setFinanceBalance(Long financeBalance) {
        this.financeBalance = financeBalance;
    }

    /**
     *放款日，保理放款实时冗余到此
     */
    public String getLoanDate() {
        return loanDate;
    }

    /**
     *放款日，保理放款实时冗余到此
     */
    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate == null ? null : loanDate.trim();
    }

    /**
     *到期日，保理放款实时冗余到此
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     *到期日，保理放款实时冗余到此
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate == null ? null : dueDate.trim();
    }

    /**
     *还本付息方式，保理放款实时取，非保理资产为业务手动录入的文本
     */
    public String getRepaymentMethod() {
        return repaymentMethod;
    }

    /**
     *还本付息方式，保理放款实时取，非保理资产为业务手动录入的文本
     */
    public void setRepaymentMethod(String repaymentMethod) {
        this.repaymentMethod = repaymentMethod == null ? null : repaymentMethod.trim();
    }

    /**
     *利率单位，非保理资产可为空值
     */
    public String getInterestRateUnit() {
        return interestRateUnit;
    }

    /**
     *利率单位，非保理资产可为空值
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
     *放款期限，保理放款实时取
     */
    public String getLoanPeriod() {
        return loanPeriod;
    }

    /**
     *放款期限，保理放款实时取
     */
    public void setLoanPeriod(String loanPeriod) {
        this.loanPeriod = loanPeriod == null ? null : loanPeriod.trim();
    }

    /**
     *五级分类
     */
    public String getFiveLevelCategory() {
        return fiveLevelCategory;
    }

    /**
     *五级分类
     */
    public void setFiveLevelCategory(String fiveLevelCategory) {
        this.fiveLevelCategory = fiveLevelCategory == null ? null : fiveLevelCategory.trim();
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
     *展期天数，保理放款目前不存在，取0
     */
    public Integer getExtensionDays() {
        return extensionDays;
    }

    /**
     *展期天数，保理放款目前不存在，取0
     */
    public void setExtensionDays(Integer extensionDays) {
        this.extensionDays = extensionDays;
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
        UnderlyingAsset other = (UnderlyingAsset) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBusinessContractNo() == null ? other.getBusinessContractNo() == null : this.getBusinessContractNo().equals(other.getBusinessContractNo()))
            && (this.getSpecialProgramId() == null ? other.getSpecialProgramId() == null : this.getSpecialProgramId().equals(other.getSpecialProgramId()))
            && (this.getAssetPoolStatus() == null ? other.getAssetPoolStatus() == null : this.getAssetPoolStatus().equals(other.getAssetPoolStatus()))
            && (this.getAssetSource() == null ? other.getAssetSource() == null : this.getAssetSource().equals(other.getAssetSource()))
            && (this.getFactorId() == null ? other.getFactorId() == null : this.getFactorId().equals(other.getFactorId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getCustomerEconomicCategory() == null ? other.getCustomerEconomicCategory() == null : this.getCustomerEconomicCategory().equals(other.getCustomerEconomicCategory()))
            && (this.getCustomerCity() == null ? other.getCustomerCity() == null : this.getCustomerCity().equals(other.getCustomerCity()))
            && (this.getCustomerIndustry() == null ? other.getCustomerIndustry() == null : this.getCustomerIndustry().equals(other.getCustomerIndustry()))
            && (this.getCustomerRating() == null ? other.getCustomerRating() == null : this.getCustomerRating().equals(other.getCustomerRating()))
            && (this.getCounterpartyId() == null ? other.getCounterpartyId() == null : this.getCounterpartyId().equals(other.getCounterpartyId()))
            && (this.getCounterparty() == null ? other.getCounterparty() == null : this.getCounterparty().equals(other.getCounterparty()))
            && (this.getCounterpartyEconomicCategory() == null ? other.getCounterpartyEconomicCategory() == null : this.getCounterpartyEconomicCategory().equals(other.getCounterpartyEconomicCategory()))
            && (this.getCounterpartyCity() == null ? other.getCounterpartyCity() == null : this.getCounterpartyCity().equals(other.getCounterpartyCity()))
            && (this.getCounterpartyIndustry() == null ? other.getCounterpartyIndustry() == null : this.getCounterpartyIndustry().equals(other.getCounterpartyIndustry()))
            && (this.getCounterpartyRating() == null ? other.getCounterpartyRating() == null : this.getCounterpartyRating().equals(other.getCounterpartyRating()))
            && (this.getGuaranteeInfo() == null ? other.getGuaranteeInfo() == null : this.getGuaranteeInfo().equals(other.getGuaranteeInfo()))
            && (this.getGuaranteeType() == null ? other.getGuaranteeType() == null : this.getGuaranteeType().equals(other.getGuaranteeType()))
            && (this.getGuaranteeCompanyInfo() == null ? other.getGuaranteeCompanyInfo() == null : this.getGuaranteeCompanyInfo().equals(other.getGuaranteeCompanyInfo()))
            && (this.getGuaranteeCompanyType() == null ? other.getGuaranteeCompanyType() == null : this.getGuaranteeCompanyType().equals(other.getGuaranteeCompanyType()))
            && (this.getGuaranteeGoodsNo() == null ? other.getGuaranteeGoodsNo() == null : this.getGuaranteeGoodsNo().equals(other.getGuaranteeGoodsNo()))
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
            && (this.getFiveLevelCategory() == null ? other.getFiveLevelCategory() == null : this.getFiveLevelCategory().equals(other.getFiveLevelCategory()))
            && (this.getSettleStatus() == null ? other.getSettleStatus() == null : this.getSettleStatus().equals(other.getSettleStatus()))
            && (this.getForwardDays() == null ? other.getForwardDays() == null : this.getForwardDays().equals(other.getForwardDays()))
            && (this.getOverdueDays() == null ? other.getOverdueDays() == null : this.getOverdueDays().equals(other.getOverdueDays()))
            && (this.getExtensionDays() == null ? other.getExtensionDays() == null : this.getExtensionDays().equals(other.getExtensionDays()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBusinessContractNo() == null) ? 0 : getBusinessContractNo().hashCode());
        result = prime * result + ((getSpecialProgramId() == null) ? 0 : getSpecialProgramId().hashCode());
        result = prime * result + ((getAssetPoolStatus() == null) ? 0 : getAssetPoolStatus().hashCode());
        result = prime * result + ((getAssetSource() == null) ? 0 : getAssetSource().hashCode());
        result = prime * result + ((getFactorId() == null) ? 0 : getFactorId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getCustomerEconomicCategory() == null) ? 0 : getCustomerEconomicCategory().hashCode());
        result = prime * result + ((getCustomerCity() == null) ? 0 : getCustomerCity().hashCode());
        result = prime * result + ((getCustomerIndustry() == null) ? 0 : getCustomerIndustry().hashCode());
        result = prime * result + ((getCustomerRating() == null) ? 0 : getCustomerRating().hashCode());
        result = prime * result + ((getCounterpartyId() == null) ? 0 : getCounterpartyId().hashCode());
        result = prime * result + ((getCounterparty() == null) ? 0 : getCounterparty().hashCode());
        result = prime * result + ((getCounterpartyEconomicCategory() == null) ? 0 : getCounterpartyEconomicCategory().hashCode());
        result = prime * result + ((getCounterpartyCity() == null) ? 0 : getCounterpartyCity().hashCode());
        result = prime * result + ((getCounterpartyIndustry() == null) ? 0 : getCounterpartyIndustry().hashCode());
        result = prime * result + ((getCounterpartyRating() == null) ? 0 : getCounterpartyRating().hashCode());
        result = prime * result + ((getGuaranteeInfo() == null) ? 0 : getGuaranteeInfo().hashCode());
        result = prime * result + ((getGuaranteeType() == null) ? 0 : getGuaranteeType().hashCode());
        result = prime * result + ((getGuaranteeCompanyInfo() == null) ? 0 : getGuaranteeCompanyInfo().hashCode());
        result = prime * result + ((getGuaranteeCompanyType() == null) ? 0 : getGuaranteeCompanyType().hashCode());
        result = prime * result + ((getGuaranteeGoodsNo() == null) ? 0 : getGuaranteeGoodsNo().hashCode());
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
        result = prime * result + ((getFiveLevelCategory() == null) ? 0 : getFiveLevelCategory().hashCode());
        result = prime * result + ((getSettleStatus() == null) ? 0 : getSettleStatus().hashCode());
        result = prime * result + ((getForwardDays() == null) ? 0 : getForwardDays().hashCode());
        result = prime * result + ((getOverdueDays() == null) ? 0 : getOverdueDays().hashCode());
        result = prime * result + ((getExtensionDays() == null) ? 0 : getExtensionDays().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}