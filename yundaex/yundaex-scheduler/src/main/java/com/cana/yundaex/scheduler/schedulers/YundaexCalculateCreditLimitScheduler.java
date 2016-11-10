package com.cana.yundaex.scheduler.schedulers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cana.yundaex.service.IYundaexCreditService;

/**
 * 生成授信额度
 * @author xiaoyu
 *
 */
@Service
public class YundaexCalculateCreditLimitScheduler {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private IYundaexCreditService ydCreditService;
	
	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE)
	public void doTask(){
		logger.info("开始跑已通过人工审核的韵达客户申请授信额度！");
		long startTime = System.currentTimeMillis();
		ydCreditService.calculateApplyCreditLimit();
		logger.info("结束跑已通过人工审核的韵达客户申请授信额度！");
		logger.info("本次耗时{}ms",System.currentTimeMillis()-startTime);
		System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " calculate yundaex apply credit limit complete!");
	}
}
