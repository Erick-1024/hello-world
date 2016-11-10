package com.cana.vbam.common.credit.enums;

public enum SameIndustryPlaceOnLocal {

	TOP3("前三名"),
	ABOVEAVERAGE("中等偏上"),
	AVERAGE("中等"),
	BELOWAVERAGE("中等偏下"),
	WEAKNESS("较弱势");
	
	private String desc;
	
	private SameIndustryPlaceOnLocal(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
	
}
