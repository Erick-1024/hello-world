package com.cana.yundaex.common.enums;

public enum YundaexStationAddress {

	DOWNTOWN("市区"),
	MARKET("集镇"),
	COUNTRYSIDE("村镇");
	
	private String desc;

	private YundaexStationAddress(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
