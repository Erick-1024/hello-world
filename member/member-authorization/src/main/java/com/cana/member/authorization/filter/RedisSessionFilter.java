package com.cana.member.authorization.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cana.member.authorization.common.MemberAuthConstants;
import com.cana.member.authorization.common.MemberAuthUtils;
import com.cana.member.authorization.common.MemberCommonConfig;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.member.authorization.model.UserDetailsDTO;
import com.cana.member.authorization.service.MemberAuthRedisService;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.utils.Counter;

public class RedisSessionFilter implements Filter {

	private static final Logger LGR = LoggerFactory.getLogger(RedisSessionFilter.class);

	private MemberAuthRedisService redisService;

	@SuppressWarnings({ "unchecked" })
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		if ("DEV".equals(MemberCommonConfig.get("profile"))) {
			chain.doFilter(request, response);
			return;
		}

		String redisSessionKey = MemberAuthUtils.getRedisSessionKey(req);

		LGR.info("redisKey: {}", redisSessionKey);

		String lock = session.getId().intern();
		synchronized (lock) {
			if (session.getAttribute(MemberAuthConstants.REQUEST_COUNTER_PER_SESSION) == null) {
				session.setAttribute(MemberAuthConstants.REQUEST_COUNTER_PER_SESSION, new Counter());
			}
		}

		Object redisSessoin = null;
		synchronized (lock) {
			getRequestCounter(session).increase();
			try {
				redisSessoin = redisService.get(redisSessionKey);
			} catch (Throwable t) {
				LGR.error("get session(" + redisSessionKey + ") attributes form RedisServer failed." ,t);
			}
			Map<String, Object> remoteAttributes = (Map<String, Object>) redisSessoin;

			if (remoteAttributes != null && remoteAttributes.size() > 0) {
				SecurityContextImpl securityContext = (SecurityContextImpl) remoteAttributes.get(SecurityContextUtils.SPRING_SECURITY_CONTEXT);
				this.log4SessionAnalyse(securityContext, req, res);
				try{
					this.checkUsername(req, res, securityContext);
				}catch(Exception e){
					LGR.error(e.getMessage(), e);
					MemberAuthUtils.deleteSIDCookie(req, res);
					MemberAuthUtils.deleteUsernameCookie(req, res);
					res.sendRedirect(req.getContextPath());
					return ;
				}

				for (Entry<String, Object> attr : remoteAttributes.entrySet()) {
					LGR.trace("from RedisSession({}) <== {}", attr.getKey(), redisSessionKey);
					session.setAttribute(attr.getKey(), attr.getValue());
				}
				session.setAttribute(MemberAuthConstants.LOADED_FROM_REDIS, "");
				LGR.debug("{} session({}) attributes loaded from REDIS.", remoteAttributes.size(), redisSessionKey);
			} else {
				LGR.debug("no session({}) fetched from REDIS.", redisSessionKey);
			}
		}

		chain.doFilter(request, response);

		if(StringUtils.isNotBlank((String)request.getAttribute(MemberAuthConstants.NEW_REDIS_KEY))){
			redisSessionKey = (String)request.getAttribute(MemberAuthConstants.NEW_REDIS_KEY);
		}

