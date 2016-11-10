package com.cana.flight.finance.common.enums;

public enum PassengerType {

	ADT("成人"),
	INF("婴儿"),
    CHD("儿童");
	
	private String desc;
	
	private PassengerType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
