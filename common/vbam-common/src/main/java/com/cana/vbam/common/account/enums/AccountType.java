package com.cana.vbam.common.account.enums;

/**
 *
 * @since Nov 12, 20154:56:59 PM
 * @author dev1
 *
 */
public enum AccountType {
	GENERAL("一般账户"),
	SPECIAL("专用账户");
	
	private String desc;
	
	private AccountType(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
	public String getDesc() {
		return desc;
	}

}
