package com.cana.vbam.common.customer.enums;

public enum EnterpriseInfoType {
	
	BUSINESS_MATERIAL("工商资料"),
	FINANCE_MATERIAL("财务资料"),
	LEGAL_INQUIRE("法务查询"),
	CREDIT_INQUIRE("征信查询"),
	BASIC_TRANSACTION("基础交易"),
	FACTORING_FLOW("保理流程"),
	LEDGER_INQUIRE("台账查询")
	;
	
	private String desc;
	
	private EnterpriseInfoType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
}
