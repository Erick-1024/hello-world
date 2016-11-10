package com.cana.vbam.common.customer.enums;

public enum LedgerInquire {
	
	LEDGER("台账", false),
	OTHER("其他", false),
	;
	private String desc;
	
	private boolean isMust;
	
	private LedgerInquire(String desc, boolean isMust) {
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
