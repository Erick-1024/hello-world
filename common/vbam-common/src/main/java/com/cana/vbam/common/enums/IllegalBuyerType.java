package com.cana.vbam.common.enums;

public enum IllegalBuyerType {

	SAME("相同买家名称"),
	EXISTED("该买家名称已经存在");
	
	private String desc;
	
	private IllegalBuyerType(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
