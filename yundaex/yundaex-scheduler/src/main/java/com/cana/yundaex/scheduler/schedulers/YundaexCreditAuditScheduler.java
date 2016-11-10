package com.cana.yundaex.scheduler.schedulers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cana.yundaex.service.IYundaexMonitorService;

@Service
public class YundaexCreditAuditScheduler {

private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private IYundaexMonitorService yundaexMonitorService;
	
//	@Scheduled(cron = "0 0 0 * * ?")
	@Scheduled(cron = "10 * * * * ?")
	public void doTask(){
		logger.info("授信审核scheduler开始");
		long startTime = System.currentTimeMillis();
		yundaexMonitorService.creditAuditScheduler();
		logger.info("授信审核scheduler结束");
		logger.info("本次耗时{}ms",System.currentTimeMillis()-startTime);
		System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + "yundaex creditAudit scheduler complete!");
	}
}
