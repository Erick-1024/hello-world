package com.cana.vbam.common.account.enums;

/**
 * 
 * @author XuMeng
 * 账户监管状态
 */
public enum AccountAccumulationStatus {
	NO_ACCUMULATION("未归集"),
	HAVE_ACCUMULATION("归集"),
    BE_ACCUMULATED("被归集");
	
	private String desc;
	
	private AccountAccumulationStatus(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}

}
