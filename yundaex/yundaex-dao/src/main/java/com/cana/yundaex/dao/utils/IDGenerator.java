package com.cana.yundaex.dao.utils;

import com.cana.yundaex.common.util.Constants;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class IDGenerator {

	private static SequenceGenerator generator = SpringApplicationContext.getApplicationContext().getBean(SequenceGenerator.class);
	
	/*public static String generateCreditTradeId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_YUNDAEX_CREDIT_TRADE_ID, 12);
	}*/
	
	public static String generateCustomerApplyId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_YUNDAEX_CREDIT_CUSTOMER_ID, 3);
	}

	/*public static String generateSupervisionAccountId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_CREDIT_SUPERVISION_ACCOUNT_ID, 4);
	}*/
	
	public static String generateCreditLimitId(){
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_YUNDAEX_CREDIT_LIMIT_ID,3);
	}
	
	public static String generateCreditLimitAuditId(){
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_YUNDAEX_CREDIT_LIMIT_AUDIT_ID,7);
	}
	public static String generateCreditTransferId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_YUNDAEX_CREDIT_TRANSFER_ID,12);
	}
	
	public static String generateCreditTransferBusinessSeq() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.BUSINESS_SEQ, 5);
	}
	
	public static String generateLoanInfoRecordId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_YUNDAEX_LOAN_INFO_RECORD_ID, 5);
	}
	public static String generateRepaymentPlanRecordId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_YUNDAEX_REPAYMENT_PLAN_RECORD_ID, 5);
	}
	public static String gnerateLoanInfoVersionId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_YUNDAEX_LOAN_INFO_VERSION, 4);
	}

	public static String generateCustomerApplyMonitorId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_YUNDAEX_CREDIT_CUSTOMER_MONITOR_ID, 4);
	}
	
	public static String generateCreditMonitorId() {
		return DateTimeUtil.datetime12() + generator.getNextSeq(Constants.SEQUENCE_NAME_YUNDAEX_CREDIT_MONITOR_ID, 4);
	}
	
}
