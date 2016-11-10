package com.cana.member.authorization.model;

import org.springframework.security.core.GrantedAuthority;

public class SimpleGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = -115863499688720322L;
	
	private String authority;

	public SimpleGrantedAuthority() {}

	public SimpleGrantedAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	@Override
	public int hashCode() {
		return authority.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof SimpleGrantedAuthority)) {
			return false;
		}
		SimpleGrantedAuthority target = (SimpleGrantedAuthority) obj;
		return authority == null ? target.authority == null : this.authority.equals(target.authority);
	}

}
