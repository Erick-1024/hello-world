package com.cana.credit.service.convertors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.credit.dao.po.CustomerApply;
import com.cana.flight.finance.common.dto.CustomerSaleDataVO;
import com.cana.vbam.common.credit.dto.apply.AutomaticAuditDataDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyDetailDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyMinDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyRequestDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerSaleDataDTO;
import com.cana.vbam.common.credit.enums.AccessAutomaticState;
import com.cana.vbam.common.credit.enums.AccessManualState;
import com.cana.vbam.common.credit.enums.AccountPeriodType;
import com.cana.vbam.common.credit.enums.ApplyApplicantType;
import com.cana.vbam.common.credit.enums.ApplyCreditLimitType;
import com.cana.vbam.common.credit.enums.DownstreamCustomerType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.util.DateUtils;
import com.travelzen.tops.mediaserver.client.MediaClientUtil;


public class CustomerApplyConvertor {
	private static Logger logger = LoggerFactory.getLogger(CustomerApplyConvertor.class);

	public static void convertCustomerApplyDTO2DAO(CustomerApplyRequestDTO customerApplyDTO, CustomerApply customerApply) throws  Exception {
			logger.info("AuditDTO2DAO转换,customerId:[{}]", customerApplyDTO.getCustomerId());
			checkApplyData(customerApplyDTO);
			BeanUtils.copyProperties(customerApply, customerApplyDTO);
			customerApply.setTzCustomerId(customerApplyDTO.getCustomerId());
			String oriMediaId = uploadCertificates(customerApplyDTO.getOrganizationCode());
			String busMediaId = uploadCertificates(customerApplyDTO.getBusinessLicenceCode());
			String taxMediaId = uploadCertificates(customerApplyDTO.getTaxRegistrationCertificateCode());
			if(StringUtils.isNotBlank(customerApplyDTO.getRealControlPersonIdHandheldFrontCode()))
				customerApply.setRealControlPersonIdHandheldFrontMediaId(uploadCertificates(customerApplyDTO.getRealControlPersonIdHandheldFrontCode()));
			if(StringUtils.isNotBlank(customerApplyDTO.getLegalPersonIdHandheldFrontCode()))
				customerApply.setLegalPersonIdHandheldFrontMediaId(uploadCertificates(customerApplyDTO.getLegalPersonIdHandheldFrontCode()));
			Date applyDate = DateTimeUtil.parseDate(DateTimeUtil.DATE_TIME_PATTERN, customerApplyDTO.getApplyTime()).toDate();
			customerApply.setApplyDate(applyDate);
			customerApply.setTzCustomerName(customerApplyDTO.getCustomerName());
			customerApply.setOrganizationNo(customerApplyDTO.getOrganizationNo());
			customerApply.setOrganizationMediaId(oriMediaId);
			customerApply.setBusinessLicenceNo(customerApplyDTO.getBusinessLicenceNo());
			customerApply.setBusinessLicenceMediaId(busMediaId);
			customerApply.setTaxRegistrationCertificateNo(customerApplyDTO.getTaxRegistrationCertificateNo());
			customerApply.setTaxRegistrationCertificateMediaId(taxMediaId);
			Date upDate = DateUtils.getDate(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
			customerApply.setCreateTime(upDate);
			customerApply.setUpdateTime(upDate);
	}

	public static List<CustomerApplyMinDTO> convertCustomerApply2CustomerApplyMinDTO(List<CustomerApply> customerApplys) {
		List<CustomerApplyMinDTO> customerApplyMinDTOs = new ArrayList<>();
		Iterator<CustomerApply> customerApplyIterator = customerApplys.iterator();
		while (customerApplyIterator.hasNext()) {
			CustomerApply customerApply = customerApplyIterator.next();
			CustomerApplyMinDTO customerApplyMinDTO = new CustomerApplyMinDTO();
			String auditState = customerApply.getAccessManualState();
			String automaticState = customerApply.getAccessAutomaticState();
			customerApplyMinDTO.setApplyDate(customerApply.getApplyDate());
			if(StringUtils.isNotBlank(auditState)) {
				customerApplyMinDTO.setAuditState(auditState);
				customerApplyMinDTO.setAuditStateDesc(AccessManualState.valueOf(auditState).desc());
			}
			if(StringUtils.isNotBlank(automaticState)){
				customerApplyMinDTO.setAutomaticState(automaticState);
				customerApplyMinDTO.setAutomaticStateDesc(AccessAutomaticState.valueOf(automaticState).desc());
			}
			customerApplyMinDTO.setCompanyName(customerApply.getCompanyName());
			customerApplyMinDTO.setOutCustomerName(customerApply.getTzCustomerName());
			customerApplyMinDTO.setId(customerApply.getId());
			customerApplyMinDTO.setInWhitelist(customerApply.getInWhitelist());
			if(StringUtils.isNotBlank(customerApply.getApplicantType())){
				customerApplyMinDTO.setApplicantType(customerApply.getApplicantType());
				customerApplyMinDTO.setApplicantTypeDesc(ApplyApplicantType.valueOf(customerApply.getApplicantType()).desc());
			}
			customerApplyMinDTOs.add(customerApplyMinDTO);
		}
		return customerApplyMinDTOs;
	}

	public static CustomerApplyDetailDTO convertCustomerApply2CustomerApplyDetailDTO(CustomerApply customerApply) {
		String accessManualState = customerApply.getAccessManualState();
		String accessAutomaticState = customerApply.getAccessAutomaticState();
		String applyType = customerApply.getApplyType();
		Integer consistencyCheck = customerApply.getConsistencyCheck();
		String downstreamCustomerType = customerApply.getDownstreamCustomerType();
		String downstreamRepaymentAccountPeriod = customerApply.getDownstreamRepaymentAccountPeriod();
		Long enterpriseExecutionMoney = customerApply.getEnterpriseExecutionMoney();
		Long individualExecutionMoney = customerApply.getIndividualExecutionMoney();
		Boolean inWhitelist = customerApply.getInWhitelist();
		CustomerApplyDetailDTO customerApplyDetailDTO = new CustomerApplyDetailDTO();
		customerApplyDetailDTO.setTzCustomerId(customerApply.getTzCustomerId());
		if(StringUtils.isNotBlank(accessManualState)) {
			customerApplyDetailDTO.setAccessManualState(accessManualState);
			customerApplyDetailDTO.setAccessManualStateDesc(AccessManualState.valueOf(accessManualState).desc());
		}
		if(StringUtils.isNotBlank(accessAutomaticState)) {
			customerApplyDetailDTO.setAccessAutomaticState(accessAutomaticState);
			customerApplyDetailDTO.setAccessAutomaticStateDesc(AccessAutomaticState.valueOf(accessAutomaticState).desc());
		}
		customerApplyDetailDTO.setApplyCreditLimit(MoneyUtil.cent2Yuan(customerApply.getApplyCreditLimit()));
		customerApplyDetailDTO.setApplyDate(DateFormatUtils.format(customerApply.getApplyDate(), "yyyy-MM-dd"));
		customerApplyDetailDTO.setApplyType(applyType);
		customerApplyDetailDTO.setApplyTypeDesc(ApplyCreditLimitType.valueOf(applyType).desc());
		if(consistencyCheck != null)
			customerApplyDetailDTO.setBusinessInfo((consistencyCheck & 1) == 1 ? "真实有效" : "虚假材料");
		customerApplyDetailDTO.setApplicantTypeDesc(ApplyApplicantType.getDesc(customerApply.getApplicantType()));
		customerApplyDetailDTO.setBusinessLicenceNo(customerApply.getBusinessLicenceNo());
		customerApplyDetailDTO.setBusinessLicenceMediaId(customerApply.getBusinessLicenceMediaId());
		customerApplyDetailDTO.setCompanyName(customerApply.getCompanyName());
		customerApplyDetailDTO.setContactName(customerApply.getContactName());
		customerApplyDetailDTO.setInWhitelist(inWhitelist);
		List<CustomerSaleDataDTO> saleDatas = new ArrayList<>();
		if(StringUtils.isNotBlank(customerApply.getSaleData()))
			saleDatas = convertSaleDatas(customerApply.getSaleData());
		customerApplyDetailDTO.setSaleDatas(saleDatas);
		if(StringUtils.isNotBlank(customerApply.getAutomaticAuditData()))
			customerApplyDetailDTO.setAutomaticAuditData(new Gson().fromJson(customerApply.getAutomaticAuditData(), AutomaticAuditDataDTO.class));
		if(StringUtils.isNotBlank(customerApply.getAutomaticAuditRemarks()))
			customerApplyDetailDTO.setAutomaticAuditRemarkList(Arrays.asList(customerApply.getAutomaticAuditRemarks().split("\\|")));
//		if(firstBusinessTime != null)
//			customerApplyDetailDTO.setCooperationPeriod(CreditDateUtil.calculatePeriodMonth(firstBusinessTime));
		customerApplyDetailDTO.setDownstreamCustomerType(downstreamCustomerType);
		if(StringUtils.isNotBlank(downstreamCustomerType))
			customerApplyDetailDTO.setDownstreamCustomerTypeDesc(DownstreamCustomerType.valueOf(downstreamCustomerType).desc());
		if(StringUtils.isBlank(downstreamRepaymentAccountPeriod))
			customerApplyDetailDTO.setDownstreamRepaymentAccountPeriod("无账期");
		else
			customerApplyDetailDTO.setDownstreamRepaymentAccountPeriod(AccountPeriodType.valueOf(downstreamRepaymentAccountPeriod).desc());
		customerApplyDetailDTO.setEmail(customerApply.getEmail());
		if(enterpriseExecutionMoney != null)
			customerApplyDetailDTO.setEnterpriseExecutionMoney(MoneyUtil.cent2Yuan(enterpriseExecutionMoney));
		customerApplyDetailDTO.setEnterpriseExecutionTimes(customerApply.getEnterpriseExecutionTimes());
		customerApplyDetailDTO.setId(customerApply.getId());
		if(individualExecutionMoney != null)
			customerApplyDetailDTO.setIndividualExecutionMoney(MoneyUtil.cent2Yuan(individualExecutionMoney));
		customerApplyDetailDTO.setIndividualExecutionTimes(customerApply.getIndividualExecutionTimes());
		customerApplyDetailDTO.setManualAuditRemarks(customerApply.getManualAuditRemarks());
		customerApplyDetailDTO.setNegativeNetwork(customerApply.getNegativeNetwork());
		customerApplyDetailDTO.setOrganizationNo(customerApply.getOrganizationNo());
		customerApplyDetailDTO.setOrganizationMediaId(customerApply.getOrganizationMediaId());
		customerApplyDetailDTO.setPhoneNumber(customerApply.getPhoneNumber());
		customerApplyDetailDTO.setRealControlPerson(customerApply.getRealControlPerson());
		customerApplyDetailDTO.setTaxRegistrationCertificateNo(customerApply.getTaxRegistrationCertificateNo());
		customerApplyDetailDTO.setTaxRegistrationCertificateMediaId(customerApply.getTaxRegistrationCertificateMediaId());
		customerApplyDetailDTO.setLegalPerson(customerApply.getLegalPerson());
		String realControlPersonId = customerApply.getRealControlPersonId();
		String legalPersonId = customerApply.getLegalPersonId();
		customerApplyDetailDTO.setRealControlPersonId(realControlPersonId);
		customerApplyDetailDTO.setRealControlPersonIdHandheldFrontMediaId(customerApply.getRealControlPersonIdHandheldFrontMediaId());
		customerApplyDetailDTO.setLegalPersonId(legalPersonId);
		customerApplyDetailDTO.setLegalPersonIdHandheldFrontMediaId(customerApply.getLegalPersonIdHandheldFrontMediaId());
		if(StringUtils.isNotBlank(realControlPersonId) && StringUtils.isNotBlank(legalPersonId)){
			if(StringUtils.equals(realControlPersonId, legalPersonId))
				customerApplyDetailDTO.setSamePersonOfLegalAndRealControl(Boolean.TRUE);
			else
				customerApplyDetailDTO.setSamePersonOfLegalAndRealControl(Boolean.FALSE);
		}
		return customerApplyDetailDTO;
	}
	
	/**
	 * 检查传入的字段信息是否为空
	 */
	public static void checkApplyData(CustomerApplyRequestDTO customerApplyDTO) throws Exception {
		if (!DateTimeUtil.isValdateDate(customerApplyDTO.getApplyTime(), 8))
			throw WebException.instance(ReturnCode.TP1100);
		if (StringUtils.isBlank(customerApplyDTO.getCustomerId()))
			throw WebException.instance(ReturnCode.TP1101);
		if (StringUtils.isBlank(customerApplyDTO.getCompanyName()))
			throw WebException.instance(ReturnCode.TP1102);
		if (StringUtils.isBlank(customerApplyDTO.getRealControlPerson()))
			throw WebException.instance(ReturnCode.TP1103);
		if (StringUtils.isBlank(String.valueOf(customerApplyDTO.getApplyCreditLimit())))
			throw WebException.instance(ReturnCode.TP1104);
		if (!EnumUtils.isValidEnum(ApplyCreditLimitType.class, customerApplyDTO.getApplyType()))
			throw WebException.instance(ReturnCode.TP1105);
		if (StringUtils.isBlank(customerApplyDTO.getOrganizationNo()))
			throw WebException.instance(ReturnCode.TP1134);
		if (StringUtils.isBlank(customerApplyDTO.getOrganizationCode()))
			throw WebException.instance(ReturnCode.TP1112);
		if (StringUtils.isBlank(customerApplyDTO.getBusinessLicenceNo()))
			throw WebException.instance(ReturnCode.TP1135);
		if (StringUtils.isBlank(customerApplyDTO.getBusinessLicenceCode()))
			throw WebException.instance(ReturnCode.TP1113);
		if (StringUtils.isBlank(customerApplyDTO.getTaxRegistrationCertificateNo()))
			throw WebException.instance(ReturnCode.TP1136);
		if (StringUtils.isBlank(customerApplyDTO.getTaxRegistrationCertificateCode()))
			throw WebException.instance(ReturnCode.TP1114);
		if (StringUtils.isBlank(customerApplyDTO.getContactName()))
			throw WebException.instance(ReturnCode.TP1120);
		if (StringUtils.isBlank(customerApplyDTO.getPhoneNumber()))
			throw WebException.instance(ReturnCode.TP1121);
		if (StringUtils.isBlank(customerApplyDTO.getEmail()))
			throw WebException.instance(ReturnCode.TP1122);
		if (customerApplyDTO.getDownstreamCustomerType() != null && !EnumUtils.isValidEnum(DownstreamCustomerType.class, customerApplyDTO.getDownstreamCustomerType()))
			throw WebException.instance(ReturnCode.TP1125);
		if(!checkDownstreamRepaymentAccountPeriod(customerApplyDTO.getDownstreamRepaymentAccountPeriod()))
			throw WebException.instance(ReturnCode.TP1133);
//		if(StringUtils.isBlank(customerApplyDTO.getApplicantType()))//TODO 以后去掉注释
//		throw WebException.instance(ReturnCode.TP1147);//TODO 以后去掉注释
		if(StringUtils.isNotBlank(customerApplyDTO.getApplicantType())){//TODO 以后去掉这一行
			if(!EnumUtils.isValidEnum(ApplyApplicantType.class, customerApplyDTO.getApplicantType()))
				throw WebException.instance(ReturnCode.TP1140);
			if(ApplyApplicantType.individual == ApplyApplicantType.valueOf(customerApplyDTO.getApplicantType()) && (!StringUtils.equals(customerApplyDTO.getOrganizationNo(), customerApplyDTO.getBusinessLicenceNo()) || !StringUtils.equals(customerApplyDTO.getOrganizationNo(), customerApplyDTO.getTaxRegistrationCertificateNo())))
				throw WebException.instance(ReturnCode.TP1141);
			if(ApplyApplicantType.company == ApplyApplicantType.valueOf(customerApplyDTO.getApplicantType())){
				if(StringUtils.isBlank(customerApplyDTO.getRealControlPersonId()))
					throw WebException.instance(ReturnCode.TP1148);
				if(StringUtils.isBlank(customerApplyDTO.getRealControlPersonIdHandheldFrontCode()))
					throw WebException.instance(ReturnCode.TP1149);
				if(StringUtils.isBlank(customerApplyDTO.getLegalPersonId()))
					throw WebException.instance(ReturnCode.TP1150);
				if(StringUtils.isBlank(customerApplyDTO.getLegalPersonIdHandheldFrontCode()))
					throw WebException.instance(ReturnCode.TP1151);
			}
		}//TODO 以后去掉这一行
//		if(StringUtils.isBlank(customerApplyDTO.getAuditNotifyUrl()))
//			throw WebException.instance(ReturnCode.TP1142);
//		if(StringUtils.isBlank(customerApplyDTO.getLimitNotifyUrl()))
//			throw WebException.instance(ReturnCode.TP1143);
		String idCardRegular = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
		if(StringUtils.isNotBlank(customerApplyDTO.getRealControlPersonId()) && !regular(idCardRegular, customerApplyDTO.getRealControlPersonId()))
			throw WebException.instance(ReturnCode.TP1144);
		if(StringUtils.isNotBlank(customerApplyDTO.getLegalPersonId()) && !regular(idCardRegular, customerApplyDTO.getLegalPersonId()))
			throw WebException.instance(ReturnCode.TP1145);
			
	}
	
	private static String uploadCertificates(String code) throws IOException, Exception {
		String mediaId = "";
		byte[] codeByte = Base64.decodeBase64(code);
		mediaId = MediaClientUtil.upload(codeByte);
		return mediaId;
	}
	
//   private static boolean cooperationDegreeIsCorrect(int degree){
//	   return degree==1||degree==0||degree==2;
//   }
   
   private static boolean regular(String exp ,String con){
	   Pattern patter=Pattern.compile(exp);
	   Matcher mat=patter.matcher(con);
	   return mat.matches();
   }
   
   /**
	 * 检查客户的下游回款账期是否符合要求
	 * 必须是无账期 or 有账期，值为AccountPeriodType中的一个
	 * @param downstreamRepaymentAccountPeriod
	 * @return true 通过要求，false 不通过要求
	 */
	private static boolean checkDownstreamRepaymentAccountPeriod(String downstreamRepaymentAccountPeriod){
		return StringUtils.isEmpty(downstreamRepaymentAccountPeriod) || EnumUtils.isValidEnum(AccountPeriodType.class, downstreamRepaymentAccountPeriod);
	}
	
	private static List<CustomerSaleDataDTO> convertSaleDatas(String saleDatas){
		List<CustomerSaleDataVO> customerApplySaleDataVOs = new Gson().fromJson(saleDatas, new TypeToken<List<CustomerSaleDataVO>>(){}.getType());
		if(CollectionUtils.isEmpty(customerApplySaleDataVOs))
			return null;
		
		List<CustomerSaleDataDTO> customerApplySaleDataDTOs = new ArrayList<>();
		for(CustomerSaleDataVO customerApplySaleDataVO : customerApplySaleDataVOs){
			CustomerSaleDataDTO customerApplySaleDataDTO = new CustomerSaleDataDTO();
			customerApplySaleDataDTO.setYear(customerApplySaleDataVO.getYear());
			customerApplySaleDataDTO.setMonth(customerApplySaleDataVO.getMonth());
			customerApplySaleDataDTO.setSaleMoneyStr(MoneyUtil.cent2Yuan(customerApplySaleDataVO.getSaleMoney()));
			customerApplySaleDataDTOs.add(customerApplySaleDataDTO);
		}
		return customerApplySaleDataDTOs;
	}
}
