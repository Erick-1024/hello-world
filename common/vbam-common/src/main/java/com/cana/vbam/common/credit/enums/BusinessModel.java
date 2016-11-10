package com.cana.vbam.common.credit.enums;

public enum BusinessModel {

	SELF("自营"),
	TITULAR("挂靠");
	
	private String desc;
	
	private BusinessModel(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
	
}
