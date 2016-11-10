package com.cana.flight.finance.common.util;

import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;

public class Constants {
	
	// 编号序列数名称
	public static final String SEQUENCE_WHITE_CUSTOMER_ID = "white_customer_id";
	
	public static final String SEQUENCE_DAILY_BILL_ID = "daily_bill_id";
	
	public static final String SEPARATOR_CUSTOMER_NAME ="||";
	
	public static final String SEPARATOR_CUSTOMER_NAME_REGEX = "\\|{2}";
	
	public static final String TRAVELZEN_AUDIT_RESULT_RESPONSE_CODE_FORMAT_ERROR="100001";
	
	public static final String TRAVELZEN_REPAYMENT_URL_PREFIX = TopsConfReader.getConfContent("properties/travelzen-url.properties", "travelzen.repayment.url.prefix", ConfScope.R);
	
	public static final String TRAVELZEN_FLIGHT_TICKET_URL_PREFIX = TopsConfReader.getConfContent("properties/travelzen-url.properties", "travelzen.flight.ticket.url.prefix", ConfScope.R);
	
	public static final String TRAVELZEN_AUDIT_RESULT_URL_PREFIX = TopsConfReader.getConfContent("properties/travelzen-url.properties", "travelzen.audit.result.url.prefix", ConfScope.R);
	
	public static final String TRAVELZEN_CREDIT_LIMIT_FEEDBACK_URL_PREFIX = TopsConfReader.getConfContent("properties/travelzen-url.properties", "travelzen.credit.limit.feedback.url.prefix", ConfScope.R);
	
}
