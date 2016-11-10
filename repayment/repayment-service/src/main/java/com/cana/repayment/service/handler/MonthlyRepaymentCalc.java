package com.cana.repayment.service.handler;

import java.math.BigDecimal;
import java.util.List;

import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.utils.CanaDateUtils;
import com.cana.vbam.common.utils.InterestCalcUtil;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * 按月付息，到期还本
 * 
 * @author XuMeng
 *
 */
public class MonthlyRepaymentCalc extends MultiPeriodRepaymentCalc {

	@Override
	protected void calcPeriodPrincipalAndInterest(RepaymentLoanInfoBO loanInfoBO, RepaymentPlan repaymentPlan) {
		calcPrincipalAndInterest(repaymentPlan, loanInfoBO.getRepaymentPeriod(), loanInfoBO.getFinanceAmount(),
				InterestRateUnit.valueOf(loanInfoBO.getInterestRateUnit()), loanInfoBO.getInterestRate(),
				repaymentPlan.getValueDate(), repaymentPlan.getRepaymentDate());
	}

	@Override
	protected void reCalcRepaymentPlans(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO currentPlanBO,
			String repaymentDate10) {

		// 如果是第一期第一天还款，则到期应还利息少一天
		String valueDate10 = repaymentDate10;
		if (currentPlanBO.getRepaymentPeriod() == 1 && valueDate10.equals(currentPlanBO.getValueDate()))
			valueDate10 = DateTimeUtil.date10(DateTimeUtil.parseDate10(valueDate10).plusDays(1));

		long leftFinanceAmount = currentPlanBO.getFinanceBalance();
		calcPrincipalAndInterest(currentPlanBO, loanInfoBO.getRepaymentPeriod(), leftFinanceAmount,
				InterestRateUnit.valueOf(loanInfoBO.getInterestRateUnit()), loanInfoBO.getInterestRate(),
				valueDate10, currentPlanBO.getRepaymentDate());

		List<RepaymentPlanBO> planBOs = loanInfoBO.lazyLoadPlans();
		for (RepaymentPlanBO futurePlanBO : planBOs) {
			if (futurePlanBO.getRepaymentPeriod() > currentPlanBO.getRepaymentPeriod()) {
				calcPrincipalAndInterest(futurePlanBO, loanInfoBO.getRepaymentPeriod(), leftFinanceAmount,
						InterestRateUnit.valueOf(loanInfoBO.getInterestRateUnit()), loanInfoBO.getInterestRate(),
						futurePlanBO.getValueDate(), futurePlanBO.getRepaymentDate());
				futurePlanBO.setFinanceBalance(leftFinanceAmount);
			}
		}
	}

	/**
	 * 按照按月付息到期还本的方式给当前还款计划计算应还本金和应还利息
	 * 
	 * @param planBO
	 *            当前待计算的还款计划
	 * @param totalPeriod
	 *            放款的总期数
	 * @param principal
	 *            剩余未还本金
	 * @param interestRateUnit
	 *            利率单位
	 * @param interestRate
	 *            利率
	 * @param valueDate
	 *            当期还款计划利息开始计算日
	 * @param settleDate
	 *            当期还款计划利息结束计算日
	 */
	private void calcPrincipalAndInterest(RepaymentPlan planBO, int totalPeriod, long principal,
			InterestRateUnit interestRateUnit, BigDecimal interestRate, String valueDate, String settleDate) {

		if (planBO.getRepaymentPeriod() == totalPeriod) {
			planBO.setAccountPrincipal(principal);
		} else {
			planBO.setAccountPrincipal(0l);
		}

		planBO.setAccountInterest(InterestCalcUtil.calcInterest(principal, interestRateUnit, interestRate, DateUnit.DAY,
				CanaDateUtils.durationDays(valueDate, settleDate)));
	}

}
