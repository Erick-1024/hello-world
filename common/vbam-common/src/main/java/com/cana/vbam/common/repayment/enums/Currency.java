package com.cana.vbam.common.repayment.enums;

public enum Currency {
	RMB("人民币");
	
	private String desc;
	
	private Currency(String desc){
		this.desc = desc;
	} 
	
	public static Currency getValue(String desc){
		if(RMB.desc.equals(desc))
			return RMB;
		return null;
	}
	
	public String desc() {
		return desc;
	}
}
