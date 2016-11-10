package com.cana.member.authorization.handler;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.cana.member.authorization.common.AuthResult;
import com.cana.member.authorization.common.MemberAuthConstants;
import com.cana.member.authorization.common.MemberAuthUtils;
import com.cana.member.authorization.exception.CaptchaNotMatchException;
import com.cana.member.authorization.filter.AuthResultPrintingFilter;

public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	private static final Logger LGR = LoggerFactory.getLogger(AuthFailureHandler.class);
	
	@Resource
	private AuthResultPrintingFilter authResultPrintingFilter;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		String originHost = request.getParameter("origin_host");
		AuthResult ar = null;
		if (exception instanceof UsernameNotFoundException ||
				exception instanceof BadCredentialsException) {
			ar = AuthResult.USERNAME_OR_PASSWORD_ERROR;
		} else if (exception instanceof LockedException) {
			ar = AuthResult.LOCKED;
		} else if (exception instanceof AccountExpiredException) {
			ar = AuthResult.EXPIRED;
		} else if (exception instanceof CaptchaNotMatchException) {
			ar = AuthResult.CAPTCHA_ERROR;
		} else if (exception != null) {
			ar = AuthResult.UNKOWN_ERROR;
		}
		if (MemberAuthUtils.requestAuthResultOnly(request)) {
//			if (StringUtils.isNotBlank(originHost)) {
//				response.sendRedirect((originHost + MemberAuthConstants.AUTH_RESULT_PRINTING_REQUEST_PATH)
//						.replaceAll("(?<!http:)/{2,}", "/") + "?code=" + ar.code());
//			} else {
				authResultPrintingFilter.printAuthResult(request, response, ar, null);
//			}
		} else {
			super.onAuthenticationFailure(request, response, exception);
		}
	}
	
}
