package com.cana.repayment.server.validate;

public class LoanInfoBizVerifyRuleDesc {
	
	public static final String LOANNO_VERIFY = "放款编号不能重复";
	
	public static final String CURRENCY_VERIFY = "限用币别为人民币";
	
	public static final String RECEIVABLES_VERIFY = "应收账款金额应大于等于应收账款余额";
	
	public static final String FINANCE_VERIFY = "融资金额应大于等于融资余额";
	
	public static final String DATE_VERIFY = "放款日应小于等于到期日";
	
	public static final String DUEDATE_VERIFY = "放款日、期限单位、期限、到期日不匹配。正确的：放款日+（期限单位*贷款期限）=到期日";
	
}
