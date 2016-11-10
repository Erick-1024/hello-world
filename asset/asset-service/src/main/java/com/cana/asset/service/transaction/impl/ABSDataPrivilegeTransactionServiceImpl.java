package com.cana.asset.service.transaction.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.ABSDataPrivilegeMapper;
import com.cana.asset.service.transaction.IABSDataPrivilegeTransactionService;
import com.cana.vbam.common.member.enums.user.UserType;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

@Service
public class ABSDataPrivilegeTransactionServiceImpl implements IABSDataPrivilegeTransactionService{
	
	@Resource
	private ABSDataPrivilegeMapper mapper;

	@Override
	public Set<String> allowedProgramIdList(UserType userType, String companyName) {
		if(userType == null)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "userType不能为空");
		if(StringUtils.isBlank(companyName))
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "companyName不能为空");
		companyName = StringUtils.trimToEmpty(companyName);
		return mapper.allowedProjectList(userType, companyName);
	}

	@Override
	public boolean allow(UserType userType, String companyName, String programId) {
		companyName = StringUtils.trimToEmpty(companyName);
		programId = StringUtils.trimToEmpty(programId);
		return allowedProgramIdList(userType, companyName).contains(programId);
	}

}
