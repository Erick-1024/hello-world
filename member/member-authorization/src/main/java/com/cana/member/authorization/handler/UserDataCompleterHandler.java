package com.cana.member.authorization.handler;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

import com.cana.member.api.IPermissionApi;
import com.cana.member.authorization.model.SimpleGrantedAuthority;
import com.cana.member.authorization.model.UserDetailsDTO;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.google.common.collect.Lists;

@Component("postAnthenticationHandler")
public class UserDataCompleterHandler implements UserDetailsChecker {

    private static final Logger LOG = LoggerFactory.getLogger(UserDataCompleterHandler.class);

    @Resource(name = "permissionApiImpl")
    private IPermissionApi permissionService;

    @Override
    public void check(UserDetails toCheck) {
    	completeUserData(toCheck);
    }

    private void completeUserData(UserDetails ud) {
        UserDetailsDTO tud = (UserDetailsDTO) ud;
        tud.setCountInMillis(0);
        fillPermissionData(tud);
    }
	
	private void fillPermissionData(UserDetailsDTO ud) {
		// Generating authorities information.
        Collection<GrantedAuthority> authorities = new HashSet<>();
    	//角色拥有的权限
    	if (CollectionUtils.isNotEmpty(ud.getUserData().getRoleIdList())) {
			List<String> permissionKeyList = Lists.newArrayList();
			try {
				UserSessionDTO userDTO = ud.getUserData();
				Set<String> permissionSet = new HashSet<>();
				for(String roleId : userDTO.getRoleIdList()){
					permissionSet.addAll(permissionService.getPermissionKeyByRole(roleId));
				}
				if(StringUtils.isNotBlank(userDTO.getPermissions())){
					for(String key : userDTO.getPermissions().split(";")){
						if(StringUtils.isNotBlank(key))
							permissionSet.add(key);
					}
				}
				permissionKeyList.addAll(permissionSet);
				
				for (String auth : permissionKeyList) {
		            authorities.add(new SimpleGrantedAuthority(auth));
		        }
		        
		        ud.setPermissions(permissionKeyList);
		        ud.setAuthorities(authorities);
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("permissions find by role fail");
			}
    		
			
        }
        
	}

}
