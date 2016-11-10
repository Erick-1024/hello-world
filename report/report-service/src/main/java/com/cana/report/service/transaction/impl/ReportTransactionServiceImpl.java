package com.cana.report.service.transaction.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.common.dao.mapper.gen.PropertiesMapper;
import com.cana.common.dao.po.Properties;
import com.cana.credit.api.ICreditApi;
import com.cana.flight.finance.api.IFlightFinanceApi;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.service.IRepaymentLoanInfoSnapshotService;
import com.cana.repayment.service.IRepaymentReportTaskService;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentLoanInfoSnapshotBO;
import com.cana.report.dao.mapper.ReportFactorFinanceCountLockMapper;
import com.cana.report.dao.mapper.ReportFactorFinanceDayLockMapper;
import com.cana.report.dao.mapper.ReportFactorFinanceYearLockMapper;
import com.cana.report.dao.mapper.ReportLoanInfoChangeTraceLockMapper;
import com.cana.report.dao.mapper.gen.ReportFactorFinanceCountMapper;
import com.cana.report.dao.mapper.gen.ReportFactorFinanceDayMapper;
import com.cana.report.dao.mapper.gen.ReportFactorFinanceYearMapper;
import com.cana.report.dao.mapper.gen.ReportLoanInfoChangeTraceMapper;
import com.cana.report.dao.mapper.gen.ReportMonitorDataMapper;
import com.cana.report.dao.po.ReportFactorFinanceCount;
import com.cana.report.dao.po.ReportFactorFinanceDay;
import com.cana.report.dao.po.ReportFactorFinanceYear;
import com.cana.report.dao.po.ReportLoanInfoChangeTrace;
import com.cana.report.dao.po.ReportMonitorData;
import com.cana.report.service.IReportMonitorService;
import com.cana.report.service.event.LoanInfoChangeEvent;
import com.cana.report.service.event.LoanInfoChangeEventListenerRegistry;
import com.cana.report.service.transaction.IReportTransactionService;
import com.cana.report.service.util.IReportServiceHelper;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.repayment.enums.InputMethod;
import com.cana.vbam.common.repayment.enums.LoanInfoChangeType;
import com.cana.vbam.common.repayment.message.dto.RepaymentSuccessMessage;
import com.cana.vbam.common.report.enums.BusinessProduct;
import com.cana.vbam.common.report.enums.ReportMonitorDataType;
import com.cana.vbam.common.report.enums.ReportType;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

@Service
public class ReportTransactionServiceImpl implements IReportTransactionService {
	private static Logger logger = LoggerFactory.getLogger(ReportTransactionServiceImpl.class);

	@Resource
	private SequenceGenerator seqGen;

	@Resource
	private ReportLoanInfoChangeTraceMapper reportLoanInfoChangeTraceMapper;

	@Resource
	private ReportLoanInfoChangeTraceLockMapper reportLoanInfoChangeTraceLockMapper;

	@Resource
	private IRepaymentLoanInfoSnapshotService repaymentLoanInfoSnapshotService;

	@Resource
	private ReportFactorFinanceDayLockMapper reportFactorFinanceDayLockMapper;

	@Resource
	private ReportFactorFinanceYearLockMapper reportFactorFinanceYearLockMapper;
	
	@Resource
	private ReportFactorFinanceCountLockMapper reportFactorFinanceCountLockMapper;

	@Resource
	private ReportFactorFinanceDayMapper reportFactorFinanceDayMapper;

	@Resource
	private ReportFactorFinanceYearMapper reportFactorFinanceYearMapper;
	
	@Resource
	private ReportFactorFinanceCountMapper reportFactorFinanceCountMapper;

	@Resource
	private IRepaymentReportTaskService repaymentReportTaskService;

	@Resource
	private IVbamCommonService vbamCommonService;
	
	@Resource
	private IReportServiceHelper serviceHelper;
	
	@Resource
	private ReportMonitorDataMapper monitorDataMapper;
	
	@Resource
	private PropertiesMapper propertiesMapper;
	
	@Resource
	private RepaymentLoanInfoMapper repaymentLoanInfoMapper;

	@Resource
	private ICreditApi creditApiImpl;
	
	@Resource
	private IFlightFinanceApi flightFinanceApiImpl;
	
	@Resource
	private IReportMonitorService reportMonitorServiceImpl;
	

	@Override
	public void processLoanInfoChangeTask(String loanInfoId) throws Exception {
		// 对放款信息的追踪记录 进行加锁 查询
		ReportLoanInfoChangeTrace trace = reportLoanInfoChangeTraceLockMapper.lockReportLoanInfoChangeTraceById(loanInfoId);
		RepaymentLoanInfoBO repaymentLoanInfoBO = new RepaymentLoanInfoBO(loanInfoId);
		List<LoanInfoChangeEvent> events = generateChangeEvents(repaymentLoanInfoBO, trace);

		if (CollectionUtils.isEmpty(events)) {
			logger.info("融资报表生成定时任务 - 状态 - 放款信息[id：{}]不需要处理", loanInfoId);
			return;
		}
		logger.info("融资报表生成定时任务 - 执行 - 放款信息[id：{}]获取处理器", loanInfoId);
		for (LoanInfoChangeEvent event : events)
			LoanInfoChangeEventListenerRegistry.getListener(event.getType()).execute(event);
		
		if (null == trace)
			throw new RuntimeException("放款信息的追踪记录不存在！");
		else
			updateReportLoanInfoChangeTrace(repaymentLoanInfoBO, trace);
		logger.info("融资报表生成定时任务 - 状态 - 放款信息[id：{}]报表已生成，更新追踪记录表，追踪到的版本号为：{}", loanInfoId, repaymentLoanInfoBO.getCurrentVersion());
	}

	private void updateReportLoanInfoChangeTrace(RepaymentLoanInfoBO repaymentLoanInfoBO,
			ReportLoanInfoChangeTrace trace) throws RuntimeException{
		ReportLoanInfoChangeTrace newTrace = reportLoanInfoChangeTraceLockMapper.lockReportLoanInfoChangeTraceById(repaymentLoanInfoBO.getId());
		if(!StringUtils.equals(trace.getLastTraceVersion(), newTrace.getLastTraceVersion())){
			throw new RuntimeException("放款信息追踪记录发生了改变，可能该放款信息的变更已经生成了报表");
		}
		trace.setLastTraceVersion(repaymentLoanInfoBO.getCurrentVersion());
		reportLoanInfoChangeTraceMapper.updateByPrimaryKeySelective(trace);
	}

	/**
	 * 产生变更事件
	 * 
	 * @param repaymentLoanInfoBO
	 * @param trace
	 * @return
	 */
	private List<LoanInfoChangeEvent> generateChangeEvents(RepaymentLoanInfoBO loanInfoBO,
			ReportLoanInfoChangeTrace trace) {
		List<LoanInfoChangeEvent> events = new ArrayList<>();
		RepaymentLoanInfoSnapshotBO currentLoanInfoSnapshot = new RepaymentLoanInfoSnapshotBO(
				repaymentLoanInfoSnapshotService.getRepaymentLoanInfoSnapshotByLoanInfoIdAndCurrentVersion(
						loanInfoBO.getId(), loanInfoBO.getCurrentVersion()));
		while (currentLoanInfoSnapshot != null && (!currentLoanInfoSnapshot.getCurrentVersion().equals(trace.getLastTraceVersion()))) {
			LoanInfoChangeEvent event = new LoanInfoChangeEvent();
			event.setType(LoanInfoChangeType.valueOf(currentLoanInfoSnapshot.getChangeType()));
			event.setNewSnapshot(currentLoanInfoSnapshot);
			event.setOldSnapshot(currentLoanInfoSnapshot.lazyLoadLastSnapshot());
			event.setCurDay(getCurrentDay());
			event.setCurYear(getCurrentYear());
			event.setLastDay(getLastDay());
			event.setLastYear(getLastYear());
			if(StringUtils.equals(currentLoanInfoSnapshot.getInputMethod(), InputMethod.AUTO.name())){
				event.setAuto(true);
			}else{
				event.setAuto(false);
			}
			events.add(event);
			currentLoanInfoSnapshot = currentLoanInfoSnapshot.lazyLoadLastSnapshot();
		}
		Collections.reverse(events);
		return events;
	}

