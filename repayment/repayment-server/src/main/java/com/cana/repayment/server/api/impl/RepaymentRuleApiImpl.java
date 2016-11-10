package com.cana.repayment.server.api.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cana.account.api.IAccountApi;
import com.cana.member.api.IUserApi;
import com.cana.repayment.api.IRepaymentRuleApi;
import com.cana.repayment.dao.po.RepaymentRule;
import com.cana.repayment.dao.po.RepaymentRuleExample;
import com.cana.repayment.dao.po.RepaymentRuleExample.Criteria;
import com.cana.repayment.service.IRepaymentRuleService;
import com.cana.repayment.service.transaction.IRepaymentTransactionService;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.dto.AccountQueryCriteria;
import com.cana.vbam.common.account.enums.AccountStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.enums.user.UserGuideStatus;
import com.cana.vbam.common.repayment.rule.dto.RepaymentRuleDTO;
import com.cana.vbam.common.repayment.rule.dto.RepaymentRuleSearchCriteriaDTO;
import com.cana.vbam.common.repayment.rule.dto.RepaymentRuleSearchResult;
import com.cana.vbam.common.repayment.rule.enums.DeductionRule;
import com.cana.vbam.common.repayment.rule.enums.ScopeOfApplication;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.dianping.cat.Cat;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.StringUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

public class RepaymentRuleApiImpl implements IRepaymentRuleApi{
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private IRepaymentRuleService repaymentRuleServiceImpl;
	
	@Resource
	private IRepaymentTransactionService repaymentTransactionServiceImpl;
	
	@Resource
	private IUserApi userApi;
	
	@Resource
	private IAccountApi accountApi;
	
	private final String repaymentDefaultRule = "properties/repayment-rule-default.properties";

	@Override
	public ListResult<RepaymentRuleSearchResult> queryRepaymentRuleList(String masterId, 
			RepaymentRuleSearchCriteriaDTO repaymentRuleSearchCriteriaDTO){
		long startTime = System.currentTimeMillis();
		ListResult<RepaymentRuleSearchResult> result = new ListResult<RepaymentRuleSearchResult>();
		RepaymentRuleExample example = checkRepaymentRuleSearchCriteria(masterId, repaymentRuleSearchCriteriaDTO);
		List<RepaymentRule> repaymentRules =repaymentRuleServiceImpl.queryRepaymentRuleList(example);
		result.setData(convertRepaymentRuleToRepaymentRuleSearchResult(repaymentRules));
		result.setTotalNum(repaymentRuleServiceImpl.countRepaymentRuleList(example));
		Cat.logMetricForDuration("query_repayment_rule_list", System.currentTimeMillis()-startTime);
		return result;
	}
	
	private RepaymentRuleExample checkRepaymentRuleSearchCriteria(String masterId, RepaymentRuleSearchCriteriaDTO repaymentRuleSearchCriteriaDTO) {
		StringUtil.trimObjectFields(repaymentRuleSearchCriteriaDTO);
		RepaymentRuleExample example = new RepaymentRuleExample();
		example.setLimitStart((repaymentRuleSearchCriteriaDTO.getPage()-1) * repaymentRuleSearchCriteriaDTO.getPageSize());
		example.setLimitEnd(repaymentRuleSearchCriteriaDTO.getPageSize());
		example.setOrderByClause("-id");
		Criteria criteria = example.createCriteria();
		criteria.andFactorIdEqualTo(masterId);
		if(StringUtils.isNotBlank(repaymentRuleSearchCriteriaDTO.getId())){
			criteria.andIdEqualTo(repaymentRuleSearchCriteriaDTO.getId());
		}
		if(null != repaymentRuleSearchCriteriaDTO.getScopeOfApplication()){
			if(ScopeOfApplication.DEFAULT == repaymentRuleSearchCriteriaDTO.getScopeOfApplication()){
				criteria.andFianceCustomerCompanysIsNull();
			}else{
				criteria.andFianceCustomerCompanysIsNotNull();
			}
		}
		return example;
	}
	
