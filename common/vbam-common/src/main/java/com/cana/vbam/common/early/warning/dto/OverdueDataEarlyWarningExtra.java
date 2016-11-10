package com.cana.vbam.common.early.warning.dto;

import java.io.Serializable;

public class OverdueDataEarlyWarningExtra implements Serializable {

	private static final long serialVersionUID = 1L;

	// 统计月数
	private int month;
	
	// 次数
	private int number;

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
}
