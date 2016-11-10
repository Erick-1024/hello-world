package com.cana.member.authorization.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import com.cana.member.authorization.common.MemberAuthUtils;
import com.cana.member.authorization.service.MemberGuideService;

public class GuideStatusFilter extends GenericFilterBean {

	private static final Logger LOG = LoggerFactory.getLogger(GuideStatusFilter.class);

	private String guideUrl;

	@Resource
	private MemberGuideService memberGuideService;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getServletPath();
        if (path.startsWith("/guide/")) {
            chain.doFilter(req, res);
            return;
        }

        if (memberGuideService.needRedirect2GuidePage()) {
            response.sendRedirect(MemberAuthUtils.buildHttpsRedirectUrlForRequest(request, guideUrl));
            return;
        }
		chain.doFilter(req, res);
	}

    public void setGuideUrl(String guideUrl) {
        this.guideUrl = guideUrl;
    }

}
