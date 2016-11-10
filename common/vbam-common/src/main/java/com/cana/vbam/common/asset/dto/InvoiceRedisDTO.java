package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

public class InvoiceRedisDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4157721992887641219L;

	//序号
	private String sequence;
	
	//账款序号
	private String invoiceSequence;
	
	//业务合同号
	private String businessContractNo;
	
	//客户名称
	private String memberName;
	
	//业务产品
	private String businessProductDesc;
	
	//币种
	private String currencyDesc;
	
	//交易对手名称
	private String counterparty;
	
	//单证号码
	private String invoiceNo;
	
	//单证面额
	private String nominvoiceAmt;
	
	//应收账款金额
	private String invoiceAmt;
	
	//融资比例
	private String financingRatio;
	
	//开票日
	private String invoiceDate;
	
	//到期日
	private String dueDate;
	
	//本次收取费用名称
	private String expenseSubject;
	
	//本次收取费用金额
	private String amount;

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getInvoiceSequence() {
		return invoiceSequence;
	}

	public void setInvoiceSequence(String invoiceSequence) {
		this.invoiceSequence = invoiceSequence;
	}

	public String getBusinessContractNo() {
		return businessContractNo;
	}

	public void setBusinessContractNo(String businessContractNo) {
		this.businessContractNo = businessContractNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getBusinessProductDesc() {
		return businessProductDesc;
	}

	public void setBusinessProductDesc(String businessProductDesc) {
		this.businessProductDesc = businessProductDesc;
	}

	public String getCurrencyDesc() {
		return currencyDesc;
	}

	public void setCurrencyDesc(String currencyDesc) {
		this.currencyDesc = currencyDesc;
	}

	public String getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
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

	public String getFinancingRatio() {
		return financingRatio;
	}

	public void setFinancingRatio(String financingRatio) {
		this.financingRatio = financingRatio;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getExpenseSubject() {
		return expenseSubject;
	}

	public void setExpenseSubject(String expenseSubject) {
		this.expenseSubject = expenseSubject;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
	
	
}
