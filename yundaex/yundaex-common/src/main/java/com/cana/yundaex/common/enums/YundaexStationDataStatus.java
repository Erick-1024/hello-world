package com.cana.yundaex.common.enums;

public enum YundaexStationDataStatus {
	SUCCESS("完成"), 
	HANDLING("处理中"),
	FAIL("失败");

	private String desc;

	private YundaexStationDataStatus(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return this.desc;
	}
}