	private List<RepaymentRuleSearchResult> convertRepaymentRuleToRepaymentRuleSearchResult(List<RepaymentRule> repaymentRules){
		if(CollectionUtils.isEmpty(repaymentRules)){
			throw WebException.instance("未找到相关还款规则");
		}
		List<RepaymentRuleSearchResult> repaymentRuleSearchResults = new ArrayList<RepaymentRuleSearchResult>(repaymentRules.size());
		for (int i = 0; i < repaymentRules.size(); i++) {
			RepaymentRuleSearchResult repaymentRuleSearchResult = new RepaymentRuleSearchResult();
			BeanUtils.copyProperties(repaymentRules.get(i), repaymentRuleSearchResult);
			repaymentRuleSearchResults.add(repaymentRuleSearchResult);
		}
		return repaymentRuleSearchResults;
	}
	
	@Override
	public RepaymentRuleDTO generateDefaultRepaymentRule(String factorId) {
		RepaymentRuleDTO repaymentRuleDTO = new RepaymentRuleDTO();
//		repaymentRuleDTO.setFactorId(factorId);
		repaymentRuleDTO.setDeductionTime(TopsConfReader.getConfContent(repaymentDefaultRule, "deduction.time", ConfScope.G));
		repaymentRuleDTO.setDeductionRule(DeductionRule.valueOf(TopsConfReader.getConfContent(repaymentDefaultRule, "deduction.rule", ConfScope.G)));
		repaymentRuleDTO.setExtensionDays(Integer.parseInt(TopsConfReader.getConfContent(repaymentDefaultRule, "extension.days", ConfScope.G)));
		repaymentRuleDTO.setExtensionRatio(TopsConfReader.getConfContent(repaymentDefaultRule, "extension.ratio", ConfScope.G));
		repaymentRuleDTO.setPenaltyRate(TopsConfReader.getConfContent(repaymentDefaultRule, "penalty.rate", ConfScope.G));
		repaymentRuleDTO.setEarlyRepaymentChargeRatio(TopsConfReader.getConfContent(repaymentDefaultRule, "early.repayment.charge.ratio", ConfScope.G));
		repaymentRuleDTO.setAccountAccumulationTime(TopsConfReader.getConfContent(repaymentDefaultRule, "account.accumulation.time", ConfScope.G));
		return repaymentRuleDTO;
	}

	@Override
	public String generateRuleId(){
		return DateTimeUtil.datetime12()
				+ seqGen.getNextSeq(Constants.REPAYMENT_RULE_ID, 4);
	}
	
	@Override
	public void addRepaymentRule(RepaymentRuleDTO repaymentRuleDTO) {
		RepaymentRule repaymentRule = new RepaymentRule();
		if(isAddDefaultRepaymentRule(repaymentRuleDTO)){
			accountApi.setFactorTransferInAccount(repaymentRuleDTO.getFactorId(), repaymentRuleDTO.getFactorTransferInAccountNo());
			RepaymentRuleExample example = new RepaymentRuleExample();
			example.createCriteria().andFactorIdEqualTo(repaymentRuleDTO.getFactorId()).andFianceCustomerIdsIsNull();
			List<RepaymentRule> repaymentRules = repaymentRuleServiceImpl.queryRepaymentRuleList(example);
			if(CollectionUtils.isNotEmpty(repaymentRules)){
				for (RepaymentRule oldRepaymentRule : repaymentRules) {
					repaymentTransactionServiceImpl.deleteRepaymentRule(oldRepaymentRule.getId());
				}
			}
		}else {
			RepaymentRuleExample example = new RepaymentRuleExample();
			example.createCriteria().andFactorIdEqualTo(repaymentRuleDTO.getFactorId()).andFianceCustomerIdsEqualTo(repaymentRuleDTO.getFianceCustomerIds());
			if(repaymentRuleServiceImpl.countRepaymentRuleList(example) > 0){
				throw WebException.instance("重复提交还款规则");
			}
		}
		BeanUtils.copyProperties(repaymentRuleDTO, repaymentRule);
		repaymentRule.setFactorTransferInAccountNo(repaymentRuleDTO.getFactorTransferInAccountNo());
		repaymentRule.setDeductionRule(repaymentRuleDTO.getDeductionRule().name());
		repaymentRule.setExtensionDays(repaymentRuleDTO.getExtensionDays());
		repaymentRule.setExtensionRatio(MoneyArithUtil.convertStringToInterestRate(repaymentRuleDTO.getExtensionRatio()));
		repaymentRule.setPenaltyRate(MoneyArithUtil.convertStringToInterestRate(repaymentRuleDTO.getPenaltyRate()));
		repaymentRule.setEarlyRepaymentChargeRatio(MoneyArithUtil.convertStringToInterestRate(repaymentRuleDTO.getEarlyRepaymentChargeRatio()));
		repaymentRule.setCreateTime(new Date());
		repaymentTransactionServiceImpl.addRepaymentRule(repaymentRule);
	}
	
