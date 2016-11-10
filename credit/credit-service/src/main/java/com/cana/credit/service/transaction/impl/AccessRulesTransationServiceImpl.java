package com.cana.credit.service.transaction.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.credit.dao.mapper.gen.CustomerApplyMapper;
import com.cana.credit.dao.po.AccessRule;
import com.cana.credit.dao.po.CustomerApply;
import com.cana.credit.service.IRetryTaskService;
import com.cana.credit.service.IWhiteListService;
import com.cana.credit.service.transaction.IAccessRulesTransationService;
import com.cana.credit.service.utils.NewestAccessRuleHolder;
import com.cana.flight.finance.service.IRepaymentService;
import com.cana.vbam.common.credit.dto.apply.AutomaticAuditDataDTO;
import com.cana.vbam.common.credit.enums.AccessAutomaticState;
import com.cana.vbam.common.credit.enums.AccessManualState;
import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnClass;
import com.travelzen.framework.core.common.ReturnCode;

@Service
public class AccessRulesTransationServiceImpl implements IAccessRulesTransationService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private IRetryTaskService retryTaskServiceImpl;
	
	@Resource
	private CustomerApplyMapper customerApplyMapper;
	
	@Resource
	private IRepaymentService repaymentServiceImpl;
	
	@Resource 
	private IWhiteListService whiteListService;
	
	@Override
	public boolean checkApplyByAccessRules(CustomerApply apply) {
		AccessRule accessRule = null;
//		if(apply.getInWhitelist()!=null && !apply.getInWhitelist()){
//			accessRule = NewestAccessRuleHolder.nonWhiteCustomerNewestAccessRule;
//			logger.info("使用准入规则——非白名单,客户id为{}",apply.getTzCustomerId());
//		}else{
			accessRule = NewestAccessRuleHolder.whiteCustomerNewestAccessRule;
			logger.info("使用准入规则——白名单,客户id为{}",apply.getTzCustomerId());
//		}
			
		AutomaticAuditDataDTO automaticAuditDataDTO = getAutomaticAuditData(apply.getTzCustomerId());
		List<ReturnClass> errorRetClassList = checkApply(apply,accessRule,automaticAuditDataDTO);
		String errorRetMsg = "";
		for(ReturnClass errorRetClass : errorRetClassList)
			errorRetMsg += errorRetClass.getMessage()+"|";
		if(StringUtils.isNotBlank(errorRetMsg))
			errorRetMsg = errorRetMsg.substring(0, errorRetMsg.length()-1);
		if(CollectionUtils.isEmpty(errorRetClassList)){
			updateCustomerApply(apply,AccessAutomaticState.ACCESS,null,accessRule.getBatchNo(),automaticAuditDataDTO);
			return true;
		}else{
			updateCustomerApply(apply,AccessAutomaticState.NOTACCESS,errorRetMsg,accessRule.getBatchNo(),automaticAuditDataDTO);
			retryTaskServiceImpl.createAuditResultNotify(Institution.travelzen, apply.getTzCustomerId(), errorRetClassList.get(0).getRetCode().getRetCode(), errorRetClassList.get(0).getMessage(), apply.getAuditNotifyUrl());
			return false;
		}
	
	}
	
	/**
	 * 获取客户的合作期限，逾期数据
	 * @param customerId
	 * @return
	 */
	private AutomaticAuditDataDTO getAutomaticAuditData(String customerId){
		AutomaticAuditDataDTO automaticAuditDataDTO = new AutomaticAuditDataDTO();
		
		automaticAuditDataDTO.setCooperationPeriod(repaymentServiceImpl.calculateCooperationPeriodByCustomerId(customerId));
		automaticAuditDataDTO.setOverdueRate(repaymentServiceImpl.getOverdueRateBycustomerId(customerId,3));
		automaticAuditDataDTO.setOverdueTimes(repaymentServiceImpl.getOverdueTimesBycustomerId(customerId,3));
		automaticAuditDataDTO.setOverdueDays(repaymentServiceImpl.getOverdueTimesBycustomerIdAndOverdueDays(customerId,3));
		
		return automaticAuditDataDTO;
	}
	
	/**
	 * 检查申请是否符合准入规则
	 * @param apply 申请
	 * @param accessRule 准入规则
	 * @param accessAutomaticDataDTO 客户的合作期限，逾期数据
	 * @return 不符合准入规则的错误list，如果返回的list的size为0,就说明准入通过
	 */
	private List<ReturnClass> checkApply(CustomerApply apply, AccessRule accessRule,AutomaticAuditDataDTO automaticAuditDataDTO){
		List<ReturnClass> errorReturnClassList = new ArrayList<>();
		String customerId = apply.getTzCustomerId();
		
//		AccessRuleFitObject fitObject = null;
//		if(apply.getInWhitelist()!=null && !apply.getInWhitelist())
//			fitObject = AccessRuleFitObject.NON_WHITE_CUSTOMER;
//		else
//			fitObject = AccessRuleFitObject.WHITE_CUSTOMER;
		
		//是否检查是否是白名单客户
		if(accessRule.getIsCheckWhiteCustomer()){
//			if(AccessRuleFitObject.NON_WHITE_CUSTOMER.equals(fitObject))
//				if(whiteListService.isAvailableWhiteCustomer(customerId))
//					return new ReturnClass(ReturnCode.TP0021);
//			
//			if(AccessRuleFitObject.WHITE_CUSTOMER.equals(fitObject))
				//if(!whiteListService.isAvailableWhiteCustomer(customerId))//暂时不要
					//errorReturnClassList.add(new ReturnClass(ReturnCode.TP0001));//暂时不要
		}
		
		//检查合作期限，逾期率，逾期次数
//		errorReturnClassList.addAll(checkBaseApplyData(accessRule,customerId));
		
		//检查合作期限
		Integer cooperationPeriod = automaticAuditDataDTO.getCooperationPeriod();
		if(cooperationPeriod == null)
			errorReturnClassList.add(new ReturnClass(ReturnCode.TP0002));
		if(accessRule.getCooperationPeriodMin() != null && cooperationPeriod.compareTo(accessRule.getCooperationPeriodMin()) < 0)
			errorReturnClassList.add(new ReturnClass(ReturnCode.TP0003,"小于"+accessRule.getCooperationPeriodMin().toString()));
		//判断客户一段时间内每月的订票是否连续
		if(!repaymentServiceImpl.checkBillContinuous(customerId, accessRule.getCooperationPeriodMin()))
			errorReturnClassList.add(new ReturnClass(ReturnCode.TP0023, accessRule.getCooperationPeriodMin().toString()));
		if(accessRule.getCooperationPeriodMax() != null && cooperationPeriod.compareTo(accessRule.getCooperationPeriodMax()) > 0)
			errorReturnClassList.add(new ReturnClass(ReturnCode.TP0003,"大于"+accessRule.getCooperationPeriodMax().toString()));
		
		//检查订单采购逾期率
		BigDecimal overdueRate = automaticAuditDataDTO.getOverdueRate();
		if(overdueRate == null || overdueRate.compareTo(accessRule.getOverdueRateTz()) > 0)
			errorReturnClassList.add(new ReturnClass(ReturnCode.TP0006,MoneyArithUtil.convertInterestRateToString(overdueRate),MoneyArithUtil.convertInterestRateToString(accessRule.getOverdueRateTz())));
			
		//检查订单采购逾期次数
		Integer overdueTimes = automaticAuditDataDTO.getOverdueTimes();
		if(overdueTimes == null || overdueTimes.compareTo(accessRule.getOverdueTimesTz()) > 0)
			errorReturnClassList.add(new ReturnClass(ReturnCode.TP0007,overdueTimes,accessRule.getOverdueTimesTz().toString()));

		//检查逾期记录中逾期天数总和
		Integer overdueDays = automaticAuditDataDTO.getOverdueDays();
		if(accessRule.getOverdueDaysTz()!=null){
			if(overdueDays != null && overdueDays.compareTo(accessRule.getOverdueDaysTz()) > 0)
				errorReturnClassList.add(new ReturnClass(ReturnCode.TP0022,overdueDays,accessRule.getOverdueDaysTz().toString()));
		}
				
		//检查增长率
		if(accessRule.getPurchaseOrderGrowthRate()!=null){
			BigDecimal growthRate = repaymentServiceImpl.getGrowthRateByCustomerId(customerId);
			if(growthRate == null || growthRate.compareTo(accessRule.getPurchaseOrderGrowthRate()) < 0)
				errorReturnClassList.add(new ReturnClass(ReturnCode.TP0008,MoneyArithUtil.convertInterestRateToString(growthRate),MoneyArithUtil.convertInterestRateToString(accessRule.getPurchaseOrderGrowthRate())));
		}
		return errorReturnClassList;
	}
	
	/**
	 * 根据applyId更新CustomerApply记录
	 * @param apply
	 * @param auditState  准入规则审核状态
	 * @param auditRemarks  准入规则审核备注（审核不通过的时候备注原因）
	 * @param batchNo 规则批次号
	 * @param accessAutomaticDataDTO 准入时计算的合作月份，逾期数据
	 */
	private void updateCustomerApply(CustomerApply apply,AccessAutomaticState auditState,String auditRemarks,Integer batchNo,AutomaticAuditDataDTO automaticAuditDataDTO){
		apply.setAccessAutomaticState(auditState.name());
		apply.setAutomaticAuditRemarks(auditRemarks);
		apply.setAutomaticAuditRuleBatchNo(batchNo);
		apply.setAutomaticAuditData(new Gson().toJson(automaticAuditDataDTO));
		if(AccessAutomaticState.ACCESS.equals(auditState))
			apply.setAccessManualState(AccessManualState.WAIT.name());
		customerApplyMapper.updateByPrimaryKeySelective(apply);
	}
	
