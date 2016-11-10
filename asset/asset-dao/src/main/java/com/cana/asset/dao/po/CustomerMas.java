/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CustomerMas implements Serializable {
    /**
     *融资ｉｄ
     */
    private String id;

    /**
     *关联客户ｉｄ
     */
    private String customerId;

    /**
     *金融机构名称
     */
    private String financialInstitutionName;

    /**
     *金额（万元）
     */
    private BigDecimal amount;

    /**
     *开始时间
     */
    private String startDate;

    /**
     *到期日
     */
    private String endDate;

    /**
     *担保方式
     */
    private String guaranteeType;

    /**
     *备注
     */
    private String remark;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *记录更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     *融资ｉｄ
     */
    public String getId() {
        return id;
    }

    /**
     *融资ｉｄ
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *关联客户ｉｄ
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *关联客户ｉｄ
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     *金融机构名称
     */
    public String getFinancialInstitutionName() {
        return financialInstitutionName;
    }

    /**
     *金融机构名称
     */
    public void setFinancialInstitutionName(String financialInstitutionName) {
        this.financialInstitutionName = financialInstitutionName == null ? null : financialInstitutionName.trim();
    }

    /**
     *金额（万元）
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     *金额（万元）
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     *开始时间
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     *开始时间
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    /**
     *到期日
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     *到期日
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    /**
     *担保方式
     */
    public String getGuaranteeType() {
        return guaranteeType;
    }

    /**
     *担保方式
     */
    public void setGuaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType == null ? null : guaranteeType.trim();
    }

    /**
     *备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     *备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
     *记录更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *记录更新时间
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
        CustomerMas other = (CustomerMas) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getFinancialInstitutionName() == null ? other.getFinancialInstitutionName() == null : this.getFinancialInstitutionName().equals(other.getFinancialInstitutionName()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getGuaranteeType() == null ? other.getGuaranteeType() == null : this.getGuaranteeType().equals(other.getGuaranteeType()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getFinancialInstitutionName() == null) ? 0 : getFinancialInstitutionName().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getGuaranteeType() == null) ? 0 : getGuaranteeType().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}