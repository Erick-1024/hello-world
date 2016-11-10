package com.cana.yundaex.common.enums;

/**
 * @author hu
 *
 */
public enum PersonalInfoType {

	ACCOUNT_HOLDER("开户人"),
	CONTROLLER("实际控制人"),
	ACCOUNT_HOLDER_AND_CONTROLLER("");
	
	private String desc;
	
	private PersonalInfoType(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return this.desc;
	}
}
