package com.cana.repayment.server.api.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;

import com.cana.account.api.IAccountApi;
import com.cana.member.api.IUserApi;
import com.cana.repayment.api.IActiveRepaymentApi;
import com.cana.repayment.dao.mapper.IRepaymentPlanAndExpenseSearchMapper;
import com.cana.repayment.dao.mapper.gen.LoanInfoConfigMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentExpenseMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPlanMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentSingleCollectMapper;
import com.cana.repayment.dao.po.LoanInfoConfig;
import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentExpenseExample;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.dao.po.RepaymentPlanExample;
import com.cana.repayment.dao.po.RepaymentPlanExample.Criteria;
import com.cana.repayment.dao.po.manual.ActiveRepaymentExpense;
import com.cana.repayment.dao.po.manual.ActiveRepaymentPlan;
import com.cana.repayment.service.IActiveRepaymentService;
import com.cana.repayment.service.ILoanInfoService;
import com.cana.repayment.service.IMessageService;
import com.cana.repayment.service.IRepaymentPlanService;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.repayment.service.data.OfflineRepaymentData;
import com.cana.repayment.service.handler.RepaymentCalcFactory;
import com.cana.repayment.service.transaction.IActiveRepaymentTransactionService;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.dto.user.CustomerSearchCriteriaDTO;
import com.cana.vbam.common.member.dto.user.CustomerSearchResultDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.repayment.dto.AccountRepaymentPlanRedisDTO;
import com.cana.vbam.common.repayment.dto.PaidRepaymentExpenseRedisDTO;
import com.cana.vbam.common.repayment.dto.PaidRepaymentPlanRedisDTO;
import com.cana.vbam.common.repayment.dto.QueryRepaymentAndExpenseRequestDTO;
import com.cana.vbam.common.repayment.dto.RepaymentAmount;
import com.cana.vbam.common.repayment.dto.RepaymentExpenseActiveRepaymentDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanActiveRepaymentDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanAdjustmentIntegration;
import com.cana.vbam.common.repayment.dto.RepaymentPlanSearchCriteriaDTO;
import com.cana.vbam.common.repayment.dto.RepaymentSingleCollectDTO;
import com.cana.vbam.common.repayment.dto.RepaymentSingleDistributeDetailDTO;
import com.cana.vbam.common.repayment.enums.ActiveRepaymentStatus;
import com.cana.vbam.common.repayment.enums.ActiveRepaymentType;
import com.cana.vbam.common.repayment.enums.ChargeMethod;
import com.cana.vbam.common.repayment.enums.LoanInfoChangeType;
import com.cana.vbam.common.repayment.enums.PeriodStatus;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.utils.RedisUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.redis.client.SpringRedisClient;


public class ActiveRepaymentApiImpl implements IActiveRepaymentApi {
	
	@Resource 
	private LoanInfoConfigMapper loanInfoConfigMapper;
	
	@Resource
	private RepaymentLoanInfoMapper repaymentLoanInfoMapper;
	
	@Resource 
	private RepaymentPlanMapper repaymentPlanMapper;

	@Resource
	private IRepaymentPlanService repaymentPlanServiceImpl;
	
	@Resource
	private IActiveRepaymentService activeRepaymentService;
	
	@Resource
	private RepaymentExpenseMapper repaymentExpenseMapper;
	
	@Resource
	private ILoanInfoService loanInfoService;
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private IActiveRepaymentTransactionService activeRepaymentTransactionService;
	
	@Resource 
	private IRepaymentPlanAndExpenseSearchMapper repaymentPlanAndExpenseSearchMapper;

	@Resource
	private IAccountApi accountApi;
	
	@Resource
	private RepaymentSingleCollectMapper singleCollectMapper;
	
	@Resource
	private IMessageService messageServiceImpl;
	
	private final SpringRedisClient redisCache = SpringRedisClient.getInstance();
	
	@Resource
	private IVbamCommonService commonService;
	
	@Resource
	private IUserApi userApi;
	
	@Override
	public String getPlanAndExpenseToRedis(String operatorId,String loanInfoId, String masterId) throws Exception{
		RepaymentLoanInfo repaymentLoanInfo = repaymentLoanInfoMapper.selectByPrimaryKey(loanInfoId);
		// 从数据库中获取放款信息
		if(repaymentLoanInfo==null){
			throw WebException.instance("放款信息不存在");
		}
		if(!StringUtils.equals(ActiveRepaymentStatus.already_active_repayment.name(), repaymentLoanInfo.getActiveRepaymentStatus())){
			throw WebException.instance("该还款计划已经被调整！");
		}
		String redisKey = generateRepaymentPlanInfoId();
		String redisKeyFull = RedisUtils.generateLoanInfoRedisKeyByOperator(redisKey,operatorId);
		RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration = new RepaymentPlanAdjustmentIntegration();
		// 从数据库中获取还款计划信息
		RepaymentPlanExample repaymentPlanExample = new RepaymentPlanExample();
		repaymentPlanExample.createCriteria().andLoanInfoIdEqualTo(loanInfoId);
		repaymentPlanExample.setOrderByClause("repayment_period");
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
		// 获取本次主动还款的金额
		Map<String, String> extraData = new Gson().fromJson(repaymentLoanInfo.getExtraData(), new TypeToken<HashMap<String, String>>(){}.getType());
		repaymentPlanAdjustmentIntegration.setAccountTotalMoney(MoneyArithUtil.convertStringToMoney(extraData.get(Constants.REPAYMENT_LOAN_INFO_EXTRA_KEY_ACTIVE)));
		redisCache.save(redisKeyFull, repaymentPlanAdjustmentIntegration, getRedisExpireTimeFromProperties());
		return redisKey;
	}
	
