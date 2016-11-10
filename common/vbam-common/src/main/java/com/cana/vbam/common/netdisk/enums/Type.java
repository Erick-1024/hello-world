package com.cana.vbam.common.netdisk.enums;

public enum Type {
	FILE("文件"),
	DIRECTORY("目录");
	
	private String desc;
	
	private Type(String desc){
		this.desc = desc;
	} 
	
	public String desc() {
		return desc;
	}
}
