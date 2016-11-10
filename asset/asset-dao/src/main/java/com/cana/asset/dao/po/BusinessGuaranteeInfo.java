/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.util.Date;

public class BusinessGuaranteeInfo implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *业务资料Id
     */
    private String businessInfoId;

    /**
     *保证合同号
     */
    private String guaranteedContractNo;

    /**
     *担保方信息
     */
    private String guarantorInfo;

    /**
     *担保类型
     */
    private String guaranteeType;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    /**
     *资料顺序
     */
    private Integer sequence;

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
     *业务资料Id
     */
    public String getBusinessInfoId() {
        return businessInfoId;
    }

    /**
     *业务资料Id
     */
    public void setBusinessInfoId(String businessInfoId) {
        this.businessInfoId = businessInfoId == null ? null : businessInfoId.trim();
    }

    /**
     *保证合同号
     */
    public String getGuaranteedContractNo() {
        return guaranteedContractNo;
    }

    /**
     *保证合同号
     */
    public void setGuaranteedContractNo(String guaranteedContractNo) {
        this.guaranteedContractNo = guaranteedContractNo == null ? null : guaranteedContractNo.trim();
    }

    /**
     *担保方信息
     */
    public String getGuarantorInfo() {
        return guarantorInfo;
    }

    /**
     *担保方信息
     */
    public void setGuarantorInfo(String guarantorInfo) {
        this.guarantorInfo = guarantorInfo == null ? null : guarantorInfo.trim();
    }

    /**
     *担保类型
     */
    public String getGuaranteeType() {
        return guaranteeType;
    }

    /**
     *担保类型
     */
    public void setGuaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType == null ? null : guaranteeType.trim();
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

    /**
     *资料顺序
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     *资料顺序
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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
        BusinessGuaranteeInfo other = (BusinessGuaranteeInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBusinessInfoId() == null ? other.getBusinessInfoId() == null : this.getBusinessInfoId().equals(other.getBusinessInfoId()))
            && (this.getGuaranteedContractNo() == null ? other.getGuaranteedContractNo() == null : this.getGuaranteedContractNo().equals(other.getGuaranteedContractNo()))
            && (this.getGuarantorInfo() == null ? other.getGuarantorInfo() == null : this.getGuarantorInfo().equals(other.getGuarantorInfo()))
            && (this.getGuaranteeType() == null ? other.getGuaranteeType() == null : this.getGuaranteeType().equals(other.getGuaranteeType()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getSequence() == null ? other.getSequence() == null : this.getSequence().equals(other.getSequence()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBusinessInfoId() == null) ? 0 : getBusinessInfoId().hashCode());
        result = prime * result + ((getGuaranteedContractNo() == null) ? 0 : getGuaranteedContractNo().hashCode());
        result = prime * result + ((getGuarantorInfo() == null) ? 0 : getGuarantorInfo().hashCode());
        result = prime * result + ((getGuaranteeType() == null) ? 0 : getGuaranteeType().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getSequence() == null) ? 0 : getSequence().hashCode());
        return result;
    }
}