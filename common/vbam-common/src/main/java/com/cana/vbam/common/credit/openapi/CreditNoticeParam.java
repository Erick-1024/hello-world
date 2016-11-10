package com.cana.vbam.common.credit.openapi;

import java.io.Serializable;

import com.cana.vbam.common.credit.enums.NoticeScene;

/**
 * 短信和邮件通知
 * @author sugar
 *
 */
public class CreditNoticeParam implements Serializable{

	private static final long serialVersionUID = -3816545756267183872L;
	
	private String companyName;//企业名称
	
	private String accessManualState;//人工审核状态
	
	private String email;//客户的邮箱
	
	private String phoneNumber;//客户的手机号
	
	private boolean inwhitelist;//是否是白名单客户
	
	private NoticeScene noticeScene;//邮件和短信应用场景
	
	private String activeLink;//用户激活链接
	
	private String loginLink;//登陆链接
	
	private String limit;//最终额度
	
	private String accountNo;//还款账号
	
	private boolean needLoginActive;//是否需要登录激活

	public boolean isNeedLoginActive() {
		return needLoginActive;
	}

	public void setNeedLoginActive(boolean needLoginActive) {
		this.needLoginActive = needLoginActive;
	}

	public boolean isInwhitelist() {
		return inwhitelist;
	}

	public void setInwhitelist(boolean inwhitelist) {
		this.inwhitelist = inwhitelist;
	}

	public NoticeScene getNoticeScene() {
		return noticeScene;
	}

	public void setNoticeScene(NoticeScene noticeScene) {
		this.noticeScene = noticeScene;
	}

	public String getActiveLink() {
		return activeLink;
	}

	public void setActiveLink(String activeLink) {
		this.activeLink = activeLink;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAccessManualState() {
		return accessManualState;
	}

	public void setAccessManualState(String accessManualState) {
		this.accessManualState = accessManualState;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLoginLink() {
		return loginLink;
	}

	public void setLoginLink(String loginLink) {
		this.loginLink = loginLink;
	}
}
