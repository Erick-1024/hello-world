package com.cana.vbam.common.member.dto.user;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.member.enums.user.UserStatus;

public class EmployeeRegisterDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6202406780036142699L;

	/**
    *主键
    */
    private String id;
    
    /**
    *主账号Id
    */
    private String masterId;
    
    /**
    *用户名
    */
    private String username;

    /**
    *密码
    */
    private String password;

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
    *用户状态
    */
    private UserStatus userStatus;

    /**
    *职称
    */
    private String jobTitle;

    /**
    *用户所属角色
    */
    private List<String> roleIdList;
    
    /**
     * 员工工号
     */
    private String jobNo;
    
    /**
     * 真实姓名
     */
    private String realName;
    
    /**
    *员工电话
    */
    private String employeeTel;

    /**
    *员工邮件
    */
    private String employeeMail;

    /**
    *员工职称
    */
    private String employeeJobTitle;

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
		this.username = username;	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmployeeTel() {
		return employeeTel;
	}

	public void setEmployeeTel(String employeeTel) {
		this.employeeTel = employeeTel;
	}

	public String getEmployeeMail() {
		return employeeMail;
	}

	public void setEmployeeMail(String employeeMail) {
		this.employeeMail = employeeMail;
	}

	public String getEmployeeJobTitle() {
		return employeeJobTitle;
	}

	public void setEmployeeJobTitle(String employeeJobTitle) {
		this.employeeJobTitle = employeeJobTitle;
	}

}
