package com.cana.vbam.common.member.dto.role;

import java.io.Serializable;
import java.util.List;

public class RoleSearchDTO implements Serializable{

	private static final long serialVersionUID = -7071993710316240233L;
	private List<String> roleIdList;

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}
	
	
}
