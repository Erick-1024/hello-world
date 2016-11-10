package com.cana.yundaex.service;

import java.util.List;

import com.cana.yundaex.common.dto.InterestRateDTO;
import com.cana.yundaex.dao.po.InterestRate;

/**
 * @author hu
 *
 */
public interface IYundaexInterestRateService {
	
	/**
	 * 根据客户id 获取利率
	 * @param CustomerId
	 * @return
	 */
	public List<InterestRate> getInterestRateByCustId(String CustomerId);
	
	/**
	 * 根据id 获取利率
	 * @param id
	 * @return
	 */
	public InterestRateDTO getInterestRateById(String id);
}
