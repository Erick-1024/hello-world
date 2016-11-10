package com.cana.vbam.common.vj.enums;

public enum DepositState {
	normal("正常"),
	archive("封存");
	
	private String desc;
	
	private DepositState(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}

}
