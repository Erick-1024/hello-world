package com.cana.vbam.common.member.dto.user;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cana.vbam.common.member.enums.user.UserGuideStatus;
import com.cana.vbam.common.member.enums.user.UserStatus;
import com.cana.vbam.common.member.enums.user.UserType;

public class UserSessionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6456685936394719888L;

	/**
    *id
    */
    private String id;
	
	/**
    *用户名
    */
    private String username;
    
    /**
     * 公司名称
     */
    private String companyName;
    
    /**
    *员工姓名
    */
    private String realName;
    
    /**
    *密码
    */
    private String password;
    
    /**
    *主账号Id
    */
    private String masterId;
    
    /**
    *用户类型（保理商，融资商）
    */
    private UserType userType;
    
    /**
    *用户状态
    */
    private UserStatus userStatus;
    
    /**
    *用户所属角色
    */
    private List<String> roleIdList;
    
    /**
    *随机字符串，用于记住登录功能
    */
    private String token;
    
    /**
    *是否已登陆
    */
    private Boolean signedin;
    
    /**
    *最后登录时间
    */
    private Date signinTime;

    /**
    *最后登陆Ip
    */
    private String signinIp;
    
    /**
     * 个性权限
     */
    private String permissions;

    private Set<UserGuideStatus> guideStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getSignedin() {
		return signedin;
	}

	public void setSignedin(Boolean signedin) {
		this.signedin = signedin;
	}

	public Date getSigninTime() {
		return signinTime;
	}

	public void setSigninTime(Date signinTime) {
		this.signinTime = signinTime;
	}

	public String getSigninIp() {
		return signinIp;
	}

	public void setSigninIp(String signinIp) {
		this.signinIp = signinIp;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Set<UserGuideStatus> getGuideStatus() {
		return guideStatus;
	}

	public void setGuideStatus(Set<UserGuideStatus> guideStatus) {
		this.guideStatus = guideStatus;
	}
	
	public void addGuideStatus(UserGuideStatus guideStatus) {
		if(this.guideStatus == null){
			this.guideStatus = new HashSet<UserGuideStatus>();
		}
		this.guideStatus.add(guideStatus);
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
}
