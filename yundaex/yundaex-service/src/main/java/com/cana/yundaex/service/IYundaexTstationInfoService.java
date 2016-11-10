package com.cana.yundaex.service;

import java.util.List;

import com.cana.yundaex.common.dto.YundaexTstationSynDTO;

public interface IYundaexTstationInfoService {

	/**
	 * 拉取所有网点编号 以及最近一次 统计年月
	 * @return
	 */
	public List<YundaexTstationSynDTO> getAllTstationAndMaxStatmonth(String endDate);

}
