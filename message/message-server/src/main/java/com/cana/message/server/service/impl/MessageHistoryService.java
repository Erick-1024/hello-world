package com.cana.message.server.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cana.message.mongo.dao.IMessageHistoryDao;
import com.cana.message.mongo.dao.ISmsMessageHistoryDao;
import com.cana.message.mongo.entity.MMessageHistory;
import com.cana.message.mongo.entity.MailMessageHistory;
import com.cana.message.mongo.entity.SmsMessage;
import com.cana.message.mongo.entity.SmsMessageHistory;
import com.cana.message.server.service.IMessageHistoryService;

@Service("messageHistoryService")
public class MessageHistoryService implements IMessageHistoryService {

	@Resource(name="messageHistoryDao")
	private IMessageHistoryDao messageHistoryDao;
	
	@Resource(name="smsMessageHistoryDao")
	private ISmsMessageHistoryDao smsMessageHistoryDao;
	
	@Override
	public void saveMailMessageHistory(MailMessageHistory history) {
		 messageHistoryDao.create(new MMessageHistory(history));
	}

	@Override
	public void saveSmsMessageHistory(SmsMessage history) {
		smsMessageHistoryDao.create(new SmsMessageHistory(history));
	}

}
