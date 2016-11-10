/**
 * Copyright © 2016 Cana. All rights reserved.
 */
package com.cana.bankgate.server.lock;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cana.vbam.common.utils.Constants;

/**
 * 清楚队列中超时的节点
 * 
 * @author renshui
 */
@Component
public class CleanTimeoutQueueNodeScheduler {
	@Resource(name = "citicQueryLockManager")
	private ILockManager citicQueryLockManager;
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_SECOND * 3)
	public void doTask() throws Exception {
		MDC.put(Constants.TRACE_ID, RandomStringUtils.randomAlphanumeric(10));
		logger.info("开始删除队列中的超时节点");
		citicQueryLockManager.cleanTimeoutQueueNode();
		logger.info("删除队列中的超时节点结束");
		MDC.clear();
	}
}
