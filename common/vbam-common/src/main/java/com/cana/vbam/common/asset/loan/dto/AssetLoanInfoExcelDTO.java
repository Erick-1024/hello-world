package com.cana.vbam.common.asset.loan.dto;

import java.io.Serializable;

/**
 * @author hu
 *
 */
public class AssetLoanInfoExcelDTO extends EditAssetLoanRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7854704356019921811L;
	
	/**
	 * 融资客户
	 */
	private String financeName;
	/**
	 * 业务产品
	 */
	private String businessProduct;
	/**
	 * 交易对手
	 */
	private String counterpartyName;
	/**
	 * 单证号
	 */
	private String invoiceNo;
	/**
	 * 单证面额
	 */
	private String nominvoiceAmt;
	/**
	 * 应收金额
	 */
	private String invoiceAmt;
	/**
	 * 融资余额
	 */
	private String financeBalance;
	
	private String loanPeriodStr; // 放款期限
	
	private String dayCountConventionStr; // 计息基准天数，360或者365
	
	private String interestRateUnitDesc;
	
	private String loanPeriodUnitDesc;
	
	private String repaymentTypeDesc;
	
	private String checkFailedMessage;
	
	public String getFinanceName() {
		return financeName;
	}
	public void setFinanceName(String financeName) {
		this.financeName = financeName;
	}
	public String getBusinessProduct() {
		return businessProduct;
	}
	public void setBusinessProduct(String businessProduct) {
		this.businessProduct = businessProduct;
	}
	public String getCounterpartyName() {
		return counterpartyName;
	}
	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getNominvoiceAmt() {
		return nominvoiceAmt;
	}
	public void setNominvoiceAmt(String nominvoiceAmt) {
		this.nominvoiceAmt = nominvoiceAmt;
	}
	public String getInvoiceAmt() {
		return invoiceAmt;
	}
	public void setInvoiceAmt(String invoiceAmt) {
		this.invoiceAmt = invoiceAmt;
	}
	public String getFinanceBalance() {
		return financeBalance;
	}
	public void setFinanceBalance(String financeBalance) {
		this.financeBalance = financeBalance;
	}
	public String getLoanPeriodStr() {
		return loanPeriodStr;
	}
	public void setLoanPeriodStr(String loanPeriodStr) {
		this.loanPeriodStr = loanPeriodStr;
	}
	public String getDayCountConventionStr() {
		return dayCountConventionStr;
	}
	public void setDayCountConventionStr(String dayCountConventionStr) {
		this.dayCountConventionStr = dayCountConventionStr;
	}
	public String getCheckFailedMessage() {
		return checkFailedMessage;
	}
	public void setCheckFailedMessage(String checkFailedMessage) {
		this.checkFailedMessage = checkFailedMessage;
	}
	public String getInterestRateUnitDesc() {
		return interestRateUnitDesc;
	}
	public void setInterestRateUnitDesc(String interestRateUnitDesc) {
		this.interestRateUnitDesc = interestRateUnitDesc;
	}
	public String getLoanPeriodUnitDesc() {
		return loanPeriodUnitDesc;
	}
	public void setLoanPeriodUnitDesc(String loanPeriodUnitDesc) {
		this.loanPeriodUnitDesc = loanPeriodUnitDesc;
	}
	public String getRepaymentTypeDesc() {
		return repaymentTypeDesc;
	}
	public void setRepaymentTypeDesc(String repaymentTypeDesc) {
		this.repaymentTypeDesc = repaymentTypeDesc;
	}

}
