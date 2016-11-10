package com.cana.crawler.dao.mongo.dao.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.cana.crawler.dao.mongo.dao.ILianhanghaoCompleteDataDao;
import com.cana.crawler.dao.mongo.entity.LianhanghaoCompleteData;
import com.github.jmkgreen.morphia.query.Query;
import com.travelzen.mongo.dao.impl.MorphiaBasicDao;

@Repository("lianhanghaoCompleteDataDao")
public class LianhanghaoCompleteDataDaoImpl extends MorphiaBasicDao<LianhanghaoCompleteData, ObjectId> implements ILianhanghaoCompleteDataDao<LianhanghaoCompleteData, ObjectId> {

	@Override
	public LianhanghaoCompleteData getByLianhanghao(String lianhanghao) {
		Query<LianhanghaoCompleteData> query = createQuery();
		query.field("lianhanghao").equal(lianhanghao);
		return query.get();
	}

	@Override
	public List<LianhanghaoCompleteData> getAll() {
		Query<LianhanghaoCompleteData> query = createQuery();
		return query.asList();
	}

}
