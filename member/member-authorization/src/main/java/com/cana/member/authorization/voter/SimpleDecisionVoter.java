package com.cana.member.authorization.voter;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.member.authorization.model.SimpleConfigAttribute;
import com.cana.member.authorization.model.UserDetailsDTO;
import com.cana.vbam.common.member.dto.permission.PermissionDTO;
import com.travelzen.framework.monitor.RequestIdentityLogger;

public class SimpleDecisionVoter implements AccessDecisionVoter<FilterInvocation> {

	private static final Logger LOG = RequestIdentityLogger.getLogger(SimpleDecisionVoter.class);

	@Override
	public boolean supports(ConfigAttribute attribute) {
	    return attribute instanceof SimpleConfigAttribute;
	}

	@Override
	public boolean supports(Class<?> clazz) {
        return true;
	}

	@Override
	public int vote(Authentication authentication, FilterInvocation fltIvktn,
			Collection<ConfigAttribute> attributes) {
		UserDetailsDTO ud = null;
		try{
			ud = (UserDetailsDTO) authentication.getPrincipal();
		}catch(ClassCastException e){
			throw new BadCredentialsException("No pre-authenticated credentials found in request.");
		}
				
		HttpServletRequest request = fltIvktn.getRequest();
		
		if (CollectionUtils.isEmpty(attributes)) {
			LOG.debug("无权限可认证。");
			return ACCESS_GRANTED;
		}
		if (authentication == null || authentication.getAuthorities() == null
				|| authentication.getAuthorities().size() == 0) {
			LOG.debug("当前用户无任何权限。");
			request.setAttribute(SecurityContextUtils.DENIED_MESSAGE, "user has no permission.");
			return ACCESS_DENIED;
		}
		for (ConfigAttribute ca : attributes) {
			if(CollectionUtils.isNotEmpty(ud.getPermissions())){
				for(String key : ud.getPermissions()){
					PermissionDTO permission = SecurityContextUtils.getPermissionByKey(key);
					if(null != permission){
						if(ca.getAttribute().equals(permission.getUrl())){
							LOG.debug("认证通过：{}", ca.getAttribute());
					        return ACCESS_GRANTED;
						}
					}
				}
			}
		}
		LOG.debug("认证失败。");
		request.setAttribute(SecurityContextUtils.DENIED_MESSAGE, "authentication failed for:" + attributes);
		return ACCESS_DENIED;
	}

}
