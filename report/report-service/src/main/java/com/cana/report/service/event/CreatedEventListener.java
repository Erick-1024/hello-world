package com.cana.report.service.event;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.repayment.dao.po.RepaymentPlanSnapshot;
import com.cana.report.dao.po.ReportFactorFinanceCount;
import com.cana.report.dao.po.ReportFactorFinanceDay;
import com.cana.report.dao.po.ReportFactorFinanceYear;
import com.cana.report.service.transaction.IReportTransactionService;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.report.enums.BusinessProduct;
import com.cana.vbam.common.report.enums.ReportType;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class CreatedEventListener extends AbstractEventListener {
	
	private static final Logger logger = LoggerFactory.getLogger(CreatedEventListener.class);
	
	private IReportTransactionService reportTransactionService = SpringApplicationContext.getApplicationContext().getBean(IReportTransactionService.class);

	@Override
	public void doExecute(LoanInfoChangeEvent event) throws Exception {
		logger.info("融资报表生成定时任务 - 执行 - 放款信息[id：{}]新建处理器", event.getNewSnapshot().getLoanInfoId());
//		initReportForNewUser(event);
		Delta delta = this.new Delta();
		calculate(event, delta);
		updateFactorReport(event, delta);
		updateFinanceReport(event, delta);
		updateCoreCompanyReport(event, delta);
		updateCanaReport(event, delta);
		logger.info("融资报表生成定时任务 - 状态 - 放款信息[id：{}]新建处理器处理完成，追踪至[版本号：{}]", event.getNewSnapshot().getLoanInfoId(), event.getNewSnapshot().getCurrentVersion());
	}
	
	private void updateFactorReport(LoanInfoChangeEvent event, Delta delta) throws Exception{
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getNewSnapshot().getBusinessProductId())){
			businessProductId = event.getNewSnapshot().getBusinessProductId();
		}
		//更新保理商的日报表
		ReportFactorFinanceDay reportFactorDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getFactorId(), businessProductId);
		updateDailyReport(reportFactorDay, delta, event, UserType.FACTOR, event.getNewSnapshot().getFactorId());
		//更新保理商的年报表
		ReportFactorFinanceYear reportFactorYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getFactorId(), businessProductId);
		updateAnnualReport(reportFactorYear, delta, event, UserType.FACTOR, event.getNewSnapshot().getFactorId());
		//更新保理商的计数报表
		ReportFactorFinanceCount reportFactorCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), event.getNewSnapshot().getFactorId(), businessProductId);
		updateCountReport(reportFactorCount, event, UserType.FACTOR, event.getNewSnapshot().getFactorId());
	}
	
	private void updateCanaReport(LoanInfoChangeEvent event, Delta delta) throws Exception {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getNewSnapshot().getBusinessProductId())){
			businessProductId = event.getNewSnapshot().getBusinessProductId();
		}
		String canaId = userApi.getCanaId();
		//更新cana的日报表
		ReportFactorFinanceDay reportCanaDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), canaId, businessProductId);
		updateDailyReport(reportCanaDay, delta, event, UserType.CANA, canaId);
		//更新cana的年报表
		ReportFactorFinanceYear reportCanaYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), canaId, businessProductId);
		updateAnnualReport(reportCanaYear, delta, event, UserType.CANA, canaId);
		//更新cana的计数报表
		ReportFactorFinanceCount reportCanaCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), canaId, businessProductId);
		updateCountReport(reportCanaCount, event, UserType.CANA, canaId);
	}
	
	private void updateFinanceReport(LoanInfoChangeEvent event, Delta delta) throws Exception {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getNewSnapshot().getBusinessProductId())){
			businessProductId = event.getNewSnapshot().getBusinessProductId();
		}
		//如果放款信息的融资客户存在
		if(StringUtils.isNotBlank(event.getNewSnapshot().getFinanceId())) {
			//更新融资客户的日报表
			ReportFactorFinanceDay reportFinanceDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateDailyReport(reportFinanceDay, delta, event, UserType.FINACE, event.getNewSnapshot().getFinanceId());
			//更新融资客户的年报表
			ReportFactorFinanceYear reportFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateAnnualReport(reportFinanceYear, delta, event, UserType.FINACE, event.getNewSnapshot().getFinanceId());
			//更新融资客户的计数报表
			ReportFactorFinanceCount reportFinanceCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), event.getNewSnapshot().getFinanceId(), businessProductId);
			updateCountReport(reportFinanceCount, event, UserType.FINACE, event.getNewSnapshot().getFinanceId());
		}
	}
	
	private void updateCoreCompanyReport(LoanInfoChangeEvent event, Delta delta) throws Exception {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getNewSnapshot().getBusinessProductId())){
			businessProductId = event.getNewSnapshot().getBusinessProductId();
		}
		if(StringUtils.isNoneBlank(event.getNewSnapshot().getCoreCompanyId())) {
			//更新核心企业的日报表
			ReportFactorFinanceDay reportFactorDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateDailyReport(reportFactorDay, delta, event, UserType.CORECOMPANY, event.getNewSnapshot().getCoreCompanyId());
			//更新核心企业的年报表
			ReportFactorFinanceYear reportFactorYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateAnnualReport(reportFactorYear, delta, event, UserType.CORECOMPANY, event.getNewSnapshot().getCoreCompanyId());
			//更新核心企业的计数报表
			ReportFactorFinanceCount reportFactorCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), event.getNewSnapshot().getCoreCompanyId(), businessProductId);
			updateCountReport(reportFactorCount, event, UserType.CORECOMPANY, event.getNewSnapshot().getCoreCompanyId());
		}
	}
	
	private void updateCountReport(ReportFactorFinanceCount reportFactorFinanceCount, LoanInfoChangeEvent event, UserType userType, String ownerId) throws Exception {
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getNewSnapshot().getBusinessProductId())){
			businessProductId = event.getNewSnapshot().getBusinessProductId();
		}
		if(null == reportFactorFinanceCount){
			initReport(ReportType.COUNT, businessProductId, userType, ownerId);
			reportFactorFinanceCount = reportFactorFinanceCountLockMapper.lockCountReport(event.getCurDay(), ownerId, businessProductId);
		}
		reportFactorFinanceCount.setLoanItems(reportFactorFinanceCount.getLoanItems() + 1);
		reportFactorFinanceCountMapper.updateByPrimaryKeySelective(reportFactorFinanceCount);
	}
	
	private void updateDailyReport(ReportFactorFinanceDay reportFactorFinanceDay, Delta delta, LoanInfoChangeEvent event, UserType userType, String ownerId) throws Exception{
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getNewSnapshot().getBusinessProductId())){
			businessProductId = event.getNewSnapshot().getBusinessProductId();
		}
		if(null == reportFactorFinanceDay){
			initReport(ReportType.DAILY, businessProductId, userType, ownerId);
			reportFactorFinanceDay = reportFactorFinanceDayLockMapper.lockDailyReport(event.getCurDay(), ownerId, businessProductId);
		}
		reportFactorFinanceDay.setFinanceAmount(reportFactorFinanceDay.getFinanceAmount() + delta.financeAmount);
		reportFactorFinanceDay.setFinanceBalance(reportFactorFinanceDay.getFinanceBalance() + delta.financeBalance);
		reportFactorFinanceDay.setAccountPrincipal(reportFactorFinanceDay.getAccountPrincipal() + delta.dailyAccountPrincipalDiff);
		reportFactorFinanceDay.setAccountCharge(reportFactorFinanceDay.getAccountCharge() + delta.dailyAccountInterestDiff + delta.dailyAccountServiceChargeDiff);
		reportFactorFinanceDay.setAccountInterest(reportFactorFinanceDay.getAccountInterest() + delta.dailyAccountInterestDiff);
		reportFactorFinanceDay.setAccountServiceCharge(reportFactorFinanceDay.getAccountServiceCharge() + delta.dailyAccountServiceChargeDiff);
		reportFactorFinanceDayMapper.updateByPrimaryKeySelective(reportFactorFinanceDay);
	}
	
	private void updateAnnualReport(ReportFactorFinanceYear reportFactorFinanceYear, Delta delta, LoanInfoChangeEvent event, UserType userType, String ownerId) throws Exception{
		String businessProductId = BusinessProduct.other.name();
		if(StringUtils.isNotBlank(event.getNewSnapshot().getBusinessProductId())){
			businessProductId = event.getNewSnapshot().getBusinessProductId();
		}
		if(null == reportFactorFinanceYear){
			initReport(ReportType.ANNUAL, businessProductId, userType, ownerId);
			reportFactorFinanceYear = reportFactorFinanceYearLockMapper.lockAnnualReport(event.getCurYear(), ownerId, businessProductId);
		}
		reportFactorFinanceYear.setFinanceAmount(reportFactorFinanceYear.getFinanceAmount() + delta.financeAmount);
		reportFactorFinanceYear.setFinanceBalance(reportFactorFinanceYear.getFinanceBalance() + delta.financeBalance);
		reportFactorFinanceYear.setAccountPrincipal(reportFactorFinanceYear.getAccountPrincipal() + delta.annualAccountPrincipalDiff);
		reportFactorFinanceYear.setAccountCharge(reportFactorFinanceYear.getAccountCharge() + delta.annualAccountInterestDiff + delta.annualAccountServiceChargeDiff);
		reportFactorFinanceYear.setAccountInterest(reportFactorFinanceYear.getAccountInterest() + delta.annualAccountInterestDiff);
		reportFactorFinanceYear.setAccountServiceCharge(reportFactorFinanceYear.getAccountServiceCharge() + delta.annualAccountServiceChargeDiff);
		reportFactorFinanceYearMapper.updateByPrimaryKeySelective(reportFactorFinanceYear);
	}
	
