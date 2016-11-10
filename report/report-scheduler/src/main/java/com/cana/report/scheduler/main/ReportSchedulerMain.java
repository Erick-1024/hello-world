package com.cana.report.scheduler.main;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReportSchedulerMain {
	
	private static Logger logger = LoggerFactory.getLogger(ReportSchedulerMain.class);
	
	public static void main(String[] args) {
		initSpringContext();
		
		logger.info("报表定时器已启动");
		System.out.println("报表定时器已启动");
	}
	
	@SuppressWarnings("resource")
	public static void initSpringContext() {
		new ClassPathXmlApplicationContext(
				"classpath:spring/report-scheduler-*.xml"
				);
	}

}
