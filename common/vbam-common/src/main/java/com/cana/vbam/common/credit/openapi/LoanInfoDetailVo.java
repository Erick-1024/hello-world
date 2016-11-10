package com.cana.vbam.common.credit.openapi;

import java.io.Serializable;

import com.cana.vbam.common.repayment.dto.RepaymentAmount;

public class LoanInfoDetailVo implements Serializable{

	private static final long serialVersionUID = -1296686465480022310L;

	private String customerId;//外部客户Id
	
	private String loanInfoId;//放款批次号
	
	private String loanNo;// 放款编号
	
	private String loanDate;//放款日
	
	private String dueDate;//到期日
	
	private RepaymentAmount repaymentAmount;//融资金额信息
	
	private Integer extensionDays;//展期天数
	
	private String settleStatus;

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

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public RepaymentAmount getRepaymentAmount() {
		return repaymentAmount;
	}

	public void setRepaymentAmount(RepaymentAmount repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
