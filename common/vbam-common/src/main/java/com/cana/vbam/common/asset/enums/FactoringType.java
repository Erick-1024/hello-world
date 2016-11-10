package com.cana.vbam.common.asset.enums;

public enum FactoringType {
	
	PUBLIC("明保理"),
	PRIVATE("暗保理");

	private String desc;

	private FactoringType(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
	
}
