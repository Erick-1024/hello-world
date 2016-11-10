package com.cana.vbam.common.asset.enums;

public enum ReceiptType {

	INVOICE("发票"),
	FLIGHT_TICKET("机票"),
	ORDER("订单");

	private String desc;

	private ReceiptType(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
	
}
