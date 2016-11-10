package com.cana.yundaex.common.enums;

/**
 * @author hu
 *
 */
public enum ContractCompleteState {

	COMPLETE("已完成"),
	UNCOMPLETE("未完成");
	
	private String desc;
	
	private ContractCompleteState(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return this.desc;
	}
}
