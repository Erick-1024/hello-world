package com.cana.asset.service;

import java.util.List;

import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetExcelDTO;
import com.cana.vbam.common.dto.ListResult;

/**
 * 基础资产excel导入
 * @author XuMeng
 *
 */
public interface IUnderlyingAssetImportService {

	/**
	 * 导入暂存redis
	 */
	public void importUnderlyingAssetExcel2Redis(List<UnderlyingAssetExcelDTO> loanExcelList, String operatorId, String rediskey);
	
	/**
	 * 从redis获取
	 */
	public ListResult<UnderlyingAssetExcelDTO> getUnderlyingAssetFromRedis(String redisKey, String operatorId, boolean passed, int page, int pageSize);

	/**
	 * 导入数据库
	 */
	public void importUnderlyingAssetExcel2DB(String operatorId, String rediskey);

	public String generateUnderlyingAssetRedisKey();
	
}
