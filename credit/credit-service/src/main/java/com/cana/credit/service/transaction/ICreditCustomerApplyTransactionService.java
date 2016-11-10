package com.cana.credit.service.transaction;

import com.cana.credit.dao.po.CustomerApply;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyRequestDTO;

public interface ICreditCustomerApplyTransactionService {

	public CustomerApply saveTravelzenAuditResult(CustomerApplyRequestDTO customerApplyDTO) throws Exception;
	
}
