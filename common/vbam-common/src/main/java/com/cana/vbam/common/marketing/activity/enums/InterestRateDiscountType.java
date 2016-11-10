package com.cana.vbam.common.marketing.activity.enums;

public enum InterestRateDiscountType {

	RATIO("比率折扣"),
	AMOUNT("金额折扣");
	
	private String desc;
	
	private InterestRateDiscountType(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
}
