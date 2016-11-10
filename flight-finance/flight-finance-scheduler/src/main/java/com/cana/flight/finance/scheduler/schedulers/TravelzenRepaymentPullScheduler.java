/**
 * Copyright © 2016-2019 Cana. All rights reserved.
 */
package com.cana.flight.finance.scheduler.schedulers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cana.flight.finance.common.dto.RepaymentDTO;
import com.cana.flight.finance.common.dto.TravelzenRepaymentResponse;
import com.cana.flight.finance.dataaccess.travelzen.api.ITravelzenDataApi;
import com.cana.flight.finance.service.constants.FlightFinanceConstant;
import com.cana.flight.finance.service.transaction.ITravelzenDataTransactionService;
import com.dianping.cat.Cat;

/**
 * @author ducer
 *
 */
@Service
public class TravelzenRepaymentPullScheduler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private ITravelzenDataTransactionService travelzenDataTransactionService;
	@Resource
	private ITravelzenDataApi travelzenDataApiImpl;

	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE * 30)
	public void doTask() throws Exception {
		logger.info("开始拉取真旅结算增量数据");
		String lastRecordId = travelzenDataTransactionService.getLastRecordId(FlightFinanceConstant.travelzen_repayment_last_record_id);
		while (true) {
			Cat.logMetricForCount("授信定时任务-调用真旅财务增量数据接口次数");
			List<RepaymentDTO> result = pullData(lastRecordId);
			Cat.logMetricForSum("授信定时任务-拉取真旅财务增量数据总计", result.size());
			logger.info("开始处理真旅财务增量数据,本次处理{}条", result.size());
			travelzenDataTransactionService.saveRepayments(result);
			if (!CollectionUtils.isEmpty(result)) {
				lastRecordId = result.get(result.size() - 1).getRecordId().toString();
			}
			if (result.size() < FlightFinanceConstant.travelzen_repayment_record_size_once) {
				logger.info("结束拉取真旅结算增量数据-最后一个结算ID：{}", lastRecordId);
				break;
			}
		}
	}

	private List<RepaymentDTO> pullData(String lastRecordId) {
		TravelzenRepaymentResponse result = travelzenDataApiImpl.getRepayments(lastRecordId,
				FlightFinanceConstant.travelzen_repayment_record_size_once);
		if (result == null || CollectionUtils.isEmpty(result.getRepaymentData())) {
			logger.info("当前没有真旅结算增量数据");
			return new ArrayList<>();
		}
		return result.getRepaymentData();
	}
}
