package com.cana.vbam.common.asset.loan.dto;

import java.io.Serializable;

public class GenerateLoanPlanRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String financeAmount; // 融资金额，单位元
	private String repaymentType; // 还款方式
	private int dayCountConvention; // 计息基准天数，360或者365
	private String interestRateUnit; // 利率单位
	private String interestRate; // 利率，百分之
	private String loanDate; // 放款日期，yyyy-MM-dd
	private String dueDate; // 放款到期日，yyyy-MM-dd

	public String getFinanceAmount() {
		return financeAmount;
	}

	public void setFinanceAmount(String financeAmount) {
		this.financeAmount = financeAmount;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public int getDayCountConvention() {
		return dayCountConvention;
	}

	public void setDayCountConvention(int dayCountConvention) {
		this.dayCountConvention = dayCountConvention;
	}

	public String getInterestRateUnit() {
		return interestRateUnit;
	}

	public void setInterestRateUnit(String interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
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

}
