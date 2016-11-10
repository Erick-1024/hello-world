package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

public class FinanceInfo4FactorDTO implements Serializable{

	private static final long serialVersionUID = -6894899438370222994L;
	// 放款余额, 单位分
	private long financeBalance;
	// 放款笔数
	private int loanInfoNum;
	// 逾期金额, 单位分
	private long totalOverdueAmount;
	// 逾期笔数
	private int overdueNum;
	
	public long getFinanceBalance() {
		return financeBalance;
	}
	public void setFinanceBalance(long financeBalance) {
		this.financeBalance = financeBalance;
	}
	public int getLoanInfoNum() {
		return loanInfoNum;
	}
	public void setLoanInfoNum(int loanInfoNum) {
		this.loanInfoNum = loanInfoNum;
	}
	public long getTotalOverdueAmount() {
		return totalOverdueAmount;
	}
	public void setTotalOverdueAmount(long totalOverdueAmount) {
		this.totalOverdueAmount = totalOverdueAmount;
	}
	public int getOverdueNum() {
		return overdueNum;
	}
	public void setOverdueNum(int overdueNum) {
		this.overdueNum = overdueNum;
	}
}
