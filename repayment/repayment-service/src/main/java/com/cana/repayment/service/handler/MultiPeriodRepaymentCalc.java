package com.cana.repayment.service.handler;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.dao.po.RepaymentSingleCollect;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.repayment.service.bo.RepaymentSingleCollectBO;
import com.cana.repayment.service.bo.RepaymentSingleDistributeDetailBO;
import com.cana.repayment.service.context.RepaymentCalcContext;
import com.cana.vbam.common.repayment.dto.LoanInfoRepaymentResult;
import com.cana.vbam.common.repayment.enums.BusinessMode;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InputMethod;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.LoanInfoChangeType;
import com.cana.vbam.common.repayment.enums.RepaymentMethod;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.repayment.exception.InvalidRepaymentPlanException;
import com.cana.vbam.common.repayment.exception.RepaymentAmountNotEnoughException;
import com.cana.vbam.common.utils.CanaDateUtils;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.InterestCalcUtil;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * 多期还款计划计算抽象类。
 * 
 * 有三个子类： 按月付息到期还本 等额本金 等额本息
 *
 * 此类中包含了以上三种还款方式共同的计算逻辑
 * 
 * @author XuMeng
 *
 */
public abstract class MultiPeriodRepaymentCalc extends AbstractRepaymentCalc {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(MultiPeriodRepaymentCalc.class);

	@Override
	public final void generateRepaymentPlan(RepaymentLoanInfoBO loanInfoBO, RepaymentCalcContext context)
			throws Exception {

		DateTime loanDate = DateTimeUtil.parseDate10(loanInfoBO.getLoanDate());
		String prePeriodRepaymentDate10 = loanInfoBO.getLoanDate();
		long financeBalance = loanInfoBO.getFinanceAmount();

		for (int period = 1; period <= loanInfoBO.getRepaymentPeriod(); period++) {
			RepaymentPlan plan = new RepaymentPlan();
			plan.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.REDIS_REPAYMENT_PLAN_INFO_ID, 4));
			plan.setLoanInfoId(loanInfoBO.getId());
			plan.setLoanNo(loanInfoBO.getLoanNo());
			plan.setBusinessMode(BusinessMode.FACTORANDFINACE.name());
			plan.setInputMethod(InputMethod.AUTO.name());
			plan.setRepaymentPeriod(period); // 期数
			plan.setFactorId(loanInfoBO.getFactorId());
			plan.setFinanceId(loanInfoBO.getFinanceId());
			plan.setFinanceCompany(loanInfoBO.getFinanceCompany());
			plan.setCoreCompanyId(loanInfoBO.getCoreCompanyId());
			plan.setCoreCompanyName(loanInfoBO.getCoreCompanyName());

			// 放款中的放款日和到期日
			plan.setLoanDate(loanInfoBO.getLoanDate());
			plan.setDueDate(loanInfoBO.getDueDate());

			plan.setFinanceAmount(loanInfoBO.getFinanceAmount());

			// 本次还款计划的起息日、结息日和还款日
			DateTime repaymentDate = loanDate.plusMonths(period);
			if (CanaDateUtils.isMonthLastDay(loanDate))
				repaymentDate = repaymentDate.dayOfMonth().withMaximumValue();
			plan.setValueDate(prePeriodRepaymentDate10);
			plan.setSettleInterestDate(DateTimeUtil.date10(repaymentDate.minusDays(1)));
			plan.setRepaymentDate(DateTimeUtil.date10(repaymentDate));
			prePeriodRepaymentDate10 = plan.getRepaymentDate();

			plan.setFinanceBalance(financeBalance);
			calcPeriodPrincipalAndInterest(loanInfoBO, plan);
			financeBalance -= plan.getAccountPrincipal();

