package com.cana.vbam.test.register;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@Controller
@RequestMapping("/captcha/gen")
public class CaptchaGeneratingController{
	private static final Logger LGR = LoggerFactory.getLogger(CaptchaGeneratingController.class);
	
	String CAPTCHA_SESSION_KEY = "TOPS_CAPTCHA_SESSION_KEY";
	
	private static DefaultKaptcha generator;
	
	public CaptchaGeneratingController() {
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
	
	@RequestMapping("")
	public void GenerateCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException,
			ServletException {
		HttpSession session = request.getSession();
		
		response.setDateHeader("Expires", 0);
		
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		
		response.setHeader("Pragma", "no-cache");

		response.setContentType("image/jpeg");
		
		String capText = generator.createText();
		
		session.setAttribute(CAPTCHA_SESSION_KEY, capText);
		
		BufferedImage bi = generator.createImage(capText);
		
		ServletOutputStream out = response.getOutputStream();
		
		ImageIO.write(bi, "jpg", out);
		
		try {
			out.flush();
		} finally {
			out.close();
		}
		
		if (LGR.isDebugEnabled()){
			LGR.debug("captch generated for session({})", request.getSession().getId());
		}
		
	}

}
