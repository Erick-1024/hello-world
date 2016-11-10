package com.cana.asset.service.convertors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.cana.asset.dao.mapper.gen.AssetInvoiceInfoMapper;
import com.cana.asset.dao.mapper.gen.ExpenseMapper;
import com.cana.asset.dao.mapper.gen.LoanPaidMapper;
import com.cana.asset.dao.mapper.gen.LoanPlanMapper;
import com.cana.asset.dao.mapper.gen.UnderlyingAssetMapper;
import com.cana.asset.dao.po.AssetInvoiceInfoExample;
import com.cana.asset.dao.po.ExpenseExample;
import com.cana.asset.dao.po.LoanInfo;
import com.cana.asset.dao.po.LoanPaid;
import com.cana.asset.dao.po.LoanPaidExample;
import com.cana.asset.dao.po.LoanPlan;
import com.cana.asset.dao.po.LoanPlanExample;
import com.cana.asset.dao.po.UnderlyingAsset;
import com.cana.asset.dao.po.UnderlyingAssetExample;
import com.cana.asset.service.transaction.util.AssetLoanInfoUtil;
import com.cana.vbam.common.asset.enums.BusinessProduct;
import com.cana.vbam.common.asset.enums.ExpenseType;
import com.cana.vbam.common.asset.loan.dto.AssetLoanDTO;
import com.cana.vbam.common.asset.loan.dto.LoanPlanDTO;
import com.cana.vbam.common.repayment.enums.Currency;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;

@Component
public class LoanInfoConvertor {

	@Resource
	private LoanPlanMapper loanPlanMapper;
	
	@Resource
	private LoanPaidMapper loanPaidMapper;
	
	@Resource
	private ExpenseMapper expenseMapper;
	
	@Resource
	private AssetInvoiceInfoMapper assetInvoiceInfoMapper;

	@Resource
	private UnderlyingAssetMapper underlyingAssetMapper;
	
	public AssetLoanDTO convertLoanInfo2AssetLoanDTO(LoanInfo loanInfo, boolean relevant) {
		AssetLoanDTO assetLoanDTO = new AssetLoanDTO();
		BeanUtils.copyProperties(loanInfo, assetLoanDTO);
		assetLoanDTO.setBusinessProduct(BusinessProduct.valueOf(loanInfo.getBusinessProduct()));
		assetLoanDTO.setCurrency(Currency.valueOf(loanInfo.getCurrency()));
		assetLoanDTO.setRepaymentMethod(RepaymentType.valueOf(loanInfo.getRepaymentMethod()));
		assetLoanDTO.setLoanPeriodUnit(DateUnit.valueOf(loanInfo.getLoanPeriodUnit()));
		assetLoanDTO.setInterestRateUnit(InterestRateUnit.valueOf(loanInfo.getInterestRateUnit()));
		assetLoanDTO.setSettleStatus(SettleStatus.valueOf(loanInfo.getSettleStatus()));
		if(relevant) {
			AssetInvoiceInfoExample assetInvoiceInfoExample = new AssetInvoiceInfoExample();
			assetInvoiceInfoExample.createCriteria().andLoanInfoIdEqualTo(loanInfo.getId());
			assetLoanDTO.setInvoiceInfoList(InvoiceConvertor.convertorAssetInvInfoToInvInfoDTO(assetInvoiceInfoMapper.selectByExample(assetInvoiceInfoExample), loanInfo.getBusinessContractNo()));
			ExpenseExample expenseExample = new ExpenseExample();
			expenseExample.createCriteria().andReftypeEqualTo(ExpenseType.LOAN.name()).andRefidEqualTo(loanInfo.getId());
			expenseExample.setOrderByClause("sequence");
			assetLoanDTO.setExpenseList(InvoiceConvertor.convertExpense2ExpenseDTO(expenseMapper.selectByExample(expenseExample)));
			LoanPlanExample loanPlanExample = new LoanPlanExample();
			loanPlanExample.createCriteria().andLoanInfoIdEqualTo(loanInfo.getId());
			loanPlanExample.setOrderByClause("repayment_period");
			assetLoanDTO.setLoanPlanList(convertLoanPlan2LoanPlanDTO(loanPlanMapper.selectByExample(loanPlanExample)));
		}
		return assetLoanDTO;
	}
	
	public List<AssetLoanDTO> convertLoanInfo2AssetLoanDTO(List<LoanInfo> loanInfos, boolean relevant) {
		List<AssetLoanDTO> returnValue = new ArrayList<>();
		if (CollectionUtils.isEmpty(loanInfos))
			return returnValue;

		List<String> loanInfoIds = Lists.newArrayList();
		for (LoanInfo loanInfo : loanInfos) {
			loanInfoIds.add(loanInfo.getId());
			returnValue.add(convertLoanInfo2AssetLoanDTO(loanInfo, relevant));
		}

		Set<String> existPaids = getExistPaidLoanInfoIds(loanInfoIds);
		Set<String> isUnderlyingAssetSet = getIsUnderlyingAssetLoanInfoIds(loanInfoIds);
		Set<String> denyContractNos = AssetLoanInfoUtil.getDenyModifyOrPaidContractNos();
		for (AssetLoanDTO loan : returnValue) {
			loan.setExistPaidInfo(existPaids.contains(loan.getId()));
			loan.setUnderlyingAsset(isUnderlyingAssetSet.contains(loan.getId()));
			loan.setDenyModifyOrPaid(denyContractNos.contains(loan.getBusinessContractNo()));
		}

		return returnValue;
	}

