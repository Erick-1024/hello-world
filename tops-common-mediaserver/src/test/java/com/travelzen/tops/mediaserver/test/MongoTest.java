package com.travelzen.tops.mediaserver.test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class MongoTest {

	private static Logger LOG = LoggerFactory.getLogger(MongoDaoTest.class);

	@Test
	public void MongoApiTest() {
//		try {

			List<ServerAddress> address = new ArrayList<>();
//			address.add(new ServerAddress("192.168.160.76"));
//			address.add(new ServerAddress("192.168.160.77"));
//			address.add(new ServerAddress("192.168.160.78"));
			Mongo mongo = new MongoClient(address);

			DB db = mongo.getDB("media");
//			db.authenticate("tz", "tz".toCharArray());
			DBCollection collection = db.getCollection("fs.files");

			List<DBObject> curs = collection.find().toArray();
			System.out.println(curs.size());

			getGroup(db, collection);
//
//		} catch ( e) {
//			LOG.error(e.getMessage(), e);
//		}
	}

	public void getGroup(DB db, DBCollection collection) {
		// db.fs.files.group({key:{mediaId:true},cond:{filename:{$ne:''}},reduce:function(obj,prev){prev.csum+=1;},initial:{csum:0}})

		BasicDBObject key = new BasicDBObject("mediaId", true);
		BasicDBObject cond = new BasicDBObject("filename", new BasicDBObject("$ne", ""));
		BasicDBObject initial = new BasicDBObject("count", 0);
		String reduce = "function(obj,prev){prev.count++}";

		// Calendar.getInstance().getTime().getTime();
		BasicDBList returnList = (BasicDBList) collection.group(key, cond, initial, reduce);

		for (Object o : returnList) {
			System.out.println(o.toString());
		}
		System.out.println(returnList.size());
	}

}
