package com.cana.report.scheduler.rabbitmq.handler;

import com.cana.vbam.common.annotation.MQConsumer;
import com.cana.vbam.common.repayment.message.dto.RepaymentSuccessMessage;

@MQConsumer(RepaymentSuccessMessage.class)
public class RepaymentSuccessHandler extends AbstractMQMessageHandler<RepaymentSuccessMessage>{

	@Override
	public void handleMessage(RepaymentSuccessMessage message) throws Exception {
		logger.info("处理还款成功消息开始");
		reportTransactionService.processRepaymentSuccessMessage(message);
		logger.info("处理还款成功消息结束");
	}

}
