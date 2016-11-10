package com.cana.message.mongo.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.cana.message.mongo.dao.IMessageHistoryDao;
import com.cana.message.mongo.dao.MessagingMorphiaDao;
import com.cana.message.mongo.entity.MMessageHistory;

@Repository("messageHistoryDao")
public class MessageHistoryDao extends
		MessagingMorphiaDao<MMessageHistory, ObjectId> implements
		IMessageHistoryDao {

}
