package com.cana.vbam.common.credit.enums;

public enum DownstreamCustomerType {

	ENTERPRISE("企业"),
	INDIVIDUAL("个人"),
	BOTH("两者都有");
	
	private String desc;
	
	private DownstreamCustomerType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
	
}
