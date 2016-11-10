package com.cana.credit.service.convertors;

import java.util.ArrayList;
import java.util.List;

import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitDTO;
import com.cana.vbam.common.credit.dto.limit.QueryCreditLimitResponse;

public class CreditLimitConvertor {

    public static QueryCreditLimitResponse convertCreditLimitBalanceDao2Dto(CreditLimit creditLimit){
        QueryCreditLimitResponse queryCreditLimitResponse=new QueryCreditLimitResponse();
        queryCreditLimitResponse.setStatus(creditLimit.getStatus());
        queryCreditLimitResponse.setTotalLimit(creditLimit.getTotalLimit());
        long usedLimit = creditLimit.getUsedLimit() == null ? 0l : creditLimit.getUsedLimit();
        queryCreditLimitResponse.setUnusedLimit(creditLimit.getTotalLimit() - usedLimit);
        return queryCreditLimitResponse;
    }
    
    public static List<CreditUsedLimitDTO> convertCreditUsedLimitDTO(List<CreditLimit> creditLimits){
    	List<CreditUsedLimitDTO> creditUsedLimitDTOs = new ArrayList<CreditUsedLimitDTO>();
    	for(CreditLimit creditLimit : creditLimits){
    		CreditUsedLimitDTO usedLimitDTO = new CreditUsedLimitDTO();
    		usedLimitDTO.setMemberId(creditLimit.getMemberId());
    		usedLimitDTO.setUsedLimit(creditLimit.getUsedLimit());
    		usedLimitDTO.setProductId(creditLimit.getProjectId());
    		usedLimitDTO.setCompanyName(creditLimit.getCompanyName());
    		creditUsedLimitDTOs.add(usedLimitDTO);
    	}
    	return creditUsedLimitDTOs;
    }

}
