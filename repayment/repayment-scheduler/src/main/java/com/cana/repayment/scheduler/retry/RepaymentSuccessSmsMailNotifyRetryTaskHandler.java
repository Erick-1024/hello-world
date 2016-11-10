package com.cana.repayment.scheduler.retry;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.member.api.IUserApi;
import com.cana.message.client.message.MessageClient;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.message.MailMessageTemplate;
import com.cana.vbam.common.message.SmsMessageTemplate;
import com.cana.vbam.common.message.dto.MailMessageDTO;
import com.cana.vbam.common.message.dto.SmsMessageDTO;
import com.cana.vbam.common.message.enums.MailContentType;
import com.cana.vbam.common.message.enums.SmsMessageType;
import com.cana.vbam.common.message.util.SmsMessageFilterUtil;
import com.cana.vbam.common.repayment.message.dto.RepaymentSuccessMessage;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.utils.WebEnv;
import com.cana.vbam.common.yundaex.templete.YundaexMailMessageTemplate;
import com.cana.vbam.common.yundaex.templete.YundaexSmsMessageTemplate;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.handler.AbstractRetryTaskHandler;
import com.travelzen.framework.retry.handler.HandlerStatus;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class RepaymentSuccessSmsMailNotifyRetryTaskHandler extends AbstractRetryTaskHandler{

	private MessageClient messageClient = SpringApplicationContext.getApplicationContext().getBean(MessageClient.class);
	
	private IUserApi userApiImpl = SpringApplicationContext.getApplicationContext().getBean(IUserApi.class);
	
	private static Logger logger = LoggerFactory.getLogger(RepaymentSuccessSmsMailNotifyRetryTaskHandler.class);
	
	private IVbamCommonService commonService = SpringApplicationContext.getApplicationContext().getBean(IVbamCommonService.class);
	
	@Override
	public void execute(RetryTask task, HandlerStatus status) throws Exception {
		RepaymentSuccessMessage message = new Gson().fromJson(task.getData(), RepaymentSuccessMessage.class);
		
		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(message.getLoanInfoId());
		
		if(!SmsMessageFilterUtil.smsSendFlag(commonService.isProdEnv(), loanInfoBO.getCreateTime())){
			return;
		}
		
		CustomerDetailDTO customerDetailDTO = userApiImpl.queryCustomerDetail(loanInfoBO.getFinanceId());
		
		if(StringUtils.isBlank(loanInfoBO.getBusinessProductId()) || (!StringUtils.equals(loanInfoBO.getBusinessProductId(), 
				Constants.TRAVELZEN_FINANCE_PRODUCT_ID)	&& !StringUtils.equals(loanInfoBO.getBusinessProductId(), Constants.YUNDAEX_FINANCE_PRODUCT_ID))){
			logger.info(loanInfoBO.getId() + "放款信息，无需提醒");
			return;
		}
		
		DateTime currentDate = DateTimeUtil.convertStringToDate(commonService.getCurrentDate());
		String month = currentDate.getMonthOfYear()+"";
		String day = currentDate.getDayOfMonth()+"";
		
		try{
			List<String> smsMessageParamList = Lists.newArrayList();
			if(StringUtils.equals(loanInfoBO.getBusinessProductId(), Constants.TRAVELZEN_FINANCE_PRODUCT_ID)){
				smsMessageParamList.add(customerDetailDTO.getCompanyName());
				smsMessageParamList.add(month);
				smsMessageParamList.add(day);
				smsMessageParamList.add(MoneyArithUtil.convertMoneyToString(message.getTotal()));
				sendSmsMessage(generateMessageContent(SmsMessageTemplate.tz_repayment_success_sms_message_content_template, smsMessageParamList), customerDetailDTO.getContactTel());
			}else{
				smsMessageParamList.add(customerDetailDTO.getCompanyName());
				smsMessageParamList.add(loanInfoBO.getId());
				smsMessageParamList.add(MoneyArithUtil.convertMoneyToString(message.getTotal()));
				sendSmsMessage(generateMessageContent(YundaexSmsMessageTemplate.yd_repayment_success_sms_message_content_template, smsMessageParamList), customerDetailDTO.getContactTel());
			}
		} catch(WebException e){
			logger.error(e.getMessage(), e);
		} catch(Exception e){
			logger.error("未知异常", e);
		}

		try{
			// 组装邮件相关参数并发送
			List<String> mailMessageParamList = Lists.newArrayList();
			
			if(StringUtils.equals(loanInfoBO.getBusinessProductId(), Constants.TRAVELZEN_FINANCE_PRODUCT_ID)){
				mailMessageParamList.add(customerDetailDTO.getCompanyName());
				mailMessageParamList.add(month);
				mailMessageParamList.add(day);
				mailMessageParamList.add(MoneyArithUtil.convertMoneyToString(message.getTotal()));
				mailMessageParamList.add(generateMailUrl(WebEnv.getVBAMPlatformPath() + "/loanInfo/manage/gotoRepaymentPlanDetails?loanId=" + loanInfoBO.getId()));
				mailMessageParamList.add(MailMessageTemplate.cana_phone);
				sendMailMessage(customerDetailDTO.getContactMail(), generateMessageContent(MailMessageTemplate.
						tz_repayment_success_mail_message_content_template, mailMessageParamList), MailMessageTemplate.tz_repayment_success_mail_message_subject_template);
			}else{
				mailMessageParamList.add(customerDetailDTO.getCompanyName());
				mailMessageParamList.add(loanInfoBO.getId());
				mailMessageParamList.add(MoneyArithUtil.convertMoneyToString(message.getTotal()));
				mailMessageParamList.add(generateMailUrl(WebEnv.getVBAMPlatformPath() + "/loanInfo/manage/gotoRepaymentPlanDetails?loanId=" + loanInfoBO.getId()));
				mailMessageParamList.add(MailMessageTemplate.cana_phone);
				sendMailMessage(customerDetailDTO.getContactMail(), generateMessageContent(YundaexMailMessageTemplate.
						yd_repayment_success_mail_message_content_template, mailMessageParamList), YundaexMailMessageTemplate.yd_repayment_success_mail_message_subject_template);
			}
		} catch(WebException e){
			logger.error(e.getMessage(), e);
		} catch(Exception e){
			logger.error("未知异常", e);
		}
		
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
