package com.cana.vbam.common.early.warning.enums;

public enum EarlywarningEventAction {
	
	add("新增"),
	cancel("解除");
	
	private String desc;
	
	private EarlywarningEventAction(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
