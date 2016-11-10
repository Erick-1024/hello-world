package com.travelzen.framework.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.config.tops.util.TopsAppRegistry;

public class TestLogServer {
	private static final Logger logger = LoggerFactory.getLogger(TestLogServer.class);
	public static void main(String[] args) throws InterruptedException {

		TopsAppRegistry.setApplicationName("TestLogServer");
		
		while(true){
			logger.debug("sleeping");
			Thread.sleep(1000);
		}

//		Thread.currentThread().join();
	}

}