			plan.setExtensionDays(calcExtensionDaysForPlan(plan.getRepaymentDate(),
					loanInfoBO.lazyLoadRepaymentConfig().getExtensionDays(), context.isUseHolidayPolicy()));
			plan.setSettleStatus(SettleStatus.UNSETTLE.name());
			plan.setCreateTime(new Date());
			plan.setUpateTime(new Date());

			planMapper.insertSelective(plan);
		}
	}

	/**
	 * 首次生成还款计划时计算某一期的本金和利息，并设置到repaymentPlan对象中
	 * 
	 * @param loanInfoBO
	 *            放款信息BO，其中
	 *            financeAmount、repaymentPeriod、interestRateUnit、interestRate
	 *            字段需要有值
	 * @param repaymentPlan
	 *            还款计划，其中 financeBalance、period、valueDate、repaymentDate 字段需要有值
	 */
	protected abstract void calcPeriodPrincipalAndInterest(RepaymentLoanInfoBO loanInfoBO, RepaymentPlan repaymentPlan);

	@Override
	public String calcLoanDueDate(String loanDate, DateUnit loanPeriodUnit, int loanPeriod) throws Exception {
		if (loanPeriodUnit == DateUnit.DAY)
			throw new Exception("不支持的放款期限单位");

		return super.calcLoanDueDate(loanDate, loanPeriodUnit, loanPeriod);
	}

	@Override
	public LoanInfoRepaymentResult repayment(RepaymentLoanInfoBO loanInfoBO, long repaymentAmount,
			RepaymentCalcContext context) throws Exception {

		if (context.getRepaymentMethod() == null)
			throw WebException.instance(ReturnCode.ERROR, "还款方式不能为空");

		String repaymentDate10 = context.getRepaymentDate();
		long remainingRepaymentAmount = repaymentAmount;

		long minimumAmount = calcMinimumRepaymentAmount(loanInfoBO, repaymentDate10);
		if (remainingRepaymentAmount < minimumAmount)
			throw new RepaymentAmountNotEnoughException("还款金额不能小于最少还款金额", minimumAmount - remainingRepaymentAmount);

		List<RepaymentPlanBO> planBOs = loanInfoBO.lazyLoadPlans();
		for (int period = 1; period <= planBOs.size(); period++) {
			RepaymentPlanBO planBO = planBOs.get(period - 1);

			if (planBO.isSettled() && repaymentDate10.compareTo(planBO.getRepaymentDate()) > 0)
				continue;

			if (planBO.inOverdueState(repaymentDate10)) {
				remainingRepaymentAmount = payOverduePlan(loanInfoBO, planBO, remainingRepaymentAmount, context);
			} else if (planBO.inExtensionState(repaymentDate10)) {
				remainingRepaymentAmount = payExtensionPlan(loanInfoBO, planBO, remainingRepaymentAmount, context);
			} else {
				String lastRepaymentDate10 = loanInfoBO.getLastSuccessRepaymentDate();
				checkRepaymentDateIsValid(lastRepaymentDate10, planBO, repaymentDate10);
				// 获取当日应还利息
				long accountInterest = calcInterestInAdvance(loanInfoBO, planBO, repaymentDate10, lastRepaymentDate10);

				// 如果还款金额小于当日应还利息，则抛异常不允许还款
				if (remainingRepaymentAmount < accountInterest)
					throw new RepaymentAmountNotEnoughException("还款金额不足以还清当日应还利息", accountInterest - remainingRepaymentAmount);

				planBO.setAccountInterest(accountInterest);
				remainingRepaymentAmount = payNormalPlan(loanInfoBO, planBO, remainingRepaymentAmount, context);

				// 如果剩余金额大于0，或者还款日在固定还款日之前，则触发重新生成还款计划流程
				if (!planBO.isRepaymentDate(repaymentDate10) || remainingRepaymentAmount > 0) {
					remainingRepaymentAmount = payNormalPlanInAdvance(loanInfoBO, planBO, lastRepaymentDate10,
							remainingRepaymentAmount);

					reCalcRepaymentPlans(loanInfoBO, planBO, repaymentDate10);
				}
				break;
			}
		}

		for (RepaymentPlanBO updatePlanBO : planBOs) {
			updatePlanBO.setFinanceBalance(RepaymentPlanBO.calcFinanceBalance(planBOs, updatePlanBO.getRepaymentPeriod()));
			updatePlanBO.duplicate();
			if (context.isPrepareRepayment() == false) {
				updatePlanBO.setUpateTime(new Date());
				planMapper.updateByPrimaryKeySelective(updatePlanBO);
			}
		}

		long actualRepaymentTotalAmount = repaymentAmount - remainingRepaymentAmount;

		LoanInfoRepaymentResult result = new LoanInfoRepaymentResult();
		result.setLoanInfoId(loanInfoBO.getId());
		result.setActualRepaymentTotalAmount(actualRepaymentTotalAmount);
		result.setActualRepaymentPrincipal(sumActualRepaymentTotalPrincipal(loanInfoBO.lazyLoadPlans()));
		if (context.isPrepareRepayment() == true) {
			loanInfoBO.duplicate();
			return result;
		}

		RepaymentSingleCollectBO repaymentSummaryRecord = createRepaymentSummaryAndDetail(loanInfoBO, actualRepaymentTotalAmount,
				context.getRepaymentMethod(), null, repaymentDate10);

		loanInfoBO = new RepaymentLoanInfoBO(loanInfoBO.getId()); // 加载最新的放款信息
		loanInfoBO.duplicate();
		loanInfoBO.setUpateTime(new Date());
		loanInfoBO.setLastVersion(loanInfoBO.getCurrentVersion());
		loanInfoBO.setCurrentVersion(
				DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_LOAN_INFO_VERSION, 4));
		loanInfoBO.setChangeType(LoanInfoChangeType.getChangeType(context.getRepaymentMethod()).name());
		loanInfoBO.setChangeId(repaymentSummaryRecord.getId());
		loanInfoMapper.updateByPrimaryKey(loanInfoBO);
		loanInfoBO.createSnapshot();

		result.setRepaymentSummaryRecordId(repaymentSummaryRecord.getId());
		return result;
	}

	/**
	 * 创建还款汇总和明细记录
	 * 
	 * @param loanInfoBO
	 * @param planBO
	 * @param repaymentTotalAmount
	 * @param repaymentDate
	 */
	private RepaymentSingleCollectBO createRepaymentSummaryAndDetail(final RepaymentLoanInfoBO loanInfoBO,
			long repaymentTotalAmount, RepaymentMethod repaymentMethod, String repaymentCertificate,
			String repaymentDate) {

		RepaymentSingleCollectBO summaryRecord = new RepaymentSingleCollectBO(new RepaymentSingleCollect());
		summaryRecord
				.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_SINGLE_COLLECT_ID, 4));
		summaryRecord.setLoanInfoId(loanInfoBO.getId());
		summaryRecord.setRepaymentDate(repaymentDate);
		summaryRecord.setRepaymentType(repaymentMethod.name());
		summaryRecord.setRepaymentTotalAmount(repaymentTotalAmount);
		summaryRecord.setRepaymentCertificate(repaymentCertificate);
		summaryRecord.setCreateTime(new Date());
		repaymentSummaryMapper.insertSelective(summaryRecord);

		for (RepaymentPlanBO planBO : loanInfoBO.lazyLoadPlans()) {
			if (planBO.allotDetail() == null)
				continue;
			RepaymentSingleDistributeDetailBO repaymentRecordItem = planBO.allotDetail();
			repaymentRecordItem.setRepaymentSingleCollectId(summaryRecord.getId());
			repaymentRecordItem.setCreateTime(new Date());
			singleDistributeDetailMapper.insertSelective(repaymentRecordItem);
		}

		return summaryRecord;
	}

	/**
	 * 
	 * @param loanInfoBO
	 * @param planBO
	 * @param repaymentDate10
	 * @param remainingRepaymentAmount
	 * @return 剩余还款金额
	 */
	private long payNormalPlanInAdvance(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO planBO, String repaymentDate10,
			long remainingRepaymentAmount) {
		if (remainingRepaymentAmount == 0)
			return remainingRepaymentAmount;

		long paidPrincipal = Math.min(remainingRepaymentAmount, planBO.getFinanceBalance());
		RepaymentSingleDistributeDetailBO allotDetailBO = planBO.allotDetail();
		planBO.setPaidNormalPrincipal(planBO.getPaidNormalPrincipal() + paidPrincipal);
		long accountPrincipal = planBO.getAccountPrincipal() - paidPrincipal;
		planBO.setAccountPrincipal(accountPrincipal < 0 ? 0 : accountPrincipal);
		planBO.setFinanceBalance(planBO.getFinanceBalance() - paidPrincipal);
		allotDetailBO.setPayNormalPrincipal(allotDetailBO.getPayNormalPrincipal() + paidPrincipal);

		remainingRepaymentAmount -= paidPrincipal;
		return remainingRepaymentAmount;
	}

	/**
	 * 重新计算当期和未来期的还款计划
	 * 
	 * @param loanInfoBO
	 *            放款信息
	 * @param currentPlanBO
	 *            当期还款计划，需保证该期还款计划中的融资余额是准的，会使用该融资余额作为剩余未还本金
	 * @param repaymentDate10
	 *            还款日
	 */
	protected abstract void reCalcRepaymentPlans(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO currentPlanBO,
			String repaymentDate10);

	/**
	 * 计算提前还款时当天应还利息。
	 * 
	 * 如果是第一期的第一天还款，在计算利息时，将其当作第二天还款来处理
	 * 如果上一次还款日是第一期的第一天，则将其当作第二天还款来处理
	 * 
	 * 此实现目前仅用于按月付息和等额本金方式，等额本息的提前还款利息计算见
	 * {@link EqualAllRepaymentCalc#calcInterestInAdvance}
	 * 
	 * @param loanInfoBO
	 * @param planBO
	 * @param repaymentDate10
	 * @param lastRepaymentDate10
	 */
	protected long calcInterestInAdvance(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO planBO, String repaymentDate10,
			String lastRepaymentDate10) {
		if (planBO.isRepaymentDate(repaymentDate10)) {
			return planBO.getAccountInterest();
		}
		long principal = planBO.getFinanceBalance();

		InterestRateUnit interestRateUnit = InterestRateUnit.valueOf(loanInfoBO.getInterestRateUnit());
		BigDecimal interestRate = loanInfoBO.getInterestRate();

		String startDate10 = planBO.getValueDate();
		if (StringUtils.isNotEmpty(lastRepaymentDate10) && startDate10.compareTo(lastRepaymentDate10) < 0)
			startDate10 = lastRepaymentDate10;
		String endDate10 = repaymentDate10;

		if (planBO.getRepaymentPeriod() == 1) {
			if (StringUtils.isNotEmpty(lastRepaymentDate10) && startDate10.equals(planBO.getValueDate()))
				startDate10 = DateTimeUtil.date10(DateTimeUtil.parseDate10(startDate10).plusDays(1));
			if (endDate10.equals(planBO.getValueDate()))
				endDate10 = DateTimeUtil.date10(DateTimeUtil.parseDate10(endDate10).plusDays(1));
		}

		int durationDays = CanaDateUtils.durationDays(startDate10, endDate10);

		return InterestCalcUtil.calcInterest(principal, interestRateUnit, interestRate, DateUnit.DAY, durationDays);
	}

	/**
	 * 检查还款日必须在起息日（不包含）之后和固定还款日（包含）之前， 如果当前计划包含上一次还款日，则还款日应该大于等于上一次还款日
	 * 
	 * 特例，第一期还款计划的第一天可以还款
	 */
	private void checkRepaymentDateIsValid(String lastRepaymentDate10, RepaymentPlanBO planBO, String repaymentDate10)
			throws InvalidRepaymentPlanException {
		if (planBO.getRepaymentPeriod() == 1 && planBO.getValueDate().equals(repaymentDate10))
			return;

		if (repaymentDate10.compareTo(planBO.getValueDate()) <= 0
				|| repaymentDate10.compareTo(planBO.getRepaymentDate()) > 0) {
			logger.error("还款日{}不在还款期数{}的还款计划的起息日{}和固定还款日{}之间，", repaymentDate10, planBO.getRepaymentPeriod(), planBO.getValueDate(), planBO.getRepaymentDate());
			throw new InvalidRepaymentPlanException("还款日不在起息日和固定还款日之间");
		}

		if (StringUtils.isNotEmpty(lastRepaymentDate10) && repaymentDate10.compareTo(lastRepaymentDate10) < 0) {
			logger.error("还款日{}必须在上一次还款日{}之后", repaymentDate10, lastRepaymentDate10);
			throw new InvalidRepaymentPlanException("还款日必须在上一次还款日之后");
		}
	}

	@Override
	public long calcMinimumRepaymentAmount(RepaymentLoanInfoBO loanInfoBO, String curDate10) {

		long minimumAmount = 0l;

		for (RepaymentPlanBO planBO : loanInfoBO.lazyLoadPlans()) {

			if (planBO.isSettled() && curDate10.compareTo(planBO.getRepaymentDate()) > 0)
				continue;

			if (planBO.inOverdueState(curDate10)) {
				minimumAmount += planBO.totalOverdueAmount();
			} else if (planBO.inExtensionState(curDate10)) {
				minimumAmount += planBO.totalExtensionAmount();
			} else {
				String lastRepaymentDate10 = loanInfoBO.getLastSuccessRepaymentDate();
				checkRepaymentDateIsValid(lastRepaymentDate10, planBO, curDate10);
				// 获取当日应还利息
				long accountInterest = calcInterestInAdvance(loanInfoBO, planBO, curDate10, lastRepaymentDate10);
				minimumAmount += planBO.getAccountServiceCharge() + accountInterest;
				break;
			}
		}

		return minimumAmount;
	}

	@Override
	public long calcMaximumRepaymentAmount(RepaymentLoanInfoBO loanInfoBO, String curDate10) {
		long maximumAmount = 0l;

		List<RepaymentPlanBO> planBOs = loanInfoBO.lazyLoadPlans();
		for (int period = 1; period <= planBOs.size(); ++period) {
			RepaymentPlanBO planBO = planBOs.get(period - 1);

			if (planBO.isSettled() && curDate10.compareTo(planBO.getRepaymentDate()) > 0)
				continue;

			if (planBO.inOverdueState(curDate10)) {
				maximumAmount += planBO.totalOverdueAmount();
			} else if (planBO.inExtensionState(curDate10)) {
				maximumAmount += planBO.totalExtensionAmount();
			} else {
				String lastRepaymentDate10 = loanInfoBO.getLastSuccessRepaymentDate();
				checkRepaymentDateIsValid(lastRepaymentDate10, planBO, curDate10);
				// 获取当日应还利息
				long accountInterest = calcInterestInAdvance(loanInfoBO, planBO, curDate10, lastRepaymentDate10);
				maximumAmount += planBO.getAccountServiceCharge() + accountInterest;

				maximumAmount += planBO.getAccountPrincipal();

				for (period += 1; period <= planBOs.size(); ++ period) {
					planBO = planBOs.get(period - 1);
					maximumAmount += planBO.getAccountServiceCharge() + planBO.getAccountPrincipal();
				}
				break;
			}
		}

		return maximumAmount;
	}
}
