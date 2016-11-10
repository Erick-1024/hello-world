package com.cana.vbam.common.repayment.enums;

public enum DateUnit {
	YEAR("年"),
	MONTH("月"),
	DAY("日");
	
	private String desc;
	
	private DateUnit(String desc) {
		this.desc = desc;
	}
	
	public static DateUnit getValue(String desc){
		if(YEAR.desc.equals(desc))
			return YEAR;
		if(MONTH.desc.equals(desc))
			return MONTH;
		if(DAY.desc.equals(desc))
			return DAY;
		return null;
	}
	
	public String desc() {
		return desc;
	}
}
