package com.cana.account.service.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

/**
 * ID生成器
 * @author XuMeng
 *
 */
@Component
public class AccountIDGenerator implements ApplicationContextAware{

    private static SequenceGenerator seqGen;

    public static String generateAccountId() {
        return DateTimeUtil.datetime12()
                + seqGen.getNextSeq(Constants.SEQUENCE_NAME_ACCOUNT_ID, 3);
    }

    public static String generateAccountApplyId() {
        return DateTimeUtil.datetime12()
                + seqGen.getNextSeq(Constants.SEQUENCE_NAME_ACCOUNT_APPLY_ID, 3);
    }

    /** 交易记录ID */
    public static String generateAccountTradeRecordId() {
        return DateTimeUtil.datetime12()
                + seqGen.getNextSeq(Constants.SEQUENCE_NAME_TRADE_RECORD_ID, 5);
    }

    /** 交易记录ID */
    public static String generateAccountTradeApplyId() {
        return DateTimeUtil.datetime12()
                + seqGen.getNextSeq(Constants.SEQUENCE_NAME_TRADE_APPLY_ID, 3);
    }

    /**
     * 生成审核id
     */
    public static String generateAuditId() {
        return DateTimeUtil.datetime12()
                + seqGen.getNextSeq(Constants.SEQUENCE_NAME_AUDIT_ID, 3);
    }

    /** 生成用户ID */
    public static String generateUserId() {
        return DateTimeUtil.date8()
                + seqGen.getNextSeq(Constants.SEQUENCE_NAME_USER_ID, 4);
    }
    
    public static String generateCustomerAuditId() {
        return DateTimeUtil.datetime12()
                + seqGen.getNextSeq(Constants.SEQUENCE_NAME_AUDIT_ID, 3);
    }

    /**
     * 生成业务流水号
     */
    public static String generateBusinessSeq(){
    	return DateTimeUtil.datetime12()
                + seqGen.getNextSeq(Constants.BUSINESS_SEQ, 5);
    }
    
    /**
     * 生成监管关系ID
     */
    public static String generateAccountSupervisionId(){
    	return DateTimeUtil.datetime12()
                + seqGen.getNextSeq(Constants.ACCOUNT_SUPERVISION_ID, 3);
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      seqGen = applicationContext.getBean(SequenceGenerator.class);
    }

}
