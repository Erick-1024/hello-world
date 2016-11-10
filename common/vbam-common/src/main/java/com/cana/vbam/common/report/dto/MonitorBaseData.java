package com.cana.vbam.common.report.dto;

import java.io.Serializable;

public class MonitorBaseData implements Serializable {

	private static final long serialVersionUID = 1L;

	// 统计日期。yyyy-MM-dd
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
