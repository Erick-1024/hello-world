package com.cana.asset.dao.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.cana.vbam.common.member.enums.user.UserType;

/**
 * ABS数据权限
 * @author renshui
 *
 */
public interface ABSDataPrivilegeMapper {
	
	/**
	 * 获取当前登录用户可以查看的专项计划列表
	 * @param userType
	 * @param companyName
	 * @return
	 */
	public Set<String> allowedProjectList(@Param("userType") UserType userType, @Param("companyName") String companyName);
}
