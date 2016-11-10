package com.cana.vbam.common.signature.dto;

import java.io.Serializable;

import com.cana.vbam.common.signature.enums.BranchCode;
import com.cana.vbam.common.signature.enums.CertCustomerType;
import com.cana.vbam.common.signature.enums.CertType;
import com.cana.vbam.common.signature.enums.SecretKeyType;

/**
 * @author hu
 * 时间格式:yyyyMMddHHmmss
 */
public class CertApplyDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1715269284807991551L;

	private String local;
	
	private String Remark;

	/**
	 * 非空
	 */
	private String certType = CertType.SINGLE_CERT.number();
	
	/**
	 * 非空
	 */
	private String customerType = CertCustomerType.PERSONAL.number();
	
	/**
	 * 非空
	 */
	private String userName;
	
	/**
	 * 非空
	 */
	private String identType;
	
	/**
	 * 非空
	 */
	private String identNo;
	
	private String keyAlg = SecretKeyType.RSA_2048.keyAlg();
	
	private String keyLength = SecretKeyType.RSA_2048.keyLength();
	
	private String branchCode = BranchCode.CANABRANCHCODE.number();
	
	private String email;
	
	private String phoneNo;
	
	private String address;
	
	private String duration;
	
	private String startTime;
	
	private String endTime;
	
	/**
	 * 非空
	 */
	private String p10;
	
	private String p10Sub;

	public String getRemark() {
		return Remark;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdentType() {
		return identType;
	}

	public void setIdentType(String identType) {
		this.identType = identType;
	}

	public String getIdentNo() {
		return identNo;
	}

	public void setIdentNo(String identNo) {
		this.identNo = identNo;
	}

	public String getKeyAlg() {
		return keyAlg;
	}

	public void setKeyAlg(String keyAlg) {
		this.keyAlg = keyAlg;
	}

	public String getKeyLength() {
		return keyLength;
	}

	public void setKeyLength(String keyLength) {
		this.keyLength = keyLength;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getP10() {
		return p10;
	}

	public void setP10(String p10) {
		this.p10 = p10;
	}

	public String getP10Sub() {
		return p10Sub;
	}

	public void setP10Sub(String p10Sub) {
		this.p10Sub = p10Sub;
	}
	
	
}
