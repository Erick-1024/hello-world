package com.cana.vbam.common.member.enums.user;

public enum AccountAuditResult {
	
	PASSAUDIT("通过"),
	REJECTED("未通过");
	
	private String desc;
	
	private AccountAuditResult(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
}
