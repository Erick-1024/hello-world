package com.cana.vbam.common.member.enums.user;

public enum AccountActivateStatus {
	
	UNACTIVATE("未激活"),
	ACTIVATED("激活");
	
	private String desc;
	
	private AccountActivateStatus(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
}
