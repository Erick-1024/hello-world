package com.cana.flight.finance.dataaccess.travelzen.api;

import com.cana.flight.finance.common.dto.AsyncNotifyTzCustomerApplyResult;
import com.cana.flight.finance.common.dto.AsyncNotifyTzCustomerCreditLimitResult;
import com.cana.flight.finance.common.dto.CreditTradeResult;
import com.cana.flight.finance.common.dto.TravelzenBaseResponse;
import com.cana.flight.finance.common.dto.TravelzenFlightTicketResponse;
import com.cana.flight.finance.common.dto.TravelzenRepaymentResponse;

public interface ITravelzenDataApi {

	/**
	 * 获取真旅网的部分订单数据
	 * @param lastRecordId 上一次成功接收的记录id(如果是第一次获取则该值为null)
	 * @param num 最大记录数	
	 * @return
	 */
	public TravelzenFlightTicketResponse getFlightTickets(String lastRecordId, int num);
	
	/**
	 * 获取真旅网的部分财务数据
	 * @param lastRecordId 上一次成功接收的记录id
	 * @param num 最大记录数
	 * @return
	 */
	public TravelzenRepaymentResponse getRepayments(String lastRecordId, int num);
	
	/**
	 * Cana发送客户的额度申请审核结果
	 * @param canaAuditResult
	 * @param url
	 * @return
	 */
	public TravelzenBaseResponse sendAuditResult(AsyncNotifyTzCustomerApplyResult canaAuditResult, String url);
	
	/**
	 * Cana发送额度是否生效指令
	 * @param creditLimitFeedback
	 * @param url
	 * @return
	 */
	public TravelzenBaseResponse sendCreditLimitFeedback(AsyncNotifyTzCustomerCreditLimitResult creditLimitResult, String url);
	
	/**
	 * 向真旅发送交易结果
	 * @param notifyUrl　通知地址
	 * @param creditTradeResult
	 * @return
	 */
	public TravelzenBaseResponse sendCreditTradeResult(String notifyUrl, CreditTradeResult creditTradeResult);
	
}
