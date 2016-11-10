package com.cana.vbam.common.marketing.activity.enums;

/**
 * 营销活动类型
 * @author renshui
 *
 */
public enum MarketingActivityType {

	INTEREST_RATE_DISCOUNT("利率折扣");
	
	private String desc;
	
	private MarketingActivityType(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
}
