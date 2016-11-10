package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

import com.cana.vbam.common.asset.enums.SpecialProgramStatus;

/**
 * SpecialProgramListRequestIssueDTO
 * @author jiangzhou.Ren
 * @time 2016年9月9日上午9:25:55
 */
public class SpecialProgramListRequestIssueDTO implements Serializable{
	
	
	private static final long serialVersionUID = 1728901309731206084L;

	/**
	 * 专项计划名称
	 */
	private String specialProgramName;

	/**
	 * 基础资产类型(保理)
	 */
	private String underlyingAssetType;
	
	
	/**
     *删除标志默认为０表示未删除１表示删除
     */
    private Boolean deleted;
    
    /**
     *管理人名称
     */
    private String managerName;
    
    
    /**
     *成立起始日期
     */
    private String startEstablishmentDate;
    
    /**
     *成立到期日期
     */
    private String endEstablishmentDate;
    
    /**
     * 状态　@SpecialProgramStatus
     */
    private SpecialProgramStatus status;
    
    private int page = 1;

	/**
	 * 页面显示条数
	 */
	private int pageSize = 10;
    

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

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getStartEstablishmentDate() {
		return startEstablishmentDate;
	}

	public void setStartEstablishmentDate(String startEstablishmentDate) {
		this.startEstablishmentDate = startEstablishmentDate;
	}

	public String getEndEstablishmentDate() {
		return endEstablishmentDate;
	}

	public void setEndEstablishmentDate(String endEstablishmentDate) {
		this.endEstablishmentDate = endEstablishmentDate;
	}

	public SpecialProgramStatus getStatus() {
		return status;
	}

	public void setStatus(SpecialProgramStatus status) {
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
