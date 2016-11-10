package com.cana.yundaex.scheduler.schedulers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cana.yundaex.service.IYundaexAutomaticRulesService;


@Service
public class YundaexCheckApplysByAutomaticAuditScheduler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private IYundaexAutomaticRulesService automaticRulesService;
	
	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE)
	public void doTask(){
		logger.info("开始检查申请额度的客户是否符合系统审核规则！");
		long startTime = System.currentTimeMillis();
		automaticRulesService.checkApplysByAutomaticRules();
		logger.info("结束检查申请额度的客户是否符合系统审核规则！");
		logger.info("本次耗时{}ms",System.currentTimeMillis()-startTime);
		System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " check applys by access rules complete!");
	}
}
