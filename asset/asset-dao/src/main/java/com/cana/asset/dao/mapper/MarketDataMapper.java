package com.cana.asset.dao.mapper;

import java.util.List;

import com.cana.vbam.common.asset.dto.MarketDataReportDTO;

public interface MarketDataMapper {

	 List<MarketDataReportDTO> selectSummaryProjectsByTypeAndMonth();
	 
	 List<String> selectUnderlyingAssetType();
}
