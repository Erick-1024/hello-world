package com.cana.vbam.common.signature.dto;

/**
 * @author hu
 *
 */
public class CertResultDTO extends CertResultBaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2228422280408787252L;
	
	String subjectDN;
	String serialNo;
	String authCode;
	String startTime;
	String endTime;
	String signatureCert;
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
	public String getSignatureCert() {
		return signatureCert;
	}
	public void setSignatureCert(String signatureCert) {
		this.signatureCert = signatureCert;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
}
