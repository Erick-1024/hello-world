package com.cana.credit.limit.service.transaction;

import java.util.List;

import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.vbam.common.credit.dto.limit.CreditLimitRecoveryRequstDTO;
import com.cana.vbam.common.credit.enums.CreditLimitStatus;
import com.cana.vbam.common.credit.enums.CreditMode;

public interface ICommonCreditLimitTransactionService {
	
	/**
	 * 
	 * @param memberId
	 * @param projectId
	 * @param outCustomerId，2016年6月添加该字段，允许为空
	 * @param creditMode
	 */
	public CreditLimit lockCreditLimit(String memberId, String projectId, String outCustomerId, CreditMode creditMode);
	
	/**
	 * 恢复额度
	 * @param request
	 */
	public void recoveryLimit(CreditLimitRecoveryRequstDTO request);
	
	public List<CreditLimit> getCreditLimitList(String projectId, CreditLimitStatus... statusList);

}
