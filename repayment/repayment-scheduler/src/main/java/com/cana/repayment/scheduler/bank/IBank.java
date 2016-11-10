package com.cana.repayment.scheduler.bank;

import com.cana.repayment.service.bo.RepaymentDailyBatchTaskItemBO;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;

public interface IBank {

	/**
	 * 获取账户余额
	 * @param accountNo
	 * @return
	 */
	public long getAccountBalance(String accountNo) throws Exception;
	
	/**
	 * 扣款。返回实际扣款金额;
	 * @param amount
	 * @param loanInfoBO
	 */
	public long deductAmount(long amount, RepaymentLoanInfoBO loanInfoBO, RepaymentDailyBatchTaskItemBO taskItemBO) throws Exception;
	
}
