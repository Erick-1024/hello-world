/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

public class ProjectListResponseDTO implements Serializable {
	
	

	private static final long serialVersionUID = 2756421511184618564L;

	/**
	 * 项目id
	 */
	private String id;
	
	/**
     *项目名称
     */
    private String name;
    
	/**
     *核心企业名称
     */
    private String coreCompanyName;
    
    /**
     *项目类型，平台、保理、租赁、小贷
     */
    private String typeDesc;
    /*
     * 枚举英文标记
     */
    private String projectStatus;
    
   

	/**
     *行业
     */
    private String coreIndustry;
    
    /**
     *项目状态：立项、进行中、暂停、结束
     */
    private String statusDesc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoreCompanyName() {
		return coreCompanyName;
	}

	public void setCoreCompanyName(String coreCompanyName) {
		this.coreCompanyName = coreCompanyName;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getCoreIndustry() {
		return coreIndustry;
	}

	public void setCoreIndustry(String coreIndustry) {
		this.coreIndustry = coreIndustry;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
    
    
	 

  
}