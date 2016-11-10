/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.setting.dao.po;

import java.io.Serializable;
import java.util.Date;

public class CanaCalendar implements Serializable {
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

    /**
     *操作员ID
     */
    private String operatorId;

    /**
     *假期属性变更时间
     */
    private Date holidayUpateTime;

    /**
     *更新时间
     */
    private Date updateTime;

    /**
     *创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     *yyyy-MM-dd
     */
    public String getDate() {
        return date;
    }

    /**
     *yyyy-MM-dd
     */
    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    /**
     *年份
     */
    public Integer getYear() {
        return year;
    }

    /**
     *年份
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     *月份
     */
    public Integer getMonth() {
        return month;
    }

    /**
     *月份
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     *天
     */
    public Integer getDay() {
        return day;
    }

    /**
     *天
     */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     *星期几
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     *星期几
     */
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek == null ? null : dayOfWeek.trim();
    }

    /**
     *是否为节假日
     */
    public Boolean getIsHoliday() {
        return isHoliday;
    }

    /**
     *是否为节假日
     */
    public void setIsHoliday(Boolean isHoliday) {
        this.isHoliday = isHoliday;
    }

    /**
     *描述
     */
    public String getDescription() {
        return description;
    }

    /**
     *描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     *操作员ID
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     *操作员ID
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    /**
     *假期属性变更时间
     */
    public Date getHolidayUpateTime() {
        return holidayUpateTime;
    }

    /**
     *假期属性变更时间
     */
    public void setHolidayUpateTime(Date holidayUpateTime) {
        this.holidayUpateTime = holidayUpateTime;
    }

    /**
     *更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CanaCalendar other = (CanaCalendar) that;
        return (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getYear() == null ? other.getYear() == null : this.getYear().equals(other.getYear()))
            && (this.getMonth() == null ? other.getMonth() == null : this.getMonth().equals(other.getMonth()))
            && (this.getDay() == null ? other.getDay() == null : this.getDay().equals(other.getDay()))
            && (this.getDayOfWeek() == null ? other.getDayOfWeek() == null : this.getDayOfWeek().equals(other.getDayOfWeek()))
            && (this.getIsHoliday() == null ? other.getIsHoliday() == null : this.getIsHoliday().equals(other.getIsHoliday()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getOperatorId() == null ? other.getOperatorId() == null : this.getOperatorId().equals(other.getOperatorId()))
            && (this.getHolidayUpateTime() == null ? other.getHolidayUpateTime() == null : this.getHolidayUpateTime().equals(other.getHolidayUpateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getYear() == null) ? 0 : getYear().hashCode());
        result = prime * result + ((getMonth() == null) ? 0 : getMonth().hashCode());
        result = prime * result + ((getDay() == null) ? 0 : getDay().hashCode());
        result = prime * result + ((getDayOfWeek() == null) ? 0 : getDayOfWeek().hashCode());
        result = prime * result + ((getIsHoliday() == null) ? 0 : getIsHoliday().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getOperatorId() == null) ? 0 : getOperatorId().hashCode());
        result = prime * result + ((getHolidayUpateTime() == null) ? 0 : getHolidayUpateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}