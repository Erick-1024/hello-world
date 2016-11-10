package com.cana.repayment.service.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.dao.po.RepaymentSingleCollect;
import com.cana.repayment.dao.po.RepaymentSingleDistributeDetail;
import com.cana.vbam.common.repayment.dto.AccountRepaymentPlanRedisDTO;
import com.cana.vbam.common.repayment.dto.PaidRepaymentExpenseRedisDTO;
import com.cana.vbam.common.repayment.dto.PaidRepaymentPlanRedisDTO;
import com.cana.vbam.common.repayment.dto.RepaymentSingleCollectDTO;
import com.cana.vbam.common.repayment.dto.RepaymentSingleDistributeDetailDTO;
import com.cana.vbam.common.repayment.enums.ChargeMethod;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.travelzen.framework.core.util.MoneyUtil;

public class OfflineRepaymentData {
	// 转换后还款计划
	private List<RepaymentPlan> repaymentPlanList;
	// 转换后还款费用
	private List<RepaymentExpense> repaymentExpenseList;
	// 转换后还款汇总数据
	private List<RepaymentSingleCollect> repaymentSingleCollectList;
	// 转换后还款明细数据
	private List<RepaymentSingleDistributeDetail> repaymentSingleDistributeDetailList;
	
	// 转换前还款计划(已还)
	private List<PaidRepaymentPlanRedisDTO> paidRepaymentPlanRedisDTOList;
	// 转换前还款计划(应还)
	private List<AccountRepaymentPlanRedisDTO> accountRepaymentPlanRedisDTOList;
	// 转换前还款费用
	private List<PaidRepaymentExpenseRedisDTO> paidRepaymentExpenseRedisDTOList;
	// 转换前还款汇总数据
	private List<RepaymentSingleCollectDTO> repaymentSingleCollectDTOList;
	// 转换前还款明细数据
	private List<RepaymentSingleDistributeDetailDTO> repaymentSingleDistributeDetailDTOList;
	
	// 还款计划&费用旧版本号
	private String loanInfoOldVersion;
	// 还款计划&费用新版本号
	private String loanInfoVersion;
	// 放款信息编号
	private String loanInfoId;
	// 变更Id
	private String changeId;
	// 变更类型
	private String changeType;
	
	public List<RepaymentPlan> getRepaymentPlanList() {
		return repaymentPlanList;
	}

	public void setRepaymentPlanList(List<RepaymentPlan> repaymentPlanList) {
		this.repaymentPlanList = repaymentPlanList;
	}

	public List<RepaymentExpense> getRepaymentExpenseList() {
		return repaymentExpenseList;
	}

	public void setRepaymentExpenseList(List<RepaymentExpense> repaymentExpenseList) {
		this.repaymentExpenseList = repaymentExpenseList;
	}

	public List<RepaymentSingleCollect> getRepaymentSingleCollectList() {
		return repaymentSingleCollectList;
	}

	public void setRepaymentSingleCollectList(
			List<RepaymentSingleCollect> repaymentSingleCollectList) {
		this.repaymentSingleCollectList = repaymentSingleCollectList;
	}

	public List<RepaymentSingleDistributeDetail> getRepaymentSingleDistributeDetailList() {
		return repaymentSingleDistributeDetailList;
	}

	public void setRepaymentSingleDistributeDetailList(
			List<RepaymentSingleDistributeDetail> repaymentSingleDistributeDetailList) {
		this.repaymentSingleDistributeDetailList = repaymentSingleDistributeDetailList;
	}

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

	public List<RepaymentSingleCollectDTO> getRepaymentSingleCollectDTOList() {
		return repaymentSingleCollectDTOList;
	}

	public void setRepaymentSingleCollectDTOList(
			List<RepaymentSingleCollectDTO> repaymentSingleCollectDTOList) {
		this.repaymentSingleCollectDTOList = repaymentSingleCollectDTOList;
	}

	public List<RepaymentSingleDistributeDetailDTO> getRepaymentSingleDistributeDetailDTOList() {
		return repaymentSingleDistributeDetailDTOList;
	}

	public void setRepaymentSingleDistributeDetailDTOList(
			List<RepaymentSingleDistributeDetailDTO> repaymentSingleDistributeDetailDTOList) {
		this.repaymentSingleDistributeDetailDTOList = repaymentSingleDistributeDetailDTOList;
	}

	public String getLoanInfoOldVersion() {
		return loanInfoOldVersion;
	}

