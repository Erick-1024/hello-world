package com.cana.vbam.common.member.vo;

import java.io.Serializable;

/**
 * 用户信息
 * @author XuMeng
 *
 */
public class UserVo implements Serializable {
	private static final long serialVersionUID = 914854713965044679L;

	private String userId;	//用户ID
	private String username;	//登录名
	private String realname;	//用户真实名称
	private CustomerVo customer;	//用户所属客户

	public String getCustomerId() {
		return getCustomer().getCustomerId();
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public CustomerVo getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerVo customer) {
		this.customer = customer;
	}
	
}