	private boolean isAddDefaultRepaymentRule(RepaymentRuleDTO repaymentRuleDTO){
		CustomerDetailDTO factorDetail = userApi.queryCustomerDetail(repaymentRuleDTO.getFactorId());
		if(StringUtils.isBlank(repaymentRuleDTO.getFianceCustomerIds()) && StringUtils.isBlank(repaymentRuleDTO.getFianceCustomerCompanys())){
			if(null != factorDetail.getGuideStatus() && factorDetail.getGuideStatus().contains(UserGuideStatus.APPLIED_ACCOUNT)){
				return true;
			}else {
				throw WebException.instance("您已经创建过默认还款规则，不能重复创建");
			}
		}
		return false;
	}
	
	@Override
	public void modifyRepaymentRule(RepaymentRuleDTO repaymentRuleDTO) {
		RepaymentRule repaymentRule = new RepaymentRule();
		BeanUtils.copyProperties(repaymentRuleDTO, repaymentRule);
		repaymentRule.setDeductionRule(repaymentRuleDTO.getDeductionRule().name());
		repaymentRule.setExtensionDays(repaymentRuleDTO.getExtensionDays());
		repaymentRule.setExtensionRatio(MoneyArithUtil.convertStringToInterestRate(repaymentRuleDTO.getExtensionRatio()));
		repaymentRule.setPenaltyRate(MoneyArithUtil.convertStringToInterestRate(repaymentRuleDTO.getPenaltyRate()));
		repaymentRule.setEarlyRepaymentChargeRatio(MoneyArithUtil.convertStringToInterestRate(repaymentRuleDTO.getEarlyRepaymentChargeRatio()));
		repaymentRule.setUpdateTime(new Date());
		repaymentTransactionServiceImpl.modifyRepaymentRule(repaymentRule);
		if(null == repaymentRule.getFianceCustomerIds()){
			accountApi.setFactorTransferInAccount(repaymentRule.getFactorId(), repaymentRule.getFactorTransferInAccountNo());
			RepaymentRuleExample repaymentRuleExample = new RepaymentRuleExample();
			repaymentRuleExample.createCriteria().andFactorIdEqualTo(repaymentRuleDTO.getFactorId()).andFianceCustomerIdsIsNotNull();
			List<RepaymentRule> repaymentRules = repaymentRuleServiceImpl.queryRepaymentRuleList(repaymentRuleExample);
			for (RepaymentRule otherRepaymentRule : repaymentRules) {
				otherRepaymentRule.setFactorTransferInAccountNo(repaymentRuleDTO.getFactorTransferInAccountNo());
				repaymentTransactionServiceImpl.modifyRepaymentRule(otherRepaymentRule);
			}
		}
	}

	@Override
	public RepaymentRuleDTO queryRepaymentRule(String factorId, String ruleId) {
		long startTime = System.currentTimeMillis();
		RepaymentRuleDTO repaymentRuleDTO = convertRepaymentRuleToRepaymentRuleDTO(repaymentRuleServiceImpl.queryRepaymentRule(ruleId));
		if(!factorId.equals(repaymentRuleDTO.getFactorId())){
			throw WebException.instance("您没有查看该还款规则的权限！");
		}
		Cat.logMetricForDuration("query_repayment_rule_single", System.currentTimeMillis()-startTime);
		return repaymentRuleDTO;
	}

