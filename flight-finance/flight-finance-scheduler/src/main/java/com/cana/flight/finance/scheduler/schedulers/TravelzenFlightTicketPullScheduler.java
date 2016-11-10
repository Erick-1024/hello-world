/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.flight.finance.scheduler.schedulers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cana.flight.finance.common.dto.FlightTicketDTO;
import com.cana.flight.finance.common.dto.TravelzenFlightTicketResponse;
import com.cana.flight.finance.dataaccess.travelzen.api.ITravelzenDataApi;
import com.cana.flight.finance.service.constants.FlightFinanceConstant;
import com.cana.flight.finance.service.transaction.ITravelzenDataTransactionService;
import com.cana.vbam.common.utils.Constants;
import com.dianping.cat.Cat;

/**
 * 
 * @author ducer
 */
@Service
public class TravelzenFlightTicketPullScheduler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private ITravelzenDataTransactionService travelzenDataTransactionService;
	@Resource
	private ITravelzenDataApi travelzenDataApiImpl;

	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE * 30)
	public void doTask() throws Exception {
		logger.info("开始拉取真旅订单增量数据");
		String lastRecordId = travelzenDataTransactionService.getLastRecordId(FlightFinanceConstant.travelzen_ticket_last_record_id);
		while (true) {
			MDC.put(Constants.TRACE_ID, RandomStringUtils.randomAlphanumeric(10));
			Cat.logMetricForCount("授信定时任务-调用真旅机票订单增量数据接口次数");
			List<FlightTicketDTO> result = pullData(lastRecordId);
			Cat.logMetricForSum("授信定时任务-拉取真旅机票订单增量数据总计", result.size());
			logger.info("开始处理真旅机票订单增量数据,本次处理{}条", result.size());
			travelzenDataTransactionService.saveFlightTickets(result);
			if (!CollectionUtils.isEmpty(result)) {
				lastRecordId = result.get(result.size() - 1).getRecordId();
			}
			if (result.size() < FlightFinanceConstant.travelzen_ticket_record_size_once) {
				logger.info("结束拉取真旅订单增量数据-最后一个订单ID：{}", lastRecordId);
				break;
			}
			MDC.clear();
		}
	}

	public List<FlightTicketDTO> pullData(String lastRecordId) {
		TravelzenFlightTicketResponse result = travelzenDataApiImpl.getFlightTickets(lastRecordId,
				FlightFinanceConstant.travelzen_ticket_record_size_once);
		if (result == null || CollectionUtils.isEmpty(result.getTickets())) {
			logger.info("当前没有真旅结算增量数据");
			return new ArrayList<>();
		}
		return result.getTickets();
	}
}