	@Override
	public void submitValidate(String redisKey) {
		RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration = (RepaymentPlanAdjustmentIntegration)redisCache.get(redisKey);
		// 获取修改后以及原始已还还款计划
		List<PaidRepaymentPlanRedisDTO> paidRepaymentPlanRedisDTOList = repaymentPlanAdjustmentIntegration.getPaidRepaymentPlanRedisDTOList();
		List<PaidRepaymentPlanRedisDTO> originPaidRepaymentPlanRedisDTOList = repaymentPlanAdjustmentIntegration.getOriginPaidRepaymentPlanRedisDTOList();
		long[] paidPepymentPlanAndERC = getPaidPepymentPlan(paidRepaymentPlanRedisDTOList, originPaidRepaymentPlanRedisDTOList);
		// 获取修改后以及原始的应还还款计划
		List<AccountRepaymentPlanRedisDTO> accountRepaymentPlanRedisDTOList = repaymentPlanAdjustmentIntegration.getAccountRepaymentPlanRedisDTOList();
		List<AccountRepaymentPlanRedisDTO> originAccountRepaymentPlanRedisDTOList = repaymentPlanAdjustmentIntegration.getOriginAccountRepaymentPlanRedisDTOList();
		long accountRepaymentPlan = getAccountRepaymentPlan(accountRepaymentPlanRedisDTOList, originAccountRepaymentPlanRedisDTOList);
		// 获取修改后以及原始的费用
		List<PaidRepaymentExpenseRedisDTO> paidRepaymentExpenseRedisDTOList = repaymentPlanAdjustmentIntegration.getPaidRepaymentExpenseRedisDTOList();
		List<PaidRepaymentExpenseRedisDTO> originPaidRepaymentExpenseRedisDTOList = repaymentPlanAdjustmentIntegration.getOriginPaidRepaymentExpenseRedisDTOList();
		long[] expense = getPaidRepaymentExpense(paidRepaymentExpenseRedisDTOList, originPaidRepaymentExpenseRedisDTOList);
		if( (paidPepymentPlanAndERC[0]+expense[0]) != repaymentPlanAdjustmentIntegration.getAccountTotalMoney() || (accountRepaymentPlan + expense[1] + paidPepymentPlanAndERC[1]) != repaymentPlanAdjustmentIntegration.getAccountTotalMoney()){
			throw WebException.instance("调账的金额与还款的金额不一致，请核对和再提交！");
		}
	}
	
	/**
	 * 获取本次还款最大还款金额
	 * @param loanInfoId
	 * @param activeRepaymentType
	 * @return
	 */
	@Override
	public String getMaxAccountTotalMoney(String loanInfoId, ActiveRepaymentType activeRepaymentType) {
		return MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(getAccountTotalMoney(loanInfoId, activeRepaymentType)));
	}


	
	
	/**
	 * 保存调整后的还款计划和费用(由于主动还款中的数据与线下还款的基本一致，此处组装成OfflineRepaymentData，单此处并非线下还款)
	 * @param redisKey
	 * @param loanInfoId
	 * @param operatorId
	 * @param flag
	 * @param changeType
	 * @return
	 */
	@Override
	public void saveRepaymentPlanAndExpenseToDB(String redisKey,String loanInfoId, String operatorId, String flag,String changeType) throws Exception {
		RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration = (RepaymentPlanAdjustmentIntegration)redisCache.get(redisKey);
		OfflineRepaymentData offlineRepaymentData = new OfflineRepaymentData();
		offlineRepaymentData.setPaidRepaymentPlanRedisDTOList(repaymentPlanAdjustmentIntegration.getPaidRepaymentPlanRedisDTOList());
		offlineRepaymentData.setAccountRepaymentPlanRedisDTOList(repaymentPlanAdjustmentIntegration.getAccountRepaymentPlanRedisDTOList());
		offlineRepaymentData.setPaidRepaymentExpenseRedisDTOList(repaymentPlanAdjustmentIntegration.getPaidRepaymentExpenseRedisDTOList());
		offlineRepaymentData.setLoanInfoId(loanInfoId);
		offlineRepaymentData.setLoanInfoOldVersion(repaymentPlanAdjustmentIntegration.getLoanInfoVersion());
		offlineRepaymentData.setLoanInfoVersion(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_LOAN_INFO_VERSION, 4));
		// 还款计划转换
		offlineRepaymentData.repaymentPlanConvert();
		// 还款费用转换
		offlineRepaymentData.repaymentExpenseConvert();
		//设置变更类型
		offlineRepaymentData.setChangeType(LoanInfoChangeType.active_repayment.name());
		activeRepaymentTransactionService.updatePlanAndExpenseAndSaveRepaymentDetail(offlineRepaymentData);
		
		//调账消息发送
