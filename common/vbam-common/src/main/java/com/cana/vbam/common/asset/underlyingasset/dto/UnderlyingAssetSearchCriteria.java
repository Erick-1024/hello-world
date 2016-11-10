package com.cana.vbam.common.asset.underlyingasset.dto;

import com.cana.vbam.common.dto.Pagination;

/**
 * @author hu
 *
 */
public class UnderlyingAssetSearchCriteria extends Pagination{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8804577470624941423L;

	private String userId;
	
	private String specialProgramId;
	
	private String businessContractNo;
	
	private String businessProduct;
	
	private String customerName;

    /**
     *借款人经济类型类型，@see EconomicCategoryEnum
     */
    private String customerEconomicCategory;

    /**
     *借款人所在地区，@see CustomerCityTypeEnum
     */
    private String customerCity;

    /**
     *借款人所属行业，@see IndustryTypeEnum
     */
    private String customerIndustry;

    /**
     *交易对手名称
     */
    private String counterparty;

    /**
     *交易对手经济类型，@see EconomicCategoryEnum
     */
    private String counterpartyEconomicCategory;

    /**
     *交易对手所在地区，@see CustomerCityTypeEnum
     */
    private String counterpartyCity;

    /**
     *交易对手所属行业，@see IndustryTypeEnum
     */
    private String counterpartyIndustry;

    /**
     *交易对手评级，客户输入的文本
     */
    private String counterpartyRating;
    
    private String loanStartDate;
    
    private String loanEndDate;
    
    private String settleStartDate;
    
    private String settleEndDate;
    
    private String dueStartDate;
    
    private String dueEndDate;
    
    private String repaymentMethod;
    
    /**
     *担保人信息，客户输入的文本
     */
    private String guaranteeInfo;
    
    /**
     *担保企业信息，客户输入的文本
     */
    private String guaranteeCompanyInfo;
    
    /**
     *担保品编号，客户输入的文本
     */
    private String guaranteeGoodsNo;
    
    private String invoiceStartAmount;
    
    private String invoiceEndAmount;
    
    private String invoiceStartBalance;
    
    private String invoiceEndBalance;
    
    private String financeStartAmount;
    
    private String financeEndAmount;
    
    private String financeStartBalance;
    
    private String financeEndBalance;
    
    private String annualInterestRateStart;
    
    private String annualInterestRateEnd;
    
    private String accountInterestStart;
    
    private String accountInterestEnd;
    
    private String penaltyRateStart;
    
    private String penaltyRateEnd;
    
    private String accountTotalAmountStart;
    
    private String accountTotalAmountEnd;
    
    private String creditLimitStart;
    
    private String creditLimitEnd;
    
    private String creditBalanceStart;
    
    private String creditBalanceEnd;
    
    private String counterpartyLimitStart;
    
    private String counterpartyLimitEnd;
    
    private String counterpartyBalanceStart;
    
    private String counterpartyBalanceEnd;

	public String getBusinessContractNo() {
		return businessContractNo;
	}

	public void setBusinessContractNo(String businessContractNo) {
		this.businessContractNo = businessContractNo;
	}

	public String getBusinessProduct() {
		return businessProduct;
	}

