package com.cana.flight.finance.service.transaction;

public interface IFlightTicketTransactionService {

	/**
	 * 更新三个标示
	 * @param startRecordId 获取客票的起始recordId
	 * @return 返回新的startRecordId
	 */
	public String updateFlag(String startRecordId);
	
}
