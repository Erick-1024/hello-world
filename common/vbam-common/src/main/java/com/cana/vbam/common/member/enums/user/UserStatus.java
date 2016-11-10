package com.cana.vbam.common.member.enums.user;

public enum UserStatus {
	REJECTED("审核未通过"),
	PENDINGAUDIT("待审核"),
	PENDINGACTIVATE("未激活"),
	ACTIVATED("已激活"),
	DELETED("已删除");
	
	private String desc;
	
	private UserStatus(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}

}
