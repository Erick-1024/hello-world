package com.cana.flight.finance.common.enums;

public enum DocumentType {

	NI("身份证"),
	PP("护照"),
    ID("其他证件");
	
	private String desc;
	
	private DocumentType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
