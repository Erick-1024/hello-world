package com.cana.repayment.service;

import java.util.List;

import com.cana.repayment.dao.po.RepaymentRule;
import com.cana.repayment.dao.po.RepaymentRuleExample;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.dto.AccountQueryCriteria;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;

public interface IRepaymentRuleService {

	/**
	 * 查询还款规则
	 * @return
	 * @throws Exception
	 */
	public List<RepaymentRule> queryRepaymentRuleList(RepaymentRuleExample repaymentRuleExample);
	
	/**
	 * 查询还款规则总数
	 * @param repaymentRuleExample
	 * @return
	 * @throws Exception
	 */
	public int countRepaymentRuleList(RepaymentRuleExample repaymentRuleExample);
	
	/**
	 * 查询还款规则
	 * @param ruleId
	 * @return
	 */
	public RepaymentRule queryRepaymentRule(String ruleId);
	
	/**
	 * 查询保理商的一般正常户(汇款账号)
	 * @param factorId
	 * @param accountQueryCriteria
	 * @return
	 */
	public List<AccountDTO> queryReceivedPaymentsAccounts(String factorId, AccountQueryCriteria accountQueryCriteria);
	
	/**
	 * 查询保理商的所有融资客户
	 * @param factorId
	 * @return
	 */
	public List<CustomerDetailDTO> querySupervisorsByFactorId(String factorId);
	
}
