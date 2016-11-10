package com.cana.member.authorization.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cana.vbam.common.member.dto.user.UserSessionDTO;

public class UserDetailsDTO implements UserDetails {

    private static final long serialVersionUID = -8325343495164224189L;

    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    /** 是否是主账号 */
    private boolean isPrincipal;

    /**平台名称*/
	private String platformName;

    private Collection<GrantedAuthority> authorities;
    private Collection<String> permissions;

    private UserSessionDTO userData;
    private String companyCode;

    /** 采购商计数器 */
    private long countInMillis;

    public UserDetailsDTO(UserSessionDTO usr) {
    	this.userData = usr;
    	this.password = usr.getPassword();
    	this.username = usr.getUsername();
        accountNonLocked = true;
        credentialsNonExpired = true;
        enabled = true;
        accountNonExpired = true;
    }
    
	@Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> auths) {
        this.authorities = auths;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserSessionDTO getUserData() {
        return userData;
    }

    public Collection<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<String> permissions) {
        this.permissions = permissions;
    }

	public boolean isPrincipal() {
		return isPrincipal;
	}

	public void setPrincipal(boolean isPrincipal) {
		this.isPrincipal = isPrincipal;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public long getCountInMillis() {
		return countInMillis;
	}

	public void setCountInMillis(long countInMillis) {
		this.countInMillis = countInMillis;
	}

}
