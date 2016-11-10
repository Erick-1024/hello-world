package com.cana.vbam.common.enums;

public enum Marriage {
	married("已婚"),
	unmarried("未婚");
	
	private String desc;
	
	private Marriage(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}

}
