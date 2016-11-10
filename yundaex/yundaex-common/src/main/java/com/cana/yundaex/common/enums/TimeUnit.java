package com.cana.yundaex.common.enums;

import org.apache.commons.lang3.StringUtils;

public enum TimeUnit {

	YEAR("年"),
	MONTH("月"),
	DAY("天");
	
	private String desc;
	
	private TimeUnit(String desc){
		this.desc = desc;
	}
	
	public String desc(){
		return desc;
	}
	
	/**
	 * 根据枚举的enumDesc,获取它的枚举类型
	 * @param enumDesc
	 * @return
	 */
	public static TimeUnit getEnum (String enumDesc){
		if(StringUtils.isBlank(enumDesc))
			return null;
		TimeUnit[] products = TimeUnit.values();
		for(int i =0 ;i<products.length;i++){
			if(products[i].desc().equals(enumDesc))
				return products[i];
		}
		//没有匹配的desc
		return null;
	}
}
