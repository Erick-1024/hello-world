package com.cana.yundaex.common.dto.personalinfo;

import java.io.Serializable;

/**
 * @author hu
 *
 */
public class PersonalInfoRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7682860207306511842L;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getResidentIdentityCardNo() {
		return residentIdentityCardNo;
	}

	public void setResidentIdentityCardNo(String residentIdentityCardNo) {
		this.residentIdentityCardNo = residentIdentityCardNo;
	}

	public String getResidentIdentityCardFrontMediaId() {
		return residentIdentityCardFrontMediaId;
	}

	public void setResidentIdentityCardFrontMediaId(String residentIdentityCardFrontMediaId) {
		this.residentIdentityCardFrontMediaId = residentIdentityCardFrontMediaId;
	}

	public String getResidentIdentityCardBackMediaId() {
		return residentIdentityCardBackMediaId;
	}

	public void setResidentIdentityCardBackMediaId(String residentIdentityCardBackMediaId) {
		this.residentIdentityCardBackMediaId = residentIdentityCardBackMediaId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
