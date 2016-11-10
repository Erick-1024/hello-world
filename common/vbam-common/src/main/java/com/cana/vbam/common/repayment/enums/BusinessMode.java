package com.cana.vbam.common.repayment.enums;

public enum BusinessMode {
	FACTORANDFINACE("保理商+融资商"),
	FACTORANDFINACEANDBUYER("保理商+融资商+买方企业");
	
	private String desc;
	
	private BusinessMode(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}

}
