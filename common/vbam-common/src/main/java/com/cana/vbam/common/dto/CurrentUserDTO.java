package com.cana.vbam.common.dto;

import java.io.Serializable;

/**
 * @author hu
 *
 */
public class CurrentUserDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4160392536939687923L;

	private String userId;
	
	private String masterId;
	
	private String userType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
