package com.cana.asset.search.api.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.asset.api.IABSUnderlyingAssetSearchApi;
import com.cana.asset.search.index.bean.UnderlyingAssetIndexBean;
import com.cana.asset.search.index.bean.UnderlyingAssetIndexBean.UnderlyingAssetFieldEnum;
import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetSearchCriteria;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.lucene.index.IndexBuilder;
import com.travelzen.framework.lucene.index.query.bean.BooleanQueryExample;

/**
 * @author hu
 *
 */
public class ABSUnderlyingAssetSearchImpl implements IABSUnderlyingAssetSearchApi{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IMemberQueryApi memberQueryApi;
	
	@Override
	public ListResult<UnderlyingAssetDTO> searchUnderlyAsset(UnderlyingAssetSearchCriteria criteria) throws Exception{
		IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
		BooleanQueryExample stuexa = new BooleanQueryExample();

		stuexa.and(UnderlyingAssetFieldEnum.specialProgramId.name(), criteria.getSpecialProgramId());
		
		if(StringUtils.isNotBlank(criteria.getBusinessContractNo()))
			stuexa.contains(UnderlyingAssetFieldEnum.businessContractNo.name(), criteria.getBusinessContractNo());
		if(StringUtils.isNotBlank(criteria.getBusinessProduct()))
			stuexa.and(UnderlyingAssetFieldEnum.businessProduct.name(), criteria.getBusinessProduct());
		if(StringUtils.isNotBlank(criteria.getCustomerName()))
			stuexa.contains(UnderlyingAssetFieldEnum.customerName.name(), criteria.getCustomerName());
		if(StringUtils.isNotBlank(criteria.getCustomerEconomicCategory()))
			stuexa.contains(UnderlyingAssetFieldEnum.customerEconomicCategory.name(), criteria.getCustomerEconomicCategory());
		if(StringUtils.isNotBlank(criteria.getCustomerCity()))
			stuexa.contains(UnderlyingAssetFieldEnum.customerCity.name(), criteria.getCustomerCity());
		if(StringUtils.isNotBlank(criteria.getCustomerIndustry()))
			stuexa.contains(UnderlyingAssetFieldEnum.customerIndustry.name(), criteria.getCustomerIndustry());
		if(StringUtils.isNotBlank(criteria.getCounterparty()))
			stuexa.contains(UnderlyingAssetFieldEnum.counterparty.name(), criteria.getCounterparty());
		if(StringUtils.isNotBlank(criteria.getCounterpartyEconomicCategory()))
			stuexa.contains(UnderlyingAssetFieldEnum.counterpartyEconomicCategory.name(), criteria.getCounterpartyEconomicCategory());
		if(StringUtils.isNotBlank(criteria.getCounterpartyCity()))
			stuexa.contains(UnderlyingAssetFieldEnum.counterpartyCity.name(), criteria.getCounterpartyCity());
		if(StringUtils.isNotBlank(criteria.getCounterpartyIndustry()))
			stuexa.contains(UnderlyingAssetFieldEnum.counterpartyIndustry.name(), criteria.getCounterpartyIndustry());
		if(StringUtils.isNotBlank(criteria.getCounterpartyRating()))
			stuexa.contains(UnderlyingAssetFieldEnum.counterpartyRating.name(), criteria.getCounterpartyRating());
		if(StringUtils.isNotBlank(criteria.getLoanStartDate()) || StringUtils.isNotBlank(criteria.getLoanEndDate()))
			stuexa.betweenAndEqual(UnderlyingAssetFieldEnum.loanDate.name(), criteria.getLoanStartDate(), criteria.getLoanEndDate());
		if(StringUtils.isNotBlank(criteria.getSettleStartDate()) || StringUtils.isNotBlank(criteria.getSettleEndDate()))
			stuexa.betweenAndEqual(UnderlyingAssetFieldEnum.settlementDate.name(), criteria.getSettleStartDate(), criteria.getSettleEndDate());
		if(StringUtils.isNotBlank(criteria.getDueStartDate()) || StringUtils.isNotBlank(criteria.getDueEndDate()))
			stuexa.betweenAndEqual(UnderlyingAssetFieldEnum.dueDate.name(), criteria.getDueStartDate(), criteria.getDueEndDate());
		if(StringUtils.isNotBlank(criteria.getRepaymentMethod()))
			stuexa.contains(UnderlyingAssetFieldEnum.repaymentMethod.name(), criteria.getRepaymentMethod());
		if(StringUtils.isNotBlank(criteria.getGuaranteeInfo()))
			stuexa.contains(UnderlyingAssetFieldEnum.guaranteeInfo.name(), criteria.getGuaranteeInfo());
		if(StringUtils.isNotBlank(criteria.getGuaranteeCompanyInfo()))
			stuexa.contains(UnderlyingAssetFieldEnum.guaranteeCompanyInfo.name(), criteria.getGuaranteeCompanyInfo());
		if(StringUtils.isNotBlank(criteria.getGuaranteeGoodsNo()))
			stuexa.contains(UnderlyingAssetFieldEnum.guaranteeGoodsNo.name(), criteria.getGuaranteeGoodsNo());
		if(StringUtils.isNotBlank(criteria.getInvoiceStartAmount()) || StringUtils.isNotBlank(criteria.getInvoiceEndAmount()))
			stuexa.betweenAndEqual(UnderlyingAssetFieldEnum.invoiceAmount.name(), MoneyArithUtil.convertStringToMoney(
					MoneyUtil.parseMoney(criteria.getInvoiceStartAmount())), MoneyArithUtil.convertStringToMoney(MoneyUtil.parseMoney(criteria.getInvoiceEndAmount())));
		if(StringUtils.isNotBlank(criteria.getInvoiceStartBalance()) || StringUtils.isNotBlank(criteria.getInvoiceEndBalance()))
			stuexa.betweenAndEqual(UnderlyingAssetFieldEnum.invoiceBalance.name(), MoneyArithUtil.convertStringToMoney(
					MoneyUtil.parseMoney(criteria.getInvoiceStartBalance())), MoneyArithUtil.convertStringToMoney(MoneyUtil.parseMoney(criteria.getInvoiceEndBalance())));
		if(StringUtils.isNotBlank(criteria.getFinanceStartAmount()) || StringUtils.isNotBlank(criteria.getFinanceEndAmount()))
			stuexa.betweenAndEqual(UnderlyingAssetFieldEnum.financeAmount.name(), MoneyArithUtil.convertStringToMoney(
					MoneyUtil.parseMoney(criteria.getFinanceStartAmount())), MoneyArithUtil.convertStringToMoney(MoneyUtil.parseMoney(criteria.getFinanceEndAmount())));
		if(StringUtils.isNotBlank(criteria.getFinanceStartBalance()) || StringUtils.isNotBlank(criteria.getFinanceEndBalance()))
			stuexa.betweenAndEqual(UnderlyingAssetFieldEnum.financeBalance.name(), MoneyArithUtil.convertStringToMoney(
					MoneyUtil.parseMoney(criteria.getFinanceStartBalance())), MoneyArithUtil.convertStringToMoney(MoneyUtil.parseMoney(criteria.getFinanceEndBalance())));
		if(StringUtils.isNotBlank(criteria.getAnnualInterestRateStart()) || StringUtils.isNotBlank(criteria.getAnnualInterestRateEnd())){
			BigDecimal startRate = MoneyArithUtil.convertStringToInterestRate(criteria.getAnnualInterestRateStart());
			BigDecimal endRate = MoneyArithUtil.convertStringToInterestRate(criteria.getAnnualInterestRateEnd());
			stuexa.betweenAndEqual(UnderlyingAssetFieldEnum.interestRate.name(), startRate == null ? null : startRate.doubleValue(),
					endRate == null ? null : endRate.doubleValue());
		}
		if(StringUtils.isNotBlank(criteria.getAccountInterestStart()) || StringUtils.isNotBlank(criteria.getAccountInterestEnd()))
			stuexa.betweenAndEqual(UnderlyingAssetFieldEnum.accountInterest.name(), MoneyArithUtil.convertStringToMoney(
					MoneyUtil.parseMoney(criteria.getAccountInterestStart())), MoneyArithUtil.convertStringToMoney(MoneyUtil.parseMoney(criteria.getAccountInterestEnd())));
		if(StringUtils.isNotBlank(criteria.getPenaltyRateStart()) || StringUtils.isNotBlank(criteria.getPenaltyRateEnd())){
			BigDecimal startRate = MoneyArithUtil.convertStringToInterestRate(criteria.getPenaltyRateStart());
			BigDecimal endRate = MoneyArithUtil.convertStringToInterestRate(criteria.getPenaltyRateEnd());
			stuexa.betweenAndEqual(UnderlyingAssetFieldEnum.penaltyRate.name(), startRate == null ? null : startRate.doubleValue(),
					endRate == null ? null : endRate.doubleValue());
		}
		if(StringUtils.isNotBlank(criteria.getAccountTotalAmountStart()) || StringUtils.isNotBlank(criteria.getAccountTotalAmountEnd()))
			stuexa.betweenAndEqual(UnderlyingAssetFieldEnum.accountTotalAmount.name(), MoneyArithUtil.convertStringToMoney(
					MoneyUtil.parseMoney(criteria.getAccountTotalAmountStart())), MoneyArithUtil.convertStringToMoney(MoneyUtil.parseMoney(criteria.getAccountTotalAmountEnd())));
		if(StringUtils.isNotBlank(criteria.getCreditLimitStart()) || StringUtils.isNotBlank(criteria.getCreditLimitEnd()))
			stuexa.betweenAndEqual(UnderlyingAssetFieldEnum.creditLimit.name(), MoneyArithUtil.convertStringToMoney(
					MoneyUtil.parseMoney(criteria.getCreditLimitStart())), MoneyArithUtil.convertStringToMoney(MoneyUtil.parseMoney(criteria.getCreditLimitEnd())));
		if(StringUtils.isNotBlank(criteria.getCreditBalanceStart()) || StringUtils.isNotBlank(criteria.getCreditBalanceEnd()))
			stuexa.betweenAndEqual(UnderlyingAssetFieldEnum.creditBalance.name(), MoneyArithUtil.convertStringToMoney(
					MoneyUtil.parseMoney(criteria.getCreditBalanceStart())), MoneyArithUtil.convertStringToMoney(MoneyUtil.parseMoney(criteria.getCreditBalanceEnd())));
		if(StringUtils.isNotBlank(criteria.getCounterpartyLimitStart()) || StringUtils.isNotBlank(criteria.getCounterpartyLimitEnd()))
			stuexa.betweenAndEqual(UnderlyingAssetFieldEnum.counterpartyLimit.name(), MoneyArithUtil.convertStringToMoney(
					MoneyUtil.parseMoney(criteria.getCounterpartyLimitStart())), MoneyArithUtil.convertStringToMoney(MoneyUtil.parseMoney(criteria.getCounterpartyLimitEnd())));
		if(StringUtils.isNotBlank(criteria.getCounterpartyBalanceStart()) || StringUtils.isNotBlank(criteria.getCounterpartyBalanceEnd()))
			stuexa.betweenAndEqual(UnderlyingAssetFieldEnum.counterpartyBalance.name(), MoneyArithUtil.convertStringToMoney(
					MoneyUtil.parseMoney(criteria.getCounterpartyBalanceStart())), MoneyArithUtil.convertStringToMoney(MoneyUtil.parseMoney(criteria.getCounterpartyBalanceEnd())));
		
		stuexa.sortByDESC(UnderlyingAssetFieldEnum.updateTime.name(), UnderlyingAssetIndexBean.class);
		long totalNum = indexBuilder.count(stuexa, UnderlyingAssetIndexBean.class);
		List<UnderlyingAssetIndexBean> underlyingAssetIndexList = indexBuilder.doSearch(stuexa, criteria.getPage(), criteria.getPageSize(), UnderlyingAssetIndexBean.class);
		return ListResult.success(convertIndexBean2DTO(underlyingAssetIndexList), (int)totalNum);
	}