	@Override
	public void initReportDayAndYear(Map<String, List<String>> allUserIds) throws Exception {
		Map<String, List<String>> listsDay = new HashMap<>();
		Map<String, List<String>> listsYear = new HashMap<>();
		Map<String, List<String>> listsCount = new HashMap<>();
		for(BusinessProduct businessProduct:BusinessProduct.values()){
			List<String> listDay = reportFactorFinanceDayLockMapper.getAllFactorAndFinanceIdOfCurrentDayAndBusinessProduct(getCurrentDay(), businessProduct.name());
			List<String> listYear = reportFactorFinanceYearLockMapper.getAllFactorAndFinanceIdOfCurrentYearAndBusinessProduct(getCurrentYear(), businessProduct.name());
			List<String> listCount = reportFactorFinanceCountLockMapper.getAllFactorAndFinanceIdOfCurrentDayAndBusinessProduct(getCurrentDay(), businessProduct.name());
			listsDay.put(businessProduct.name(), listDay);
			listsYear.put(businessProduct.name(), listYear);
			listsCount.put(businessProduct.name(), listCount);
		}
		logger.info("融资报表初始化定时任务 - 执行 - 开始执行初始化保理商报表任务");
		for (String factorId : allUserIds.get(UserType.FACTOR.name())) {
			Map<String, Delta> deltaMap = new HashMap<>();
			Map<String, Delta> deltaMapYear = new HashMap<>();
			List<RepaymentLoanInfoBO> loanInfos = repaymentReportTaskService.getLoanInfoIdsByFactorIdOrFinanceId(factorId);//该用户所有的放款信息
			calculate(loanInfos, deltaMap);
			calculateForAnnualReport(loanInfos, deltaMapYear);
			for(String businessProductId : deltaMap.keySet()){
				if(!listsDay.get(businessProductId).contains(factorId)){
					// 为该用户初始化日报表
					ReportFactorFinanceDay reportToday = getBasicInfoForDailyReport(factorId, UserType.FACTOR.name());
					reportToday.setBusinessProductId(businessProductId);
					initDailyReport(reportToday, deltaMap.get(businessProductId));
				}
				if(!listsYear.get(businessProductId).contains(factorId)){
					// 为该用户初始化年报表
					ReportFactorFinanceYear reportYear = getBasicInfoForAnnualReport(factorId, UserType.FACTOR.name());
					reportYear.setBusinessProductId(businessProductId);
					initAnnualReport(reportYear, deltaMapYear.get(businessProductId));
				}
				if(!listsCount.get(businessProductId).contains(factorId)){
					// 为该用户初始化计数报表
					initReportCountForUser(factorId, UserType.FACTOR.name(), businessProductId);
				}
			}
		}
		logger.info("融资报表初始化定时任务 - 执行 - 开始执行初始化融资客户报表任务");
		for (String financeId : allUserIds.get(UserType.FINACE.name())) {
			Map<String, Delta> deltaMap = new HashMap<>();
			Map<String, Delta> deltaMapYear = new HashMap<>();
			List<RepaymentLoanInfoBO> loanInfos = repaymentReportTaskService.getLoanInfoIdsByFactorIdOrFinanceId(financeId);//该用户所有的放款信息
			calculate(loanInfos, deltaMap);
			calculateForAnnualReport(loanInfos, deltaMapYear);
			for(String businessProductId : deltaMap.keySet()){
				if(!listsDay.get(businessProductId).contains(financeId)){
					// 为该用户初始化日报表
					ReportFactorFinanceDay reportToday = getBasicInfoForDailyReport(financeId, UserType.FINACE.name());
					reportToday.setBusinessProductId(businessProductId);
					initDailyReport(reportToday, deltaMap.get(businessProductId));
				}
				if(!listsYear.get(businessProductId).contains(financeId)){
					// 为该用户初始化年报表
					ReportFactorFinanceYear reportYear = getBasicInfoForAnnualReport(financeId, UserType.FINACE.name());
					reportYear.setBusinessProductId(businessProductId);
					initAnnualReport(reportYear, deltaMapYear.get(businessProductId));
				}
				if(!listsCount.get(businessProductId).contains(financeId)){
					// 为该用户初始化计数报表
					initReportCountForUser(financeId, UserType.FINACE.name(), businessProductId);
				}
			}
		}
		logger.info("融资报表初始化定时任务 - 执行 - 开始执行初始化核心企业报表任务");
		for(String coreCompanyId : allUserIds.get(UserType.CORECOMPANY.name())){
			List<RepaymentLoanInfoBO> loanInfos = repaymentReportTaskService.getLoanInfoIdsByFactorIdOrFinanceId(coreCompanyId);//该用户所有的放款信息
			Map<String, Delta> deltaMap = new HashMap<>();
			Map<String, Delta> deltaMapYear = new HashMap<>();
			calculate(loanInfos, deltaMap);
			calculateForAnnualReport(loanInfos, deltaMapYear);
			for(String businessProductId : deltaMap.keySet()){
				if(!listsDay.get(businessProductId).contains(coreCompanyId)){
					// 为该用户初始化日报表
					ReportFactorFinanceDay reportToday = getBasicInfoForDailyReport(coreCompanyId, UserType.CORECOMPANY.name());
					reportToday.setBusinessProductId(businessProductId);
					initDailyReport(reportToday, deltaMap.get(businessProductId));
				}
				if(!listsYear.get(businessProductId).contains(coreCompanyId)){
					// 为该用户初始化年报表
					ReportFactorFinanceYear reportYear = getBasicInfoForAnnualReport(coreCompanyId, UserType.CORECOMPANY.name());
					reportYear.setBusinessProductId(businessProductId);
					initAnnualReport(reportYear, deltaMapYear.get(businessProductId));
				}
				if(!listsCount.get(businessProductId).contains(coreCompanyId)){
					// 为该用户初始化计数报表
					initReportCountForUser(coreCompanyId, UserType.CORECOMPANY.name(), businessProductId);
				}
			}
		}
		logger.info("融资报表初始化定时任务 - 执行 - 开始执行初始化CANA报表任务");
		for(BusinessProduct businessProduct:BusinessProduct.values()){
			for (String canaId : allUserIds.get(UserType.CANA.name())) {
				if(!listsDay.get(businessProduct.name()).contains(canaId)){
					// 初始化CANA日报表
					initCanaReportDay(canaId, businessProduct);
				}
				if (!listsYear.get(businessProduct.name()).contains(canaId)){
					initCanaReportYear(canaId, businessProduct);
				}
				if (!listsCount.get(businessProduct.name()).contains(canaId)){
					initReportCountForUser(canaId, UserType.CANA.name(), businessProduct.name());
				}
			}
		}
		
		// 修改 初始化报表任务 的完成结果
		vbamCommonService.markInitReportTaskDone();
	}
	
