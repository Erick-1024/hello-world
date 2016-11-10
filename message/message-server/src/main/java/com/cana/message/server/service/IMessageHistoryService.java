package com.cana.message.server.service;

import com.cana.message.mongo.entity.MailMessageHistory;
import com.cana.message.mongo.entity.SmsMessage;

public interface IMessageHistoryService {

	public void saveMailMessageHistory(MailMessageHistory mailMsg);
	
	public void saveSmsMessageHistory(SmsMessage smsMsg);
}
