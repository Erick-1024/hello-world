package com.cana.report.service.event;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.repayment.dao.po.RepaymentExpenseSnapshot;
import com.cana.repayment.dao.po.RepaymentPlanSnapshot;
import com.cana.report.dao.po.ReportFactorFinanceCount;
import com.cana.report.dao.po.ReportFactorFinanceDay;
import com.cana.report.dao.po.ReportFactorFinanceYear;
import com.cana.vbam.common.report.enums.BusinessProduct;

public class AdjustEventListener extends AbstractEventListener {
	
	private static final Logger logger = LoggerFactory.getLogger(AdjustEventListener.class);
	
	@Override
	public void doExecute(LoanInfoChangeEvent event) throws Exception {
		logger.info("融资报表生成定时任务 - 执行 - 放款信息[id：{}]调账处理器", event.getNewSnapshot().getLoanInfoId());
		Delta delta = this.new Delta();
		calculate(event, delta);
		updateFactorReport(event, delta);
		updateFinanceReport(event, delta);
		updateCoreCompanyReport(event, delta);
		updateCanaReport(event, delta);
		logger.info("融资报表生成定时任务 - 状态 - 放款信息[id：{}]调账处理器处理完成，追踪至[版本号：{}]", event.getNewSnapshot().getLoanInfoId(), event.getNewSnapshot().getCurrentVersion());
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
		//更新保理商的计数报表
		ReportFactorFinanceCount reportFactorCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), event.getNewSnapshot().getFactorId(), businessProductId);
		updateCountReport(reportFactorCount);
	}
	
	private void updateFinanceReport(LoanInfoChangeEvent event, Delta delta) {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getOldSnapshot().getBusinessProductId())){
			businessProductId = event.getOldSnapshot().getBusinessProductId();
		}
		//如果放款信息的融资客户存在
		if(StringUtils.isNoneBlank(event.getNewSnapshot().getFinanceId())) {
			//更新融资客户的日报表
			ReportFactorFinanceDay reportFinanceDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateDailyReport(reportFinanceDay, delta);
			//更新融资客户的年报表
			ReportFactorFinanceYear reportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateAnnualReport(reportFinanceYear, delta);
			//更新融资客户的计数报表
			ReportFactorFinanceCount reportFinanceCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateCountReport(reportFinanceCount);
		}
	}
	
	private void updateCoreCompanyReport(LoanInfoChangeEvent event, Delta delta){
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getOldSnapshot().getBusinessProductId())){
			businessProductId = event.getOldSnapshot().getBusinessProductId();
		}
		//如果放款信息的核心企业存在
		if(StringUtils.isNoneBlank(event.getNewSnapshot().getCoreCompanyId())) {
			//更新核心企业的日报表
			ReportFactorFinanceDay reportFinanceDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getCoreCompanyId(),businessProductId);
			updateDailyReport(reportFinanceDay, delta);
			//更新核心企业的年报表
			ReportFactorFinanceYear reportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateAnnualReport(reportFinanceYear, delta);
			//更新核心企业的计数报表
			ReportFactorFinanceCount reportFinanceCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateCountReport(reportFinanceCount);
		}
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
		//更新cana的计数报表
		ReportFactorFinanceCount reportCanaCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), userApi.getCanaId(), businessProductId);
		updateCountReport(reportCanaCount);
	}
	
	private void updateDailyReport(ReportFactorFinanceDay reportFactorFinanceDay, Delta delta){
		if(null == reportFactorFinanceDay)
			return;
		reportFactorFinanceDay.setAdjustAmount(reportFactorFinanceDay.getAdjustAmount() + delta.adjustInterestDiff + delta.adjustServiceChargeDiff 
				+ delta.adjustExtensionDiff + delta.adjustOverdueInterestDiff + delta.adjustOverdueServiceChargeDiff + delta.adjustPenaltyDiff + delta.annualAccountExpenseDiff);
		reportFactorFinanceDay.setAdjustInterest(reportFactorFinanceDay.getAdjustInterest() + delta.adjustInterestDiff);
		reportFactorFinanceDay.setAdjustServiceCharge(reportFactorFinanceDay.getAdjustServiceCharge() + delta.adjustServiceChargeDiff);
		reportFactorFinanceDay.setAdjustExtension(reportFactorFinanceDay.getAdjustExtension() + delta.adjustExtensionDiff);
		reportFactorFinanceDay.setAdjustOverdueInterest(reportFactorFinanceDay.getAdjustOverdueInterest() + delta.adjustOverdueInterestDiff);
		reportFactorFinanceDay.setAdjustOverdueServiceCharge(reportFactorFinanceDay.getAdjustOverdueServiceCharge() + delta.adjustOverdueServiceChargeDiff);
		reportFactorFinanceDay.setAdjustPenalty(reportFactorFinanceDay.getAdjustPenalty() + delta.adjustPenaltyDiff);
		reportFactorFinanceDayMapper.updateByPrimaryKeySelective(reportFactorFinanceDay);
	}
	
	private void updateAnnualReport(ReportFactorFinanceYear reportFactorFinanceYear, Delta delta){
		if(null == reportFactorFinanceYear)
			return;
		reportFactorFinanceYear.setAccountCharge(reportFactorFinanceYear.getAccountCharge() + delta.annualAccountInterestDiff + delta.annualAccountServiceChargeDiff
				+ delta.annualAccountExtensionChargeDiff + delta.annualAccountOverduePrincipalPenaltyDiff + delta.annualAccountOverdueInterestPenaltyDiff 
				+ delta.annualAccountOverdueServiceChargePenaltyDiff + delta.annualAccountOtherPenaltyDiff + delta.annualAccountExpenseDiff);
		reportFactorFinanceYear.setAccountInterest(reportFactorFinanceYear.getAccountInterest() + delta.annualAccountInterestDiff);
		reportFactorFinanceYear.setAccountServiceCharge(reportFactorFinanceYear.getAccountServiceCharge() + delta.annualAccountServiceChargeDiff);
		reportFactorFinanceYear.setAccountExtensionCharge(reportFactorFinanceYear.getAccountExtensionCharge() + delta.annualAccountExtensionChargeDiff);
		reportFactorFinanceYear.setAccountOverduePrincipalPenalty(reportFactorFinanceYear.getAccountOverduePrincipalPenalty() + delta.annualAccountOverduePrincipalPenaltyDiff);
		reportFactorFinanceYear.setAccountOverdueInterestPenalty(reportFactorFinanceYear.getAccountOverdueInterestPenalty() + delta.annualAccountOverdueInterestPenaltyDiff);
		reportFactorFinanceYear.setAccountOverdueServiceChargePenalty(reportFactorFinanceYear.getAccountOverdueServiceChargePenalty() + delta.annualAccountOverdueServiceChargePenaltyDiff);
		reportFactorFinanceYear.setAccountOtherPenalty(reportFactorFinanceYear.getAccountOtherPenalty() + delta.annualAccountOtherPenaltyDiff);
		reportFactorFinanceYear.setAccountExpense(reportFactorFinanceYear.getAccountExpense() + delta.annualAccountExpenseDiff);
		reportFactorFinanceYear.setAccountOverdue(reportFactorFinanceYear.getAccountOverdue() + delta.annualAccountOverdueInterestDiff + delta.annualAccountOverdueServiceChargeDiff
				+ delta.annualAccountOverdueExtensionChargeDiff);
		reportFactorFinanceYear.setAdjustAmount(reportFactorFinanceYear.getAdjustAmount() + delta.adjustInterestDiff + delta.adjustServiceChargeDiff 
				+ delta.adjustExtensionDiff + delta.adjustOverdueInterestDiff + delta.adjustOverdueServiceChargeDiff + delta.adjustPenaltyDiff + delta.annualAccountExpenseDiff);
		reportFactorFinanceYear.setAdjustInterest(reportFactorFinanceYear.getAdjustInterest() + delta.adjustInterestDiff);
		reportFactorFinanceYear.setAdjustServiceCharge(reportFactorFinanceYear.getAdjustServiceCharge() + delta.adjustServiceChargeDiff);
		reportFactorFinanceYear.setAdjustExtension(reportFactorFinanceYear.getAdjustExtension() + delta.adjustExtensionDiff);
		reportFactorFinanceYear.setAdjustOverdueInterest(reportFactorFinanceYear.getAdjustOverdueInterest() + delta.adjustOverdueInterestDiff);
		reportFactorFinanceYear.setAdjustOverdueServiceCharge(reportFactorFinanceYear.getAdjustOverdueServiceCharge() + delta.adjustOverdueServiceChargeDiff);
		reportFactorFinanceYear.setAdjustPenalty(reportFactorFinanceYear.getAdjustPenalty() + delta.adjustPenaltyDiff);
		reportFactorFinanceYearMapper.updateByPrimaryKeySelective(reportFactorFinanceYear);
	}
	
	private void updateCountReport(ReportFactorFinanceCount reportFactorFinanceCount) {
		if(null == reportFactorFinanceCount)
			return;
		reportFactorFinanceCount.setAdjustItems(reportFactorFinanceCount.getAdjustItems() + 1);
		reportFactorFinanceCountMapper.updateByPrimaryKeySelective(reportFactorFinanceCount);
	}
	
	//计算报表更新需要的字段
	private void calculate(LoanInfoChangeEvent event, Delta delta) throws Exception{
		List<RepaymentPlanSnapshot> oldRepaymentPlanSnapshots = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getOldSnapshot().getId());
		List<RepaymentPlanSnapshot> newRepaymentPlanSnapshots = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getNewSnapshot().getId());
		
		for (RepaymentPlanSnapshot newRepaymentPlanSnapshot : newRepaymentPlanSnapshots) {
			RepaymentPlanSnapshot oldRepaymentPlanSnapshot = oldRepaymentPlanSnapshots.get(newRepaymentPlanSnapshots.indexOf(newRepaymentPlanSnapshot));
			//调账金额 = 应还利息调整金额 + 应还服务费调整金额 + 应还展期调整金额 + 逾期利息调整金额 + 逾期服务费调整金额 + 逾期本金罚息调整金额 + 逾期利息罚息调整金额 + 逾期服务费罚息调整金额 + 其他罚息调整金额
//			delta.adjustAmountDiff += newRepaymentPlanSnapshot.getAccountInterest() - oldRepaymentPlanSnapshot.getAccountInterest() 
//							  + newRepaymentPlanSnapshot.getAccountServiceCharge() - oldRepaymentPlanSnapshot.getAccountServiceCharge()
//							  + newRepaymentPlanSnapshot.getAccountExtensionCharge() - oldRepaymentPlanSnapshot.getAccountExtensionCharge()
//							  + newRepaymentPlanSnapshot.getOverdueInterest() - oldRepaymentPlanSnapshot.getOverdueInterest()
//							  + newRepaymentPlanSnapshot.getOverdueServiceCharge() - oldRepaymentPlanSnapshot.getOverdueServiceCharge()
//							  + newRepaymentPlanSnapshot.getOverduePrincipalPenalty() - oldRepaymentPlanSnapshot.getOverduePrincipalPenalty()
//							  + newRepaymentPlanSnapshot.getOverdueInterestPenalty() - oldRepaymentPlanSnapshot.getOverdueInterestPenalty()
//							  + newRepaymentPlanSnapshot.getOverdueServiceChargePenalty() - oldRepaymentPlanSnapshot.getOverdueServiceChargePenalty()
//							  + newRepaymentPlanSnapshot.getOtherPenalty() - oldRepaymentPlanSnapshot.getOtherPenalty();
			delta.adjustInterestDiff += newRepaymentPlanSnapshot.getAccountInterest() - oldRepaymentPlanSnapshot.getAccountInterest();
			delta.adjustServiceChargeDiff += newRepaymentPlanSnapshot.getAccountServiceCharge() - oldRepaymentPlanSnapshot.getAccountServiceCharge();
			delta.adjustExtensionDiff += newRepaymentPlanSnapshot.getAccountExtensionCharge() - oldRepaymentPlanSnapshot.getAccountExtensionCharge();
			delta.adjustOverdueInterestDiff += newRepaymentPlanSnapshot.getOverdueInterest() - oldRepaymentPlanSnapshot.getOverdueInterest();
			delta.adjustOverdueServiceChargeDiff += newRepaymentPlanSnapshot.getOverdueServiceCharge() - oldRepaymentPlanSnapshot.getOverdueServiceCharge();
			delta.adjustPenaltyDiff += newRepaymentPlanSnapshot.getOverduePrincipalPenalty() - oldRepaymentPlanSnapshot.getOverduePrincipalPenalty()
					 				+ newRepaymentPlanSnapshot.getOverdueInterestPenalty() - oldRepaymentPlanSnapshot.getOverdueInterestPenalty()
					 				+ newRepaymentPlanSnapshot.getOverdueServiceChargePenalty() - oldRepaymentPlanSnapshot.getOverdueServiceChargePenalty()
					 				+ newRepaymentPlanSnapshot.getOtherPenalty() - oldRepaymentPlanSnapshot.getOtherPenalty();
			
			if(event.getCurYear().equals(newRepaymentPlanSnapshot.getRepaymentDate().substring(0, 4))){
				//当年应还费用调整金额 = 当年应还利息调整金额 + 当年应还服务费调整金额 + 当年逾期本金罚息调整金额 + 当年逾期利息罚息调整金额 + 当年逾期服务费罚息调整金额 + 当年其他罚息调整金额
//				delta.annualAccountChargeDiff += newRepaymentPlanSnapshot.getAccountInterest() - oldRepaymentPlanSnapshot.getAccountInterest() 
//								   + newRepaymentPlanSnapshot.getAccountServiceCharge() - oldRepaymentPlanSnapshot.getAccountServiceCharge()
//								   + newRepaymentPlanSnapshot.getOverduePrincipalPenalty() - oldRepaymentPlanSnapshot.getOverduePrincipalPenalty()
//								   + newRepaymentPlanSnapshot.getOverdueInterestPenalty() - oldRepaymentPlanSnapshot.getOverdueInterestPenalty()
//								   + newRepaymentPlanSnapshot.getOverdueServiceChargePenalty() - oldRepaymentPlanSnapshot.getOverdueServiceChargePenalty()
//								   + newRepaymentPlanSnapshot.getOtherPenalty() - oldRepaymentPlanSnapshot.getOtherPenalty();
				delta.annualAccountInterestDiff += newRepaymentPlanSnapshot.getAccountInterest() - oldRepaymentPlanSnapshot.getAccountInterest();
				delta.annualAccountServiceChargeDiff += newRepaymentPlanSnapshot.getAccountServiceCharge() - oldRepaymentPlanSnapshot.getAccountServiceCharge();
				delta.annualAccountOverduePrincipalPenaltyDiff += newRepaymentPlanSnapshot.getOverduePrincipalPenalty() - oldRepaymentPlanSnapshot.getOverduePrincipalPenalty();
				delta.annualAccountOverdueInterestPenaltyDiff += newRepaymentPlanSnapshot.getOverdueInterestPenalty() - oldRepaymentPlanSnapshot.getOverdueInterestPenalty();
				delta.annualAccountOverdueServiceChargePenaltyDiff += newRepaymentPlanSnapshot.getOverdueServiceChargePenalty() - oldRepaymentPlanSnapshot.getOverdueServiceChargePenalty();
				delta.annualAccountOtherPenaltyDiff += newRepaymentPlanSnapshot.getOtherPenalty() - oldRepaymentPlanSnapshot.getOtherPenalty();
				
				//当年应还逾期调整金额 = 当年应还逾期利息调整金额 + 当年应还逾期服务费调整金额
//				delta.annualAccountOverdueDiff += newRepaymentPlanSnapshot.getOverdueInterest() - oldRepaymentPlanSnapshot.getOverdueInterest()
//						  			+ newRepaymentPlanSnapshot.getOverdueServiceCharge() - oldRepaymentPlanSnapshot.getOverdueServiceCharge();
				delta.annualAccountOverdueInterestDiff += newRepaymentPlanSnapshot.getOverdueInterest() - oldRepaymentPlanSnapshot.getOverdueInterest();
				delta.annualAccountOverdueServiceChargeDiff += newRepaymentPlanSnapshot.getOverdueServiceCharge() - oldRepaymentPlanSnapshot.getOverdueServiceCharge();
				
				//判断展期费用有没有超过展期天数
				DateTime extensionDate = DateTime.parse(newRepaymentPlanSnapshot.getRepaymentDate()).plusDays(newRepaymentPlanSnapshot.getExtensionDays());
				if(DateTime.parse(event.getCurDay()).compareTo(extensionDate) > 0 ){
					//展期已在今天以前结束,展期费用加入应还逾期
//					delta.annualAccountOverdueDiff += newRepaymentPlanSnapshot.getAccountExtensionCharge() - oldRepaymentPlanSnapshot.getAccountExtensionCharge();
					delta.annualAccountOverdueExtensionChargeDiff += newRepaymentPlanSnapshot.getAccountExtensionCharge() - oldRepaymentPlanSnapshot.getAccountExtensionCharge();
				} else {
					//展期未在今天以前结束,展期费用加入应还费用
//					delta.annualAccountChargeDiff += newRepaymentPlanSnapshot.getAccountExtensionCharge() - oldRepaymentPlanSnapshot.getAccountExtensionCharge();
					delta.annualAccountExtensionChargeDiff += newRepaymentPlanSnapshot.getAccountExtensionCharge() - oldRepaymentPlanSnapshot.getAccountExtensionCharge();
				}
			}
		}
		
		List<RepaymentExpenseSnapshot> oldRepaymentExpenseSnapshots = repaymentPlanSnapshotService.getRepaymentExpenseSnapshotByLoanInfoSnapshotId(event.getOldSnapshot().getId());
		List<RepaymentExpenseSnapshot> newRepaymentExpenseSnapshots = repaymentPlanSnapshotService.getRepaymentExpenseSnapshotByLoanInfoSnapshotId(event.getNewSnapshot().getId());
		
		for (RepaymentExpenseSnapshot newRepaymentExpenseSnapshot : newRepaymentExpenseSnapshots) {
			RepaymentExpenseSnapshot oldRepaymentExpenseSnapshot = oldRepaymentExpenseSnapshots.get(newRepaymentExpenseSnapshots.indexOf(newRepaymentExpenseSnapshot));
			//还款日在今天以前(包括今天)应还固定费用
			if(DateTime.parse(event.getCurDay()).compareTo(DateTime.parse(newRepaymentExpenseSnapshot.getRepaymentDate())) >= 0){
				delta.annualAccountExpenseDiff += newRepaymentExpenseSnapshot.getRepaymentAmount() - oldRepaymentExpenseSnapshot.getRepaymentAmount();
			}
		}
	}
	
	private class Delta{
	    private long adjustInterestDiff = 0;
	    private long adjustServiceChargeDiff = 0;
	    private long adjustExtensionDiff = 0;
	    private long adjustOverdueInterestDiff = 0;
	    private long adjustOverdueServiceChargeDiff = 0;
	    private long adjustPenaltyDiff = 0;
//		private long adjustAmountDiff = 0;			//调账金额
		private long annualAccountInterestDiff = 0;
		private long annualAccountServiceChargeDiff = 0;
		private long annualAccountExtensionChargeDiff = 0;
		private long annualAccountOverduePrincipalPenaltyDiff = 0;
	    private long annualAccountOverdueInterestPenaltyDiff = 0;
	    private long annualAccountOverdueServiceChargePenaltyDiff = 0;
	    private long annualAccountOtherPenaltyDiff;
//		private long annualAccountChargeDiff = 0;	//当年的应还费用调整金额
		private long annualAccountExpenseDiff = 0;	//当年的应还固定费用调整金额
		private long annualAccountOverdueInterestDiff = 0;
		private long annualAccountOverdueServiceChargeDiff = 0;
	    private long annualAccountOverdueExtensionChargeDiff = 0;
//		private long annualAccountOverdueDiff = 0;	//当年的应还逾期调整金额
	}
}
