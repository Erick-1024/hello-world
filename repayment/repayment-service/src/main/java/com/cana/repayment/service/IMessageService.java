package com.cana.repayment.service;

public interface IMessageService {
	
	/**
	 * 调账消息发送
	 * @param operatorId
	 * @param factor
	 * @param loanInfoNo
	 * @param id
	 * @param receiveCustomerId
	 */
	public void sendAdjustMessage(String operatorId, String factor, String loanInfoNo, String id, String receiveCustomerId);
	
	/**
	 * 自动账扣消息发送
	 * @param operatorId
	 * @param factor
	 * @param loanInfoNo
	 * @param id
	 * @param receiveCustomerId
	 */
	public void sendDeductMessage(String loanInfoNo, String loanInfoId, String receiveCustomerId, String accountNo, String amount);
	
	/**
	   *  主动还款消息发送
	 * @param loanInfoNo
	 * @param loanInfoId
	 * @param receiveCustomerId
	 * @param accountNo
	 * @param amount
	 */
	public void sendActiveRepaymentMessage(String loanNo, String loanInfoId, String receiveCustomerId, String financeCompany);
}
