package com.cana.report.service.event;

import java.util.HashMap;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.travelzen.framework.core.time.DateTimeUtil;

public class DeductEventListener extends AbstractEventListener {
	
	private static final Logger logger = LoggerFactory.getLogger(DeductEventListener.class);

	@Override
	public void doExecute(LoanInfoChangeEvent event) throws Exception {
		logger.info("融资报表生成定时任务 - 执行 - 放款信息[id：{}]帐扣处理器", event.getNewSnapshot().getLoanInfoId());
		for(RepaymentPlanSnapshot repaymentPlanSnapshot : repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getOldSnapshot().getId())){
			Delta delta = this.new Delta();
			calculate(event, delta, repaymentPlanSnapshot.getRepaymentPeriod());
			updateFactorReport(event, delta);
			updateFinanceReport(event, delta);
			updateCoreCompanyReport(event, delta);
			updateCanaReport(event, delta);
		}
		logger.info("融资报表生成定时任务 - 状态 - 放款信息[id：{}]帐扣处理器处理完成，追踪至[版本号：{}]", event.getNewSnapshot().getLoanInfoId(), event.getNewSnapshot().getCurrentVersion());
	}
	
	private void updateFactorReport(LoanInfoChangeEvent event, Delta delta) {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getOldSnapshot().getBusinessProductId())){
			businessProductId = event.getOldSnapshot().getBusinessProductId();
		}
		if(isDefaultDeduct(event)) {
			//更新保理商的前一日日报表
			ReportFactorFinanceDay preReportFactorDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getLastDay(), event.getNewSnapshot().getFactorId(), businessProductId);
			updateChangedDailyReport(preReportFactorDay, delta);
			//更新保理商的当日报表
			ReportFactorFinanceDay curReportFactorDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getFactorId(), businessProductId);
			updateNextDailyReport(curReportFactorDay, delta);
			
			if(!event.getCurDay().endsWith("01-01")) {
				//当日不为1月1日。更新保理商的当年报表
				ReportFactorFinanceYear reportFactorYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getFactorId(), businessProductId);
				updateChangedAnnualReport(reportFactorYear, delta);
			} else {
				//当日为1月1日。
				//更新保理商的前一年报表
				ReportFactorFinanceYear preReportFactorYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getLastYear(), event.getNewSnapshot().getFactorId(), businessProductId);
				updateChangedAnnualReport(preReportFactorYear, delta);
				//更新保理商的当年年报表
				ReportFactorFinanceYear curReportFactorYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getFactorId(), businessProductId);
				updateNextAnnualReport(curReportFactorYear, delta);
			}
			//更新保理商的计数报表
			ReportFactorFinanceCount reportFactorCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getLastDay(), event.getNewSnapshot().getFactorId(), businessProductId);
			updateCountReport(reportFactorCount);
		} else {
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
	}
	
	private void updateFinanceReport(LoanInfoChangeEvent event, Delta delta) {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getOldSnapshot().getBusinessProductId())){
			businessProductId = event.getOldSnapshot().getBusinessProductId();
		}
		if(isDefaultDeduct(event)) {
			//更新融资客户前一日报表
			ReportFactorFinanceDay preReportFinanceDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getLastDay(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateChangedDailyReport(preReportFinanceDay, delta);
			//更新融资客户当日报表
			ReportFactorFinanceDay curReportFinanceDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateNextDailyReport(curReportFinanceDay, delta);
			
			if(!event.getCurDay().endsWith("01-01")) {
				//当日不为1月1日。更新融资客户的当年报表
				ReportFactorFinanceYear reportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getFinanceId(), businessProductId);
				updateChangedAnnualReport(reportFinanceYear, delta);
			} else {
				//当日为1月1日。
				//更新融资客户的前一年报表
				ReportFactorFinanceYear preReportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getLastYear(), event.getNewSnapshot().getFinanceId(), businessProductId);
				updateChangedAnnualReport(preReportFinanceYear, delta);
				//更新融资客户的当年年报表
				ReportFactorFinanceYear curReportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getFinanceId(), businessProductId);
				updateNextAnnualReport(curReportFinanceYear, delta);
			}
			//更新融资客户的计数报表
			ReportFactorFinanceCount reportFinanceCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getLastDay(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateCountReport(reportFinanceCount);
		} else {
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
	}
	
	private void updateCoreCompanyReport(LoanInfoChangeEvent event, Delta delta) {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getOldSnapshot().getBusinessProductId())){
			businessProductId = event.getOldSnapshot().getBusinessProductId();
		}
		if(isDefaultDeduct(event)) {
			//更新融资客户前一日报表
			ReportFactorFinanceDay preReportFinanceDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getLastDay(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateChangedDailyReport(preReportFinanceDay, delta);
			//更新融资客户当日报表
			ReportFactorFinanceDay curReportFinanceDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateNextDailyReport(curReportFinanceDay, delta);
			
			if(!event.getCurDay().endsWith("01-01")) {
				//当日不为1月1日。更新融资客户的当年报表
				ReportFactorFinanceYear reportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
				updateChangedAnnualReport(reportFinanceYear, delta);
			} else {
				//当日为1月1日。
				//更新融资客户的前一年报表
				ReportFactorFinanceYear preReportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getLastYear(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
				updateChangedAnnualReport(preReportFinanceYear, delta);
				//更新融资客户的当年年报表
				ReportFactorFinanceYear curReportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
				updateNextAnnualReport(curReportFinanceYear, delta);
			}
			//更新融资客户的计数报表
			ReportFactorFinanceCount reportFinanceCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getLastDay(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateCountReport(reportFinanceCount);
		} else {
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
	}
	
	private void updateCanaReport(LoanInfoChangeEvent event, Delta delta) {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getOldSnapshot().getBusinessProductId())){
			businessProductId = event.getOldSnapshot().getBusinessProductId();
		}
		if(isDefaultDeduct(event)) {
			//更新cana的前一日日报表
			ReportFactorFinanceDay preReportCanaDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getLastDay(), userApi.getCanaId(), businessProductId);
			updateChangedDailyReport(preReportCanaDay, delta);
			//更新cana的当日报表
			ReportFactorFinanceDay curReportCanaDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), userApi.getCanaId(), businessProductId);
			updateNextDailyReport(curReportCanaDay, delta);
			
			if(!event.getCurDay().endsWith("01-01")) {
				//当日不为1月1日。更新cana的当年报表
				ReportFactorFinanceYear reportCanaYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), userApi.getCanaId(), businessProductId);
				updateChangedAnnualReport(reportCanaYear, delta);
			} else {
				//当日为1月1日。
				//更新cana的前一年报表
				ReportFactorFinanceYear preReportCanaYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getLastYear(), userApi.getCanaId(), businessProductId);
				updateChangedAnnualReport(preReportCanaYear, delta);
				//更新cana的当年年报表
				ReportFactorFinanceYear curReportCanaYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), userApi.getCanaId(), businessProductId);
				updateNextAnnualReport(curReportCanaYear, delta);
			}
			//更新cana的计数报表
			ReportFactorFinanceCount reportCanaCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getLastDay(), userApi.getCanaId(), businessProductId);
			updateCountReport(reportCanaCount);
		} else {
			//更新cana的当日日报表
			ReportFactorFinanceDay reportCanaDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), userApi.getCanaId(), businessProductId);
			updateChangedDailyReport(reportCanaDay, delta);
			//更新cana的当年年报表
			ReportFactorFinanceYear reportCanaYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), userApi.getCanaId(), businessProductId);
			updateChangedAnnualReport(reportCanaYear, delta);
			//更新cana的计数报表
			ReportFactorFinanceCount reportCanaCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), userApi.getCanaId(), businessProductId);
			updateCountReport(reportCanaCount);
		}
	}
	
	private void updateChangedDailyReport(ReportFactorFinanceDay reportFactorFinanceDay, Delta delta){
		if(null == reportFactorFinanceDay)
			return;
		
		reportFactorFinanceDay.setFinanceBalance(reportFactorFinanceDay.getFinanceBalance() + delta.financeBalanceDiff);
		reportFactorFinanceDay.setPaidPrincipal(reportFactorFinanceDay.getPaidPrincipal() + delta.paidPrincipalDiff);
		reportFactorFinanceDay.setPaidCharge(reportFactorFinanceDay.getPaidCharge() + delta.paidInterestDiff + delta.paidServiceChargeDiff + delta.paidExtensionChargeDiff 
				+ delta.paidEarlyRepaymentChargeDiff + delta.paidOverduePrincipalPenaltyDiff + delta.paidOverdueInterestPenaltyDiff + delta.paidOverdueServiceChargePenaltyDiff+ delta.paidOtherPenaltyDiff + delta.paidExpenseDiff);
		reportFactorFinanceDay.setPaidInterest(reportFactorFinanceDay.getPaidInterest() + delta.paidInterestDiff);
		reportFactorFinanceDay.setPaidServiceCharge(reportFactorFinanceDay.getPaidServiceCharge() + delta.paidServiceChargeDiff);
		reportFactorFinanceDay.setPaidExtensionCharge(reportFactorFinanceDay.getPaidExtensionCharge() + delta.paidExtensionChargeDiff);
		reportFactorFinanceDay.setPaidEarlyRepaymentCharge(reportFactorFinanceDay.getPaidEarlyRepaymentCharge() + delta.paidEarlyRepaymentChargeDiff);
		reportFactorFinanceDay.setPaidOverduePrincipalPenalty(reportFactorFinanceDay.getPaidOverduePrincipalPenalty() + delta.paidOverduePrincipalPenaltyDiff);
		reportFactorFinanceDay.setPaidOverdueInterestPenalty(reportFactorFinanceDay.getPaidOverdueInterestPenalty() + delta.paidOverdueInterestPenaltyDiff);
		reportFactorFinanceDay.setPaidOverdueServiceChargePenalty(reportFactorFinanceDay.getPaidOverdueServiceChargePenalty() + delta.paidOverdueServiceChargePenaltyDiff);
		reportFactorFinanceDay.setPaidOtherPenalty(reportFactorFinanceDay.getPaidOtherPenalty() + delta.paidOtherPenaltyDiff);
		reportFactorFinanceDay.setPaidExpense(reportFactorFinanceDay.getPaidExpense() + delta.paidExpenseDiff);
		reportFactorFinanceDay.setPaidOverdue(reportFactorFinanceDay.getPaidOverdue() + delta.paidOverduePrincipalDiff + delta.paidOverdueInterestDiff
				+ delta.paidOverdueServiceChargeDiff  + delta.paidOverdueExtensionChargeDiff);
		reportFactorFinanceDay.setPaidOverduePrincipal(reportFactorFinanceDay.getPaidOverduePrincipal() + delta.paidOverduePrincipalDiff );
		reportFactorFinanceDay.setPaidOverdueInterest(reportFactorFinanceDay.getPaidOverdueInterest() + delta.paidOverdueInterestDiff);
		reportFactorFinanceDay.setPaidOverdueServiceCharge(reportFactorFinanceDay.getPaidOverdueServiceCharge() + delta.paidOverdueServiceChargeDiff);
		reportFactorFinanceDay.setPaidOverdueExtensionCharge(reportFactorFinanceDay.getPaidOverdueExtensionCharge() + delta.paidOverdueExtensionChargeDiff);
		reportFactorFinanceDayMapper.updateByPrimaryKeySelective(reportFactorFinanceDay);
	}
	
	private void updateChangedAnnualReport(ReportFactorFinanceYear reportFactorFinanceYear, Delta delta){
		if(null == reportFactorFinanceYear)
			return;
		reportFactorFinanceYear.setFinanceBalance(reportFactorFinanceYear.getFinanceBalance() + delta.financeBalanceDiff);
		reportFactorFinanceYear.setPaidPrincipal(reportFactorFinanceYear.getPaidPrincipal() + delta.paidPrincipalDiff);
		reportFactorFinanceYear.setPaidCharge(reportFactorFinanceYear.getPaidCharge() + delta.paidInterestDiff + delta.paidServiceChargeDiff + delta.paidExtensionChargeDiff 
				+ delta.paidEarlyRepaymentChargeDiff + delta.paidOverduePrincipalPenaltyDiff + delta.paidOverdueInterestPenaltyDiff + delta.paidOverdueServiceChargePenaltyDiff + delta.paidOtherPenaltyDiff + delta.paidExpenseDiff);
		reportFactorFinanceYear.setPaidInterest(reportFactorFinanceYear.getPaidInterest() + delta.paidInterestDiff);
		reportFactorFinanceYear.setPaidServiceCharge(reportFactorFinanceYear.getPaidServiceCharge() + delta.paidServiceChargeDiff);
		reportFactorFinanceYear.setPaidExtensionCharge(reportFactorFinanceYear.getPaidExtensionCharge() + delta.paidExtensionChargeDiff);
		reportFactorFinanceYear.setPaidEarlyRepaymentCharge(reportFactorFinanceYear.getPaidEarlyRepaymentCharge() + delta.paidEarlyRepaymentChargeDiff);
		reportFactorFinanceYear.setPaidOverduePrincipalPenalty(reportFactorFinanceYear.getPaidOverduePrincipalPenalty() + delta.paidOverduePrincipalPenaltyDiff);
		reportFactorFinanceYear.setPaidOverdueInterestPenalty(reportFactorFinanceYear.getPaidOverdueInterestPenalty() + delta.paidOverdueInterestPenaltyDiff);
		reportFactorFinanceYear.setPaidOverdueServiceChargePenalty(reportFactorFinanceYear.getPaidOverdueServiceChargePenalty() + delta.paidOverdueServiceChargePenaltyDiff);
		reportFactorFinanceYear.setPaidOtherPenalty(reportFactorFinanceYear.getPaidOtherPenalty() + delta.paidOtherPenaltyDiff);
		reportFactorFinanceYear.setPaidExpense(reportFactorFinanceYear.getPaidExpense() + delta.paidExpenseDiff);
		reportFactorFinanceYear.setPaidOverdue(reportFactorFinanceYear.getPaidOverdue() + delta.paidOverduePrincipalDiff + delta.paidOverdueInterestDiff
				+ delta.paidOverdueServiceChargeDiff  + delta.paidOverdueExtensionChargeDiff);
		reportFactorFinanceYear.setPaidOverduePrincipal(reportFactorFinanceYear.getPaidOverduePrincipal() + delta.paidOverduePrincipalDiff);
		reportFactorFinanceYear.setPaidOverdueInterest(reportFactorFinanceYear.getPaidOverdueInterest() + delta.paidOverdueInterestDiff);
		reportFactorFinanceYear.setPaidOverdueServiceCharge(reportFactorFinanceYear.getPaidOverdueServiceCharge() + delta.paidOverdueServiceChargeDiff);
		reportFactorFinanceYear.setPaidOverdueExtensionCharge(reportFactorFinanceYear.getPaidOverdueExtensionCharge() + delta.paidOverdueExtensionChargeDiff);
//		reportFactorFinanceYear.setAccountCharge(reportFactorFinanceYear.getAccountCharge() + delta.accountInterestDiff + delta.accountServiceChargeDiff);
		reportFactorFinanceYear.setAccountCharge(reportFactorFinanceYear.getAccountCharge() + delta.accountInterestDiff);
		reportFactorFinanceYear.setAccountInterest(reportFactorFinanceYear.getAccountInterest() + delta.accountInterestDiff);
//		reportFactorFinanceYear.setAccountServiceCharge(reportFactorFinanceYear.getAccountServiceCharge() + delta.accountServiceChargeDiff);
		reportFactorFinanceYearMapper.updateByPrimaryKeySelective(reportFactorFinanceYear);
	}
	
	private void updateNextDailyReport(ReportFactorFinanceDay reportFactorFinanceDay, Delta delta){
		if(null == reportFactorFinanceDay)
			return;
		reportFactorFinanceDay.setFinanceBalance(reportFactorFinanceDay.getFinanceBalance() + delta.financeBalanceDiff);
		// 由于自动生成的还款计划可以提前还款，在初始化时未将这部分本金、利息带入，所以不做扣除
		if(delta.flagForDay){
			reportFactorFinanceDay.setAccountPrincipal(reportFactorFinanceDay.getAccountPrincipal() - delta.paidPrincipalDiff);
			reportFactorFinanceDay.setAccountCharge(reportFactorFinanceDay.getAccountCharge() - (delta.paidServiceChargeDiff + delta.paidExtensionChargeDiff 
					+ delta.paidEarlyRepaymentChargeDiff + delta.paidOverduePrincipalPenaltyDiff + delta.paidOverdueInterestPenaltyDiff + delta.paidOverdueServiceChargePenaltyDiff 
					+ delta.paidOtherPenaltyDiff + delta.paidExpenseDiff) + delta.accountInterestDiff);
			reportFactorFinanceDay.setAccountInterest(reportFactorFinanceDay.getAccountInterest() + delta.accountInterestDiff);
			reportFactorFinanceDay.setAccountServiceCharge(reportFactorFinanceDay.getAccountServiceCharge() - delta.paidServiceChargeDiff);
			reportFactorFinanceDay.setAccountExtensionCharge(reportFactorFinanceDay.getAccountExtensionCharge() - delta.paidExtensionChargeDiff);
//			reportFactorFinanceDay.setAccountEarlyRepaymentCharge(reportFactorFinanceDay.getPaidEarlyRepaymentCharge() - delta.paidEarlyRepaymentChargeDiff);
			reportFactorFinanceDay.setAccountExpense(reportFactorFinanceDay.getAccountExpense() - delta.paidExpenseDiff);
			reportFactorFinanceDay.setAccountOverduePrincipalPenalty(reportFactorFinanceDay.getAccountOverduePrincipalPenalty() - delta.paidOverduePrincipalPenaltyDiff);
			reportFactorFinanceDay.setAccountOverdueInterestPenalty(reportFactorFinanceDay.getAccountOverdueInterestPenalty() - delta.paidOverdueInterestPenaltyDiff);
			reportFactorFinanceDay.setAccountOverdueServiceChargePenalty(reportFactorFinanceDay.getAccountOverdueServiceChargePenalty() - delta.paidOverdueServiceChargePenaltyDiff);
			reportFactorFinanceDay.setAccountOtherPenalty(reportFactorFinanceDay.getAccountOtherPenalty() - delta.paidOtherPenaltyDiff);
			reportFactorFinanceDay.setAccountOverdue(reportFactorFinanceDay.getAccountOverdue() - (delta.paidOverduePrincipalDiff + delta.paidOverdueInterestDiff
					+ delta.paidOverdueServiceChargeDiff  + delta.paidOverdueExtensionChargeDiff));
			reportFactorFinanceDay.setAccountOverduePrincipal(reportFactorFinanceDay.getAccountOverduePrincipal() - delta.paidOverduePrincipalDiff);
			reportFactorFinanceDay.setAccountOverdueInterest(reportFactorFinanceDay.getAccountOverdueInterest() - delta.paidOverdueInterestDiff);
			reportFactorFinanceDay.setAccountOverdueServiceCharge(reportFactorFinanceDay.getAccountOverdueServiceCharge() - delta.paidOverdueServiceChargeDiff);
			reportFactorFinanceDay.setAccountOverdueExtensionCharge(reportFactorFinanceDay.getAccountOverdueExtensionCharge() - delta.paidOverdueExtensionChargeDiff);
		}
		reportFactorFinanceDayMapper.updateByPrimaryKeySelective(reportFactorFinanceDay);
	}
	
	private void updateNextAnnualReport(ReportFactorFinanceYear reportFactorFinanceYear, Delta delta){
		if(null == reportFactorFinanceYear)
			return;
		reportFactorFinanceYear.setFinanceBalance(reportFactorFinanceYear.getFinanceBalance() + delta.financeBalanceDiff);
		if(delta.flagForYear){
			reportFactorFinanceYear.setAccountPrincipal(reportFactorFinanceYear.getAccountPrincipal() - delta.paidPrincipalDiff);
			reportFactorFinanceYear.setAccountCharge(reportFactorFinanceYear.getAccountCharge() - (delta.paidInterestDiff + delta.paidServiceChargeDiff + delta.paidExtensionChargeDiff 
					+ delta.paidEarlyRepaymentChargeDiff + delta.paidOverduePrincipalPenaltyDiff + delta.paidOverdueInterestPenaltyDiff + delta.paidOverdueServiceChargePenaltyDiff+ delta.paidOtherPenaltyDiff + delta.paidExpenseDiff));
			reportFactorFinanceYear.setAccountInterest(reportFactorFinanceYear.getAccountInterest() - delta.paidInterestDiff);
			reportFactorFinanceYear.setAccountServiceCharge(reportFactorFinanceYear.getAccountServiceCharge() - delta.paidServiceChargeDiff);
			reportFactorFinanceYear.setAccountExtensionCharge(reportFactorFinanceYear.getAccountExtensionCharge() - delta.paidExtensionChargeDiff);
//			reportFactorFinanceYear.setAccountEarlyRepaymentCharge(reportFactorFinanceYear.getPaidEarlyRepaymentCharge() - delta.paidEarlyRepaymentChargeDiff);
			reportFactorFinanceYear.setAccountExpense(reportFactorFinanceYear.getAccountExpense() - delta.paidExpenseDiff);
			reportFactorFinanceYear.setAccountOverduePrincipalPenalty(reportFactorFinanceYear.getAccountOverduePrincipalPenalty() - delta.paidOverduePrincipalPenaltyDiff);
			reportFactorFinanceYear.setAccountOverdueInterestPenalty(reportFactorFinanceYear.getAccountOverdueInterestPenalty() - delta.paidOverdueInterestPenaltyDiff);
			reportFactorFinanceYear.setAccountOverdueServiceChargePenalty(reportFactorFinanceYear.getAccountOverdueServiceChargePenalty() - delta.paidOverdueServiceChargePenaltyDiff);
			reportFactorFinanceYear.setAccountOtherPenalty(reportFactorFinanceYear.getAccountOtherPenalty() - delta.paidOtherPenaltyDiff);
			reportFactorFinanceYear.setAccountOverdue(reportFactorFinanceYear.getAccountOverdue() - (delta.paidOverduePrincipalDiff + delta.paidOverdueInterestDiff
					+ delta.paidOverdueServiceChargeDiff  + delta.paidOverdueExtensionChargeDiff));
			reportFactorFinanceYear.setAccountOverduePrincipal(reportFactorFinanceYear.getAccountOverduePrincipal() - delta.paidOverduePrincipalDiff);
			reportFactorFinanceYear.setAccountOverdueInterest(reportFactorFinanceYear.getAccountOverdueInterest() - delta.paidOverdueInterestDiff);
			reportFactorFinanceYear.setAccountOverdueServiceCharge(reportFactorFinanceYear.getAccountOverdueServiceCharge() - delta.paidOverdueServiceChargeDiff);
			reportFactorFinanceYear.setAccountOverdueExtensionCharge(reportFactorFinanceYear.getAccountOverdueExtensionCharge() - delta.paidOverdueExtensionChargeDiff);
		}
		reportFactorFinanceYearMapper.updateByPrimaryKeySelective(reportFactorFinanceYear);
	}
	
	private void updateCountReport(ReportFactorFinanceCount reportFactorFinanceCount) {
		if(null == reportFactorFinanceCount)
			return;
		reportFactorFinanceCount.setRepaymentItems(reportFactorFinanceCount.getRepaymentItems() + 1);
		reportFactorFinanceCountMapper.updateByPrimaryKeySelective(reportFactorFinanceCount);
	}
	
	private void calculate(LoanInfoChangeEvent event, Delta delta, int repaymentPeriod) throws Exception{
		if(repaymentPeriod == 1){
			delta.financeBalanceDiff += event.getNewSnapshot().getFinanceBalance() - event.getOldSnapshot().getFinanceBalance();
		}
		
		List<RepaymentPlanSnapshot> oldRepaymentPlanSnapshots = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getOldSnapshot().getId());
		List<RepaymentPlanSnapshot> newRepaymentPlanSnapshots = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getNewSnapshot().getId());
		RepaymentPlanSnapshot oldRepaymentPlanSnapshot = oldRepaymentPlanSnapshots.get(repaymentPeriod-1);
		RepaymentPlanSnapshot newRepaymentPlanSnapshot = newRepaymentPlanSnapshots.get(repaymentPeriod-1);
		// 判断是否需要减本金和费用
