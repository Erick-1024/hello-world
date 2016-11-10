/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AssetMarketDataProduct implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     *项目名称
     */
    private String productName;

    /**
     *发行规模(亿元)
     */
    private BigDecimal issueAmount;

    /**
     *债项评级
     */
    private String debtRating;

    /**
     *利率
     */
    private BigDecimal interestRate;

    /**
     *评级机构
     */
    private String ratingAgency;

    /**
     * 项目id
     */
    private String projectId;

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
     *项目名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     *项目名称
     */
    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    /**
     *发行规模(亿元)
     */
    public BigDecimal getIssueAmount() {
        return issueAmount;
    }

    /**
     *发行规模(亿元)
     */
    public void setIssueAmount(BigDecimal issueAmount) {
        this.issueAmount = issueAmount;
    }

    /**
     *债项评级
     */
    public String getDebtRating() {
        return debtRating;
    }

    /**
     *债项评级
     */
    public void setDebtRating(String debtRating) {
        this.debtRating = debtRating == null ? null : debtRating.trim();
    }

    /**
     *利率
     */
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    /**
     *利率
     */
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    /**
     *评级机构
     */
    public String getRatingAgency() {
        return ratingAgency;
    }

    /**
     *评级机构
     */
    public void setRatingAgency(String ratingAgency) {
        this.ratingAgency = ratingAgency == null ? null : ratingAgency.trim();
    }

    /**
     * 项目id
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * 项目id
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
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
        AssetMarketDataProduct other = (AssetMarketDataProduct) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getIssueAmount() == null ? other.getIssueAmount() == null : this.getIssueAmount().equals(other.getIssueAmount()))
            && (this.getDebtRating() == null ? other.getDebtRating() == null : this.getDebtRating().equals(other.getDebtRating()))
            && (this.getInterestRate() == null ? other.getInterestRate() == null : this.getInterestRate().equals(other.getInterestRate()))
            && (this.getRatingAgency() == null ? other.getRatingAgency() == null : this.getRatingAgency().equals(other.getRatingAgency()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpateTime() == null ? other.getUpateTime() == null : this.getUpateTime().equals(other.getUpateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getIssueAmount() == null) ? 0 : getIssueAmount().hashCode());
        result = prime * result + ((getDebtRating() == null) ? 0 : getDebtRating().hashCode());
        result = prime * result + ((getInterestRate() == null) ? 0 : getInterestRate().hashCode());
        result = prime * result + ((getRatingAgency() == null) ? 0 : getRatingAgency().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpateTime() == null) ? 0 : getUpateTime().hashCode());
        return result;
    }
}