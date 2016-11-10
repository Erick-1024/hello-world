package com.cana.vbam.common.customer.enums;

public enum LegalInquire {

	LITIGATION_INQUIRY("诉讼查询", false),
	OTHER("其他", false),
	;

	private String desc;
	
	private boolean isMust;
	
	private LegalInquire(String desc, boolean isMust) {
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
