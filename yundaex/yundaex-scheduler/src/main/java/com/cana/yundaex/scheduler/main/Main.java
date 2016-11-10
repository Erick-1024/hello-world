package com.cana.yundaex.scheduler.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		logger.info("启动韵达项目定时器");
        System.out.println("启动韵达项目定时器");
        initSpringContext();
    }

    @SuppressWarnings("resource")
    private static void initSpringContext(){
        new ClassPathXmlApplicationContext(
            "classpath:spring/yundaex-scheduler-*.xml"
        );
    }
}
