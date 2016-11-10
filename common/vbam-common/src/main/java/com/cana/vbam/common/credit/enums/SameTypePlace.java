package com.cana.vbam.common.credit.enums;

public enum SameTypePlace {

	MAJOR("主要代理人"),
	GENERAL("一般代理人"),
	ACTING("临时代理人");
	
	private String desc;
	
	private SameTypePlace(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
