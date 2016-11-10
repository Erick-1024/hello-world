package com.cana.vbam.common.credit.enums;

public enum EnterpriseQualifications {

	DOMESTIC("国内"),
	INTERNATIONAL("国际"),
	OTHER("其他");
	
	private String desc;
	
	private EnterpriseQualifications(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
	/**
	 * 判断是否存在该枚举
	 */
	public static boolean checkElememt(String element){
		EnterpriseQualifications[] aType=EnterpriseQualifications.values();
		for(EnterpriseQualifications ele:aType){
			if(ele.name().equals(element))
				return true;
		}
		return false;
	}
	
}
