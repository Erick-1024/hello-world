package com.cana.message.server.api.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.message.api.INotificationApi;
import com.cana.message.server.service.transaction.INotificationTransactionService;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.message.dto.NotificationMessageDTO;
import com.cana.vbam.common.message.dto.NotificationQueryCriteria;

public class NotificationApiImpl implements INotificationApi {
	private static final Logger logger = LoggerFactory.getLogger(NotificationApiImpl.class);

	@Resource
	private INotificationTransactionService notificationServiceImpl;
	
	@Override
	public PageResult<NotificationMessageDTO> queryNotifications(NotificationQueryCriteria criteria) {
		
		return notificationServiceImpl.queryNotifications(criteria);
	}

	@Override
	public boolean updateReadStatus(String notificationId, String userId) {
		
		return notificationServiceImpl.updateReadStatus(notificationId,userId);
	}

}
