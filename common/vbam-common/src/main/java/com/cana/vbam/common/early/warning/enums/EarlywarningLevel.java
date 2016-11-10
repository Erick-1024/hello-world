package com.cana.vbam.common.early.warning.enums;

public enum EarlywarningLevel{
	
	// java enum 默认按照出现顺序比较大小，低级别的预警级别一定要写在前面
	
	yellow("黄色预警"),
	orange("橙色预警"),
	red("红色预警");
	
	private String desc;
	
	private EarlywarningLevel(String desc){
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
	public static EarlywarningLevel[] getYundaexEarlyWarningLevel() {
		return new EarlywarningLevel[]{yellow, red};
	}
	
}
