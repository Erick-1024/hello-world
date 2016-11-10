package com.cana.vbam.common.homsom.enums;

public enum SettlementTrackState {
	
	SETTLED("已核销"),
	BUYBACKED("已回购");
	
	private String desc;
	
	private SettlementTrackState(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return desc;
	}
}
