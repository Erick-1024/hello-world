package com.cana.vbam.common.credit.enums;

public enum CreditTradeType {

	PAYMENT("支付"),
	REFUND("退款"),
	AGENT_REPAYMENT("账户还款");
	
	private String desc;
	
	private CreditTradeType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
