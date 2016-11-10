package com.cana.repayment.service.transaction;

import com.cana.vbam.common.repayment.dto.PrepareRepaymentResult;

public interface IRepaymentCalcTransactionService {

	/**
	 * 预还款接口。
	 * 
	 * 根据还款金额，计算此次还款金额可以还掉的本金、利息等情况。
	 * 
	 * 返回数据中，remainingAmount表示剩余金额
	 * 
	 * @param loanInfoId
	 *            放款ID
	 * @param repaymentAmount
	 *            预还款金额，必须大于0
	 * @return 预还款金额分配情况，参数合法且调用成功的情况下
	 */
	public PrepareRepaymentResult prepareRepayment(String loanInfoId, long repaymentAmount) throws Exception;
}
