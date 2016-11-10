/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.member.dao.po;

import java.io.Serializable;
import java.util.Date;

public class Permission implements Serializable {
    /**
     *key值
     */
    private String id;

    /**
     *权限名称
     */
    private String name;

    /**
     *权限对应的地址
     */
    private String url;

    /**
     *前端所属的前端项目
     */
    private String platform;

    /**
     *MODULE, BUTTON, PERM; 三种权限类型
     */
    private String type;

    /**
     *父级权限
     */
    private String parentId;

    /**
     *序号
     */
    private Integer ord;

    /**
     *权限描述
     */
    private String description;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     *key值
     */
    public String getId() {
        return id;
    }

    /**
     *key值
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *权限名称
     */
    public String getName() {
        return name;
    }

    /**
     *权限名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     *权限对应的地址
     */
    public String getUrl() {
        return url;
    }

    /**
     *权限对应的地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     *前端所属的前端项目
     */
    public String getPlatform() {
        return platform;
    }

    /**
     *前端所属的前端项目
     */
    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    /**
     *MODULE, BUTTON, PERM; 三种权限类型
     */
    public String getType() {
        return type;
    }

    /**
     *MODULE, BUTTON, PERM; 三种权限类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     *父级权限
     */
    public String getParentId() {
        return parentId;
    }

    /**
     *父级权限
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     *序号
     */
    public Integer getOrd() {
        return ord;
    }

    /**
     *序号
     */
    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    /**
     *权限描述
     */
    public String getDescription() {
        return description;
    }

    /**
     *权限描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
     *更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *更新时间
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
        Permission other = (Permission) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getPlatform() == null ? other.getPlatform() == null : this.getPlatform().equals(other.getPlatform()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getOrd() == null ? other.getOrd() == null : this.getOrd().equals(other.getOrd()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getPlatform() == null) ? 0 : getPlatform().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getOrd() == null) ? 0 : getOrd().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}