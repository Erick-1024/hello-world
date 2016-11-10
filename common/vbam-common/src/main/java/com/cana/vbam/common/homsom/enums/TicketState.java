package com.cana.vbam.common.homsom.enums;

/**
 * @author renshui
 *
 */
public enum TicketState {

	INIT(""),
	DISCARD("作废"),
	HANDLED("已处理");
	
	
	private String desc;
	
	private TicketState(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return desc;
	}
}
