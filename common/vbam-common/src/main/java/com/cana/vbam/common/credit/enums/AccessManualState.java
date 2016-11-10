package com.cana.vbam.common.credit.enums;

public enum AccessManualState {

	WAIT("待审核"),
	ACCESS("已通过"),
	NOTACCESS("未通过"),
	WAIT_APPROVE("待审批");
	
	private String desc;
	
	private AccessManualState(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
