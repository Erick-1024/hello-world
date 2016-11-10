package com.cana.account.scheduler.task;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cana.account.api.IAccountSchedulerApi;

@Component
public class AutoUpdateAccountTradeRecordStatusTask {
    private static Logger logger = LoggerFactory.getLogger(AutoUpdateAccountTradeRecordStatusTask.class);

    @Resource
    private IAccountSchedulerApi accountScheduleApi;

//    @Scheduled(cron = "0 */5 * * * ?")
    @Scheduled(fixedDelay = 1000 * 5)
    public void autoUpdateAccountTradeRecordStatus() {
        try {
        	logger.info("自动更新交易状态定时开始");
            String executeDetail = accountScheduleApi.autoUpdateTradeRecordStatus();
            logger.info(executeDetail);
        } catch (Exception e) {
            logger.error("autoUpdateAccountTradeRecordStatus failed", e);
        }
    }
}
