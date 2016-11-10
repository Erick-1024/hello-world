package com.cana.vbam.common.credit.enums;

public enum CreditLimitGenerateState {

	WAIT("待生成"),
	FINANCE("已生成");
	
	private String desc;
	
	private CreditLimitGenerateState(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
