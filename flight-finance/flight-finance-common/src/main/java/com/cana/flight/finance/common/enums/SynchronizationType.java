package com.cana.flight.finance.common.enums;

public enum SynchronizationType {

	FLIGHTORDER("机票订单"),
	REPAYMENT("还款记录");
	
	private String desc;
	
	private SynchronizationType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
