package com.cana.yundaex.common.enums;

public enum StationInfoType {

	/**
	 * 拉取网点数据
	 */
	Y("拉取数据成功"),
	W("拉取数据错误"),
	N("没有拉取数据");
	
	private String desc;
	
	private StationInfoType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
