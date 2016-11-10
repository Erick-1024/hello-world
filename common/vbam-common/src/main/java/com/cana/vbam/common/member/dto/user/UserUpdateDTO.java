package com.cana.vbam.common.member.dto.user;

import java.io.Serializable;
public class UserUpdateDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6233927390624480982L;

	/**
    * 用户ID
    */
    private String id;

    /**
    *用户所属角色
    */
    private String roleId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
