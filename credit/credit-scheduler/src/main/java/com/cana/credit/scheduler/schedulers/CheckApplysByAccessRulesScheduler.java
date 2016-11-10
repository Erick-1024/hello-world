package com.cana.credit.scheduler.schedulers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cana.credit.service.IAccessRulesService;


/**
 * 检查申请额度的客户是否符合准入规则定时器
 * @author tangyihong
 *
 */
@Service
public class CheckApplysByAccessRulesScheduler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private IAccessRulesService accessRulesService;
	
	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE)
	public void doTask(){
		logger.info("开始检查申请额度的客户是否符合准入规则！");
		long startTime = System.currentTimeMillis();
		accessRulesService.checkApplysByAccessRules();
		logger.info("结束检查申请额度的客户是否符合准入规则！");
		logger.info("本次耗时{}ms",System.currentTimeMillis()-startTime);
		System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " check applys by access rules complete!");
	}

}
