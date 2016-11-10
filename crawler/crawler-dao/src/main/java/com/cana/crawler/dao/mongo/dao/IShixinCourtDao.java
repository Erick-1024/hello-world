package com.cana.crawler.dao.mongo.dao;

import com.cana.crawler.dao.mongo.entity.ShixinCourt;
import com.cana.vbam.common.crawler.dto.GetShixinCourtRequest;
import com.travelzen.mongo.dao.IBasicDao;
import com.travelzen.mongo.entity.MorphiaEntity;

public interface IShixinCourtDao<E extends MorphiaEntity<I>, I> extends IBasicDao<E, I>{

	/**
	 * 查询失信执行请求
	 * @param request
	 * @return
	 */
	public ShixinCourt getShixinCourt(GetShixinCourtRequest request);
	
	/**
	 * 根据code和name删除
	 * @param code
	 * @param name
	 * @return
	 */
	public void deleteByCodeAndName(String code, String name);
	
}
