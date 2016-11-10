package com.cana.credit.limit.service.transaction.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.credit.limit.dao.mapper.gen.CreditLimitAuditMapper;
import com.cana.credit.limit.dao.mapper.gen.CreditLimitMapper;
import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.credit.limit.dao.po.CreditLimitAudit;
import com.cana.credit.limit.dao.po.CreditLimitExample;
import com.cana.credit.limit.dao.po.CreditLimitExample.Criteria;
import com.cana.credit.limit.dao.utils.CreditLimitIDGenerator;
import com.cana.credit.limit.service.transaction.ICommonCreditLimitTransactionService;
import com.cana.vbam.common.credit.dto.limit.CreditLimitRecoveryRequstDTO;
import com.cana.vbam.common.credit.enums.CreditLimitStatus;
import com.cana.vbam.common.credit.enums.CreditMode;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

@Service
public class CommonCreditLimitTransactionServiceImpl implements ICommonCreditLimitTransactionService{
	
	@Resource
	private CreditLimitMapper creditLimitMapper;
	
	@Resource
	private CreditLimitAuditMapper creditLimitAuditMapper;

	@Override
	public CreditLimit lockCreditLimit(String memberId, String projectId, String outCustomerId, CreditMode creditMode) {
		CreditLimitExample example = new CreditLimitExample();
		CreditLimitExample.Criteria criteria = example.createCriteria();
		criteria.andProjectIdEqualTo(projectId)
				.andMemberIdEqualTo(memberId)
				.andCreditModeEqualTo(creditMode.name());
		if (StringUtils.isNotBlank(outCustomerId))
			criteria.andOutCustomerIdEqualTo(outCustomerId);
		List<CreditLimit> creditLimits = creditLimitMapper.lockByExample(example);
		if(creditLimits == null || creditLimits.isEmpty())
			throw WebException.instance(ReturnCode.TP3012);
		if(creditLimits.size() > 1)
			throw WebException.instance(ReturnCode.TP3014);
		return creditLimits.get(0);
	}

	@Override
	public void recoveryLimit(CreditLimitRecoveryRequstDTO request) {
		CreditLimit limit = lockCreditLimit(request.getMemberId(), request.getProjectId(), request.getOutCustomerId(), request.getCreditMode());
		long prevUsedLimit = limit.getUsedLimit();
		limit.setUsedLimit(prevUsedLimit - request.getLimit());
		limit.setUpdateTime(new Date());
		creditLimitMapper.updateByPrimaryKeySelective(limit);
		CreditLimitAudit audit = new CreditLimitAudit();
		audit.setId(CreditLimitIDGenerator.generateCreditLimitAuditId());
		audit.setLimitId(limit.getId());
		audit.setType(request.getAuditType().name());
		audit.setPrevTotalLimit(limit.getTotalLimit());
		audit.setTotalLimit(limit.getTotalLimit());
		audit.setPrevUsedLimit(prevUsedLimit);
		audit.setUsedLimit(limit.getUsedLimit());
		audit.setCreateTime(new Date());
		creditLimitAuditMapper.insertSelective(audit);
	}

	@Override
	public List<CreditLimit> getCreditLimitList(String projectId, CreditLimitStatus... statusList) {
		CreditLimitExample example = new CreditLimitExample();
		Criteria criteria = example.createCriteria();
		criteria.andProjectIdEqualTo(projectId);
		if(!ArrayUtils.isEmpty(statusList)){
			List<String> statusStrList = new ArrayList<>();
			for(CreditLimitStatus status : statusList)
				statusStrList.add(status.name());
			criteria.andStatusIn(statusStrList);
		}
		return creditLimitMapper.selectByExample(example);
	}

}
