/**
 *  Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.account.enums;

/**
 * @author ducer
 *
 */
public enum AccountTradeStatus {

	BANKAPI_REQUEST("银行接口请求中"),
	TRADE_HANDLING("交易中"),
	TRADE_SUCCESS("交易成功"),
	TRADE_FAIL("交易失败"),
	;
	
	private String desc;
	AccountTradeStatus(String desc){
		this.desc = desc;
	}
	public String getDesc() {
		return desc;
	}
}
