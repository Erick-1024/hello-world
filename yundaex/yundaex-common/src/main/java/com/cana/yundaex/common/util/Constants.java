package com.cana.yundaex.common.util;

import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;

public class Constants {
	public static final String YUNDAEX_STATION_INFO_URL_PREFIX = TopsConfReader.getConfContent("properties/yundaex-url.properties", "yundaex.station.url.prefix", ConfScope.R);
	// http://127.0.0.1:8080/yundaex-openapi/cana/test/auditResultPush?
	public static final String YUNDAEX_AUDIT_RESULT_URL_PREFIX = TopsConfReader.getConfContent("properties/yundaex-url.properties", "yundaex.audit.result.url.prefix", ConfScope.R);
	public static final String YUNDAEX_LOAN_RESULT_URL_PREFIX = TopsConfReader.getConfContent("properties/yundaex-url.properties", "yundaex.loan.result.url.prefix", ConfScope.R);
	public static final String YUNDAEX_REPAYMENT_RESULT_URL_PREFIX = TopsConfReader.getConfContent("properties/yundaex-url.properties", "yundaex.loan.repayment.url.prefix", ConfScope.R);
	public static final String YUNDAEX_YUNDAEX_FINACE_ID = TopsConfReader.getConfContent("properties/yundaex.properties", "yundaex.finace.id", ConfScope.R);
	public static final String YUNDAEX_MAX_CREDIT_LIMIT = TopsConfReader.getConfContent("properties/yundaex.properties", "yundaex.max.credit.limit", ConfScope.R);
	
	// yundaex
	public static final String SEQUENCE_NAME_YUNDAEX_CREDIT_CUSTOMER_ID = "yundaex_customer_apply_id";
	public static final String SEQUENCE_NAME_YUNDAEX_CREDIT_CUSTOMER_MONITOR_ID = "yundaex_customer_apply_monitor_id";
	public static final String SEQUENCE_NAME_YUNDAEX_CREDIT_MONITOR_ID = "yundaex_credit_monitor_id";
	public static final String SEQUENCE_NAME_YUNDAEX_CREDIT_LIMIT_ID = "yundaex_credit_limit_id";
	public static final String SEQUENCE_NAME_YUNDAEX_CREDIT_TRANSFER_ID = "yundaex_credit_transfer_id";
	public static final String SEQUENCE_NAME_YUNDAEX_LOAN_INFO_RECORD_ID = "yundaex_loan_info_record_id";
	public static final String SEQUENCE_NAME_YUNDAEX_REPAYMENT_PLAN_RECORD_ID = "yundaex_repayment_plan_record_id";
	public static final String SEQUENCE_NAME_YUNDAEX_CREDIT_LIMIT_AUDIT_ID = "yundaex_credit_limit_audit_id";
	public static final String SEQUENCE_NAME_YUNDAEX_LOAN_INFO_VERSION = "loan_info_version";
	public static final String BUSINESS_SEQ = "business_seq";
	
	public static final String SEQUENCE_NAME_SINGLE_LOAN_NUMBER = "single_loan_number";
	public static final String SEQUENCE_NAME_FINANCE_CONTRACT_SERIAL_NUMBER = "finance_contract_serial_number";
	public static final String SEQUENCE_NAME_PERSONAL_INFO_ID = "personal_info_id";
	public static final String SEQUENCE_NAME_CONTRACT_SITUATION_ID = "contract_situation_id";
	public static final String SEQUENCE_NAME_INTEREST_RATE_ID = "yundaex_interest_rate_id";
	
	//韵达评分准入最低分数
	public static final String minScore = "70";
	//韵达保证金账户余额/日资金需求
	public static final String DayMoney = "0.1";
	
	// 韵达项目id
	public static final String YUNDAEX_ASSET_PROJECT_ID = TopsConfReader.getConfContent("properties/yundaex.properties", "yundaex.assest.project.id", ConfScope.R); 

	// 韵达项目-国内保理合同-网点贷
	public static final String SERIAL_NUMBER_PREFIX = TopsConfReader.getConfContent("properties/yundaex.properties", "serial.number.prefix", ConfScope.R); 
	public static final String FACTOR_FINANCING_RADIO = TopsConfReader.getConfContent("properties/yundaex.properties", "factor.financing.radio", ConfScope.R); 
	public static final String FINANCE_CREDIT_MAX = TopsConfReader.getConfContent("properties/yundaex.properties", "finance.credit.max", ConfScope.R); 
	public static final String CREDIT_DUE_PERIOD = TopsConfReader.getConfContent("properties/yundaex.properties", "credit.due.period", ConfScope.R); 
	public static final String FINANCE_RADIO = TopsConfReader.getConfContent("properties/yundaex.properties", "finance.radio", ConfScope.R); 
	public static final String FACTOR_RADIO = TopsConfReader.getConfContent("properties/yundaex.properties", "factor.radio", ConfScope.R); 
	public static final String CONTRACT_DUE_PERIOD = TopsConfReader.getConfContent("properties/yundaex.properties", "contract.due.period", ConfScope.R); 
	
	//个人信息激活链接有效时间/小时
	public static final int PERSNAL_MAIL_URL_DUE_PERIOD = 24;
	
	//利率
	public static final String BASE_RATE = TopsConfReader.getConfContent("properties/yundaex.properties", "base.rate", ConfScope.R); 
	public static final String RISK_FLOAT_RATIO = TopsConfReader.getConfContent("properties/yundaex.properties", "risk.float.ratio", ConfScope.R); 
	public static final String CUSTOMER_GRADE_WEIGHT = TopsConfReader.getConfContent("properties/yundaex.properties", "customer.grade.weight", ConfScope.R); 
	
	// 最近一次拉取网点的统计日期
	public static final String YUNDAEX_STATION_PULL_DATE = "yundaex_station_pull_date";
	
}
