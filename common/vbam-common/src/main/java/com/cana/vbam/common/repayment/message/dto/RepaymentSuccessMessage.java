package com.cana.vbam.common.repayment.message.dto;

import java.io.Serializable;

import com.cana.vbam.common.repayment.enums.RepaymentMethod;

/**
 * 还款成功消息
 * @author renshui
 *
 */
public class RepaymentSuccessMessage implements Serializable{
	
	private static final long serialVersionUID = 3745786201620639949L;
	// 还款类型
	RepaymentMethod repaymentMethod;
	// 消息id
	private String messageId;
	// 总还款记录id
	private String repaymentSummaryRecordId;
	// 放款信息id
	private String loanInfoId;
	// 放款编号
	private String loanNo;
	// 融资客户id
	private String financeId;
	// 融资客户名称
	private String financeCompany;
	// 外部客户id
	private String outCustomerId;
	// 保理商id
	private String factorId;
	// 融资客户的还款账号
	 private String accountNo;
	// 总的还款金额
	private long total;
	// 还款日期，格式: yyyy-MM-dd
	private String repaymentDate;
	// 业务产品id
	private String businessProductId;
	// 如果是退款还款的话，此字段代表“发起还款的外部平台名称”
	private String institutionName;
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public String getRepaymentSummaryRecordId() {
		return repaymentSummaryRecordId;
	}
	public void setRepaymentSummaryRecordId(String repaymentSummaryRecordId) {
		this.repaymentSummaryRecordId = repaymentSummaryRecordId;
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
	public String getFinanceCompany() {
		return financeCompany;
	}
	public void setFinanceCompany(String financeCompany) {
		this.financeCompany = financeCompany;
	}
	
	public String getOutCustomerId() {
		return outCustomerId;
	}
	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}
	public String getFactorId() {
		return factorId;
	}
	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getRepaymentDate() {
		return repaymentDate;
	}
	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}
	public String getBusinessProductId() {
		return businessProductId;
	}
	public void setBusinessProductId(String businessProductId) {
		this.businessProductId = businessProductId;
	}
	public RepaymentMethod getRepaymentMethod() {
		return repaymentMethod;
	}
	public void setRepaymentMethod(RepaymentMethod repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	
}