	/**
	 * 对日报表基础字段赋值
	 * @param userId
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	private ReportFactorFinanceDay getBasicInfoForDailyReport(String userId, String userType) throws Exception {
		ReportFactorFinanceDay reportToday = new ReportFactorFinanceDay();
		reportToday.setId(generateReportFactorFinanceDayId());
		reportToday.setCreateTime(new Date());
		reportToday.setReportDate(getCurrentDay());
		reportToday.setOwnerId(userId);
		reportToday.setUserType(userType);
		return reportToday;
	}
	
	/**
	 * 对年报表基础字段赋值
	 * @param userId
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	private ReportFactorFinanceYear getBasicInfoForAnnualReport(String userId, String userType) throws Exception {
		ReportFactorFinanceYear reportYear = new ReportFactorFinanceYear();
		reportYear.setId(generateReportFactorFinanceDayId());
		reportYear.setCreateTime(new Date());
		reportYear.setReportDate(getCurrentYear());
		reportYear.setOwnerId(userId);
		reportYear.setUserType(userType);
		return reportYear;
	}
	
	private void calculate(List<RepaymentLoanInfoBO> loanInfos, Map<String, Delta> deltaMap){
		for(RepaymentLoanInfoBO loanInfoBO : loanInfos) {
			List<RepaymentPlan> repaymentPlans = new ArrayList<>();// 固定还款日为今天的还款计划
			List<RepaymentExpense> repaymentExpenses = new ArrayList<>();// 固定还款日为今日或之前且未结清的费用列表
			List<RepaymentPlan> overduePlans = new ArrayList<>();//已过展期的还款计划
			List<RepaymentPlan> extensionPlans = new ArrayList<>();//未过展期的还款计划
			String businessProductId = BusinessProduct.other.name();
			if(StringUtils.isNotBlank(loanInfoBO.getBusinessProductId())){
				businessProductId = loanInfoBO.getBusinessProductId();
			}
			Delta delta = deltaMap.get(businessProductId);
			if(null == delta){
				delta = new Delta();
			}
			// 获取还款日为今日的还款计划
			repaymentPlans.addAll(loanInfoBO.normalPlansOnRepaymentDate(vbamCommonService.getCurrentDate()));
			for(RepaymentPlan repaymentPlan : repaymentPlans) {
				// 当日应还本金：固定还款日为今天的还款计划的应还本金之和 + 展期中的还款计划本金
				delta.accountPrincipal = MoneyArithUtil.addLong(delta.accountPrincipal, repaymentPlan.getAccountPrincipal()); 
				// 当日应还费用：固定还款日为今天的还款计划的利息 和 服务费 + 未过展期的还款计划的应还展期费用 + 罚息 + 展期中的还款计划利息和服务费
				delta.accountCharge = MoneyArithUtil.addLong(delta.accountCharge, repaymentPlan.getAccountInterest());
				delta.accountInterest = MoneyArithUtil.addLong(delta.accountInterest, repaymentPlan.getAccountInterest());
				delta.accountCharge = MoneyArithUtil.addLong(delta.accountCharge, repaymentPlan.getAccountServiceCharge());
				delta.accountServiceCharge = MoneyArithUtil.addLong(delta.accountServiceCharge, repaymentPlan.getAccountServiceCharge());
			}
			// 固定还款日为今日或之前且未结清的费用列表
			repaymentExpenses.addAll(loanInfoBO.unpaidExpenses(vbamCommonService.getCurrentDate()));
			for(RepaymentExpense repaymentExpense : repaymentExpenses) {
				// 当日应还费用：固定还款日为今天的还款计划的利息 和 服务费 + 未过展期的还款计划的应还展期费用 + 罚息 + 展期中的还款计划利息和服务费
				delta.accountExpense = MoneyArithUtil.addLong(delta.accountExpense, repaymentExpense.getRepaymentAmount());
				delta.accountCharge = MoneyArithUtil.addLong(delta.accountCharge, repaymentExpense.getRepaymentAmount());
			}
			// 逾期的还款计划
			overduePlans.addAll(loanInfoBO.overduePlans(vbamCommonService.getCurrentDate()));
			for(RepaymentPlan repaymentPlan:overduePlans){
				// 当日应还逾期：该用户对应的所有的放款信息对应的所有的还款计划 中的 逾期本金+利息+服务费 之和 + 已过展期的还款计划的应还展期费用
				delta.accountOverdue = MoneyArithUtil.addLong(delta.accountOverdue, repaymentPlan.getAccountExtensionCharge());
				delta.accountOverdueExtensionCharge = MoneyArithUtil.addLong(delta.accountOverdueExtensionCharge, repaymentPlan.getAccountExtensionCharge());
				// 当日应还逾期：该用户对应的所有的放款信息对应的所有的还款计划 中的 逾期本金+利息+服务费 之和 + 已过展期的还款计划的应还展期费用
				delta.accountOverdue = MoneyArithUtil.addLong(delta.accountOverdue, repaymentPlan.getOverduePrincipal());
				delta.accountOverduePrincipal = MoneyArithUtil.addLong(delta.accountOverduePrincipal, repaymentPlan.getOverduePrincipal());
				delta.accountOverdue = MoneyArithUtil.addLong(delta.accountOverdue, repaymentPlan.getOverdueInterest());
				delta.accountOverdueInterest = MoneyArithUtil.addLong(delta.accountOverdueInterest, repaymentPlan.getOverdueInterest());
				delta.accountOverdue = MoneyArithUtil.addLong(delta.accountOverdue, repaymentPlan.getOverdueServiceCharge());
				delta.accountOverdueServiceCharge = MoneyArithUtil.addLong(delta.accountOverdueServiceCharge, repaymentPlan.getOverdueServiceCharge());
				// 当日应还费用：固定还款日为今天的还款计划的利息 和 服务费 + 未过展期的还款计划的应还展期费用 + 罚息 + 展期中的还款计划利息和服务费
				delta.accountCharge = MoneyArithUtil.addLong(delta.accountCharge, repaymentPlan.getOtherPenalty());
				delta.accountOtherPenalty = MoneyArithUtil.addLong(delta.accountOtherPenalty, repaymentPlan.getOtherPenalty());
				delta.accountCharge = MoneyArithUtil.addLong(delta.accountCharge, repaymentPlan.getOverduePrincipalPenalty());
				delta.accountOverduePrincipalPenalty = MoneyArithUtil.addLong(delta.accountOverduePrincipalPenalty, repaymentPlan.getOverduePrincipalPenalty());
				delta.accountCharge = MoneyArithUtil.addLong(delta.accountCharge, repaymentPlan.getOverdueInterestPenalty());
				delta.accountOverdueInterestPenalty = MoneyArithUtil.addLong(delta.accountOverdueInterestPenalty, repaymentPlan.getOverdueInterestPenalty());
				delta.accountCharge = MoneyArithUtil.addLong(delta.accountCharge, repaymentPlan.getOverdueServiceChargePenalty());
				delta.accountOverdueServiceChargePenalty = MoneyArithUtil.addLong(delta.accountOverdueServiceChargePenalty, repaymentPlan.getOverdueServiceChargePenalty());
			}
			// 未过展期的还款计划
			extensionPlans.addAll(loanInfoBO.extensionPlans(vbamCommonService.getCurrentDate()));
			for(RepaymentPlan repaymentPlan:extensionPlans){
				// 当日应还费用：固定还款日为今天的还款计划的利息 和 服务费 + 未过展期的还款计划的应还展期费用 + 罚息 + 展期中的还款计划利息和服务费
				delta.accountCharge = MoneyArithUtil.addLong(delta.accountCharge, repaymentPlan.getAccountExtensionCharge());
				delta.accountExtensionCharge = MoneyArithUtil.addLong(delta.accountExtensionCharge, repaymentPlan.getAccountExtensionCharge());
				delta.accountCharge = MoneyArithUtil.addLong(delta.accountCharge, repaymentPlan.getAccountInterest());
				delta.accountInterest = MoneyArithUtil.addLong(delta.accountInterest, repaymentPlan.getAccountInterest());
				delta.accountCharge = MoneyArithUtil.addLong(delta.accountCharge, repaymentPlan.getAccountServiceCharge());
				delta.accountServiceCharge = MoneyArithUtil.addLong(delta.accountServiceCharge, repaymentPlan.getAccountServiceCharge());
				// 当日应还本金：固定还款日为今天的还款计划的应还本金之和 + 展期中的还款计划本金
				delta.accountPrincipal = MoneyArithUtil.addLong(delta.accountPrincipal, repaymentPlan.getAccountPrincipal());
			}
			// 融资余额：所有放款信的融资余额之和
			delta.financeBalance = MoneyArithUtil.addLong(delta.financeBalance, loanInfoBO.getFinanceBalance());
			deltaMap.put(businessProductId, delta);
		}
	}
	
	private void calculateForAnnualReport(List<RepaymentLoanInfoBO> loanInfos, Map<String, Delta> deltaMap) throws Exception{
		for (RepaymentLoanInfoBO loanInfo : loanInfos) {
			List<RepaymentPlan> repaymentPlans = new ArrayList<>();// 固定还款日为今年的还款计划 		
			List<RepaymentExpense> repaymentExpenses = new ArrayList<>();// 固定还款日为今年或之前且未结清的费用列表 		
			List<RepaymentPlan> overduePlans = new ArrayList<>();// 已过展期的还款计划 		
			List<RepaymentPlan> extensionPlans = new ArrayList<>();//未过展期的还款计划 
			String businessProductId = BusinessProduct.other.name();
			if(StringUtils.isNoneBlank(loanInfo.getBusinessProductId())){
				businessProductId = loanInfo.getBusinessProductId();
			}
			Delta delta = deltaMap.get(businessProductId);
			if(null == delta){
				delta = new Delta();
			}
			// 获取还款日为今年的还款计划
			List<RepaymentPlan> plans = repaymentReportTaskService.getRepaymentPlanByLoanInfoIdAndCurrentYear(loanInfo.getId(), getCurrentYear());		
			if(CollectionUtils.isNotEmpty(plans)){
				repaymentPlans.addAll(plans);		
				for(RepaymentPlan repaymentPlan : repaymentPlans) {
					// 当年应还本金：固定还款日为今年的还款计划的应还本金之和 + 展期中的还款计划本金
					delta.accountPrincipalForAnnual = MoneyArithUtil.addLong(delta.accountPrincipalForAnnual, repaymentPlan.getAccountPrincipal()); 
					// 当年应还费用：固定还款日为今年的还款计划的利息 和 服务费 + 未过展期的还款计划的应还展期费用 + 罚息 + 展期中的还款计划利息和服务费
					delta.accountChargeForAnnual = MoneyArithUtil.addLong(delta.accountChargeForAnnual, repaymentPlan.getAccountInterest());
					delta.accountInterestForAnnual= MoneyArithUtil.addLong(delta.accountInterestForAnnual, repaymentPlan.getAccountInterest());
					delta.accountChargeForAnnual = MoneyArithUtil.addLong(delta.accountChargeForAnnual, repaymentPlan.getAccountServiceCharge());
					delta.accountServiceChargeForAnnual = MoneyArithUtil.addLong(delta.accountServiceChargeForAnnual, repaymentPlan.getAccountServiceCharge());
				}
			}
			// 获取固定还款日为今年或之前且未结清的费用列表
			List<RepaymentExpense> expenses = repaymentReportTaskService.getRepaymentExpenseBeforeOrEqualsCurrentYearAndUnsettle(loanInfo.getId(), getCurrentYear());		
			if(CollectionUtils.isNotEmpty(expenses)){
				repaymentExpenses.addAll(expenses);		
				for(RepaymentExpense repaymentExpense : repaymentExpenses) {
					// 当年应还费用：固定还款日为今年的还款计划的利息 和 服务费 + 未过展期的还款计划的应还展期费用 + 罚息 + 展期中的还款计划利息和服务费
					delta.accountExpenseForAnnual = MoneyArithUtil.addLong(delta.accountExpenseForAnnual, repaymentExpense.getRepaymentAmount());
					delta.accountChargeForAnnual = MoneyArithUtil.addLong(delta.accountChargeForAnnual, repaymentExpense.getRepaymentAmount());
				}
			}
			// 逾期的还款计划
			overduePlans.addAll(loanInfo.overduePlans(vbamCommonService.getCurrentDate()));
			for(RepaymentPlan repaymentPlan:overduePlans){
				// 当年应还费用：固定还款日为今年的还款计划的利息 和 服务费 + 未过展期的还款计划的应还展期费用 + 罚息 + 展期中的还款计划利息和服务费
				delta.accountChargeForAnnual = MoneyArithUtil.addLong(delta.accountChargeForAnnual, repaymentPlan.getOtherPenalty());
				delta.accountOtherPenaltyForAnnual = MoneyArithUtil.addLong(delta.accountOtherPenaltyForAnnual, repaymentPlan.getOtherPenalty());
				delta.accountChargeForAnnual = MoneyArithUtil.addLong(delta.accountChargeForAnnual, repaymentPlan.getOverduePrincipalPenalty());
				delta.accountOverduePrincipalPenaltyForAnnual = MoneyArithUtil.addLong(delta.accountOverduePrincipalPenaltyForAnnual, repaymentPlan.getOverduePrincipalPenalty());
				delta.accountChargeForAnnual = MoneyArithUtil.addLong(delta.accountChargeForAnnual, repaymentPlan.getOverdueInterestPenalty());
				delta.accountOverdueInterestPenaltyForAnnual = MoneyArithUtil.addLong(delta.accountOverdueInterestPenaltyForAnnual, repaymentPlan.getOverdueInterestPenalty());
				delta.accountChargeForAnnual = MoneyArithUtil.addLong(delta.accountChargeForAnnual, repaymentPlan.getOverdueServiceChargePenalty());
				delta.accountOverdueServiceChargePenaltyForAnnual = MoneyArithUtil.addLong(delta.accountOverdueServiceChargePenaltyForAnnual, repaymentPlan.getOverdueServiceChargePenalty());
				delta.accountOverduePrincipal = MoneyArithUtil.addLong(delta.accountOverduePrincipal, repaymentPlan.getOverduePrincipal());
				delta.accountOverdueInterest = MoneyArithUtil.addLong(delta.accountOverdueInterest, repaymentPlan.getOverdueInterest());
				delta.accountOverdueServiceCharge = MoneyArithUtil.addLong(delta.accountOverdueServiceCharge, repaymentPlan.getOverdueServiceCharge());
				delta.accountOverdueExtensionChargeForAnnual = MoneyArithUtil.addLong(delta.accountOverdueExtensionChargeForAnnual, repaymentPlan.getAccountExtensionCharge());
			}
			// 未过展期的还款计划
			extensionPlans.addAll(loanInfo.extensionPlans(vbamCommonService.getCurrentDate()));
			for(RepaymentPlan repaymentPlan:extensionPlans){
				// 当年应还费用：固定还款日为今年的还款计划的利息 和 服务费 + 未过展期的还款计划的应还展期费用 + 罚息 + 展期中的还款计划利息和服务费
				delta.accountChargeForAnnual = MoneyArithUtil.addLong(delta.accountChargeForAnnual, repaymentPlan.getAccountExtensionCharge());
				delta.accountExtensionChargeForAnnual = MoneyArithUtil.addLong(delta.accountExtensionChargeForAnnual, repaymentPlan.getAccountExtensionCharge());
				delta.accountChargeForAnnual = MoneyArithUtil.addLong(delta.accountChargeForAnnual, repaymentPlan.getAccountInterest());
				delta.accountInterestForAnnual = MoneyArithUtil.addLong(delta.accountInterestForAnnual, repaymentPlan.getAccountInterest());
				delta.accountChargeForAnnual = MoneyArithUtil.addLong(delta.accountChargeForAnnual, repaymentPlan.getAccountServiceCharge());
				delta.accountServiceCharge = MoneyArithUtil.addLong(delta.accountServiceCharge, repaymentPlan.getAccountServiceCharge());
				// 当年应还本金：固定还款日为今年的还款计划的应还本金之和 + 展期中的还款计划本金
				delta.accountPrincipalForAnnual = MoneyArithUtil.addLong(delta.accountPrincipalForAnnual, repaymentPlan.getAccountPrincipal());
			}
			delta.financeBalance = MoneyArithUtil.addLong(delta.financeBalance, loanInfo.getFinanceBalance());
			deltaMap.put(businessProductId, delta);
		}
	}
	
	/**
	 * 初始化日报表
	 * @param reportToday
	 * @param delta
	 * @throws Exception
	 */
	private void initDailyReport(ReportFactorFinanceDay reportToday, Delta delta) throws Exception {
		 try {
			 // 融资余额:所有放款信的融资余额之和
			 reportToday.setFinanceBalance(delta.financeBalance);
			 // 当日应还本金:固定还款日为今天的还款计划的应还本金之和
			 reportToday.setAccountPrincipal(delta.accountPrincipal);
			 // 当日应还费用
			 reportToday.setAccountCharge(delta.accountCharge);
			 reportToday.setAccountInterest(delta.accountInterest);
			 reportToday.setAccountServiceCharge(delta.accountServiceCharge);
			 reportToday.setAccountExtensionCharge(delta.accountExtensionCharge);
			 reportToday.setAccountOverduePrincipalPenalty(delta.accountOverduePrincipalPenalty);
			 reportToday.setAccountOverdueInterestPenalty(delta.accountOverdueInterestPenalty);
			 reportToday.setAccountOverdueServiceChargePenalty(delta.accountOverdueServiceChargePenalty);
			 reportToday.setAccountOtherPenalty(delta.accountOtherPenalty);
			 // 当日应还固定费用:固定还款日为之前，且未还清的固定费用之和 + 固定还款日为今日的固定费用之和
			 reportToday.setAccountExpense(delta.accountExpense);
			 // 当日应还逾期:该用户对应的所有的放款信息对应的所有的还款计划 中的 逾期本金+利息+服务费 之和 + 已过展期的还款计划的应还展期费用
			 reportToday.setAccountOverdue(delta.accountOverdue);
			 reportToday.setAccountOverduePrincipal(delta.accountOverduePrincipal);
			 reportToday.setAccountOverdueInterest(delta.accountOverdueInterest);
			 reportToday.setAccountOverdueServiceCharge(delta.accountOverdueServiceCharge);
			 reportToday.setAccountOverdueExtensionCharge(delta.accountOverdueExtensionCharge);
			 // 放款金额：0
			 // 当日已还本金：0
			 // 当日已还费用：0
			 // 当日已还固定：0
			 // 当日已还逾期：0
			 // 当日调账金额：0
			 reportFactorFinanceDayMapper.insertSelective(reportToday);
		 } catch (Exception e) {
			 logger.error("融资报表初始化定时任务 - 状态 - 初始化用户[id：{}]日报表任务失败，异常原因：", reportToday.getOwnerId(), e);
			 throw e;
		 }
	 }
	

