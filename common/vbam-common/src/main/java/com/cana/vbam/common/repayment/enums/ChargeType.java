package com.cana.vbam.common.repayment.enums;

public enum ChargeType {
	REPAYMENTPLAN("还款计划"),
	REPAYMENTEXPENSE("还款费用");
	
	private String desc;
	
	private ChargeType(String desc) {
		this.desc = desc;
	}
	
	public static ChargeType getValue(String desc){
		if(REPAYMENTPLAN.desc.equals(desc))
			return REPAYMENTPLAN;
		if(REPAYMENTEXPENSE.desc.equals(desc))
			return REPAYMENTEXPENSE;
		return null;
	}
	
	public String desc() {
		return desc;
	}
}
