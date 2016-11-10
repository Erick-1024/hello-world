package com.cana.repayment.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.cana.repayment.dao.mapper.gen.RepaymentExpenseMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPlanMapper;
import com.cana.repayment.dao.po.RepaymentPlanExample;
import com.cana.repayment.service.IRepaymentPlanService;
import com.cana.vbam.common.repayment.dto.AccountRepaymentPlanRedisDTO;
import com.cana.vbam.common.repayment.dto.ChargeDTO;
import com.cana.vbam.common.repayment.dto.PaidRepaymentPlanRedisDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanAdjustmentIntegration;
import com.cana.vbam.common.repayment.enums.PeriodStatus;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.CanaDateUtils;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.travelzen.framework.core.exception.WebException;

@Service
public class RepaymentPlanServiceImpl implements IRepaymentPlanService{

	@Resource
	private RepaymentPlanMapper repaymentPlanMapper;
	
	@Resource
	private RepaymentExpenseMapper repaymentExpenseMapper;
	
	@Resource
	private RepaymentLoanInfoMapper repaymentLoanInfoMapper;
	
	@Resource
	private IVbamCommonService vbamCommonService;

	@Override
	public int queryRepaymentPlanCount(RepaymentPlanExample repaymentPlanExample) throws Exception {
		return repaymentPlanMapper.countByExample(repaymentPlanExample);
	}

	@Override
	public void distributePlanWithRepayment(RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration, ChargeDTO charge,BigDecimal earlyRepaymentRatio) throws Exception {
		long importCharge = charge.getCharge(); 
		List<PaidRepaymentPlanRedisDTO> paidPlanList = new ArrayList<>();
		List<AccountRepaymentPlanRedisDTO> accountPlanList = repaymentPlanAdjustmentIntegration.getOriginAccountRepaymentPlanRedisDTOList();
		repaymentPlanAdjustmentIntegration.copyPaidRepaymentPlanListToOrigin(repaymentPlanAdjustmentIntegration.getOriginPaidRepaymentPlanRedisDTOList(), paidPlanList);
		for(int i=0; i<paidPlanList.size(); i++){
			PaidRepaymentPlanRedisDTO paidPlan = paidPlanList.get(i);
			String periodStatus = paidPlan.getPeriodStatus();
			if(StringUtils.equals(periodStatus, PeriodStatus.OVERDUE.name()) || StringUtils.equals(periodStatus, PeriodStatus.EXTENSION.name())){
				computeBeforePeriodPaid(paidPlan, accountPlanList.get(i), charge, periodStatus);
			}else if(StringUtils.equals(periodStatus, PeriodStatus.CURRENTIN.name())){
				computeNowPeriodPaid(paidPlan, accountPlanList.get(i), charge, earlyRepaymentRatio, true);
			}else if(StringUtils.equals(periodStatus, PeriodStatus.CURRENTOUT.name())){
				computeNowPeriodPaid(paidPlan, accountPlanList.get(i), charge, earlyRepaymentRatio, false);
			}

		}
		repaymentPlanAdjustmentIntegration.setPaidRepaymentPlanRedisDTOList(paidPlanList);
		repaymentPlanAdjustmentIntegration.generateRepaymentInfo(MoneyArithUtil.convertMoneyToString(importCharge),repaymentPlanAdjustmentIntegration);
	}
	
	
	/**
	 * 根据当前时间判断计划是当前期,-2是往期逾期 -1是往期展期，0是当期还款日，1是当期非还款日，2未来期
	 * @param plan
	 * @return
	 */
	@Override
	public String judgePlanPeriodByNowDate(String lastRepaymentDate, String repaymentDate, int extenseDays){
		DateTime valueDateTime;
		DateTime dueDate;
		DateTime extenseDate;
		DateTime nowDate;
		try {
			valueDateTime = new DateTime(lastRepaymentDate);
			dueDate = new DateTime(repaymentDate);
			extenseDate = new DateTime(CanaDateUtils.plusDays(repaymentDate, extenseDays));
			nowDate = new DateTime(vbamCommonService.getCurrentDate());
		} catch (Exception e) {
			throw WebException.instance("时间转换错误！");
		}
		if(valueDateTime.isAfter(nowDate)){
			return PeriodStatus.FUTURE.name();
		}else if(extenseDate.isBefore(nowDate)){
			return PeriodStatus.OVERDUE.name();
		}else if(dueDate.isBefore(nowDate)){
			return PeriodStatus.EXTENSION.name();
		}else if(dueDate.isEqual(nowDate)){
			return PeriodStatus.CURRENTIN.name();
		}else{
			return PeriodStatus.CURRENTOUT.name();
		}
	}
	
