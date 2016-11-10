package com.cana.member.authorization.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.cana.member.authorization.common.MemberAuthUtils;

public class SIDFilter extends GenericFilterBean{

	private static final Logger LGR = LoggerFactory.getLogger(SIDFilter.class);

	private static final Marker marker = MarkerFactory.getMarker("SID_CHECK");

	private static final String SID_PARAM = "SID";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		try{
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;

//			if(req.isRequestedSessionIdFromURL()){
//				MultipartResolver resolver=new CommonsMultipartResolver();
//				if(!resolver.isMultipart(req)){
//					String error = "请求地址中包含非法信息，为了您的资料安全，本次请求被拒绝。";
//					request.setAttribute("exception", error);
//					throw new ServletException(error);
//				}
//			}
			String sid = req.getParameter(SID_PARAM);

			if (StringUtils.isNotBlank(sid)) {
				LGR.debug(marker, "get cookie from request parameter.");
				//overwrite sid and username cookies, if request come with SID parameter
				MemberAuthUtils.createCookie(MemberAuthUtils.getSID(), sid, (HttpServletRequest)request, (HttpServletResponse)response);
				Cookie usernameCookie = MemberAuthUtils.createUsernameCookie((HttpServletRequest) request, (HttpServletResponse) response);
				if(usernameCookie != null){
					((HttpServletResponse) response).addCookie(usernameCookie);
				}
			}

			if (StringUtils.isBlank(sid)){
				Cookie cookie = MemberAuthUtils.getCookie(req, MemberAuthUtils.getSID());
				if (cookie != null) {
				    sid = cookie.getValue();
				}
			}

			if (StringUtils.isBlank(sid)) {
			    LGR.debug(marker, "no sid cookie found, create a new one.");
			    MemberAuthUtils.createSIDCookie(req, res);
			}

			chain.doFilter(request, response);
		}catch(Exception e){
			LGR.error("Filter调用发生错误", e);
		}finally{
			SecurityContextHolder.clearContext();
		}
	}
}
