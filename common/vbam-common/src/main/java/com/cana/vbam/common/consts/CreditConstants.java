/**
 * Copyright © 2016-2019 Cana. All rights reserved.
 */
package com.cana.vbam.common.consts;

import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;

/**
 * 授信项目常量
 */
public class CreditConstants {

    private static final String COMPANY_CONTRACT_FACTOR_ID = "201607050033";
    private static final String INDIVIDUAL_CONTRACT_FACTOR_ID = "cana-baoli";

    /**
     * 获得真旅项目资金方ID
     * @param individual 融资客户是否是真旅项目个人客户
     */
    public static String getTzFactorId(boolean individual) {
    	return individual ? INDIVIDUAL_CONTRACT_FACTOR_ID : COMPANY_CONTRACT_FACTOR_ID;
    }

    public static final long USER_GUIDE_CONTEXT_EXPIRE_TIME = 3600 * 24;

    public static final String COMPANY_CONTRACT_TEMPLATE_PATH = "/template/travelzen/companyContractTemplateV2.ftl";
    public static final String INDIVIDUAL_CONTRACT_TEMPLATE_PATH = "/template/travelzen/individualContractTemplate.ftl";

    public static final String COMPANY_CONTRACT_NAME = "国内保理业务合同";
    public static final String INDIVIDUAL_CONTRACT_NAME = "借款合同";

    public static final String INDIVIDUAL_LOAN_PERSON_NAME
    	= TopsConfReader.getConfContent("properties/credit.properties", "tz_individual_loan_person_name", ConfScope.R);
    public static final String INDIVIDUAL_LOAN_PERSON_IDENTITY
    	= TopsConfReader.getConfContent("properties/credit.properties", "tz_individual_loan_person_identity", ConfScope.R);

    public static final int CONTRACT_EFFECTIVE_YEARS = 1; // 合同有效期，年
}
