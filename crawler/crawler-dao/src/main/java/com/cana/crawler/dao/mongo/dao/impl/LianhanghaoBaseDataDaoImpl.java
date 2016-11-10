package com.cana.crawler.dao.mongo.dao.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.cana.crawler.dao.mongo.dao.ILianhanghaoBaseDataDao;
import com.cana.crawler.dao.mongo.entity.LianhanghaoBaseData;
import com.github.jmkgreen.morphia.query.Query;
import com.travelzen.mongo.dao.impl.MorphiaBasicDao;

@Repository("lianhanghaoBaseDataDao")
public class LianhanghaoBaseDataDaoImpl extends MorphiaBasicDao<LianhanghaoBaseData, ObjectId> implements ILianhanghaoBaseDataDao<LianhanghaoBaseData, ObjectId> {

	@Override
	public List<LianhanghaoBaseData> getAll() {
		Query<LianhanghaoBaseData> query = createQuery();
		return query.asList();
	}
}
