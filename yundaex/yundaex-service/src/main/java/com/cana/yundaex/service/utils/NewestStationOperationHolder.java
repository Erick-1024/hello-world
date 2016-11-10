package com.cana.yundaex.service.utils;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.cana.yundaex.dao.mapper.gen.YundaexStationOperationMapper;
import com.cana.yundaex.dao.po.YundaexStationOperation;
import com.cana.yundaex.dao.po.YundaexStationOperationExample;

@Component
public class NewestStationOperationHolder implements InitializingBean{

	private static final Logger logger = LoggerFactory.getLogger(NewestStationOperationHolder.class);
	
	@Resource
	private YundaexStationOperationMapper ydStationOperationMapper;
	
	public static YundaexStationOperation newestStaionOperation = null;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		YundaexStationOperationExample example = new YundaexStationOperationExample();
		example.setOrderByClause("id desc");
		example.setLimitStart(0);
		example.setLimitEnd(1);
		List<YundaexStationOperation> ydStationOerations = ydStationOperationMapper.selectByExample(example);
		if(ydStationOerations == null || ydStationOerations.isEmpty()){
			logger.error("站点经营状况信息不能为空");
			newestStaionOperation = null;
			return;
		}
		newestStaionOperation = ydStationOerations.get(0);
	}

}
