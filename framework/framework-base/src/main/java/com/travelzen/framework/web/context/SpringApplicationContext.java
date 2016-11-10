package com.travelzen.framework.web.context;

import org.springframework.context.ApplicationContext;

/**
 *   use classs in   framework-spring  instead
 * @author liang.wang
 *
 */
@Deprecated

public class SpringApplicationContext {
	private static ApplicationContext ctx;

	public static void setApplicationContext(ApplicationContext applicationContext) {
		ctx = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return ctx;
	}

	private SpringApplicationContext() {}

}
