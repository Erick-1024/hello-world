package com.cana.front.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;

import com.cana.front.common.util.FrontUtils;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.member.authorization.model.UserDetailsDTO;
import com.travelzen.framework.monitor.RequestIdentityHolder;

public class RequestIdentityGenerateFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		RequestIdentityHolder.init();
		String username = null;
		HttpSession session = req.getSession();
		UserDetailsDTO userDetail = null;
		SecurityContextImpl securityContext = (SecurityContextImpl)session.getAttribute(SecurityContextUtils.SPRING_SECURITY_CONTEXT);
		if(securityContext != null && securityContext.getAuthentication() != null && securityContext.getAuthentication().getPrincipal() != null){
			Authentication authentication = securityContext.getAuthentication();
			userDetail = (UserDetailsDTO) authentication.getPrincipal();
		}
		
//		UserSessionDTO userSessionDTO = SecurityContextUtils.getUserDTOFromSession();
		if (userDetail != null) {
			username = userDetail.getUserData().getUsername();
		} else {
			username = FrontUtils.getIpAddr(req);
		}
		String reqId = username + '-' + RequestIdentityHolder.ip + "-" + RequestIdentityHolder.get().getRpid();
		RequestIdentityHolder.get().setRpid(reqId);
		res.setHeader("Tops-Request-Identity", reqId);
        org.slf4j.MDC.put("rpid", String.format("[rpid=%s]", reqId));
		chain.doFilter(request, response);
        //暂时注销掉清空ThradLocal，因为此处不是AOP的环绕织入，会导致CallInfo传递不下去
//		RequestIdentityHolder.remove();
	}

	@Override
	public void destroy() {}
}
