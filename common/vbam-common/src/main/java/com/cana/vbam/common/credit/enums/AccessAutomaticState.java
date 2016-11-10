package com.cana.vbam.common.credit.enums;

public enum AccessAutomaticState {

	WAIT("待审核"),
	ACCESS("已通过"),
	NOTACCESS("未通过");
	
	private String desc;
	
	private AccessAutomaticState(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
