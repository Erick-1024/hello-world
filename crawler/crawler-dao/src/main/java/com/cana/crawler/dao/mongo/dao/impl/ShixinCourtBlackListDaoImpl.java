package com.cana.crawler.dao.mongo.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.cana.crawler.dao.mongo.dao.IShixinCourtBlackListDao;
import com.cana.crawler.dao.mongo.entity.ShixinCourtBlackList;
import com.github.jmkgreen.morphia.query.Query;
import com.travelzen.mongo.dao.impl.MorphiaBasicDao;

@Repository("shixinCourtBlackListDao")
public class ShixinCourtBlackListDaoImpl extends MorphiaBasicDao<ShixinCourtBlackList, ObjectId> implements IShixinCourtBlackListDao<ShixinCourtBlackList, ObjectId> {


	@Override
	public ShixinCourtBlackList findByCodeAndName(String code, String name) {
		Query<ShixinCourtBlackList> query = createQuery();
		query.field("code").equal(code);
		query.field("name").equal(name);
		return query.get();
	}
	
}
