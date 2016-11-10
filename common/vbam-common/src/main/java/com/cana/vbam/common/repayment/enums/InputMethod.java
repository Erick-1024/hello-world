package com.cana.vbam.common.repayment.enums;

public enum InputMethod {
	EXCEL("excel导入"),
	MANUAL("手动输入"),
	AUTO("自动生成");
	
	private String desc;
	
	private InputMethod(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}

}
