package com.cana.repayment.service.handler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.utils.InterestRateConverter;

/**
 * 等额本息
 * 
 * @author XuMeng
 *
 */
public class EqualAllRepaymentCalc extends MultiPeriodRepaymentCalc {

	@Override
	protected void calcPeriodPrincipalAndInterest(RepaymentLoanInfoBO loanInfoBO, RepaymentPlan repaymentPlan) {
		long financeAmount = loanInfoBO.getFinanceAmount();
		int totalPeriod = loanInfoBO.getRepaymentPeriod();

		BigDecimal ratio = new BigDecimal(1);
		BigDecimal interestRateOfMonth = InterestRateConverter.getMonthlyRate(
				InterestRateUnit.valueOf(loanInfoBO.getInterestRateUnit()), loanInfoBO.getInterestRate());

		long periodRepaymentAmount = new BigDecimal(financeAmount).multiply(interestRateOfMonth)
				.multiply(new BigDecimal(1).add(interestRateOfMonth.multiply(ratio)))
				.multiply(new BigDecimal(1).add(interestRateOfMonth).pow(totalPeriod - 1))
				.divide(new BigDecimal(1).add(interestRateOfMonth).pow(totalPeriod).subtract(new BigDecimal(1)), 0,
						RoundingMode.HALF_UP)
				.longValue();

		long unpaidPrincipal = repaymentPlan.getFinanceBalance();

		long interest = new BigDecimal(unpaidPrincipal).multiply(interestRateOfMonth).multiply(ratio)
				.setScale(0, RoundingMode.HALF_UP).longValue();
		repaymentPlan.setAccountInterest(interest);
		if (repaymentPlan.getRepaymentPeriod() == totalPeriod) { // 最后一期特殊处理
			repaymentPlan.setAccountPrincipal(unpaidPrincipal);
		} else {
			repaymentPlan.setAccountPrincipal(periodRepaymentAmount - interest);
		}
	}

	/*
	 *  等额本息还款方式，提前还款时，当期利息和本金保持不变，未来期变化
	 */
	@Override
	protected void reCalcRepaymentPlans(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO currentPlanBO,
			String repaymentDate10) {

		int advancePeriod = currentPlanBO.getRepaymentPeriod();
		if (advancePeriod == loanInfoBO.getRepaymentPeriod())
			return;

		long leftFinanceAmount = currentPlanBO.getFinanceBalance() - currentPlanBO.getAccountPrincipal();
		int leftTotalPeriod = loanInfoBO.getRepaymentPeriod() - advancePeriod;
		
		BigDecimal ratio = new BigDecimal(1);
		BigDecimal interestRateOfMonth = InterestRateConverter.getMonthlyRate(
				InterestRateUnit.valueOf(loanInfoBO.getInterestRateUnit()), loanInfoBO.getInterestRate());

		long periodRepaymentAmount = new BigDecimal(leftFinanceAmount).multiply(interestRateOfMonth)
				.multiply(new BigDecimal(1).add(interestRateOfMonth.multiply(ratio)))
				.multiply(new BigDecimal(1).add(interestRateOfMonth).pow(leftTotalPeriod - 1))
				.divide(new BigDecimal(1).add(interestRateOfMonth).pow(leftTotalPeriod).subtract(new BigDecimal(1)), 0,
						RoundingMode.HALF_UP)
				.longValue();

		long unpaidPrincipal = leftFinanceAmount;
		List<RepaymentPlanBO> planBOs = loanInfoBO.lazyLoadPlans();
		for (RepaymentPlanBO futurePlanBO : planBOs) {
			if (futurePlanBO.getRepaymentPeriod() <= advancePeriod)
				continue; // 当前期和之前的计划均不需改变

			futurePlanBO.setFinanceBalance(unpaidPrincipal);

			long interest = new BigDecimal(unpaidPrincipal).multiply(interestRateOfMonth).multiply(ratio)
					.setScale(0, RoundingMode.HALF_UP).longValue();
			futurePlanBO.setAccountInterest(interest);
			if (futurePlanBO.getRepaymentPeriod() == advancePeriod + leftTotalPeriod) { // 最后一期特殊处理
				futurePlanBO.setAccountPrincipal(unpaidPrincipal);
			} else {
				futurePlanBO.setAccountPrincipal(periodRepaymentAmount - interest);
			}
			
			unpaidPrincipal -= futurePlanBO.getAccountPrincipal();
		}
		
	}

	@Override
	protected long calcInterestInAdvance(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO planBO, String repaymentDate10,
			String lastRepaymentDate10) {
		return planBO.getAccountInterest();
	}
}
