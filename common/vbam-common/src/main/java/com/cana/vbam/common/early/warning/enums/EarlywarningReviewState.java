package com.cana.vbam.common.early.warning.enums;

public enum EarlywarningReviewState {
	
	wait_for_review("待审核"),
	review_pass("已通过"),
	review_fail("未通过");
	
	private String desc;
	
	private EarlywarningReviewState(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
