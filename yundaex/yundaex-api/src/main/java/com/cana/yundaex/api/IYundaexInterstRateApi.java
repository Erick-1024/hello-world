package com.cana.yundaex.api;

import com.cana.yundaex.common.dto.InterestRateDTO;

public interface IYundaexInterstRateApi {
	/**
	 * 根据id 获取利率
	 * @param id
	 * @return
	 */
	public InterestRateDTO getInterestRateById(String id);
}
