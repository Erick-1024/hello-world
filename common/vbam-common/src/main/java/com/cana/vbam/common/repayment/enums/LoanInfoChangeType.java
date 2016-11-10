package com.cana.vbam.common.repayment.enums;

public enum LoanInfoChangeType {
	created("新增放款信息"),
	modify("还款计划新增或改变"),
	offline_repayment("线下还款"),
	active_repayment("主动还款"),
	adjust("调账"),
	deduct("账扣"),
	extension_charge_generate("展期费用生成"),
	overdue_generate("逾期生成"),
	penalty_generate("罚息生成"),
	append_finance_amount("追加融资金额"),
	refund("退款"),
	tz_account("账户还款");
	
	private String desc;
	
	private LoanInfoChangeType(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}

	public static LoanInfoChangeType getChangeType(RepaymentMethod method) {
		switch (method) {
		case ACCOUNTDEDUCTION:
			return deduct;
		case ACTIVE:
			return active_repayment;
		case OFFLINE:
			return offline_repayment;
		case REFUND:
			return refund;
		case TZACCOUNT:
			return tz_account;
		default:
			return null;
		}
	}
}
