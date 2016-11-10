package com.cana.yundaex.common.dto.contract;

import java.io.Serializable;

/**
 * @author hu
 *
 */
public class ContractSituationQueryCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3724884825343420605L;
	
	private String stationName;
	
	private String comleteStartTime;
	
	private String comleteEndTime;
	
	private String contractSignState;
	
	private int page;
	
	private int pageSize;

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getComleteStartTime() {
		return comleteStartTime;
	}

	public void setComleteStartTime(String comleteStartTime) {
		this.comleteStartTime = comleteStartTime;
	}

	public String getContractSignState() {
		return contractSignState;
	}

	public void setContractSignState(String contractSignState) {
		this.contractSignState = contractSignState;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getComleteEndTime() {
		return comleteEndTime;
	}

	public void setComleteEndTime(String comleteEndTime) {
		this.comleteEndTime = comleteEndTime;
	}
}