	public void setLoanInfoOldVersion(String loanInfoOldVersion) {
		this.loanInfoOldVersion = loanInfoOldVersion;
	}

	public String getLoanInfoVersion() {
		return loanInfoVersion;
	}

	public void setLoanInfoVersion(String loanInfoVersion) {
		this.loanInfoVersion = loanInfoVersion;
	}

	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}
	
	public String getChangeId() {
		return changeId;
	}

	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	// 还款计划转换
	public void repaymentPlanConvert(){
		repaymentPlanList = new ArrayList<RepaymentPlan>();
		// 已还计划转换
		for(PaidRepaymentPlanRedisDTO paidRepaymentPlanRedisDTO:paidRepaymentPlanRedisDTOList){
			RepaymentPlan repaymentPlan = new RepaymentPlan();
			BeanUtils.copyProperties(paidRepaymentPlanRedisDTO, repaymentPlan);
			repaymentPlan.setRepaymentPeriod(Integer.parseInt(paidRepaymentPlanRedisDTO.getRepaymentPeriod()));
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidNormalPrincipalNew())){
				repaymentPlan.setPaidNormalPrincipal(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidNormalPrincipal()));
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidNormalInterestNew())){
				repaymentPlan.setPaidNormalInterest(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidNormalInterest()));
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidNormalServiceChargeNew())){
				repaymentPlan.setPaidNormalServiceCharge(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidNormalServiceCharge()));
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidOverduePrincipalNew())){
				repaymentPlan.setPaidOverduePrincipal(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidOverduePrincipal()));
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidOverdueInterestNew())){
				repaymentPlan.setPaidOverdueInterest(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidOverdueInterest()));
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidOverdueServiceChargeNew())){
				repaymentPlan.setPaidOverdueServiceCharge(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidOverdueServiceCharge()));
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidExtensionChargeNew())){
				repaymentPlan.setPaidExtensionCharge(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidExtensionCharge()));
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidOverdueManagerFeeNew())){
				// 此处存储的为罚息发生改变的差值
				repaymentPlan.setPaidOtherPenalty(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidOverdueManagerFee()) - MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidOverdueManagerFeeNew()));
			}
			if(StringUtils.isNotBlank(paidRepaymentPlanRedisDTO.getPaidEarlyRepaymentChargeNew())){
				repaymentPlan.setPaidEarlyRepaymentCharge(MoneyArithUtil.convertStringToMoney(paidRepaymentPlanRedisDTO.getPaidEarlyRepaymentCharge()));
			}
			repaymentPlanList.add(repaymentPlan);
		}
		// 应还计划转换
		for(AccountRepaymentPlanRedisDTO accountRepaymentPlanRedisDTO:accountRepaymentPlanRedisDTOList){
			RepaymentPlan repaymentPlan = new RepaymentPlan();
			repaymentPlan.setId(accountRepaymentPlanRedisDTO.getId());
			for(RepaymentPlan repaymentPlanTemp:repaymentPlanList){
				if(StringUtils.equals(repaymentPlanTemp.getId(), accountRepaymentPlanRedisDTO.getId())){
					repaymentPlan = repaymentPlanTemp;
				}
			}
			BeanUtils.copyProperties(accountRepaymentPlanRedisDTO, repaymentPlan);
			repaymentPlan.setFinanceBalance(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getFinanceBalance()));
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getAccountPrincipalNew())){
				repaymentPlan.setAccountPrincipal(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getAccountPrincipal()));
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getAccountInterestNew())){
				repaymentPlan.setAccountInterest(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getAccountInterest()));
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getAccountServiceChargeNew())){
				repaymentPlan.setAccountServiceCharge(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getAccountServiceCharge()));
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getOverduePrincipalNew())){
				repaymentPlan.setOverduePrincipal(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getOverduePrincipal()));
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getOverdueInterestNew())){
				repaymentPlan.setOverdueInterest(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getOverdueInterest()));
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getOverdueServiceChargeNew())){
				repaymentPlan.setOverdueServiceCharge(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getOverdueServiceCharge()));
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getExtensionChargeNew())){
				repaymentPlan.setAccountExtensionCharge(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getExtensionCharge()));
			}
			if(StringUtils.isNotBlank(accountRepaymentPlanRedisDTO.getOverdueManagerFeeNew())){
				// 此处存储的为罚息发生改变的差值
				repaymentPlan.setOtherPenalty(MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getOverdueManagerFeeNew()) - 
						MoneyArithUtil.convertStringToMoney(accountRepaymentPlanRedisDTO.getOverdueManagerFee()));
				repaymentPlan.setOverduePrincipalPenalty(0l);
				repaymentPlan.setOverdueInterestPenalty(0l);
				repaymentPlan.setOverdueServiceChargePenalty(0l);
			}
			repaymentPlan.setSettleStatus(SettleStatus.getValue(accountRepaymentPlanRedisDTO.getSettleStatus()).name());
			repaymentPlan.setCreateTime(new Date());
			//repaymentPlanList.add(repaymentPlan);
		}
	}
	
	// 还款费用转换
	public void repaymentExpenseConvert(){
		repaymentExpenseList = new ArrayList<RepaymentExpense>();
		for(PaidRepaymentExpenseRedisDTO paidRepaymentExpenseRedisDTO:paidRepaymentExpenseRedisDTOList){
			RepaymentExpense repaymentExpense = new RepaymentExpense();
			BeanUtils.copyProperties(paidRepaymentExpenseRedisDTO, repaymentExpense);
			
			if(StringUtils.isNotBlank(paidRepaymentExpenseRedisDTO.getPaidAmountNew())){
				repaymentExpense.setPaidAmount(MoneyArithUtil.convertStringToMoney(paidRepaymentExpenseRedisDTO.getPaidAmount()));
			}
//			repaymentExpense.setChargeStandard(MoneyUtil.convertStringToMoney(paidRepaymentExpenseRedisDTO.getChargeStandard()));
			if(StringUtils.isNotBlank(paidRepaymentExpenseRedisDTO.getChargeStandard())){
				repaymentExpense.setChargeStandard(MoneyArithUtil.convertStringToMoney(MoneyUtil.parseMoney(paidRepaymentExpenseRedisDTO.getChargeStandard())));
			}
//			if(StringUtils.isNotBlank(paidRepaymentExpenseRedisDTO.getChargeStandard())){
//				if(null == ChargeStandard.getValue(paidRepaymentExpenseRedisDTO.getChargeStandard()))
//				else if(ChargeStandard.FINANCEAMOUNT == ChargeStandard.getValue(paidRepaymentExpenseRedisDTO.getChargeStandard())){
//					RepaymentLoanInfo loanInfo = loanInfoMapper.selectByPrimaryKey(paidRepaymentExpenseRedisDTO.getLoanInfoId());
//					repaymentExpense.setChargeStandard(loanInfo.getFinanceAmount());
//				}
//				else {
//					RepaymentLoanInfo loanInfo = loanInfoMapper.selectByPrimaryKey(paidRepaymentExpenseRedisDTO.getLoanInfoId());
//					repaymentExpense.setChargeStandard(loanInfo.getFinanceBalance());
//				}
//			}
			if(StringUtils.isNotBlank(paidRepaymentExpenseRedisDTO.getRepaymentAmountNew())){
				repaymentExpense.setRepaymentAmount(MoneyArithUtil.convertStringToMoney(paidRepaymentExpenseRedisDTO.getRepaymentAmount()));
			}
			repaymentExpense.setSettleStatus(SettleStatus.getValue(paidRepaymentExpenseRedisDTO.getSettleStatus()).name());
			if(StringUtils.isNotBlank(paidRepaymentExpenseRedisDTO.getChargeMethod()))
				repaymentExpense.setChargeMethod(ChargeMethod.getValue(paidRepaymentExpenseRedisDTO.getChargeMethod()).name());
			if(StringUtils.isNotBlank(paidRepaymentExpenseRedisDTO.getChargeRatio()))
				repaymentExpense.setChargeRatio(MoneyArithUtil.convertStringToInterestRate(paidRepaymentExpenseRedisDTO.getChargeRatio()));
			repaymentExpense.setCreateTime(new Date());
			if(StringUtils.isNotBlank(paidRepaymentExpenseRedisDTO.getChargeAmount()))
				repaymentExpense.setChargeAmount(MoneyArithUtil.convertStringToMoney(paidRepaymentExpenseRedisDTO.getChargeAmount()));
			repaymentExpenseList.add(repaymentExpense);
		}
	}
	
	// 汇总数据转换
	public void repaymentSingleCollectConvert(){
		repaymentSingleCollectList = new ArrayList<RepaymentSingleCollect>();
		for(RepaymentSingleCollectDTO repaymentSingleCollectDTO:repaymentSingleCollectDTOList){
			RepaymentSingleCollect repaymentSingleCollect = new RepaymentSingleCollect();
			BeanUtils.copyProperties(repaymentSingleCollectDTO, repaymentSingleCollect);
			repaymentSingleCollect.setRepaymentTotalAmount(MoneyArithUtil.convertStringToMoney(repaymentSingleCollectDTO.getRepaymentTotalAmount()));
			repaymentSingleCollect.setCreateTime(new Date());
			repaymentSingleCollectList.add(repaymentSingleCollect);
		}
	}
	
	//明细数据转换
	public void repaymentSingleDistributeDetailConvert(){
		repaymentSingleDistributeDetailList = new ArrayList<RepaymentSingleDistributeDetail>();
		for(RepaymentSingleDistributeDetailDTO repaymentSingleDistributeDetailDTO:repaymentSingleDistributeDetailDTOList){
			RepaymentSingleDistributeDetail repaymentSingleDistributeDetail = new RepaymentSingleDistributeDetail();
			BeanUtils.copyProperties(repaymentSingleDistributeDetailDTO, repaymentSingleDistributeDetail);
			repaymentSingleDistributeDetail.setPayNormalPrincipal(MoneyArithUtil.convertStringToMoney(repaymentSingleDistributeDetailDTO.getPayNormalPrincipal()));
			repaymentSingleDistributeDetail.setPayNormalInterest(MoneyArithUtil.convertStringToMoney(repaymentSingleDistributeDetailDTO.getPayNormalInterest()));
			repaymentSingleDistributeDetail.setPayNormalServiceCharge(MoneyArithUtil.convertStringToMoney(repaymentSingleDistributeDetailDTO.getPayNormalServiceCharge()));
			repaymentSingleDistributeDetail.setEarlyRepaymentCharge(MoneyArithUtil.convertStringToMoney(repaymentSingleDistributeDetailDTO.getEarlyRepaymentCharge()));
			if(StringUtils.isNotBlank(repaymentSingleDistributeDetailDTO.getEarlyRepaymentChargeRatio())){
				repaymentSingleDistributeDetail.setEarlyRepaymentChargeRatio(MoneyArithUtil.convertStringToInterestRate(repaymentSingleDistributeDetailDTO.getEarlyRepaymentChargeRatio()));
			}
			repaymentSingleDistributeDetail.setPayExtensionCharge(MoneyArithUtil.convertStringToMoney(repaymentSingleDistributeDetailDTO.getPayExtensionCharge()));
			repaymentSingleDistributeDetail.setPayOverduePrincipal(MoneyArithUtil.convertStringToMoney(repaymentSingleDistributeDetailDTO.getPayOverduePrincipal()));
			repaymentSingleDistributeDetail.setPayOverdueInterest(MoneyArithUtil.convertStringToMoney(repaymentSingleDistributeDetailDTO.getPayOverdueInterest()));
			repaymentSingleDistributeDetail.setPayOverdueServiceCharge(MoneyArithUtil.convertStringToMoney(repaymentSingleDistributeDetailDTO.getPayOverdueServiceCharge()));
			repaymentSingleDistributeDetail.setPayOverduePrincipalPenalty(MoneyArithUtil.convertStringToMoney(repaymentSingleDistributeDetailDTO.getPayOverduePrincipalPenalty()));
			repaymentSingleDistributeDetail.setPayOverdueInterestPenalty(MoneyArithUtil.convertStringToMoney(repaymentSingleDistributeDetailDTO.getPayOverdueInterestPenalty()));
			repaymentSingleDistributeDetail.setPayOverdueServiceChargePenalty(MoneyArithUtil.convertStringToMoney(repaymentSingleDistributeDetailDTO.getPayOverdueServiceChargePenalty()));
			repaymentSingleDistributeDetail.setPayOtherPenalty(MoneyArithUtil.convertStringToMoney(repaymentSingleDistributeDetailDTO.getPayOtherPenalty()));
			repaymentSingleDistributeDetail.setPayExpense(MoneyArithUtil.convertStringToMoney(repaymentSingleDistributeDetailDTO.getPayNormalExpense()));
			repaymentSingleDistributeDetail.setCreateTime(new Date());
			repaymentSingleDistributeDetailList.add(repaymentSingleDistributeDetail);
		}
	}
}
