package com.cana.account.server.consts;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cana.account.dao.mapper.IBankListAndCityListSearchMapper;
import com.google.common.collect.Maps;

@Component
public class BankListAndCityComponent {
	
	@Resource
	private IBankListAndCityListSearchMapper bankListAndCityListSearchMapper;
	
	// 存储所有城市地区信息
	public static Map<String, List<String>> cityMap = Maps.newHashMap();
	
	@PostConstruct
	public void init(){
		// 程序启动城市地区信息
		
		// 查询所有省份
		List<String> provinces = bankListAndCityListSearchMapper.getAllProvince();
		for(String province : provinces){
			// 查询城市列表
			List<String> cityList = bankListAndCityListSearchMapper.getCityList(province);
			cityMap.put(province, cityList);
		}
		
	}
}
