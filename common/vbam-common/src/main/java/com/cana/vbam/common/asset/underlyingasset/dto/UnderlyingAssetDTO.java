package com.cana.vbam.common.asset.underlyingasset.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cana.vbam.common.asset.enums.UnderlyingAssetPoolStatus;
import com.cana.vbam.common.asset.enums.UnderlyingAssetSource;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;

public class UnderlyingAssetDTO implements Serializable{

	private static final long serialVersionUID = -912268219831530407L;

	/**
	 * 放款编号
	 */
	private String loanNo;
	
	/**
	 */
	private String businessContractNo;
	
	/**
	 * 借款人名称
	 */
	private String customerName;
	
	/**
	 * 借款人类型
	 */
	private String customerEconomicCategory;
	
	/**
	 * 交易对手名称
	 */
	private String counterparty;
	
	/**
	 * 交易对手类型
	 */
	private String counterpartyEconomicCategory;
	
	/**
	 * 借款人所在地区
	 */
	private String customerCity;
	
	/**
	 * 借款人所属行业
	 */
	private String customerIndustry;
	
	/**
	 * 借款人评级
	 */
	private String customerRating;
	
	/**
	 * 交易对手所在地区
	 */
	private String counterpartyCity;
	
	/**
	 * 交易对手所属行业
	 */
	private String counterpartyIndustry;
	
	/**
	 * 交易对手评级
	 */
	private String counterpartyRating;
	
	/**
	 * 担保人信息
	 */
	private String guaranteeInfo;
	
	/**
	 * 担保人类型
	 */
	private String guaranteeType;
	
	/**
	 * 担保企业信息
	 */
	private String guaranteeCompanyInfo;
	
	/**
	 * 担保企业类型
	 */
	private String guaranteeCompanyType;
	
    /**
     *担保品编号，客户输入的文本
     */
    private String guaranteeGoodsNo;
	
	/**
	 * 授信额度
	 */
	private String creditLimit;

	private long creditLimitNum;
	
	/**
	 * 授信可用余额
	 */
	private String creditBalance;

	private long creditBalanceNum;
	
	/**
	 * 交易对手非承保额度
	 */
	private String counterpartyLimit;

	private long counterpartyLimitNum;
	
	/**
	 * 交易对手非承保余额
	 */
	private String counterpartyBalance;

	private long counterpartyBalanceNum;
	
	/**
	 * 应收账款金额
	 */
	private String invoiceAmount;

	private long invoiceAmountNum;
	
	/**
	 * 应收账款余额
	 */
	private String invoiceBalance;

	private long invoiceBalanceNum;
	
	/**
	 * 融资金额
	 */
	private String financeAmount;

	private long financeAmountNum;
	
	/**
	 * 融资余额
	 */
	private String financeBalance;

	private long financeBalanceNum;
	
	/**
	 * 起息日
	 */
	private String loanDate;
	
	/**
	 * 到期日
	 */
	private String dueDate;
	
	/**
	 * 还款方式
	 */
	private String repaymentMethod;
	
	/**
	 * 利率类型
	 */
	private InterestRateUnit interestRateUnit;
	
	/**
	 * 利率
	 */
	private String interestRate;
	
	/**
     *计息基准天数，360或者365
     */
    private Integer dayCountConvention = 360;

	private BigDecimal interestRateNum;
	
	/**
	 * 期限
	 */
	private String loanPeriod;
	
	/**
     * 放款期限单位
     */
    private String loanPeriodUnit;
	
	/**
	 * 五级分类
	 */
	private String fiveLevelCategory;
	
	/**
	 * 结清状态
	 */
	private String settleStatus;
	
	/**
	 * 提前还款天数
	 */
	private String forwardDays;
	
	/**
	 * 逾期天数
	 */
	private String overdueDays;
	
	/**
	 * 展期天数
	 */
	private String extensionDays;
	
	/**
	 * 所属专项计划编号
	 */
	private String specialProgramId;
	
	/**
	 * 专项计划名称
	 */
	private String specialProgramName;
	
	/**
     * 资产池状态，未入池，备选池，入池
     */
    private UnderlyingAssetPoolStatus assetPoolStatus;
    
    /**
     * 业务产品
     */
    private String businessProduct;
    
    /**
     * 结息日
     */
    private String settleInterestDate;
    
    /**
     * 应收利息
     */
    private long totalInterestAmount;
    
    /**
     * 应还总金额
     */
    private long totalAmount;
    
    /**
     * 逾期管理费率
     */
    private BigDecimal penaltyRate;
    
    /**
     * 基础资产类型
     */
    private UnderlyingAssetSource assetSource;
    
    /**
     * 保理商Id
     */
    private String factorId;
    
    private Date updateTime;
    
    private Date createTime;

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getBusinessContractNo() {
		return businessContractNo;
	}

