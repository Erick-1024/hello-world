package com.cana.vbam.common.vj.enums;

public enum ApplyState {
	PASS("通过"),
	FAIL("失败");
	
	private String desc;
	
	private ApplyState(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}

}
