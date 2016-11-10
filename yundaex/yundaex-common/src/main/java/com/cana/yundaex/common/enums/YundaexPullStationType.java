package com.cana.yundaex.common.enums;

public enum YundaexPullStationType {
	PULL("拉取网点数据"), 
	SYN("同步网点数据");

	private String desc;

	private YundaexPullStationType(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return this.desc;
	}
}
