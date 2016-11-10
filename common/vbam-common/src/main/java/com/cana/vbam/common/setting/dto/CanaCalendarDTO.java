package com.cana.vbam.common.setting.dto;

import java.io.Serializable;

public class CanaCalendarDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2693855907368116134L;

	/**
     *yyyy-MM-dd
     */
    private String date;

    /**
     *年份
     */
    private Integer year;

    /**
     *月份
     */
    private Integer month;

    /**
     *天
     */
    private Integer day;

    /**
     *是否为节假日
     */
    private Boolean isHoliday;

    /**
     *描述
     */
    private String description;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Boolean getIsHoliday() {
		return isHoliday;
	}

	public void setIsHoliday(Boolean isHoliday) {
		this.isHoliday = isHoliday;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
