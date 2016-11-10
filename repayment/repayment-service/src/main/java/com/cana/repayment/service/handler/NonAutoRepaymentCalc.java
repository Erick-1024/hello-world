package com.cana.repayment.service.handler;

import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.context.RepaymentCalcContext;
import com.cana.vbam.common.repayment.dto.LoanInfoRepaymentResult;

public class NonAutoRepaymentCalc extends AbstractRepaymentCalc {

	@Override
	public LoanInfoRepaymentResult repayment(RepaymentLoanInfoBO loanInfoBO, long repaymentAmount,
			RepaymentCalcContext context) throws Exception {
		throw new UnsupportedOperationException("不支持还款接口");
	}

	@Override
	public long calcMinimumRepaymentAmount(RepaymentLoanInfoBO loanInfoBO, String curDate10) {

		throw new UnsupportedOperationException("不支持计算最少还款金额");

	}

	@Override
	public long calcMaximumRepaymentAmount(RepaymentLoanInfoBO loanInfoBO, String curDate10) {
		throw new UnsupportedOperationException("不支持计算最大还款金额");
	}
}
