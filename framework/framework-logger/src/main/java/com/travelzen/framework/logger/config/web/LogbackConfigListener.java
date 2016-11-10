package com.travelzen.framework.logger.config.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * logback configuration
 *
 * @author dylan
 */
@Deprecated
public class LogbackConfigListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
//        WebLogbackConfigurer.shutdownLogging(event.getServletContext());
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		WebLogbackConfigurer.initLogging(event.getServletContext());
	}
}