package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

public class FinanceInfo4FinanceDTO implements Serializable{

	private static final long serialVersionUID = -4543390467640635215L;
	
	// 融资余额, 单位分
	private long financeBalance;
	// 融资笔数
	private int loanInfoNum;
	// 逾期金额, 单位分
	private long totalOverdueAmount;
	// 逾期笔数
	private int overdueNum;
	// 近7日待还款金额, 单位分
	private long toPayAmount;
	
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
	public long getToPayAmount() {
		return toPayAmount;
	}
	public void setToPayAmount(long toPayAmount) {
		this.toPayAmount = toPayAmount;
	}

}
