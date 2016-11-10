package com.cana.vbam.common.member.dto.user;

import java.io.Serializable;
import java.util.List;
public class UserRoleListUpdateDTO implements Serializable{
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
    private List<String> roleIdList;

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
