package com.cana.early.warning.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.early.warning.dao.mapper.EarlyWarningAndCreditCustomMapper;
import com.cana.early.warning.dao.mapper.gen.EarlywarningCustomerMapper;
import com.cana.early.warning.service.IEarlyWarningCustomerService;
import com.cana.early.warning.service.utils.EarlyWarningProperties;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCommonRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCustomerInformationResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCustomerResponse;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;

@Service
public class EarlyWarningCustomerServiceImpl implements IEarlyWarningCustomerService {

	@Resource
	private EarlyWarningAndCreditCustomMapper earlyWarningAndCreditCustomMapper;
	
	@Resource
	private EarlywarningCustomerMapper earlywarningCustomerMapper;

	@Override
	public ListResult<EarlyWarningCustomerResponse> queryEarlyWarningCustomer(EarlyWarningCommonRequest earlyWarningCommonRequest) {
		earlyWarningCommonRequest.setCompanyName("%" + StringUtils.trim(earlyWarningCommonRequest.getCompanyName()) + "%");
		List<EarlyWarningCustomerResponse> earlywarningCustomerResponses = earlyWarningAndCreditCustomMapper.queryEarlyWarningCustomer(earlyWarningCommonRequest);
		for (EarlyWarningCustomerResponse earlyWarningCustomerResponse : earlywarningCustomerResponses) {
			String earlywarningLevel = earlyWarningCustomerResponse.getEarlywarningLevel();
			if(earlywarningLevel != null) {
				earlyWarningCustomerResponse.setEarlywarningLevelDesc(EarlywarningLevel.valueOf(earlywarningLevel).desc());
				earlyWarningCustomerResponse.setAction("观察");
			} else {
				earlyWarningCustomerResponse.setAction("-");
			}
		}
		return ListResult.success(earlywarningCustomerResponses, earlyWarningAndCreditCustomMapper.queryEarlyWarningCustomerCount(earlyWarningCommonRequest));
	}
	
	@Override
	public ListResult<EarlyWarningCustomerInformationResponse> queryEarlyWarningCustomerInformation(EarlyWarningCommonRequest earlyWarningCommonRequest) {
		earlyWarningCommonRequest.setCompanyName("%" + StringUtils.trim(earlyWarningCommonRequest.getCompanyName()) + "%");
		List<EarlyWarningCustomerInformationResponse> earlyWarningCustomerInformationResponses = earlyWarningAndCreditCustomMapper.queryEarlyWarningCustomerInformation(earlyWarningCommonRequest);
		for (EarlyWarningCustomerInformationResponse earlyWarningCustomerInformationResponse : earlyWarningCustomerInformationResponses) {
			String level = earlyWarningCustomerInformationResponse.getLevel();
			if(level != null)
				earlyWarningCustomerInformationResponse.setEarlywaringLevelDesc(EarlywarningLevel.valueOf(level).desc());
		}
		return ListResult.success(earlyWarningCustomerInformationResponses, earlyWarningAndCreditCustomMapper.queryEarlyWarningCustomerInformationCount(earlyWarningCommonRequest));
	}

	@Override
	public Map<String, Long> queryEarlyWarningStandard(EarlywarningLevel earlywarningLevel) {
		return EarlyWarningProperties.queryEarlyWarningStandard(earlywarningLevel);
	}

}
