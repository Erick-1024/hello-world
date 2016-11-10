package com.cana.vbam.common.credit.dto.marketing;

import com.cana.vbam.common.credit.openapi.TravelzenBaseResponse;

public class CurrentActivityResponse extends TravelzenBaseResponse {

	private static final long serialVersionUID = 1L;

	private String activityId;
	
	private String startTime;
	
	private String endTime;
	
	private String discount;

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
}
