package com.cana.asset.service.convertors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cana.asset.dao.po.AssetInvoiceBasicInfo;
import com.cana.asset.dao.po.AssetInvoiceInfo;
import com.cana.asset.dao.po.Expense;
import com.cana.vbam.common.asset.dto.ExpenseDTO;
import com.cana.vbam.common.asset.dto.InvoiceInfoDTO;
import com.cana.vbam.common.asset.dto.InvoiceListDTO;
import com.cana.vbam.common.asset.dto.InvoiceRedisDTO;
import com.cana.vbam.common.asset.enums.BusinessProduct;
import com.cana.vbam.common.asset.enums.CreditCurrencyType;
import com.cana.vbam.common.asset.enums.ExpenseType;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.travelzen.framework.core.util.MoneyUtil;

public class InvoiceConvertor {

	public static List<InvoiceListDTO> convertorInvListToInvoiceDTO(List<AssetInvoiceBasicInfo> assetInvoiceList) {
		List<InvoiceListDTO> invoiceListDTOs = new ArrayList<>();
		Iterator<AssetInvoiceBasicInfo> invIterator = assetInvoiceList.iterator(); 
		while(invIterator.hasNext()){
			AssetInvoiceBasicInfo assetInvoiceBasicInfo = invIterator.next(); 
			InvoiceListDTO invDTO = new InvoiceListDTO();
			invDTO.setCustomerName(assetInvoiceBasicInfo.getCustomerName()); 
			if(StringUtils.isNotBlank(assetInvoiceBasicInfo.getCurrency())){
				invDTO.setCurrency(assetInvoiceBasicInfo.getCurrency());
				invDTO.setCurrencyDesc(CreditCurrencyType.valueOf(assetInvoiceBasicInfo.getCurrency()).desc());
			}
			if(StringUtils.isNotBlank(assetInvoiceBasicInfo.getBusinessProduct())){
				invDTO.setBusinessProduct(assetInvoiceBasicInfo.getBusinessProduct());
				invDTO.setBusinessProductDesc(BusinessProduct.valueOf(assetInvoiceBasicInfo.getBusinessProduct()).desc());
			}
			invDTO.setBusinessContractNo(assetInvoiceBasicInfo.getBusinessContractNo());
			invDTO.setCountInvoice(assetInvoiceBasicInfo.getCountInvoice());
			invDTO.setSumInvoiceAmt(MoneyArithUtil.convertMoneyToString(assetInvoiceBasicInfo.getSumInvoiceAmt()));
			invDTO.setId(assetInvoiceBasicInfo.getId());
			invoiceListDTOs.add(invDTO);
		}
		return invoiceListDTOs;
	}

	public static List<InvoiceListDTO> convertorRedisToListDTO(Map<String,List<InvoiceRedisDTO>> passInvoiceMap) {
		List<InvoiceListDTO> invList = new ArrayList<>();
		for(String key : passInvoiceMap.keySet()){
			List<InvoiceRedisDTO> invRedisList = passInvoiceMap.get(key);
			InvoiceListDTO listDTO = new InvoiceListDTO();
			listDTO.setCustomerName(invRedisList.get(0).getMemberName());
			listDTO.setCurrencyDesc(invRedisList.get(0).getCurrencyDesc());
			listDTO.setBusinessProductDesc(invRedisList.get(0).getBusinessProductDesc());
			listDTO.setBusinessContractNo(invRedisList.get(0).getBusinessContractNo());
			listDTO.setCountInvoice(invRedisList.size());
			listDTO.setSumInvoiceAmt(MoneyUtil.formatMoney(getSumInvoiceAmt(invRedisList)));
			invList.add(listDTO);
		}
		return invList;
	}


	public static InvoiceListDTO convertorInvBasicInfoToInvDTO(AssetInvoiceBasicInfo assetInvoiceBasicInfo) {
		InvoiceListDTO invDTO = new InvoiceListDTO();
		invDTO.setCustomerId(assetInvoiceBasicInfo.getCustomerId()); 
		invDTO.setCustomerName(assetInvoiceBasicInfo.getCustomerName()); 
		if(StringUtils.isNotBlank(assetInvoiceBasicInfo.getCurrency())){
			invDTO.setCurrency(assetInvoiceBasicInfo.getCurrency());
			invDTO.setCurrencyDesc(CreditCurrencyType.valueOf(assetInvoiceBasicInfo.getCurrency()).desc());
		}
		if(StringUtils.isNotBlank(assetInvoiceBasicInfo.getBusinessProduct())){
			invDTO.setBusinessProduct(assetInvoiceBasicInfo.getBusinessProduct());
			invDTO.setBusinessProductDesc(BusinessProduct.valueOf(assetInvoiceBasicInfo.getBusinessProduct()).desc());
		}
		String sumInvoiceAmt = MoneyArithUtil.convertMoneyToString(assetInvoiceBasicInfo.getSumInvoiceAmt());
		invDTO.setBusinessContractNo(assetInvoiceBasicInfo.getBusinessContractNo());
		invDTO.setCountInvoice(assetInvoiceBasicInfo.getCountInvoice());
		invDTO.setSumInvoiceAmt(sumInvoiceAmt);
		invDTO.setId(assetInvoiceBasicInfo.getId());
		return invDTO;
	}

