package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.Date;

import com.cana.vbam.common.asset.enums.BasicAssetType;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;

/**
 * 发行后专项计划管理列表
 * @author jiangzhou.Ren
 * @time 2016年9月9日上午9:22:15
 */
public class SpecialProgramIssueListDTO implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 543489506226530235L;
	
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
     *删除标志默认为０表示未删除１表示删除
     */
    private Boolean deleted;
    
    /**
	 * 管理人名称
	 */
	private String managerName;
	
	 /**
     *成立日期
     */
    private String establishmentDate;
    
    /**
     *总规模
     */
    private String gross;
    
    /**
     *法定到期日期
     */
    private String statutoryDueDate;
	
	
    /**
     * 专项计划
     */
	private SpecialProgramStatus status;
	/**
	 * 列表显示状态
	 */
	private String statusDesc;
	
	/**
     *更新时间
     */
    private Date updateTime;
    
    //成立按钮显示
    private boolean allowIssue = true;
    
    private boolean allowManage = true;
    
    
	public boolean isAllowIssue() {
		return allowIssue;
	}

	public void setAllowIssue(boolean allowIssue) {
		this.allowIssue = allowIssue;
	}

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

	public String getEstablishmentDate() {
		return establishmentDate;
	}

	public void setEstablishmentDate(String establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	public String getGross() {
		return gross;
	}

	public void setGross(String gross) {
		this.gross = gross;
	}

	public String getStatutoryDueDate() {
		return statutoryDueDate;
	}

	public void setStatutoryDueDate(String statutoryDueDate) {
		this.statutoryDueDate = statutoryDueDate;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isAllowManage() {
		return allowManage;
	}

	public void setAllowManage(boolean allowManage) {
		this.allowManage = allowManage;
	}
	
	
}
