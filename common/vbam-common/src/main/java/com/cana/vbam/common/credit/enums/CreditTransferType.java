package com.cana.vbam.common.credit.enums;

public enum CreditTransferType {

	LOAN("放款"),
	REFUND2FACTOR("退款给资金方"),
	REFUND2CUSTOMER("退款给客户"),
	AGENT_REPAYMENT("账户还款");
	
	private String desc;
	
	private CreditTransferType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
