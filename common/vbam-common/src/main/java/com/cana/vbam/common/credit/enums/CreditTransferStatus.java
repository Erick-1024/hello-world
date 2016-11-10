package com.cana.vbam.common.credit.enums;

public enum CreditTransferStatus {

	SUCCESS("成功"),
	HANDING("交易中"),
	FAIL("失败");
	
	private String desc;
	
	private CreditTransferStatus(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
