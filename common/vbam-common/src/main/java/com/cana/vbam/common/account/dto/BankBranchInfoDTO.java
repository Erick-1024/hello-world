package com.cana.vbam.common.account.dto;

import java.io.Serializable;

public class BankBranchInfoDTO implements Serializable{

	private static final long serialVersionUID = -323117187528390768L;
	
	private String id;
	
	private String lianHangNo;
	
	private String bankName;

	private String province;

	private String city;

	private String branchName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLianHangNo() {
		return lianHangNo;
	}

	public void setLianHangNo(String lianHangNo) {
		this.lianHangNo = lianHangNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

}