	/**
	 * 初始化年报表
	 * @param reportYear
	 * @param delta
	 * @throws Exception
	 */
	private void initAnnualReport(ReportFactorFinanceYear reportYear, Delta delta) throws Exception {
		try {
			// 融资余额:所有放款信的融资余额之和
			reportYear.setFinanceBalance(delta.financeBalance);
			// 当年应还本金:固定还款日为今天的还款计划的应还本金之和
			reportYear.setAccountPrincipal(delta.accountPrincipalForAnnual);
			// 当年应还费用:
			reportYear.setAccountCharge(delta.accountChargeForAnnual);
			reportYear.setAccountInterest(delta.accountInterestForAnnual);
			reportYear.setAccountServiceCharge(delta.accountServiceChargeForAnnual);
			reportYear.setAccountExtensionCharge(delta.accountExtensionChargeForAnnual);
			reportYear.setAccountOverduePrincipalPenalty(delta.accountOverduePrincipalPenaltyForAnnual);
			reportYear.setAccountOverdueInterestPenalty(delta.accountOverdueInterestPenaltyForAnnual);
			reportYear.setAccountOverdueServiceChargePenalty(delta.accountOverdueServiceChargePenaltyForAnnual);
			reportYear.setAccountOtherPenalty(delta.accountOtherPenaltyForAnnual);
			// 当年应还固定费用:固定还款日为之前，且未还清的固定费用之和 + 固定还款日为今日的固定费用之和
			reportYear.setAccountExpense(delta.accountExpenseForAnnual);
			// 当年应还逾期:该用户对应的所有的放款信息对应的所有的还款计划 中的 逾期本金+利息+服务费 之和 + 已过展期的还款计划的应还展期费用
			reportYear.setAccountOverdue(delta.accountOverdue);
			reportYear.setAccountOverduePrincipal(delta.accountOverduePrincipal);
			reportYear.setAccountOverdueInterest(delta.accountOverdueInterest);
			reportYear.setAccountOverdueServiceCharge(delta.accountOverdueServiceCharge);
			reportYear.setAccountOverdueExtensionCharge(delta.accountOverdueExtensionChargeForAnnual);
			// 放款金额：0
			// 当年已还本金：0
			// 当年已还费用：0
			// 当年已还固定：0
			// 当年已还逾期：0
			// 累计逾期金额：0
			// 累计展期本金：0
			// 调账金额
			reportFactorFinanceYearMapper.insertSelective(reportYear);
		} catch (Exception e) {
			logger.error("融资报表初始化定时任务 - 状态 - 初始化用户[id：{}]年报表任务失败，异常原因：", reportYear.getOwnerId(), e);
			throw e;
		}
	}
	
