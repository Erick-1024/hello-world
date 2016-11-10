package com.cana.yundaex.common.enums;

public enum YundaexAccountOwner {

	LEGAL("法人代表"), 
	CONTROLLER("实际控制人"), 
	OTHER("其他"),
	COMPANY("公司"),
	;
	
	private String desc;

	private YundaexAccountOwner(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
}
