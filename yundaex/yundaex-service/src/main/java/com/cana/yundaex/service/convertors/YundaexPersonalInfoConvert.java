package com.cana.yundaex.service.convertors;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyDetailDTO;
import com.cana.yundaex.common.dto.personalinfo.PersonalInfoResultDTO;
import com.cana.yundaex.common.enums.PersonalInfoAuditStatus;
import com.cana.yundaex.common.enums.PersonalInfoType;
import com.cana.yundaex.common.enums.YundaexAccountOwner;
import com.cana.yundaex.dao.po.YundaexPersonalInfo;
import com.cana.yundaex.service.utils.YundaexIDGenerator;
import com.google.common.collect.Lists;

/**
 * @author hu
 *
 */
@Component
public class YundaexPersonalInfoConvert {

	public PersonalInfoResultDTO convertPersonalInfo2DTO(YundaexPersonalInfo personalInfo){
		PersonalInfoResultDTO resultDTO = new PersonalInfoResultDTO();
		BeanUtils.copyProperties(personalInfo, resultDTO);
		if(StringUtils.isNotBlank(personalInfo.getAuditState()))
			resultDTO.setAuditStateDesc(PersonalInfoAuditStatus.valueOf(personalInfo.getAuditState()).desc());
		return resultDTO;
	}
	
	public List<PersonalInfoResultDTO> convertPersonalInfoList2DTO(List<YundaexPersonalInfo> personalInfoList){
		List<PersonalInfoResultDTO> resultDTOList = Lists.newArrayList();
		for(YundaexPersonalInfo personalInfo : personalInfoList){
			resultDTOList.add(convertPersonalInfo2DTO(personalInfo));
		}
		return resultDTOList;
	}
	
	public List<YundaexPersonalInfo> convertCusApply2Po(YdCustomerApplyDetailDTO detailDTO,String customerId){
		 List<YundaexPersonalInfo> infoList = Lists.newArrayList();
		YundaexPersonalInfo personalInfo = new YundaexPersonalInfo();
		if("0".equals(detailDTO.getControllerIsLegal())){
			personalInfo.setId(YundaexIDGenerator.generatePersonalInfoId());
			personalInfo.setCreateTime(new Date());
			personalInfo.setStationName(detailDTO.getStationName());
			personalInfo.setRealName(detailDTO.getController());
			personalInfo.setMail(detailDTO.getControllerEmail());
			personalInfo.setCellphone(detailDTO.getControllerPhone());
			personalInfo.setType(PersonalInfoType.CONTROLLER.name());
			personalInfo.setRelatedCustomerId(customerId);

			infoList.add(personalInfo);
		}
		switch (YundaexAccountOwner.valueOf(detailDTO.getAccountOwner())) {
		case CONTROLLER:
			if("0".equals(detailDTO.getControllerIsLegal())){
				personalInfo.setType(PersonalInfoType.ACCOUNT_HOLDER_AND_CONTROLLER.name());
				break;
			}
		case OTHER:
		case LEGAL:
			YundaexPersonalInfo bankPersonalInfo = new YundaexPersonalInfo();
			bankPersonalInfo.setId(YundaexIDGenerator.generatePersonalInfoId());
			bankPersonalInfo.setCreateTime(new Date());
			bankPersonalInfo.setStationName(detailDTO.getStationName());
			bankPersonalInfo.setRealName(detailDTO.getAccountOwnerName());
			bankPersonalInfo.setMail(detailDTO.getAccountOwnerEmail());
			bankPersonalInfo.setCellphone(detailDTO.getAccountOwnerPhone());
			bankPersonalInfo.setType(PersonalInfoType.ACCOUNT_HOLDER.name());
			bankPersonalInfo.setRelatedCustomerId(customerId);
			infoList.add(bankPersonalInfo);
			break;
		default:
			break;
		}
		return infoList;
	}
}
