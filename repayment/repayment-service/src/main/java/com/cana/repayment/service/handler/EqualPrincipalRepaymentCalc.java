package com.cana.repayment.service.handler;

import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.utils.CanaDateUtils;
import com.cana.vbam.common.utils.InterestCalcUtil;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * 等额本金
 * 
 * @author XuMeng
 *
 */
public class EqualPrincipalRepaymentCalc extends MultiPeriodRepaymentCalc {

	@Override
	protected void calcPeriodPrincipalAndInterest(RepaymentLoanInfoBO loanInfoBO, RepaymentPlan repaymentPlan) {
		long financeAmount = loanInfoBO.getFinanceAmount();
		int totalPeriod = loanInfoBO.getRepaymentPeriod();
		long averagePrincipal = financeAmount / totalPeriod;
		if (repaymentPlan.getRepaymentPeriod() == totalPeriod)
			repaymentPlan.setAccountPrincipal(averagePrincipal + financeAmount % totalPeriod);
		else
			repaymentPlan.setAccountPrincipal(averagePrincipal);

		long unpaidPrincipal = financeAmount - averagePrincipal * (repaymentPlan.getRepaymentPeriod() - 1);
		repaymentPlan.setAccountInterest(InterestCalcUtil.calcInterest(unpaidPrincipal,
				InterestRateUnit.valueOf(loanInfoBO.getInterestRateUnit()), loanInfoBO.getInterestRate(), DateUnit.DAY,
				CanaDateUtils.durationDays(repaymentPlan.getValueDate(), repaymentPlan.getRepaymentDate())));
	}

	@Override
	protected void reCalcRepaymentPlans(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO currentPlanBO,
			String repaymentDate10) {
		long financeAmount = currentPlanBO.getFinanceBalance();

		// 如果是第一期第一天还款，则到期应还利息少一天
		String valueDate10 = repaymentDate10;
		if (currentPlanBO.getRepaymentPeriod() == 1 && valueDate10.equals(currentPlanBO.getValueDate()))
			valueDate10 = DateTimeUtil.date10(DateTimeUtil.parseDate10(valueDate10).plusDays(1));
		
		currentPlanBO.setAccountInterest(InterestCalcUtil.calcInterest(financeAmount,
				InterestRateUnit.valueOf(loanInfoBO.getInterestRateUnit()), loanInfoBO.getInterestRate(), DateUnit.DAY,
				CanaDateUtils.durationDays(valueDate10, currentPlanBO.getRepaymentDate())));

		financeAmount -= currentPlanBO.getAccountPrincipal();

		int leftTotalPeriod = loanInfoBO.getRepaymentPeriod() - currentPlanBO.getRepaymentPeriod();
		for (int period = currentPlanBO.getRepaymentPeriod() + 1; period <= loanInfoBO.getRepaymentPeriod(); period++) {
			RepaymentPlanBO futurePlanBO = loanInfoBO.lazyLoadPlans().get(period - 1);
			long averagePrincipal = financeAmount / leftTotalPeriod;
			if (futurePlanBO.getRepaymentPeriod() == loanInfoBO.getRepaymentPeriod())
				futurePlanBO.setAccountPrincipal(averagePrincipal + financeAmount % leftTotalPeriod);
			else
				futurePlanBO.setAccountPrincipal(averagePrincipal);

			long unpaidPrincipal = financeAmount - averagePrincipal * (futurePlanBO.getRepaymentPeriod() - currentPlanBO.getRepaymentPeriod() - 1);
			futurePlanBO.setAccountInterest(InterestCalcUtil.calcInterest(unpaidPrincipal,
					InterestRateUnit.valueOf(loanInfoBO.getInterestRateUnit()), loanInfoBO.getInterestRate(),
					DateUnit.DAY,
					CanaDateUtils.durationDays(futurePlanBO.getValueDate(), futurePlanBO.getRepaymentDate())));
			futurePlanBO.setFinanceBalance(unpaidPrincipal);
		}

	}

}
