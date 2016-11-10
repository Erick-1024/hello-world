package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

import com.cana.vbam.common.dto.Pagination;

/**
 * @author hu
 *
 */
public class CreditAuditQueryCriteria extends Pagination implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5942857645274179683L;
	
	private String creditMode;
	
	private String creditOperateType;
	
	private String customerId;
	
	private String creditId;
	
	private String userId;

	public String getCreditMode() {
		return creditMode;
	}

	public void setCreditMode(String creditMode) {
		this.creditMode = creditMode;
	}

	public String getCreditOperateType() {
		return creditOperateType;
	}

	public void setCreditOperateType(String creditOperateType) {
		this.creditOperateType = creditOperateType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCreditId() {
		return creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}
}
