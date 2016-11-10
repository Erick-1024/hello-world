/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.yundaex.dao.po;

import java.io.Serializable;
import java.util.Date;

public class YundaexPersonalInfo implements Serializable {
    /**
     *主键Ｉｄ
     */
    private String id;

    /**
     *真实姓名
     */
    private String realName;

    /**
     *邮箱地址
     */
    private String mail;

    /**
     *手机号码
     */
    private String cellphone;

    /**
     *居民身份证号码
     */
    private String residentIdentityCardNo;

    /**
     *居民身份证正面图片Ｉｄ
     */
    private String residentIdentityCardFrontMediaId;

    /**
     *居民身份证反面图片Ｉｄ
     */
    private String residentIdentityCardBackMediaId;

    /**
     *类型（法人代表、实际控制人）
     */
    private String type;

    /**
     *状态（待审核、审核通过）
     */
    private String auditState;

    /**
     *审核申请时间
     */
    private Date auditApplyTime;

    /**
     *安全码
     */
    private String securityCode;

    /**
     *安全码过期时间
     */
    private Date securityCodeExpirationTime;

    /**
     *个人数字证书ｄｎ
     */
    private String certSubjectDn;

    /**
     *关联的企业用户Ｉｄ
     */
    private String relatedCustomerId;

    /**
     *网点名称
     */
    private String stationName;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    /**
     *
     */
    private String auditorId;

    /**
     *
     */
    private String auditorName;

    private static final long serialVersionUID = 1L;

    /**
     *主键Ｉｄ
     */
    public String getId() {
        return id;
    }

    /**
     *主键Ｉｄ
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     *真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     *邮箱地址
     */
    public String getMail() {
        return mail;
    }

    /**
     *邮箱地址
     */
    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    /**
     *手机号码
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     *手机号码
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    /**
     *居民身份证号码
     */
    public String getResidentIdentityCardNo() {
        return residentIdentityCardNo;
    }

    /**
     *居民身份证号码
     */
    public void setResidentIdentityCardNo(String residentIdentityCardNo) {
        this.residentIdentityCardNo = residentIdentityCardNo == null ? null : residentIdentityCardNo.trim();
    }

    /**
     *居民身份证正面图片Ｉｄ
     */
    public String getResidentIdentityCardFrontMediaId() {
        return residentIdentityCardFrontMediaId;
    }

    /**
     *居民身份证正面图片Ｉｄ
     */
    public void setResidentIdentityCardFrontMediaId(String residentIdentityCardFrontMediaId) {
        this.residentIdentityCardFrontMediaId = residentIdentityCardFrontMediaId == null ? null : residentIdentityCardFrontMediaId.trim();
    }

    /**
     *居民身份证反面图片Ｉｄ
     */
    public String getResidentIdentityCardBackMediaId() {
        return residentIdentityCardBackMediaId;
    }

    /**
     *居民身份证反面图片Ｉｄ
     */
    public void setResidentIdentityCardBackMediaId(String residentIdentityCardBackMediaId) {
        this.residentIdentityCardBackMediaId = residentIdentityCardBackMediaId == null ? null : residentIdentityCardBackMediaId.trim();
    }

    /**
     *类型（法人代表、实际控制人）
     */
    public String getType() {
        return type;
    }

    /**
     *类型（法人代表、实际控制人）
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     *状态（待审核、审核通过）
     */
    public String getAuditState() {
        return auditState;
    }

    /**
     *状态（待审核、审核通过）
     */
    public void setAuditState(String auditState) {
        this.auditState = auditState == null ? null : auditState.trim();
    }

    /**
     *审核申请时间
     */
    public Date getAuditApplyTime() {
        return auditApplyTime;
    }

    /**
     *审核申请时间
     */
    public void setAuditApplyTime(Date auditApplyTime) {
        this.auditApplyTime = auditApplyTime;
    }

    /**
     *安全码
     */
    public String getSecurityCode() {
        return securityCode;
    }

