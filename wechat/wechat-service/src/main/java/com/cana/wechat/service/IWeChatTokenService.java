package com.cana.wechat.service;

import com.cana.wechat.common.dto.TokenRedisDTO;

public interface IWeChatTokenService {
	/**
	 * 获取token相关信息
	 * 
	 * @return lastTime上次拿到时间,token 微信access_token
	 */
	public TokenRedisDTO getToken();

	/**
	 * 保存新的token
	 */
	public void saveToken(long lastTime, String accessToken);
	
	public void del();
}
