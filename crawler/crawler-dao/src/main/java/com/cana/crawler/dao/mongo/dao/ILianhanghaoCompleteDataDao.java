package com.cana.crawler.dao.mongo.dao;

import java.util.List;

import com.travelzen.mongo.dao.IBasicDao;
import com.travelzen.mongo.entity.MorphiaEntity;

public interface ILianhanghaoCompleteDataDao<E extends MorphiaEntity<I>, I> extends IBasicDao<E, I>{

	public E getByLianhanghao(String lianhanghao);
	
	public List<E> getAll();
}
