package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jiangzhou.Ren
 * @time 2016年8月30日下午2:13:15
 * 原始权益人
 */
public class OriginatorDTO implements Serializable{
	
	private static final long serialVersionUID = 1401656373518452418L;

	/**
     *主键 
     */
    private String id;

    /**
     *专项计划id
     */
    private String specialProgramId;

    /**
     *原始权限人名称
     */
    private String originatorName;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date upateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSpecialProgramId() {
		return specialProgramId;
	}

	public void setSpecialProgramId(String specialProgramId) {
		this.specialProgramId = specialProgramId;
	}

	public String getOriginatorName() {
		return originatorName;
	}

	public void setOriginatorName(String originatorName) {
		this.originatorName = originatorName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpateTime() {
		return upateTime;
	}

	public void setUpateTime(Date upateTime) {
		this.upateTime = upateTime;
	}
    
    
    
}
