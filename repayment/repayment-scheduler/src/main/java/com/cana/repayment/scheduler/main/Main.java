package com.cana.repayment.scheduler.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {

        initSpringContext();

        logger.info("融资管理定时器已启动");
        System.out.println("融资管理定时器已启动");
        
    }

    @SuppressWarnings("resource")
    private static void initSpringContext(){
        new ClassPathXmlApplicationContext(
            "classpath:spring/repayment-scheduler-*.xml"
        );
    }
}
