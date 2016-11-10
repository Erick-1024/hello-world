package com.cana.vbam.common.asset.enums;

public enum LoanState {

	LOANED("已放款"),
	UNLOAN("未放款");

	private String desc;

	private LoanState(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
