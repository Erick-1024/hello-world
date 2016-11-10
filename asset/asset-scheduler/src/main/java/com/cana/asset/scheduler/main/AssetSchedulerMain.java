package com.cana.asset.scheduler.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class AssetSchedulerMain {
	
	private static Logger logger = LoggerFactory.getLogger(AssetSchedulerMain.class);
	
	public static void main(String[] args) {

        initSpringContext();

        logger.info("资产管理定时器已启动");
        System.out.println("资产管理定时器已启动");
        
    }

    @SuppressWarnings("resource")
    private static void initSpringContext(){
        new ClassPathXmlApplicationContext(
            "classpath:spring/asset-scheduler-*.xml"
        );
    }

}