	/**
	 * 初始化计数报表
	 * @param userId
	 * @param userType
	 * @throws Exception
	 */
	private void initReportCountForUser(String userId, String userType, String businessProductId) throws Exception {
		try {
			ReportFactorFinanceCount reportFactorFinanceCount = new ReportFactorFinanceCount();
			reportFactorFinanceCount.setId(generateReportFactorFinanceCountId());
			reportFactorFinanceCount.setOwnerId(userId);
			reportFactorFinanceCount.setReportDate(getCurrentDay());
			reportFactorFinanceCount.setUserType(userType);
			reportFactorFinanceCount.setBusinessProductId(businessProductId);
			reportFactorFinanceCount.setCreateTime(new Date());
			reportFactorFinanceCount.setBusinessProductId(businessProductId);
			reportFactorFinanceCountMapper.insertSelective(reportFactorFinanceCount);
		} catch (Exception e) {
			logger.error("融资报表初始化定时任务 - 状态 - 初始化用户[id：{}]计数报表任务失败，异常原因：", userId, e);
			throw e;
		}
	}

	private class Delta {
		private long financeBalance = 0;
		private long accountPrincipal = 0;
		private long accountInterest = 0;
		private long accountServiceCharge = 0;
		private long accountExtensionCharge = 0;
		private long accountOverduePrincipalPenalty = 0;
	    private long accountOverdueInterestPenalty = 0;
	    private long accountOverdueServiceChargePenalty = 0;
	    private long accountOtherPenalty;
		private long accountCharge = 0;
		private long accountExpense = 0;
		private long accountOverduePrincipal = 0;
		private long accountOverdueInterest = 0;
		private long accountOverdueServiceCharge = 0;
	    private long accountOverdueExtensionCharge = 0;
		private long accountOverdue = 0;

		private long accountPrincipalForAnnual = 0;
		private long accountInterestForAnnual = 0;
		private long accountServiceChargeForAnnual = 0;
		private long accountExtensionChargeForAnnual = 0;
//		private long accountExtensionChargeForAnnual = 0;
//		private long accountOverdueInterestForAnnual = 0;
//		private long accountOverdueServiceChargeForAnnual = 0;
		private long accountOverdueExtensionChargeForAnnual = 0;
		private long accountOverduePrincipalPenaltyForAnnual = 0;
	    private long accountOverdueInterestPenaltyForAnnual = 0;
	    private long accountOverdueServiceChargePenaltyForAnnual = 0;
	    private long accountOtherPenaltyForAnnual;
		private long accountChargeForAnnual = 0;
		private long accountExpenseForAnnual = 0;
	}
	
	
	@Override
	public String generateReportFactorFinanceDayId() throws Exception {
		return DateTimeUtil.datetime14() + seqGen.getNextSeq(Constants.REPORT_FACTOR_FINANCE_DAY_ID, 4);
	}

