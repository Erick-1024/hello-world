package com.travelzen.mongo.dao;

import java.util.List;

import com.travelzen.mongo.dao.IBasicDao;
import com.travelzen.mongo.entity.MorphiaEntity;

public interface MemberBasicDao<E extends MorphiaEntity<I>, I> extends IBasicDao <E, I> {

	void batchSave(List<E> data);

	void clear();

}
