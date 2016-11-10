package com.cana.yundaex.common.enums;

public enum YundaexInspectionRecord {

	qualified("合格"),
	unqualified("不合格");
	
	private String desc;

	private YundaexInspectionRecord(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
