package com.cana.message.server.consts;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;

public class Consts {
	private static final Logger logger = LoggerFactory.getLogger(Consts.class);
	// 邮件配置文件路径
	private static final String MAIL_PROP_PATH = "properties/mail.properties";
	// 短信配置文件路径
	private static final String SMS_MESSAGE_PROP_PATH = "properties/sms-message.properties";
	
	// 邮件发送参数
	public static final String MAIL_SMTP;
	public static final String MAIL_AUTH_USERNAME;
	public static final String MAIL_AUTH_PASSWORD;
	public static final String MAIL_FROM;
	
	// 云片短信发送参数
	public static String YUNPIAN_API_KEY;
	public static String YUNPIAN_SEND_URL_SINGLE;
//	public static final String YUNPIAN_PREFIX;

	// 叮咚云短信发送参数
	public static String DINGDONG_API_KEY;
	public static String DINGDONG_SEND_URL;
	public static String DINGDONG_CODE_URL;
//	public static final String YUNPIAN_PREFIX;
	
	// 使用短信通道顺序
	public static String CHANNEL_SEQ;
	
	static {
		// 邮件发送参数初始化
		MAIL_SMTP = StringUtils.trimToEmpty(TopsConfReader.getConfContent(MAIL_PROP_PATH, "mail.smtp", ConfScope.R));
		logger.info("mail.smtp --> {}", MAIL_SMTP);
		MAIL_AUTH_USERNAME = StringUtils.trimToEmpty(TopsConfReader.getConfContent(MAIL_PROP_PATH, "mail.auth.username", ConfScope.R));
		logger.info("mail.auth.username --> {}", MAIL_AUTH_USERNAME);
		MAIL_AUTH_PASSWORD = StringUtils.trimToEmpty(TopsConfReader.getConfContent(MAIL_PROP_PATH, "mail.auth.password", ConfScope.R));
		logger.info("mail.auth.password --> {}", MAIL_AUTH_PASSWORD);
		MAIL_FROM = StringUtils.trimToEmpty(TopsConfReader.getConfContent(MAIL_PROP_PATH, "mail.from", ConfScope.R));
		logger.info("mail.from --> {}", MAIL_FROM);
		
	}
	
	public static void loadSmsMessageConfig(){
		// 云片短息发送参数初始化
		YUNPIAN_API_KEY = StringUtils.trimToEmpty(TopsConfReader.getConfContent(SMS_MESSAGE_PROP_PATH, "yunpian.api.key", ConfScope.R));
		logger.info("yunpian.api.key --> {}", YUNPIAN_API_KEY);
		YUNPIAN_SEND_URL_SINGLE = StringUtils.trimToEmpty(TopsConfReader.getConfContent(SMS_MESSAGE_PROP_PATH, "yunpian.send.url.single", ConfScope.R));
		logger.info("yunpian.send.url --> {}", YUNPIAN_SEND_URL_SINGLE);

		// 叮咚云短息发送参数初始化
		DINGDONG_API_KEY = StringUtils.trimToEmpty(TopsConfReader.getConfContent(SMS_MESSAGE_PROP_PATH, "dingdong.api.key", ConfScope.R));
		logger.info("dingdong.api.key --> {}", DINGDONG_API_KEY);
		DINGDONG_SEND_URL = StringUtils.trimToEmpty(TopsConfReader.getConfContent(SMS_MESSAGE_PROP_PATH, "dingdong.send.url", ConfScope.R));
		logger.info("dingdong.send.url --> {}", DINGDONG_SEND_URL);
		DINGDONG_CODE_URL = StringUtils.trimToEmpty(TopsConfReader.getConfContent(SMS_MESSAGE_PROP_PATH, "dingdong.code.url", ConfScope.R));
		logger.info("dingdong.code.url --> {}", DINGDONG_CODE_URL);
		
		// 短信通道使用顺序
		CHANNEL_SEQ = StringUtils.trimToEmpty(TopsConfReader.getConfContent(SMS_MESSAGE_PROP_PATH, "channel.seq", ConfScope.R));
		logger.info("channel.seq --> {}", CHANNEL_SEQ);
	}
}
