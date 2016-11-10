package com.cana.credit.scheduler.schedulers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cana.credit.service.ICreditLimitService;


/**
 * 跑已通过人工审核的客户申请授信额度定时器
 * @author yihong.tang
 *
 */
@Service
public class CalculateApplyCreditLimitScheduler {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private ICreditLimitService creditLimitService; 
	
	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE)
	public void doTask(){
		logger.info("开始跑已通过人工审核的客户申请授信额度！");
		long startTime = System.currentTimeMillis();
		creditLimitService.calculateApplyCreditLimit();
		logger.info("结束跑已通过人工审核的客户申请授信额度！");
		logger.info("本次耗时{}ms",System.currentTimeMillis()-startTime);
		System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " calculate apply credit limit complete!");
	}

}
