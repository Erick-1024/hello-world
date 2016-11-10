package com.cana.credit.scheduler.retry;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.cana.member.api.IUserApi;
import com.cana.message.client.message.MessageClient;
import com.cana.vbam.common.credit.dto.trade.Refund2CustomerSuccessMessage;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.message.MailMessageTemplate;
import com.cana.vbam.common.message.SmsMessageTemplate;
import com.cana.vbam.common.message.dto.MailMessageDTO;
import com.cana.vbam.common.message.dto.SmsMessageDTO;
import com.cana.vbam.common.message.enums.MailContentType;
import com.cana.vbam.common.message.enums.SmsMessageType;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.utils.WebEnv;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.handler.AbstractRetryTaskHandler;
import com.travelzen.framework.retry.handler.HandlerStatus;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class Refund2CustomerSuccessRetryTaskHandler extends AbstractRetryTaskHandler{
	
	private MessageClient messageClient = SpringApplicationContext.getApplicationContext().getBean(MessageClient.class);
	
	private IUserApi userApiImpl = SpringApplicationContext.getApplicationContext().getBean(IUserApi.class);
	
	@Override
	public void execute(RetryTask task, HandlerStatus status) throws Exception {
		
		Refund2CustomerSuccessMessage message = new Gson().fromJson(task.getData(), Refund2CustomerSuccessMessage.class);
		
		CustomerDetailDTO customerDetailDTO = userApiImpl.queryCustomerByCompanyName(message.getToAccountName(), UserType.FINACE);
		
		// 获取短信相关参数并组装并发送
		List<String> smsMessageParamList = Lists.newArrayList();
		smsMessageParamList.add(customerDetailDTO.getCompanyName());
		smsMessageParamList.add(message.getToAccountNo().substring(message.getToAccountNo().length()-4));
		smsMessageParamList.add(MoneyArithUtil.convertMoneyToString(message.getAmount()));
		sendSmsMessage(generateMessageContent(SmsMessageTemplate.tz_refund_sms_message_content_template, smsMessageParamList), customerDetailDTO.getContactTel());
		
		// 组装邮件相关参数并发送
		List<String> mailMessageParamList = Lists.newArrayList();
		mailMessageParamList.add(customerDetailDTO.getCompanyName());
		mailMessageParamList.add(message.getToAccountNo().substring(message.getToAccountNo().length()-4));
		mailMessageParamList.add(MoneyArithUtil.convertMoneyToString(message.getAmount()));
		mailMessageParamList.add(generateMailUrl(WebEnv.getVBAMPlatformPath()));
		mailMessageParamList.add(MailMessageTemplate.cana_phone);
		sendMailMessage(customerDetailDTO.getContactMail(), generateMessageContent(MailMessageTemplate.
				tz_refund_mail_message_content_template, mailMessageParamList), MailMessageTemplate.tz_refund_mail_message_subject_template);
	}
	
	private void sendSmsMessage(String content, String receivePhoneNum){
		SmsMessageDTO smsMessageDTO = new SmsMessageDTO();
		smsMessageDTO.setContent(content);
		smsMessageDTO.setPrefix("【CANA金融】");
		smsMessageDTO.setReceivePhoneNum(receivePhoneNum);
		smsMessageDTO.setSmsMessageType(SmsMessageType.NOTICE);
		messageClient.sendSmsMessage(smsMessageDTO);
	}
	
	private String generateMessageContent(String template, List<String> dataItems){
		for(String dataItem : dataItems){
			if(StringUtils.isNotBlank(dataItem)){
				template = template.replaceFirst(MailMessageTemplate.matchcharactersReg, dataItem);
			}
		}
		return template;
	}
	
	private String generateMailUrl(String url){
		return "<a href=" + url + " target='_blank'>" + url + "</a>";
	}
	
	private void sendMailMessage(String receiver, String content, String subject) {
        MailMessageDTO mailMessageDTO = new MailMessageDTO();
        mailMessageDTO.setContentType(MailContentType.HTML);
        mailMessageDTO.setReceiver(receiver);
        mailMessageDTO.setSubject(subject);
        mailMessageDTO.setContent(content);
        messageClient.sendMail(mailMessageDTO);
    }
}
