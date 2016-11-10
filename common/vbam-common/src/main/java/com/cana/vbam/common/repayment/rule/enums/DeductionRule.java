package com.cana.vbam.common.repayment.rule.enums;

public enum DeductionRule {

	ALL("足额后扣款"),
	PART("可部分扣款");
	
	private String desc;
	
	private DeductionRule(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
}
