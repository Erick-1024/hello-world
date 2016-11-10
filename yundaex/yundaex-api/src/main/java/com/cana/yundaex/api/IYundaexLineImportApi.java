package com.cana.yundaex.api;

import java.text.ParseException;
import java.util.List;

import com.cana.yundaex.common.dto.YundaexLineImportDTO;
import com.travelzen.framework.common.PageList;

public interface IYundaexLineImportApi {

	/**
	 * 获取redisKey
	 * 
	 * @return
	 */
	public String generateRediskey();

	/**
	 * 保存excel数据到redis
	 * 
	 * @throws ParseException
	 */
	public void batchSaveToRedis(String customerId, String rediskey, List<YundaexLineImportDTO> list);

	/**
	 * 查询redis数据
	 * 
	 * @param rediskey
	 * @param page
	 * @param pageSize
	 * @param status
	 * @return
	 */
	public PageList<YundaexLineImportDTO> queryLineRedis(String rediskey,String status, int page, int pageSize);

	/**
	 * 将redis数据导入mysql
	 * 
	 * @param redisKey
	 * @param customerId
	 * @param operatorId
	 */
	public void importExcelList(String redisKey, String customerId, String operatorId);

}
