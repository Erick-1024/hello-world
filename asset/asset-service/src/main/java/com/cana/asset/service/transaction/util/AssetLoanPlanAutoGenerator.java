package com.cana.asset.service.transaction.util;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;

import com.cana.vbam.common.asset.loan.dto.GenerateLoanPlanRequest;
import com.cana.vbam.common.asset.loan.dto.LoanPlanDTO;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.utils.CanaDateUtils;
import com.cana.vbam.common.utils.InterestCalcUtil;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;

/**
 * 还款计划自动生成工具类
 * @author XuMeng
 */
public class AssetLoanPlanAutoGenerator {

	/**
	 * 生成还款计划
	 */
	public static List<LoanPlanDTO> generateLoanPlanDTO(GenerateLoanPlanRequest request) {
		if (MoneyArithUtil.convertStringToMoney(request.getFinanceAmount()) <= 0)
			throw WebException.instance("融资金额必须大于0");
		if (!EnumUtils.isValidEnum(RepaymentType.class, request.getRepaymentType()))
			throw WebException.instance("不合法的还款方式");
		if (!EnumUtils.isValidEnum(InterestRateUnit.class, request.getInterestRateUnit()))
			throw WebException.instance("不合法的利率单位");
		if (request.getDayCountConvention() != 360 && request.getDayCountConvention() != 365)
			throw WebException.instance("请选择正确的计息基准天数");
		if (!DateTimeUtil.validateDate10(request.getLoanDate()))
			throw WebException.instance("放款日期不合法");
		if (!DateTimeUtil.validateDate10(request.getDueDate()))
			throw WebException.instance("到期日不合法");
		if (request.getDueDate().compareTo(request.getLoanDate()) <= 0)
			throw WebException.instance("到期日必须大于放款日");
		if (MoneyArithUtil.convertStringToInterestRate(request.getInterestRate()).compareTo(BigDecimal.ZERO) <= 0)
			throw WebException.instance("利率不合法");

		RepaymentType repaymentType = RepaymentType.valueOf(request.getRepaymentType());

		List<LoanPlanDTO> generatedPlans = getInstance(repaymentType).generateLoanPlan(request);

		return generatedPlans;
	}

	private static GenerateLoanPlanInterface getInstance(RepaymentType repaymentType) {
		if (repaymentType == RepaymentType.ORDER)
			return new OrderOrMaturity();
		else if (repaymentType == RepaymentType.MONTHLY)
			return new Monthly();
		else if (repaymentType == RepaymentType.MATURITY)
			return new OrderOrMaturity();
		else
			throw WebException.instance("暂不支持的还款方式");
	}


	private interface GenerateLoanPlanInterface {
		List<LoanPlanDTO> generateLoanPlan(GenerateLoanPlanRequest request);
	}

	private static class OrderOrMaturity implements GenerateLoanPlanInterface {

		@Override
		public List<LoanPlanDTO> generateLoanPlan(GenerateLoanPlanRequest request) {
			long principal = MoneyUtil.yuan2Cent(request.getFinanceAmount());

			LoanPlanDTO plan = new LoanPlanDTO();
			plan.setFinanceBalance(MoneyUtil.cent2Yuan(principal));
			plan.setValueDate(request.getLoanDate());
			plan.setRepaymentDate(request.getDueDate());
			plan.setSettleInterestDate(request.getDueDate());

			plan.setAccountPrincipal(MoneyUtil.cent2Yuan(principal));
			long interest = InterestCalcUtil.calcInterest(principal,
					InterestRateUnit.valueOf(request.getInterestRateUnit()),
					MoneyArithUtil.convertStringToInterestRate(request.getInterestRate()),
					DateUnit.DAY, CanaDateUtils.durationDays(plan.getValueDate(), plan.getRepaymentDate()),
					request.getDayCountConvention());
			plan.setAccountInterest(MoneyUtil.cent2Yuan(interest));
			plan.setAccountAmount(MoneyUtil.cent2Yuan(principal + interest));
			plan.setAccountOverdue(MoneyUtil.cent2Yuan(0l));
			plan.setSettleStatus(SettleStatus.UNSETTLE.name());
			return Lists.newArrayList(plan);
		}
		
	}

	private static class Monthly implements GenerateLoanPlanInterface {

		@Override
		public List<LoanPlanDTO> generateLoanPlan(GenerateLoanPlanRequest request) {

			String dueDate = request.getDueDate();
			List<LoanPlanDTO> plans = Lists.newArrayList();

			String valueDate = request.getLoanDate();
			boolean isMonthLastDay = CanaDateUtils.isMonthLastDay(request.getLoanDate());
			for (int period = 1; valueDate.compareTo(dueDate) < 0; ++period) {
				LoanPlanDTO plan = new LoanPlanDTO();
				plan.setValueDate(valueDate);

				String nextMonthDate;
				if (isMonthLastDay)
					nextMonthDate = CanaDateUtils.plusMonthsReturnLastDay(request.getLoanDate(), period);
				else
					nextMonthDate = CanaDateUtils.plusMonths(request.getLoanDate(), period);
				String settleDate = CanaDateUtils.plusDays(nextMonthDate, -1);

				if (settleDate.compareTo(dueDate) > 0)
					settleDate = dueDate;

				plan.setSettleInterestDate(settleDate);
				plan.setRepaymentDate(settleDate);
				valueDate = settleDate;

				plans.add(plan);
			}

			long principal = MoneyUtil.yuan2Cent(request.getFinanceAmount());
			for (int period = 1; period <= plans.size(); ++period) {
				LoanPlanDTO plan = plans.get(period - 1);

				long interest = InterestCalcUtil.calcInterest(principal,
						InterestRateUnit.valueOf(request.getInterestRateUnit()),
						MoneyArithUtil.convertStringToInterestRate(request.getInterestRate()),
						DateUnit.DAY, CanaDateUtils.durationDays(plan.getValueDate(), plan.getRepaymentDate()),
						request.getDayCountConvention());
				plan.setFinanceBalance(MoneyUtil.cent2Yuan(principal));
				plan.setAccountInterest(MoneyUtil.cent2Yuan(interest));
				plan.setAccountOverdue(MoneyUtil.cent2Yuan(0l));

				if (period == plans.size()) {
					plan.setAccountPrincipal(MoneyUtil.cent2Yuan(principal));
					plan.setAccountAmount(MoneyUtil.cent2Yuan(principal + interest));
				} else {
					plan.setAccountPrincipal(MoneyUtil.cent2Yuan(0l));
					plan.setAccountAmount(MoneyUtil.cent2Yuan(0l + interest));
				}
				plan.setSettleStatus(SettleStatus.UNSETTLE.name());
			}
			return plans;
		}

	}

}
