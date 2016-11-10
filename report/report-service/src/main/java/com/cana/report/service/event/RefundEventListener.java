package com.cana.report.service.event;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.repayment.dao.po.RepaymentPlanSnapshot;
import com.cana.report.dao.po.ReportFactorFinanceCount;
import com.cana.report.dao.po.ReportFactorFinanceDay;
import com.cana.report.dao.po.ReportFactorFinanceYear;
import com.cana.vbam.common.report.enums.BusinessProduct;

public class RefundEventListener extends AbstractEventListener {
	
	private static final Logger logger = LoggerFactory.getLogger(RefundEventListener.class);

	@Override
	public void doExecute(LoanInfoChangeEvent event) throws Exception {
		logger.info("融资报表生成定时任务 - 执行 - 放款信息[id：{}]退款还款处理器", event.getNewSnapshot().getLoanInfoId());
		Delta delta = this.new Delta();
		calculate(event, delta);
		updateFactorReport(event, delta);
		updateFinanceReport(event, delta);
		updateCoreCompanyReport(event, delta);
		updateCanaReport(event, delta);
		logger.info("融资报表生成定时任务 - 状态 - 放款信息[id：{}]退款还款处理器处理完成，追踪至[版本号：{}]", event.getNewSnapshot().getLoanInfoId(), event.getNewSnapshot().getCurrentVersion());
	}
	
