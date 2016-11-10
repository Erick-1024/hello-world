package com.travelzen.framework.core.dict;

public enum FlightOrderItemMajorStageState {
	not_begin("未被处理", ""), processing("处理中", "highlight-fl"), complete("完成", "highlight"), exception("异常", "highlight-fe");

	private String desc;
	private String cls;

	private FlightOrderItemMajorStageState(String desc, String cls) {
		this.desc = desc;
		this.cls = cls;
	}

	public String getDesc() {
		return desc;
	}

	public String getCls() {
		return cls;
	}
}
