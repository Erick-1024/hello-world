package com.cana.vbam.common.asset.underlyingasset.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 专项计划日志返回结果集
 * @author yihong.tang
 */
public class SpecialProgramLogDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     *主键
     */
    private String id;

    /**
     *专项计划编号
     */
    private String specialProgramId;

    /**
     *专项计划名称
     */
    private String specialProgramName;

    /**
     *基础资产类型 @see UnderlyingAssetSource
     */
    private String underlyingAssetType;
    
    /**
     *基础资产类型描述 @see UnderlyingAssetSource
     */
    private String underlyingAssetTypeDesc;

    /**
     *资产池金额
     */
    private String assetPoolAmount;

    /**
     *操作人的企业名称
     */
    private String operateCompanyName;

    /**
     *操作人的用户名
     */
    private String operateUsername;

    /**
     *操作类型 @see SpecialProgramStatus
     */
    private String operateType;
    
    /**
     *操作类型描述 @see SpecialProgramStatus
     */
    private String operateTypeDesc;

    /**
     *操作时间
     */
    private Date createTime;

    /**
     *主键
     */
    public String getId() {
        return id;
    }

    /**
     *主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *专项计划编号
     */
    public String getSpecialProgramId() {
        return specialProgramId;
    }

    /**
     *专项计划编号
     */
    public void setSpecialProgramId(String specialProgramId) {
        this.specialProgramId = specialProgramId == null ? null : specialProgramId.trim();
    }

    /**
     *
     */
    public String getSpecialProgramName() {
        return specialProgramName;
    }

    /**
     *
     */
    public void setSpecialProgramName(String specialProgramName) {
        this.specialProgramName = specialProgramName == null ? null : specialProgramName.trim();
    }

    /**
     *基础资产类型
     */
    public String getUnderlyingAssetType() {
        return underlyingAssetType;
    }

    /**
     *基础资产类型
     */
    public void setUnderlyingAssetType(String underlyingAssetType) {
        this.underlyingAssetType = underlyingAssetType == null ? null : underlyingAssetType.trim();
    }

    /**
     *操作人的企业名称
     */
    public String getOperateCompanyName() {
        return operateCompanyName;
    }

    /**
     *操作人的企业名称
     */
    public void setOperateCompanyName(String operateCompanyName) {
        this.operateCompanyName = operateCompanyName == null ? null : operateCompanyName.trim();
    }

    /**
     *操作人的用户名
     */
    public String getOperateUsername() {
        return operateUsername;
    }

    /**
     *操作人的用户名
     */
    public void setOperateUsername(String operateUsername) {
        this.operateUsername = operateUsername == null ? null : operateUsername.trim();
    }

    /**
     *操作类型 @see SpecialProgramStatus
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     *操作类型 @see SpecialProgramStatus
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    /**
     *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SpecialProgramLogDTO other = (SpecialProgramLogDTO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSpecialProgramId() == null ? other.getSpecialProgramId() == null : this.getSpecialProgramId().equals(other.getSpecialProgramId()))
            && (this.getSpecialProgramName() == null ? other.getSpecialProgramName() == null : this.getSpecialProgramName().equals(other.getSpecialProgramName()))
            && (this.getUnderlyingAssetType() == null ? other.getUnderlyingAssetType() == null : this.getUnderlyingAssetType().equals(other.getUnderlyingAssetType()))
            && (this.getAssetPoolAmount() == null ? other.getAssetPoolAmount() == null : this.getAssetPoolAmount().equals(other.getAssetPoolAmount()))
            && (this.getOperateCompanyName() == null ? other.getOperateCompanyName() == null : this.getOperateCompanyName().equals(other.getOperateCompanyName()))
            && (this.getOperateUsername() == null ? other.getOperateUsername() == null : this.getOperateUsername().equals(other.getOperateUsername()))
            && (this.getOperateType() == null ? other.getOperateType() == null : this.getOperateType().equals(other.getOperateType()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSpecialProgramId() == null) ? 0 : getSpecialProgramId().hashCode());
        result = prime * result + ((getSpecialProgramName() == null) ? 0 : getSpecialProgramName().hashCode());
        result = prime * result + ((getUnderlyingAssetType() == null) ? 0 : getUnderlyingAssetType().hashCode());
        result = prime * result + ((getAssetPoolAmount() == null) ? 0 : getAssetPoolAmount().hashCode());
        result = prime * result + ((getOperateCompanyName() == null) ? 0 : getOperateCompanyName().hashCode());
        result = prime * result + ((getOperateUsername() == null) ? 0 : getOperateUsername().hashCode());
        result = prime * result + ((getOperateType() == null) ? 0 : getOperateType().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

	public String getUnderlyingAssetTypeDesc() {
		return underlyingAssetTypeDesc;
	}

	public void setUnderlyingAssetTypeDesc(String underlyingAssetTypeDesc) {
		this.underlyingAssetTypeDesc = underlyingAssetTypeDesc;
	}

	public String getOperateTypeDesc() {
		return operateTypeDesc;
	}

	public void setOperateTypeDesc(String operateTypeDesc) {
		this.operateTypeDesc = operateTypeDesc;
	}

	public void setAssetPoolAmount(String assetPoolAmount) {
		this.assetPoolAmount = assetPoolAmount;
	}

	public String getAssetPoolAmount() {
		return assetPoolAmount;
	}
}