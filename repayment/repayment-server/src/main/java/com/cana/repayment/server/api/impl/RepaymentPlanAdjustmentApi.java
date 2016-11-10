package com.cana.repayment.server.api.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.cana.common.dao.mapper.gen.PropertiesMapper;
import com.cana.repayment.api.IRepaymentPlanAdjustmentApi;
import com.cana.repayment.dao.mapper.gen.LoanInfoConfigMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentExpenseMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPlanMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentSingleDistributeDetailMapper;
import com.cana.repayment.dao.po.LoanInfoConfig;
import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentExpenseExample;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.dao.po.RepaymentPlanExample;
import com.cana.repayment.dao.po.RepaymentSingleDistributeDetail;
import com.cana.repayment.dao.po.RepaymentSingleDistributeDetailExample;
import com.cana.repayment.service.IMessageService;
import com.cana.repayment.service.IRepaymentPlanService;
import com.cana.repayment.service.data.OfflineRepaymentData;
import com.cana.repayment.service.transaction.IRepaymentAdjustmentTransactionService;
import com.cana.repayment.service.transaction.IRepaymentTransactionService;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.vbam.common.repayment.dto.AccountRepaymentPlanRedisDTO;
import com.cana.vbam.common.repayment.dto.ChargeDTO;
import com.cana.vbam.common.repayment.dto.PaidRepaymentExpenseRedisDTO;
import com.cana.vbam.common.repayment.dto.PaidRepaymentPlanRedisDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanAdjustmentIntegration;
import com.cana.vbam.common.repayment.dto.RepaymentSingleCollectDTO;
import com.cana.vbam.common.repayment.dto.RepaymentSingleDistributeDetailDTO;
import com.cana.vbam.common.repayment.enums.ChargeMethod;
import com.cana.vbam.common.repayment.enums.ChargeType;
import com.cana.vbam.common.repayment.enums.LoanInfoChangeType;
import com.cana.vbam.common.repayment.enums.RepaymentMethod;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.utils.RedisUtils;
import com.dianping.cat.Cat;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.redis.client.SpringRedisClient;

public class RepaymentPlanAdjustmentApi implements IRepaymentPlanAdjustmentApi {

	private final SpringRedisClient redisCache = SpringRedisClient.getInstance();
	
	private static final Logger LGR = LoggerFactory.getLogger(RepaymentPlanAdjustmentApi.class);
	
	@Resource
	private IRepaymentPlanService repaymentPlanServiceImpl;
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private RepaymentPlanMapper repaymentPlanMapper;
	
	@Resource
	private RepaymentExpenseMapper repaymentExpenseMapper;
	
	@Resource
	private PropertiesMapper propertiesMapper;
	
	@Resource 
	private LoanInfoConfigMapper loanInfoConfigMapper;

	@Resource 
	private RepaymentLoanInfoMapper repaymentLoanInfoMapper;
	
	@Resource 
	private RepaymentSingleDistributeDetailMapper repaymentSingleDistributeDetailMapper;
	
	@Resource
	private IRepaymentAdjustmentTransactionService repaymentAdjustmentTransactionService;
	
	@Resource
	private IMessageService messageServiceImpl;

	@Resource
	private IRepaymentTransactionService repaymentTransactionService;
	
	@Override
	public ListResult<PaidRepaymentPlanRedisDTO> queryPaidRepaymentPlan(String redisKey, int page, int pageSize) throws Exception {
		long startTime = System.currentTimeMillis();
		ListResult<PaidRepaymentPlanRedisDTO> result = new ListResult<PaidRepaymentPlanRedisDTO>();
		RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration = (RepaymentPlanAdjustmentIntegration)redisCache.get(redisKey);
		result.setTotalNum(repaymentPlanAdjustmentIntegration.getPaidRepaymentPlanRedisDTOList().size());
		result.setData(repaymentPlanAdjustmentIntegration.getPaidRepaymentPlanRedisDTOList().subList(getStartIndex(page, pageSize), getEndIndex(page, pageSize, repaymentPlanAdjustmentIntegration.getPaidRepaymentPlanRedisDTOList())));
		Cat.logMetricForDuration("query_paid_repayment_plan_from_redis", System.currentTimeMillis()-startTime);
		return result;
	}

	@Override
	public ListResult<AccountRepaymentPlanRedisDTO> queryAccountRepaymentPlan(String redisKey, int page, int pageSize) throws Exception {
		long startTime = System.currentTimeMillis();
		ListResult<AccountRepaymentPlanRedisDTO> result = new ListResult<AccountRepaymentPlanRedisDTO>();
		RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration = (RepaymentPlanAdjustmentIntegration)redisCache.get(redisKey);
		result.setTotalNum(repaymentPlanAdjustmentIntegration.getAccountRepaymentPlanRedisDTOList().size());
		result.setData(repaymentPlanAdjustmentIntegration.getAccountRepaymentPlanRedisDTOList().subList(getStartIndex(page, pageSize), getEndIndex(page, pageSize, repaymentPlanAdjustmentIntegration.getAccountRepaymentPlanRedisDTOList())));
		Cat.logMetricForDuration("query_account_repayment_plan_from_redis", System.currentTimeMillis()-startTime);
		return result;
	}

	@Override
	public ListResult<PaidRepaymentExpenseRedisDTO> queryPaidRepaymentExpense(String redisKey, int page, int pageSize) throws Exception {
		ListResult<PaidRepaymentExpenseRedisDTO> result = new ListResult<PaidRepaymentExpenseRedisDTO>();
		RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration = (RepaymentPlanAdjustmentIntegration)redisCache.get(redisKey);
		result.setTotalNum(repaymentPlanAdjustmentIntegration.getPaidRepaymentExpenseRedisDTOList().size());
		result.setData(repaymentPlanAdjustmentIntegration.getPaidRepaymentExpenseRedisDTOList().subList(getStartIndex(page, pageSize), getEndIndex(page, pageSize, repaymentPlanAdjustmentIntegration.getPaidRepaymentExpenseRedisDTOList())));
		return result;
	}