//		if(StringUtils.isNotBlank(flag) && flag.equals("0")){
//			RepaymentLoanInfo repaymentLoanInfo = repaymentLoanInfoMapper.selectByPrimaryKey(loanInfoId);
//			if(StringUtils.isNotBlank(repaymentLoanInfo.getFinanceId())){
//				messageServiceImpl.sendAdjustMessage(operatorId, repaymentLoanInfo.getFactorCompany(), repaymentLoanInfo.getLoanNo(), loanInfoId, repaymentLoanInfo.getFinanceId());
//			}
//		}
	}
	
	private long getAccountTotalMoney(String loanInfoId, ActiveRepaymentType activeRepaymentType){
		// 从数据库中获取还款规则(获取提前还款手续费率以及展期天数)
		LoanInfoConfig loanInfoConfig = loanInfoConfigMapper.selectByPrimaryKey(loanInfoId);
		if(loanInfoConfig==null){
			throw WebException.instance("放款信息配置不存在");
		}
		RepaymentLoanInfo repaymentLoanInfo = repaymentLoanInfoMapper.selectByPrimaryKey(loanInfoId);
		if(repaymentLoanInfo==null){
			throw WebException.instance("放款信息不存在");
		}
		RepaymentPlanExample example = new RepaymentPlanExample();
		example.createCriteria().andLoanInfoIdEqualTo(loanInfoId);
		List<RepaymentPlan> repaymentPlanList = repaymentPlanMapper.selectByExample(example);
		long accountTotalMoney = 0l;
		String lastRepaymentDate = null;
		String periodStatus = null;
		switch (activeRepaymentType) {
		
		case single_plan:
			int repaymentPeriod = getSingleActiveRepaymentIndex(repaymentPlanList);
			if(repaymentPeriod == -1){
				throw WebException.instance("该还款计划已结清");
			}
			lastRepaymentDate = getLastRepaymentDate(repaymentLoanInfo, repaymentPlanList, repaymentPeriod);
			periodStatus = repaymentPlanServiceImpl.judgePlanPeriodByNowDate(lastRepaymentDate, repaymentPlanList.get(repaymentPeriod-1).getRepaymentDate(), repaymentPlanList.get(repaymentPeriod-1).getExtensionDays());
			accountTotalMoney += calculateAccountTotalMoney(loanInfoConfig.getEarlyRepaymentChargeRatio(), repaymentPlanList.get(repaymentPeriod-1), periodStatus);
			break;
			
		case mutiple_plan:
			for(RepaymentPlan repaymentPlan:repaymentPlanList){
				lastRepaymentDate = getLastRepaymentDate(repaymentLoanInfo, repaymentPlanList, repaymentPlan.getRepaymentPeriod());
				periodStatus = repaymentPlanServiceImpl.judgePlanPeriodByNowDate(lastRepaymentDate, repaymentPlan.getRepaymentDate(), repaymentPlan.getExtensionDays());
				accountTotalMoney += calculateAccountTotalMoney(loanInfoConfig.getEarlyRepaymentChargeRatio(), repaymentPlan, periodStatus);
			}
			break;
			
		case single_expense:
		case mutiple_expense:
			accountTotalMoney += getUnsettleExpense(loanInfoId, activeRepaymentType);
			break;

		default:
			throw WebException.instance("sad");
		}
		return accountTotalMoney;
	}
	
	
	public ListResult<RepaymentPlanActiveRepaymentDTO> getOverdueRepaymentPlan(QueryRepaymentAndExpenseRequestDTO queryDTO, UserType userType) throws Exception{
		ListResult<RepaymentPlanActiveRepaymentDTO> result = new ListResult<RepaymentPlanActiveRepaymentDTO>();
		List<ActiveRepaymentPlan> activeRepaymentPlanList = repaymentPlanAndExpenseSearchMapper.searchRepaymentPlan(queryDTO);
		List<RepaymentPlanActiveRepaymentDTO> RepaymentPlanActiveRepaymentDTOList = convertActiveRepaymentPlanToRepaymentPlanActiveRepaymentDTO(activeRepaymentPlanList, userType);
		int totalNum = repaymentPlanAndExpenseSearchMapper.countRepaymentPlan(queryDTO);
		result.setData(RepaymentPlanActiveRepaymentDTOList);
		result.setTotalNum(totalNum);
		return result;
	}
	
	public ListResult<RepaymentExpenseActiveRepaymentDTO> getOverdueRepaymentExpense(QueryRepaymentAndExpenseRequestDTO queryDTO, UserType userType) throws Exception{
		ListResult<RepaymentExpenseActiveRepaymentDTO> result = new ListResult<>();
		queryDTO.setEndRepaymentDate(commonService.getCurrentDate());
		List<ActiveRepaymentExpense> activeRepaymentExpenseList = repaymentPlanAndExpenseSearchMapper.searchRepaymentExpense(queryDTO);
		List<RepaymentExpenseActiveRepaymentDTO> RepaymentExpenseActiveRepaymentDTOList = 
				convertActiveRepaymentExpenseToRepaymentExpenseRedisDTO(activeRepaymentExpenseList, userType);
		int totalNum = repaymentPlanAndExpenseSearchMapper.countRepaymentExpense(queryDTO);
		result.setData(RepaymentExpenseActiveRepaymentDTOList);
		result.setTotalNum(totalNum);
		return result;
	}
	
	/**
	 * 计算当前最大还款金额
	 * @param earlyPayRatio
	 * @return
	 */
	private long calculateAccountTotalMoney(BigDecimal earlyPayRatio,RepaymentPlan repaymentPlan, String periodStatus){
		long accountTotalCharge = 0;
		long earlyPayPrincipal = 0;
//		long paidTotalCharge = 0;
		if(repaymentPlan != null){
			if(SettleStatus.UNSETTLE.name().equals(repaymentPlan.getSettleStatus())){
				if(StringUtils.equals(periodStatus, PeriodStatus.OVERDUE.name())){
					accountTotalCharge += repaymentPlan.getOtherPenalty();
					accountTotalCharge += repaymentPlan.getOverdueServiceChargePenalty();
					accountTotalCharge += repaymentPlan.getOverdueInterestPenalty();
					accountTotalCharge += repaymentPlan.getOverduePrincipalPenalty();
					accountTotalCharge += repaymentPlan.getAccountExtensionCharge();
					accountTotalCharge += repaymentPlan.getOverdueServiceCharge();
					accountTotalCharge += repaymentPlan.getOverdueInterest();
					accountTotalCharge += repaymentPlan.getOverduePrincipal();
					
//					paidTotalCharge += repaymentPlan.getPaidOtherPenalty(); 
//					paidTotalCharge += repaymentPlan.getPaidOverdueServiceChargePenalty();
//					paidTotalCharge += repaymentPlan.getPaidOverdueInterestPenalty();
//					paidTotalCharge += repaymentPlan.getPaidOverduePrincipalPenalty();
//					paidTotalCharge += repaymentPlan.getPaidExtensionCharge();
//					paidTotalCharge += repaymentPlan.getPaidOverdueServiceCharge();
//					paidTotalCharge += repaymentPlan.getPaidOverdueInterest();
//					paidTotalCharge += repaymentPlan.getPaidOverduePrincipal();
				}
				if(StringUtils.equals(periodStatus, PeriodStatus.EXTENSION.name())){
					accountTotalCharge += repaymentPlan.getAccountExtensionCharge();
					accountTotalCharge += repaymentPlan.getAccountServiceCharge();
					accountTotalCharge += repaymentPlan.getAccountInterest();
					accountTotalCharge += repaymentPlan.getAccountPrincipal();
					
//					paidTotalCharge += repaymentPlan.getPaidExtensionCharge();
//					paidTotalCharge += repaymentPlan.getPaidNormalServiceCharge();
//					paidTotalCharge += repaymentPlan.getPaidNormalInterest();
//					paidTotalCharge += repaymentPlan.getPaidNormalPrincipal();
				}
				if(StringUtils.equals(periodStatus, PeriodStatus.CURRENTIN.name()) || StringUtils.equals(periodStatus, PeriodStatus.CURRENTOUT.name())){
					if(StringUtils.equals(periodStatus, PeriodStatus.CURRENTOUT.name())){
						earlyPayPrincipal += repaymentPlan.getAccountPrincipal();
					}
					accountTotalCharge += repaymentPlan.getAccountServiceCharge();
					accountTotalCharge += repaymentPlan.getAccountInterest();
					accountTotalCharge += repaymentPlan.getAccountPrincipal();
					
//					paidTotalCharge += repaymentPlan.getPaidEarlyRepaymentCharge();
//					paidTotalCharge += repaymentPlan.getPaidNormalServiceCharge();
//					paidTotalCharge += repaymentPlan.getPaidNormalInterest();
//					paidTotalCharge += repaymentPlan.getPaidNormalPrincipal();
				}
				if( StringUtils.equals(periodStatus, PeriodStatus.FUTURE.name())){
					accountTotalCharge += repaymentPlan.getAccountPrincipal();
					earlyPayPrincipal += repaymentPlan.getAccountPrincipal();
					
//					paidTotalCharge += repaymentPlan.getPaidEarlyRepaymentCharge();
//					paidTotalCharge += repaymentPlan.getPaidNormalPrincipal();
				}
			}
		}
		return accountTotalCharge + computeEarlyRepaymentCharge(earlyPayPrincipal, earlyPayRatio);
	}

	/**
	 * 计算提前还款手续费
	 * @param principal
	 * @param earlyRepaymentRatio
	 * @return
	 */
	private long computeEarlyRepaymentCharge(long principal, BigDecimal earlyRepaymentRatio){
		BigDecimal principalDec = new BigDecimal(principal);
		principalDec = MoneyArithUtil.round(MoneyArithUtil.mul(principalDec, earlyRepaymentRatio), 0);
		return principalDec.longValue();
	}
	
	/**
	 * 获取单期还款的还款期数
	 * @param repaymentPlanList
	 * @return
	 */
	private int getSingleActiveRepaymentIndex(List<RepaymentPlan> repaymentPlanList){
		int repaymentParied = -1;
		for(RepaymentPlan repaymentPlan:repaymentPlanList){
			if(StringUtils.equals(SettleStatus.UNSETTLE.name(), repaymentPlan.getSettleStatus())){
				repaymentParied = repaymentPlan.getRepaymentPeriod();
				break;
			}
		}
		return repaymentParied;
	}
	
	/**
	 * 获取上期还款计划的还款日(如果为第一期，则为放款日)
	 * @param repaymentLoanInfo
	 * @param repaymentPlanList
	 * @param repaymentPeriod
	 * @return
	 */
	private String getLastRepaymentDate(RepaymentLoanInfo repaymentLoanInfo, List<RepaymentPlan> repaymentPlanList, int repaymentPeriod){
		String lastRepaymentDate=repaymentLoanInfo.getLoanDate();
		if(repaymentPeriod != 1){
			lastRepaymentDate = repaymentPlanList.get(repaymentPeriod-2).getRepaymentDate();
		}
		return lastRepaymentDate;
	}
	
	/**
	 * 获取还款费用
	 * @param repaymentLoanInfo
	 * @return
	 */
	private long getUnsettleExpense(String loanInfoId, ActiveRepaymentType activeRepaymentType){
		long accountTotalMoney = 0l;
		RepaymentExpenseExample repaymentExpenseExample = new RepaymentExpenseExample();
		repaymentExpenseExample.createCriteria().andLoanInfoIdEqualTo(loanInfoId);
		repaymentExpenseExample.setOrderByClause("repayment_date");
		List<RepaymentExpense> repaymentExpenseList = repaymentExpenseMapper.selectByExample(repaymentExpenseExample);
		boolean isSingle = StringUtils.equals("single_expense", ActiveRepaymentType.single_expense.name()) ? true : false;
		for(RepaymentExpense repaymentExpense:repaymentExpenseList){
			if(StringUtils.equals(repaymentExpense.getSettleStatus(), SettleStatus.UNSETTLE.name())){
				accountTotalMoney += repaymentExpense.getRepaymentAmount();
				if(isSingle){
					break;
				}
			}
		}
		return accountTotalMoney;
	}
	
	
	/**
	 * 计算调整的已还金额
	 * @param paidRepaymentPlanRedisDTOList
	 * @param originPaidRepaymentPlanRedisDTOList
	 * @return
	 */
	private long[] getPaidPepymentPlan(List<PaidRepaymentPlanRedisDTO> paidRepaymentPlanRedisDTOList, 
			List<PaidRepaymentPlanRedisDTO> originPaidRepaymentPlanRedisDTOList){
		long adjustPaidPlan = 0l;
		long paidEarlyRepaymentCharge = 0l;
		long[] adjustPaidPlanAndERC = new long[2];
		for(PaidRepaymentPlanRedisDTO paidRepaymentPlanRedisDTO:paidRepaymentPlanRedisDTOList){
			int index = paidRepaymentPlanRedisDTOList.indexOf(paidRepaymentPlanRedisDTO);
			PaidRepaymentPlanRedisDTO originPaidRepaymentPlanRedisDTO = originPaidRepaymentPlanRedisDTOList.get(index);
			// 计算已还正常部分
			adjustPaidPlan += (MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidNormalPrincipal()) - 
					MoneyArithUtil.convertStringToMoney(originPaidRepaymentPlanRedisDTO.getPaidNormalPrincipal())); 
			adjustPaidPlan += (MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidNormalInterest()) - 
					MoneyArithUtil.convertStringToMoney(originPaidRepaymentPlanRedisDTO.getPaidNormalInterest())); 
			adjustPaidPlan += (MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidNormalServiceCharge()) - 
					MoneyArithUtil.convertStringToMoney(originPaidRepaymentPlanRedisDTO.getPaidNormalServiceCharge())); 
			// 计算已还逾期部分
			adjustPaidPlan += (MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidOverduePrincipal()) - 
					MoneyArithUtil.convertStringToMoney(originPaidRepaymentPlanRedisDTO.getPaidOverduePrincipal())); 
			adjustPaidPlan += (MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidOverdueInterest()) - 
					MoneyArithUtil.convertStringToMoney(originPaidRepaymentPlanRedisDTO.getPaidOverdueInterest())); 
			adjustPaidPlan += (MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidOverdueServiceCharge()) - 
					MoneyArithUtil.convertStringToMoney(originPaidRepaymentPlanRedisDTO.getPaidOverdueServiceCharge())); 
			adjustPaidPlan += (MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidOverdueManagerFee()) - 
					MoneyArithUtil.convertStringToMoney(originPaidRepaymentPlanRedisDTO.getPaidOverdueManagerFee()));
			// 计算已还其他部分
			adjustPaidPlan += (MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidExtensionCharge()) - 
					MoneyArithUtil.convertStringToMoney(originPaidRepaymentPlanRedisDTO.getPaidExtensionCharge()));
			paidEarlyRepaymentCharge += (MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidEarlyRepaymentCharge()) - 
					MoneyArithUtil.convertStringToMoney(originPaidRepaymentPlanRedisDTO.getPaidEarlyRepaymentCharge()));
			
		}
		adjustPaidPlanAndERC[0] = (adjustPaidPlan + paidEarlyRepaymentCharge);
		adjustPaidPlanAndERC[1] = (paidEarlyRepaymentCharge);
		return adjustPaidPlanAndERC;
	}
	
	/**
	 * 计算调整的应还金额
	 * @param accountRepaymentPlanRedisDTOList
	 * @param originAccountRepaymentPlanRedisDTOList
	 * @return
	 */
	private long getAccountRepaymentPlan(List<AccountRepaymentPlanRedisDTO> accountRepaymentPlanRedisDTOList,
			List<AccountRepaymentPlanRedisDTO> originAccountRepaymentPlanRedisDTOList){
		long adjustAccountPlan = 0l;
		for(AccountRepaymentPlanRedisDTO accountRepaymentPlanRedisDTO:accountRepaymentPlanRedisDTOList){
			int index = accountRepaymentPlanRedisDTOList.indexOf(accountRepaymentPlanRedisDTO);
			AccountRepaymentPlanRedisDTO originAccountRepaymentPlanRedisDTO = originAccountRepaymentPlanRedisDTOList.get(index);
			// 计算应还正常部分
			adjustAccountPlan += (MoneyArithUtil.convertStringToMoney(originAccountRepaymentPlanRedisDTO.getAccountPrincipal()) - 
					MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getAccountPrincipal())); 
			if(!StringUtils.equals(PeriodStatus.FUTURE.name(), accountRepaymentPlanRedisDTO.getPeriodStatus())){
				adjustAccountPlan += (MoneyArithUtil.convertStringToMoney(originAccountRepaymentPlanRedisDTO.getAccountInterest()) - 
						MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getAccountInterest())); 
				adjustAccountPlan += (MoneyArithUtil.convertStringToMoney(originAccountRepaymentPlanRedisDTO.getAccountServiceCharge()) - 
						MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getAccountServiceCharge())); 
			}
			// 计算应还逾期部分
			adjustAccountPlan += (MoneyArithUtil.convertStringToMoney(originAccountRepaymentPlanRedisDTO.getOverduePrincipal()) - 
					MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getOverduePrincipal())); 
			adjustAccountPlan += (MoneyArithUtil.convertStringToMoney(originAccountRepaymentPlanRedisDTO.getOverdueInterest()) - 
					MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getOverdueInterest())); 
			adjustAccountPlan += (MoneyArithUtil.convertStringToMoney(originAccountRepaymentPlanRedisDTO.getOverdueServiceCharge()) - 
					MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getOverdueServiceCharge())); 
			adjustAccountPlan += (MoneyArithUtil.convertStringToMoney(originAccountRepaymentPlanRedisDTO.getOverdueManagerFee()) - 
					MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getOverdueManagerFee())); 
			// 计算应还其他部分
			adjustAccountPlan += (MoneyArithUtil.convertStringToMoney(originAccountRepaymentPlanRedisDTO.getExtensionCharge())) - 
					MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getExtensionCharge()); 
			adjustAccountPlan += (MoneyArithUtil.convertStringToMoney(originAccountRepaymentPlanRedisDTO.getPaidEarlyRepaymentCharge()) - 
					MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getPaidEarlyRepaymentCharge())); 
		}
		return adjustAccountPlan;
	}
	
	/**
	 * 计算费用调整差值
	 * @param paidRepaymentExpenseRedisDTOList
	 * @param originPaidRepaymentExpenseRedisDTOList
	 * @return
	 */
	private long[] getPaidRepaymentExpense(List<PaidRepaymentExpenseRedisDTO> paidRepaymentExpenseRedisDTOList,
			List<PaidRepaymentExpenseRedisDTO> originPaidRepaymentExpenseRedisDTOList){
		long adjustPaidExpense = 0l;
		long adjustAccountExpense = 0l;
		long[] expense = new long[2];
		for(PaidRepaymentExpenseRedisDTO paidRepaymentExpenseRedisDTO:paidRepaymentExpenseRedisDTOList){
			if(StringUtils.isNoneBlank(paidRepaymentExpenseRedisDTO.getRepaymentAmountNew()) && StringUtils.isNoneBlank(paidRepaymentExpenseRedisDTO.getPaidAmountNew())){
				adjustPaidExpense += MoneyArithUtil.convertStringToMoney(paidRepaymentExpenseRedisDTO.getPaidAmount()) - MoneyArithUtil.convertStringToMoney(paidRepaymentExpenseRedisDTO.getPaidAmountNew());
				adjustAccountExpense += MoneyArithUtil.convertStringToMoney(paidRepaymentExpenseRedisDTO.getRepaymentAmountNew()) - MoneyArithUtil.convertStringToMoney(paidRepaymentExpenseRedisDTO.getRepaymentAmount()) ;
			}
//			for(PaidRepaymentExpenseRedisDTO originPaidRepaymentExpenseRedisDTO:originPaidRepaymentExpenseRedisDTOList){
//				if(StringUtils.equals(paidRepaymentExpenseRedisDTO.getExpenseSubject(), originPaidRepaymentExpenseRedisDTO.getExpenseSubject())
//						&& StringUtils.equals(paidRepaymentExpenseRedisDTO.getRepaymentDate(), originPaidRepaymentExpenseRedisDTO.getRepaymentDate())){
//					adjustPaidExpense += (MoneyArithUtil.convertStringToMoney(paidRepaymentExpenseRedisDTO.getPaidAmount()) - 
//							MoneyArithUtil.convertStringToMoney(paidRepaymentExpenseRedisDTO.getPaidAmount()));
//					adjustAccountExpense += (MoneyArithUtil.convertStringToMoney(paidRepaymentExpenseRedisDTO.getRepaymentAmount()) - 
//							MoneyArithUtil.convertStringToMoney(paidRepaymentExpenseRedisDTO.getRepaymentAmount()));
//					break;
//				}
//			}
		}
		expense[0] = adjustPaidExpense;
		expense[1] = adjustAccountExpense;
		return expense;
	}

	@Override
	public ListResult<RepaymentPlanActiveRepaymentDTO> getRepaymentPlansWithn7Days(RepaymentPlanSearchCriteriaDTO searchDto, UserType userType) throws Exception {
		ListResult<RepaymentPlanActiveRepaymentDTO> result = new ListResult<RepaymentPlanActiveRepaymentDTO>();
		RepaymentPlanExample example = setQueryContions(searchDto);
		int totalNum = repaymentPlanMapper.countByExample(example);
		result.setTotalNum(totalNum);
		//设置分页查询; 设置起始页begainRowNo页码； 
		int begainRowNo = (searchDto.getPage() - 1) * searchDto.getPageSize();
		if(begainRowNo < totalNum || begainRowNo == 0){
			example.setLimitStart(begainRowNo);
			example.setLimitEnd(searchDto.getPageSize());//设置每页显示多少条
			example.setOrderByClause("repayment_date");
		}
		else{
			throw WebException.instance("查询页码超出总页数");
		}
		List<RepaymentPlan> repaymentPlans = repaymentPlanMapper.selectByExample(example);
		result.setData(convertRepaymentPlanToRepaymentPlanActiveRepaymentDTO(RepaymentPlanBO.poList2boList(repaymentPlans), userType));
		return result;
	}
	/**
	 * 
	 * @param planBOList
	 * @return
	 */
	private List<RepaymentPlanActiveRepaymentDTO> convertRepaymentPlanToRepaymentPlanActiveRepaymentDTO(List<RepaymentPlanBO> planBOList, UserType userType)throws Exception{
		List<RepaymentPlanActiveRepaymentDTO> rActiveRepaymentDTOs = new ArrayList<RepaymentPlanActiveRepaymentDTO>();
		List<String> financeIds = new ArrayList<>();
		for(RepaymentPlanBO planBO : planBOList){
			RepaymentPlanActiveRepaymentDTO rDto = new RepaymentPlanActiveRepaymentDTO();
			BeanUtils.copyProperties(planBO, rDto);
			RepaymentLoanInfoBO loanInfoBO = planBO.lazyLoadLoanInfoBO();
			rDto.setCoreCompanyName(loanInfoBO.getCoreCompanyName());
			rDto.setFactorCompany(loanInfoBO.getFactorCompany());
			rDto.setFinanceBalance(MoneyUtil.formatMoney(loanInfoBO.getFinanceBalance()));
			rDto.setRepaymentPeriod(planBO.getRepaymentPeriod().toString());
			rDto.setAccountPrincipal(MoneyUtil.formatMoney(planBO.getAccountPrincipal()));
			RepaymentAmount accountInterestAndAccountServiceCharge = RepaymentCalcFactory.getRepaymentCalc(loanInfoBO).calcAccountInterestAndAccountServiceChargeUntilNow(loanInfoBO, planBO);
			rDto.setAccountInterestUntilNow(MoneyArithUtil.convertMoneyToString(accountInterestAndAccountServiceCharge.getAccountInterest()));
			rDto.setAccountInterest(MoneyUtil.formatMoney(planBO.getAccountInterest()));
			rDto.setAccountServiceCharge(MoneyUtil.formatMoney(planBO.getAccountServiceCharge()));
			rDto.setAccountTotalAmount(getAccountTotalAmount(planBO));
			if(StringUtils.equals(userType.name(), UserType.CANA.name())){
				rDto.setRepaied(false);
			}else{
				rDto.setRepaied(activeRepaymentService.isRepaymentPlanReadyToRepay(planBO));
			}
			financeIds.add(planBO.getFinanceId());
			rActiveRepaymentDTOs.add(rDto);
		}
		CustomerSearchCriteriaDTO customerCriteriaDTO = new CustomerSearchCriteriaDTO();
		customerCriteriaDTO.setPageSize(financeIds.size());
		customerCriteriaDTO.setCustomerIds(financeIds);
		List<CustomerSearchResultDTO> customerResultDTOs = userApi.queryCustomerList(customerCriteriaDTO).getData();
		Map<String, String> customerResultMap = new HashMap<String, String>();
		for(CustomerSearchResultDTO customerResultDTO : customerResultDTOs){
			customerResultMap.put(customerResultDTO.getId(), customerResultDTO.getContactTel().trim());  
		}
		
		for(int i= 0;i<rActiveRepaymentDTOs.size();i++){
			rActiveRepaymentDTOs.get(i).setFinanceTel(customerResultMap.get(financeIds.get(i)));
		}
		return rActiveRepaymentDTOs;
	}
	
	private List<RepaymentPlanActiveRepaymentDTO> convertActiveRepaymentPlanToRepaymentPlanActiveRepaymentDTO(List<ActiveRepaymentPlan> plans, UserType userType) throws Exception{
		List<RepaymentPlanActiveRepaymentDTO> rActiveRepaymentDTOs = new ArrayList<RepaymentPlanActiveRepaymentDTO>();
		for(ActiveRepaymentPlan activeRepaymentPlan:plans){
			RepaymentPlanActiveRepaymentDTO repaymentPlanActiveRepaymentDTO = new RepaymentPlanActiveRepaymentDTO();
			BeanUtils.copyProperties(activeRepaymentPlan, repaymentPlanActiveRepaymentDTO);
			RepaymentPlanBO planBO = new RepaymentPlanBO(activeRepaymentPlan.getId());
			RepaymentLoanInfoBO loanInfoBO = planBO.lazyLoadLoanInfoBO();
			repaymentPlanActiveRepaymentDTO.setFinanceAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getFinanceAmount())));
			repaymentPlanActiveRepaymentDTO.setFinanceBalance(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getFinanceBalance())));
			repaymentPlanActiveRepaymentDTO.setRepaymentPeriod(activeRepaymentPlan.getRepaymentPeriod()+"");
			repaymentPlanActiveRepaymentDTO.setAccountPrincipal(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getAccountPrincipal())));
			RepaymentAmount accountInterestAndAccountServiceCharge = RepaymentCalcFactory.getRepaymentCalc(loanInfoBO).calcAccountInterestAndAccountServiceChargeUntilNow(loanInfoBO, planBO);
			repaymentPlanActiveRepaymentDTO.setAccountInterestUntilNow(MoneyArithUtil.convertMoneyToString(accountInterestAndAccountServiceCharge.getAccountInterest()));
			repaymentPlanActiveRepaymentDTO.setAccountInterest(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getAccountInterest())));
			repaymentPlanActiveRepaymentDTO.setAccountServiceCharge(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getAccountServiceCharge())));
			if(SettleStatus.SETTLED == SettleStatus.valueOf(activeRepaymentPlan.getSettleStatus()))
				repaymentPlanActiveRepaymentDTO.setSettleStatus(SettleStatus.SETTLED.desc());
			else
				repaymentPlanActiveRepaymentDTO.setSettleStatus(SettleStatus.UNSETTLE.desc());
			if(activeRepaymentPlan.getOverduePrincipal() > 0 || activeRepaymentPlan.getOverdueInterest() > 0 || activeRepaymentPlan.getOverdueServiceCharge() > 0){
				repaymentPlanActiveRepaymentDTO.setSettleStatus("逾期");
				repaymentPlanActiveRepaymentDTO.setAccountExtensionCharge(MoneyUtil.formatMoney(0L));
				repaymentPlanActiveRepaymentDTO.setOverdueExtensionCharge(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getAccountExtensionCharge())));
			}else{
				repaymentPlanActiveRepaymentDTO.setAccountExtensionCharge(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getAccountExtensionCharge())));
				repaymentPlanActiveRepaymentDTO.setOverdueExtensionCharge(MoneyUtil.formatMoney(0L));
			}
			repaymentPlanActiveRepaymentDTO.setPaidExtensionCharge(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getPaidExtensionCharge())));
			repaymentPlanActiveRepaymentDTO.setPaidNormalPrincipal(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getPaidNormalPrincipal())));
			repaymentPlanActiveRepaymentDTO.setPaidNormalInterest(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getPaidNormalInterest())));
			repaymentPlanActiveRepaymentDTO.setPaidNormalServiceCharge(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getPaidNormalServiceCharge())));
			repaymentPlanActiveRepaymentDTO.setPaidExtensionCharge(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getPaidExtensionCharge())));
			repaymentPlanActiveRepaymentDTO.setOverduePrincipal(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getOverduePrincipal())));
			repaymentPlanActiveRepaymentDTO.setOverdueInterest(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getOverdueInterest())));
			repaymentPlanActiveRepaymentDTO.setOverdueManageCharge(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getOverdueManageCharge())));
			repaymentPlanActiveRepaymentDTO.setOverdueServiceCharge(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getOverdueServiceCharge())));
			repaymentPlanActiveRepaymentDTO.setPaidOverduePrincipal(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getPaidOverduePrincipal())));
			repaymentPlanActiveRepaymentDTO.setPaidOverdueInterest(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getPaidOverdueInterest())));
			repaymentPlanActiveRepaymentDTO.setPaidOverdueServiceCharge(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getPaidOverdueServiceCharge())));
			repaymentPlanActiveRepaymentDTO.setPaidOverdueManageCharge(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(activeRepaymentPlan.getPaidOverdueManageCharge())));
			if(null != userType){
				if(StringUtils.equals(userType.name(), UserType.CANA.name())){
					repaymentPlanActiveRepaymentDTO.setRepaied(false);
				}else{
					repaymentPlanActiveRepaymentDTO.setRepaied(activeRepaymentService.isRepaymentPlanReadyToRepay(planBO));
				}
			}
			rActiveRepaymentDTOs.add(repaymentPlanActiveRepaymentDTO);
		}
		return rActiveRepaymentDTOs;
	}
	
	/**
	 * 应还总金额=应还本金+应还利息+应还服务费+应还展期费用+已还提前还款手续费
	 * @param repaymentPlan
	 * @return
	 */
	private String getAccountTotalAmount(RepaymentPlan repaymentPlan){
		Long accountTotalAmount = 0L;
		accountTotalAmount = accountTotalAmount + repaymentPlan.getAccountPrincipal() + repaymentPlan.getAccountInterest() +repaymentPlan.getAccountServiceCharge() + repaymentPlan.getAccountExtensionCharge() + repaymentPlan.getPaidEarlyRepaymentCharge();
		return MoneyUtil.formatMoney(accountTotalAmount);
	}
	/**
	 * 设置还款计划的条件
	 * @param searchDto
	 * @return
	 * @throws Exception
	 */
	private RepaymentPlanExample setQueryContions(RepaymentPlanSearchCriteriaDTO searchDto)throws Exception{
		RepaymentPlanExample example = new RepaymentPlanExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(searchDto.getFactorId()))
			criteria.andFactorIdEqualTo(searchDto.getFactorId());
		if (StringUtils.isNotBlank(searchDto.getFinanceId()))
			criteria.andFinanceIdEqualTo(searchDto.getFinanceId());
		if(StringUtils.isNotBlank(searchDto.getCoreCompanyId()))
			criteria.andCoreCompanyIdEqualTo(searchDto.getCoreCompanyId());
		criteria.andRepaymentDateBetween(commonService.getCurrentDate(), DateTime.parse(commonService.getCurrentDate()).plusDays(6).toString("yyyy-MM-dd"));//当天至后6天
