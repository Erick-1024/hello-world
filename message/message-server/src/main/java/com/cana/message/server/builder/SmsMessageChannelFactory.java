package com.cana.message.server.builder;

import com.cana.message.server.service.ISmsMessageSenderChannel;
import com.cana.message.server.service.impl.DingdongSenderChannel;
import com.cana.message.server.service.impl.YunpianSenderChannel;
import com.cana.vbam.common.message.enums.SmsMessageChannelType;
import com.travelzen.framework.core.exception.WebException;

public class SmsMessageChannelFactory {

	public static ISmsMessageSenderChannel getSmsMesssageChannel(SmsMessageChannelType smsMessageChannelType){
		switch (smsMessageChannelType) {
		case YUNPIAN:
			return new YunpianSenderChannel();
		case DINGDONG:
			return new DingdongSenderChannel();
		default:
			throw WebException.instance("未找到相应的短信通道");
		}
	}
}
