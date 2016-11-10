package com.cana.wechat.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.cana.wechat.common.dto.TokenRedisDTO;
import com.cana.wechat.service.IWeChatTokenService;
import com.travelzen.framework.redis.client.SpringRedisClient;

@Service
public class WeChatTokenServiceImpl implements IWeChatTokenService {

	private SpringRedisClient redisClient = SpringRedisClient.getInstance();

	private String redisQueueName = "wechat_token";

	/**
	 * 获取token相关信息
	 * 
	 * @return lastTime上次拿到时间,token 微信access_token
	 */
	@Override
	public TokenRedisDTO getToken() {
		TokenRedisDTO tokenDTO = (TokenRedisDTO) redisClient.get(redisQueueName);
		return tokenDTO;
	}

	/**
	 * 保存新的token
	 */
	@Override
	public void saveToken(long lastTime, String accessToken) {
		TokenRedisDTO tokenDTO = new TokenRedisDTO();
		tokenDTO.setLastTime(new Date().getTime());
		tokenDTO.setToken(accessToken);
		redisClient.save(redisQueueName, tokenDTO, 86400);
	}
	
	@Override
	public void del() {
		redisClient.delete(redisQueueName);
	}
}
