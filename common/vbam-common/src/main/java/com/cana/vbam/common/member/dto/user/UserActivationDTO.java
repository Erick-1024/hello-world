package com.cana.vbam.common.member.dto.user;

import java.io.Serializable;

public class UserActivationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7792213435836710662L;

	private String id;
	
	private String masterId;
	
	private String username;
	
	private String password;
	
	private String[] permissionNames;
	
	private String securityCode;
	
	private String roleId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getPermissionNames() {
		return permissionNames;
	}

	public void setPermissionNames(String[] permissionNames) {
		this.permissionNames = permissionNames;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
