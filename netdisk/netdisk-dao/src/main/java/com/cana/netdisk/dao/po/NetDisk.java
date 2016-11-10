/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.netdisk.dao.po;

import java.io.Serializable;
import java.util.Date;

public class NetDisk implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *模块(ASSET_ARCHIVES：资产证券化-档案管理)
     */
    private String module;

    /**
     *组Id
     */
    private String groupId;

    /**
     *路径（“/”为根目录）
     */
    private String path;

    /**
     *类型（DIRECTORY：目录；FILE：文件）
     */
    private String type;

    /**
     *名称
     */
    private String name;

    /**
     *文件的媒体服务器ID
     */
    private String mediaId;

    /**
     *文件大小
     */
    private Long size;

    /**
     *创建人ID
     */
    private String creatorId;

    /**
     *
     */
    private Date createTime;

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
     *模块(ASSET_ARCHIVES：资产证券化-档案管理)
     */
    public String getModule() {
        return module;
    }

    /**
     *模块(ASSET_ARCHIVES：资产证券化-档案管理)
     */
    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    /**
     *组Id
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     *组Id
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    /**
     *路径（“/”为根目录）
     */
    public String getPath() {
        return path;
    }

    /**
     *路径（“/”为根目录）
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     *类型（DIRECTORY：目录；FILE：文件）
     */
    public String getType() {
        return type;
    }

    /**
     *类型（DIRECTORY：目录；FILE：文件）
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     *名称
     */
    public String getName() {
        return name;
    }

    /**
     *名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     *文件的媒体服务器ID
     */
    public String getMediaId() {
        return mediaId;
    }

    /**
     *文件的媒体服务器ID
     */
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
    }

    /**
     *文件大小
     */
    public Long getSize() {
        return size;
    }

    /**
     *文件大小
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     *
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     *
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    /**
     *创建人ID
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建人ID
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
        NetDisk other = (NetDisk) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getModule() == null ? other.getModule() == null : this.getModule().equals(other.getModule()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getMediaId() == null ? other.getMediaId() == null : this.getMediaId().equals(other.getMediaId()))
            && (this.getSize() == null ? other.getSize() == null : this.getSize().equals(other.getSize()))
            && (this.getCreatorId() == null ? other.getCreatorId() == null : this.getCreatorId().equals(other.getCreatorId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getModule() == null) ? 0 : getModule().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getMediaId() == null) ? 0 : getMediaId().hashCode());
        result = prime * result + ((getSize() == null) ? 0 : getSize().hashCode());
        result = prime * result + ((getCreatorId() == null) ? 0 : getCreatorId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}