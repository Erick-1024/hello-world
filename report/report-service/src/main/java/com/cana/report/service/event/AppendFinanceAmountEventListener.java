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

public class AppendFinanceAmountEventListener extends AbstractEventListener {
	
	private static final Logger logger = LoggerFactory.getLogger(AppendFinanceAmountEventListener.class);
	
	@Override
	public void doExecute(LoanInfoChangeEvent event) throws Exception {
		logger.info("融资报表生成定时任务 - 执行 - 放款信息[id：{}]追加放款处理器", event.getNewSnapshot().getLoanInfoId());
		Delta delta = this.new Delta();
		calculate(event, delta);
		updateFactorReport(event, delta);
		updateFinanceReport(event, delta);
		updateCoreCompanyReport(event, delta);
		updateCanaReport(event, delta);
		logger.info("融资报表生成定时任务 - 状态 - 放款信息[id：{}]追加放款处理器处理完成，追踪至[版本号：{}]", event.getNewSnapshot().getLoanInfoId(), event.getNewSnapshot().getCurrentVersion());
	}
	
	private void updateFactorReport(LoanInfoChangeEvent event, Delta delta) {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getNewSnapshot().getBusinessProductId())){
			businessProductId = event.getNewSnapshot().getBusinessProductId();
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
	
	private void updateCanaReport(LoanInfoChangeEvent event, Delta delta) {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getNewSnapshot().getBusinessProductId())){
			businessProductId = event.getNewSnapshot().getBusinessProductId();
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
	
	private void updateFinanceReport(LoanInfoChangeEvent event, Delta delta) {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getNewSnapshot().getBusinessProductId())){
			businessProductId = event.getNewSnapshot().getBusinessProductId();
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
	
	private void updateCoreCompanyReport(LoanInfoChangeEvent event, Delta delta) {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getNewSnapshot().getBusinessProductId())){
			businessProductId = event.getNewSnapshot().getBusinessProductId();
		}
		//如果放款信息的核心企业存在
		if(StringUtils.isNoneBlank(event.getNewSnapshot().getCoreCompanyId())) {
			//更新核心企业的日报表
			ReportFactorFinanceDay reportFinanceDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateDailyReport(reportFinanceDay, delta);
			//更新核心企业的年报表
			ReportFactorFinanceYear reportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateAnnualReport(reportFinanceYear, delta);
			//更新核心企业的计数报表
			ReportFactorFinanceCount reportFactorCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateCountReport(reportFactorCount);
		}
	}
	
	private void updateDailyReport(ReportFactorFinanceDay reportFactorFinanceDay, Delta delta){
		if(null == reportFactorFinanceDay)
			return;
		reportFactorFinanceDay.setFinanceBalance(reportFactorFinanceDay.getFinanceBalance() + delta.financeBalanceDiff);
		reportFactorFinanceDay.setFinanceAmount(reportFactorFinanceDay.getFinanceAmount() + delta.financeAmountDiff);
		reportFactorFinanceDayMapper.updateByPrimaryKeySelective(reportFactorFinanceDay);
	}
	
	private void updateAnnualReport(ReportFactorFinanceYear reportFactorFinanceYear, Delta delta){
		if(null == reportFactorFinanceYear)
			return;
		reportFactorFinanceYear.setFinanceBalance(reportFactorFinanceYear.getFinanceBalance() + delta.financeBalanceDiff);
		reportFactorFinanceYear.setFinanceAmount(reportFactorFinanceYear.getFinanceAmount() + delta.financeAmountDiff);
		reportFactorFinanceYear.setAccountPrincipal(reportFactorFinanceYear.getAccountPrincipal() + delta.accountPrincipalDiff);
		reportFactorFinanceYear.setAccountCharge(reportFactorFinanceYear.getAccountCharge() + delta.accountInterestDiff + delta.accountServiceChargeDiff);
		reportFactorFinanceYear.setAccountInterest(reportFactorFinanceYear.getAccountInterest() + delta.accountInterestDiff);
		reportFactorFinanceYear.setAccountServiceCharge(reportFactorFinanceYear.getAccountServiceCharge() + delta.accountServiceChargeDiff);
		reportFactorFinanceYearMapper.updateByPrimaryKeySelective(reportFactorFinanceYear);
	}
	
	private void updateCountReport(ReportFactorFinanceCount reportFactorFinanceCount) {
		if(null == reportFactorFinanceCount)
			return;
		reportFactorFinanceCount.setLoanItems(reportFactorFinanceCount.getLoanItems() + 1);
		reportFactorFinanceCountMapper.updateByPrimaryKeySelective(reportFactorFinanceCount);
	}
	
	//计算报表修改还款计划时。更新需要的字段
	private void calculate(LoanInfoChangeEvent event, Delta delta) {
		delta.financeBalanceDiff += event.getNewSnapshot().getFinanceBalance() - event.getOldSnapshot().getFinanceBalance();
		delta.financeAmountDiff += event.getNewSnapshot().getFinanceAmount()- event.getOldSnapshot().getFinanceAmount();
			
		List<RepaymentPlanSnapshot> newRepaymentPlanSnapshots = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getNewSnapshot().getId());
		List<RepaymentPlanSnapshot> oldRepaymentPlanSnapshots = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getOldSnapshot().getId());
		for (RepaymentPlanSnapshot newRepaymentPlanSnapshot : newRepaymentPlanSnapshots) {
			RepaymentPlanSnapshot oldRepaymentPlanSnapshot = oldRepaymentPlanSnapshots.get(newRepaymentPlanSnapshots.indexOf(newRepaymentPlanSnapshot));
			//当还款日为当年时，计算应还
			if(event.getCurYear().equals(newRepaymentPlanSnapshot.getRepaymentDate().substring(0, 4))) {
					
				//当年应还本金
				delta.accountPrincipalDiff += newRepaymentPlanSnapshot.getAccountPrincipal() - oldRepaymentPlanSnapshot.getAccountPrincipal();
				
				//当年应还费用 = 当年应还利息 + 当年应还服务费
//				delta.accountChargeDiff += newRepaymentPlanSnapshot.getAccountInterest() - oldRepaymentPlanSnapshot.getAccountInterest()
//										 + newRepaymentPlanSnapshot.getAccountServiceCharge() - oldRepaymentPlanSnapshot.getAccountServiceCharge();
				delta.accountInterestDiff += newRepaymentPlanSnapshot.getAccountInterest() - oldRepaymentPlanSnapshot.getAccountInterest();
				delta.accountServiceChargeDiff += newRepaymentPlanSnapshot.getAccountServiceCharge() - oldRepaymentPlanSnapshot.getAccountServiceCharge();;
			}
		}
	}
		
		private class Delta {
			private long financeAmountDiff = 0; 		//放款金额
			private long financeBalanceDiff = 0;	//融资余额
			private long accountPrincipalDiff = 0;	//当日应还本金
			private long accountInterestDiff = 0;
			private long accountServiceChargeDiff = 0;
//			private long accountChargeDiff = 0;		//当日应还费用
		}
}