//		criteria.andInputMethodNotEqualTo("AUTO");
		criteria.andSettleStatusEqualTo("UNSETTLE");
		return example;
	}
	
	/**
	 * 生成id
	 * @return
	 * @throws Exception
	 */
	private String generateRepaymentPlanInfoId() throws Exception{
		return DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.ACTIVE_REPAYMENT_ADJUSTMENT_ID, 4);
	}
	
	/**
	 * 获取还款计划已还部分
	 * @param repaymentPlanList
	 * @param paidRepaymentPlanRedisDTOList
	 * @param loanDate
	 * @param extensionDays
	 */
	private void getPaidRepaymentPlanToRedis(List<RepaymentPlan> repaymentPlanList,List<PaidRepaymentPlanRedisDTO> paidRepaymentPlanRedisDTOList, String loanDate)throws Exception{
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
	
	/**
	 * 获取还款计划未还部分
	 * @param repaymentPlanList
	 * @param accountRepaymentPlanRedisDTOList
	 * @param loanDate
	 * @param extensionDays
	 */
	private void getAccountRepaymentPlanToRedis(List<RepaymentPlan> repaymentPlanList, List<AccountRepaymentPlanRedisDTO> accountRepaymentPlanRedisDTOList, String loanDate) throws Exception{
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
	
	/**
	 * 获取还款费用
	 * @param repaymentPlanList
	 * @param paidRepaymentExpenseRedisDTOList
	 */
	private void getPaidRepaymentExpenseToRedis(List<RepaymentExpense> repaymentPlanList,List<PaidRepaymentExpenseRedisDTO> paidRepaymentExpenseRedisDTOList)throws Exception{
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
	private int getRedisExpireTimeFromProperties(){
		return Integer.parseInt(TopsConfReader.getConfContent("properties/repayment-common.properties", "redis.temp.timeout", ConfScope.G));
	}

	@Override
	public ListResult<RepaymentExpenseActiveRepaymentDTO> getRepaymentExpenseWithn7Days(RepaymentPlanSearchCriteriaDTO searchDto, UserType userType) throws Exception {
		ListResult<RepaymentExpenseActiveRepaymentDTO> result = new ListResult<RepaymentExpenseActiveRepaymentDTO>();
		RepaymentExpenseExample example = setQueryContionsForExpenseExample(searchDto);
		int totalNum = repaymentExpenseMapper.countByExample(example);
		result.setTotalNum(totalNum);
		//设置分页查询; 设置起始页begainRowNo页码； 
		int begainRowNo = (searchDto.getPage() - 1) * searchDto.getPageSize();
		if(begainRowNo < totalNum || begainRowNo == 0){
			example.setLimitStart(begainRowNo);
			example.setLimitEnd(searchDto.getPageSize());//设置每页显示多少条
			example.setOrderByClause("repayment_date");
		}
		else{
			throw WebException.instance("查询页码超出总页数");
		}
		List<RepaymentExpense> repaymentExpenses = repaymentExpenseMapper.selectByExample(example);
		result.setData(convertRepaymentExpenseToRepaymentExpenseRedisDTO(repaymentExpenses, userType));
		return result;
	}
	
	/**
	 * 设置费用列表的查询条件
	 * @param searchDto
	 * @return
	 * @throws Exception
	 */
	private RepaymentExpenseExample setQueryContionsForExpenseExample(RepaymentPlanSearchCriteriaDTO searchDto)throws Exception{
		RepaymentExpenseExample example = new RepaymentExpenseExample();
		com.cana.repayment.dao.po.RepaymentExpenseExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(searchDto.getFactorId()))
			criteria.andFactorIdEqualTo(searchDto.getFactorId());
		if (StringUtils.isNotBlank(searchDto.getFinanceId()))
			criteria.andFinanceIdEqualTo(searchDto.getFinanceId());
		if (StringUtils.isNotBlank(searchDto.getCoreCompanyId()))
			criteria.andCoreCompanyIdEqualTo(searchDto.getCoreCompanyId());
		criteria.andRepaymentDateBetween(commonService.getCurrentDate(), DateTime.parse(commonService.getCurrentDate()).plusDays(6).toString("yyyy-MM-dd"));//当天至后6天
		criteria.andSettleStatusEqualTo("UNSETTLE");
		return example;
	}
	
	/**
	 * 
	 * @param repaymentExpenses
	 * @return
	 * @throws Exception
	 */
	private List<RepaymentExpenseActiveRepaymentDTO> convertRepaymentExpenseToRepaymentExpenseRedisDTO(List<RepaymentExpense> repaymentExpenses, UserType userType)throws Exception{
		List<RepaymentExpenseActiveRepaymentDTO> rExpenseRedisDTOs = new ArrayList<RepaymentExpenseActiveRepaymentDTO>();
		ActiveRepaymentExpense activeRepaymentExpense = new ActiveRepaymentExpense();
		for(RepaymentExpense repaymentExpense : repaymentExpenses){
			RepaymentExpenseActiveRepaymentDTO repaymentExpenseActiveRepaymentDTO = new RepaymentExpenseActiveRepaymentDTO();
			BeanUtils.copyProperties(repaymentExpense, repaymentExpenseActiveRepaymentDTO);
			repaymentExpenseActiveRepaymentDTO.setRepaymentAmount(MoneyUtil.formatMoney(repaymentExpense.getRepaymentAmount()));
			//判断费用是否可以还款，还款日在其之前的费用是否为已结清
			BeanUtils.copyProperties(repaymentExpense, activeRepaymentExpense);
			if(StringUtils.equals(userType.name(), UserType.CANA.name())){
				repaymentExpenseActiveRepaymentDTO.setRepaied(false);
			}else{
				repaymentExpenseActiveRepaymentDTO.setRepaied(activeRepaymentService.isRepaymentExpenseReadyToPay(activeRepaymentExpense));
			}
			rExpenseRedisDTOs.add(repaymentExpenseActiveRepaymentDTO);
		}
		return rExpenseRedisDTOs;
	}
	
	/**
	 * 
	 * @param expenses
	 * @return
	 * @throws Exception
	 */
	private List<RepaymentExpenseActiveRepaymentDTO> convertActiveRepaymentExpenseToRepaymentExpenseRedisDTO(List<ActiveRepaymentExpense> expenses, UserType userType) 
			throws Exception{
		List<RepaymentExpenseActiveRepaymentDTO> rExpenseRedisDTOs = new ArrayList<>();
		for(ActiveRepaymentExpense activeRepaymentExpense : expenses){
			RepaymentExpenseActiveRepaymentDTO repaymentExpenseActiveRepaymentDTO = new RepaymentExpenseActiveRepaymentDTO();
			BeanUtils.copyProperties(activeRepaymentExpense, repaymentExpenseActiveRepaymentDTO);
			repaymentExpenseActiveRepaymentDTO.setPaidAmount(MoneyUtil.formatMoney(activeRepaymentExpense.getPaidAmount()));
			repaymentExpenseActiveRepaymentDTO.setRepaymentAmount(MoneyUtil.formatMoney(activeRepaymentExpense.getRepaymentAmount()));
			//判断费用是否可以还款，还款日在其之前的费用是否为已结清
			if(null != userType){
				if(StringUtils.equals(userType.name(), UserType.CANA.name())){
					repaymentExpenseActiveRepaymentDTO.setRepaied(false);		
				}else{
					repaymentExpenseActiveRepaymentDTO.setRepaied(activeRepaymentService.isRepaymentExpenseReadyToPay(activeRepaymentExpense));		
				}
			}
			if(SettleStatus.SETTLED == SettleStatus.valueOf(activeRepaymentExpense.getSettleStatus()))
				repaymentExpenseActiveRepaymentDTO.setSettleStatus(SettleStatus.SETTLED.desc());
			else
				repaymentExpenseActiveRepaymentDTO.setSettleStatus(SettleStatus.UNSETTLE.desc());

			rExpenseRedisDTOs.add(repaymentExpenseActiveRepaymentDTO);
		}
		return rExpenseRedisDTOs;
	}

	@Override
	public ListResult<RepaymentExpenseActiveRepaymentDTO> queryRepaymentExpensesBySearchCondition(QueryRepaymentAndExpenseRequestDTO queryDTO) throws Exception {
		ListResult<RepaymentExpenseActiveRepaymentDTO> result = new ListResult<>();
		List<ActiveRepaymentExpense> activeRepaymentExpenseList = repaymentPlanAndExpenseSearchMapper.searchRepaymentExpense(queryDTO);
		List<RepaymentExpenseActiveRepaymentDTO> RepaymentExpenseActiveRepaymentDTOList = convertActiveRepaymentExpenseToRepaymentExpenseRedisDTO(activeRepaymentExpenseList, null);
		int totalNum = repaymentPlanAndExpenseSearchMapper.countRepaymentExpense(queryDTO);
		result.setData(RepaymentExpenseActiveRepaymentDTOList);
		result.setTotalNum(totalNum);
		return result;
	}

	@Override
	public ListResult<RepaymentPlanActiveRepaymentDTO> queryRepaymentPlansBySearchCondition(QueryRepaymentAndExpenseRequestDTO queryDTO) throws Exception {
		ListResult<RepaymentPlanActiveRepaymentDTO> result = new ListResult<RepaymentPlanActiveRepaymentDTO>();
		List<ActiveRepaymentPlan> activeRepaymentPlanList = repaymentPlanAndExpenseSearchMapper.searchRepaymentPlan(queryDTO);
		List<RepaymentPlanActiveRepaymentDTO> RepaymentPlanActiveRepaymentDTOList = convertActiveRepaymentPlanToRepaymentPlanActiveRepaymentDTO(activeRepaymentPlanList, null);
		int totalNum = repaymentPlanAndExpenseSearchMapper.countRepaymentPlan(queryDTO);
		result.setTotalNum(totalNum);
		result.setData(RepaymentPlanActiveRepaymentDTOList);
		return result;
	}
	
	@Override
	public String getTransferInAccountNo(String loanInfoId){
		return loanInfoConfigMapper.selectByPrimaryKey(loanInfoId).getFactorTransferInAccountNo();
	}
	
	@Override
	public void updateOnActiveRepaymentSuccess(String loanInfoId, String amount, ActiveRepaymentType activeRepaymentType, String accountNo){
		try {
			Map<String, String> accountTradeStatusAndBusinessSeq = activeRepaymentService.getAccountTradeStatusAndBusinessSeq(accountNo, loanInfoId, getTransferInAccountNo(loanInfoId), amount);
			if(StringUtils.equals(AccountTradeStatus.TRADE_SUCCESS.name(), accountTradeStatusAndBusinessSeq.get(Constants.ACCOUNT_TRADE_STATUS))){
				activeRepaymentTransactionService.updateOnActiveRepaymentSuccess(loanInfoId, amount, activeRepaymentType, accountTradeStatusAndBusinessSeq.get(Constants.BUSINESS_SEQ));
			}
			else if(AccountTradeStatus.TRADE_FAIL.name().equals(accountTradeStatusAndBusinessSeq.get(Constants.ACCOUNT_TRADE_STATUS)))
				throw WebException.instance("转账失败");
			else throw WebException.instance("系统错误，请联系cana管理员！");
		} catch (Exception e) {
			throw WebException.instance("系统错误，请联系cana管理员！");
		}
	}

	@Override
	public String getCurrentRepaymentNum(String redisKey) throws Exception {
		RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration = (RepaymentPlanAdjustmentIntegration)redisCache.get(redisKey);
		return MoneyUtil.formatMoney(repaymentPlanAdjustmentIntegration.getAccountTotalMoney());
	}
}