	private static String getSumInvoiceAmt(List<InvoiceRedisDTO> invRedisList) {
		BigDecimal invAmt = new BigDecimal(0);
		for(InvoiceRedisDTO invRedisDTO : invRedisList){
			String parseMoney = MoneyUtil.parseMoney(invRedisDTO.getInvoiceAmt()); 
			if(StringUtils.isNotBlank(parseMoney))
				invAmt = invAmt.add(new BigDecimal(parseMoney));
		}
		return invAmt.toString();
	}

	public static List<InvoiceInfoDTO> convertorAssetInvInfoToInvInfoDTO(List<AssetInvoiceInfo> invList,String businessContractNo) {
		List<InvoiceInfoDTO> invoiceInfoDTOs = new ArrayList<>();
		for(AssetInvoiceInfo assetInvInfo : invList){
			InvoiceInfoDTO invInfoDTO = new InvoiceInfoDTO();
			String nominvoiceAmt = MoneyArithUtil.convertMoneyToString(assetInvInfo.getNominvoiceAmt());
			String invoiceAmt = MoneyArithUtil.convertMoneyToString(assetInvInfo.getInvoiceAmt());
			invInfoDTO.setBusinessContractNo(businessContractNo);
			invInfoDTO.setId(assetInvInfo.getId());
			invInfoDTO.setInvoiceBaseId(assetInvInfo.getInvoiceBaseId());
			invInfoDTO.setCounterpartyId(assetInvInfo.getCounterpartyId());
			invInfoDTO.setCounterparty(assetInvInfo.getCounterparty());
			invInfoDTO.setInvoiceNo(assetInvInfo.getInvoiceNo());
			invInfoDTO.setNominvoiceAmt(nominvoiceAmt);
			invInfoDTO.setInvoiceAmt(invoiceAmt);
			invInfoDTO.setFinancingRatio(assetInvInfo.getFinancingRatio().multiply(new BigDecimal(100))); 
			invInfoDTO.setInvoiceDate(assetInvInfo.getInvoiceDate());
			invInfoDTO.setDueDate(assetInvInfo.getDueDate());
			invInfoDTO.setCreateTime(assetInvInfo.getCreateTime());
			invInfoDTO.setUpdateTime(assetInvInfo.getUpdateTime());
			if(StringUtils.isNotBlank(assetInvInfo.getLoanInfoId()))
				invInfoDTO.setLoanInfoId(assetInvInfo.getLoanInfoId());
			invoiceInfoDTOs.add(invInfoDTO);
		}
		return invoiceInfoDTOs;
	}

	
	public static ExpenseDTO convertExpense2ExpenseDTO(Expense expense) {
		ExpenseDTO expenseDTO = new ExpenseDTO();
		BeanUtils.copyProperties(expense, expenseDTO);
		expenseDTO.setAmountStr(MoneyArithUtil.convertMoneyToString(expense.getAmount()));
		expenseDTO.setReftype(ExpenseType.valueOf(expense.getReftype()));
		return expenseDTO;
	}
	
	public static List<ExpenseDTO> convertExpense2ExpenseDTO(List<Expense> expenses) {
		List<ExpenseDTO> returnValue = new ArrayList<>();
		for (Expense expense : expenses)
			returnValue.add(convertExpense2ExpenseDTO(expense));
		return returnValue;
	}


	public static List<ExpenseDTO> convertorExpensesToExpenseDTO(List<Expense> expenses) {
		List<ExpenseDTO> expenseDTOs = new ArrayList<>();
		for(Expense expense : expenses){
			ExpenseDTO expenseDTO = new ExpenseDTO();
			expenseDTO.setId(expense.getId());
			expenseDTO.setRefid(expense.getRefid()); 
			expenseDTO.setReftype(ExpenseType.valueOf(expense.getReftype())); 
			expenseDTO.setExpenseSubject(expense.getExpenseSubject());
			expenseDTO.setAmount(expense.getAmount());
			expenseDTO.setAmountStr(MoneyArithUtil.convertMoneyToString(expense.getAmount()));
			expenseDTO.setCreateTime(expense.getCreateTime());
			expenseDTO.setUpdateTime(expense.getUpdateTime());
			expenseDTOs.add(expenseDTO);
		}
		return expenseDTOs;

	}
	
}
