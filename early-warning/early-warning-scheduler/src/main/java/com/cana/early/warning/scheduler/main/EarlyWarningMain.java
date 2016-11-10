package com.cana.early.warning.scheduler.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EarlyWarningMain {
	
	private static Logger logger = LoggerFactory.getLogger(EarlyWarningMain.class);
	
	public static void main(String[] args) {
		logger.info("启动预警系统定时器");
        System.out.println("启动预警系统定时器");
        initSpringContext();
    }

    @SuppressWarnings("resource")
    private static void initSpringContext(){
        new ClassPathXmlApplicationContext(
            "classpath:spring/early-warning-scheduler-*.xml"
        );
    }
}
