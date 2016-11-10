package com.cana.yundaex.common.enums;

public enum YundaexCreditLimitGenerateState {

	WAIT("待生成"),
	FINISH("已生成"),
	NEGATIVE("负值额度"),
	UNFINISH("未通过");
	
	private String desc;
	
	private YundaexCreditLimitGenerateState(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
}
