package com.cana.vbam.common.early.warning.dto;

import java.io.Serializable;

public class EarlyWarningEventCommonRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	// 操作发起人ID
	private String userId;
	
	// 操作发起人真实姓名
	private String realName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
}
