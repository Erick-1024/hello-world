package com.cana.vbam.common.report.dto;

import java.io.Serializable;

public class MonitorMoneyDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	// 外部客户ID
	private String outCustomerId;
	
	// cana平台用户ID
	private String memberId;
	
	// 金额值
	private Long price;

	public String getOutCustomerId() {
		return outCustomerId;
	}

	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	
}