//		for (RepaymentPlanSnapshot newRepaymentPlanSnapshot : newRepaymentPlanSnapshots) {
		if(event.isAuto()){
			RepaymentPlanSnapshot repaymentPlanSnapshot = newRepaymentPlanSnapshots.get(newRepaymentPlanSnapshot.getRepaymentPeriod() - 1);
			if(DateTimeUtil.date10(new DateTime(repaymentPlanSnapshot.getRepaymentDate())).compareTo(event.getCurDay()) > 0){
				delta.flagForDay = false;
			}
			if(DateTimeUtil.date10(new DateTime(repaymentPlanSnapshot.getRepaymentDate())).substring(0,  4).compareTo(event.getCurYear()) > 0){
				delta.flagForYear = false;
			}
		}
		
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
		delta.paidInterestDiff += newRepaymentPlanSnapshot.getPaidNormalInterest() - oldRepaymentPlanSnapshot.getPaidNormalInterest();
		delta.paidServiceChargeDiff += newRepaymentPlanSnapshot.getPaidNormalServiceCharge() - oldRepaymentPlanSnapshot.getPaidNormalServiceCharge();
		delta.paidEarlyRepaymentChargeDiff += newRepaymentPlanSnapshot.getPaidEarlyRepaymentCharge() - oldRepaymentPlanSnapshot.getPaidEarlyRepaymentCharge();
		delta.paidOverduePrincipalPenaltyDiff += newRepaymentPlanSnapshot.getPaidOverduePrincipalPenalty() - oldRepaymentPlanSnapshot.getPaidOverduePrincipalPenalty();
		delta.paidOverdueInterestPenaltyDiff += newRepaymentPlanSnapshot.getPaidOverdueInterestPenalty() - oldRepaymentPlanSnapshot.getPaidOverdueInterestPenalty();
		delta.paidOverdueServiceChargePenaltyDiff += newRepaymentPlanSnapshot.getPaidOverdueServiceChargePenalty() - oldRepaymentPlanSnapshot.getPaidOverdueServiceChargePenalty();
		delta.paidOtherPenaltyDiff += newRepaymentPlanSnapshot.getPaidOtherPenalty() - oldRepaymentPlanSnapshot.getPaidOtherPenalty();
		
		//已还逾期费用 = 已还逾期本金 + 已还逾期利息 + 已还逾期服务费
