/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.member.dao.po;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *角色名称
     */
    private String roleName;

    /**
     *描述
     */
    private String description;

    /**
     *类型（一级，二级）
     */
    private String type;

    /**
     *主账号Id
     */
    private String masterId;

    /**
     *序号
     */
    private Integer ord;

    /**
     *创建时间
     */
    private Date createtime;

    /**
     *更新时间
     */
    private Date updatetime;

    /**
     *
     */
    private String roleType;

    /**
     *权限（以分号隔开）
     */
    private String permissions;

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
     *角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     *角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     *描述
     */
    public String getDescription() {
        return description;
    }

    /**
     *描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     *类型（一级，二级）
     */
    public String getType() {
        return type;
    }

    /**
     *类型（一级，二级）
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     *主账号Id
     */
    public String getMasterId() {
        return masterId;
    }

    /**
     *主账号Id
     */
    public void setMasterId(String masterId) {
        this.masterId = masterId == null ? null : masterId.trim();
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
     *创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     *创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     *更新时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     *更新时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     *
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     *
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType == null ? null : roleType.trim();
    }

    /**
     *权限（以分号隔开）
     */
    public String getPermissions() {
        return permissions;
    }

    /**
     *权限（以分号隔开）
     */
    public void setPermissions(String permissions) {
        this.permissions = permissions == null ? null : permissions.trim();
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
        Role other = (Role) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName().equals(other.getRoleName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getMasterId() == null ? other.getMasterId() == null : this.getMasterId().equals(other.getMasterId()))
            && (this.getOrd() == null ? other.getOrd() == null : this.getOrd().equals(other.getOrd()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getRoleType() == null ? other.getRoleType() == null : this.getRoleType().equals(other.getRoleType()))
            && (this.getPermissions() == null ? other.getPermissions() == null : this.getPermissions().equals(other.getPermissions()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoleName() == null) ? 0 : getRoleName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getMasterId() == null) ? 0 : getMasterId().hashCode());
        result = prime * result + ((getOrd() == null) ? 0 : getOrd().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getRoleType() == null) ? 0 : getRoleType().hashCode());
        result = prime * result + ((getPermissions() == null) ? 0 : getPermissions().hashCode());
        return result;
    }
}