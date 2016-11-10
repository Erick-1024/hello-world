package com.cana.yundaex.service.transaction;

import com.cana.vbam.common.yundaex.dto.apply.YundaexCustomerAuditResultDTO;

public interface IYundaexCustomerApplyTransactionService {

	/**
	 * 客户申请-人工审核
	 * @param resultDTO
	 * @param userId
	 */
	void auditYundaexCustomer(YundaexCustomerAuditResultDTO resultDTO);

}
