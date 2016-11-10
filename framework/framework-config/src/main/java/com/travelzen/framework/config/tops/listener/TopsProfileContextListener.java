package com.travelzen.framework.config.tops.listener;

import java.io.InputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.AbstractEnvironment;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

import com.travelzen.framework.config.tops.TopsConfEnum;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.core.config.PropertiesUtil;

/**
 * Created with IntelliJ IDEA.
 * User: dingguangxian
 * Date: 14-6-17
 * Time: 下午5:24
 */
public class TopsProfileContextListener implements ServletContextListener {
	private static Logger logger = LoggerFactory.getLogger(TopsProfileContextListener.class);
	private static final String TOPS_DEFAULT_PROFILE = "production";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String defaultProfile = sce.getServletContext().getInitParameter(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME);
		if (StringUtils.isBlank(defaultProfile)) {
			System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, TOPS_DEFAULT_PROFILE);
		}

		String activeProfile = "";

		try {
			activeProfile = TopsConfReader.getConfContent("properties/web-env-default.properties", AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, TopsConfEnum.ConfScope.R);
			if (StringUtils.isNotBlank(activeProfile)) {
				System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, activeProfile);
			}
		} catch (Exception e) {
			logger.warn("加载spring.profiles.active出现异常.", e);
		}

		

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}
