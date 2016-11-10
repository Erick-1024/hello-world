/**
 * Copyright © 2016 Cana. All rights reserved.
 */
package com.cana.bankgate.server.schedules;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cana.bankgate.api.BankgateApi;
import com.cana.bankgate.server.mapper.gen.BankgateTransMapper;
import com.cana.vbam.common.bankgate.dto.request.BankMainAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.BankMainAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.enums.BankTranStatus;

/**
 * 目前中信有session过期时间，用该定时任务刷新session生命周期
 * 
 * @author ducer
 */
@Component
public class BankSessionSchedule {
  private Logger logger = LoggerFactory.getLogger(getClass());
  @Resource
  private BankgateApi bankgateApi;
  @Resource
  private BankgateTransMapper bankgateTransMapper;

  @Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE * 3)
  public void doTask() throws Exception {
    logger.info("网关定时刷新Session任务执行");
    try {
      BankMainAccountBalanceQueryDTO query = new BankMainAccountBalanceQueryDTO();
      BankMainAccountBalanceResultDTO result = bankgateApi.queryBankMainAccountBalance(query);
      if (!BankTranStatus.success.equals(result.getStatus())) {
        logger.info("网关定时刷新Session任务,返回失败原因:{}", result.getStatusText());
      } else {
        logger.info("网关定时刷新Session任务,执行成功.");
      }
    } catch (Exception e) {
      logger.error("网关定时刷新Session任务", e);
    }
  }
}
