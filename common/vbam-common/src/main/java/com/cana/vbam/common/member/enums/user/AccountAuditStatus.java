package com.cana.vbam.common.member.enums.user;

public enum AccountAuditStatus {
	
	PENDINGAUDIT("待审核"),
	HAVINGAUDIT("已审核");
	
	private String desc;
	
	private AccountAuditStatus(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
}
