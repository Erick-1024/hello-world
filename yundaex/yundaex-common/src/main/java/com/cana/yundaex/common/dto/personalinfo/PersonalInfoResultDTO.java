package com.cana.yundaex.common.dto.personalinfo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hu
 *
 */
public class PersonalInfoResultDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5881461304803396355L;

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

    private String auditStateDesc;
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
    *
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

    private String auditorId;

    /**
     *
     */
    private String auditorName;
    
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

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public Date getAuditApplyTime() {
		return auditApplyTime;
	}

	public void setAuditApplyTime(Date auditApplyTime) {
		this.auditApplyTime = auditApplyTime;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public Date getSecurityCodeExpirationTime() {
		return securityCodeExpirationTime;
	}

	public void setSecurityCodeExpirationTime(Date securityCodeExpirationTime) {
		this.securityCodeExpirationTime = securityCodeExpirationTime;
	}

	public String getAuditStateDesc() {
		return auditStateDesc;
	}

	public void setAuditStateDesc(String auditStateDesc) {
		this.auditStateDesc = auditStateDesc;
	}

	public String getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}
}
