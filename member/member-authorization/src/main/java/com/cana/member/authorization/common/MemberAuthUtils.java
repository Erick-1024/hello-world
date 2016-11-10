package com.cana.member.authorization.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.RedirectUrlBuilder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cana.member.authorization.model.UserDetailsDTO;
import com.cana.vbam.common.enums.Platform;


/**
 *
 * @since Nov 7, 201511:51:04 AM
 * @author dev1
 *
 */
public class MemberAuthUtils { 
	
	private static final Logger LGR = LoggerFactory.getLogger(MemberAuthUtils.class);
	
	private static final Pattern pattern = Pattern.compile("\\w+?Session[\\w-]+");
	
	public static Map<Platform, String> PLATFORM_SID = new HashMap<Platform, String>();
	
//	private static final String SID_PARAM = "SID";
	
	static{
		PLATFORM_SID.put(Platform.BIZ, "SID");
	}
	
	public static void addRememberMeCookie(String token, int maxAge, boolean useSecure, HttpServletRequest request,
			HttpServletResponse response) {
		Cookie cookie = new Cookie(getRememberMeCookieName(), token);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
		cookie.setDomain(secLevelDomain(request.getRequestURL().toString()));
		cookie.setSecure(useSecure);
		response.addCookie(cookie);
	}
	
	public static String getRememberMeCookieName() {
		return MemberAuthConstants.COOKIE_KEY_TOKEN;
	}
	
