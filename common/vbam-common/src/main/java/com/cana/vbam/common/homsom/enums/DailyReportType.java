package com.cana.vbam.common.homsom.enums;

/**
 * 日报表资金转移明细表类型
 * @author XuMeng
 *
 */
public enum DailyReportType {

	LOAN("放款"),
	REFUND("退款"),
	;
	
	
	private String desc;
	
	private DailyReportType(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return desc;
	}
}
