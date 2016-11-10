package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.List;

public class MarketDataRedisDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1202574877415287044L;

	private List<MarketDataProjectExcelDTO> projectExcelList;

	public List<MarketDataProjectExcelDTO> getProjectExcelList() {
		return projectExcelList;
	}

	public void setProjectExcelList(List<MarketDataProjectExcelDTO> projectExcelList) {
		this.projectExcelList = projectExcelList;
	}
	
}
