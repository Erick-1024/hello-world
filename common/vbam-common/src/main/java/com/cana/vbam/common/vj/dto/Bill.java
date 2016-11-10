package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class Bill implements Serializable{
	
	private static final long serialVersionUID = -4345482373482955372L;

	private String month;
	
	private long amount;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

}
