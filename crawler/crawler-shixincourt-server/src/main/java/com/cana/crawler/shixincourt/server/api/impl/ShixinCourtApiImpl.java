package com.cana.crawler.shixincourt.server.api.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.crawler.dao.mongo.dao.IShixinCourtBlackListDao;
import com.cana.crawler.dao.mongo.dao.IShixinCourtDao;
import com.cana.crawler.dao.mongo.entity.ShixinCourt;
import com.cana.crawler.dao.mongo.entity.ShixinCourtBlackList;
import com.cana.crawler.shixincourt.api.IShixinCourtApi;
import com.cana.crawler.shixincourt.server.processor.ShixinCourtProcessor;
import com.cana.vbam.common.crawler.dto.GetShixinCourtRequest;
import com.cana.vbam.common.crawler.dto.GetShixinCourtResponse;
import com.travelzen.framework.core.common.ReturnCode;

public class ShixinCourtApiImpl implements IShixinCourtApi{
	
	@Resource
	private IShixinCourtDao<ShixinCourt, ObjectId> dao;
	
	@Resource
	private IShixinCourtBlackListDao<ShixinCourtBlackList, ObjectId> blackListDao;
	
	private Logger logger = LoggerFactory.getLogger(ShixinCourtApiImpl.class);
	

	@Override
	public GetShixinCourtResponse getShixinCourt(GetShixinCourtRequest request) {
		GetShixinCourtResponse response = new GetShixinCourtResponse();
		response.setRetCode(ReturnCode.ERROR);
		try{
			checkGetShixinCourtRequest(request);
			//先从缓存中获取
			boolean found = canFoundFromDB(request);
			if (found) {
				logger.info("缓存命中");
				response.setRetCode(ReturnCode.SUCCESS);
				response.setOnBlackList(true);
				return response;
			} else {
				logger.info("缓存未命中，需要实时爬取");
			}
			
			//暂时不去实时爬取
			response.setRetCode(ReturnCode.SUCCESS);
			response.setOnBlackList(false);
			return response;
			
//			ShixinCourt shixinCourt = new ShixinCourtProcessor().doCrawl(request);
//			updateDB(shixinCourt);
//			response.setRetCode(ReturnCode.SUCCESS);
//			response.setOnBlackList(shixinCourt.getCount() > 0);
		}catch(Throwable thr){
			logger.error("", thr);
			response.setRetMsg(thr.getMessage());
		}
		return response;
	}
	
	private boolean canFoundFromDB(GetShixinCourtRequest request){
		ShixinCourt shixinCourt = dao.getShixinCourt(request);
		if(shixinCourt != null && shixinCourt.getCount() > 0)
			return true;
		ShixinCourtBlackList blackList = blackListDao.findByCodeAndName(maskCode(request.getCode()), request.getName());
		if(blackList != null)
			return true;
		return false;
	}

	private String maskCode(String code) {
		code = StringUtils.trimToEmpty(code);
		if(code.length() != 18)
			return code;
		else return code.substring(0, 10) + "****" + code.substring(14, 18); 
	}

	private void updateDB(ShixinCourt shixinCourt) {
		dao.deleteByCodeAndName(shixinCourt.getCode(), shixinCourt.getName());
		dao.create(shixinCourt);
	}
	
	
	private void checkGetShixinCourtRequest(GetShixinCourtRequest request) throws Exception{
		
		if(request == null){
			throw new Exception("request为null");
		}
		
		if(request.getSubject() == null){
			throw new Exception("subject不能为null");
		}
		
		if(StringUtils.isBlank(request.getName())){
			throw new Exception("name不能为空");
		}
		
		if(StringUtils.isBlank(request.getCode())){
			throw new Exception("code不能为空");
		}
	
		
	}

}
