package com.cana.repayment.service.transaction.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.credit.limit.service.transaction.ICommonCreditLimitTransactionService;
import com.cana.repayment.dao.mapper.RepaymentTableLockMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentDailyBatchTaskItemMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentDailyBatchTaskMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentExpenseMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentExtensionProductDetailMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentExtensionProductSummaryMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentOverdueProductDetailMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentOverdueProductSummaryMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPenaltyProductDetailMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPenaltyProductSummaryMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPlanMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentRuleMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentSingleCollectMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentSingleDistributeDetailMapper;
import com.cana.repayment.dao.po.RepaymentDailyBatchTask;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskExample;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskItem;
import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentExtensionProductDetail;
import com.cana.repayment.dao.po.RepaymentExtensionProductSummary;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentOverdueProductDetail;
import com.cana.repayment.dao.po.RepaymentOverdueProductSummary;
import com.cana.repayment.dao.po.RepaymentPenaltyProductDetail;
import com.cana.repayment.dao.po.RepaymentPenaltyProductSummary;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.dao.po.RepaymentRule;
import com.cana.repayment.dao.po.RepaymentRuleExample;
import com.cana.repayment.dao.po.RepaymentSingleCollect;
import com.cana.repayment.service.IRepositoryService;
import com.cana.repayment.service.bo.RepaymentDailyBatchTaskBO;
import com.cana.repayment.service.bo.RepaymentDailyBatchTaskItemBO;
import com.cana.repayment.service.bo.RepaymentExpenseBO;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.repayment.service.bo.RepaymentSingleCollectBO;
import com.cana.repayment.service.bo.RepaymentSingleDistributeDetailBO;
import com.cana.repayment.service.context.RepaymentCalcContext;
import com.cana.repayment.service.handler.IRepaymentCalc;
import com.cana.repayment.service.handler.RepaymentCalcFactory;
import com.cana.repayment.service.transaction.IRepaymentTransactionService;
import com.cana.repayment.service.util.IRepaymentServiceHelper;
import com.cana.vbam.common.credit.dto.limit.CreditLimitRecoveryRequstDTO;
import com.cana.vbam.common.credit.enums.CreditLimitAuditType;
import com.cana.vbam.common.credit.enums.CreditMode;
import com.cana.vbam.common.repayment.dto.CreateLoanRequest;
import com.cana.vbam.common.repayment.dto.LoanInfoRepaymentResult;
import com.cana.vbam.common.repayment.dto.PreCalcInterestRequest;
import com.cana.vbam.common.repayment.dto.PreCalcInterestResponse;
import com.cana.vbam.common.repayment.dto.RepaymentRequest;
import com.cana.vbam.common.repayment.dto.TravelzenUserRefundInfo;
import com.cana.vbam.common.repayment.dto.TravelzenUserRefundResult;
import com.cana.vbam.common.repayment.enums.BusinessMode;
import com.cana.vbam.common.repayment.enums.ChargeMethod;
import com.cana.vbam.common.repayment.enums.ChargeType;
import com.cana.vbam.common.repayment.enums.Currency;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InputMethod;
import com.cana.vbam.common.repayment.enums.LoanInfoChangeType;
import com.cana.vbam.common.repayment.enums.RepaymentMethod;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.InterestRateConverter;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.google.gson.Gson;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.StringUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.retry.dao.mapper.gen.RetryTaskMapper;

@Service("repaymentTransactionService")
public class RepaymentTransactionService implements IRepaymentTransactionService{

	@Resource
	private RepaymentPlanMapper repaymentPlanMapper;
	
	@Resource
	private RepaymentLoanInfoMapper loanInfoMapper;
	
	@Resource
	private RepaymentExpenseMapper repaymentExpenseMapper;
	
	@Resource
	private RepaymentRuleMapper repaymentRuleMapper;
	
	@Resource
	private RepaymentDailyBatchTaskMapper dailyBatchTaskMapper;
	
	@Resource
	private RepaymentDailyBatchTaskItemMapper dailyBatchTaskItemMapper;
	
	@Resource 
	private RepaymentTableLockMapper tableLockMapper;
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private IVbamCommonService commonService;
	
	@Resource
	private RepaymentSingleCollectMapper singleCollectMapper;
	
	@Resource
	private RepaymentSingleDistributeDetailMapper singleDistributeDetailMapper;
	
	@Resource
	private IRepositoryService repositoryService;
	
	@Resource
	private RepaymentExtensionProductDetailMapper extensionProductDetailMapper;
	
	@Resource
	private RepaymentExtensionProductSummaryMapper extensionProductSummaryMapper;
	
	@Resource
	private RepaymentOverdueProductDetailMapper overdueProductDetailMapper;
	
	@Resource
	private RepaymentOverdueProductSummaryMapper overdueProductSummaryMapper;
	
	@Resource
	private RepaymentPenaltyProductDetailMapper penaltyProductDetailMapper;
	
	@Resource
	private RepaymentPenaltyProductSummaryMapper penaltyProductSummaryMapper;
	
	@Resource
	private RetryTaskMapper retryTaskMapper;
	
	@Resource
	private IRepaymentServiceHelper serviceHelper;
	
	@Resource
	private ICommonCreditLimitTransactionService commonCreditLimitTransactionService;
	
	
	private static Logger logger = LoggerFactory.getLogger(RepaymentTransactionService.class);
	
	@Override
	public void saveRepymentPlanAndExpenseInfo(List<RepaymentPlan> repaymentPlanList,List<RepaymentExpense> repaymentExpenseList, String loanInfoId,String version) throws Exception{
		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(tableLockMapper.lockLoanInfoById(loanInfoId));
		Integer extensionDays = loanInfoBO.lazyLoadRepaymentConfig().getExtensionDays();
		for(RepaymentPlan repaymentPlan:repaymentPlanList){
			repaymentPlan.setExtensionDays(extensionDays);
			repaymentPlanMapper.insertSelective(repaymentPlan);
		}
		for(RepaymentExpense repaymentExpense:repaymentExpenseList){
			repaymentExpenseMapper.insertSelective(repaymentExpense);
		}
		loanInfoBO.duplicate();
		loanInfoBO.setLastVersion(loanInfoBO.getCurrentVersion());
		loanInfoBO.setCurrentVersion(version);
		loanInfoBO.setChangeType(LoanInfoChangeType.modify.name());
		loanInfoBO.createSnapshot();
		loanInfoMapper.updateByPrimaryKey(loanInfoBO);
	}

