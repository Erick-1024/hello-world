/*package com.cana.vbam.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.common.dao.mapper.gen.CommonAreaCodeMapper;
import com.cana.common.dao.po.CommonAreaCode;
import com.cana.common.dao.po.CommonAreaCodeExample;
import com.cana.vbam.common.service.QueryCity;

public class QueryCityImpl implements QueryCity {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private CommonAreaCodeMapper commonAreaCodeMapper;
	
	
	 * 查询省
	 * (non-Javadoc)
	 * @see com.cana.vbam.common.service.QueryCity#getQueryCity()
	 
	@Override
	public List<CommonAreaCode> getQueryCity() {
		CommonAreaCodeExample commonAreaCodeExample = new	CommonAreaCodeExample();
		logger.info("开始查询省");
		return commonAreaCodeMapper.selectByExample(commonAreaCodeExample);
	}
	
}*/
