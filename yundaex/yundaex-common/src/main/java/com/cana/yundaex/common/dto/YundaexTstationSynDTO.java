package com.cana.yundaex.common.dto;

import java.io.Serializable;

public class YundaexTstationSynDTO implements Serializable {

	private static final long serialVersionUID = -8932784272958377295L;

	/**
	 * 站点编号1
	 */
	private String stationNo;

	/**
	 * 最近一次的 统计日期 yyyy-mm
	 */
	private String maxStatmonth;

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getMaxStatmonth() {
		return maxStatmonth;
	}

	public void setMaxStatmonth(String maxStatmonth) {
		this.maxStatmonth = maxStatmonth;
	}

}
