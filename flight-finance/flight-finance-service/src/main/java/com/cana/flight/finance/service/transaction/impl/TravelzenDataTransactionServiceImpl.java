/**
 * Copyright © 2016-2019 Cana. All rights reserved.
 */
package com.cana.flight.finance.service.transaction.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.cana.common.dao.mapper.CommonTableLockMapper;
import com.cana.common.dao.mapper.gen.PropertiesMapper;
import com.cana.common.dao.po.Properties;
import com.cana.flight.finance.common.dto.FlightTicketDTO;
import com.cana.flight.finance.common.dto.RepaymentDTO;
import com.cana.flight.finance.dao.mapper.gen.FlightTicketMapper;
import com.cana.flight.finance.dao.mapper.gen.RepaymentMapper;
import com.cana.flight.finance.dao.po.FlightTicket;
import com.cana.flight.finance.dao.po.Repayment;
import com.cana.flight.finance.service.constants.FlightFinanceConstant;
import com.cana.flight.finance.service.transaction.ITravelzenDataTransactionService;
import com.dianping.cat.Cat;

/**
 * @author ducer
 *
 */
@Service
public class TravelzenDataTransactionServiceImpl implements ITravelzenDataTransactionService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private FlightTicketMapper flightTicketMapper;
	@Resource
	private CommonTableLockMapper commonTableLockMapper;
	@Resource
	private PropertiesMapper propertiesMapper;
	@Resource
	private RepaymentMapper repaymentMapper;

	@Override
	public void saveFlightTickets(List<FlightTicketDTO> ticketDTOs) {
		if (CollectionUtils.isEmpty(ticketDTOs)) {
			return;
		}
		// 锁记录保证同一时间只有一个session在写
		long time = System.currentTimeMillis();
		Properties properties = commonTableLockMapper.lockCommonPropertiesByName(FlightFinanceConstant.travelzen_ticket_last_record_id);
		if (properties == null) {
			logger.error("credit.properties不存在数据");
		}
		for (FlightTicketDTO dto : ticketDTOs) {
			FlightTicket ticket = new FlightTicket();
			BeanUtils.copyProperties(dto, ticket);
			ticket.setCreateTime(new Date());
			ticket.setUpdateTime(new Date());
			try {
				flightTicketMapper.insertSelective(ticket);
			} catch (DuplicateKeyException e) {
				Cat.logMetricForCount("授信定时任务-插入真旅机票订单增量数据主键冲突");
				logger.error("插入真旅订单增量数据发生主键冲突，主键ID为：{}", dto.getRecordId());
				logger.error("", e);
			} catch (Exception e) {
				Cat.logMetricForCount("授信定时任务-插入真旅机票订单增量数据未知异常");
				logger.error("插入真旅订单增量数据发生未知异常，信息:{}", e.getMessage());
				logger.error("", e);
				throw e;
			}
		}
		properties.setValue(ticketDTOs.get(ticketDTOs.size() - 1).getRecordId());
		properties.setUpdateTime(new Date());
		propertiesMapper.updateByPrimaryKeySelective(properties);
		logger.info("本次执行时间:{}ms", System.currentTimeMillis() - time);
	}

	@Override
	public void saveRepayments(List<RepaymentDTO> repaymentDTOs) {
		if (CollectionUtils.isEmpty(repaymentDTOs)) {
			return;
		}
		// 锁记录保证同一时间只有一个session在写
		long time = System.currentTimeMillis();
		Properties properties = commonTableLockMapper.lockCommonPropertiesByName(FlightFinanceConstant.travelzen_repayment_last_record_id);
		if (properties == null) {
			logger.error("credit.properties不存在数据");
		}
		for (RepaymentDTO dto : repaymentDTOs) {
			Repayment repayment = new Repayment();
			BeanUtils.copyProperties(dto, repayment);
			repayment.setCreateTime(new Date());
			repayment.setUpdateTime(new Date());
			try {
				repaymentMapper.insertSelective(repayment);
			} catch (DuplicateKeyException e) {
				Cat.logMetricForCount("授信定时任务-插入真旅财务增量数据主键冲突");
				logger.error("插入真旅财务增量数据发生主键冲突，主键ID为：{}", dto.getRecordId());
				logger.error("", e);
			} catch (Exception e) {
				Cat.logMetricForCount("授信定时任务-插入真旅财务增量数据未知异常");
				logger.error("插入真旅财务增量数据发生未知异常，信息:{}", e.getMessage());
				logger.error("", e);
				throw e;
			}
		}
		properties.setValue(repaymentDTOs.get(repaymentDTOs.size() - 1).getRecordId().toString());
		properties.setUpdateTime(new Date());
		propertiesMapper.updateByPrimaryKeySelective(properties);
		logger.info("本次执行时间:{}ms", System.currentTimeMillis() - time);
	}

	@Override
	public String getLastRecordId(String key) {

		Properties properties = propertiesMapper.selectByPrimaryKey(key);
		if (properties == null) {
			logger.info("获取上次真旅数据ID失败,KEY:{}", key);
			logger.info("视为数据库Properties中不存在该数据，执行插入数据,KEY:{}", key);
			properties = new Properties();
			properties.setName(key);
			properties.setValue(null);
			propertiesMapper.insertSelective(properties);
			return null;
		}
		if (StringUtils.isBlank(properties.getValue())) {
			logger.info("获取上次真旅数据ID为空,KEY:{}", key);
		}
		return properties.getValue();
	}
}
