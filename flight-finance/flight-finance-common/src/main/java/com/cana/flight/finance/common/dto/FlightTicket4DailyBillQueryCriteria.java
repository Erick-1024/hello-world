package com.cana.flight.finance.common.dto;

import java.io.Serializable;

public class FlightTicket4DailyBillQueryCriteria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String startRecordId;
	
	private String endRecordId;
	
	protected int limitSize = -1;

	public String getStartRecordId() {
		return startRecordId;
	}

	public void setStartRecordId(String startRecordId) {
		this.startRecordId = startRecordId;
	}

	public int getLimitSize() {
		return limitSize;
	}

	public void setLimitSize(int limitSize) {
		this.limitSize = limitSize;
	}

	public String getEndRecordId() {
		return endRecordId;
	}

	public void setEndRecordId(String endRecordId) {
		this.endRecordId = endRecordId;
	}
	
}