	@Override
	public String generateReportFactorFinanceYearId() throws Exception {
		return DateTimeUtil.datetime14() + seqGen.getNextSeq(Constants.REPORT_FACTOR_FINANCE_YEAR_ID, 4);
	}
	
	@Override
	public String generateReportFactorFinanceCountId() throws Exception {
		return DateTimeUtil.datetime14() + seqGen.getNextSeq(Constants.REPORT_FACTOR_FINANCE_COUNT_ID, 4);
	}

	/**
	 * 初始化Cana日报表
	 */
	private void initCanaReportDay(String canaId, BusinessProduct businessProduct) throws Exception {
		try {
			// Cana当天的日报表
			ReportFactorFinanceDay canaReportDay = reportFactorFinanceDayLockMapper.lockDailyReport(getCurrentDay(), canaId, businessProduct.name());
			List<ReportFactorFinanceDay> allFactorReportDay = reportFactorFinanceDayLockMapper.
					getAllFactorReportDayOfCurrentDayAndBusinessProduct(getCurrentDay(), businessProduct.name());
			if (null == canaReportDay) {
				canaReportDay = new ReportFactorFinanceDay();
				canaReportDay.setId(generateReportFactorFinanceDayId());
				canaReportDay.setCreateTime(new Date());
				canaReportDay.setReportDate(getCurrentDay());
				canaReportDay.setOwnerId(canaId);
				canaReportDay.setUserType(UserType.CANA.name());
				canaReportDay.setBusinessProductId(businessProduct.name());
				calculateCanaReportDay(canaReportDay, allFactorReportDay);
				reportFactorFinanceDayMapper.insertSelective(canaReportDay);
			} else {
				calculateCanaReportDay(canaReportDay, allFactorReportDay);
				reportFactorFinanceDayMapper.updateByPrimaryKeySelective(canaReportDay);
			}
		} catch (Exception e) {
			logger.error("融资报表初始化定时任务 - 状态 - 初始化用户[id：{}]日报表任务失败，异常原因：", canaId, e);
			throw e;
		}
	}

	/**
	 * 对Cana日报表的字段的进行赋值
	 * 
	 * @param canaReportDay
	 * @param allFactorReportDay
	 * @throws Exception
	 */
	private void calculateCanaReportDay(ReportFactorFinanceDay canaReportDay, List<ReportFactorFinanceDay> allFactorReportDay) throws Exception {
		for (ReportFactorFinanceDay day : allFactorReportDay) {
			canaReportDay.setFinanceBalance(MoneyArithUtil.addLong(canaReportDay.getFinanceBalance(), day.getFinanceBalance()));
			canaReportDay.setFinanceAmount(MoneyArithUtil.addLong(canaReportDay.getFinanceAmount(), day.getFinanceAmount()));
			canaReportDay.setAccountPrincipal(MoneyArithUtil.addLong(canaReportDay.getAccountPrincipal(), day.getAccountPrincipal()));
			canaReportDay.setAccountInterest(MoneyArithUtil.addLong(canaReportDay.getAccountInterest(), day.getAccountInterest()));
			canaReportDay.setAccountServiceCharge(MoneyArithUtil.addLong(canaReportDay.getAccountServiceCharge(), day.getAccountServiceCharge()));
			canaReportDay.setAccountExtensionCharge(MoneyArithUtil.addLong(canaReportDay.getAccountExtensionCharge(), day.getAccountExtensionCharge()));
			canaReportDay.setAccountExpense(MoneyArithUtil.addLong(canaReportDay.getAccountExpense(), day.getAccountExpense()));
			canaReportDay.setAccountOverduePrincipal(MoneyArithUtil.addLong(canaReportDay.getAccountOverduePrincipal(), day.getAccountOverduePrincipal()));
			canaReportDay.setAccountOverdueInterest(MoneyArithUtil.addLong(canaReportDay.getAccountOverdueInterest(), day.getAccountOverdueInterest()));
			canaReportDay.setAccountOverdueServiceCharge(MoneyArithUtil.addLong(canaReportDay.getAccountOverdueServiceCharge(), day.getAccountOverdueServiceCharge()));
			canaReportDay.setAccountOverdueExtensionCharge(MoneyArithUtil.addLong(canaReportDay.getAccountOverdueExtensionCharge(), day.getAccountOverdueExtensionCharge()));
			canaReportDay.setAccountOverduePrincipalPenalty(MoneyArithUtil.addLong(canaReportDay.getAccountOverduePrincipalPenalty(), day.getAccountOverduePrincipalPenalty()));
			canaReportDay.setAccountOverdueInterestPenalty(MoneyArithUtil.addLong(canaReportDay.getAccountOverdueInterestPenalty(), day.getAccountOverdueInterestPenalty()));
			canaReportDay.setAccountOverdueServiceChargePenalty(MoneyArithUtil.addLong(canaReportDay.getAccountOverdueServiceChargePenalty(), day.getAccountOverdueServiceChargePenalty()));
			canaReportDay.setAccountOtherPenalty(MoneyArithUtil.addLong(canaReportDay.getAccountOtherPenalty(), day.getAccountOtherPenalty()));
			canaReportDay.setAccountCharge(MoneyArithUtil.addLong(canaReportDay.getAccountCharge(), day.getAccountCharge()));
			canaReportDay.setAccountOverdue(MoneyArithUtil.addLong(canaReportDay.getAccountOverdue(), day.getAccountOverdue()));
			canaReportDay.setPaidPrincipal(MoneyArithUtil.addLong(canaReportDay.getPaidPrincipal(), day.getPaidPrincipal()));
			canaReportDay.setPaidInterest(MoneyArithUtil.addLong(canaReportDay.getPaidInterest(), day.getPaidInterest()));
			canaReportDay.setPaidServiceCharge(MoneyArithUtil.addLong(canaReportDay.getPaidServiceCharge(), day.getPaidServiceCharge()));
			canaReportDay.setPaidExtensionCharge(MoneyArithUtil.addLong(canaReportDay.getPaidExtensionCharge(), day.getPaidExtensionCharge()));
			canaReportDay.setPaidEarlyRepaymentCharge(MoneyArithUtil.addLong(canaReportDay.getPaidEarlyRepaymentCharge(), day.getPaidEarlyRepaymentCharge()));
			canaReportDay.setPaidExpense(MoneyArithUtil.addLong(canaReportDay.getPaidExpense(), day.getPaidExpense()));
			canaReportDay.setPaidOverduePrincipal(MoneyArithUtil.addLong(canaReportDay.getPaidOverduePrincipal(), day.getPaidOverduePrincipal()));
			canaReportDay.setPaidOverdueInterest(MoneyArithUtil.addLong(canaReportDay.getPaidOverdueInterest(), day.getPaidOverdueInterest()));
			canaReportDay.setPaidOverdueServiceCharge(MoneyArithUtil.addLong(canaReportDay.getPaidOverdueServiceCharge(), day.getPaidOverdueServiceCharge()));
			canaReportDay.setPaidOverdueExtensionCharge(MoneyArithUtil.addLong(canaReportDay.getPaidOverdueExtensionCharge(), day.getPaidOverdueExtensionCharge()));
			canaReportDay.setPaidOverduePrincipalPenalty(MoneyArithUtil.addLong(canaReportDay.getPaidOverduePrincipalPenalty(), day.getPaidOverduePrincipalPenalty()));
			canaReportDay.setPaidOverdueInterestPenalty(MoneyArithUtil.addLong(canaReportDay.getPaidOverdueInterestPenalty(), day.getPaidOverdueInterestPenalty()));
			canaReportDay.setPaidOverdueServiceChargePenalty(MoneyArithUtil.addLong(canaReportDay.getPaidOverdueServiceChargePenalty(), day.getPaidOverdueServiceChargePenalty()));
			canaReportDay.setPaidOtherPenalty(MoneyArithUtil.addLong(canaReportDay.getPaidOtherPenalty(), day.getPaidOtherPenalty()));
			canaReportDay.setPaidCharge(MoneyArithUtil.addLong(canaReportDay.getPaidCharge(), day.getPaidCharge()));
			canaReportDay.setPaidExpense(MoneyArithUtil.addLong(canaReportDay.getPaidExpense(), day.getPaidExpense()));
			canaReportDay.setPaidOverdue(MoneyArithUtil.addLong(canaReportDay.getPaidOverdue(), day.getPaidOverdue()));
			
			canaReportDay.setAdjustPrincipal(MoneyArithUtil.addLong(canaReportDay.getAdjustPrincipal(), day.getAdjustPrincipal()));
			canaReportDay.setAdjustInterest(MoneyArithUtil.addLong(canaReportDay.getAdjustInterest(), day.getAdjustInterest()));
			canaReportDay.setAdjustServiceCharge(MoneyArithUtil.addLong(canaReportDay.getAdjustServiceCharge(), day.getAdjustServiceCharge()));
			canaReportDay.setAdjustExtension(MoneyArithUtil.addLong(canaReportDay.getAdjustExtension(), day.getAdjustExtension()));
			canaReportDay.setAdjustExpense(MoneyArithUtil.addLong(canaReportDay.getAdjustExpense(), day.getAdjustExpense()));
			canaReportDay.setAdjustOverdueInterest(MoneyArithUtil.addLong(canaReportDay.getAdjustOverdueInterest(), day.getAdjustOverdueInterest()));
			canaReportDay.setAdjustOverdueServiceCharge(MoneyArithUtil.addLong(canaReportDay.getAdjustOverdueServiceCharge(), day.getAdjustOverdueServiceCharge()));
			canaReportDay.setAdjustPenalty(MoneyArithUtil.addLong(canaReportDay.getAdjustPenalty(), day.getAdjustPenalty()));
			
			
			
			
			canaReportDay.setAdjustAmount(MoneyArithUtil.addLong(canaReportDay.getAdjustAmount(), day.getAdjustAmount()));
		}
	}

