package com.cana.vbam.common.repayment.rule.enums;

public enum ScopeOfApplication {
	DEFAULT("全部融资客户"),
	PART("部分融资客户");
	
	private String desc;
	
	private ScopeOfApplication(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return desc;
	}

}
