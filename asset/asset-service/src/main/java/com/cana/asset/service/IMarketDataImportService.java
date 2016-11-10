package com.cana.asset.service;

import java.util.List;

import com.cana.vbam.common.asset.dto.MarketDataProductExcelDTO;
import com.cana.vbam.common.asset.dto.MarketDataProjectExcelDTO;

/**
 * 市场数据excel导入
 * @author hu
 */
public interface IMarketDataImportService {

	/**
	 * 导入暂存redis
	 */
	public int importMarketDataExcel2Redis(List<MarketDataProjectExcelDTO> projectExcelList,
			List<MarketDataProductExcelDTO> productExcelList,String operatorId, String rediskey);
	
	/**
	 * 导入数据库
	 */
	public void importMarketDataExcel2DB(String operatorId, String rediskey);

	public String generateMarketDataRedisKey();
	
}