//			delta.paidOverdueDiff += newRepaymentPlanSnapshot.getPaidOverduePrincipal() - oldRepaymentPlanSnapshot.getPaidOverduePrincipal()
//							 + newRepaymentPlanSnapshot.getPaidOverdueInterest() - oldRepaymentPlanSnapshot.getPaidOverdueInterest()
//							 + newRepaymentPlanSnapshot.getPaidOverdueServiceCharge() - oldRepaymentPlanSnapshot.getPaidOverdueServiceCharge();
		delta.paidOverduePrincipalDiff += newRepaymentPlanSnapshot.getPaidOverduePrincipal() - oldRepaymentPlanSnapshot.getPaidOverduePrincipal();
		delta.paidOverdueInterestDiff += newRepaymentPlanSnapshot.getPaidOverdueInterest() - oldRepaymentPlanSnapshot.getPaidOverdueInterest();
		delta.paidOverdueServiceChargeDiff += newRepaymentPlanSnapshot.getPaidOverdueServiceCharge() - oldRepaymentPlanSnapshot.getPaidOverdueServiceCharge();
		//应还费用调整
//			delta.accountChargeDiff += newRepaymentPlanSnapshot.getAccountInterest() - oldRepaymentPlanSnapshot.getAccountInterest()
//									+ newRepaymentPlanSnapshot.getAccountServiceCharge() - oldRepaymentPlanSnapshot.getAccountServiceCharge();
		delta.accountInterestDiff += newRepaymentPlanSnapshot.getAccountInterest() - oldRepaymentPlanSnapshot.getAccountInterest();
