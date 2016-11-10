/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.yundaex.dao.po;

import java.io.Serializable;

public class YundaexOutCustomer implements Serializable {
    /**
     *主键ID
     */
    private Integer id;

    /**
     *站点编号
     */
    private String stationNo;

    /**
     *Cana用户的ID
     */
    private String memberId;

    /**
     *机构
     */
    private String institutionId;

    /**
     *站点名称
     */
    private String stationName;

    private static final long serialVersionUID = 1L;

    /**
     *主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     *主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *站点编号
     */
    public String getStationNo() {
        return stationNo;
    }

    /**
     *站点编号
     */
    public void setStationNo(String stationNo) {
        this.stationNo = stationNo == null ? null : stationNo.trim();
    }

    /**
     *Cana用户的ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     *Cana用户的ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     *机构
     */
    public String getInstitutionId() {
        return institutionId;
    }

    /**
     *机构
     */
    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId == null ? null : institutionId.trim();
    }

    /**
     *站点名称
     */
    public String getStationName() {
        return stationName;
    }

    /**
     *站点名称
     */
    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
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
        YundaexOutCustomer other = (YundaexOutCustomer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStationNo() == null ? other.getStationNo() == null : this.getStationNo().equals(other.getStationNo()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getInstitutionId() == null ? other.getInstitutionId() == null : this.getInstitutionId().equals(other.getInstitutionId()))
            && (this.getStationName() == null ? other.getStationName() == null : this.getStationName().equals(other.getStationName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStationNo() == null) ? 0 : getStationNo().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getInstitutionId() == null) ? 0 : getInstitutionId().hashCode());
        result = prime * result + ((getStationName() == null) ? 0 : getStationName().hashCode());
        return result;
    }
}