package com.cana.vbam.common.enums;

public enum Education {
	E_040("高中及以下"),
	E_050("大专"),
	E_060("本科"),
	E_070("研究生"),
	E_080("博士及以上");
	
	private String desc;
	
	private Education(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}

}