//			delta.accountServiceChargeDiff += newRepaymentPlanSnapshot.getAccountServiceCharge() - oldRepaymentPlanSnapshot.getAccountServiceCharge();
	 
		//判断展期费用有没有超过展期天数
		DateTime extensionDate = DateTime.parse(newRepaymentPlanSnapshot.getRepaymentDate()).plusDays(newRepaymentPlanSnapshot.getExtensionDays() + 1);
		if(DateTime.parse(event.getCurDay()).compareTo(extensionDate) > 0 ){
			//展期已在今天以前结束,已还展期费用加入已还逾期
//				delta.paidOverdueDiff += newRepaymentPlanSnapshot.getPaidExtensionCharge() - oldRepaymentPlanSnapshot.getPaidExtensionCharge();
			delta.paidOverdueExtensionChargeDiff += newRepaymentPlanSnapshot.getPaidExtensionCharge() - oldRepaymentPlanSnapshot.getPaidExtensionCharge();
		} else {
			//展期未在今天以前结束,已还展期费用加入已还费用
//				delta.paidChargeDiff += newRepaymentPlanSnapshot.getPaidExtensionCharge() - oldRepaymentPlanSnapshot.getPaidExtensionCharge();
			delta.paidExtensionChargeDiff += newRepaymentPlanSnapshot.getPaidExtensionCharge() - oldRepaymentPlanSnapshot.getPaidExtensionCharge();
		}
