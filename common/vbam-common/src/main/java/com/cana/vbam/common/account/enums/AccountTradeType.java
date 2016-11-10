package com.cana.vbam.common.account.enums;

public enum AccountTradeType {
    CREATE_ACCOUNT("开户"),
    FREEZE("冻结"),
    UNFREEZE("解冻"),
    TRANSFER_FUND("转账"),
    WITHDRAW_FUND("提现");

    private String desc;
    AccountTradeType(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }
    public String desc() {
		return desc;
	}
    
}