	/**
	 * 初始化Cana年报表
	 */
	private void initCanaReportYear(String canaId, BusinessProduct businessProduct) throws Exception {
		try {
			// Cana当年的年报表
			ReportFactorFinanceYear canaReportYear = reportFactorFinanceYearLockMapper.lockAnnualReport(getCurrentYear(), canaId, businessProduct.name());
			List<ReportFactorFinanceYear> allFactorReportYear = reportFactorFinanceYearLockMapper.
					getAllFactorReportYearOfCurrentYearAndBusinessProduct(getCurrentYear(), businessProduct.name());
			if (null == canaReportYear) {
				canaReportYear = new ReportFactorFinanceYear();
				canaReportYear.setId(generateReportFactorFinanceYearId());
				canaReportYear.setCreateTime(new Date());
				canaReportYear.setReportDate(getCurrentYear());
				canaReportYear.setOwnerId(canaId);
				canaReportYear.setUserType(UserType.CANA.name());
				canaReportYear.setBusinessProductId(businessProduct.name());
				calculateCanaReportYear(canaReportYear, allFactorReportYear);
				reportFactorFinanceYearMapper.insertSelective(canaReportYear);
			} else {
				calculateCanaReportYear(canaReportYear, allFactorReportYear);
				reportFactorFinanceYearMapper.updateByPrimaryKeySelective(canaReportYear);
			}
		} catch (Exception e) {
			logger.error("融资报表初始化定时任务 - 状态 - 初始化用户[id：{}]年报表任务失败，异常原因：", canaId, e);
			throw e;
		}
	}

	/**
	 * 对Cana年报表的字段的进行赋值
	 * 
	 * @param canaReportYear
	 * @param allFactorReportYear
	 * @throws Exception
	 */
	private void calculateCanaReportYear(ReportFactorFinanceYear canaReportYear, List<ReportFactorFinanceYear> allFactorReportYear) throws Exception {
		for (ReportFactorFinanceYear year : allFactorReportYear) {
			canaReportYear.setFinanceBalance(MoneyArithUtil.addLong(canaReportYear.getFinanceBalance(), year.getFinanceBalance()));
			canaReportYear.setFinanceAmount(MoneyArithUtil.addLong(canaReportYear.getFinanceAmount(), year.getFinanceAmount()));
			canaReportYear.setAccountPrincipal(MoneyArithUtil.addLong(canaReportYear.getAccountPrincipal(), year.getAccountPrincipal()));
			canaReportYear.setAccountInterest(MoneyArithUtil.addLong(canaReportYear.getAccountInterest(), year.getAccountInterest()));
			canaReportYear.setAccountServiceCharge(MoneyArithUtil.addLong(canaReportYear.getAccountServiceCharge(), year.getAccountServiceCharge()));
			canaReportYear.setAccountExtensionCharge(MoneyArithUtil.addLong(canaReportYear.getAccountExtensionCharge(), year.getAccountExtensionCharge()));
			canaReportYear.setAccountExpense(MoneyArithUtil.addLong(canaReportYear.getAccountExpense(), year.getAccountExpense()));
			canaReportYear.setAccountOverduePrincipal(MoneyArithUtil.addLong(canaReportYear.getAccountOverduePrincipal(), year.getAccountOverduePrincipal()));
			canaReportYear.setAccountOverdueInterest(MoneyArithUtil.addLong(canaReportYear.getAccountOverdueInterest(), year.getAccountOverdueInterest()));
			canaReportYear.setAccountOverdueServiceCharge(MoneyArithUtil.addLong(canaReportYear.getAccountOverdueServiceCharge(), year.getAccountOverdueServiceCharge()));
			canaReportYear.setAccountOverdueExtensionCharge(MoneyArithUtil.addLong(canaReportYear.getAccountOverdueExtensionCharge(), year.getAccountOverdueExtensionCharge()));
			canaReportYear.setAccountOverduePrincipalPenalty(MoneyArithUtil.addLong(canaReportYear.getAccountOverduePrincipalPenalty(), year.getAccountOverduePrincipalPenalty()));
			canaReportYear.setAccountOverdueInterestPenalty(MoneyArithUtil.addLong(canaReportYear.getAccountOverdueInterestPenalty(), year.getAccountOverdueInterestPenalty()));
			canaReportYear.setAccountOverdueServiceChargePenalty(MoneyArithUtil.addLong(canaReportYear.getAccountOverdueServiceChargePenalty(), year.getAccountOverdueServiceChargePenalty()));
			canaReportYear.setAccountOtherPenalty(MoneyArithUtil.addLong(canaReportYear.getAccountOtherPenalty(), year.getAccountOtherPenalty()));
			canaReportYear.setAccountCharge(MoneyArithUtil.addLong(canaReportYear.getAccountCharge(), year.getAccountCharge()));
			canaReportYear.setAccountOverdue(MoneyArithUtil.addLong(canaReportYear.getAccountOverdue(), year.getAccountOverdue()));
			canaReportYear.setPaidPrincipal(MoneyArithUtil.addLong(canaReportYear.getPaidPrincipal(), year.getPaidPrincipal()));
			canaReportYear.setPaidInterest(MoneyArithUtil.addLong(canaReportYear.getPaidInterest(), year.getPaidInterest()));
			canaReportYear.setPaidServiceCharge(MoneyArithUtil.addLong(canaReportYear.getPaidServiceCharge(), year.getPaidServiceCharge()));
			canaReportYear.setPaidExtensionCharge(MoneyArithUtil.addLong(canaReportYear.getPaidExtensionCharge(), year.getPaidExtensionCharge()));
			canaReportYear.setPaidEarlyRepaymentCharge(MoneyArithUtil.addLong(canaReportYear.getPaidEarlyRepaymentCharge(), year.getPaidEarlyRepaymentCharge()));
			canaReportYear.setPaidExpense(MoneyArithUtil.addLong(canaReportYear.getPaidExpense(), year.getPaidExpense()));
			canaReportYear.setPaidOverduePrincipal(MoneyArithUtil.addLong(canaReportYear.getPaidOverduePrincipal(), year.getPaidOverduePrincipal()));
			canaReportYear.setPaidOverdueInterest(MoneyArithUtil.addLong(canaReportYear.getPaidOverdueInterest(), year.getPaidOverdueInterest()));
			canaReportYear.setPaidOverdueServiceCharge(MoneyArithUtil.addLong(canaReportYear.getPaidOverdueServiceCharge(), year.getPaidOverdueServiceCharge()));
			canaReportYear.setPaidOverdueExtensionCharge(MoneyArithUtil.addLong(canaReportYear.getPaidOverdueExtensionCharge(), year.getPaidOverdueExtensionCharge()));
			canaReportYear.setPaidOverduePrincipalPenalty(MoneyArithUtil.addLong(canaReportYear.getPaidOverduePrincipalPenalty(), year.getPaidOverduePrincipalPenalty()));
			canaReportYear.setPaidOverdueInterestPenalty(MoneyArithUtil.addLong(canaReportYear.getPaidOverdueInterestPenalty(), year.getPaidOverdueInterestPenalty()));
			canaReportYear.setPaidOverdueServiceChargePenalty(MoneyArithUtil.addLong(canaReportYear.getPaidOverdueServiceChargePenalty(), year.getPaidOverdueServiceChargePenalty()));
			canaReportYear.setPaidOtherPenalty(MoneyArithUtil.addLong(canaReportYear.getPaidOtherPenalty(), year.getPaidOtherPenalty()));
			canaReportYear.setPaidCharge(MoneyArithUtil.addLong(canaReportYear.getPaidCharge(), year.getPaidCharge()));
			canaReportYear.setPaidExpense(MoneyArithUtil.addLong(canaReportYear.getPaidExpense(), year.getPaidExpense()));
			canaReportYear.setPaidOverdue(MoneyArithUtil.addLong(canaReportYear.getPaidOverdue(), year.getPaidOverdue()));
			canaReportYear.setTotalOverdue(MoneyArithUtil.addLong(canaReportYear.getTotalOverdue(), year.getTotalOverdue()));
			canaReportYear.setTotalExtension(MoneyArithUtil.addLong(canaReportYear.getTotalExtension(), year.getTotalExtension()));
			canaReportYear.setAdjustPrincipal(MoneyArithUtil.addLong(canaReportYear.getAdjustPrincipal(), year.getAdjustPrincipal()));
			canaReportYear.setAdjustInterest(MoneyArithUtil.addLong(canaReportYear.getAdjustInterest(), year.getAdjustInterest()));
			canaReportYear.setAdjustServiceCharge(MoneyArithUtil.addLong(canaReportYear.getAdjustServiceCharge(), year.getAdjustServiceCharge()));
			canaReportYear.setAdjustExtension(MoneyArithUtil.addLong(canaReportYear.getAdjustExtension(), year.getAdjustExtension()));
			canaReportYear.setAdjustExpense(MoneyArithUtil.addLong(canaReportYear.getAdjustExpense(), year.getAdjustExpense()));
			canaReportYear.setAdjustOverdueInterest(MoneyArithUtil.addLong(canaReportYear.getAdjustOverdueInterest(), year.getAdjustOverdueInterest()));
			canaReportYear.setAdjustOverdueServiceCharge(MoneyArithUtil.addLong(canaReportYear.getAdjustOverdueServiceCharge(), year.getAdjustOverdueServiceCharge()));
			canaReportYear.setAdjustPenalty(MoneyArithUtil.addLong(canaReportYear.getAdjustPenalty(), year.getAdjustPenalty()));
			canaReportYear.setAdjustAmount(MoneyArithUtil.addLong(canaReportYear.getAdjustAmount(), year.getAdjustAmount()));
		}
	}

