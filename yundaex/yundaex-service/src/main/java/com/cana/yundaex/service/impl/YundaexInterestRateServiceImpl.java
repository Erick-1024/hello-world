package com.cana.yundaex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cana.yundaex.common.dto.InterestRateDTO;
import com.cana.yundaex.dao.mapper.gen.InterestRateMapper;
import com.cana.yundaex.dao.po.InterestRate;
import com.cana.yundaex.dao.po.InterestRateExample;
import com.cana.yundaex.service.IYundaexInterestRateService;
import com.travelzen.framework.core.exception.WebException;

/**
 * @author hu
 *
 */
@Service
public class YundaexInterestRateServiceImpl implements IYundaexInterestRateService{

	@Resource
	InterestRateMapper rateMapper;
	
	@Override
	public List<InterestRate> getInterestRateByCustId(String CustomerId) {
		InterestRateExample exammple = new InterestRateExample();
		exammple.createCriteria().andCustomerIdEqualTo(CustomerId);
		
		return rateMapper.selectByExample(exammple);
	}
	
	@Override
	public InterestRateDTO getInterestRateById(String id){
		if(StringUtils.isBlank(id))
			throw WebException.instance("利率id不能为空");
		InterestRate interestRate = rateMapper.selectByPrimaryKey(id);
		InterestRateDTO interestRateDTO = new InterestRateDTO();
		BeanUtils.copyProperties(interestRate, interestRateDTO);
		return interestRateDTO;
	}

}
