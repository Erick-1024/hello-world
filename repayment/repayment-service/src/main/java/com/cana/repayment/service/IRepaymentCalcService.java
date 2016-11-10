package com.cana.repayment.service;

public interface IRepaymentCalcService {

	/**
	 * 计算一笔放款的最低还款额。
	 */
	public long calcMinimumRepaymentAmount(String loanInfoId) throws Exception;
	
	/**
	 * 计算一笔放款的最大还款额。
	 */
	public long calcMaximumRepaymentAmount(String loanInfoId) throws Exception;
}
