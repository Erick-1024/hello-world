package com.cana.wechat.common.dto;

import java.io.Serializable;

public class UserTest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String openid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}
