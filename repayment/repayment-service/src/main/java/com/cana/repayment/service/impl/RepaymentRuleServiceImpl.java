package com.cana.repayment.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cana.account.api.IAccountApi;
import com.cana.repayment.dao.mapper.gen.RepaymentRuleMapper;
import com.cana.repayment.dao.po.RepaymentRule;
import com.cana.repayment.dao.po.RepaymentRuleExample;
import com.cana.repayment.service.IRepaymentRuleService;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.dto.AccountQueryCriteria;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;

@Service
public class RepaymentRuleServiceImpl implements IRepaymentRuleService{
	
	@Resource
	private RepaymentRuleMapper repaymentRuleMapper;
	
	@Resource
	private IAccountApi accountApiImpl;

	@Override
	public List<RepaymentRule> queryRepaymentRuleList(RepaymentRuleExample repaymentRuleExample){
		return repaymentRuleMapper.selectByExample(repaymentRuleExample);
	}

	@Override
	public int countRepaymentRuleList(RepaymentRuleExample repaymentRuleExample){
		return repaymentRuleMapper.countByExample(repaymentRuleExample);
	}

	@Override
	public RepaymentRule queryRepaymentRule(String ruleId) {
		return repaymentRuleMapper.selectByPrimaryKey(ruleId);
	}

	@Override
	public List<AccountDTO> queryReceivedPaymentsAccounts(String factorId,
			AccountQueryCriteria accountQueryCriteria) {
		return accountApiImpl.queryAccounts(factorId, accountQueryCriteria).getData();
	}

	@Override
	public List<CustomerDetailDTO> querySupervisorsByFactorId(String factorId) {
		return accountApiImpl.querySupervisorsByFactorId(factorId);
	}

}
