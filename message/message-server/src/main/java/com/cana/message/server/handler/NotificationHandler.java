package com.cana.message.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.message.server.service.transaction.INotificationTransactionService;
import com.cana.vbam.common.message.dto.NotificationMessageDTO;

public class NotificationHandler {

	private INotificationTransactionService notificationService;

	private Logger log = LoggerFactory.getLogger(NotificationHandler.class);

	public NotificationHandler(INotificationTransactionService notificationService) {
		this.notificationService = notificationService;
	}

	public void handleMessage(NotificationMessageDTO message) throws Exception {
	    notificationService.saveNotification(message);
	}
}