//	/**
//	 * 检查客户的下游回款账期是否符合要求
//	 * 必须是无账期 or 有账期，值为AccountPeriodType中的一个
//	 * @param downstreamRepaymentAccountPeriod
//	 * @return true 通过要求，false 不通过要求
//	 */
//	private boolean checkDownstreamRepaymentAccountPeriod(String downstreamRepaymentAccountPeriod){
//		return StringUtils.isEmpty(downstreamRepaymentAccountPeriod) || EnumUtils.isValidEnum(AccountPeriodType.class, downstreamRepaymentAccountPeriod);
//	}
	
	/**
	 * (暂时废弃)
	 * 检查合作期限，逾期
	 * @param accessRule　准入规则
	 * @param customerId 真旅客户id
	 * @return 不符合准入规则的错误list，如果返回的list的size为0,就说明准入通过
	 */
	@Override
	public List<ReturnClass> checkBaseApplyData(AccessRule accessRule,String customerId){
		List<ReturnClass> errorReturnClassList = new ArrayList<>();
		//检查合作期限
		Integer cooperationPeriod = repaymentServiceImpl.calculateCooperationPeriodByCustomerId(customerId);
		if(cooperationPeriod == null)
			errorReturnClassList.add(new ReturnClass(ReturnCode.TP0002));
		if(accessRule.getCooperationPeriodMin() != null && cooperationPeriod.compareTo(accessRule.getCooperationPeriodMin()) < 0)
			errorReturnClassList.add(new ReturnClass(ReturnCode.TP0003,"小于"+accessRule.getCooperationPeriodMin().toString()));
		//判断客户一段时间内每月的订票是否连续
		if(!repaymentServiceImpl.checkBillContinuous(customerId, accessRule.getCooperationPeriodMin()))
			errorReturnClassList.add(new ReturnClass(ReturnCode.TP0023, accessRule.getCooperationPeriodMin().toString()));
		if(accessRule.getCooperationPeriodMax() != null && cooperationPeriod.compareTo(accessRule.getCooperationPeriodMax()) > 0)
			errorReturnClassList.add(new ReturnClass(ReturnCode.TP0003,"大于"+accessRule.getCooperationPeriodMax().toString()));
		
		
		//逾期率，逾期次数
		BigDecimal overdueRate = null;
		Integer overdueTimes = null;
		Integer overdueDays = null;
//		if(AccessRuleFitObject.WHITE_CUSTOMER.name().equals(accessRule.getFitObject())){
			overdueRate = repaymentServiceImpl.getOverdueRateBycustomerId(customerId,3);
			overdueTimes = repaymentServiceImpl.getOverdueTimesBycustomerId(customerId,3);
			if(accessRule.getOverdueDaysTz()!=null)
				overdueDays = repaymentServiceImpl.getOverdueTimesBycustomerIdAndOverdueDays(customerId,3);
//		}
//		else if(AccessRuleFitObject.NON_WHITE_CUSTOMER.name().equals(accessRule.getFitObject())){
//			overdueRate = repaymentServiceImpl.getOverdueRateBycustomerId(customerId,13);
//			overdueTimes = repaymentServiceImpl.getOverdueTimesBycustomerId(customerId,13);
//			if(accessRule.getOverdueDaysTz()!=null)
//				overdueTimesByOverdueDaysTz = repaymentServiceImpl.getOverdueTimesBycustomerIdAndOverdueDays(customerId, accessRule.getOverdueDaysTz(),13);
//		}
		
		
		//检查订单采购逾期率
		if(overdueRate == null || overdueRate.compareTo(accessRule.getOverdueRateTz()) > 0)
			errorReturnClassList.add(new ReturnClass(ReturnCode.TP0006,MoneyArithUtil.convertInterestRateToString(accessRule.getOverdueRateTz())));
			
		//检查订单采购逾期次数
		if(overdueTimes == null || overdueTimes.compareTo(accessRule.getOverdueTimesTz()) > 0)
			errorReturnClassList.add(new ReturnClass(ReturnCode.TP0007,accessRule.getOverdueTimesTz().toString()));

		//检查逾期记录中是否都小于等于规则中配置的逾期天数
		if(accessRule.getOverdueDaysTz()!=null){
			if(overdueDays == null || overdueDays.compareTo(accessRule.getOverdueDaysTz()) > 0)
				errorReturnClassList.add(new ReturnClass(ReturnCode.TP0022,accessRule.getOverdueDaysTz().toString()));
		}
		
		return errorReturnClassList;
	}
	
}
