package com.cana.vbam.common.credit.enums;

public enum AccountPeriodType {
	
	_1_30("1-30天"),
	_31_60("31-60天"),
	_61_90("61-90天"),
	_90("90天以上");
	
	private String desc;
	
	private AccountPeriodType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
