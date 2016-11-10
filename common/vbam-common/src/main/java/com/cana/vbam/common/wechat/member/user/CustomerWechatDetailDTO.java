package com.cana.vbam.common.wechat.member.user;

import java.io.Serializable;

public class CustomerWechatDetailDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7619779636688421999L;

	/**
    *主键
    */
    private String id;
    
    /**
     *联系人
     */
     private String contactName;
     
     /**
     *联系人电话
     */
     private String contactTel;

     /**
     *邮箱
     */
     private String contactMail;
     
     /**
     *职称
     */
     private String jobTitle;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getContactMail() {
		return contactMail;
	}

	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
     
     
}