	@Override
	public void addRepaymentRule(RepaymentRule repaymentRule) {
		repaymentRuleMapper.insertSelective(repaymentRule);
	}
	
	@Override
	public void modifyRepaymentRule(RepaymentRule repaymentRule) {
		repaymentRuleMapper.updateByPrimaryKeySelective(repaymentRule);
	}

	@Override
	public void deleteRepaymentRule(String ruleId) {
		repaymentRuleMapper.deleteByPrimaryKey(ruleId);
	}

	@Override
	public void generateDailyBatchTask(String loanInfoId, String curDate) throws Exception {
		
		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(tableLockMapper.lockLoanInfoById(loanInfoId));
		
		if(!loanInfoBO.islegal()){
			logger.info("此放款[{}]的还款计划不完整", loanInfoBO.getId());
			return;
		}
		
		if(existDailyBatchTask(loanInfoId,curDate)){
			logger.info("此放款[{}]的跑批任务已经生成过了", loanInfoBO.getId());
			return;
		}
		
		IRepaymentCalc repaymentCalc = RepaymentCalcFactory.getRepaymentCalc(loanInfoBO);
		
		List<RepaymentDailyBatchTaskItemBO> taskItems = repaymentCalc.generateDailyBatchTask(loanInfoBO, curDate);
		
		saveTasks(loanInfoBO, taskItems, curDate);
		
	}

	/**
	 * 保存跑批任务
	 * @param loanInfoBO
	 * @param taskItems
	 */
	private void saveTasks(RepaymentLoanInfoBO loanInfoBO, List<RepaymentDailyBatchTaskItemBO> taskItems, String curDate) {
		RepaymentDailyBatchTask task = new RepaymentDailyBatchTask();
		task.setId(generateTaskId());
		task.setLoanInfoId(loanInfoBO.getId());
		task.setCustomerId(loanInfoBO.getFinanceId());
		task.setDate(curDate);
		task.setTaskNum(taskItems.size());
		RepaymentDailyBatchTaskItem firstTaskItem = taskItems.size() == 0 ? null : taskItems.get(0);
		if(firstTaskItem != null){
			task.setSequence(0);
			task.setNextTaskItemId(firstTaskItem.getId());
			task.setNextTaskItemExecuteTime(firstTaskItem.getExecuteTime());
		}
		task.setCreateTime(new Date());
		dailyBatchTaskMapper.insertSelective(task);
		for(int i = 0; i < taskItems.size(); i++){
			RepaymentDailyBatchTaskItem item = taskItems.get(i);
			item.setTaskId(task.getId());
			item.setSequence(i);
			item.setCreateTime(new Date());
			dailyBatchTaskItemMapper.insertSelective(item);
		}
		
	}

