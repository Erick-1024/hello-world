package com.cana.vbam.common.yundaex.dto.creditLimit;

import java.io.Serializable;

public class YundaexAccessCreditLimitCustomerInfoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4392731695508589803L;

	private String id;
	
	private String stationNo;
	
	private String memberId;
	
	private String stationName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
	
}
