package com.cana.vbam.common.early.warning.enums;

public enum EarlywarningEventState {
	
	add_wait_for_review("新增待审核"),
	effective("生效"),
	add_review_fail("新增审核失败"),
    cancel_wait_for_review("解除待审核"),
    cancel("解除");
	
	private String desc;
	
	private EarlywarningEventState(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
