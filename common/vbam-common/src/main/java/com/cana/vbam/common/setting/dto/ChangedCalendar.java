package com.cana.vbam.common.setting.dto;

import java.io.Serializable;

public class ChangedCalendar implements Serializable {

	private static final long serialVersionUID = -9033154940772122721L;

	private String date;
	
	private Integer beforeFirstWeekday;
	
	private Integer notBeforeFirstWeekday;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getBeforeFirstWeekday() {
		return beforeFirstWeekday;
	}

	public void setBeforeFirstWeekday(Integer beforeFirstWeekday) {
		this.beforeFirstWeekday = beforeFirstWeekday;
	}

	public Integer getNotBeforeFirstWeekday() {
		return notBeforeFirstWeekday;
	}

	public void setNotBeforeFirstWeekday(Integer notBeforeFirstWeekday) {
		this.notBeforeFirstWeekday = notBeforeFirstWeekday;
	}

}
