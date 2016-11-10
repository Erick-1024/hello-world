package com.cana.account.scheduler.task;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cana.account.api.IAccountSchedulerApi;

@Component
public class AutoUpdateWithdrawFeeTask {
	
	private static Logger logger = LoggerFactory.getLogger(AutoUpdateWithdrawFeeTask.class);
	
    @Resource
    private IAccountSchedulerApi accountScheduleApi;
    
    @Scheduled(fixedDelay = 1000 * 10)
    public void autoUpdateWithdrawFeeTask() {
      try {
      	logger.info("自动更新提现手续费定时开始");
        String executeDetail = accountScheduleApi.autoUpdateWithdrawFee();
        logger.info(executeDetail);
        logger.info("自动更新提现手续费结束");
      } catch (Exception e) {
          logger.error("自动更新提现手续费失败", e);
      }
    }
}
