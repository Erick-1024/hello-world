package com.cana.message.client.message;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.cana.vbam.common.message.dto.MailMessageDTO;
import com.cana.vbam.common.message.dto.NotificationMessageDTO;
import com.cana.vbam.common.message.dto.SmsMessageDTO;
import com.google.gson.Gson;

@Service
public class MessageClient {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final Gson gson = new Gson();

	@Resource(name="mailRabbitTemplate")
	private RabbitTemplate mailRabbitTemplate;
	@Resource(name="notificationRabbitTemplate")
    private RabbitTemplate notificationRabbitTemplate;
	@Resource(name="smsMessageRabbitTemplate")
	private RabbitTemplate smsMessageRabbitTemplate;
	
	public void sendMail(MailMessageDTO message){
		logger.info("发送邮件请求：{}", gson.toJson(message));
		mailRabbitTemplate.convertAndSend(message);
	}
	
	public void sendNotification(NotificationMessageDTO message){
		logger.info("发送通知请求：{}", gson.toJson(message));
	    notificationRabbitTemplate.convertAndSend(message);
    }
	
	public void sendMessage(SmsMessageDTO message){
		logger.info("发送短信请求：{}",gson.toJson(message));
		smsMessageRabbitTemplate.convertAndSend(message);
	}
	public void sendSmsMessage(SmsMessageDTO smsMessage){
		logger.info("发送短信请求：{}", gson.toJson(smsMessage));
		smsMessageRabbitTemplate.convertAndSend(smsMessage);
	}
}
