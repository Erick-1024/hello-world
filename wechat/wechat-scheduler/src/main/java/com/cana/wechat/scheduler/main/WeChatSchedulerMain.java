package com.cana.wechat.scheduler.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WeChatSchedulerMain {
	
	private static Logger logger = LoggerFactory.getLogger(WeChatSchedulerMain.class);
	
	public static void main(String[] args) {

        initSpringContext();

        logger.info("wechat定时器已启动");
        System.out.println("wechat定时器已启动");
        
    }

    @SuppressWarnings("resource")
    private static void initSpringContext(){
        new ClassPathXmlApplicationContext(
            "classpath:spring/wechat-scheduler-*.xml"
        );
    }
}
