package com.cana.account.scheduler.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * server启动类, 
 * @author renshui
 *
 */
public class AccountSchedulerMain {
    private static Logger logger = LoggerFactory.getLogger(AccountSchedulerMain.class);

    public static void main(String[] args) {

        initSpringContext();

        logger.info("账户定时器已启动");
        System.out.println("账户定时器已启动");
    }

    @SuppressWarnings("resource")
    private static void initSpringContext(){
        new ClassPathXmlApplicationContext(
            "classpath:spring/account-scheduler-*.xml"
        );
    }
}
