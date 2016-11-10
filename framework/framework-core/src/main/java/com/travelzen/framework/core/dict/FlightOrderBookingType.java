/**
 * 机票预订方式
 * Description:
 * Copyright (c) 2013
 * Company:真旅网
 * @author renshui
 * @version 1.0
 * @date 2013-6-28
 */
package com.travelzen.framework.core.dict;


public enum FlightOrderBookingType {
	pnr_import("后台黑屏导入"),
	white_screen("白屏预订"),
	enquiry("询价单"),
	extend_config("外借配置订单导入"),
    distributor("分销平台订单导入"),
    recording("订单补录"),
    order_sync("订单同步");

	private String desc;

	private FlightOrderBookingType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public static FlightOrderBookingType[] getProviderFlightOrderBookingTypes() {
	    FlightOrderBookingType[] types = new FlightOrderBookingType[2];
	    types[0] = FlightOrderBookingType.pnr_import;
	    types[1] = FlightOrderBookingType.white_screen;
		return types;
	}
}
