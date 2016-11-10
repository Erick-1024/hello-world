package com.cana.yundaex.dao.mapper;

import java.util.List;

import com.cana.yundaex.common.dto.YundaexTstationSynDTO;

public interface YundaexStationMapper {
	
	/**
	 * 查询所有的网点的编号以及最近一次统计年月
	 * @return
	 */
	public List<YundaexTstationSynDTO> getAllTstationAndMaxStatmonth(String endDate);
}
