package com.cana.vbam.common.report.enums;


public enum FundBalanceGetState {

	fail("失败"),
	success("成功"),
	;
	
	private String desc;
	
	private FundBalanceGetState(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return desc;
	}
	
}