	private Set<String> getIsUnderlyingAssetLoanInfoIds(List<String> loanInfoIds) {
		if (CollectionUtils.isEmpty(loanInfoIds))
			return Sets.newHashSet();

		UnderlyingAssetExample underlyingAssetExample = new UnderlyingAssetExample();
		underlyingAssetExample.createCriteria().andIdIn(loanInfoIds);
		List<UnderlyingAsset> underlyingAssets = underlyingAssetMapper.selectByExample(underlyingAssetExample);
		Set<String> isUnderlyingAssetSet = Sets.newHashSet();
		for (UnderlyingAsset asset : underlyingAssets) {
			isUnderlyingAssetSet.add(asset.getId());
		}
		return isUnderlyingAssetSet;
	}

	private Set<String> getExistPaidLoanInfoIds(List<String> loanInfoIds) {
		if (CollectionUtils.isEmpty(loanInfoIds))
			return Sets.newHashSet();

		LoanPaidExample example = new LoanPaidExample();
		example.createCriteria().andLoanInfoIdIn(loanInfoIds);
		List<LoanPaid> paids = loanPaidMapper.selectByExample(example);
		Set<String> existPaids = Sets.newHashSet();
		for (LoanPaid paid : paids) {
			existPaids.add(paid.getLoanInfoId());
		}
		return existPaids;
	}
	
	public LoanPlanDTO convertLoanPlan2LoanPlanDTO(LoanPlan loanPlan) {
		LoanPlanDTO loanPlanDTO = new LoanPlanDTO();
		BeanUtils.copyProperties(loanPlan, loanPlanDTO);
		if((loanPlanDTO.getPaidPrincipal()!=null && loanPlanDTO.getPaidPrincipal()>0) || (loanPlanDTO.getPaidInterest()!=null && loanPlanDTO.getPaidInterest()>0) || (loanPlanDTO.getPaidOverdue()!=null && loanPlanDTO.getPaidOverdue()>0))
			loanPlanDTO.setExistPaidInfo(true);
		if(loanPlan.getFinanceBalance() != null)
			loanPlanDTO.setFinanceBalance(MoneyUtil.formatMoney(loanPlan.getFinanceBalance()));
		Long accountAmount = 0L;
		Long accountPrincipal = loanPlan.getAccountPrincipal();
		if(accountPrincipal != null) {
			loanPlanDTO.setAccountPrincipal(MoneyUtil.formatMoney(accountPrincipal));
			accountAmount += accountPrincipal;
		}
		Long accountInterest = loanPlan.getAccountInterest();
		if(accountInterest != null) {
			loanPlanDTO.setAccountInterest(MoneyUtil.formatMoney(accountInterest));
			accountAmount += accountInterest;
		}
		Long accountOverdue = loanPlan.getAccountOverdue();
		if(accountOverdue != null) {
			loanPlanDTO.setAccountOverdue(MoneyUtil.formatMoney(accountOverdue));
			accountAmount += accountOverdue;
		}
		if(accountPrincipal != null && accountInterest != null && accountOverdue != null)
			loanPlanDTO.setAccountAmount(MoneyUtil.formatMoney(accountAmount));
		if(StringUtils.isNotBlank(loanPlan.getSettleStatus()))
			loanPlanDTO.setSettleStatusDesc(SettleStatus.valueOf(loanPlan.getSettleStatus()).desc());
		if(StringUtils.isNotBlank(loanPlan.getLastPaidDate())) {
			String diffInDayStr = String.valueOf(diffInDay(loanPlan.getRepaymentDate(), loanPlan.getLastPaidDate()));
			if(loanPlan.getRepaymentDate().compareTo(loanPlan.getLastPaidDate()) > 0)
				loanPlanDTO.setForwardDays(diffInDayStr);
			else if(loanPlan.getRepaymentDate().compareTo(loanPlan.getLastPaidDate()) < 0)
				loanPlanDTO.setOverdueDays(diffInDayStr);
		}
		return loanPlanDTO;
	}

	public List<LoanPlanDTO> convertLoanPlan2LoanPlanDTO(List<LoanPlan> loanPlans) {
		List<LoanPlanDTO> returnValue = new ArrayList<>();
		for (LoanPlan loanPlan : loanPlans)
			returnValue.add(convertLoanPlan2LoanPlanDTO(loanPlan));
		return returnValue;
	}

	/**
	 * 计算两个日期相差天数
	 * 例如2016-08-15与2016-08-16相差1天
	 * @param date1
	 * @param date2
	 * @return
	 */
	public int diffInDay(String date1,String date2){
		return DateTimeUtil.diffInDay(DateTimeUtil.convertStringToDate(date1), DateTimeUtil.convertStringToDate(date2));
	}
}
