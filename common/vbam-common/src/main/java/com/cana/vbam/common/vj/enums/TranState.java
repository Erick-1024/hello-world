package com.cana.vbam.common.vj.enums;

public enum TranState {
	SUCCESS("成功"),
	FAIL("失败"),
	UNKNOWN("未知");
	
	private String desc;
	
	private TranState(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}

}
