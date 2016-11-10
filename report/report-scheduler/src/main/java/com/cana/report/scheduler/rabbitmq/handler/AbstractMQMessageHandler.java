package com.cana.report.scheduler.rabbitmq.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.report.service.transaction.IReportTransactionService;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public abstract class AbstractMQMessageHandler<T> {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected IReportTransactionService reportTransactionService = SpringApplicationContext.getApplicationContext().getBean(IReportTransactionService.class);
	
	public abstract void handleMessage(T message) throws Exception;
	
}
