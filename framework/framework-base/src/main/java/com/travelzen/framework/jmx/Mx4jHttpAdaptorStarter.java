/**
 * 
 * Description: 
 * Copyright (c) 2013
 * Company:真旅网
 * @author renshui
 * @version 1.0
 * @date 2013-8-21
 */
package com.travelzen.framework.jmx;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.travelzen.framework.core.util.RPIDLogger;

import mx4j.tools.adaptor.http.HttpAdaptor;

public class Mx4jHttpAdaptorStarter implements InitializingBean {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private HttpAdaptor httpAdaptor;
	

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			httpAdaptor.start();
			RPIDLogger.info("jmx http adapter started");
		} catch (Throwable thr) {
			logger.error("", thr);
		}
	}

}
