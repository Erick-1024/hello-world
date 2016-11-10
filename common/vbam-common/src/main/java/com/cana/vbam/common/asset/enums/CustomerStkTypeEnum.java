package com.cana.vbam.common.asset.enums;

/**
 * 股东类型枚举
 * @author jiangzhou.Ren
 * @time 2016年7月29日下午3:57:27
 */
public enum CustomerStkTypeEnum {
	
	NATURALPERSON("自然人"),
	ENTERPRISE("企业");
	
	private String desc;
	
	 CustomerStkTypeEnum(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
	
}
