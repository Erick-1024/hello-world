package com.cana.yundaex.service.convertors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyDetailDTO;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyMinDTO;
import com.cana.yundaex.common.dto.apply.YdCustomerApplyAddRequestDTO;
import com.cana.yundaex.common.dto.apply.YdCustomerApplyRequestDTO;
import com.cana.yundaex.common.enums.YundaexAccountOwner;
import com.cana.yundaex.common.enums.YundaexAuditState;
import com.cana.yundaex.common.enums.YundaexInspectionRecord;
import com.cana.yundaex.common.enums.YundaexJudge;
import com.cana.yundaex.common.enums.YundaexLoanType;
import com.cana.yundaex.common.enums.YundaexStationAddress;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.util.DateUtils;

public class YundaexCustomerApplyConvert {
	
	private static Logger logger = LoggerFactory.getLogger(YundaexCustomerApplyConvert.class);

	public static List<YdCustomerApplyMinDTO> convertCustomerApply2CustomerApplyMinDTO(List<YundaexCustomerApply> ydCustomerApplys) {
		List<YdCustomerApplyMinDTO> customerApplyMinDTOs = new ArrayList<>();
		Iterator<YundaexCustomerApply> customerApplyIterator = ydCustomerApplys.iterator();
		while (customerApplyIterator.hasNext()) {
			YundaexCustomerApply customerApply = customerApplyIterator.next();
			YdCustomerApplyMinDTO customerApplyMinDTO = new YdCustomerApplyMinDTO();
			String auditState = customerApply.getAccessManualState();
			customerApplyMinDTO.setApplyDate(customerApply.getApplyDate());
			if(StringUtils.isNotBlank(auditState)) {
				customerApplyMinDTO.setAuditState(auditState);
				customerApplyMinDTO.setAuditStateDesc(YundaexAuditState.valueOf(auditState).desc());
			}
			customerApplyMinDTO.setCompanyName(customerApply.getStationName());	
			customerApplyMinDTO.setId(customerApply.getId());
			customerApplyMinDTOs.add(customerApplyMinDTO);
		}
		return customerApplyMinDTOs;
	}

	//详情页　
	public static YdCustomerApplyDetailDTO convertCustomerApply2CustomerApplyDetailDTO(YundaexCustomerApply customerApply) {
		
		YdCustomerApplyDetailDTO customerApplyDetailDTO = new YdCustomerApplyDetailDTO();
		BeanUtils.copyProperties(customerApply, customerApplyDetailDTO);

		if(StringUtils.isNotBlank(customerApply.getAccountOwner()))
			customerApplyDetailDTO.setAccountOwnerDesc(YundaexAccountOwner.valueOf(customerApply.getAccountOwner()).desc());
		if(StringUtils.isNotBlank(customerApply.getLoanType()))
			customerApplyDetailDTO.setLoanTypeDesc(YundaexLoanType.valueOf(customerApply.getLoanType()).desc());
		if(StringUtils.isNotBlank(customerApply.getStationAddress()))
			customerApplyDetailDTO.setStationAddressDesc(YundaexStationAddress.valueOf(customerApply.getStationAddress()).desc());
		if(StringUtils.isNotBlank(customerApply.getQualifiedInspectionRecord()))
			customerApplyDetailDTO.setQualifiedInspectionRecordDesc(YundaexInspectionRecord.valueOf(customerApply.getQualifiedInspectionRecord()).desc());
		if(StringUtils.isNotBlank(customerApply.getYundaexJudge()))
			customerApplyDetailDTO.setYundaexJudgeDesc(YundaexJudge.valueOf(customerApply.getYundaexJudge()).desc());
		
		if(customerApply.getApplyCreditLimit() != null){
			customerApplyDetailDTO.setApplyCreditLimit(MoneyUtil.cent2Yuan(new BigDecimal(customerApply.getApplyCreditLimit())));
		}
		if(customerApply.getBailBalance() != null){
			customerApplyDetailDTO.setBailBalance(MoneyUtil.cent2Yuan(new BigDecimal(customerApply.getBailBalance())));
		}
		if(customerApply.getShortLoan() != null){
			customerApplyDetailDTO.setShortLoan(MoneyUtil.cent2Yuan(new BigDecimal(customerApply.getShortLoan())));
		}
		
		if(customerApply.getTbOrderRatio()!= null){
			customerApplyDetailDTO.setTbOrderRatio(customerApply.getTbOrderRatio().multiply(new BigDecimal(100))+"");
		}
		
		customerApplyDetailDTO.setDetailAddress(customerApply.getAddress());
		return customerApplyDetailDTO;
	}

