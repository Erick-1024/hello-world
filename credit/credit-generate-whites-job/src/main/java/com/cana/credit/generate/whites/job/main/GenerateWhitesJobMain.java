package com.cana.credit.generate.whites.job.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cana.credit.generate.whites.job.service.GenerateWhitesService;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;



public class GenerateWhitesJobMain {
	
	public static void main(String args[]) {
		initSpringContext();
		GenerateWhitesService generateWhitesService = SpringApplicationContext.getApplicationContext().getBean(GenerateWhitesService.class);
		System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " generate whites job start!");
		generateWhitesService.generateWhiteCustomers();
		System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " generate whites job complete!");
	}

	@SuppressWarnings("resource")
	private static void initSpringContext() {
		// TODO Auto-generated method stub
		new ClassPathXmlApplicationContext("classpath:spring/generate-whites-job-*.xml");
	}
	
}
