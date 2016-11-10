package com.cana.vbam.common.asset.enums;

import org.apache.commons.lang3.StringUtils;

public enum BusinessProduct {

	DP_RECOURSE_FACTORING("国内公开-有追索保理"),
	DH_RECOURSE_FACTORING("国内隐蔽-有追索保理"),
	DP_NON_RECOURSE_FACTORING("国内公开-无追索保理"),
	DH_NON_RECOURSE_FACTORING("国内隐蔽-无追索保理");

	private String desc;

	private BusinessProduct(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}
	
	/**
	 * 根据枚举的enumDesc,获取它的枚举类型
	 * @param enumDesc
	 * @return
	 */
	public static BusinessProduct getEnum (String enumDesc){
		if(StringUtils.isBlank(enumDesc))
			return null;
		BusinessProduct[] products = BusinessProduct.values();
		for(int i =0 ;i<products.length;i++){
			if(products[i].desc().equals(enumDesc))
				return products[i];
		}
		//没有匹配的desc
		return null;
	}
}
