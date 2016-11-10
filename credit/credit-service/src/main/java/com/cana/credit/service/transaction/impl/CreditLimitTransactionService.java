package com.cana.credit.service.transaction.impl;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.credit.dao.mapper.CreditTableLockMapper;
import com.cana.credit.dao.mapper.gen.CustomerApplyMapper;
import com.cana.credit.dao.mapper.gen.OutCustomerMapper;
import com.cana.credit.dao.po.CustomerApply;
import com.cana.credit.dao.po.CustomerApplyExample;
import com.cana.credit.dao.po.OutCustomerExample;
import com.cana.credit.limit.dao.mapper.gen.CreditLimitAuditMapper;
import com.cana.credit.limit.dao.mapper.gen.CreditLimitMapper;
import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.credit.limit.dao.po.CreditLimitAudit;
import com.cana.credit.limit.dao.po.CreditLimitExample;
import com.cana.credit.limit.dao.utils.CreditLimitIDGenerator;
import com.cana.credit.limit.service.transaction.ICommonCreditLimitTransactionService;
import com.cana.credit.service.IRetryTaskService;
import com.cana.credit.service.transaction.ICreditLimitTransactionService;
import com.cana.credit.service.utils.CreditLimitCalculateUtil3;
import com.cana.vbam.common.credit.dto.limit.CalculateLimitResult;
import com.cana.vbam.common.credit.enums.AccessAutomaticState;
import com.cana.vbam.common.credit.enums.AccessManualState;
import com.cana.vbam.common.credit.enums.CreditLimitAuditType;
import com.cana.vbam.common.credit.enums.CreditLimitGenerateState;
import com.cana.vbam.common.credit.enums.CreditLimitStatus;
import com.cana.vbam.common.credit.enums.CreditMode;
import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.utils.Constants;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnClass;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

@Service
public class CreditLimitTransactionService implements ICreditLimitTransactionService {
	
	private static final Logger logger = LoggerFactory.getLogger(CreditLimitTransactionService.class);
	
	@Resource
	private CreditLimitMapper creditLimitMapper;
	
	@Resource
	private IRetryTaskService retryTaskServiceImpl;
	
	@Resource
	private OutCustomerMapper outCustomerMapper;
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
    private CustomerApplyMapper customerApplyMapper;
	
	@Resource
	private CreditTableLockMapper creditTableLockMapper;
	
	@Resource
	private CreditLimitAuditMapper creditLimitAuditMapper;
	
	@Resource
	private ICommonCreditLimitTransactionService commonCreditLimitTransactionService;
	
	@Override
	@Deprecated
	public List<CreditLimit> activateCreditLimit(String memberId) {
		CreditLimitExample example = new CreditLimitExample();
		example.createCriteria().andMemberIdEqualTo(memberId)
				.andProjectIdEqualTo(Constants.TRAVELZEN_FINANCE_PRODUCT_ID)
				.andCreditModeEqualTo(CreditMode.SYNTHETICAL.name());
		List<CreditLimit> limits = creditLimitMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(limits))
			throw WebException.instance("该客户无额度需要激活");

		List<CreditLimit> activeLimits = Lists.newArrayList();
		for (CreditLimit limit : limits) {
			CreditLimit lockedLimit = creditLimitMapper.lockByPrimaryKey(limit.getId());
			
			if(!CreditLimitStatus.INACTIVE.name().equals(lockedLimit.getStatus()))
				continue;
			lockedLimit.setStatus(CreditLimitStatus.NORMAL.name());
			lockedLimit.setEffectiveDate(new Date());
			lockedLimit.setUpdateTime(lockedLimit.getEffectiveDate());
			creditLimitMapper.updateByPrimaryKey(lockedLimit);
			activeLimits.add(lockedLimit);
			retryTaskServiceImpl.createCreditLimiteffect(Institution.travelzen, lockedLimit.getOutCustomerId(), lockedLimit.getTotalLimit(), ReturnCode.SUCCESS.getRetCode(), ReturnCode.SUCCESS.getRetMsg(), getLimitNotifyUrlByCreditLimit(lockedLimit));
		}

