package com.cana.repayment.scheduler.handler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.vbam.common.message.dto.NotificationMessageDTO;
import com.cana.vbam.common.message.enums.NotificationType;
import com.cana.vbam.common.service.impl.VbamCommonService;
import com.travelzen.framework.retry.dao.mapper.gen.RetryTaskMapper;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public abstract class AbstractMQMessageHandler<T> {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected VbamCommonService vbamCommonService = SpringApplicationContext.getApplicationContext().getBean(VbamCommonService.class);

	protected RetryTaskMapper retryTaskMapper = SpringApplicationContext.getApplicationContext().getBean(RetryTaskMapper.class);
	
	public abstract void handleMessage(T message) throws Exception;
	
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
	protected NotificationMessageDTO convertMessageDTO(NotificationType type,  String sendUserId,
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
	
}
