/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.util.Date;

public class CustomerMag implements Serializable {
    /**
     *高管id
     */
    private String id;

    /**
     *关联客户ｉｄ
     */
    private String customerId;

    /**
     *姓名
     */
    private String magName;

    /**
     *性别
     */
    private String magSex;

    /**
     *身份证号
     */
    private String magIdentityCardNo;

    /**
     *主要职务
     */
    private String magDutyType;

    /**
     *学历
     */
    private String magEducation;

    /**
     *专业
     */
    private String magProfession;

    /**
     *主要经历
     */
    private String experience;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新记录时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     *高管id
     */
    public String getId() {
        return id;
    }

    /**
     *高管id
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
     *姓名
     */
    public String getMagName() {
        return magName;
    }

    /**
     *姓名
     */
    public void setMagName(String magName) {
        this.magName = magName == null ? null : magName.trim();
    }

    /**
     *性别
     */
    public String getMagSex() {
        return magSex;
    }

    /**
     *性别
     */
    public void setMagSex(String magSex) {
        this.magSex = magSex == null ? null : magSex.trim();
    }

    /**
     *身份证号
     */
    public String getMagIdentityCardNo() {
        return magIdentityCardNo;
    }

    /**
     *身份证号
     */
    public void setMagIdentityCardNo(String magIdentityCardNo) {
        this.magIdentityCardNo = magIdentityCardNo == null ? null : magIdentityCardNo.trim();
    }

    /**
     *主要职务
     */
    public String getMagDutyType() {
        return magDutyType;
    }

    /**
     *主要职务
     */
    public void setMagDutyType(String magDutyType) {
        this.magDutyType = magDutyType == null ? null : magDutyType.trim();
    }

    /**
     *学历
     */
    public String getMagEducation() {
        return magEducation;
    }

    /**
     *学历
     */
    public void setMagEducation(String magEducation) {
        this.magEducation = magEducation == null ? null : magEducation.trim();
    }

    /**
     *专业
     */
    public String getMagProfession() {
        return magProfession;
    }

    /**
     *专业
     */
    public void setMagProfession(String magProfession) {
        this.magProfession = magProfession == null ? null : magProfession.trim();
    }

    /**
     *主要经历
     */
    public String getExperience() {
        return experience;
    }

    /**
     *主要经历
     */
    public void setExperience(String experience) {
        this.experience = experience == null ? null : experience.trim();
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
     *更新记录时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *更新记录时间
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
        CustomerMag other = (CustomerMag) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getMagName() == null ? other.getMagName() == null : this.getMagName().equals(other.getMagName()))
            && (this.getMagSex() == null ? other.getMagSex() == null : this.getMagSex().equals(other.getMagSex()))
            && (this.getMagIdentityCardNo() == null ? other.getMagIdentityCardNo() == null : this.getMagIdentityCardNo().equals(other.getMagIdentityCardNo()))
            && (this.getMagDutyType() == null ? other.getMagDutyType() == null : this.getMagDutyType().equals(other.getMagDutyType()))
            && (this.getMagEducation() == null ? other.getMagEducation() == null : this.getMagEducation().equals(other.getMagEducation()))
            && (this.getMagProfession() == null ? other.getMagProfession() == null : this.getMagProfession().equals(other.getMagProfession()))
            && (this.getExperience() == null ? other.getExperience() == null : this.getExperience().equals(other.getExperience()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getMagName() == null) ? 0 : getMagName().hashCode());
        result = prime * result + ((getMagSex() == null) ? 0 : getMagSex().hashCode());
        result = prime * result + ((getMagIdentityCardNo() == null) ? 0 : getMagIdentityCardNo().hashCode());
        result = prime * result + ((getMagDutyType() == null) ? 0 : getMagDutyType().hashCode());
        result = prime * result + ((getMagEducation() == null) ? 0 : getMagEducation().hashCode());
        result = prime * result + ((getMagProfession() == null) ? 0 : getMagProfession().hashCode());
        result = prime * result + ((getExperience() == null) ? 0 : getExperience().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}