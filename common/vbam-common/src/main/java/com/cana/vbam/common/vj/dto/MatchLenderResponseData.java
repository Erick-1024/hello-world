package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class MatchLenderResponseData implements Serializable{
	
	private static final long serialVersionUID = 698717041412780677L;

	private String canaLenderId;
	
	private String canaLenderName;

	public String getCanaLenderId() {
		return canaLenderId;
	}

	public void setCanaLenderId(String canaLenderId) {
		this.canaLenderId = canaLenderId;
	}

	public String getCanaLenderName() {
		return canaLenderName;
	}

	public void setCanaLenderName(String canaLenderName) {
		this.canaLenderName = canaLenderName;
	}

}
