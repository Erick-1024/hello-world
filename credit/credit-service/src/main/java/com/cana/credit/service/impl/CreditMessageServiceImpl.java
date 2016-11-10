package com.cana.credit.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.credit.service.ICreditMessageService;
import com.cana.message.client.message.MessageClient;
import com.cana.vbam.common.credit.enums.AccessManualState;
import com.cana.vbam.common.credit.enums.NoticeScene;
import com.cana.vbam.common.credit.openapi.CreditNoticeParam;
import com.cana.vbam.common.message.dto.MailMessageDTO;
import com.cana.vbam.common.message.dto.SmsMessageDTO;
import com.cana.vbam.common.message.enums.MailContentType;
import com.cana.vbam.common.message.enums.SmsMessageType;
import com.cana.vbam.common.utils.AccountNoUtil;
import com.google.gson.Gson;

@Service
public class CreditMessageServiceImpl implements ICreditMessageService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MessageClient messageClient;
    
    private static final String cana_email = "xlb@canacorp.net";

    private static final String cana_phone = "021-53866655-8051";
    
    //客户申请
//    private static final String tz_customer_apply_not_in_whitelist_mail_template =
//		"【CANA金融】尊敬的${companyName}用户：<br/>" +
//			"您好，您的信旅宝申请已受理，请提交如下申请材料：<br/>" +   
//		    "请提交其它平台或者真旅网其它账户数据<br/>" +                   
//		    "1、增信资料<br/>" +        
//		    "2、销售数据<br/>" +                                
//		    "3、合作协议<br/>" +                               
//		    "4、补充资料<br/>" +                                          
//		    "申请提交之后我们将在1-3工作日内将申请结果发送至指定邮箱，谢谢配合！<br/>" +         
//		    "资料发送邮箱："+ cana_email +"<br/>" + 
//		    "联系电话："+ cana_phone; 
    
//    private static final String tz_customer_apply_not_in_whitelist_subject = "信旅宝-资料提交";
    
    private static final String tz_customer_apply_in_whitelist_mail_template =
    	"【CANA金融】尊敬的${companyName}用户：<br/>" +
    		"您好，您的信旅宝申请已受理，我们会尽快通知您审核结果。<br/>" +
    		"如有疑问，请与CANA金融联系，电话：" + cana_phone + "。" ;
    
    private static final String tz_customer_apply_in_whitelist_subject = "信旅宝-融资申请";
    
