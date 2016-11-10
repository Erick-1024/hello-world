package com.cana.vbam.common.homsom.enums;

/**
 * @author hu
 *
 */
public enum OrderType {

	BOOK("预定"),
	REFUND("退票"),
	ENDORSE("改签");
	
	
	private String desc;
	
	private OrderType(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return desc;
	}
}
