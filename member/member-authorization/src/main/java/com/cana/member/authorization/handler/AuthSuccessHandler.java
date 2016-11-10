package com.cana.member.authorization.handler;

import java.io.IOException;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.RedirectUrlBuilder;

import com.cana.member.authorization.common.AuthResult;
import com.cana.member.authorization.common.MemberAuthConfig;
import com.cana.member.authorization.common.MemberAuthConstants;
import com.cana.member.authorization.common.MemberAuthUtils;
import com.cana.member.authorization.common.MemberCommonConfig;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.member.authorization.filter.AuthResultPrintingFilter;
import com.cana.member.authorization.model.UserDetailsDTO;
import com.cana.member.authorization.service.MemberAuthRedisService;
import com.cana.member.authorization.service.MemberAuthUserService;
import com.cana.vbam.common.member.dto.permission.PermissionDTO;

public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private RememberMeServiceHandler rememberMeService;

	private static final Logger LGR = LoggerFactory.getLogger(AuthSuccessHandler.class);

	@Resource(name = "memberAuthUserService")
	private MemberAuthUserService userService;

	@Resource
	private MemberAuthRedisService redisService;

	@Resource
	private AuthResultPrintingFilter authResultPrintingFilter;

	@PostConstruct
	public void replaceRedirectStrategy() {
		super.setRedirectStrategy(new DefaultRedirectStrategy() {

			@Override
			public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url)
					throws IOException {
				url = getTargetUrl(request, url);
				String originHost = request.getParameter("origin_host");
				if (MemberAuthUtils.requestAuthResultOnly(request)) {
					
					if (StringUtils.isNotBlank(originHost)) {
//						response.sendRedirect((originHost + MemberAuthConstants.AUTH_RESULT_PRINTING_REQUEST_PATH)
//			        			.replaceAll("(?<!http:)/{2,}", "/") + "?code=" + AuthResult.SUCCESS.code());
						authResultPrintingFilter.printAuthResult(request, response, AuthResult.SUCCESS, url);
					}else {
						super.sendRedirect(request, response, url);
					}
					

				} else {
					super.sendRedirect(request, response, url);
				}
			}

		});
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		UserDetailsDTO tud = (UserDetailsDTO) authentication.getPrincipal();
		redisService.removeUsernameLoginFailureCount(request);
		redisService.removeIPLoginFailureCount(request);
		
		String userKey = tud.getUserData().getId();
		String userName = tud.getUserData().getUsername();
		if (!rememberMeService.rememberMeRequested(request)) {
			userService.makeUserLogin(userKey, userName, MemberAuthUtils.getIpAddr(request), 0);
		}
		if(!"DEV".equals(MemberCommonConfig.get("profile"))){
			Cookie cookie = MemberAuthUtils.getCookie(request, MemberAuthUtils.getSID());
			if(cookie == null){
				LGR.error("attention: user {} login without SID, this is very strange", tud.getUsername());
			}
			LGR.info("user login success, ip:{}, username:{}, SID:{}", MemberAuthUtils.getIpAddr(request), tud.getUsername(), cookie == null ? "null": cookie.getValue());
		}
		response.addCookie(MemberAuthUtils.createUsernameCookie(request, response));
		this.changeSID(request, response);
		super.onAuthenticationSuccess(request, response, authentication);
	}

	private String changeSID(HttpServletRequest request, HttpServletResponse response){
		Cookie oldCookie = MemberAuthUtils.getCookie(request, MemberAuthUtils.getSID());
		String oldSID = null;
		if(oldCookie != null){
			oldSID = oldCookie.getValue();
		}
		MemberAuthUtils.deleteSIDCookie(request, response);
		Cookie c = MemberAuthUtils.createSIDCookie(request, response);
		String newSID = c.getValue();
		LGR.info("change SID {} to {}", oldSID, newSID);
		String newRedisKey = MemberAuthUtils.getRedisSessionKey(newSID);
		String oldRedisKey = MemberAuthUtils.getRedisSessionKey(oldSID);
		LGR.info("add to redis start=============================");
		try{
			this.redisService.putSessionAttrs(newRedisKey, this.redisService.get(oldRedisKey));
			this.redisService.remove(oldRedisKey);
		}catch (Exception e) {
			LGR.error("add to redis start=============================exception" , e);
		}
		LGR.debug("change redisSessionKey from {} to {}", oldRedisKey, newRedisKey);
		request.setAttribute(MemberAuthConstants.NEW_REDIS_KEY, newRedisKey);
		return newSID;
	}

	public void setRememberMeService(RememberMeServiceHandler rememberMeService) {
		this.rememberMeService = rememberMeService;
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	private String getTargetUrl(HttpServletRequest request, String url){
		if(StringUtils.isNotBlank(url) && checkUrlEqualTargetUrl(request, url)){
			Set<PermissionDTO> permissions = SecurityContextUtils.getUserPermission();
			if(CollectionUtils.isNotEmpty(permissions)){
				String newUrl = "";
				int countRootPer = 0;
				for(PermissionDTO permission : permissions){
					if(StringUtils.isBlank(permission.getParentId()))
						++countRootPer;
					if(StringUtils.isBlank(newUrl) && StringUtils.isNotBlank(permission.getUrl()))
						newUrl = permission.getUrl();
				}
				if(StringUtils.isNotBlank(newUrl) && countRootPer <= 3)
					return newUrl;
			}
		}
		return url;
	}
	
	private boolean checkUrlEqualTargetUrl(HttpServletRequest request, String url){
		if(url.contains("http")){
			return url.equals(buildUrl(request, getDefaultTargetUrl()));
		}
		return url.equals(getDefaultTargetUrl());
	}
	
	private String buildUrl(HttpServletRequest request, String url){
		RedirectUrlBuilder urlBuilder = new RedirectUrlBuilder();

		urlBuilder.setScheme(request.getScheme());
		urlBuilder.setPort(request.getServerPort());

        urlBuilder.setServerName(request.getServerName());
        urlBuilder.setContextPath(request.getContextPath());
        urlBuilder.setServletPath(url);
//        urlBuilder.setPathInfo(request.getPathInfo());
        
        return urlBuilder.getUrl();
	}
}
