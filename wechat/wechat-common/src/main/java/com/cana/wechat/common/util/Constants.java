package com.cana.wechat.common.util;

import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;

public class Constants {
	// 获取token接口url
	public static final String WECHAT_TOKEN_URL = TopsConfReader.getConfContent("properties/wechat-url.properties", "wechat.token.url.prefix", ConfScope.R);

	
	
	
	
	// 获取微信公众号appid
	public static final String WECHAT_APPID_PARAM = TopsConfReader.getConfContent("properties/wechat.properties", "wechat.appid.prefix", ConfScope.R);

	// 微信公众号secret
	public static final String WECHAT_SECRET_PARAM = TopsConfReader.getConfContent("properties/wechat.properties", "wechat.secret.prefix", ConfScope.R);
}
