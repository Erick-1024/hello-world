package com.cana.message.server.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.message.server.consts.Consts;
import com.cana.message.server.response.DingdongResponse;
import com.cana.message.server.response.SendResponse;
import com.cana.message.server.service.ISmsMessageSenderChannel;
import com.cana.vbam.common.message.dto.SmsMessageDTO;
import com.cana.vbam.common.message.enums.SmsMessageChannelType;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.net.http.SimpleHttpClient;

public class DingdongSenderChannel implements ISmsMessageSenderChannel{
	
	private Logger log = LoggerFactory.getLogger(DingdongSenderChannel.class);

	@Override
	public SendResponse singleSmsMessageSender(SmsMessageDTO message){
		NameValuePair[] nameValuePairs = new NameValuePair[3];
		nameValuePairs[0] = new BasicNameValuePair(Constants.DINGDING_API_KEY, Consts.DINGDONG_API_KEY);
		nameValuePairs[1] = new BasicNameValuePair(Constants.DINGDING_MOBILE, message.getReceivePhoneNum());
		nameValuePairs[2] = new BasicNameValuePair(Constants.DINGDING_CONTENT, message.getPrefix() + message.getContent());
		log.info("sms message is ready for sending,TO {}, CONTENT {}, PLATFORM {}", message.getReceivePhoneNum(), message.getPrefix() + message.getContent(), SmsMessageChannelType.DINGDONG);
		String result = SimpleHttpClient.post(Consts.DINGDONG_SEND_URL, nameValuePairs);
		return resultResolve(result);
	}

	@Override
	public SendResponse verificationCodeSmsMessageSender(SmsMessageDTO message) {
		NameValuePair[] nameValuePairs = new NameValuePair[3];
		nameValuePairs[0] = new BasicNameValuePair(Constants.DINGDING_API_KEY, Consts.DINGDONG_API_KEY);
		nameValuePairs[1] = new BasicNameValuePair(Constants.DINGDING_MOBILE, message.getReceivePhoneNum());
		nameValuePairs[2] = new BasicNameValuePair(Constants.DINGDING_CONTENT, message.getPrefix() + message.getContent());
		log.info("sms message is ready for sending,TO {}, CONTENT {}, PLATFORM {}", message.getReceivePhoneNum(), message.getPrefix() + message.getContent(), SmsMessageChannelType.DINGDONG);
		String result = SimpleHttpClient.post(Consts.DINGDONG_CODE_URL, nameValuePairs);
		return resultResolve(result);
	}
	
	private SendResponse resultResolve(String result){
		if(StringUtils.isBlank(result)){
			return SendResponse.fail("未知异常");
		} else {
			ObjectMapper ddMapper = new ObjectMapper();
			DingdongResponse ddResp = new DingdongResponse();
			try {
				ddResp = ddMapper.readValue(result, DingdongResponse.class);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return SendResponse.fail(e.getMessage());
			}
			if(StringUtils.equals(Constants.DINGDONG_SUCCESS_CODE, ddResp.getCode())){
				return SendResponse.success();
			}else{
				return SendResponse.fail(ddResp.getMsg());
			}
		}
	}

}
