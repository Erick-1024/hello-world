package com.cana.wechat.client.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cana.wechat.client.IWeChatClinet;
import com.cana.wechat.common.dto.TokenReponse;
import com.cana.wechat.common.dto.TokenRequest;
import com.cana.wechat.common.util.Constants;
import com.google.gson.Gson;
import com.travelzen.framework.net.http.HttpTookit;

@Component
public class WeChatClinetImpl implements IWeChatClinet {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Gson gson = new Gson();

	/**
	 * access_token是公众号的全局唯一票据,7200秒变化一次
	 * 
	 * @param token
	 * @return
	 */
	public TokenReponse token(TokenRequest token) {
		List<NameValuePair> params = new ArrayList<>();
		params.add(new NameValuePair("grant_type", token.getGrantType()));
		params.add(new NameValuePair("appid", token.getAppid()));
		params.add(new NameValuePair("secret", token.getSecret()));

		logger.info("请求微信access_token接口,地址:{},参数{}.", Constants.WECHAT_TOKEN_URL, params);
		String returnStr = HttpTookit.doGetJson(Constants.WECHAT_TOKEN_URL, params);
		logger.info("请求微信access_token接口返回内容:", returnStr);

		TokenReponse tokenReponse = gson.fromJson(returnStr, TokenReponse.class);
		return tokenReponse;
	}
}
