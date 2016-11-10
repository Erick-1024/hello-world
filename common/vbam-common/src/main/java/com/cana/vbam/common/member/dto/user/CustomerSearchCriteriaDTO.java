package com.cana.vbam.common.member.dto.user;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.member.enums.user.AccountActivateStatus;
import com.cana.vbam.common.member.enums.user.AccountAuditResult;
import com.cana.vbam.common.member.enums.user.AccountAuditStatus;
import com.cana.vbam.common.member.enums.user.UserType;

public class CustomerSearchCriteriaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4113556353735704148L;

	/**
	 * 每页显示行数
	 */
	private int pageSize;
	
	/**
	 * 页码
	 */
	private int page = 1;
	
	/**
    *用户名
    */
    private String username;
    
    /**
     *用户类型
     */
    private UserType userType;
    
    /**
    *公司名称
    */
    private String companyName;

    /**
    *开始时间
    */
    private String beginTime;
    
    /**
     *结束时间
     */
    private String endTime;
    
    /**
     *账号审核状态
     */
    private AccountAuditStatus accountAuditStatus;
    
    /**
     *账号审核结果
     */
    private AccountAuditResult accountAuditResult;
    
    /**
     *账号激活状态
     */
    private AccountActivateStatus accountActivateStatus;
    
    /**
     * 包含的用户类型
     */
    private List<String> userTypeWithInList;
    
    /**
     * 不包含的用户类型
     */
    private List<String> userTypeWithOutList;
    
    /**
     * 包含的用户状态
     */
    private List<String> userStatusWithInList;
    
    /**
     * 不包含的用户状态
     */
    private List<String> userStatusWithOutList;
    
    /**
     * 用户id list
     */
    private List<String> customerIds;
    
	public int getPageSize() {
		return pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public AccountAuditStatus getAccountAuditStatus() {
		return accountAuditStatus;
	}

	public AccountAuditResult getAccountAuditResult() {
		return accountAuditResult;
	}

	public AccountActivateStatus getAccountActivateStatus() {
		return accountActivateStatus;
	}

	public void setAccountAuditStatus(AccountAuditStatus accountAuditStatus) {
		this.accountAuditStatus = accountAuditStatus;
	}

	public void setAccountAuditResult(AccountAuditResult accountAuditResult) {
		this.accountAuditResult = accountAuditResult;
	}

	public void setAccountActivateStatus(AccountActivateStatus accountActivateStatus) {
		this.accountActivateStatus = accountActivateStatus;
	}

	public List<String> getUserTypeWithInList() {
		return userTypeWithInList;
	}

	public void setUserTypeWithInList(List<String> userTypeWithInList) {
		this.userTypeWithInList = userTypeWithInList;
	}

	public List<String> getUserTypeWithOutList() {
		return userTypeWithOutList;
	}

	public void setUserTypeWithOutList(List<String> userTypeWithOutList) {
		this.userTypeWithOutList = userTypeWithOutList;
	}

	public List<String> getUserStatusWithInList() {
		return userStatusWithInList;
	}

	public void setUserStatusWithInList(List<String> userStatusWithInList) {
		this.userStatusWithInList = userStatusWithInList;
	}

	public List<String> getUserStatusWithOutList() {
		return userStatusWithOutList;
	}

	public void setUserStatusWithOutList(List<String> userStatusWithOutList) {
		this.userStatusWithOutList = userStatusWithOutList;
	}

	public List<String> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(List<String> customerIds) {
		this.customerIds = customerIds;
	}

}
