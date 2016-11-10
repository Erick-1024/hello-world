package com.cana.message.mongo.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.cana.message.mongo.dao.ISmsMessageHistoryDao;
import com.cana.message.mongo.dao.MessagingMorphiaDao;
import com.cana.message.mongo.entity.SmsMessageHistory;

@Repository("smsMessageHistoryDao")
public class SmsMessageHistoryDao extends MessagingMorphiaDao<SmsMessageHistory, ObjectId> implements ISmsMessageHistoryDao{


}