	@Override
	public List<AccountDTO> queryReceivedPaymentsAccounts(String factorId) {
		long startTime = System.currentTimeMillis();
		AccountQueryCriteria accountQueryCriteria = new AccountQueryCriteria();
		accountQueryCriteria.setPageSize(Integer.MAX_VALUE);
		accountQueryCriteria.setAccountStatus(AccountStatus.NORMAL);
		accountQueryCriteria.setAccountType(AccountType.GENERAL);
		accountQueryCriteria.setSupervisoryStatus(AccountSupervisionStatus.NO_SUPERVISION);
		Cat.logMetricForDuration("query_received_payments_accounts", System.currentTimeMillis()-startTime);
		return repaymentRuleServiceImpl.queryReceivedPaymentsAccounts(factorId, accountQueryCriteria);
	}

	@Override
	public List<CustomerDetailDTO> querySupervisorsByFactorId(String factorId) {
		long startTime = System.currentTimeMillis();
		RepaymentRuleExample example = new RepaymentRuleExample();
		example.createCriteria().andFactorIdEqualTo(factorId).andFianceCustomerIdsIsNotNull();
		List<RepaymentRule> repaymentRules = repaymentRuleServiceImpl.queryRepaymentRuleList(example);
		List<CustomerDetailDTO> customerDetailDTOs = repaymentRuleServiceImpl.querySupervisorsByFactorId(factorId);
		if(CollectionUtils.isNotEmpty(repaymentRules)){
			String fianceCustomerIds = "";
			for (RepaymentRule repaymentRule : repaymentRules) {
				fianceCustomerIds += repaymentRule.getFianceCustomerIds() + ",";
			}
			String[] fianceCustomerIdArray = fianceCustomerIds.split(",");
			for (String fianceCustomerId : fianceCustomerIdArray) {
				CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO();
				customerDetailDTO.setId(fianceCustomerId);
				customerDetailDTOs.remove(customerDetailDTO);
			}
		}
		Cat.logMetricForDuration("query_supervisors_by_factor_id", System.currentTimeMillis()-startTime);
		return customerDetailDTOs;
	}

	@Override
	public RepaymentRuleDTO queryDefaultRepaymentRule(String factorId) {
		long startTime = System.currentTimeMillis();
		RepaymentRuleExample repaymentRuleExample = new RepaymentRuleExample();
		repaymentRuleExample.createCriteria().andFactorIdEqualTo(factorId).andFianceCustomerIdsIsNull();
		List<RepaymentRule> repaymentRules = repaymentRuleServiceImpl.queryRepaymentRuleList(repaymentRuleExample);
		if(CollectionUtils.isEmpty(repaymentRules)){
			throw WebException.instance("您的默认还款规则不存在");
		}
		Cat.logMetricForDuration("query_default_repayment_rule", System.currentTimeMillis()-startTime);
		return convertRepaymentRuleToRepaymentRuleDTO(repaymentRules.get(0));
	}
	
	private RepaymentRuleDTO convertRepaymentRuleToRepaymentRuleDTO(RepaymentRule repaymentRule){
		RepaymentRuleDTO repaymentRuleDTO = new RepaymentRuleDTO();
		BeanUtils.copyProperties(repaymentRule, repaymentRuleDTO);
		repaymentRuleDTO.setDeductionRule(DeductionRule.valueOf(repaymentRule.getDeductionRule()));
		repaymentRuleDTO.setExtensionDays(repaymentRule.getExtensionDays());
		repaymentRuleDTO.setExtensionRatio(MoneyArithUtil.convertInterestRateToString(repaymentRule.getExtensionRatio()));
		repaymentRuleDTO.setPenaltyRate(MoneyArithUtil.convertInterestRateToString(repaymentRule.getPenaltyRate()));
		repaymentRuleDTO.setEarlyRepaymentChargeRatio(MoneyArithUtil.convertInterestRateToString(repaymentRule.getEarlyRepaymentChargeRatio()));
		return repaymentRuleDTO;
	}

	@Override
	public List<String> getNotTransferInAccountNos(List<String> accountNos) {
		return repaymentTransactionServiceImpl.getNotTransferInAccountNos(accountNos);
	}

}
