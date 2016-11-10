package com.cana.vbam.common.credit.enums;

public enum AccessRuleFitObject {

	WHITE_CUSTOMER("白名单客户"),
	NON_WHITE_CUSTOMER("非白名单客户");
	
	private String desc;
	
	private AccessRuleFitObject(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
