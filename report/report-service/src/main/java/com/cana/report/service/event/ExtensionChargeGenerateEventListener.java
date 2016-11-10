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
import com.cana.vbam.common.report.enums.BusinessProduct;

public class ExtensionChargeGenerateEventListener extends AbstractEventListener {
	
	private static final Logger logger = LoggerFactory.getLogger(ExtensionChargeGenerateEventListener.class);
	
	@Override
	public void doExecute(LoanInfoChangeEvent event) throws Exception {
		logger.info("融资报表生成定时任务 - 执行 - 放款信息[id：{}]展期生成处理器", event.getNewSnapshot().getLoanInfoId());
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getOldSnapshot().getBusinessProductId())){
			businessProductId = event.getOldSnapshot().getBusinessProductId();
		}
		Delta delta = this.new Delta();
		
		// 计算本次生成的展期
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
			// 更新核心企业的计数报表
			ReportFactorFinanceCount reportFinanceCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateCountReport(reportFinanceCount);
		}
		logger.info("融资报表生成定时任务 - 状态 - 放款信息[id：{}]展期生成处理器处理完成，追踪至[版本号：{}]", event.getNewSnapshot().getLoanInfoId(), event.getNewSnapshot().getCurrentVersion());
	}
	
	private void updateDailyReport(ReportFactorFinanceDay reportFactorFinanceDay, Delta delta){
		if( null == reportFactorFinanceDay ){
			return;
		}
		reportFactorFinanceDay.setAccountCharge(reportFactorFinanceDay.getAccountCharge() + delta.extensionCharge);
		reportFactorFinanceDay.setAccountExtensionCharge(reportFactorFinanceDay.getAccountExtensionCharge() + delta.extensionCharge);
		reportFactorFinanceDayMapper.updateByPrimaryKeySelective(reportFactorFinanceDay);
	}
	
	private void updateAnnualReport(ReportFactorFinanceYear reportFactorFinanceYear, Delta delta){
		if( null == reportFactorFinanceYear ){
			return;
		}
		reportFactorFinanceYear.setAccountCharge(reportFactorFinanceYear.getAccountCharge() + delta.extensionCharge);
		reportFactorFinanceYear.setTotalExtension(reportFactorFinanceYear.getTotalExtension() + delta.extensionPrincipal);
		reportFactorFinanceYear.setAccountExtensionCharge(reportFactorFinanceYear.getAccountExtensionCharge() + delta.extensionCharge);
		reportFactorFinanceYearMapper.updateByPrimaryKeySelective(reportFactorFinanceYear);
	}
	
	private void updateCountReport(ReportFactorFinanceCount reportFactorFinanceCount) {
		if(null == reportFactorFinanceCount)
			return;
		reportFactorFinanceCount.setExtensionItems(reportFactorFinanceCount.getExtensionItems() + 1);
		reportFactorFinanceCountMapper.updateByPrimaryKeySelective(reportFactorFinanceCount);
	}
	
	private void calculate(LoanInfoChangeEvent event, Delta delta){
		// 获取新老还款计划的快照
		List<RepaymentPlanSnapshot> repaymentPlanSnapshotListOld = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getOldSnapshot().getId());
		List<RepaymentPlanSnapshot> repaymentPlanSnapshotListNew = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getNewSnapshot().getId());
		for(RepaymentPlanSnapshot repaymentPlanSnapshotNew : repaymentPlanSnapshotListNew){
			int index = repaymentPlanSnapshotListNew.indexOf(repaymentPlanSnapshotNew);
			RepaymentPlanSnapshot repaymentPlanSnapshotOld = repaymentPlanSnapshotListOld.get(index);
			// 计算本次生成的展期金额
			delta.extensionCharge += (repaymentPlanSnapshotNew.getAccountExtensionCharge() - repaymentPlanSnapshotOld.getAccountExtensionCharge());
			// 计算进入展期第一天的应还本金
			String nowDate = commonService.getCurrentDate();
			String repayemntDate = DateTime.parse(repaymentPlanSnapshotNew.getRepaymentDate()).plusDays(1).toString("yyyy-MM-dd");
			int compareResult = StringUtils.trim(nowDate).compareTo(StringUtils.trim(repayemntDate));
			if( compareResult == 0 ){
				delta.extensionPrincipal += (repaymentPlanSnapshotNew.getAccountPrincipal());
			}
		}
	}

	private class Delta{
		private long extensionPrincipal = 0;
		private long extensionCharge = 0;
	}
}
