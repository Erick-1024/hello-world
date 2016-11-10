package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cana.vbam.common.repayment.enums.ChargeType;
import com.cana.vbam.common.repayment.enums.PeriodStatus;
import com.cana.vbam.common.repayment.enums.RepaymentMethod;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.utils.CanaDateUtils;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.travelzen.framework.core.exception.WebException;

public class RepaymentPlanAdjustmentIntegration implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5028816427517119454L;
	
	/**
	 * 已还还款计划列表
	 */
	private List<PaidRepaymentPlanRedisDTO> paidRepaymentPlanRedisDTOList;
	
	/**
	 * 待还还款计划列表
	 */
	private List<AccountRepaymentPlanRedisDTO> accountRepaymentPlanRedisDTOList;
	
	/**
	 * 还款费用列表
	 */
	private List<PaidRepaymentExpenseRedisDTO> paidRepaymentExpenseRedisDTOList;
	
	/**
	 * 原始已还还款计划列表
	 */
	private List<PaidRepaymentPlanRedisDTO> originPaidRepaymentPlanRedisDTOList;
	
	/**
	 * 原始待还还款计划列表
	 */
	private List<AccountRepaymentPlanRedisDTO> originAccountRepaymentPlanRedisDTOList;
	
	/**
	 * 原始还款费用列表
	 */
	private List<PaidRepaymentExpenseRedisDTO> originPaidRepaymentExpenseRedisDTOList;
	
	/**
	 * 费用&计划还款信息汇总列表
	 */
	private List<RepaymentSingleCollectDTO> planAndExpenseRepaymentSummaryList;
	
	/**
	 * 费用&计划还款信息明细列表
	 */
	private List<RepaymentSingleDistributeDetailDTO> repaymentSingleDistributeDetailList;
	
	/**
	 * 还款计划&费用版本号
	 */
	private String loanInfoVersion;
	
	/**
	 * 当前应还总金额
	 */
	private long accountTotalMoney = 0;
	
	/**
	 * 变更Id
	 */
	private String changeId;

	public List<PaidRepaymentPlanRedisDTO> getPaidRepaymentPlanRedisDTOList() {
		return paidRepaymentPlanRedisDTOList;
	}

	public void setPaidRepaymentPlanRedisDTOList(
			List<PaidRepaymentPlanRedisDTO> paidRepaymentPlanRedisDTOList) {
		this.paidRepaymentPlanRedisDTOList = paidRepaymentPlanRedisDTOList;
	}

	public List<AccountRepaymentPlanRedisDTO> getAccountRepaymentPlanRedisDTOList() {
		return accountRepaymentPlanRedisDTOList;
	}

	public void setAccountRepaymentPlanRedisDTOList(
			List<AccountRepaymentPlanRedisDTO> accountRepaymentPlanRedisDTOList) {
		this.accountRepaymentPlanRedisDTOList = accountRepaymentPlanRedisDTOList;
	}

	public List<PaidRepaymentExpenseRedisDTO> getPaidRepaymentExpenseRedisDTOList() {
		return paidRepaymentExpenseRedisDTOList;
	}

	public void setPaidRepaymentExpenseRedisDTOList(
			List<PaidRepaymentExpenseRedisDTO> paidRepaymentExpenseRedisDTOList) {
		this.paidRepaymentExpenseRedisDTOList = paidRepaymentExpenseRedisDTOList;
	}

	public long getAccountTotalMoney() {
		return accountTotalMoney;
	}

	public void setAccountTotalMoney(long accountTotalMoney) {
		this.accountTotalMoney = accountTotalMoney;
	}

	public List<PaidRepaymentPlanRedisDTO> getOriginPaidRepaymentPlanRedisDTOList() {
		return originPaidRepaymentPlanRedisDTOList;
	}

	public void setOriginPaidRepaymentPlanRedisDTOList(
			List<PaidRepaymentPlanRedisDTO> originPaidRepaymentPlanRedisDTOList) {
		this.originPaidRepaymentPlanRedisDTOList = originPaidRepaymentPlanRedisDTOList;
	}

	public List<AccountRepaymentPlanRedisDTO> getOriginAccountRepaymentPlanRedisDTOList() {
		return originAccountRepaymentPlanRedisDTOList;
	}

	public void setOriginAccountRepaymentPlanRedisDTOList(
			List<AccountRepaymentPlanRedisDTO> originAccountRepaymentPlanRedisDTOList) {
		this.originAccountRepaymentPlanRedisDTOList = originAccountRepaymentPlanRedisDTOList;
	}

	public List<PaidRepaymentExpenseRedisDTO> getOriginPaidRepaymentExpenseRedisDTOList() {
		return originPaidRepaymentExpenseRedisDTOList;
	}

	public void setOriginPaidRepaymentExpenseRedisDTOList(
			List<PaidRepaymentExpenseRedisDTO> originPaidRepaymentExpenseRedisDTOList) {
		this.originPaidRepaymentExpenseRedisDTOList = originPaidRepaymentExpenseRedisDTOList;
	}

	public List<RepaymentSingleCollectDTO> getPlanAndExpenseRepaymentSummaryList() {
		return planAndExpenseRepaymentSummaryList;
	}

	public void setPlanAndExpenseRepaymentSummaryList(
			List<RepaymentSingleCollectDTO> planAndExpenseRepaymentSummaryList) {
		this.planAndExpenseRepaymentSummaryList = planAndExpenseRepaymentSummaryList;
	}

	public List<RepaymentSingleDistributeDetailDTO> getRepaymentSingleDistributeDetailList() {
		return repaymentSingleDistributeDetailList;
	}

	public void setRepaymentSingleDistributeDetailList(
			List<RepaymentSingleDistributeDetailDTO> repaymentSingleDistributeDetailList) {
		this.repaymentSingleDistributeDetailList = repaymentSingleDistributeDetailList;
	}
	
	public AccountRepaymentPlanRedisDTO getSingleAccountRepaymentPlan(String planId){
		AccountRepaymentPlanRedisDTO accountPlanDTO = new AccountRepaymentPlanRedisDTO();
		accountPlanDTO.setId(planId);
		int index = accountRepaymentPlanRedisDTOList.indexOf(accountPlanDTO);
		if(index == -1){
			return null;
		}
		return accountRepaymentPlanRedisDTOList.get(index);
	}

	public String getLoanInfoVersion() {
		return loanInfoVersion;
	}

	public void setLoanInfoVersion(String loanInfoVersion) {
		this.loanInfoVersion = loanInfoVersion;
	}

	public PaidRepaymentPlanRedisDTO getSinglePaidRepaymentPlan(String planId){
		PaidRepaymentPlanRedisDTO paidPlanDTO = new PaidRepaymentPlanRedisDTO();
		paidPlanDTO.setId(planId);
		int index = paidRepaymentPlanRedisDTOList.indexOf(paidPlanDTO);
		if(index == -1){
			return null;
		}
		return paidRepaymentPlanRedisDTOList.get(index);
	}
	
	public RepaymentSingleDistributeDetailDTO getRepaymentSingleDistributeDetail(String relatedId){
		RepaymentSingleDistributeDetailDTO detailDTO = new RepaymentSingleDistributeDetailDTO();
		detailDTO.setRelatedId(relatedId);
		int index = repaymentSingleDistributeDetailList.indexOf(detailDTO);
		if(index == -1){
			return detailDTO;
		}
		return repaymentSingleDistributeDetailList.get(index);
	}
	
	public String getChangeId() {
		return changeId;
	}

	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}

	/**
	 * 生成还款信息记录（自动分配）
	 * @param charge
	 */
	public void generateRepaymentInfo(String charge,RepaymentPlanAdjustmentIntegration repaymentPlanAdjustmentIntegration){
		String id = "";
		for(int i=0; i < paidRepaymentPlanRedisDTOList.size(); i++){
			PaidRepaymentPlanRedisDTO paidPlan = paidRepaymentPlanRedisDTOList.get(i);
			PaidRepaymentPlanRedisDTO originPaidPlan = originPaidRepaymentPlanRedisDTOList.get(i);
			if(i == 0){
				id = createOrModifyRepaymentSingleCollectByPlan(paidPlan.getLoanInfoId(), charge, CanaDateUtils.date10());
			}
			if(MoneyArithUtil.convertStringToMoney(paidPlan.getPaidTotalAmount()) > 
				MoneyArithUtil.convertStringToMoney(originPaidPlan.getPaidTotalAmount())){
				createOrModifyRepaymentSingleDetailByPlan(paidPlan, originPaidPlan, id);
			}
		}
		repaymentPlanAdjustmentIntegration.setChangeId(id);
	}
	
	/**
	 * 创建或修改还款汇总表（自动分配）
	 * @param loanInfo
	 * @param paidAmount
	 * @param repaymentDate
	 * @return
	 */
	public String createOrModifyRepaymentSingleCollectByPlan(String loanInfo, String paidAmount, String repaymentDate){
		RepaymentSingleCollectDTO repaymentSingleCollectDTO = judePlanRepaymentSingleCollectPresent();
		
		if(null == repaymentSingleCollectDTO){
			repaymentSingleCollectDTO = new RepaymentSingleCollectDTO();
			repaymentSingleCollectDTO.setId(UUID.randomUUID().toString());
			repaymentSingleCollectDTO.setChargeType(ChargeType.REPAYMENTPLAN.name());
			repaymentSingleCollectDTO.setRepaymentType(RepaymentMethod.OFFLINE.name());
			repaymentSingleCollectDTO.setLoanInfoId(loanInfo);
		}
		repaymentSingleCollectDTO.setRepaymentDate(repaymentDate);
		repaymentSingleCollectDTO.setRepaymentTotalAmount(paidAmount);
		int index = planAndExpenseRepaymentSummaryList.indexOf(repaymentSingleCollectDTO);
		if(index == -1){
			planAndExpenseRepaymentSummaryList.add(repaymentSingleCollectDTO);
		}else{
			planAndExpenseRepaymentSummaryList.set(index, repaymentSingleCollectDTO);
		}
		
		return repaymentSingleCollectDTO.getId();
	}
	
	/**
	 * 创建或修改还款明细表（自动分配）
	 * @param paidPlan
	 * @param originPaidPlan
	 * @param collectId
	 */
	public void createOrModifyRepaymentSingleDetailByPlan(PaidRepaymentPlanRedisDTO paidPlan, PaidRepaymentPlanRedisDTO originPaidPlan, String collectId){
		
		RepaymentSingleDistributeDetailDTO detailDTO = new RepaymentSingleDistributeDetailDTO();
		detailDTO.setRelatedId(paidPlan.getId());
		if(repaymentSingleDistributeDetailList.indexOf(detailDTO) >= 0);{
			repaymentSingleDistributeDetailList.remove(detailDTO);
		}
		detailDTO.setId(UUID.randomUUID().toString());
		detailDTO.setEarlyRepaymentCharge(getIncreaseCharge(paidPlan.getPaidEarlyRepaymentCharge(), originPaidPlan.getPaidEarlyRepaymentCharge()));
		detailDTO.setPayExtensionCharge(getIncreaseCharge(paidPlan.getPaidExtensionCharge(), originPaidPlan.getPaidExtensionCharge()));
		detailDTO.setPayOtherPenalty(getIncreaseCharge(paidPlan.getPaidOverdueManagerFee(), originPaidPlan.getPaidOverdueManagerFee()));
		detailDTO.setPayOverdueServiceCharge(getIncreaseCharge(paidPlan.getPaidOverdueServiceCharge(), originPaidPlan.getPaidOverdueServiceCharge()));
		detailDTO.setPayOverdueInterest(getIncreaseCharge(paidPlan.getPaidOverdueInterest(), originPaidPlan.getPaidOverdueInterest()));
		detailDTO.setPayOverduePrincipal(getIncreaseCharge(paidPlan.getPaidOverduePrincipal(), originPaidPlan.getPaidOverduePrincipal()));
		detailDTO.setPayNormalServiceCharge(getIncreaseCharge(paidPlan.getPaidNormalServiceCharge(), originPaidPlan.getPaidNormalServiceCharge()));
		detailDTO.setPayNormalInterest(getIncreaseCharge(paidPlan.getPaidNormalInterest(), originPaidPlan.getPaidNormalInterest()));
		detailDTO.setPayNormalPrincipal(getIncreaseCharge(paidPlan.getPaidNormalPrincipal(), originPaidPlan.getPaidNormalPrincipal()));
		detailDTO.setRelatedType(ChargeType.REPAYMENTPLAN.name());
		detailDTO.setRepaymentSingleCollectId(collectId);
		repaymentSingleDistributeDetailList.add(detailDTO);
	}
	
	/**
	 * 获取增加金额
	 * @param chargeStr
	 * @param originChargeStr
	 * @return
	 */
	private String getIncreaseCharge(String chargeStr, String originChargeStr){
		long originCharge = MoneyArithUtil.convertStringToMoney(originChargeStr);
		long charge = MoneyArithUtil.convertStringToMoney(chargeStr);
		long increaseCharge = charge - originCharge;
		return MoneyArithUtil.convertMoneyToString(increaseCharge);
	}
	
	/**
	 * 金额相加
	 * @param chargeStr1
	 * @param chargeStr2
	 * @return
	 */
	private String addCharge(String chargeStr1, String chargeStr2){
		long charge1 = MoneyArithUtil.convertStringToMoney(chargeStr1);
		long charge2 = MoneyArithUtil.convertStringToMoney(chargeStr2);
		long total = charge1 + charge2;
		return MoneyArithUtil.convertMoneyToString(total);
	}
	
	/**
	 * 计算调整钱数
	 * @param increaseStr
	 * @param paidChargeStr
	 * @param originPaidChargeStr
	 * @return
	 */
	private long getAdjustCharge(String increaseStr, String paidChargeStr, String originPaidChargeStr){
		long increase = MoneyArithUtil.convertStringToMoney(increaseStr);
		long paidCharge = MoneyArithUtil.convertStringToMoney(paidChargeStr);
		long originPaidCharge = MoneyArithUtil.convertStringToMoney(originPaidChargeStr);
		return increase+originPaidCharge-paidCharge;
	}
	
	/**
	 * 判断是否已经存在计划还款汇总表
	 * @return
	 */
	public RepaymentSingleCollectDTO judePlanRepaymentSingleCollectPresent(){
		for(RepaymentSingleCollectDTO collectDTO : planAndExpenseRepaymentSummaryList){
			if(collectDTO.getChargeType().equals(ChargeType.REPAYMENTPLAN.name())){
				return collectDTO;
			}
		}
		return null;
	}
	
	/**
	 * 已还调整
	 * @param paidPlan
	 */
	public void adjustPaidRepaymentPlan(PaidRepaymentPlanRedisDTO paidPlan, long financeAmount, long paidNormalPrincipal, long paidOverduePrincipal){
		int originIndex = originPaidRepaymentPlanRedisDTOList.indexOf(paidPlan);
		PaidRepaymentPlanRedisDTO originPaidPlan = originPaidRepaymentPlanRedisDTOList.get(originIndex);
		
		// 计算已还本金是否已超过融资金额
		financeAmountValidate(originPaidPlan, financeAmount, paidNormalPrincipal, paidOverduePrincipal);
		
		int index = paidRepaymentPlanRedisDTOList.indexOf(paidPlan);
		PaidRepaymentPlanRedisDTO newPaidPlan = paidRepaymentPlanRedisDTOList.get(index);
		
		String periodStatus = newPaidPlan.getPeriodStatus();
		paidPlan.setPeriodStatus(periodStatus);
		long increaseCharge = 0;
		if(StringUtils.equals(periodStatus, PeriodStatus.OVERDUE.name())){
			increaseCharge += getAdjustCharge(paidPlan.getPaidExtensionCharge(), newPaidPlan.getPaidExtensionCharge(), originPaidPlan.getPaidExtensionCharge());
			increaseCharge += getAdjustCharge(paidPlan.getPaidOverdueManagerFee(), newPaidPlan.getPaidOverdueManagerFee(), originPaidPlan.getPaidOverdueManagerFee());
			increaseCharge += getAdjustCharge(paidPlan.getPaidOverdueServiceCharge(), newPaidPlan.getPaidOverdueServiceCharge(), originPaidPlan.getPaidOverdueServiceCharge());
			increaseCharge += getAdjustCharge(paidPlan.getPaidOverdueInterest(), newPaidPlan.getPaidOverdueInterest(), originPaidPlan.getPaidOverdueInterest());
			increaseCharge += getAdjustCharge(paidPlan.getPaidOverduePrincipal(), newPaidPlan.getPaidOverduePrincipal(), originPaidPlan.getPaidOverduePrincipal());

			newPaidPlan.setPaidExtensionCharge(addCharge(paidPlan.getPaidExtensionCharge(), originPaidPlan.getPaidExtensionCharge()));
			newPaidPlan.setPaidOverdueManagerFee(addCharge(paidPlan.getPaidOverdueManagerFee(), originPaidPlan.getPaidOverdueManagerFee()));
			newPaidPlan.setPaidOverdueServiceCharge(addCharge(paidPlan.getPaidOverdueServiceCharge(), originPaidPlan.getPaidOverdueServiceCharge()));
			newPaidPlan.setPaidOverdueInterest(addCharge(paidPlan.getPaidOverdueInterest(), originPaidPlan.getPaidOverdueInterest()));
			newPaidPlan.setPaidOverduePrincipal(addCharge(paidPlan.getPaidOverduePrincipal(), originPaidPlan.getPaidOverduePrincipal()));
		}
		
		if(StringUtils.equals(periodStatus, PeriodStatus.EXTENSION.name())){
			increaseCharge += getAdjustCharge(paidPlan.getPaidExtensionCharge(), newPaidPlan.getPaidExtensionCharge(), originPaidPlan.getPaidExtensionCharge());
			increaseCharge += getAdjustCharge(paidPlan.getPaidNormalServiceCharge(), newPaidPlan.getPaidNormalServiceCharge(), originPaidPlan.getPaidNormalServiceCharge());
			increaseCharge += getAdjustCharge(paidPlan.getPaidNormalInterest(), newPaidPlan.getPaidNormalInterest(), originPaidPlan.getPaidNormalInterest());
			increaseCharge += getAdjustCharge(paidPlan.getPaidNormalPrincipal(), newPaidPlan.getPaidNormalPrincipal(), originPaidPlan.getPaidNormalPrincipal());
			
			newPaidPlan.setPaidExtensionCharge(addCharge(paidPlan.getPaidExtensionCharge(), originPaidPlan.getPaidExtensionCharge()));
			newPaidPlan.setPaidNormalServiceCharge(addCharge(paidPlan.getPaidNormalServiceCharge(), originPaidPlan.getPaidNormalServiceCharge()));
			newPaidPlan.setPaidNormalInterest(addCharge(paidPlan.getPaidNormalInterest(), originPaidPlan.getPaidNormalInterest()));
			newPaidPlan.setPaidNormalPrincipal(addCharge(paidPlan.getPaidNormalPrincipal(), originPaidPlan.getPaidNormalPrincipal()));
		}
		
		if(StringUtils.equals(periodStatus, PeriodStatus.CURRENTIN.name()) || StringUtils.equals(periodStatus, PeriodStatus.CURRENTOUT.name())){
			increaseCharge += getAdjustCharge(paidPlan.getPaidEarlyRepaymentCharge(), newPaidPlan.getPaidEarlyRepaymentCharge(), originPaidPlan.getPaidEarlyRepaymentCharge());
			increaseCharge += getAdjustCharge(paidPlan.getPaidNormalServiceCharge(), newPaidPlan.getPaidNormalServiceCharge(), originPaidPlan.getPaidNormalServiceCharge());
			increaseCharge += getAdjustCharge(paidPlan.getPaidNormalInterest(), newPaidPlan.getPaidNormalInterest(), originPaidPlan.getPaidNormalInterest());
			increaseCharge += getAdjustCharge(paidPlan.getPaidNormalPrincipal(), newPaidPlan.getPaidNormalPrincipal(), originPaidPlan.getPaidNormalPrincipal());
			
			newPaidPlan.setPaidEarlyRepaymentCharge(addCharge(paidPlan.getPaidEarlyRepaymentCharge(), originPaidPlan.getPaidEarlyRepaymentCharge()));
			newPaidPlan.setPaidNormalServiceCharge(addCharge(paidPlan.getPaidNormalServiceCharge(), originPaidPlan.getPaidNormalServiceCharge()));
			newPaidPlan.setPaidNormalInterest(addCharge(paidPlan.getPaidNormalInterest(), originPaidPlan.getPaidNormalInterest()));
			newPaidPlan.setPaidNormalPrincipal(addCharge(paidPlan.getPaidNormalPrincipal(), originPaidPlan.getPaidNormalPrincipal()));
		}

		if(StringUtils.equals(periodStatus, PeriodStatus.FUTURE.name())){
			increaseCharge += getAdjustCharge(paidPlan.getPaidEarlyRepaymentCharge(), newPaidPlan.getPaidEarlyRepaymentCharge(), originPaidPlan.getPaidEarlyRepaymentCharge());
			increaseCharge += getAdjustCharge(paidPlan.getPaidNormalServiceCharge(), newPaidPlan.getPaidNormalServiceCharge(), originPaidPlan.getPaidNormalServiceCharge());
			increaseCharge += getAdjustCharge(paidPlan.getPaidNormalInterest(), newPaidPlan.getPaidNormalInterest(), originPaidPlan.getPaidNormalInterest());
			increaseCharge += getAdjustCharge(paidPlan.getPaidNormalPrincipal(), newPaidPlan.getPaidNormalPrincipal(), originPaidPlan.getPaidNormalPrincipal());
			
			newPaidPlan.setPaidEarlyRepaymentCharge(addCharge(paidPlan.getPaidEarlyRepaymentCharge(), originPaidPlan.getPaidEarlyRepaymentCharge()));
			newPaidPlan.setPaidNormalServiceCharge(addCharge(paidPlan.getPaidNormalServiceCharge(), originPaidPlan.getPaidNormalServiceCharge()));
			newPaidPlan.setPaidNormalInterest(addCharge(paidPlan.getPaidNormalInterest(), originPaidPlan.getPaidNormalInterest()));
			newPaidPlan.setPaidNormalPrincipal(addCharge(paidPlan.getPaidNormalPrincipal(), originPaidPlan.getPaidNormalPrincipal()));
		}
		
		RepaymentSingleCollectDTO collectDTO = judePlanRepaymentSingleCollectPresent();
		if(null != collectDTO){
			increaseCharge += MoneyArithUtil.convertStringToMoney(collectDTO.getRepaymentTotalAmount());
		}
		String collectId = createOrModifyRepaymentSingleCollectByPlan(newPaidPlan.getLoanInfoId(), MoneyArithUtil.convertMoneyToString(increaseCharge), CanaDateUtils.date10());
		
		newPaidPlan.computeTotalAmountCharge();
		paidRepaymentPlanRedisDTOList.set(index, newPaidPlan);
		createOrModifyRepaymentSingleDetailByPlan(paidPlan, collectId);
	}
	
	/**
	 * 还款明细修改或创建
	 * @param paidPlan
	 * @param collectId
	 */
	private void createOrModifyRepaymentSingleDetailByPlan(PaidRepaymentPlanRedisDTO paidPlan, String collectId){
		RepaymentSingleDistributeDetailDTO detailDTO = new RepaymentSingleDistributeDetailDTO();
		detailDTO.setRelatedId(paidPlan.getId());
	
		int index = repaymentSingleDistributeDetailList.indexOf(detailDTO);
		if(index >= 0){
			detailDTO = repaymentSingleDistributeDetailList.get(index);
		}else{
			detailDTO.setId(UUID.randomUUID().toString());
			detailDTO.setRepaymentSingleCollectId(collectId);
		}
		String periodStatus = paidPlan.getPeriodStatus();
		if(StringUtils.equals(periodStatus, PeriodStatus.OVERDUE.name())){
			detailDTO.setPayOtherPenalty(paidPlan.getPaidOverdueManagerFee());
			detailDTO.setPayOverdueServiceCharge(paidPlan.getPaidOverdueServiceCharge());
			detailDTO.setPayOverdueInterest(paidPlan.getPaidOverdueInterest());
			detailDTO.setPayOverduePrincipal(paidPlan.getPaidOverduePrincipal());
			detailDTO.setPayExtensionCharge(paidPlan.getPaidExtensionCharge());
		}
		
		if(StringUtils.equals(periodStatus, PeriodStatus.EXTENSION.name())){
			detailDTO.setPayExtensionCharge(paidPlan.getPaidExtensionCharge());
			detailDTO.setPayNormalServiceCharge(paidPlan.getPaidNormalServiceCharge());
			detailDTO.setPayNormalInterest(paidPlan.getPaidNormalInterest());
			detailDTO.setPayNormalPrincipal(paidPlan.getPaidNormalPrincipal());
		}
		
		if(StringUtils.equals(periodStatus, PeriodStatus.CURRENTIN.name()) || StringUtils.equals(periodStatus, PeriodStatus.CURRENTOUT.name())){
			detailDTO.setEarlyRepaymentCharge(paidPlan.getPaidEarlyRepaymentCharge());
			detailDTO.setPayNormalServiceCharge(paidPlan.getPaidNormalServiceCharge());
			detailDTO.setPayNormalInterest(paidPlan.getPaidNormalInterest());
			detailDTO.setPayNormalPrincipal(paidPlan.getPaidNormalPrincipal());
		}
		
		if(StringUtils.equals(periodStatus, PeriodStatus.FUTURE.name())){
			detailDTO.setEarlyRepaymentCharge(paidPlan.getPaidEarlyRepaymentCharge());
			detailDTO.setPayNormalServiceCharge(paidPlan.getPaidNormalServiceCharge());
			detailDTO.setPayNormalInterest(paidPlan.getPaidNormalInterest());
			detailDTO.setPayNormalPrincipal(paidPlan.getPaidNormalPrincipal());
		}
		
		if(index >= 0){
			repaymentSingleDistributeDetailList.set(index, detailDTO);
		}else{
			detailDTO.setRelatedType(ChargeType.REPAYMENTPLAN.name());
			repaymentSingleDistributeDetailList.add(detailDTO);
		}
	}
	
	/**
	 * 备份原始已还还款计划列表
	 * @param paidRepaymentPlanRedisDTOList
	 * @param originPaidRepaymentPlanRedisDTOList
	 */
	public void copyPaidRepaymentPlanListToOrigin(List<PaidRepaymentPlanRedisDTO> paidRepaymentPlanRedisDTOList,List<PaidRepaymentPlanRedisDTO> originPaidRepaymentPlanRedisDTOList){
		for(PaidRepaymentPlanRedisDTO paidRepaymentPlanRedisDTO:paidRepaymentPlanRedisDTOList){
			PaidRepaymentPlanRedisDTO originPaidRepaymentPlanRedisDTO = new PaidRepaymentPlanRedisDTO();
			BeanUtils.copyProperties(paidRepaymentPlanRedisDTO, originPaidRepaymentPlanRedisDTO);
			originPaidRepaymentPlanRedisDTOList.add(originPaidRepaymentPlanRedisDTO);
		}
	}
	
	/**
	 * 备份待还还款计划列表
	 * @param accountRepaymentPlanRedisDTOList
	 * @param originAccountRepaymentPlanRedisDTOList
	 */
	public void copyAccountRepaymentPlanListToOrigin(List<AccountRepaymentPlanRedisDTO> accountRepaymentPlanRedisDTOList,List<AccountRepaymentPlanRedisDTO> originAccountRepaymentPlanRedisDTOList){
		for(AccountRepaymentPlanRedisDTO accountRepaymentPlanRedisDTO:accountRepaymentPlanRedisDTOList){
			AccountRepaymentPlanRedisDTO originAccountRepaymentPlanRedisDTO = new AccountRepaymentPlanRedisDTO();
			BeanUtils.copyProperties(accountRepaymentPlanRedisDTO, originAccountRepaymentPlanRedisDTO);
			originAccountRepaymentPlanRedisDTOList.add(originAccountRepaymentPlanRedisDTO);
		}
	}
	
	/**
	 * 备份费用列表
	 * @param paidRepaymentExpenseRedisDTOList
	 * @param originPaidRepaymentExpenseRedisDTOList
	 */
	public void copyPaidRepaymentExpenseListToOrigin(List<PaidRepaymentExpenseRedisDTO> paidRepaymentExpenseRedisDTOList, List<PaidRepaymentExpenseRedisDTO> originPaidRepaymentExpenseRedisDTOList){
		for(PaidRepaymentExpenseRedisDTO paidRepaymentExpenseRedisDTO:paidRepaymentExpenseRedisDTOList){
			PaidRepaymentExpenseRedisDTO originPaidRepaymentExpenseRedisDTO = new PaidRepaymentExpenseRedisDTO();
			BeanUtils.copyProperties(paidRepaymentExpenseRedisDTO, originPaidRepaymentExpenseRedisDTO);
			originPaidRepaymentExpenseRedisDTOList.add(originPaidRepaymentExpenseRedisDTO);
		}
	}
	
	
	/**
	 * 计算当前应还总金额
	 * @param earlyPayRatio
	 * @return
	 */
	public void calculateAccountTotalMoney(BigDecimal earlyPayRatio){
		long accountTotalCharge = 0;
		long earlyPayPrincipal = 0;
		if(CollectionUtils.isNotEmpty(originAccountRepaymentPlanRedisDTOList)){
			for (int i = 0; i < originAccountRepaymentPlanRedisDTOList.size(); i++) {
				AccountRepaymentPlanRedisDTO accountPlan = originAccountRepaymentPlanRedisDTOList.get(i);
				if(SettleStatus.UNSETTLE.desc().equals(accountPlan.getSettleStatus())){
					String periodStatus = accountPlan.getPeriodStatus();
					if(StringUtils.equals(periodStatus, PeriodStatus.OVERDUE.name())){
						accountTotalCharge += MoneyArithUtil.convertStringToMoney(accountPlan.getOverdueManagerFee());
						accountTotalCharge += MoneyArithUtil.convertStringToMoney(accountPlan.getExtensionCharge());
						accountTotalCharge += MoneyArithUtil.convertStringToMoney(accountPlan.getOverdueServiceCharge());
						accountTotalCharge += MoneyArithUtil.convertStringToMoney(accountPlan.getOverdueInterest());
						accountTotalCharge += MoneyArithUtil.convertStringToMoney(accountPlan.getOverduePrincipal());
					}
					if(StringUtils.equals(periodStatus, PeriodStatus.EXTENSION.name())){
						accountTotalCharge += MoneyArithUtil.convertStringToMoney(accountPlan.getExtensionCharge());
						accountTotalCharge += MoneyArithUtil.convertStringToMoney(accountPlan.getAccountServiceCharge());
						accountTotalCharge += MoneyArithUtil.convertStringToMoney(accountPlan.getAccountInterest());
						accountTotalCharge += MoneyArithUtil.convertStringToMoney(accountPlan.getAccountPrincipal());
					}
					if(StringUtils.equals(periodStatus, PeriodStatus.CURRENTIN.name()) || StringUtils.equals(accountPlan.getPeriodStatus(), PeriodStatus.CURRENTOUT.name())){
						if(StringUtils.equals(periodStatus, PeriodStatus.CURRENTOUT.name())){
							earlyPayPrincipal += MoneyArithUtil.convertStringToMoney(accountPlan.getAccountPrincipal());
						}
						accountTotalCharge += MoneyArithUtil.convertStringToMoney(accountPlan.getAccountServiceCharge());
						accountTotalCharge += MoneyArithUtil.convertStringToMoney(accountPlan.getAccountInterest());
						accountTotalCharge += MoneyArithUtil.convertStringToMoney(accountPlan.getAccountPrincipal());
					}
					if( StringUtils.equals(periodStatus, PeriodStatus.FUTURE.name())){
						accountTotalCharge += MoneyArithUtil.convertStringToMoney(accountPlan.getAccountPrincipal());
						earlyPayPrincipal += MoneyArithUtil.convertStringToMoney(accountPlan.getAccountPrincipal());
					}
				}
			}
			
			
			accountTotalMoney = accountTotalCharge + computeEarlyRepaymentCharge(earlyPayPrincipal, earlyPayRatio);
		}
	}
	
	/**
	 * 计算提前还款手续费
	 * @param principal
	 * @param earlyRepaymentRatio
	 * @return
	 */
	private long computeEarlyRepaymentCharge(long principal, BigDecimal earlyRepaymentRatio){
		BigDecimal principalDec = new BigDecimal(principal);
		principalDec = principalDec.multiply(earlyRepaymentRatio).setScale(0, BigDecimal.ROUND_HALF_UP);
		return principalDec.longValue();
		
	}
	
	/**
	 * 归零
	 * @param principal
	 * @param earlyRepaymentRatio
	 */
	public void back2Origin(){
		List<PaidRepaymentPlanRedisDTO> paidRepaymentPlanRedisDTOList = new ArrayList<>();
		copyPaidRepaymentPlanListToOrigin(originPaidRepaymentPlanRedisDTOList, paidRepaymentPlanRedisDTOList);
		this.paidRepaymentPlanRedisDTOList = paidRepaymentPlanRedisDTOList;
		RepaymentSingleCollectDTO collectDTO = judePlanRepaymentSingleCollectPresent();
		if(null != collectDTO){
			if(CollectionUtils.isNotEmpty(repaymentSingleDistributeDetailList)){
				List<RepaymentSingleDistributeDetailDTO> detailDTOList = new ArrayList<>();
				for(RepaymentSingleDistributeDetailDTO detailDTO : repaymentSingleDistributeDetailList){
					if(!collectDTO.getId().equals(detailDTO.getRepaymentSingleCollectId())){
						detailDTOList.add(detailDTO);
					}
				}
				repaymentSingleDistributeDetailList = detailDTOList;
			}
			planAndExpenseRepaymentSummaryList.remove(collectDTO);
		}
	}
	
	/**
	 * 判断已还本金已超过融资金额
	 * @param originPaidPlan
	 * @param financeAmount
	 */
	private void financeAmountValidate(PaidRepaymentPlanRedisDTO originPaidPlan, long financeAmount,long paidNormalPrincipal, long paidOverduePrincipal){
		long totalFinanceAmount = 0l;
		totalFinanceAmount = MoneyArithUtil.convertStringToMoney(originPaidPlan.getPaidNormalPrincipal()) + MoneyArithUtil.convertStringToMoney(originPaidPlan.getPaidOverduePrincipal()) + paidNormalPrincipal +paidOverduePrincipal;
		for(PaidRepaymentPlanRedisDTO paidRepaymentPlanRedisDTO: paidRepaymentPlanRedisDTOList){
			if(!(StringUtils.equals(originPaidPlan.getLoanNo(), paidRepaymentPlanRedisDTO.getLoanNo()) && StringUtils.equals(originPaidPlan.getRepaymentPeriod(), paidRepaymentPlanRedisDTO.getRepaymentPeriod()))){
				totalFinanceAmount += MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidNormalPrincipal());
				totalFinanceAmount += MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidOverduePrincipal());
			}
		}
		if(totalFinanceAmount > financeAmount){
			throw WebException.instance("还款本金已超过融资金额，请重新调整");
		}
	}
	
	/**
	 * 线下还款修改部分
	 */
	public void judgePaidRepaymentForAll(){
		for(PaidRepaymentPlanRedisDTO paidRepaymentPlanRedisDTO:paidRepaymentPlanRedisDTOList){
			int indexOfOrigin = originPaidRepaymentPlanRedisDTOList.indexOf(paidRepaymentPlanRedisDTO);
			PaidRepaymentPlanRedisDTO paidRepaymentPlanOrigin = originPaidRepaymentPlanRedisDTOList.get(indexOfOrigin);
			if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidNormalPrincipal()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidNormalPrincipal()).longValue()){
				paidRepaymentPlanRedisDTO.setPaidNormalPrincipalNew(paidRepaymentPlanOrigin.getAccountPrincipal());
			}else{
				paidRepaymentPlanRedisDTO.setPaidNormalPrincipalNew(null);
			}
			if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidNormalInterest()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidNormalInterest()).longValue()){
				paidRepaymentPlanRedisDTO.setPaidNormalInterestNew(paidRepaymentPlanOrigin.getPaidNormalInterest());
			}else{
				paidRepaymentPlanRedisDTO.setPaidNormalInterestNew(null);
			}
			if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidNormalServiceCharge()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidNormalServiceCharge()).longValue()){
				paidRepaymentPlanRedisDTO.setPaidNormalServiceChargeNew(paidRepaymentPlanOrigin.getPaidNormalServiceCharge());
			}else{
				paidRepaymentPlanRedisDTO.setPaidNormalServiceChargeNew(null);
			}
			if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidOverduePrincipal()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidOverduePrincipal()).longValue()){
				paidRepaymentPlanRedisDTO.setPaidOverduePrincipalNew(paidRepaymentPlanOrigin.getPaidOverduePrincipal());
			}else{
				paidRepaymentPlanRedisDTO.setPaidOverduePrincipalNew(null);
			}
			if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidOverdueInterest()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidOverdueInterest()).longValue()){
				paidRepaymentPlanRedisDTO.setPaidOverdueInterestNew(paidRepaymentPlanOrigin.getPaidOverdueInterest());
			}else{
				paidRepaymentPlanRedisDTO.setPaidOverdueInterestNew(null);
			}
			if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidOverdueServiceCharge()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidOverdueServiceCharge()).longValue()){
				paidRepaymentPlanRedisDTO.setPaidOverdueServiceChargeNew(paidRepaymentPlanOrigin.getPaidOverdueServiceCharge());
			}else{
				paidRepaymentPlanRedisDTO.setPaidOverdueServiceChargeNew(null);
			}
			if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidExtensionCharge()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidExtensionCharge()).longValue()){
				paidRepaymentPlanRedisDTO.setPaidExtensionChargeNew(paidRepaymentPlanOrigin.getPaidExtensionCharge());
			}else{
				paidRepaymentPlanRedisDTO.setPaidExtensionChargeNew(null);
			}
			if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidOverdueManagerFee()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidOverdueManagerFee()).longValue()){
				paidRepaymentPlanRedisDTO.setPaidOverdueManagerFeeNew(paidRepaymentPlanOrigin.getPaidOverdueManagerFee());
			}else{
				paidRepaymentPlanRedisDTO.setPaidOverdueManagerFeeNew(null);
			}
			if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidEarlyRepaymentCharge()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidEarlyRepaymentCharge()).longValue()){
				paidRepaymentPlanRedisDTO.setPaidEarlyRepaymentChargeNew(paidRepaymentPlanOrigin.getPaidEarlyRepaymentCharge());
			}else{
				paidRepaymentPlanRedisDTO.setPaidEarlyRepaymentChargeNew(null);
			}
			if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidTotalAmount()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidTotalAmount()).longValue()){
				paidRepaymentPlanRedisDTO.setPaidTotalAmountNew(paidRepaymentPlanOrigin.getPaidTotalAmount());
			}else{
				paidRepaymentPlanRedisDTO.setPaidTotalAmountNew(null);
			}
		}
	}
	
	/**
	 * 判断是否调整还款计划
	 * @param paidRepaymentPlanRedisDTO
	 */
	public void judgePaidRepaymentPlanAdjustment(PaidRepaymentPlanRedisDTO paidRepaymentPlanRedisDTO){
		int indexOfCurrent = paidRepaymentPlanRedisDTOList.indexOf(paidRepaymentPlanRedisDTO);
		int indexOfOrigin = originPaidRepaymentPlanRedisDTOList.indexOf(paidRepaymentPlanRedisDTO);
		PaidRepaymentPlanRedisDTO paidRepaymentPlanAdjustment = paidRepaymentPlanRedisDTOList.get(indexOfCurrent);
		PaidRepaymentPlanRedisDTO paidRepaymentPlanOrigin = originPaidRepaymentPlanRedisDTOList.get(indexOfOrigin);
		if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanAdjustment.getPaidNormalPrincipal()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidNormalPrincipal()).longValue()){
			paidRepaymentPlanAdjustment.setPaidNormalPrincipalNew(paidRepaymentPlanOrigin.getAccountPrincipal());
		}else{
			paidRepaymentPlanAdjustment.setPaidNormalPrincipalNew(null);
		}
		if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanAdjustment.getPaidNormalInterest()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidNormalInterest()).longValue()){
			paidRepaymentPlanAdjustment.setPaidNormalInterestNew(paidRepaymentPlanOrigin.getPaidNormalInterest());
		}else{
			paidRepaymentPlanAdjustment.setPaidNormalInterestNew(null);
		}
		if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanAdjustment.getPaidNormalServiceCharge()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidNormalServiceCharge()).longValue()){
			paidRepaymentPlanAdjustment.setPaidNormalServiceChargeNew(paidRepaymentPlanOrigin.getPaidNormalServiceCharge());
		}else{
			paidRepaymentPlanAdjustment.setPaidNormalServiceChargeNew(null);
		}
		if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanAdjustment.getPaidOverduePrincipal()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidOverduePrincipal()).longValue()){
			paidRepaymentPlanAdjustment.setPaidOverduePrincipalNew(paidRepaymentPlanOrigin.getPaidOverduePrincipal());
		}else{
			paidRepaymentPlanAdjustment.setPaidOverduePrincipalNew(null);
		}
		if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanAdjustment.getPaidOverdueInterest()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidOverdueInterest()).longValue()){
			paidRepaymentPlanAdjustment.setPaidOverdueInterestNew(paidRepaymentPlanOrigin.getPaidOverdueInterest());
		}else{
			paidRepaymentPlanAdjustment.setPaidOverdueInterestNew(null);
		}
		if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanAdjustment.getPaidOverdueServiceCharge()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidOverdueServiceCharge()).longValue()){
			paidRepaymentPlanAdjustment.setPaidOverdueServiceChargeNew(paidRepaymentPlanOrigin.getPaidOverdueServiceCharge());
		}else{
			paidRepaymentPlanAdjustment.setPaidOverdueServiceChargeNew(null);
		}
		if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanAdjustment.getPaidExtensionCharge()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidExtensionCharge()).longValue()){
			paidRepaymentPlanAdjustment.setPaidExtensionChargeNew(paidRepaymentPlanOrigin.getPaidExtensionCharge());
		}else{
			paidRepaymentPlanAdjustment.setPaidExtensionChargeNew(null);
		}
		if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanAdjustment.getPaidOverdueManagerFee()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidOverdueManagerFee()).longValue()){
			paidRepaymentPlanAdjustment.setPaidOverdueManagerFeeNew(paidRepaymentPlanOrigin.getPaidOverdueManagerFee());
		}else{
			paidRepaymentPlanAdjustment.setPaidOverdueManagerFeeNew(null);
		}
		if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanAdjustment.getPaidEarlyRepaymentCharge()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidEarlyRepaymentCharge()).longValue()){
			paidRepaymentPlanAdjustment.setPaidEarlyRepaymentChargeNew(paidRepaymentPlanOrigin.getPaidEarlyRepaymentCharge());
		}else{
			paidRepaymentPlanAdjustment.setPaidEarlyRepaymentChargeNew(null);
		}
		if(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanAdjustment.getPaidTotalAmount()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentPlanOrigin.getPaidTotalAmount()).longValue()){
			paidRepaymentPlanAdjustment.setPaidTotalAmountNew(paidRepaymentPlanOrigin.getPaidTotalAmount());
		}else{
			paidRepaymentPlanAdjustment.setPaidTotalAmountNew(null);
		}
	}
	
	/**
	 * 判断是否修改新还款计划
	 * @param repaymentPlanId
	 */
	public void judgeAccountRepaymentPlanEdit(AccountRepaymentPlanRedisDTO accountRepaymentPlanRedisDTO){
		int indexOfCurrent = accountRepaymentPlanRedisDTOList.indexOf(accountRepaymentPlanRedisDTO);
		int indexOfOrigin = originAccountRepaymentPlanRedisDTOList.indexOf(accountRepaymentPlanRedisDTO);
		AccountRepaymentPlanRedisDTO accountRepaymentPlanRedisDTOEdit = accountRepaymentPlanRedisDTOList.get(indexOfCurrent);
		AccountRepaymentPlanRedisDTO accountRepaymentPlanRedisDTOOrigin = originAccountRepaymentPlanRedisDTOList.get(indexOfOrigin);
		if(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOEdit.getAccountPrincipal()).longValue() != MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOOrigin.getAccountPrincipal()).longValue()){
			accountRepaymentPlanRedisDTOEdit.setAccountPrincipalNew(accountRepaymentPlanRedisDTOOrigin.getAccountPrincipal());
		}else{
			accountRepaymentPlanRedisDTOEdit.setAccountPrincipalNew(null);
		}
		if(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOEdit.getAccountInterest()).longValue() != MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOOrigin.getAccountInterest()).longValue()){
			accountRepaymentPlanRedisDTOEdit.setAccountInterestNew(accountRepaymentPlanRedisDTOOrigin.getAccountInterest());
		}else{
			accountRepaymentPlanRedisDTOEdit.setAccountInterestNew(null);
		}
		if(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOEdit.getAccountServiceCharge()).longValue() != MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOOrigin.getAccountServiceCharge()).longValue()){
			accountRepaymentPlanRedisDTOEdit.setAccountServiceChargeNew(accountRepaymentPlanRedisDTOOrigin.getAccountServiceCharge());
		}else{
			accountRepaymentPlanRedisDTOEdit.setAccountServiceChargeNew(null);
		}
		if(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOEdit.getOverduePrincipal()).longValue() != MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOOrigin.getOverduePrincipal()).longValue()){
			accountRepaymentPlanRedisDTOEdit.setOverduePrincipalNew(accountRepaymentPlanRedisDTOOrigin.getOverduePrincipal());
		}else{
			accountRepaymentPlanRedisDTOEdit.setOverduePrincipalNew(null);
		}
		if(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOEdit.getOverdueInterest()).longValue() != MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOOrigin.getOverdueInterest()).longValue()){
			accountRepaymentPlanRedisDTOEdit.setOverdueInterestNew(accountRepaymentPlanRedisDTOOrigin.getOverdueInterest());
		}else{
			accountRepaymentPlanRedisDTOEdit.setOverdueInterestNew(null);
		}
		if(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOEdit.getOverdueServiceCharge()).longValue() != MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOOrigin.getOverdueServiceCharge()).longValue()){
			accountRepaymentPlanRedisDTOEdit.setOverdueServiceChargeNew(accountRepaymentPlanRedisDTOOrigin.getOverdueServiceCharge());
		}else{
			accountRepaymentPlanRedisDTOEdit.setOverdueServiceChargeNew(null);
		}
		if(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOEdit.getExtensionCharge()).longValue() != MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOOrigin.getExtensionCharge()).longValue()){
			accountRepaymentPlanRedisDTOEdit.setExtensionChargeNew(accountRepaymentPlanRedisDTOOrigin.getExtensionCharge());
		}else{
			accountRepaymentPlanRedisDTOEdit.setExtensionChargeNew(null);
		}
		if(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOEdit.getOverdueManagerFee()).longValue() != MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOOrigin.getOverdueManagerFee()).longValue()){
			accountRepaymentPlanRedisDTOEdit.setOverdueManagerFeeNew(accountRepaymentPlanRedisDTOOrigin.getOverdueManagerFee());
		}else{
			accountRepaymentPlanRedisDTOEdit.setOverdueManagerFeeNew(null);
		}
		if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTOEdit.getPaidEarlyRepaymentCharge())){
			if(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOEdit.getPaidEarlyRepaymentCharge()).longValue() != MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOOrigin.getPaidEarlyRepaymentCharge()).longValue()){
				accountRepaymentPlanRedisDTOEdit.setPaidEarlyRepaymentChargeNew(accountRepaymentPlanRedisDTOOrigin.getPaidEarlyRepaymentCharge());
			}else{
				accountRepaymentPlanRedisDTOEdit.setPaidEarlyRepaymentChargeNew(null);
			}
		}
		if(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOEdit.getAccountTotalAmount()).longValue() != MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTOOrigin.getAccountTotalAmount()).longValue()){
			accountRepaymentPlanRedisDTOEdit.setAccountTotalAmountNew(accountRepaymentPlanRedisDTOOrigin.getAccountTotalAmount());
		}else{
			accountRepaymentPlanRedisDTOEdit.setAccountTotalAmountNew(null);
		}
	}
	
	/**
	 * 判断是否修改新还款费用
	 * @param paidRepaymentExpenseRedisDTO
	 */
	public void judgeRepaymentExpenseEdit(PaidRepaymentExpenseRedisDTO paidRepaymentExpenseRedisDTO){
		int indexOfCurrent = paidRepaymentExpenseRedisDTOList.indexOf(paidRepaymentExpenseRedisDTO);
		int indexOfOrigin = originPaidRepaymentExpenseRedisDTOList.indexOf(paidRepaymentExpenseRedisDTO);
		PaidRepaymentExpenseRedisDTO paidRepaymentExpenseRedisDTOEdit = paidRepaymentExpenseRedisDTOList.get(indexOfCurrent);
		PaidRepaymentExpenseRedisDTO paidRepaymentExpenseRedisDTOOrigin = originPaidRepaymentExpenseRedisDTOList.get(indexOfOrigin);
		if(MoneyArithUtil.convertStringToMoney(paidRepaymentExpenseRedisDTOEdit.getPaidAmount()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentExpenseRedisDTOOrigin.getPaidAmount()).longValue()){
			paidRepaymentExpenseRedisDTOEdit.setPaidAmountNew(paidRepaymentExpenseRedisDTOOrigin.getPaidAmount());
		}else{
			paidRepaymentExpenseRedisDTOEdit.setPaidAmountNew(null);
		}
		if(MoneyArithUtil.convertStringToMoney(paidRepaymentExpenseRedisDTOEdit.getRepaymentAmount()).longValue() != MoneyArithUtil.convertStringToMoney(paidRepaymentExpenseRedisDTOOrigin.getRepaymentAmount()).longValue()){
			paidRepaymentExpenseRedisDTOEdit.setRepaymentAmountNew(paidRepaymentExpenseRedisDTOOrigin.getRepaymentAmount());
		}else{
			paidRepaymentExpenseRedisDTOEdit.setRepaymentAmountNew(null);
		}
	}
}
