package com.cana.repayment.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPlanMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentSingleCollectMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentSingleDistributeDetailMapper;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskItem;
import com.cana.repayment.service.IRepositoryService;
import com.cana.repayment.service.bo.RepaymentDailyBatchTaskBO;
import com.cana.repayment.service.bo.RepaymentDailyBatchTaskItemBO;
import com.cana.repayment.service.bo.RepaymentExpenseBO;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.repayment.service.bo.RepaymentSingleDistributeDetailBO;
import com.cana.repayment.service.context.RepaymentCalcContext;
import com.cana.repayment.service.util.IRepaymentServiceHelper;
import com.cana.setting.service.transaction.ICanaCalendarTransactionService;
import com.cana.vbam.common.repayment.dto.LoanInfoRepaymentResult;
import com.cana.vbam.common.repayment.dto.PreCalcInterestRequest;
import com.cana.vbam.common.repayment.dto.PreCalcInterestResponse;
import com.cana.vbam.common.repayment.dto.RepaymentAmount;
import com.cana.vbam.common.repayment.enums.BatchTaskType;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentMethod;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.CanaDateUtils;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public abstract class AbstractRepaymentCalc implements IRepaymentCalc{
	
	protected SequenceGenerator seqGen = SpringApplicationContext.getApplicationContext().getBean(SequenceGenerator.class);
	
	protected IRepositoryService repositoryService = SpringApplicationContext.getApplicationContext().getBean(IRepositoryService.class);
	
	protected RepaymentLoanInfoMapper loanInfoMapper = SpringApplicationContext.getApplicationContext().getBean(RepaymentLoanInfoMapper.class);
	
	protected RepaymentPlanMapper planMapper = SpringApplicationContext.getApplicationContext().getBean(RepaymentPlanMapper.class);
	
	protected RepaymentSingleCollectMapper repaymentSummaryMapper = SpringApplicationContext.getApplicationContext().getBean(RepaymentSingleCollectMapper.class);
	
	protected RepaymentSingleDistributeDetailMapper singleDistributeDetailMapper = SpringApplicationContext.getApplicationContext().getBean(RepaymentSingleDistributeDetailMapper.class);
	
	protected IRepaymentServiceHelper serviceHelper = SpringApplicationContext.getApplicationContext().getBean(IRepaymentServiceHelper.class);
	
	protected IVbamCommonService commonService = SpringApplicationContext.getApplicationContext().getBean(IVbamCommonService.class);

	protected ICanaCalendarTransactionService holidayService = SpringApplicationContext.getApplicationContext().getBean(ICanaCalendarTransactionService.class);


	@Override
	public long totalDeduct(RepaymentLoanInfoBO loanInfoBO, String curDate10) {
		return loanInfoBO.totalOverdueAmount(curDate10) + loanInfoBO.totalUnpaidExpensesAmount(curDate10) 
	         + loanInfoBO.totalExtensionAmount(curDate10) + loanInfoBO.totalNormalAmountOnRepaymentDate(curDate10);
	}

	@Override
	public void deduct(long actualDeductAmount, RepaymentLoanInfoBO loanInfoBO,
			RepaymentDailyBatchTaskBO taskBO, RepaymentDailyBatchTaskItemBO taskItemBO, String curDate10) throws Exception {
		
		RepaymentCalcContext context = new RepaymentCalcContext();
		context.setRepaymentDate(curDate10);
		
		long remainingAmount = payOverduePlans(loanInfoBO, actualDeductAmount, curDate10, context);
		remainingAmount = payExpenses(loanInfoBO, remainingAmount, curDate10, context);
		remainingAmount = payExtensionPlans(loanInfoBO, remainingAmount, curDate10, context);
		remainingAmount = payNormalPlans(loanInfoBO, remainingAmount, curDate10, context);
		
		if(remainingAmount > 0)
			throw new Exception("账扣金额计算可能出现了问题");
		
	}
	
	protected long payNormalPlans(RepaymentLoanInfoBO loanInfoBO, long remainingAmount, String curDate10,
			RepaymentCalcContext context) {
		for(RepaymentPlanBO planBO : loanInfoBO.normalPlansOnRepaymentDate(curDate10))
			remainingAmount = payNormalPlan(loanInfoBO, planBO, remainingAmount, context);
		return remainingAmount;
	}

	private long payExtensionPlans(RepaymentLoanInfoBO loanInfoBO, long remainingAmount, String curDate10,
			RepaymentCalcContext context) {
		for(RepaymentPlanBO planBO : loanInfoBO.plansInExtensionState(curDate10))
			remainingAmount = payExtensionPlan(loanInfoBO, planBO, remainingAmount, context);
		return remainingAmount;
	}

	private long payExpenses(RepaymentLoanInfoBO loanInfoBO, long remainingAmount, String curDate10,
			RepaymentCalcContext context) {
		for(RepaymentExpenseBO expenseBO : loanInfoBO.unpaidExpenses(curDate10))
			remainingAmount = payExpense(loanInfoBO, expenseBO, remainingAmount, curDate10, context);
		return remainingAmount;
	}

	protected long payExpense(RepaymentLoanInfoBO loanInfoBO, final RepaymentExpenseBO expenseBO, long remainingAmount,
			String curDate10, RepaymentCalcContext context) {
		RepaymentSingleDistributeDetailBO allotDetailBO = expenseBO.createAllotDetailIfMissing();
		long paid = Math.min(remainingAmount, expenseBO.unpaid(curDate10));
		expenseBO.setPaidAmount(expenseBO.getPaidAmount() + paid);
		expenseBO.setRepaymentAmount(expenseBO.getRepaymentAmount() - paid);
		allotDetailBO.setPayExpense(paid);
		remainingAmount -= paid;
		return remainingAmount;
	}

	private long payOverduePlans(RepaymentLoanInfoBO loanInfoBO, long remainingRepaymentAmount, String curDate10,
			RepaymentCalcContext context) {
		for(RepaymentPlanBO planBO : loanInfoBO.overduePlans(curDate10))
			remainingRepaymentAmount = payOverduePlan(loanInfoBO, planBO, remainingRepaymentAmount, context);
		return remainingRepaymentAmount;
	}

	/**
	 * 还处于正常状态的账扣
	 * @param remainingAmount
	 * @return
	 */
	protected long payNormalPlan(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO planBO, long remainingAmount, RepaymentCalcContext context) {
		return payExtensionPlan(loanInfoBO, planBO, remainingAmount, context);
	}
	
	/**
	 * 还逾期
	 * @param loanInfoBO
	 * @param remainingRepaymentAmount
	 * @param extra
	 * @return 
	 */
	protected Long payOverduePlan(RepaymentLoanInfoBO loanInfoBO, final RepaymentPlanBO planBO, long remainingAmount,
			RepaymentCalcContext context) {
		
		RepaymentSingleDistributeDetailBO allotDetailBO = planBO.createAllotDetailIfMissing();
		
		long paidOtherPenalty = Math.min(remainingAmount, planBO.getOtherPenalty());
		planBO.setPaidOtherPenalty(paidOtherPenalty + planBO.getPaidOtherPenalty());
		planBO.setOtherPenalty(planBO.getOtherPenalty() - paidOtherPenalty);
		allotDetailBO.setPayOtherPenalty(paidOtherPenalty);
		
		remainingAmount -= paidOtherPenalty; 
		long paidServiceChargePenalty = Math.min(remainingAmount, planBO.getOverdueServiceChargePenalty());
		planBO.setPaidOverdueServiceChargePenalty(planBO.getPaidOverdueServiceChargePenalty() + paidServiceChargePenalty);
		planBO.setOverdueServiceChargePenalty(planBO.getOverdueServiceChargePenalty() - paidServiceChargePenalty);
		allotDetailBO.setPayOverdueServiceChargePenalty(paidServiceChargePenalty);
		
		remainingAmount -= paidServiceChargePenalty;
		long paidInterestPenalty = Math.min(remainingAmount, planBO.getOverdueInterestPenalty());
		planBO.setPaidOverdueInterestPenalty(planBO.getPaidOverdueInterestPenalty() + paidInterestPenalty);
		planBO.setOverdueInterestPenalty(planBO.getOverdueInterestPenalty() - paidInterestPenalty);
		allotDetailBO.setPayOverdueInterestPenalty(paidInterestPenalty);
		
		remainingAmount -= paidInterestPenalty;
		long paidPrincipalPenalty = Math.min(remainingAmount, planBO.getOverduePrincipalPenalty());
		planBO.setPaidOverduePrincipalPenalty(planBO.getPaidOverduePrincipalPenalty() + paidPrincipalPenalty);
		planBO.setOverduePrincipalPenalty(planBO.getOverduePrincipalPenalty() - paidPrincipalPenalty);
		allotDetailBO.setPayOverduePrincipalPenalty(paidPrincipalPenalty);
		
		remainingAmount -= paidPrincipalPenalty;
		long paidExtensionCharge = Math.min(remainingAmount, planBO.overdueExtensionCharge());
		planBO.setPaidExtensionCharge(planBO.getPaidExtensionCharge() + paidExtensionCharge); 
		planBO.setAccountExtensionCharge(planBO.getAccountExtensionCharge() - paidExtensionCharge);
		allotDetailBO.setPayExtensionCharge(allotDetailBO.getPayExtensionCharge() == null ? paidExtensionCharge : paidExtensionCharge + allotDetailBO.getPayExtensionCharge());
		
		remainingAmount -= paidExtensionCharge;
		long paidServiceCharge = Math.min(remainingAmount, planBO.getOverdueServiceCharge());
		planBO.setPaidOverdueServiceCharge(planBO.getPaidOverdueServiceCharge() + paidServiceCharge); 
		planBO.setOverdueServiceCharge(planBO.getOverdueServiceCharge() - paidServiceCharge);
		allotDetailBO.setPayOverdueServiceCharge(paidServiceCharge);
		
		remainingAmount -= paidServiceCharge;
		long paidInterest = Math.min(remainingAmount, planBO.getOverdueInterest());
		planBO.setPaidOverdueInterest(planBO.getPaidOverdueInterest() + paidInterest);
		planBO.setOverdueInterest(planBO.getOverdueInterest() - paidInterest);
		allotDetailBO.setPayOverdueInterest(paidInterest);
		
		remainingAmount -= paidInterest;
		long financeBalance = RepaymentPlanBO.calcFinanceBalance(loanInfoBO.lazyLoadPlans(), planBO.getRepaymentPeriod());
		long paidPrincipal = Math.min(remainingAmount, planBO.getOverduePrincipal());
		planBO.setPaidOverduePrincipal(planBO.getPaidOverduePrincipal() + paidPrincipal);
		planBO.setOverduePrincipal(planBO.getOverduePrincipal() - paidPrincipal);
		planBO.setFinanceBalance(financeBalance - paidPrincipal);
		allotDetailBO.setPayOverduePrincipal(paidPrincipal);
		
		remainingAmount -= paidPrincipal;
		return remainingAmount;
	}
	
	@Override
	public void generateRepaymentPlan(RepaymentLoanInfoBO loanInfoBO, RepaymentCalcContext context) throws Exception{
		throw new UnsupportedOperationException("不支持自动生成还款计划");
	}

	/**
	 * 根据还款日查询可宽限天数
	 * @param repaymentDate
	 */
	private int getHolidayPolicyExtensionDays(String repaymentDate) throws Exception {
		Integer days = holidayService.getNotBeforeFirstWeekday(repaymentDate);
		return days == null ? 0 : days;
	}

	/**
	 * 计算还款计划的展期天数
	 * @param repaymentDate 还款计划的固定还款日
	 * @param loanExtensionDays 放款信息的展期天数
	 * @param useHolidayPolicy 是否使用节假日政策
	 */
	protected int calcExtensionDaysForPlan(String repaymentDate, int loanExtensionDays, boolean useHolidayPolicy) throws Exception {
		if (!useHolidayPolicy)
			return loanExtensionDays;
		int holidayExtensionDays = getHolidayPolicyExtensionDays(repaymentDate);
		return holidayExtensionDays > loanExtensionDays ? holidayExtensionDays : loanExtensionDays;
	}

	/**
	 * 还处于展期状态的还款计划
	 * @param loanInfoBO
	 * @param planBO
	 * @param remainingRepaymentAmount
	 * @param extra
	 * @return
	 */
	protected long payExtensionPlan(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO planBO, long remainingAmount,
			RepaymentCalcContext context) {
		
		final RepaymentSingleDistributeDetailBO allotDetailBO = planBO.createAllotDetailIfMissing();
		
		long paidExtensionCharge = Math.min(remainingAmount, planBO.normalExtensionCharge());
		planBO.setPaidExtensionCharge(planBO.getPaidExtensionCharge() + paidExtensionCharge); 
		planBO.setAccountExtensionCharge(planBO.getAccountExtensionCharge() - paidExtensionCharge);
		allotDetailBO.setPayExtensionCharge(allotDetailBO.getPayExtensionCharge() == null ? paidExtensionCharge : paidExtensionCharge + allotDetailBO.getPayExtensionCharge());
		
		remainingAmount -= paidExtensionCharge;
		long paidServiceCharge = Math.min(remainingAmount, planBO.getAccountServiceCharge());
		planBO.setPaidNormalServiceCharge(planBO.getPaidNormalServiceCharge() + paidServiceCharge);
		planBO.setAccountServiceCharge(planBO.getAccountServiceCharge() - paidServiceCharge);
		allotDetailBO.setPayNormalServiceCharge(paidServiceCharge);
		
		remainingAmount -= paidServiceCharge;
		long paidInterest = Math.min(remainingAmount, planBO.getAccountInterest());
		planBO.setPaidNormalInterest(planBO.getPaidNormalInterest() + paidInterest);
		planBO.setAccountInterest(planBO.getAccountInterest() - paidInterest);
		allotDetailBO.setPayNormalInterest(paidInterest);
		
		remainingAmount -= paidInterest;
		long financeBalance = RepaymentPlanBO.calcFinanceBalance(loanInfoBO.lazyLoadPlans(), planBO.getRepaymentPeriod());
		long paidPrincipal = Math.min(remainingAmount, planBO.getAccountPrincipal());
		planBO.setPaidNormalPrincipal(planBO.getPaidNormalPrincipal() + paidPrincipal);
		planBO.setAccountPrincipal(planBO.getAccountPrincipal() - paidPrincipal);
		planBO.setFinanceBalance(financeBalance - paidPrincipal);
		allotDetailBO.setPayNormalPrincipal(paidPrincipal);
		
		remainingAmount -= paidPrincipal;
		
		return remainingAmount;
	}

	@Override
	public List<RepaymentDailyBatchTaskItemBO> generateDailyBatchTask(final RepaymentLoanInfoBO loanInfoBO, String curDate)
			throws Exception {
		
		final List<RepaymentDailyBatchTaskItemBO> taskItems = new ArrayList<>();
		
		if(needGenerateDefaultDeductTaskItem(loanInfoBO, DateTimeUtil.addDay10(curDate, -1))){ //判断是否需要生成默认账扣任务
			RepaymentDailyBatchTaskItemBO taskItemBO = generateBatchTaskItem(loanInfoBO, BatchTaskType.deduct, "");
			taskItemBO.updateExtraData("defaultDeduct", "true");
			taskItems.add(taskItemBO);
		}
		
		if(needGenerateExtensionChargeTaskItem(loanInfoBO, curDate)){ // 判断是否需要生成展期费用
			RepaymentDailyBatchTaskItemBO taskItemBO = generateBatchTaskItem(loanInfoBO, BatchTaskType.extension_charge_generate, "");
			taskItems.add(taskItemBO);
		}
		
		if(needGenerateOverdueTaskItem(loanInfoBO, curDate)){ // 判断是否需要生成新增逾期任务
			RepaymentDailyBatchTaskItemBO taskItemBO = generateBatchTaskItem(loanInfoBO, BatchTaskType.overdue_generate, "");
			taskItems.add(taskItemBO);
		}
		
		if(needGeneratePenaltyTaskItem(loanInfoBO, curDate)){// 判断是否需要生成罚息
			RepaymentDailyBatchTaskItemBO taskItemBO = generateBatchTaskItem(loanInfoBO, BatchTaskType.penalty_generate, "");
			taskItems.add(taskItemBO);
		}
		
		if(needGenerateNonDefaultDeductTaskItem(loanInfoBO, curDate)){ // 保理商自己也设置了账扣时间
			
			String deductTime = loanInfoBO.lazyLoadRepaymentConfig().getDeductionTime();
			if(deductTime.compareTo("24:00") >= 0)
				deductTime = "00:00";
			else if(deductTime.compareTo("23:30") >= 0)
				deductTime = "23:30";
			
			RepaymentDailyBatchTaskItemBO taskItemBO = generateBatchTaskItem(loanInfoBO, BatchTaskType.deduct, deductTime);
			taskItemBO.updateExtraData("defaultDeduct", "false");
			taskItems.add(taskItemBO);
			
		}
		return taskItems;
	}
	
	/**
	 * 是否需要生成“生成罚息”任务
	 * @param loanInfoBO
	 * @param curDate
	 * @return
	 */
	private boolean needGeneratePenaltyTaskItem(RepaymentLoanInfoBO loanInfoBO, String curDate) {
		for(RepaymentPlanBO planBO : loanInfoBO.lazyLoadPlans()){
			if(curDate.compareTo(planBO.getRepaymentDate()) > 0){
				return true;
			}
		}
		return false;
	}

	/**
	 * 生成账扣任务
	 * @param loanInfoBO
	 * @param deduct
	 * @param string
	 * @return
	 */
	protected RepaymentDailyBatchTaskItemBO generateBatchTaskItem(RepaymentLoanInfoBO loanInfoBO, BatchTaskType taskType,
			String executeTime) {
		RepaymentDailyBatchTaskItemBO item = new RepaymentDailyBatchTaskItemBO(new RepaymentDailyBatchTaskItem());
		item.setId(serviceHelper.generateTaskItemId());
		item.setTaskType(taskType.name());
		item.setExecuteTime(executeTime);
		return item;
	}

	/**
	 * 是否需要生成默认账扣任务
	 * @param loanInfoBO
	 * @param curDate
	 * @return
	 */
	protected boolean needGenerateDefaultDeductTaskItem(final RepaymentLoanInfoBO loanInfoBO, String curDate){
		return totalDeduct(loanInfoBO, curDate) > 0;
	}
	
	/**
	 * 是否需要生成非默认账扣任务
	 * @param loanInfoBO
	 * @param curDate
	 * @return
	 */
	protected boolean needGenerateNonDefaultDeductTaskItem(final RepaymentLoanInfoBO loanInfoBO, String curDate){
		return loanInfoBO.lazyLoadRepaymentConfig() != null && StringUtils.isNotBlank(loanInfoBO.lazyLoadRepaymentConfig().getDeductionTime());
	}	
	
	/**
	 * 是否需要生成展期任务
	 * @param loanInfoBO
	 * @param curDate
	 * @return
	 */
	protected boolean needGenerateExtensionChargeTaskItem(final RepaymentLoanInfoBO loanInfoBO, String curDate){
		return loanInfoBO.inSomePlanExtensionTimeRange(curDate);
	}
	
	/**
	 * 是否需要生成逾期任务
	 * @param loanInfoBO
	 * @param curDate
	 * @return
	 */
	protected boolean needGenerateOverdueTaskItem(final RepaymentLoanInfoBO loanInfoBO, String curDate){
		return loanInfoBO.somePlanInOverdueTimeRangeAt(curDate);
	}

	@Override
	@Deprecated
	public PreCalcInterestResponse preCalcInterest(PreCalcInterestRequest request) throws Exception {
		throw new UnsupportedOperationException("不支持预算利息");
	}

	@Override
	public String calcLoanDueDate(String loanDate, DateUnit loanPeriodUnit, int loanPeriod) throws Exception {
		if (loanPeriod <= 0)
			throw new Exception("放款期限必须大于0");

		int periodMonths;
		if (loanPeriodUnit == DateUnit.MONTH) {
			periodMonths = loanPeriod;
		} else if (loanPeriodUnit == DateUnit.YEAR) {
			periodMonths = loanPeriod * 12;
		} else {
			return CanaDateUtils.plusDays(loanDate, loanPeriod);
		}

		if (CanaDateUtils.isMonthLastDay(loanDate))
			return CanaDateUtils.plusMonthsReturnLastDay(loanDate, periodMonths);
		else
			return CanaDateUtils.plusMonths(loanDate, periodMonths);
	}

	@Override
	public RepaymentAmount calcAccountInterestAndAccountServiceChargeUntilNow(RepaymentLoanInfoBO loanInfoBO,
			RepaymentPlanBO planBO) throws Exception {
		RepaymentAmount repaymentAmount = new RepaymentAmount();
//		repaymentAmount.setAccountInterest(planBO.getAccountInterest());
//		repaymentAmount.setAccountServiceCharge(planBO.getAccountServiceCharge());
		return repaymentAmount;
	}

	@Override
	public long prepareRepayment(RepaymentLoanInfoBO loanInfoBO, long repaymentAmount, String repaymentDate)
			throws Exception {
		RepaymentCalcContext context = new RepaymentCalcContext();
		context.setRepaymentDate(repaymentDate);
		context.setPrepareRepayment(true);
		context.setRepaymentMethod(RepaymentMethod.ACCOUNTDEDUCTION); // 此时保证此值不为空即可
		LoanInfoRepaymentResult result = repayment(loanInfoBO, repaymentAmount, context);
		return result.getActualRepaymentTotalAmount();
	}

	/**
	 * 统计本次还款的总的还款本金
	 * @param planBOs
	 */
	protected long sumActualRepaymentTotalPrincipal(List<RepaymentPlanBO> planBOs) {
		long actualRepaymentPrincipal = 0l;
		if (CollectionUtils.isNotEmpty(planBOs)) {
			for (RepaymentPlanBO planBO : planBOs) {
				RepaymentSingleDistributeDetailBO allotDetail = planBO.allotDetail();
				if (allotDetail == null)
					continue;
				actualRepaymentPrincipal += allotDetail.totalPrincipal();
			}
		}
		return actualRepaymentPrincipal;
	}
}
