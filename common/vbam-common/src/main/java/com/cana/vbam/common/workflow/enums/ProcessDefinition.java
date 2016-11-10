package com.cana.vbam.common.workflow.enums;

public enum ProcessDefinition {
	AccountRequest("accountRequest");
	
	private String desc;
	
	private ProcessDefinition(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
}
