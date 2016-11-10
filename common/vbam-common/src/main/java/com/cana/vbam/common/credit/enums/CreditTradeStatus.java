package com.cana.vbam.common.credit.enums;

public enum CreditTradeStatus {

	SUCCESS("成功"),
	HANDING("交易中"),
	FAIL("失败");
	
	private String desc;
	
	private CreditTradeStatus(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
