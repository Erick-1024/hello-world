package com.cana.asset.service.transaction.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.cana.vbam.common.asset.dto.ExpenseDTO;
import com.cana.vbam.common.asset.dto.InvoiceInfoDTO;
import com.cana.vbam.common.asset.dto.InvoiceListDTO;
import com.cana.vbam.common.asset.dto.InvoiceRedisDTO;
import com.cana.vbam.common.asset.enums.BusinessProduct;
import com.cana.vbam.common.asset.enums.CreditCurrencyType;
import com.travelzen.framework.core.exception.WebException;

public class AssetInvoicePersistenceValidator {

	/**
	 * 校验excel中的数据
	 * @param invoiceRedisDTO
	 * @return
	 * @throws ParseException 
	 */
	public static Boolean checkInvoiceExcelDTO(InvoiceRedisDTO invoiceRedisDTO) throws ParseException {
		String businessContractNo = invoiceRedisDTO.getBusinessContractNo(); 
		String memberName = invoiceRedisDTO.getMemberName(); 
		String businessProductDesc = invoiceRedisDTO.getBusinessProductDesc(); 
		String currencyDesc = invoiceRedisDTO.getCurrencyDesc(); 
		String counterparty = invoiceRedisDTO.getCounterparty(); 
		String invoiceNo = invoiceRedisDTO.getInvoiceNo(); 
		String nominvoiceAmt = invoiceRedisDTO.getNominvoiceAmt(); 
		String invoiceAmt = invoiceRedisDTO.getInvoiceAmt(); 
		String financingRatio = invoiceRedisDTO.getFinancingRatio(); 
		String invoiceDate = invoiceRedisDTO.getInvoiceDate(); 
		String dueDate = invoiceRedisDTO.getDueDate(); 
		String expenseSubject = invoiceRedisDTO.getExpenseSubject(); 
		String amount = invoiceRedisDTO.getAmount(); 
		if(StringUtils.isBlank(businessContractNo))
			return false;
		if(StringUtils.isBlank(memberName))
			return false;
		if(StringUtils.isBlank(businessProductDesc))
			return false;
		else{
			BusinessProduct businessProduct = BusinessProduct.getEnum(businessProductDesc);
			if(businessProduct == null)
				return false;
		}
		if(StringUtils.isBlank(currencyDesc))
			return false;
		else{
			CreditCurrencyType creditCurrencyType = CreditCurrencyType.getEnum(currencyDesc);
			if(creditCurrencyType == null)
				return false;
		}
		if(StringUtils.isBlank(counterparty))
			return false;
		if(StringUtils.isBlank(invoiceNo))
			return false;
		if(StringUtils.isBlank(nominvoiceAmt))
			return false;
		else{
			if(!ValidateRules.regexAmountCheck(nominvoiceAmt))
				return false;
		}
		if(StringUtils.isBlank(invoiceAmt))
			return false;
		else{
			if(!ValidateRules.regexAmountCheck(invoiceAmt))
				return false;
		}
		if(StringUtils.isBlank(financingRatio))
			return false;
		else{
			if(!ValidateRules.regexPercentCheck(financingRatio))
				return false;
		}
		if(StringUtils.isBlank(invoiceDate))
			return false;
		else{
			if(!ValidateRules.regexDateCheck(invoiceDate))
				return false;
		}
		if(StringUtils.isBlank(dueDate))
			return false;
		else{
			if(!ValidateRules.regexDateCheck(dueDate))
				return false;
		}
		//invoiceDate 小于 dueDate
		if((new SimpleDateFormat("yyyy-MM-dd").parse(invoiceDate)).compareTo(new SimpleDateFormat("yyyy-MM-dd").parse(dueDate))>=0)
			return false;
		if(StringUtils.isBlank(expenseSubject))
			return false;
		if(StringUtils.isBlank(amount))
			return false;
		else{
			if(!ValidateRules.regexAmountCheck(amount))
				return false;
		}
		return true;
	}

	public static void checkInvoice(InvoiceListDTO invoiceListDTO){
		List<InvoiceInfoDTO> invoiceInfoDTOs = invoiceListDTO.getInvoiceInfoDTOs(); 
		List<ExpenseDTO> expenseDTOs = invoiceListDTO.getExpenseDTOs(); 
		//应收账款数据
		if(CollectionUtils.isNotEmpty(invoiceInfoDTOs)){
			for (InvoiceInfoDTO invoiceInfoDTO : invoiceInfoDTOs){
				if(!ValidateRules.regexDateCheck(invoiceInfoDTO.getInvoiceDate())){
					throw WebException.instance("开票日期不合法");
				}
				if(!ValidateRules.regexDateCheck(invoiceInfoDTO.getDueDate())){
					throw WebException.instance("到期日期不合法");
				}
				try {
					Date invDate = new SimpleDateFormat("yyyy-MM-dd").parse(invoiceInfoDTO.getInvoiceDate());
					Date dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(invoiceInfoDTO.getDueDate());
					if(invDate.compareTo(dueDate)>=0){
						throw WebException.instance("到期日应大于开票日");
					}
				} catch (ParseException e) {
					throw WebException.instance("应收账款日期解析异常");
				}
			}
		}
		//费用信息数据
		/*if(CollectionUtils.isNotEmpty(expenseDTOs)){
			for(ExpenseDTO ExpenseDTO : expenseDTOs){
				
			}
		}*/
	}
}
