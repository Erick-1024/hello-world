package com.cana.flight.finance.common.enums;

public enum PayType {

	CREDIT("信用"),
	OTHER("其他");
	
	private String desc;
	
	private PayType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
