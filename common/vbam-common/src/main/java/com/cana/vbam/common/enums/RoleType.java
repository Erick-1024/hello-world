package com.cana.vbam.common.enums;

public enum RoleType {
	LEVEL1("一级角色"),
	LEVEL2("二级角色");
	
	private String desc;
	
	private RoleType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}

}
