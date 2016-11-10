package com.cana.early.warning.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.early.warning.dao.mapper.EarlyWarningLevelChangeHistoryAndCreditCustomMapper;
import com.cana.early.warning.service.IEarlyWarningLevelChangeHistoryService;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.early.warning.dto.EarlyWarningLevelChangeHistoryDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningLevelChangeHistoryRequest;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;

@Service
public class EarlyWarningLevelChangeHistoryServiceImpl implements IEarlyWarningLevelChangeHistoryService {

	@Resource
	private EarlyWarningLevelChangeHistoryAndCreditCustomMapper earlywarningLevelChangeHistoryAndCreditCustomerMapper;
	
	@Override
	public ListResult<EarlyWarningLevelChangeHistoryDTO> queryEarlyWarningLevelChangeHistory(EarlyWarningLevelChangeHistoryRequest earlyWarningLevelChangeHistoryRequest) {
		earlyWarningLevelChangeHistoryRequest.setCompanyName("%" + StringUtils.trimToEmpty(earlyWarningLevelChangeHistoryRequest.getCompanyName()) + "%");
		List<EarlyWarningLevelChangeHistoryDTO> returnValue = earlywarningLevelChangeHistoryAndCreditCustomerMapper.queryEarlyWarningLevelChangeHistory(earlyWarningLevelChangeHistoryRequest);
		for (EarlyWarningLevelChangeHistoryDTO earlyWarningLevelChangeHistoryDTO : returnValue)
			earlyWarningLevelChangeHistoryDTO.setLevelDesc(EarlywarningLevel.valueOf(earlyWarningLevelChangeHistoryDTO.getLevel()).desc());
		return ListResult.success(returnValue, earlywarningLevelChangeHistoryAndCreditCustomerMapper.queryEarlyWarningLevelChangeHistoryCount(earlyWarningLevelChangeHistoryRequest));
	}

}
