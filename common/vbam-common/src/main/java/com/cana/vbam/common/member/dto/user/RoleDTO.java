package com.cana.vbam.common.member.dto.user;

import java.io.Serializable;

public class RoleDTO implements Serializable{

	private static final long serialVersionUID = -5422851833802499901L;

	/**
	 * 角色Id
	 */
	private String roleId;
	
	/**
	 * 角色名称
	 */
	private String roleName;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
