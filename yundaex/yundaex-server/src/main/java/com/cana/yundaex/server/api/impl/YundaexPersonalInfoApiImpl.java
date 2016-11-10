package com.cana.yundaex.server.api.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyDetailDTO;
import com.cana.yundaex.api.IYundaexPersonalInfoApi;
import com.cana.yundaex.common.dto.personalinfo.PersonalInfoAuditDTO;
import com.cana.yundaex.common.dto.personalinfo.PersonalInfoQueryCriteria;
import com.cana.yundaex.common.dto.personalinfo.PersonalInfoRequestDTO;
import com.cana.yundaex.common.dto.personalinfo.PersonalInfoResultDTO;
import com.cana.yundaex.common.enums.PersonalInfoType;
import com.cana.yundaex.dao.mapper.gen.YundaexPersonalInfoMapper;
import com.cana.yundaex.dao.po.YundaexPersonalInfo;
import com.cana.yundaex.dao.po.YundaexPersonalInfoExample;
import com.cana.yundaex.dao.po.YundaexPersonalInfoExample.Criteria;
import com.cana.yundaex.service.IYundaexAuditService;
import com.cana.yundaex.service.IYundaexCreditService;
import com.cana.yundaex.service.convertors.YundaexPersonalInfoConvert;
import com.cana.yundaex.service.transaction.IYundaexCreditLimitTransactionService;
import com.cana.yundaex.service.transaction.IYundaexPersonalInfoTransactionService;
import com.travelzen.framework.core.exception.WebException;

/**
 * @author hu
 *
 */
public class YundaexPersonalInfoApiImpl implements IYundaexPersonalInfoApi{

	@Resource
	private YundaexPersonalInfoMapper personalInfoMapper;
	
	@Resource
	private IYundaexPersonalInfoTransactionService personalInfoTransactionService;
	
	@Resource
	private YundaexPersonalInfoConvert personalInfoConvert;
	
	@Resource
	private IYundaexAuditService auditService;
	
	@Resource
	private IYundaexCreditLimitTransactionService creditTransactionService;
	
	@Resource
	private IYundaexCreditService creditService;
	
	@Override
	public PersonalInfoType sendPersonalInfoLink(String customerId) {
		YdCustomerApplyDetailDTO detailDTO = auditService.getUserBaseInfo(customerId);
		List<YundaexPersonalInfo> infoList = personalInfoConvert.convertCusApply2Po(detailDTO, customerId);
		PersonalInfoType type = null;
		if(!infoList.isEmpty()){
			personalInfoTransactionService.BatchCreatePersonalInfo(infoList);
			type = infoList.size() == 2 ? PersonalInfoType.ACCOUNT_HOLDER_AND_CONTROLLER : PersonalInfoType.valueOf(infoList.get(0).getType());
		}else{
			creditTransactionService.activateCreditLimit(customerId);
		}
		
		return type;
	}

	@Override
	public void createPersonalInfo(PersonalInfoRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePersonalInfo(PersonalInfoRequestDTO requestDTO) {
		
		personalInfoTransactionService.updatePersonalInfo(requestDTO);
	}

	@Override
	public PageResult<PersonalInfoResultDTO> findPersonalInfoByCondition(PersonalInfoQueryCriteria queryCriteria) {
		YundaexPersonalInfoExample example = new YundaexPersonalInfoExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(queryCriteria.getCustomerName())){
			criteria.andStationNameLike("%"+queryCriteria.getCustomerName()+"%");
		}
		if(StringUtils.isNotBlank(queryCriteria.getAuditStatus())){
			criteria.andAuditStateEqualTo(queryCriteria.getAuditStatus());
		}
		if(StringUtils.isNotBlank(queryCriteria.getAuditStartTime())){
			criteria.andAuditApplyTimeGreaterThanOrEqualTo(new DateTime(queryCriteria.getAuditStartTime()).toDate());
		}
		if(StringUtils.isNotBlank(queryCriteria.getAuditEndTime())){
			criteria.andAuditApplyTimeLessThan(new DateTime(queryCriteria.getAuditEndTime()).toDate());
		}
		int totelRecord = personalInfoMapper.countByExample(example);
		int page = queryCriteria.getPage() < 1 ? 1 : queryCriteria.getPage();
		example.setLimitStart((page-1)*queryCriteria.getPageSize());
		example.setLimitEnd(queryCriteria.getPageSize());
		example.setOrderByClause("create_time desc");
		List<YundaexPersonalInfo> personalInfoList = personalInfoMapper.selectByExample(example);
		return new PageResult<>(personalInfoConvert.convertPersonalInfoList2DTO(personalInfoList), totelRecord);
	}

	@Override
	public PersonalInfoResultDTO findPersonalInfoById(String id) {
		YundaexPersonalInfo personalInfo = personalInfoMapper.selectByPrimaryKey(id);
		return personalInfoConvert.convertPersonalInfo2DTO(personalInfo);
	}
	
	@Override
	public Long getCreditLimitInfo(String customerId){
		CreditLimit creditLimit = creditService.getCreditLimitByMemberId(customerId);
		if(null != creditLimit)
			return creditLimit.getTotalLimit();
		return null;
	}
	
	@Override
	public void auditPersonalInfo(PersonalInfoAuditDTO auditDTO) {
		personalInfoTransactionService.auditPersonalInfo(auditDTO);
	}

	@Override
	public void resendSubmitInfoLink(String id) {
		if(StringUtils.isBlank(id))
			throw WebException.instance("参数有误");
		
		personalInfoTransactionService.resendSubmitLink(id);
	}
	
	@Override
	public void updatePersonalSubjectDn(String id, String subjectDn) {
		personalInfoTransactionService.updatePersonalSubjectDn(id, subjectDn);
		
	}

}
