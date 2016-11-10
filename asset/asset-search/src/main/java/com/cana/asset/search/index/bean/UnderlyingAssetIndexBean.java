package com.cana.asset.search.index.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;
import com.travelzen.framework.lucene.index.bean.AbstractIndexBean;

/**
 * @author hu
 *
 */
public class UnderlyingAssetIndexBean extends AbstractIndexBean{

	/**
     *放款编号
     */
	public String loanId;

    /**
     *业务合同号
     */
	public String businessContractNo;

    /**
     * 业务产品
     */
	public String businessProduct;
    
    /**
     *基础资产关联的专项计划编号
     */
	public String specialProgramId;
	
	/**
	 * 专项计划名称
	 */
	public String specialProgramName;
//
//    /**
//     *资产池状态，未入池，备选池，入池
//     */
//    private String assetPoolStatus;

//    /**
//     *基础资产类型，保理类型，非保理类型
//     */
//    private String assetType;

    public String getSpecialProgramName() {
		return specialProgramName;
	}

	public void setSpecialProgramName(String specialProgramName) {
		this.specialProgramName = specialProgramName;
	}

	/**
     *创建基础资产的客户ID
     */
	public String factorId;

//    /**
//     *借款人ID，保理资产才有值
//     */
//    private String customerId;

    /**
     *借款人名称，即放款中的融资客户公司名称
     */
	public String customerName;

    /**
     *借款人经济类型类型，@see EconomicCategoryEnum
     */
	public String customerEconomicCategory;

    /**
     *借款人所在地区，@see CustomerCityTypeEnum
     */
	public String customerCity;

    /**
     *借款人所属行业，@see IndustryTypeEnum
     */
	public String customerIndustry;

//    /**
//     *借款人评级，客户输入的文本
//     */
//    private String customerRating;
//
//    /**
//     *交易对手ID，只有保理资产才有值
//     */
//    private String counterpartyId;

    /**
     *交易对手名称
     */
	public String counterparty;

    /**
     *交易对手经济类型，@see EconomicCategoryEnum
     */
	public String counterpartyEconomicCategory;

    /**
     *交易对手所在地区，@see CustomerCityTypeEnum
     */
	public String counterpartyCity;

    /**
     *交易对手所属行业，@see IndustryTypeEnum
     */
	public String counterpartyIndustry;

    /**
     *交易对手评级，客户输入的文本
     */
	public String counterpartyRating;

    /**
     *担保人信息，客户输入的文本
     */
	public String guaranteeInfo;

//    /**
//     *担保人类型，客户输入的文本
//     */
//    private String guaranteeType;

    /**
     *担保企业信息，客户输入的文本
     */
	public String guaranteeCompanyInfo;

//    /**
//     *担保企业类型，客户输入的文本
//     */
//    private String guaranteeCompanyType;

    /**
     *担保品编号，客户输入的文本
     */
	public String guaranteeGoodsNo;

    /**
     *授信额度，如果是保理放款，则实时从额度模块读取
     */
	public Long creditLimit;

    /**
     *授信额度可用余额，如果是保理放款，则实时从额度模块读取
     */
	public Long creditBalance;

    /**
     *交易对手非承保额度，保理放款不存在此值
     */
	public Long counterpartyLimit;

    /**
     *交易对手非承保余额，保理放款不存在此值
     */
	public Long counterpartyBalance;

    /**
     *应收账款金额，保理放款则实时计算
     */
	public Long invoiceAmount;

    /**
     *应收账款余额，保理放款时，为未到期的应收账款金额
     */
	public Long invoiceBalance;

    /**
     *融资金额，保理放款实时取t = 0l
     */
	public Long financeAmount;

    /**
     *融资余额，保理放款实时取
     */
	public Long financeBalance;

    /**
     *放款日，保理放款实时冗余到此
     */
	public String loanDate;

    /**
     * 结息日
     */
	public String settlementDate;
    /**
     *到期日，保理放款实时冗余到此
     */
	public String dueDate;

    /**
     *还本付息方式，保理放款实时取
     */
	public String repaymentMethod;

//    /**
//     *利率单位
//     */
//    private String interestRateUnit;

