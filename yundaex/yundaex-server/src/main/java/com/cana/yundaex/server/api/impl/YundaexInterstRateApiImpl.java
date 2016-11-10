package com.cana.yundaex.server.api.impl;

import javax.annotation.Resource;

import com.cana.yundaex.api.IYundaexInterstRateApi;
import com.cana.yundaex.common.dto.InterestRateDTO;
import com.cana.yundaex.service.IYundaexInterestRateService;

/**
 * 韵达项目 利率API实现类
 * 
 * @author guguanggong
 *
 */
public class YundaexInterstRateApiImpl implements IYundaexInterstRateApi {

	@Resource
	private IYundaexInterestRateService yundaexInterestRateService;
	
	/**
	 * 根据id 获取利率
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public InterestRateDTO getInterestRateById(String id) {
		return yundaexInterestRateService.getInterestRateById(id);
	}

}
