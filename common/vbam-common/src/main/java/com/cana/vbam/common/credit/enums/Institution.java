package com.cana.vbam.common.credit.enums;

public enum Institution {

	travelzen("真旅网"),
	vj("VJ"),
	yd("yd"),
	cana("CANA");
	
	private String desc;
	
	private Institution(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
