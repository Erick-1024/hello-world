package com.cana.asset.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.CreditCustomMapper;
import com.cana.asset.dao.mapper.gen.CreditAuditMapper;
import com.cana.asset.dao.mapper.gen.CreditMapper;
import com.cana.asset.dao.po.Credit;
import com.cana.asset.dao.po.CreditAudit;
import com.cana.asset.dao.po.CreditAuditExample;
import com.cana.asset.dao.po.CreditExample;
import com.cana.asset.service.IAssetCreditService;
import com.cana.asset.service.convertors.CreditConvertor;
import com.cana.vbam.common.asset.dto.CreditDTO;
import com.cana.vbam.common.asset.dto.CustomerCreditDTO;
import com.cana.vbam.common.asset.dto.CustomerCreditQueryCriteria;
import com.cana.vbam.common.dto.ListResult;
import com.travelzen.framework.core.exception.WebException;

@Service
public class AssetCreditServiceImpl implements IAssetCreditService {

	@Resource
	private CreditMapper creditMapper;
	
	@Resource
	private CreditCustomMapper creditCustomMapper;
	
	@Resource
	private CreditAuditMapper creditAuditMapper;
	
	@Resource
	private CreditConvertor creditConvertor;
	
	@Override
	public ListResult<CustomerCreditDTO> getCustomers(CustomerCreditQueryCriteria crieria) {
		return ListResult.success(creditCustomMapper.searchAllCustomerOrderByCreditLimit(crieria), creditCustomMapper.countAllCustomerOrderByCreditLimit(crieria));
	}

	@Override
	public CreditDTO getCreditByBusinessContractNo(String businessContractNo, String customerId) {
		if(StringUtils.isBlank(businessContractNo))
			throw WebException.instance("业务合同号不能为空");
		if(StringUtils.isBlank(customerId))
			throw WebException.instance("客户id不能为空");
		CreditExample example = new CreditExample();
		example.createCriteria().andBusinessContractNoEqualTo(businessContractNo).andCustomerIdEqualTo(customerId);
		List<Credit> creditList = creditMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(creditList))
			return null;
		return creditConvertor.convertorCredit2DTO(creditList.get(0));
	}

	@Override
	public String getLastCreditAuditByCreditId(String creditId) {
		if(StringUtils.isBlank(creditId))
			throw WebException.instance("获取日志参数有误");
		CreditAuditExample example = new CreditAuditExample();
		example.createCriteria().andCreditIdEqualTo(creditId);
		example.setOrderByClause("create_time desc");
		example.setLimitStart(0);
		example.setLimitEnd(1);
		List<CreditAudit> auditList = creditAuditMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(auditList))
			return null;
		return auditList.get(0).getId();
	}
}
