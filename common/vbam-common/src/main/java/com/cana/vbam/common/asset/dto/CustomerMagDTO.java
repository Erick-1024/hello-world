/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.Date;

import com.cana.vbam.common.asset.enums.CustomerManEducationEnum;


/**
 * @author jiangzhou.Ren
 * @time 2016年7月22日上午10:24:07
 */
public class CustomerMagDTO implements Serializable {
	
	private static final long serialVersionUID = -1812702173479055976L;

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
    private CustomerManEducationEnum magEducation;

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
 
    /**
     *专业
     */
    public String getMagProfession() {
        return magProfession;
    }

    public CustomerManEducationEnum getMagEducation() {
		return magEducation;
	}

	public void setMagEducation(CustomerManEducationEnum magEducation) {
		this.magEducation = magEducation;
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

}