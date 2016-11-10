package com.cana.vbam.common.vj.enums;

public enum VJArea {
	beijing("北京", "43.5", "16"),
	shanghai("上海", "45.5", "14"),
	guangzhou("广州", "34.1", "14"),
	shenzhen("深圳", "34.6", "14");
	
	private String name;
	private String socialSecurityRatio;
	private String housingFundRatio;
	
	private VJArea(String name, String socialSecurityRatio, String housingFundRatio) {
		this.name = name;
		this.socialSecurityRatio = socialSecurityRatio;
		this.housingFundRatio = housingFundRatio;
	}

	public String getName() {
		return name;
	}

	public String getSocialSecurityRatio() {
		return socialSecurityRatio;
	}

	public String getHousingFundRatio() {
		return housingFundRatio;
	}
	

}
