package com.cana.repayment.service.transaction;

import com.cana.repayment.service.data.OfflineRepaymentData;
import com.cana.vbam.common.repayment.enums.ActiveRepaymentType;

/**
 * 主动还款 transaction service
 * @author xiong.li
 *
 */
public interface IActiveRepaymentTransactionService {
	public void updatePlanAndExpenseAndSaveRepaymentDetail(OfflineRepaymentData offlineRepaymentData);
	
	public void updateOnActiveRepaymentSuccess(String loanInfoId, String amount, ActiveRepaymentType activeRepaymentType, String repaymentCertificate);
}
