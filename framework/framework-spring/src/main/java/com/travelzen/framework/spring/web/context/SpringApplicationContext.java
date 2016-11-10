package com.travelzen.framework.spring.web.context;

import org.springframework.context.ApplicationContext;

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
