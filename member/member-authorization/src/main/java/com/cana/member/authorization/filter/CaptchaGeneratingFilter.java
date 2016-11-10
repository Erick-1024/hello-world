package com.cana.member.authorization.filter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import com.cana.member.authorization.common.MemberAuthConstants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

public class CaptchaGeneratingFilter extends GenericFilterBean {

	private static final Logger LGR = LoggerFactory.getLogger(GenericFilterBean.class);

	private static DefaultKaptcha generator;

	private String requestPath = "/captcha/gen";

	public CaptchaGeneratingFilter() {
		Properties ppt = new Properties();
		ppt.put("kaptcha.border", "no");
		ppt.put("kaptcha.textproducer.font.color", "88,105,147");
		ppt.put("kaptcha.textproducer.char.length", "4");
		ppt.put("kaptcha.background.clear.from", "white");
		ppt.put("kaptcha.background.clear.to", "white");
		Config config = new Config(ppt);
		generator = new DefaultKaptcha();
		generator.setConfig(config);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (!requiresCaptchaGenerating(request, response)) {
        	chain.doFilter(req, res);
        	return;
        }

        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);

        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = generator.createText();

        // store the text in the session
        session.setAttribute(MemberAuthConstants.CAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bi = generator.createImage(capText);
        ServletOutputStream out = response.getOutputStream();

        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }

        if (LGR.isDebugEnabled()) {
        	LGR.debug("captch generated for session({})", request.getSession().getId());
        }

	}

    private boolean requiresCaptchaGenerating(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        int pathParamIndex = uri.indexOf(';');

        if (pathParamIndex > 0) {
            // strip everything after the first semi-colon
            uri = uri.substring(0, pathParamIndex);
        }

        if (request.getContextPath().length() == 0) {
            return uri.endsWith(requestPath);
        }

        return uri.endsWith(request.getContextPath() + requestPath);
    }

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

}
