package com.cana.repayment.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.message.client.message.MessageClient;
import com.cana.repayment.service.IMessageService;
import com.cana.vbam.common.message.dto.NotificationMessageDTO;
import com.cana.vbam.common.message.enums.NotificationType;
import com.cana.vbam.common.repayment.utils.MessageConstant;

/**
 * @author hu
 *
 */
@Service
public class MessageServiceImpl implements IMessageService {

	@Resource
	private MessageClient messageClient;
	
	@Override
	public void sendAdjustMessage(String operatorId, String factor, String loanInfoNo, String id, String receiveCustomerId) {
		String content = MessageConstant.generateContent(MessageConstant.AdjustMessage, factor, loanInfoNo);
		String detailURL = MessageConstant.LoanInfoDetailURL + id;
		NotificationMessageDTO messageDTO = convertMessageDTO(NotificationType.ADJUST_FUND, operatorId, null, receiveCustomerId, content, detailURL);
		messageClient.sendNotification(messageDTO);
	}

	/**
	 * 消息DTO转化
	 * @param type
	 * @param sendUserId
	 * @param receiveUserId
	 * @param receiveCustomerId
	 * @param content
	 * @param detailURL
	 * @return
	 */
	private NotificationMessageDTO convertMessageDTO(NotificationType type,  String sendUserId,
			String receiveUserId, String receiveCustomerId, String content, String detailURL){
		NotificationMessageDTO messageDTO = new NotificationMessageDTO();
		messageDTO.setType(type);
		messageDTO.setContent(content);
		if(StringUtils.isNotBlank(sendUserId)){
			messageDTO.setSendUserId(sendUserId);
		}
		if(StringUtils.isNotBlank(receiveUserId)){
			messageDTO.setReceiveUserId(receiveUserId);
		}
		if(StringUtils.isNotBlank(receiveCustomerId)){
			messageDTO.setReceiveCustomerId(receiveCustomerId);
		}
		if(StringUtils.isNotBlank(detailURL)){
			messageDTO.setDetailURL(detailURL);
		}
		return messageDTO;
	}

	@Override
	public void sendDeductMessage(String loanInfoNo, String loanInfoId, String receiveCustomerId, String accountNo, String amount) {
		String content = MessageConstant.generateContent(MessageConstant.DEDUCT_MESSAGE, accountNo, amount, loanInfoNo);
		String detailURL = MessageConstant.LoanInfoDetailURL + loanInfoId;
		NotificationMessageDTO messageDTO = convertMessageDTO(NotificationType.AUTO_DEDUCT_FUND, null, null, receiveCustomerId, content, detailURL);
		messageClient.sendNotification(messageDTO);
	}

	@Override
	public void sendActiveRepaymentMessage(String loanNo, String loanInfoId, String receiveCustomerId, String financeCompany) {
		String content = MessageConstant.generateContent(MessageConstant.ACTIVE_REPAYMENT_MESSAGE, financeCompany, loanNo);
		String detailURL = MessageConstant.LoanInfoAdjustURL + loanInfoId;
		NotificationMessageDTO messageDTO = convertMessageDTO(NotificationType.ACTIVE_REPAYMENT, null, null, receiveCustomerId, content, detailURL);
		messageClient.sendNotification(messageDTO);
	}
}
