package com.cana.vbam.common.asset.enums;

/**
 * 城市枚举类
 * @author jiangzhou.Ren
 * @time 2016年8月3日上午9:20:09
 */
public enum CustomerCityTypeEnum {
	
	
	
	BEIJING("北京"),
	SHANGHAI("上海"),
	TIANJIN("天津"),
	CHONGQING("重庆"),
	ZHEJIANG("浙江"),
	JIANGSU("江苏"),
	GUANGZHOU("广东"),
	FUJIAN("福建"),
	HUNAN("湖南"),
	HUBEI("湖北"),
	LIAONING("辽宁"),
	JILIN("吉林"),
	HEILONGJIANG("黑龙江"),
	HENAN("河南"),
	HEBEI("河北"),
	SHANDONG("山东"),
	SHAANXI("陕西"),
	GANSU("甘肃"),
	XINJIANG("新疆"),
	QINGHAI("青海"),
	SHANXI("山西"),
	SISHUAN("四川"),
	GUIZHOU("贵州"),
	ANHUI("安徽"),
	HAINAN("海南"),
	JIANGXI("江西"),
	YUNNAN("云南"),
	NEIMENGU("内蒙古"),
	XIZANG("西藏"),
	GUANGXI("广西"),
	NINXIA("宁夏"),
	XIANGGANG("香港"),
	AOMENG("澳门"),
	TAIWAN("台湾");
	
	
	private String desc;

	public String desc() {
		return desc;
	}

	CustomerCityTypeEnum(String desc) {
		this.desc = desc;
	}
	

}
