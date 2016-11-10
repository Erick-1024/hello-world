package com.cana.report.service.event;

public interface ILoanInfoChangeEventListener {
	/**
	 * 执行指定的事件
	 * @param event
	 * @throws Exception
	 */
	public void execute(LoanInfoChangeEvent event) throws Exception;
}