package com.cana.message.server.handler;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.message.mongo.entity.SmsMessage;
import com.cana.message.server.builder.SmsMessageChannelFactory;
import com.cana.message.server.consts.Consts;
import com.cana.message.server.response.SendResponse;
import com.cana.message.server.service.IMessageHistoryService;
import com.cana.message.server.service.ISmsMessageSenderChannel;
import com.cana.vbam.common.annotation.MQConsumer;
import com.cana.vbam.common.message.dto.SmsMessageDTO;
import com.cana.vbam.common.message.enums.SmsMessageChannelType;
import com.cana.vbam.common.utils.Constants;
import com.google.gson.Gson;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

@MQConsumer(SmsMessageDTO.class)
public class SmsMessageHandler extends AbstractMQMessageHandler<SmsMessageDTO> {

	private Logger log = LoggerFactory.getLogger(SmsMessageHandler.class);
	
	private IMessageHistoryService messageHistoryService = SpringApplicationContext.getApplicationContext().getBean(IMessageHistoryService.class);
	
	@Override
	public void handleMessage(SmsMessageDTO message) throws Exception {
		Consts.loadSmsMessageConfig();
		String[] channelSeq = Consts.CHANNEL_SEQ.split(Constants.SPLIT_SYMBOL);
		SendResponse sendResponse = new SendResponse();
		for(String channelName : channelSeq){
			SmsMessageChannelType smsMessageChannelType = SmsMessageChannelType.valueOf(channelName);
			ISmsMessageSenderChannel smsMessageChannel = SmsMessageChannelFactory.getSmsMesssageChannel(smsMessageChannelType);
			switch (message.getSmsMessageType()) {
			case NOTICE:
				sendResponse = smsMessageChannel.singleSmsMessageSender(message);
				break;
			case CODE:
				sendResponse = smsMessageChannel.verificationCodeSmsMessageSender(message);
				break;
			default:
				throw WebException.instance("未找到相应的处理类型");
			}
			log.info("sms message send notice: "+ new Gson().toJson(message));
			log.info("sms message send state: " + sendResponse.isSuccess());
			saveSmsMessageHistory(message, sendResponse);
			if(sendResponse.isSuccess()){
				break;
			}
		}
	}
	
	private void saveSmsMessageHistory(SmsMessageDTO message, SendResponse sendResponse){
		SmsMessage smsMessage = new SmsMessage();
		smsMessage.setReceivePhoneNum(message.getReceivePhoneNum());
		smsMessage.setContent(message.getPrefix() + message.getContent());
		smsMessage.setCreateTime(new DateTime());
		smsMessage.setSendState(sendResponse.isSuccess());
		if(!sendResponse.isSuccess()){
			smsMessage.setFailReason(sendResponse.getFailReason());
		}
		messageHistoryService.saveSmsMessageHistory(smsMessage);
	}
}
