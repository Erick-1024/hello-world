package com.cana.asset.server.api.impl;

import java.util.List;

import javax.annotation.Resource;

import com.cana.asset.api.IABSMarketResearchApi;
import com.cana.asset.service.IABSMarketResearchService;
import com.cana.asset.service.IMarketDataImportService;
import com.cana.vbam.common.asset.dto.EchartsQuery;
import com.cana.vbam.common.asset.dto.EchartsResponseDTO;
import com.cana.vbam.common.asset.dto.MarketDataListDTO;
import com.cana.vbam.common.asset.dto.MarketDataProductExcelDTO;
import com.cana.vbam.common.asset.dto.MarketDataProjectExcelDTO;
import com.cana.vbam.common.asset.dto.MarketDataQueryDTO;
import com.cana.vbam.common.asset.dto.MarketDataSummaryDTO;
import com.travelzen.framework.common.PageList;

public class ABSMarketResearchApiImpl implements IABSMarketResearchApi {
	
	@Resource
	private IABSMarketResearchService absMarketResearchService;

	@Resource
	private IMarketDataImportService marketDataImportService;
	
	@Override
	public EchartsResponseDTO getMarketDataReport(EchartsQuery queryDTO) {
		return absMarketResearchService.getMarketDataReport(queryDTO);
	}

	@Override
	public MarketDataSummaryDTO getMarketDataSummary() {
		return absMarketResearchService.getMarketDataSummary();
	}

	@Override
	public PageList<MarketDataListDTO> getMarketDataSearchList(MarketDataQueryDTO queryDTO) {
		return absMarketResearchService.getMarketDataSearchList(queryDTO);
	}

	@Override
	public MarketDataSummaryDTO getMarketProduct() {
		return absMarketResearchService.getMarketProduct();
	}

	@Override
	public MarketDataListDTO getMarketDataDetail(String id) {
		return absMarketResearchService.getMarketDataDetail(id);
	}

	@Override
	public int importMarketDataExcel2Redis(List<MarketDataProjectExcelDTO> projectExcelList,
			List<MarketDataProductExcelDTO> productExcelList, String operatorId, String rediskey) {
		return marketDataImportService.importMarketDataExcel2Redis(projectExcelList, productExcelList, operatorId, rediskey);
	}

	@Override
	public void importMarketDataExcel2DB(String operatorId, String rediskey) {
		marketDataImportService.importMarketDataExcel2DB(operatorId, rediskey);
	}

	@Override
	public String generateMarketDataRedisKey() {
		return marketDataImportService.generateMarketDataRedisKey();
	}

	@Override
	public List<String> getUnderlyingAssetType() {
		return absMarketResearchService.getUnderlyingAssetType();
	}

}
