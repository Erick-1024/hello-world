package com.cana.repayment.service.transaction;

import com.cana.repayment.service.data.OfflineRepaymentData;

public interface IRepaymentAdjustmentTransactionService {
	public void updatePlanAndExpenseAndSaveRepaymentDetail(OfflineRepaymentData offlineRepaymentData);
}
