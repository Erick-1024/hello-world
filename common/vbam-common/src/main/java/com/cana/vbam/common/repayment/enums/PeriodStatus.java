package com.cana.vbam.common.repayment.enums;


public enum PeriodStatus {
	/** 
	 * 往期逾期 -2 
	 * 往期展期 -1
	 * 当期还款日 0
	 * 当期非还款日 1
	 * 未来期 2
	 */
	OVERDUE("往期逾期"),
	EXTENSION("往期展期"),
	CURRENTIN("当期还款日"),
	CURRENTOUT("当期非还款日"),
	FUTURE("未来期");
	
	private String desc;
	
	private PeriodStatus(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
}
