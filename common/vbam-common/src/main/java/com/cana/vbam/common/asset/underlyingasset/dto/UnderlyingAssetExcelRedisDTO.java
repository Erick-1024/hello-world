package com.cana.vbam.common.asset.underlyingasset.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author XuMeng
 *
 */
public class UnderlyingAssetExcelRedisDTO implements Serializable {

	private static final long serialVersionUID = 6842830905311940378L;

	private List<UnderlyingAssetExcelDTO> passUnderlyingAssetList;

	private List<UnderlyingAssetExcelDTO> notPassUnderlyingAssetList;

	private Set<String> underlyingAssetIds;

	public List<UnderlyingAssetExcelDTO> getPassUnderlyingAssetList() {
		return passUnderlyingAssetList;
	}

	public void setPassUnderlyingAssetList(List<UnderlyingAssetExcelDTO> passUnderlyingAssetList) {
		this.passUnderlyingAssetList = passUnderlyingAssetList;
	}

	public List<UnderlyingAssetExcelDTO> getNotPassUnderlyingAssetList() {
		return notPassUnderlyingAssetList;
	}

	public void setNotPassUnderlyingAssetList(List<UnderlyingAssetExcelDTO> notPassUnderlyingAssetList) {
		this.notPassUnderlyingAssetList = notPassUnderlyingAssetList;
	}

	public Set<String> getUnderlyingAssetIds() {
		return underlyingAssetIds;
	}

	public void setUnderlyingAssetIds(Set<String> underlyingAssetIds) {
		this.underlyingAssetIds = underlyingAssetIds;
	}

}
