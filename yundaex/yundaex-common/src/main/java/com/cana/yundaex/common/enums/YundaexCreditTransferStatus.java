package com.cana.yundaex.common.enums;

public enum YundaexCreditTransferStatus {

	SUCCESS("成功"),
	HANDING("交易中"),
	FAIL("失败");
	
	private String desc;
	
	private YundaexCreditTransferStatus(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
