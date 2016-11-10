package com.cana.credit.dao.utils;

import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class IDGenerator {

	private static SequenceGenerator generator = SpringApplicationContext.getApplicationContext().getBean(SequenceGenerator.class);
	
	public static String generateCreditTradeId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_CREDIT_TRADE_ID, 12);
	}
	
	public static String generateCustomerApplyId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_CREDIT_CUSTOMER_ID, 3);
	}
	
	public static String generateSupervisionAccountId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_CREDIT_SUPERVISION_ACCOUNT_ID, 4);
	}
	
	public static String generateCreditTransferId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_CREDIT_TRANSFER_ID,12);
	}
	
	public static String generateCreditTransferBusinessSeq() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.BUSINESS_SEQ, 5);
	}
	
}
