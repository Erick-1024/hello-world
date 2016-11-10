package com.cana.member.authorization.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.GenericFilterBean;

import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.member.authorization.model.UserDetailsDTO;

public class LoginStatusFilter extends GenericFilterBean {

	private static final Logger LOG = LoggerFactory.getLogger(LoginStatusFilter.class);

	private String loginUrl;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        UserDetailsDTO tud = SecurityContextUtils.getUserFromSession();
		if (tud == null || tud.getUserData() == null) {
			 
			LOG.warn("login status is required for target resource:{}", request.getRequestURI());
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
				response.setHeader("error_type", "login_requested");
				response.setContentType("application/text; charset=utf-8");
				PrintWriter writer = response.getWriter();
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				writer.print("请重新登录");
				writer.flush();
				writer.close();
				return;
			}else{
				throw new BadCredentialsException("No pre-authenticated credentials found in request.");
//				MemberAuthUtils.deleteSIDCookie(request, response);
//				response.sendRedirect(request.getContextPath() + loginUrl);
//				return;
			}
		}

		chain.doFilter(req, res);
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

}