//		}
		
		
		if(repaymentPeriod == 1){
			List<RepaymentExpenseSnapshot> oldRepaymentExpenseSnapshots = repaymentPlanSnapshotService.getRepaymentExpenseSnapshotByLoanInfoSnapshotId(event.getOldSnapshot().getId());
			List<RepaymentExpenseSnapshot> newRepaymentExpenseSnapshots = repaymentPlanSnapshotService.getRepaymentExpenseSnapshotByLoanInfoSnapshotId(event.getNewSnapshot().getId());
			for (RepaymentExpenseSnapshot newRepaymentExpenseSnapshot : newRepaymentExpenseSnapshots) {
				RepaymentExpenseSnapshot oldRepaymentExpenseSnapshot = oldRepaymentExpenseSnapshots.get(newRepaymentExpenseSnapshots.indexOf(newRepaymentExpenseSnapshot));
				//已还固定费用
				delta.paidExpenseDiff += newRepaymentExpenseSnapshot.getPaidAmount() - oldRepaymentExpenseSnapshot.getPaidAmount();
			}
		}
	}
	
	private boolean isDefaultDeduct(LoanInfoChangeEvent event) {
		String extraData = event.getNewSnapshot().getExtraData();
		return Boolean.valueOf(getExtraData("defaultDeduct", extraData));
	}

	/**
	 * 获取指定key值的自定义数据
	 * @param key
	 * @return
	 */
	private String getExtraData(String key, String extraData) {
		HashMap<String, String> extraDataMap = new HashMap<>();
		if(StringUtils.isNotBlank(extraData))
			extraDataMap = new Gson().fromJson(extraData, new TypeToken<HashMap<String, String>>(){}.getType());
		return extraDataMap.get(key);
	}
	
	private class Delta {
		private long financeBalanceDiff = 0;	//融资余额差额
		private long paidPrincipalDiff = 0;		//已还正常本金
		private long paidInterestDiff = 0;
	    private long paidServiceChargeDiff = 0;
	    private long paidExtensionChargeDiff = 0;
	    private long paidEarlyRepaymentChargeDiff = 0;
	    private long paidOverduePrincipalPenaltyDiff = 0;
	    private long paidOverdueInterestPenaltyDiff = 0;
	    private long paidOverdueServiceChargePenaltyDiff = 0;
	    private long paidOtherPenaltyDiff = 0;
//		private long paidChargeDiff = 0;		//已还费用
		private long paidExpenseDiff = 0;		//已还固定费用
		private long paidOverduePrincipalDiff = 0;
	    private long paidOverdueInterestDiff = 0;
	    private long paidOverdueServiceChargeDiff = 0;
	    private long paidOverdueExtensionChargeDiff = 0;
//		private long paidOverdueDiff = 0;		//已还逾期
	    private long accountInterestDiff = 0;
//		private long accountServiceChargeDiff = 0;
//		private long accountChargeDiff = 0;     //应还费用
		
		private boolean flagForDay = true; // 判断该放款信息在默认账扣的情况下是否需要减本金和费用(日报表)
		private boolean flagForYear = true; // 判断该放款信息在默认账扣的情况下是否需要减本金和费用(年报表)
	}
}
