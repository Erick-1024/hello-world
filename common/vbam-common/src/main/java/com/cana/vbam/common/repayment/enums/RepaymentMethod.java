package com.cana.vbam.common.repayment.enums;

public enum RepaymentMethod {

	ACCOUNTDEDUCTION("自动扣款"),
	ACTIVE("主动还款"),
	OFFLINE("线下还款"),
	REFUND("退款还款"),
	TZACCOUNT("账户还款");
	
	private String desc;
	
	private RepaymentMethod(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
}
