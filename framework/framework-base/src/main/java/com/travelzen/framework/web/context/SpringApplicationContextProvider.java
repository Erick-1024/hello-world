package com.travelzen.framework.web.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *   use classs in   framework-spring  instead
 * @author liang.wang
 *
 */
@Deprecated


public class SpringApplicationContextProvider implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		SpringApplicationContext.setApplicationContext(ctx);
	}

}
