package com.cana.vbam.common.asset.dto;

import com.cana.vbam.common.asset.enums.SpecialProgramStatus;
import com.cana.vbam.common.dto.Pagination;

/**
 * SpecialProgramListRequestDTO
 * @author jiangzhou.Ren
 * @time 2016年8月29日上午10:19:36
 */
public class SpecialProgramListRequestDTO extends Pagination{

	private static final long serialVersionUID = -6697076178145745915L;

    private String userId;

	/**
	 * 专项计划名称
	 */
	private String specialProgramName;

	/**
	 * 基础资产类型(保理)
	 */
	private String underlyingAssetType;
	

	/**
	 * 管理人名称
	 */
	private String managerName;

	/**
	 * 预计成立开始日期
	 */
	private String startEstimateEstablishmentDate;
	/**
	 * 预计成立结束日期
	 */

	private String endEstimateEstablishmentDate;

	/**
	 * 状态
	 */
	private SpecialProgramStatus status;

	/**
	 * 当为true时表示为基础资产管理模块查询可入池的专项计划列表，查询结果中不包含已结束的、成立状态下非循环购买结构的
	 */
	private boolean forUnderlyingAssetEnter;

	public String getStartEstimateEstablishmentDate() {
		return startEstimateEstablishmentDate;
	}
	public void setStartEstimateEstablishmentDate(String startEstimateEstablishmentDate) {
		this.startEstimateEstablishmentDate = startEstimateEstablishmentDate;
	}
	public String getEndEstimateEstablishmentDate() {
		return endEstimateEstablishmentDate;
	}
	public void setEndEstimateEstablishmentDate(String endEstimateEstablishmentDate) {
		this.endEstimateEstablishmentDate = endEstimateEstablishmentDate;
	}
	public SpecialProgramStatus getStatus() {
		return status;
	}
	public void setStatus(SpecialProgramStatus status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSpecialProgramName() {
		return specialProgramName;
	}
	public void setSpecialProgramName(String specialProgramName) {
		this.specialProgramName = specialProgramName;
	}
	public String getUnderlyingAssetType() {
		return underlyingAssetType;
	}
	public void setUnderlyingAssetType(String underlyingAssetType) {
		this.underlyingAssetType = underlyingAssetType;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public boolean isForUnderlyingAssetEnter() {
		return forUnderlyingAssetEnter;
	}
	public void setForUnderlyingAssetEnter(boolean forUnderlyingAssetEnter) {
		this.forUnderlyingAssetEnter = forUnderlyingAssetEnter;
	}
}
