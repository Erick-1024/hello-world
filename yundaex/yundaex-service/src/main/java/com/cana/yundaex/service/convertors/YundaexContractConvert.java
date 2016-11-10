package com.cana.yundaex.service.convertors;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.cana.yundaex.common.dto.contract.ContractSituationResultDTO;
import com.cana.yundaex.common.enums.ContractCompleteState;
import com.cana.yundaex.dao.po.ContractSignSituation;
import com.google.common.collect.Lists;

/**
 * @author hu
 *
 */
@Component
public class YundaexContractConvert {

	public ContractSituationResultDTO convertPersonalInfo2DTO(ContractSignSituation contractSignSituation){
		ContractSituationResultDTO resultDTO = new ContractSituationResultDTO();
		BeanUtils.copyProperties(contractSignSituation, resultDTO);
		
//		if(StringUtils.isNotBlank(contractSignSituation.getFinanceContractSignState()))
//			resultDTO.setFinanceContractSignState(ContractSignState.valueOf(contractSignSituation.getFinanceContractSignState()).desc());
//		if(StringUtils.isNotBlank(contractSignSituation.getCreditContractSignState()))
//			resultDTO.setCreditContractSignState(ContractSignState.valueOf(contractSignSituation.getCreditContractSignState()).desc());
//		if(StringUtils.isNotBlank(contractSignSituation.getDutyContractSignState()))
//			resultDTO.setDutyContractSignState(ContractSignState.valueOf(contractSignSituation.getDutyContractSignState()).desc());
//		
		if(null != contractSignSituation.getSignCompleteTime()){
			resultDTO.setCompleteState(ContractCompleteState.COMPLETE.name());
		}else{
			resultDTO.setCompleteState(ContractCompleteState.UNCOMPLETE.name());
		}
		return resultDTO;
	}
	
	public List<ContractSituationResultDTO> convertContractSituationList2DTO(List<ContractSignSituation> contractSignSituationList){
		List<ContractSituationResultDTO> resultDTOList = Lists.newArrayList();
		for(ContractSignSituation contractSignSituation : contractSignSituationList){
			resultDTOList.add(convertPersonalInfo2DTO(contractSignSituation));
		}
		return resultDTOList;
	}
}
