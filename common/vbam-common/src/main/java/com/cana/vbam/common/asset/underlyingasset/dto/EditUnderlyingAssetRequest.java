package com.cana.vbam.common.asset.underlyingasset.dto;

import java.io.Serializable;

/**
 * 创建或者修改基础资产请求
 * 
 * @author XuMeng
 *
 */
public class EditUnderlyingAssetRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String contractNo; // 业务合同号
	private String underlyingAssetId; // 放款编号
	private String customerName; // 借款人名称
	private String customerEconomicCategory; // 借款人经济类型，@see EconomicCategoryEnum
	private String customerCity; // 借款人所在地区，@see CustomerCityTypeEnum
	private String customerIndustry; // 借款人所属行业，@see IndustryTypeEnum
	private String customerRating; // 借款人评级，客户输入的文本
	private String counterparty; // 交易对手名称
	private String counterpartyEconomicCategory; // 交易对手经济类型，@see EconomicCategoryEnum
	private String counterpartyCity; // 交易对手所在地区，@see CustomerCityTypeEnum
	private String counterpartyIndustry; // 交易对手所属行业，@see IndustryTypeEnum
	private String counterpartyRating; // 交易对手评级，客户输入的文本
	private String guaranteeInfo; // 担保人信息，客户输入的文本
	private String guaranteeType; // 担保人类型，客户输入的文本
	private String guaranteeCompanyInfo; // 担保企业信息，客户输入的文本
	private String guaranteeCompanyType; // 担保企业类型，客户输入的文本
	private String guaranteeGoodsNo; // 担保品编号，客户输入的文本
	private String creditLimit; // 授信额度，单位元
	private String creditBalance; // 授信额度可用余额，单位元，且小于等于授信额度
	private String counterpartyLimit; // 交易对手非承保额度，单位元
	private String counterpartyBalance; // 交易对手非承保余额，单位元
	private String invoiceAmount; // 应收账款金额，保理放款则实时计算
	private String invoiceBalance; // 应收账款余额，保理放款时，为未到期的应收账款金额
	private String financeAmount; // 融资金额，保理放款实时取
	private String financeBalance; // 融资余额，保理放款实时取
	private String loanDate; // 放款日，保理放款实时冗余到此
	private String dueDate; // 到期日，保理放款实时冗余到此
	private String repaymentMethod; // 还本付息方式，保理放款实时取
	private String interestRateUnit; // 利率单位
	private String interestRate; // 利率
	private String loanPeriod; // 放款期限，客户输入文本
	private String fiveLevelCategory; // 五级分类
	private String settleStatus; // 结清状态（已结清、未结清），为空时表示未结清，@see SettleStatus
	private int forwardDays; // 提前还款天数
	private int overdueDays; // 逾期天数
	private int extensionDays; // 展期天数

	private String programId; // 修改后的专项计划编号，可修改未入正式池基础资产的专项计划编号

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getUnderlyingAssetId() {
		return underlyingAssetId;
	}

	public void setUnderlyingAssetId(String underlyingAssetId) {
		this.underlyingAssetId = underlyingAssetId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEconomicCategory() {
		return customerEconomicCategory;
	}

	public void setCustomerEconomicCategory(String customerEconomicCategory) {
		this.customerEconomicCategory = customerEconomicCategory;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerIndustry() {
		return customerIndustry;
	}

	public void setCustomerIndustry(String customerIndustry) {
		this.customerIndustry = customerIndustry;
	}

	public String getCustomerRating() {
		return customerRating;
	}

	public void setCustomerRating(String customerRating) {
		this.customerRating = customerRating;
	}

	public String getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}

	public String getCounterpartyEconomicCategory() {
		return counterpartyEconomicCategory;
	}

	public void setCounterpartyEconomicCategory(String counterpartyEconomicCategory) {
		this.counterpartyEconomicCategory = counterpartyEconomicCategory;
	}

	public String getCounterpartyCity() {
		return counterpartyCity;
	}

	public void setCounterpartyCity(String counterpartyCity) {
		this.counterpartyCity = counterpartyCity;
	}

	public String getCounterpartyIndustry() {
		return counterpartyIndustry;
	}

	public void setCounterpartyIndustry(String counterpartyIndustry) {
		this.counterpartyIndustry = counterpartyIndustry;
	}

	public String getCounterpartyRating() {
		return counterpartyRating;
	}

	public void setCounterpartyRating(String counterpartyRating) {
		this.counterpartyRating = counterpartyRating;
	}

	public String getGuaranteeInfo() {
		return guaranteeInfo;
	}

	public void setGuaranteeInfo(String guaranteeInfo) {
		this.guaranteeInfo = guaranteeInfo;
	}

	public String getGuaranteeType() {
		return guaranteeType;
	}

	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}

	public String getGuaranteeCompanyInfo() {
		return guaranteeCompanyInfo;
	}

	public void setGuaranteeCompanyInfo(String guaranteeCompanyInfo) {
		this.guaranteeCompanyInfo = guaranteeCompanyInfo;
	}

	public String getGuaranteeCompanyType() {
		return guaranteeCompanyType;
	}

	public void setGuaranteeCompanyType(String guaranteeCompanyType) {
		this.guaranteeCompanyType = guaranteeCompanyType;
	}

	public String getGuaranteeGoodsNo() {
		return guaranteeGoodsNo;
	}

	public void setGuaranteeGoodsNo(String guaranteeGoodsNo) {
		this.guaranteeGoodsNo = guaranteeGoodsNo;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(String creditBalance) {
		this.creditBalance = creditBalance;
	}

	public String getCounterpartyLimit() {
		return counterpartyLimit;
	}

	public void setCounterpartyLimit(String counterpartyLimit) {
		this.counterpartyLimit = counterpartyLimit;
	}

	public String getCounterpartyBalance() {
		return counterpartyBalance;
	}

	public void setCounterpartyBalance(String counterpartyBalance) {
		this.counterpartyBalance = counterpartyBalance;
	}

	public String getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getInvoiceBalance() {
		return invoiceBalance;
	}

	public void setInvoiceBalance(String invoiceBalance) {
		this.invoiceBalance = invoiceBalance;
	}

	public String getFinanceAmount() {
		return financeAmount;
	}

	public void setFinanceAmount(String financeAmount) {
		this.financeAmount = financeAmount;
	}

	public String getFinanceBalance() {
		return financeBalance;
	}

	public void setFinanceBalance(String financeBalance) {
		this.financeBalance = financeBalance;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getRepaymentMethod() {
		return repaymentMethod;
	}

	public void setRepaymentMethod(String repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}

	public String getInterestRateUnit() {
		return interestRateUnit;
	}

	public void setInterestRateUnit(String interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(String loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public String getFiveLevelCategory() {
		return fiveLevelCategory;
	}

	public void setFiveLevelCategory(String fiveLevelCategory) {
		this.fiveLevelCategory = fiveLevelCategory;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}

	public int getForwardDays() {
		return forwardDays;
	}

	public void setForwardDays(int forwardDays) {
		this.forwardDays = forwardDays;
	}

	public int getOverdueDays() {
		return overdueDays;
	}

	public void setOverdueDays(int overdueDays) {
		this.overdueDays = overdueDays;
	}

	public int getExtensionDays() {
		return extensionDays;
	}

	public void setExtensionDays(int extensionDays) {
		this.extensionDays = extensionDays;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

}
