package com.cana.wechat.common.dto;

import java.io.Serializable;

public class TokenRedisDTO implements Serializable {

	private static final long serialVersionUID = 3047046198105779655L;

	/**
	 * 微信token名称，默认wechat_token
	 */
	private String name = "wechat_token";

	/**
	 * 上次拿到时间
	 */
	private long lastTime;

	/**
	 * access_token
	 */
	private String token;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