	public static void removeRememberMeCookie(boolean useSecure, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie(getRememberMeCookieName(), "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		cookie.setDomain(secLevelDomain(request.getRequestURL().toString()));
		cookie.setSecure(useSecure);
		response.addCookie(cookie);
	}
	
	public static Cookie createSIDCookie(HttpServletRequest request, HttpServletResponse response){
		String sid = UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
		Cookie cookie = createCookie(MemberAuthUtils.getSID(), sid, request, response);
		LGR.info("create SID cookie, sid={}", sid);
		return cookie;
	}
	
	public static void deleteSIDCookie(HttpServletRequest request, HttpServletResponse response){
		MemberAuthUtils.deleteCookie(MemberAuthUtils.getSID(), request, response);
	}
	
	public static Cookie createUsernameCookie(HttpServletRequest request, HttpServletResponse response){
		UserDetailsDTO userDetail = SecurityContextUtils.getUserFromSession();
		String username = null;
		if(userDetail == null || userDetail.getUserData() == null){
			username = request.getParameter("username");
		} else {
			username = userDetail.getUserData().getUsername();
		}
		if (StringUtils.isBlank(username)) {
			return null;
		}
		return createCookie(MemberAuthUtils.getUsername(), username, request, response);
	}
	
	public static void deleteUsernameCookie(HttpServletRequest request, HttpServletResponse response){
		MemberAuthUtils.deleteCookie(MemberAuthUtils.getUsername(), request, response);
	}
	
	public static void deleteCookie(String name, HttpServletRequest request, HttpServletResponse response){
		if(StringUtils.isBlank(name)){
			return;
		}
		Cookie c = new Cookie(StringUtils.trimToEmpty(name), "");
		c.setHttpOnly(true);
		c.setSecure(false);
		c.setPath("/");
		c.setDomain(MemberAuthUtils.secLevelDomain(request.getRequestURL().toString()));
		c.setMaxAge(0);
		response.addCookie(c);
		LGR.info("delete cookie, ip:{}, name:{}", getIpAddr(request), name);
	}
	
	public static Cookie createCookie(String name, String value, HttpServletRequest request, HttpServletResponse response){
		if(StringUtils.isBlank(name)){
			return null;
		}
		Cookie c = new Cookie(StringUtils.trimToEmpty(name), StringUtils.trimToEmpty(value));
		c.setHttpOnly(true);
		c.setSecure(false);
		c.setPath("/");
		c.setDomain(MemberAuthUtils.secLevelDomain(request.getRequestURL().toString()));
		LGR.info("create new cookie, ip:{}, name:{}, value:{}", getIpAddr(request), c.getName(), c.getValue());
		response.addCookie(c);
		return c;
	}
	
	public static String secLevelDomain(String url) {
		String host = url.replaceAll("https?:\\/{2,}", "").replaceAll("(:\\d*)?(\\/.*)?", "");
		if (host.indexOf('.') < 0 || host.indexOf('.') == host.lastIndexOf('.')) {
			return host;
		} else {
			//判断是否为ip地址,如果是ip地址就直接返回,以ip作为作用域
			if(isIP(host)){
				return host;
			}
			//目前只考虑包含.cn顶级域名的情况，其他顶级域名暂未考虑，如有需要会做进一步改进（InternetDomainName）
			int idx = 0;
			if (host.endsWith(".com.cn")) {
				idx = host.lastIndexOf(".com.cn");
			}else {
		    	idx = host.lastIndexOf('.');
			}
	    	idx = host.lastIndexOf('.', --idx);
	    	return host.substring(++idx);
		}
	}
	
	public static boolean isIP(String addr)
    {
      if(addr.length() < 7 || addr.length() > 15 || "".equals(addr))
      {
        return false;
      }
      /**
       * 判断IP格式和范围
       */
      String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
      
      Pattern pat = Pattern.compile(rexp);  
      
      Matcher mat = pat.matcher(addr);  
      
      boolean ipAddress = mat.find();

      return ipAddress;
    }
	
	
	public static String getRedisSessionKey(HttpServletRequest request){
		if(request == null){
			return "";
		}
		String key = request.getParameter(getSID());
		if (key == null) {
			Cookie cookie = MemberAuthUtils.getCookie(request, MemberAuthUtils.getSID());
			key = cookie != null ? cookie.getValue() : "";
		}
		return PlatformUtils.getPlatform().name() + "Session" + key;
	}
	
	public static String getRedisSessionKey(String SID){
		return PlatformUtils.getPlatform().name() + "Session" + StringUtils.trimToEmpty(SID);
	}
	
	public static String getSID(){
		return PLATFORM_SID.get(PlatformUtils.getPlatform());
	}
	
	public static Cookie getCookie(HttpServletRequest request, String name){
		Cookie returnCookie = null;
		Cookie[] cookies = request.getCookies();
		if (!ArrayUtils.isEmpty(cookies)) {
			for (Cookie cookie : cookies) {
				if (name.equalsIgnoreCase(cookie.getName())) {
					returnCookie = cookie;
				}
			}
		}else{
			if(MemberAuthUtils.getSID().equals(name) && MemberAuthUtils.isUploading(request)){
				return new Cookie(MemberAuthUtils.getSID(), request.getParameter(MemberAuthConstants.UPLOAD_SID));
			}
		}
		return returnCookie;
	}
	
	public static boolean isUploading(HttpServletRequest request){
		if (StringUtils.startsWithIgnoreCase(request.getContentType(), "multipart/")) {
			return StringUtils.isNotBlank(request.getParameter(MemberAuthConstants.UPLOAD_SID));
		}
		return false;
	}
	
	public static String getUsername(){
		Platform platform = PlatformUtils.getPlatform();
		String prefix = "username";
		switch(platform){
		case BIZ:
			return prefix;
		default:
			return prefix + "0";
		}
	}
	
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static boolean isValidRediskey(String key){
	    if (StringUtils.isBlank(key)) {
	        LGR.warn("blank redis key is not allowed!");
	        return false;
	    }
	    boolean isValid = false;
	    for (Platform p : Platform.values()) {
	        if (key.startsWith(p.name())) {
	            isValid = true;
	            break;
	        }
	    }
	    if (isValid) {
	        isValid = pattern.matcher(key).matches();
	    }
	    if (!isValid) {
	        LGR.warn("redis key[{}] invalid!", key);
	    }
		return isValid;
	}
	/**
	 * 获取request
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	public static boolean requestAuthResultOnly(HttpServletRequest request) {
		return "true".equals(request.getParameter("auth_result_only"));
	}
	
	public static String serializeStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		String result = "";
		try (PrintWriter pw = new PrintWriter(sw);) {
			t.printStackTrace(pw);
			result = sw.toString();
		} catch (Exception e) {
			LGR.error("", e);
		}
		return result;
	}
	
	public static String buildHttpsRedirectUrlForRequest(HttpServletRequest request, String redirectUrl){
		RedirectUrlBuilder urlBuilder = new RedirectUrlBuilder();
		if("true".equals(MemberAuthConfig.get(MemberAuthConstants.FORCE_HTTPS)) && "http".equals(request.getScheme())){
			urlBuilder.setScheme("https");
			urlBuilder.setPort(443);
		}else{
			urlBuilder.setScheme(request.getScheme());
			urlBuilder.setPort(request.getServerPort());
		}

        urlBuilder.setServerName(request.getServerName());
        urlBuilder.setContextPath(request.getContextPath());
        urlBuilder.setServletPath(redirectUrl);
//        urlBuilder.setPathInfo(request.getPathInfo());
        
        return urlBuilder.getUrl();
    }
}
