package com.cana.vbam.common.account.dto;

import java.io.Serializable;

import com.cana.vbam.common.member.enums.user.UserType;

/**
 * 查询开户申请列表条件
 * @author XuMeng
 *
 */
public class AccountApplyQueryCriteria implements Serializable {
    private static final long serialVersionUID = 7770029478823946110L;

	private int pageSize;  //每页显示行数
	private int page = 1;  //页码
	private String username;   //用户名
	private UserType userType; //客户类型
	private String companyName;    //企业名称
	private String accountNo;  //银行帐号
	private String beginTime;  //申请开始时间，yyyy-MM-dd
    private String endTime; //申请结束时间，yyyy-MM-dd
    private Boolean isAudited;  //账户审核状态
    private Boolean isAuditPassed;  //账户审核结果

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

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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

    public Boolean getIsAudited() {
        return isAudited;
    }

    public void setIsAudited(Boolean isAudited) {
        this.isAudited = isAudited;
    }

    public Boolean getIsAuditPassed() {
        return isAuditPassed;
    }

    public void setIsAuditPassed(Boolean isAuditPassed) {
        this.isAuditPassed = isAuditPassed;
    }
    
}
