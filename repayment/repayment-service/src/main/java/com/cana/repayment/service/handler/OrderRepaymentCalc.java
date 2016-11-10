package com.cana.repayment.service.handler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.dao.po.RepaymentSingleCollect;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.repayment.service.bo.RepaymentSingleCollectBO;
import com.cana.repayment.service.bo.RepaymentSingleDistributeDetailBO;
import com.cana.repayment.service.context.RepaymentCalcContext;
import com.cana.vbam.common.repayment.dto.LoanInfoRepaymentResult;
import com.cana.vbam.common.repayment.dto.PreCalcInterestRequest;
import com.cana.vbam.common.repayment.dto.PreCalcInterestResponse;
import com.cana.vbam.common.repayment.dto.PreCalcRepaymentPlan;
import com.cana.vbam.common.repayment.dto.RepaymentAmount;
import com.cana.vbam.common.repayment.enums.BusinessMode;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InputMethod;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.LoanInfoChangeType;
import com.cana.vbam.common.repayment.enums.RepaymentMethod;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.utils.CanaDateUtils;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.InterestCalcUtil;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;

public class OrderRepaymentCalc extends AbstractRepaymentCalc{

	@Override
	public void generateRepaymentPlan(RepaymentLoanInfoBO loanInfoBO, RepaymentCalcContext context) throws Exception {
		RepaymentPlan plan = new RepaymentPlan();
		plan.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.REDIS_REPAYMENT_PLAN_INFO_ID, 4));
		plan.setLoanInfoId(loanInfoBO.getId());
		plan.setLoanNo(loanInfoBO.getLoanNo());
		plan.setLoanDate(loanInfoBO.getLoanDate());
		plan.setBusinessMode(BusinessMode.FACTORANDFINACE.name());
		plan.setInputMethod(InputMethod.AUTO.name());
		plan.setRepaymentPeriod(1);
		plan.setFactorId(loanInfoBO.getFactorId());
		plan.setFinanceId(loanInfoBO.getFinanceId());
		plan.setFinanceCompany(loanInfoBO.getFinanceCompany());
		plan.setFinanceAmount(loanInfoBO.getFinanceAmount());
		plan.setCoreCompanyId(loanInfoBO.getCoreCompanyId());
		plan.setCoreCompanyName(loanInfoBO.getCoreCompanyName());
		plan.setFinanceBalance(loanInfoBO.getFinanceBalance());
		plan.setValueDate(loanInfoBO.getLoanDate());
		plan.setDueDate(loanInfoBO.getDueDate());
		plan.setSettleInterestDate(DateTimeUtil.date10(DateTimeUtil.addDay(DateTimeUtil.parseDate10(loanInfoBO.getDueDate()), -1)));
		plan.setRepaymentDate(loanInfoBO.getDueDate());
		plan.setAccountPrincipal(loanInfoBO.getFinanceBalance());
		plan.setAccountInterest(InterestCalcUtil.calcInterest(plan.getAccountPrincipal(),
				InterestRateUnit.valueOf(loanInfoBO.getInterestRateUnit()), loanInfoBO.getInterestRate(), DateUnit.DAY,
				CanaDateUtils.durationDays(plan.getValueDate(), plan.getRepaymentDate())));

		plan.setExtensionDays(calcExtensionDaysForPlan(plan.getRepaymentDate(),
				loanInfoBO.lazyLoadRepaymentConfig().getExtensionDays(), context.isUseHolidayPolicy()));
		plan.setSettleStatus(SettleStatus.UNSETTLE.name());
		plan.setCreateTime(new Date());
		plan.setUpateTime(new Date());
		planMapper.insertSelective(plan);
	}

	@Override
	public long payNormalPlans(RepaymentLoanInfoBO loanInfoBO, long remainingAmount, String curDate10,
			RepaymentCalcContext context) {
		for(RepaymentPlanBO planBO : loanInfoBO.normalPlans(curDate10))
			remainingAmount = payNormalPlan(loanInfoBO, planBO, remainingAmount, context);
		return remainingAmount;
	}

	@Override
	public LoanInfoRepaymentResult repayment(RepaymentLoanInfoBO loanInfoBO,
			long repaymentAmount, RepaymentCalcContext context) throws Exception {

		if (context.getRepaymentMethod() == null)
			throw WebException.instance(ReturnCode.ERROR, "还款方式不能为空");

		RepaymentPlanBO planBO = loanInfoBO.lazyLoadPlans().get(0);
		String refundDate10 = context.getRepaymentDate();
		long remainingRepaymentAmount = repaymentAmount;
		if(planBO.inOverdueState(refundDate10)){
			remainingRepaymentAmount = payOverduePlan(loanInfoBO, planBO, remainingRepaymentAmount, context);
		}else if(planBO.inExtensionState(refundDate10)){
			remainingRepaymentAmount = payExtensionPlan(loanInfoBO, planBO, remainingRepaymentAmount, context);
		}else{
			remainingRepaymentAmount = payNormalPlan(loanInfoBO, planBO, remainingRepaymentAmount, context);
		}
		
		long actualRepaymentTotalAmount = repaymentAmount - remainingRepaymentAmount;

		LoanInfoRepaymentResult result = new LoanInfoRepaymentResult();
		result.setLoanInfoId(loanInfoBO.getId());
		result.setActualRepaymentTotalAmount(actualRepaymentTotalAmount);
		result.setActualRepaymentPrincipal(sumActualRepaymentTotalPrincipal(loanInfoBO.lazyLoadPlans()));
		if (context.isPrepareRepayment() == true) {
			planBO.duplicate();
			loanInfoBO.duplicate();
			return result;
		}

		RepaymentSingleCollectBO repaymentSummaryRecord = createRepaymentSummaryAndDetail(loanInfoBO, planBO, actualRepaymentTotalAmount,
                                  context.getRepaymentMethod(), context.getRepaymentCertificate(), refundDate10);
		
		if(actualRepaymentTotalAmount != repaymentSummaryRecord.calcTotal())
			throw new Exception("根据还款明细算出来的总金额不等于总的扣款金额");
		
		planBO.duplicate();
		planBO.setUpateTime(new Date());
		planMapper.updateByPrimaryKey(planBO);
		
		loanInfoBO = new RepaymentLoanInfoBO(loanInfoBO.getId()); //加载最新的放款信息
		loanInfoBO.duplicate();
		loanInfoBO.setUpateTime(new Date());
		loanInfoBO.setLastVersion(loanInfoBO.getCurrentVersion());
        loanInfoBO.setCurrentVersion(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_LOAN_INFO_VERSION, 4));
        loanInfoBO.setChangeType(LoanInfoChangeType.getChangeType(context.getRepaymentMethod()).name());
        loanInfoBO.setChangeId(repaymentSummaryRecord.getId());
		loanInfoMapper.updateByPrimaryKey(loanInfoBO);
		
		loanInfoBO.createSnapshot();
		
		
		result.setRepaymentSummaryRecordId(repaymentSummaryRecord.getId());
		
		return result;
	}

	

	/**
	 * 创建还款汇总和明细记录
	 * @param loanInfoBO
	 * @param planBO
	 * @param repaymentTotalAmount
	 * @param repaymentDate
	 */
	private RepaymentSingleCollectBO createRepaymentSummaryAndDetail(final RepaymentLoanInfoBO loanInfoBO, final RepaymentPlanBO planBO, 
			long repaymentTotalAmount, RepaymentMethod repaymentMethod, String repaymentCertificate, String repaymentDate) {
		
		RepaymentSingleCollectBO summaryRecord = new RepaymentSingleCollectBO(new RepaymentSingleCollect());
		summaryRecord.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_SINGLE_COLLECT_ID, 4));
		summaryRecord.setLoanInfoId(loanInfoBO.getId());
	    summaryRecord.setRepaymentDate(repaymentDate);
		summaryRecord.setRepaymentType(repaymentMethod.name());
		summaryRecord.setRepaymentTotalAmount(repaymentTotalAmount);
		summaryRecord.setRepaymentCertificate(repaymentCertificate);
		summaryRecord.setCreateTime(new Date());
		repaymentSummaryMapper.insertSelective(summaryRecord);
		
		RepaymentSingleDistributeDetailBO repaymentRecordItem = planBO.allotDetail();
		repaymentRecordItem.setRepaymentSingleCollectId(summaryRecord.getId());
		repaymentRecordItem.setCreateTime(new Date());
		singleDistributeDetailMapper.insertSelective(repaymentRecordItem);
		
		return summaryRecord;
	}

	@Override
	protected long payNormalPlan(final RepaymentLoanInfoBO loanInfoBO, final RepaymentPlanBO planBO, long remainingAmount, final RepaymentCalcContext context) {
		String repaymentDate10 = context.getRepaymentDate();
		RepaymentAmount maxRepaymentAmount = maxNormalRepaymentAmount(loanInfoBO, planBO, context);
		if(planBO.isRepaymentDate(repaymentDate10))
			return super.payNormalPlan(loanInfoBO, planBO, remainingAmount, context);
		else if(remainingAmount >= maxRepaymentAmount.unpaidTotal()){
			return payAllNormalWhenInAdvance(loanInfoBO, planBO, remainingAmount, maxRepaymentAmount, context);
		} else return payPartialNormalWhenInAdvance(loanInfoBO, planBO, remainingAmount, context);
	}
	
	/**
	 * 提前还款，还清所有
	 * @param loanInfoBO
	 * @param planBO
	 * @param remainingAmount
	 * @param maxRepaymentAmount
	 * @param context
	 * @return
	 */
	private long payAllNormalWhenInAdvance(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO planBO, long remainingAmount,
			final RepaymentAmount repaymentAmount, final RepaymentCalcContext context) {
		
		final RepaymentSingleDistributeDetailBO allotDetailBO = planBO.createAllotDetailIfMissing();

		long paidServiceCharge = Math.min(remainingAmount, repaymentAmount.getAccountServiceCharge());
		planBO.setPaidNormalServiceCharge(planBO.getPaidNormalServiceCharge() + paidServiceCharge);
		allotDetailBO.setPayNormalServiceCharge(paidServiceCharge);

		remainingAmount -= paidServiceCharge;
		long paidInterest = Math.min(remainingAmount, repaymentAmount.getAccountInterest());
		planBO.setPaidNormalInterest(planBO.getPaidNormalInterest() + paidInterest);
		allotDetailBO.setPayNormalInterest(paidInterest);
		
		remainingAmount -= paidInterest;
		long financeBalance = RepaymentPlanBO.calcFinanceBalance(loanInfoBO.lazyLoadPlans(), planBO.getRepaymentPeriod());
		long paidPrincipal = Math.min(remainingAmount, repaymentAmount.getAccountPrincipal());
		planBO.setPaidNormalPrincipal(planBO.getPaidNormalPrincipal() + paidPrincipal);
		planBO.setAccountPrincipal(planBO.getAccountPrincipal() - paidPrincipal);
		planBO.setFinanceBalance(financeBalance - paidPrincipal);
		allotDetailBO.setPayNormalPrincipal(paidPrincipal);
		
		planBO.setAccountServiceCharge(calcNewServiceChargeAfterInAdvance(loanInfoBO, planBO, planBO.getAccountPrincipal(), context));
		planBO.setAccountInterest(calcNewInterestAfterInAdvance(loanInfoBO, planBO, planBO.getAccountPrincipal(), context));
		
		remainingAmount -= paidPrincipal;
		
		return remainingAmount;
	}

	/**
	 * 还部分
	 * @param loanInfoBO
	 * @param planBO
	 * @param remainingAmount
	 * @param context
	 * @return
	 */
	private long payPartialNormalWhenInAdvance(final RepaymentLoanInfoBO loanInfoBO, final RepaymentPlanBO planBO, long remainingAmount,
			final RepaymentCalcContext context) {
		
		RepaymentAmount repaymentAmount = partialNormalRepaymentAmount(loanInfoBO, planBO, remainingAmount, context);
        
		final RepaymentSingleDistributeDetailBO allotDetailBO = planBO.createAllotDetailIfMissing();
		
		long paidServiceCharge = Math.min(remainingAmount, repaymentAmount.getAccountServiceCharge());
		planBO.setPaidNormalServiceCharge(planBO.getPaidNormalServiceCharge() + paidServiceCharge);
		allotDetailBO.setPayNormalServiceCharge(paidServiceCharge);

		remainingAmount -= paidServiceCharge;
		long paidInterest = Math.min(remainingAmount, repaymentAmount.getAccountInterest());
		planBO.setPaidNormalInterest(planBO.getPaidNormalInterest() + paidInterest);
		allotDetailBO.setPayNormalInterest(paidInterest);
		
		remainingAmount -= paidInterest;
		long financeBalance = RepaymentPlanBO.calcFinanceBalance(loanInfoBO.lazyLoadPlans(), planBO.getRepaymentPeriod());
		long paidPrincipal = Math.min(remainingAmount, repaymentAmount.getAccountPrincipal());
		planBO.setPaidNormalPrincipal(planBO.getPaidNormalPrincipal() + paidPrincipal);
		planBO.setAccountPrincipal(planBO.getAccountPrincipal() - paidPrincipal);
		planBO.setFinanceBalance(financeBalance - paidPrincipal);
		allotDetailBO.setPayNormalPrincipal(paidPrincipal);
		
		planBO.setAccountServiceCharge(calcNewServiceChargeAfterInAdvance(loanInfoBO, planBO, planBO.getAccountPrincipal(), context));
		planBO.setAccountInterest(calcNewInterestAfterInAdvance(loanInfoBO, planBO, planBO.getAccountPrincipal(), context));
		
		remainingAmount -= paidPrincipal;
		
		return remainingAmount;
	}

	private Long calcNewInterestAfterInAdvance(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO planBO,
			Long accountPrincipal, RepaymentCalcContext context) {
		return InterestCalcUtil.calcInterest(accountPrincipal,
				InterestRateUnit.valueOf(loanInfoBO.getInterestRateUnit()), loanInfoBO.getInterestRate(), DateUnit.DAY,
				CanaDateUtils.durationDays(planBO.getValueDate(), planBO.getRepaymentDate()));
	}

	/**
	 * 计算提前还款后还款计划新的服务费
	 * @param loanInfoBO
	 * @param planBO
	 * @param accountPrincipal
	 * @param context
	 * @return
	 */
	protected Long calcNewServiceChargeAfterInAdvance(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO planBO,
			Long accountPrincipal, RepaymentCalcContext context) {
		return 0L;
	}

	/**
	 * 当还款金额不足以支付所有应还时，将还款金额按照公式计算可还的本金和利息
	 * @param loanInfoBO
	 * @param planBO
	 * @param remainingAmount
	 * @param context
	 * @return
	 */
	protected RepaymentAmount partialNormalRepaymentAmount(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO planBO,
			long remainingAmount, RepaymentCalcContext context) {
		RepaymentAmount repaymentAmount = new RepaymentAmount();
		BigDecimal actualUseInterestRate = calcActualUseInterestRate(loanInfoBO, planBO, remainingAmount, context);
		BigDecimal interest = new BigDecimal(remainingAmount)
				.divide(BigDecimal.ONE.add(actualUseInterestRate), 16, RoundingMode.HALF_UP)
				.multiply(actualUseInterestRate);
		if (interest.compareTo(BigDecimal.ZERO) > 0 && interest.compareTo(BigDecimal.ONE) <= 0)
			interest = BigDecimal.ONE;

		long interestValue = MoneyArithUtil.round(interest, 0).longValue();
		repaymentAmount.setAccountInterest(interestValue);
		repaymentAmount.setAccountPrincipal(remainingAmount - interestValue);

		return repaymentAmount;
	}

	/**
	 * 计算实际使用的利率， 如：日利率0.0002, 使用5天的利率是0.001
	 * @param loanInfoBO
	 * @param planBO
	 * @param remainingAmount
	 * @param extra
	 * @return
	 */
	private BigDecimal calcActualUseInterestRate(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO planBO,
			long remainingAmount, RepaymentCalcContext context) {
		int durationDays = durationDays(loanInfoBO, planBO, context);
		return MoneyArithUtil.mul(durationDays, loanInfoBO.getInterestRate());
	}

	/**
	 * 最大正常还款金额
	 * @param loanInfoBO
	 * @param planBO
	 * @param context
	 * @return
	 */
	private RepaymentAmount maxNormalRepaymentAmount(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO planBO,
			RepaymentCalcContext context) {
		String repaymentDate10 = context.getRepaymentDate();
		if(planBO.isRepaymentDate(repaymentDate10))
			return maxNormalRepaymentAmountOnRepaymentDate(loanInfoBO, planBO, context);
		else return maxNormalRepaymentAmountWhenInAdvance(loanInfoBO, planBO, context);
	}

	/**
	 * 提前还款时应还的最大金额
	 * @param loanInfoBO
	 * @param planBO
	 * @param context
	 * @return
	 */
	private RepaymentAmount maxNormalRepaymentAmountWhenInAdvance(RepaymentLoanInfoBO loanInfoBO,
			RepaymentPlanBO planBO, RepaymentCalcContext context) {
		RepaymentAmount repaymentAmount = new RepaymentAmount();
		repaymentAmount.setAccountPrincipal(planBO.getAccountPrincipal());
		repaymentAmount.setAccountInterest(calcMaxCanPayInterestWhenInAdvance(loanInfoBO, planBO, context));
		repaymentAmount.setAccountServiceCharge(calcMaxCanPayServiceChargeWhenInAdvance(loanInfoBO, planBO, context));
		return repaymentAmount;
	}

	/**
	 * 计算最大可还利息
	 * @param loanInfoBO
	 * @param planBO
	 * @param context
	 * @return
	 */
	private long calcMaxCanPayInterestWhenInAdvance(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO planBO,
			RepaymentCalcContext context) {

		int durationDays = durationDays(loanInfoBO, planBO, context);
		return InterestCalcUtil.calcInterest(planBO.getAccountPrincipal(),
				InterestRateUnit.valueOf(loanInfoBO.getInterestRateUnit()), loanInfoBO.getInterestRate(), DateUnit.DAY,
				durationDays);
	}

	private int durationDays(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO planBO, RepaymentCalcContext context) {
		String repaymentDate10 = context.getRepaymentDate();
		int durationDays = 0;
		if(planBO.getValueDate().compareTo(repaymentDate10) >= 0)
			durationDays = 1;
		else durationDays = CanaDateUtils.durationDays(planBO.getValueDate(), repaymentDate10);
		return durationDays;
	}
	
	/**
	 * 计算最大可还服务费
	 * @param loanInfoBO
	 * @param planBO
	 * @param context
	 * @return
	 */
	private long calcMaxCanPayServiceChargeWhenInAdvance(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO planBO,
			RepaymentCalcContext context) {
		return 0L;
	}

	/**
	 * 在固定还款日当天还款时需要还的最大金额
	 * @param loanInfoBO
	 * @param planBO
	 * @param context
	 */
	private RepaymentAmount maxNormalRepaymentAmountOnRepaymentDate(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO planBO,
			RepaymentCalcContext context) {
		RepaymentAmount repaymentAmount = new RepaymentAmount();
		repaymentAmount.setAccountPrincipal(planBO.getAccountPrincipal());
		repaymentAmount.setAccountInterest(planBO.getAccountInterest());
		repaymentAmount.setAccountServiceCharge(planBO.getAccountServiceCharge());
		return repaymentAmount;
	}

	@Override
	public long totalDeduct(RepaymentLoanInfoBO loanInfoBO, String deductDate10) {
		RepaymentPlanBO planBO = loanInfoBO.lazyLoadPlans().get(0);
		if(planBO.inOverdueState(deductDate10)){
			return planBO.totalOverdueAmount();
		}else if(planBO.inExtensionState(deductDate10)){
			return planBO.totalExtensionAmount();
		}else{
			RepaymentCalcContext context = new RepaymentCalcContext();
			context.setRepaymentDate(deductDate10);
			return maxNormalRepaymentAmount(loanInfoBO, planBO, context).unpaidTotal();
		}
	}

	@Override
	protected boolean needGenerateDefaultDeductTaskItem(RepaymentLoanInfoBO loanInfoBO, String curDate) {
		if(curDate.compareTo(loanInfoBO.getLoanDate()) >= 0)
			return true;
		else return false;
	}	
	
	@Override
	protected boolean needGenerateNonDefaultDeductTaskItem(final RepaymentLoanInfoBO loanInfoBO, String curDate){
		return super.needGenerateNonDefaultDeductTaskItem(loanInfoBO, curDate) && curDate.compareTo(loanInfoBO.getLoanDate()) > 0;
	}

	@Override
	public RepaymentAmount calcAccountInterestAndAccountServiceChargeUntilNow(RepaymentLoanInfoBO loanInfoBO,
			RepaymentPlanBO planBO) throws Exception {
		RepaymentAmount result = new RepaymentAmount();
		String currentDate = commonService.getCurrentDate();
		if(planBO.inOverdueState(currentDate)){ // 逾期直接返回0
			return result;
		}else if(planBO.inExtensionState(currentDate)){ //展期返回计划中当前的金额
			result.setAccountPrincipal(planBO.getAccountPrincipal());
			result.setAccountInterest(planBO.getAccountInterest());
			result.setAccountServiceCharge(planBO.getAccountServiceCharge());
			return result;
		}else{ // 正常状态需要实时计算
			RepaymentCalcContext context = new RepaymentCalcContext();
			context.setRepaymentDate(currentDate);
			RepaymentAmount maxRepaymentAmount = maxNormalRepaymentAmount(loanInfoBO, planBO, context);
			result.setAccountInterest(maxRepaymentAmount.getAccountInterest());
			result.setAccountServiceCharge(maxRepaymentAmount.getAccountServiceCharge());
			return result;
		}
	}

	@Override
	public long calcMinimumRepaymentAmount(RepaymentLoanInfoBO loanInfoBO, String curDate10) {

		return 0l;

	}

	@Override
	public long calcMaximumRepaymentAmount(RepaymentLoanInfoBO loanInfoBO, String curDate10) {
		return totalDeduct(loanInfoBO, curDate10);
	}

	@Override
	public PreCalcInterestResponse preCalcInterest(PreCalcInterestRequest request) throws Exception {

		String loanDueDate = calcLoanDueDate(request.getLoanDate(), request.getLoanPeriodUnit(), request.getLoanPeriod());

		long interest = InterestCalcUtil.calcInterest(request.getPrincipal(), request.getInterestRateUnit(),
                request.getInterestRate(), DateUnit.DAY,
                CanaDateUtils.durationDays(request.getLoanDate(), loanDueDate));

		PreCalcRepaymentPlan plan = new PreCalcRepaymentPlan();
		plan.setPeriod(1);
		plan.setValueDate(request.getLoanDate());
		plan.setRepaymentDate(loanDueDate);
		plan.setPrincipal(request.getPrincipal());
		plan.setInterest(interest);
		List<PreCalcRepaymentPlan> plans = Lists.newArrayList(plan);
		PreCalcInterestResponse response = new PreCalcInterestResponse();
		response.setRepaymentPlans(plans);
		return response;
	}
}
