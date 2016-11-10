package com.cana.member.authorization.handler;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;

import com.cana.member.authorization.common.MemberAuthUtils;
import com.cana.member.authorization.exception.MemberException;
import com.cana.member.authorization.exception.MemberExceptionCode;
import com.cana.member.authorization.model.UserDetailsDTO;
import com.cana.member.authorization.service.MemberAuthUserService;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;

public class RememberMeServiceHandler extends AbstractRememberMeServices {

	private static final Logger LOG = LoggerFactory.getLogger(RememberMeServiceHandler.class);

	private boolean useSecureCookie;

	@Resource(name = "memberAuthUserService")
	private MemberAuthUserService userService;

	@Resource
	private UserDataCompleterHandler postAnthentication;

	public RememberMeServiceHandler(UserDetailsServiceHandler userDetailsService) {
		super(MemberAuthUtils.getRememberMeCookieName(), userDetailsService);
		this.setCookieName(MemberAuthUtils.getRememberMeCookieName());
	}

	@Override
	protected void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication successfulAuthentication) {
		UserDetailsDTO tud = (UserDetailsDTO) successfulAuthentication.getPrincipal();
		System.out.println(this.getTokenValiditySeconds());
		String token = userService.makeUserLogin(tud.getUserData().getId(), tud.getUserData().getUsername(), MemberAuthUtils.getIpAddr(request), 1000 * this.getTokenValiditySeconds());
		MemberAuthUtils.addRememberMeCookie(token, this.getTokenValiditySeconds(), useSecureCookie, request,
				response);
	}

	@Override
	protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request, HttpServletResponse response)
			throws RememberMeAuthenticationException, UsernameNotFoundException {
		UserSessionDTO user = null;
		try {
			Cookie ck = getCookie(request);
			if (ck == null) {
				throw new RememberMeAuthenticationException("no cookie found.");
			}
			LOG.debug("process auto login, token[{}]", ck.getValue());
			user = userService.authenticateUserToken(ck.getValue());
			
			LOG.info("User[{}] automatical login succeed", user.getUsername());
		} catch (MemberException e) {
			LOG.warn("authenticate token failed. ", e);
			if (e.getErrorCode().equals(MemberExceptionCode.USERNAME_ERROR.name())) {
				throw new UsernameNotFoundException("username not found.");
			} else {
				throw new RememberMeAuthenticationException(e.getMessage());
			}
		}
		UserDetailsDTO tud = new UserDetailsDTO(user);
		postAnthentication.check(tud);
		return tud;
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		MemberAuthUtils.removeRememberMeCookie(useSecureCookie, request, response);
	}

	private Cookie getCookie(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		for (Cookie ck : cookies) {
			if (MemberAuthUtils.getRememberMeCookieName().equals(ck.getName())) {
				return ck;
			}
		}
		return null;
	}

	public boolean getUseSecureCookie() {
		return useSecureCookie;
	}

	public void setUseSecureCookie(boolean useSecureCookie) {
		this.useSecureCookie = useSecureCookie;
	}

	public boolean rememberMeRequested(HttpServletRequest request) {
		return super.rememberMeRequested(request, this.getParameter());
	}

}
