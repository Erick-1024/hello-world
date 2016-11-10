package com.cana.repayment.service.handler;

import com.cana.repayment.service.bo.RepaymentLoanInfoBO;

public class VJRepaymentCalc extends OrderRepaymentCalc{

	@Override
	protected boolean needGenerateDefaultDeductTaskItem(RepaymentLoanInfoBO loanInfoBO, String curDate) {
		return curDate.compareTo(loanInfoBO.getDueDate()) >= 0;
	}

	@Override
	protected boolean needGenerateNonDefaultDeductTaskItem(RepaymentLoanInfoBO loanInfoBO, String curDate) {
		// TODO Auto-generated method stub
		return super.needGenerateNonDefaultDeductTaskItem(loanInfoBO, curDate) && curDate.compareTo(loanInfoBO.getDueDate()) >= 0;
	}
	

}
