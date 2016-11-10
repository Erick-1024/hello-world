package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.Date;

import com.cana.vbam.common.asset.enums.BasicAssetType;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;

/**
 * @author jiangzhou.Ren
 * @time 2016年8月29日上午10:21:42
 * SpecialProgramListDTO
 */
public class SpecialProgramListDTO implements Serializable{

	private static final long serialVersionUID = -4928190650553140779L;
	
	/**
	 * 主键专项计划编号
	 */
	private String id;
	/**
	 * 专项计划名称
	 */
	private String specialProgramName;
	
	/**
	 * 基础资产类型描述
	 */
	private String underlyingAssetTypeDesc;
	
	/**
	 * 基础资产类型(保理)
	 */
	private BasicAssetType underlyingAssetType;
	
	/**
	 * 管理人名称
	 */
	private String managerName;

	/**
	 * 预计成立日期
	 */
	private String estimateEstablishmentDate;
	
	/**
     *更新时间
     */
    private Date updateTime;
	
	private SpecialProgramStatus status;
	/**
	 * 列表显示状态
	 */
	private String statusDesc;
	
	/**
	 * 专项计划是否显示修改按钮
	 */
	 private boolean allowUpdate = true;
	    
	 /**
	  * 专项计划是否显示删除按钮
	  */
	 private boolean allowDelete = true;
	
	
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
	public String getUnderlyingAssetTypeDesc() {
		return underlyingAssetTypeDesc;
	}
	public void setUnderlyingAssetTypeDesc(String underlyingAssetTypeDesc) {
		this.underlyingAssetTypeDesc = underlyingAssetTypeDesc;
	}
	public BasicAssetType getUnderlyingAssetType() {
		return underlyingAssetType;
	}
	public void setUnderlyingAssetType(BasicAssetType underlyingAssetType) {
		this.underlyingAssetType = underlyingAssetType;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getEstimateEstablishmentDate() {
		return estimateEstablishmentDate;
	}
	public void setEstimateEstablishmentDate(String estimateEstablishmentDate) {
		this.estimateEstablishmentDate = estimateEstablishmentDate;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public SpecialProgramStatus getStatus() {
		return status;
	}
	public void setStatus(SpecialProgramStatus status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public boolean isAllowUpdate() {
		return allowUpdate;
	}
	public void setAllowUpdate(boolean allowUpdate) {
		this.allowUpdate = allowUpdate;
	}
	public boolean isAllowDelete() {
		return allowDelete;
	}
	public void setAllowDelete(boolean allowDelete) {
		this.allowDelete = allowDelete;
	}
	
}
