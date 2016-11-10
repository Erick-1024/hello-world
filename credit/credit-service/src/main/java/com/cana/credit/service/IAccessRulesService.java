package com.cana.credit.service;

/**
 * 准入规则service
 * @author tangyihong
 *
 */
public interface IAccessRulesService {
	
	/**
	 * 检查申请额度的客户是否符合准入规则
	 */
	public void checkApplysByAccessRules();
	
}
