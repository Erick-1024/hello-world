package com.cana.vbam.common.credit.enums;

public enum CompanyNature {

	SOE("国有"),
	COOPERATIVE("合作"),
	JOINT("合资"),
	SOLE("独资"),
	COLLECTIVE("集体"),
	PRIAVTE("私营"),
	INDIVIDUAL("个体工商户"),
	CUSTOMS("报关"),
	OTHER("其他");
	
	private String desc;
	
	private CompanyNature(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
	
}
