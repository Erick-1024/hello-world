package com.cana.wechat.client;

import com.cana.wechat.common.dto.TokenReponse;
import com.cana.wechat.common.dto.TokenRequest;

public interface IWeChatClinet {

	/**
	 * access_token是公众号的全局唯一票据,7200秒变化一次
	 * 
	 * @param token
	 * @return
	 */
	public TokenReponse token(TokenRequest token);
}
