package com.cana.vbam.common.vj.enums;

public enum TranType {
	ACTIVE_REPAYMENT("主动还款"),
	DEDUCT("自动扣款"),
	CONFIRM_LOAN("确定放款"),
	PAY("打款");
	
	private String desc;
	
	private TranType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}

}
