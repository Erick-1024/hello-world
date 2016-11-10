package com.travelzen.framework.logger.config.web;

import javax.servlet.ServletContext;

import ch.qos.logback.core.joran.spi.JoranException;

import com.travelzen.framework.logger.config.LogbackConfigurer;

/**
 * logback config
 *
 * @author dylan
 */
@Deprecated
public class WebLogbackConfigurer {

	public static final String DEFAULT_LOGBACK_FILE = "logback-travelzen.xml";
	public static final String PARAM_LOGBACK_CONFIG_LOCATION = "logbackConfigLocation";

	private WebLogbackConfigurer() {
	}

	/**
	 * Initialize Logback,
	 *
	 * @param servletContext the current ServletContext
	 */
	public static void initLogging(ServletContext servletContext) {
		String configFile = servletContext.getInitParameter(PARAM_LOGBACK_CONFIG_LOCATION);
		if (configFile != null && configFile.trim().length() > 0) {
			configFile = configFile.trim();
		} else {
			configFile = DEFAULT_LOGBACK_FILE;
		}
		// Perform actual Logback initialization; else rely on Logback's default initialization.
		servletContext.log("Initializing Logback from location:" + configFile);
		LogbackConfigurer.setSystemLoggerURL(configFile);
		try {
			LogbackConfigurer.getInstance().reloadDefaultConfiguration();
		} catch (JoranException e) {
			servletContext.log("Initializing Logback fail." + e.getLocalizedMessage());
		}
	}

}
