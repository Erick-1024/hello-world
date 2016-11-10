package com.cana.vbam.common.homsom.enums;

public enum PaymentType {
	
	SETTLEMENT("核销"),
	BUYBACK("回购");
	
	private String desc;
	
	private PaymentType(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return desc;
	}
}
