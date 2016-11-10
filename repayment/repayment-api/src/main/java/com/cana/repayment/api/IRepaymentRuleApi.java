package com.cana.repayment.api;

import java.util.List;

import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.repayment.rule.dto.RepaymentRuleDTO;
import com.cana.vbam.common.repayment.rule.dto.RepaymentRuleSearchCriteriaDTO;
import com.cana.vbam.common.repayment.rule.dto.RepaymentRuleSearchResult;

public interface IRepaymentRuleApi {
	
	public ListResult<RepaymentRuleSearchResult> queryRepaymentRuleList(String masterId, RepaymentRuleSearchCriteriaDTO repaymentRuleSearchCriteriaDTO);
	
	/**
	 * 生成默认还款规则的DTO
	 * @param factorId
	 * @return
	 */
	public RepaymentRuleDTO generateDefaultRepaymentRule(String factorId);
	
	/**
	 * 生成还款规则id
	 * @return
	 */
	public String generateRuleId();

	/**
	 * 新增还款规则
	 * @param repaymentRuleDTO
	 */
	public void addRepaymentRule(RepaymentRuleDTO repaymentRuleDTO);
	
	/**
	 * 修改还款规则
	 * @param repaymentRuleDTO
	 */
	public void modifyRepaymentRule(RepaymentRuleDTO repaymentRuleDTO);
	
	/**
	 * 查询还款规则
	 * @param ruleId
	 * @param factorId
	 * @return
	 */
	public RepaymentRuleDTO queryRepaymentRule(String factorId, String ruleId);
	
	/**
	 * 查询保理商的一般正常户(汇款账号)
	 * @param factorId
	 * @return
	 */
	public List<AccountDTO> queryReceivedPaymentsAccounts(String factorId);
	
	/**
	 * 按操作查询保理商的融资客户
	 * @param factorId
	 * @return
	 */
	public List<CustomerDetailDTO> querySupervisorsByFactorId(String factorId);
	
	/**
	 * 查询保理商的默认还款规则
	 * @param factorId
	 * @return
	 */
	public RepaymentRuleDTO queryDefaultRepaymentRule(String factorId);
	
	/**
	 * 获取银行账号列表中不是回款的银行账号列表
	 * @param accountNos
	 * @return
	 */
	public List<String> getNotTransferInAccountNos(List<String> accountNos);
}
