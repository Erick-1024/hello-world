package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

public class SpecialProgramQueryDTO implements Serializable {

	private static final long serialVersionUID = 6037312177889338048L;

	/**
	 * 专项计划ID
	 */
	private String id;

	/**
	 * 专项计划名称
	 */
	private String specialProgramName;
	/**
	 * 基础资产类型
	 */
	private String underlyingAssetType;
	/**
	 * 管理人名称
	 */
	private String managerName;
	/**
	 * 预计成立开始日期
	 */
	private String startDate;
	/**
	 * 预计成立结束日期
	 */
	private String endDate;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 当前页数
	 */
	private int page = 1;

	/**
	 * 页面显示条数
	 */
	private int pageSize = 10;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
