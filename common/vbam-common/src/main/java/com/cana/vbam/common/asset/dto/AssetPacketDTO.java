package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

public class AssetPacketDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5427621502047283242L;

	/**
	 * 专项计划编号
	 */
	private String id ;
	
	/** 初始资产池规模   */
	private String originAssetpoolScale;
	
	/** 封包日期 */
	private String encapsulationDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriginAssetpoolScale() {
		return originAssetpoolScale;
	}

	public void setOriginAssetpoolScale(String originAssetpoolScale) {
		this.originAssetpoolScale = originAssetpoolScale;
	}

	public String getEncapsulationDate() {
		return encapsulationDate;
	}

	public void setEncapsulationDate(String encapsulationDate) {
		this.encapsulationDate = encapsulationDate;
	}
	
	
	
}
