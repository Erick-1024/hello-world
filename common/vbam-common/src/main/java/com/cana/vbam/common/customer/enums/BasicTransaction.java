package com.cana.vbam.common.customer.enums;

public enum BasicTransaction {

	BASIC_TRANSACTION_CONTRACT("基础交易合同", true),
	ACCEPTED_CONTRACT("中标合同", false),
	CONSTRUCTION_CONFIRMATION("施工确认书", false),
	COMPLETION_CONFIRMATION("竣工确认书", false),
	RECEIPT("收货单", false),
	INVOICE("发票", false),
	BANK_STATEMENTS("银行流水", false),
	OTHER("其他", false),
	;
	private String desc;
	
	private boolean isMust;
	
	private BasicTransaction(String desc, boolean isMust) {
		this.desc = desc;
		this.isMust = isMust;
	}
	
	public String desc() {
		return desc;
	}
	
	public boolean isMust() {
		return isMust;
	}
}
