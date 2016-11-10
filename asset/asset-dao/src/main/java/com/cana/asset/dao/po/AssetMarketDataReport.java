/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AssetMarketDataReport implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     *基础资产类型
     */
    private String underlyingAssetType;

    /**
     *发行总额
     */
    private BigDecimal issueAmount;

    /**
     *发行数量
     */
    private Integer issueNum;

    /**
     *发布的年月
     */
    private String month;

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
     * 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *基础资产类型
     */
    public String getUnderlyingAssetType() {
        return underlyingAssetType;
    }

    /**
     *基础资产类型
     */
    public void setUnderlyingAssetType(String underlyingAssetType) {
        this.underlyingAssetType = underlyingAssetType == null ? null : underlyingAssetType.trim();
    }

    /**
     *发行总额
     */
    public BigDecimal getIssueAmount() {
        return issueAmount;
    }

    /**
     *发行总额
     */
    public void setIssueAmount(BigDecimal issueAmount) {
        this.issueAmount = issueAmount;
    }

    /**
     *发行数量
     */
    public Integer getIssueNum() {
        return issueNum;
    }

    /**
     *发行数量
     */
    public void setIssueNum(Integer issueNum) {
        this.issueNum = issueNum;
    }

    /**
     *发布的年月
     */
    public String getMonth() {
        return month;
    }

    /**
     *发布的年月
     */
    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
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
        AssetMarketDataReport other = (AssetMarketDataReport) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUnderlyingAssetType() == null ? other.getUnderlyingAssetType() == null : this.getUnderlyingAssetType().equals(other.getUnderlyingAssetType()))
            && (this.getIssueAmount() == null ? other.getIssueAmount() == null : this.getIssueAmount().equals(other.getIssueAmount()))
            && (this.getIssueNum() == null ? other.getIssueNum() == null : this.getIssueNum().equals(other.getIssueNum()))
            && (this.getMonth() == null ? other.getMonth() == null : this.getMonth().equals(other.getMonth()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpateTime() == null ? other.getUpateTime() == null : this.getUpateTime().equals(other.getUpateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUnderlyingAssetType() == null) ? 0 : getUnderlyingAssetType().hashCode());
        result = prime * result + ((getIssueAmount() == null) ? 0 : getIssueAmount().hashCode());
        result = prime * result + ((getIssueNum() == null) ? 0 : getIssueNum().hashCode());
        result = prime * result + ((getMonth() == null) ? 0 : getMonth().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpateTime() == null) ? 0 : getUpateTime().hashCode());
        return result;
    }
}