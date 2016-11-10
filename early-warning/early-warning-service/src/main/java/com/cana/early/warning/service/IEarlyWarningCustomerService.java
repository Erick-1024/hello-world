package com.cana.early.warning.service;

import java.util.Map;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCommonRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCustomerInformationResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCustomerResponse;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;

public interface IEarlyWarningCustomerService {

	public ListResult<EarlyWarningCustomerResponse> queryEarlyWarningCustomer(EarlyWarningCommonRequest earlyWarningCustomerRequest);
	
	public ListResult<EarlyWarningCustomerInformationResponse> queryEarlyWarningCustomerInformation(EarlyWarningCommonRequest earlyWarningCustomerRequest);
	
	/**
	 * 获取当前最新的预警等级标准
	 * @param earlywarningLevel 预警等级
	 * @return
	 */
	public Map<String, Long> queryEarlyWarningStandard(EarlywarningLevel earlywarningLevel);

}
