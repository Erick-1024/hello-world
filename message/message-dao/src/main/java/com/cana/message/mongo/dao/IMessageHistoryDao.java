package com.cana.message.mongo.dao;

import org.bson.types.ObjectId;

import com.cana.message.mongo.entity.MMessageHistory;
import com.travelzen.mongo.dao.IBasicDao;

public interface IMessageHistoryDao extends IBasicDao<MMessageHistory, ObjectId> {

}