	@Override
	public void paidRepaymentPlanAdjustment(String redisKey,PaidRepaymentPlanRedisDTO paidRepaymentPlanRedisDTO) throws Exception {
		long startTime = System.currentTimeMillis();
		RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration = (RepaymentPlanAdjustmentIntegration)redisCache.get(redisKey);
		RepaymentLoanInfo repaymentLoanInfo = repaymentLoanInfoMapper.selectByPrimaryKey(paidRepaymentPlanRedisDTO.getLoanInfoId());
		repaymentPlanAdjustmentIntegration.adjustPaidRepaymentPlan(paidRepaymentPlanRedisDTO, repaymentLoanInfo.getFinanceAmount(),MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidNormalPrincipal()),MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidOverduePrincipal()));
		repaymentPlanAdjustmentIntegration.judgePaidRepaymentPlanAdjustment(paidRepaymentPlanRedisDTO);
		Cat.logMetricForDuration("query_repayment_plan_from_redis", System.currentTimeMillis()-startTime);
		redisCache.save(redisKey, repaymentPlanAdjustmentIntegration, getRedisExpireTimeFromProperties());
	}

	@Override
	public PaidRepaymentPlanRedisDTO queryPaidRepaymentIncrementFromPlan(String redisKey, String planId) throws Exception {
		long startTime = System.currentTimeMillis();
		RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration = (RepaymentPlanAdjustmentIntegration)redisCache.get(redisKey);
		PaidRepaymentPlanRedisDTO paidPlanDTO = repaymentPlanAdjustmentIntegration.getSinglePaidRepaymentPlan(planId);
		RepaymentSingleDistributeDetailDTO detailDTO = repaymentPlanAdjustmentIntegration.getRepaymentSingleDistributeDetail(planId);
//		String periodStatus = paidPlanDTO.getPeriodStatus();
		//往期
//		if(StringUtils.equals(periodStatus, PeriodStatus.OVERDUE.name())){
			paidPlanDTO.setPaidNormalPrincipal(detailDTO.getPayNormalPrincipal());
			paidPlanDTO.setPaidNormalInterest(detailDTO.getPayNormalInterest());
			paidPlanDTO.setPaidNormalServiceCharge(detailDTO.getPayNormalServiceCharge());
			paidPlanDTO.setPaidOverdueManagerFee(detailDTO.getPayOtherPenalty());
			paidPlanDTO.setPaidOverdueServiceCharge(detailDTO.getPayOverdueServiceCharge());
			paidPlanDTO.setPaidOverdueInterest(detailDTO.getPayOverdueInterest());
			paidPlanDTO.setPaidOverduePrincipal(detailDTO.getPayOverduePrincipal());
			paidPlanDTO.setPaidExtensionCharge(detailDTO.getPayExtensionCharge());
			paidPlanDTO.setPaidEarlyRepaymentCharge(detailDTO.getEarlyRepaymentCharge());

//		}
		//往期展期
//		if(StringUtils.equals(periodStatus, PeriodStatus.EXTENSION.name())){
//			paidPlanDTO.setPaidExtensionCharge(detailDTO.getPayExtensionCharge());
//			paidPlanDTO.setPaidNormalServiceCharge(detailDTO.getPayNormalServiceCharge());
//			paidPlanDTO.setPaidNormalInterest(detailDTO.getPayNormalInterest());
//			paidPlanDTO.setPaidNormalPrincipal(detailDTO.getPayNormalPrincipal());
//		}
//		
//		if(StringUtils.equals(periodStatus, PeriodStatus.CURRENTIN.name()) || StringUtils.equals(periodStatus, PeriodStatus.CURRENTOUT.name())){
//			paidPlanDTO.setPaidEarlyRepaymentCharge(detailDTO.getEarlyRepaymentCharge());
//			paidPlanDTO.setPaidNormalServiceCharge(detailDTO.getPayNormalServiceCharge());
//			paidPlanDTO.setPaidNormalInterest(detailDTO.getPayNormalInterest());
//			paidPlanDTO.setPaidNormalPrincipal(detailDTO.getPayNormalPrincipal());
//		}
//		
//		if(StringUtils.equals(periodStatus, PeriodStatus.FUTURE.name())){
//			paidPlanDTO.setPaidNormalServiceCharge(detailDTO.getPayNormalServiceCharge());
//			paidPlanDTO.setPaidNormalInterest(detailDTO.getPayNormalInterest());
//			paidPlanDTO.setPaidNormalPrincipal(detailDTO.getPayNormalPrincipal());
//			paidPlanDTO.setPaidEarlyRepaymentCharge(detailDTO.getEarlyRepaymentCharge());
//		}
		
		Cat.logMetricForDuration("query_paid_repayment_increment_from_plan", System.currentTimeMillis()-startTime);
		return paidPlanDTO;
	}

	@Override
	public AccountRepaymentPlanRedisDTO queryAccountRepaymentPlanFromRedis(String redisKey, String planId)
			throws Exception {
		RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration = (RepaymentPlanAdjustmentIntegration)redisCache.get(redisKey);
		return repaymentPlanAdjustmentIntegration.getSingleAccountRepaymentPlan(planId);
	}
	
	@Override
	public void accountRepaymentPlanEdit(String redisKey,AccountRepaymentPlanRedisDTO accountRepaymentPlanRedisDTO) throws Exception {
		RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration = (RepaymentPlanAdjustmentIntegration)redisCache.get(redisKey);
		List<AccountRepaymentPlanRedisDTO> accountRepaymentPlanRedisDTOList = repaymentPlanAdjustmentIntegration.getAccountRepaymentPlanRedisDTOList();
		int index = accountRepaymentPlanRedisDTOList.indexOf(accountRepaymentPlanRedisDTO);
		if( index == -1 ){
			throw WebException.instance("未找到相应数据");
		}
		accountRepaymentPlanRedisDTO.computeTotalAmountCharge();
		AccountRepaymentPlanRedisDTO accountRepaymentPlanRedisDTOOld = accountRepaymentPlanRedisDTOList.get(index);
		accountRepaymentPlanRedisDTO.setPeriodStatus(accountRepaymentPlanRedisDTOOld.getPeriodStatus());
		accountRepaymentPlanRedisDTOList.set(index, accountRepaymentPlanRedisDTO);
		repaymentPlanAdjustmentIntegration.judgeAccountRepaymentPlanEdit(accountRepaymentPlanRedisDTO);
		redisCache.save(redisKey, repaymentPlanAdjustmentIntegration, getRedisExpireTimeFromProperties());
	}

	@Override
	public String getPlanAndExpenseToRedis(String operatorId, String loanInfoId, String masterId) throws Exception {
		String redisKey = generateRepaymentPlanInfoId();
		String redisKeyFull = RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey,operatorId);
		RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration = new RepaymentPlanAdjustmentIntegration();
		// 从数据库中获取还款计划信息
		RepaymentPlanExample repaymentPlanExample = new RepaymentPlanExample();
		repaymentPlanExample.createCriteria().andLoanInfoIdEqualTo(loanInfoId);
		repaymentPlanExample.setOrderByClause("repayment_period asc");
		List<RepaymentPlan> repaymentPlanList = repaymentPlanMapper.selectByExample(repaymentPlanExample);
		// 从数据库中获取还款费用信息
		RepaymentExpenseExample RepaymentExpenseExample = new RepaymentExpenseExample();
		RepaymentExpenseExample.createCriteria().andLoanInfoIdEqualTo(loanInfoId);
		List<RepaymentExpense> repaymentExpenseList = repaymentExpenseMapper.selectByExample(RepaymentExpenseExample);
		List<PaidRepaymentPlanRedisDTO> paidRepaymentPlanRedisDTOList = new ArrayList<PaidRepaymentPlanRedisDTO>();
		List<AccountRepaymentPlanRedisDTO> accountRepaymentPlanRedisDTOList = new ArrayList<AccountRepaymentPlanRedisDTO>();
		List<PaidRepaymentPlanRedisDTO> originPaidRepaymentPlanRedisDTOList = new ArrayList<PaidRepaymentPlanRedisDTO>();
		List<AccountRepaymentPlanRedisDTO> originAccountRepaymentPlanRedisDTOList = new ArrayList<AccountRepaymentPlanRedisDTO>();
		List<PaidRepaymentExpenseRedisDTO> paidRepaymentExpenseRedisDTOList = new ArrayList<PaidRepaymentExpenseRedisDTO>();
		List<PaidRepaymentExpenseRedisDTO> originPaidRepaymentExpenseRedisDTOList = new ArrayList<PaidRepaymentExpenseRedisDTO>();
		// 从数据库中获取放款信息
		RepaymentLoanInfo repaymentLoanInfo = repaymentLoanInfoMapper.selectByPrimaryKey(loanInfoId);
		if(repaymentLoanInfo==null){
			throw WebException.instance("放款信息不存在");
		}
		if(!StringUtils.equals(masterId, repaymentLoanInfo.getFactorId())){
			throw WebException.instance("当前用户无权访问当前还款计划，还款失败！");
		}
		repaymentPlanAdjustmentIntegration.setLoanInfoVersion(repaymentLoanInfo.getCurrentVersion());
