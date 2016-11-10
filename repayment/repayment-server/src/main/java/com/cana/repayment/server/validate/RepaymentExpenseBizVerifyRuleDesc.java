package com.cana.repayment.server.validate;

public class RepaymentExpenseBizVerifyRuleDesc {
	public static final String REPAYMENT_DATE_VERIFY = "还款日不能小于当前的营业日";
	
	public static final String SETTLEMENT_STATE_VERIFY = "结清状态必须是未结清、已结清";
	
	public static final String LOAN_INFO_VERIFY = "放款信息不存在";

	public static final String LOAN_INFO_VERIFY_2 = "费用已存在";
	
	public static final String FINANCE_COMPANY_VERIFY = "融资客户与放款信息中不一致";
}
