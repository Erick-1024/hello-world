package com.cana.vbam.common.report.dto;

import java.io.Serializable;

public class MonitorBaseDataYunda implements Serializable{

	private static final long serialVersionUID = -6362995812546176362L;
	
	
	// 统计日期。yyyy-MM
		private String date;

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}
}
