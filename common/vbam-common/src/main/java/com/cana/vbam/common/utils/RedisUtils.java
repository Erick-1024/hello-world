package com.cana.vbam.common.utils;

/**
 * @author hu
 *
 */
public class RedisUtils {

	public final static String INPUTREDIS = "input";
	
	public final static String INVOICE = "invoice";
	
	public final static String ADJUSTMENTREDIS = "adjustment";
	
	public final static String YUNDAEXMONITOR = "yundaexMonitor";
	
	public static String generateLoanInfoRedisKeyByOperator(String key, String operatorId){
		return key+INPUTREDIS+operatorId;
	}
	
	public static String generateRedisKeyByOperator(String key, String operatorId, String busModal){
		return key+busModal+operatorId;
	}
	
	public static String generateRepaymentPlanRedisKeyByOperator(String loanInfoId, String key, String operatorId) {
		return (null == loanInfoId ? "" : loanInfoId)+key+INPUTREDIS+operatorId;
	}
	
	//应收账款
	public static String generateInvoiceInfoRedisKeyByOperator(String key, String operatorId){
		return key+INVOICE+operatorId;
	}

	public static String generateAssetLoanInfoRedisKeyByOperator(String key, String operatorId){
		return key+Constants.SEQUENCE_NAME_ASSET_LOAN_INFO_REDIS_KEY+operatorId;
	}
	
	public static String generateAssetLoanPlanRedisKeyByOperator(String key, String operatorId){
		return key+Constants.SEQUENCE_NAME_ASSET_LOAN_PLAN_REDIS_KEY+operatorId;
	}

	public static String generateUnderlyingAssetRedisKeyByOperator(String key, String operatorId) {
		return key + Constants.SEQUENCE_NAME_UNDERLYING_ASSET_REDIS_KEY + operatorId;
	}
	
	public static String genertateAssetMarketDataRedisKeyByOperator(String key, String operatorId){
		return key + Constants.SEQUENCE_NAME_ASSET_MARKET_DATA_REDIS_KEY + operatorId;
	}
	
	public static String generateExcelInfoRedisKeyByOperator(String key, String mapKey) {
		return key+mapKey;
	}

	public static String generateMonitorRedisKeyByCustomerId(String key, String customerId) {
		return key+customerId+YUNDAEXMONITOR;
	}
	
	public static String generateHomsomSettlementRedisKeyByOperator(String key, String operatorId) {
		return key + Constants.SEQUENCE_NAME_HOMSOM_SETTLEMENT_REDIS_KEY + operatorId;
	}
	
}