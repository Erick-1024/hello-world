/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

public class ProjectDocumentDTO implements Serializable {
   
	
	private static final long serialVersionUID = 3578800960999560769L;

	/**
     *主键
     */
    private String id;

    /**
     *关联的项目ID
     */
    private String projectId;

    /**
     *文件版本日期
     */
    private String version;

    /**
     *显示名称
     */
    private String name;

    /**
     *文件在媒体服务器的ID
     */
    private String mediaId;

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
     *关联的项目ID
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     *关联的项目ID
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    /**
     *文件版本日期
     */
    public String getVersion() {
        return version;
    }

    /**
     *文件版本日期
     */
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    /**
     *显示名称
     */
    public String getName() {
        return name;
    }

    /**
     *显示名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     *文件在媒体服务器的ID
     */
    public String getMediaId() {
        return mediaId;
    }

    /**
     *文件在媒体服务器的ID
     */
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
    }

}