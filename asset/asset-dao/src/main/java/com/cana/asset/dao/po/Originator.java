/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.util.Date;

public class Originator implements Serializable {
    /**
     *主键 
     */
    private String id;

    /**
     *专项计划id
     */
    private String specialProgramId;

    /**
     *原始权限人名称
     */
    private String originatorName;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date upateTime;

    private static final long serialVersionUID = 1L;

    /**
     *主键 
     */
    public String getId() {
        return id;
    }

    /**
     *主键 
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *专项计划id
     */
    public String getSpecialProgramId() {
        return specialProgramId;
    }

    /**
     *专项计划id
     */
    public void setSpecialProgramId(String specialProgramId) {
        this.specialProgramId = specialProgramId == null ? null : specialProgramId.trim();
    }

    /**
     *原始权限人名称
     */
    public String getOriginatorName() {
        return originatorName;
    }

    /**
     *原始权限人名称
     */
    public void setOriginatorName(String originatorName) {
        this.originatorName = originatorName == null ? null : originatorName.trim();
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
    public Date getUpateTime() {
        return upateTime;
    }

    /**
     *更新时间
     */
    public void setUpateTime(Date upateTime) {
        this.upateTime = upateTime;
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
        Originator other = (Originator) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSpecialProgramId() == null ? other.getSpecialProgramId() == null : this.getSpecialProgramId().equals(other.getSpecialProgramId()))
            && (this.getOriginatorName() == null ? other.getOriginatorName() == null : this.getOriginatorName().equals(other.getOriginatorName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpateTime() == null ? other.getUpateTime() == null : this.getUpateTime().equals(other.getUpateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSpecialProgramId() == null) ? 0 : getSpecialProgramId().hashCode());
        result = prime * result + ((getOriginatorName() == null) ? 0 : getOriginatorName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpateTime() == null) ? 0 : getUpateTime().hashCode());
        return result;
    }
}