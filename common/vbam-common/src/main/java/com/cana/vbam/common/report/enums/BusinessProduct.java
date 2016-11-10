package com.cana.vbam.common.report.enums;

public enum BusinessProduct {
	travelzen_finance("信旅宝"),
	yundaex_project_id("韵达"),
	vj("VJ"),
	other("其他");
	
	private String desc;
	
	private BusinessProduct(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
