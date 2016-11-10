package com.travelzen.framework.core.dict;

public enum GovernmentPayType{
	governmeng_card("公务卡"),
	purchaser_card("采购卡");

	private String desc;

	private GovernmentPayType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}