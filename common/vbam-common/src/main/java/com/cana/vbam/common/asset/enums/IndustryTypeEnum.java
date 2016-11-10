package com.cana.vbam.common.asset.enums;

/**
 * @author yihong.tang
 * @time 2016.5.18
 */
public enum IndustryTypeEnum {
	
	IT("计算机"),
	COMMERCE("商业贸易"),
	TRAVEL("差旅"),
	LOGISTICS("物流"),
	HUSBANDRY("农林牧渔"), 
	DIGGING("采掘"), 
	CHEMICAL("化工"),
	STEEL("钢铁"),
	NONFERROUS("有色金属"),
	BUILDING("建筑建材"),
	DECORATION("建筑装饰"),
	ELECTRIC("电气设备"),
	MECHANICAL("机械设备"),
	DEFENSE("国防军工"),
	CAR("汽车"),
	ELECTRON("电子"),
	APPLIANCE("家用电器"),
	FOOD("食品饮料"),
	TEXTILE("纺织服装"),
	LIGHT("轻工制造"),
	MEDICAL("医药生物"),
	UTILITY("公用事业"),
	TRAFFIC("交通运输"),
	REALTY("房地产"),
	BANK("银行"),
	NONBANKING("非银金融"),
	LEISURE("休闲服务"),
	MEDIA("传媒"),
	COMMUNICATION("通信"),
	SYNTHESIS("综合"),
	EDUCATION("教育");

	private String desc;

	IndustryTypeEnum(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

}
