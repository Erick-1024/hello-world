/**
 * 
 */
package com.cana.yundaex.common.dto;

import java.io.Serializable;

/**
 * @author guguanggong
 *
 */
public class YundaexTstationInfoRequest implements Serializable {
	private static final long serialVersionUID = -1720459481714988121L;

	private String stationNo;

	private String startDate;

	private String endDate;

	private String sign;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
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
