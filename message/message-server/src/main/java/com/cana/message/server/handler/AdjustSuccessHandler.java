package com.cana.message.server.handler;

import com.cana.vbam.common.annotation.MQConsumer;
import com.cana.vbam.common.message.dto.NotificationMessageDTO;
import com.cana.vbam.common.message.enums.NotificationType;
import com.cana.vbam.common.repayment.message.dto.AdjustSuccessMessage;
import com.cana.vbam.common.repayment.utils.MessageConstant;

@MQConsumer(AdjustSuccessMessage.class)
public class AdjustSuccessHandler extends AbstractMQMessageHandler<AdjustSuccessMessage>{

	@Override
	public void handleMessage(AdjustSuccessMessage message) throws Exception {
		String content =  MessageConstant.generateContent(MessageConstant.AdjustMessage, message.getFactorCompany(), message.getLoanNo());
		String detailURL = MessageConstant.LoanInfoDetailURL + message.getLoanInfoId();
		NotificationMessageDTO messageDTO = convertMessageDTO(NotificationType.ADJUST_FUND, null, null, message.getFinanceId(), content, detailURL);
		messageDTO.setMessageId(message.getMessageId());
		logger.info("处理还款成功消息开始");
		notificationTransactionService.saveNotification(messageDTO);
		logger.info("处理还款成功消息结束");
	}
	
}
