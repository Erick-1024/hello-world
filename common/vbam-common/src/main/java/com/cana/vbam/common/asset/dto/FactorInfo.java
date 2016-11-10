package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

import com.cana.vbam.common.asset.enums.ProjectFactorStatusEnum;

public class FactorInfo implements Serializable{
	
	private static final long serialVersionUID = -3122584660019142621L;
	
	// 资金方企业ID
    private String companyId;
    // 资金方企业名称
    private String companyName;
    // 资金方回款账号
    private String accountNo;
    // 状态
    private ProjectFactorStatusEnum status;
    
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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
	public ProjectFactorStatusEnum getStatus() {
		return status;
	}
	public void setStatus(ProjectFactorStatusEnum status) {
		this.status = status;
	}

}
