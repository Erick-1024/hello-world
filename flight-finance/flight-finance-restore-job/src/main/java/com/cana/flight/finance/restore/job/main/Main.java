package com.cana.flight.finance.restore.job.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cana.flight.finance.restore.job.service.FlightTicketRestoreService;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class Main {

	static FlightTicketRestoreService ftRestore;

	public static void main(String args[]) {
		if (args.length == 3) {
			initSpringContext();
			ftRestore = SpringApplicationContext.getApplicationContext().getBean(FlightTicketRestoreService.class);
			ftRestore.restore(args[0],Integer.valueOf(args[1]),Long.valueOf(args[2]));
		}else{
			System.out.println("需要输入三个参数");
		}
	}

//	public static void main(String args[]) {
//			initSpringContext();
//			ftRestore = SpringApplicationContext.getApplicationContext().getBean(FlightTicketRestoreService.class);
//			ftRestore.restore("/Users/meng/Downloads/nbb4688",0,0);
//	}


	@SuppressWarnings("resource")
	private static void initSpringContext() {
		// TODO Auto-generated method stub
		new ClassPathXmlApplicationContext("classpath:spring/restore-job-*.xml");
	}

}
