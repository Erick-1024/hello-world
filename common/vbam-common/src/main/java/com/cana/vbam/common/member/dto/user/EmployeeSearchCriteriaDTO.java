package com.cana.vbam.common.member.dto.user;

import java.io.Serializable;

import com.cana.vbam.common.member.enums.user.AccountActivateStatus;

public class EmployeeSearchCriteriaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8016576691724077413L;

	/**
     *主键
     */
    private String id;
    
    /**
     *用户名
     */
    private String username;
    
    /**
    *联系人
    */
    private String realName;
    
    /**
     * 角色ID
     */
    private String roleId;
    
    /**
     * 每页显示行数
     */
	private int pageSize;
	
	/**
	 * 页码
	 */
	private int page = 1;
    /**
    *账号激活状态
    */
    private AccountActivateStatus accountActivateStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public AccountActivateStatus getAccountActivateStatus() {
		return accountActivateStatus;
	}

	public void setAccountActivateStatus(AccountActivateStatus accountActivateStatus) {
		this.accountActivateStatus = accountActivateStatus;
	}

}
