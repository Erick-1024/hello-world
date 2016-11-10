package com.cana.vbam.common.account.enums;

/**
 * 
 * @author XuMeng
 * 账户监管状态
 */
public enum AccountSupervisionStatus {
	NO_SUPERVISION("未监管"),
	HAVE_SUPERVISION("监管");
	
	private String desc;
	
	private AccountSupervisionStatus(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
	public String getDesc() {
		return desc;
	}

}
