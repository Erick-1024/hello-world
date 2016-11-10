/**
 *  Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.account.enums;

/**
 * @author ducer
 *
 */
public enum AccountTradeApplyStatus {

    PENDINGAUDIT("待审核"),
    ACCEPTED("已通过"),
    REJECTED("未通过")
	;
	
	private String desc;
	AccountTradeApplyStatus(String desc){
		this.desc = desc;
	}
	public String getDesc() {
		return desc;
	}
}
