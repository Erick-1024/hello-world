package com.cana.yundaex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cana.yundaex.common.dto.YundaexTstationSynDTO;
import com.cana.yundaex.dao.mapper.YundaexStationMapper;
import com.cana.yundaex.service.IYundaexTstationInfoService;

@Service
public class YundaexTstationInfoServiceImpl implements IYundaexTstationInfoService {

	@Resource
	private YundaexStationMapper yundaexStationMapper; 
	
	/**
	 * 拉取所有网点编号 以及最近一次 统计年月
	 * @return
	 */
	@Override
	public List<YundaexTstationSynDTO> getAllTstationAndMaxStatmonth(String endDate) {
		return yundaexStationMapper.getAllTstationAndMaxStatmonth(endDate);
	}

}
