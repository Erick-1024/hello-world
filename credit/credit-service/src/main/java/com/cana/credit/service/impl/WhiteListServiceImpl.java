package com.cana.credit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cana.credit.dao.mapper.WhiteCustomerAndRuleCustomMapper;
import com.cana.credit.dao.mapper.gen.WhiteCustomerMapper;
import com.cana.credit.dao.mapper.gen.WhiteCustomerRuleMapper;
import com.cana.credit.dao.po.WhiteCustomer;
import com.cana.credit.dao.po.WhiteCustomerExample;
import com.cana.credit.dao.po.WhiteCustomerRule;
import com.cana.credit.dao.po.WhiteCustomerRuleExample;
import com.cana.credit.service.IWhiteListService;
import com.cana.credit.service.convertors.WhiteCustomerRuleConvertor;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerDTO;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerParamDTO;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerRuleDTO;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerRuleParamDTO;
import com.google.common.collect.Lists;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.exception.WebException;

@Service
public class WhiteListServiceImpl implements IWhiteListService {

	@Resource
	private WhiteCustomerMapper whiteCustomerMapper;
	@Resource
	private WhiteCustomerRuleMapper whiteCustomerRuleMapper;
	@Resource
	private WhiteCustomerAndRuleCustomMapper whiteCustomerAndRuleCustomMapper;
	
	@Override
	public boolean isAvailableWhiteCustomer(String customerId) {
		return whiteCustomerAndRuleCustomMapper.getAvailableWhiteCustomerIds().contains(customerId);
	}

	@Override
	public PageList<WhiteCustomerRuleDTO> getWhiteCustomerRules(WhiteCustomerRuleParamDTO param) {
		if (param.getPage() < 0 || param.getPageSize() < 1) {
			throw WebException.instance("数据非法");
		}
		WhiteCustomerRuleExample ex = new WhiteCustomerRuleExample();
		WhiteCustomerRuleExample.Criteria criteria = ex.createCriteria();
		criteria.andBatchNoGreaterThanOrEqualTo(getMaxWhiteCustomerRuleBatchNo() - 2);
		if (param.getBatchNo() != null) {
			criteria.andBatchNoEqualTo(param.getBatchNo());
		}
		if (param.getMinCreateTime() != null) {
			criteria.andCreateTimeGreaterThan(param.getMinCreateTime());
		}
		if (param.getMaxCreateTime() != null) {
			criteria.andCreateTimeLessThan(param.getMaxCreateTime());
		}
		ex.setOrderByClause("batch_no desc");
		ex.setLimitStart((param.getPage() - 1) * param.getPageSize());
		ex.setLimitEnd(param.getPageSize());
		List<WhiteCustomerRule> rules = whiteCustomerRuleMapper.selectByExample(ex);
		int count = whiteCustomerRuleMapper.countByExample(ex);
		List<WhiteCustomerRuleDTO> dtos = WhiteCustomerRuleConvertor.PO2DTO4WhiteCustomerRule(rules);
		PageList<WhiteCustomerRuleDTO> result = new PageList<WhiteCustomerRuleDTO>();
		result.setRecords(dtos);
		result.setTotalRecords(count);
		return result;
	}

	private int getMaxWhiteCustomerRuleBatchNo() {
		WhiteCustomerRuleExample ex = new WhiteCustomerRuleExample();
		ex.setOrderByClause("batch_no desc");
		ex.setLimitStart(0);
		ex.setLimitEnd(1);
		List<WhiteCustomerRule> rules = whiteCustomerRuleMapper.selectByExample(ex);
		if (CollectionUtils.isEmpty(rules))
			return 0;
		else
			return rules.get(0).getBatchNo();
	}

	@Override
	public PageList<WhiteCustomerDTO> getWhiteCustomers(WhiteCustomerParamDTO param) {
		if (param.getPage() < 0 || param.getPageSize() < 1) {
			throw WebException.instance("数据非法");
		}
		WhiteCustomerExample ex = new WhiteCustomerExample();
		WhiteCustomerExample.Criteria criteria = ex.createCriteria();
		if (param.getBatchNo() != null) {
			criteria.andRuleBatchNoEqualTo(param.getBatchNo());
		}
		if (param.getCanaId() != null) {
			criteria.andTzShortIdEqualTo(param.getCanaId());
		}
		if (StringUtils.isNotBlank(param.getCustomerName())) {
			criteria.andCustomerNameEqualTo(param.getCustomerName());
		}
		ex.setOrderByClause("id desc");
		ex.setLimitStart((param.getPage() - 1) * param.getPageSize());
		ex.setLimitEnd(param.getPageSize());
		List<WhiteCustomer> customers = whiteCustomerMapper.selectByExample(ex);
		int count = whiteCustomerMapper.countByExample(ex);
		List<WhiteCustomerDTO> dtos = Lists.newArrayList();
		if (!CollectionUtils.isEmpty(customers)) {
			for (WhiteCustomer customer : customers) {
				WhiteCustomerDTO dto = new WhiteCustomerDTO();
				BeanUtils.copyProperties(customer, dto);
				dtos.add(dto);
			}
		}
		PageList<WhiteCustomerDTO> result = new PageList<WhiteCustomerDTO>();
		result.setRecords(dtos);
		result.setTotalRecords(count);
		return result;
	}

}
