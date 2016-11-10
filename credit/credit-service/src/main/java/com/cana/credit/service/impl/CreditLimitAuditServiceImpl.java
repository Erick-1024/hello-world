package com.cana.credit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.credit.dao.mapper.CreditLimitAuditCustomMapper;
import com.cana.credit.limit.dao.mapper.gen.CreditLimitAuditMapper;
import com.cana.credit.limit.dao.mapper.gen.CreditLimitMapper;
import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.credit.limit.dao.po.CreditLimitExample;
import com.cana.credit.service.ICreditLimitAuditService;
import com.cana.credit.service.convertors.CreditLimitConvertor;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitDTO;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitQueryDTO;
import com.cana.vbam.common.credit.dto.trade.CreditUsedLimit;

@Service
public class CreditLimitAuditServiceImpl implements ICreditLimitAuditService {

	@Resource
	private CreditLimitAuditCustomMapper creditLimitAuditCustomerMapper;
	@Resource
	private CreditLimitMapper creditLimitMapper;

	@Resource
	private CreditLimitAuditMapper creditLimitAuditMapper;

	@Override
	public List<CreditUsedLimit> queryUsedLimit(CreditUsedLimitQueryDTO creditUsedLimitQueryDTO) {
		return creditLimitAuditCustomerMapper.queryUsedLimit(creditUsedLimitQueryDTO);
	}

	@Override
	public List<CreditUsedLimitDTO> queryYundaUsedLimit(CreditUsedLimitQueryDTO creditUsedLimitQueryDTO) {
		CreditLimitExample creditLimitExample = new CreditLimitExample();
		CreditLimitExample.Criteria criteria = creditLimitExample.createCriteria();
		criteria.andProjectIdEqualTo(creditUsedLimitQueryDTO.getProductId());
		if(StringUtils.isNotBlank(creditUsedLimitQueryDTO.getCustomerName())){
			criteria.andCompanyNameLike("%" + creditUsedLimitQueryDTO.getCustomerName() + "%");
		}
		creditLimitExample.setOrderByClause("create_time desc");
		creditLimitExample.setLimitStart((creditUsedLimitQueryDTO.getPage() - 1) * creditUsedLimitQueryDTO.getPageSize());
		creditLimitExample.setLimitEnd(creditUsedLimitQueryDTO.getPageSize());
		List<CreditLimit> creditLimits = creditLimitMapper.selectByExample(creditLimitExample);
		return CreditLimitConvertor.convertCreditUsedLimitDTO(creditLimits);
	}

	@Override
	public int queryYundaUsedLimitCount(CreditUsedLimitQueryDTO creditUsedLimitQueryDTO) {
		CreditLimitExample creditLimitExample = new CreditLimitExample();
		CreditLimitExample.Criteria criteria = creditLimitExample.createCriteria();
		criteria.andProjectIdEqualTo(creditUsedLimitQueryDTO.getProductId());
		if(StringUtils.isNotBlank(creditUsedLimitQueryDTO.getCustomerName())){
			criteria.andCompanyNameLike("%" + creditUsedLimitQueryDTO.getCustomerName() + "%");
		}
		creditLimitExample.setOrderByClause("create_time desc");
		creditLimitExample.setLimitStart((creditUsedLimitQueryDTO.getPage() - 1) * creditUsedLimitQueryDTO.getPageSize());
		creditLimitExample.setLimitEnd(creditUsedLimitQueryDTO.getPageSize());
		int count = creditLimitMapper.countByExample(creditLimitExample);
		return count;
	}
	

}