    /**
     *利率(年化)
     */
	public Double interestRate;

    /**
     *放款期限，保理放款实时取
     */
	public String loanPeriod;

    /**
     * 应收利息
     */
	public Long accountInterest;
    
    /**
     * 应还总金额
     */
	public Long accountTotalAmount;
    
    /**
     * 逾期管理费率 
     */
	public Double penaltyRate;
	
	public Date updateTime;
    
    public static enum UnderlyingAssetFieldEnum{
    	loanId, businessContractNo, businessProduct, specialProgramId, specialProgramName, factorId, customerName, customerEconomicCategory,
    	customerCity, customerIndustry, counterparty, counterpartyEconomicCategory, counterpartyCity, counterpartyIndustry,
    	counterpartyRating, guaranteeInfo, guaranteeCompanyInfo, guaranteeGoodsNo, creditLimit, creditBalance, counterpartyLimit,
    	counterpartyBalance, invoiceAmount, invoiceBalance, financeAmount, financeBalance, loanDate, settlementDate, dueDate,
    	repaymentMethod, interestRate, loanPeriod, accountInterest, accountTotalAmount, penaltyRate, updateTime;
    	private UnderlyingAssetFieldEnum(){
			
		}
	}
    
	@Override
	public List<String> getAnalyzedField() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getIndexField() {
		List<String> fields = Lists.newArrayList();
		fields.add(UnderlyingAssetFieldEnum.loanId.name());
		fields.add(UnderlyingAssetFieldEnum.factorId.name());
		fields.add(UnderlyingAssetFieldEnum.businessContractNo.name());
		fields.add(UnderlyingAssetFieldEnum.businessProduct.name());
		fields.add(UnderlyingAssetFieldEnum.customerName.name());
		fields.add(UnderlyingAssetFieldEnum.customerEconomicCategory.name());
		fields.add(UnderlyingAssetFieldEnum.counterparty.name());
		fields.add(UnderlyingAssetFieldEnum.counterpartyEconomicCategory.name());
		fields.add(UnderlyingAssetFieldEnum.customerCity.name());
		fields.add(UnderlyingAssetFieldEnum.customerIndustry.name());
		fields.add(UnderlyingAssetFieldEnum.counterpartyCity.name());
		fields.add(UnderlyingAssetFieldEnum.counterpartyIndustry.name());
		fields.add(UnderlyingAssetFieldEnum.counterpartyRating.name());
		fields.add(UnderlyingAssetFieldEnum.loanDate.name());
		fields.add(UnderlyingAssetFieldEnum.settlementDate.name());
		fields.add(UnderlyingAssetFieldEnum.dueDate.name());
		fields.add(UnderlyingAssetFieldEnum.repaymentMethod.name());
		fields.add(UnderlyingAssetFieldEnum.guaranteeInfo.name());
		fields.add(UnderlyingAssetFieldEnum.guaranteeCompanyInfo.name());
		fields.add(UnderlyingAssetFieldEnum.guaranteeGoodsNo.name());
		fields.add(UnderlyingAssetFieldEnum.invoiceAmount.name());
		fields.add(UnderlyingAssetFieldEnum.invoiceBalance.name());
		fields.add(UnderlyingAssetFieldEnum.financeAmount.name());
		fields.add(UnderlyingAssetFieldEnum.financeBalance.name());
		fields.add(UnderlyingAssetFieldEnum.interestRate.name());
		fields.add(UnderlyingAssetFieldEnum.accountInterest.name());
		fields.add(UnderlyingAssetFieldEnum.penaltyRate.name());
		fields.add(UnderlyingAssetFieldEnum.accountTotalAmount.name());
		fields.add(UnderlyingAssetFieldEnum.creditLimit.name());
		fields.add(UnderlyingAssetFieldEnum.creditBalance.name());
		fields.add(UnderlyingAssetFieldEnum.counterpartyLimit.name());
		fields.add(UnderlyingAssetFieldEnum.counterpartyBalance.name());
		fields.add(UnderlyingAssetFieldEnum.specialProgramId.name());
		fields.add(UnderlyingAssetFieldEnum.updateTime.name());
		return fields;
	}

