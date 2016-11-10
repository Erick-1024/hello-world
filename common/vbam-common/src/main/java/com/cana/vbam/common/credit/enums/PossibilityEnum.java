package com.cana.vbam.common.credit.enums;

public enum PossibilityEnum {

	SURE("肯定"),
	MAYBE("可能"),
	NOT("肯定不");
	
	private String desc;
	
	private PossibilityEnum(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
	/**
	 * 判断是否存在该枚举
	 */
	public static boolean checkElememt(String element){
		PossibilityEnum[] aType=PossibilityEnum.values();
		for(PossibilityEnum ele:aType){
			if(ele.name().equals(element))
				return true;
		}
		return false;
	}
	
}
