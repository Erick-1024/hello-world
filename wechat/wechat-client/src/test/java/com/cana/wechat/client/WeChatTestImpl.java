package com.cana.wechat.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.junit.Test;

import com.cana.wechat.common.dto.UserTest;
import com.google.gson.Gson;
import com.travelzen.framework.net.http.HttpTookit;

public class WeChatTestImpl {

	private static String accessToken = "LHEtOv0dxgNrRxUpvAV3MD0_c3Ku8tveqNe9NraoTdbYCBpU1H7wWH9j7WSqlJpndTnMvGGAcvX4tIXotIU8ufrhGagtNOnxOpwo7YjdvmUZAGgAIAFGM";

	/**
	 * access_token是公众号的全局唯一票据,7200秒变化一次
	 */
	@Test
	public void testToken() {
		String url = "https://api.weixin.qq.com/cgi-bin/token";
		List<NameValuePair> params = new ArrayList<>();
		params.add(new NameValuePair("grant_type", "client_credential"));
		params.add(new NameValuePair("appid", "wx2a084e47b96bc617"));
		params.add(new NameValuePair("secret", "225b1b5e9cb03511c4c90882d67f5463"));
		String returnStr = HttpTookit.doGetJson(url, params);
		// accessToken = "";
		System.out.println(returnStr);
	}

	/**
	 * 获取微信服务器IP地址
	 */
	@Test
	public void testGetcallbackip() {
		String url = "https://api.weixin.qq.com/cgi-bin/getcallbackip";
		List<NameValuePair> params = new ArrayList<>();
		params.add(new NameValuePair("access_token", accessToken));
		String returnStr = HttpTookit.doGetJson(url, params);
		System.out.println(returnStr);
	}

	/**
	 * 获取用户列表
	 */
	@Test
	public void get() {
		String url = "https://api.weixin.qq.com/cgi-bin/user/get";
		List<NameValuePair> params = new ArrayList<>();
		params.add(new NameValuePair("access_token", accessToken));
		params.add(new NameValuePair("next_openid", "")); // 第一个拉取的OPENID，不填默认从头开始拉取
		String returnStr = HttpTookit.doGetJson(url, params);
		System.out.println(returnStr);
	}

	/**
	 * 获取用户基本信息
	 */
	@Test
	public void info() {
		String url = "https://api.weixin.qq.com/cgi-bin/user/info";
		List<NameValuePair> params = new ArrayList<>();
		params.add(new NameValuePair("access_token", accessToken));
		params.add(new NameValuePair("openid", "ocRZAv7RlHrbX3PvglgwqBGaOEvw")); // 加密后的微信号，每个用户对每个公众号的OpenID是唯一的。对于不同公众号，同一用户的openid不同
		params.add(new NameValuePair("lang", "zh_CN")); // 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
		String returnStr = HttpTookit.doGetJson(url, params);
		System.out.println(returnStr);
	}
	
	/**
	 * 查询所有分组
	 */
	@Test
	public void getGroups() {
		String url = "https://api.weixin.qq.com/cgi-bin/groups/get";
		List<NameValuePair> params = new ArrayList<>();
		params.add(new NameValuePair("access_token", accessToken));
		String returnStr = HttpTookit.doGetJson(url, params);
		System.out.println(returnStr); 
	}
	
	/**
	 * 查询用户所在分组
	 */
	@Test
	public void getid() {
		String url = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token="+ accessToken;
		UserTest test = new UserTest();
		test.setOpenid("ocRZAv7RlHrbX3PvglgwqBGaOEvw");
		String returnStr = HttpTookit.doPostJson(url, new Gson().toJson(test));
		System.out.println(returnStr);
	}
	
	@Test
	public void etss() {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
//		https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
	}

}
