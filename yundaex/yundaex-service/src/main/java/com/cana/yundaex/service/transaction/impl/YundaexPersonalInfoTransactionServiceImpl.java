package com.cana.yundaex.service.transaction.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.vbam.common.dto.ListResult;
import com.cana.yundaex.common.dto.personalinfo.PersonalInfoAuditDTO;
import com.cana.yundaex.common.dto.personalinfo.PersonalInfoRequestDTO;
import com.cana.yundaex.common.enums.PersonalInfoAuditStatus;
import com.cana.yundaex.common.util.Constants;
import com.cana.yundaex.dao.mapper.gen.YundaexPersonalInfoMapper;
import com.cana.yundaex.dao.po.YundaexPersonalInfo;
import com.cana.yundaex.dao.po.YundaexPersonalInfoExample;
import com.cana.yundaex.service.IYundaexMessageService;
import com.cana.yundaex.service.transaction.IYundaexPersonalInfoTransactionService;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;

/**
 * @author hu
 *
 */
@Service
public class YundaexPersonalInfoTransactionServiceImpl implements IYundaexPersonalInfoTransactionService{

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private YundaexPersonalInfoMapper personalInfoMapper;

	@Resource
	private IYundaexMessageService messageService;
	
	@Override
	public void BatchCreatePersonalInfo(List<YundaexPersonalInfo> infoList){
		for(YundaexPersonalInfo info : infoList){
			info.setSecurityCode(generateSecurityCode());
			info.setSecurityCodeExpirationTime(new DateTime().plusHours(Constants.PERSNAL_MAIL_URL_DUE_PERIOD).toDate());
			personalInfoMapper.insertSelective(info);

			messageService.sendPersonalInfoCommitMailAndSMS(info);
		}
	}
	
	@Override
	public void updatePersonalInfo(PersonalInfoRequestDTO requestDTO) {
		checkUpdatePersonalInfo(requestDTO);
		YundaexPersonalInfo personPo = personalInfoMapper.lockByPrimaryKey(requestDTO.getId());
		personPo.setResidentIdentityCardNo(requestDTO.getResidentIdentityCardNo());
		personPo.setResidentIdentityCardFrontMediaId(requestDTO.getResidentIdentityCardFrontMediaId());
		personPo.setResidentIdentityCardBackMediaId(requestDTO.getResidentIdentityCardBackMediaId());
		personPo.setAuditState(PersonalInfoAuditStatus.PENDINGAUDIT.name());
		personPo.setAuditApplyTime(new Date());
		personPo.setSecurityCode("");
		personalInfoMapper.updateByPrimaryKeySelective(personPo);
	}

	@Override
	public void auditPersonalInfo(PersonalInfoAuditDTO auditDTO) {
		if(StringUtils.isBlank(auditDTO.getId()) || null == auditDTO.getAuditStatus()){
			throw WebException.instance("参数异常");
		}
		YundaexPersonalInfo personPo = personalInfoMapper.lockByPrimaryKey(auditDTO.getId());
		
		YundaexPersonalInfoExample example = new YundaexPersonalInfoExample();
		example.createCriteria().andResidentIdentityCardNoEqualTo(personPo.getResidentIdentityCardNo())
			.andAuditStateEqualTo(PersonalInfoAuditStatus.PASSED.name());
		List<YundaexPersonalInfo> personalList = personalInfoMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(personalList) && StringUtils.isNotBlank(personalList.get(0).getCertSubjectDn())){
			personPo.setCertSubjectDn(personalList.get(0).getCertSubjectDn());
			logger.info("this person already exist, copy the certsubject:{} to this", personPo.getCertSubjectDn());
		}
		personPo.setAuditState(auditDTO.getAuditStatus().name());
		personPo.setAuditorId(auditDTO.getAuditorId());
		personPo.setAuditorName(auditDTO.getAuditorName());
		personPo.setSecurityCode(generateSecurityCode());
		personPo.setSecurityCodeExpirationTime(new DateTime().plusHours(Constants.PERSNAL_MAIL_URL_DUE_PERIOD).toDate());
		personalInfoMapper.updateByPrimaryKeySelective(personPo);
		
		if(PersonalInfoAuditStatus.PASSED.equals(auditDTO.getAuditStatus()))
			messageService.sendPersonalSignContractMailAndSMS(personPo);
	}

	@Override
	public void updatePersonalSubjectDn(String id, String subjectDn) {
		if(StringUtils.isBlank(id) || StringUtils.isBlank(subjectDn)){
			throw WebException.instance("参数异常");
		}
		YundaexPersonalInfo personPo = personalInfoMapper.lockByPrimaryKey(id);
		personPo.setCertSubjectDn(subjectDn);
		personalInfoMapper.updateByPrimaryKeySelective(personPo);
	}
	
	private void checkUpdatePersonalInfo(PersonalInfoRequestDTO requestDTO){
		if(null == requestDTO || StringUtils.isBlank(requestDTO.getId())){
			throw WebException.instance("参数异常");
		}
		if(StringUtils.isBlank(requestDTO.getResidentIdentityCardNo())
				|| StringUtils.isBlank(requestDTO.getResidentIdentityCardFrontMediaId())
				|| StringUtils.isBlank(requestDTO.getResidentIdentityCardBackMediaId())){
			throw WebException.instance("身份证不能为空");
		}
	}

	private String generateSecurityCode() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	@Override
	public void resendSubmitLink(String id) {
		YundaexPersonalInfo info = personalInfoMapper.selectByPrimaryKey(id);
		if(null == info)
			throw WebException.instance("个人信息不存在");
		
		info.setSecurityCode(generateSecurityCode());
		info.setSecurityCodeExpirationTime(new DateTime().plusHours(Constants.PERSNAL_MAIL_URL_DUE_PERIOD).toDate());
		personalInfoMapper.updateByPrimaryKeySelective(info);
		
		messageService.sendPersonalInfoCommitMailAndSMS(info);
	}

	@Override
	public ListResult<String> resendSignContractLink(String customerId, List<String> type) {
		List<String> result = Lists.newArrayList();
		YundaexPersonalInfoExample example = new YundaexPersonalInfoExample();
		example.createCriteria().andRelatedCustomerIdEqualTo(customerId).andTypeIn(type);
		List<YundaexPersonalInfo> infoList = personalInfoMapper.lockByExample(example);
		for(YundaexPersonalInfo info : infoList){
			if(PersonalInfoAuditStatus.PASSED.name().equals(info.getAuditState())){
				info.setSecurityCode(generateSecurityCode());
				info.setSecurityCodeExpirationTime(new DateTime().plusHours(Constants.PERSNAL_MAIL_URL_DUE_PERIOD).toDate());
				personalInfoMapper.updateByPrimaryKeySelective(info);
				
				messageService.sendPersonalSignContractMailAndSMS(info);
			}else{
				result.add(info.getType());
			}
		}
		return ListResult.success("重发合同成功", result, result.size());
	}
	
	
}
