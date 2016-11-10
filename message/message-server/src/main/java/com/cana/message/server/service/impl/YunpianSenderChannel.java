package com.cana.message.server.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.message.server.consts.Consts;
import com.cana.message.server.response.SendResponse;
import com.cana.message.server.response.YunpianResponse;
import com.cana.message.server.service.ISmsMessageSenderChannel;
import com.cana.vbam.common.message.dto.SmsMessageDTO;
import com.cana.vbam.common.message.enums.SmsMessageChannelType;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.net.http.SimpleHttpClient;

public class YunpianSenderChannel implements ISmsMessageSenderChannel {
	
	private Logger log = LoggerFactory.getLogger(YunpianSenderChannel.class);

	@Override
	public SendResponse singleSmsMessageSender(SmsMessageDTO message){
		NameValuePair[] nameValuePairs = new NameValuePair[3];
		nameValuePairs[0] = new BasicNameValuePair(Constants.YUNPIAN_API_KEY, Consts.YUNPIAN_API_KEY);
		nameValuePairs[1] = new BasicNameValuePair(Constants.YUNPIAN_MOBILE, message.getReceivePhoneNum());
		nameValuePairs[2] = new BasicNameValuePair(Constants.YUNPIAN_CONTENT, message.getPrefix() + message.getContent());
		log.info("sms message is ready for sending,TO {}, CONTENT {}, PLATFORM {}", message.getReceivePhoneNum(), message.getPrefix() + message.getContent(), SmsMessageChannelType.YUNPIAN);
		String result = SimpleHttpClient.post(Consts.YUNPIAN_SEND_URL_SINGLE, nameValuePairs);
		if(StringUtils.isBlank(result)){
			return SendResponse.fail("请求错误");
		} else {
			ObjectMapper ypMapper = new ObjectMapper();
			YunpianResponse ypResp = new YunpianResponse();
			try {
				ypResp = ypMapper.readValue(result, YunpianResponse.class);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return SendResponse.fail("解析数据出错");
			} 
			if(StringUtils.equals(Constants.YUNPIAN_SUCCESS_CODE, ypResp.getCode())){
				return SendResponse.success();
			}else{
				return SendResponse.fail(ypResp.getMsg());
			}
		}
	}

	@Override
	public SendResponse verificationCodeSmsMessageSender(SmsMessageDTO message) {
		return singleSmsMessageSender(message);
	}
}
