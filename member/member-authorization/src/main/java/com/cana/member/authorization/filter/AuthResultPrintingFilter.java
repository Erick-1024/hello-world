package com.cana.member.authorization.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.filter.GenericFilterBean;

import com.cana.member.authorization.common.AuthResult;
import com.cana.member.authorization.common.MemberAuthConstants;
import com.cana.member.authorization.common.MemberAuthUtils;
import com.cana.member.authorization.service.MemberAuthRedisService;

public class AuthResultPrintingFilter extends GenericFilterBean {

	@Resource
	private MemberAuthRedisService redisService;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		if (requiresToPrintAuthResult(request, response)) {
			AuthResult ar = AuthResult.UNKOWN_ERROR;
			String authResultCodeStr = request.getParameter("code");
			if (StringUtils.isNotBlank(authResultCodeStr) && authResultCodeStr.matches("\\d+")) {
				int code = Integer.parseInt(authResultCodeStr);
				ar = AuthResult.valueOf(code);
			}
			printAuthResult(request, response, ar, null);
		} else {
			chain.doFilter(request, response);
		}
	}

	public void printAuthResult(HttpServletRequest request, HttpServletResponse response, AuthResult ar, String url) throws IOException {
		String exceptionMessage = null;
		if (ar == AuthResult.UNKOWN_ERROR) {
			Object exp = request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			if (exp != null && exp instanceof Exception) {
				Exception e = (Exception) exp;
				exceptionMessage = e.getMessage();
				exceptionMessage += "\n";
				exceptionMessage += MemberAuthUtils.serializeStackTrace(e);
			}
		}

		String body = null;
		if (ar == AuthResult.SUCCESS) {
			body = "[AUTHRESULT[" + ar.name() + ";" + url +"]]";
		} else {
			Long loginFailureCount = redisService.getLoginFailureCount(request);
			body = "[AUTHRESULT[" + ar.name() + ":" + loginFailureCount + "]]";
		}
		if (exceptionMessage != null) {
			body += "<br/>";
			body += exceptionMessage;
		}
		response.getWriter().write(buildHtmlPage(body));
		response.getWriter().flush();
		response.getWriter().close();
	}

    private boolean requiresToPrintAuthResult(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        int pathParamIndex = uri.indexOf(';');

        if (pathParamIndex > 0) {
            // strip everything after the first semi-colon
            uri = uri.substring(0, pathParamIndex);
        }

        if (request.getContextPath().length() == 0) {
            return uri.endsWith(MemberAuthConstants.AUTH_RESULT_PRINTING_REQUEST_PATH);
        }

        return uri.endsWith(request.getContextPath() + MemberAuthConstants.AUTH_RESULT_PRINTING_REQUEST_PATH);
    }

    private String buildHtmlPage(String body) {
		return "<html><head><script>window.parent.window.signinCallback('" + body + "')</script></head></html>";
    }

}
