package com.cana.yundaex.scheduler.schedulers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cana.yundaex.service.IYundaexMonitorService;

@Service
public class YundaexMonitorScheduler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private IYundaexMonitorService yundaexMonitorService;
	
	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE*3)
//	@Scheduled(cron = "0 0 4 * * ?")
	public void doTask(){
		logger.info("韵达监控scheduler开始");
		long startTime = System.currentTimeMillis();
		yundaexMonitorService.monitorScheduler();
		logger.info("韵达监控scheduler结束");
		logger.info("本次耗时{}ms",System.currentTimeMillis()-startTime);
		System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " yundaex monitor scheduler complete!");
	}
}
