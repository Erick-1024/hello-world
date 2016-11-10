/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.common.dao.po;

import java.io.Serializable;
import java.util.Date;

public class Properties implements Serializable {
    /**
     *版本号
     */
    private String name;

    /**
     *流水创建时间
     */
    private Date createTime;

    /**
     *流水创建时间
     */
    private Date updateTime;

    /**
     *对应key的值
     */
    private String value;

    private static final long serialVersionUID = 1L;

    /**
     *版本号
     */
    public String getName() {
        return name;
    }

    /**
     *版本号
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     *流水创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *流水创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *流水创建时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *流水创建时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *对应key的值
     */
    public String getValue() {
        return value;
    }

    /**
     *对应key的值
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
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
        Properties other = (Properties) that;
        return (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        return result;
    }
}