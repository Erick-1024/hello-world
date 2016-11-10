package com.cana.vbam.common.report.enums;

public enum ReportMonitorMetricType {
	COUNTER_GUARANTEE_RATE("质押反担保覆盖率"),
	SALES_CHANGE_RATE("销售变化率"),
	SALES_REPAYMENT_RATE("销售回款率");
	
	private String desc;
	
	private ReportMonitorMetricType(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