	/**
	 * 获取当天日期
	 * 
	 * @return
	 */
	private String getCurrentDay() {
		return vbamCommonService.getCurrentDate();
	}

	private String getLastDay() {
		return DateTime.parse(getCurrentDay()).minusDays(1).toString("yyyy-MM-dd");
	}

	private String getCurrentYear() {
		return vbamCommonService.getCurrentDate().substring(0, 4);
	}

	private String getLastYear() {
		return DateTime.parse(getCurrentYear()).minusYears(1).toString("yyyy");
	}

	@Override
	public void initLoanInfoChangeTrace(List<String> loanInfoIds) throws Exception {
		for(String loanInfoId : loanInfoIds){
			ReportLoanInfoChangeTrace reportLoanInfoChangeTrace = reportLoanInfoChangeTraceMapper.selectByPrimaryKey(loanInfoId);
			if(null == reportLoanInfoChangeTrace){
				reportLoanInfoChangeTrace = new ReportLoanInfoChangeTrace();
				reportLoanInfoChangeTrace.setLoanInfoId(loanInfoId);
				reportLoanInfoChangeTraceMapper.insertSelective(reportLoanInfoChangeTrace);
				logger.info("融资报表生成定时任务 - 状态 - 放款信息[id：{}]生成追踪记录成功", loanInfoId);
			}
		}
	}

	@Override
	public void processRepaymentSuccessMessage(RepaymentSuccessMessage message) throws Exception {
		
		String messageId = getMessageId(message);
		Properties txRecord = propertiesMapper.selectByPrimaryKey(messageId);
		if(txRecord == null){
			vbamCommonService.addProperties(messageId, "true");
		}else return;
		
		if(Constants.TRAVELZEN_FINANCE_PRODUCT_ID.equals(message.getBusinessProductId()))
			generateRepaymentData(message);
		
	}
	
	
	/**
	 * 生成回款数据
	 * @param message
	 */
	private void generateRepaymentData(RepaymentSuccessMessage message) {
		String type = StringUtils.trimToEmpty(message.getBusinessProductId()) + ReportMonitorDataType.REPAYMENT_SALES.name();
		ReportMonitorData monitorData = serviceHelper.lockReportMonitorDataByMemberIdAndDateAndType(message.getFinanceId(), message.getOutCustomerId(), message.getRepaymentDate(), type);
		if(monitorData == null){
			monitorData = new ReportMonitorData();
			monitorData.setId(serviceHelper.generateReportMonitorDataId());
			monitorData.setMemberId(message.getFinanceId());
			monitorData.setOutCustomerId(message.getOutCustomerId());
			monitorData.setProductId(message.getBusinessProductId());
			monitorData.setDate(message.getRepaymentDate());
			monitorData.setType(type);
			monitorData.setAmount(message.getTotal());
			monitorData.setCreateTime(new Date());
			monitorDataMapper.insertSelective(monitorData);
			logger.info("插入监控的还款数据，messageId:{}", message.getMessageId());
		}else{
			monitorData.setAmount(monitorData.getAmount() + message.getTotal());
			monitorDataMapper.updateByPrimaryKeySelective(monitorData);
			logger.info("更新监控的还款数据，messageId:{}", message.getMessageId());
		}
	}

	/**
	 * 返回的id用于分布式事务判断操作是否执行过
	 * @param message
	 * @return
	 */
	private String getMessageId(RepaymentSuccessMessage message) {
		return "report:repayment-success-message:" + message.getMessageId();
	}

	@Override
	public void initReport(ReportType reportType, String businessProduct, String own_id, UserType userType) throws Exception {
		switch(reportType){
		case DAILY:
			Delta delta = new Delta();
			ReportFactorFinanceDay reportToday = getBasicInfoForDailyReport(own_id, userType.name());
			reportToday.setBusinessProductId(businessProduct);
			initDailyReport(reportToday, delta);
			break;
		case ANNUAL:
			Delta deltaAnnual = new Delta();
			ReportFactorFinanceYear reportYear = getBasicInfoForAnnualReport(own_id, userType.name());
			reportYear.setBusinessProductId(businessProduct);
			initAnnualReport(reportYear, deltaAnnual);
			break;
		case COUNT:
			initReportCountForUser(own_id, userType.name(), businessProduct);
			break;
		default:
			break;
		}
	}
	
}
