package com.cana.flight.finance.common.enums;

public enum OrderType {

	NORMAL("正常订单"),
	REFUND("退票"),
    ENDORSE("改签");
	
	private String desc;
	
	private OrderType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
