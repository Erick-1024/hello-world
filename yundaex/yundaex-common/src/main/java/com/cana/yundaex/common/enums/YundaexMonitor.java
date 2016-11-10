package com.cana.yundaex.common.enums;

public enum YundaexMonitor {

	YUNDAEXJUDGE("韵达评价"),  		// 1(bad)  2(fine)  3(excellent)
	RECANDSEND_GROWTHRATE("揽派件增长率"),
	DAY_REQUIREMENTS("日资金需求"),
	YUNDAEXGRADE("韵达评分"),
	BAILBALANCE("保证金余额"),
	SHORTLOAN("短期借款"),
	NET_CASHFLOW("净现金流"),
	CREDIT_LIMIT("最大授信金额"),
	OVERDUES("逾期次数");
	
    private String desc;
	
	private YundaexMonitor(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
}
