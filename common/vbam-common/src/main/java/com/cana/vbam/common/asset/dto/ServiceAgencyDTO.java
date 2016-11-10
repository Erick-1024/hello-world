package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jiangzhou.Ren
 * @time 2016年8月30日下午1:51:44
 * 资产服务机构
 */
public class ServiceAgencyDTO implements Serializable{
	
	private static final long serialVersionUID = 4077255465520208554L;

	/**
     *主键 
     */
    private String id;

    /**
     *专项计划id
     */
    private String specialProgramId;

    /**
     *资产服务机构名称
     */
    private String serviceAgencyName;

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

	public String getServiceAgencyName() {
		return serviceAgencyName;
	}

	public void setServiceAgencyName(String serviceAgencyName) {
		this.serviceAgencyName = serviceAgencyName;
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
