package com.cana.early.warning.service.transaction;

import com.cana.vbam.common.early.warning.dto.AuditEarlyWarningEventRequest;

public interface IEarlyWarningEventReviewTransactionService {

	/**
	 * 对审核记录进行审核
	 * @param auditEarlyWarningEventRequest
	 */
	public void auditEarlyWarningEventReview(AuditEarlyWarningEventRequest auditEarlyWarningEventRequest);
	
}
