package com.cana.vbam.common.account.enums;

/**
 * 
 * @author XuMeng
 * 账户监管申请类型
 */
public enum AccountTradeApplyType {

    CREATE_SUPERVISION("新建监管"),
    REMOVE_SUPERVISION("解除监管"),
    TRANSFER_FUND("转账"),
    WITHDRAW_FUND("提现")
    ;
	
	private String desc;
	
	private AccountTradeApplyType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}

}
