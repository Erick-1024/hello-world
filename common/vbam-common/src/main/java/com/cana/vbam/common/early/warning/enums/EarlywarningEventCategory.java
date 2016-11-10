package com.cana.vbam.common.early.warning.enums;

public enum EarlywarningEventCategory {
	
	COURT_EXECUTION_COMPANY("被法院执行（企业）"),
	COURT_EXECUTION_INDIVIDUAL("被法院执行（个人）"),
	NEGATIVE_REPORT("负面信息"),
    OTHER("其它"),
    SYSTEM("系统");
	
	private String desc;
	
	private EarlywarningEventCategory(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
}
