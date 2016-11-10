package com.cana.message.server.service;

import com.cana.message.server.response.SendResponse;
import com.cana.vbam.common.message.dto.SmsMessageDTO;

public interface ISmsMessageSenderChannel {
	/**
	 * 单条信息发送
	 * @param message
	 */
	public SendResponse singleSmsMessageSender(SmsMessageDTO message);
	
	/**
	 * 验证码短信发送
	 * @param message
	 * @return
	 */
	public SendResponse verificationCodeSmsMessageSender(SmsMessageDTO message);
}
