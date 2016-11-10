package com.cana.asset.service.convertors;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cana.asset.dao.mapper.gen.BusinessCounterpartyMapper;
import com.cana.asset.dao.mapper.gen.BusinessGuaranteeInfoMapper;
import com.cana.asset.dao.mapper.gen.ExpenseMapper;
import com.cana.asset.dao.po.BusinessBasicInfo;
import com.cana.asset.dao.po.BusinessCounterparty;
import com.cana.asset.dao.po.BusinessCounterpartyExample;
import com.cana.asset.dao.po.BusinessGuaranteeInfo;
import com.cana.asset.dao.po.BusinessGuaranteeInfoExample;
import com.cana.asset.dao.po.Expense;
import com.cana.asset.dao.po.ExpenseExample;
import com.cana.vbam.common.asset.dto.BusinessCounterpartyDTO;
import com.cana.vbam.common.asset.dto.BusinessGuaranteeInfoDTO;
import com.cana.vbam.common.asset.dto.ExpenseDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessDTO;
import com.cana.vbam.common.asset.enums.BusinessMode;
import com.cana.vbam.common.asset.enums.BusinessProduct;
import com.cana.vbam.common.asset.enums.CustomerTypeEnum;
import com.cana.vbam.common.asset.enums.ExpenseType;
import com.cana.vbam.common.asset.enums.FactoringType;
import com.cana.vbam.common.asset.enums.LoanState;
import com.cana.vbam.common.asset.enums.ReceiptType;
import com.cana.vbam.common.repayment.enums.Currency;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.utils.AccountNoUtil;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.util.MoneyUtil;

@Component
public class FactorBusinessConvertor {

	@Resource
	private BusinessCounterpartyMapper businessCounterpartyMapper;
	
	@Resource
	private BusinessGuaranteeInfoMapper businessGuaranteeInfoMapper;
	
	@Resource
	private ExpenseMapper expenseMapper;
	
	public FactorBusinessDTO convertBusinessBasicInfo2FactorBusinessDTO(BusinessBasicInfo businessBasicInfo, boolean relevant) {
		FactorBusinessDTO factorBusinessDTO = new FactorBusinessDTO();
		BeanUtils.copyProperties(businessBasicInfo, factorBusinessDTO);
		if(StringUtils.isNotBlank(businessBasicInfo.getCurrency()))
			factorBusinessDTO.setCurrency(Currency.valueOf(businessBasicInfo.getCurrency()));
		if(StringUtils.isNotBlank(businessBasicInfo.getBusinessProduct()))
			factorBusinessDTO.setBusinessProduct(BusinessProduct.valueOf(businessBasicInfo.getBusinessProduct()));
		if(StringUtils.isNotBlank(businessBasicInfo.getBusinessMode()))
			factorBusinessDTO.setBusinessMode(BusinessMode.valueOf(businessBasicInfo.getBusinessMode()));
		if(StringUtils.isNotBlank(businessBasicInfo.getInterestType()))
			factorBusinessDTO.setInterestType(RepaymentType.valueOf(businessBasicInfo.getInterestType()));
		if(StringUtils.isNotBlank(businessBasicInfo.getReceiptType()))
			factorBusinessDTO.setReceiptType(ReceiptType.valueOf(businessBasicInfo.getReceiptType()));
		if(StringUtils.isNotBlank(businessBasicInfo.getLoanState()))
			factorBusinessDTO.setLoanState(LoanState.valueOf(businessBasicInfo.getLoanState()));
		if(StringUtils.isNotBlank(businessBasicInfo.getFactoringAccount())){
			factorBusinessDTO.setFactoringAccount(AccountNoUtil.formatBankAccountNo(businessBasicInfo.getFactoringAccount()));
		}
		if(StringUtils.isNotBlank(businessBasicInfo.getSettlementAccount())){
			factorBusinessDTO.setSettlementAccount(AccountNoUtil.formatBankAccountNo(businessBasicInfo.getSettlementAccount()));
		}
		if(relevant) {
			BusinessCounterpartyExample businessCounterpartyExample = new BusinessCounterpartyExample();
			businessCounterpartyExample.createCriteria().andBusinessInfoIdEqualTo(businessBasicInfo.getId());
			businessCounterpartyExample.setOrderByClause("sequence");
			factorBusinessDTO.setCounterpartyList(convertBusinessCounterparty2BusinessCounterpartyDTO(businessCounterpartyMapper.selectByExample(businessCounterpartyExample)));
			BusinessGuaranteeInfoExample businessGuaranteeInfoExample = new BusinessGuaranteeInfoExample();
			businessGuaranteeInfoExample.createCriteria().andBusinessInfoIdEqualTo(businessBasicInfo.getId());
			businessGuaranteeInfoExample.setOrderByClause("sequence");
			factorBusinessDTO.setGuaranteeInfoList(convertBusinessGuaranteeInfo2BusinessGuaranteeInfoDTO(businessGuaranteeInfoMapper.selectByExample(businessGuaranteeInfoExample)));
			ExpenseExample expenseExample = new ExpenseExample();
			expenseExample.createCriteria().andRefidEqualTo(businessBasicInfo.getId()).andReftypeEqualTo(ExpenseType.FACTOR_BUSINESS.name());
			expenseExample.setOrderByClause("sequence");
			factorBusinessDTO.setExpenseList(InvoiceConvertor.convertExpense2ExpenseDTO(expenseMapper.selectByExample(expenseExample)));
		}
		return factorBusinessDTO;
	}
	
