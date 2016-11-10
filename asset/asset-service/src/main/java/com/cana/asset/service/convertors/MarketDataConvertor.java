package com.cana.asset.service.convertors;

import java.util.ArrayList;
import java.util.List;

import com.cana.asset.dao.po.AssetMarketDataProject;
import com.cana.vbam.common.asset.dto.MarketDataListDTO;
import com.cana.vbam.common.asset.enums.SupervisionAgencyEnum;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.travelzen.framework.core.util.MoneyUtil;

public class MarketDataConvertor {

	public static List<MarketDataListDTO> convertorProjects2ListDTO(List<AssetMarketDataProject> projects) {
		List<MarketDataListDTO> marketDataListDTOs = new ArrayList<>();
		for(AssetMarketDataProject assetMarketDataProject : projects){
			MarketDataListDTO marketDataDTO = new MarketDataListDTO();
			marketDataDTO.setId(assetMarketDataProject.getId());
			marketDataDTO.setProjectName(assetMarketDataProject.getProjectName());
			marketDataDTO.setIssueTotalAmount(MoneyUtil.cent2Yuan(assetMarketDataProject.getIssueTotalAmount()));
			marketDataDTO.setSupervisionAgency(SupervisionAgencyEnum.valueOf(assetMarketDataProject.getSupervisionAgency()).name());
			marketDataDTO.setSupervisionAgencyDesc(SupervisionAgencyEnum.valueOf(assetMarketDataProject.getSupervisionAgency()).desc());
			marketDataDTO.setUnderlyingAssetType(assetMarketDataProject.getUnderlyingAssetType());
			marketDataDTO.setIssueDate(assetMarketDataProject.getValueDate());
			if(null != assetMarketDataProject.getAaaAverageInterestRate())
				marketDataDTO.setAAAAverageInterestRate(MoneyArithUtil.convertInterestRateToString(assetMarketDataProject.getAaaAverageInterestRate()));
			if(null != assetMarketDataProject.getPriorityAverageInterestRate())
				marketDataDTO.setPriorityAverageInterestRate(MoneyArithUtil.convertInterestRateToString(assetMarketDataProject.getPriorityAverageInterestRate()));
			marketDataDTO.setOriginator(assetMarketDataProject.getOriginator());
			marketDataDTO.setIssuer(assetMarketDataProject.getIssuer());
			marketDataListDTOs.add(marketDataDTO);
		}
		return marketDataListDTOs;
	}

}
