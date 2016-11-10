package com.cana.asset.service;

import java.util.List;

import com.cana.vbam.common.asset.dto.EchartsQuery;
import com.cana.vbam.common.asset.dto.EchartsResponseDTO;
import com.cana.vbam.common.asset.dto.MarketDataListDTO;
import com.cana.vbam.common.asset.dto.MarketDataQueryDTO;
import com.cana.vbam.common.asset.dto.MarketDataSummaryDTO;
import com.travelzen.framework.common.PageList;

public interface IABSMarketResearchService {

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
	 * 市场数据详情
	 * @param id
	 * @return
	 */
	MarketDataListDTO getMarketDataDetail(String id);

	/**
	 * 
	 * @return
	 */
	List<String> getUnderlyingAssetType();

}
