package com.cana.vbam.common.repayment.enums;

public enum ActiveRepaymentStatus {
	already_active_repayment(0, "已主动还款"),
	complete_active_repayment(1, "主动还款已完成");
	
	private String desc;
	private int intValue;
	
	private ActiveRepaymentStatus(int intValue, String desc){
		this.desc = desc;
		this.intValue = intValue;
	}
	
	public String desc() {
		return desc;
	}
	
	public int intValue() {
		return intValue;
	}
}
