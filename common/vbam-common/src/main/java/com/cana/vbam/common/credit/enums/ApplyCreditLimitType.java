package com.cana.vbam.common.credit.enums;

public enum ApplyCreditLimitType {

	REPAYMENT("订单回款"),
	FINANCING("订单融资");
	
	private String desc;
	
	private ApplyCreditLimitType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
