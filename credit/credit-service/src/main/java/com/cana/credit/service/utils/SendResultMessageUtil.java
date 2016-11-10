package com.cana.credit.service.utils;

import com.cana.credit.dao.po.CustomerApply;
import com.cana.credit.service.ICreditMessageService;
import com.cana.vbam.common.credit.enums.NoticeScene;
import com.cana.vbam.common.credit.openapi.CreditNoticeParam;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;


/**
 * 发送结果邮件及短信
 * @author yihong.tang
 * @since 2016-9-2
 */
public class SendResultMessageUtil {

	private static ICreditMessageService creditMessageServiceImpl = SpringApplicationContext.getApplicationContext().getBean(ICreditMessageService.class);

	public static void sendAutomaticMessage(CustomerApply customerApply){
		CreditNoticeParam creditNoticeParam = new CreditNoticeParam();
		creditNoticeParam.setInwhitelist(customerApply.getInWhitelist());
		creditNoticeParam.setNoticeScene(NoticeScene.ACCESS_AUTOMATIC);
		creditNoticeParam.setCompanyName(customerApply.getCompanyName());
		creditNoticeParam.setEmail(customerApply.getEmail());
		creditNoticeParam.setPhoneNumber(customerApply.getPhoneNumber());
//		creditNoticeParam.setAccessManualState(customerApply.getAccessManualState());
		creditMessageServiceImpl.sendMailForTzCustomerApply(creditNoticeParam);
		creditMessageServiceImpl.sendSmsMessageForTzCustomerApply(creditNoticeParam);
	}
}
