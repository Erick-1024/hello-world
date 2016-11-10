package com.cana.early.warning.dao.mapper;

import java.util.List;

import com.cana.vbam.common.early.warning.dto.EarlyWaringEventTypeListResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCommonRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCustomerInformationResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCustomerResponse;

public interface EarlyWarningAndCreditCustomMapper {

	public List<EarlyWarningCustomerResponse> queryEarlyWarningCustomer(EarlyWarningCommonRequest earlyWarningCommonRequest);
	
	public int queryEarlyWarningCustomerCount(EarlyWarningCommonRequest earlyWarningCommonRequest);
	
	public List<EarlyWarningCustomerInformationResponse> queryEarlyWarningCustomerInformation(EarlyWarningCommonRequest earlyWarningCommonRequest);
	
	public int queryEarlyWarningCustomerInformationCount(EarlyWarningCommonRequest earlyWarningCommonRequest);

	public EarlyWaringEventTypeListResponse queryEarlyWarningTypeList(EarlyWarningCommonRequest earlyWarningCommonRequest);
	
}
