package com.cana.vbam.common.asset.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 币种枚举
 * @author jiangzhou.Ren
 * @time 2016年8月4日上午11:13:21
 */
public enum CreditCurrencyType {
	
	RMB("人民币");

	private String desc;

	public String desc() {
		return desc;
	}

	private CreditCurrencyType(String desc) {
		this.desc = desc;
	}

	/**
	 * 根据枚举的enumDesc,获取它的枚举类型
	 * @param enumDesc
	 * @return
	 */
	public static CreditCurrencyType getEnum (String enumDesc){
		if(StringUtils.isBlank(enumDesc))
			return null;
		CreditCurrencyType[] products = CreditCurrencyType.values();
		for(int i =0 ;i<products.length;i++){
			if(products[i].name().equals(enumDesc))
				return products[i];
		}
		//没有匹配的desc
		return null;
	}
	
}
