/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.util.Date;

public class ProjectDocument implements Serializable {
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
     *是否已经被删除了
     */
    private Boolean deleted;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新记录时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

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

    /**
     *是否已经被删除了
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     *是否已经被删除了
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     *创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *更新记录时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *更新记录时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        ProjectDocument other = (ProjectDocument) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getMediaId() == null ? other.getMediaId() == null : this.getMediaId().equals(other.getMediaId()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getMediaId() == null) ? 0 : getMediaId().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}