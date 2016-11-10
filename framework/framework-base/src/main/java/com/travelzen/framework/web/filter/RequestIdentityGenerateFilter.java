package com.travelzen.framework.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String reqId = RequestIdentityHolder.ip + "-" + RequestIdentityHolder.get().getRpid();
		
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
