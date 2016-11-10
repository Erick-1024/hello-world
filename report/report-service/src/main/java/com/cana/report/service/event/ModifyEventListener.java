package com.cana.report.service.event;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.repayment.dao.po.RepaymentExpenseSnapshot;
import com.cana.repayment.dao.po.RepaymentPlanSnapshot;
import com.cana.report.dao.po.ReportFactorFinanceDay;
import com.cana.report.dao.po.ReportFactorFinanceYear;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.report.enums.BusinessProduct;

public class ModifyEventListener extends AbstractEventListener {
	
	private static final Logger logger = LoggerFactory.getLogger(ModifyEventListener.class);
	
	@Override
	public void doExecute(LoanInfoChangeEvent event) throws Exception {
		logger.info("融资报表生成定时任务 - 执行 - 放款信息[id：{}]修改还款计划处理器", event.getNewSnapshot().getLoanInfoId());
		Delta delta = this.new Delta();
//		List<RepaymentPlanSnapshot> newRepaymentPlanSnapshots = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getNewSnapshot().getId());
//		if(newRepaymentPlanSnapshots.size() != event.getNewSnapshot().getRepaymentPeriod())
//			return;
		calculate(event, delta);
		updateFactorReport(event, delta);
		updateFinanceReport(event, delta);
		updateCoreCompanyReport(event, delta);
		updateCanaReport(event, delta);
		logger.info("融资报表生成定时任务 - 状态 - 放款信息[id：{}]修改还款计划处理器处理完成，追踪至[版本号：{}]", event.getNewSnapshot().getLoanInfoId(), event.getNewSnapshot().getCurrentVersion());
	}
	
