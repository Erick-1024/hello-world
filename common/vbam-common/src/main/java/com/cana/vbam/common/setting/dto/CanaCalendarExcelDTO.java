package com.cana.vbam.common.setting.dto;

import java.io.Serializable;

public class CanaCalendarExcelDTO implements Serializable {

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
     *星期几
     */
    private String dayOfWeek;
    
    /**
     *是否为节假日
     */
    private Boolean isHoliday;

    /**
     *描述
     */
    private String description;

    private static final long serialVersionUID = 3072260954752829725L;
    
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

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Boolean isIsHoliday() {
		return isHoliday;
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
