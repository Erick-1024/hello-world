package com.cana.vbam.common.asset.underlyingasset.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 入池请求类
 * 
 * @author XuMeng
 *
 */
public class EnterAssetPoolRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> underlyingAssetIds; // 本次选择的基础资产ID列表
	private String specialProgramId; // 待进入的专项计划资产池

	public List<String> getUnderlyingAssetIds() {
		return underlyingAssetIds;
	}

	public void setUnderlyingAssetIds(List<String> underlyingAssetIds) {
		this.underlyingAssetIds = underlyingAssetIds;
	}

	public String getSpecialProgramId() {
		return specialProgramId;
	}

	public void setSpecialProgramId(String specialProgramId) {
		this.specialProgramId = specialProgramId;
	}

}
