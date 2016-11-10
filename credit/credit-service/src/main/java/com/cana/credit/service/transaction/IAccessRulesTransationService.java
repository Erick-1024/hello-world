package com.cana.credit.service.transaction;

import java.util.List;

import com.cana.credit.dao.po.AccessRule;
import com.cana.credit.dao.po.CustomerApply;
import com.travelzen.framework.core.common.ReturnClass;

public interface IAccessRulesTransationService {

	public boolean checkApplyByAccessRules(CustomerApply apply);
	
	/**
	 * (暂时废弃)
	 * 检查合作期限，逾期
	 * @param accessRule　准入规则
	 * @param customerId 真旅客户id
	 * @return 不符合准入规则的错误list，如果返回的list的size为0,就说明准入通过
	 */
	public List<ReturnClass> checkBaseApplyData(AccessRule accessRule,String customerId);
}