	/**
	 * 计算往期已还
	 * @param paidPlan
	 * @param accountPlan
	 */
	private void computeBeforePeriodPaid(PaidRepaymentPlanRedisDTO paidPlan, AccountRepaymentPlanRedisDTO accountPlan, ChargeDTO charge, String periodStatus){
		if(accountPlan.getSettleStatus().equals(SettleStatus.UNSETTLE.desc())){
			if(StringUtils.equals(periodStatus, PeriodStatus.OVERDUE.name())){
				paidPlan.setPaidOverdueManagerFee(computePaidByAccount(paidPlan.getPaidOverdueManagerFee(), accountPlan.getOverdueManagerFee(), charge));
				paidPlan.setPaidExtensionCharge(computePaidByAccount(paidPlan.getPaidExtensionCharge(), accountPlan.getExtensionCharge(), charge));
				paidPlan.setPaidOverdueServiceCharge(computePaidByAccount(paidPlan.getPaidOverdueServiceCharge(), accountPlan.getOverdueServiceCharge(), charge));
				paidPlan.setPaidOverdueInterest(computePaidByAccount(paidPlan.getPaidOverdueInterest(), accountPlan.getOverdueInterest(), charge));
				paidPlan.setPaidOverduePrincipal(computePaidByAccount(paidPlan.getPaidOverduePrincipal(), accountPlan.getOverduePrincipal(), charge));
			
			}
			if(StringUtils.equals(periodStatus, PeriodStatus.EXTENSION.name())){
				paidPlan.setPaidExtensionCharge(computePaidByAccount(paidPlan.getPaidExtensionCharge(), accountPlan.getExtensionCharge(), charge));
				paidPlan.setPaidNormalServiceCharge(computePaidByAccount(paidPlan.getPaidNormalServiceCharge(), accountPlan.getAccountServiceCharge(), charge));
				paidPlan.setPaidNormalInterest(computePaidByAccount(paidPlan.getPaidNormalInterest(), accountPlan.getAccountInterest(), charge));
				paidPlan.setPaidNormalPrincipal(computePaidByAccount(paidPlan.getPaidNormalPrincipal(), accountPlan.getAccountPrincipal(), charge));
			}
			
			paidPlan.setPaidTotalAmount(computeTotalAmountCharge(paidPlan));
		}
	}
	
	/**
	 * 计算当期已还
	 * @param paidPlan
	 * @param accountPlan
	 * @param charge
	 * @param earlyRepaymentRatio
	 */
	private void computeNowPeriodPaid(PaidRepaymentPlanRedisDTO paidPlan, AccountRepaymentPlanRedisDTO accountPlan, ChargeDTO charge,
			BigDecimal earlyRepaymentRatio, boolean isRepaymentDate){
		paidPlan.setPaidNormalServiceCharge(computePaidByAccount(paidPlan.getPaidNormalServiceCharge(), accountPlan.getAccountServiceCharge(), charge));
		paidPlan.setPaidNormalInterest(computePaidByAccount(paidPlan.getPaidNormalInterest(), accountPlan.getAccountInterest(), charge));
		long principal = 0;
		long earlyRepaymentPrincipal = 0;
		if(isRepaymentDate){
			principal = MoneyArithUtil.convertStringToMoney(computePaidByAccount(paidPlan.getPaidNormalPrincipal(), accountPlan.getAccountPrincipal(), charge));
			
		}
		earlyRepaymentPrincipal = computeEarlyRepaymentPrincipal(charge, earlyRepaymentRatio);
		paidPlan.setPaidNormalPrincipal(MoneyArithUtil.convertMoneyToString(principal + earlyRepaymentPrincipal + MoneyArithUtil.convertStringToMoney(paidPlan.getPaidNormalPrincipal())));
		paidPlan.setPaidEarlyRepaymentCharge(computeEarlyRepaymentCharge(earlyRepaymentPrincipal, earlyRepaymentRatio, paidPlan.getPaidEarlyRepaymentCharge()));
		
		paidPlan.setPaidTotalAmount(computeTotalAmountCharge(paidPlan));
	}
	
