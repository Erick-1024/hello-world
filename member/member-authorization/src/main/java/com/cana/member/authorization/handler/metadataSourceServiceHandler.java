package com.cana.member.authorization.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.cana.member.api.IPermissionApi;
import com.cana.member.authorization.common.PlatformUtils;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.member.authorization.model.SimpleConfigAttribute;
import com.cana.vbam.common.member.dto.permission.PermissionDTO;
import com.travelzen.framework.monitor.RequestIdentityLogger;

@Component("metadataSourceServiceHandler")
public class metadataSourceServiceHandler implements FilterInvocationSecurityMetadataSource, InitializingBean {

	private static final Logger LOG = RequestIdentityLogger.getLogger(metadataSourceServiceHandler.class);

    @Resource
    private IPermissionApi permissionService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        String url = getServletPath(fi);
        List<PermissionDTO> permList = SecurityContextUtils.getPermissionByUrl(url);
        
        if (CollectionUtils.isEmpty(permList)) {
        	return null;
        }

    	List<ConfigAttribute> attributes = new ArrayList<>(1);
    	attributes.add(new SimpleConfigAttribute(url));
    	return attributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        List<PermissionDTO> all = null;
		try {
			all = permissionService.getPermissionByPlatform(PlatformUtils.getPlatform());
		} catch (Exception e) {
		}
        if (CollectionUtils.isEmpty(all)) {
        	LOG.error("no permission returned from database, for platform:{}", PlatformUtils.getPlatform());
            return null;
        }
        List<ConfigAttribute> result = new ArrayList<ConfigAttribute>(all.size());
        for (PermissionDTO p : all) {
            result.add(new SimpleConfigAttribute(p.getUrl()));
        }
        return result;
    }

    @Override
    public boolean supports(Class<?> clazz) {
      return FilterInvocation.class.isAssignableFrom(clazz);
    }

    private String getServletPath(FilterInvocation fi) {
    	String url = fi.getRequest().getServletPath();
    	LOG.trace("url to authorize: {}", url);
        return url;
    }

    /**
	 * 加载所有的Permission
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		List<PermissionDTO> all = permissionService.getPermissionByPlatform(PlatformUtils.getPlatform());
		if (CollectionUtils.isEmpty(all)) {
			LOG.error("no permission returned from database, for platform:{}", PlatformUtils.getPlatform());
		} else {
			SecurityContextUtils.setAllPermissions(all);
		}
	}

}
