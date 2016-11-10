package com.cana.vbam.common.repayment.enums;

public enum ChargeStandard {
	FINANCEAMOUNT("融资金额"), 
	FINANCEBALANCE("融资余额"),
	OTHERSTANDARD("其他");
	
	private String desc;
	
	private ChargeStandard(String desc){
		this.desc = desc;
	}

	public static ChargeStandard getValue(String desc) {
		if(FINANCEAMOUNT.desc.equals(desc))
			return FINANCEAMOUNT;
		if(FINANCEBALANCE.desc.equals(desc))
			return FINANCEBALANCE;
		if(OTHERSTANDARD.desc.equals(desc))
			return OTHERSTANDARD;
		return null;
	}
	
	public String desc(){
		return desc;
	}

}
