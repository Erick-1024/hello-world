package com.cana.vbam.common.repayment.enums;

public enum ChargeMethod {

	RATIO("比例"),
	AMOUNT("定额");
	
	private String desc;
	
	private ChargeMethod(String desc) {
		this.desc = desc;
	}
	
	public static ChargeMethod getValue(String desc){
		if(RATIO.desc.equals(desc))
			return RATIO;
		if(AMOUNT.desc.equals(desc))
			return AMOUNT;
		return null;
	}
	
	public static ChargeMethod getDesc(String value){
		if(RATIO.name().equals(value))
			return RATIO;
		if(AMOUNT.name().equals(value))
			return AMOUNT;
		return null;
	}
	
	public String desc(){
		return desc;
	}
}