		return activeLimits;
	}

	@Override
	public CreditLimit activateCreditLimitByLimitId(String limitId) {
		CreditLimit creditLimit = creditLimitMapper.lockByPrimaryKey(limitId);
		if(CreditLimitStatus.NORMAL.name().equals(creditLimit.getStatus()))
			return creditLimit;
		if(!CreditLimitStatus.INACTIVE.name().equals(creditLimit.getStatus()))
			throw WebException.instance(ReturnCode.TP3030);
		creditLimit.setStatus(CreditLimitStatus.NORMAL.name());
		creditLimit.setEffectiveDate(new Date());
		creditLimit.setUpdateTime(creditLimit.getEffectiveDate());
		creditLimitMapper.updateByPrimaryKey(creditLimit);
		
		retryTaskServiceImpl.createCreditLimiteffect(Institution.travelzen, creditLimit.getOutCustomerId(), creditLimit.getTotalLimit(), ReturnCode.SUCCESS.getRetCode(), ReturnCode.SUCCESS.getRetMsg(), getLimitNotifyUrlByCreditLimit(creditLimit));

		return creditLimit;
	}

	@Override
	public CreditLimit getCreditLimit(String limitId) {
		return creditLimitMapper.selectByPrimaryKey(limitId);
	}
	
	@Override
	public CalculateLimitResult calculateApplyCreditLimit(CustomerApply customerApply) {
		OutCustomerExample outCustomerExample = new OutCustomerExample();
    	outCustomerExample.createCriteria().andOutCustomerIdEqualTo(customerApply.getTzCustomerId()).andInstitutionIdEqualTo(Institution.travelzen.name());
    	CalculateLimitResult limitResult = new CalculateLimitResult(0l);
    	if(customerApply.getInWhitelist())
    		limitResult = CreditLimitCalculateUtil3.calculateCreditLimitByCustomerApplyId(customerApply);
    	if(limitResult.getFinalLimit() <= 0){//没有额度 => 自动准入不通过
    		ReturnClass returnClass = new ReturnClass(ReturnCode.TP0024,limitResult.getFinalLimit().toString());
    		customerApply.setAccessAutomaticState(AccessAutomaticState.NOTACCESS.name());
    		customerApply.setAutomaticAuditRemarks(returnClass.getMessage());
    		customerApply.setAccessManualState(null);
    		customerApply.setCreditLimitGenerateState(null);
    		customerApplyMapper.updateByPrimaryKey(customerApply);
	    	retryTaskServiceImpl.createAuditResultNotify(Institution.travelzen, customerApply.getTzCustomerId(), ReturnCode.TP0024.getRetCode(), returnClass.getMessage(), customerApply.getAuditNotifyUrl());
    	}else{//有额度
    		customerApply.setCreditLimitGenerateState(CreditLimitGenerateState.FINANCE.name());
	        insertCreditLimit(customerApply, outCustomerMapper.selectByExample(outCustomerExample).get(0).getMemberId(), limitResult);
	    	customerApplyMapper.updateByPrimaryKeySelective(customerApply);
	    	retryTaskServiceImpl.createAuditResultNotify(Institution.travelzen, customerApply.getTzCustomerId(), ReturnCode.SUCCESS.getRetCode(), ReturnCode.SUCCESS.getRetMsg(), customerApply.getAuditNotifyUrl());
	    }
        return limitResult;
	}
	
	/****
     * 插入数据到credit_limit表
     * @param customerApply
     *            申请
     * @param memberId
     *            cana的用户Id
     * @param limit
     *            分配的额度
     */
    private void insertCreditLimit(CustomerApply customerApply, String memberId, CalculateLimitResult limit) {
        CreditLimit creditLimit = new CreditLimit();
        creditLimit.setId(CreditLimitIDGenerator.generateCreditLimitId());
        creditLimit.setMemberId(memberId);
        creditLimit.setProjectId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
        creditLimit.setCompanyName(customerApply.getCompanyName());
        creditLimit.setCreditMode(CreditMode.SYNTHETICAL.name());
        creditLimit.setTotalLimit(limit.getFinalLimit());
        creditLimit.setUsedLimit(0l);
        creditLimit.setStatus(CreditLimitStatus.INACTIVE.name());
        creditLimit.setOutCustomerId(customerApply.getTzCustomerId());
        creditLimit.setOutCustomerName(customerApply.getTzCustomerName());
        creditLimit.setCreateTime(new Date());
        creditLimit.setUpdateTime(creditLimit.getCreateTime());
        creditLimitMapper.insertSelective(creditLimit);
        
        CreditLimitAudit audit = new CreditLimitAudit();
        audit.setId(CreditLimitIDGenerator.generateCreditLimitAuditId());
        audit.setLimitId(creditLimit.getId());
        audit.setType(CreditLimitAuditType.TOTAL.name());
        audit.setPrevTotalLimit(0l);
        audit.setTotalLimit(creditLimit.getTotalLimit());
        audit.setPrevUsedLimit(0l);
        audit.setUsedLimit(0l);
        audit.setCreateTime(new Date());
        creditLimitAuditMapper.insertSelective(audit);
    }

	@Override
	public void insertCreditLimitAudit(CreditLimit limit, CreditLimitAuditType creditLimitAuditType,Long newUsedLimit, String tradeId) {
		CreditLimitAudit audit = new CreditLimitAudit();
		audit.setId(CreditLimitIDGenerator.generateCreditLimitAuditId());
		audit.setLimitId(limit.getId());
		audit.setType(creditLimitAuditType.name());
		audit.setPrevTotalLimit(limit.getTotalLimit());
		audit.setTotalLimit(limit.getTotalLimit());
		audit.setPrevUsedLimit(limit.getUsedLimit() == null ? 0l : limit.getUsedLimit());
		audit.setUsedLimit(newUsedLimit);
		audit.setTradeId(tradeId);
		audit.setCreateTime(new Date());
		creditLimitAuditMapper.insertSelective(audit);
	}
	
	private String getLimitNotifyUrlByCreditLimit(CreditLimit creditLimit) {
		CustomerApplyExample customerApplyExample = new CustomerApplyExample();
		customerApplyExample.createCriteria().andTzCustomerIdEqualTo(creditLimit.getOutCustomerId()).andAccessManualStateEqualTo(AccessManualState.ACCESS.name());
		return customerApplyMapper.selectByExample(customerApplyExample).get(0).getLimitNotifyUrl();
	}
	
	
	/**
	 * 根据memberId获取该用户的额度记录
	 * @param memberId cana的用户Id
	 * @return
	 */
	@Override
	public CreditLimit getCreditLimitByMemberId(String memberId, String outCustomerId){
		CreditLimit creditLimit = commonCreditLimitTransactionService.lockCreditLimit(memberId, Constants.TRAVELZEN_FINANCE_PRODUCT_ID, outCustomerId, CreditMode.SYNTHETICAL);
		logger.info("额度记录为:"+new Gson().toJson(creditLimit));
		if(!CreditLimitStatus.NORMAL.name().equals(creditLimit.getStatus()))
			throw WebException.instance(ReturnCode.TP3015);
		return creditLimit;	
	}
}