//    private static final String tz_customer_apply_not_in_whitelist_smsmessage_template =
//    	"尊敬的${companyName}用户：您好，您的信旅宝申请已受理，还需提交其他补充资料，资料详情已发送至您的邮箱，请查实！";
    
    private static final String tz_customer_apply_in_whitelist_smsmessage_template =
        "尊敬的${companyName}用户：您好，您的信旅宝申请已受理，我们会尽快通知您审核结果。";
    
    //自动准入以及生成额度
    private static final String audit_subject = "信旅宝-审核结果";
    
    private static final String audit_not_access_mail_template =
    	"【CANA金融】尊敬的${companyName}用户：<br/>" +
    	"您好，您的资质暂不符合信旅宝申请要求，如您符合条件后，我们将及时通知您！<br/>" +
    	"如有疑问，请与CANA金融联系，电话：" + cana_phone + "。 ";
    
    private static final String audit_not_access_smsmessage_template =
    	"尊敬的${companyName}用户：您好，您的资质暂不符合信旅宝申请要求，如您符合条件后，我们将及时通知您！谢谢！";		
    
    private static final String generate_limit_mail_template =
    	"【CANA金融】尊敬的${companyName}用户：<br/>" + 
    	"您好，您本次信旅宝申请已通过审核，相关信息如下：可申请用款金额${limit}元<br/>" +
    	"${activeLink}" +
    	"如有疑问，请与CANA金融联系，电话：" + cana_phone +"。 " +
    	"${loginLink}";
    
    private static final String generate_limit_smsmessage_template =
    	"尊敬的${companyName}用户：您好，您本次信旅宝申请已通过审核${needActiveContent}。";
    		
    //额度生效
    private static final String limit_active_subject = "信旅宝-额度生效";
    
    private static final String limit_active_mail_template =
    	"【CANA金融】尊敬的${companyName}用户：<br/>" + 	
    	"您好，您的信旅宝已生效，您可正常使用。<br/>"+          
    	"还款账户${accountNo}" +                 
    	"如有疑问，请与CANA金融联系，电话：" + cana_phone + "。请知悉！";
	
    private static final String limit_active_smsmessage_template =
    	"尊敬的${companyName}用户：您好，您的信旅宝已生效，您可正常使用。";
    @Override
	public void sendMailForTzCustomerApply(CreditNoticeParam creditNoticeParam) {
    	logger.info("发送邮件的参数："+new Gson().toJson(creditNoticeParam));
    	NoticeScene noticeScene = creditNoticeParam.getNoticeScene();
    	boolean inwhitelist = creditNoticeParam.isInwhitelist();
		try {
			String content = "";
			String subject = "";
			if(NoticeScene.TZ_CUSTOMER_APPLY.equals(noticeScene)){
//				if(!inwhitelist){
//					content = tz_customer_apply_not_in_whitelist_mail_template;
//					subject = tz_customer_apply_not_in_whitelist_subject;
//				}else{
					content = tz_customer_apply_in_whitelist_mail_template;
					subject = tz_customer_apply_in_whitelist_subject;
//				}
			}else if(NoticeScene.ACCESS_AUTOMATIC.equals(noticeScene)){
				content = audit_not_access_mail_template;
				subject = audit_subject;
			}else if(NoticeScene.GENERATE_LIMIT.equals(noticeScene)){
				if(AccessManualState.NOTACCESS.name().equals(creditNoticeParam.getAccessManualState())){
					content = audit_not_access_mail_template;
					subject = audit_subject;
				}else if(AccessManualState.ACCESS.name().equals(creditNoticeParam.getAccessManualState())){
					content = generate_limit_mail_template;
					subject = audit_subject;
					content = content.replace("${limit}", creditNoticeParam.getLimit());
					if(creditNoticeParam.getActiveLink() != null){
						content = content.replace("${activeLink}", "请点击链接以下链接激活<br/>激活链接"+creditNoticeParam.getActiveLink()+"<br/>");
						content = content.replace("${loginLink}", "");
					}else{
						content = content.replace("${activeLink}", "");
						content = content.replace("${loginLink}", "<br/>CANA平台链接"+creditNoticeParam.getLoginLink());
					}
				}
			}else if(NoticeScene.LIMIT_ACTIVE.equals(noticeScene)){
				content = limit_active_mail_template;
				subject = limit_active_subject;
				content = content.replace("${accountNo}", AccountNoUtil.formatBankAccountNo(creditNoticeParam.getAccountNo()));
			}else 
				return;
            content = content.replace("${companyName}", creditNoticeParam.getCompanyName());
			sendMail(creditNoticeParam.getEmail(),content,subject);
		} catch (Exception e) {
    		logger.error("发送【信旅宝】邮件异常", e);
    	}
	}
	
	 /**
     * 拼装【信旅宝-资料提交】邮件
     */
    private void sendMail(String receiver, String content,String subject) {
        MailMessageDTO mail = new MailMessageDTO();
        mail.setContentType(MailContentType.HTML);
        mail.setReceiver(receiver);
        mail.setSubject(subject);
        mail.setContent(content);
        messageClient.sendMail(mail);
    }

	@Override
	public void sendSmsMessageForTzCustomerApply(CreditNoticeParam creditNoticeParam) {
		logger.info("发送短信的参数："+new Gson().toJson(creditNoticeParam));
		NoticeScene noticeScene = creditNoticeParam.getNoticeScene();
    	boolean inwhitelist = creditNoticeParam.isInwhitelist();
		try {
			String content = "";
			if(NoticeScene.TZ_CUSTOMER_APPLY.equals(noticeScene)){
//				if(!inwhitelist)
//					content = tz_customer_apply_not_in_whitelist_smsmessage_template;
//				else
					content = tz_customer_apply_in_whitelist_smsmessage_template;
			}else if(NoticeScene.ACCESS_AUTOMATIC.equals(noticeScene)){
				content = audit_not_access_smsmessage_template;
			}else if(NoticeScene.GENERATE_LIMIT.equals(noticeScene)){
				if(AccessManualState.NOTACCESS.name().equals(creditNoticeParam.getAccessManualState()))
					content = audit_not_access_smsmessage_template;
				else if(AccessManualState.ACCESS.name().equals(creditNoticeParam.getAccessManualState())){
					content = generate_limit_smsmessage_template;	
					if(creditNoticeParam.isNeedLoginActive())
						content = content.replace("${needActiveContent}", "，您需要登录真旅后台进行激活");
					else
						content = content.replace("${needActiveContent}", "");
				}
			}else if(NoticeScene.LIMIT_ACTIVE.equals(noticeScene)){
				content = limit_active_smsmessage_template;
			}else 
				return;
            content = content.replace("${companyName}", creditNoticeParam.getCompanyName());
            sendSmsMessage(creditNoticeParam.getPhoneNumber(),content);
		} catch (Exception e) {
    		logger.error("发送【信旅宝】短信异常", e);
    	}
	}
	
	/**
     * 拼装【信旅宝-资料提交】短信
     */
    private void sendSmsMessage(String receivePhoneNum, String content) {
    	SmsMessageDTO smsMessage = new SmsMessageDTO();
    	smsMessage.setReceivePhoneNum(receivePhoneNum);
    	smsMessage.setContent(content);
    	smsMessage.setSmsMessageType(SmsMessageType.NOTICE);
    	smsMessage.setPrefix("【CANA金融】");
        messageClient.sendSmsMessage(smsMessage);
    }

}
