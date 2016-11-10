package com.cana.yundaex.service.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.cana.yundaex.common.util.Constants;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

/**
 * ID生成器
 * @author hu
 *
 */
@Component
public class YundaexIDGenerator implements ApplicationContextAware{

    private static SequenceGenerator seqGen;
    
    /**
     * 生成个人信息Id
     * @return
     */
    public static String generatePersonalInfoId() {
        return DateTimeUtil.datetime12()
                + seqGen.getNextSeq(Constants.SEQUENCE_NAME_PERSONAL_INFO_ID, 3);
    }

    /**
     * 生成合同情况Id
     * @return
     */
    public static String generateContractSituationId() {
        return DateTimeUtil.datetime12()
                + seqGen.getNextSeq(Constants.SEQUENCE_NAME_CONTRACT_SITUATION_ID, 3);
    }
    
    /**
     * 融资合同编号
     * @return
     */
    public static String generateContractSerialNumber(String prefix) {
        return prefix + DateTimeUtil.date8()
                + seqGen.getNextSeq(Constants.SEQUENCE_NAME_FINANCE_CONTRACT_SERIAL_NUMBER, 3);
    }
    
    /**
     * 单笔融资合同
     */
    public static String generateSingleLoanNumber(){
    	 return DateTimeUtil.date8() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_SINGLE_LOAN_NUMBER, 3);
    }
    
    /**
     * 利率id
     * @return
     */
    public static String generateInterestRateId() {
        return DateTimeUtil.datetime12()
                + seqGen.getNextSeq(Constants.SEQUENCE_NAME_INTEREST_RATE_ID, 3);
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      seqGen = applicationContext.getBean(SequenceGenerator.class);
    }

}
