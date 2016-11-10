/**
 * 机票预订使用的系统
 * Description: 
 * Copyright (c) 2013
 * Company:真旅网
 * @author renshui
 * @version 1.0
 * @date 2013-6-28
 */
package com.travelzen.framework.core.dict;

public enum FlightOrderBookingSystem {
	
	purchaser("采购商平台"),
	operator("运营商"),
	external_api("外部接口"),
	internal_api("内部接口"),
	lowerPurchaser("下级采购商"),
	b2g("企业差旅"),
	purchaser_app("采购商APP"),
	purchaser_weixin("采购商微信"),
    api("接口"),
	;
	
	private String desc;
	
	private FlightOrderBookingSystem(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}