	private void updateFactorReport(LoanInfoChangeEvent event, Delta delta) {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getOldSnapshot().getBusinessProductId())){
			businessProductId = event.getOldSnapshot().getBusinessProductId();
		}
		//更新保理商的当日日报表
		ReportFactorFinanceDay reportFactorDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getFactorId(), businessProductId);
		updateChangedDailyReport(reportFactorDay, delta);
		//更新保理商的当年年报表
		ReportFactorFinanceYear reportFactorYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getFactorId(), businessProductId);
		updateChangedAnnualReport(reportFactorYear, delta);
		//更新保理商的计数报表
		ReportFactorFinanceCount reportFactorCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), event.getNewSnapshot().getFactorId(), businessProductId);
		updateCountReport(reportFactorCount);
	}
	
	private void updateFinanceReport(LoanInfoChangeEvent event, Delta delta) {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getOldSnapshot().getBusinessProductId())){
			businessProductId = event.getOldSnapshot().getBusinessProductId();
		}
		//更新融资客户的当日日报表
		ReportFactorFinanceDay reportFinanceDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getFinanceId(), businessProductId);
		updateChangedDailyReport(reportFinanceDay, delta);
		//更新融资客户的当年年报表
		ReportFactorFinanceYear reportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getFinanceId(), businessProductId);
		updateChangedAnnualReport(reportFinanceYear, delta);
		//更新融资客户的计数报表
		ReportFactorFinanceCount reportFinanceCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), event.getNewSnapshot().getFinanceId(), businessProductId);
		updateCountReport(reportFinanceCount);
	}
	
	private void updateCoreCompanyReport(LoanInfoChangeEvent event, Delta delta) {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getOldSnapshot().getBusinessProductId())){
			businessProductId = event.getOldSnapshot().getBusinessProductId();
		}
		//更新融资客户的当日日报表
		ReportFactorFinanceDay reportFinanceDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
		updateChangedDailyReport(reportFinanceDay, delta);
		//更新融资客户的当年年报表
		ReportFactorFinanceYear reportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
		updateChangedAnnualReport(reportFinanceYear, delta);
		//更新融资客户的计数报表
		ReportFactorFinanceCount reportFinanceCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
		updateCountReport(reportFinanceCount);
	}
	
	private void updateCanaReport(LoanInfoChangeEvent event, Delta delta) {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getOldSnapshot().getBusinessProductId())){
			businessProductId = event.getOldSnapshot().getBusinessProductId();
		}
		//更新cana的日报表
		ReportFactorFinanceDay reportCanaDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), userApi.getCanaId(), businessProductId);
		updateChangedDailyReport(reportCanaDay, delta);
		//更新cana的年报表
		ReportFactorFinanceYear reportCanaYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), userApi.getCanaId(), businessProductId);
		updateChangedAnnualReport(reportCanaYear, delta);
		//更新cana的计数报表
		ReportFactorFinanceCount reportCanaCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), userApi.getCanaId(), businessProductId);
		updateCountReport(reportCanaCount);
	}
	
	private void updateChangedDailyReport(ReportFactorFinanceDay reportFactorFinanceDay, Delta delta){
		if(null == reportFactorFinanceDay)
			return;
		reportFactorFinanceDay.setFinanceBalance(reportFactorFinanceDay.getFinanceBalance() + delta.financeBalanceDiff);
		reportFactorFinanceDay.setPaidPrincipal(reportFactorFinanceDay.getPaidPrincipal() + delta.paidPrincipalDiff);
//		reportFactorFinanceDay.setPaidCharge(reportFactorFinanceDay.getPaidCharge() + delta.paidChargeDiff);
		reportFactorFinanceDay.setPaidCharge(reportFactorFinanceDay.getPaidCharge() + delta.paidInterestDiff + delta.paidServiceChargeDiff
				+ delta.paidEarlyRepaymentChargeDiff + delta.paidOverduePrincipalPenaltyDiff + delta.paidOverdueInterestPenaltyDiff + delta.paidOverdueServiceChargePenaltyDiff 
				+ delta.paidOtherPenaltyDiff);
		reportFactorFinanceDay.setPaidInterest(reportFactorFinanceDay.getPaidInterest() + delta.paidInterestDiff);
		reportFactorFinanceDay.setPaidServiceCharge(reportFactorFinanceDay.getPaidServiceCharge() + delta.paidServiceChargeDiff);
		reportFactorFinanceDay.setPaidEarlyRepaymentCharge(reportFactorFinanceDay.getPaidEarlyRepaymentCharge() + delta.paidEarlyRepaymentChargeDiff);
		reportFactorFinanceDay.setPaidOverduePrincipalPenalty(reportFactorFinanceDay.getPaidOverduePrincipalPenalty()+ delta.paidOverduePrincipalPenaltyDiff);
		reportFactorFinanceDay.setPaidOverdueInterestPenalty(reportFactorFinanceDay.getPaidOverdueInterestPenalty() + delta.paidOverdueInterestPenaltyDiff);
		reportFactorFinanceDay.setPaidOverdueServiceChargePenalty(reportFactorFinanceDay.getPaidOverdueServiceChargePenalty() + delta.paidOverdueServiceChargePenaltyDiff);
		reportFactorFinanceDay.setPaidOtherPenalty(reportFactorFinanceDay.getPaidOtherPenalty() + delta.paidOtherPenaltyDiff);
//		reportFactorFinanceDay.setPaidOverdue(reportFactorFinanceDay.getPaidOverdue() + delta.paidOverdueDiff);
		reportFactorFinanceDay.setPaidOverdue(reportFactorFinanceDay.getPaidOverdue() + delta.paidOverduePrincipalDiff + delta.paidOverdueInterestDiff 
				+ delta.paidOverdueServiceChargeDiff);
		reportFactorFinanceDay.setPaidOverduePrincipal(reportFactorFinanceDay.getPaidOverduePrincipal() + delta.paidOverduePrincipalDiff );
		reportFactorFinanceDay.setPaidOverdueInterest(reportFactorFinanceDay.getPaidOverdueInterest() + delta.paidOverdueInterestDiff);
		reportFactorFinanceDay.setPaidOverdueServiceCharge(reportFactorFinanceDay.getPaidOverdueServiceCharge() + delta.paidOverdueServiceChargeDiff );
		reportFactorFinanceDayMapper.updateByPrimaryKeySelective(reportFactorFinanceDay);
	}
	
	private void updateChangedAnnualReport(ReportFactorFinanceYear reportFactorFinanceYear, Delta delta){
		if(null == reportFactorFinanceYear)
			return;
		reportFactorFinanceYear.setFinanceBalance(reportFactorFinanceYear.getFinanceBalance() + delta.financeBalanceDiff);
		reportFactorFinanceYear.setPaidPrincipal(reportFactorFinanceYear.getPaidPrincipal() + delta.paidPrincipalDiff);
//		reportFactorFinanceYear.setPaidCharge(reportFactorFinanceYear.getPaidCharge() + delta.paidChargeDiff);
		reportFactorFinanceYear.setPaidCharge(reportFactorFinanceYear.getPaidCharge() + delta.paidInterestDiff + delta.paidServiceChargeDiff
				+ delta.paidEarlyRepaymentChargeDiff + delta.paidOverduePrincipalPenaltyDiff + delta.paidOverdueInterestPenaltyDiff + delta.paidOverdueServiceChargePenaltyDiff 
				+ delta.paidOtherPenaltyDiff);
		reportFactorFinanceYear.setPaidInterest(reportFactorFinanceYear.getPaidInterest() + delta.paidInterestDiff);
		reportFactorFinanceYear.setPaidServiceCharge(reportFactorFinanceYear.getPaidServiceCharge() + delta.paidServiceChargeDiff);
		reportFactorFinanceYear.setPaidEarlyRepaymentCharge(reportFactorFinanceYear.getPaidEarlyRepaymentCharge() + delta.paidEarlyRepaymentChargeDiff);
		reportFactorFinanceYear.setPaidOverduePrincipalPenalty(reportFactorFinanceYear.getPaidOverduePrincipalPenalty()+ delta.paidOverduePrincipalPenaltyDiff);
		reportFactorFinanceYear.setPaidOverdueInterestPenalty(reportFactorFinanceYear.getPaidOverdueInterestPenalty() + delta.paidOverdueInterestPenaltyDiff);
		reportFactorFinanceYear.setPaidOverdueServiceChargePenalty(reportFactorFinanceYear.getPaidOverdueServiceChargePenalty() + delta.paidOverdueServiceChargePenaltyDiff);
		reportFactorFinanceYear.setPaidOtherPenalty(reportFactorFinanceYear.getPaidOtherPenalty() + delta.paidOtherPenaltyDiff);
//		reportFactorFinanceYear.setPaidOverdue(reportFactorFinanceYear.getPaidOverdue() + delta.paidOverdueDiff);
		reportFactorFinanceYear.setPaidOverdue(reportFactorFinanceYear.getPaidOverdue() + delta.paidOverduePrincipalDiff + delta.paidOverdueInterestDiff 
				+ delta.paidOverdueServiceChargeDiff);
		reportFactorFinanceYear.setPaidOverduePrincipal(reportFactorFinanceYear.getPaidOverduePrincipal() + delta.paidOverduePrincipalDiff );
		reportFactorFinanceYear.setPaidOverdueInterest(reportFactorFinanceYear.getPaidOverdueInterest() + delta.paidOverdueInterestDiff);
		reportFactorFinanceYear.setPaidOverdueServiceCharge(reportFactorFinanceYear.getPaidOverdueServiceCharge() + delta.paidOverdueServiceChargeDiff );
		reportFactorFinanceYearMapper.updateByPrimaryKeySelective(reportFactorFinanceYear);
	}
	
	private void updateCountReport(ReportFactorFinanceCount reportFactorFinanceCount) {
		if(null == reportFactorFinanceCount)
			return;
		reportFactorFinanceCount.setRepaymentItems(reportFactorFinanceCount.getRepaymentItems() + 1);
		reportFactorFinanceCountMapper.updateByPrimaryKeySelective(reportFactorFinanceCount);
	}
	
	private void calculate(LoanInfoChangeEvent event, Delta delta) throws Exception{
		delta.financeBalanceDiff += event.getNewSnapshot().getFinanceBalance() - event.getOldSnapshot().getFinanceBalance();
		
		List<RepaymentPlanSnapshot> oldRepaymentPlanSnapshots = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getOldSnapshot().getId());
		List<RepaymentPlanSnapshot> newRepaymentPlanSnapshots = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getNewSnapshot().getId());
		for (RepaymentPlanSnapshot newRepaymentPlanSnapshot : newRepaymentPlanSnapshots) {
			RepaymentPlanSnapshot oldRepaymentPlanSnapshot = oldRepaymentPlanSnapshots.get(newRepaymentPlanSnapshots.indexOf(newRepaymentPlanSnapshot));
			
			//已还本金 = 已还正常本金
			delta.paidPrincipalDiff += newRepaymentPlanSnapshot.getPaidNormalPrincipal() - oldRepaymentPlanSnapshot.getPaidNormalPrincipal();
			
			//已还费用 = 已还正常利息 + 已还正常服务费 + 已还提前还款手续费 + 已还逾期本金罚息 + 已还逾期利息罚息 + 已还逾期服务费罚息 + 已还其他罚息
//			delta.paidChargeDiff += newRepaymentPlanSnapshot.getPaidNormalInterest() - oldRepaymentPlanSnapshot.getPaidNormalInterest()
//							+ newRepaymentPlanSnapshot.getPaidNormalServiceCharge() - oldRepaymentPlanSnapshot.getPaidNormalServiceCharge()
//							+ newRepaymentPlanSnapshot.getPaidEarlyRepaymentCharge() - oldRepaymentPlanSnapshot.getPaidEarlyRepaymentCharge()
//							+ newRepaymentPlanSnapshot.getPaidOverduePrincipalPenalty() - oldRepaymentPlanSnapshot.getPaidOverduePrincipalPenalty()
//							+ newRepaymentPlanSnapshot.getPaidOverdueInterestPenalty() - oldRepaymentPlanSnapshot.getPaidOverdueInterestPenalty()
//							+ newRepaymentPlanSnapshot.getPaidOverdueServiceChargePenalty() - oldRepaymentPlanSnapshot.getPaidOverdueServiceChargePenalty()
//							+ newRepaymentPlanSnapshot.getPaidOtherPenalty() - oldRepaymentPlanSnapshot.getPaidOtherPenalty();
			delta.paidInterestDiff += (newRepaymentPlanSnapshot.getPaidNormalInterest() - oldRepaymentPlanSnapshot.getPaidNormalInterest());
			delta.paidServiceChargeDiff += (newRepaymentPlanSnapshot.getPaidNormalServiceCharge() - oldRepaymentPlanSnapshot.getPaidNormalServiceCharge());
			delta.paidEarlyRepaymentChargeDiff += (newRepaymentPlanSnapshot.getPaidEarlyRepaymentCharge() - oldRepaymentPlanSnapshot.getPaidEarlyRepaymentCharge());
			delta.paidOverduePrincipalPenaltyDiff = (newRepaymentPlanSnapshot.getPaidOverduePrincipalPenalty() - oldRepaymentPlanSnapshot.getPaidOverduePrincipalPenalty());
			delta.paidOverdueInterestPenaltyDiff += (newRepaymentPlanSnapshot.getPaidOverdueInterestPenalty() - oldRepaymentPlanSnapshot.getPaidOverdueInterestPenalty());
			delta.paidOverdueServiceChargePenaltyDiff += (newRepaymentPlanSnapshot.getPaidOverdueServiceCharge() - oldRepaymentPlanSnapshot.getPaidOverdueServiceCharge());
			delta.paidOtherPenaltyDiff = (newRepaymentPlanSnapshot.getPaidOtherPenalty() - oldRepaymentPlanSnapshot.getPaidOtherPenalty());
			//已还逾期费用 = 已还逾期本金 + 已还逾期利息 + 已还逾期服务费
//			delta.paidOverdueDiff += newRepaymentPlanSnapshot.getPaidOverduePrincipal() - oldRepaymentPlanSnapshot.getPaidOverduePrincipal()
//							 + newRepaymentPlanSnapshot.getPaidOverdueInterest() - oldRepaymentPlanSnapshot.getPaidOverdueInterest()
//							 + newRepaymentPlanSnapshot.getPaidOverdueServiceCharge() - oldRepaymentPlanSnapshot.getPaidOverdueServiceCharge();
			delta.paidOverduePrincipalDiff += (newRepaymentPlanSnapshot.getPaidOverduePrincipal() - oldRepaymentPlanSnapshot.getPaidOverduePrincipal());
			delta.paidOverdueInterestDiff += (newRepaymentPlanSnapshot.getPaidOverdueInterest() - oldRepaymentPlanSnapshot.getPaidOverdueInterest());
			delta.paidOverdueServiceChargeDiff += (newRepaymentPlanSnapshot.getPaidOverdueServiceCharge() - oldRepaymentPlanSnapshot.getPaidOverdueServiceCharge());
		
		}
	}
	
	private class Delta {
		private long financeBalanceDiff = 0;	//融资余额差额
		private long paidPrincipalDiff = 0;		//已还正常本金
		private long paidInterestDiff = 0;
	    private long paidServiceChargeDiff = 0;
	    private long paidEarlyRepaymentChargeDiff = 0;
	    private long paidOverduePrincipalPenaltyDiff = 0;
	    private long paidOverdueInterestPenaltyDiff = 0;
	    private long paidOverdueServiceChargePenaltyDiff = 0;
	    private long paidOtherPenaltyDiff = 0;
//		private long paidChargeDiff = 0;		//已还费用
	    private long paidOverduePrincipalDiff = 0;
	    private long paidOverdueInterestDiff = 0;
	    private long paidOverdueServiceChargeDiff = 0;
//		private long paidOverdueDiff = 0;		//已还逾期
	}
}
