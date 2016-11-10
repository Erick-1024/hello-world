package com.cana.report.service.event;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.repayment.dao.po.RepaymentPlanSnapshot;
import com.cana.report.dao.po.ReportFactorFinanceDay;
import com.cana.report.dao.po.ReportFactorFinanceYear;
import com.cana.vbam.common.report.enums.BusinessProduct;

public class PenaltyGenerateEventListener extends AbstractEventListener {
	
	private static final Logger logger = LoggerFactory.getLogger(PenaltyGenerateEventListener.class);

	@Override
	public void doExecute(LoanInfoChangeEvent event) throws Exception {
		logger.info("融资报表生成定时任务 - 执行 - 放款信息[id：{}]罚息生成处理器", event.getNewSnapshot().getLoanInfoId());
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getOldSnapshot().getBusinessProductId())){
			businessProductId = event.getOldSnapshot().getBusinessProductId();
		}
		Delta delta = this.new Delta();
		calculate(event, delta);
		
		// 更新保理商日报表
		ReportFactorFinanceDay reportFactorDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getFactorId(), businessProductId);
		updateDailyReport(reportFactorDay, delta);
		// 更新保理商年报表
		ReportFactorFinanceYear reportFactorYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getFactorId(), businessProductId);
		updateAnnualReport(reportFactorYear, delta);
		
		// 更新CANA日报表
		ReportFactorFinanceDay canaReportDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), userApi.getCanaId(), businessProductId);
		updateDailyReport(canaReportDay, delta);
		// 更新CANA年报表
		ReportFactorFinanceYear canaReportYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), userApi.getCanaId(), businessProductId);
		updateAnnualReport(canaReportYear, delta);
		
		if(StringUtils.isNotBlank(event.getNewSnapshot().getFinanceId())){
			// 更新融资客户日报表
			ReportFactorFinanceDay reportFinanceDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateDailyReport(reportFinanceDay, delta);
			// 更新融资客户年报表
			ReportFactorFinanceYear reportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateAnnualReport(reportFinanceYear, delta);
		}

		if(StringUtils.isNotBlank(event.getNewSnapshot().getCoreCompanyId())){
			// 更新核心企业日报表
			ReportFactorFinanceDay reportFinanceDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateDailyReport(reportFinanceDay, delta);
			// 更新核心企业年报表
			ReportFactorFinanceYear reportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateAnnualReport(reportFinanceYear, delta);
		}
		logger.info("融资报表生成定时任务 - 状态 - 放款信息[id：{}]罚息生成处理器处理完成，追踪至[版本号：{}]", event.getNewSnapshot().getLoanInfoId(), event.getNewSnapshot().getCurrentVersion());
	}
	
	private void updateDailyReport(ReportFactorFinanceDay reportFactorFinanceDay, Delta delta){
		if( null == reportFactorFinanceDay ){
			return;
		}
		reportFactorFinanceDay.setAccountCharge(reportFactorFinanceDay.getAccountCharge() + delta.overduePrincipalPenaltyDiff 
				+ delta.overdueInterestPenaltyDiff + delta.overdueServiceChargePenaltyDiff + delta.otherPenaltyDiff);
		reportFactorFinanceDay.setAccountOverduePrincipalPenalty(reportFactorFinanceDay.getAccountOverduePrincipalPenalty() + delta.overduePrincipalPenaltyDiff);
		reportFactorFinanceDay.setAccountOverdueInterestPenalty(reportFactorFinanceDay.getAccountOverdueInterestPenalty() + delta.overdueInterestPenaltyDiff);
		reportFactorFinanceDay.setAccountOverdueServiceChargePenalty(reportFactorFinanceDay.getAccountOverdueServiceChargePenalty() + delta.overdueServiceChargePenaltyDiff);
		reportFactorFinanceDay.setAccountOtherPenalty(reportFactorFinanceDay.getAccountOtherPenalty() + delta.otherPenaltyDiff);
		reportFactorFinanceDayMapper.updateByPrimaryKeySelective(reportFactorFinanceDay);
	}
	
	private void updateAnnualReport(ReportFactorFinanceYear reportFactorFinanceYear, Delta delta){
		if( null == reportFactorFinanceYear ){
			return;
		}
		reportFactorFinanceYear.setAccountCharge(reportFactorFinanceYear.getAccountCharge() + delta.overduePrincipalPenaltyDiff 
				+ delta.overdueInterestPenaltyDiff + delta.overdueServiceChargePenaltyDiff + delta.otherPenaltyDiff);
		reportFactorFinanceYear.setAccountOverduePrincipalPenalty(reportFactorFinanceYear.getAccountOverduePrincipalPenalty() + delta.overduePrincipalPenaltyDiff);
		reportFactorFinanceYear.setAccountOverdueInterestPenalty(reportFactorFinanceYear.getAccountOverdueInterestPenalty() + delta.overdueInterestPenaltyDiff);
		reportFactorFinanceYear.setAccountOverdueServiceChargePenalty(reportFactorFinanceYear.getAccountOverdueServiceChargePenalty() + delta.overdueServiceChargePenaltyDiff);
		reportFactorFinanceYear.setAccountOtherPenalty(reportFactorFinanceYear.getAccountOtherPenalty() + delta.otherPenaltyDiff);
		reportFactorFinanceYearMapper.updateByPrimaryKeySelective(reportFactorFinanceYear);
	}
	
	private void calculate(LoanInfoChangeEvent event, Delta delta){
		// 获取新老还款计划的快照
				List<RepaymentPlanSnapshot> repaymentPlanSnapshotListOld = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getOldSnapshot().getId());
				List<RepaymentPlanSnapshot> repaymentPlanSnapshotListNew = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getNewSnapshot().getId());
		for(RepaymentPlanSnapshot repaymentPlanSnapshotNew : repaymentPlanSnapshotListNew){
			int index = repaymentPlanSnapshotListNew.indexOf(repaymentPlanSnapshotNew);
			RepaymentPlanSnapshot repaymentPlanSnapshotOld = repaymentPlanSnapshotListOld.get(index);
//			delta.penalty += (repaymentPlanSnapshotNew.getOverduePrincipalPenalty() - repaymentPlanSnapshotOld.getOverduePrincipalPenalty())
//							+ (repaymentPlanSnapshotNew.getOverdueInterestPenalty() - repaymentPlanSnapshotOld.getOverdueInterestPenalty())
//							+ (repaymentPlanSnapshotNew.getOverdueServiceChargePenalty() - repaymentPlanSnapshotOld.getOverdueServiceChargePenalty())
//							+ (repaymentPlanSnapshotNew.getOtherPenalty() - repaymentPlanSnapshotOld.getOtherPenalty());
			delta.overduePrincipalPenaltyDiff += repaymentPlanSnapshotNew.getOverduePrincipalPenalty() - repaymentPlanSnapshotOld.getOverduePrincipalPenalty();
			delta.overdueInterestPenaltyDiff += repaymentPlanSnapshotNew.getOverdueInterestPenalty() - repaymentPlanSnapshotOld.getOverdueInterestPenalty();
			delta.overdueServiceChargePenaltyDiff += repaymentPlanSnapshotNew.getOverdueServiceChargePenalty() - repaymentPlanSnapshotOld.getOverdueServiceChargePenalty();
			delta.otherPenaltyDiff += repaymentPlanSnapshotNew.getOtherPenalty() - repaymentPlanSnapshotOld.getOtherPenalty();
		}
	}

	private class Delta {
		private long overduePrincipalPenaltyDiff = 0;
	    private long overdueInterestPenaltyDiff = 0;
	    private long overdueServiceChargePenaltyDiff = 0;
	    private long otherPenaltyDiff = 0;
//		private long penalty = 0;
	}
}
