package com.travelzen.framework.core.dict;

public enum RegionType {
	ALL("全部"),
	CNHBR("华北"),
	CNHDR("华东"),
	CNHNR("华南"),
	CNXNR("西南"),
	CNSAR("港澳台"),
	OREGION("海外");
	
	private String  desc;
	private RegionType(String desc){
		this.desc = desc;
	}
	public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
