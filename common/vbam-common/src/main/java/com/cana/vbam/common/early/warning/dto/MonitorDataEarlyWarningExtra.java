package com.cana.vbam.common.early.warning.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class MonitorDataEarlyWarningExtra implements Serializable {

	private static final long serialVersionUID = 1L;

	// 日期。yyyy-MM-dd
	private String date;
	
	// 指标
	private BigDecimal metirc;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigDecimal getMetirc() {
		return metirc;
	}

	public void setMetirc(BigDecimal metirc) {
		this.metirc = metirc;
	}
	
}
