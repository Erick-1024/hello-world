package com.cana.repayment.service.handler;

import java.util.Date;

import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.repayment.service.context.RepaymentCalcContext;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.utils.CanaDateUtils;
import com.cana.vbam.common.utils.InterestCalcUtil;

public class TravelzenFinanceRepaymentCalc extends OrderRepaymentCalc{

	@Override
	public void generateRepaymentPlan(RepaymentLoanInfoBO loanInfoBO, RepaymentCalcContext context) throws Exception {
		if(!loanInfoBO.hasAnyPlans())
			super.generateRepaymentPlan(loanInfoBO, context);
		else updateRepaymentPlan(loanInfoBO, context);
	}

	/**
	 * 后续更新还款计划
	 * @param loanInfoBO
	 * @param extra
	 */
	private void updateRepaymentPlan(RepaymentLoanInfoBO loanInfoBO, RepaymentCalcContext context) {
		long appendFinanceAmount = context.getAppendFinanceAmount();
		RepaymentPlanBO planBO = loanInfoBO.lazyLoadPlans().get(0);
		planBO.setFinanceAmount(loanInfoBO.getFinanceAmount());
		planBO.setFinanceBalance(planBO.getFinanceBalance() + appendFinanceAmount);
		planBO.setAccountPrincipal(planBO.getAccountPrincipal() + appendFinanceAmount);
		planBO.setAccountInterest(InterestCalcUtil.calcInterest(planBO.getAccountPrincipal(),
				InterestRateUnit.valueOf(loanInfoBO.getInterestRateUnit()), loanInfoBO.getInterestRate(), DateUnit.DAY,
				CanaDateUtils.durationDays(planBO.getValueDate(), planBO.getRepaymentDate())));
		planBO.setSettleStatus(SettleStatus.UNSETTLE.name());
		planBO.setUpateTime(new Date());
		planMapper.updateByPrimaryKey(planBO);
		loanInfoBO.duplicate();
	}


}