    /**
     *安全码
     */
    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode == null ? null : securityCode.trim();
    }

    /**
     *安全码过期时间
     */
    public Date getSecurityCodeExpirationTime() {
        return securityCodeExpirationTime;
    }

    /**
     *安全码过期时间
     */
    public void setSecurityCodeExpirationTime(Date securityCodeExpirationTime) {
        this.securityCodeExpirationTime = securityCodeExpirationTime;
    }

    /**
     *个人数字证书ｄｎ
     */
    public String getCertSubjectDn() {
        return certSubjectDn;
    }

    /**
     *个人数字证书ｄｎ
     */
    public void setCertSubjectDn(String certSubjectDn) {
        this.certSubjectDn = certSubjectDn == null ? null : certSubjectDn.trim();
    }

    /**
     *关联的企业用户Ｉｄ
     */
    public String getRelatedCustomerId() {
        return relatedCustomerId;
    }

    /**
     *关联的企业用户Ｉｄ
     */
    public void setRelatedCustomerId(String relatedCustomerId) {
        this.relatedCustomerId = relatedCustomerId == null ? null : relatedCustomerId.trim();
    }

    /**
     *网点名称
     */
    public String getStationName() {
        return stationName;
    }

    /**
     *网点名称
     */
    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
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
     *
     */
    public String getAuditorId() {
        return auditorId;
    }

    /**
     *
     */
    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId == null ? null : auditorId.trim();
    }

    /**
     *
     */
    public String getAuditorName() {
        return auditorName;
    }

    /**
     *
     */
    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName == null ? null : auditorName.trim();
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
        YundaexPersonalInfo other = (YundaexPersonalInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRealName() == null ? other.getRealName() == null : this.getRealName().equals(other.getRealName()))
            && (this.getMail() == null ? other.getMail() == null : this.getMail().equals(other.getMail()))
            && (this.getCellphone() == null ? other.getCellphone() == null : this.getCellphone().equals(other.getCellphone()))
            && (this.getResidentIdentityCardNo() == null ? other.getResidentIdentityCardNo() == null : this.getResidentIdentityCardNo().equals(other.getResidentIdentityCardNo()))
            && (this.getResidentIdentityCardFrontMediaId() == null ? other.getResidentIdentityCardFrontMediaId() == null : this.getResidentIdentityCardFrontMediaId().equals(other.getResidentIdentityCardFrontMediaId()))
            && (this.getResidentIdentityCardBackMediaId() == null ? other.getResidentIdentityCardBackMediaId() == null : this.getResidentIdentityCardBackMediaId().equals(other.getResidentIdentityCardBackMediaId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getAuditState() == null ? other.getAuditState() == null : this.getAuditState().equals(other.getAuditState()))
            && (this.getAuditApplyTime() == null ? other.getAuditApplyTime() == null : this.getAuditApplyTime().equals(other.getAuditApplyTime()))
            && (this.getSecurityCode() == null ? other.getSecurityCode() == null : this.getSecurityCode().equals(other.getSecurityCode()))
            && (this.getSecurityCodeExpirationTime() == null ? other.getSecurityCodeExpirationTime() == null : this.getSecurityCodeExpirationTime().equals(other.getSecurityCodeExpirationTime()))
            && (this.getCertSubjectDn() == null ? other.getCertSubjectDn() == null : this.getCertSubjectDn().equals(other.getCertSubjectDn()))
            && (this.getRelatedCustomerId() == null ? other.getRelatedCustomerId() == null : this.getRelatedCustomerId().equals(other.getRelatedCustomerId()))
            && (this.getStationName() == null ? other.getStationName() == null : this.getStationName().equals(other.getStationName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getAuditorId() == null ? other.getAuditorId() == null : this.getAuditorId().equals(other.getAuditorId()))
            && (this.getAuditorName() == null ? other.getAuditorName() == null : this.getAuditorName().equals(other.getAuditorName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRealName() == null) ? 0 : getRealName().hashCode());
        result = prime * result + ((getMail() == null) ? 0 : getMail().hashCode());
        result = prime * result + ((getCellphone() == null) ? 0 : getCellphone().hashCode());
        result = prime * result + ((getResidentIdentityCardNo() == null) ? 0 : getResidentIdentityCardNo().hashCode());
        result = prime * result + ((getResidentIdentityCardFrontMediaId() == null) ? 0 : getResidentIdentityCardFrontMediaId().hashCode());
        result = prime * result + ((getResidentIdentityCardBackMediaId() == null) ? 0 : getResidentIdentityCardBackMediaId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getAuditState() == null) ? 0 : getAuditState().hashCode());
        result = prime * result + ((getAuditApplyTime() == null) ? 0 : getAuditApplyTime().hashCode());
        result = prime * result + ((getSecurityCode() == null) ? 0 : getSecurityCode().hashCode());
        result = prime * result + ((getSecurityCodeExpirationTime() == null) ? 0 : getSecurityCodeExpirationTime().hashCode());
        result = prime * result + ((getCertSubjectDn() == null) ? 0 : getCertSubjectDn().hashCode());
        result = prime * result + ((getRelatedCustomerId() == null) ? 0 : getRelatedCustomerId().hashCode());
        result = prime * result + ((getStationName() == null) ? 0 : getStationName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getAuditorId() == null) ? 0 : getAuditorId().hashCode());
        result = prime * result + ((getAuditorName() == null) ? 0 : getAuditorName().hashCode());
        return result;
    }
}