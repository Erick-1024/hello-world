package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

public class MarketDataQueryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5364480543375310745L;

	private String supervisionAgency;		//监管机构
	private String underlyingAssetType;		//基础资产类型
	private String valueDate;				//发行时间
	private String originator;				//发起机构
	private String issuer;					//发行人
    private int page;
	private int pageSize;
	public String getSupervisionAgency() {
		return supervisionAgency;
	}
	public void setSupervisionAgency(String supervisionAgency) {
		this.supervisionAgency = supervisionAgency;
	}
	public String getUnderlyingAssetType() {
		return underlyingAssetType;
	}
	public void setUnderlyingAssetType(String underlyingAssetType) {
		this.underlyingAssetType = underlyingAssetType;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getOriginator() {
		return originator;
	}
	public void setOriginator(String originator) {
		this.originator = originator;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
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
	
	
}
