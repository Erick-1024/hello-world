package com.cana.vbam.common.repayment.enums;

public enum ActiveRepaymentType {
	
	single_plan("单次计划还款"),
	mutiple_plan("合并计划还款"),
	single_expense("单次费用还款"),
	mutiple_expense("合并费用还款");

	private String desc;
	
	private ActiveRepaymentType(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
}
