package com.cana.repayment.scheduler.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.cana.vbam.common.repayment.message.dto.AdjustSuccessMessage;
import com.cana.vbam.rabbitmq.configuration.CommonConfiguration;
import com.google.gson.Gson;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.handler.AbstractRetryTaskHandler;
import com.travelzen.framework.retry.handler.HandlerStatus;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class AdjustSuccessNotifyRetryTaskHandler extends AbstractRetryTaskHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private RabbitTemplate repaymentRabbitTemplate = SpringApplicationContext.getApplicationContext().getBean("repaymentRabbitTemplate" ,RabbitTemplate.class);
	
	
	@Override
	public void execute(RetryTask task, HandlerStatus status) throws Exception {
		logger.info("发送还款成功通知开始:" + task.getData());
		repaymentRabbitTemplate.convertAndSend(CommonConfiguration.ROUTING_KEY_PRODUCER_REPAYMENT_SUCCESS, new Gson().fromJson(task.getData(), AdjustSuccessMessage.class));
		logger.info("发送还款成功通知结束");
	}

}
