package com.cana.vbam.common.setting.dto;

import com.cana.vbam.common.dto.Pagination;

public class CanaCalendarRequest extends Pagination {

	private static final long serialVersionUID = -8326717581514634946L;

	private String startDate;
	
	private String endDate;

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
