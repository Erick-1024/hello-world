package com.cana.asset.service.transaction;

import java.util.Set;

import com.cana.vbam.common.member.enums.user.UserType;

public interface IABSDataPrivilegeTransactionService {

	/**
	 * 获取当前登录用户允许查看的专项计划id列表
	 * @param userType 当前登录用户的客户类型
	 * @param companyName 当前登录用户的企业名称
	 * @return
	 */
	public Set<String> allowedProgramIdList(UserType userType, String companyName);
	
	/**
	 * 当前登录用户是否可以查看该专项计划
	 * @param userType 当前登录用户的客户类型
	 * @param companyName 当前登录用户的企业名称
	 * @param programId 专项计划id
	 * @return
	 */
	public boolean allow(UserType userType, String companyName, String programId);
	
}
