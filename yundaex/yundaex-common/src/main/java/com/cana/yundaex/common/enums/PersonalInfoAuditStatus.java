package com.cana.yundaex.common.enums;

/**
 * @author hu
 *
 */
public enum PersonalInfoAuditStatus {

	PENDINGAUDIT("待审核"),
	PASSED("已通过"),
	NOTPASSED("未通过");
	
	String desc;
	
	private PersonalInfoAuditStatus(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return this.desc;
	}
	
}
