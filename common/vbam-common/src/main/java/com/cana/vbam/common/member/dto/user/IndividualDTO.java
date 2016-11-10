package com.cana.vbam.common.member.dto.user;

import java.io.Serializable;


public class IndividualDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
    private String contactName;

    /**
    *联系人电话
    */
    private String contactTel;
    
    /**
    *邮箱
    */
    private String contactMail;
    
    /**
    *职称
    */
    private String jobTitle;
    
    
    /**
     * 角色id
     */
    private String roleId;
    
    /**
     * 角色名称
     */
    private String roleName;
    
	
	/**
	 * 员工工号
	 */
	private String jobNo;
	
	 /**
	    *登陆密码
	    */
	private String password;
	
	 /**
	    *支付密码
	    */
	    private String payPassword;

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

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getContactMail() {
		return contactMail;
	}

	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

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

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

}