	public List<FactorBusinessDTO> convertBusinessBasicInfo2FactorBusinessDTO(List<BusinessBasicInfo> businessBasicInfos, boolean relevant) {
		List<FactorBusinessDTO> returnValue = new ArrayList<>();
		for (BusinessBasicInfo businessBasicInfo : businessBasicInfos)
			returnValue.add(convertBusinessBasicInfo2FactorBusinessDTO(businessBasicInfo, relevant));
		return returnValue;
	}
	
	public BusinessCounterpartyDTO convertBusinessCounterparty2BusinessCounterpartyDTO(BusinessCounterparty businessCounterparty) {
		BusinessCounterpartyDTO businessCounterpartyDTO = new BusinessCounterpartyDTO();
		BeanUtils.copyProperties(businessCounterparty, businessCounterpartyDTO);
		businessCounterpartyDTO.setFinancingRatio(MoneyArithUtil.convertInterestRateToString(businessCounterparty.getFinancingRatio()));
		if(StringUtils.isNotBlank(businessCounterparty.getFactoringType()))
			businessCounterpartyDTO.setFactoringType(FactoringType.valueOf(businessCounterparty.getFactoringType()));
		if(businessCounterparty.getQuerySubLimitFlag()){
			businessCounterpartyDTO.setQuerySubLimitFlag(Boolean.TRUE);
			businessCounterpartyDTO.setSubLimit(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(businessCounterparty.getSubLimit())));
		}else{
			businessCounterpartyDTO.setQuerySubLimitFlag(Boolean.FALSE);
			businessCounterpartyDTO.setSubLimit(null);
		}
		businessCounterpartyDTO.setCounterpartyType(CustomerTypeEnum.valueOf(businessCounterparty.getCounterpartyType()));
		businessCounterpartyDTO.setSequence(businessCounterparty.getSequence().toString());
		return businessCounterpartyDTO;
	}
	
	public List<BusinessCounterpartyDTO> convertBusinessCounterparty2BusinessCounterpartyDTO(List<BusinessCounterparty> businessCounterpartys) {
		List<BusinessCounterpartyDTO> returnValue = new ArrayList<>();
		for (BusinessCounterparty businessCounterparty : businessCounterpartys)
			returnValue.add(convertBusinessCounterparty2BusinessCounterpartyDTO(businessCounterparty));
		return returnValue;
	}
	
	public BusinessGuaranteeInfoDTO convertBusinessGuaranteeInfo2BusinessGuaranteeInfoDTO(BusinessGuaranteeInfo businessGuaranteeInfo) {
		BusinessGuaranteeInfoDTO businessGuaranteeInfoDTO = new BusinessGuaranteeInfoDTO();
		BeanUtils.copyProperties(businessGuaranteeInfo, businessGuaranteeInfoDTO);
		businessGuaranteeInfoDTO.setSequence(businessGuaranteeInfo.getSequence().toString());
		return businessGuaranteeInfoDTO;
	}
	
	public List<BusinessGuaranteeInfoDTO> convertBusinessGuaranteeInfo2BusinessGuaranteeInfoDTO(List<BusinessGuaranteeInfo> businessGuaranteeInfos) {
		List<BusinessGuaranteeInfoDTO> returnValue = new ArrayList<>();
		for (BusinessGuaranteeInfo businessGuaranteeInfo : businessGuaranteeInfos)
			returnValue.add(convertBusinessGuaranteeInfo2BusinessGuaranteeInfoDTO(businessGuaranteeInfo));
		return returnValue;
	}
	
	public BusinessBasicInfo convertBusinessBasicInfoDTO2FactorBusiness(FactorBusinessDTO businessBasicInfoDTO) {
		BusinessBasicInfo businessBasicInfo = new BusinessBasicInfo();
		BeanUtils.copyProperties(businessBasicInfoDTO, businessBasicInfo);
		if(null != businessBasicInfoDTO.getCurrency())
			businessBasicInfo.setCurrency(businessBasicInfoDTO.getCurrency().name());
		if(null != businessBasicInfoDTO.getBusinessProduct())
			businessBasicInfo.setBusinessProduct(businessBasicInfoDTO.getBusinessProduct().name());
		if(null != businessBasicInfoDTO.getBusinessMode())
			businessBasicInfo.setBusinessMode(businessBasicInfoDTO.getBusinessMode().name());
		if(null != businessBasicInfoDTO.getInterestType())
			businessBasicInfo.setInterestType(businessBasicInfoDTO.getInterestType().name());
		if(null != businessBasicInfoDTO.getReceiptType())
			businessBasicInfo.setReceiptType(businessBasicInfoDTO.getReceiptType().name());
		businessBasicInfo.setLoanState(LoanState.UNLOAN.name());
		businessBasicInfo.setFactoringAccount(AccountNoUtil.parseBankAccountNo(businessBasicInfoDTO.getFactoringAccount()));
		businessBasicInfo.setSettlementAccount(AccountNoUtil.parseBankAccountNo(businessBasicInfoDTO.getSettlementAccount()));
		return businessBasicInfo;
	}
	
	public List<BusinessCounterparty> convertBusinessCounterpartyDTO2BusinessCounterparty(List<BusinessCounterpartyDTO> businessCounterpartyDTOList) {
		List<BusinessCounterparty> returnValue = new ArrayList<>();
		for (BusinessCounterpartyDTO businessCounterpartyDTO : businessCounterpartyDTOList)
			returnValue.add(convertBusinessCounterpartyDTO2BusinessCounterparty(businessCounterpartyDTO));
		return returnValue;
	}
	
	public BusinessCounterparty convertBusinessCounterpartyDTO2BusinessCounterparty(BusinessCounterpartyDTO businessCounterpartyDTO) {
		BusinessCounterparty businessCounterparty = new BusinessCounterparty();
		BeanUtils.copyProperties(businessCounterpartyDTO, businessCounterparty);
		if(null != businessCounterpartyDTO.getFactoringType())
			businessCounterparty.setFactoringType(businessCounterpartyDTO.getFactoringType().name());
		if(null !=businessCounterpartyDTO.getCounterpartyType())
			businessCounterparty.setCounterpartyType(businessCounterpartyDTO.getCounterpartyType().name());
		if(StringUtils.isNotBlank(businessCounterpartyDTO.getSequence()))
			businessCounterparty.setSequence(Integer.parseInt(businessCounterpartyDTO.getSequence()));
		if(StringUtils.isNotBlank(businessCounterpartyDTO.getFinancingRatio())){
			businessCounterparty.setFinancingRatio(MoneyArithUtil.convertStringToInterestRate(businessCounterpartyDTO.getFinancingRatio()));
		}
		if(businessCounterpartyDTO.getQuerySubLimitFlag()){
			businessCounterparty.setQuerySubLimitFlag(Boolean.TRUE);
			businessCounterparty.setSubLimit(MoneyArithUtil.convertStringToMoney(MoneyUtil.parseMoney(businessCounterpartyDTO.getSubLimit())));
		}else{
			businessCounterparty.setQuerySubLimitFlag(Boolean.FALSE);
		}
		return businessCounterparty;
	}
	
	public List<BusinessGuaranteeInfo> convertBusinessGuaranteeInfoDTO2BusinessGuaranteeInfo(List<BusinessGuaranteeInfoDTO> businessGuaranteeInfoList) {
		if(CollectionUtils.isEmpty(businessGuaranteeInfoList)){
			return Lists.newArrayList();
		}
		List<BusinessGuaranteeInfo> returnValue = new ArrayList<>();
		for (BusinessGuaranteeInfoDTO businessGuaranteeInfoDTO : businessGuaranteeInfoList)
			returnValue.add(convertBusinessGuaranteeInfoDTO2BusinessGuaranteeInfo(businessGuaranteeInfoDTO));
		return returnValue;
	}
	
	public BusinessGuaranteeInfo convertBusinessGuaranteeInfoDTO2BusinessGuaranteeInfo(BusinessGuaranteeInfoDTO businessGuaranteeInfoDTO) {
		BusinessGuaranteeInfo businessGuaranteeInfo = new BusinessGuaranteeInfo();
		BeanUtils.copyProperties(businessGuaranteeInfoDTO, businessGuaranteeInfo);
		businessGuaranteeInfo.setSequence(Integer.parseInt(businessGuaranteeInfoDTO.getSequence()));
		return businessGuaranteeInfo;
	}
	
	public List<Expense> convertAssetExpenseDTO2AssetExpense(List<ExpenseDTO> expenseDTOList) {
		if(CollectionUtils.isEmpty(expenseDTOList)){
			return Lists.newArrayList();
		}
		List<Expense> returnValue = new ArrayList<>();
		for (ExpenseDTO expenseDTO : expenseDTOList)
			returnValue.add(convertAssetExpenseDTO2AssetExpense(expenseDTO));
		return returnValue;
	}
	
	public Expense convertAssetExpenseDTO2AssetExpense(ExpenseDTO expenseDTO) {
		Expense expense = new Expense();
		BeanUtils.copyProperties(expenseDTO, expense);
		expense.setReftype(ExpenseType.FACTOR_BUSINESS.name());
		expense.setAmount(MoneyArithUtil.convertStringToMoney(expenseDTO.getAmountStr()));
		expense.setSequence(expenseDTO.getSequence());
		return expense;
	}
}
