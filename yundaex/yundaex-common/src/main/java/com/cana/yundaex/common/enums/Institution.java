package com.cana.yundaex.common.enums;

public enum Institution {

	yundaex("韵达"),
	cana("CANA");
	
	private String desc;
	
	private Institution(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
