package com.cana.account.service.utils;

import org.apache.commons.lang3.StringUtils;

import com.cana.member.dao.po.User;
import com.cana.vbam.common.account.dto.AccountQueryCriteria;
import com.cana.vbam.common.member.enums.user.UserType;

public class AccountQueryCriteriaUtil {

    /**
     * 根据当前登录客户和账号查询条件，返回一个用于查询账号列表的条件类
     */
    public static AccountQueryCriteria getValidCriteria(User customer, AccountQueryCriteria criteria) {
        UserType userType = UserType.valueOf(customer.getUserType());

        AccountQueryCriteria validCriteria = new AccountQueryCriteria();
        validCriteria.setPage(criteria.getPage() < 1 ? 1 : criteria.getPage());
        validCriteria.setPageSize(criteria.getPageSize() < 1 ? 10 : criteria.getPageSize());
        if (StringUtils.isNotBlank(criteria.getAccountName())) {
            validCriteria.setAccountName("%" + criteria.getAccountName() + "%");
        }
        if (StringUtils.isNotBlank(criteria.getAccountNo())) {
            validCriteria.setAccountNo(criteria.getAccountNo());
        }
        if (UserType.FACTOR.equals(userType)) {
            validCriteria.setFactorId(customer.getId());
        } else if (UserType.FINACE.equals(userType)) {
            validCriteria.setFinaceId(customer.getId());
        } else if (UserType.CORECOMPANY.equals(userType)) {
            validCriteria.setCompanyId(customer.getId());
        } else if(UserType.CANA.equals(userType)){//CANA
        	if(StringUtils.isNotBlank(criteria.getCompanyId()))//查“我的账户”
        		validCriteria.setCompanyId(criteria.getCompanyId());
        	//否则查“账户列表”
        } 
        if (StringUtils.isNotBlank(criteria.getFactorName())) {
            validCriteria.setFactorName("%" + criteria.getFactorName() + "%");
        }
        if (StringUtils.isNotBlank(criteria.getFinaceName())) {
            validCriteria.setFinaceName("%" + criteria.getFinaceName() + "%");
        }
        if (StringUtils.isNotBlank(criteria.getCoreCompanyName())) {
            validCriteria.setCoreCompanyName("%" + criteria.getCoreCompanyName() + "%");
        }
        validCriteria.setAccountStatus(criteria.getAccountStatus());
        validCriteria.setAccountType(criteria.getAccountType());
        validCriteria.setAccumulationStatus(criteria.getAccumulationStatus());
        validCriteria.setSupervisoryStatus(criteria.getSupervisoryStatus());
        validCriteria.setIsTransferInAccount(criteria.getIsTransferInAccount());
        return validCriteria;
    }

	public static AccountQueryCriteria getValidCriteria(User customer) {
		UserType userType = UserType.valueOf(customer.getUserType());
		AccountQueryCriteria validCriteria = new AccountQueryCriteria();
        if (UserType.FACTOR.equals(userType)) {
            validCriteria.setFactorId(customer.getId());
        } else if (UserType.FINACE.equals(userType)) {
            validCriteria.setFinaceId(customer.getId());
        } else if (UserType.CORECOMPANY.equals(userType)) {
            validCriteria.setCompanyId(customer.getId());
        } else if(UserType.CANA.equals(userType)){//CANA
        	if(StringUtils.isNotBlank(customer.getId()))//查“我的账户”
        		validCriteria.setCompanyId(customer.getId());
        	//否则查“账户列表”
        } 
        return validCriteria;
	}
}
