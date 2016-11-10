package com.cana.yundaex.service.utils;

import org.apache.commons.lang3.StringUtils;

import com.cana.yundaex.common.dto.apply.YdCustomerApplyAddRequestDTO;
import com.cana.yundaex.common.enums.YundaexAccountOwner;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.ValidationUtil;

public class ValidatorUtils {

	/**
	 * 补充资料 数据校验
	 * @param ydCustomerApplyAddRequestDTO
	 */
	public static void checkAdditionInfo(YdCustomerApplyAddRequestDTO ydCustomerApplyAddRequestDTO) {
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getStationNo()))
			throw WebException.instance(ReturnCode.YP5200);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getPayAccount()))
			throw WebException.instance(ReturnCode.YP5201);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getPayAccountName()))
			throw WebException.instance(ReturnCode.YP5202);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getPayAccountAddress()))
			throw WebException.instance(ReturnCode.YP5203);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getYundaexJudge()))
			throw WebException.instance(ReturnCode.YP5204);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getCustEmail()))
			throw WebException.instance(ReturnCode.YP5205);
		else{
			if(!ValidationUtil.isValidatePattern("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", ydCustomerApplyAddRequestDTO.getCustEmail()))
				throw WebException.instance(ReturnCode.YP5206);
		}
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getController()))
			throw WebException.instance(ReturnCode.YP5207);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getControllerOrigin()))
			throw WebException.instance(ReturnCode.YP5208);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getControllerIdno()))
			throw WebException.instance(ReturnCode.YP5209);
		else{
			if(!ValidationUtil.isValidatePattern("^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$", ydCustomerApplyAddRequestDTO.getControllerIdno()))
				throw WebException.instance(ReturnCode.YP5210);
		}
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getControllerEmail()))
			throw WebException.instance(ReturnCode.YP5211);
		else{
			if(!ValidationUtil.isValidatePattern("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", ydCustomerApplyAddRequestDTO.getControllerEmail()))
				throw WebException.instance(ReturnCode.YP5212);
		}
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getControllerPhone()))
			throw WebException.instance(ReturnCode.YP5213);
		else{
			if(!ValidationUtil.isValidatePattern("^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$", ydCustomerApplyAddRequestDTO.getControllerPhone()))
				throw WebException.instance(ReturnCode.YP5214);
		}
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getControllerIsLegal()))
			throw WebException.instance(ReturnCode.YP5215);
		else{
			if("0".equals(ydCustomerApplyAddRequestDTO.getControllerIsLegal())){
				//验证法人代表信息
				if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getLegalPerson()))
					throw WebException.instance(ReturnCode.YP5216);
				if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getLegalEmail()))
					throw WebException.instance(ReturnCode.YP5217);
				else{
					if(!ValidationUtil.isValidatePattern("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", ydCustomerApplyAddRequestDTO.getLegalEmail()))
						throw WebException.instance(ReturnCode.YP5218);
				}
				if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getLegalPhone()))
					throw WebException.instance(ReturnCode.YP5219);
				else{
					if(!ValidationUtil.isValidatePattern("^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$", ydCustomerApplyAddRequestDTO.getLegalPhone()))
						throw WebException.instance(ReturnCode.YP5220);
				}
			}
		}
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getAccountOwner()))
			throw WebException.instance(ReturnCode.YP5221);
		else{
			if(YundaexAccountOwner.OTHER.name().equals(ydCustomerApplyAddRequestDTO.getAccountOwner())){
				//验证开户人
				if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getAccountOwnerName()))
					throw WebException.instance(ReturnCode.YP5222);
				if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getAccountOwnerEmail()))
					throw WebException.instance(ReturnCode.YP5223);
				else{
					if(!ValidationUtil.isValidatePattern("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", ydCustomerApplyAddRequestDTO.getAccountOwnerEmail()))
						throw WebException.instance(ReturnCode.YP5224);
				}
				if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getAccountOwnerPhone()))
					throw WebException.instance(ReturnCode.YP5225);
				else{
					if(!ValidationUtil.isValidatePattern("^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$", ydCustomerApplyAddRequestDTO.getAccountOwnerPhone()))
						throw WebException.instance(ReturnCode.YP5226);
				}
			}
		}
		if(ydCustomerApplyAddRequestDTO.getStationAmount() == null)
			throw WebException.instance(ReturnCode.YP5227);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getLoanType()))
			throw WebException.instance(ReturnCode.YP5228);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getAgentQualification()))
			throw WebException.instance(ReturnCode.YP5229);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getWhetherTbOrder()))
			throw WebException.instance(ReturnCode.YP5230);
		else{
			if("1".equals(ydCustomerApplyAddRequestDTO.getWhetherTbOrder())){
				if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getTbOrderRatio()))
					throw WebException.instance(ReturnCode.YP5231);
			}
		}
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getOrganizationNo()))
			throw WebException.instance(ReturnCode.YP5232);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getOrganizationMediaId()))
			throw WebException.instance(ReturnCode.YP5233);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getBusinessLicenceNo()))
			throw WebException.instance(ReturnCode.YP5234);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getBusinessLicenceMediaId()))
			throw WebException.instance(ReturnCode.YP5235);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getTaxRegistrationCertificateNo()))
			throw WebException.instance(ReturnCode.YP5236);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getTaxRegistrationCertificateMediaId()))
			throw WebException.instance(ReturnCode.YP5237);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getLegalIdno()))
			throw WebException.instance(ReturnCode.YP5238);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getLegalIdnoFrontMediaId()))
			throw WebException.instance(ReturnCode.YP5239);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getLegalIdnoBackMediaId()))
			throw WebException.instance(ReturnCode.YP5240);
		if(StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getAdditionInformationMediaId()))
			throw WebException.instance(ReturnCode.YP5240);
	}

}
