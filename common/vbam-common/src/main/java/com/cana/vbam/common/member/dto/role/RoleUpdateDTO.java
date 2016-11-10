/**
* Copyright (c) 2015, travelzen and/or its affiliates. All rights reserved.
*/
package com.cana.vbam.common.member.dto.role;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.member.enums.user.UserType;

public class RoleUpdateDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7688119356052038512L;

	/**
	 * 修改人的角色Id
	 */
	private List<String> changeRoleIdList;
	
	public List<String> getChangeRoleIdList() {
		return changeRoleIdList;
	}

	public void setChangeRoleIdList(List<String> changeRoleIdList) {
		this.changeRoleIdList = changeRoleIdList;
	}

	/**
	 * 角色Id
	 */
	private String roleId;
	/**
    *角色名称
    */
    private String roleName;

    /**
    *权限（以分号隔开）
    */
    private String permissions;
    
    /**
     * 角色类型，只有企业类型有
     */
    private UserType userType;

	public String getRoleId()
	{
		return roleId;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public String getPermissions()
	{
		return permissions;
	}

	public void setPermissions(String permissions)
	{
		this.permissions = permissions;
	}

	public UserType getUserType()
	{
		return userType;
	}

	public void setUserType(UserType userType)
	{
		this.userType = userType;
	}
}