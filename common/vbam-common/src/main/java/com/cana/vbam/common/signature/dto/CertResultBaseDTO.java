package com.cana.vbam.common.signature.dto;

import java.io.Serializable;

/**
 * @author hu
 *
 */
public class CertResultBaseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 926436057932537430L;
	
	String operationTime;
	String resultStatus;
	String resultMessage;
	
	public String getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}
	public String getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

}