	public void setBusinessContractNo(String businessContractNo) {
		this.businessContractNo = businessContractNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public void setCustomerEconomicCategory(String customerEconomicCategory) {
		this.customerEconomicCategory = customerEconomicCategory;
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

	public String getCustomerEconomicCategory() {
		return customerEconomicCategory;
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

	public String getSpecialProgramId() {
		return specialProgramId;
	}

	public void setSpecialProgramId(String specialProgramId) {
		this.specialProgramId = specialProgramId;
	}

	public String getCustomerRating() {
		return customerRating;
	}

	public void setCustomerRating(String customerRating) {
		this.customerRating = customerRating;
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

	public InterestRateUnit getInterestRateUnit() {
		return interestRateUnit;
	}

	public void setInterestRateUnit(InterestRateUnit interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}

	public Integer getDayCountConvention() {
		return dayCountConvention;
	}

	public void setDayCountConvention(Integer dayCountConvention) {
		this.dayCountConvention = dayCountConvention;
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

	public String getForwardDays() {
		return forwardDays;
	}

	public void setForwardDays(String forwardDays) {
		this.forwardDays = forwardDays;
	}

	public String getOverdueDays() {
		return overdueDays;
	}

	public void setOverdueDays(String overdueDays) {
		this.overdueDays = overdueDays;
	}

	public String getSpecialProgramName() {
		return specialProgramName;
	}

	public void setSpecialProgramName(String specialProgramName) {
		this.specialProgramName = specialProgramName;
	}

	public UnderlyingAssetPoolStatus getAssetPoolStatus() {
		return assetPoolStatus;
	}

	public void setAssetPoolStatus(UnderlyingAssetPoolStatus assetPoolStatus) {
		this.assetPoolStatus = assetPoolStatus;
	}

	public String getBusinessProduct() {
		return businessProduct;
	}

	public void setBusinessProduct(String businessProduct) {
		this.businessProduct = businessProduct;
	}

	public String getSettleInterestDate() {
		return settleInterestDate;
	}

	public void setSettleInterestDate(String settleInterestDate) {
		this.settleInterestDate = settleInterestDate;
	}

	public long getTotalInterestAmount() {
		return totalInterestAmount;
	}

	public void setTotalInterestAmount(long totalInterestAmount) {
		this.totalInterestAmount = totalInterestAmount;
	}

	public long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getPenaltyRate() {
		return penaltyRate;
	}

	public void setPenaltyRate(BigDecimal penaltyRate) {
		this.penaltyRate = penaltyRate;
	}

	public UnderlyingAssetSource getAssetSource() {
		return assetSource;
	}

	public void setAssetSource(UnderlyingAssetSource assetSource) {
		this.assetSource = assetSource;
	}

	public long getCreditLimitNum() {
		return creditLimitNum;
	}

	public void setCreditLimitNum(long creditLimitNum) {
		this.creditLimitNum = creditLimitNum;
	}

	public long getCreditBalanceNum() {
		return creditBalanceNum;
	}

	public void setCreditBalanceNum(long creditBalanceNum) {
		this.creditBalanceNum = creditBalanceNum;
	}

	public long getCounterpartyLimitNum() {
		return counterpartyLimitNum;
	}

	public void setCounterpartyLimitNum(long counterpartyLimitNum) {
		this.counterpartyLimitNum = counterpartyLimitNum;
	}

	public long getCounterpartyBalanceNum() {
		return counterpartyBalanceNum;
	}

	public void setCounterpartyBalanceNum(long counterpartyBalanceNum) {
		this.counterpartyBalanceNum = counterpartyBalanceNum;
	}

	public long getInvoiceAmountNum() {
		return invoiceAmountNum;
	}

	public void setInvoiceAmountNum(long invoiceAmountNum) {
		this.invoiceAmountNum = invoiceAmountNum;
	}

	public long getInvoiceBalanceNum() {
		return invoiceBalanceNum;
	}

	public void setInvoiceBalanceNum(long invoiceBalanceNum) {
		this.invoiceBalanceNum = invoiceBalanceNum;
	}

	public long getFinanceAmountNum() {
		return financeAmountNum;
	}

	public void setFinanceAmountNum(long financeAmountNum) {
		this.financeAmountNum = financeAmountNum;
	}

	public long getFinanceBalanceNum() {
		return financeBalanceNum;
	}

	public void setFinanceBalanceNum(long financeBalanceNum) {
		this.financeBalanceNum = financeBalanceNum;
	}

	public BigDecimal getInterestRateNum() {
		return interestRateNum;
	}

	public void setInterestRateNum(BigDecimal interestRateNum) {
		this.interestRateNum = interestRateNum;
	}

	public String getGuaranteeGoodsNo() {
		return guaranteeGoodsNo;
	}

	public void setGuaranteeGoodsNo(String guaranteeGoodsNo) {
		this.guaranteeGoodsNo = guaranteeGoodsNo;
	}

	public String getExtensionDays() {
		return extensionDays;
	}

	public void setExtensionDays(String extensionDays) {
		this.extensionDays = extensionDays;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getLoanPeriodUnit() {
		return loanPeriodUnit;
	}

	public void setLoanPeriodUnit(String loanPeriodUnit) {
		this.loanPeriodUnit = loanPeriodUnit;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
