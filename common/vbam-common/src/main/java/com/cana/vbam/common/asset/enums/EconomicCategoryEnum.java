package com.cana.vbam.common.asset.enums;

/**
 * @author yihong.tang
 * @time 2016.5.18
 */
public enum EconomicCategoryEnum {
	
	STATE("国有"), 
	COLLECTIVE("集体"), 
	PRIVATE("私营"),
	INDIVIDUAL("个体"),
	POOLING("联营"),
	STOCK("股份制"),
	FOREIGN("外商投资"),
	HMT("港澳台投资"),
	OTHER("其他经济类");

	private String desc;

	EconomicCategoryEnum(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

}
