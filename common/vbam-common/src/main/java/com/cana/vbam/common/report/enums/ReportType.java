package com.cana.vbam.common.report.enums;

public enum ReportType {
	DAILY("日报表"),
	ANNUAL("年报表"),
	COUNT("计数报表");
	
	private String desc;
	
	private ReportType(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