	@Override
	public List<String> getStoreField() {
		List<String> fields = Lists.newArrayList();
		fields.add(UnderlyingAssetFieldEnum.loanId.name());
		fields.add(UnderlyingAssetFieldEnum.businessContractNo.name());
		fields.add(UnderlyingAssetFieldEnum.customerName.name());
		fields.add(UnderlyingAssetFieldEnum.customerEconomicCategory.name());
		fields.add(UnderlyingAssetFieldEnum.counterparty.name());
		fields.add(UnderlyingAssetFieldEnum.counterpartyEconomicCategory.name());
		fields.add(UnderlyingAssetFieldEnum.customerCity.name());
		fields.add(UnderlyingAssetFieldEnum.customerIndustry.name());
		fields.add(UnderlyingAssetFieldEnum.counterpartyCity.name());
		fields.add(UnderlyingAssetFieldEnum.counterpartyIndustry.name());
		fields.add(UnderlyingAssetFieldEnum.loanDate.name());
		fields.add(UnderlyingAssetFieldEnum.dueDate.name());
		fields.add(UnderlyingAssetFieldEnum.repaymentMethod.name());
		fields.add(UnderlyingAssetFieldEnum.invoiceAmount.name());
		fields.add(UnderlyingAssetFieldEnum.invoiceBalance.name());
		fields.add(UnderlyingAssetFieldEnum.financeAmount.name());
		fields.add(UnderlyingAssetFieldEnum.financeBalance.name());
		fields.add(UnderlyingAssetFieldEnum.interestRate.name());
		fields.add(UnderlyingAssetFieldEnum.creditLimit.name());
		fields.add(UnderlyingAssetFieldEnum.creditBalance.name());
		fields.add(UnderlyingAssetFieldEnum.counterpartyLimit.name());
		fields.add(UnderlyingAssetFieldEnum.counterpartyBalance.name());
		fields.add(UnderlyingAssetFieldEnum.loanPeriod.name());
		fields.add(UnderlyingAssetFieldEnum.specialProgramId.name());
		fields.add(UnderlyingAssetFieldEnum.specialProgramName.name());
		return fields;
	}

	@Override
	public Map<String, Float> getFieldBoost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Pair<String, String>> getHighlighter() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

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

	public String getSpecialProgramId() {
		return specialProgramId;
	}

	public void setSpecialProgramId(String specialProgramId) {
		this.specialProgramId = specialProgramId;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
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

	public Long getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Long creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Long getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(Long creditBalance) {
		this.creditBalance = creditBalance;
	}

	public Long getCounterpartyLimit() {
		return counterpartyLimit;
	}

	public void setCounterpartyLimit(Long counterpartyLimit) {
		this.counterpartyLimit = counterpartyLimit;
	}

	public Long getCounterpartyBalance() {
		return counterpartyBalance;
	}

	public void setCounterpartyBalance(Long counterpartyBalance) {
		this.counterpartyBalance = counterpartyBalance;
	}

	public Long getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(Long invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public Long getInvoiceBalance() {
		return invoiceBalance;
	}

	public void setInvoiceBalance(Long invoiceBalance) {
		this.invoiceBalance = invoiceBalance;
	}

	public Long getFinanceAmount() {
		return financeAmount;
	}

	public void setFinanceAmount(Long financeAmount) {
		this.financeAmount = financeAmount;
	}

	public Long getFinanceBalance() {
		return financeBalance;
	}

	public void setFinanceBalance(Long financeBalance) {
		this.financeBalance = financeBalance;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
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

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public String getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(String loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public Long getAccountInterest() {
		return accountInterest;
	}

	public void setAccountInterest(Long accountInterest) {
		this.accountInterest = accountInterest;
	}

	public Long getAccountTotalAmount() {
		return accountTotalAmount;
	}

	public void setAccountTotalAmount(Long accountTotalAmount) {
		this.accountTotalAmount = accountTotalAmount;
	}

	public Double getPenaltyRate() {
		return penaltyRate;
	}

	public void setPenaltyRate(Double penaltyRate) {
		this.penaltyRate = penaltyRate;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