	/**
	 * 生成任务id
	 * @return
	 */
	private String generateTaskId(){
		return DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_TASK_ITEM_ID, 4);
	}
	
	/**
	 * 是否放款信息的跑批任务已经生成过
	 * @param loanInfoId
	 * @param curDate
	 * @return
	 */
	private boolean existDailyBatchTask(String loanInfoId, String curDate) {
		RepaymentDailyBatchTaskExample example = new RepaymentDailyBatchTaskExample();
		example.createCriteria().andLoanInfoIdEqualTo(loanInfoId)
		                        .andDateEqualTo(curDate);
		return dailyBatchTaskMapper.selectByExample(example).size() != 0;
	}


	/**
	 * 创建还款记录汇总
	 * @param actualDeductAmount
	 * @param taskItemBO
	 * @param repaymentMethod
	 * @return
	 */
	private RepaymentSingleCollectBO createRepaymentRecord(long actualDeductAmount, RepaymentDailyBatchTaskBO taskBO, RepaymentDailyBatchTaskItemBO taskItemBO, RepaymentMethod repaymentMethod, String deductDate10){
		RepaymentSingleCollectBO record = new RepaymentSingleCollectBO(new RepaymentSingleCollect());
		record.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_SINGLE_COLLECT_ID, 4));
		record.setLoanInfoId(taskBO.getLoanInfoId());
	    record.setRepaymentDate(deductDate10);
		record.setRepaymentType(repaymentMethod.name());
		record.setRepaymentTotalAmount(actualDeductAmount);
		record.setRepaymentCertificate(taskItemBO.extraData("businessSeq"));
		record.setCreateTime(new Date());
		return record;
	}


	@Override
	public void generateExtensionCharge(RepaymentDailyBatchTaskBO taskBO, RepaymentDailyBatchTaskItemBO taskItemBO)
			throws Exception {
		
		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(tableLockMapper.lockLoanInfoById(taskBO.getLoanInfoId()));
		
		if(loanInfoBO.lazyLoadRepaymentConfig().getExtensionRatio().doubleValue() <= 0){
			logger.info("展期利率等于0");
			taskBO.advanceToNextTask();
			return;
		}
		
		String summaryId = DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_EXTENSION_PRODUCT_SUMMARY_ID, 4);
		
	    List<RepaymentPlanBO> affectedPlans = new ArrayList<>();
	    
	    String today10 = commonService.getCurrentDate();
		
		for(RepaymentPlanBO planBO : loanInfoBO.plansInExtensionState(today10)){
			
			if(planBO.totalAccountAmount() == 0){
				logger.info("所有的应还都已还清，不计算展期费用");
				continue;
			}
			if(repositoryService.getExtensionChargeGenerateDetailByPlanIdAndDate(planBO.getId(), taskBO.getDate()) != null){
				logger.info("展期费用已经生成过");
				continue;
			}
			
			long extensionCharge = MoneyArithUtil.round(MoneyArithUtil.mul(new BigDecimal(planBO.getAccountPrincipal()), loanInfoBO.lazyLoadRepaymentConfig().getExtensionRatio()), 0).longValue(); 
			
			planBO.setAccountExtensionCharge(planBO.getAccountExtensionCharge() + extensionCharge);
			planBO.duplicate();
			planBO.setUpateTime(new Date());
			repaymentPlanMapper.updateByPrimaryKey(planBO);
			affectedPlans.add(planBO);
			
			RepaymentExtensionProductDetail detail = createExtensionProductDetail(loanInfoBO, planBO, taskBO, extensionCharge);
			detail.setSummaryId(summaryId);
			extensionProductDetailMapper.insertSelective(detail);
		}
		
		if(affectedPlans.size() > 0){
			
			RepaymentExtensionProductSummary summary = createExtensionProductSummary(loanInfoBO, taskBO, affectedPlans);
			summary.setId(summaryId);
			extensionProductSummaryMapper.insertSelective(summary);
			
			loanInfoBO = new RepaymentLoanInfoBO(loanInfoBO.getId()); //加载最新的放款信息
			loanInfoBO.duplicate();
			loanInfoBO.setUpateTime(new Date());
			loanInfoBO.setLastVersion(loanInfoBO.getCurrentVersion());
			loanInfoBO.setCurrentVersion(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_LOAN_INFO_VERSION, 4));
			loanInfoBO.setChangeType(LoanInfoChangeType.extension_charge_generate.name());
			loanInfoBO.setChangeId(summaryId);
			loanInfoMapper.updateByPrimaryKey(loanInfoBO);
			
			loanInfoBO.createSnapshot();
		}
		
		taskBO.advanceToNextTask();
		
	}

	private RepaymentExtensionProductDetail createExtensionProductDetail(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO planBO, RepaymentDailyBatchTaskBO taskBO, long extensionCharge) {
		RepaymentExtensionProductDetail detail = new RepaymentExtensionProductDetail();
		detail.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_EXTENSION_PRODUCT_ID, 4));
		detail.setAccountPrincipal(planBO.getAccountPrincipal());
		detail.setAccountInterest(planBO.getAccountInterest());
		detail.setAccountServiceCharge(planBO.getAccountServiceCharge());
		detail.setRepaymentPlanId(planBO.getId());
		detail.setLoanInfoId(loanInfoBO.getId());
		detail.setDate(taskBO.getDate());
		detail.setExtensionRatio(loanInfoBO.lazyLoadRepaymentConfig().getExtensionRatio());
		detail.setExtensionCharge(extensionCharge);
		detail.setCreateTime(new Date());
		return detail;
	}
	
	private RepaymentExtensionProductSummary createExtensionProductSummary(RepaymentLoanInfoBO loanInfoBO, RepaymentDailyBatchTaskBO taskBO, List<RepaymentPlanBO> affectedPlans){
		RepaymentExtensionProductSummary summary = new RepaymentExtensionProductSummary();
		summary.setAffectedPlanNum(affectedPlans.size());
		summary.setCreateTime(new Date());
		summary.setDate(taskBO.getDate());
		summary.setLoanInfoId(loanInfoBO.getId());
		return summary;
	}

	@Override
	public void generateOverdue(RepaymentDailyBatchTaskBO taskBO, RepaymentDailyBatchTaskItemBO taskItemBO) throws Exception {
		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(tableLockMapper.lockLoanInfoById(taskBO.getLoanInfoId()));	
		
        String summaryId = DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_OVERDUE_PRODUCT_SUMMARY_ID, 4);
		
	    List<RepaymentPlanBO> affectedPlans = new ArrayList<>();
	    
	    String curDate10 = taskBO.getDate();
		
		for(RepaymentPlanBO planBO : loanInfoBO.lazyLoadPlans()){
			if(planBO.inOverdueTimeRange(curDate10) && planBO.hasAccount()){
				planBO.setOverduePrincipal(planBO.getAccountPrincipal());
				planBO.setAccountPrincipal(0L);
				planBO.setOverdueInterest(planBO.getAccountInterest());
				planBO.setAccountInterest(0L);
				planBO.setOverdueServiceCharge(planBO.getAccountServiceCharge());
				planBO.setAccountServiceCharge(0L);
				planBO.duplicate();
				planBO.setUpateTime(new Date());
				repaymentPlanMapper.updateByPrimaryKey(planBO);
				affectedPlans.add(planBO);
				
				RepaymentOverdueProductDetail detail = createOverdueProductDetail(planBO);
				detail.setSummaryId(summaryId);
				overdueProductDetailMapper.insertSelective(detail);
			}
		}
		
		if(affectedPlans.size() > 0){
			
			RepaymentOverdueProductSummary summary = createOverdueProductSummary(loanInfoBO, taskBO, affectedPlans);
			summary.setId(summaryId);
			overdueProductSummaryMapper.insertSelective(summary);
			
			loanInfoBO = new RepaymentLoanInfoBO(loanInfoBO.getId()); //加载最新的放款信息
			loanInfoBO.duplicate();
			loanInfoBO.setUpateTime(new Date());
			loanInfoBO.setLastVersion(loanInfoBO.getCurrentVersion());
			loanInfoBO.setCurrentVersion(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_LOAN_INFO_VERSION, 4));
			loanInfoBO.setChangeType(LoanInfoChangeType.overdue_generate.name());
			loanInfoBO.setChangeId(summaryId);
			loanInfoMapper.updateByPrimaryKey(loanInfoBO);
			
			loanInfoBO.createSnapshot();
		}
		
		taskBO.advanceToNextTask();
	}

	
	private RepaymentOverdueProductDetail createOverdueProductDetail(RepaymentPlanBO planBO) {
		RepaymentOverdueProductDetail detail = new RepaymentOverdueProductDetail();
		detail.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_OVERDUE_PRODUCT_ID, 4));
		detail.setOverduePrincipal(planBO.getOverduePrincipal());
		detail.setOverdueInterest(planBO.getOverdueInterest());
		detail.setOverdueServiceCharge(planBO.getOverdueServiceCharge());
		detail.setRelatedType(ChargeType.REPAYMENTPLAN.name());
		detail.setRelatedId(planBO.getId());
		detail.setLoanInfoId(planBO.getLoanInfoId());
		detail.setCreateTime(new Date());
		return detail;
	}
	
	private RepaymentOverdueProductSummary createOverdueProductSummary(RepaymentLoanInfoBO loanInfoBO, RepaymentDailyBatchTaskBO taskBO, List<RepaymentPlanBO> affectedPlans){
		RepaymentOverdueProductSummary summary = new RepaymentOverdueProductSummary();
		summary.setAffectedPlanNum(affectedPlans.size());
		summary.setCreateTime(new Date());
		summary.setDate(taskBO.getDate());
		summary.setLoanInfoId(loanInfoBO.getId());
		return summary;
	}

	@Override
	public void generatePenalty(RepaymentDailyBatchTaskBO taskBO, RepaymentDailyBatchTaskItemBO taskItemBO) throws Exception {
		
		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(tableLockMapper.lockLoanInfoById(taskBO.getLoanInfoId()));	
		
		if(loanInfoBO.lazyLoadRepaymentConfig().getPenaltyRate().doubleValue() <= 0){
			logger.info("罚息率等于0");
			taskBO.advanceToNextTask();
			return;
		}
		
		String curDate10 = taskBO.getDate();
		
		 String summaryId = DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_PENALTY_PRODUCT_SUMMARY_ID, 4);
			
		 List<RepaymentPlanBO> affectedPlans = new ArrayList<>();
		
		for(RepaymentPlanBO planBO : loanInfoBO.overduePlans(curDate10)){
			if(repositoryService.getPenaltyDetailByPlanIdAndDate(planBO.getId(), taskBO.getDate()) != null){
				logger.info("还款计划[id={}]的罚息已经生成过了", planBO.getId());
				continue;
			}
			if((planBO.getOverduePrincipal() + planBO.getOverdueInterest() + planBO.getOverdueServiceCharge()) <= 0){
				logger.info("还款计划[id={}]没有逾期的本金，利息，服务费", planBO.getId());
				continue;
			}
			
			RepaymentPenaltyProductDetail detail = createPenaltyProductDetail(loanInfoBO, planBO, taskBO);
			detail.setSummaryId(summaryId);
			penaltyProductDetailMapper.insertSelective(detail);
			
			planBO.setOverduePrincipalPenalty(planBO.getOverduePrincipalPenalty() + detail.getPlusPrincipalPenalty());
			planBO.setOverdueInterestPenalty(planBO.getOverdueInterestPenalty() + detail.getPlusInterestPenalty());
			planBO.setOverdueServiceChargePenalty(planBO.getOverdueServiceChargePenalty() + detail.getPlusServiceChargePenalty());
			
			planBO.duplicate();
			planBO.setUpateTime(new Date());
			repaymentPlanMapper.updateByPrimaryKey(planBO);
			affectedPlans.add(planBO);

		}
		
		if(affectedPlans.size() > 0){
			
			RepaymentPenaltyProductSummary summary = createPenaltyProductSummary(loanInfoBO, taskBO, affectedPlans);
			summary.setId(summaryId);
			penaltyProductSummaryMapper.insertSelective(summary);
			
			loanInfoBO = new RepaymentLoanInfoBO(loanInfoBO.getId()); //加载最新的放款信息
			loanInfoBO.duplicate();
			loanInfoBO.setUpateTime(new Date());
			loanInfoBO.setLastVersion(loanInfoBO.getCurrentVersion());
			loanInfoBO.setCurrentVersion(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_LOAN_INFO_VERSION, 4));
			loanInfoBO.setChangeType(LoanInfoChangeType.penalty_generate.name());
			loanInfoBO.setChangeId(summaryId);
			loanInfoMapper.updateByPrimaryKey(loanInfoBO);
			
			loanInfoBO.createSnapshot();
		}
		
		taskBO.advanceToNextTask();
	}

	private RepaymentPenaltyProductDetail createPenaltyProductDetail(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO planBO, RepaymentDailyBatchTaskBO taskBO) {
		
		BigDecimal penaltyRate = loanInfoBO.lazyLoadRepaymentConfig().getPenaltyRate();
		
		RepaymentPenaltyProductDetail detail = new RepaymentPenaltyProductDetail();
		detail.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_PENALTY_PRODUCT_ID, 4));
		detail.setRepaymentPlanId(planBO.getId());
		detail.setLoanInfoId(loanInfoBO.getId());
		detail.setDate(taskBO.getDate());
		detail.setPenaltyRate(penaltyRate);
		detail.setCreateTime(new Date());
		
		long totalPenalty = MoneyArithUtil.round(MoneyArithUtil.mul(new BigDecimal(planBO.getOverduePrincipal() + planBO.getOverdueInterest() + planBO.getOverdueServiceCharge()), penaltyRate), 0).longValue();
		
		long principalPenalty = MoneyArithUtil.round(MoneyArithUtil.mul(new BigDecimal(planBO.getOverduePrincipal()), penaltyRate), 0).longValue();
		if(principalPenalty > totalPenalty)
			principalPenalty = totalPenalty;
		
		long interestPenalty = MoneyArithUtil.round(MoneyArithUtil.mul(new BigDecimal(planBO.getOverdueInterest()), penaltyRate), 0).longValue();
		if((principalPenalty + interestPenalty) > totalPenalty || planBO.getOverdueServiceCharge().longValue() == 0)
			interestPenalty = totalPenalty - principalPenalty;
		
	    long serviceChargePenalty = totalPenalty - principalPenalty - interestPenalty;
		
		detail.setOverduePrincipal(planBO.getOverduePrincipal());
		detail.setPlusPrincipalPenalty(principalPenalty);
		detail.setOverdueInterest(planBO.getOverdueInterest());
		detail.setPlusInterestPenalty(interestPenalty);
		detail.setOverdueServiceCharge(planBO.getOverdueServiceCharge());
		detail.setPlusServiceChargePenalty(serviceChargePenalty);
		
		return detail;
	}
	
	private RepaymentPenaltyProductSummary createPenaltyProductSummary(RepaymentLoanInfoBO loanInfoBO, RepaymentDailyBatchTaskBO taskBO, List<RepaymentPlanBO> affectedPlans){
		
		RepaymentPenaltyProductSummary summary = new RepaymentPenaltyProductSummary();
		summary.setAffectedPlanNum(affectedPlans.size());
		summary.setDate(taskBO.getDate());
		summary.setLoanInfoId(loanInfoBO.getId());
		summary.setCreateTime(new Date());
		
		return summary;
	}

	@Override
	public void updateOnDeductSuccess(long actualDeductAmount, RepaymentDailyBatchTaskBO taskBO,
			RepaymentDailyBatchTaskItemBO taskItemBO, String curDate10, final Map<Object, Object> extra) throws Exception {
		
		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(tableLockMapper.lockLoanInfoById(taskBO.getLoanInfoId()));
		IRepaymentCalc repaymentCalc = RepaymentCalcFactory.getRepaymentCalc(loanInfoBO);
		if(actualDeductAmount > repaymentCalc.totalDeduct(loanInfoBO, curDate10))
			throw new Exception("扣款金额大于了账扣金额，可能是在账扣期间发生了其他还款");
		
		
		RepaymentSingleCollectBO repaymentRecord = createRepaymentRecord(actualDeductAmount, taskBO, taskItemBO, RepaymentMethod.ACCOUNTDEDUCTION, curDate10);
		singleCollectMapper.insertSelective(repaymentRecord);
		
		repaymentCalc.deduct(actualDeductAmount, loanInfoBO, taskBO, taskItemBO, curDate10);
		
		
		for(RepaymentPlanBO planBO : loanInfoBO.lazyLoadPlans()){
			
			RepaymentSingleDistributeDetailBO repaymentRecordItem = planBO.allotDetail();
			if(repaymentRecordItem == null || repaymentRecordItem.total() <= 0)
				continue;
			
			planBO.duplicate();			
			planBO.setUpateTime(new Date());
			repaymentPlanMapper.updateByPrimaryKey(planBO);
			
			repaymentRecordItem.setRepaymentSingleCollectId(repaymentRecord.getId());
			repaymentRecordItem.setCreateTime(new Date());
			singleDistributeDetailMapper.insertSelective(repaymentRecordItem);
		}
		
		for(RepaymentExpenseBO expenseBO : loanInfoBO.lazyLoadExpenses()){
			
			RepaymentSingleDistributeDetailBO repaymentRecordItem = expenseBO.allotDetail();
			if(repaymentRecordItem == null || repaymentRecordItem.total() <= 0)
				continue;
			
			expenseBO.duplicate();			
			expenseBO.setUpdateTime(new Date());
			repaymentExpenseMapper.updateByPrimaryKey(expenseBO);
			
			repaymentRecordItem.setRepaymentSingleCollectId(repaymentRecord.getId());
			repaymentRecordItem.setCreateTime(new Date());
			singleDistributeDetailMapper.insertSelective(repaymentRecordItem);
		}
		
		
		repaymentRecord = new RepaymentSingleCollectBO(repaymentRecord.getId()); // 重新加载还款记录
		if(actualDeductAmount != repaymentRecord.calcTotal())
			throw new Exception("根据还款明细算出来的总金额不等于总的扣款金额");
		
		loanInfoBO = new RepaymentLoanInfoBO(loanInfoBO.getId()); //加载最新的放款信息
		loanInfoBO.duplicate();
		loanInfoBO.setUpateTime(new Date());
		loanInfoBO.setLastVersion(loanInfoBO.getCurrentVersion());
        loanInfoBO.setCurrentVersion(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_LOAN_INFO_VERSION, 4));
        loanInfoBO.setChangeType(LoanInfoChangeType.deduct.name());
        loanInfoBO.setChangeId(repaymentRecord.getId());
        loanInfoBO.updateExtraData("defaultDeduct", taskItemBO.extraData(Constants.TASK_ITEM_EXTRA_KEY_DEFAULT_DEDUCT));
		loanInfoMapper.updateByPrimaryKey(loanInfoBO);
		
		loanInfoBO.createSnapshot();
		
		taskBO.advanceToNextTask();
		
		if(needRecoveryCreditLimit(loanInfoBO)){
			recoveryLimit4deduct(loanInfoBO, repaymentRecord);
		}
		
		serviceHelper.insertRepaymentSuccessNotificationRetryTaskRecord(loanInfoBO, repaymentRecord);
		
	}
	
	
	/**
	 * 账扣成功后恢复额度
	 * @param loanInfoBO
	 * @param repaymentRecord
	 */
	private void recoveryLimit4deduct(RepaymentLoanInfoBO loanInfoBO, RepaymentSingleCollectBO repaymentRecord) {
		
		
		CreditLimitRecoveryRequstDTO request = new CreditLimitRecoveryRequstDTO();
		request.setAuditType(CreditLimitAuditType.REPAYMENT);
		request.setCreditMode(CreditMode.SYNTHETICAL);
		request.setLimit(repaymentRecord.calcTotalPrincipal());
		request.setMemberId(loanInfoBO.getFinanceId());
		request.setOutCustomerId(loanInfoBO.getOutCustomerId());
		request.setProjectId(loanInfoBO.getBusinessProductId());
	
		logger.info("恢复额度开始:" + new Gson().toJson(request));
		commonCreditLimitTransactionService.recoveryLimit(request);
		logger.info("恢复额度成功");
		
	}

	/**
	 * 是否需要授信额度恢复
	 * @param loanInfoBO
	 * @return
	 */
	private boolean needRecoveryCreditLimit(RepaymentLoanInfoBO loanInfoBO) {
		return !loanInfoBO.containNonAutoRepaymentPlans();
	}

	@Override
	public List<String> getNotTransferInAccountNos(List<String> accountNos) {
		RepaymentRuleExample example = new RepaymentRuleExample();
		example.createCriteria().andFactorTransferInAccountNoIn(accountNos);
		List<RepaymentRule> repaymentRules = repaymentRuleMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(repaymentRules)){
			return accountNos;
		}
		for (RepaymentRule repaymentRule : repaymentRules) {
			accountNos.remove(repaymentRule.getFactorTransferInAccountNo());
		}
		return accountNos;
	}

	@Override
	public RepaymentLoanInfoBO addTravelzenFinanceLoan(CreateLoanRequest request) throws Exception{
		checkCreateLoanRequest(request);
		RepaymentLoanInfo loanInfo = serviceHelper.lockLoanInfoByFinanceIdAndProductIdAndLoanDate(
				request.getFactorId(),
				request.getFinanceId(), Constants.TRAVELZEN_FINANCE_PRODUCT_ID, request.getLoanDate(),
				request.getOutCustomerId());
		if(loanInfo == null)
			return createLoan(request);
		else
			return updateLoanInfo4TravelzenFinance(request, new RepaymentLoanInfoBO(loanInfo));
	}
	
	/**
	 * 更新已有的真旅金融产品放款信息
	 * @param payInfo
	 * @return
	 */
	private RepaymentLoanInfoBO updateLoanInfo4TravelzenFinance(CreateLoanRequest createLoanRequest, RepaymentLoanInfoBO loanInfoBO) throws Exception{
		
		checkUpdateLoanInfoRequest4TravelzenFinance(createLoanRequest, loanInfoBO);

		long appendFinanceAmount = createLoanRequest.getFinanceAmount();
		loanInfoBO.setFinanceAmount(loanInfoBO.getFinanceAmount() + appendFinanceAmount);
		loanInfoBO.setFinanceBalance(loanInfoBO.getFinanceBalance() + appendFinanceAmount);
		loanInfoBO.setReceivablesAmount(loanInfoBO.getReceivablesAmount() + appendFinanceAmount);
		loanInfoBO.setReceivablesBalance(loanInfoBO.getReceivablesBalance() + appendFinanceAmount);
		loanInfoBO.setLastVersion(loanInfoBO.getCurrentVersion());
		loanInfoBO.setCurrentVersion(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_LOAN_INFO_VERSION, 4));
		loanInfoBO.setChangeType(LoanInfoChangeType.append_finance_amount.name());
		loanInfoBO.setChangeId("");
		loanInfoBO.setUpateTime(new Date());
		loanInfoMapper.updateByPrimaryKey(loanInfoBO);
		
		RepaymentCalcContext context = new RepaymentCalcContext();
		context.setAppendFinanceAmount(appendFinanceAmount);
		RepaymentCalcFactory.getRepaymentCalc(loanInfoBO).generateRepaymentPlan(loanInfoBO, context);
		
		loanInfoBO = new RepaymentLoanInfoBO(loanInfoBO.getId());
		loanInfoBO.duplicate();
		loanInfoMapper.updateByPrimaryKey(loanInfoBO);
		
		loanInfoBO.createSnapshot();
		
		return loanInfoBO;
	}

	/**
	 * 校验真旅金融产品的放款信息更新请求
	 * @param payInfo
	 * @param loanInfo
	 * @throws Exception
	 */
	private void checkUpdateLoanInfoRequest4TravelzenFinance(CreateLoanRequest createLoanRequest, RepaymentLoanInfo loanInfo) throws Exception{
		if(!loanInfo.getFactorId().equals(createLoanRequest.getFactorId()))
			throw new Exception("支付信息中的factorId与放款信息中的factorId不一致");
		
		if(!loanInfo.getFinanceId().equals(createLoanRequest.getFinanceId()))
			throw new Exception("支付信息中的financeId与放款信息中的financeId不一致");
		
		if(!loanInfo.getAccountNo().equals(createLoanRequest.getAccountNo()))
			throw new Exception("支付信息中的accountNo与放款信息中的accountNo不一致");
		
		if(!loanInfo.getAccountSupervisionId().equals(createLoanRequest.getAccountSupervisionId()))
			throw new Exception("支付信息中的accountSupervisionId与放款信息中的accountSupervisionId不一致");
	}

	@Override
	public TravelzenUserRefundResult refundByTravelzenFinancier(TravelzenUserRefundInfo refundInfo) throws Exception {
		
		checkTravelzenFinanceRefund(refundInfo);
		
		List<LoanInfoRepaymentResult> loanInfoRepaymentResultList = new ArrayList<>();
		
		List<RepaymentLoanInfo> loanInfos = serviceHelper.lockUnsettleLoanInfosByFinanceIdAndProductId(refundInfo.getFinanceId(), Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		
		long remainingRepaymentAmount = refundInfo.getRefundAmount();
		
		String refundDate10 = commonService.getCurrentDate();
		
		for(RepaymentLoanInfo loanInfo : loanInfos){
			if(!loanInfo.getOutCustomerId().equals(refundInfo.getOutCustomerId()))
				continue;
			LoanInfoRepaymentResult loanInfoRepaymentResult = autoRepaymentCausedByRefund(new RepaymentLoanInfoBO(loanInfo), remainingRepaymentAmount, refundInfo.getTxnId(), refundDate10);
			loanInfoRepaymentResultList.add(loanInfoRepaymentResult);
			remainingRepaymentAmount -= loanInfoRepaymentResult.getActualRepaymentTotalAmount();
			if(remainingRepaymentAmount <= 0)
				break;
		}
		
		insertRepaymentSuccessNotificationRetryTaskRecordCausedByRefund(loanInfoRepaymentResultList);
		
		TravelzenUserRefundResult result = new TravelzenUserRefundResult();
		result.setRemainingAmount(remainingRepaymentAmount);
		result.setResult(loanInfoRepaymentResultList);
		result.setTotalPaidPrincipal(totalPaidPrincipal(loanInfoRepaymentResultList));
		
		recoveryLimit4refundByTravelzenFinancier(result);
		
		return result;
	}
	
	private void recoveryLimit4refundByTravelzenFinancier(TravelzenUserRefundResult refundResult) {
		if (refundResult.getTotalPaidPrincipal() > 0) {
			
			RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(refundResult.getResult().get(0).getLoanInfoId());
			
			CreditLimitRecoveryRequstDTO request = new CreditLimitRecoveryRequstDTO();
			request.setAuditType(CreditLimitAuditType.REFUND);
			request.setCreditMode(CreditMode.SYNTHETICAL);
			request.setLimit(refundResult.getTotalPaidPrincipal());
			request.setMemberId(loanInfoBO.getFinanceId());
			request.setOutCustomerId(loanInfoBO.getOutCustomerId());
			request.setProjectId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		
			logger.info("恢复额度开始:" + new Gson().toJson(request));
			commonCreditLimitTransactionService.recoveryLimit(request);
			logger.info("恢复额度成功");
			
		}
	}
	
	/**
	 * 插入由退款引起的还款成功通知记录
	 * @param loanInfoRepaymentResultList
	 */
	private void insertRepaymentSuccessNotificationRetryTaskRecordCausedByRefund(List<LoanInfoRepaymentResult> loanInfoRepaymentResultList) {
		for(LoanInfoRepaymentResult result : loanInfoRepaymentResultList){
			RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(result.getLoanInfoId());
			RepaymentSingleCollectBO repaymentRecord = new RepaymentSingleCollectBO(result.getRepaymentSummaryRecordId());
			serviceHelper.insertRepaymentSuccessNotificationRetryTaskRecord(loanInfoBO, repaymentRecord);
		}
	}

	/**
	 * 计算还的总的本金金额
	 * @param loanInfoRepaymentResultList
	 * @return
	 */
	private long totalPaidPrincipal(List<LoanInfoRepaymentResult> loanInfoRepaymentResultList) {
		if(CollectionUtils.isEmpty(loanInfoRepaymentResultList)){
			return 0L;
		}
		long total = 0;
		for(LoanInfoRepaymentResult loanInfoRepaymentResult : loanInfoRepaymentResultList){
			RepaymentSingleCollectBO repaymentSummaryBO = new RepaymentSingleCollectBO(loanInfoRepaymentResult.getRepaymentSummaryRecordId());
			total += repaymentSummaryBO.calcTotalPrincipal();
		}
		return total;
	}

	/**
	 * 处理由退款引起的自动还款
	 * @param repaymentLoanInfoBO
	 * @param remainingRepaymentAmount
	 * @param refundTxnId
	 * @return
	 */
	private LoanInfoRepaymentResult autoRepaymentCausedByRefund(RepaymentLoanInfoBO loanInfoBO,
			long remainingRepaymentAmount, String refundTxnId, String refundDate10) throws Exception{
		
		RepaymentCalcContext context = new RepaymentCalcContext();
		context.setRepaymentDate(refundDate10);
		context.setRepaymentCertificate(refundTxnId);
		context.setRepaymentMethod(RepaymentMethod.REFUND);
		return RepaymentCalcFactory.getRepaymentCalc(loanInfoBO).repayment(loanInfoBO, remainingRepaymentAmount, context);
		
	}

	/**
	 * 校验真旅用户退款信息的正确性
	 * @param refundInfo
	 * @throws Exception
	 */
	private void checkTravelzenFinanceRefund(TravelzenUserRefundInfo refundInfo) throws Exception{
		if(StringUtils.isBlank(refundInfo.getFinanceId()))
			throw new Exception("financeId不能为空");
		if(refundInfo.getRefundAmount() <= 0)
			throw new Exception("refundAmount格式不正确");
	}

	@Override
	public PreCalcInterestResponse preCalcInterest(PreCalcInterestRequest request) throws Exception {
		StringUtil.trimObjectFields(request);
		checkPreCalcInterestRequest(request);
		return RepaymentCalcFactory.getRepaymentCalc(request.getProductId()).preCalcInterest(request);
	}

	private void checkPreCalcInterestRequest(PreCalcInterestRequest request) throws Exception{
		if(request == null)
			throw new Exception("请求对象为null");
		if(StringUtils.isBlank(request.getProductId()))
			throw new Exception("productId为空");
		if(request.getPrincipal() <= 0)
			throw new Exception("本金必须大于0");
		if(request.getInterestRateUnit() == null)
			throw new Exception("interestRateUnit不能为null");
		if(request.getInterestRate() == null)
			throw new Exception("interestRate不能为null");
		if(StringUtils.isBlank(request.getLoanDate()))
			throw new Exception("loanDate不能为空");
		if(!DateTimeUtil.validateDate10(request.getLoanDate()))
			throw new Exception("loanDate的正确格式为: yyyy-MM-dd");
		if (request.getLoanPeriodUnit() == null)
			throw new Exception("放款期限单位不能为空");
		if (request.getLoanPeriod() <= 0)
			throw new Exception("放款期限必须为正数");
	}

	public RepaymentLoanInfoBO createLoan(CreateLoanRequest request) throws Exception {

		checkCreateLoanRequest(request);

		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(new RepaymentLoanInfo());
		loanInfoBO.setId(DateTimeUtil.date8() + seqGen.getNextSeq(Constants.REPAYMENT_LOAN_INFO_ID, 5));
		loanInfoBO.setLoanNo(serviceHelper.generateLoanNo());
		loanInfoBO.setBusinessMode(BusinessMode.FACTORANDFINACE.name());
		loanInfoBO.setInputMethod(InputMethod.AUTO.name());
		loanInfoBO.setFactorId(request.getFactorId());
		loanInfoBO.setFactorCompany(request.getFactorCompany());
		loanInfoBO.setFinanceId(request.getFinanceId());
		loanInfoBO.setFinanceCompany(request.getFinanceCompany());
		loanInfoBO.setOutCustomerId(request.getOutCustomerId());
		loanInfoBO.setOutCustomerName(request.getOutCustomerName());
		loanInfoBO.setCoreCompanyId(request.getCoreCompanyId());
		loanInfoBO.setCoreCompanyName(request.getCoreCompanyName());
		loanInfoBO.setCurrency(Currency.RMB.name());
		loanInfoBO.setBusinessProduct(request.getProductName());
		loanInfoBO.setBusinessProductId(request.getProductId());
		loanInfoBO.setReceivablesAmount(request.getFinanceAmount());
		loanInfoBO.setReceivablesBalance(request.getFinanceAmount());
		loanInfoBO.setFinanceAmount(request.getFinanceAmount());
		loanInfoBO.setFinanceBalance(request.getFinanceAmount());
		loanInfoBO.setInterestRateUnit(request.getInterestRateUnit().name());
		loanInfoBO.setInterestRate(request.getInterestRate());
		loanInfoBO.setAccountNo(request.getAccountNo());
		loanInfoBO.setAccountSupervisionId(request.getAccountSupervisionId());
		loanInfoBO.setLoanPeriodUnit(request.getLoanPeriodUnit().name());
		loanInfoBO.setLoanPeriod(String.valueOf(request.getLoanPeriod()));
		loanInfoBO.setLoanDate(request.getLoanDate());
		loanInfoBO.setRepaymentMethod(request.getRepaymentMethod().name());
		if (request.getRepaymentMethod() == RepaymentType.ORDER) {
			loanInfoBO.setRepaymentPeriod(1);
		} else if (request.getRepaymentMethod() == RepaymentType.MONTHLY
				|| request.getRepaymentMethod() == RepaymentType.EQUALALL
				|| request.getRepaymentMethod() == RepaymentType.EQUALPRINCIPAL) {
			if (request.getLoanPeriodUnit() == DateUnit.YEAR)
				loanInfoBO.setRepaymentPeriod(request.getLoanPeriod() * 12);
			else if (request.getLoanPeriodUnit() == DateUnit.MONTH)
				loanInfoBO.setRepaymentPeriod(request.getLoanPeriod());
			else
				throw new Exception("按月付息、等额本息、等额本金不支持按日放款");
		} else {
			throw new Exception("暂不支持的还款方式");
		}
		loanInfoBO.setDueDate(RepaymentCalcFactory.getRepaymentCalc(loanInfoBO).calcLoanDueDate(request.getLoanDate(),
				request.getLoanPeriodUnit(), request.getLoanPeriod()));

		loanInfoBO.setSettleStatus(SettleStatus.UNSETTLE.name());
		loanInfoBO.setChangeType(LoanInfoChangeType.created.name());// 变更类型
		loanInfoBO.setCreateTime(new Date());
		loanInfoMapper.insertSelective(loanInfoBO);

		serviceHelper.createLoanInfoConfig(loanInfoBO, request);

		RepaymentCalcContext context = new RepaymentCalcContext();
		context.setUseHolidayPolicy(request.isUseHolidayPolicy());
		RepaymentCalcFactory.getRepaymentCalc(loanInfoBO).generateRepaymentPlan(loanInfoBO, context);

		// 重新加载loanInfoBO
		loanInfoBO = new RepaymentLoanInfoBO(loanInfoBO.getId());
		loanInfoBO.createSnapshot();

		return loanInfoBO;
	}

	/**
	 * 校验新建放款请求是否合法
	 */
	private void checkCreateLoanRequest(CreateLoanRequest request) throws Exception {
		if (request == null)
			throw new Exception("请求对象为null");
		if (!DateTimeUtil.validateDate10(request.getLoanDate()))
			throw new Exception("放款日期格式不正确");
		if (StringUtils.isAnyBlank(request.getFinanceId(), request.getFinanceCompany()))
			throw new Exception("融资客户不能为空");
		if (StringUtils.isAnyBlank(request.getCoreCompanyId(), request.getCoreCompanyName()))
			throw new Exception("核心企业不能为空");
		if (StringUtils.isAnyBlank(request.getFactorId(), request.getFactorCompany()))
			throw new Exception("资金方企业不能为空");
		if (request.getFinanceAmount() <= 0)
			throw new Exception("融资金额必须大于0");
		if (request.getLoanPeriodUnit() == null)
			throw new Exception("放款期限单位不能为空");
		if (request.getLoanPeriod() <= 0)
			throw new Exception("放款期限必须大于0");
		if (request.getInterestRateUnit() == null)
			throw new Exception("利率单位不能为空");
		if (request.getInterestRate() == null || request.getInterestRate().compareTo(BigDecimal.ZERO) <= 0)
			throw new Exception("利率必须大于0");
		if (request.getRepaymentMethod() == null)
			throw new Exception("付息还本方式不能为空");
		if (StringUtils.isAnyBlank(request.getProductName(), request.getProductId()))
			throw new Exception("产品不能为空");
		if (StringUtils.isBlank(request.getInstitutionName()))
			throw new Exception("外部平台名称不能为空");

		if (!Constants.VJ_PRODUCT_ID.equals(request.getProductId())) {
			if (StringUtils.isBlank(request.getAccountNo()))
				throw new Exception("还款账号不能为空");
			if (StringUtils.isBlank(request.getAccountSupervisionId()))
				throw new Exception("还款账号监管关系ID不能为空");
		}

		if (StringUtils.isNotBlank(request.getAccountNo()) && StringUtils.equals(request.getAccountNo(), request.getFactorTransferInAccountNo()))
			throw new Exception("还款账号与回款账号不能相同");

		if (request.getPenaltyChargeMethod() != null && request.getPenaltyRatio() != null) {
			if (request.getPenaltyRatio().compareTo(BigDecimal.ZERO) < 0)
				throw WebException.instance("罚息率不能为负数");
			BigDecimal dailyRate = InterestRateConverter.getDailyRate(request.getInterestRateUnit(), request.getInterestRate());
			if (request.getPenaltyChargeMethod() == ChargeMethod.AMOUNT
					&& request.getPenaltyRatio().compareTo(dailyRate) < 0)
				throw WebException.instance("罚息率不可小于正常利率");
		} else if (request.getPenaltyChargeMethod() != null || request.getPenaltyRatio() != null) {
			throw WebException.instance("罚息比率与罚息计算方式不可为空");
		}
		
		if (request.getExtensionChargeMethod() != null && request.getExtensionRatio() != null) {
			if (request.getExtensionRatio().compareTo(BigDecimal.ZERO) < 0)
				throw WebException.instance("展期率不能为负数");
		} else if (request.getExtensionChargeMethod() != null || request.getExtensionRatio() != null) {
			throw WebException.instance("展期率与展期计算方式不可为空");
		}
		
		if(request.getExtensionDays() < 0){
			throw WebException.instance("展期天数不能小于0");
		}
			
	}

	@Override
	public LoanInfoRepaymentResult repayment(RepaymentRequest request,RepaymentMethod repaymentMethod) throws Exception {
		
		checkRepaymentRequest(request);

		long repaymentAmount = request.getRepaymentAmount();
		String repaymentDate10 = commonService.getCurrentDate();
		RepaymentLoanInfoBO loanInfoBO = serviceHelper.lockLoanInfoById(request.getLoanId());
		
		//调还款接口
		RepaymentCalcContext context = new RepaymentCalcContext();
		context.setRepaymentDate(repaymentDate10);
		context.setRepaymentMethod(repaymentMethod);
		LoanInfoRepaymentResult loanInfoRepaymentResult = RepaymentCalcFactory.getRepaymentCalc(loanInfoBO).repayment(loanInfoBO, repaymentAmount, context);
		
		if(StringUtils.isNotBlank(loanInfoBO.getBusinessProductId())){
			//恢复额度
			CreditLimitRecoveryRequstDTO limitRequest = new CreditLimitRecoveryRequstDTO();
			limitRequest.setAuditType(CreditLimitAuditType.REPAYMENT);
			limitRequest.setCreditMode(CreditMode.SYNTHETICAL);
			limitRequest.setLimit(loanInfoRepaymentResult.getActualRepaymentPrincipal());
			limitRequest.setMemberId(loanInfoBO.getFinanceId());
			limitRequest.setOutCustomerId(loanInfoBO.getOutCustomerId());
			limitRequest.setProjectId(loanInfoBO.getBusinessProductId());
		
			logger.info("恢复额度开始:" + new Gson().toJson(limitRequest));
			commonCreditLimitTransactionService.recoveryLimit(limitRequest);
			logger.info("恢复额度成功");
		}
		if(request.isSendMessage()){
			RepaymentLoanInfo loanInfo = loanInfoMapper.selectByPrimaryKey(request.getLoanId());
			RepaymentSingleCollect repaymentSingleCollect = singleCollectMapper.selectByPrimaryKey(loanInfoRepaymentResult.getRepaymentSummaryRecordId());
			//插入还款成功发送短信重试任务
			serviceHelper.insertActiveRepaymentSuccessNotificationRetryTaskRecord(loanInfo, repaymentSingleCollect);
		}
		return loanInfoRepaymentResult;
	
	}
	
	/**
	 * 校验用户还款信息的正确性
	 * @param request
	 * @throws Exception
	 */
	private void checkRepaymentRequest(RepaymentRequest request) throws WebException{
		if(StringUtils.isBlank(request.getLoanId()))
			throw WebException.instance("loanId不能为空");
		if(request.getRepaymentAmount() <= 0)
			throw WebException.instance("还款金额格式不正确");
	}
}
