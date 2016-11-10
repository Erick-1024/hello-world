package com.cana.vbam.common.enums;

public enum IndustryType {
	I_010("银行"),
	I_020("非银金融"),
	I_030("计算机"),
	I_040("差旅"),
	I_050("物流"),
	I_060("交通运输"),
	I_070("教育"),
	I_080("汽车"),
	I_090("电子"),
	I_100("医药生物"),
	I_110("食品饮料"),
	I_120("纺织服装"),
	I_130("家用电器"),
	I_140("房地产"),
	I_150("建筑装饰"),
	I_160("传媒"),
	I_170("通信"),
	I_180("化工"),
	I_190("其他");
	
	private String desc;
	
	private IndustryType(String desc) {
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}

}
