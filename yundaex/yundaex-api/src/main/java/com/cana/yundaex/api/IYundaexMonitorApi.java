package com.cana.yundaex.api;

import java.math.BigDecimal;
import java.util.List;

import com.cana.yundaex.common.dto.monitor.YundaexMonitorImportDTO;
import com.cana.yundaex.common.dto.monitor.YundaexmonitorGradeInfoDTO;
import com.travelzen.framework.common.PageList;

/**
 * 韵达监控
 * @author xiaoyu
 *
 */
public interface IYundaexMonitorApi {

	/**
	 * 获取redisKey
	 * @return
	 */
	public String generateRediskey();

	/**
	 * 批量保存excel数据到redis
	 * @param customerId
	 * @param rediskey
	 * @param list
	 */
	public void batchSaveToRedis(String customerId, String rediskey, List<YundaexMonitorImportDTO> list);

	/**
	 * 监控信息导入DB
	 * @param redisKey
	 * @param customerId
	 */
	public void importExcelToDB(String redisKey, String customerId);

	/**
	 * 查询韵达监控导入数据
	 * @param key
	 * @param customerId
	 * @param status
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public PageList<YundaexMonitorImportDTO> queryMonitorInfoFromRedis(String key, String customerId, String status,int page, int pageSize);
	
	
	/**
	 * 根据评级分数获取韵达评级信息
	 */
	YundaexmonitorGradeInfoDTO getYundaexGradeInfoByScore(BigDecimal score);

}
