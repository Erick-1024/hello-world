package com.cana.message.mongo.dao;

import org.bson.types.ObjectId;

import com.cana.message.mongo.entity.SmsMessageHistory;
import com.travelzen.mongo.dao.IBasicDao;

public interface ISmsMessageHistoryDao extends IBasicDao<SmsMessageHistory, ObjectId> {

}
