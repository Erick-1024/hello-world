package com.cana.yundaex.service.convertors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditLimitDTO;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexCreditListMinDTO;
import com.cana.yundaex.common.dto.limit.YdQueryCreditLimitResponse;
import com.cana.yundaex.common.enums.YundaexAuditState;
import com.cana.yundaex.common.enums.YundaexCreditLimitGenerateState;
import com.cana.yundaex.common.enums.YundaexLimitStatus;
import com.cana.yundaex.common.enums.YundaexMode;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.util.DateUtils;

public class YundaexCreditConvert {

	public static List<YundaexCreditLimitDTO> convertYundaexCredit2YundaexCredit(
			List<CreditLimit> yundaexCreditLimits) {
		List<YundaexCreditLimitDTO> responseDTOs = new ArrayList<YundaexCreditLimitDTO>();
		for (CreditLimit creditLimit : yundaexCreditLimits) {
			YundaexCreditLimitDTO responseDTO = new YundaexCreditLimitDTO();
			responseDTO.setMemberId(creditLimit.getMemberId());
			responseDTO.setCompanyName(creditLimit.getCompanyName());
			responseDTO.setCreditMode(YundaexMode.valueOf(creditLimit.getCreditMode()).desc());
			String totalLimit = MoneyUtil
					.formatMoney(creditLimit.getTotalLimit() == null ? 0 : creditLimit.getTotalLimit());
			String usedLimit = MoneyUtil
					.formatMoney(creditLimit.getUsedLimit() == null ? 0 : creditLimit.getUsedLimit());
			responseDTO.setTotalLimit(totalLimit);
			responseDTO.setUsedLimit(usedLimit);
			String date = creditLimit.getEffectiveDate() == null ? "--"
					: DateUtils.format(creditLimit.getEffectiveDate(), 19);
			responseDTO.setEffectiveDate(date);
			responseDTO.setLimitStatus(YundaexLimitStatus.valueOf(creditLimit.getStatus()).desc());
			responseDTOs.add(responseDTO);
		}
		return responseDTOs;
	}

	public static YdQueryCreditLimitResponse convertYundaexCreditLimit2DTO(CreditLimit yundaexCreditLimit) {
		YdQueryCreditLimitResponse ydQueryCreditLimitResponse = new YdQueryCreditLimitResponse();
		ydQueryCreditLimitResponse.setStatus(yundaexCreditLimit.getStatus());
		ydQueryCreditLimitResponse.setTotalLimit(yundaexCreditLimit.getTotalLimit());
		long usedLimit = yundaexCreditLimit.getUsedLimit() == null ? 0l : yundaexCreditLimit.getUsedLimit();
		ydQueryCreditLimitResponse.setUnusedLimit(yundaexCreditLimit.getTotalLimit() - usedLimit);
		return ydQueryCreditLimitResponse;
	}

	public static List<YundaexCreditListMinDTO> convertCustomerApply2CustomerApplyMinDTO(
			List<YundaexCustomerApply> ydCustomerApplys) {

		List<YundaexCreditListMinDTO> creditList = new ArrayList<>();
		Iterator<YundaexCustomerApply> customerApplyIterator = ydCustomerApplys.iterator();
		while (customerApplyIterator.hasNext()) {
			YundaexCustomerApply customerApply = customerApplyIterator.next();
			YundaexCreditListMinDTO creditListMinDTO = new YundaexCreditListMinDTO();
			creditListMinDTO.setId(customerApply.getId());
			creditListMinDTO.setStationNo(customerApply.getStationNo());
			String creditLimitGenerateState = customerApply.getCreditLimitGenerateState();
			String gradeState = customerApply.getGradeState(); 
			String accessManualState = customerApply.getAccessManualState(); 
			if(YundaexCreditLimitGenerateState.FINISH.name().equals(creditLimitGenerateState)) {
				creditListMinDTO.setCreditLimitGenerateState(creditLimitGenerateState);
				creditListMinDTO.setCreditLimitGenerateStateDesc(YundaexCreditLimitGenerateState.valueOf(creditLimitGenerateState).desc());
			}else if(YundaexAuditState.WAIT.name().equals(creditLimitGenerateState) || YundaexAuditState.WAIT.name().equals(gradeState) || YundaexAuditState.WAIT.name().equals(accessManualState)){
				creditListMinDTO.setCreditLimitGenerateState(YundaexAuditState.WAIT.name());
				creditListMinDTO.setCreditLimitGenerateStateDesc(YundaexAuditState.WAIT.desc());
			}else{
				creditListMinDTO.setCreditLimitGenerateState(YundaexCreditLimitGenerateState.UNFINISH.name());
				creditListMinDTO.setCreditLimitGenerateStateDesc(YundaexCreditLimitGenerateState.UNFINISH.desc());
			}
			creditListMinDTO.setBailRatio(customerApply.getBailRatio()==null?new BigDecimal(0):customerApply.getBailRatio().multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
			creditListMinDTO.setAuditTime(customerApply.getAuditTime());
			creditListMinDTO.setCompanyName(customerApply.getStationName());
			
			creditList.add(creditListMinDTO);
		}
		return creditList;
	}

}
