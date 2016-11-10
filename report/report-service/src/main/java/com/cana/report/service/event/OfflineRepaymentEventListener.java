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

public class OfflineRepaymentEventListener extends AbstractEventListener {
	
	private static final Logger logger = LoggerFactory.getLogger(OfflineRepaymentEventListener.class);
	
	@Override
	public void doExecute(LoanInfoChangeEvent event) throws Exception {
		logger.info("融资报表生成定时任务 - 执行 - 放款信息[id：{}]线下还款处理器", event.getNewSnapshot().getLoanInfoId());
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getOldSnapshot().getBusinessProductId())){
			businessProductId = event.getOldSnapshot().getBusinessProductId();
		}
		Delta delta = this.new Delta();
		// 相关值计算
		calculate(event, delta);
		
		// 更新保理商日报表
		ReportFactorFinanceDay reportFactorDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getFactorId(), businessProductId);
		updateDailyReport(reportFactorDay, delta);
		// 更新保理商年报表
		ReportFactorFinanceYear reportFactorYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getFactorId(), businessProductId);
		updateAnnualReport(reportFactorYear, delta);
		//更新保理商的计数报表
		ReportFactorFinanceCount reportFactorCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), event.getNewSnapshot().getFactorId(), businessProductId);
		updateCountReport(reportFactorCount);
		
		// 更新CANA日报表
		ReportFactorFinanceDay canaReportDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), userApi.getCanaId(), businessProductId);
		updateDailyReport(canaReportDay, delta);
		// 更新CANA年报表
		ReportFactorFinanceYear canaReportYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), userApi.getCanaId(), businessProductId);
		updateAnnualReport(canaReportYear, delta);
		//更新cana的计数报表
		ReportFactorFinanceCount reportCanaCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), userApi.getCanaId(), businessProductId);
		updateCountReport(reportCanaCount);
		
		if(StringUtils.isNotBlank(event.getNewSnapshot().getFinanceId())){
			// 更新融资客户日报表
			ReportFactorFinanceDay reportFinanceDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateDailyReport(reportFinanceDay, delta);
			// 更新融资客户年报表
			ReportFactorFinanceYear reportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateAnnualReport(reportFinanceYear, delta);
			//更新融资客户的计数报表
			ReportFactorFinanceCount reportFinanceCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateCountReport(reportFinanceCount);
		}
		
		if(StringUtils.isNotBlank(event.getNewSnapshot().getCoreCompanyId())){
			// 更新核心企业日报表
			ReportFactorFinanceDay reportFinanceDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateDailyReport(reportFinanceDay, delta);
			// 更新核心企业年报表
			ReportFactorFinanceYear reportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateAnnualReport(reportFinanceYear, delta);
			//更新核心企业的计数报表
			ReportFactorFinanceCount reportFinanceCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateCountReport(reportFinanceCount);
		}
		logger.info("融资报表生成定时任务 - 状态 - 放款信息[id：{}]线下还款处理器处理完成，追踪至[版本号：{}]", event.getNewSnapshot().getLoanInfoId(), event.getNewSnapshot().getCurrentVersion());
	}

	private void updateDailyReport(ReportFactorFinanceDay reportFactorFinanceDay, Delta delta){
		if( null == reportFactorFinanceDay ){
			return;
		}
		reportFactorFinanceDay.setFinanceBalance(reportFactorFinanceDay.getFinanceBalance() - delta.paidOverduePrincipal - delta.paidNormalPrincipal);
		reportFactorFinanceDay.setPaidPrincipal(reportFactorFinanceDay.getPaidPrincipal() + delta.paidNormalPrincipal);
		reportFactorFinanceDay.setPaidOverduePrincipal(reportFactorFinanceDay.getPaidOverduePrincipal() + delta.paidOverduePrincipal);
		reportFactorFinanceDay.setPaidCharge(reportFactorFinanceDay.getPaidCharge() + delta.paidInterestDiff + delta.paidServiceChargeDiff + delta.paidExtensionChargeDiff 
				+ delta.paidEarlyRepaymentChargeDiff + delta.paidOverduePrincipalPenaltyDiff + delta.paidOverdueInterestPenaltyDiff + delta.paidOverdueServiceChargePenaltyDiff 
				+ delta.paidOtherPenaltyDiff);
		reportFactorFinanceDay.setPaidInterest(reportFactorFinanceDay.getPaidInterest() + delta.paidInterestDiff);
		reportFactorFinanceDay.setPaidServiceCharge(reportFactorFinanceDay.getPaidServiceCharge() + delta.paidServiceChargeDiff);
		reportFactorFinanceDay.setPaidExtensionCharge(reportFactorFinanceDay.getPaidExtensionCharge() + delta.paidExtensionChargeDiff);
		reportFactorFinanceDay.setPaidEarlyRepaymentCharge(reportFactorFinanceDay.getPaidEarlyRepaymentCharge() + delta.paidEarlyRepaymentChargeDiff);
		reportFactorFinanceDay.setPaidOverduePrincipalPenalty(reportFactorFinanceDay.getPaidOverduePrincipalPenalty()+ delta.paidOverduePrincipalPenaltyDiff);
		reportFactorFinanceDay.setPaidOverdueInterestPenalty(reportFactorFinanceDay.getPaidOverdueInterestPenalty() + delta.paidOverdueInterestPenaltyDiff);
		reportFactorFinanceDay.setPaidOverdueServiceChargePenalty(reportFactorFinanceDay.getPaidOverdueServiceChargePenalty() + delta.paidOverdueServiceChargePenaltyDiff);
		reportFactorFinanceDay.setPaidOtherPenalty(reportFactorFinanceDay.getPaidOtherPenalty() + delta.paidOtherPenaltyDiff);
		reportFactorFinanceDay.setPaidExpense(reportFactorFinanceDay.getPaidExpense() + delta.paidExpenseCharge);
		reportFactorFinanceDay.setPaidOverdue(reportFactorFinanceDay.getPaidOverdue() + delta.paidOverduePrincipalDiff + delta.paidOverdueInterestDiff 
				+ delta.paidOverdueServiceChargeDiff + delta.paidOverdueExtensionChargeDiff);
//		reportFactorFinanceDay.setPaidOverduePrincipal(reportFactorFinanceDay.getPaidOverduePrincipal() + delta.paidOverduePrincipalDiff );
		reportFactorFinanceDay.setPaidOverdueInterest(reportFactorFinanceDay.getPaidOverdueInterest() + delta.paidOverdueInterestDiff);
		reportFactorFinanceDay.setPaidOverdueServiceCharge(reportFactorFinanceDay.getPaidOverdueServiceCharge() + delta.paidOverdueServiceChargeDiff );
		reportFactorFinanceDay.setPaidOverdueExtensionCharge(reportFactorFinanceDay.getPaidOverdueExtensionCharge() + delta.paidOverdueExtensionChargeDiff);
		reportFactorFinanceDayMapper.updateByPrimaryKeySelective(reportFactorFinanceDay);
	}
	
	private void updateAnnualReport(ReportFactorFinanceYear reportFactorFinanceYear, Delta delta){
		if( null == reportFactorFinanceYear ){
			return;
		}
		reportFactorFinanceYear.setFinanceBalance(reportFactorFinanceYear.getFinanceBalance() - delta.paidOverduePrincipal - delta.paidNormalPrincipal);
		reportFactorFinanceYear.setPaidPrincipal(reportFactorFinanceYear.getPaidPrincipal() + delta.paidNormalPrincipal);
		reportFactorFinanceYear.setPaidOverduePrincipal(reportFactorFinanceYear.getPaidOverduePrincipal() + delta.paidOverduePrincipal);
		reportFactorFinanceYear.setPaidCharge(reportFactorFinanceYear.getPaidCharge() + delta.paidInterestDiff + delta.paidServiceChargeDiff + delta.paidExtensionChargeDiff 
				+ delta.paidEarlyRepaymentChargeDiff + delta.paidOverduePrincipalPenaltyDiff + delta.paidOverdueInterestPenaltyDiff + delta.paidOverdueServiceChargePenaltyDiff 
				+ delta.paidOtherPenaltyDiff);
		reportFactorFinanceYear.setPaidInterest(reportFactorFinanceYear.getPaidInterest() + delta.paidInterestDiff);
		reportFactorFinanceYear.setPaidServiceCharge(reportFactorFinanceYear.getPaidServiceCharge() + delta.paidServiceChargeDiff);
		reportFactorFinanceYear.setPaidExtensionCharge(reportFactorFinanceYear.getPaidExtensionCharge() + delta.paidExtensionChargeDiff);
		reportFactorFinanceYear.setPaidEarlyRepaymentCharge(reportFactorFinanceYear.getPaidEarlyRepaymentCharge() + delta.paidEarlyRepaymentChargeDiff);
		reportFactorFinanceYear.setPaidOverduePrincipalPenalty(reportFactorFinanceYear.getPaidOverduePrincipalPenalty()+ delta.paidOverduePrincipalPenaltyDiff);
		reportFactorFinanceYear.setPaidOverdueInterestPenalty(reportFactorFinanceYear.getPaidOverdueInterestPenalty() + delta.paidOverdueInterestPenaltyDiff);
		reportFactorFinanceYear.setPaidOverdueServiceChargePenalty(reportFactorFinanceYear.getPaidOverdueServiceChargePenalty() + delta.paidOverdueServiceChargePenaltyDiff);
		reportFactorFinanceYear.setPaidOtherPenalty(reportFactorFinanceYear.getPaidOtherPenalty() + delta.paidOtherPenaltyDiff);
		reportFactorFinanceYear.setPaidExpense(reportFactorFinanceYear.getPaidExpense() + delta.paidExpenseCharge);
		reportFactorFinanceYear.setPaidOverdue(reportFactorFinanceYear.getPaidOverdue() + delta.paidOverduePrincipalDiff + delta.paidOverdueInterestDiff 
				+ delta.paidOverdueServiceChargeDiff + delta.paidOverdueExtensionChargeDiff);
//		reportFactorFinanceYear.setPaidOverduePrincipal(reportFactorFinanceYear.getPaidOverduePrincipal() + delta.paidOverduePrincipalDiff );
		reportFactorFinanceYear.setPaidOverdueInterest(reportFactorFinanceYear.getPaidOverdueInterest() + delta.paidOverdueInterestDiff);
		reportFactorFinanceYear.setPaidOverdueServiceCharge(reportFactorFinanceYear.getPaidOverdueServiceCharge() + delta.paidOverdueServiceChargeDiff );
		reportFactorFinanceYear.setPaidOverdueExtensionCharge(reportFactorFinanceYear.getPaidOverdueExtensionCharge() + delta.paidOverdueExtensionChargeDiff);
		reportFactorFinanceYear.setAccountCharge(reportFactorFinanceYear.getAccountCharge() + delta.accountInterestDiff);
		reportFactorFinanceYear.setAccountInterest(reportFactorFinanceYear.getAccountInterest() + delta.accountInterestDiff);
		reportFactorFinanceYearMapper.updateByPrimaryKeySelective(reportFactorFinanceYear);
	}
	
	private void updateCountReport(ReportFactorFinanceCount reportFactorFinanceCount) {
		if(null == reportFactorFinanceCount)
			return;
		reportFactorFinanceCount.setRepaymentItems(reportFactorFinanceCount.getRepaymentItems() + 1);
		reportFactorFinanceCountMapper.updateByPrimaryKeySelective(reportFactorFinanceCount);
	}
	
	private void calculate(LoanInfoChangeEvent event, Delta delta) throws Exception{
		// 获取新老还款计划的快照
		List<RepaymentPlanSnapshot> repaymentPlanSnapshotListOld = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getOldSnapshot().getId());
		List<RepaymentPlanSnapshot> repaymentPlanSnapshotListNew = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getNewSnapshot().getId());
		// 获取新老还款费用的快照
		List<RepaymentExpenseSnapshot> repaymentExpenseSnapshotListOld = repaymentPlanSnapshotService.getRepaymentExpenseSnapshotByLoanInfoSnapshotId(event.getOldSnapshot().getId());
		List<RepaymentExpenseSnapshot> repaymentExpenseSnapshotListNew = repaymentPlanSnapshotService.getRepaymentExpenseSnapshotByLoanInfoSnapshotId(event.getNewSnapshot().getId());
		
		for(RepaymentPlanSnapshot repaymentPlanSnapshotNew : repaymentPlanSnapshotListNew){
			int index = repaymentPlanSnapshotListNew.indexOf(repaymentPlanSnapshotNew);
			RepaymentPlanSnapshot repaymentPlanSnapshotOld = repaymentPlanSnapshotListOld.get(index);
			// 计算已还本金
//			delta.paidPrincipal += (repaymentPlanSnapshotNew.getPaidNormalPrincipal() - repaymentPlanSnapshotOld.getPaidNormalPrincipal() + repaymentPlanSnapshotNew.getPaidOverduePrincipal() - repaymentPlanSnapshotOld.getPaidOverduePrincipal());
			delta.paidOverduePrincipal += (repaymentPlanSnapshotNew.getPaidOverduePrincipal() - repaymentPlanSnapshotOld.getPaidOverduePrincipal());
			// 计算已还正常本金
			delta.paidNormalPrincipal += (repaymentPlanSnapshotNew.getPaidNormalPrincipal() - repaymentPlanSnapshotOld.getPaidNormalPrincipal());
			// 计算已还费用
//			delta.paidCharge += (repaymentPlanSnapshotNew.getPaidNormalInterest() - repaymentPlanSnapshotOld.getPaidNormalInterest())
//					    + (repaymentPlanSnapshotNew.getPaidNormalServiceCharge() - repaymentPlanSnapshotOld.getPaidNormalServiceCharge())
//					    + (repaymentPlanSnapshotNew.getPaidEarlyRepaymentCharge() - repaymentPlanSnapshotOld.getPaidEarlyRepaymentCharge())
//					    + (repaymentPlanSnapshotNew.getPaidOverdueInterestPenalty() - repaymentPlanSnapshotOld.getPaidOverdueInterestPenalty())
//					    + (repaymentPlanSnapshotNew.getPaidOverdueServiceCharge() - repaymentPlanSnapshotOld.getPaidOverdueServiceCharge())
//					    + (repaymentPlanSnapshotNew.getPaidOverduePrincipalPenalty() - repaymentPlanSnapshotOld.getPaidOverduePrincipalPenalty())
//					    + (repaymentPlanSnapshotNew.getPaidOtherPenalty() - repaymentPlanSnapshotOld.getPaidOtherPenalty());
			delta.paidInterestDiff += (repaymentPlanSnapshotNew.getPaidNormalInterest() - repaymentPlanSnapshotOld.getPaidNormalInterest());
			delta.paidServiceChargeDiff += (repaymentPlanSnapshotNew.getPaidNormalServiceCharge() - repaymentPlanSnapshotOld.getPaidNormalServiceCharge());
			delta.paidEarlyRepaymentChargeDiff += (repaymentPlanSnapshotNew.getPaidEarlyRepaymentCharge() - repaymentPlanSnapshotOld.getPaidEarlyRepaymentCharge());
			delta.paidOverduePrincipalPenaltyDiff = (repaymentPlanSnapshotNew.getPaidOverduePrincipalPenalty() - repaymentPlanSnapshotOld.getPaidOverduePrincipalPenalty());
			delta.paidOverdueInterestPenaltyDiff += (repaymentPlanSnapshotNew.getPaidOverdueInterestPenalty() - repaymentPlanSnapshotOld.getPaidOverdueInterestPenalty());
			delta.paidOverdueServiceChargePenaltyDiff += (repaymentPlanSnapshotNew.getPaidOverdueServiceChargePenalty() - repaymentPlanSnapshotOld.getPaidOverdueServiceChargePenalty());
			delta.paidOtherPenaltyDiff = (repaymentPlanSnapshotNew.getPaidOtherPenalty() - repaymentPlanSnapshotOld.getPaidOtherPenalty());
			
			// 计算已还逾期
//			delta.paidOverdue += (repaymentPlanSnapshotNew.getPaidOverduePrincipal() - repaymentPlanSnapshotOld.getPaidOverduePrincipal())
//						 + (repaymentPlanSnapshotNew.getPaidOverdueInterest() - repaymentPlanSnapshotOld.getPaidOverdueInterest())
//						 + (repaymentPlanSnapshotNew.getPaidOverdueServiceCharge() - repaymentPlanSnapshotOld.getPaidOverdueServiceCharge());
			delta.paidOverduePrincipalDiff += (repaymentPlanSnapshotNew.getPaidOverduePrincipal() - repaymentPlanSnapshotOld.getPaidOverduePrincipal());
			delta.paidOverdueInterestDiff += (repaymentPlanSnapshotNew.getPaidOverdueInterest() - repaymentPlanSnapshotOld.getPaidOverdueInterest());
			delta.paidOverdueServiceChargeDiff += (repaymentPlanSnapshotNew.getPaidOverdueServiceCharge() - repaymentPlanSnapshotOld.getPaidOverdueServiceCharge());
			
			
			//判断展期费用有没有超过展期天数
			DateTime extensionDate = DateTime.parse(repaymentPlanSnapshotNew.getRepaymentDate()).plusDays(repaymentPlanSnapshotNew.getExtensionDays());
			if(DateTime.parse(event.getCurDay()).compareTo(extensionDate) > 0 ){
				//展期已在今天以前结束,展期费用加入应还逾期
//				delta.paidOverdue += repaymentPlanSnapshotNew.getPaidExtensionCharge() - repaymentPlanSnapshotOld.getPaidExtensionCharge();
				delta.paidOverdueExtensionChargeDiff += repaymentPlanSnapshotNew.getPaidExtensionCharge() - repaymentPlanSnapshotOld.getPaidExtensionCharge();
			} else {
				//展期未在今天以前结束,展期费用加入应还费用
//				delta.paidCharge += repaymentPlanSnapshotNew.getPaidExtensionCharge() - repaymentPlanSnapshotOld.getPaidExtensionCharge();
				delta.paidExtensionChargeDiff += repaymentPlanSnapshotNew.getPaidExtensionCharge() - repaymentPlanSnapshotOld.getPaidExtensionCharge();
			}
			
			//应还费用调整
//			delta.accountChargeDiff += newRepaymentPlanSnapshot.getAccountInterest() - oldRepaymentPlanSnapshot.getAccountInterest()
//									+ newRepaymentPlanSnapshot.getAccountServiceCharge() - oldRepaymentPlanSnapshot.getAccountServiceCharge();
			if(repaymentPlanSnapshotNew.getRepaymentDate().substring(0,  4).compareTo(event.getCurYear()) <= 0){
				delta.accountInterestDiff += repaymentPlanSnapshotNew.getAccountInterest() - repaymentPlanSnapshotOld.getAccountInterest();
			}
//			delta.accountServiceChargeDiff += newRepaymentPlanSnapshot.getAccountServiceCharge() - oldRepaymentPlanSnapshot.getAccountServiceCharge();
			
		}
		for(RepaymentExpenseSnapshot repaymentExpenseSnapshotNew : repaymentExpenseSnapshotListNew){
			int index = repaymentExpenseSnapshotListNew.indexOf(repaymentExpenseSnapshotNew);
			RepaymentExpenseSnapshot repaymentExpenseSnapshotOld = repaymentExpenseSnapshotListOld.get(index);
			delta.paidExpenseCharge += (repaymentExpenseSnapshotNew.getPaidAmount() - repaymentExpenseSnapshotOld.getPaidAmount());
		}
	}

	private class Delta {
//		private long paidPrincipal = 0;
		private long paidNormalPrincipal = 0;
		private long paidOverduePrincipal = 0;
		private long paidInterestDiff = 0;
	    private long paidServiceChargeDiff = 0;
	    private long paidExtensionChargeDiff = 0;
	    private long paidEarlyRepaymentChargeDiff = 0;
	    private long paidOverduePrincipalPenaltyDiff = 0;
	    private long paidOverdueInterestPenaltyDiff = 0;
	    private long paidOverdueServiceChargePenaltyDiff = 0;
	    private long paidOtherPenaltyDiff = 0;
//		private long paidCharge = 0;
		private long paidExpenseCharge = 0;
		private long paidOverduePrincipalDiff = 0;
	    private long paidOverdueInterestDiff = 0;
	    private long paidOverdueServiceChargeDiff = 0;
	    private long paidOverdueExtensionChargeDiff = 0;
	    private long accountInterestDiff = 0;
//		private long paidOverdue = 0;
	}
}
