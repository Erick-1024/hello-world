package com.cana.vbam.common.repayment.message.dto;

import java.io.Serializable;

public class AdjustSuccessMessage implements Serializable {

	private static final long serialVersionUID = 7831530362972083611L;
	// 消息id
	private String messageId;
	// 放款信息id
	private String loanInfoId;
	// 放款编号
	private String loanNo;
	// 融资客户id
	private String financeId;
	// 保理商id
	private String factorId;
	// 保理商名称
	private String factorCompany;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getFinanceId() {
		return financeId;
	}

	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getFactorCompany() {
		return factorCompany;
	}

	public void setFactorCompany(String factorCompany) {
		this.factorCompany = factorCompany;
	}

}
