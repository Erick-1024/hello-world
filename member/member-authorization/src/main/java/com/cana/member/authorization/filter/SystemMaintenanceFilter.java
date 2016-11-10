package com.cana.member.authorization.filter;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import com.travelzen.framework.config.tops.TopsConfReader;
import com.cana.member.authorization.common.MemberAuthUtils;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;

/**
 * @author hu
 *
 */
public class SystemMaintenanceFilter extends GenericFilterBean {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private static final Pattern IP_PATTERN = Pattern.compile("^([01]?\\d{0,2}|2[0-4]\\d|25[0-5])\\.([01]?\\d{0,2}|2[0-4]\\d|25[0-5])\\.([01]?\\d{0,2}|2[0-4]\\d|25[0-5])\\.([01]?\\d{0,2}|2[0-4]\\d|25[0-5])$");
	private static final String MAINTENANCE_URL = "/facade/maintenance";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		boolean isMaintain = true;

		Properties ppts = null;
		try {
			ppts = TopsConfReader.getConfProperties(Constants.MAINTENANCE_PATH, ConfScope.G);
		} catch (Exception e) {
			log.error("load properties(" + Constants.MAINTENANCE_PATH + ") failed." ,e);
		}
		
		if(null != ppts){
			String isMaintaining = ppts.getProperty(Constants.IS_MAINTAINING);
			if("true".equals(isMaintaining)){
				String whiteIps = ppts.getProperty(Constants.MAINTENANCE_WHITE_IP);
				if(StringUtils.isNotBlank(whiteIps)){
					String[] ipArry = whiteIps.split(",");
					String clientIp = MemberAuthUtils.getIpAddr((HttpServletRequest)request);
					for(String ip : ipArry){
						if(IP_PATTERN.matcher(ip).matches() && clientIp.equals(ip)){
							isMaintain = false;
							break;
						}
					}
				}
			}else{
				isMaintain = false;
			}
				
		}else{
			isMaintain = false;
		}
		
		if(isMaintain && !requiresMaintainingUrl((HttpServletRequest)request, (HttpServletResponse)response)){
			sendRedirect((HttpServletRequest)request, (HttpServletResponse)response, MAINTENANCE_URL);
		}else if(!isMaintain && requiresMaintainingUrl((HttpServletRequest)request, (HttpServletResponse)response)){
			sendRedirect((HttpServletRequest)request, (HttpServletResponse)response, null);
		}
		chain.doFilter(request, response);
	}

	private void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        String redirectUrl = url;
        redirectUrl = response.encodeRedirectURL(redirectUrl);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + redirectUrl);
            return;
        }
        
        if (logger.isDebugEnabled()) {
            logger.debug("Redirecting to '" + redirectUrl + "'");
        }

        response.sendRedirect(MemberAuthUtils.buildHttpsRedirectUrlForRequest(request, redirectUrl));
    }
	
	private boolean requiresMaintainingUrl(HttpServletRequest request, HttpServletResponse response) {
        String url = request.getRequestURI();
        int pathParamIndex = url.indexOf(';');

        if (pathParamIndex > 0) {
            // strip everything after the first semi-colon
        	url = url.substring(0, pathParamIndex);
        }

        if (request.getContextPath().length() == 0) {
            return url.endsWith(MAINTENANCE_URL);
        }
        
        log.info("now url :{}, maintenance url :{}", url, MAINTENANCE_URL);
        return url.endsWith(request.getContextPath() + MAINTENANCE_URL);
    }

}
