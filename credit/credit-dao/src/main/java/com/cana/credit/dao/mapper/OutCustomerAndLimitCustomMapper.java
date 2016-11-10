package com.cana.credit.dao.mapper;

import java.util.List;

import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitInfo;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitInfoQueryCriteria;

public interface OutCustomerAndLimitCustomMapper {

	List<CreditUsedLimitInfo> queryCreditUsedLimitInfo(CreditUsedLimitInfoQueryCriteria creditUsedLimitInfoQueryCriteria);
	
	int queryCountCreditUsedLimitInfo(CreditUsedLimitInfoQueryCriteria creditUsedLimitInfoQueryCriteria);
	
}
