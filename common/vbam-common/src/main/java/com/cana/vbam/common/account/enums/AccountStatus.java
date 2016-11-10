package com.cana.vbam.common.account.enums;

/**
 *
 * @since Nov 12, 20154:57:03 PM
 * @author dev1
 * 账户状态
 */
public enum AccountStatus {
	NORMAL("正常"),
	FROZEN("冻结"),
	HANDLING("审核中");
	
	private String desc;
	
	private AccountStatus(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}

}
