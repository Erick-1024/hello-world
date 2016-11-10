package com.cana.credit.service.convertors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.cana.credit.dao.po.OutCustomer;
import com.cana.vbam.common.credit.dto.outcustomer.OutCustomerDTO;

public class OutCustomerConvertor {

	public static Map<String, Map<String, String>> convertOutCustomer2Map(List<OutCustomer> outCustomers) {
		Map<String, Map<String, String>> returnValue = new HashMap<>();
		for (OutCustomer outCustomer : outCustomers) {
			String institutionId = outCustomer.getInstitutionId();
			String outCustomerId = outCustomer.getOutCustomerId();
			String memberId = outCustomer.getMemberId();
			if(returnValue.containsKey(institutionId))
				returnValue.get(institutionId).put(outCustomerId, memberId);
			else {
				Map<String, String> innerMap = new HashMap<>();
				innerMap.put(outCustomerId, memberId);
				returnValue.put(institutionId, innerMap);
			}
		}
		return returnValue;
	}

	public static List<OutCustomerDTO> convertOutCustomer2OutCustomerDTO(List<OutCustomer> outCustomers) {
		List<OutCustomerDTO> returnValue = new ArrayList<>();
		for (OutCustomer outCustomer : outCustomers) {
			OutCustomerDTO outCustomerDTO = new OutCustomerDTO();
			BeanUtils.copyProperties(outCustomer, outCustomerDTO);
			returnValue.add(outCustomerDTO);
		}
		return returnValue;
	}
	
}
