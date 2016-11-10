package com.cana.member.authorization.filter;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cana.member.authorization.common.MemberAuthConstants;
import com.cana.member.authorization.common.MemberAuthUtils;
import com.cana.member.authorization.common.PlatformUtils;
import com.cana.member.authorization.exception.CaptchaNotMatchException;
import com.cana.member.authorization.service.MemberAuthRedisService;
import com.travelzen.framework.core.util.StringUtil;

public class CaptchaCheckingUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final Logger LGR = LoggerFactory.getLogger(CaptchaCheckingUsernamePasswordAuthenticationFilter.class);
	private static final Pattern IP_PATTERN = Pattern.compile("^([01]?\\d{0,2}|2[0-4]\\d|25[0-5])\\.([01]?\\d{0,2}|2[0-4]\\d|25[0-5])\\.([01]?\\d{0,2}|2[0-4]\\d|25[0-5])\\.([01]?\\d{0,2}|2[0-4]\\d|25[0-5])$");
	private static final Set<String> ignoreCaptchaIps = new HashSet<>();

	@Resource
	private MemberAuthRedisService redisService;

	static {
		try (Scanner scn = new Scanner(CaptchaCheckingUsernamePasswordAuthenticationFilter.class.getResourceAsStream("/ignore-captcha"))) {
			while (scn.hasNextLine()) {
				String line = scn.nextLine();
				if (StringUtils.isBlank(line)) {
					continue;
				}
				String ip = line.trim();
				if (IP_PATTERN.matcher(ip).matches()) {
					ignoreCaptchaIps.add(ip);
				}
			}
			LGR.info("success ignoreCaptchaIps: {}", ignoreCaptchaIps.toString());
		} catch (Exception e) {
			LGR.error("failed to load ignoreCaptchaIps.", e);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		username = username == null ? "" : StringUtil.trim(username);
		password = password == null ? "" : StringUtil.trim(password);
//		String originHost = request.getParameter("origin_host");
		if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
			throw new BadCredentialsException("用户名或密码错误");
		}
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

//		Cookie ck = MemberAuthUtils.createUsernameCookie(request, response);
//		if (ck != null) {
//			response.setCharacterEncoding(URLEncoder.encode("username", "utf-8"));
//			response.addCookie(ck);
//		}

		String clientIp = MemberAuthUtils.getIpAddr(request);
		Object captcha = request.getSession().getAttribute(MemberAuthConstants.CAPTCHA_SESSION_KEY);
		request.getSession().removeAttribute(MemberAuthConstants.CAPTCHA_SESSION_KEY);

//		long loginFailureCount = 0;
//		Object objLoginFailureCount = redisService.getLoginFailureCount(request);
//		if (objLoginFailureCount != null && objLoginFailureCount.toString().matches("\\d+")) {
//			loginFailureCount = Long.parseLong(objLoginFailureCount.toString());
//		}

		if (!ignoreCaptchaIps.contains(clientIp)) {
			LGR.info("[TOPS_LOGIN] ckecking captcha for login request of {}, from {}, in {}.", username, clientIp, PlatformUtils.getPlatform());
			if (captcha == null && !ignoreCaptchaIps.contains(clientIp)) {
				throw new CaptchaNotMatchException("no captcha generated");
			}
			String captchaCode = captcha.toString();
			String inputCode = request.getParameter("captcha");
			if (!captchaCode.equalsIgnoreCase(inputCode)) {
				CaptchaNotMatchException cnme = new CaptchaNotMatchException("captcha not match");
				cnme.setAuthentication(authRequest);
				throw cnme;
			}
		} else {
			LGR.info("[TOPS_LOGIN] handling login request of {} without captcha check, from {}, in {}.", username, clientIp, PlatformUtils.getPlatform());
		}

//		String customerShortName = request.getParameter("customerShortName");
//		String rawCustomerType = request.getParameter("customerType");
//		CustomerType customerType = null;
//		if (CustomerType.COMPANY.name().equals(rawCustomerType)) {
//			customerType = CustomerType.COMPANY;
//		} else if (CustomerType.INDIVIDUAL.name().equals(rawCustomerType)) {
//			customerType = CustomerType.INDIVIDUAL;
//		}
//        username = MemberAuthUtils.combineUsernameAndCustomerShortName(username, customerShortName, customerType);


        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        Authentication result = this.getAuthenticationManager().authenticate(authRequest);

//        if(PlatformUtils.getPlatform() == Platform.PURCHASER ||PlatformUtils.getPlatform() == Platform.DISTRIBUTOR){
//        	UserDetailsDTO topsUserDetail =  (UserDetailsDTO)result.getPrincipal();
//        	PurchaserLevel purchaserLevel = topsUserDetail.getUserData().getPurchaserLevel();
//        	if(!MemberAuthUtils.matchesUsernameAndPurchaserPlatform(request, purchaserLevel)){
//        		throw new BadCredentialsException("用户名与登陆平台不符");
//        	}
//
//        }

        LGR.info("[TOPS_LOGIN] {} authenticated successfully, from {}.", username, clientIp);

        return result;

	}

}
