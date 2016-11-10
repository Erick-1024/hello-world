package com.cana.yundaex.common.enums;

public enum YundaexAuditState {

	WAIT("待审核"),
	ACCESS("已通过"),
	NOTACCESS("未通过");
	
	private String desc;
	
	private YundaexAuditState(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
