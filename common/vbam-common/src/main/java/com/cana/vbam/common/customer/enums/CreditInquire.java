package com.cana.vbam.common.customer.enums;

public enum CreditInquire {
	
	LOAN_CARDS_AND_QUERY_RESULTS("贷款卡及查询结果", false),
	CREDIT_REPORT("征信报告", false),
	BORROWING_SCHEDULE("借款明细", false),
	OTHER("其他", false),
	;
	private String desc;
	
	private boolean isMust;
	
	private CreditInquire(String desc, boolean isMust) {
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
