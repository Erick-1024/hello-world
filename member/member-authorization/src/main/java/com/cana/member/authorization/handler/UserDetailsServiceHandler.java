package com.cana.member.authorization.handler;

import javax.annotation.Resource;

import com.cana.member.api.IUserApi;
import com.cana.member.authorization.model.UserDetailsDTO;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userDetailsServiceHandler")
public class UserDetailsServiceHandler implements UserDetailsService {

    @Resource(name = "userApiImpl")
    private IUserApi userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSessionDTO user = null;
        try {
			user = userService.getUserSession(username);
		} catch (Exception e) {
	
		}
        
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found.");
        }
        return new UserDetailsDTO(user);
    }

}