	private void updateFactorReport(LoanInfoChangeEvent event, Delta delta) {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getOldSnapshot().getBusinessProductId())){
			businessProductId = event.getOldSnapshot().getBusinessProductId();
		}
		//更新保理商的日报表
		ReportFactorFinanceDay reportFactorDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getFactorId(), businessProductId);
		updateDailyReport(reportFactorDay, delta);
		//更新保理商的年报表
		ReportFactorFinanceYear reportFactorYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getFactorId(), businessProductId);
		updateAnnualReport(reportFactorYear, delta);
	}
	
	private void updateCanaReport(LoanInfoChangeEvent event, Delta delta) {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getOldSnapshot().getBusinessProductId())){
			businessProductId = event.getOldSnapshot().getBusinessProductId();
		}
		//更新cana的日报表
		ReportFactorFinanceDay reportCanaDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), userApi.getCanaId(), businessProductId);
		updateDailyReport(reportCanaDay, delta);
		//更新cana的年报表
		ReportFactorFinanceYear reportCanaYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), userApi.getCanaId(), businessProductId);
		updateAnnualReport(reportCanaYear, delta);
	}
	
	private void updateFinanceReport(LoanInfoChangeEvent event, Delta delta) {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getOldSnapshot().getBusinessProductId())){
			businessProductId = event.getOldSnapshot().getBusinessProductId();
		}
		//如果放款信息的融资客户存在
		if(StringUtils.isNotBlank(event.getNewSnapshot().getFinanceId())) {
			//更新融资客户的日报表
			ReportFactorFinanceDay reportFinanceDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateDailyReport(reportFinanceDay, delta);
			//更新融资客户的年报表
			ReportFactorFinanceYear reportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateAnnualReport(reportFinanceYear, delta);
		}
	}
	
	private void updateCoreCompanyReport(LoanInfoChangeEvent event, Delta delta) {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getOldSnapshot().getBusinessProductId())){
			businessProductId = event.getOldSnapshot().getBusinessProductId();
		}
		//如果放款信息的核心企业存在
		if(StringUtils.isNoneBlank(event.getNewSnapshot().getCoreCompanyId())) {
			//更新核心企业的日报表
			ReportFactorFinanceDay reportFinanceDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateDailyReport(reportFinanceDay, delta);
			//更新核心企业的年报表
			ReportFactorFinanceYear reportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateAnnualReport(reportFinanceYear, delta);
		}
	}
	
	private void updateDailyReport(ReportFactorFinanceDay reportFactorFinanceDay, Delta delta){
		if(null == reportFactorFinanceDay)
			return;
		reportFactorFinanceDay.setFinanceBalance(reportFactorFinanceDay.getFinanceBalance() + delta.financeBalanceDiff);
		reportFactorFinanceDay.setAccountPrincipal(reportFactorFinanceDay.getAccountPrincipal() + delta.dailyAccountPrincipalDiff);
		reportFactorFinanceDay.setAccountCharge(reportFactorFinanceDay.getAccountCharge() + delta.dailyAccountInterestDiff + delta.dailyAccountServiceChargeDiff + delta.dailyAccountExpenseDiff);
		reportFactorFinanceDay.setAccountInterest(reportFactorFinanceDay.getAccountInterest() + delta.dailyAccountInterestDiff);
		reportFactorFinanceDay.setAccountServiceCharge(reportFactorFinanceDay.getAccountServiceCharge() + delta.dailyAccountServiceChargeDiff);
		reportFactorFinanceDay.setAccountExpense(reportFactorFinanceDay.getAccountExpense() + delta.dailyAccountExpenseDiff);
		reportFactorFinanceDay.setAdjustPrincipal(reportFactorFinanceDay.getAdjustPrincipal() + delta.adjustPrincipalDiff);
		reportFactorFinanceDay.setAdjustAmount(reportFactorFinanceDay.getAdjustAmount() + delta.adjustInterestDiff + delta.adjustServiceChargeDiff + delta.adjustExpenseDiff);
		reportFactorFinanceDay.setAdjustInterest(reportFactorFinanceDay.getAdjustInterest() + delta.adjustInterestDiff);
		reportFactorFinanceDay.setAdjustServiceCharge(reportFactorFinanceDay.getAdjustServiceCharge() + delta.adjustServiceChargeDiff);
		reportFactorFinanceDay.setAdjustExpense(reportFactorFinanceDay.getAdjustExpense() + delta.adjustExpenseDiff);
		reportFactorFinanceDayMapper.updateByPrimaryKeySelective(reportFactorFinanceDay);
	}
	
	private void updateAnnualReport(ReportFactorFinanceYear reportFactorFinanceYear, Delta delta){
		if(null == reportFactorFinanceYear)
			return;
		reportFactorFinanceYear.setFinanceBalance(reportFactorFinanceYear.getFinanceBalance() + delta.financeBalanceDiff);
		reportFactorFinanceYear.setAccountPrincipal(reportFactorFinanceYear.getAccountPrincipal() + delta.annualAccountPrincipalDiff);
		reportFactorFinanceYear.setAccountCharge(reportFactorFinanceYear.getAccountCharge() + delta.annualAccountInterestDiff + delta.annualAccountServiceChargeDiff + delta.annualAccountExpenseDiff);
		reportFactorFinanceYear.setAccountInterest(reportFactorFinanceYear.getAccountInterest() + delta.annualAccountInterestDiff);
		reportFactorFinanceYear.setAccountServiceCharge(reportFactorFinanceYear.getAccountServiceCharge() + delta.annualAccountServiceChargeDiff);
		reportFactorFinanceYear.setAccountExpense(reportFactorFinanceYear.getAccountExpense() + delta.annualAccountExpenseDiff);
		reportFactorFinanceYear.setAdjustPrincipal(reportFactorFinanceYear.getAdjustPrincipal() + delta.adjustPrincipalDiff);
		reportFactorFinanceYear.setAdjustAmount(reportFactorFinanceYear.getAdjustAmount() + delta.adjustInterestDiff + delta.adjustServiceChargeDiff + delta.adjustExpenseDiff);
		reportFactorFinanceYear.setAdjustInterest(reportFactorFinanceYear.getAdjustInterest() + delta.adjustInterestDiff);
		reportFactorFinanceYear.setAdjustServiceCharge(reportFactorFinanceYear.getAdjustServiceCharge() + delta.adjustServiceChargeDiff);
		reportFactorFinanceYear.setAdjustExpense(reportFactorFinanceYear.getAdjustExpense() + delta.adjustExpenseDiff);
		reportFactorFinanceYearMapper.updateByPrimaryKeySelective(reportFactorFinanceYear);
	}
	
	//计算报表修改还款计划时。更新需要的字段
		private void calculate(LoanInfoChangeEvent event, Delta delta) {
			delta.financeBalanceDiff += event.getNewSnapshot().getFinanceBalance() - event.getOldSnapshot().getFinanceBalance();
			
			List<RepaymentPlanSnapshot> newRepaymentPlanSnapshots = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getNewSnapshot().getId());
			List<RepaymentPlanSnapshot> oldRepaymentPlanSnapshots = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getOldSnapshot().getId());
			for (RepaymentPlanSnapshot newRepaymentPlanSnapshot : newRepaymentPlanSnapshots) {
				RepaymentPlanSnapshot oldRepaymentPlanSnapshot = new RepaymentPlanSnapshot();
				//如果修改还款计划时，新增加一条还款计划，必定会抛出IndexOutOfBoundsException，此时将使用新初始化的po类，并将使用到的Long变量设置为0；
				try {
					oldRepaymentPlanSnapshot = oldRepaymentPlanSnapshots.get(newRepaymentPlanSnapshots.indexOf(newRepaymentPlanSnapshot));
				} catch(IndexOutOfBoundsException  ie) {
					oldRepaymentPlanSnapshot.setAccountPrincipal(0L);
					oldRepaymentPlanSnapshot.setAccountInterest(0L);
					oldRepaymentPlanSnapshot.setAccountServiceCharge(0L);
					// 新增还款计划且计划是已结清的状态，将已还本金加入当日/当年调账本金，已还利息以及已还服务费加入当日/当年调账费用
					if(StringUtils.equals(SettleStatus.SETTLED.name(), newRepaymentPlanSnapshot.getSettleStatus())){
						delta.adjustPrincipalDiff += newRepaymentPlanSnapshot.getPaidNormalPrincipal();
//						delta.adjustAmoutDiff += newRepaymentPlanSnapshot.getPaidNormalInterest() + newRepaymentPlanSnapshot.getPaidNormalServiceCharge();
						delta.adjustInterestDiff += newRepaymentPlanSnapshot.getPaidNormalInterest();
						delta.adjustServiceChargeDiff += newRepaymentPlanSnapshot.getPaidNormalServiceCharge();
						
					}
				}
				//当还款日为当天时,计算应还
				if(event.getCurDay().equals(newRepaymentPlanSnapshot.getRepaymentDate())) {
					
					//当日应还本金
					delta.dailyAccountPrincipalDiff += newRepaymentPlanSnapshot.getAccountPrincipal() - oldRepaymentPlanSnapshot.getAccountPrincipal();
					
					//当日应还费用 = 当日应还利息 + 当日应还服务费
//					delta.dailyAccountChargeDiff += newRepaymentPlanSnapshot.getAccountInterest() - oldRepaymentPlanSnapshot.getAccountInterest()
//											+ newRepaymentPlanSnapshot.getAccountServiceCharge() - oldRepaymentPlanSnapshot.getAccountServiceCharge();
					delta.dailyAccountInterestDiff += newRepaymentPlanSnapshot.getAccountInterest() - oldRepaymentPlanSnapshot.getAccountInterest();
					delta.dailyAccountServiceChargeDiff += newRepaymentPlanSnapshot.getAccountServiceCharge() - oldRepaymentPlanSnapshot.getAccountServiceCharge();
				}
				//当还款日为当年时，计算应还
				if(event.getCurYear().equals(newRepaymentPlanSnapshot.getRepaymentDate().substring(0, 4))) {
					
					//当年应还本金
					delta.annualAccountPrincipalDiff += newRepaymentPlanSnapshot.getAccountPrincipal() - oldRepaymentPlanSnapshot.getAccountPrincipal();
					
					//当年应还费用 = 当年应还利息 + 当年应还服务费
//					delta.annualAccountChargeDiff += newRepaymentPlanSnapshot.getAccountInterest() - oldRepaymentPlanSnapshot.getAccountInterest()
//											 + newRepaymentPlanSnapshot.getAccountServiceCharge() - oldRepaymentPlanSnapshot.getAccountServiceCharge();
					delta.annualAccountInterestDiff += newRepaymentPlanSnapshot.getAccountInterest() - oldRepaymentPlanSnapshot.getAccountInterest();
					delta.annualAccountServiceChargeDiff += newRepaymentPlanSnapshot.getAccountServiceCharge() - oldRepaymentPlanSnapshot.getAccountServiceCharge();
				}
			}
			
			List<RepaymentExpenseSnapshot> newRepaymentExpenseSnapshots = repaymentPlanSnapshotService.getRepaymentExpenseSnapshotByLoanInfoSnapshotId(event.getNewSnapshot().getId());
			List<RepaymentExpenseSnapshot> oldRepaymentExpenseSnapshots = repaymentPlanSnapshotService.getRepaymentExpenseSnapshotByLoanInfoSnapshotId(event.getOldSnapshot().getId());
			for (RepaymentExpenseSnapshot newRepaymentExpenseSnapshot : newRepaymentExpenseSnapshots) {
				RepaymentExpenseSnapshot oldRepaymentExpenseSnapshot = new RepaymentExpenseSnapshot();
				//如果修改固定费用时，新增加一条固定费用，必定会抛出IndexOutOfBoundsException，此时将使用新初始化的po类，并将使用到的Long变量设置为0；
				try {
					oldRepaymentExpenseSnapshot = oldRepaymentExpenseSnapshots.get(newRepaymentExpenseSnapshots.indexOf(newRepaymentExpenseSnapshot));
				} catch (IndexOutOfBoundsException ie) {
					oldRepaymentExpenseSnapshot.setPaidAmount(0L);
					oldRepaymentExpenseSnapshot.setRepaymentAmount(0L);
					// 新增固定费用且固定费用是已结清的状态，将已还费用加入当日/当年调账费用
					if(StringUtils.equals(SettleStatus.SETTLED.name(), newRepaymentExpenseSnapshot.getSettleStatus())){
//						delta.adjustAmoutDiff += newRepaymentExpenseSnapshot.getPaidAmount() - oldRepaymentExpenseSnapshot.getPaidAmount();
						delta.adjustExpenseDiff += (newRepaymentExpenseSnapshot.getPaidAmount() - oldRepaymentExpenseSnapshot.getPaidAmount());
					}
				}
				
				//还款日在今天以前(包括今天)应还固定费用
				if(DateTime.parse(event.getCurDay()).compareTo(DateTime.parse(newRepaymentExpenseSnapshot.getRepaymentDate())) >= 0) {
					delta.dailyAccountExpenseDiff += newRepaymentExpenseSnapshot.getRepaymentAmount() - oldRepaymentExpenseSnapshot.getRepaymentAmount();
				}
					
				//还款日在今年以前(包括今年)应还固定费用
				if(DateTime.parse(event.getCurDay()).plusYears(1).compareTo(DateTime.parse(newRepaymentExpenseSnapshot.getRepaymentDate())) > 0) {
					delta.annualAccountExpenseDiff += newRepaymentExpenseSnapshot.getRepaymentAmount() - oldRepaymentExpenseSnapshot.getRepaymentAmount();
				}
			}
		}
		
		private class Delta {
			private long financeBalanceDiff = 0;				//融资余额
			private long dailyAccountPrincipalDiff = 0;		//当日应还本金
			private long dailyAccountInterestDiff = 0;
			private long dailyAccountServiceChargeDiff = 0;
//			private long dailyAccountChargeDiff = 0;		//当日应还费用
			private long dailyAccountExpenseDiff = 0;		//当日应还固定费用
			private long annualAccountPrincipalDiff = 0;	//当年应还本金
			private long annualAccountInterestDiff = 0;
			private long annualAccountServiceChargeDiff = 0;
//			private long annualAccountChargeDiff = 0;		//当年应还费用
			private long annualAccountExpenseDiff = 0;		//当年应还固定费用
			private long adjustPrincipalDiff = 0;           //调账本金
			private long adjustInterestDiff = 0;
			private long adjustServiceChargeDiff = 0;
			private long adjustExpenseDiff = 0;
//			private long adjustAmoutDiff = 0;               //调账费用
		}
}