	private List<UnderlyingAssetDTO> convertIndexBean2DTO(List<UnderlyingAssetIndexBean> underlyingAssetList){
		List<UnderlyingAssetDTO> underlyingAssetDTOList = Lists.newArrayList();
		for(UnderlyingAssetIndexBean underlyingAsset:underlyingAssetList){
			UnderlyingAssetDTO underlyingAssetDTO = new UnderlyingAssetDTO();
			underlyingAssetDTO.setLoanNo(underlyingAsset.getLoanId());
			underlyingAssetDTO.setBusinessContractNo(underlyingAsset.getBusinessContractNo());
			underlyingAssetDTO.setCustomerEconomicCategory(underlyingAsset.getCustomerEconomicCategory());
			underlyingAssetDTO.setCustomerCity(underlyingAsset.getCustomerCity());
			underlyingAssetDTO.setCustomerIndustry(underlyingAsset.getCustomerIndustry());
			underlyingAssetDTO.setCustomerName(underlyingAsset.getCustomerName());
			underlyingAssetDTO.setCounterparty(underlyingAsset.getCounterparty());
			underlyingAssetDTO.setCounterpartyEconomicCategory(underlyingAsset.getCounterpartyEconomicCategory());
			underlyingAssetDTO.setCounterpartyCity(underlyingAsset.getCounterpartyCity());
			underlyingAssetDTO.setCounterpartyIndustry(underlyingAsset.getCounterpartyIndustry());
			underlyingAssetDTO.setCreditLimit(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(underlyingAsset.getCreditLimit())));
			underlyingAssetDTO.setCreditBalance(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(underlyingAsset.getCreditBalance())));
			if(underlyingAsset.getCounterpartyLimit() != null){
				underlyingAssetDTO.setCounterpartyLimit(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(underlyingAsset.getCounterpartyLimit())));
			}
			if(underlyingAsset.getCounterpartyBalance() != null){
				underlyingAssetDTO.setCounterpartyBalance(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(underlyingAsset.getCounterpartyBalance())));
			}
			underlyingAssetDTO.setInvoiceAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(underlyingAsset.getInvoiceAmount())));
			underlyingAssetDTO.setInvoiceBalance(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(underlyingAsset.getInvoiceBalance())));
			underlyingAssetDTO.setFinanceAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(underlyingAsset.getFinanceAmount())));
			underlyingAssetDTO.setFinanceBalance(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(underlyingAsset.getFinanceBalance())));
			underlyingAssetDTO.setRepaymentMethod(underlyingAsset.getRepaymentMethod());
			underlyingAssetDTO.setInterestRate(MoneyArithUtil.convertInterestRateToString(new BigDecimal(underlyingAsset.getInterestRate())));
			underlyingAssetDTO.setLoanDate(underlyingAsset.getLoanDate());
			underlyingAssetDTO.setDueDate(underlyingAsset.getDueDate());
			underlyingAssetDTO.setLoanPeriod(underlyingAsset.getLoanPeriod());
			underlyingAssetDTO.setSpecialProgramId(underlyingAsset.getSpecialProgramId());
			underlyingAssetDTO.setSpecialProgramName(underlyingAsset.getSpecialProgramName());
			underlyingAssetDTOList.add(underlyingAssetDTO);
		}
		return underlyingAssetDTOList;
	}
}
