package com.cana.yundaex.common.enums;

public enum YundaexApplyType {

	INTERFACE_APPLY("接口申请"),
	OFFLINE_APPLY("线下申请");
	
	private String desc;
	
	private YundaexApplyType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
}
