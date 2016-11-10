package com.cana.yundaex.dao.po;

import java.io.Serializable;

public class YundaexTstationInfoRecordKey implements Serializable {
    /**
     *批次日期
     */
    private String batchDate;

    /**
     *网点id
     */
    private String stationNo;

    private static final long serialVersionUID = 1L;

    /**
     *批次日期
     */
    public String getBatchDate() {
        return batchDate;
    }

    /**
     *批次日期
     */
    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate == null ? null : batchDate.trim();
    }

    /**
     *网点id
     */
    public String getStationNo() {
        return stationNo;
    }

    /**
     *网点id
     */
    public void setStationNo(String stationNo) {
        this.stationNo = stationNo == null ? null : stationNo.trim();
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
        YundaexTstationInfoRecordKey other = (YundaexTstationInfoRecordKey) that;
        return (this.getBatchDate() == null ? other.getBatchDate() == null : this.getBatchDate().equals(other.getBatchDate()))
            && (this.getStationNo() == null ? other.getStationNo() == null : this.getStationNo().equals(other.getStationNo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBatchDate() == null) ? 0 : getBatchDate().hashCode());
        result = prime * result + ((getStationNo() == null) ? 0 : getStationNo().hashCode());
        return result;
    }
}