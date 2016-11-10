package com.travelzen.framework.core.dict;

public enum RejectReason {
	The_ticket_shall_not_refund("该票不得退票"),
	Has_expired_No_refund("已超过有效期，不得退票"),
	The_ticket_has_been_used_No_refund("该票已使用，不得退票"),
	Refund_to_prove_incomplete_or_unclear("退票证明不全或不清楚"),
	Refundable_amount_is_negative("可退金额为负"),
	State_wrong_Should_be_changed_into_the_OPEN_FOR_USE("状态不对，需改为OPEN FOR USE"),
	Suspected_delay_aircraft_cost_Please_restore_number("疑似误机费，请恢复编号"),
	Other("其他");
	
	private String desc;

	private RejectReason(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}
