package com.cana.yundaex.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.member.dao.po.User;
import com.cana.message.client.message.MessageClient;
import com.cana.repayment.api.IRepaymentPlanApi;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.message.dto.MailMessageDTO;
import com.cana.vbam.common.message.dto.SmsMessageDTO;
import com.cana.vbam.common.message.enums.MailContentType;
import com.cana.vbam.common.message.enums.SmsMessageType;
import com.cana.vbam.common.utils.WebEnv;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyDetailDTO;
import com.cana.yundaex.common.enums.PersonalInfoType;
import com.cana.yundaex.common.util.MessageContentUtils;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.po.YundaexPersonalInfo;
import com.cana.yundaex.service.IYundaexMessageService;
import com.esotericsoftware.minlog.Log;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;

/**
 * @author hu
 *
 */
@Service
public class YundaexMessageServiceImpl implements IYundaexMessageService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private MessageClient messageClient;

	@Resource
	private IRepaymentPlanApi repaymentPlanApi;

	private MailMessageDTO convertMailDTO(String subject, String receiver, String content) {
		MailMessageDTO mail = new MailMessageDTO();
		mail.setSubject(subject);
		mail.setReceiver(receiver);
		mail.setContent(content);
		mail.setContentType(MailContentType.HTML);
		return mail;
	}

	private SmsMessageDTO convertSMSDTO(String phoneNum, String prefix, String content) {
		SmsMessageDTO sms = new SmsMessageDTO();
		sms.setReceivePhoneNum(phoneNum);
		sms.setContent(content);
		sms.setPrefix(prefix);
		sms.setSmsMessageType(SmsMessageType.NOTICE);
		return sms;
	}

	@Override
	public void sendFinanceContractSignedMailAndSMS(String customer, String mail, String phone){
		
		String smsContent = MessageContentUtils.generateContent(MessageContentUtils.smsFinanceContractSign, customer);
		SmsMessageDTO smsDTO = convertSMSDTO(phone, MessageContentUtils.smsPrefix, smsContent);
		messageClient.sendSmsMessage(smsDTO);
		
		String mailContent = MessageContentUtils.generateContent(MessageContentUtils.mailFinanceContractSign, customer, MessageContentUtils.mailContactPhone);
		MailMessageDTO mailDTO = convertMailDTO(MessageContentUtils.mailSignContractSubject, mail, mailContent);
		messageClient.sendMail(mailDTO);
	}
	
	@Override
	public void sendPersonalInfoCommitMailAndSMS(YundaexPersonalInfo info) {
		MailMessageDTO mailDTO = null;
		SmsMessageDTO smsDTO = null;
		String smsContent;
		String mailContent;
		String mailSubject = MessageContentUtils.mailcommitSubject;
		String mailReceiver = info.getMail();
		String mailRequestUrl = MessageContentUtils.generateHtmlUrl(MessageContentUtils.generateContent(
				MessageContentUtils.mailcommitUrl, WebEnv.getVBAMPlatformPath(), info.getId(), info.getSecurityCode()));

		if (PersonalInfoType.ACCOUNT_HOLDER.name().equals(info.getType())) {
			mailContent = MessageContentUtils.generateContent(MessageContentUtils.mailAccountInconformity,
					info.getRealName(), mailRequestUrl, MessageContentUtils.mailContactPhone);
			smsContent = MessageContentUtils.generateContent(MessageContentUtils.smsAccountInconformity,
					info.getRealName());
		} else if (PersonalInfoType.CONTROLLER.name().equals(info.getType())) {
			mailContent = MessageContentUtils.generateContent(MessageContentUtils.mailControllerInconformity,
					info.getRealName(), mailRequestUrl, MessageContentUtils.mailContactPhone);
			smsContent = MessageContentUtils.generateContent(MessageContentUtils.smsControllerInconformity,
					info.getRealName());
		} else if (PersonalInfoType.ACCOUNT_HOLDER_AND_CONTROLLER.name().equals(info.getType())) {
			mailContent = MessageContentUtils.generateContent(MessageContentUtils.mailAccountAndControllerInconformity,
					info.getRealName(), mailRequestUrl, MessageContentUtils.mailContactPhone);
			smsContent = MessageContentUtils.generateContent(MessageContentUtils.smsAccountAndControllerInconformity,
					info.getRealName());
		} else {
			Log.error("该用户不存在类型 id:" + info.getId());
			throw WebException.instance("该用户不存在类型");
		}
		mailDTO = convertMailDTO(mailSubject, mailReceiver, mailContent);
		messageClient.sendMail(mailDTO);

		smsDTO = convertSMSDTO(info.getCellphone(), MessageContentUtils.smsPrefix, smsContent);
		 messageClient.sendSmsMessage(smsDTO);
	}

	@Override
	public void sendPersonalSignContractMailAndSMS(YundaexPersonalInfo info) {
		MailMessageDTO mailDTO = null;
		SmsMessageDTO smsDTO = null;
		String smsContent;
		String mailContent;
		String mailSubject = MessageContentUtils.mailSignContractSubject;
		String mailReceiver = info.getMail();
		String mailRequestUrl = MessageContentUtils
				.generateHtmlUrl(MessageContentUtils.generateContent(MessageContentUtils.mailSignContractUrl,
						WebEnv.getVBAMPlatformPath(), info.getId(), info.getSecurityCode()));

		if (PersonalInfoType.ACCOUNT_HOLDER.name().equals(info.getType())) {
			mailContent = MessageContentUtils.generateContent(MessageContentUtils.mailDelegateContractSign,
					info.getRealName(), MessageContentUtils.mailContactPhone, mailRequestUrl);
			smsContent = MessageContentUtils.generateContent(MessageContentUtils.smsDelegateContractSign,
					info.getRealName());
		} else if (PersonalInfoType.CONTROLLER.name().equals(info.getType())) {
			mailContent = MessageContentUtils.generateContent(MessageContentUtils.mailGuaranteeContractSign,
					info.getRealName(), MessageContentUtils.mailContactPhone, mailRequestUrl);
			smsContent = MessageContentUtils.generateContent(MessageContentUtils.smsGuaranteeContractSign,
					info.getRealName());
		} else if (PersonalInfoType.ACCOUNT_HOLDER_AND_CONTROLLER.name().equals(info.getType())) {
			mailContent = MessageContentUtils.generateContent(MessageContentUtils.mailDelegAndGuaraContractsSign,
					info.getRealName(), MessageContentUtils.mailContactPhone, mailRequestUrl);
			smsContent = MessageContentUtils.generateContent(MessageContentUtils.smsDelegAndGuaraContractsSign,
					info.getRealName());
		} else {
			Log.error("该用户不存在类型 id:" + info.getId());
			throw WebException.instance("该用户不存在类型");
		}
		mailDTO = convertMailDTO(mailSubject, mailReceiver, mailContent);
		messageClient.sendMail(mailDTO);

		smsDTO = convertSMSDTO(info.getCellphone(), MessageContentUtils.smsPrefix, smsContent);
		 messageClient.sendSmsMessage(smsDTO);
	}

	/**
	 * 用款申请发送邮件和短信
	 */
	@Override
	public void sendLoanApplyMailAndSMS(RepaymentLoanInfoBO info, YdCustomerApplyDetailDTO customerApplyDetailDTO, CustomerDetailDTO customerDetailDTO) {
		// 用款申请发送邮件
		sendLoanApplyMail(info, customerApplyDetailDTO, customerDetailDTO);

		// 用款申请发送短信
		sendLoanApplySMS(customerApplyDetailDTO, customerDetailDTO);
	}

	/**
	 * 审核失败，发送短信邮件通知
	 */
	@Override
	public void sendYundaexAuditResultMessageAndMail(YundaexCustomerApply apply) {
		if(StringUtils.isNotBlank(apply.getCustPhone())){
			SmsMessageDTO smsMessageDTO = new SmsMessageDTO();
			smsMessageDTO.setReceivePhoneNum(apply.getCustPhone());
			smsMessageDTO.setSmsMessageType(SmsMessageType.NOTICE);
			smsMessageDTO.setPrefix(MessageContentUtils.smsPrefix);
			String content = MessageContentUtils.generateContent(MessageContentUtils.smsAuditReject, apply.getStationName());
			smsMessageDTO.setContent(content);
			messageClient.sendMessage(smsMessageDTO);
			logger.info("审核拒绝短信发送："+content);
		}
		if(StringUtils.isNotBlank(apply.getCustEmail())){
			MailMessageDTO message = new MailMessageDTO();
			message.setContentType(MailContentType.HTML); 
			message.setReceiver(apply.getCustEmail()); 
			message.setSubject(MessageContentUtils.mailAuditSubject);
			String content = MessageContentUtils.generateContent(MessageContentUtils.mailAuditReject, apply.getStationName(),MessageContentUtils.mailContactPhone);
			message.setContent(content); 
			messageClient.sendMail(message);
			logger.info("审核拒绝邮件发送："+content);
		}
	}

	/**
	 * 额度计算成功，发送通知
	 */
	@Override
	public void sendAuditSuccessMessageAndMail(YundaexCustomerApply ydCustomerApply,String finalLimit,String generateActivacationURL) {
		if(StringUtils.isNotBlank(ydCustomerApply.getCustPhone())){
			SmsMessageDTO smsMessageDTO = new SmsMessageDTO();
			smsMessageDTO.setReceivePhoneNum(ydCustomerApply.getCustPhone());
			smsMessageDTO.setSmsMessageType(SmsMessageType.NOTICE);
			smsMessageDTO.setPrefix(MessageContentUtils.smsPrefix);
			String content = MessageContentUtils.generateContent(MessageContentUtils.smsAuditThrough, ydCustomerApply.getStationName());
			smsMessageDTO.setContent(content);
			messageClient.sendMessage(smsMessageDTO);
			logger.info("用户通过短信发送："+content);
		}
		
		if(StringUtils.isNotBlank(ydCustomerApply.getCustEmail())){
			MailMessageDTO message = new MailMessageDTO();
			message.setContentType(MailContentType.HTML); 
			message.setReceiver(ydCustomerApply.getCustEmail()); 
			message.setSubject(MessageContentUtils.mailAuditSubject);
			String content = MessageContentUtils.generateContent(MessageContentUtils.mailAuditThrough, ydCustomerApply.getStationName(),finalLimit.toString(),generateActivacationURL,MessageContentUtils.mailContactPhone);
			message.setContent(content); 
			messageClient.sendMail(message);
			logger.info("用户通过邮件发送："+content);
		}
	}

	/**
	 * 发送激活额度通知
	 */
	@Override
	public void sendCreditActiveMessageAndMail(User user) {
		if(StringUtils.isNotBlank(user.getContactTel())){
			SmsMessageDTO smsMessageDTO = new SmsMessageDTO();
			smsMessageDTO.setReceivePhoneNum(user.getContactTel());
			smsMessageDTO.setSmsMessageType(SmsMessageType.NOTICE);
			smsMessageDTO.setPrefix(MessageContentUtils.smsPrefix);
			String content = MessageContentUtils.generateContent(MessageContentUtils.smsCreditActivated, user.getCompanyName());
			smsMessageDTO.setContent(content);
			messageClient.sendMessage(smsMessageDTO);
			logger.info("激活额度短信发送："+content);
		}
		if(StringUtils.isNotBlank(user.getContactMail())){
			MailMessageDTO message = new MailMessageDTO();
			message.setContentType(MailContentType.HTML); 
			message.setReceiver(user.getContactMail()); 
			message.setSubject(MessageContentUtils.mailCreditActivatedSubject);
			String content = MessageContentUtils.generateContent(MessageContentUtils.mailCreditActivated,user.getCompanyName(), MessageContentUtils.mailContactPhone);
			message.setContent(content); 
			messageClient.sendMail(message);
			logger.info("激活额度邮件发送："+content);
		}
		
	}
	
	/**
	 * 用款申请发送邮件
	 * @param info
	 * @param customerApplyDetailDTO
	 */
	private void sendLoanApplyMail(RepaymentLoanInfoBO info, YdCustomerApplyDetailDTO customerApplyDetailDTO, CustomerDetailDTO customerDetailDTO) {
		try {
			String mailSubject = MessageContentUtils.mailLoanApplySubject; // 标题
			String mailReceiver = customerDetailDTO.getContactMail(); // 收件人
			String planStr = getPlanContent(info);
			String mailRequestUrl = MessageContentUtils.generateHtmlUrl(
					MessageContentUtils.generateContent(WebEnv.getVBAMPlatformPath(), WebEnv.getVBAMPlatformPath()));
			String accountNameStr = formatAccoutName(customerApplyDetailDTO.getPayAccountName(), customerApplyDetailDTO);
			String payAccountStr = formatPayAccout(customerApplyDetailDTO.getPayAccount(), customerApplyDetailDTO.getPayAccountName(), customerApplyDetailDTO);

			String mailContent = MessageContentUtils.generateContent(MessageContentUtils.mailLoanApplyInfo,
					customerApplyDetailDTO.getStationName(), info.getLoanNo(), accountNameStr,
					payAccountStr, planStr, MessageContentUtils.mailContactPhone, mailRequestUrl);
			MailMessageDTO mailDTO = convertMailDTO(mailSubject, mailReceiver, mailContent);
			messageClient.sendMail(mailDTO);
		} catch (Exception e) {
			logger.error("用款申请发送邮件通知错误", e);
		}
	}
	
	/**
	 * 用款申请发送短信
	 * @param customerApplyDetailDTO
	 */
	private void sendLoanApplySMS(YdCustomerApplyDetailDTO customerApplyDetailDTO, CustomerDetailDTO customerDetailDTO) {
		try {
			String smsContent = MessageContentUtils.generateContent(MessageContentUtils.smsLoanApplyInfo,
					customerApplyDetailDTO.getStationName());
			SmsMessageDTO smsDTO = convertSMSDTO(customerDetailDTO.getContactTel(), MessageContentUtils.smsPrefix,
					smsContent);
			messageClient.sendSmsMessage(smsDTO);
		} catch (Exception e) {
			logger.error("用款申请发送短信通知错误", e);
		}
	}
	
	/**
	 * 用款申请邮件构造还款计划
	 * @param info
	 * @return
	 * @throws Exception
	 */
	private String getPlanContent(RepaymentLoanInfoBO info) throws Exception {
		StringBuffer planBuf = new StringBuffer();
		for (RepaymentPlanBO planDBD : info.lazyLoadPlans()) {
			Integer period = planDBD.getRepaymentPeriod(); // 期数
			String principal = MoneyUtil.cent2Yuan(planDBD.getAccountPrincipal()); // 应还本金
			String interest = MoneyUtil.cent2Yuan(planDBD.getAccountInterest()); // 应还利息
			String repaymentDate = planDBD.getRepaymentDate(); // 还款日
			planBuf.append("<tr><td>").append(period).append("</td><td>").append(principal)
					.append("</td><td>").append(interest).append("</td><td>")
					.append(repaymentDate).append("</td></tr>");
		}
		
		return planBuf.toString();
	}
	
	private String formatPayAccout(String payAccount,String accountName, YdCustomerApplyDetailDTO customerApplyDetailDTO) {
		String payAccountStr = "";
		int accountLength = payAccount.length();
		String starStr ="";
		if (customerApplyDetailDTO.getAccountOwnerName().equals(accountName)) {
			for (int i = 0; i < accountLength - 4; i++) {
				starStr += "*";
			}
			payAccountStr = starStr + payAccount.substring(accountLength - 4);
		} else {
			for (int i = 0; i < accountLength - 8; i++) {
				starStr += "*";
			}
			payAccountStr = payAccount.substring(0, 4) + starStr + payAccount.substring(accountLength - 4);
		}
		
		return payAccountStr;
	}
	
	private String formatAccoutName(String accountName, YdCustomerApplyDetailDTO customerApplyDetailDTO) {
		if (customerApplyDetailDTO.getAccountOwnerName().equals(accountName)) {
			return accountName;
		}
		int length = accountName.length();
		String accountNameStr ="";
		if (length < 3) {
			accountNameStr = accountName.substring(0, 1) + "*"; 
		} else {
			String aa ="";
			for (int i = 0; i < length-2; i++) {
				aa += "*";
			}
			accountNameStr = accountName.substring(0, 1) + aa + accountName.substring(length - 1);
		}
		return accountNameStr;
	}
}
