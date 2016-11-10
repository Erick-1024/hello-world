package com.cana.yundaex.service.transaction.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.vbam.common.yundaex.dto.apply.YundaexCustomerAuditResultDTO;
import com.cana.yundaex.common.enums.Institution;
import com.cana.yundaex.common.enums.YundaexAuditState;
import com.cana.yundaex.common.enums.YundaexStationAddress;
import com.cana.yundaex.dao.mapper.YundaexCreditTableLockMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerApplyMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexOutCustomerMapper;
import com.cana.yundaex.dao.po.YundaexAuditRule;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.po.YundaexOutCustomer;
import com.cana.yundaex.dao.po.YundaexOutCustomerExample;
import com.cana.yundaex.service.IYundaexMessageService;
import com.cana.yundaex.service.IYundaexRetryTaskService;
import com.cana.yundaex.service.transaction.IYundaexCustomerApplyTransactionService;
import com.cana.yundaex.service.utils.NewestAutomaticRuleHolder;
import com.travelzen.framework.core.common.ReturnClass;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.util.DateUtils;

@Service
public class YundaexCustomerApplyTransactionServiceImpl implements IYundaexCustomerApplyTransactionService {

	@Resource
	private YundaexCreditTableLockMapper ydCreditTableLockMapper;

	@Resource
	private YundaexOutCustomerMapper ydOutCustomerMapper;
	
	@Resource
	private YundaexCustomerApplyMapper ydCustomerApplyMapper;
	
	@Resource
	private IYundaexRetryTaskService ydRetryTaskService;
	
	@Resource
	private IYundaexMessageService messageService;
	
	@Override
	public void auditYundaexCustomer(YundaexCustomerAuditResultDTO resultDTO) {
		YundaexAuditRule newestAccessRule = NewestAutomaticRuleHolder.newestAccessRule;
		String customerApplyId = resultDTO.getCustomerApplyId();
		if (StringUtils.isBlank(resultDTO.getCustomerApplyId()))
			throw WebException.instance("授信客户人工审核申请ID不能为空");
		YundaexCustomerApply ydCustomerAply = ydCreditTableLockMapper
				.lockCustomerApplyById(customerApplyId);
		if (ydCustomerAply == null)
			throw WebException.instance("当前申请不存在");
		if (!YundaexAuditState.ACCESS.name().equals(ydCustomerAply.getAccessAutomaticState()))
			throw WebException.instance("当前申请未通过准入验证，不能进行人工审核");
		if (!YundaexAuditState.WAIT.name().equals(ydCustomerAply.getAccessManualState()))
			throw WebException.instance("当前申请已被审核过，不能再次进行人工审核");

		String auditorId = resultDTO.getAuditorId();
		String manualAuditRemarks = resultDTO.getManualAuditRemarks();
		String stationAddress = resultDTO.getStationAddress();
		Integer consistencyCheck = resultDTO.getConsistencyCheck();
		String executeIndividualInfo = resultDTO.getExecuteIndividualInfo();
		String negativeNetwork = resultDTO.getNegativeNetwork();

		ydCustomerAply.setAuditorId(auditorId);
		ydCustomerAply.setManualAuditRemarks(manualAuditRemarks);
		ydCustomerAply.setStationAddress(stationAddress);
		ydCustomerAply.setConsistencyCheck(consistencyCheck);
		ydCustomerAply.setExecuteIndividualInfo(executeIndividualInfo);
		ydCustomerAply.setNegativeNetwork(negativeNetwork);
		ydCustomerAply.setUpdateTime(new Date());
		ydCustomerAply.setAuditTime(DateUtils.format(new Date(), 19));
		ReturnClass retClass = checkYundaexCustomer(consistencyCheck, manualAuditRemarks,stationAddress, executeIndividualInfo,
				negativeNetwork, newestAccessRule);
		ReturnCode retCode = retClass.getRetCode(); 
		String stationNo = ydCustomerAply.getStationNo(); 
		if(ReturnCode.SUCCESS.equals(retCode)){
			if(isExistCustomer(stationNo, ydCustomerAply.getStationName()))
				throw WebException.instance("该客户已经通过了额度审核，不能通过审核，请将该申请设为不通过！");
			ydCustomerAply.setAccessManualState(YundaexAuditState.ACCESS.name());
			ydCustomerAply.setGradeState(YundaexAuditState.WAIT.name());
			ydCustomerApplyMapper.updateByPrimaryKeySelective(ydCustomerAply);
		}else{
			ydCustomerAply.setAccessManualState(YundaexAuditState.NOTACCESS.name());
			ydCustomerApplyMapper.updateByPrimaryKeySelective(ydCustomerAply);
			if(ydCustomerAply.getNotifyFlag()){
				messageService.sendYundaexAuditResultMessageAndMail(ydCustomerAply);
				ydRetryTaskService.createAuditResultNotify(Institution.yundaex, stationNo, YundaexAuditState.NOTACCESS.name(), null, null);
			}
		}
	}

	
	/**
	 * 判断该客户是否已通过审核
	 * @param stationNo
	 * @param stationName
	 * @return
	 */
	private boolean isExistCustomer(String stationNo, String stationName) {
		YundaexOutCustomerExample example = new YundaexOutCustomerExample();
		example.createCriteria().andStationNoEqualTo(stationNo).andInstitutionIdEqualTo(Institution.yundaex.name());
		example.or().andStationNameEqualTo(stationName).andInstitutionIdEqualTo(Institution.yundaex.name());
		List<YundaexOutCustomer> ydOutCustomers = ydOutCustomerMapper.selectByExample(example);
		return ydOutCustomers.size() != 0;
	}

	private ReturnClass checkYundaexCustomer(Integer consistencyCheck, String manualAuditRemarks,String stationAddress,
			String executeIndividualInfo, String negativeNetwork, YundaexAuditRule newestAccessRule) {
		// 检查组织机构代码证、营业执照证、税务登记证,法人身份证,经营地址，其他检查
		if((consistencyCheck & 8) == 0)
			return new ReturnClass(ReturnCode.YP5016);
		if(StringUtils.isBlank(stationAddress) ||YundaexStationAddress.COUNTRYSIDE.name().equals(stationAddress))
			return new ReturnClass(ReturnCode.YP5017);
		if((consistencyCheck & 16) == 0)
			return new ReturnClass(ReturnCode.YP5015, manualAuditRemarks);
		if(consistencyCheck != 31)
			return new ReturnClass(ReturnCode.YP5009);
		//是否有法院被执行人信息
		if(StringUtils.isNotBlank(executeIndividualInfo))
			return  new ReturnClass(ReturnCode.YP5010);
		//是否存在网络负面信息
		if(!newestAccessRule.getNegativeInfomationSearch()){
			if(StringUtils.isNotBlank(negativeNetwork))
				return new ReturnClass(ReturnCode.YP5011);
		}
		return new ReturnClass(ReturnCode.SUCCESS);
	}

}
