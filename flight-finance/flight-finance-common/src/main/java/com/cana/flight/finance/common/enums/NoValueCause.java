package com.cana.flight.finance.common.enums;

public enum NoValueCause {

	REFUND("退票"),
	ENDORSE("改签"),
    TAKE_OFF("航班全部起飞");
	
	private String desc;
	
	private NoValueCause(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
