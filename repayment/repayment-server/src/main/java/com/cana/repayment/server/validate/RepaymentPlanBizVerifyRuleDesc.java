package com.cana.repayment.server.validate;

public class RepaymentPlanBizVerifyRuleDesc {
	public static final String SETTLEMENT_STATE_VERIFY = "结清状态必须是未结清、已结清";
	
	public static final String ACCOUNT_REPAYMENT_TOTAL_VERIFY = "应还总金额=应还本金+应还利息+应还服务费";
	
	public static final String ACCOUNT_REPAYMENT_PRINCIPAL_VERIFY = "应还本金应小于等于融资余额";
	
	public static final String REPAYMENT_DATE_VERIFY = "还款日不能小于当前营业日";

	public static final String REPAYMENT_DATE_VERIFY_2 = "还款日应大于等于结息日";

	public static final String VALUE_DATE_VERIFY = "起息日应小于结息日";

	public static final String FINANCE_AMOUNT_VERIFY = "融资金额应大于等于融资余额";
	
	public static final String REPAYMENT_PERIOD_VERIFY = "期数不正确";

	public static final String REPAYMENT_PERIOD_VERIFY_2 = "同一批次的还款计划中某一期存在错误";
	
	public static final String LOAN_INFO_VERIFY = "放款信息不存在";

	public static final String REPAYMENT_PLAN_VERIFY = "还款计划已存在";
	
	public static final String LOAN_DATE_VERIFY = "放款日与放款信息中不一致";

	public static final String DUE_DATE_VERIFY = "到期日与放款信息中不一致";

	public static final String FINANCE_AMOUNT_EQUAL_VERIFY = "融资金额与放款信息中不一致";
	
	public static final String FINANCE_COMPANY_VERIFY = "融资客户与放款信息中不一致";
	
	public static final String REPAYMENT_DATE_VERIFY_3 = "期数大的还款日必须大于期数小的还款日";

	public static final String REPAYMENT_DATE_VERIFY_4 = "中间期数大的还款日不能大于到期日";

	public static final String VALUE_DATE_VERIFY_2 = "第一期的起息日与放款信息的放款日不一致";
	
	public static final String LOAN_DATE_VERIFY_2 = "最后一期还款日与放款信息的到期日不一致";
	
	public static final String VALUE_DATE_VERIFY_3 = "起息日不正确:当期起息日应为上期结息日的后一天";
}
