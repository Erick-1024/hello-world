package com.cana.member.authorization.handler;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.cana.member.authorization.common.MemberAuthUtils;
import com.cana.member.authorization.model.UserDetailsDTO;
import com.cana.member.authorization.service.MemberAuthRedisService;
import com.cana.member.authorization.service.MemberAuthUserService;

@Component("logoutSuccessHandler")
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

	private static final Logger LOG = LoggerFactory.getLogger(UserLogoutSuccessHandler.class);

	private String logoutSuccessUrl;

	@Resource(name = "memberAuthUserService")
	private MemberAuthUserService userService;

	@Resource(name = "memberAuthRedisService")
	private MemberAuthRedisService redisService;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		MemberAuthUtils.deleteSIDCookie(request, response);
		MemberAuthUtils.deleteUsernameCookie(request, response);
		if (authentication == null || authentication.getPrincipal() == null
				|| !(authentication.getPrincipal() instanceof UserDetailsDTO)) {
			response.sendRedirect(MemberAuthUtils.buildHttpsRedirectUrlForRequest(request, null));
			return;
		}
		UserDetailsDTO tud = (UserDetailsDTO) authentication.getPrincipal();
		userService.logoutUpateUserLoginInfo(tud.getUserData().getId());
		redisService.remove(MemberAuthUtils.getRedisSessionKey(request));
		LOG.info("User[{}] signed out successfully.", tud.getUsername());
		if (logoutSuccessUrl == null) {
			response.sendRedirect(MemberAuthUtils.buildHttpsRedirectUrlForRequest(request, null));
		} else {
			response.sendRedirect(MemberAuthUtils.buildHttpsRedirectUrlForRequest(request, logoutSuccessUrl));
		}
	}

	public String getLogoutSuccessUrl() {
		return logoutSuccessUrl;
	}

	public void setLogoutSuccessUrl(String logoutSuccessUrl) {
		this.logoutSuccessUrl = logoutSuccessUrl;
	}

}
