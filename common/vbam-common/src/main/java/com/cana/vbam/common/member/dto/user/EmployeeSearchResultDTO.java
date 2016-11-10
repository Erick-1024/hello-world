package com.cana.vbam.common.member.dto.user;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.member.enums.user.AccountActivateStatus;

public class EmployeeSearchResultDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2653744022477987701L;

	/**
     *主键
     */
    private String id;
    
    /**
     * 主账户id
     */
    private String masterId;
    
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
    *账号激活状态
    */
    private AccountActivateStatus accountActivateStatus;
    
    /**
     * 角色名称&Id
     */
    private List<RoleDTO> roleDTOList;
    
    /**
     * 每页显示行数
     */
	private int pageSize;
	
	/**
	 * 页码
	 */
	private int pageIndex = 1;
	
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

	public AccountActivateStatus getAccountActivateStatus() {
		return accountActivateStatus;
	}

	public void setAccountActivateStatus(AccountActivateStatus accountActivateStatus) {
		this.accountActivateStatus = accountActivateStatus;
	}

	public List<RoleDTO> getRoleDTOList() {
		return roleDTOList;
	}

	public void setRoleDTOList(List<RoleDTO> roleDTOList) {
		this.roleDTOList = roleDTOList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
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

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	
}
