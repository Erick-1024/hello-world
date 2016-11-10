package com.cana.message.server.handler;

import com.cana.vbam.common.annotation.MQConsumer;
import com.cana.vbam.common.message.dto.NotificationMessageDTO;
import com.cana.vbam.common.message.enums.NotificationType;
import com.cana.vbam.common.repayment.enums.RepaymentMethod;
import com.cana.vbam.common.repayment.message.dto.RepaymentSuccessMessage;
import com.cana.vbam.common.repayment.utils.MessageConstant;
import com.travelzen.framework.core.util.MoneyUtil;

@MQConsumer(RepaymentSuccessMessage.class)
public class RepaymentSuccessHandler extends AbstractMQMessageHandler<RepaymentSuccessMessage>{

	@Override
	public void handleMessage(RepaymentSuccessMessage message) throws Exception {
		String content = null;
		String detailURL = null;
		NotificationMessageDTO messageDTO = null;
		switch (message.getRepaymentMethod()) {
		case ACCOUNTDEDUCTION:
			content = MessageConstant.generateContent(MessageConstant.DEDUCT_MESSAGE, message.getAccountNo(), MoneyUtil.cent2Yuan(message.getTotal()), message.getLoanNo());
			detailURL = MessageConstant.LoanInfoDetailURL + message.getLoanInfoId();
			messageDTO = convertMessageDTO(repaymentMethod2NotificationType(message.getRepaymentMethod()), null, null, message.getFinanceId(), content, detailURL);
			break;
			
		case REFUND:
			content = MessageConstant.generateContent(MessageConstant.REFUND_REPAYMENT_MESSAGE, message.getInstitutionName(), MoneyUtil.cent2Yuan(message.getTotal()), message.getAccountNo(), message.getLoanNo());
			detailURL = MessageConstant.LoanInfoDetailURL + message.getLoanInfoId();
			messageDTO = convertMessageDTO(repaymentMethod2NotificationType(message.getRepaymentMethod()), null, null, message.getFinanceId(), content, detailURL);
			break;
			
		case ACTIVE:
			content = MessageConstant.generateContent(MessageConstant.ACTIVE_REPAYMENT_MESSAGE, message.getFinanceCompany());
			detailURL = MessageConstant.LoanInfoAdjustURL + message.getLoanInfoId();
			messageDTO = convertMessageDTO(repaymentMethod2NotificationType(message.getRepaymentMethod()), null, null, message.getFactorId(), content, detailURL);
			break;
			
		default:
			break;
		}
		messageDTO.setMessageId(message.getMessageId());
		logger.info("处理还款成功消息开始");
		notificationTransactionService.saveNotification(messageDTO);
		logger.info("处理还款成功消息结束");
	}

	public NotificationType repaymentMethod2NotificationType(RepaymentMethod repaymentMethod) throws Exception{
		switch(repaymentMethod){
		case ACCOUNTDEDUCTION:
			return NotificationType.AUTO_DEDUCT_FUND;
		case REFUND:
			return NotificationType.REFUND_REPAYMENT;
		case ACTIVE:
			return NotificationType.ACTIVE_REPAYMENT;
		default:
			throw new Exception(String.format("无法将还款类型[%s]转换为消息类型", repaymentMethod.name()));
		}
	}
	
}
