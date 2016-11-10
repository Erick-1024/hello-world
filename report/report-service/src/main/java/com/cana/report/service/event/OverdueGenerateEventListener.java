package com.cana.report.service.event;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.repayment.dao.po.RepaymentPlanSnapshot;
import com.cana.report.dao.po.ReportFactorFinanceCount;
import com.cana.report.dao.po.ReportFactorFinanceDay;
import com.cana.report.dao.po.ReportFactorFinanceYear;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.report.enums.BusinessProduct;

public class OverdueGenerateEventListener extends AbstractEventListener{
	
	private static final Logger logger = LoggerFactory.getLogger(OverdueGenerateEventListener.class);
	
	@Override
	public void doExecute(LoanInfoChangeEvent event) throws Exception {
		logger.info("融资报表生成定时任务 - 执行 - 放款信息[id：{}]逾期生成处理器", event.getNewSnapshot().getLoanInfoId());
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getOldSnapshot().getBusinessProductId())){
			businessProductId = event.getOldSnapshot().getBusinessProductId();
		}
		Delta delta = this.new Delta();
		// 计算本次生成的逾期
		calculate(event, delta);
		
		// 更新保理商日报表
		ReportFactorFinanceDay reportFactorDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getFactorId(), businessProductId);
		updateDailyReport(reportFactorDay, delta);
		// 更新保理商年报表
		ReportFactorFinanceYear reportFactorYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getFactorId(), businessProductId);
		updateAnnualReport(reportFactorYear, delta);
		//更新保理商的计数报表
		ReportFactorFinanceCount reportFactorCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), event.getNewSnapshot().getFactorId(), businessProductId);
		updateCountReport(reportFactorCount, delta);
		
		// 更新CANA日报表
		ReportFactorFinanceDay canaReportDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), userApi.getCanaId(), businessProductId);
		updateDailyReport(canaReportDay, delta);
		// 更新CANA年报表
		ReportFactorFinanceYear canaReportYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), userApi.getCanaId(), businessProductId);
		updateAnnualReport(canaReportYear, delta);
		//更新cana的计数报表
		ReportFactorFinanceCount reportCanaCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), userApi.getCanaId(), businessProductId);
		updateCountReport(reportCanaCount, delta);
		
		if(StringUtils.isNotBlank(event.getNewSnapshot().getFinanceId())){
			// 更新融资客户日报表
			ReportFactorFinanceDay reportFinanceDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateDailyReport(reportFinanceDay, delta);
			// 更新融资客户年报表
			ReportFactorFinanceYear reportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateAnnualReport(reportFinanceYear, delta);
			//更新融资客户的计数报表
			ReportFactorFinanceCount reportFinanceCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateCountReport(reportFinanceCount, delta);
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
			updateCountReport(reportFinanceCount, delta);
		}
		logger.info("融资报表生成定时任务 - 状态 - 放款信息[id：{}]逾期生成处理器处理完成，追踪至[版本号：{}]", event.getNewSnapshot().getLoanInfoId(), event.getNewSnapshot().getCurrentVersion());
	}

	private void updateDailyReport(ReportFactorFinanceDay reportFactorFinanceDay, Delta delta){
		if( null == reportFactorFinanceDay ){
			return;
		}
		reportFactorFinanceDay.setAccountPrincipal(reportFactorFinanceDay.getAccountPrincipal() - delta.overduePrincipal);
		reportFactorFinanceDay.setAccountCharge(reportFactorFinanceDay.getAccountCharge() - delta.overdueInterest - delta.overdueServiceCharge - delta.overdueExtension);
		reportFactorFinanceDay.setAccountInterest(reportFactorFinanceDay.getAccountInterest() - delta.overdueInterest);
		reportFactorFinanceDay.setAccountServiceCharge(reportFactorFinanceDay.getAccountServiceCharge() - delta.overdueServiceCharge);
		reportFactorFinanceDay.setAccountExtensionCharge(reportFactorFinanceDay.getAccountExtensionCharge() - delta.overdueExtension);
		reportFactorFinanceDay.setAccountOverdue(reportFactorFinanceDay.getAccountOverdue() + delta.overduePrincipal + delta.overdueInterest + delta.overdueServiceCharge + delta.overdueExtension);
		reportFactorFinanceDay.setAccountOverduePrincipal(reportFactorFinanceDay.getAccountOverduePrincipal() + delta.overduePrincipal);
		reportFactorFinanceDay.setAccountOverdueInterest(reportFactorFinanceDay.getAccountOverdueInterest() + delta.overdueInterest);
		reportFactorFinanceDay.setAccountOverdueServiceCharge(reportFactorFinanceDay.getAccountOverdueServiceCharge() + delta.overdueServiceCharge);
		reportFactorFinanceDay.setAccountOverdueExtensionCharge(reportFactorFinanceDay.getAccountOverdueExtensionCharge() + delta.overdueExtension);
		reportFactorFinanceDayMapper.updateByPrimaryKeySelective(reportFactorFinanceDay);
	}
	
	private void updateAnnualReport(ReportFactorFinanceYear reportFactorFinanceYear, Delta delta){
		if( null == reportFactorFinanceYear ){
			return;
		}
		reportFactorFinanceYear.setAccountPrincipal(reportFactorFinanceYear.getAccountPrincipal() - delta.overduePrincipal);
		reportFactorFinanceYear.setAccountCharge(reportFactorFinanceYear.getAccountCharge() - delta.overdueInterest - delta.overdueServiceCharge - delta.overdueExtension);
		reportFactorFinanceYear.setAccountInterest(reportFactorFinanceYear.getAccountInterest() - delta.overdueInterest);
		reportFactorFinanceYear.setAccountServiceCharge(reportFactorFinanceYear.getAccountServiceCharge() - delta.overdueServiceCharge);
		reportFactorFinanceYear.setAccountExtensionCharge(reportFactorFinanceYear.getAccountExtensionCharge() - delta.overdueExtension);
		reportFactorFinanceYear.setAccountOverdue(reportFactorFinanceYear.getAccountOverdue() + delta.overduePrincipal + delta.overdueInterest + delta.overdueServiceCharge + delta.overdueExtension);
		reportFactorFinanceYear.setAccountOverduePrincipal(reportFactorFinanceYear.getAccountOverduePrincipal() + delta.overduePrincipal);
		reportFactorFinanceYear.setAccountOverdueInterest(reportFactorFinanceYear.getAccountOverdueInterest() + delta.overdueInterest);
		reportFactorFinanceYear.setAccountOverdueServiceCharge(reportFactorFinanceYear.getAccountOverdueServiceCharge() + delta.overdueServiceCharge);
		reportFactorFinanceYear.setAccountOverdueExtensionCharge(reportFactorFinanceYear.getAccountOverdueExtensionCharge() + delta.overdueExtension);
		reportFactorFinanceYear.setTotalOverdue(reportFactorFinanceYear.getTotalOverdue() + delta.overduePrincipal);
		reportFactorFinanceYearMapper.updateByPrimaryKeySelective(reportFactorFinanceYear);
	}
	
	private void updateCountReport(ReportFactorFinanceCount reportFactorFinanceCount, Delta delta) {
		if(null == reportFactorFinanceCount)
			return;
		reportFactorFinanceCount.setOverdueItems(reportFactorFinanceCount.getOverdueItems() + 1);
		if(!StringUtils.equals(reportFactorFinanceCount.getUserType(), UserType.FINACE.name())){
			reportFactorFinanceCount.setOverdueCustomer(reportFactorFinanceCount.getOverdueCustomer() + delta.overdueCustomer);
		}
		reportFactorFinanceCountMapper.updateByPrimaryKeySelective(reportFactorFinanceCount);
	}
	
	private void calculate(LoanInfoChangeEvent event, Delta delta) throws Exception{
		// 获取新老还款计划的快照
		List<RepaymentPlanSnapshot> repaymentPlanSnapshotListOld = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getOldSnapshot().getId());
		List<RepaymentPlanSnapshot> repaymentPlanSnapshotListNew = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getNewSnapshot().getId());
				
		for(RepaymentPlanSnapshot repaymentPlanSnapshotNew : repaymentPlanSnapshotListNew){
			int index = repaymentPlanSnapshotListNew.indexOf(repaymentPlanSnapshotNew);
			RepaymentPlanSnapshot repaymentPlanSnapshotOld = repaymentPlanSnapshotListOld.get(index);
			// 计算本次生成的逾期本金
			delta.overduePrincipal += (repaymentPlanSnapshotNew.getOverduePrincipal() - repaymentPlanSnapshotOld.getOverduePrincipal());
			// 计算本次生成的逾期利息
			delta.overdueInterest += (repaymentPlanSnapshotNew.getOverdueInterest() - repaymentPlanSnapshotOld.getOverdueInterest());
			// 计算本次生成的逾期服务费
			delta.overdueServiceCharge += (repaymentPlanSnapshotNew.getOverdueServiceCharge() - repaymentPlanSnapshotOld.getOverdueServiceCharge());
			// 计算本次生成的逾期展期
			String nowDate = commonService.getCurrentDate();
			String repayemntDate = DateTime.parse(repaymentPlanSnapshotNew.getRepaymentDate()).plusDays(repaymentPlanSnapshotNew.getExtensionDays()).toString();
			int compareResult = StringUtils.trim(nowDate).compareTo(StringUtils.trim(repayemntDate));
			if(compareResult > 0 ){
				delta.overdueExtension += (repaymentPlanSnapshotNew.getAccountExtensionCharge());
			}
		}
		if(overdueCustomerCountMapper.getAllFactorAndFinanceUsers(event.getOldSnapshot().getFinanceId(), event.getOldSnapshot().getLoanInfoId(), 
				event.getOldSnapshot().getFactorId())==0){
			delta.overdueCustomer = 1;
		}
	}

	private class Delta {
		private long overduePrincipal = 0;
		private long overdueInterest = 0;
		private long overdueServiceCharge = 0;
		private long overdueExtension = 0;
		private int overdueCustomer = 0;
	}
}
