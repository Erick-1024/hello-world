package com.cana.crawler.dao.mongo.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.cana.crawler.dao.mongo.dao.IShixinCourtDao;
import com.cana.crawler.dao.mongo.entity.ShixinCourt;
import com.cana.vbam.common.crawler.dto.GetShixinCourtRequest;
import com.github.jmkgreen.morphia.query.Query;
import com.travelzen.mongo.dao.impl.MorphiaBasicDao;

@Repository("shixinCourtDao")
public class ShixinCourtDaoImpl extends MorphiaBasicDao<ShixinCourt, ObjectId> implements IShixinCourtDao<ShixinCourt, ObjectId> {

	@Override
	public ShixinCourt getShixinCourt(GetShixinCourtRequest request) {
		
		Query<ShixinCourt> query = createQuery();
		
		if(request.getSubject() != null){
			query.field("subject").equal(request.getSubject());
		}
		
		if(StringUtils.isNotBlank(request.getName())){
			query.field("name").equal(request.getName());
		}
		
		if(StringUtils.isNotBlank(request.getCode())){
			query.field("code").equal(request.getCode());
		}
		
//		if(request.getCacheDate() != null){
//			query.field("createDate").greaterThanOrEq(new DateTime(request.getCacheDate()));
//		}
		
		return query.get();
	}

	@Override
	public void deleteByCodeAndName(String code, String name) {
		Query<ShixinCourt> query = createQuery();
		query.field("code").equal(code);
		query.field("name").equal(name);
		datastore.delete(query);
	}
	
}
