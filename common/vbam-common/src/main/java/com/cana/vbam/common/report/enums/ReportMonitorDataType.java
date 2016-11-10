package com.cana.vbam.common.report.enums;

public enum ReportMonitorDataType {
	QUALIFIED_AR("合格AR余额"),
	TOTAL_SALES("销售总金额"),
	REPAYMENT_SALES("回款总金额"),
	TICKET_TAKE_OFF_SALE("机票已起飞金额"),
	USED_LIMIT_THE_DAY("当日已使用额度")
	;
	
	private String desc;
	
	private ReportMonitorDataType(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
