package com.cana.credit.service;

import com.cana.vbam.common.credit.openapi.CreditNoticeParam;
/**
 * 真旅项目发送邮件等信息
 * @author tangyihong
 *
 */
public interface ICreditMessageService {

    /**
     * 发送邮件
     */
    public void sendMailForTzCustomerApply(CreditNoticeParam creditNoticeParam);
    
    /**
     * 发送短信
     */
    public void sendSmsMessageForTzCustomerApply(CreditNoticeParam creditNoticeParam);
    
    
}
