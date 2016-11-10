package com.cana.vbam.common.netdisk.enums;

public enum Module {
	ASSET_ARCHIVES("资产证券化-档案管理");
	
	private String desc;
	
	private Module(String desc){
		this.desc = desc;
	} 
	
	public String desc() {
		return desc;
	}
}
