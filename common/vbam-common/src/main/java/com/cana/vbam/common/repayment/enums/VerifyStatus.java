package com.cana.vbam.common.repayment.enums;

public enum VerifyStatus {

	PASS("通过"),
	NEGATIVE("未通过");
	private String desc;
	
	private VerifyStatus(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return desc;
	}
}