//		repaymentPlanAdjustmentIntegration.setRepaymentExpenseVersion(repaymentLoanInfo.getRepaymentExpenseVersion());
		
		// 从数据库中获取还款规则
		LoanInfoConfig loanInfoConfig = loanInfoConfigMapper.selectByPrimaryKey(loanInfoId);
		if(loanInfoConfig==null){
			throw WebException.instance("放款信息配置不存在");
		}
		// 获取还款计划已还部分
		getPaidRepaymentPlanToRedis(repaymentPlanList, paidRepaymentPlanRedisDTOList, repaymentLoanInfo.getLoanDate());
		// 获取还款计划未还部分
		getAccountRepaymentPlanToRedis(repaymentPlanList, accountRepaymentPlanRedisDTOList, repaymentLoanInfo.getLoanDate());
		// 获取还款费用已还部分
		getPaidRepaymentExpenseToRedis(repaymentExpenseList, paidRepaymentExpenseRedisDTOList);
		// 保存所有数据到redis中
		repaymentPlanAdjustmentIntegration.setPaidRepaymentPlanRedisDTOList(paidRepaymentPlanRedisDTOList);
		// 实现备份原始数据
		repaymentPlanAdjustmentIntegration.copyPaidRepaymentPlanListToOrigin(paidRepaymentPlanRedisDTOList, originPaidRepaymentPlanRedisDTOList);
		repaymentPlanAdjustmentIntegration.copyAccountRepaymentPlanListToOrigin(accountRepaymentPlanRedisDTOList, originAccountRepaymentPlanRedisDTOList);
		repaymentPlanAdjustmentIntegration.copyPaidRepaymentExpenseListToOrigin(paidRepaymentExpenseRedisDTOList, originPaidRepaymentExpenseRedisDTOList);
		repaymentPlanAdjustmentIntegration.setOriginPaidRepaymentPlanRedisDTOList(originPaidRepaymentPlanRedisDTOList);
		repaymentPlanAdjustmentIntegration.setOriginAccountRepaymentPlanRedisDTOList(originAccountRepaymentPlanRedisDTOList);
		repaymentPlanAdjustmentIntegration.setOriginPaidRepaymentExpenseRedisDTOList(originPaidRepaymentExpenseRedisDTOList);
		repaymentPlanAdjustmentIntegration.setAccountRepaymentPlanRedisDTOList(accountRepaymentPlanRedisDTOList);
		repaymentPlanAdjustmentIntegration.setPaidRepaymentExpenseRedisDTOList(paidRepaymentExpenseRedisDTOList);
		// 初始化List
		repaymentPlanAdjustmentIntegration.setRepaymentSingleDistributeDetailList(new ArrayList<RepaymentSingleDistributeDetailDTO>());
		repaymentPlanAdjustmentIntegration.setPlanAndExpenseRepaymentSummaryList(new ArrayList<RepaymentSingleCollectDTO>());
		// 计算当前最高应还总金额
		repaymentPlanAdjustmentIntegration.calculateAccountTotalMoney(loanInfoConfig.getEarlyRepaymentChargeRatio());
		redisCache.save(redisKeyFull, repaymentPlanAdjustmentIntegration, getRedisExpireTimeFromProperties());
		return redisKey;
	}

	@Override
	public void calculateRepaymentExpense(String redisKey,String expenseId, String paidAmount,String repaymentDate) throws Exception {
		RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration = (RepaymentPlanAdjustmentIntegration)redisCache.get(redisKey);
		List<PaidRepaymentExpenseRedisDTO> paidRepaymentExpenseRedisDTOList = repaymentPlanAdjustmentIntegration.getPaidRepaymentExpenseRedisDTOList();
		int index = -1;
		for(PaidRepaymentExpenseRedisDTO paidRepaymentExpenseRedisDTO:paidRepaymentExpenseRedisDTOList){
			if(StringUtils.equals(paidRepaymentExpenseRedisDTO.getId(), expenseId)){
				index = paidRepaymentExpenseRedisDTOList.indexOf(paidRepaymentExpenseRedisDTO);
				break;
			}
		}
		if(index == -1){
			throw WebException.instance("未找到相应数据");
		}
		PaidRepaymentExpenseRedisDTO paidRepaymentExpenseRedisDTO = paidRepaymentExpenseRedisDTOList.get(index);
		// 修改还款费用已还金额
		paidRepaymentExpenseRedisDTO.setPaidAmount(MoneyArithUtil.convertMoneyToString(MoneyArithUtil.convertStringToMoney(paidAmount)+MoneyArithUtil.convertStringToMoney(paidRepaymentExpenseRedisDTO.getPaidAmount())));
		List<RepaymentSingleCollectDTO> planAndExpenseRepaymentSummaryList = repaymentPlanAdjustmentIntegration.getPlanAndExpenseRepaymentSummaryList();
		if(CollectionUtils.isEmpty(planAndExpenseRepaymentSummaryList)){
			planAndExpenseRepaymentSummaryList = new ArrayList<RepaymentSingleCollectDTO>();
		}
		// 设置还款汇总数据
		RepaymentSingleCollectDTO repaymentSingleCollectDTO = new RepaymentSingleCollectDTO();
		repaymentSingleCollectDTO.setId(generateSingleCollectId());
		repaymentSingleCollectDTO.setChargeType(ChargeType.REPAYMENTEXPENSE.name());
		repaymentSingleCollectDTO.setRepaymentDate(repaymentDate);
		repaymentSingleCollectDTO.setLoanInfoId(paidRepaymentExpenseRedisDTO.getLoanInfoId());
		repaymentSingleCollectDTO.setRepaymentType(RepaymentMethod.OFFLINE.name());
		repaymentSingleCollectDTO.setRepaymentTotalAmount(paidAmount);
		planAndExpenseRepaymentSummaryList.add(repaymentSingleCollectDTO);
		repaymentPlanAdjustmentIntegration.setPlanAndExpenseRepaymentSummaryList(planAndExpenseRepaymentSummaryList);
		// 设置还款明细信息
		List<RepaymentSingleDistributeDetailDTO> repaymentSingleDistributeDetailList = repaymentPlanAdjustmentIntegration.getRepaymentSingleDistributeDetailList();
		if(CollectionUtils.isEmpty(repaymentSingleDistributeDetailList)){
			repaymentSingleDistributeDetailList = new ArrayList<RepaymentSingleDistributeDetailDTO>();
		}
		RepaymentSingleDistributeDetailDTO repaymentSingleDistributeDetailDTO = new RepaymentSingleDistributeDetailDTO();
		repaymentSingleDistributeDetailDTO.setRelatedId(expenseId);
		repaymentSingleDistributeDetailDTO.setRepaymentSingleCollectId(repaymentSingleCollectDTO.getId());
		repaymentSingleDistributeDetailDTO.setId(generateSingleDistributeDetailId());
		repaymentSingleDistributeDetailDTO.setPayNormalExpense(paidAmount);
		repaymentSingleDistributeDetailDTO.setRelatedType(ChargeType.REPAYMENTEXPENSE.name());
		repaymentSingleDistributeDetailList.add(repaymentSingleDistributeDetailDTO);
		repaymentPlanAdjustmentIntegration.setRepaymentSingleDistributeDetailList(repaymentSingleDistributeDetailList);
		repaymentPlanAdjustmentIntegration.judgeRepaymentExpenseEdit(paidRepaymentExpenseRedisDTO);
		repaymentPlanAdjustmentIntegration.setChangeId(repaymentSingleCollectDTO.getId());
		redisCache.save(redisKey, repaymentPlanAdjustmentIntegration, getRedisExpireTimeFromProperties());
	}

	@Override
	public void accountRepaymentExpenseEdit(String redisKey,String expenseId, String accountExpenseAmount) throws Exception {
		PaidRepaymentExpenseRedisDTO paidRepaymentExpenseRedisDTO = new PaidRepaymentExpenseRedisDTO();
		paidRepaymentExpenseRedisDTO.setId(expenseId);
		RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration = (RepaymentPlanAdjustmentIntegration)redisCache.get(redisKey);
		List<PaidRepaymentExpenseRedisDTO> paidRepaymentExpenseRedisDTOList = repaymentPlanAdjustmentIntegration.getPaidRepaymentExpenseRedisDTOList();
		int index = paidRepaymentExpenseRedisDTOList.indexOf(paidRepaymentExpenseRedisDTO);
		if( index < -1 ){
			throw WebException.instance("未找到相应数据");
		}
		paidRepaymentExpenseRedisDTO = paidRepaymentExpenseRedisDTOList.get(index);
		paidRepaymentExpenseRedisDTO.setRepaymentAmount(accountExpenseAmount);
		if(0 == MoneyArithUtil.convertStringToMoney(accountExpenseAmount)){
			paidRepaymentExpenseRedisDTO.setSettleStatus(SettleStatus.SETTLED.desc());
		} else{ 
			paidRepaymentExpenseRedisDTO.setSettleStatus(SettleStatus.UNSETTLE.desc());
		}
		paidRepaymentExpenseRedisDTOList.set(index, paidRepaymentExpenseRedisDTO);
		repaymentPlanAdjustmentIntegration.judgeRepaymentExpenseEdit(paidRepaymentExpenseRedisDTO);
		redisCache.save(redisKey, repaymentPlanAdjustmentIntegration, getRedisExpireTimeFromProperties());
	}

	@Override
	public ObjectResult<String> autoAllocationCharge(String redisKey, String loanInfoId,String charge) throws Exception {
		 ObjectResult<String> objectResult = new ObjectResult<>();
		LoanInfoConfig loanConfig = loanInfoConfigMapper.selectByPrimaryKey(loanInfoId);
		RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration = (RepaymentPlanAdjustmentIntegration)redisCache.get(redisKey);
		ChargeDTO chargeDTO = new ChargeDTO();
		chargeDTO.setCharge(charge);
		if(chargeDTO.getCharge() == 0){
			repaymentPlanAdjustmentIntegration.back2Origin();
			
		}else{
			if(chargeDTO.getCharge() > repaymentPlanAdjustmentIntegration.getAccountTotalMoney()){
				objectResult.setStatus(AjaxResponseStatus.FAILED);
//				objectResult.setMessage("当前还款超过应还总金额，应还总金额为"+MoneyUtil.formatMoney(
//						MoneyArithUtil.convertMoneyToString(repaymentPlanAdjustmentIntegration.getAccountTotalMoney())));
				objectResult.setMessage("当前还款金额已超过全部应还总金额，请核对后重新还款！");
				LGR.warn("当前还款超过应还总金额，应还总金额为"+MoneyUtil.formatMoney(
						MoneyArithUtil.convertMoneyToString(repaymentPlanAdjustmentIntegration.getAccountTotalMoney())));
				return objectResult;
			}
			repaymentPlanServiceImpl.distributePlanWithRepayment(repaymentPlanAdjustmentIntegration,
					chargeDTO, loanConfig.getEarlyRepaymentChargeRatio());
		}
		repaymentPlanAdjustmentIntegration.judgePaidRepaymentForAll();
		redisCache.save(redisKey, repaymentPlanAdjustmentIntegration, getRedisExpireTimeFromProperties());
		objectResult.setStatus(AjaxResponseStatus.SUCCESS);
		return objectResult;
	}

	@Override
	public void saveRepaymentSummaryAndDetailToDB(String redisKey,String loanInfoId, String operatorId, String flag,String changeType) throws Exception {
		RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration = (RepaymentPlanAdjustmentIntegration)redisCache.get(redisKey);
		OfflineRepaymentData offlineRepaymentData = new OfflineRepaymentData();
		offlineRepaymentData.setPaidRepaymentPlanRedisDTOList(repaymentPlanAdjustmentIntegration.getPaidRepaymentPlanRedisDTOList());
		offlineRepaymentData.setAccountRepaymentPlanRedisDTOList(repaymentPlanAdjustmentIntegration.getAccountRepaymentPlanRedisDTOList());
		offlineRepaymentData.setPaidRepaymentExpenseRedisDTOList(repaymentPlanAdjustmentIntegration.getPaidRepaymentExpenseRedisDTOList());
		offlineRepaymentData.setRepaymentSingleCollectDTOList(repaymentPlanAdjustmentIntegration.getPlanAndExpenseRepaymentSummaryList());
		offlineRepaymentData.setRepaymentSingleDistributeDetailDTOList(repaymentPlanAdjustmentIntegration.getRepaymentSingleDistributeDetailList());
		offlineRepaymentData.setLoanInfoId(loanInfoId);
		offlineRepaymentData.setLoanInfoOldVersion(repaymentPlanAdjustmentIntegration.getLoanInfoVersion());
//		offlineRepaymentData.setRepaymentExpenseOldVersion(repaymentPlanAdjustmentIntegration.getRepaymentExpenseVersion());
		offlineRepaymentData.setLoanInfoVersion(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_LOAN_INFO_VERSION, 4));
