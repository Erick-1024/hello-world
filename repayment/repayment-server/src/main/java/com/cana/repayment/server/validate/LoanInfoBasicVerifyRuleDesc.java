package com.cana.repayment.server.validate;

public class LoanInfoBasicVerifyRuleDesc {

//-------------------------------正则描述-----------------------------------------//
	public static final String DUE_DATE_REGEX_MESSAGE = "到期日格式不正确或者为空";
	
	public static final String LOAN_DATE_REGEX_MESSAGE = "放款日格式不正确或者为空";
	
	public static final String FINANCE_AMOUNT_REGEX_MESSAGE = "融资金额格式不正确或者为空";
	
	public static final String FINANCE_BALANCE_REGEX_MESSAGE = "融资余额格式不正确或者为空";
	
	public static final String RECEIVABLES_AMOUNT_REGEX_MESSAGE = "应收账款金额格式不正确或者为空";
	
	public static final String RECEIVABLES_BALANCE_REGEX_MESSAGE = "应收账款余额格式不正确或者为空";
	
	public static final String LOAN_PERIOD_REGEX_MESSAGE = "贷款期限格式不正确或者为空";
	
	public static final String INTEREST_RATE_REGEX_MESSAGE = "利率格式不正确或者为空";
	
//-----------------------------------空值描述----------------------------------------------//	
	
	public static final String LOANNO_NOT_NULL = "放款编号不能为空";
	
	public static final String BUSSINESS_CONTRACTNO_NOT_NULL = "业务合同号不能为空";
	
	public static final String FINANCE_COMPANY_NOT_NULL = "融资客户公司名称不能为空";
	
	public static final String VOUCHERNO_NOT_NULL = "凭证号码不能为空";
	
	public static final String BUSSINESS_PRODUCT_NOT_NULL = "业务产品不能为空";
	
	public static final String CURRENCY_NOT_NULL = "币别不能为空或本系统不支持";
	
	public static final String LOAN_PERIOD_UNIT_NOT_NULL = "期限单位不能为空或本系统不支持";
	
	public static final String REPAYMENT_TYPE_NOT_NULL = "还款方式不能为空或本系统不支持";
	
	public static final String INTEREST_RATE_UNIT_NOT_NULL = "利率单位不能为空或本系统不支持";
}
