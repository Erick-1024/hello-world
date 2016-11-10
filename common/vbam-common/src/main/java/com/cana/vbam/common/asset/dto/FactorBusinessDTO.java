package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.asset.enums.BusinessMode;
import com.cana.vbam.common.asset.enums.BusinessProduct;
import com.cana.vbam.common.asset.enums.LoanState;
import com.cana.vbam.common.asset.enums.ReceiptType;
import com.cana.vbam.common.repayment.enums.Currency;
import com.cana.vbam.common.repayment.enums.RepaymentType;

public class FactorBusinessDTO implements Serializable{
	
	private static final long serialVersionUID = -1225756945429602305L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 保理商Id
	 */
	private String factorId;
	
	/**
	 * 保理商名称
	 */
	private String factorName;
	
	/**
     * 客户Id
     */
    private String customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 业务合同号
     */
    private String businessContractNo;

    /**
     * 币种
     */
    private Currency currency;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 业务产品
     */
    private BusinessProduct businessProduct;

    /**
     * 业务模式
     */
    private BusinessMode businessMode;

    /**
     * 合同起始日期
     */
    private String contractStartDate;

    /**
     * 合同到期日期
     */
    private String contractEndDate;

    /**
     * 利率
     */
    private String interestRate;

    /**
     * 计息方式
     */
    private RepaymentType interestType;

    /**
     * 手续费率
     */
    private String feeRate;

    /**
     * 罚息利率
     */
    private String penaltyRate;

    /**
     * 宽限期
     */
    private String extensionDays;

    /**
     * 付款期限
     */
    private String paymentPeriod;

    /**
     * 单证类型
     */
    private ReceiptType receiptType;

    /**
     * 还款来源
     */
    private String repaymentSource;

    /**
     * 还款安排
     */
    private String repaymentArrangement;

    /**
     * 资金用途
     */
    private String fundUsage;

    /**
     * 监控方案
     */
    private String monitoringSolution;

    /**
     * 增信措施
     */
    private String increaseTrustMeasures;

    /**
     * 约束性条款
     */
    private String bindingProvisions;

    /**
     * 放款状态
     */
    private LoanState loanState;

    /**
     * 保理专户户名
     */
    private String factoringAccountName;

    /**
     * 保理专用账户开户行
     */
    private String factoringAccountBankAddress;

    /**
     * 保理专用账户
     */
    private String factoringAccount;

    /**
     * 结算账户户名
     */
    private String settlementAccountName;

    /**
     * 结算账户开户行
     */
    private String settlementAccountBankAddress;

    /**
     * 结算账户账号
     */
    private String settlementAccount;
    
    /**
     * 费用信息
     */
    private List<ExpenseDTO> expenseList; 
    
    /**
     * 约定信息
     */
    private List<BusinessCounterpartyDTO> counterpartyList;
    
    /**
     * 担保信息
     */
    private List<BusinessGuaranteeInfoDTO> guaranteeInfoList;
    
    /**
     * 额度变更Id
     */
    private String creditVersion;
    
    /**
     * 额度Id
     */
    private String creditId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getBusinessContractNo() {
		return businessContractNo;
	}