//	private void initReportForNewUser(LoanInfoChangeEvent event) throws Exception{
//		ReportFactorFinanceDayExample factorExample = new ReportFactorFinanceDayExample();
//		factorExample.createCriteria().andReportDateEqualTo(event.getCurDay()).andOwnerIdEqualTo(event.getNewSnapshot().getFactorId());
//		List<ReportFactorFinanceDay> reportFactorDays = reportFactorFinanceDayMapper.selectByExample(factorExample);
//		if(CollectionUtils.isEmpty(reportFactorDays)) {
//			initReport(event.getNewSnapshot().getFactorId(), UserType.FACTOR.name());
//		}
//		if(StringUtils.isNoneBlank(event.getNewSnapshot().getFinanceId())){
//			ReportFactorFinanceDayExample financeExample = new ReportFactorFinanceDayExample();
//			financeExample.createCriteria().andReportDateEqualTo(event.getCurDay()).andOwnerIdEqualTo(event.getNewSnapshot().getFactorId());
//			List<ReportFactorFinanceDay> reportFinanceDays = reportFactorFinanceDayMapper.selectByExample(financeExample);
//			if(CollectionUtils.isEmpty(reportFinanceDays)) {
//				initReport(event.getNewSnapshot().getFinanceId(), UserType.FINACE.name());
//			}
//		}
//	}
	
	private void calculate(LoanInfoChangeEvent event, Delta delta) {
		if(event.isAuto()){
			delta.financeAmount = event.getNewSnapshot().getFinanceAmount();
			delta.financeBalance = event.getNewSnapshot().getFinanceBalance();
			List<RepaymentPlanSnapshot> newRepaymentPlanSnapshots = repaymentPlanSnapshotService.getRepaymentPlanSnapshotByLoanInfoSnapshotId(event.getNewSnapshot().getId());
			for(RepaymentPlanSnapshot newRepaymentPlanSnapshot:newRepaymentPlanSnapshots){
				//当还款日为当天时,计算应还
				if(event.getCurDay().equals(newRepaymentPlanSnapshot.getRepaymentDate())) {
					
					//当日应还本金
					delta.dailyAccountPrincipalDiff += newRepaymentPlanSnapshot.getAccountPrincipal();
					
					//当日应还费用 = 当日应还利息 + 当日应还服务费
//					delta.dailyAccountChargeDiff += newRepaymentPlanSnapshot.getAccountInterest() + newRepaymentPlanSnapshot.getAccountServiceCharge();
					delta.dailyAccountInterestDiff += newRepaymentPlanSnapshot.getAccountInterest();
					delta.dailyAccountServiceChargeDiff += newRepaymentPlanSnapshot.getAccountServiceCharge();
				}
				//当还款日为当年时，计算应还
				if(event.getCurYear().equals(newRepaymentPlanSnapshot.getRepaymentDate().substring(0, 4))) {
					
					//当年应还本金
					delta.annualAccountPrincipalDiff += newRepaymentPlanSnapshot.getAccountPrincipal();
					
					//当年应还费用 = 当年应还利息 + 当年应还服务费
//					delta.annualAccountChargeDiff += newRepaymentPlanSnapshot.getAccountInterest() + newRepaymentPlanSnapshot.getAccountServiceCharge();
					delta.annualAccountInterestDiff += newRepaymentPlanSnapshot.getAccountInterest();
					delta.annualAccountServiceChargeDiff += newRepaymentPlanSnapshot.getAccountServiceCharge();
				}
			}
		}else{
			delta.financeAmount = event.getNewSnapshot().getFinanceAmount();
			delta.financeBalance = event.getNewSnapshot().getFinanceBalance();
		}
	}
	
