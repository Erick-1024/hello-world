package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

public class EchartsQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1837189836650584799L;

	private String zoom;               //缩放
	private String startDate;          //开始日期
	private String endDate;				//结束日期
	public String getZoom() {
		return zoom;
	}
	public void setZoom(String zoom) {
		this.zoom = zoom;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