	public void setBusinessContractNo(String businessContractNo) {
		this.businessContractNo = businessContractNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public String getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(String feeRate) {
		this.feeRate = feeRate;
	}

	public String getPenaltyRate() {
		return penaltyRate;
	}

	public void setPenaltyRate(String penaltyRate) {
		this.penaltyRate = penaltyRate;
	}

	public String getExtensionDays() {
		return extensionDays;
	}

	public void setExtensionDays(String extensionDays) {
		this.extensionDays = extensionDays;
	}

	public String getPaymentPeriod() {
		return paymentPeriod;
	}

	public void setPaymentPeriod(String paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}

	public String getRepaymentSource() {
		return repaymentSource;
	}

	public void setRepaymentSource(String repaymentSource) {
		this.repaymentSource = repaymentSource;
	}

	public String getRepaymentArrangement() {
		return repaymentArrangement;
	}

	public void setRepaymentArrangement(String repaymentArrangement) {
		this.repaymentArrangement = repaymentArrangement;
	}

	public String getFundUsage() {
		return fundUsage;
	}

	public void setFundUsage(String fundUsage) {
		this.fundUsage = fundUsage;
	}

	public String getMonitoringSolution() {
		return monitoringSolution;
	}

	public void setMonitoringSolution(String monitoringSolution) {
		this.monitoringSolution = monitoringSolution;
	}

	public String getIncreaseTrustMeasures() {
		return increaseTrustMeasures;
	}

	public void setIncreaseTrustMeasures(String increaseTrustMeasures) {
		this.increaseTrustMeasures = increaseTrustMeasures;
	}

	public String getBindingProvisions() {
		return bindingProvisions;
	}

	public void setBindingProvisions(String bindingProvisions) {
		this.bindingProvisions = bindingProvisions;
	}

	public String getFactoringAccountName() {
		return factoringAccountName;
	}

	public void setFactoringAccountName(String factoringAccountName) {
		this.factoringAccountName = factoringAccountName;
	}

	public String getFactoringAccountBankAddress() {
		return factoringAccountBankAddress;
	}

	public void setFactoringAccountBankAddress(String factoringAccountBankAddress) {
		this.factoringAccountBankAddress = factoringAccountBankAddress;
	}

	public String getFactoringAccount() {
		return factoringAccount;
	}

	public void setFactoringAccount(String factoringAccount) {
		this.factoringAccount = factoringAccount;
	}

	public String getSettlementAccountName() {
		return settlementAccountName;
	}

	public void setSettlementAccountName(String settlementAccountName) {
		this.settlementAccountName = settlementAccountName;
	}

	public String getSettlementAccountBankAddress() {
		return settlementAccountBankAddress;
	}

	public void setSettlementAccountBankAddress(String settlementAccountBankAddress) {
		this.settlementAccountBankAddress = settlementAccountBankAddress;
	}

	public String getSettlementAccount() {
		return settlementAccount;
	}

	public void setSettlementAccount(String settlementAccount) {
		this.settlementAccount = settlementAccount;
	}

	public List<ExpenseDTO> getExpenseList() {
		return expenseList;
	}

	public void setExpenseList(List<ExpenseDTO> expenseList) {
		this.expenseList = expenseList;
	}

	public List<BusinessCounterpartyDTO> getCounterpartyList() {
		return counterpartyList;
	}

	public void setCounterpartyList(List<BusinessCounterpartyDTO> counterpartyList) {
		this.counterpartyList = counterpartyList;
	}

	public List<BusinessGuaranteeInfoDTO> getGuaranteeInfoList() {
		return guaranteeInfoList;
	}

	public void setGuaranteeInfoList(
			List<BusinessGuaranteeInfoDTO> guaranteeInfoList) {
		this.guaranteeInfoList = guaranteeInfoList;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BusinessProduct getBusinessProduct() {
		return businessProduct;
	}

	public void setBusinessProduct(BusinessProduct businessProduct) {
		this.businessProduct = businessProduct;
	}

	public BusinessMode getBusinessMode() {
		return businessMode;
	}

	public void setBusinessMode(BusinessMode businessMode) {
		this.businessMode = businessMode;
	}

	public RepaymentType getInterestType() {
		return interestType;
	}

	public void setInterestType(RepaymentType interestType) {
		this.interestType = interestType;
	}

	public ReceiptType getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(ReceiptType receiptType) {
		this.receiptType = receiptType;
	}

	public LoanState getLoanState() {
		return loanState;
	}

	public void setLoanState(LoanState loanState) {
		this.loanState = loanState;
	}

	public String getCreditVersion() {
		return creditVersion;
	}

	public void setCreditVersion(String creditVersion) {
		this.creditVersion = creditVersion;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	public String getCreditId() {
		return creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}
}
