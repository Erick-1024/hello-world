package com.cana.vbam.common.credit.enums;

public enum CreditLimitUsedStatus {

	in_use("使用中"),
	not_used("未使用");

	private String desc;

	private CreditLimitUsedStatus(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

}
