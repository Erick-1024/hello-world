package com.cana.yundaex.common.enums;

public enum YundaexGrade {

	//二级指标
	controllerOrigin("籍贯"),
	cityLevel("城市等级"),
	city("城市"),
	stationAddress("地址"),
	busiLimit("加盟年限"),
	stationAmount("站点数量"),
	loanType("借款类型"),
	recAndSendGrowthRate("揽派件增长率"),
	yundaexJudge("韵达评价"),
	
	FIRST_TIER("一线城市"),
	SECOND_TIER("二线城市"),
	THIRD_TIER("三线城市"),
	FOUR_TIER("四线城市"),
	
	
	SINGLE("单值类型"),
	INTERVAL("区间类型")
	;
	private String desc;

	private YundaexGrade(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
