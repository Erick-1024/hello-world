package com.cana.vbam.common.homsom.enums;

public enum SettlementState {
	
	SETTLED("核销"),
	UNSETTLE("未核销");
	
	private String desc;
	
	private SettlementState(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return desc;
	}
}
