package com.cana.vbam.common.customer.enums;

public enum FinanceMaterial {
	
	AUDIT_REPORT("审计报告", false),
	BALANCE_SHEET("资产负债表", false),
	INCOME_STATEMENT("利润表", false),
	CASH_FLOW_STATEMENT("现金流量表", false),
	ACCOUNT_BALANCE_SHEET("科目余额表", false),
	TAX_BILL("税单", false),
	OTHER("其他", false),
	;

	private String desc;
	
	private boolean isMust;
	
	private FinanceMaterial(String desc, boolean isMust) {
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
