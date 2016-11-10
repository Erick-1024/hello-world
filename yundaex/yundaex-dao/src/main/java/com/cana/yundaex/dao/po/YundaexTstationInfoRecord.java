/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.yundaex.dao.po;

import java.io.Serializable;
import java.util.Date;

public class YundaexTstationInfoRecord extends YundaexTstationInfoRecordKey implements Serializable {
    /**
     *站点数据状态
     */
    private String stationDataStatus;

    /**
     *扩展字段
     */
    private String extraData;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     *站点数据状态
     */
    public String getStationDataStatus() {
        return stationDataStatus;
    }

    /**
     *站点数据状态
     */
    public void setStationDataStatus(String stationDataStatus) {
        this.stationDataStatus = stationDataStatus == null ? null : stationDataStatus.trim();
    }

    /**
     *扩展字段
     */
    public String getExtraData() {
        return extraData;
    }

    /**
     *扩展字段
     */
    public void setExtraData(String extraData) {
        this.extraData = extraData == null ? null : extraData.trim();
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
        YundaexTstationInfoRecord other = (YundaexTstationInfoRecord) that;
        return (this.getBatchDate() == null ? other.getBatchDate() == null : this.getBatchDate().equals(other.getBatchDate()))
            && (this.getStationNo() == null ? other.getStationNo() == null : this.getStationNo().equals(other.getStationNo()))
            && (this.getStationDataStatus() == null ? other.getStationDataStatus() == null : this.getStationDataStatus().equals(other.getStationDataStatus()))
            && (this.getExtraData() == null ? other.getExtraData() == null : this.getExtraData().equals(other.getExtraData()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBatchDate() == null) ? 0 : getBatchDate().hashCode());
        result = prime * result + ((getStationNo() == null) ? 0 : getStationNo().hashCode());
        result = prime * result + ((getStationDataStatus() == null) ? 0 : getStationDataStatus().hashCode());
        result = prime * result + ((getExtraData() == null) ? 0 : getExtraData().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}