//		offlineRepaymentData.setRepaymentExpenseVersion(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_EXPENSE_VERSION, 4));
		// 还款计划转换
		offlineRepaymentData.repaymentPlanConvert();
		// 还款费用转换
		offlineRepaymentData.repaymentExpenseConvert();
		// 汇总数据转换
		offlineRepaymentData.repaymentSingleCollectConvert();
		// 明细数据转换
		offlineRepaymentData.repaymentSingleDistributeDetailConvert();
		// 设置变更Id
		offlineRepaymentData.setChangeId(repaymentPlanAdjustmentIntegration.getChangeId());
		//设置变更类型
		if(StringUtils.equals(LoanInfoChangeType.adjust.name(), changeType)){
			offlineRepaymentData.setChangeType(LoanInfoChangeType.adjust.name());
		}else if(StringUtils.equals(LoanInfoChangeType.offline_repayment.name(), changeType)){
			offlineRepaymentData.setChangeType(LoanInfoChangeType.offline_repayment.name());
		}
		repaymentAdjustmentTransactionService.updatePlanAndExpenseAndSaveRepaymentDetail(offlineRepaymentData);
		// 更新放款信息冗余数据
//		repaymentTransactionService.updateLoanInfoRedundancyField(loanInfoId);
		
		//调账消息发送
		if(StringUtils.isNotBlank(flag) && flag.equals("0")){
			RepaymentLoanInfo repaymentLoanInfo = repaymentLoanInfoMapper.selectByPrimaryKey(loanInfoId);
			if(StringUtils.isNotBlank(repaymentLoanInfo.getFinanceId())){
				messageServiceImpl.sendAdjustMessage(operatorId, repaymentLoanInfo.getFactorCompany(), repaymentLoanInfo.getLoanNo(), loanInfoId, repaymentLoanInfo.getFinanceId());
			}
		}
	}
	
	@Override
	public ListResult<RepaymentSingleDistributeDetailDTO> queryHistoryRepaymentDetailFromDB(
			String planId) throws Exception {
		long startTime = System.currentTimeMillis();
		ListResult<RepaymentSingleDistributeDetailDTO> result = new ListResult<RepaymentSingleDistributeDetailDTO>();
		RepaymentSingleDistributeDetailExample repaymentSingleDistributeDetailExample = new RepaymentSingleDistributeDetailExample();
		repaymentSingleDistributeDetailExample.createCriteria().andRelatedIdEqualTo(planId);
		List<RepaymentSingleDistributeDetail> repaymentSingleDistributeDetailList = repaymentSingleDistributeDetailMapper.selectByExample(repaymentSingleDistributeDetailExample);
		List<RepaymentSingleDistributeDetailDTO> repaymentSingleDistributeDetailDTOList = new ArrayList<RepaymentSingleDistributeDetailDTO>();
		for(RepaymentSingleDistributeDetail repaymentSingleDistributeDetail:repaymentSingleDistributeDetailList){
			RepaymentSingleDistributeDetailDTO repaymentSingleDistributeDetailDTO = new RepaymentSingleDistributeDetailDTO();
			BeanUtils.copyProperties(repaymentSingleDistributeDetail, repaymentSingleDistributeDetailDTO);
			repaymentSingleDistributeDetailDTOList.add(repaymentSingleDistributeDetailDTO);
		}
		result.setData(repaymentSingleDistributeDetailDTOList);
		Cat.logMetricForDuration("query_history_repayment_detail_from_DB", System.currentTimeMillis()-startTime);
		return result;
	}

	@Override
	public void submitValidate(String redisKey)	throws Exception {
		List<PaidRepaymentPlanRedisDTO> repaymentPlanList = new ArrayList<PaidRepaymentPlanRedisDTO>();
		List<PaidRepaymentExpenseRedisDTO> repaymentExpenseList = new ArrayList<PaidRepaymentExpenseRedisDTO>();
		List<AccountRepaymentPlanRedisDTO> accountRepaymentPlanList = new ArrayList<AccountRepaymentPlanRedisDTO>();
		RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration = (RepaymentPlanAdjustmentIntegration)redisCache.get(redisKey);
		List<PaidRepaymentPlanRedisDTO> paidRepaymentPlanRedisDTOList = repaymentPlanAdjustmentIntegration.getPaidRepaymentPlanRedisDTOList();
		List<PaidRepaymentExpenseRedisDTO> paidRepaymentExpenseRedisDTOList = repaymentPlanAdjustmentIntegration.getPaidRepaymentExpenseRedisDTOList();
		long totalAmount = 0l;
		// 判断还款计划是否调整
		for(PaidRepaymentPlanRedisDTO paidRepaymentPlanRedisDTO:paidRepaymentPlanRedisDTOList){
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidNormalPrincipalNew())){
				repaymentPlanList.add(paidRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidNormalInterestNew())){
				repaymentPlanList.add(paidRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidNormalServiceChargeNew())){
				repaymentPlanList.add(paidRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidOverduePrincipalNew())){
				repaymentPlanList.add(paidRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidOverdueInterestNew())){
				repaymentPlanList.add(paidRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidOverdueServiceChargeNew())){
				repaymentPlanList.add(paidRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidExtensionChargeNew())){
				repaymentPlanList.add(paidRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidOverdueManagerFeeNew())){
				repaymentPlanList.add(paidRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidEarlyRepaymentChargeNew())){
				repaymentPlanList.add(paidRepaymentPlanRedisDTO);
				break;
			}
		}
		boolean editFlag = false;
		
		// 判断费用列表是否调整
		for(PaidRepaymentExpenseRedisDTO paidRepaymentExpenseRedisDTO:paidRepaymentExpenseRedisDTOList){
			if(StringUtils.isNotBlank(paidRepaymentExpenseRedisDTO.getPaidAmountNew())){
				repaymentExpenseList.add(paidRepaymentExpenseRedisDTO);
				editFlag = true;
				break;
			}
			if(StringUtils.isNotBlank(paidRepaymentExpenseRedisDTO.getRepaymentAmountNew())){
				repaymentExpenseList.add(paidRepaymentExpenseRedisDTO);
				editFlag = true;
				break;
			}
		}
		List<AccountRepaymentPlanRedisDTO> accountRepaymentPlanRedisDTOList = repaymentPlanAdjustmentIntegration.getAccountRepaymentPlanRedisDTOList();
		for(AccountRepaymentPlanRedisDTO accountRepaymentPlanRedisDTO : accountRepaymentPlanRedisDTOList) {  
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getAccountPrincipalNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				editFlag = true;
				break;
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getAccountInterestNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				editFlag = true;
				break;
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getAccountServiceChargeNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				editFlag = true;
				break;
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getOverduePrincipalNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				editFlag = true;
				break;
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getOverdueInterestNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				editFlag = true;
				break;
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getOverdueServiceChargeNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				editFlag = true;
				break;
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getExtensionChargeNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				editFlag = true;
				break;
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getOverdueManagerFeeNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				editFlag = true;
				break;
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getPaidEarlyRepaymentChargeNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				editFlag = true;
				break;
			}
		}
		if(CollectionUtils.isEmpty(repaymentPlanList) && CollectionUtils.isEmpty(repaymentExpenseList) && CollectionUtils.isEmpty(accountRepaymentPlanList) ){
			throw WebException.instance("您未调整修改任何还款计划或费用，请调整后再提交！");
		}
		if(!editFlag){
			throw WebException.instance("您已调整还款计划，请修改相应新还款计划后再提交！");
		}
		RepaymentLoanInfo repaymentLoanInfo = repaymentLoanInfoMapper.selectByPrimaryKey(paidRepaymentPlanRedisDTOList.get(0).getLoanInfoId());
		// 获取已还本金之和
		for(PaidRepaymentPlanRedisDTO paidRepaymentPlanRedisDTO:paidRepaymentPlanRedisDTOList){
			totalAmount += MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidNormalPrincipal());
			totalAmount += MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidOverduePrincipal());
		}
		// 获取应还本金之和
		for(AccountRepaymentPlanRedisDTO accountRepaymentPlanRedisDTO : accountRepaymentPlanRedisDTOList) {
			totalAmount += MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getAccountPrincipal());
			totalAmount += MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getOverduePrincipal());
		}
		if(repaymentLoanInfo.getFinanceAmount() != totalAmount){
			throw WebException.instance("应还本金和已还本金之和与融资金额不相等，请核对后再提交");
		}
	}
	
	@Override
	public void redirectValidate(String redisKey) throws Exception {
		List<PaidRepaymentPlanRedisDTO> repaymentPlanList = new ArrayList<PaidRepaymentPlanRedisDTO>();
		List<AccountRepaymentPlanRedisDTO> accountRepaymentPlanList = new ArrayList<AccountRepaymentPlanRedisDTO>();
		List<PaidRepaymentExpenseRedisDTO> repaymentExpenseList = new ArrayList<PaidRepaymentExpenseRedisDTO>();
		RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration = (RepaymentPlanAdjustmentIntegration)redisCache.get(redisKey);
		List<PaidRepaymentPlanRedisDTO> paidRepaymentPlanRedisDTOList = repaymentPlanAdjustmentIntegration.getPaidRepaymentPlanRedisDTOList();
		List<AccountRepaymentPlanRedisDTO> accountRepaymentPlanRedisDTOList = repaymentPlanAdjustmentIntegration.getAccountRepaymentPlanRedisDTOList();
		List<PaidRepaymentExpenseRedisDTO> paidRepaymentExpenseRedisDTOList = repaymentPlanAdjustmentIntegration.getPaidRepaymentExpenseRedisDTOList();
		// 判断还款计划是否调整
		for(PaidRepaymentPlanRedisDTO paidRepaymentPlanRedisDTO:paidRepaymentPlanRedisDTOList){
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidNormalPrincipalNew())){
				repaymentPlanList.add(paidRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidNormalInterestNew())){
				repaymentPlanList.add(paidRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidNormalServiceChargeNew())){
				repaymentPlanList.add(paidRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidOverduePrincipalNew())){
				repaymentPlanList.add(paidRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidOverdueInterestNew())){
				repaymentPlanList.add(paidRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidOverdueServiceChargeNew())){
				repaymentPlanList.add(paidRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidExtensionChargeNew())){
				repaymentPlanList.add(paidRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidOverdueManagerFeeNew())){
				repaymentPlanList.add(paidRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidEarlyRepaymentChargeNew())){
				repaymentPlanList.add(paidRepaymentPlanRedisDTO);
				break;
			}
		}
		
		// 判断是否修改计划
		for(AccountRepaymentPlanRedisDTO accountRepaymentPlanRedisDTO:accountRepaymentPlanRedisDTOList){
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getAccountPrincipalNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getAccountInterestNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getAccountServiceChargeNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getOverduePrincipalNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getOverdueInterestNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getOverdueServiceChargeNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getExtensionChargeNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getOverdueManagerFeeNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getPaidEarlyRepaymentChargeNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getAccountTotalAmountNew())){
				accountRepaymentPlanList.add(accountRepaymentPlanRedisDTO);
				break;
			}
		}
		
		// 判断费用列表是否调整
		for(PaidRepaymentExpenseRedisDTO paidRepaymentExpenseRedisDTO:paidRepaymentExpenseRedisDTOList){
			if(StringUtils.isNotBlank(paidRepaymentExpenseRedisDTO.getPaidAmountNew())){
				repaymentExpenseList.add(paidRepaymentExpenseRedisDTO);
				break;
			}
			if(StringUtils.isNotBlank(paidRepaymentExpenseRedisDTO.getRepaymentAmountNew())){
				repaymentExpenseList.add(paidRepaymentExpenseRedisDTO);
				break;
			}
		}
		
		if(CollectionUtils.isEmpty(repaymentPlanList) && CollectionUtils.isEmpty(repaymentExpenseList) && CollectionUtils.isEmpty(accountRepaymentPlanList)){
			throw WebException.instance("您未调整修改任何还款计划或费用，请调整后再提交！");
		}
	}
	
	// 获取还款计划已还部分
	private void getPaidRepaymentPlanToRedis(List<RepaymentPlan> repaymentPlanList,List<PaidRepaymentPlanRedisDTO> paidRepaymentPlanRedisDTOList,String loanDate){
		String lastRepaymentDate = loanDate;
		for(RepaymentPlan repaymentPlan:repaymentPlanList){
			PaidRepaymentPlanRedisDTO paidRepaymentPlanRedisDTO = new PaidRepaymentPlanRedisDTO();
			BeanUtils.copyProperties(repaymentPlan, paidRepaymentPlanRedisDTO);
			paidRepaymentPlanRedisDTO.setPeriodStatus(repaymentPlanServiceImpl.judgePlanPeriodByNowDate(lastRepaymentDate,
					paidRepaymentPlanRedisDTO.getRepaymentDate(), repaymentPlan.getExtensionDays()));
			paidRepaymentPlanRedisDTO.setRepaymentPeriod(repaymentPlan.getRepaymentPeriod().toString());
			paidRepaymentPlanRedisDTO.setAccountPrincipal(MoneyArithUtil.convertMoneyToString(repaymentPlan.getAccountPrincipal()));
			paidRepaymentPlanRedisDTO.setPaidNormalPrincipal(MoneyArithUtil.convertMoneyToString(repaymentPlan.getPaidNormalPrincipal()));
			paidRepaymentPlanRedisDTO.setPaidNormalInterest(MoneyArithUtil.convertMoneyToString(repaymentPlan.getPaidNormalInterest()));
			paidRepaymentPlanRedisDTO.setPaidNormalServiceCharge(MoneyArithUtil.convertMoneyToString(repaymentPlan.getPaidNormalServiceCharge()));
			paidRepaymentPlanRedisDTO.setPaidOverduePrincipal(MoneyArithUtil.convertMoneyToString(repaymentPlan.getPaidOverduePrincipal()));
			paidRepaymentPlanRedisDTO.setPaidOverdueInterest(MoneyArithUtil.convertMoneyToString(repaymentPlan.getPaidOverdueInterest()));
			paidRepaymentPlanRedisDTO.setPaidOverdueServiceCharge(MoneyArithUtil.convertMoneyToString(repaymentPlan.getPaidOverdueServiceCharge()));
			paidRepaymentPlanRedisDTO.setPaidExtensionCharge(MoneyArithUtil.convertMoneyToString(repaymentPlan.getPaidExtensionCharge()));
			paidRepaymentPlanRedisDTO.setPaidOverdueManagerFee(MoneyArithUtil.convertMoneyToString(repaymentPlan.getPaidOtherPenalty()+repaymentPlan.getPaidOverdueServiceChargePenalty()
				+repaymentPlan.getPaidOverdueInterestPenalty()+repaymentPlan.getPaidOverduePrincipalPenalty()));
			paidRepaymentPlanRedisDTO.setPaidEarlyRepaymentCharge(MoneyArithUtil.convertMoneyToString(repaymentPlan.getPaidEarlyRepaymentCharge()));
			paidRepaymentPlanRedisDTO.computeTotalAmountCharge();
			paidRepaymentPlanRedisDTO.setSettleStatusForPage(SettleStatus.valueOf(repaymentPlan.getSettleStatus()).desc());
			paidRepaymentPlanRedisDTOList.add(paidRepaymentPlanRedisDTO);
			lastRepaymentDate = paidRepaymentPlanRedisDTO.getRepaymentDate();
		}
	}

	// 获取还款计划未还部分
	private void getAccountRepaymentPlanToRedis(List<RepaymentPlan> repaymentPlanList, List<AccountRepaymentPlanRedisDTO> accountRepaymentPlanRedisDTOList, String loanDate){
		String lastRepaymentDate = loanDate;
		for(RepaymentPlan repaymentPlan:repaymentPlanList){
			AccountRepaymentPlanRedisDTO accountRepaymentPlanRedisDTO = new AccountRepaymentPlanRedisDTO();
			BeanUtils.copyProperties(repaymentPlan, accountRepaymentPlanRedisDTO);
			accountRepaymentPlanRedisDTO.setPeriodStatus(repaymentPlanServiceImpl.judgePlanPeriodByNowDate(lastRepaymentDate,
					accountRepaymentPlanRedisDTO.getRepaymentDate(), repaymentPlan.getExtensionDays()));
			accountRepaymentPlanRedisDTO.setRepaymentPeriod(repaymentPlan.getRepaymentPeriod().toString());
			accountRepaymentPlanRedisDTO.setFinanceBalance(MoneyArithUtil.convertMoneyToString(repaymentPlan.getFinanceBalance()));
			accountRepaymentPlanRedisDTO.setAccountPrincipal(MoneyArithUtil.convertMoneyToString(repaymentPlan.getAccountPrincipal()));
			accountRepaymentPlanRedisDTO.setAccountInterest(MoneyArithUtil.convertMoneyToString(repaymentPlan.getAccountInterest()));
			accountRepaymentPlanRedisDTO.setAccountServiceCharge(MoneyArithUtil.convertMoneyToString(repaymentPlan.getAccountServiceCharge()));
			accountRepaymentPlanRedisDTO.setOverduePrincipal(MoneyArithUtil.convertMoneyToString(repaymentPlan.getOverduePrincipal()));
			accountRepaymentPlanRedisDTO.setOverdueInterest(MoneyArithUtil.convertMoneyToString(repaymentPlan.getOverdueInterest()));
			accountRepaymentPlanRedisDTO.setOverdueServiceCharge(MoneyArithUtil.convertMoneyToString(repaymentPlan.getOverdueServiceCharge()));
			accountRepaymentPlanRedisDTO.setExtensionCharge(MoneyArithUtil.convertMoneyToString(repaymentPlan.getAccountExtensionCharge()));
			accountRepaymentPlanRedisDTO.setOverdueManagerFee(MoneyArithUtil.convertMoneyToString(repaymentPlan.getOtherPenalty()+repaymentPlan.getOverdueServiceChargePenalty()
			+repaymentPlan.getOverdueInterestPenalty()+repaymentPlan.getOverduePrincipalPenalty()));
			accountRepaymentPlanRedisDTO.setPaidEarlyRepaymentCharge(MoneyArithUtil.convertMoneyToString(repaymentPlan.getPaidEarlyRepaymentCharge()));
			accountRepaymentPlanRedisDTO.setSettleStatus(SettleStatus.getDesc(repaymentPlan.getSettleStatus()).desc());
			accountRepaymentPlanRedisDTO.setSettleStatusForPage(SettleStatus.valueOf(repaymentPlan.getSettleStatus()).desc());
			accountRepaymentPlanRedisDTO.computeTotalAmountCharge();
			accountRepaymentPlanRedisDTOList.add(accountRepaymentPlanRedisDTO);
		
			lastRepaymentDate = accountRepaymentPlanRedisDTO.getRepaymentDate();
		}
	}
	
	// 获取还款费用
	private void getPaidRepaymentExpenseToRedis(List<RepaymentExpense> repaymentPlanList,List<PaidRepaymentExpenseRedisDTO> paidRepaymentExpenseRedisDTOList){
		for(RepaymentExpense repaymentExpense:repaymentPlanList){
			PaidRepaymentExpenseRedisDTO paidRepaymentExpenseRedisDTO = new PaidRepaymentExpenseRedisDTO();
			BeanUtils.copyProperties(repaymentExpense, paidRepaymentExpenseRedisDTO);
			paidRepaymentExpenseRedisDTO.setPaidAmount(MoneyArithUtil.convertMoneyToString(repaymentExpense.getPaidAmount()));
			if(StringUtils.isNotBlank(repaymentExpense.getChargeMethod())){
				paidRepaymentExpenseRedisDTO.setChargeStandard(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(repaymentExpense.getChargeStandard())));
				paidRepaymentExpenseRedisDTO.setChargeMethod(ChargeMethod.valueOf(repaymentExpense.getChargeMethod()).desc());
				if(StringUtils.equals(ChargeMethod.AMOUNT.name(), repaymentExpense.getChargeMethod())){
					paidRepaymentExpenseRedisDTO.setChargeValue(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(repaymentExpense.getChargeAmount())));
				}else{
					paidRepaymentExpenseRedisDTO.setChargeValue(MoneyArithUtil.convertInterestRateToString(repaymentExpense.getChargeRatio()));
				}
			}else{
				paidRepaymentExpenseRedisDTO.setChargeValue("");
			}
			paidRepaymentExpenseRedisDTO.setRepaymentAmount(MoneyArithUtil.convertMoneyToString(repaymentExpense.getRepaymentAmount()));
			paidRepaymentExpenseRedisDTO.setSettleStatus(SettleStatus.getDesc(repaymentExpense.getSettleStatus()).desc());
			paidRepaymentExpenseRedisDTO.setSettleStatusForPage(SettleStatus.valueOf(repaymentExpense.getSettleStatus()).desc());
			paidRepaymentExpenseRedisDTOList.add(paidRepaymentExpenseRedisDTO);
		}
	}
	
	/**
	 * 生成id
	 * @return
	 * @throws Exception
	 */
	private String generateRepaymentPlanInfoId() throws Exception{
		return DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.REPAYMENT_ADJUSTMENT_ID, 4);
	}
	
	/**
	 * 生成汇总信息表id
	 * @return
	 * @throws Exception
	 */
	private String generateSingleCollectId() throws Exception{
		return DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_SINGLE_COLLECT_ID, 4);
	}

	/**
	 * 生成还款信息详细表id
	 * @return
	 * @throws Exception
	 */
	private String generateSingleDistributeDetailId() throws Exception{
		return DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_SINGLE_DISTRIBUTE_DETAIL_ID, 4);
	}
	
	private int getRedisExpireTimeFromProperties(){
		return Integer.parseInt(TopsConfReader.getConfContent("properties/repayment-common.properties", "redis.temp.timeout", ConfScope.G));
	}
	
	private int getStartIndex(int page, int pageSize) {
		return (page - 1) * pageSize;
	}

	private int getEndIndex(int page, int pageSize, List<?> curList) {
		return page * pageSize < curList.size() ? page * pageSize : curList
				.size();
	}

}
