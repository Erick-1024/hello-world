package com.cana.vbam.common.signature.dto;

/**
 * @author hu
 *
 */
public class CertDTO extends CertResultBaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1567329676093428325L;
	
	private String certType;
	
	private String customerType;
	
	private String subjectDN;
	
	private String serialNo;
	
	private String certStatus;
	
	private String startTime;
	
	private String endTime;
	
	private String duration;
	
	private String applyTime;
	
	private String keyAlg;
	
	private String keyLength;
	
	private String branchCode;

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

	public String getSubjectDN() {
		return subjectDN;
	}

	public void setSubjectDN(String subjectDN) {
		this.subjectDN = subjectDN;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getCertStatus() {
		return certStatus;
	}

	public void setCertStatus(String certStatus) {
		this.certStatus = certStatus;
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
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

}
