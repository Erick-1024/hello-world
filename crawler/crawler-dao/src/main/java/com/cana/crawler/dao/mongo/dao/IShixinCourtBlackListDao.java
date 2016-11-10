package com.cana.crawler.dao.mongo.dao;

import com.cana.crawler.dao.mongo.entity.ShixinCourtBlackList;
import com.travelzen.mongo.dao.IBasicDao;
import com.travelzen.mongo.entity.MorphiaEntity;

public interface IShixinCourtBlackListDao<E extends MorphiaEntity<I>, I> extends IBasicDao<E, I>{

	/**
	 * 根据code和name查询
	 * @param code
	 * @param name
	 * @return
	 */
	public ShixinCourtBlackList findByCodeAndName(String code, String name);
	
}
