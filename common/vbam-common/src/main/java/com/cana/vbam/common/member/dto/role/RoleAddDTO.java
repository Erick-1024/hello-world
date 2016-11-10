/**
* Copyright (c) 2015, travelzen and/or its affiliates. All rights reserved.
*/
package com.cana.vbam.common.member.dto.role;

import java.io.Serializable;

import com.cana.vbam.common.member.enums.user.UserType;

public class RoleAddDTO implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1218766504406605070L;

	/**
    *角色名称
    */
    private String roleName;

    /**
    *描述
    */
    private String description;

    /**
    *类型（一级，二级）
    */
    private String type;

    /**
    *主账号Id
    */
    private String masterId;

    /**
    *权限（以分号隔开）
    */
    private String permissions;
    
    /**
     * 角色类型，只有企业类型有
     */
    private UserType userType;

    public UserType getUserType()
	{
		return userType;
	}

	public void setUserType(UserType userType)
	{
		this.userType = userType;
	}

	/**
    *角色名称
    */
    public String getRoleName() {
        return roleName;
    }

    /**
    *角色名称
    */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
    *描述
    */
    public String getDescription() {
        return description;
    }

    /**
    *描述
    */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
    *类型（一级，二级）
    */
    public String getType() {
        return type;
    }

    /**
    *类型（一级，二级）
    */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
    *主账号Id
    */
    public String getMasterId() {
        return masterId;
    }

    /**
    *主账号Id
    */
    public void setMasterId(String masterId) {
        this.masterId = masterId == null ? null : masterId.trim();
    }

    /**
    *权限（以分号隔开）
    */
    public String getPermissions() {
        return permissions;
    }

    /**
    *权限（以分号隔开）
    */
    public void setPermissions(String permissions) {
        this.permissions = permissions == null ? null : permissions.trim();
    }
}