	public static void convertCustomerApplyDTO2DAO(YdCustomerApplyRequestDTO ydCustomerApplyDTO,
			YundaexCustomerApply ydCustomerApply) throws Exception {
		logger.info("AuditDTO2DAO转换,stationNo:[{}]", ydCustomerApplyDTO.getStationNo());
		
		//查询请求数据的合法性
		checkApplyDataWhetherBlank(ydCustomerApplyDTO);
		
		BeanUtils.copyProperties(ydCustomerApplyDTO, ydCustomerApply);
		//字段名称不一致，单独set
		ydCustomerApply.setYundaexExplain(ydCustomerApplyDTO.getExplain());
		
		Date upDate = DateUtils.getDate(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
		ydCustomerApply.setApplyDate(upDate);
		ydCustomerApply.setCreateTime(upDate);
		ydCustomerApply.setUpdateTime(upDate);
	}

	private static void checkApplyDataWhetherBlank(YdCustomerApplyRequestDTO ydCustomerApplyDTO) throws Exception{
		
		if (StringUtils.isBlank(ydCustomerApplyDTO.getStationNo()))
			throw WebException.instance(ReturnCode.YP5101);
		if (StringUtils.isBlank(ydCustomerApplyDTO.getStationName()))
			throw WebException.instance(ReturnCode.YP5102);
		if (StringUtils.isBlank(ydCustomerApplyDTO.getStationMgr()))
			throw WebException.instance(ReturnCode.YP5103);
		if(StringUtils.isBlank(ydCustomerApplyDTO.getCustName()))
			throw WebException.instance(ReturnCode.YP5140);
		if(StringUtils.isBlank(ydCustomerApplyDTO.getCustIdno()))
			throw WebException.instance(ReturnCode.YP5141);
		else{
			if(!regular("^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$",ydCustomerApplyDTO.getCustIdno()))
				throw WebException.instance(ReturnCode.YP5115);
		}
		if(StringUtils.isBlank(ydCustomerApplyDTO.getTelephone())){
			throw WebException.instance(ReturnCode.YP5121);
		}else{
			if(!regular("^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$", ydCustomerApplyDTO.getTelephone()))
				throw WebException.instance(ReturnCode.YP5105);
		}
		if(StringUtils.isBlank(ydCustomerApplyDTO.getProvince()))
			throw WebException.instance(ReturnCode.YP5142);
		if(StringUtils.isBlank(ydCustomerApplyDTO.getCity()))
			throw WebException.instance(ReturnCode.YP5143);
		if(StringUtils.isBlank(ydCustomerApplyDTO.getAddress()))
			throw WebException.instance(ReturnCode.YP5144);
		if(StringUtils.isBlank(String.valueOf(ydCustomerApplyDTO.getBusiLimit())))
			throw WebException.instance(ReturnCode.YP5145);
		if(StringUtils.isBlank(ydCustomerApplyDTO.getRegioncode()))
			throw WebException.instance(ReturnCode.YP5146);
		if(StringUtils.isBlank(String.valueOf(ydCustomerApplyDTO.getLoanLimit())))
			throw WebException.instance(ReturnCode.YP5147);
		if(StringUtils.isBlank(ydCustomerApplyDTO.getAddCredit()))
			throw WebException.instance(ReturnCode.YP5148);
		if(ydCustomerApplyDTO.getApplyCreditLimit()<0L)
			throw WebException.instance(ReturnCode.YP5111);
		if(ydCustomerApplyDTO.getApplyCreditLimit()==null )
			throw WebException.instance(ReturnCode.YP5104);
	}
	
	private static boolean regular(String exp ,String con){
		   Pattern patter=Pattern.compile(exp);
		   Matcher mat=patter.matcher(con);
		   return mat.matches();
	   }

	public static YundaexCustomerApply convertApplyAddDTO2CustomerApply(YdCustomerApplyAddRequestDTO ydCustomerApplyAddRequestDTO,
			YundaexCustomerApply yundaexCustomerApply) {
		String controllerIsLegal = ydCustomerApplyAddRequestDTO.getControllerIsLegal(); //1
		String accountOwner = ydCustomerApplyAddRequestDTO.getAccountOwner();
		if("1".equals(controllerIsLegal)){
			//法人信息填入实际控制人的信息
			yundaexCustomerApply.setLegalPerson(ydCustomerApplyAddRequestDTO.getController());
			yundaexCustomerApply.setLegalEmail(ydCustomerApplyAddRequestDTO.getControllerEmail());
			yundaexCustomerApply.setLegalPhone(ydCustomerApplyAddRequestDTO.getControllerPhone());
		}
		
		if(YundaexAccountOwner.LEGAL.name().equals(accountOwner)){
			yundaexCustomerApply.setAccountOwner(YundaexAccountOwner.LEGAL.name());
			yundaexCustomerApply.setAccountOwnerName(yundaexCustomerApply.getLegalPerson());
			yundaexCustomerApply.setAccountOwnerEmail(yundaexCustomerApply.getLegalEmail());
			yundaexCustomerApply.setAccountOwnerPhone(yundaexCustomerApply.getLegalPhone());
		}
		if(YundaexAccountOwner.CONTROLLER.name().equals(accountOwner)){
			yundaexCustomerApply.setAccountOwner(YundaexAccountOwner.CONTROLLER.name());
			yundaexCustomerApply.setAccountOwnerName(yundaexCustomerApply.getController());
			yundaexCustomerApply.setAccountOwnerEmail(yundaexCustomerApply.getControllerEmail());
			yundaexCustomerApply.setAccountOwnerPhone(yundaexCustomerApply.getControllerPhone());
		}
		
		
		if(!StringUtils.isBlank(ydCustomerApplyAddRequestDTO.getRanchiseContractDeadline())){
			try {
				yundaexCustomerApply.setRanchiseContractDeadline(DateUtils.formatDate(ydCustomerApplyAddRequestDTO.getRanchiseContractDeadline(),19));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//元转化为分
		if(ydCustomerApplyAddRequestDTO.getBailBalance()!=null){
			yundaexCustomerApply.setBailBalance(MoneyUtil.yuan2Cent(ydCustomerApplyAddRequestDTO.getBailBalance()));
		}
		if(ydCustomerApplyAddRequestDTO.getShortLoan()!=null){
			yundaexCustomerApply.setShortLoan(MoneyUtil.yuan2Cent(ydCustomerApplyAddRequestDTO.getShortLoan()));
		}
		
		if(StringUtils.isNotBlank(ydCustomerApplyAddRequestDTO.getTbOrderRatio())){
			yundaexCustomerApply.setTbOrderRatio(convertRatio(ydCustomerApplyAddRequestDTO.getTbOrderRatio()));
		}
		yundaexCustomerApply.setOtherExplain(yundaexCustomerApply.getOtherExplain());
		String payAccount = ydCustomerApplyAddRequestDTO.getPayAccount();
		if(StringUtils.isNotBlank(payAccount))
			yundaexCustomerApply.setPayAccount(payAccount.replaceAll(" ",""));
		return yundaexCustomerApply;
	}

	private static BigDecimal convertRatio(String tbOrderRatio) {
	    BigDecimal divide = new BigDecimal(tbOrderRatio).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
		return divide;
	}
}
