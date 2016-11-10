package com.cana.yundaex.service;

/**
 * 系统审核rules
 * @author xiaoyu
 *
 */
public interface IYundaexAutomaticRulesService {

	/**
	 * 检查申请额度的客户是否符合系统审查规则
	 */
	void checkApplysByAutomaticRules();

	/**
	 * 进行系统用户评级
	 */
	void calculateCustomerGrade();

}