//	private void initReport(String userId, String userType) throws Exception{
//		String curDay = CanaDateUtils.date10();
//		String curYear = CanaDateUtils.getCurrentYear();
//		ReportFactorFinanceDay dailyReport = new ReportFactorFinanceDay();
//		dailyReport.setId(reportTransactionService.generateReportFactorFinanceDayId());
//		dailyReport.setOwnerId(userId);
//		dailyReport.setReportDate(curDay);
//		dailyReport.setUserType(userType);
//		dailyReport.setCreateTime(new Date());
//		reportFactorFinanceDayMapper.insertSelective(dailyReport);
//		
//		ReportFactorFinanceYear annualReport = new ReportFactorFinanceYear();
//		annualReport.setId(reportTransactionService.generateReportFactorFinanceYearId());
//		annualReport.setCreateTime(new Date());
//		annualReport.setReportDate(curYear);
//		annualReport.setOwnerId(userId);
//		annualReport.setUserType(userType);
//		reportFactorFinanceYearMapper.insertSelective(annualReport);
//		
//		ReportFactorFinanceCount countReport = new ReportFactorFinanceCount();
//		countReport.setId(reportTransactionService.generateReportFactorFinanceCountId());
//		countReport.setOwnerId(userId);
//		countReport.setReportDate(curDay);
//		countReport.setUserType(userType);
//		countReport.setCreateTime(new Date());
//		reportFactorFinanceCountMapper.insertSelective(countReport);
//	}
	
	private void initReport(ReportType reportType, String businessProduct, UserType userType, String ownerId) throws Exception{
//		BusinessProduct businessProduct = BusinessProduct.other;
//		if(StringUtils.isNotBlank(event.getNewSnapshot().getBusinessProductId())){
//			businessProduct = BusinessProduct.valueOf(event.getNewSnapshot().getBusinessProductId());
//		}
		reportTransactionService.initReport(reportType, businessProduct, ownerId, userType);
	}
	
	private class Delta {
		private long financeAmount = 0;		//融资金额
		private long financeBalance = 0;	//融资余额
		
		private long dailyAccountPrincipalDiff = 0;		//当日应还本金
		private long dailyAccountInterestDiff = 0;
		private long dailyAccountServiceChargeDiff = 0;
//		private long dailyAccountChargeDiff = 0;		//当日应还费用
		private long annualAccountPrincipalDiff = 0;	//当年应还本金
		private long annualAccountInterestDiff = 0;
		private long annualAccountServiceChargeDiff = 0;
//		private long annualAccountChargeDiff = 0;		//当年应还费用
	}
	
}