	/**
	 * 根据应还计算已还
	 * @param paid
	 * @param account
	 * @param charge
	 * @return
	 */
	private String computePaidByAccount(String paid, String account, ChargeDTO chargeDTO){
		long charge = chargeDTO.getCharge();
		long accountFee = MoneyArithUtil.convertStringToMoney(account);
		if(charge > 0){
			if(accountFee > 0){
				long paidFee = MoneyArithUtil.convertStringToMoney(paid);
//				if(paidFee < accountFee){
//					if(accountFee - paidFee > charge){
//						paid = MoneyArithUtil.convertMoneyToString(charge + paidFee);
//						charge = 0;
//					}else{
//						paid = MoneyArithUtil.convertMoneyToString(accountFee);
//						charge = charge - accountFee + paidFee;
//					}
//					chargeDTO.setCharge(charge);
//				}
				if( accountFee > charge ){
					paid = MoneyArithUtil.convertMoneyToString(paidFee + charge);
					charge = 0;
				}else{
					paid = MoneyArithUtil.convertMoneyToString(paidFee + accountFee);
					charge -= accountFee;
				}
				chargeDTO.setCharge(charge);
			}
		}
		return paid;
	}
	
	/**
	 * 根据提前还款金额，计算提前还款本金
	 * @param charge
	 * @param earlyRepaymentRatio
	 * @return
	 */
	private long computeEarlyRepaymentPrincipal(ChargeDTO chargeDTO, BigDecimal earlyRepaymentRatio){
		long charge = chargeDTO.getCharge();
		long principal = 0;
		if(charge > 0){
			principal = MoneyArithUtil.divide(new BigDecimal(charge), MoneyArithUtil.add(new BigDecimal(1), earlyRepaymentRatio), 0).longValue();
			charge = 0;
			chargeDTO.setCharge(charge);
		}
		return principal;
	}
		
	/**
	 * 计算提前还款手续费
	 * @param principal
	 * @param earlyRepaymentRatio
	 * @return
	 */
	private String computeEarlyRepaymentCharge(long principal, BigDecimal earlyRepaymentRatio,String earlyRepaymentCharge){
		BigDecimal principalDec = new BigDecimal(principal);
		principalDec = MoneyArithUtil.round(MoneyArithUtil.mul(principalDec, earlyRepaymentRatio), 0);
		return MoneyArithUtil.convertMoneyToString(principalDec.longValue()+MoneyArithUtil.convertStringToMoney(earlyRepaymentCharge));
		
	}
	
	/**
	 * 计算还款总金额
	 * @param plan
	 * @return
	 */
	private String computeTotalAmountCharge(PaidRepaymentPlanRedisDTO plan){
		long amount = 0;
		amount += MoneyArithUtil.convertStringToMoney(plan.getPaidNormalPrincipal());
		amount += MoneyArithUtil.convertStringToMoney(plan.getPaidNormalInterest());
		amount += MoneyArithUtil.convertStringToMoney(plan.getPaidNormalServiceCharge());
		amount += MoneyArithUtil.convertStringToMoney(plan.getPaidOverduePrincipal());
		amount += MoneyArithUtil.convertStringToMoney(plan.getPaidOverdueInterest());
		amount += MoneyArithUtil.convertStringToMoney(plan.getPaidOverdueServiceCharge());
		amount += MoneyArithUtil.convertStringToMoney(plan.getPaidExtensionCharge());
		amount += MoneyArithUtil.convertStringToMoney(plan.getPaidEarlyRepaymentCharge());
		amount += MoneyArithUtil.convertStringToMoney(plan.getPaidOverdueManagerFee());
		return MoneyArithUtil.convertMoneyToString(amount);
	}
}
