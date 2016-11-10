/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.util.Date;

public class SpecialProgramLog implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *专项计划编号
     */
    private String specialProgramId;

    /**
     *专项计划名称
     */
    private String specialProgramName;

    /**
     *基础资产类型 @see UnderlyingAssetSource
     */
    private String underlyingAssetType;

    /**
     *资产池金额
     */
    private Long assetPoolAmount;

    /**
     *操作人的企业名称
     */
    private String operateCompanyName;

    /**
     *操作人的用户名
     */
    private String operateUsername;

    /**
     *操作类型 @see SpecialProgramStatus
     */
    private String operateType;

    /**
     *
     */
    private Date createTime;

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
     *专项计划编号
     */
    public String getSpecialProgramId() {
        return specialProgramId;
    }

    /**
     *专项计划编号
     */
    public void setSpecialProgramId(String specialProgramId) {
        this.specialProgramId = specialProgramId == null ? null : specialProgramId.trim();
    }

    /**
     *专项计划名称
     */
    public String getSpecialProgramName() {
        return specialProgramName;
    }

    /**
     *专项计划名称
     */
    public void setSpecialProgramName(String specialProgramName) {
        this.specialProgramName = specialProgramName == null ? null : specialProgramName.trim();
    }

    /**
     *基础资产类型 @see UnderlyingAssetSource
     */
    public String getUnderlyingAssetType() {
        return underlyingAssetType;
    }

    /**
     *基础资产类型 @see UnderlyingAssetSource
     */
    public void setUnderlyingAssetType(String underlyingAssetType) {
        this.underlyingAssetType = underlyingAssetType == null ? null : underlyingAssetType.trim();
    }

    /**
     *资产池金额
     */
    public Long getAssetPoolAmount() {
        return assetPoolAmount;
    }

    /**
     *资产池金额
     */
    public void setAssetPoolAmount(Long assetPoolAmount) {
        this.assetPoolAmount = assetPoolAmount;
    }

    /**
     *操作人的企业名称
     */
    public String getOperateCompanyName() {
        return operateCompanyName;
    }

    /**
     *操作人的企业名称
     */
    public void setOperateCompanyName(String operateCompanyName) {
        this.operateCompanyName = operateCompanyName == null ? null : operateCompanyName.trim();
    }

    /**
     *操作人的用户名
     */
    public String getOperateUsername() {
        return operateUsername;
    }

    /**
     *操作人的用户名
     */
    public void setOperateUsername(String operateUsername) {
        this.operateUsername = operateUsername == null ? null : operateUsername.trim();
    }

    /**
     *操作类型 @see SpecialProgramStatus
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     *操作类型 @see SpecialProgramStatus
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    /**
     *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
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
        SpecialProgramLog other = (SpecialProgramLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSpecialProgramId() == null ? other.getSpecialProgramId() == null : this.getSpecialProgramId().equals(other.getSpecialProgramId()))
            && (this.getSpecialProgramName() == null ? other.getSpecialProgramName() == null : this.getSpecialProgramName().equals(other.getSpecialProgramName()))
            && (this.getUnderlyingAssetType() == null ? other.getUnderlyingAssetType() == null : this.getUnderlyingAssetType().equals(other.getUnderlyingAssetType()))
            && (this.getAssetPoolAmount() == null ? other.getAssetPoolAmount() == null : this.getAssetPoolAmount().equals(other.getAssetPoolAmount()))
            && (this.getOperateCompanyName() == null ? other.getOperateCompanyName() == null : this.getOperateCompanyName().equals(other.getOperateCompanyName()))
            && (this.getOperateUsername() == null ? other.getOperateUsername() == null : this.getOperateUsername().equals(other.getOperateUsername()))
            && (this.getOperateType() == null ? other.getOperateType() == null : this.getOperateType().equals(other.getOperateType()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSpecialProgramId() == null) ? 0 : getSpecialProgramId().hashCode());
        result = prime * result + ((getSpecialProgramName() == null) ? 0 : getSpecialProgramName().hashCode());
        result = prime * result + ((getUnderlyingAssetType() == null) ? 0 : getUnderlyingAssetType().hashCode());
        result = prime * result + ((getAssetPoolAmount() == null) ? 0 : getAssetPoolAmount().hashCode());
        result = prime * result + ((getOperateCompanyName() == null) ? 0 : getOperateCompanyName().hashCode());
        result = prime * result + ((getOperateUsername() == null) ? 0 : getOperateUsername().hashCode());
        result = prime * result + ((getOperateType() == null) ? 0 : getOperateType().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}