package com.cana.flight.finance.common.enums;

public enum DepartureStatus {

	ALL_NOT_TAKE_OFF("全部未起飞"),
	PART_TAKE_OFF("部分起飞"),
	ALL_TAKE_OFF("全部起飞");
	
	private String desc;
	
	private DepartureStatus(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
