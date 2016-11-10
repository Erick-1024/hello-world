package com.cana.asset.service.transaction;

import java.util.List;

import com.cana.vbam.common.asset.dto.MarketDataProjectExcelDTO;

public interface IABSMarketDataTransactionService {

	public void importMarketData(List<MarketDataProjectExcelDTO> projectExcelList);
}