		Enumeration<String> attributeNames = null;
		synchronized (lock) {
			try {
				getRequestCounter(session).decrease();
				attributeNames = session.getAttributeNames();
			} catch (IllegalStateException e) {
				LGR.error("get local session({}) attributes failed.", session.getId());
				LGR.error(e.getMessage(), e);
				return;
			}

			if (attributeNames != null && attributeNames.hasMoreElements()) {
				Map<String, Object> localAttributes = new HashMap<>();
				while (attributeNames.hasMoreElements()) {
					String attr = attributeNames.nextElement();
					if (MemberAuthConstants.LOADED_FROM_REDIS.equals(attr) || MemberAuthConstants.REQUEST_COUNTER_PER_SESSION.equals(attr)) {
						continue;
					}
					Object obj = session.getAttribute(attr);
					localAttributes.put(attr, obj);
					LGR.trace("to RedisSession({}) ==> {}", attr, redisSessionKey);
				}
				if (!"1".equals(request.getParameter("gds"))) {
					try {
						redisService.putSessionAttrs(redisSessionKey, localAttributes);
						LGR.debug("{} session({}) attributes saved to REDIS.", localAttributes.size(), redisSessionKey);
					} catch (Throwable t) {
						LGR.error("put session(" + redisSessionKey + ") attributes to RedisServer failed.", t);
						return; // if operation of put to redis failed, keep local attributes and return immediately
					}
				}

				// remove attributes from local session
				if (getRequestCounter(session).count() == 0) {
					for (String attr : localAttributes.keySet()) {
						session.removeAttribute(attr);
						LGR.debug("{} attributes removed from local session({}).", localAttributes.size(), redisSessionKey);
					}
					session.removeAttribute(MemberAuthConstants.LOADED_FROM_REDIS);
				}
			} else {
				if (getRequestCounter(session).count() == 0 && session.getAttribute(MemberAuthConstants.LOADED_FROM_REDIS) != null) {
					LGR.debug("all session attributes removed, remove RedisSession({}).", redisSessionKey);
					session.removeAttribute(MemberAuthConstants.LOADED_FROM_REDIS);
					redisService.remove(redisSessionKey);
				} else {
					LGR.debug("no session({}) attributes need to save in REDIS.", redisSessionKey);
				}
			}
		}
	}

	private Counter getRequestCounter(HttpSession session) {
		return (Counter) session.getAttribute(MemberAuthConstants.REQUEST_COUNTER_PER_SESSION);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		WebApplicationContext cxt = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
		redisService = (MemberAuthRedisService) cxt.getBean("memberAuthRedisService");
	}

	@Override
	public void destroy() {
	}

	private void log4SessionAnalyse(SecurityContextImpl securityContext, HttpServletRequest request, HttpServletResponse response){
		if("1".equals(request.getParameter("gds"))){
			return;
		}
		if(securityContext == null){
			LGR.error("no security context found");
			return;
		}

		Cookie usernameCookie = MemberAuthUtils.getCookie(request, MemberAuthUtils.getUsername());
		Authentication authentication = securityContext.getAuthentication();
		if(authentication == null){
			LGR.error("no authentication found");
		}
		WebAuthenticationDetails authenticationDetail = (WebAuthenticationDetails) authentication.getDetails();
		UserDetailsDTO userDetail = (UserDetailsDTO) authentication.getPrincipal();

		LGR.info("[session analyse start] IP: {} | username: {} | user agent: {} | servlet path: {} | query string: {} | authentication detail: {} | authentication principal: {}",
				MemberAuthUtils.getIpAddr(request),
				usernameCookie == null ? "null" : usernameCookie.getValue(),
				request.getHeader("user-agent"),
				request.getServletPath(),
				request.getQueryString(),
				Objects.toString(authenticationDetail),
				userDetail == null ? "no user detail found" : Objects.toString(userDetail.getUserData()));

	}

	private void checkUsername(HttpServletRequest request, HttpServletResponse response, SecurityContextImpl securityContext){
		if(securityContext == null || securityContext.getAuthentication() == null || securityContext.getAuthentication().getPrincipal() == null){
			return;
		}
		UserDetailsDTO userDetail = (UserDetailsDTO) securityContext.getAuthentication().getPrincipal();
//		WebAuthenticationDetails authenticationDetail = (WebAuthenticationDetails) securityContext.getAuthentication().getDetails();
		UserSessionDTO user = userDetail.getUserData();
		if(user == null){
			return;
		}

		//do not check if SID parameter exists
		if(StringUtils.isNotBlank(request.getParameter("SID"))){
			return;
		}

		Cookie cookie = MemberAuthUtils.getCookie(request, MemberAuthUtils.getUsername());
		if(cookie == null){
			return;
		}
		if(!user.getUsername().equals(cookie.getValue())){
			LGR.error("username not match, originName:{}, name:{}", user.getUsername(), cookie.getValue());
			throw new IllegalStateException("username not match");
		}
	}
}
