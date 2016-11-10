package com.cana.yundaex.common.enums;

/**
 * @author hu
 *
 */
public enum ContractSignState {

	SIGNED("已签署"),
	UNSIGNED("未签署");
	
	private String desc;
	
	private ContractSignState(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return desc;
	}
}
