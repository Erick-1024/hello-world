package com.cana.vbam.common.asset.enums;

/**
 * 股东出资方式
 * @author jiangzhou.Ren
 * @time 2016年7月29日下午4:46:43
 */
public enum CustomerStkPayamtTypeEnum {
	
	MONETARYCAPITALCONTRIBUTION("货币出资"),
	CAPITALCONTRIBUTION("现物出资");
	
	private String desc;

	CustomerStkPayamtTypeEnum(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
	
}
