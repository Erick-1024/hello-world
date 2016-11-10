package com.cana.asset.api;

import java.util.List;

import com.cana.vbam.common.asset.dto.EchartsQuery;
import com.cana.vbam.common.asset.dto.EchartsResponseDTO;
import com.cana.vbam.common.asset.dto.MarketDataListDTO;
import com.cana.vbam.common.asset.dto.MarketDataProductExcelDTO;
import com.cana.vbam.common.asset.dto.MarketDataProjectExcelDTO;
import com.cana.vbam.common.asset.dto.MarketDataQueryDTO;
import com.cana.vbam.common.asset.dto.MarketDataSummaryDTO;
import com.travelzen.framework.common.PageList;

public interface IABSMarketResearchApi {

	/**
	 * echarts 图表数据
	 * @param queryDTO
	 * @return
	 */
	EchartsResponseDTO getMarketDataReport(EchartsQuery queryDTO);

	/**
	 * 市场数据总览页面数据
	 * @return
	 */
	MarketDataSummaryDTO getMarketDataSummary();

	/**
	 * 市场数据列表
	 * @param queryDTO
	 * @return
	 */
	PageList<MarketDataListDTO> getMarketDataSearchList(MarketDataQueryDTO queryDTO);

	/**
	 * 企业资产证券化产品
	 * @return
	 */
	MarketDataSummaryDTO getMarketProduct();

	/**
	 * 市场数据详情页
	 * @param id
	 * @return
	 */
	MarketDataListDTO getMarketDataDetail(String id);

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

	/**
	 * 查询以存在的基础资产类型
	 * @return
	 */
	List<String> getUnderlyingAssetType();
}
