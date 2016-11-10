package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.SettleStatus;

public class LoanInfoDetail implements Serializable{
	
	private static final long serialVersionUID = 7674473668689825174L;
	// 放款信息id
	private String loanInfoId;
	// 放款编号
	private String loanNo;
	// 放款日, 格式：yyyy-MM-dd
	private String loanDate;
	// 到期日, 格式：yyyy-MM-dd
	private String dueDate;
	// 融资金额, 单位：分
	private long financeAmount;
	// 金额信息
	private RepaymentAmount repaymentAmount;
	// 结清状态
	private SettleStatus settleStatus;
	// 融资客户在cana平台的用户id
	private String financeId;
	// 融资客户在cana平台的企业名称
	private String financeCompany;
	// 融资客户的外部ID
	private String outCustomerId;
	// 融资客户的外部名称
	private String outCustomerName;
	// 利率单位
	private InterestRateUnit interestRateUnit;
	// 利率值
	private BigDecimal interestRate;
	// 当前放款的第一期还款计划的展期天数
	private Integer extensionDays;
	
	public String getLoanInfoId() {
		return loanInfoId;
	}
	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}
	public String getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}
	public long getFinanceAmount() {
		return financeAmount;
	}
	public void setFinanceAmount(long financeAmount) {
		this.financeAmount = financeAmount;
	}
	public RepaymentAmount getRepaymentAmount() {
		return repaymentAmount;
	}
	public void setRepaymentAmount(RepaymentAmount repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}
	public SettleStatus getSettleStatus() {
		return settleStatus;
	}
	public void setSettleStatus(SettleStatus settleStatus) {
		this.settleStatus = settleStatus;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getFinanceCompany() {
		return financeCompany;
	}
	public void setFinanceCompany(String financeCompany) {
		this.financeCompany = financeCompany;
	}
	public InterestRateUnit getInterestRateUnit() {
		return interestRateUnit;
	}
	public void setInterestRateUnit(InterestRateUnit interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}
	public BigDecimal getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
	public String getFinanceId() {
		return financeId;
	}
	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}
	public String getOutCustomerId() {
		return outCustomerId;
	}
	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}
	public String getOutCustomerName() {
		return outCustomerName;
	}
	public void setOutCustomerName(String outCustomerName) {
		this.outCustomerName = outCustomerName;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public Integer getExtensionDays() {
		return extensionDays;
	}
	public void setExtensionDays(Integer extensionDays) {
		this.extensionDays = extensionDays;
	}

}