	public void setBusinessProduct(String businessProduct) {
		this.businessProduct = businessProduct;
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

	public String getLoanStartDate() {
		return loanStartDate;
	}

	public void setLoanStartDate(String loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	public String getLoanEndDate() {
		return loanEndDate;
	}

	public void setLoanEndDate(String loanEndDate) {
		this.loanEndDate = loanEndDate;
	}

	public String getSettleStartDate() {
		return settleStartDate;
	}

	public void setSettleStartDate(String settleStartDate) {
		this.settleStartDate = settleStartDate;
	}

	public String getSettleEndDate() {
		return settleEndDate;
	}

	public void setSettleEndDate(String settleEndDate) {
		this.settleEndDate = settleEndDate;
	}

	public String getDueStartDate() {
		return dueStartDate;
	}

	public void setDueStartDate(String dueStartDate) {
		this.dueStartDate = dueStartDate;
	}

	public String getDueEndDate() {
		return dueEndDate;
	}

	public void setDueEndDate(String dueEndDate) {
		this.dueEndDate = dueEndDate;
	}

	public String getRepaymentMethod() {
		return repaymentMethod;
	}

	public void setRepaymentMethod(String repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}

	public String getGuaranteeInfo() {
		return guaranteeInfo;
	}

	public void setGuaranteeInfo(String guaranteeInfo) {
		this.guaranteeInfo = guaranteeInfo;
	}

	public String getGuaranteeCompanyInfo() {
		return guaranteeCompanyInfo;
	}

	public void setGuaranteeCompanyInfo(String guaranteeCompanyInfo) {
		this.guaranteeCompanyInfo = guaranteeCompanyInfo;
	}

	public String getGuaranteeGoodsNo() {
		return guaranteeGoodsNo;
	}

	public void setGuaranteeGoodsNo(String guaranteeGoodsNo) {
		this.guaranteeGoodsNo = guaranteeGoodsNo;
	}

	public String getInvoiceStartAmount() {
		return invoiceStartAmount;
	}

	public void setInvoiceStartAmount(String invoiceStartAmount) {
		this.invoiceStartAmount = invoiceStartAmount;
	}

	public String getInvoiceEndAmount() {
		return invoiceEndAmount;
	}

	public void setInvoiceEndAmount(String invoiceEndAmount) {
		this.invoiceEndAmount = invoiceEndAmount;
	}

	public String getInvoiceStartBalance() {
		return invoiceStartBalance;
	}

	public void setInvoiceStartBalance(String invoiceStartBalance) {
		this.invoiceStartBalance = invoiceStartBalance;
	}

	public String getInvoiceEndBalance() {
		return invoiceEndBalance;
	}

	public void setInvoiceEndBalance(String invoiceEndBalance) {
		this.invoiceEndBalance = invoiceEndBalance;
	}

	public String getFinanceStartAmount() {
		return financeStartAmount;
	}

	public void setFinanceStartAmount(String financeStartAmount) {
		this.financeStartAmount = financeStartAmount;
	}

	public String getFinanceEndAmount() {
		return financeEndAmount;
	}

	public void setFinanceEndAmount(String financeEndAmount) {
		this.financeEndAmount = financeEndAmount;
	}

	public String getFinanceStartBalance() {
		return financeStartBalance;
	}

	public void setFinanceStartBalance(String financeStartBalance) {
		this.financeStartBalance = financeStartBalance;
	}

	public String getFinanceEndBalance() {
		return financeEndBalance;
	}

	public void setFinanceEndBalance(String financeEndBalance) {
		this.financeEndBalance = financeEndBalance;
	}

	public String getAnnualInterestRateStart() {
		return annualInterestRateStart;
	}

	public void setAnnualInterestRateStart(String annualInterestRateStart) {
		this.annualInterestRateStart = annualInterestRateStart;
	}

	public String getAnnualInterestRateEnd() {
		return annualInterestRateEnd;
	}

	public void setAnnualInterestRateEnd(String annualInterestRateEnd) {
		this.annualInterestRateEnd = annualInterestRateEnd;
	}

	public String getAccountInterestStart() {
		return accountInterestStart;
	}

	public void setAccountInterestStart(String accountInterestStart) {
		this.accountInterestStart = accountInterestStart;
	}

	public String getAccountInterestEnd() {
		return accountInterestEnd;
	}

	public void setAccountInterestEnd(String accountInterestEnd) {
		this.accountInterestEnd = accountInterestEnd;
	}

	public String getPenaltyRateStart() {
		return penaltyRateStart;
	}

	public void setPenaltyRateStart(String penaltyRateStart) {
		this.penaltyRateStart = penaltyRateStart;
	}

	public String getPenaltyRateEnd() {
		return penaltyRateEnd;
	}

	public void setPenaltyRateEnd(String penaltyRateEnd) {
		this.penaltyRateEnd = penaltyRateEnd;
	}

	public String getAccountTotalAmountStart() {
		return accountTotalAmountStart;
	}

	public void setAccountTotalAmountStart(String accountTotalAmountStart) {
		this.accountTotalAmountStart = accountTotalAmountStart;
	}

	public String getAccountTotalAmountEnd() {
		return accountTotalAmountEnd;
	}

	public void setAccountTotalAmountEnd(String accountTotalAmountEnd) {
		this.accountTotalAmountEnd = accountTotalAmountEnd;
	}

	public String getCreditLimitStart() {
		return creditLimitStart;
	}

	public void setCreditLimitStart(String creditLimitStart) {
		this.creditLimitStart = creditLimitStart;
	}

	public String getCreditLimitEnd() {
		return creditLimitEnd;
	}

	public void setCreditLimitEnd(String creditLimitEnd) {
		this.creditLimitEnd = creditLimitEnd;
	}

	public String getCreditBalanceStart() {
		return creditBalanceStart;
	}

	public void setCreditBalanceStart(String creditBalanceStart) {
		this.creditBalanceStart = creditBalanceStart;
	}

	public String getCreditBalanceEnd() {
		return creditBalanceEnd;
	}

	public void setCreditBalanceEnd(String creditBalanceEnd) {
		this.creditBalanceEnd = creditBalanceEnd;
	}

	public String getCounterpartyLimitStart() {
		return counterpartyLimitStart;
	}

	public void setCounterpartyLimitStart(String counterpartyLimitStart) {
		this.counterpartyLimitStart = counterpartyLimitStart;
	}

	public String getCounterpartyLimitEnd() {
		return counterpartyLimitEnd;
	}

	public void setCounterpartyLimitEnd(String counterpartyLimitEnd) {
		this.counterpartyLimitEnd = counterpartyLimitEnd;
	}

	public String getCounterpartyBalanceStart() {
		return counterpartyBalanceStart;
	}

	public void setCounterpartyBalanceStart(String counterpartyBalanceStart) {
		this.counterpartyBalanceStart = counterpartyBalanceStart;
	}

	public String getCounterpartyBalanceEnd() {
		return counterpartyBalanceEnd;
	}

	public void setCounterpartyBalanceEnd(String counterpartyBalanceEnd) {
		this.counterpartyBalanceEnd = counterpartyBalanceEnd;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSpecialProgramId() {
		return specialProgramId;
	}

	public void setSpecialProgramId(String specialProgramId) {
		this.specialProgramId = specialProgramId;
	}
}
