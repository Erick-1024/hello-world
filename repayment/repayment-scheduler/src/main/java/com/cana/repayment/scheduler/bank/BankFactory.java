package com.cana.repayment.scheduler.bank;

import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.vbam.common.utils.Constants;

public class BankFactory {
	
	public static IBank getBank(RepaymentLoanInfoBO loanInfoBO){
		if(Constants.VJ_PRODUCT_ID.equals(loanInfoBO.getBusinessProductId()))
			return new VJBank();
		else return new EciticBank();
	}
	
}
