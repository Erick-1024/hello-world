package com.cana.yundaex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.yundaex.dao.mapper.gen.YundaexCustomerGradeMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexOutCustomerMapper;
import com.cana.yundaex.dao.po.YundaexCustomerGrade;
import com.cana.yundaex.dao.po.YundaexCustomerGradeExample;
import com.cana.yundaex.dao.po.YundaexOutCustomer;
import com.cana.yundaex.dao.po.YundaexOutCustomerExample;
import com.cana.yundaex.service.IYundaexCustomerGradeService;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

@Service
public class YundaexCustomerGradeServiceImpl implements IYundaexCustomerGradeService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private YundaexOutCustomerMapper ydOutCustomerMapper;
	
	@Resource
	private YundaexCustomerGradeMapper ydCustomerGradeMapper;

	@Override
	public YundaexCustomerGrade getUserGrade(String userId) {
		YundaexOutCustomerExample outCustomerExample = new YundaexOutCustomerExample();
		outCustomerExample.createCriteria().andMemberIdEqualTo(userId);
		List<YundaexOutCustomer> ydOutCustomers = ydOutCustomerMapper.selectByExample(outCustomerExample); 
		if(CollectionUtils.isNotEmpty(ydOutCustomers)){
			String stationNo = ydOutCustomers.get(0).getStationNo(); 
			YundaexCustomerGradeExample example = new YundaexCustomerGradeExample();
			example.createCriteria().andStationNoEqualTo(stationNo);
			List<YundaexCustomerGrade> ydCustomerGrades = ydCustomerGradeMapper.selectByExample(example); 
			if(CollectionUtils.isNotEmpty(ydCustomerGrades)){
				return ydCustomerGrades.get(0);
			}else{
				logger.error("yundaex_customer_grade表中没有该用户的等级信息");
				throw WebException.instance(ReturnCode.ERROR);
			}
		}else{
			logger.error("yundaex_out_customer表中没有该用户信息");
			throw WebException.instance(ReturnCode.ERROR);
		}
	}

}
