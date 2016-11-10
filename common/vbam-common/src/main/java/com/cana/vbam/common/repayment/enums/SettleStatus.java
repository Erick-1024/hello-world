package com.cana.vbam.common.repayment.enums;

public enum SettleStatus {
	
	UNSETTLE("未结清"),
	SETTLED("已结清");
	
	private String desc;
	
	private SettleStatus(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
	public static SettleStatus getValue(String desc){
		if(SETTLED.desc.equals(desc))
			return SETTLED;
		if(UNSETTLE.desc.equals(desc))
			return UNSETTLE;
		return null;
	}
	
	
	public static SettleStatus getDesc(String value){
		if(SETTLED.name().equals(value))
			return SETTLED;
		if(UNSETTLE.name().equals(value))
			return UNSETTLE;
		return null;
	}
}
