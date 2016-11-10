/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AssetMarketDataProject implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     *项目名称
     */
    private String projectName;

    /**
     *计息起始日
     */
    private String valueDate;

    /**
     *发起机构
     */
    private String originator;

    /**
     *发行总金额(亿元)
     */
    private BigDecimal issueTotalAmount;

    /**
     *监管机构
     */
    private String supervisionAgency;

    /**
     *基础资产类型
     */
    private String underlyingAssetType;

    /**
     *发行人
     */
    private String issuer;

    /**
     *发行月份
     */
    private Integer issueMonth;

    /**
     *AAA平均利率
     */
    private BigDecimal aaaAverageInterestRate;

    /**
     *优先级平均利率
     */
    private BigDecimal priorityAverageInterestRate;

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
    public String getProjectName() {
        return projectName;
    }

    /**
     *项目名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    /**
     *计息起始日
     */
    public String getValueDate() {
        return valueDate;
    }

    /**
     *计息起始日
     */
    public void setValueDate(String valueDate) {
        this.valueDate = valueDate == null ? null : valueDate.trim();
    }

    /**
     *发起机构
     */
    public String getOriginator() {
        return originator;
    }

    /**
     *发起机构
     */
    public void setOriginator(String originator) {
        this.originator = originator == null ? null : originator.trim();
    }

    /**
     *发行总金额(亿元)
     */
    public BigDecimal getIssueTotalAmount() {
        return issueTotalAmount;
    }

    /**
     *发行总金额(亿元)
     */
    public void setIssueTotalAmount(BigDecimal issueTotalAmount) {
        this.issueTotalAmount = issueTotalAmount;
    }

    /**
     *监管机构
     */
    public String getSupervisionAgency() {
        return supervisionAgency;
    }

    /**
     *监管机构
     */
    public void setSupervisionAgency(String supervisionAgency) {
        this.supervisionAgency = supervisionAgency == null ? null : supervisionAgency.trim();
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
     *发行人
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     *发行人
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer == null ? null : issuer.trim();
    }

    /**
     *发行月份
     */
    public Integer getIssueMonth() {
        return issueMonth;
    }

    /**
     *发行月份
     */
    public void setIssueMonth(Integer issueMonth) {
        this.issueMonth = issueMonth;
    }

    /**
     *AAA平均利率
     */
    public BigDecimal getAaaAverageInterestRate() {
        return aaaAverageInterestRate;
    }

    /**
     *AAA平均利率
     */
    public void setAaaAverageInterestRate(BigDecimal aaaAverageInterestRate) {
        this.aaaAverageInterestRate = aaaAverageInterestRate;
    }

    /**
     *优先级平均利率
     */
    public BigDecimal getPriorityAverageInterestRate() {
        return priorityAverageInterestRate;
    }

    /**
     *优先级平均利率
     */
    public void setPriorityAverageInterestRate(BigDecimal priorityAverageInterestRate) {
        this.priorityAverageInterestRate = priorityAverageInterestRate;
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
        AssetMarketDataProject other = (AssetMarketDataProject) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProjectName() == null ? other.getProjectName() == null : this.getProjectName().equals(other.getProjectName()))
            && (this.getValueDate() == null ? other.getValueDate() == null : this.getValueDate().equals(other.getValueDate()))
            && (this.getOriginator() == null ? other.getOriginator() == null : this.getOriginator().equals(other.getOriginator()))
            && (this.getIssueTotalAmount() == null ? other.getIssueTotalAmount() == null : this.getIssueTotalAmount().equals(other.getIssueTotalAmount()))
            && (this.getSupervisionAgency() == null ? other.getSupervisionAgency() == null : this.getSupervisionAgency().equals(other.getSupervisionAgency()))
            && (this.getUnderlyingAssetType() == null ? other.getUnderlyingAssetType() == null : this.getUnderlyingAssetType().equals(other.getUnderlyingAssetType()))
            && (this.getIssuer() == null ? other.getIssuer() == null : this.getIssuer().equals(other.getIssuer()))
            && (this.getIssueMonth() == null ? other.getIssueMonth() == null : this.getIssueMonth().equals(other.getIssueMonth()))
            && (this.getAaaAverageInterestRate() == null ? other.getAaaAverageInterestRate() == null : this.getAaaAverageInterestRate().equals(other.getAaaAverageInterestRate()))
            && (this.getPriorityAverageInterestRate() == null ? other.getPriorityAverageInterestRate() == null : this.getPriorityAverageInterestRate().equals(other.getPriorityAverageInterestRate()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpateTime() == null ? other.getUpateTime() == null : this.getUpateTime().equals(other.getUpateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProjectName() == null) ? 0 : getProjectName().hashCode());
        result = prime * result + ((getValueDate() == null) ? 0 : getValueDate().hashCode());
        result = prime * result + ((getOriginator() == null) ? 0 : getOriginator().hashCode());
        result = prime * result + ((getIssueTotalAmount() == null) ? 0 : getIssueTotalAmount().hashCode());
        result = prime * result + ((getSupervisionAgency() == null) ? 0 : getSupervisionAgency().hashCode());
        result = prime * result + ((getUnderlyingAssetType() == null) ? 0 : getUnderlyingAssetType().hashCode());
        result = prime * result + ((getIssuer() == null) ? 0 : getIssuer().hashCode());
        result = prime * result + ((getIssueMonth() == null) ? 0 : getIssueMonth().hashCode());
        result = prime * result + ((getAaaAverageInterestRate() == null) ? 0 : getAaaAverageInterestRate().hashCode());
        result = prime * result + ((getPriorityAverageInterestRate() == null) ? 0 : getPriorityAverageInterestRate().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpateTime() == null) ? 0 : getUpateTime().hashCode());
        return result;
    }
}